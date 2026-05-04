package com.sammy.malum.common.payloads.wand_tinkerer;

import com.sammy.malum.common.block.curiosities.sorcery.wand_tinkerer.WandTinkererContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import team.lodestar.lodestone.systems.network.OneSidedPayloadData;

public class WandTinkererInteractionItemPayload extends OneSidedPayloadData {
    private final BlockPos pos;
    private final ItemStack carried;

    public WandTinkererInteractionItemPayload(BlockPos pos, ItemStack carried) {
        this.pos = pos;
        this.carried = carried;
    }

    public WandTinkererInteractionItemPayload(RegistryFriendlyByteBuf buf) {
        this.pos = BlockPos.STREAM_CODEC.decode(buf);
        this.carried = ItemStack.OPTIONAL_STREAM_CODEC.decode(buf);
    }

    @Override
    public void handle(IPayloadContext iPayloadContext) {
        if (iPayloadContext.player() instanceof ServerPlayer player) {
            if (player.containerMenu instanceof WandTinkererContainer container) {
                var wandTinkerer = container.blockEntity;
                if (!wandTinkerer.getBlockPos().equals(pos)) {
                    return;
                }

                var carried = container.getCarried();
                if (!ItemStack.isSameItem(carried, this.carried)) {
                    return;
                }
                wandTinkerer.handleInteraction(player.serverLevel(), player, carried);
            }
        }
    }

    @Override
    public void serialize(RegistryFriendlyByteBuf buf) {
        BlockPos.STREAM_CODEC.encode(buf, pos);
        ItemStack.OPTIONAL_STREAM_CODEC.encode(buf, carried);
    }
}