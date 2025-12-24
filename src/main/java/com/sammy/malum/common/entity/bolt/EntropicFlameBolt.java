package com.sammy.malum.common.entity.bolt;

import com.mojang.datafixers.util.Pair;
import com.sammy.malum.common.entity.nitrate.EthericNitrate;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.entity.*;
import com.sammy.malum.registry.common.magic.*;
import com.sammy.malum.visual_effects.*;
import com.sammy.malum.visual_effects.networked.MalumNetworkedParticleEffectColorData;
import com.sammy.malum.visual_effects.networked.staff.*;
import net.minecraft.sounds.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.projectile.*;
import net.minecraft.world.level.*;
import net.minecraft.world.phys.*;
import net.neoforged.api.distmarker.*;
import org.jetbrains.annotations.NotNull;
import team.lodestar.lodestone.handlers.*;
import team.lodestar.lodestone.systems.easing.*;
import team.lodestar.lodestone.systems.particle.builder.*;
import team.lodestar.lodestone.systems.particle.data.*;
import team.lodestar.lodestone.systems.particle.data.color.ColorParticleData;
import team.lodestar.lodestone.systems.particle.render_types.*;
import team.lodestar.lodestone.systems.particle.world.*;
import team.lodestar.lodestone.systems.particle.world.behaviors.*;
import team.lodestar.lodestone.systems.rendering.trail.TrailPointBuilder;

import java.util.function.*;

import static com.sammy.malum.common.item.curiosities.weapons.staff.UnwindingChaosStaffItem.AURIC_COLOR_DATA;

public class EntropicFlameBolt extends AbstractStaffBoltProjectile {

    public static final MalumNetworkedParticleEffectColorData COLOR = new MalumNetworkedParticleEffectColorData(AURIC_COLOR_DATA, MalumSpiritTypes.AQUEOUS_SPIRIT.createColorData().build());

    public TrailPointBuilder secondarySpinningTrailPointBuilder;

    public EntropicFlameBolt(Level level) {
        super(MalumEntities.ENTROPIC_FLAME_BOLT.get(), level);
        trailPointBuilder = TrailPointBuilder.create(32);
        spinningTrailPointBuilder = TrailPointBuilder.create(24);
        secondarySpinningTrailPointBuilder = TrailPointBuilder.create(24);
    }

    @Override
    public float getOrbitingTrailDistance() {
        return 0.2f + random.nextFloat() * 0.4f;
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        final AABB boundingBox = getBoundingBox();
        setBoundingBox(boundingBox.deflate(0.25f));
        HitResult hitresult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
        if (hitresult instanceof BlockHitResult blockHitResult) {
            super.onHitBlock(blockHitResult);
        }
        setBoundingBox(boundingBox);
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        if (isAwaitingSpawn() || isFadingAway()) {
            return;
        }
        if (result.getEntity() instanceof LivingEntity livingentity) {
            livingentity.igniteForSeconds(4);
        }
        super.onHitEntity(result);
    }

    @Override
    public void tick() {
        super.tick();
        if (level().isClientSide) {
            if (isAwaitingSpawn()) {
                return;
            }
            float offsetScale = isFadingAway() ? 0f : getOrbitingTrailDistance();
            for (int i = 0; i < 2; i++) {
                float progress = (i + 1) * 0.5f;
                Vec3 position = getPosition(progress);
                float scalar = (age + progress) / 2f;
                double xOffset = Math.cos(spinOffset + 3.14f + scalar) * offsetScale;
                double zOffset = Math.sin(spinOffset + 3.14f + scalar) * offsetScale;
                secondarySpinningTrailPointBuilder.addTrailPoint(position.add(xOffset, 0, zOffset));
            }
            secondarySpinningTrailPointBuilder.tickTrailPoints();
        }
    }

