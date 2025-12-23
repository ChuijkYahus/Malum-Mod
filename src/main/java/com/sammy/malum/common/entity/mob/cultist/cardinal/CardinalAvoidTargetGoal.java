package com.sammy.malum.common.entity.mob.cultist.cardinal;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.level.pathfinder.Path;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class CardinalAvoidTargetGoal extends Goal {

    protected final CardinalCultist cardinal;
    protected final PathNavigation pathNav;

    private final double speedModifier;

    @Nullable
    protected Path path;

    public CardinalAvoidTargetGoal(CardinalCultist cardinal, double speedModifier) {
        this.cardinal = cardinal;
        this.speedModifier = speedModifier;
        this.pathNav = cardinal.getNavigation();
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        return cardinal.shouldAvoidTarget();
    }

    @Override
    public void start() {
        super.start();
        cardinal.getNavigation().stop();
    }


    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        var target = cardinal.target;
        if (target != null) {
            path = cardinal.getEscapePath(target, 8, 2);
            pathNav.moveTo(path, speedModifier);
            cardinal.lookAtAndFaceTarget(target);
        }
    }
}