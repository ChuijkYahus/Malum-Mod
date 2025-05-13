package com.sammy.malum.visual_effects.networked.pylon;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import io.netty.buffer.*;
import net.minecraft.core.*;
import net.minecraft.network.codec.*;
import net.minecraft.world.item.*;
import team.lodestar.lodestone.systems.network.particle.*;

public record PylonEffectData(BlockPos holderPos, ItemStack stack) implements NetworkedParticleEffectExtraData {
    public static final Codec<PylonEffectData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BlockPos.CODEC.fieldOf("holderPos").forGetter(data -> data.holderPos),
            ItemStack.CODEC.fieldOf("stack").forGetter(data -> data.stack)
    ).apply(instance, PylonEffectData::new));

    public static final StreamCodec<ByteBuf, PylonEffectData> STREAM_CODEC = ByteBufCodecs.fromCodec(CODEC);
}