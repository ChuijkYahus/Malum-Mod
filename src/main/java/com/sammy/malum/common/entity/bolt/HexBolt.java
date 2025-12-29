package com.sammy.malum.common.entity.bolt;

import com.mojang.datafixers.util.Pair;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.entity.*;
import com.sammy.malum.registry.common.magic.*;
import com.sammy.malum.visual_effects.*;
import com.sammy.malum.visual_effects.networked.MalumNetworkedParticleEffectColorData;
import com.sammy.malum.visual_effects.networked.staff.*;
import net.minecraft.world.level.*;
import net.minecraft.world.phys.*;
import net.neoforged.api.distmarker.*;
import team.lodestar.lodestone.handlers.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.systems.easing.*;
import team.lodestar.lodestone.systems.particle.builder.*;
import team.lodestar.lodestone.systems.particle.data.*;
import team.lodestar.lodestone.systems.particle.data.color.ColorParticleData;
import team.lodestar.lodestone.systems.particle.data.spin.*;
import team.lodestar.lodestone.systems.particle.render_types.*;
import team.lodestar.lodestone.systems.particle.world.*;
import team.lodestar.lodestone.systems.particle.world.behaviors.*;

import java.util.function.*;

public class HexBolt extends AbstractStaffBoltProjectile {

    public static final MalumNetworkedParticleEffectColorData COLOR = new MalumNetworkedParticleEffectColorData(MalumSpiritTypes.WICKED_SPIRIT);
    public HexBolt(Level level) {
        super(MalumEntityTypes.HEX_BOLT.get(), level);
    }

    @Override
    public int getMaxAge() {
        return 40;
    }

    @Override
    public Pair<BoltImpactParticleEffect, MalumNetworkedParticleEffectColorData> getImpactParticleEffect() {
        return Pair.of(MalumParticleEffectTypes.HEX_BOLT_IMPACT, COLOR);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void spawnParticles() {
        Level level = level();
        Vec3 position = position();
        float scalar = getVisualEffectScalar();
        Vec3 norm = getDeltaMovement().normalize().scale(0.05f);
        var lightSpecs = SpiritLightSpecs.spiritLightSpecs(level, position, MalumSpiritTypes.WICKED_SPIRIT);
        lightSpecs.getBuilder()
                .setRenderTarget(LodestoneRenderHandler.LATE_DEFERRED_RENDER)
                .multiplyLifetime(1.25f)
                .setMotion(norm);
        lightSpecs.getBloomBuilder()
                .setRenderTarget(LodestoneRenderHandler.LATE_DEFERRED_RENDER)
                .multiplyLifetime(1.25f)
                .setMotion(norm);
        lightSpecs.spawnParticles();
        final SpinParticleData spinData = SpinParticleData.createRandomDirection(random, RandomHelper.randomBetween(random, 0.25f, 0.5f)).randomSpinOffset(random).build();
        final Consumer<LodestoneWorldParticle> behavior = p -> p.setParticleSpeed(p.getParticleSpeed().scale(0.95f));
        WorldParticleBuilder.create(MalumParticles.SAW)
                .setBehavior(DirectionalParticleBehavior.directional(getDeltaMovement().normalize()))
                .setTransparencyData(GenericParticleData.create(0.9f * scalar, 0.4f * scalar, 0f).setEasing(Easing.SINE_IN_OUT, Easing.SINE_IN).build())
                .setScaleData(GenericParticleData.create(0.4f * scalar, 0.3f * scalar).setEasing(Easing.SINE_IN_OUT).build())
                .setColorData(MalumSpiritTypes.WICKED_SPIRIT.createColorData().build())
                .setRenderTarget(LodestoneRenderHandler.LATE_DEFERRED_RENDER)
                .setLifetime(Math.min(6 + age * 3, 30))
                .addTickActor(behavior)
                .setSpinData(spinData)
                .enableForcedSpawn()
                .enableNoClip()
                .spawn(level, position.x, position.y, position.z)
                .setScaleData(GenericParticleData.create(0.5f * scalar, 0.3f * scalar).setEasing(Easing.SINE_IN_OUT).build())
                .setTransparencyData(GenericParticleData.create(0.4f * scalar, 0.2f * scalar, 0f).setEasing(Easing.SINE_IN_OUT, Easing.SINE_IN).build())
                .setRenderType(LodestoneWorldParticleRenderType.LUMITRANSPARENT)
                .setColorData(ColorParticleData.create(ColorHelper.darker(MalumSpiritTypes.WICKED_SPIRIT.getPrimaryColor(), 2)).build())
                .spawn(level, position.x, position.y, position.z);
    }
}