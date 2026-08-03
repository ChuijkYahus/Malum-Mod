package com.sammy.malum.common.effect.geas;

import com.sammy.malum.MalumMod;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.wayward_attributes.core.registry.WaywardAttributeTypes;

public class ShakenFaithEffect extends MobEffect {
    public ShakenFaithEffect() {
        super(MobEffectCategory.HARMFUL, ColorHelper.getColor(24, 124, 234));
        addAttributeModifier(Attributes.MOVEMENT_SPEED, MalumMod.malumPath("shaken_faith"), -0.4f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    }
}