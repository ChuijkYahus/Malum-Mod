package com.sammy.malum.common.effect.rite.aura;

import com.sammy.malum.*;
import com.sammy.malum.registry.common.magic.MalumSpiritTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import team.lodestar.lodestone.helpers.ColorHelper;

public class AerialAura extends MobEffect {
    public AerialAura() {
        super(MobEffectCategory.BENEFICIAL, ColorHelper.getColor(MalumSpiritTypes.AERIAL_COLORS().primaryColor()));
        addAttributeModifier(Attributes.MOVEMENT_SPEED, MalumMod.malumPath("aerial_aura"), 0.2f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    }
}