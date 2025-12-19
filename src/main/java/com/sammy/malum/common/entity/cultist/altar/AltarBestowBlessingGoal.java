package com.sammy.malum.common.entity.cultist.altar;

import com.sammy.malum.common.entity.cultist.ICultist;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import team.lodestar.lodestone.helpers.RandomHelper;

import java.util.EnumSet;
import java.util.List;

public class AltarBestowBlessingGoal extends Goal {

    private final AltarCultist altar;

    private final double speedModifier;
    private final int chargeDuration;
    private final float chargeRadiusSqr;

    private LivingEntity targetedCultist;

    private int chargeTime;

    private int seeTime;

    public AltarBestowBlessingGoal(AltarCultist altar, double speedModifier, int chargeDuration, float chargeRadius) {
        this.altar = altar;
        this.speedModifier = speedModifier;
        this.chargeDuration = chargeDuration;
        this.chargeRadiusSqr = chargeRadius * chargeRadius;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (targetedCultist == null) {
            var level = altar.level();
            var area = altar.getBoundingBox().inflate(20f, 10f, 20f);
            List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, area);
            for (LivingEntity entity : entities) {
                if (entity.equals(altar)) {
                    continue;
                }
                if (entity instanceof ICultist cultist) {
                    if (cultist.canReceiveAltarBuff()) {
                        if (altar.distanceTo(entity) <= chargeRadiusSqr) {
                            targetedCultist = entity;
                            break;
                        }
                    }
                }
            }

        }
        else if (!targetedCultist.isAlive() || (targetedCultist instanceof ICultist cultist && !cultist.canReceiveAltarBuff())) {
            targetedCultist = null;
        }
        return targetedCultist != null;
    }

    @Override
    public boolean canContinueToUse() {
        return (targetedCultist != null && targetedCultist.isAlive() && targetedCultist instanceof ICultist cultist && cultist.canReceiveAltarBuff()) || this.canUse();
    }

    @Override
    public void start() {
        super.start();
        altar.setAggressive(true);
        altar.getNavigation().stop();
        Minecraft.getInstance().player.displayClientMessage(Component.literal("Entering Blessing"), false);
    }

    @Override
    public void stop() {
        super.stop();
        altar.setAggressive(false);
        seeTime = 0;
        chargeTime = -1;
        targetedCultist = null;
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        LivingEntity target = targetedCultist;
        if (target != null) {
            double distanceToTarget = altar.distanceToSqr(target.getX(), target.getY(), target.getZ());
            boolean hasLineOfSight = altar.getSensing().hasLineOfSight(target);
            boolean seesTarget = seeTime > 0;
            if (hasLineOfSight != seesTarget) {
                seeTime = 0;
            }

            if (hasLineOfSight) {
                seeTime++;
            } else {
                seeTime--;
            }

            if (seeTime > 20) {
                if ((distanceToTarget < chargeRadiusSqr * 0.5f)) {
                    altar.getNavigation().stop();
                } else {
                    altar.getNavigation().moveTo(target, speedModifier);
                }
            }

            altar.lookAt(target, 15.0F, 15.0F);

            if (hasLineOfSight) {
                chargeTime++;
                if (chargeTime >= chargeDuration) {
                    altar.performRangedAttack(targetedCultist);
                    chargeTime = 0;
                }
            }
            else if (seeTime < -60) {
                stop();
            }
        }
    }
}