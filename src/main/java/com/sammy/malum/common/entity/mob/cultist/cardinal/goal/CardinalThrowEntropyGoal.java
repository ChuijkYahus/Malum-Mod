package com.sammy.malum.common.entity.mob.cultist.cardinal.goal;

import com.sammy.malum.common.entity.mob.cultist.cardinal.CardinalCultist;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import team.lodestar.lodestone.helpers.RandomHelper;

import java.util.EnumSet;

public class CardinalThrowEntropyGoal extends Goal {

    private final CardinalCultist cardinal;
    protected final PathNavigation navigation;

    private static final int LOB_ANIMATION_DELAY = 12;

    private final double speedModifier;
    private final int attackInterval;
    private final float attackRadiusSqr;

    private int attackTime;
    private int randomizedAttackInterval;

    private int seeTime;
    private boolean strafingClockwise;
    private boolean strafingBackwards;
    private int strafingTime = -1;

    private boolean isInThrowingState;

    public CardinalThrowEntropyGoal(CardinalCultist cardinal, double speedModifier) {
        this(cardinal, speedModifier, CardinalCultist.ENTROPY_THROW_INTERVAL, CardinalCultist.ENTROPY_THROW_RADIUS);
    }
    public CardinalThrowEntropyGoal(CardinalCultist cardinal, double speedModifier, int attackInterval, float attackRadius) {
        this.cardinal = cardinal;
        this.navigation = cardinal.getNavigation();
        this.speedModifier = speedModifier;
        this.attackInterval = attackInterval;
        this.attackRadiusSqr = attackRadius * attackRadius;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        return cardinal.target != null && !cardinal.shouldAvoidTarget();
    }

    @Override
    public boolean canContinueToUse() {
        if (isInThrowingState) {
            return true;
        }
        if (cardinal.canTriggerRetaliationBlast()) {
            return false;
        }
        return canUse() || !navigation.isDone();
    }

    @Override
    public void start() {
        super.start();
        setRandomizedAttackInterval();
        cardinal.setAggressive(true);
        navigation.stop();
        attackTime = 0;
    }

    @Override
    public void stop() {
        super.stop();
        cardinal.setAggressive(false);
        navigation.stop();
        randomizedAttackInterval = -1;
        attackTime = 0;
        seeTime = 0;
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        LivingEntity target = cardinal.getTarget();
        if (target != null) {
            var random = cardinal.getRandom();
            double distanceToTarget = cardinal.distanceToSqr(target.getX(), target.getY(), target.getZ());
            boolean hasLineOfSight = cardinal.getSensing().hasLineOfSight(target);
            boolean seesTarget = seeTime > 0;
            if (hasLineOfSight != seesTarget) {
                seeTime = 0;
            }

            if (hasLineOfSight) {
                seeTime++;
            } else {
                seeTime--;
            }

            if (seeTime > 20) {
                if ((distanceToTarget < attackRadiusSqr)) {
                    navigation.stop();
                    strafingTime++;
                } else {
                    navigation.moveTo(target, speedModifier);
                    strafingTime = -1;
                }
            }

            if (strafingTime >= 20) {
                if ((double) random.nextFloat() < 0.3) {
                    strafingClockwise = !strafingClockwise;
                }

                if ((double) random.nextFloat() < 0.3) {
                    strafingBackwards = !strafingBackwards;
                }

                strafingTime = 0;
            }

            if (strafingTime >= 0) {
                if (distanceToTarget > attackRadiusSqr * 1.5f) {
                    strafingBackwards = false;
                } else if (distanceToTarget < attackRadiusSqr * 0.75F) {
                    strafingBackwards = true;
                }

                cardinal.getMoveControl().strafe(strafingBackwards ? -0.75F : 0.25F, strafingClockwise ? 0.5F : -0.5F);
                if (cardinal.getControlledVehicle() instanceof Mob mob) {
                    mob.lookAt(target, 30.0F, 30.0F);
                }
            }
            cardinal.lookAtAndFaceTarget(target);

            if (hasLineOfSight || isInThrowingState) {
                if (cardinal.entropyCharge == null) {
                    attackTime++;
                    if (attackTime == randomizedAttackInterval - LOB_ANIMATION_DELAY) {
                        cardinal.level().broadcastEntityEvent(cardinal, CardinalCultist.THROW_ANIMATION);
                        isInThrowingState = true;
                    }
                    if (attackTime >= randomizedAttackInterval) {
                        cardinal.throwEntropyCharge(target);
                        setRandomizedAttackInterval();
                        attackTime = 0;
                        isInThrowingState = false;
                    }
                }
            } else if (seeTime < -60) {
                stop();
            }
        }
    }

    public void setRandomizedAttackInterval() {
        randomizedAttackInterval = Mth.floor(RandomHelper.randomBetween(cardinal.getRandom(), attackInterval * 0.8f, attackInterval * 1.2f));
    }
}