package com.sammy.malum.common.entity.mob.cultist.cardinal;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import team.lodestar.lodestone.helpers.RandomHelper;

import java.util.EnumSet;

public class CardinalLobEntropyGoal extends Goal {

    private final CardinalCultist cardinal;

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

    private boolean isInLobbingState;

    public CardinalLobEntropyGoal(CardinalCultist cardinal, double speedModifier, int attackInterval, float attackRadius) {
        this.cardinal = cardinal;
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
        return attackTime > randomizedAttackInterval * 0.75f || this.canUse() || !cardinal.getNavigation().isDone();
    }

    @Override
    public void start() {
        super.start();
        cardinal.setAggressive(true);
        cardinal.getNavigation().stop();
        attackTime = 0;
        Minecraft.getInstance().player.displayClientMessage(Component.literal("Entering Lobbing State"), false);
    }

    @Override
    public void stop() {
        super.stop();
        cardinal.setAggressive(false);
        seeTime = 0;
        attackTime = 0;
        randomizedAttackInterval = -1;
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
                    cardinal.getNavigation().stop();
                    strafingTime++;
                } else {
                    cardinal.getNavigation().moveTo(target, speedModifier);
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

                cardinal.lookAt(target, 30.0F, 30.0F);
            } else {
                cardinal.getLookControl().setLookAt(target, 30.0F, 30.0F);
            }

            if (hasLineOfSight || isInLobbingState) {
                if (cardinal.entropyCharge == null) {
                    if (randomizedAttackInterval == -1) {
                        randomizedAttackInterval = Mth.floor(RandomHelper.randomBetween(random, attackInterval * 0.8f, attackInterval * 1.2f));
                    }
                    attackTime++;
                    if (attackTime == randomizedAttackInterval - LOB_ANIMATION_DELAY) {
                        cardinal.level().broadcastEntityEvent(cardinal, CardinalCultist.THROW_ANIMATION);
                        isInLobbingState = true;
                    }
                    if (attackTime >= randomizedAttackInterval) {
                        cardinal.throwEntropyCharge(target);
                        attackTime = 0;
                        randomizedAttackInterval = -1;
                        isInLobbingState = false;
                    }
                }
            } else if (seeTime < -60) {
                stop();
            }
        }
    }
}