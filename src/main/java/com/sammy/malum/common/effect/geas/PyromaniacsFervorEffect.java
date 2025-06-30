package com.sammy.malum.common.effect.geas;

import com.sammy.malum.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.ai.attributes.*;
import team.lodestar.lodestone.helpers.*;

public class PyromaniacsFervorEffect extends MobEffect {
    public PyromaniacsFervorEffect() {
        super(MobEffectCategory.BENEFICIAL, ColorHelper.getColor(255, 230, 93));
        var id = MalumMod.malumPath("pyromaniacs_fervor");
        addAttributeModifier(Attributes.MOVEMENT_SPEED, id, 0.05f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        addAttributeModifier(Attributes.BLOCK_BREAK_SPEED, id, 0.05f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        addAttributeModifier(Attributes.ATTACK_SPEED, id, 0.05f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    }
}