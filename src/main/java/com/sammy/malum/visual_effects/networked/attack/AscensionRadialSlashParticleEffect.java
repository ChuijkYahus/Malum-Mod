package com.sammy.malum.visual_effects.networked.attack;

import com.sammy.malum.registry.client.*;
import com.sammy.malum.visual_effects.*;
import net.minecraft.util.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.*;
import net.neoforged.api.distmarker.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.systems.network.WeaponParticleEffectType;
import team.lodestar.lodestone.systems.network.particle.NetworkedParticleEffectColorData;
import team.lodestar.lodestone.systems.network.particle.NetworkedParticleEffectExtraData;
import team.lodestar.lodestone.systems.network.particle.NetworkedParticleEffectPositionData;
import team.lodestar.lodestone.systems.particle.data.*;
import team.lodestar.lodestone.systems.particle.data.spin.*;
import team.lodestar.lodestone.systems.particle.world.behaviors.*;

public class AscensionRadialSlashParticleEffect extends WeaponParticleEffectType {

    public AscensionRadialSlashParticleEffect(String id) {
        super(id);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void spawnParticles(Level level, RandomSource random, NetworkedParticleEffectPositionData positionData, NetworkedParticleEffectColorData colorData, NetworkedParticleEffectExtraData nbtData, Vec3 direction, float angle, boolean mirror, float spinOffset) {
        float yRot = ((float) (Mth.atan2(direction.x, direction.z) * (double) (180F / (float) Math.PI)));
        float yaw = (float) Math.toRadians(yRot);
        Vec3 left = new Vec3(-Math.cos(yaw), 0, Math.sin(yaw));
        for(int i = 0; i < 3; i++) {
            final Vec3 pos = positionData.getAsVector();
            for (int j = 0; j < 16; j++) {
                spinOffset = angle + RandomHelper.randomBetween(random, -0.5f, 0.5f) + (mirror ? 3.14f : 0);
                float slashAngle = (i*0.33f+j) / 16f * (float) Math.PI * 2f;
                var slashDirection = left.scale(Math.sin(slashAngle))
                        .add(direction.scale(Math.cos(slashAngle)))
                        .normalize();
                var slashPosition = pos.add(slashDirection.scale(0.14f));

                var slash = WeaponParticleEffects.spawnSlashParticle(level, slashPosition, ParticleRegistry.ROUNDABOUT_SLASH, colorData);
                slash.getBuilder()
                        .setSpinData(SpinParticleData.create(0).setSpinOffset(spinOffset).build())
                        .setScaleData(GenericParticleData.create(RandomHelper.randomBetween(random, 2.5f, 5f)).build())
                        .setMotion(slashDirection.scale(RandomHelper.randomBetween(random, 0.05f, 0.2f)).add(0, 0.2f, 0))
                        .setLifetime(12+i)
                        .setLifeDelay(i+j/4)
                        .setBehavior(PointyDirectionalParticleBehavior.pointyDirectional(slashDirection));
                slash.spawnParticles();
            }
            var slash = WeaponParticleEffects.spawnSlashParticle(level, pos, ParticleRegistry.ROUNDABOUT_SLASH, colorData);
            slash.getBuilder()
                    .setSpinData(SpinParticleData.create(0).setSpinOffset(i * 1.57f).build())
                    .setScaleData(GenericParticleData.create(RandomHelper.randomBetween(random, 4.5f, 5f)).build())
                    .setLifetime(12)
                    .setLifeDelay(i*2)
                    .setBehavior(DirectionalParticleBehavior.directional(new Vec3(0, 1, 0)));
            slash.spawnParticles();
        }
    }
}