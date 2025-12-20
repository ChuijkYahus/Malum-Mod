package com.sammy.malum.common.entity.bolt;

import com.mojang.datafixers.util.Pair;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.entity.*;
import com.sammy.malum.visual_effects.*;
import com.sammy.malum.visual_effects.networked.MalumNetworkedParticleEffectColorData;
import com.sammy.malum.visual_effects.networked.staff.*;
import net.minecraft.sounds.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.*;
import net.minecraft.world.phys.*;
import net.neoforged.api.distmarker.*;
import org.jetbrains.annotations.NotNull;
import team.lodestar.lodestone.handlers.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.systems.easing.*;
import team.lodestar.lodestone.systems.particle.builder.*;
import team.lodestar.lodestone.systems.particle.data.*;
import team.lodestar.lodestone.systems.particle.data.spin.*;
import team.lodestar.lodestone.systems.particle.render_types.*;
import team.lodestar.lodestone.systems.particle.world.behaviors.*;

import static com.sammy.malum.common.item.curiosities.weapons.staff.ErosionScepterItem.SCEPTER_COLOR_DATA;

public class DrainingBolt extends AbstractStaffBoltProjectile {

    public static final MalumNetworkedParticleEffectColorData COLOR = new MalumNetworkedParticleEffectColorData(SCEPTER_COLOR_DATA);

    public DrainingBolt(Level level) {
        super(MalumEntities.DRAINING_BOLT.get(), level);
    }

    @Override
    public void onHit(LivingEntity target) {
        if (target.getRandom().nextFloat() < 0.3f) {
            var effect = target.getEffect(MalumMobEffects.SILENCED);
            if (effect == null) {
                target.addEffect(new MobEffectInstance(MalumMobEffects.SILENCED, 150, 0, true, true, true));
            } else {
                EntityHelper.amplifyEffect(effect, target, 1, 19);
                EntityHelper.extendEffect(effect, target, 15, 300);
            }
        }
    }

    @Override
    public void playSound(@NotNull SoundEvent pSound, float pVolume, float pPitch) {
        super.playSound(pSound, pVolume, pPitch-0.2f);
        super.playSound(MalumSoundEvents.DRAINING_MOTIF.get(), pVolume, pPitch-0.1f);
    }

    @Override
    public int getMaxAge() {
        return 30;
    }

    @Override
    public float getOrbitingTrailDistance() {
        return 0.5f;
    }

    @Override
    public Pair<BoltImpactParticleEffect, MalumNetworkedParticleEffectColorData> getImpactParticleEffect() {
        return Pair.of(MalumParticleEffectTypes.DRAINING_BOLT_IMPACT, COLOR);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void spawnParticles() {
        Level level = level();
        Vec3 position = position();
        float scalar = getVisualEffectScalar();
        Vec3 norm = getDeltaMovement().normalize().scale(0.05f);
        var lightSpecs = SpiritLightSpecs.spiritLightSpecs(level, position, SCEPTER_COLOR_DATA);
        lightSpecs.getBuilder()
                .setRenderType(LodestoneWorldParticleRenderType.LUMITRANSPARENT)
                .setRenderTarget(LodestoneRenderHandler.LATE_DEFERRED_RENDER)
                .multiplyLifetime(1.5f)
                .setMotion(norm);
        lightSpecs.getBloomBuilder()
                .setRenderType(LodestoneWorldParticleRenderType.LUMITRANSPARENT)
                .setRenderTarget(LodestoneRenderHandler.LATE_DEFERRED_RENDER)
                .multiplyLifetime(1.5f)
                .setMotion(norm);
        lightSpecs.spawnParticles();
        WorldParticleBuilder.create(MalumParticles.SAW)
                .setBehavior(DirectionalParticleBehavior.directional(getDeltaMovement().normalize()))
                .setTransparencyData(GenericParticleData.create(0.4f * scalar, 0.2f * scalar, 0f).setEasing(Easing.SINE_IN_OUT, Easing.SINE_IN).build())
                .setSpinData(SpinParticleData.createRandomDirection(random, RandomHelper.randomBetween(random, 0.25f, 0.5f)).randomSpinOffset(random).build())
                .setScaleData(GenericParticleData.create(0.3f * scalar, 0.1f * scalar).setEasing(Easing.SINE_IN_OUT).build())
                .setRenderTarget(LodestoneRenderHandler.LATE_DEFERRED_RENDER)
                .setLifetime(Math.min(6 + age * 3, 24))
                .setColorData(SCEPTER_COLOR_DATA)
                .setFriction(0.95f)
                .enableForcedSpawn()
                .enableNoClip()
                .spawn(level, position.x, position.y, position.z)
                .setTransparencyData(GenericParticleData.create(0.9f * scalar, 0.4f * scalar, 0f).setEasing(Easing.SINE_IN_OUT, Easing.SINE_IN).build())
                .setRenderType(LodestoneWorldParticleRenderType.LUMITRANSPARENT)
                .spawn(level, position.x, position.y, position.z);
    }
}