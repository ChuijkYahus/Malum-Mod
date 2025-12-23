package com.sammy.malum.common.entity.mob.cultist.cardinal.projectile;

import com.mojang.datafixers.util.Pair;
import com.sammy.malum.common.entity.bolt.AbstractBoltProjectile;
import com.sammy.malum.common.entity.mob.cultist.altar.projectile.CultistBoltProjectile;
import com.sammy.malum.common.entity.mob.cultist.CultistMonster;
import com.sammy.malum.registry.common.MalumDamageTypes;
import com.sammy.malum.registry.common.MalumParticleEffectTypes;
import com.sammy.malum.registry.common.MalumParticles;
import com.sammy.malum.registry.common.entity.MalumEntities;
import com.sammy.malum.visual_effects.SpiritLightSpecs;
import com.sammy.malum.visual_effects.networked.MalumNetworkedParticleEffectColorData;
import com.sammy.malum.visual_effects.networked.staff.BoltImpactParticleEffect;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import team.lodestar.lodestone.helpers.DamageTypeHelper;
import team.lodestar.lodestone.helpers.RandomHelper;
import team.lodestar.lodestone.systems.easing.Easing;
import team.lodestar.lodestone.systems.particle.SimpleParticleOptions;
import team.lodestar.lodestone.systems.particle.builder.WorldParticleBuilder;
import team.lodestar.lodestone.systems.particle.data.GenericParticleData;
import team.lodestar.lodestone.systems.particle.data.color.ColorParticleData;
import team.lodestar.lodestone.systems.particle.data.spin.SpinParticleData;
import team.lodestar.lodestone.systems.particle.render_types.LodestoneWorldParticleRenderType;
import team.lodestar.lodestone.systems.particle.world.behaviors.DirectionalParticleBehavior;
import team.lodestar.lodestone.systems.rendering.trail.TrailPointBuilder;

import java.awt.*;
import java.util.Collections;
import java.util.List;

public class EntropyChargeProjectile extends AbstractBoltProjectile {

    protected static final EntityDataAccessor<Boolean> DATA_PLACED = SynchedEntityData.defineId(EntropyChargeProjectile.class, EntityDataSerializers.BOOLEAN);

    public static final float DETONATION_RADIUS = 5f;
    public int primedTime;

    public EntropyChargeProjectile(Level level) {
        super(MalumEntities.ENTROPY_CHARGE.get(), level);
        trailPointBuilder = TrailPointBuilder.create(32);
        spinningTrailPointBuilder = TrailPointBuilder.create(16);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_PLACED, false);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        if (isPlaced()) {
            compound.putBoolean("IsPlaced", true);
            compound.putInt("PrimedTime", primedTime);
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        setPlaced(compound.getBoolean("IsPlaced"));
        primedTime = compound.getInt("PrimedTime");
    }

    @Override
    public int getMaxAge() {
        return 200;
    }

    @Override
    public Pair<BoltImpactParticleEffect, MalumNetworkedParticleEffectColorData> getImpactParticleEffect() {
        return null;
    }

    @Override
    public ResourceKey<DamageType> getDamageType() {
        return MalumDamageTypes.CULTIST_MAGIC;
    }

    @Override
    public SoundEvent getShootSound() {
        return SoundEvents.CROSSBOW_SHOOT;
    }

    @Override
    public SoundEvent getImpactSound() {
        return SoundEvents.CROSSBOW_HIT;
    }

    @Override
    public Class<? extends LivingEntity> getHomingTarget() {
        return Player.class;
    }

    @Override
    protected boolean canHitEntity(Entity pTarget) {
        return false;
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        if (isPlaced()) {
            return;
        }
        super.onHitBlock(pResult);
    }

    @Override
    public float getHomingDelta(float dot) {
        if (!isPlaced() && distanceTo(homingTarget) < 8f) {
            return 0;
        }
        float base = 0.1f;
        float gain = 0.02f;
        float limit = 0.4f;
        return Math.clamp(base + age * gain, 0, limit);
    }

    @Override
    public void setHomingTarget(LivingEntity target) {
        if (homingTarget != null && homingTarget.isAlive() && target == null) {
            return;
        }
        super.setHomingTarget(target);
    }

    @Override
    public float getMovementDecay() {
        return 0.95f;
    }

    @Override
    public float getBoltGravity() {
        if (isPlaced()) {
            return 0;
        }
        float base = 0.01f;
        float gain = 0.005f;
        float limit = 0.2f;
        return Math.clamp(base + age * gain, 0, limit);
    }

    @Override
    public float getOrbitingTrailDistance() {
        if (isPlaced()) {
            float delta = Math.min(primedTime / 40f, 1);
            return 0.25f + delta * DETONATION_RADIUS;
        }
        return 0.25f;
    }

    @Override
    public float getOrbitingTrailRate() {
        return 0.125f;
    }

    @Override
    public void startFadingAway() {
        setPlaced(true);
        setDeltaMovement(new Vec3(0, 0.075f, 0));
    }

    @Override
    public void tick() {
        super.tick();
        if (isPlaced()) {
            primedTime++;
            float desiredY = (float) getY();
            if (homingTarget != null) {
                desiredY = (float) (homingTarget.getY() + 0.5f);
            }
            var mutable = blockPosition().mutable();
            for (int i = 0; i < 4; i++) {
                mutable.move(Direction.DOWN);

                BlockState state = level().getBlockState(mutable);
                if (state.isFaceSturdy(level(), mutable, Direction.UP)) {
                    desiredY = Math.max(desiredY, mutable.getY() + 1.5f);
                    break;
                }
            }
            float difference = (float) (desiredY - getY());
            float motion = difference * 0.015f;
            if (motion != 0) {
                setDeltaMovement(getDeltaMovement().add(0, motion, 0).scale(0.9f));
            }
        }
    }

