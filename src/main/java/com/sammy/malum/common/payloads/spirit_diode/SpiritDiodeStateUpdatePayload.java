package com.sammy.malum.common.payloads.spirit_diode;

import com.sammy.malum.common.block.curiosities.redstone.SpiritDiodeBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import team.lodestar.lodestone.systems.network.OneSidedPayloadData;

public class SpiritDiodeStateUpdatePayload extends OneSidedPayloadData {
    private final BlockPos pos;
    private final boolean isOpen;
    private final SpiritDiodeBlockEntity.TimeIntervalType type;
    private final int frequency;

    public SpiritDiodeStateUpdatePayload(BlockPos pos, boolean isOpen, SpiritDiodeBlockEntity.TimeIntervalType type, int frequency) {
        this.pos = pos;
        this.isOpen = isOpen;
        this.type = type;
        this.frequency = frequency;
    }

    public SpiritDiodeStateUpdatePayload(FriendlyByteBuf buf) {
        this.pos = BlockPos.STREAM_CODEC.decode(buf);
        this.isOpen = buf.readBoolean();
        this.type = SpiritDiodeBlockEntity.TimeIntervalType.values()[buf.readInt()];
        this.frequency = buf.readInt();
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void handle(IPayloadContext iPayloadContext) {
        Level level = iPayloadContext.player().level();
        if (level.getBlockEntity(pos) instanceof SpiritDiodeBlockEntity spiritDiode) {
            spiritDiode.toggleState(isOpen, type, frequency);
        }
    }

    @Override
    public void serialize(FriendlyByteBuf buf) {
        BlockPos.STREAM_CODEC.encode(buf, pos);
        buf.writeBoolean(this.isOpen);
        buf.writeInt(type.ordinal());
        buf.writeInt(frequency);
    }
}