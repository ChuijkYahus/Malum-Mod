package com.sammy.malum.common.entity.cultist.altar;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import team.lodestar.lodestone.helpers.RandomHelper;

public class AltarRangedAttackGoal extends Goal {

    private final AltarCultist altar;

    private final double speedModifier;
    private final int attackInterval;
    private final float attackRadiusSqr;

    private int attackTime;
    private int randomizedAttackInterval;

    private int seeTime;
    private boolean strafingClockwise;
    private boolean strafingBackwards;
    private int strafingTime = -1;

    public AltarRangedAttackGoal(AltarCultist altar, double speedModifier, int attackInterval, float attackRadius) {
        this.altar = altar;
        this.speedModifier = speedModifier;
        this.attackInterval = attackInterval;
        this.attackRadiusSqr = attackRadius * attackRadius;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        return altar.target != null && !altar.isWithinMeleeRadius();
    }

    @Override
    public boolean canContinueToUse() {
        return this.canUse() || !altar.getNavigation().isDone();
    }

    @Override
    public void start() {
        super.start();
        altar.setAggressive(true);
        altar.getNavigation().stop();
        Minecraft.getInstance().player.displayClientMessage(Component.literal("Entering Ranged"), false);
    }

    @Override
    public void stop() {
        super.stop();
        altar.setAggressive(false);
        seeTime = 0;
        attackTime = -1;
        randomizedAttackInterval = -1;
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        LivingEntity target = altar.getTarget();
        if (target != null) {
            var random = altar.getRandom();
            double distanceToTarget = altar.distanceToSqr(target.getX(), target.getY(), target.getZ());
            boolean hasLineOfSight = altar.getSensing().hasLineOfSight(target);
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
                    altar.getNavigation().stop();
                    strafingTime++;
                } else {
                    altar.getNavigation().moveTo(target, speedModifier);
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
                if (distanceToTarget > attackRadiusSqr * 0.75F) {
                    strafingBackwards = false;
                } else if (distanceToTarget < attackRadiusSqr * 0.25F) {
                    strafingBackwards = true;
                }

                altar.getMoveControl().strafe(strafingBackwards ? -0.5F : 0.5F, strafingClockwise ? 0.5F : -0.5F);
                if (altar.getControlledVehicle() instanceof Mob mob) {
                    mob.lookAt(target, 30.0F, 30.0F);
                }

                altar.lookAt(target, 30.0F, 30.0F);
            } else {
                altar.getLookControl().setLookAt(target, 30.0F, 30.0F);
            }


            if (hasLineOfSight) {
                if (randomizedAttackInterval == -1) {
                    randomizedAttackInterval = Mth.floor(RandomHelper.randomBetween(random, attackInterval*0.8f, attackInterval*1.2f));
                }
                attackTime++;
                if (attackTime >= randomizedAttackInterval) {
                    altar.performRangedAttack(target);
                    attackTime = 0;
                    randomizedAttackInterval = -1;
                }
            }
            else if (seeTime < -60) {
                stop();
            }
        }
    }
}