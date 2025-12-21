package com.sammy.malum.visual_effects.networked.cultist;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.sammy.malum.common.entity.cultist.cardinal.CardinalCultist;
import com.sammy.malum.visual_effects.CultistParticleEffects;
import com.sammy.malum.visual_effects.networked.MalumNetworkedParticleEffectColorData;
import com.sammy.malum.visual_effects.networked.MalumNetworkedParticleEffectType;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import team.lodestar.lodestone.systems.network.particle.NetworkedParticleEffectExtraData;
import team.lodestar.lodestone.systems.network.particle.NetworkedParticleEffectPositionData;

import java.util.Optional;

public class CardinalFireRetaliationBlastParticleEffect extends MalumNetworkedParticleEffectType<CardinalFireRetaliationBlastParticleEffect.CardinalFireRetaliationBlastParticleData> {

    public record CardinalFireRetaliationBlastParticleData(int cardinalId, Vec3 direction) implements NetworkedParticleEffectExtraData {
        public static final Codec<CardinalFireRetaliationBlastParticleData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Codec.INT.fieldOf("cardinalId").forGetter(CardinalFireRetaliationBlastParticleData::cardinalId),
                Vec3.CODEC.fieldOf("direction").forGetter(CardinalFireRetaliationBlastParticleData::direction)
        ).apply(instance, CardinalFireRetaliationBlastParticleData::new));

        public static final StreamCodec<ByteBuf, CardinalFireRetaliationBlastParticleData> STREAM_CODEC = ByteBufCodecs.fromCodec(CODEC);

    }

    public CardinalFireRetaliationBlastParticleEffect(String id) {
        super(id);
    }

    @Override
    public Optional<StreamCodec<ByteBuf, ? extends NetworkedParticleEffectExtraData>> getExtraCodec() {
        return Optional.of(CardinalFireRetaliationBlastParticleData.STREAM_CODEC);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void act(Level level, RandomSource random, NetworkedParticleEffectPositionData positionData, MalumNetworkedParticleEffectColorData colorData, CardinalFireRetaliationBlastParticleData extraData) {
        if (level.getEntity(extraData.cardinalId) instanceof CardinalCultist cultist) {
            CultistParticleEffects.cardinalFiresRetaliationBlast(level, positionData, colorData, cultist, extraData.direction);
        }
    }
}