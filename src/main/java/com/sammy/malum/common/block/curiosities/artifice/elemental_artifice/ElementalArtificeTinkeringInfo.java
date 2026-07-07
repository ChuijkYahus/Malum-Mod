package com.sammy.malum.common.block.curiosities.artifice.elemental_artifice;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.sammy.malum.common.block.curiosities.artifice.elemental_artifice.base.ElementalArtificeBlockEntity;
import com.sammy.malum.common.block.curiosities.artifice.ArtificeTinkeringInfo;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record ElementalArtificeTinkeringInfo(int strength,
                                             boolean modified) implements ArtificeTinkeringInfo {

    public static final Codec<ElementalArtificeTinkeringInfo> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("strength").forGetter(ElementalArtificeTinkeringInfo::strength),
            Codec.BOOL.fieldOf("modified").forGetter(ElementalArtificeTinkeringInfo::modified)
    ).apply(instance, ElementalArtificeTinkeringInfo::new));

    public static StreamCodec<ByteBuf, ElementalArtificeTinkeringInfo> STREAM_CODEC = ByteBufCodecs.fromCodec(ElementalArtificeTinkeringInfo.CODEC);
}
