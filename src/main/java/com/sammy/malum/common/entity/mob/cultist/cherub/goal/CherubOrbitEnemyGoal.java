package com.sammy.malum.common.entity.mob.cultist.cherub.goal;

import com.sammy.malum.common.entity.mob.cultist.cherub.CherubCultist;
import com.sammy.malum.common.entity.mob.cultist.cherub.CherubMoveControl;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import team.lodestar.lodestone.modules.core.easing.Easing;


import java.util.EnumSet;
import java.util.function.Predicate;

public class CherubOrbitEnemyGoal extends Goal {

    protected final CherubCultist cherub;
    protected final Predicate<CherubCultist> condition;

    protected final double speedModifier;
    protected final float orbitRadius;
    protected final float approachRadius;
    protected final int flightInterval;

    private int flightTime;
    private int randomizedFlightInterval;
    private float orbitRate;

    public static CherubOrbitEnemyGoal aggressive(CherubCultist cherub, double speedModifier) {
        return new CherubOrbitEnemyGoal(cherub, CherubCultist::isFeisty, speedModifier, CherubCultist.AGGRESSIVE_ENEMY_ORBIT_RADIUS, CherubCultist.ENEMY_APPROACH_RADIUS, CherubCultist.DISRUPTIVE_FLIGHT_INTERVAL);
    }

    public static CherubOrbitEnemyGoal evasive(CherubCultist cherub, double speedModifier) {
        return new CherubOrbitEnemyGoal(cherub, CherubCultist::isScared, speedModifier, CherubCultist.EVASIVE_ENEMY_ORBIT_RADIUS, CherubCultist.ENEMY_APPROACH_RADIUS, CherubCultist.DISRUPTIVE_FLIGHT_INTERVAL);
    }

    public CherubOrbitEnemyGoal(CherubCultist cherub, Predicate<CherubCultist> condition, double speedModifier, float orbitRadius, float approachRadius, int flightInterval) {
        this.cherub = cherub;
        this.condition = condition;
        this.speedModifier = speedModifier;
        this.orbitRadius = orbitRadius;
        this.approachRadius = approachRadius;
        this.flightInterval = flightInterval;
        this.setFlags(EnumSet.of(Goal.Flag.LOOK, Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        if (!condition.test(cherub)) {
            return false;
        }
        var leader = cherub.getLeader();
        if (leader != null) {
            return false;
        }
        var target = cherub.getTarget();
        if (target == null || !target.isAlive()) {
            return false;
        }
        return cherub.isTargetWithinRadius(approachRadius);
    }

    @Override
    public boolean canContinueToUse() {
        return cherub.getTarget() != null && cherub.getLeader() == null && condition.test(cherub);
    }

    @Override
    public void start() {
        var target = cherub.getTarget();
        if (target != null) {
            var moveControl = cherub.getMoveControl();
            setRandomizedFlightInterval();
            scrambleMovement(moveControl);
            flyTowardsTarget(moveControl, target);
        }
    }

    @Override
    public void stop() {
        super.stop();
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        var target = cherub.getTarget();
        if (target != null) {
            var moveControl = cherub.getMoveControl();
            flightTime++;
            if (flightTime >= randomizedFlightInterval) {
                scrambleMovement(moveControl);
                setRandomizedFlightInterval();
                flightTime = 0;
            }
            flyTowardsTarget(moveControl, target);
        }
    }

    public void flyTowardsTarget(CherubMoveControl moveControl, LivingEntity target) {
        var vec3 = target.position().add(0, target.getBbHeight()/2f, 0);
        moveControl.setWantedPosition(vec3.x, vec3.y, vec3.z, speedModifier);
        moveControl.adjustOrbitAngle(a -> a + orbitRate);
    }

    public void scrambleMovement(CherubMoveControl moveControl) {
        moveControl.setRandomOrbitOffset(orbitRadius);
        orbitRate = Easing.SINE_IN_OUT.asWeighedRandom(cherub.getRandom(), -0.05f, 0.05f);
    }

    public void setRandomizedFlightInterval() {
        float min = flightInterval * 0.8f;
        float max = flightInterval * 1.2f;
        randomizedFlightInterval = Mth.floor(Easing.SINE_IN_OUT.asWeighedRandom(cherub.getRandom(), min, max));
    }
}