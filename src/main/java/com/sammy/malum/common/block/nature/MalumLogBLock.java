package com.sammy.malum.common.block.nature;

import com.sammy.malum.common.block.curiosities.totem.*;
import com.sammy.malum.common.data.map.*;
import com.sammy.malum.common.item.spirit.SpiritShardItem;
import com.sammy.malum.core.systems.spirit.type.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.block.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.*;
import net.minecraft.server.level.*;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import team.lodestar.lodestone.systems.block.LodestoneLogBlock;

import java.util.function.Supplier;

public class MalumLogBLock extends LodestoneLogBlock {
    private final boolean isCorrupt;

    public MalumLogBLock(Properties properties, Supplier<Block> stripped, boolean isCorrupt) {
        super(properties, stripped);
        this.isCorrupt = isCorrupt;
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (stack.getItem() instanceof SpiritShardItem shard) {
            if (hit.getDirection().equals(Direction.UP) || hit.getDirection().equals(Direction.DOWN)) {
                return ItemInteractionResult.FAIL;
            }
            if (level instanceof ServerLevel serverLevel) {
                if (createTotemPole(serverLevel, pos, player, handIn, hit, stack, shard)) {
                    return ItemInteractionResult.SUCCESS;
                }
            }
            else {
                return ItemInteractionResult.SUCCESS;
            }
        }
        return super.useItemOn(stack, state, level, pos, player, handIn, hit);
    }

    @SuppressWarnings("deprecation")
    public boolean createTotemPole(ServerLevel level, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit, ItemStack stack, SpiritWrapper spirit) {
        if (spirit.matches(MalumSpiritTypes.UMBRAL_SPIRIT)) {
            return false;
        }

        TotemPoleMap conversion = builtInRegistryHolder().getData(MalumDataMaps.TOTEM_POLE_CONVERSION);
        if (conversion == null) {
            return false;
        }
        if (conversion.totemPoleVariant().value() instanceof TotemPoleBlock<?> totemPoleBlock) {
            level.setBlockAndUpdate(pos, TotemPoleBlock.createTotemPoleState(totemPoleBlock, hit.getDirection(), spirit));
            if (level.getBlockEntity(pos) instanceof TotemPoleBlockEntity blockEntity) {
                blockEntity.setSpirit(level, spirit.unwrapSpirit());
            }
            if (!player.isCreative()) {
                stack.shrink(1);
            }
            level.levelEvent(2001, pos, Block.getId(level.getBlockState(pos)));
            player.swing(handIn, true);
        }
        return false;
    }
}