package com.sammy.malum.core.handlers;

import com.sammy.malum.registry.common.MalumDataMaps;
import com.sammy.malum.registry.common.MalumParticleEffectTypes;
import com.sammy.malum.visual_effects.networked.sap.SapCollectionParticleEffect;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.neoforged.neoforge.event.entity.player.UseItemOnBlockEvent;
import net.neoforged.neoforge.items.ItemHandlerHelper;
import team.lodestar.lodestone.helpers.ColorHelper;
import team.lodestar.lodestone.helpers.block.BlockStateHelper;
import team.lodestar.lodestone.systems.particle.data.color.ColorParticleData;

public class BlockTappingHandler {

    public static void drainBlock(UseItemOnBlockEvent event) {
        var level = event.getLevel();
        var pos = event.getPos();
        var state = level.getBlockState(pos);
        var itemstack = event.getItemStack();
        if (itemstack.getItem() != Items.GLASS_BOTTLE) {
            return;
        }
        var data = state.getBlockHolder().getData(MalumDataMaps.FLUID_TAPPING);
        if (data == null) {
            return;
        }
        event.cancelWithResult(ItemInteractionResult.SUCCESS);
        if (!(level instanceof ServerLevel serverLevel)) {
            return;
        }

        level.playSound(null, pos, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1f, 1f);

        itemstack.shrink(1);
        Direction direction = event.getUseOnContext().getClickedFace();
        int trackedEntity;

        var result = data.bottledFluid().value().getDefaultInstance();
        if (event.getPlayer() instanceof ServerPlayer player) {
            CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(player, pos, itemstack);
            ItemHandlerHelper.giveItemToPlayer(player, result);
            trackedEntity = player.getId();
        }
        else {
            var offset = pos.relative(direction);
            var item = new ItemEntity(level, offset.getX() + 0.5f, offset.getY() + 0.5f, offset.getZ() + 0.5f, result);
            item.setPickUpDelay(40);
            item.setDeltaMovement(item.getDeltaMovement().multiply(0.0, 1.0, 0.0));
            level.addFreshEntity(item);
            trackedEntity = item.getId();
        }
        MalumParticleEffectTypes.SAP_COLLECTED.createEffect(pos)
                .customData(new SapCollectionParticleEffect.SapCollectionEffectData(direction, trackedEntity))
                .color(ColorParticleData.create(ColorHelper.getColor(data.color())))
                .spawn(serverLevel);
        BlockStateHelper.setBlockStateWithExistingProperties(level, pos, data.leftoverBlock().value().defaultBlockState(), 3);
    }
}