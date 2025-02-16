package com.sammy.malum.common.effect;

import com.sammy.malum.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.entity.player.*;
import team.lodestar.lodestone.helpers.*;

public class WyrdExhaustionEffect extends MobEffect {
    public WyrdExhaustionEffect() {
        super(MobEffectCategory.HARMFUL, ColorHelper.getColor(64, 48, 100));
        addAttributeModifier(AttributeRegistry.ARCANE_RESONANCE, MalumMod.malumPath("wyrd_exhaustion"), -0.5f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    }
}