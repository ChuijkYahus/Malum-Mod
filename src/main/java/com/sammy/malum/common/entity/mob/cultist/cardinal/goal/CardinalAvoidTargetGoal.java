package com.sammy.malum.common.entity.mob.cultist.cardinal.goal;

import com.sammy.malum.common.entity.mob.cultist.cardinal.CardinalCultist;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.level.pathfinder.Path;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class CardinalAvoidTargetGoal extends Goal {

    protected final CardinalCultist cardinal;
    protected final PathNavigation navigation;

    private final double speedModifier;

    @Nullable
    protected Path path;

    public CardinalAvoidTargetGoal(CardinalCultist cardinal, double speedModifier) {
        this.cardinal = cardinal;
        this.navigation = cardinal.getNavigation();
        this.speedModifier = speedModifier;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        var target = cardinal.target;
        if (target == null) {
            return false;
        }
        if (cardinal.shouldAvoidTarget()) {
            path = cardinal.getEscapePath(target, 8, 2);
            return path != null;
        }
        return false;
    }

    @Override
    public boolean canContinueToUse() {
        return !cardinal.shouldAvoidTarget() || !navigation.isDone();
    }

    @Override
    public void start() {
        super.start();
        navigation.moveTo(path, speedModifier);
    }

    @Override
    public void stop() {
        navigation.stop();
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        var target = cardinal.target;
        if (target != null) {
            cardinal.lookAtAndFaceTarget(target);
        }
    }
}