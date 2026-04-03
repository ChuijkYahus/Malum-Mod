package com.sammy.malum.common.entity.mob.cultist.cherub;

import com.sammy.malum.common.entity.mob.cultist.CultistMoveControl;
import it.unimi.dsi.fastutil.floats.Float2FloatFunction;
import net.minecraft.util.Mth;
import org.joml.Vector3d;
import team.lodestar.lodestone.modules.core.easing.Easing;

public class CherubMoveControl extends CultistMoveControl {

    protected final Vector3d offset = new Vector3d();
    protected final Vector3d fadingOffset = new Vector3d();
    protected boolean isOrbit;
    protected float orbitAngle;
    protected double hOrbitDistance, vOrbitDistance;

    public CherubMoveControl(CherubCultist cherub) {
        super(cherub);
    }

    public void setRandomOrbitOffset(float distance) {
        var random = mob.getRandom();
        float angle = random.nextFloat() * 6.28f;
        float half = distance * 0.5f;
        float y = Easing.QUAD_IN_OUT.asWeighedRandom(random, -half, half);
        setOrbitOffset(distance, y, angle);
    }

    public void setOrbitOffset(double horizontal, double vertical, float angle) {
        double x = Mth.sin(angle) * horizontal;
        double z = Mth.cos(angle) * horizontal;
        offset.set(x, vertical, z);
        orbitAngle = angle;
        hOrbitDistance = horizontal;
        vOrbitDistance = vertical;
        isOrbit = true;
    }

    public void adjustOrbitAngle(Float2FloatFunction modifier) {
        adjustOrbitAngle(modifier.apply(orbitAngle));
    }

    public void adjustOrbitAngle(float angle) {
        if (isOrbit) {
            setOrbitOffset(hOrbitDistance, vOrbitDistance, angle);
        }
    }

    public void setOffset(double x, double y, double z) {
        offset.set(x, y, z);
        isOrbit = false;
    }

    @Override
    public void tick() {
        super.tick();
        if (cultist.verticalCollisionBelow) {
            fadingOffset.add(0, 0.1f, 0);
        }
        else if (cultist.verticalCollision) {
            fadingOffset.sub(0, 0.1f, 0);
        }
        fadingOffset.mul(0.9f);
    }

    @Override
    public void resetValues() {
        if (operation.equals(Operation.JUMPING)) {
            operation = Operation.WAIT;
        }
        super.resetValues();
    }

    @Override
    public Vector3d getWantedPosition() {
        return new Vector3d(wantedX, wantedY, wantedZ).add(offset).add(fadingOffset);
    }

    @Override
    public Vector3d getMovementTrajectory(double x, double y, double z) {
        return new Vector3d(x, y * 1.5f, z);
    }
}