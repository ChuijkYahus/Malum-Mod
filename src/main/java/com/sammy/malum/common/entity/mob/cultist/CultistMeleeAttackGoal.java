package com.sammy.malum.common.entity.mob.cultist;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class CultistMeleeAttackGoal extends MeleeAttackGoal {

    private final CultistMonster cultist;

    public CultistMeleeAttackGoal(CultistMonster cultist, double speedModifier) {
        super(cultist, speedModifier, false);
        this.cultist = cultist;
    }

    @Override
    public void start() {
        super.start();
        cultist.setAggressive(true);
    }

    @Override
    public void stop() {
        super.stop();
        cultist.setAggressive(false);
    }
}
