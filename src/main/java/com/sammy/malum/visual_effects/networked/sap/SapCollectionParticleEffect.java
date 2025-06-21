package com.sammy.malum.visual_effects.networked.sap;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.sammy.malum.visual_effects.networked.MalumNetworkedParticleEffectColorData;
import com.sammy.malum.visual_effects.networked.MalumNetworkedParticleEffectType;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.level.Level;
import net.minecraft.core.*;
import net.minecraft.util.*;
import net.minecraft.world.phys.*;
import net.neoforged.api.distmarker.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.systems.network.particle.NetworkedParticleEffectExtraData;
import team.lodestar.lodestone.systems.network.particle.NetworkedParticleEffectPositionData;
import team.lodestar.lodestone.systems.particle.world.*;

import java.util.*;
import java.util.function.*;

import static com.sammy.malum.visual_effects.SpiritLightSpecs.spiritLightSpecs;

public class SapCollectionParticleEffect extends MalumNetworkedParticleEffectType<SapCollectionParticleEffect.SapCollectionEffectData> {

    public record SapCollectionEffectData(Direction direction, UUID playerUUID) implements NetworkedParticleEffectExtraData {
        public static final Codec<SapCollectionEffectData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Direction.CODEC.fieldOf("direction").forGetter(SapCollectionEffectData::direction),
                UUIDUtil.CODEC.fieldOf("playerUUID").forGetter(SapCollectionEffectData::playerUUID)
        ).apply(instance, SapCollectionEffectData::new));

        public static final StreamCodec<ByteBuf, SapCollectionEffectData> STREAM_CODEC = ByteBufCodecs.fromCodec(CODEC);
    }

    public SapCollectionParticleEffect(String id) {
        super(id);
    }

    @Override
    public Optional<StreamCodec<ByteBuf, ? extends NetworkedParticleEffectExtraData>> getExtraCodec() {
        return Optional.of(SapCollectionEffectData.STREAM_CODEC);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void act(Level level, RandomSource random, NetworkedParticleEffectPositionData positionData, MalumNetworkedParticleEffectColorData colorData, SapCollectionParticleEffect.SapCollectionEffectData extraData) {
        var direction = extraData.direction;
        var player = level.getPlayerByUUID(extraData.playerUUID);
        BlockPos blockPos = positionData.getAsBlockPos();
        Vec3 pos = blockPos.getCenter().relative(direction, 0.5f);
        final Vec3i normal = direction.getNormal();
        float yRot = ((float) (Mth.atan2(normal.getX(), normal.getZ()) * (double) (180F / (float) Math.PI)));
        float yaw = (float) Math.toRadians(yRot);
        Vec3 left = new Vec3(-Math.cos(yaw), 0, Math.sin(yaw));
        Vec3 up = left.cross(new Vec3(normal.getX(), normal.getY(), normal.getZ()));
        final Consumer<LodestoneWorldParticle> acceleration = p -> p.setParticleSpeed(p.getParticleSpeed().scale(1.2f));
        for (int i = 0; i < 12; i++) {
            final float leftOffset = (random.nextFloat() - 0.5f) * 0.75f;
            final float upOffset = (random.nextFloat() - 0.5f) * 0.75f;
            Vec3 particlePosition = pos.add(left.scale(leftOffset)).add(up.scale(upOffset));
            Vec3 particleMotion = player.position().add(0, player.getBbHeight()/2f, 0).subtract(particlePosition).normalize();
            Vec3 targetPosition = pos.add(particleMotion.scale(0.75f));
            Vec3 actualMotion = targetPosition.subtract(particlePosition).normalize().scale(0.01f);
            var lightSpecs = spiritLightSpecs(level, particlePosition, colorData.getColor());
            lightSpecs.getBuilder().act(b -> b
                    .addTickActor(acceleration)
                    .setMotion(actualMotion)
                    .modifyScaleData(d -> d.multiplyValue(RandomHelper.randomBetween(random, 1f, 2f))));
            lightSpecs.getBloomBuilder().act(b -> b
                    .addTickActor(acceleration)
                    .setMotion(actualMotion)
                    .modifyScaleData(d -> d.multiplyValue(RandomHelper.randomBetween(random, 0.6f, 1.5f))));
            lightSpecs.spawnParticles();
        }
    }
}