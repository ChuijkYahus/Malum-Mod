package com.sammy.malum.common.entity.mob.cultist;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.level.pathfinder.PathType;

public class CultistMoveControl extends MoveControl {

    public enum BodyDirection {
        DEFAULT,
        FACE_TARGET,
        DISABLED
    }

    public BodyDirection direction = BodyDirection.DEFAULT;
    public final CultistMonster cultist;

    public CultistMoveControl(CultistMonster cultist) {
        super(cultist);
        this.cultist = cultist;
    }

    public void replaceBodyDirection(BodyDirection direction) {
        this.direction = direction;
    }

    @Override
    public void tick() {
        float baseSpeed = (float)cultist.getAttributeValue(Attributes.MOVEMENT_SPEED);
        float speed = (float)speedModifier * baseSpeed;
        if (operation == MoveControl.Operation.STRAFE) {
            float forwards = strafeForwards;
            float right = strafeRight;
            float strafeDelta = Mth.sqrt(forwards * forwards + right * right);
            if (strafeDelta < 1.0F) {
                strafeDelta = 1.0F;
            }

            strafeDelta = speed / strafeDelta;
            forwards *= strafeDelta;
            right *= strafeDelta;
            float sin = Mth.sin(cultist.getYRot() * (float) (Math.PI / 180.0));
            float cos = Mth.cos(cultist.getYRot() * (float) (Math.PI / 180.0));
            float xRelative = forwards * cos - right * sin;
            float zRelative = right * cos + forwards * sin;
            if (!isWalkable(xRelative, zRelative)) {
                strafeForwards = 1.0F;
                strafeRight = 0.0F;
            }

            cultist.setSpeed(speed);
            cultist.setZza(strafeForwards);
            cultist.setXxa(strafeRight);
            operation = MoveControl.Operation.WAIT;
        } else if (operation == MoveControl.Operation.MOVE_TO) {
            operation = MoveControl.Operation.WAIT;
            double xDiff = wantedX - cultist.getX();
            double yDiff = wantedY - cultist.getY();
            double zDiff = wantedZ - cultist.getZ();
            double distance = xDiff * xDiff + yDiff * yDiff + zDiff * zDiff;
            if (distance < 2.5000003E-7F) {
                cultist.setZza(0.0F);
                return;
            }

            float movementAngle = (float)(Mth.atan2(zDiff, xDiff) * 180.0F / (float)Math.PI) - 90.0F;
            cultist.setSpeed(speed);
            var target = cultist.target;
            if (direction.equals(BodyDirection.FACE_TARGET) && target != null) {
                cultist.faceTarget(target);
            }
            else if (!direction.equals(BodyDirection.DISABLED)) {
                cultist.setYRot(rotlerp(cultist.getYRot(), movementAngle, 90.0F));
            }
            tryJump(xDiff, yDiff, zDiff);
        } else if (operation == MoveControl.Operation.JUMPING) {
            cultist.setSpeed(speed);
            if (cultist.onGround()) {
                operation = MoveControl.Operation.WAIT;
            }
        } else {
            cultist.setZza(0.0F);
        }
        direction = BodyDirection.DEFAULT;
    }

    public void tryJump(double xDiff, double yDiff, double zDiff) {
        var blockpos = cultist.blockPosition();
        var blockstate = cultist.level().getBlockState(blockpos);
        var voxelshape = blockstate.getCollisionShape(cultist.level(), blockpos);
        if (yDiff > (double)cultist.maxUpStep() && xDiff * xDiff + zDiff * zDiff < (double)Math.max(1.0F, cultist.getBbWidth())
                || !voxelshape.isEmpty()
                && cultist.getY() < voxelshape.max(Direction.Axis.Y) + (double)blockpos.getY()
                && !blockstate.is(BlockTags.DOORS)
                && !blockstate.is(BlockTags.FENCES)) {
            cultist.getJumpControl().jump();
            operation = MoveControl.Operation.JUMPING;
        }
    }

    @Override
    public float rotlerp(float sourceAngle, float targetAngle, float maximumChange) {
        return super.rotlerp(sourceAngle, targetAngle, maximumChange);
    }

    public boolean isWalkable(float relativeX, float relativeZ) {
        var navigation = cultist.getNavigation();
        var node = navigation.getNodeEvaluator();
        var pathType = node.getPathType(cultist, BlockPos.containing(
                        cultist.getX() + relativeX,
                        cultist.getBlockY(),
                        cultist.getZ() + relativeZ)
        );
        return pathType == PathType.WALKABLE;
    }
}
