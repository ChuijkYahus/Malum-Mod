package com.sammy.malum.core.handlers;

import com.sammy.malum.common.data.map.BlockCarvingMap;
import com.sammy.malum.core.systems.spirit.EntitySpiritDropData;
import com.sammy.malum.registry.common.MalumAttachmentTypes;
import com.sammy.malum.registry.common.MalumDataMaps;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.event.entity.item.ItemExpireEvent;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import net.neoforged.neoforge.event.entity.player.UseItemOnBlockEvent;
import team.lodestar.lodestone.helpers.block.BlockStateHelper;

public class BlockCarvingHandler {

    public static void carveBlocks(UseItemOnBlockEvent event) {
        var level = event.getLevel();
        var pos = event.getPos();
        var state = level.getBlockState(pos);
        var itemstack = event.getItemStack();
        if (!itemstack.canPerformAction(ItemAbilities.AXE_SCRAPE)) {
            return;
        }
        var data = state.getBlockHolder().getData(MalumDataMaps.BLOCK_CARVING);
        if (data == null) {
            return;
        }

        var player = event.getPlayer();
        if (player != null && playerHasShieldUseIntent(event.getUseOnContext())) {
            return;
        }
        if (player instanceof ServerPlayer serverPlayer) {
            CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(serverPlayer, pos, itemstack);
        }

        Holder<Block> variant = data.carvedVariant();
        BlockState resultState = BlockStateHelper.getBlockStateWithExistingProperties(state, variant.value().defaultBlockState());
        level.setBlock(pos, resultState, 11);
        level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, resultState));
        if (player != null) {
            itemstack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(event.getHand()));
        }
        event.cancelWithResult(ItemInteractionResult.sidedSuccess(level.isClientSide));
    }

    @SuppressWarnings("DataFlowIssue")
    public static boolean playerHasShieldUseIntent(UseOnContext context) {
        Player player = context.getPlayer();
        return context.getHand().equals(InteractionHand.MAIN_HAND) && player.getOffhandItem().is(Items.SHIELD) && !player.isSecondaryUseActive();
    }
}