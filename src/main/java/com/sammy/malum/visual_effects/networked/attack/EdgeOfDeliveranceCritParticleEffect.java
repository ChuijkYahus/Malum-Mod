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

public class EdgeOfDeliveranceCritParticleEffect extends MalumNetworkedWeaponParticleEffectType<WeaponParticleEffectType.WeaponParticleEffectData> {

    public EdgeOfDeliveranceCritParticleEffect(String id) {
        super(id);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void act(Level level, RandomSource random, NetworkedParticleEffectPositionData positionData, MalumNetworkedParticleEffectColorData colorData, WeaponParticleEffectData extraData) {
        float spinOffset = extraData.getSlashRotation() + RandomHelper.randomBetween(random, -0.5f, 0.5f) + (extraData.isMirrored() ? 3.14f : 0);
        var direction = extraData.getDirection();
        for (int i = 0; i < 4; i++) {
            var slash = WeaponParticleEffects.spawnSlashParticle(level, positionData.getAsVector(), ParticleRegistry.SLASH, colorData);
            slash.getBuilder()
                    .setSpinData(SpinParticleData.create(0).setSpinOffset(spinOffset).build())
                    .setScaleData(GenericParticleData.create(RandomHelper.randomBetween(random, 1.5f, 2f)).build())
                    .setMotion(direction.scale(RandomHelper.randomBetween(random, 0.6f, 0.8f)))
                    .setLifetime(3)
                    .setBehavior(PointyDirectionalParticleBehavior.pointyDirectional(direction));
            slash.spawnParticles();
        }
    }
}