package com.sammy.malum.common.block.flora.wood;

import com.sammy.malum.common.block.curiosities.totem.*;
import com.sammy.malum.common.item.spirit.SpiritShardItem;
import com.sammy.malum.core.systems.spirit.SpiritLike;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.magic.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.*;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.*;

public class MalumLogBlock extends RotatedPillarBlock {

    public MalumLogBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected @NotNull ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (stack.getItem() instanceof SpiritShardItem shard) {
            if (hit.getDirection().equals(Direction.UP) || hit.getDirection().equals(Direction.DOWN) || state.getValue(AXIS).isHorizontal()) {
                return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
            if (level instanceof ServerLevel serverLevel) {
                if (!createTotemPole(serverLevel, pos, hit.getDirection(), shard)) {
                    return super.useItemOn(stack, state, level, pos, player, handIn, hit);
                }
                if (!player.isCreative()) {
                    stack.shrink(1);
                }
            }
            return ItemInteractionResult.SUCCESS;
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @SuppressWarnings("deprecation")
    public boolean createTotemPole(ServerLevel level, BlockPos pos, Direction direction, SpiritLike spirit) {
        if (spirit.matches(MalumSpiritTypes.UMBRAL_SPIRIT)) {
            return false;
        }

        var conversion = builtInRegistryHolder().getData(MalumDataMaps.TOTEM_POLE_CONVERSION);
        if (conversion == null) {
            return false;
        }
        var converted = conversion.totemPoleVariant().value();
        if (converted instanceof TotemPoleBlock<?> totemPoleBlock) {
            level.setBlockAndUpdate(pos, TotemPoleBlock.createTotemPoleState(totemPoleBlock, direction, spirit));
            if (level.getBlockEntity(pos) instanceof TotemPoleBlockEntity blockEntity) {
                blockEntity.brieflyActivate(level);
            }
            level.levelEvent(2001, pos, Block.getId(level.getBlockState(pos)));
        }
        return false;
    }
}