package com.sammy.malum.visual_effects.networked.attack;

import com.sammy.malum.registry.client.*;
import com.sammy.malum.visual_effects.*;
import com.sammy.malum.visual_effects.networked.*;
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

public class HiddenBladeCounterParticleEffect extends MalumNetworkedWeaponParticleEffectType<WeaponParticleEffectType.WeaponParticleEffectData> {

    public HiddenBladeCounterParticleEffect(String id) {
        super(id);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void act(Level level, RandomSource random, NetworkedParticleEffectPositionData positionData, MalumNetworkedParticleEffectColorData colorData, WeaponParticleEffectData extraData) {
        final float maxBackwardsOffset = 1.5f;
        final float maxForwardsOffset = 4.5f;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 100; j++) {
                var direction = extraData.getDirection();
                float offsetBase = RandomHelper.randomBetween(random, 0.314f, 3.14f) * (random.nextBoolean() ? 1 : -1) + (extraData.isMirrored() ? 3.14f : 0);
                float spinOffset = extraData.getSlashRotation() + (j % 2 == 0 ? 1 : -1) * offsetBase;
                float scale = RandomHelper.randomBetween(random, 2f, 6f);
                int lifeDelay = (j % 2 == 0 ? 2 : 0) + i + j / 6;
                var position = positionData.getAsVector().add(direction.multiply(
                        RandomHelper.randomBetween(random, -maxBackwardsOffset, maxForwardsOffset),
                        RandomHelper.randomBetween(random, -maxBackwardsOffset, maxForwardsOffset),
                        RandomHelper.randomBetween(random, -maxBackwardsOffset, maxForwardsOffset)));
                var slash = WeaponParticleEffects.spawnSlashParticle(level, position, random.nextBoolean() ? ParticleRegistry.SLASH : ParticleRegistry.THIN_SLASH, colorData);
                slash.getBuilder()
                        .setSpinData(SpinParticleData.create(0).setSpinOffset(spinOffset).build())
                        .setScaleData(GenericParticleData.create(scale).build())
                        .setMotion(direction.scale(RandomHelper.randomBetween(random, 0.3f, 0.5f)))
                        .setLifeDelay(lifeDelay)
                        .setLifetime(2 + i)
                        .setBehavior(PointyDirectionalParticleBehavior.pointyDirectional(direction));
                slash.spawnParticles();
            }
        }
    }
}