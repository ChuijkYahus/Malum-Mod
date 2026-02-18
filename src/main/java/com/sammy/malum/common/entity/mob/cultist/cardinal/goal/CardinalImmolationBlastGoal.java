package com.sammy.malum.common.entity.mob.cultist.cardinal.goal;

import com.sammy.malum.common.entity.mob.cultist.cardinal.CardinalCultist;
import com.sammy.malum.registry.common.sound.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class CardinalImmolationBlastGoal extends Goal {

    private final CardinalCultist cardinal;

    private static final int CHARGE_DURATION = 50;

    private int attackTime;

    public CardinalImmolationBlastGoal(CardinalCultist cardinal) {
        this.cardinal = cardinal;
        this.setFlags(EnumSet.of(Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        return cardinal.canTriggerImmolationBlast();
    }

    @Override
    public boolean canContinueToUse() {
        return attackTime > 0 || canUse();
    }

    @Override
    public void start() {
        super.start();
        cardinal.setAggressive(true);
        cardinal.getNavigation().stop();
        cardinal.broadcastAnimation(CardinalCultist.IMMOLATION_BLAST_ANIMATION, MalumCultistSoundEvents.CARDINAL_IMMOLATION_CHARGE);
    }

    @Override
    public void stop() {
        super.stop();
        cardinal.setAggressive(false);
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        var target = cardinal.target;
        attackTime++;
        if (attackTime >= CHARGE_DURATION) {
            if (cardinal.level() instanceof ServerLevel level) {
                cardinal.triggerImmolationBlast(level);
            }
            attackTime = 0;
            stop();
        }
        if (target != null) {
            cardinal.lookAtAndFaceTarget(target);
        }
    }
}