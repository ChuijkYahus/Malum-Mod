package com.sammy.malum.common.item.food;

import com.sammy.malum.common.effect.gluttony.*;
import com.sammy.malum.core.handlers.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.item.*;
import team.lodestar.lodestone.systems.network.*;
import com.sammy.malum.visual_effects.networked.gluttony.*;
import net.minecraft.core.*;
import net.minecraft.server.level.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import team.lodestar.lodestone.helpers.*;

import java.util.*;

public class ConcentratedGluttonyItem extends BottledDrinkItem {
    public static final Collection<Holder<Item>> ROTTEN_TRINKETS = List.of(ItemRegistry.RING_OF_DESPERATE_VORACITY, ItemRegistry.GLUTTONOUS_BROOCH, ItemRegistry.BELT_OF_THE_STARVED);
    public static final Collection<Holder<GeasEffectType>> ROTTEN_GEAS = List.of(MalumGeasEffectTypeRegistry.PACT_OF_THE_PROFANE_ASCETIC, MalumGeasEffectTypeRegistry.PACT_OF_THE_PROFANE_GLUTTON);

    public ConcentratedGluttonyItem(Properties builder) {
        super(builder);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving) {
        var properties = applyConcentratedGluttonyEffect(pEntityLiving, 1f);
        SoundHelper.playSound(pEntityLiving, SoundRegistry.CONCENTRATED_GLUTTONY_DRINK.get(), 1f, RandomHelper.randomBetween(pLevel.random, 1.5f, 2f));
        if (pLevel instanceof ServerLevel serverLevel) {
            final MobEffectInstance instance = pEntityLiving.getEffect(properties.getEffectType());
            if (instance != null) {
                createGluttonyVFX(serverLevel, pEntityLiving, instance.getAmplifier());
            }
        }
        return super.finishUsingItem(pStack, pLevel, pEntityLiving);
    }

    public static GluttonyEffect.GluttonyEffectProperties applyConcentratedGluttonyEffect(LivingEntity target, float durationScalar) {
        return GluttonyEffect.applyGluttony(target, b -> {
            int amplifier = 3;
            int duration = 20;

            if (CurioHelper.hasCurioEquipped(target, ItemRegistry.RING_OF_GRUESOME_CONCENTRATION.get())) {
                amplifier++;
                duration += 40;
            }
            for (Holder<Item> rottenTrinket : ROTTEN_TRINKETS) {
                if (CurioHelper.hasCurioEquipped(target, rottenTrinket.value())) {
                    amplifier++;
                    duration += 10;
                }
            }
            for (Holder<GeasEffectType> rottenGea : ROTTEN_GEAS) {
                if (GeasEffectHandler.hasGeasEffect(target, rottenGea)) {
                    amplifier++;
                    duration += 40;
                }
            }
            b.setInitialData((int) (duration * 20 * durationScalar), amplifier);
        });
    }

    public static void createGluttonyVFX(ServerLevel serverLevel, LivingEntity target, int amplifier) {
        createGluttonyVFX(serverLevel, target, 1f + amplifier * 0.05f);
    }

    public static void createGluttonyVFX(ServerLevel serverLevel, LivingEntity target, float potency) {
        var position = target.position().add(0, target.getBbHeight() / 2f, 0);
        ParticleEffectTypeRegistry.GLUTTONY_ABSORB.createEffect(position)
                .customData(new AbsorbGluttonyParticleEffect.AbsorbGluttonyEffectData(potency))
                .spawn(serverLevel);
    }
}