package com.sammy.malum.common.effect.geas;

import com.sammy.malum.common.geas.pact.infernal.*;
import net.minecraft.world.effect.*;
import net.neoforged.neoforge.common.*;
import team.lodestar.lodestone.helpers.*;

import java.util.*;

public class GleefulTargetEffect extends MobEffect {
    public GleefulTargetEffect() {
        super(MobEffectCategory.HARMFUL, ColorHelper.getColor(250, 240, 100));
    }

    @Override
    public void fillEffectCures(Set<EffectCure> cures, MobEffectInstance effectInstance) {

    }
}