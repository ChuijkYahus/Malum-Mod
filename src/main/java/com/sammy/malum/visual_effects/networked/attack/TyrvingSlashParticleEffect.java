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
import team.lodestar.lodestone.systems.particle.*;
import team.lodestar.lodestone.systems.particle.data.*;
import team.lodestar.lodestone.systems.particle.data.color.*;
import team.lodestar.lodestone.systems.particle.data.spin.*;
import team.lodestar.lodestone.systems.particle.render_types.*;
import team.lodestar.lodestone.systems.particle.world.behaviors.*;

public class TyrvingSlashParticleEffect extends WeaponParticleEffectType {

    public TyrvingSlashParticleEffect(String id) {
        super(id);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void spawnParticles(Level level, RandomSource random, NetworkedParticleEffectPositionData positionData, NetworkedParticleEffectColorData colorData, NetworkedParticleEffectExtraData nbtData, Vec3 direction, float angle, boolean mirror, float spinOffset) {
        float offsetBase = RandomHelper.randomBetween(random, 0.4f, 0.8f) * (random.nextBoolean() ? 1 : -1) + (mirror ? 3.14f : 0);
        for (int i = 0; i < 8; i++) {
            ParticleEffectSpawner slash;
            if (i >= 6) {
                slash = WeaponParticleEffects.spawnSlashParticle(level, positionData.getAsVector(), ParticleRegistry.SLASH, ColorParticleData.create(0.15f, 0.05f, 0.1f).build());
                slash.getBuilder().setRenderType(LodestoneWorldParticleRenderType.TRANSPARENT);
            }
            else {
                slash = WeaponParticleEffects.spawnSlashParticle(level, positionData.getAsVector(), ParticleRegistry.SLASH, colorData);
            }
            spinOffset = angle + (i % 2 == 0 ? 1 : -1) * offsetBase;
            int lifeDelay = (i % 2 == 0 ? 3 : 0);
            slash.getBuilder()
                    .setSpinData(SpinParticleData.create(0).setSpinOffset(spinOffset).build())
                    .setScaleData(GenericParticleData.create(RandomHelper.randomBetween(random, 1f, 2f)).build())
                    .setMotion(direction.scale(RandomHelper.randomBetween(random, 0.5f, 0.7f)))
                    .setLifeDelay(lifeDelay)
                    .setBehavior(PointyDirectionalParticleBehavior.pointyDirectional(direction));
            slash.spawnParticles();
        }
    }
}