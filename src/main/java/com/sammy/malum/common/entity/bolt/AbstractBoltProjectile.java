package com.sammy.malum.common.entity.bolt;

import com.mojang.datafixers.util.Pair;
import com.sammy.malum.visual_effects.networked.MalumNetworkedParticleEffectColorData;
import com.sammy.malum.visual_effects.networked.staff.*;
import net.minecraft.nbt.*;
import net.minecraft.network.syncher.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.*;
import net.minecraft.sounds.*;
import net.minecraft.util.*;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.projectile.*;
import net.minecraft.world.level.*;
import net.minecraft.world.phys.*;
import net.neoforged.api.distmarker.*;
import org.jetbrains.annotations.NotNull;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.systems.rendering.trail.*;

import javax.annotation.Nullable;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractBoltProjectile extends ThrowableProjectile {

    protected static final EntityDataAccessor<Boolean> DATA_FADING_AWAY = SynchedEntityData.defineId(AbstractBoltProjectile.class, EntityDataSerializers.BOOLEAN);
    protected static final EntityDataAccessor<Integer> DATA_SPAWN_DELAY = SynchedEntityData.defineId(AbstractBoltProjectile.class, EntityDataSerializers.INT);

    public TrailPointBuilder trailPointBuilder = TrailPointBuilder.create(8);
    public TrailPointBuilder spinningTrailPointBuilder = TrailPointBuilder.create(16);
    public float spinOffset = (float) (random.nextFloat() * Math.PI * 2);
    protected float magicDamage;
    public boolean isHoming;
    public int age;
    public int spawnDelay;

    public boolean fadingAway;
    public int fadingTimer;

    protected LivingEntity homingTarget;

    public AbstractBoltProjectile(EntityType<? extends AbstractBoltProjectile> pEntityType, Level level) {
        super(pEntityType, level);
        setNoGravity(false);
    }

    public void setData(LivingEntity owner, float magicDamage, int spawnDelay, boolean isHoming) {
        setOwner(owner);
        this.magicDamage = magicDamage;
        this.isHoming = isHoming;
        getEntityData().set(DATA_SPAWN_DELAY, spawnDelay);
        if (!level().isClientSide && spawnDelay == 0) {
            playShootSound();
        }
    }

    @OnlyIn(Dist.CLIENT)
    public abstract void spawnParticles();

    public abstract int getMaxAge();

    @Nullable
    public abstract Pair<BoltImpactParticleEffect, MalumNetworkedParticleEffectColorData> getImpactParticleEffect();

    public abstract ResourceKey<DamageType> getDamageType();

    public abstract SoundEvent getShootSound();

    public abstract SoundEvent getImpactSound();

    public abstract Class<? extends LivingEntity> getHomingTarget();

    public abstract float getHomingDelta(float dot);

    public abstract float getMovementDecay();

    public abstract float getBoltGravity();

    public float getOrbitingTrailDistance() {
        return 0.3f;
    }

    public boolean canHomeIn(LivingEntity target) {
        return true;
    }

    public void onHit(LivingEntity target) {

    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(DATA_FADING_AWAY, false);
        builder.define(DATA_SPAWN_DELAY, 0);
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> pKey) {
        if (DATA_FADING_AWAY.equals(pKey)) {
            fadingAway = entityData.get(DATA_FADING_AWAY);
            if (fadingAway) {
                age = getMaxAge() - 10;
                setDeltaMovement(getDeltaMovement().scale(0.02f));
            }
        }
        if (DATA_SPAWN_DELAY.equals(pKey)) {
            spawnDelay = entityData.get(DATA_SPAWN_DELAY);
        }
        super.onSyncedDataUpdated(pKey);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        if (magicDamage != 0) {
            compound.putFloat("magicDamage", magicDamage);
        }
        if (isHoming) {
            compound.putBoolean("isHoming", true);
        }
        if (age != 0) {
            compound.putInt("age", age);
        }
        if (spawnDelay != 0) {
            compound.putInt("spawnDelay", spawnDelay);
        }
        if (fadingAway) {
            compound.putBoolean("fadingAway", true);
            compound.putInt("fadingTimer", fadingTimer);
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        magicDamage = compound.getFloat("magicDamage");
        isHoming = compound.getBoolean("isHoming");
        age = compound.getInt("age");
        getEntityData().set(DATA_SPAWN_DELAY, compound.getInt("spawnDelay"));
        getEntityData().set(DATA_FADING_AWAY, compound.getBoolean("fadingAway"));
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        if (fadingAway || spawnDelay > 0) {
            return;
        }
        if (level() instanceof ServerLevel serverLevel) {
            playImpactSound();
            spawnEffect(serverLevel, 0.25f);
            getEntityData().set(DATA_FADING_AWAY, true);
            Vec3 direction = pResult.getLocation().subtract(position());
            Vec3 offset = direction.normalize().scale(0.5f);
            setPosRaw(getX() - offset.x, getY() - offset.y, getZ() - offset.z);
        }
        super.onHitBlock(pResult);
    }

    @Override
    protected boolean canHitEntity(Entity pTarget) {
        if (pTarget.equals(getOwner())) {
            return false;
        }
        if (pTarget instanceof AbstractBoltProjectile) {
            return false;
        }
        return super.canHitEntity(pTarget);
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        if (level() instanceof ServerLevel serverLevel) {
            if (fadingAway || spawnDelay > 0) {
                return;
            }
            if (getOwner() instanceof LivingEntity boltOwner) {
                var target = result.getEntity();
                target.invulnerableTime = 0;
                var source = DamageTypeHelper.create(level(), getDamageType(), this, boltOwner);
                boolean success = target.hurt(source, magicDamage);
                if (success && target instanceof LivingEntity livingentity) {
                    onHit(livingentity);
                    playImpactSound();
                    spawnEffect(serverLevel, 0.5f);
                    setDeltaMovement(getDeltaMovement().scale(0.05f));
                    getEntityData().set(DATA_FADING_AWAY, true);
                }
            }
        }
        super.onHitEntity(result);
    }

    @Override
    public void tick() {
        if (spawnDelay > 0) {
            spawnDelay--;
            if (spawnDelay == 0 && !level().isClientSide) {
                playShootSound();
            }
            return;
        }
        super.tick();
        age++;
        if (fadingAway) {
            fadingTimer++;
        } else {
            var motion = getDeltaMovement();
            float decay = getMovementDecay();
            float gravity = getBoltGravity();
            setDeltaMovement(motion.x * decay, (motion.y - gravity) * decay, motion.z * decay);
        }
        if (isHoming) {
            homeIn();
        }
        if (level().isClientSide) {
            float offsetScale = fadingAway ? 0f : getOrbitingTrailDistance();
            for (int i = 0; i < 2; i++) {
                float progress = (i + 1) * 0.5f;
                Vec3 position = getPosition(progress);
                float scalar = (age + progress) / 2f;
                double xOffset = Math.cos(spinOffset + scalar) * offsetScale;
                double zOffset = Math.sin(spinOffset + scalar) * offsetScale;
                trailPointBuilder.addTrailPoint(position);
                spinningTrailPointBuilder.addTrailPoint(position.add(xOffset, 0, zOffset));
            }
            trailPointBuilder.tickTrailPoints();
            spinningTrailPointBuilder.tickTrailPoints();
            if (!fadingAway) {
                spawnParticles();
            }
        } else if (age >= getMaxAge()) {
            if (fadingAway) {
                discard();
            } else {
                getEntityData().set(DATA_FADING_AWAY, true);
            }
        }
    }

    @Override
    public @NotNull SoundSource getSoundSource() {
        return getOwner() != null ? getOwner().getSoundSource() : SoundSource.PLAYERS;
    }

    @Override
    public void playSound(@NotNull SoundEvent sound, float volume, float pitch) {
        if (getOwner() != null) {
            if (position().distanceTo(getOwner().position()) < 2f) {
                SoundHelper.playSound(getOwner(), sound, volume, pitch);
                return;
            }
        }
        super.playSound(sound, volume, pitch);
    }

    public void playShootSound() {
        playSound(getShootSound(), 1f, Mth.nextFloat(random, 0.9F, 1.5F));
    }

    public void playImpactSound() {
        playSound(getImpactSound(), 1f, Mth.nextFloat(random, 0.9F, 1.5F));
    }

    public void homeIn() {
        var motion = getDeltaMovement();
        var owner = getOwner();
        if (spawnDelay > 0 || owner == null || fadingAway) {
            return;
        }
        if (homingTarget == null || homingTarget.isDeadOrDying()) {
            var area = getBoundingBox().inflate(50);
            List<? extends LivingEntity> entities = level().getEntitiesOfClass(getHomingTarget(), area,
                    target -> target != owner && target.isAlive() && !target.isAlliedTo(owner) && canHomeIn(target) && hasLineOfSight(target));
            if (entities.isEmpty()) {
                return;
            }
            var angle = getDeltaMovement().normalize();
            homingTarget = entities.stream().max(Comparator.comparingDouble((e) -> e.position().subtract(position()).normalize().dot(angle))).get();
        }
        if (homingTarget != null) {
            var targetPosition = homingTarget.position().add(0, homingTarget.getBbHeight() / 2, 0);
            var diff = targetPosition.subtract(position());
            var nextPosition = position().add(getDeltaMovement());
            if (homingTarget.distanceToSqr(nextPosition) > homingTarget.distanceToSqr(position())) {
                //By checking if the next position is further away than the current one, we're effectively able to determine if the projectile is moving towards the enemy
                homingTarget = null;
                return;
            }
            float dot = (float) motion.normalize().dot(diff.normalize());
            if (dot < 0) {
                homingTarget = null;
                return;
            }

            var newMotion = diff.normalize().scale(motion.length());
            if (newMotion.length() == 0) {
                newMotion = newMotion.add(0.01, 0, 0);
            }

            float delta = getHomingDelta(dot);
            double x = Mth.clampedLerp(motion.x, newMotion.x, delta);
            double y = Mth.clampedLerp(motion.y, newMotion.y, delta);
            double z = Mth.clampedLerp(motion.z, newMotion.z, delta);
            setDeltaMovement(new Vec3(x, y, z));
        }
    }

    public void spawnEffect(ServerLevel level, float offset) {
        var data = getImpactParticleEffect();
        if (data == null) {
            return;
        }
        var effect = data.getFirst();
        var color = data.getSecond();
        effect.createEffect(position().add(getDeltaMovement().scale(offset)))
                .color(color)
                .customData(new BoltImpactParticleEffect.BoltImpactEffectData(getDeltaMovement().reverse().normalize()))
                .spawn(level);
    }

    public void shootFromStaff(@NotNull Entity shooter, float rotationPitch, float rotationYaw, float pitchOffset, float velocity, float inaccuracy) {
        float f = -Mth.sin(rotationYaw * ((float) Math.PI / 180F)) * Mth.cos(rotationPitch * ((float) Math.PI / 180F));
        float f1 = -Mth.sin((rotationPitch + pitchOffset) * ((float) Math.PI / 180F));
        float f2 = Mth.cos(rotationYaw * ((float) Math.PI / 180F)) * Mth.cos(rotationPitch * ((float) Math.PI / 180F));
        this.shoot(f, f1, f2, velocity, inaccuracy);
    }

    public boolean hasLineOfSight(Entity target) {
        Vec3 wrathPosition = new Vec3(getX(), getEyeY(), getZ());
        Vec3 targetPosition = new Vec3(target.getX(), target.getEyeY(), target.getZ());
        var clipResult = level().clip(new ClipContext(wrathPosition, targetPosition, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this));
        return clipResult.getType().equals(HitResult.Type.MISS);
    }

    public float getVisualEffectScalar() {
        float effectScalar = 1;
        if (age < 8) {
            effectScalar = age / 8f;
        } else if (fadingAway) {
            effectScalar = effectScalar / ((fadingTimer + 2) / 2f);
        }
        return effectScalar;
    }

    @Override
    public boolean isNoGravity() {
        return true;
    }

    @Override
    public float getPickRadius() {
        return 4f;
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    @Override
    public boolean ignoreExplosion(Explosion explosion) {
        return true;
    }
}