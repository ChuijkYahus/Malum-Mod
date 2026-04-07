package com.sammy.malum.common.entity.mob.cultist.altar.goal;

import java.util.EnumSet;

import com.sammy.malum.common.entity.mob.cultist.altar.AltarCultist;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import team.lodestar.lodestone.modules.core.easing.Easing;


public class AltarRangedAttackGoal extends Goal {

    private final AltarCultist altar;
    protected final PathNavigation navigation;

    private final double speedModifier;
    private final int attackInterval;
    private final float attackRadiusSqr;

    private int attackTime;
    private int randomizedAttackInterval;

    private int seeTime;
    private boolean strafingClockwise;
    private boolean strafingBackwards;
    private int strafingTime = -1;

    public AltarRangedAttackGoal(AltarCultist altar, double speedModifier) {
        this(altar, speedModifier, AltarCultist.RANGED_ATTACK_INTERVAL, AltarCultist.RANGED_ATTACK_RADIUS);
    }
    public AltarRangedAttackGoal(AltarCultist altar, double speedModifier, int attackInterval, float attackRadius) {
        this.altar = altar;
        this.navigation = altar.getNavigation();
        this.speedModifier = speedModifier;
        this.attackInterval = attackInterval;
        this.attackRadiusSqr = attackRadius * attackRadius;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        return altar.target != null && !altar.shouldChaseTarget();
    }

    @Override
    public boolean canContinueToUse() {
        return this.canUse() || !navigation.isDone();
    }

    @Override
    public void start() {
        super.start();
        altar.setAggressive(true);
        setRandomizedAttackInterval();
    }

    @Override
    public void stop() {
        super.stop();
        altar.setAggressive(false);
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
                if (distanceToTarget > attackRadiusSqr * 0.75F) {
                    strafingBackwards = false;
                } else if (distanceToTarget < attackRadiusSqr * 0.25F) {
                    strafingBackwards = true;
                }

                float factor = 0.5F;
                float forward = strafingBackwards ? -factor : factor;
                float right = strafingClockwise ? factor : -factor;
                altar.getMoveControl().strafe(forward, right);
                if (altar.getControlledVehicle() instanceof Mob mob) {
                    mob.lookAt(target, 30.0F, 30.0F);
                }
            }
            altar.lookAtAndFaceTarget(target);

            if (hasLineOfSight) {
                attackTime++;
                if (attackTime >= randomizedAttackInterval) {
                    altar.performRangedAttack(target);
                    setRandomizedAttackInterval();
                    attackTime = 0;
                }
            } else if (seeTime < -60) {
                stop();
            }
        }
    }

    public void setRandomizedAttackInterval() {
        float min = attackInterval * 0.8f;
        float max = attackInterval * 1.2f;
        randomizedAttackInterval = Mth.floor(Easing.SINE_IN_OUT.asWeighedRandom(altar.getRandom(), min, max));
    }
}