package com.sammy.malum.common.entity.mob.cultist.cardinal.goal;

import com.sammy.malum.common.entity.mob.cultist.cardinal.CardinalCultist;
import com.sammy.malum.registry.common.sound.*;
import net.minecraft.server.level.ServerLevel;

public class CardinalRetaliationBlastGoal extends CardinalAvoidTargetGoal {

    private static final int CHARGE_DURATION = 10;

    private int attackTime;

    public CardinalRetaliationBlastGoal(CardinalCultist cardinal, double speedModifier) {
        super(cardinal, speedModifier);
    }

    @Override
    public boolean canUse() {
        return cardinal.canTriggerRetaliationBlast();
    }

    @Override
    public boolean canContinueToUse() {
        return attackTime > 0 || canUse();
    }

    @Override
    public void start() {
        super.start();
        cardinal.broadcastAnimation(CardinalCultist.RETALIATION_BLAST_ANIMATION, MalumCultistSoundEvents.CARDINAL_KNOCKBACK_CHARGE);
        cardinal.setAggressive(true);
    }

    @Override
    public void stop() {
        super.stop();
        cardinal.setAggressive(false);
        attackTime = 0;
    }

    @Override
    public void tick() {
        super.tick();
        var target = cardinal.target;
        if (target != null) {
            attackTime++;
            if (attackTime >= CHARGE_DURATION) {
                if (cardinal.level() instanceof ServerLevel level) {
                    cardinal.triggerRetaliationBlast(level);
                }
                attackTime = 0;
            }
        }
    }
}