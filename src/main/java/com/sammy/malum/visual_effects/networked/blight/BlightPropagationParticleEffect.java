package com.sammy.malum.visual_effects.networked.blight;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import com.sammy.malum.common.block.curiosities.soul_brazier.*;
import com.sammy.malum.visual_effects.*;
import com.sammy.malum.visual_effects.networked.*;
import io.netty.buffer.*;
import net.minecraft.core.*;
import net.minecraft.network.codec.*;
import net.minecraft.util.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.*;
import net.neoforged.api.distmarker.*;
import team.lodestar.lodestone.systems.network.particle.*;

import java.util.*;

public class BlightPropagationParticleEffect extends MalumNetworkedParticleEffectType<BlightPropagationParticleEffect.BlightPropagationEffectData> {

    public record BlightPropagationEffectData(BlockPos sourcePos) implements NetworkedParticleEffectExtraData {
        public static final Codec<BlightPropagationEffectData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                BlockPos.CODEC.fieldOf("sourcePos").forGetter(BlightPropagationEffectData::sourcePos)
        ).apply(instance, BlightPropagationEffectData::new));

        public static final StreamCodec<ByteBuf, BlightPropagationEffectData> STREAM_CODEC = ByteBufCodecs.fromCodec(CODEC);
    }

    public BlightPropagationParticleEffect(String id) {
        super(id);
    }

    @Override
    public Optional<StreamCodec<ByteBuf, ? extends NetworkedParticleEffectExtraData>> getExtraCodec() {
        return Optional.of(BlightPropagationEffectData.STREAM_CODEC);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void act(Level level, RandomSource random, NetworkedParticleEffectPositionData positionData, MalumNetworkedParticleEffectColorData colorData, BlightPropagationEffectData extraData) {
        BlightParticleEffects.blightSpreads(positionData, colorData, extraData);
    }
}