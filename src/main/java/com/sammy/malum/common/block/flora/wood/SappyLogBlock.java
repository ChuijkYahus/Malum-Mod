package com.sammy.malum.common.block.flora.wood;

import com.sammy.malum.registry.common.*;
import com.sammy.malum.visual_effects.networked.sap.SapCollectionParticleEffect;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.items.ItemHandlerHelper;
import team.lodestar.lodestone.helpers.block.*;
import team.lodestar.lodestone.systems.particle.data.color.*;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Supplier;

public class SappyLogBlock extends RotatedPillarBlock {

    public final Supplier<Item> sap;
    public final List<? extends ColorParticleData> sapColor;

    public SappyLogBlock(Properties properties, Supplier<Item> sap, Color... sapColor) {
        super(properties);
        this.sap = sap;
        this.sapColor = Arrays.stream(sapColor).map(c -> ColorParticleData.create(c).build()).toList();
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack itemstack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (itemstack.getItem() != Items.GLASS_BOTTLE) {
            return super.useItemOn(itemstack, state, level, pos, player, handIn, hit);
        }
        if (!(level instanceof ServerLevel serverLevel)) {
            return ItemInteractionResult.SUCCESS;
        }
        itemstack.shrink(1);
        player.playSound(SoundEvents.BOTTLE_FILL);
        ItemHandlerHelper.giveItemToPlayer(player, sap.get().getDefaultInstance());
        MalumParticleEffectTypes.SAP_COLLECTED.createEffect(pos)
                .customData(new SapCollectionParticleEffect.SapCollectionEffectData(hit.getDirection(), player.getUUID()))
                .color(sapColor)
                .spawn(serverLevel);
//        BlockStateHelper.setBlockStateWithExistingProperties(level, pos, drained.get().defaultBlockState(), 3);

        return ItemInteractionResult.SUCCESS;
    }
}