package com.sammy.malum.common.entity.mob.cultist.cherub.goal;

import com.sammy.malum.common.entity.mob.cultist.CultistMonster;
import com.sammy.malum.common.entity.mob.cultist.ICherubFriend;
import com.sammy.malum.common.entity.mob.cultist.cherub.CherubCultist;
import com.sammy.malum.common.entity.mob.cultist.cherub.CherubMoveControl;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;
import team.lodestar.lodestone.modules.core.easing.Easing;


import java.util.EnumSet;

import static com.sammy.malum.common.entity.mob.cultist.cherub.CherubCultist.LEADER_ORBIT_RADIUS;
import static com.sammy.malum.common.entity.mob.cultist.cherub.CherubCultist.LEADER_ORBIT_RATE;

public class CherubOrbitLeaderGoal extends Goal {

    protected final CherubCultist cherub;

    protected final double speedModifier;

    private float randomizedOrbitRate;

    public CherubOrbitLeaderGoal(CherubCultist cherub, double speedModifier) {
        this.cherub = cherub;
        this.speedModifier = speedModifier;
        this.setFlags(EnumSet.of(Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        return cherub.getLeader() != null;
    }

    @Override
    public boolean canContinueToUse() {
        return cherub.getLeader() != null;
    }

    @Override
    public void start() {
        var leader = cherub.getLeader();
        if (leader instanceof ICherubFriend friend) {
            var moveControl = cherub.getMoveControl();
            var orbitRate = LEADER_ORBIT_RATE;
            moveControl.setRandomOrbitOffset(LEADER_ORBIT_RADIUS);
            flyTowardsLeader(moveControl, friend);
            randomizedOrbitRate = Easing.SINE_IN_OUT.asWeighedRandom(cherub.getRandom(), -orbitRate, orbitRate);
        }
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        var leader = cherub.getLeader();
        if (leader instanceof ICherubFriend friend) {
            var moveControl = cherub.getMoveControl();
            flyTowardsLeader(moveControl, friend);
            var cherubTarget = cherub.getTarget();
            var leaderTarget = leader.getTarget();
            if (cherubTarget == null && leaderTarget == null) {
                cherub.lookAtAndFaceTarget(leader);
                return;
            }
            if (leaderTarget != null) {
                cherub.lookAtAndFaceTarget(leaderTarget);
                return;
            }
            cherub.lookAtAndFaceTarget(cherubTarget);
        }
    }

    public void flyTowardsLeader(CherubMoveControl moveControl, ICherubFriend friend) {
        var cultist = (CultistMonster)friend;
        Vec3 offset = friend.getCherubHoverOffset(cherub.getLeaderCherubIndex());
        var vec3 = cultist.position().add(offset);
        moveControl.setWantedPosition(vec3.x, vec3.y, vec3.z, speedModifier);
        moveControl.adjustOrbitAngle(a -> a + randomizedOrbitRate);
    }
}