    public void detonate(ServerLevel level) {
        var source = DamageTypeHelper.create(level(), getDamageType(), this, getOwner());
        var explosionAffectedTargets = getExplosionAffectedTargets();
        for (LivingEntity explosionAffectedTarget : explosionAffectedTargets) {
            explosionAffectedTarget.hurt(source, magicDamage);
        }
        entityData.set(DATA_FADING_AWAY, true);
        MalumParticleEffectTypes.ENTROPY_CHARGE_DETONATES
                .createEffect(position())
                .color(MalumNetworkedParticleEffectColorData.fromColors(
                        List.of(ColorParticleData.create(CultistBoltProjectile.CULTIST_RED, CultistBoltProjectile.CULTIST_CRIMSON),
                                ColorParticleData.create(CultistBoltProjectile.CULTIST_CRIMSON, CultistBoltProjectile.CULTIST_DARK))
                ))
                .spawn(level);

    }

    public List<LivingEntity> getExplosionAffectedTargets() {
        if (!isPlaced()) {
            return Collections.emptyList();
        }
        float delta = Math.min(primedTime / 20f, 1);
        float half = DETONATION_RADIUS / 2f * delta;
        float quarter = DETONATION_RADIUS / 4f * delta;
        var area = getBoundingBox().inflate(half, quarter, half);
        return level().getEntitiesOfClass(LivingEntity.class, area,
                target -> {
                    if (!EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(target)) {
                        return false;
                    }
                    if (target instanceof CultistMonster) {
                        return false;
                    }
                    Entity owner = getOwner();
                    if (owner != null) {
                        if (target == owner || target.isAlliedTo(owner)) {
                            return false;
                        }
                    }
                    return target.isAlive() && hasLineOfSight(target);
                });
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void spawnParticles() {
        var level = level();
        var color = ColorParticleData.create(CultistBoltProjectile.CULTIST_RED, CultistBoltProjectile.CULTIST_CRIMSON);
        var position = position();
        float lifetimeMultiplier = 5f;
        if (isPlaced()) {
            float angle = (spinOffset + age * 0.4f) % 6.28f;
            float distance = getOrbitingTrailDistance() * 0.6f;
            position = position.add(getOrbitingTrailOrbit(angle).scale(distance));
            lifetimeMultiplier *= 0.4f;
        }
        var lightSpecs = SpiritLightSpecs.spiritLightSpecs(level, position, color);
        lightSpecs.getBuilder()
                .setRenderType(LodestoneWorldParticleRenderType.LUMITRANSPARENT)
                .setTransparencyData(GenericParticleData.create(0.9f))
                .multiplyLifetime(lifetimeMultiplier);
        lightSpecs.getBloomBuilder()
                .setRenderType(LodestoneWorldParticleRenderType.LUMITRANSPARENT)
                .setTransparencyData(GenericParticleData.create(0.9f))
                .multiplyLifetime(lifetimeMultiplier * 0.6f);
        lightSpecs.spawnParticles();


        float scale = 2 + 2 * Math.min(primedTime/2, 10)/10f;
        int lifetime = 8 + Math.min(primedTime/2, 24);
        if (!isPlaced() || level.getGameTime() % 6 == 0) {
            Vec3 direction = isPlaced() ? new Vec3(0, 1, 0) : getDeltaMovement().normalize();
            int times = isPlaced() ? 4 : 1;
            var particle = isPlaced() ? MalumParticles.CULTIST_WAVE : MalumParticles.CULTIST_PULSE;
            float particleScale = isPlaced() ? 0.5f : 0.15f;
            for (int i = 0; i < times; i++) {
                var pulsePosition = getPosition(i / (float) times);
                int delay = times == 1 ? 0 : RandomHelper.randomBetween(random, i*4, 8+i*4);
                float multiplier = (1 + random.nextFloat() * random.nextFloat())*scale;
                WorldParticleBuilder builder = WorldParticleBuilder.create(particle)
                        .setBehavior(DirectionalParticleBehavior.directional().setForcedDirection(direction))
                        .setScaleData(GenericParticleData.create(particleScale * multiplier).setEasing(Easing.SINE_IN))
                        .setRenderType(LodestoneWorldParticleRenderType.LUMITRANSPARENT)
                        .setColorData(color)
                        .setLifetime(lifetime)
                        .setLifeDelay(delay)
                        .enableNoClip();
                if (isPlaced()) {
                    builder.setSpritePicker(SimpleParticleOptions.ParticleSpritePicker.RANDOM_SPRITE)
                            .setLifeDelay(RandomHelper.randomBetween(random, 0, 8))
                            .setScaleData(GenericParticleData.create(particleScale * multiplier, 0).setEasing(Easing.SINE_IN))
                            .setSpinData(SpinParticleData.createRandomDirection(random, 0.2f, 0.1f).randomSpinOffset(random))
                            .setColorData(ColorParticleData.create(CultistBoltProjectile.CULTIST_RED, new Color(41, 11, 12)).setEasing(Easing.SINE_IN_OUT))
                            .setTransparencyData(GenericParticleData.create(0, 0.9f, 0).setEasing(Easing.SINE_IN_OUT, Easing.SINE_IN_OUT));
                }
                builder
                        .spawn(level, pulsePosition);
            }
        }
    }

    public boolean isPlaced() {
        return entityData.get(DATA_PLACED);
    }

    public void setPlaced(boolean value) {
        entityData.set(DATA_PLACED, value);
    }
}