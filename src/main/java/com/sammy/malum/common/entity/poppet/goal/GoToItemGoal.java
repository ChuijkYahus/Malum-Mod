package com.sammy.malum.common.entity.poppet.goal;

import com.sammy.malum.common.entity.poppet.*;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.item.*;

import java.util.*;

public class GoToItemGoal extends Goal {

    private final PoppetEntity poppet;
    private ItemEntity target;
    private int timeSinceTheJourneyBegan;

    public GoToItemGoal(PoppetEntity poppet) {
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        this.poppet = poppet;
    }

    @Override
    public boolean canUse() {
        return poppet.theBeastHungers();
    }

    @Override
    public boolean canContinueToUse() {
        return target != null && !poppet.hasRestriction() && poppet.theBeastHungers() && !poppet.closerThan(target, 1);
    }

    @Override
    public void start() {
        timeSinceTheJourneyBegan = 0;
        BlockPos pearlflower = poppet.findPearlFlower(16);
        if (pearlflower != null) {
            target = pearlflower;
        }
        else {
            poppet.pearlflowerTimer = 0;
        }
    }

    @Override
    public void stop() {
        timeSinceTheJourneyBegan = 0;
        target = null;
        poppet.getNavigation().stop();
        poppet.getNavigation().resetMaxVisitedNodesMultiplier();
    }

    @Override
    public void tick() {
        if (target != null) {
            timeSinceTheJourneyBegan++;
            if (timeSinceTheJourneyBegan > adjustedTickDelay(1200)) {
                target = null;
            } else if (!poppet.getNavigation().isInProgress()) {
                if (poppet.isTooFarAway(target)) {
                    target = null;
                }
                else if (poppet.closerThan(target, 3f)) {
                    poppet.pathfindDirectlyTowards(target);
                }
                else {
                    poppet.pathfindRandomlyTowards(target);
                }
            }
        }
    }
}