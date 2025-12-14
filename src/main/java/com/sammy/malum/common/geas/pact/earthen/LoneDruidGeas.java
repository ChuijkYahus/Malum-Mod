package com.sammy.malum.common.geas.pact.earthen;

import com.google.common.collect.*;
import com.sammy.malum.common.worldevent.*;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.magic.*;
import com.sammy.malum.visual_effects.networked.MalumNetworkedParticleEffectColorData;
import net.minecraft.core.*;
import net.minecraft.network.chat.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.event.tick.*;
import team.lodestar.lodestone.handlers.*;

import java.util.function.*;

public class LoneDruidGeas extends GeasEffect {

    public float coverPercentage;
    public LoneDruidGeas() {
        super(MalumGeasEffectTypes.PACT_OF_THE_LONE_DRUID.get());
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> createAttributeModifiers(LivingEntity entity, Multimap<Holder<Attribute>, AttributeModifier> modifiers) {
        float bonus = (1 - coverPercentage)*4;
        if (bonus > 0) {
            addAttributeModifier(modifiers, Attributes.ARMOR, 5*bonus, AttributeModifier.Operation.ADD_VALUE);
            addAttributeModifier(modifiers, Attributes.ARMOR_TOUGHNESS, 2*bonus, AttributeModifier.Operation.ADD_VALUE);
            addAttributeModifier(modifiers, MalumAttributes.HEALING_MULTIPLIER, 0.25f*bonus, AttributeModifier.Operation.ADD_VALUE);
        }
        return modifiers;
    }

    @Override
    public void addTooltipComponents(LivingEntity entity, Consumer<Component> tooltipAcceptor, TooltipFlag tooltipFlag) {
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("no_armor_druid_armor"));
        tooltipAcceptor.accept(ComponentHelper.negativeGeasEffect("no_armor"));
    }

    @Override
    public void update(EntityTickEvent.Pre event, LivingEntity entity) {
        if (entity.level() instanceof ServerLevel level) {
            float oldCoverPercentage = coverPercentage;
            coverPercentage = entity.getArmorCoverPercentage();
            if (oldCoverPercentage != coverPercentage) {
                setDirty();
            }
            if (entity.getHealth() <= entity.getMaxHealth() / 2f) {
                if (coverPercentage > 0) {
                    long interval = (long) (100 - coverPercentage * 80f);
                    if (entity.level().getGameTime() % interval == 0) {
                        WorldEventHandler.addWorldEvent(entity.level(),
                                new DelayedDamageWorldEvent(entity)
                                        .setDamageData(0, 2, 4)
                                        .setMagicDamageType(MalumDamageTypes.KARMIC));

                        MalumParticleEffectTypes.HIGH_PRIEST_PENANCE.createEffect(entity)
                                .color(new MalumNetworkedParticleEffectColorData(MalumSpiritTypes.EARTHEN_SPIRIT, MalumSpiritTypes.ELDRITCH_SPIRIT))
                                .spawn(level);
                    }
                }
            }
        }
    }
}