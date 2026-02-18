package com.sammy.malum.common.entity.mob.cultist;

import net.minecraft.world.phys.*;
import org.joml.*;
import team.lodestar.lodestone.helpers.*;

public class CultistMovementData {

    private final CultistMonster cultist;

    private final Vector3f previousMotion = new Vector3f();
    private final Vector3f motion = new Vector3f();

    private final Vector3f movementDirection = new Vector3f();

    public CultistMovementData(CultistMonster cultist) {
        this.cultist = cultist;
    }

    public void update() {
        previousMotion.set(motion);
    }

    public void interpolate(float speed, float acceleration) {
        double x = DataHelper.approach(previousMotion.x, movementDirection.x * speed, acceleration);
        double y = DataHelper.approach(previousMotion.y, movementDirection.y * speed, acceleration);
        double z = DataHelper.approach(previousMotion.z, movementDirection.z * speed, acceleration);
        setMotion(x, y, z);
    }

    public void setMotion(Vec3 motion) {
        setMotion(motion.x, motion.y, motion.z);
    }

    public void setMotion(float x, float y, float z) {
        motion.set(x, y, z);
    }

    public void setMotion(double x, double y, double z) {
        motion.set(x, y, z);
    }

    public Vector3f getPreviousMotion() {
        return previousMotion;
    }

    public Vector3f getMotion() {
        return motion;
    }

    public Vector3f getMovementDirection() {
        return movementDirection;
    }

    public Vec3 getMotionVector() {
        return new Vec3(motion.x, motion.y, motion.z);
    }

    public float getMotionLength() {
        return motion.length();
    }
}