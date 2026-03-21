package com.sammy.malum.common.entity.mob.cultist;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.sammy.malum.MalumMod;
import com.sammy.malum.common.entity.mob.cultist.cardinal.*;
import com.sammy.malum.registry.common.MalumDamageTypes;
import com.sammy.malum.registry.common.sound.*;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.*;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.registry.common.LodestoneAttributes;

import java.util.function.*;

@SuppressWarnings("NullableProblems")
public abstract class CultistMonster extends Monster implements Enemy {

    private static final EntityDataAccessor<Integer> SCALE = SynchedEntityData.defineId(CultistMonster.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> HAS_EMPOWERMENT = SynchedEntityData.defineId(CultistMonster.class, EntityDataSerializers.BOOLEAN);

    public static final int EMPOWERMENT_DURATION = 200;
    public static final ResourceLocation ALTAR_EMPOWERMENT = MalumMod.malumPath("altar_empowerment");
    public static final Multimap<Holder<Attribute>, AttributeModifier> EMPOWERMENT_MODIFIERS =
            ImmutableMultimap.of(
                    LodestoneAttributes.MAGIC_DAMAGE.getDelegate(), new AttributeModifier(ALTAR_EMPOWERMENT, 2f, AttributeModifier.Operation.ADD_VALUE),
                    LodestoneAttributes.MAGIC_RESISTANCE.getDelegate(), new AttributeModifier(ALTAR_EMPOWERMENT, 0.5f, AttributeModifier.Operation.ADD_VALUE),
                    Attributes.MOVEMENT_SPEED, new AttributeModifier(ALTAR_EMPOWERMENT, 0.5f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
                    Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(ALTAR_EMPOWERMENT, 0.5f, AttributeModifier.Operation.ADD_VALUE)
            );

    private final CultistSoundDefinition soundDefinition;
    private final CultistMovementData movementData;

    public int empowermentDuration;
    public float empowermentVisibility;

    protected CultistMonster(EntityType<? extends Monster> entityType, CultistSoundDefinition soundDefinition, Level level) {
        super(entityType, level);
        this.soundDefinition = soundDefinition;
        this.movementData = new CultistMovementData(this);

        setHealth(getMaxHealth());
        xpReward = Mth.floor(getMaxHealth() * 1.5f);
        moveControl = new CultistMoveControl(this);
        lookControl = new CultistLookControl(this);
    }

    public CultistSoundDefinition getSoundDefinition() {
        return soundDefinition;
    }

    public CultistMovementData getMovementData() {
        return movementData;
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
        builder.define(HAS_EMPOWERMENT, false);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("CultistScale", getCultistScale());

        compound.putInt("EmpowermentDuration", empowermentDuration);
        compound.putFloat("EmpowermentVisibility", empowermentVisibility);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        setCultistScale(compound.getInt("CultistScale"));

        setEmpowermentDuration(compound.getInt("EmpowermentDuration"));
        empowermentVisibility = compound.getFloat("EmpowermentVisibility");
    }

    @Override
    public @NotNull SoundSource getSoundSource() {
        return SoundSource.HOSTILE;
    }

    @Override
    protected @Nullable SoundEvent getAmbientSound() {
        return soundDefinition.idleSound.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return soundDefinition.hurtSound.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return soundDefinition.deathSound.get();
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

    @Override
    public void tick() {
        super.tick();
        updateEmpowermentVisibility();
    }

    @Override
    protected void customServerAiStep() {
        movementData.update();
        super.customServerAiStep();
        move(MoverType.SELF, movementData.getMotionVector());
        updateEmpowerment();
    }

    public void updateEmpowerment() {
        if (empowermentDuration > 0) {
            empowermentDuration--;
            if (empowermentDuration == 0) {
                getAttributes().removeAttributeModifiers(EMPOWERMENT_MODIFIERS);
                setEmpowerment(false);
            }
        }
    }

    public void updateEmpowermentVisibility() {
        empowermentVisibility = DataHelper.approach(empowermentDuration, hasEmpowerment() ? 1 : 0, 0.1f);
    }

    public void startAnimation(AnimationState animation) {
        animation.start(tickCount);
    }


    public void broadcastAnimation(byte animationEvent, Supplier<SoundEvent> sound) {
        level().broadcastEntityEvent(this, animationEvent);
        SoundHelper.playSoundRandomPitch(this, sound, 1.5f, 0.8f, 1.2f);
    }

    public Vec3 directionToTarget(Entity target) {
        double x = target.getX() - getX();
        double y = target.getY(0.5f) - getY(0.5f);
        double z = target.getZ() - getZ();
        return new Vec3(x, y, z).normalize();
    }

    public boolean isTargetWithinRadius(float radius) {
        return isTargetWithinRadius(getTarget(), radius);
    }

    public boolean isTargetWithinRadius(Entity target, float radius) {
        if (target != null) {
            float distanceToTarget = (float) distanceToSqr(target);
            return (distanceToTarget < radius*radius);
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
        } else {
            faceTarget(target);
        }
        getLookControl().setLookAt(target, 60.0F, 60.0F);
    }

    public void faceTarget(Entity target) {
        double xTargetDiff = target.getX() - getX();
        double zTargetDiff = target.getZ() - getZ();
        float toTarget = (float) (Mth.atan2(zTargetDiff, xTargetDiff) * 180.0F / (float) Math.PI) - 90.0F;

        setYRot(getMoveControl().rotlerp(getYRot(), toTarget, 90.0F));
    }

    public void setCultistScale(int scale) {
        entityData.set(SCALE, scale);
    }

    public int getCultistScale() {
        return entityData.get(SCALE);
    }

    public void setEmpowermentDuration(int duration) {
        empowermentDuration = duration;
        setEmpowerment(duration > 0);
    }

    public void setEmpowerment(boolean hasEmpowerment) {
        entityData.set(HAS_EMPOWERMENT, hasEmpowerment);
        if (hasEmpowerment) {
            getAttributes().addTransientAttributeModifiers(EMPOWERMENT_MODIFIERS);
        }
    }

    public boolean hasEmpowerment() {
        return entityData.get(HAS_EMPOWERMENT);
    }

    public float getCultistScaleMultiplier() {
        return 0.9f + getCultistScale() * 0.05f;
    }
}