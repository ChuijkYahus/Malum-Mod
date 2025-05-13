package com.sammy.malum.visual_effects.networked.attack;

import com.sammy.malum.registry.client.*;
import com.sammy.malum.visual_effects.*;
import com.sammy.malum.visual_effects.networked.*;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import team.lodestar.lodestone.systems.network.*;
import net.neoforged.api.distmarker.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.systems.network.particle.NetworkedParticleEffectPositionData;
import team.lodestar.lodestone.systems.particle.data.*;
import team.lodestar.lodestone.systems.particle.data.spin.*;
import team.lodestar.lodestone.systems.particle.world.behaviors.*;

public class StaffSlamAttackParticleEffect extends MalumNetworkedWeaponParticleEffectType<WeaponParticleEffectType.WeaponParticleEffectData> {

    public StaffSlamAttackParticleEffect(String id) {
        super(id);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void act(Level level, RandomSource random, NetworkedParticleEffectPositionData positionData, MalumNetworkedParticleEffectColorData colorData, WeaponParticleEffectData extraData) {
        var slam = WeaponParticleEffects.spawnSlamParticle(level, positionData.getAsVector(), ParticleRegistry.SLAM, colorData);
        var direction = extraData.getDirection();
        slam.getBuilder()
                .setSpinData(SpinParticleData.create(0).setSpinOffset(extraData.getSlashRotation()).build())
                .setScaleData(GenericParticleData.create(RandomHelper.randomBetween(random, 1f, 2f)).build())
                .setMotion(direction.scale(RandomHelper.randomBetween(random, 0.6f, 0.8f)))
                .setBehavior(DirectionalParticleBehavior.directional(direction));
        slam.spawnParticles();
    }
}