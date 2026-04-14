package com.sammy.malum.common.payloads.waveform;

import com.sammy.malum.common.block.curiosities.artifice.redstone.SpiritDiodeBlockEntity;
import io.netty.buffer.*;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.*;

public class SpiritDiodeStateUpdatePayload extends OpenBlockEntityStateUpdatePayload<SpiritDiodeBlockEntity.SpiritDiodeInfo> {

    public SpiritDiodeStateUpdatePayload(BlockPos pos, boolean isOpen, SpiritDiodeBlockEntity.SpiritDiodeInfo info) {
        super(pos, isOpen, info);
    }

    public SpiritDiodeStateUpdatePayload(RegistryFriendlyByteBuf buf) {
        super(buf);
    }

    @Override
    public StreamCodec<ByteBuf, SpiritDiodeBlockEntity.SpiritDiodeInfo> getInfoCodec() {
        return SpiritDiodeBlockEntity.SpiritDiodeInfo.STREAM_CODEC;
    }
}