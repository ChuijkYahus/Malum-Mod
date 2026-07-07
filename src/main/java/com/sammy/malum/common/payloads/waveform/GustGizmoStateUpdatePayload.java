package com.sammy.malum.common.payloads.waveform;

import com.sammy.malum.common.block.curiosities.artifice.elemental_artifice.base.ElementalArtificeBlockEntity;
import io.netty.buffer.*;
import net.minecraft.core.*;
import net.minecraft.network.*;
import net.minecraft.network.codec.*;

public class GustGizmoStateUpdatePayload extends OpenBlockEntityStateUpdatePayload<ElementalArtificeBlockEntity.ElementalArtificeBlockConfigInfo> {

    public GustGizmoStateUpdatePayload(BlockPos pos, boolean isOpen, ElementalArtificeBlockEntity.ElementalArtificeBlockConfigInfo info) {
        super(pos, isOpen, info);
    }

    public GustGizmoStateUpdatePayload(RegistryFriendlyByteBuf buf) {
        super(buf);
    }

    @Override
    public StreamCodec<ByteBuf, ElementalArtificeBlockEntity.ElementalArtificeBlockConfigInfo> getInfoCodec() {
        return ElementalArtificeBlockEntity.ElementalArtificeBlockConfigInfo.STREAM_CODEC;
    }
}