package com.sammy.malum.common.entity.mob.cultist;

import com.sammy.malum.registry.common.MalumDamageTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import team.lodestar.lodestone.helpers.DamageTypeHelper;
import team.lodestar.lodestone.helpers.RandomHelper;
import team.lodestar.lodestone.registry.common.LodestoneAttributes;

public abstract class CultistMonster extends Monster implements Enemy {

    private static final EntityDataAccessor<Integer> SCALE = SynchedEntityData.defineId(CultistMonster.class, EntityDataSerializers.INT);


    protected CultistMonster(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
        setHealth(getMaxHealth());
        xpReward = Mth.floor(getMaxHealth() * 1.5f);
        moveControl = new CultistMoveControl(this);
        lookControl = new CultistLookControl(this);
    }

    @Override
    public @NotNull CultistMoveControl getMoveControl() {
        return (CultistMoveControl) moveControl;
    }

    @Override
    public @NotNull CultistLookControl getLookControl() {
        return (CultistLookControl) super.getLookControl();
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(SCALE, 0);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("CultistScale", getCultistScale());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        setCultistScale(compound.getInt("CultistScale"));
    }

    @Override
    public @NotNull SoundSource getSoundSource() {
        return SoundSource.HOSTILE;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.is(DamageTypes.IN_WALL)) {
            return false;
        }
        return super.hurt(source, amount);
    }

    @Override
    public boolean doHurtTarget(@NotNull Entity target) {
        if (super.doHurtTarget(target)) {
            float magicDamage = (float) this.getAttributeValue(LodestoneAttributes.MAGIC_DAMAGE);
            var damagesource = DamageTypeHelper.create(level(), MalumDamageTypes.CULTIST_MAGIC, this);
            target.invulnerableTime = 0;
            target.hurt(damagesource, magicDamage);
            return true;
        }
        return false;
    }

    @SuppressWarnings("deprecation")
    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        int scale = RandomHelper.randomBetween(random, 0, 3);
        setCultistScale(scale);
        return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
    }

    @Override
    public boolean isWithinMeleeAttackRange(LivingEntity entity) {
        return getAttackBoundingBox().inflate(0.25f).intersects(entity.getHitbox());
    }

    public Vec3 directionToTarget(Entity target) {
        double x = target.getX() - getX();
        double y = target.getY(0.5f) - getY(0.5f);
        double z = target.getZ() - getZ();
        return new Vec3(x, y, z).normalize();
    }

    public boolean isTargetWithinRadius(float radius) {
        var target = getTarget();
        if (target != null) {
            float distanceToTarget = distanceTo(target);
            return (distanceToTarget < radius);
        }
        return false;
    }

    public Path getEscapePath(LivingEntity avoidedTarget, int radius, int yRange) {
        Vec3 escapePos = DefaultRandomPos.getPosAway(this, radius, yRange, avoidedTarget.position());
        if (escapePos == null) {
            return null;
        }
        if (avoidedTarget.distanceToSqr(escapePos.x, escapePos.y, escapePos.z) < distanceToSqr(avoidedTarget)) {
            return null;
        }
        return getNavigation().createPath(escapePos.x, escapePos.y, escapePos.z, 0);
    }

    public void lookAtAndFaceTarget(Entity target) {
        if (navigation.getPath() != null && !navigation.isDone()) {
            getMoveControl().replaceBodyDirection(CultistMoveControl.BodyDirection.FACE_TARGET);
        }
        else {
            faceTarget(target);
        }
        getLookControl().setLookAt(target, 60.0F, 60.0F);
    }

    public void faceTarget(Entity target) {
        double xTargetDiff = target.getX() - getX();
        double zTargetDiff = target.getZ() - getZ();
        float toTarget = (float)(Mth.atan2(zTargetDiff, xTargetDiff) * 180.0F / (float)Math.PI) - 90.0F;

        setYRot(rotlerp(getYRot(), toTarget, 90.0F));
    }

    public void setCultistScale(int scale) {
        entityData.set(SCALE, scale);
    }

    public int getCultistScale() {
        return entityData.get(SCALE);
    }

    public float getCultistScaleMultiplier() {
        return 0.9f + getCultistScale() * 0.05f;
    }

    protected static float rotlerp(float sourceAngle, float targetAngle, float maximumChange) {
        float f = Mth.wrapDegrees(targetAngle - sourceAngle);
        if (f > maximumChange) {
            f = maximumChange;
        }

        if (f < -maximumChange) {
            f = -maximumChange;
        }

        float f1 = sourceAngle + f;
        if (f1 < 0.0F) {
            f1 += 360.0F;
        } else if (f1 > 360.0F) {
            f1 -= 360.0F;
        }

        return f1;
    }
}