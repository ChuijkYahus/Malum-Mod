package com.sammy.malum.common.entity.mob.cultist.altar.goal;

import com.sammy.malum.common.entity.mob.cultist.altar.AltarCultist;
import com.sammy.malum.registry.common.sound.MalumCultistSoundEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.level.pathfinder.Path;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class AltarRetreatGoal extends Goal {

    protected final AltarCultist altar;
    protected final PathNavigation navigation;

    protected final double speedModifier;
    protected final float retreatRadius;

    @Nullable
    protected Path path;

    public AltarRetreatGoal(AltarCultist altar, double speedModifier) {
        this(altar, speedModifier, AltarCultist.RETREAT_RADIUS);
    }

    public AltarRetreatGoal(AltarCultist altar, double speedModifier, float retreatRadius) {
        this.altar = altar;
        this.navigation = altar.getNavigation();
        this.retreatRadius = retreatRadius;
        this.speedModifier = speedModifier;
        setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.JUMP));
    }

    @Override
    public boolean canUse() {
        if (altar.shouldRetreatFromTarget()) {
            var target = altar.getTarget();
            assert target != null;
            path = altar.getEscapePath(target, 8, 4);
            return path != null;
        }
        return false;
    }

    @Override
    public boolean canContinueToUse() {
        if (altar.shouldRetreatFromTarget()) {
            var target = altar.getTarget();
            if (target != null) {
                var updatedPath = altar.getEscapePath(target, 8, 4);
                if (updatedPath != null) {
                    path = updatedPath;
                }
            }
            return true;
        }
        return !navigation.isDone();
    }

    @Override
    public void start() {
        navigation.moveTo(path, speedModifier);
        altar.jumpFromGround();
        altar.playSound(MalumCultistSoundEvents.ALTAR_FLEE.get());
    }

    @Override
    public void stop() {
        navigation.stop();
    }

    @Override
    public void tick() {
        navigation.setSpeedModifier(speedModifier);
    }
}
