package com.sammy.malum.common.entity.activator.rite;

import com.sammy.malum.core.systems.rite.effect.*;
import com.sammy.malum.registry.common.entity.*;
import net.minecraft.core.*;
import net.minecraft.nbt.*;
import net.minecraft.util.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.*;
import net.minecraft.world.phys.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.systems.easing.*;
import team.lodestar.lodestone.systems.rendering.trail.*;

public class BlockRiteEffectWaveActivator extends BlockRiteEffectActivator{

    protected int acceleration;
    public BlockRiteEffectWaveActivator(Level level) {
        super(MalumEntityTypes.RITE_BLOCK_WAVE_EFFECT_ACTIVATOR.get(), level);
    }

    public BlockRiteEffectWaveActivator(Level level, SpiritRiteBlockEffect effect, BlockPos sourcePosition, Direction movementDirection) {
        super(MalumEntityTypes.RITE_BLOCK_WAVE_EFFECT_ACTIVATOR.get(), level, effect, sourcePosition, movementDirection);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("acceleration", acceleration);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        acceleration = pCompound.getInt("acceleration");
    }

    @Override
    public boolean canTriggerTravelEffects() {
        return false;
    }

    @Override
    public BlockPos getRiteEffectPosition(BlockPos pos) {
        return pos;
    }

    @Override
    public float getTravelSpeedMultiplier() {
        float delta = Math.min(acceleration * 0.05f, 1f);
        delta = Easing.EXPO_IN.ease(delta, 0, 1);
        acceleration++;
        return delta;
    }

    @Override
    public void addTrailPoints() {
        for (int i = 0; i < 2; i++) {
            float progress = i * 0.5f;
            Vec3 position = getPosition(progress).add(0, getBbHeight()/2f, 0);
            float angle = (age * 0.6f) % 6.28f;

            float x = (float) Math.cos(angle);
            float z = (float) Math.sin(angle);
            float max = Math.max(Math.abs(x), Math.abs(z));
            x /= max;
            z /= max;
            float offset = 0.5f;

            Vec3 direction = getDeltaMovement().normalize();
            float yRot = ((float) (Mth.atan2(direction.x, direction.z) * (double) (180F / (float) Math.PI)));
            float yaw = (float) Math.toRadians(yRot);
            Vec3 left = new Vec3(-Math.cos(yaw), 0, Math.sin(yaw));
            Vec3 up = left.cross(direction);
            Vec3 randomizedPosition = position
                    .add(left.scale(x * offset))
                    .add(up.scale(z * offset));
            trail.addTrailPoint(new TrailPoint(randomizedPosition));
            longTrail.addTrailPoint(new TrailPoint(randomizedPosition));
        }
    }
}
