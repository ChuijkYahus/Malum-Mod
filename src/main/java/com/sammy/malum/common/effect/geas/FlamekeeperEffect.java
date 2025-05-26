package com.sammy.malum.common.effect.geas;

import com.sammy.malum.*;
import net.minecraft.resources.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.ai.attributes.*;
import team.lodestar.lodestone.helpers.*;

public class FlamekeeperEffect extends MobEffect {
    public FlamekeeperEffect() {
        super(MobEffectCategory.BENEFICIAL, ColorHelper.getColor(255, 230, 93));
        final ResourceLocation id = MalumMod.malumPath("flamekeepers_fervor");
        addAttributeModifier(Attributes.ATTACK_SPEED, id, 0.01f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        addAttributeModifier(Attributes.BLOCK_BREAK_SPEED, id, 0.02f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    }
}