package com.sammy.malum.visual_effects.networked.attack;

import com.sammy.malum.registry.client.*;
import com.sammy.malum.visual_effects.*;
import net.minecraft.util.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.systems.network.WeaponParticleEffectType;
import team.lodestar.lodestone.systems.network.particle.NetworkedParticleEffectColorData;
import team.lodestar.lodestone.systems.network.particle.NetworkedParticleEffectExtraData;
import team.lodestar.lodestone.systems.network.particle.NetworkedParticleEffectPositionData;
import team.lodestar.lodestone.systems.particle.*;
import team.lodestar.lodestone.systems.particle.data.*;
import team.lodestar.lodestone.systems.particle.data.spin.*;
import team.lodestar.lodestone.systems.particle.world.behaviors.*;

public class SunderingAnchorSweepParticleEffect extends WeaponParticleEffectType {

    public SunderingAnchorSweepParticleEffect(String id) {
        super(id);
    }

    @Override
    public void spawnParticles(Level level, RandomSource random, NetworkedParticleEffectPositionData positionData, NetworkedParticleEffectColorData colorData, NetworkedParticleEffectExtraData nbtData, Vec3 direction, float angle, boolean mirror, float spinOffset) {
        angle = random.nextFloat() * 6.28f;
        float x = Mth.sin(angle);
        float z = Mth.cos(angle);
        direction = new Vec3(x, 0, z);

        ParticleEffectSpawner slash = WeaponParticleEffects.spawnSlashParticle(level, positionData.getAsVector(), random.nextBoolean() ? ParticleRegistry.THIN_ROUNDABOUT_SLASH : ParticleRegistry.ROUNDABOUT_SLASH, colorData);
        int lifetime = RandomHelper.randomBetween(random, 8, 12);
        int directionScalar = random.nextBoolean() ? -1 : 1;
        slash.getBuilder()
                .setSpinData(SpinParticleData.create(0).randomSpinOffset(random).build())
                .setLifetime(lifetime)
                .setScaleData(GenericParticleData.create(RandomHelper.randomBetween(random, 2f, 3f)).build())
                .setMotion(direction.scale(RandomHelper.randomBetween(random, 0.02f, 0.05f)))
                .setRandomOffset(0.5f)
                .setBehavior(PointyDirectionalParticleBehavior.pointyDirectional(direction.scale(directionScalar)));
        slash.spawnParticles();
        slash.getBuilder()
                .setTransparencyData(GenericParticleData.create(0.6f, 0.3f).build())
                .setScaleData(GenericParticleData.create(RandomHelper.randomBetween(random, 1.5f, 2f)).build())
                .setLifeDelay(lifetime);
        slash.spawnParticles();
        slash.getBuilder()
                .setTransparencyData(GenericParticleData.create(0.3f, 0f).build())
                .setScaleData(GenericParticleData.create(RandomHelper.randomBetween(random, 1.2f, 1.6f)).build())
                .setLifeDelay(lifetime*2);
        slash.spawnParticles();
    }
}