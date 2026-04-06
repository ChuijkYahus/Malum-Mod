package com.sammy.malum.common.entity.mob.cultist;

import net.minecraft.world.entity.AnimationState;

import java.util.function.Supplier;

public class CultistAnimationState extends AnimationState {

    protected final Supplier<Integer> tickCount;

    public CultistAnimationState(CultistMonster cultist) {
        this.tickCount = () -> cultist.tickCount;
    }
}
