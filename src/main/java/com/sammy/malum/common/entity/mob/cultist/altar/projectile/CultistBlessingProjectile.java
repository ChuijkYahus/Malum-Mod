package com.sammy.malum.common.entity.mob.cultist.altar.projectile;

import com.mojang.datafixers.util.Pair;
import com.sammy.malum.common.entity.bolt.AbstractBoltProjectile;
import com.sammy.malum.common.entity.mob.cultist.IAltarBlessingRecipient;
import com.sammy.malum.common.entity.mob.cultist.altar.AltarCultist;
import com.sammy.malum.registry.common.MalumDamageTypes;
import com.sammy.malum.registry.common.MalumParticles;
import com.sammy.malum.registry.common.entity.*;
import com.sammy.malum.visual_effects.SpiritLightSpecs;
import com.sammy.malum.visual_effects.networked.MalumNetworkedParticleEffectColorData;
import com.sammy.malum.visual_effects.networked.staff.BoltImpactParticleEffect;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import team.lodestar.lodestone.handlers.LodestoneRenderHandler;
import team.lodestar.lodestone.systems.easing.Easing;
import team.lodestar.lodestone.systems.particle.builder.WorldParticleBuilder;
import team.lodestar.lodestone.systems.particle.data.GenericParticleData;
import team.lodestar.lodestone.systems.particle.data.color.ColorParticleData;
import team.lodestar.lodestone.systems.particle.data.spin.SpinParticleData;
import team.lodestar.lodestone.systems.particle.render_types.LodestoneWorldParticleRenderType;

import java.awt.*;

public class CultistBlessingProjectile extends AbstractBoltProjectile {

    public static final Color CULTIST_PINK = new Color(255, 87, 167);
    public static final Color CULTIST_PURPLE = new Color(119, 10, 74);

    public CultistBlessingProjectile(Level level) {
        super(MalumCultistEntityTypes.CULTIST_BLESSING.get(), level);
    }

    @Override
    public int getMaxAge() {
        return 80;
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
        return LivingEntity.class;
    }

    @Override
    public boolean canHomeIn(LivingEntity target) {
        return target instanceof IAltarBlessingRecipient;
    }

    @Override
    protected boolean canHitEntity(Entity pTarget) {
        if (homingTarget != null) {
            return pTarget.equals(homingTarget);
        }
        return super.canHitEntity(pTarget);
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        if (level() instanceof ServerLevel level) {
            if (isFadingAway() || isAwaitingSpawn()) {
                return;
            }
            if (getOwner() instanceof AltarCultist altar) {
                var target = result.getEntity();
                if (target instanceof LivingEntity living) {
                    altar.applyBlessing(level, living);
                    playImpactSound();
                    spawnEffect(level, 0.5f);
                    setDeltaMovement(getDeltaMovement().scale(0.05f));
                    getEntityData().set(DATA_FADING_AWAY, true);
                }
            }
        }
    }

    @Override
    public float getHomingDelta(float dot) {
        if (distanceTo(homingTarget) < 4f) {
            return 1f;
        }
        float delta = 0.1f;
        float gain = 0.02f;
        float limit = 0.4f;
        return Math.clamp(delta + age * gain, 0, limit);
    }

    @Override
    public float getMovementDecay() {
        return 0.98f;
    }

    @Override
    public float getBoltGravity() {
        return 0.01f;
    }

    @Override
    public float getOrbitingTrailDistance() {
        return 0.1f;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void spawnParticles() {
        var level = level();
        var position = position();
        var direction = getDeltaMovement().normalize();
        var motion = direction.scale(0.1f);
        var color = ColorParticleData.create(CULTIST_PINK, CULTIST_PURPLE);
        var lightSpecs = SpiritLightSpecs.spiritLightSpecs(level, position, color);
        lightSpecs.getBuilder()
                .setRenderTarget(LodestoneRenderHandler.LATE_DEFERRED_RENDER)
                .setRenderType(LodestoneWorldParticleRenderType.LUMITRANSPARENT)
                .multiplyLifetime(2f)
                .setMotion(motion);
        lightSpecs.getBloomBuilder()
                .setRenderTarget(LodestoneRenderHandler.LATE_DEFERRED_RENDER)
                .setRenderType(LodestoneWorldParticleRenderType.LUMITRANSPARENT)
                .multiplyLifetime(1.25f)
                .setMotion(motion);
        lightSpecs.spawnParticles();

        var shineBuilder = WorldParticleBuilder.create(MalumParticles.SHINE)
                .setRenderTarget(LodestoneRenderHandler.LATE_DEFERRED_RENDER)
                .setLengthData(GenericParticleData.create(0.4f, 0.6f, 0f).setEasing(Easing.EXPO_OUT, Easing.SINE_IN_OUT).build())
                .setScaleData(GenericParticleData.create(0.6f, 0).setEasing(Easing.SINE_IN).build())
                .setSpinData(SpinParticleData.create(0).randomSpinOffset(random))
                .setTransparencyData(GenericParticleData.create(0.95f).build())
                .setRenderType(LodestoneWorldParticleRenderType.LUMITRANSPARENT)
                .setMotion(motion.scale(-1f))
                .setColorData(color)
                .setLifetime(12)
                .enableNoClip();
        for (int i = 0; i < 3; i++) {
            var arrowPosition = getPosition(i / 3f);
            shineBuilder.spawn(level, arrowPosition);
        }
    }
}