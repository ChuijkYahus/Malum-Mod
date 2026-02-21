package com.sammy.malum.common.item.food;

import com.sammy.malum.common.effect.gluttony.*;
import com.sammy.malum.core.handlers.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.item.*;
import com.sammy.malum.registry.common.magic.*;
import com.sammy.malum.registry.common.sound.*;
import com.sammy.malum.visual_effects.networked.gluttony.*;
import net.minecraft.core.*;
import net.minecraft.server.level.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import team.lodestar.lodestone.helpers.*;

import java.util.*;

public class ConcentratedGluttonyItem extends BottledDrinkItem {
    public static final Collection<Holder<Item>> ROTTEN_TRINKETS = List.of(MalumItems.RING_OF_DESPERATE_VORACITY, MalumItems.GLUTTONOUS_BROOCH, MalumItems.BELT_OF_THE_STARVED);
    public static final Collection<Holder<GeasEffectType>> ROTTEN_GEAS = List.of(MalumGeasEffectTypes.PACT_OF_THE_PROFANE_ASCETIC, MalumGeasEffectTypes.PACT_OF_THE_PROFANE_GLUTTON);

    public ConcentratedGluttonyItem(Properties builder) {
        super(builder);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving) {
        var properties = applyConcentratedGluttonyEffect(pEntityLiving, 1f);
        SoundHelper.playSound(pEntityLiving, MalumGearSoundEvents.CONCENTRATED_GLUTTONY_DRINK.get(), 1f, RandomHelper.randomBetween(pLevel.random, 1.5f, 2f));
        if (pLevel instanceof ServerLevel serverLevel) {
            if (pEntityLiving.hasEffect(properties.getEffectType())) {
                createGluttonyVFX(serverLevel, pEntityLiving, 0.75f);
            }
        }
        return super.finishUsingItem(pStack, pLevel, pEntityLiving);
    }

    public static GluttonyEffect.GluttonyEffectProperties applyConcentratedGluttonyEffect(LivingEntity target, float durationScalar) {
        return GluttonyEffect.applyGluttony(target, builder -> {
            int amplifier = 3;
            int duration = 600;

            if (CurioHelper.hasCurioEquipped(target, MalumItems.RING_OF_GRUESOME_CONCENTRATION.get())) {
                amplifier++;
                duration += 400;
            }
            for (Holder<Item> rottenTrinket : ROTTEN_TRINKETS) {
                if (CurioHelper.hasCurioEquipped(target, rottenTrinket.value())) {
                    amplifier++;
                    duration += 100;
                }
            }
            for (Holder<GeasEffectType> rottenGea : ROTTEN_GEAS) {
                if (GeasEffectHandler.hasGeasEffect(target, rottenGea)) {
                    amplifier++;
                    duration += 400;
                }
            }
            builder.setDuration((int) (duration * durationScalar));
            builder.setInitialAmplifier(amplifier);
            builder.setAmplifierLimit(10);
        });
    }

    public static void createGluttonyVFX(ServerLevel serverLevel, LivingEntity target, float potency) {
        var position = target.position().add(0, target.getBbHeight() / 2f, 0);
        MalumParticleEffectTypes.GLUTTONY_ABSORB.createEffect(position)
                .customData(new AbsorbGluttonyParticleEffect.AbsorbGluttonyEffectData(potency))
                .spawn(serverLevel);
    }
}