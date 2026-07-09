package com.sammy.malum.common.payloads.waveform;

import com.sammy.malum.common.block.curiosities.artifice.waveform.SpiritDiodeConfigurationInfo;
import io.netty.buffer.*;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.*;

public class SpiritDiodeStateUpdatePayload extends OpenBlockEntityStateUpdatePayload<SpiritDiodeConfigurationInfo> {

    public SpiritDiodeStateUpdatePayload(BlockPos pos, boolean isOpen, SpiritDiodeConfigurationInfo info) {
        super(pos, isOpen, info);
    }

    public SpiritDiodeStateUpdatePayload(RegistryFriendlyByteBuf buf) {
        super(buf);
    }

    @Override
    public StreamCodec<ByteBuf, SpiritDiodeConfigurationInfo> getInfoCodec() {
        return SpiritDiodeConfigurationInfo.STREAM_CODEC;
    }
}