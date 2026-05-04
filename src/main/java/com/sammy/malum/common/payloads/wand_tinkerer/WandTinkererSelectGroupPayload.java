package com.sammy.malum.common.payloads.wand_tinkerer;

import com.sammy.malum.common.block.curiosities.sorcery.wand_tinkerer.WandTinkererContainer;
import com.sammy.malum.common.data.custom.wand_parts.WandPartType;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import team.lodestar.lodestone.systems.network.OneSidedPayloadData;

public class WandTinkererSelectGroupPayload extends OneSidedPayloadData {

    private final BlockPos pos;
    private final WandPartType.WandPartGroup group;

    public WandTinkererSelectGroupPayload(BlockPos pos, WandPartType.WandPartGroup group) {
        this.pos = pos;
        this.group = group;
    }

    public WandTinkererSelectGroupPayload(RegistryFriendlyByteBuf buf) {
        this.pos = BlockPos.STREAM_CODEC.decode(buf);
        this.group = WandPartType.STREAM_GROUP_CODEC.decode(buf);
    }

    @Override
    public void handle(IPayloadContext iPayloadContext) {
        if (iPayloadContext.player() instanceof ServerPlayer player) {
            if (player.containerMenu instanceof WandTinkererContainer container) {
                var wandTinkerer = container.blockEntity;
                if (!wandTinkerer.getBlockPos().equals(pos)) {
                    return;
                }
                wandTinkerer.updateGroup(group);
            }
        }
    }

    @Override
    public void serialize(RegistryFriendlyByteBuf buf) {
        BlockPos.STREAM_CODEC.encode(buf, pos);
        WandPartType.STREAM_GROUP_CODEC.encode(buf, group);
    }
}