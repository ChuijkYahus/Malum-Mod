package com.sammy.malum.visual_effects.networked.attack;

import com.sammy.malum.registry.client.*;
import com.sammy.malum.visual_effects.*;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import team.lodestar.lodestone.systems.network.*;
import net.minecraft.world.phys.*;
import net.neoforged.api.distmarker.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.systems.network.particle.NetworkedParticleEffectColorData;
import team.lodestar.lodestone.systems.network.particle.NetworkedParticleEffectExtraData;
import team.lodestar.lodestone.systems.network.particle.NetworkedParticleEffectPositionData;
import team.lodestar.lodestone.systems.particle.data.*;
import team.lodestar.lodestone.systems.particle.data.spin.*;
import team.lodestar.lodestone.systems.particle.world.behaviors.*;

public class SlashAttackParticleEffect extends WeaponParticleEffectType {

    public SlashAttackParticleEffect(String id) {
        super(id);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void spawnParticles(Level level, RandomSource random, NetworkedParticleEffectPositionData positionData, NetworkedParticleEffectColorData colorData, NetworkedParticleEffectExtraData nbtData, Vec3 direction, float angle, boolean mirror, float spinOffset) {
        var slash = WeaponParticleEffects.spawnSlashParticle(level, positionData.getAsVector(), ParticleRegistry.SLASH, colorData);
        slash.getBuilder()
                .setSpinData(SpinParticleData.create(0).setSpinOffset(spinOffset).build())
                .setScaleData(GenericParticleData.create(RandomHelper.randomBetween(random, 2f, 3f)).build())
                .setMotion(direction.scale(RandomHelper.randomBetween(random, 0.3f, 0.4f)))
                .setBehavior(PointyDirectionalParticleBehavior.pointyDirectional(direction));
        slash.spawnParticles();
    }
}