    @Override
    public void playSound(@NotNull SoundEvent pSound, float pVolume, float pPitch) {
        super.playSound(pSound, pVolume, pPitch-0.2f);
        super.playSound(MalumSoundEvents.WORLDSOUL_MOTIF_LIGHT_IMPACT.get(), pVolume - 0.2f, pPitch + 0.5f);
        super.playSound(MalumSoundEvents.WORLDSOUL_MOTIF_REVERB.get(), pVolume - 0.2f, pPitch + 0.5f);
    }

    @Override
    public int getMaxAge() {
        return 60;
    }

    @Override
    public Pair<BoltImpactParticleEffect, MalumNetworkedParticleEffectColorData> getImpactParticleEffect() {
        return Pair.of(MalumParticleEffectTypes.ENTROPIC_BOLT_IMPACT, COLOR);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void spawnParticles() {
        Level level = level();
        Vec3 position = position();
        float scalar = getVisualEffectScalar();
        Vec3 norm = getDeltaMovement().normalize().scale(0.05f);
        var lightSpecs = SpiritLightSpecs.spiritLightSpecs(level, position, AURIC_COLOR_DATA);
        lightSpecs.getBuilder()
                .modifyScaleData(d -> d.multiplyValue(3 * scalar))
                .setRenderTarget(LodestoneRenderHandler.LATE_DEFERRED_RENDER)
                .multiplyLifetime(2.5f)
                .setRenderType(LodestoneWorldParticleRenderType.LUMITRANSPARENT)
                .enableForcedSpawn()
                .setMotion(norm);
        lightSpecs.getBloomBuilder()
                .setRenderTarget(LodestoneRenderHandler.LATE_DEFERRED_RENDER)
                .multiplyLifetime(1.5f)
                .setRenderType(LodestoneWorldParticleRenderType.LUMITRANSPARENT)
                .setMotion(norm);
        lightSpecs.spawnParticles();

        final Consumer<LodestoneWorldParticle> behavior = p -> p.setParticleSpeed(p.getParticleSpeed().scale(0.98f));
        final float min = Math.min(1f, 2 * scalar);
        WorldParticleBuilder.create(MalumParticles.GIANT_ARROW)
                .setBehavior(SparkParticleBehavior.sparkBehavior())
                .setLengthData(GenericParticleData.create(1.8f * scalar, 2.4f * scalar, 0.1f * scalar).setEasing(Easing.CUBIC_IN).build())
                .setTransparencyData(GenericParticleData.create(0.5f * min, 0f).setEasing(Easing.SINE_IN_OUT, Easing.SINE_IN).build())
                .setScaleData(GenericParticleData.create(1.2f * scalar, 0.1f * scalar).setEasing(Easing.SINE_IN_OUT).build())
                .setRenderTarget(LodestoneRenderHandler.LATE_DEFERRED_RENDER)
                .setLifetime(Math.min(2 + age, 16))
                .setColorData(AURIC_COLOR_DATA)
                .addTickActor(behavior)
                .enableForcedSpawn()
                .setMotion(norm)
                .enableNoClip()
                .spawn(level, position.x, position.y, position.z);
        WorldParticleBuilder.create(MalumParticles.GIANT_ARROW)
                .setBehavior(SparkParticleBehavior.sparkBehavior())
                .setLengthData(GenericParticleData.create(2f * scalar, 2.8f * scalar, 0.3f * scalar).setEasing(Easing.CUBIC_IN).build())
                .setTransparencyData(GenericParticleData.create(0.6f * min, 0.3f * min, 0f).setEasing(Easing.SINE_IN_OUT, Easing.SINE_IN).build())
                .setColorData(ColorParticleData.create(EthericNitrate.AURIC_RED, EthericNitrate.AURIC_RED).setCoefficient(3f).build())
                .setScaleData(GenericParticleData.create(1.5f * scalar, 0.3f * scalar).setEasing(Easing.SINE_IN_OUT).build())
                .setRenderType(LodestoneWorldParticleRenderType.LUMITRANSPARENT)
                .setLifetime(Math.min(3 + age, 24))
                .setMotion(norm.scale(0.5f))
                .addTickActor(behavior)
                .enableForcedSpawn()
                .enableNoClip()
                .spawn(level, position.x, position.y, position.z);
    }
}