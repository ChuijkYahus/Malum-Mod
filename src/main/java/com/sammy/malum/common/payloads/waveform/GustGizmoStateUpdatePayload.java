package com.sammy.malum.common.payloads.waveform;

import com.sammy.malum.common.block.curiosities.gust_igniter.*;
import com.sammy.malum.common.block.curiosities.redstone.*;
import io.netty.buffer.*;
import net.minecraft.core.*;
import net.minecraft.network.*;
import net.minecraft.network.codec.*;

public class GustGizmoStateUpdatePayload extends OpenBlockEntityStateUpdatePayload<AbstractGustGizmoBlockEntity.GustGizmoInfo> {

    public GustGizmoStateUpdatePayload(BlockPos pos, boolean isOpen, AbstractGustGizmoBlockEntity.GustGizmoInfo info) {
        super(pos, isOpen, info);
    }

    public GustGizmoStateUpdatePayload(FriendlyByteBuf buf) {
        super(buf);
    }

    @Override
    public StreamCodec<ByteBuf, AbstractGustGizmoBlockEntity.GustGizmoInfo> getInfoCodec() {
        return AbstractGustGizmoBlockEntity.GustGizmoInfo.STREAM_CODEC;
    }
}