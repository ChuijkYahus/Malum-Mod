package com.sammy.malum.visual_effects.networked.attack;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import com.sammy.malum.registry.client.*;
import com.sammy.malum.visual_effects.*;
import com.sammy.malum.visual_effects.networked.*;
import com.sammy.malum.visual_effects.networked.gluttony.*;
import io.netty.buffer.*;
import net.minecraft.network.codec.*;
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

import java.util.*;

public class SunderingAnchorSlashParticleEffect extends MalumNetworkedWeaponParticleEffectType<SunderingAnchorSlashParticleEffect.SunderingAnchorSlashEffectData> {

    public static class SunderingAnchorSlashEffectData extends WeaponParticleEffectData {
        public static final Codec<SunderingAnchorSlashEffectData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Vec3.CODEC.fieldOf("direction").forGetter(SunderingAnchorSlashEffectData::getDirection),
                Codec.BOOL.fieldOf("mirror").forGetter(SunderingAnchorSlashEffectData::isMirrored),
                Codec.FLOAT.fieldOf("slashRotation").forGetter(SunderingAnchorSlashEffectData::getSlashRotation),
                Codec.INT.fieldOf("slashCount").forGetter(SunderingAnchorSlashEffectData::slashCount)
        ).apply(instance, SunderingAnchorSlashEffectData::new));

        public static final StreamCodec<ByteBuf, SunderingAnchorSlashEffectData> STREAM_CODEC = ByteBufCodecs.fromCodec(CODEC);

        public final int slashCount;

        public SunderingAnchorSlashEffectData(Vec3 direction, boolean isMirrored, float slashRotation, int slashCount) {
            super(direction, isMirrored, slashRotation);
            this.slashCount = slashCount;
        }
        public SunderingAnchorSlashEffectData(int slashCount) {
            super(Vec3.ZERO, false, 0);
            this.slashCount = slashCount;
        }

        public int slashCount() {
            return slashCount;
        }

        @Override
        public WeaponParticleEffectData modify(Vec3 direction, boolean isMirrored, float slashRotation) {
            return new SunderingAnchorSlashEffectData(direction, isMirrored, slashRotation, slashCount);
        }
    }

    public SunderingAnchorSlashParticleEffect(String id) {
        super(id);
    }

    @Override
    public Optional<StreamCodec<ByteBuf, ? extends NetworkedParticleEffectExtraData>> getExtraCodec() {
        return Optional.of(SunderingAnchorSlashEffectData.STREAM_CODEC);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void act(Level level, RandomSource random, NetworkedParticleEffectPositionData positionData, MalumNetworkedParticleEffectColorData colorData, SunderingAnchorSlashEffectData extraData) {
        int slashCount = extraData.slashCount;
        for (int i = 0; i < slashCount; i++) {
            float spinOffset = extraData.getSlashRotation() + RandomHelper.randomBetween(random, -3.14f, 3.14f) + (extraData.isMirrored() ? 3.14f : 0);
            var direction = extraData.getDirection();
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