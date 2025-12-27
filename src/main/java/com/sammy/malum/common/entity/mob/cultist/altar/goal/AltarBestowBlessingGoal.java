package com.sammy.malum.common.entity.mob.cultist.altar.goal;

import com.sammy.malum.common.entity.mob.cultist.altar.AltarCultist;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;

import java.util.EnumSet;
import java.util.List;

public class AltarBestowBlessingGoal extends Goal {

    private final AltarCultist altar;
    protected final PathNavigation navigation;

    private final float searchRadius;
    private final double speedModifier;
    private final int chargeDuration;
    private final float chargeRadius;

    private LivingEntity target;

    private int chargeTime;

    private int seeTime;

    public AltarBestowBlessingGoal(AltarCultist altar, double speedModifier) {
        this(altar, AltarCultist.BLESSING_SEARCH_RADIUS, speedModifier, AltarCultist.BLESSING_CHARGE_DURATION, AltarCultist.BLESSING_CHARGE_RADIUS);
    }
    public AltarBestowBlessingGoal(AltarCultist altar, float searchRadius, double speedModifier, int chargeDuration, float chargeRadius) {
        this.altar = altar;
        this.navigation = altar.getNavigation();
        this.searchRadius = searchRadius;
        this.speedModifier = speedModifier;
        this.chargeDuration = chargeDuration;
        this.chargeRadius = chargeRadius;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (target == null) {
            var level = altar.level();
            float half = searchRadius/2f;
            var area = altar.getBoundingBox().inflate(searchRadius, half, searchRadius);
            List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, area);
            for (LivingEntity entity : entities) {
                if (entity.equals(altar)) {
                    continue;
                }
                if (altar.canBestowBlessing(entity)) {
                    if (altar.isTargetWithinRadius(entity, searchRadius)) {
                        target = entity;
                        break;
                    }
                }
            }

        }
        else if (!target.isAlive() || !altar.canBestowBlessing(target)) {
            target = null;
        }
        return target != null;
    }

    @Override
    public boolean canContinueToUse() {
        return (target != null && target.isAlive() && altar.canBestowBlessing(target)) || this.canUse();
    }

    @Override
    public void start() {
        super.start();
        altar.setAggressive(true);
    }

    @Override
    public void stop() {
        super.stop();
        altar.setAggressive(false);
        navigation.stop();
        seeTime = 0;
        chargeTime = -1;
        target = null;
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        if (target != null) {
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
                if ((distanceToTarget < chargeRadius * chargeRadius)) {
                    navigation.stop();
                } else {
                    navigation.moveTo(target, speedModifier);
                }
            }

            altar.lookAt(target, 15.0F, 15.0F);

            if (hasLineOfSight) {
                chargeTime++;
                if (chargeTime >= chargeDuration) {
                    altar.performRangedAttack(this.target);
                    chargeTime = 0;
                }
            }
            else if (seeTime < -60) {
                stop();
            }
        }
    }
}