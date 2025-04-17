package com.sammy.malum.visual_effects.networked.geas;

import com.sammy.malum.registry.client.*;
import com.sammy.malum.visual_effects.*;
import com.sammy.malum.visual_effects.networked.*;
import com.sammy.malum.visual_effects.networked.attack.slash.*;
import com.sammy.malum.visual_effects.networked.data.*;
import net.minecraft.nbt.*;
import net.minecraft.world.phys.*;
import net.neoforged.api.distmarker.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.systems.easing.*;
import team.lodestar.lodestone.systems.particle.*;
import team.lodestar.lodestone.systems.particle.builder.*;
import team.lodestar.lodestone.systems.particle.data.*;
import team.lodestar.lodestone.systems.particle.data.spin.*;
import team.lodestar.lodestone.systems.particle.world.behaviors.*;

import java.util.function.*;

import static net.minecraft.util.Mth.nextFloat;

public class ShakenFaithParticleEffect extends SlashAttackParticleEffect {

    public ShakenFaithParticleEffect(String id) {
        super(id);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public Supplier<ParticleEffectActor> get() {
        return () -> (level, random, positionData, colorData, nbtData) -> {
            if (!nbtData.compoundTag.contains("direction")) {
                return;
            }
            final CompoundTag directionData = nbtData.compoundTag.getCompound("direction");
            double dirX = directionData.getDouble("x");
            double dirY = directionData.getDouble("y");
            double dirZ = directionData.getDouble("z");
            Vec3 direction = new Vec3(dirX, dirY, dirZ);
            float angle = nbtData.compoundTag.getFloat("angle");
            boolean mirror = nbtData.compoundTag.getBoolean("mirror");

            final Vec3 pos = positionData.getAsVector();
            for (int i = 0; i < 6; i++) {
                float spinOffset = angle + RandomHelper.randomBetween(random, -1.5f, 1.5f) + (mirror ? 3.14f : 0);
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
        };
    }
}