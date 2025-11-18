package com.sammy.malum.common.payloads.waveform;

import com.sammy.malum.common.block.curiosities.redstone.SpiritDiodeBlockEntity;
import io.netty.buffer.*;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.*;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import team.lodestar.lodestone.systems.network.OneSidedPayloadData;

public class SpiritDiodeStateUpdatePayload extends OpenBlockEntityStateUpdatePayload<SpiritDiodeBlockEntity.SpiritDiodeInfo> {

    public SpiritDiodeStateUpdatePayload(BlockPos pos, boolean isOpen, SpiritDiodeBlockEntity.SpiritDiodeInfo info) {
        super(pos, isOpen, info);
    }

    public SpiritDiodeStateUpdatePayload(FriendlyByteBuf buf) {
        super(buf);
    }

    @Override
    public StreamCodec<ByteBuf, SpiritDiodeBlockEntity.SpiritDiodeInfo> getInfoCodec() {
        return SpiritDiodeBlockEntity.SpiritDiodeInfo.STREAM_CODEC;
    }
}