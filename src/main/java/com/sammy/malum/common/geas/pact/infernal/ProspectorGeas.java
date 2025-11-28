package com.sammy.malum.common.geas.pact.infernal;

import com.sammy.malum.core.handlers.*;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.magic.*;
import com.sammy.malum.visual_effects.networked.*;
import net.minecraft.core.*;
import net.minecraft.network.chat.*;
import net.minecraft.server.level.*;
import net.minecraft.tags.*;
import net.minecraft.util.*;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.item.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.event.entity.living.*;
import net.neoforged.neoforge.event.entity.player.*;
import net.neoforged.neoforge.event.level.*;
import team.lodestar.lodestone.helpers.*;

import java.util.function.*;

public class ProspectorGeas extends GeasEffect {

    public ProspectorGeas() {
        super(MalumGeasEffectTypes.PACT_OF_THE_PROSPECTOR.get());
    }

    @Override
    public void addTooltipComponents(LivingEntity entity, Consumer<Component> tooltipAcceptor, TooltipFlag tooltipFlag) {
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("ore_prospecting"));
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("avarice_fortune"));
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("avarice_healing"));
        tooltipAcceptor.accept(ComponentHelper.negativeGeasEffect("greed_combustion"));
        super.addTooltipComponents(entity, tooltipAcceptor, tooltipFlag);
    }

    @Override
    public void finalizedIncomingDamageEvent(LivingDamageEvent.Post event, LivingEntity attacker, LivingEntity target, ItemStack stack) {
        var source = event.getSource();
        if (!source.is(DamageTypeTags.IS_FIRE) && !source.is(DamageTypeTags.IS_EXPLOSION)) {
            var effect = target.getEffect(MalumMobEffects.AVARICE);
            if (effect != null) {
                if (target.level() instanceof ServerLevel level) {
                    target.igniteForSeconds((effect.amplifier + 1) * 0.5f);
                    MalumParticleEffectTypes.PROSPECTORS_STREAK_BURN.createEffect(target)
                            .color(new MalumNetworkedParticleEffectColorData(MalumSpiritTypes.INFERNAL_SPIRIT))
                            .spawn(level);
                }
                target.removeEffect(MalumMobEffects.AVARICE);
            }
        }
    }

    public static void pickupItem(ItemEntityPickupEvent.Post event) {
        ItemEntity entity = event.getItemEntity();
        entity.getExistingData(MalumAttachmentTypes.AVARICE_MARK).ifPresent(data -> {
            if (data.hasProspectorMark()) {
                applyAvarice(event.getPlayer());
            }
        });
    }

    public static void modifyBlockDrops(BlockDropsEvent event) {
        if (hasProspector(event.getBreaker())) {
            for (ItemEntity drop : event.getDrops()) {
                markEntity(drop);
            }
        }
    }

    public static void applyAvarice(LivingEntity target) {
        var effect = MalumMobEffects.AVARICE;
        var instance = target.getEffect(effect);
        if (instance == null) {
            target.addEffect(new MobEffectInstance(effect, 200, 0, true, true, true));
        } else {
            EntityHelper.amplifyEffect(instance, target, 1, 9);
            EntityHelper.extendEffect(instance, target, 100, 1200);
        }
        if (GeasEffectHandler.hasGeasEffect(target, MalumGeasEffectTypes.PACT_OF_THE_PROSPECTOR)) {
            target.heal(2);
        }
    }

    public static void popResourceAndMarkEntity(Level level, BlockPos pos, ItemStack stack) {
        double d0 = (double) EntityType.ITEM.getHeight() / 2.0;
        double d1 = (double) pos.getX() + 0.5 + Mth.nextDouble(level.random, -0.25, 0.25);
        double d2 = (double) pos.getY() + 0.5 + Mth.nextDouble(level.random, -0.25, 0.25) - d0;
        double d3 = (double) pos.getZ() + 0.5 + Mth.nextDouble(level.random, -0.25, 0.25);
        Block.popResource(level, () -> markEntity(new ItemEntity(level, d1, d2, d3, stack)), stack);
    }

    public static boolean hasProspector(Entity entity) {
        if (entity instanceof LivingEntity breaker) {
            return GeasEffectHandler.hasGeasEffect(breaker, MalumGeasEffectTypes.PACT_OF_THE_PROSPECTOR);
        }
        return false;
    }

    public static ItemEntity markEntity(ItemEntity entity) {
        entity.getData(MalumAttachmentTypes.AVARICE_MARK).enableMark();
        entity.syncData(MalumAttachmentTypes.AVARICE_MARK);
        return entity;
    }
}