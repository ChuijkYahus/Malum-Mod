package com.sammy.malum.common.entity;

import com.sammy.malum.registry.common.SpiritTypeRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.TheEndGatewayBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import team.lodestar.lodestone.helpers.EntityHelper;
import team.lodestar.lodestone.systems.easing.Easing;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class FloatingEntity extends Entity {

    protected static final EntityDataAccessor<Integer> DATA_COLOR = SynchedEntityData.defineId(FloatingEntity.class, EntityDataSerializers.INT);
    protected static final EntityDataAccessor<Integer> DATA_END_COLOR = SynchedEntityData.defineId(FloatingEntity.class, EntityDataSerializers.INT);
    public final ArrayList<EntityHelper.PastPosition> pastPositions = new ArrayList<>(); // *screaming*
    public Color startColor = SpiritTypeRegistry.ARCANE_SPIRIT.getPrimaryColor();
    public Color endColor = SpiritTypeRegistry.ARCANE_SPIRIT.getSecondaryColor();
    public int maxAge;
    public int age;
    public float moveTime;
    public float windUp;
    public float hoverStart;

    public FloatingEntity(EntityType<? extends FloatingEntity> type, Level level) {
        super(type, level);
        noPhysics = false;
        this.hoverStart = (float) (Math.random() * Math.PI * 2.0D);
    }

    @Override
    protected void defineSynchedData() {
        this.getEntityData().define(DATA_COLOR, SpiritTypeRegistry.ARCANE_SPIRIT.getPrimaryColor().getRGB());
        this.getEntityData().define(DATA_END_COLOR, SpiritTypeRegistry.ARCANE_SPIRIT.getSecondaryColor().getRGB());
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        compound.putInt("age", age);
        compound.putInt("maxAge", maxAge);
        compound.putFloat("moveTime", moveTime);
        compound.putFloat("windUp", windUp);

        compound.putInt("start", startColor.getRGB());
        compound.putInt("end", endColor.getRGB());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        age = compound.getInt("age");
        maxAge = compound.getInt("maxAge");
        moveTime = compound.getFloat("moveTime");
        windUp = compound.getFloat("windUp");
        startColor = new Color(compound.getInt("start"));
        endColor = new Color(compound.getInt("end"));
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> pKey) {
        if (DATA_COLOR.equals(pKey)) {
            startColor = new Color(entityData.get(DATA_COLOR));
        }
        if (DATA_END_COLOR.equals(pKey)) {
            endColor = new Color(entityData.get(DATA_END_COLOR));
        }
        super.onSyncedDataUpdated(pKey);
    }

    @Override
    public void tick() {
        super.tick();
        hoverStart = getHoverStart(0);
        baseTick();
        trackPastPositions();
        age++;
        if (windUp < 1f) {
            windUp += 0.02f;
        }
        if (age > maxAge) {
            discard();
        }
        if (level.isClientSide) {
            double x = xOld, y = yOld + getYOffset(0) + 0.25f, z = zOld;
            spawnParticles(x, y, z);
        }
        move();
    }

    public void trackPastPositions() {
        EntityHelper.trackPastPositions(pastPositions, position().add(0, getYOffset(0) + 0.25F, 0), 0.01f);
        removeOldPositions(pastPositions);
    }

    public void removeOldPositions(List<EntityHelper.PastPosition> pastPositions) {
        int amount = pastPositions.size() - 1;
        List<EntityHelper.PastPosition> toRemove = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            EntityHelper.PastPosition excess = pastPositions.get(i);
            if (excess.time > 9) {
                toRemove.add(excess);
            }
        }
        pastPositions.removeAll(toRemove);
    }

    public void baseTick() {
        BlockHitResult result = level.clip(new ClipContext(position(), position().add(getDeltaMovement()), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this));
        if (result.getType() == HitResult.Type.BLOCK) {
            BlockPos blockpos = result.getBlockPos();
            BlockState blockstate = this.level.getBlockState(blockpos);
            if (blockstate.is(Blocks.NETHER_PORTAL)) {
                this.handleInsidePortal(blockpos);
            } else if (blockstate.is(Blocks.END_GATEWAY)) {
                BlockEntity blockentity = this.level.getBlockEntity(blockpos);
                if (blockentity instanceof TheEndGatewayBlockEntity && TheEndGatewayBlockEntity.canEntityTeleport(this)) {
                    TheEndGatewayBlockEntity.teleportEntity(this.level, blockpos, blockstate, this, (TheEndGatewayBlockEntity) blockentity);
                }
            }
        }
        this.checkInsideBlocks();
        Vec3 movement = this.getDeltaMovement();
        double nextX = this.getX() + movement.x;
        double nextY = this.getY() + movement.y;
        double nextZ = this.getZ() + movement.z;
        double distance = movement.horizontalDistance();
        this.setXRot(lerpRotation(this.xRotO, (float) (Mth.atan2(movement.y, distance) * (double) (180F / (float) Math.PI))));
        this.setYRot(lerpRotation(this.yRotO, (float) (Mth.atan2(movement.x, movement.z) * (double) (180F / (float) Math.PI))));
        this.setPos(nextX, nextY, nextZ);
        ProjectileUtil.rotateTowardsMovement(this, 0.2F);
    }

    protected static float lerpRotation(float p_37274_, float p_37275_) {
        while (p_37275_ - p_37274_ < -180.0F) {
            p_37274_ -= 360.0F;
        }

        while (p_37275_ - p_37274_ >= 180.0F) {
            p_37274_ += 360.0F;
        }

        return Mth.lerp(0.2F, p_37274_, p_37275_);
    }

    public void spawnParticles(double x, double y, double z) {

    }

    public void move() {
    }

    public float getYOffset(float partialTicks) {
        return Mth.sin(((float) age + partialTicks) / 10.0F + getHoverStart(partialTicks)) * 0.1F + 0.1F;
    }

    public float getRotation(float partialTicks) {
        return ((float) age + partialTicks) / 20.0F + getHoverStart(partialTicks)/2f;
    }

    public float getHoverStart(float partialTicks) {
        return hoverStart + (1 - Easing.SINE_OUT.ease(Math.min(1, (age + partialTicks) / 60f), 0, 1, 1)) * 0.35f;
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public boolean isNoGravity() {
        return true;
    }
}
