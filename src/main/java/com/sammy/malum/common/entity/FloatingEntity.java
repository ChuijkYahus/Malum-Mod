package com.sammy.malum.common.entity;

import net.minecraft.core.particles.*;
import net.minecraft.nbt.*;
import net.minecraft.server.level.*;
import net.minecraft.util.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.*;
import net.minecraft.world.phys.*;
import team.lodestar.lodestone.systems.easing.*;
import team.lodestar.lodestone.systems.rendering.trail.*;

import java.util.*;

public abstract class FloatingEntity extends Entity {

    public final TrailPointBuilder trail = TrailPointBuilder.create(5);
    public final TrailPointBuilder longTrail = TrailPointBuilder.create(30);

    protected FloatingItemDestinationData destination;

    protected float hoverOffset;

    protected int age;
    protected int maxAge;
    protected int movementWindUp;
    protected int hoverWindUp;

    public FloatingEntity(EntityType<?> entityType, Level level) {
        super(entityType, level);
        noPhysics = false;
        hoverOffset = (float) (Math.random() * Math.PI * 2.0D);
    }

    public abstract void collect(ServerLevel level);

    public void setDestination(FloatingItemDestinationData destination) {
        this.destination = destination;
    }

    public FloatingItemDestinationData getDestination() {
        return destination;
    }

    public float getVisualEffectScalar() {
        return Math.min(age /5f, 1f);
    }

    public float getHoverOffset() {
        return hoverOffset;
    }

