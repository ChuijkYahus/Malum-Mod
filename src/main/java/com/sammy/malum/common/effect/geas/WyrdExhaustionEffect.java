package com.sammy.malum.common.effect.geas;

import com.sammy.malum.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.neoforged.neoforge.common.*;
import team.lodestar.lodestone.helpers.*;

import java.util.*;

public class WyrdExhaustionEffect extends MobEffect {
    public WyrdExhaustionEffect() {
        super(MobEffectCategory.HARMFUL, ColorHelper.getColor(64, 48, 100));
        addAttributeModifier(MalumAttributes.ARCANE_RESONANCE, MalumMod.malumPath("wyrd_exhaustion"), -0.5f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    }

    @Override
    public void fillEffectCures(Set<EffectCure> cures, MobEffectInstance effectInstance) {

    }
}