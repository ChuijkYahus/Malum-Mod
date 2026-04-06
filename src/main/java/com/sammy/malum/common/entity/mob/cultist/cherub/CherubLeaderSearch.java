package com.sammy.malum.common.entity.mob.cultist.cherub;

import com.sammy.malum.common.entity.mob.cultist.CultistMonster;
import com.sammy.malum.common.entity.mob.cultist.ICherubFriend;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.phys.AABB;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.UUID;

public class CherubLeaderSearch {

    protected final CherubCultist cherub;
    protected final TargetingConditions targetConditions;

    protected final int searchInterval;
    protected final double searchRadius;

    protected int searchDelay;
    protected UUID leaderID;
    protected CultistMonster leader;
    protected int leaderCherubIndex;

    public CherubLeaderSearch(CherubCultist cherub) {
        this(cherub, CherubCultist.LEADER_SEARCH_INTERVAL, CherubCultist.LEADER_SEARCH_RADIUS);
    }

    public CherubLeaderSearch(CherubCultist cherub, int searchInterval, double searchRadius) {
        this.cherub = cherub;
        this.searchInterval = searchInterval;
        this.searchRadius = searchRadius;
        this.targetConditions = TargetingConditions.forCombat().range(searchRadius);
    }

    public void save(CompoundTag compound) {
        if (leaderID != null) {
            compound.putUUID("Leader", leaderID);
            compound.putInt("LeaderCherubIndex", leaderCherubIndex);
        }
    }

    public void load(CompoundTag compound) {
        if (compound.contains("Leader")) {
            leaderID = compound.getUUID("Leader");
            leaderCherubIndex = compound.getInt("LeaderCherubIndex");
        }
    }

    public void update(ServerLevel level) {
        if (leaderID != null) {
            leader = level.getEntity(leaderID) instanceof CultistMonster instance ? instance : null;
            if (leader != null && leader.isAddedToLevel() && leader.isAlive()) {
                return;
            }
            leaderID = null;
            leader = null;
            leaderCherubIndex = -1;
        }

        if (searchDelay > 0) {
            searchDelay--;
            return;
        }
        trySearchForLeader();
        searchDelay = searchInterval;
    }


    public void setLeader(CultistMonster leader, int leaderCherubIndex) {
        this.leaderID = leader.getUUID();
        this.leader = leader;
        this.leaderCherubIndex = leaderCherubIndex;
    }

    protected void trySearchForLeader() {
        var level = cherub.level();
        var cultists = level.getEntitiesOfClass(CultistMonster.class, getLeaderSearchArea(), e -> !(e instanceof CherubCultist));
        var otherCherubs = level.getEntitiesOfClass(CherubCultist.class, getCompetitionSearchArea());
        Comparator<CultistMonster> comparator = Comparator.comparingInt(c -> c.tickCount);

        cultists.sort(comparator);
        var room = new Object2IntOpenHashMap<CultistMonster>();
        int total = 0;
        for (CultistMonster cultist : cultists) {
            if (!(cultist instanceof ICherubFriend)) {
                continue;
            }
            for (CherubCultist otherCherub : otherCherubs) {
                CultistMonster otherLeader = otherCherub.leaderSearch.leader;
                if (!cultist.equals(otherLeader)) {
                    continue;
                }
                room.addTo(cultist, 1);
                total++;
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
                if (occupiedSpace >= cherubFriend.getCherubCapacity()) {
                    continue;
                }
                var list = switch (cherubFriend.getCherubPriority()) {
                    case HIGHEST -> highestPriority;
                    case HIGH -> highPriority;
                    case STANDARD -> standardPriority;
                };
                list.add(cultist);
            }
        }
        var availableLeaders = highestPriority.isEmpty() ? highPriority.isEmpty() ? standardPriority : highPriority : highestPriority;
        var leader = level.getNearestEntity(availableLeaders, targetConditions, cherub, cherub.getX(), cherub.getEyeY(), cherub.getZ());
        if (leader != null) {
            int leaderIndex = room.getInt(leader);
            setLeader(leader, leaderIndex);
        }
    }

    protected AABB getLeaderSearchArea() {
        return cherub.getBoundingBox().inflate(searchRadius, searchRadius / 2f, searchRadius);
    }

    protected AABB getCompetitionSearchArea() {
        return cherub.getBoundingBox().inflate(searchRadius, searchRadius / 2f, searchRadius);
    }
}