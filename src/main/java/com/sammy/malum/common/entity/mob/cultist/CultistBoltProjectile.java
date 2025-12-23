package com.sammy.malum.common.entity.mob.cultist;

import com.mojang.datafixers.util.Pair;
import com.sammy.malum.common.entity.bolt.AbstractBoltProjectile;
import com.sammy.malum.registry.common.MalumDamageTypes;
import com.sammy.malum.registry.common.MalumParticles;
import com.sammy.malum.registry.common.entity.MalumEntities;
import com.sammy.malum.visual_effects.SpiritLightSpecs;
import com.sammy.malum.visual_effects.networked.MalumNetworkedParticleEffectColorData;
import com.sammy.malum.visual_effects.networked.staff.BoltImpactParticleEffect;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import team.lodestar.lodestone.handlers.LodestoneRenderHandler;
import team.lodestar.lodestone.systems.easing.Easing;
import team.lodestar.lodestone.systems.particle.builder.WorldParticleBuilder;
import team.lodestar.lodestone.systems.particle.data.GenericParticleData;
import team.lodestar.lodestone.systems.particle.data.color.ColorParticleData;
import team.lodestar.lodestone.systems.particle.render_types.LodestoneWorldParticleRenderType;
import team.lodestar.lodestone.systems.particle.world.behaviors.SparkParticleBehavior;

import java.awt.*;

public class CultistBoltProjectile extends AbstractBoltProjectile {

    public static final Color CULTIST_RED = new Color(214, 44, 50);
    public static final Color CULTIST_CRIMSON = new Color(102, 25, 28);
    public static final Color CULTIST_DARK = new Color(48, 11, 13);

    public CultistBoltProjectile(Level level) {
        super(MalumEntities.CULTIST_BOLT.get(), level);
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
        return Player.class;
    }

    @Override
    protected boolean canHitEntity(Entity pTarget) {
        if (homingTarget != null) {
            return pTarget.equals(homingTarget);
        }
        return super.canHitEntity(pTarget);
    }

    @Override
    public float getHomingDelta(float dot) {
        if (distanceTo(homingTarget) < 4f) {
            return 0;
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
        var color = ColorParticleData.create(CULTIST_RED, CULTIST_CRIMSON);
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

        var arrowBuilder = WorldParticleBuilder.create(MalumParticles.SMALL_ARROW)
                .setRenderTarget(LodestoneRenderHandler.LATE_DEFERRED_RENDER)
                .setLengthData(GenericParticleData.create(0.4f, 0.6f, 0f).setEasing(Easing.EXPO_OUT, Easing.SINE_IN_OUT).build())
                .setScaleData(GenericParticleData.create(0.6f, 0).setEasing(Easing.SINE_IN).build())
                .setTransparencyData(GenericParticleData.create(0.95f).build())
                .setBehavior(SparkParticleBehavior.sparkBehavior().setForcedDirection(direction))
                .setRenderType(LodestoneWorldParticleRenderType.LUMITRANSPARENT)
                .setMotion(motion.scale(-1f))
                .setColorData(color)
                .setLifetime(6)
                .enableNoClip();
        for (int i = 0; i < 3; i++) {
            var arrowPosition = getPosition(i / 3f);
            arrowBuilder.spawn(level, arrowPosition);
        }
    }
}