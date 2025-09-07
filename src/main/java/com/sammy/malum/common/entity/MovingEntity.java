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

public abstract class MovingEntity extends Entity {


    public MovingEntity(EntityType<?> entityType, Level level) {
        super(entityType, level);
        noPhysics = false;
    }

    @Override
    public void tick() {
        baseTick();
        applyMovement();
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

    public abstract float getFriction();

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

    public void updateRotation() {
        Vec3 vec3 = getDeltaMovement();
        double d0 = vec3.horizontalDistance();
        setXRot(lerpRotation(xRotO, (float) (Mth.atan2(vec3.y, d0) * 180.0F / (float) Math.PI)));
        setYRot(lerpRotation(yRotO, (float) (Mth.atan2(vec3.x, vec3.z) * 180.0F / (float) Math.PI)));
    }

    public static float lerpRotation(float currentRotation, float targetRotation) {
        while (targetRotation - currentRotation < -180.0F) {
            currentRotation -= 360.0F;
        }

        while (targetRotation - currentRotation >= 180.0F) {
            currentRotation += 360.0F;
        }

        return Mth.lerp(0.2F, currentRotation, targetRotation);
    }
}