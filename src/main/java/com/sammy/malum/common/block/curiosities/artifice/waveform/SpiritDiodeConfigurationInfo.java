package com.sammy.malum.common.block.curiosities.artifice.waveform;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.sammy.malum.common.block.curiosities.artifice.ArtificeTinkeringInfo;
import com.sammy.malum.common.block.curiosities.artifice.RedstoneTimeIntervalType;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record SpiritDiodeConfigurationInfo(RedstoneTimeIntervalType type,
                                           int frequency) implements ArtificeTinkeringInfo {
    public static final Codec<SpiritDiodeConfigurationInfo> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            RedstoneTimeIntervalType.CODEC.fieldOf("type").forGetter(SpiritDiodeConfigurationInfo::type),
            Codec.INT.fieldOf("frequency").forGetter(SpiritDiodeConfigurationInfo::frequency)
    ).apply(instance, SpiritDiodeConfigurationInfo::new));

    public static StreamCodec<ByteBuf, SpiritDiodeConfigurationInfo> STREAM_CODEC = ByteBufCodecs.fromCodec(SpiritDiodeConfigurationInfo.CODEC);

}
