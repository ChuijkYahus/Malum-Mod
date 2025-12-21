package com.sammy.malum.visual_effects.networked.cultist;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.sammy.malum.common.entity.cultist.EntropyChargeProjectile;
import com.sammy.malum.common.entity.cultist.cardinal.CardinalCultist;
import com.sammy.malum.visual_effects.CultistParticleEffects;
import com.sammy.malum.visual_effects.networked.MalumNetworkedParticleEffectColorData;
import com.sammy.malum.visual_effects.networked.MalumNetworkedParticleEffectType;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import team.lodestar.lodestone.systems.network.particle.NetworkedParticleEffectExtraData;
import team.lodestar.lodestone.systems.network.particle.NetworkedParticleEffectPositionData;

import java.util.Optional;

public class CardinalTriggerDetonationParticleEffect extends MalumNetworkedParticleEffectType<CardinalTriggerDetonationParticleEffect.CardinalTriggerDetonationParticleData> {

    public record CardinalTriggerDetonationParticleData(int cardinalId, int entropyChargeId) implements NetworkedParticleEffectExtraData {
        public static final Codec<CardinalTriggerDetonationParticleData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Codec.INT.fieldOf("cardinalId").forGetter(CardinalTriggerDetonationParticleData::cardinalId),
                Codec.INT.fieldOf("entropyChargeId").forGetter(CardinalTriggerDetonationParticleData::entropyChargeId)
        ).apply(instance, CardinalTriggerDetonationParticleData::new));

        public static final StreamCodec<ByteBuf, CardinalTriggerDetonationParticleData> STREAM_CODEC = ByteBufCodecs.fromCodec(CODEC);

    }

    public CardinalTriggerDetonationParticleEffect(String id) {
        super(id);
    }

    @Override
    public Optional<StreamCodec<ByteBuf, ? extends NetworkedParticleEffectExtraData>> getExtraCodec() {
        return Optional.of(CardinalTriggerDetonationParticleData.STREAM_CODEC);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void act(Level level, RandomSource random, NetworkedParticleEffectPositionData positionData, MalumNetworkedParticleEffectColorData colorData, CardinalTriggerDetonationParticleData extraData) {
        if (level.getEntity(extraData.cardinalId) instanceof CardinalCultist cultist) {
            if (level.getEntity(extraData.entropyChargeId) instanceof EntropyChargeProjectile entropyCharge) {
                CultistParticleEffects.cardinalTriggersEntropyChargeDetonation(level, positionData, colorData, cultist, entropyCharge);

            }
        }
    }
}