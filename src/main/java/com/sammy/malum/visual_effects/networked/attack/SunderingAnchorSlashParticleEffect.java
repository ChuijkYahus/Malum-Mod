package com.sammy.malum.visual_effects.networked.attack;

import com.sammy.malum.registry.client.*;
import com.sammy.malum.visual_effects.*;
import net.minecraft.util.RandomSource;
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

public class SunderingAnchorSlashParticleEffect extends WeaponParticleEffectType {

    public SunderingAnchorSlashParticleEffect(String id) {
        super(id);
    }

    public static NetworkedParticleEffectExtraData createData(Vec3 direction, boolean mirror, float angle, int slashCount) {
        var data = SlashAttackParticleEffect.createData(direction, mirror, angle);
        data.compoundTag.putInt("slashCount", slashCount);
        return data;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void spawnParticles(Level level, RandomSource random, NetworkedParticleEffectPositionData positionData, NetworkedParticleEffectColorData colorData, NetworkedParticleEffectExtraData nbtData, Vec3 direction, float angle, boolean mirror, float spinOffset) {
        int slashCount = nbtData.compoundTag.getInt("slashCount");

        for (int i = 0; i < slashCount; i++) {
            spinOffset = angle + RandomHelper.randomBetween(random, -3.14f, 3.14f) + (mirror ? 3.14f : 0);
            for (int j = 0; j < 2; j++) {
                var slash = WeaponParticleEffects.spawnSlashParticle(level, positionData.getAsVector(), ParticleRegistry.THIN_SLASH, colorData);
                int lifeDelay = (i+j) * 2;
                slash.getBuilder()
                        .setSpinData(SpinParticleData.create(0).setSpinOffset(spinOffset).build())
                        .setScaleData(GenericParticleData.create(RandomHelper.randomBetween(random, 1f, 2f)).build())
                        .setMotion(direction.scale(RandomHelper.randomBetween(random, 0.8f, 1.3f)))
                        .setLifeDelay(lifeDelay)
                        .setLifetime(4)
                        .setBehavior(PointyDirectionalParticleBehavior.pointyDirectional(direction));
                slash.spawnParticles();
            }
        }
    }
}