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

public class WeightOfWorldsCritParticleEffect extends WeaponParticleEffectType {

    public WeightOfWorldsCritParticleEffect(String id) {
        super(id);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void spawnParticles(Level level, RandomSource random, NetworkedParticleEffectPositionData positionData, NetworkedParticleEffectColorData colorData, NetworkedParticleEffectExtraData nbtData, Vec3 direction, float angle, boolean mirror, float spinOffset) {
        for (int i = 0; i < 8; i++) {
            if (i % 2 == 0) {
                spinOffset = angle + RandomHelper.randomBetween(random, -0.5f, 0.5f) + (mirror ? 3.14f : 0);
            }
            var slash = WeaponParticleEffects.spawnSlashParticle(level, positionData.getAsVector(), ParticleRegistry.SLASH, colorData);
            slash.getBuilder()
                    .setSpinData(SpinParticleData.create(0).setSpinOffset(spinOffset).build())
                    .setScaleData(GenericParticleData.create(RandomHelper.randomBetween(random, 3f, 4f)).build())
                    .setMotion(direction.scale(RandomHelper.randomBetween(random, 0.3f, 0.4f)))
                    .setBehavior(PointyDirectionalParticleBehavior.pointyDirectional(direction));
            slash.spawnParticles();
        }
    }
}