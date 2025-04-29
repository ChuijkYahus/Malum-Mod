package com.sammy.malum.visual_effects.networked.geas;

import com.sammy.malum.registry.client.*;
import com.sammy.malum.visual_effects.*;
import com.sammy.malum.visual_effects.networked.*;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.*;
import net.neoforged.api.distmarker.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.systems.network.WeaponParticleEffectType.*;
import team.lodestar.lodestone.systems.network.particle.NetworkedParticleEffectPositionData;
import team.lodestar.lodestone.systems.particle.data.*;
import team.lodestar.lodestone.systems.particle.data.spin.*;
import team.lodestar.lodestone.systems.particle.world.behaviors.*;

public class ShakenFaithParticleEffect extends MalumNetworkedWeaponParticleEffectType<WeaponParticleEffectData> {

    public ShakenFaithParticleEffect(String id) {
        super(id);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void act(Level level, RandomSource random, NetworkedParticleEffectPositionData positionData, MalumNetworkedParticleEffectColorData colorData, WeaponParticleEffectData extraData) {
        final Vec3 pos = positionData.getAsVector();
        for (int i = 0; i < 6; i++) {
            var slash = WeaponParticleEffects.spawnSlashParticle(level, pos, ParticleRegistry.ROUNDABOUT_SLASH, colorData);
            slash.getBuilder()
                    .setSpinData(SpinParticleData.createRandomDirection(random, 0.15f - i * 0.025f, 0.02f).setSpinOffset(extraData.getSlashRotation()).build())
                    .setScaleData(GenericParticleData.create(RandomHelper.randomBetween(random, 0.5f, 1.5f)+i*0.25f, 0).build())
                    .setBehavior(PointyDirectionalParticleBehavior.pointyDirectional(extraData.getDirection()))
                    .setLifetime(RandomHelper.randomBetween(random, 8, 12))
                    .setLifeDelay(i/2);
            slash.spawnParticles();
            slash.getBuilder().setBehavior(BillboardParticleBehavior.INSTANCE);
            slash.spawnParticles();
        }
    }
}