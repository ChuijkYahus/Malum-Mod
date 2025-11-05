package com.sammy.malum.core.handlers;

import com.sammy.malum.*;
import com.sammy.malum.common.data.attachment.*;
import com.sammy.malum.core.listeners.*;
import com.sammy.malum.core.listeners.MalignantConversionReloadListener.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.resources.*;
import net.minecraft.server.level.*;
import net.minecraft.util.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.entity.player.*;
import net.neoforged.neoforge.event.entity.living.*;
import net.neoforged.neoforge.event.tick.*;

import java.util.function.*;

public class MalignantConversionHandler {

    public static final ResourceLocation NEGATIVE_MODIFIER_ID = MalumMod.malumPath("malignant_conversion_tally");
    public static final Function<Holder<Attribute>, ResourceLocation> POSITIVE_MODIFIER_IDS = Util.memoize(MalignantConversionHandler::createPositiveModifierId);

    public static void absorbDamage(LivingDamageEvent.Pre event) {
        var entity = event.getEntity();
        if (entity.level() instanceof ServerLevel level) {
            var data = entity.getData(MalumAttachmentTypes.MALIGNANT_INFLUENCE);
            int debt = data.getReinforcementDebt();
            double reinforcement = entity.getAttributeValue(MalumAttributes.MALIGNANT_REINFORCEMENT);
            int limit = 5 + Mth.floor(reinforcement * 2);
            if (debt < limit) {
                float delta = (limit - debt) / (float) limit;
                float chance = 0.5f * delta;
                if (level.getRandom().nextFloat() < chance) {
                    data.incrementReinforcementDebt();
                    event.setNewDamage(0);
                }
            }
        }
    }


    public static void entityTick(EntityTickEvent.Pre event) {
        if (event.getEntity() instanceof LivingEntity livingEntity) {
            if (livingEntity.level() instanceof ServerLevel level) {
                if (level.getGameTime() % 100 == 0) {
                    if (livingEntity.hasData(MalumAttachmentTypes.MALIGNANT_INFLUENCE)) {
                        var data = livingEntity.getData(MalumAttachmentTypes.MALIGNANT_INFLUENCE);
                        data.reduceReinforcementDebt();
                    }
                }
                if (livingEntity instanceof Player player && player.isSpectator()) {
                    return;
                }
                else {
                    //Malignant Conversion isn't that important on non-player entities
                    //To avoid lag, we only run once every two seconds
                    if (level.getGameTime() % 40 != 0) {
                        return;
                    }
                }
                var conversionInstance = livingEntity.getAttribute(MalumAttributes.MALIGNANT_CONVERSION);
                if (conversionInstance != null) {
                    var data = livingEntity.getData(MalumAttachmentTypes.MALIGNANT_INFLUENCE);
                    runConversionLogic(livingEntity, conversionInstance, data);
                }
            }
        }
    }

    private static void runConversionLogic(LivingEntity livingEntity, AttributeInstance malignantConversion, MalignantInfluenceCacheData data) {
        var malignantConversionAttribute = malignantConversion.getAttribute();
        if (!data.canPerformConversion(malignantConversion)) {
            return;
        }
        var conversions = MalignantConversionReloadListener.CONVERSION_DATA.values();
        boolean forcedUpdate = checkForChanges(data, livingEntity, malignantConversionAttribute);
        for (MalignantConversionData conversionData : conversions) {
            // This for loop checks for any attribute that can be converted through malignant conversion
            // If the attribute is present on the entity and has changed since this code last ran, we will attempt to convert it
            // If the malignant conversion attribute changed itself, we will update all attributes regardless of if they themselves changed
            if (forcedUpdate || checkForChanges(data, livingEntity, conversionData.sourceAttribute())) {
                tryConvertAttribute(livingEntity, malignantConversion, data, conversionData);
            }
        }
        data.cacheValue(malignantConversion);
    }

    private static void tryConvertAttribute(LivingEntity livingEntity, AttributeInstance malignantConversion, MalignantInfluenceCacheData cacheData, MalignantConversionData conversionData) {
        var sourceAttribute = conversionData.sourceAttribute();
        var sourceInstance = livingEntity.getAttribute(sourceAttribute);
        if (sourceInstance == null) {
            return;
        }
        float conversionStrength = (float) malignantConversion.getValue();
        boolean hasMalignantConversion = conversionStrength > 0;
        //Before any actual logic, we remove any toll applied by malignant conversion
        //This is done to give us the actual raw value of the attribute, unaffected by the conversion process
        removeNegativeModifier(sourceInstance);
        double convertedAmount = sourceInstance.getValue() - (conversionData.ignoreBaseValue() ? sourceInstance.getBaseValue() : 0);
        for (MalignantConversionAttributePayout payout : conversionData.payoutData()) {
            var affectedAttribute = payout.attribute();
            var affectedInstance = livingEntity.getAttribute(affectedAttribute);
            double payoutRatio = payout.ratio();
            if (affectedInstance != null) {
                var id = POSITIVE_MODIFIER_IDS.apply(sourceAttribute);
                affectedInstance.removeModifier(id);
                double bonus = convertedAmount * conversionStrength * payoutRatio;
                if (bonus > 0) {
                    var modifier = new AttributeModifier(id, bonus, AttributeModifier.Operation.ADD_VALUE);
                    affectedInstance.addTransientModifier(modifier);
                }
            }
        }
        if (hasMalignantConversion) {
            double ratio = conversionData.consumptionRatio();
            double toll = -conversionStrength * ratio;
            addNegativeModifier(sourceInstance, toll);
        }
        cacheData.cacheValue(sourceInstance);
    }

    private static boolean checkForChanges(MalignantInfluenceCacheData data, LivingEntity livingEntity, Holder<Attribute> attribute) {
        var instance = livingEntity.getAttribute(attribute);
        if (instance == null) {
            return false;
        }
        if (data.hasCachedValue(attribute)) {
            var modifier = instance.getModifier(NEGATIVE_MODIFIER_ID);
            boolean isFullConversion = modifier != null && modifier.amount() <= -1;
            if (isFullConversion) {
                //We're trying to detect changes to the attribute, this cannot be done if malignant conversion is fully present, so we remove the modifier in such situation
                removeNegativeModifier(instance);
            }
            double stored = data.getCachedValue(attribute);
            double real = instance.getValue();
            if (isFullConversion) {
                //We reapply the removed modifier after checking for changes
                instance.addTransientModifier(modifier);
            }
            return stored != real;
        }
        return false;
    }

    private static void removeNegativeModifier(AttributeInstance attribute) {
        var modifier = attribute.getModifier(NEGATIVE_MODIFIER_ID);
        if (modifier != null) {
            attribute.removeModifier(modifier);
        }
    }

    private static void addNegativeModifier(AttributeInstance attribute, double toll) {
        var modifier = new AttributeModifier(NEGATIVE_MODIFIER_ID, toll, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        attribute.addTransientModifier(modifier);
    }

    private static ResourceLocation createPositiveModifierId(Holder<Attribute> attribute) {
        return MalumMod.malumPath("malignant_conversion_buff_from_" + BuiltInRegistries.ATTRIBUTE.getKey(attribute.value()).getPath());
    }
}