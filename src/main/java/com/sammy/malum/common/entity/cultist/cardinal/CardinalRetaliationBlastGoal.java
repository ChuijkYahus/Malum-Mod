package com.sammy.malum.common.entity.cultist.cardinal;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class CardinalRetaliationBlastGoal extends Goal {

    private final CardinalCultist cardinal;
    protected final PathNavigation pathNav;

    private static final int CHARGE_DURATION = 10;
    private final double speedModifier;

    private int attackTime;
    @Nullable
    protected Path path;

    public CardinalRetaliationBlastGoal(CardinalCultist cardinal, double speedModifier) {
        this.cardinal = cardinal;
        this.speedModifier = speedModifier;
        this.pathNav = cardinal.getNavigation();
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        return cardinal.isWithinRetaliationBlastRadius();
    }

    @Override
    public boolean canContinueToUse() {
        return attackTime > 0 || canUse();
    }

    @Override
    public void start() {
        super.start();
        cardinal.setAggressive(true);
        cardinal.getNavigation().stop();
        Minecraft.getInstance().player.displayClientMessage(Component.literal("Entering Retaliation State"), false);
    }

    @Override
    public void stop() {
        super.stop();
        cardinal.setAggressive(false);
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        var target = cardinal.target;
        if (target != null) {
            if (cardinal.canTriggerRetaliationBlast()) {
                if (attackTime == 0) {
                    cardinal.level().broadcastEntityEvent(cardinal, CardinalCultist.RETALIATION_BLAST_EVENT);
                }
                attackTime++;
                if (attackTime >= CHARGE_DURATION) {
                    if (cardinal.level() instanceof ServerLevel level) {
                        cardinal.triggerRetaliationBlast(level);
                    }
                    attackTime = 0;
                }
            }
            cardinal.getMoveControl().strafe(-1f, 0);
            cardinal.getLookControl().setLookAt(target, 120.0F, 120.0F);
        }
    }
}