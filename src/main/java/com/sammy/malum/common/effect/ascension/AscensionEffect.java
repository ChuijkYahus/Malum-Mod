package com.sammy.malum.common.effect.ascension;

import com.sammy.malum.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.magic.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.neoforged.neoforge.event.entity.living.*;
import team.lodestar.lodestone.helpers.*;

public class AscensionEffect extends MobEffect {
    public AscensionEffect() {
        super(MobEffectCategory.BENEFICIAL, ColorHelper.getColor(MalumSpiritTypes.AERIAL_COLORS().primaryColor()));
        addAttributeModifier(Attributes.GRAVITY, MalumMod.malumPath("ascension"), -0.10f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    }

    public static void onEntityFall(LivingFallEvent event) {
        LivingEntity entity = event.getEntity();
        MobEffectInstance effectInstance = entity.getEffect(MalumMobEffects.ASCENSION);
        if (effectInstance != null) {
            event.setDistance(event.getDistance() / (6 + effectInstance.getAmplifier()));
            entity.removeEffect(MalumMobEffects.ASCENSION);
        }
    }
}