package com.sammy.malum.common.entity.mob.cultist.altar;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class AltarRetreatGoal extends Goal {

    protected final AltarCultist altar;
    protected final double speedModifier;
    protected final float retreatRadiusSqr;
    protected final PathNavigation pathNav;

    @Nullable
    protected LivingEntity avoidedTarget;
    @Nullable
    protected Path path;

    public AltarRetreatGoal(AltarCultist altar, double speedModifier, float retreatRadius) {
        this.altar = altar;
        this.retreatRadiusSqr = retreatRadius * retreatRadius;
        this.speedModifier = speedModifier;
        this.pathNav = altar.getNavigation();
        setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.JUMP));
    }

    @Override
    public boolean canUse() {
        if (!(altar.level() instanceof ServerLevel level)) {
            return false;
        }
        if (altar.meleeVictim == null || altar.isAggressive() || altar.isRetreating()) {
            return false;
        }
        if (level.getEntity(altar.meleeVictim) instanceof LivingEntity target && target.distanceToSqr(altar) < retreatRadiusSqr) {
            avoidedTarget = target;
        } else {
            avoidedTarget = null;
        }
        if (avoidedTarget == null) {
            return false;
        }
        path = altar.getEscapePath(avoidedTarget, 8, 4);
        return path != null;
    }

    @Override
    public boolean canContinueToUse() {
        if (avoidedTarget == null) return false;
        if (altar.distanceTo(avoidedTarget) > AltarCultist.RETREAT_RADIUS * 0.75f) {
            return false;
        }
        return !pathNav.isDone();
    }

    @Override
    public void start() {
        pathNav.moveTo(path, speedModifier);
        altar.jumpFromGround();
        altar.retreatCooldown = AltarCultist.RETREAT_DURATION;
        Minecraft.getInstance().player.displayClientMessage(Component.literal("Entering Retreat"), false);
    }

    @Override
    public void stop() {
        avoidedTarget = null;
    }

    @Override
    public void tick() {
        altar.getNavigation().setSpeedModifier(speedModifier);
    }
}
