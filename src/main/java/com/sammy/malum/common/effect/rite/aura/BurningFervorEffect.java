package com.sammy.malum.common.effect.rite.aura;

import com.sammy.malum.*;
import com.sammy.malum.registry.common.magic.MalumSpiritTypes;
import net.minecraft.resources.*;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import team.lodestar.lodestone.helpers.ColorHelper;

public class BurningFervorEffect extends MobEffect {
    public BurningFervorEffect() {
        super(MobEffectCategory.BENEFICIAL, ColorHelper.getColor(MalumSpiritTypes.INFERNAL_COLORS().primaryColor()));
        var id = MalumMod.malumPath("infernal_aura");
        addAttributeModifier(Attributes.ATTACK_SPEED, id, 0.2f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        addAttributeModifier(Attributes.BLOCK_BREAK_SPEED, id, 0.2f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    }
}