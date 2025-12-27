package com.sammy.malum.common.entity.mob.cultist.cherub;

import com.sammy.malum.common.entity.mob.cultist.CultistMonster;
import com.sammy.malum.common.entity.mob.cultist.ICherubFriend;
import it.unimi.dsi.fastutil.Pair;
import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.ArrayList;
import java.util.Comparator;

public class NearestCherubFriendGoal extends Goal {

    protected final CherubCultist cherub;
    protected final TargetingConditions targetConditions;

    protected final int searchInterval;
    protected final double searchRadius;

    private int searchDelay;

    public NearestCherubFriendGoal(CherubCultist cherub, int searchInterval, double searchRadius) {
        this.cherub = cherub;
        this.searchInterval = searchInterval;
        this.searchRadius = searchRadius;
        this.targetConditions = TargetingConditions.forCombat().range(searchRadius);
    }

    @Override
    public boolean canUse() {
        if (searchDelay > 0) {
            searchDelay--;
        } else {
            findLeader();
            searchDelay = searchInterval;
        }
        return cherub.getLeader() != null;
    }

    @Override
    public boolean canContinueToUse() {
        return super.canContinueToUse();
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    protected void findLeader() {
        var level = cherub.level();
        var cultists = level.getEntitiesOfClass(CultistMonster.class, getLeaderSearchArea(), e-> !(e instanceof CherubCultist));
        var otherCherubs = level.getEntitiesOfClass(CherubCultist.class, getCompetitionSearchArea());
        Comparator<CultistMonster> comparator = Comparator.comparingInt(c -> c.tickCount);

        cultists.sort(comparator);
        var room = new Object2IntOpenHashMap<CultistMonster>();
        int total = 0;
        for (CultistMonster cultist : cultists) {
            if (cultist instanceof ICherubFriend) {
                for (CherubCultist otherCherub : otherCherubs) {
                    if (cultist.equals(otherCherub.leader)) {
                        room.addTo(cultist, 1);
                        total++;
                    }
                }
            }
        }
        if (total >= cultists.size() && cherub.getLeader() != null) {
            return;
        }

        var highestPriority = new ArrayList<CultistMonster>();
        var highPriority = new ArrayList<CultistMonster>();
        var standardPriority = new ArrayList<CultistMonster>();

        for (CultistMonster cultist : cultists) {
            if (cultist instanceof ICherubFriend cherubFriend) {
                int occupiedSpace = room.getInt(cultist);
                if (occupiedSpace < cherubFriend.getCherubCapacity()) {
                    var list = switch (cherubFriend.getCherubPriority()) {
                        case HIGHEST -> highestPriority;
                        case HIGH -> highPriority;
                        case STANDARD -> standardPriority;
                    };
                    list.add(cultist);
                }
            }
        }
        var availableLeaders = highestPriority.isEmpty() ? highPriority.isEmpty() ? standardPriority : highPriority : highestPriority;
        CultistMonster leader = level.getNearestEntity(availableLeaders, targetConditions, cherub, cherub.getX(), cherub.getEyeY(), cherub.getZ());
        if (leader != null) {
            int leaderIndex = room.getInt(leader);
            if (cherub.leaderID == null) {
                float f = 0;
            }
            cherub.setLeader(leader, leaderIndex);
        }
    }

    protected AABB getLeaderSearchArea() {
        return cherub.getBoundingBox().inflate(searchRadius, searchRadius/2f, searchRadius);
    }
    protected AABB getCompetitionSearchArea() {
        return cherub.getBoundingBox().inflate(searchRadius, searchRadius/2f, searchRadius);
    }
}