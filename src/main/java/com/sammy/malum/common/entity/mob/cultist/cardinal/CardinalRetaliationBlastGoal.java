package com.sammy.malum.common.entity.mob.cultist.cardinal;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.level.pathfinder.Path;

import javax.annotation.Nullable;
import java.util.EnumSet;

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
        cardinal.level().broadcastEntityEvent(cardinal, CardinalCultist.RETALIATION_BLAST_ANIMATION);
        cardinal.setAggressive(true);
    }

    @Override
    public void stop() {
        super.stop();
        cardinal.setAggressive(false);
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