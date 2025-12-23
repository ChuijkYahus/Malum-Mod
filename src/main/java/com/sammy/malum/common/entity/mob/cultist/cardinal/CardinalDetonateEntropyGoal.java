package com.sammy.malum.common.entity.mob.cultist.cardinal;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;

import java.util.EnumSet;

public class CardinalDetonateEntropyGoal extends Goal {

    private final CardinalCultist cardinal;
    protected final PathNavigation navigation;

    private static final int CHARGE_DURATION = 40;
    private final double speedModifier;
    private final float detonationRadiusSqr;

    private int attackTime;

    public CardinalDetonateEntropyGoal(CardinalCultist cardinal, double speedModifier, float detonationRadius) {
        this.cardinal = cardinal;
        this.navigation = cardinal.getNavigation();
        this.speedModifier = speedModifier;
        this.detonationRadiusSqr = detonationRadius * detonationRadius;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        var target = cardinal.entropyCharge;
        if (target == null || !target.isPlaced() || !cardinal.getSensing().hasLineOfSight(target)) {
            return false;
        }
        return !target.getExplosionAffectedTargets().isEmpty();
    }

    @Override
    public boolean canContinueToUse() {
        return cardinal.entropyCharge != null;
    }

    @Override
    public void start() {
        super.start();
        cardinal.setAggressive(true);
        navigation.stop();
        cardinal.level().broadcastEntityEvent(cardinal, CardinalCultist.DETONATE_ANIMATION);
    }

    @Override
    public void stop() {
        super.stop();
        cardinal.setAggressive(false);
        attackTime = 0;
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        var target = cardinal.entropyCharge;
        if (target != null) {
            target.age--;

            double distanceToTarget = cardinal.distanceToSqr(target.getX(), target.getY(), target.getZ());
            if ((distanceToTarget < detonationRadiusSqr)) {
                navigation.stop();
            } else {
                navigation.moveTo(target, speedModifier);
            }
            cardinal.lookAtAndFaceTarget(target);

            attackTime++;
            if (attackTime >= CHARGE_DURATION) {
                if (cardinal.level() instanceof ServerLevel level) {
                    cardinal.triggerDetonation(level, target);
                }
                attackTime = 0;
                stop();
            }
        }
    }
}