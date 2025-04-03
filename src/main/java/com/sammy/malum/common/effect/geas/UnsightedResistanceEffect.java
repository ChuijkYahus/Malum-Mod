package com.sammy.malum.common.effect.geas;

import com.sammy.malum.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.resources.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.ai.attributes.*;
import team.lodestar.lodestone.helpers.*;

public class UnsightedResistanceEffect extends MobEffect {
    public UnsightedResistanceEffect() {
        super(MobEffectCategory.BENEFICIAL, ColorHelper.getColor(90, 90, 140));
        final ResourceLocation id = MalumMod.malumPath("unsighted_resistance");
        addAttributeModifier(Attributes.ARMOR, id, 0.1f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        addAttributeModifier(Attributes.ARMOR_TOUGHNESS, id, 0.1f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    }
}