package com.sammy.malum.common.effect.rite;

import com.sammy.malum.*;
import com.sammy.malum.core.handlers.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.magic.*;
import net.minecraft.resources.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import team.lodestar.lodestone.helpers.ColorHelper;

public class SacredNourishment extends MobEffect {
    public SacredNourishment() {
        super(MobEffectCategory.BENEFICIAL, ColorHelper.getColor(MalumSpiritTypes.SACRED_COLORS().primaryColor()));
    }

    public static float recoverExhaustion(LivingEntity entity) {
        MobEffectInstance instance = entity.getEffect(MalumMobEffects.SACRED_NOURISHMENT);
        if (instance != null) {
            return 0.02f * (instance.amplifier+1);
        }
        return 0;
    }
}