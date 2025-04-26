package com.sammy.malum.visual_effects.networked.geas;

import com.sammy.malum.registry.client.*;
import com.sammy.malum.visual_effects.*;
import com.sammy.malum.visual_effects.networked.*;
import com.sammy.malum.visual_effects.networked.attack.slash.*;
import team.lodestar.lodestone.systems.network.*;
import net.minecraft.nbt.*;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.*;
import net.neoforged.api.distmarker.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.systems.easing.*;
import team.lodestar.lodestone.systems.network.WeaponParticleEffectType;
import team.lodestar.lodestone.systems.network.particle.NetworkedParticleEffectColorData;
import team.lodestar.lodestone.systems.network.particle.NetworkedParticleEffectExtraData;
import team.lodestar.lodestone.systems.network.particle.NetworkedParticleEffectPositionData;
import team.lodestar.lodestone.systems.particle.*;
import team.lodestar.lodestone.systems.particle.builder.*;
import team.lodestar.lodestone.systems.particle.data.*;
import team.lodestar.lodestone.systems.particle.data.spin.*;
import team.lodestar.lodestone.systems.particle.world.behaviors.*;

import java.util.function.*;

import static net.minecraft.util.Mth.nextFloat;

public class ShakenFaithParticleEffect extends WeaponParticleEffectType {

    public ShakenFaithParticleEffect(String id) {
        super(id);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void spawnParticles(Level level, RandomSource random, NetworkedParticleEffectPositionData positionData, NetworkedParticleEffectColorData colorData, NetworkedParticleEffectExtraData nbtData, Vec3 direction, float angle, boolean mirror, float spinOffset) {
        final Vec3 pos = positionData.getAsVector();
        for (int i = 0; i < 6; i++) {
            var slash = WeaponParticleEffects.spawnSlashParticle(level, pos, ParticleRegistry.ROUNDABOUT_SLASH, colorData);
            slash.getBuilder()
                    .setSpinData(SpinParticleData.createRandomDirection(random, 0.15f - i * 0.025f, 0.02f).setSpinOffset(spinOffset).build())
                    .setScaleData(GenericParticleData.create(RandomHelper.randomBetween(random, 0.5f, 1.5f)+i*0.25f, 0).build())
                    .setBehavior(PointyDirectionalParticleBehavior.pointyDirectional(direction))
                    .setLifetime(RandomHelper.randomBetween(random, 8, 12))
                    .setLifeDelay(i/2);
            slash.spawnParticles();
            slash.getBuilder().setBehavior(BillboardParticleBehavior.INSTANCE);
            slash.spawnParticles();
        }
    }
}