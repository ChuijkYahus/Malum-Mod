package com.sammy.malum.common.payloads.waveform;

import com.sammy.malum.common.block.curiosities.redstone.SpiritDiodeBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import team.lodestar.lodestone.systems.network.OneSidedPayloadData;

//TODO: This sucks!!!
public class SpiritDiodeVisualUpdatePayload extends OneSidedPayloadData {
    private final BlockPos pos;
    private final int outputSignal;
    private final int inputSignal;
    private final boolean isPowering;

    public SpiritDiodeVisualUpdatePayload(BlockPos pos, int outputSignal, int inputSignal, boolean isPowering) {
        this.pos = pos;
        this.outputSignal = outputSignal;
        this.inputSignal = inputSignal;
        this.isPowering = isPowering;
    }

    public SpiritDiodeVisualUpdatePayload(FriendlyByteBuf buf) {
        this.pos = BlockPos.STREAM_CODEC.decode(buf);
        this.outputSignal = buf.readInt();
        this.inputSignal = buf.readInt();
        this.isPowering = buf.readBoolean();
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void handle(IPayloadContext iPayloadContext) {
        Level level = iPayloadContext.player().level();
        if (level.getBlockEntity(pos) instanceof SpiritDiodeBlockEntity spiritDiode) {
            spiritDiode.updateVisuals(outputSignal, inputSignal, isPowering);
        }
    }

    @Override
    public void serialize(FriendlyByteBuf friendlyByteBuf) {
        BlockPos.STREAM_CODEC.encode(friendlyByteBuf, pos);
        friendlyByteBuf.writeInt(this.outputSignal);
        friendlyByteBuf.writeInt(this.inputSignal);
        friendlyByteBuf.writeBoolean(this.isPowering);
    }
}