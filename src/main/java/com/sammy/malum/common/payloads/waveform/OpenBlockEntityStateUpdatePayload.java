package com.sammy.malum.common.payloads.waveform;

import com.sammy.malum.common.block.curiosities.artifice.ArtificeTinkeringInfo;
import com.sammy.malum.common.block.curiosities.artifice.TinkererArtificeBlockEntity;
import io.netty.buffer.*;
import net.minecraft.core.*;
import net.minecraft.network.*;
import net.minecraft.network.codec.*;
import net.minecraft.server.level.*;
import net.neoforged.neoforge.network.handling.*;
import team.lodestar.lodestone.systems.network.*;

public abstract class OpenBlockEntityStateUpdatePayload<T extends ArtificeTinkeringInfo> extends OneSidedPayloadData {
    private final BlockPos pos;
    private final boolean isOpen;
    private final T info;

    public OpenBlockEntityStateUpdatePayload(BlockPos pos, boolean isOpen, T info) {
        this.pos = pos;
        this.isOpen = isOpen;
        this.info = info;
    }

    public OpenBlockEntityStateUpdatePayload(RegistryFriendlyByteBuf buf) {
        this.pos = BlockPos.STREAM_CODEC.decode(buf);
        this.isOpen = buf.readBoolean();
        this.info = getInfoCodec().decode(buf);
    }

    public abstract StreamCodec<ByteBuf, T> getInfoCodec();

    @Override
    public void handle(IPayloadContext iPayloadContext) {
        if (iPayloadContext.player().level() instanceof ServerLevel level) {
            if (level.getBlockEntity(pos) instanceof TinkererArtificeBlockEntity tinkererArtificeBlockEntity) {
                tinkererArtificeBlockEntity.handleTinkeredStateChange(level, isOpen, info);
            }
        }
    }

    @Override
    public void serialize(RegistryFriendlyByteBuf buf) {
        BlockPos.STREAM_CODEC.encode(buf, pos);
        buf.writeBoolean(this.isOpen);
        getInfoCodec().encode(buf, info);
    }
}