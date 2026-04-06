package com.sammy.malum.common.entity.mob.cultist.evangelist.goal;

import com.sammy.malum.common.entity.mob.cultist.evangelist.EvangelistCultist;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;

public abstract class AbstractEvangelistMeleeAttackGoal extends MeleeAttackGoal {

    protected final EvangelistCultist evangelistCultist;
    protected final PathNavigation pathNav;

    protected LivingEntity cachedTarget;
    protected boolean isReadyingAttack;
    protected int attackDelay;

    public AbstractEvangelistMeleeAttackGoal(EvangelistCultist evangelistCultist, double speedModifier) {
        super(evangelistCultist, speedModifier, true);
        this.evangelistCultist = evangelistCultist;
        this.pathNav = evangelistCultist.getNavigation();
    }

    public abstract int getAttackDelay();

    public abstract byte getAttackAnimation();

    @Override
    public boolean canUse() {
        var target = evangelistCultist.getTarget();
        if (target == null) {
            return false;
        }
        if (!target.isAlive()) {
            return false;
        }
        long time = evangelistCultist.level().getGameTime();

        if (isReadyingAttack || time - lastCanUseCheck > 4L) {
            lastCanUseCheck = time;
            path = pathNav.createPath(target, 0);
            return path != null;
        } else {
            return false;
        }
    }

    @Override
    public boolean canContinueToUse() {
        if (isReadyingAttack) {
            return true;
        }
        var target = evangelistCultist.getTarget();
        if (target == null) {
            return false;
        }
        if (!target.isAlive()) {
            return false;
        }
        if (followingTargetEvenIfNotSeen) {
            if (!evangelistCultist.isWithinRestriction(target.blockPosition())) {
                return false;
            }
            if (target.isSpectator()) {
                return false;
            }
            if (target instanceof Player player) {
                return !player.isCreative();
            }
            return true;
        } else {
            return !pathNav.isDone();
        }
    }

    @Override
    public void tick() {
        if (isReadyingAttack) {
            tickReadiedAttack();
        }
        super.tick();
        evangelistCultist.lookAtAndFaceTarget(evangelistCultist.getTarget());
    }

    public void tickReadiedAttack() {
        if (attackDelay > 0) {
            attackDelay--;
            if (attackDelay == 0) {
                isReadyingAttack = false;
                if (cachedTarget == null || cachedTarget.isDeadOrDying()) {
                    cachedTarget = null;
                }
                if (cachedTarget != null) {
                    if (canDamageCachedTarget(cachedTarget)) {
                        evangelistCultist.doHurtTarget(cachedTarget);
                        cachedTarget = null;
                    }
                }
                resetAttackCooldown();
            }
        }
    }

    @Override
    protected void checkAndPerformAttack(LivingEntity target) {
        if (isReadyingAttack) {
            return;
        }
        if (canPerformAttack(target)) {
            beginAttack(target);
        }
    }

    @Override
    protected boolean canPerformAttack(LivingEntity target) {
        if (evangelistCultist.isVehicle()) {
            return false;
        }
        if (!isTimeToAttack()) {
            return false;
        }
        if (!evangelistCultist.getSensing().hasLineOfSight(target)) {
            return false;
        }
        var vehicle = target.getVehicle();
        if (vehicle != null) {
            var aabb1 = vehicle.getBoundingBox();
            var aabb2 = target.getBoundingBox();
            var area = new AABB(
                    Math.min(aabb2.minX, aabb1.minX),
                    aabb2.minY,
                    Math.min(aabb2.minZ, aabb1.minZ),
                    Math.max(aabb2.maxX, aabb1.maxX),
                    aabb2.maxY,
                    Math.max(aabb2.maxZ, aabb1.maxZ));
            if (area.intersects(evangelistCultist.getHitbox())) {
                return true;
            }
        }
        return isCloseEnoughToStartSwing(target);
    }

    public void beginAttack(LivingEntity target) {
        evangelistCultist.broadcastAnimation(getAttackAnimation());
        cachedTarget = target;
        isReadyingAttack = true;
        attackDelay = getAttackDelay();
    }

    public boolean isCloseEnoughToStartSwing(LivingEntity target) {
        return evangelistCultist.isWithinMeleeAttackRange(target);
    }

    protected boolean canDamageCachedTarget(LivingEntity cachedTarget) {
        if (!isTimeToAttack()) {
            return false;
        }
        if (!evangelistCultist.getSensing().hasLineOfSight(cachedTarget)) {
            return false;
        }
        return cachedTarget.distanceTo(evangelistCultist) < 3f || evangelistCultist.isWithinMeleeAttackRange(cachedTarget);
    }
}