package com.sammy.malum.common.block.curiosities.artifice.soul_link;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.sammy.malum.common.block.curiosities.artifice.ArtificeTinkeringInfo;
import com.sammy.malum.common.block.curiosities.artifice.RedstoneTimeIntervalType;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record SoulLinkConfigurationInfo(float delta) implements ArtificeTinkeringInfo {
    public static final Codec<SoulLinkConfigurationInfo> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.FLOAT.fieldOf("delta").forGetter(SoulLinkConfigurationInfo::delta)
    ).apply(instance, SoulLinkConfigurationInfo::new));

    public static StreamCodec<ByteBuf, SoulLinkConfigurationInfo> STREAM_CODEC = ByteBufCodecs.fromCodec(SoulLinkConfigurationInfo.CODEC);

}
