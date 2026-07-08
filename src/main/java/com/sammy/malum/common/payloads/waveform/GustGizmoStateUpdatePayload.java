package com.sammy.malum.common.payloads.waveform;

import com.sammy.malum.common.block.curiosities.artifice.elemental_artifice.ElementalArtificeTinkeringInfo;
import io.netty.buffer.*;
import net.minecraft.core.*;
import net.minecraft.network.*;
import net.minecraft.network.codec.*;

public class GustGizmoStateUpdatePayload extends OpenBlockEntityStateUpdatePayload<ElementalArtificeTinkeringInfo> {

    public GustGizmoStateUpdatePayload(BlockPos pos, boolean isOpen, ElementalArtificeTinkeringInfo info) {
        super(pos, isOpen, info);
    }

    public GustGizmoStateUpdatePayload(RegistryFriendlyByteBuf buf) {
        super(buf);
    }

    @Override
    public StreamCodec<ByteBuf, ElementalArtificeTinkeringInfo> getInfoCodec() {
        return ElementalArtificeTinkeringInfo.STREAM_CODEC;
    }
}