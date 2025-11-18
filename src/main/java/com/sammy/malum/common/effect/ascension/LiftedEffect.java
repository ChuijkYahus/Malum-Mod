package com.sammy.malum.common.effect.ascension;

import com.sammy.malum.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.magic.*;
import net.minecraft.util.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.phys.*;
import net.neoforged.neoforge.event.entity.living.*;
import team.lodestar.lodestone.helpers.*;

public class LiftedEffect extends MobEffect {
    public LiftedEffect() {
        super(MobEffectCategory.BENEFICIAL, ColorHelper.getColor(MalumSpiritTypes.AERIAL_COLORS().primaryColor()));
    }

    public static double modifyVelocity(LivingEntity entity, double original) {
        MobEffectInstance effectInstance = entity.getEffect(MalumMobEffects.LIFTED);
        if (effectInstance != null) {
            int amplifier = effectInstance.getAmplifier();
            float limit = 0.2f / (amplifier+1);
            return Math.max(original, -limit);
        }
        return original;
    }

    public static void onEntityFall(LivingFallEvent event) {
        LivingEntity entity = event.getEntity();
        MobEffectInstance effectInstance = entity.getEffect(MalumMobEffects.LIFTED);
        if (effectInstance != null) {
            event.setDistance(event.getDistance() / (10 + effectInstance.getAmplifier() * 2));
            entity.removeEffect(MalumMobEffects.LIFTED);
        }
    }
}