    public int getAge() {
        return age;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public int getMovementWindUp() {
        return movementWindUp;
    }

    public int getHoverWindUp() {
        return hoverWindUp;
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compound) {
        destination = FloatingItemDestinationData.load(compound);
        age = compound.getInt("age");
        maxAge = compound.getInt("maxAge");
        movementWindUp = compound.getInt("movementWindUp");
        hoverWindUp = compound.getInt("hoverWindUp");
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compound) {
        if (destination != null) {
            destination.save(compound);
        }
        compound.putInt("age", age);
        compound.putInt("maxAge", maxAge);
        compound.putInt("movementWindUp", movementWindUp);
        compound.putInt("hoverWindUp", hoverWindUp);
    }

    @Override
    public void tick() {
        if (age > maxAge) {
            discard();
            return;
        }
        baseTick();
        if (level() instanceof ServerLevel level) {
            if (destination != null && destination.isValid(level)) {
                float distance = (float) destination.getDistance(level, this);
                Optional<Vec3> destination = this.destination.getDestination(level);
                if (destination.isPresent()) {
                    float windUpDuration = getWindUpDuration();
                    var targetPos = destination.get();
                    if (movementWindUp < windUpDuration) {
                        movementWindUp++;
                    }
                    float delta = Mth.clamp(movementWindUp / windUpDuration, 0, 1);
                    float velocity = Mth.clamp(delta - 0.25f, 0, 0.75f) * 3f;
                    float motionStrength = getMovementInterpolation(delta, distance);
                    var targetMovement = targetPos.subtract(position()).normalize().scale(velocity);
                    var newMovement = getDeltaMovement().lerp(targetMovement, motionStrength);
                    setDeltaMovement(newMovement);
                    if (distance < 0.4f) {
                        collect(level);
                        remove(RemovalReason.DISCARDED);
                    }
                }
            } else {
                if (movementWindUp > 0) {
                    movementWindUp--;
                }
                if (age >= 40) {
                    float windUpDuration = getWindUpDuration();
                    float gravity = 0.004f * (windUpDuration - movementWindUp) / windUpDuration;
                    setDeltaMovement(getDeltaMovement().subtract(0, gravity, 0).multiply(0.9f, 0.96f, 0.9f));
                }
                if (level.getGameTime() % 20L == 0) {
                    ServerPlayer nearestPlayer = null;
                    float minimumDistance = 6f;
                    for (ServerPlayer player : level.players()) {
                        float distance = player.distanceTo(this);
                        if (distance < minimumDistance) {
                            if (player.hasLineOfSight(this)) {
                                nearestPlayer = player;
                                minimumDistance = distance;
                            }
                        }
                    }
                    if (nearestPlayer != null && nearestPlayer.isAlive()) {
                        setDestination(new FloatingItemDestinationData(nearestPlayer.getUUID()));
                    }
                }
            }
        }
        else {
            Vec3 position = getOffsetPosition(0.5f);
            trail.addTrailPoint(position);
            longTrail.addTrailPoint(position);
            trail.tickTrailPoints();
            longTrail.tickTrailPoints();
        }
        applyMovement();
        age++;
    }

    @Override
    public void lerpMotion(double x, double y, double z) {
        this.setDeltaMovement(x, y, z);
        if (this.xRotO == 0.0F && this.yRotO == 0.0F) {
            double d0 = Math.sqrt(x * x + z * z);
            this.setXRot((float) (Mth.atan2(y, d0) * 180.0F / (float) Math.PI));
            this.setYRot((float) (Mth.atan2(x, z) * 180.0F / (float) Math.PI));
            this.xRotO = this.getXRot();
            this.yRotO = this.getYRot();
            this.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), this.getXRot());
        }
    }

    @Override
    public boolean isPickable() {
        return true;
    }

    public int getWindUpDuration() {
        return 50;
    }

    public float getMovementInterpolation(float windUp, float distance) {
        float windUpScalar = windUp * 0.01f;
        float distanceScalar = (1 / Math.max(distance, 1)) * 0.025f;
        return windUpScalar + distanceScalar;
    }

    public float getFriction() {
        return 0.96f;
    }

    protected void applyMovement() {
        checkInsideBlocks();
        Vec3 vec3 = getDeltaMovement();
        double d0 = getX() + vec3.x;
        double d1 = getY() + vec3.y;
        double d2 = getZ() + vec3.z;
        updateRotation();
        float friction = getFriction();
        if (isInWater()) {
            for (int i = 0; i < 4; i++) {
                level().addParticle(ParticleTypes.BUBBLE, d0 - vec3.x * 0.25, d1 - vec3.y * 0.25, d2 - vec3.z * 0.25, vec3.x, vec3.y, vec3.z);
            }
            friction *= 0.825f;
        }

        setDeltaMovement(vec3.scale(friction));
        applyGravity();
        setPos(d0, d1, d2);
    }

    public Vec3 getOffsetPosition() {
        return getOffsetPosition(0);
    }

    public Vec3 getOffsetPosition(float partialTicks) {
        return getPosition(partialTicks).add(0, getYOffset(0), 0);
    }

    public float getYOffset(float partialTicks) {
        float windUpDuration = getWindUpDuration();
        float offsetStrength = Easing.CIRC_IN_OUT.clamped((age+partialTicks) / windUpDuration, 0, 1);
        return Mth.sin(((float) age + partialTicks) / 6.0F + hoverOffset) * (0.5F - (offsetStrength * 0.25F));
    }

    public float getRotation(float partialTicks) {
        return ((float) age + partialTicks) / 10.0F + hoverOffset;
    }

    protected void updateRotation() {
        Vec3 vec3 = getDeltaMovement();
        double d0 = vec3.horizontalDistance();
        setXRot(lerpRotation(xRotO, (float) (Mth.atan2(vec3.y, d0) * 180.0F / (float) Math.PI)));
        setYRot(lerpRotation(yRotO, (float) (Mth.atan2(vec3.x, vec3.z) * 180.0F / (float) Math.PI)));
    }

    protected static float lerpRotation(float currentRotation, float targetRotation) {
        while (targetRotation - currentRotation < -180.0F) {
            currentRotation -= 360.0F;
        }

        while (targetRotation - currentRotation >= 180.0F) {
            currentRotation += 360.0F;
        }

        return Mth.lerp(0.2F, currentRotation, targetRotation);
    }
}