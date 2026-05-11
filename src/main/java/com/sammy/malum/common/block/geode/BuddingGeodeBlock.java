package com.sammy.malum.common.block.geode;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;

public class BuddingGeodeBlock extends CrystalGeodeBlock {

    public final GeodeCrystalClusterBlock clusterBlock;

    public BuddingGeodeBlock(Properties properties, GeodeCrystalClusterBlock clusterBlock) {
        super(properties);
        this.clusterBlock = clusterBlock;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        var direction = Direction.values()[random.nextInt(6)];
        var blockpos = pos.relative(direction);
        var blockstate = level.getBlockState(blockpos);
        if (!canClusterGrowAtState(blockstate)) {
            return;
        }
        int age = 0;

        if (blockstate.is(clusterBlock) && blockstate.getValue(GeodeCrystalClusterBlock.FACING) == direction) {
            int crystalAge = blockstate.getValue(GeodeCrystalClusterBlock.AGE);
            if (crystalAge == 2) {
                return;
            }
            age = crystalAge + 1;
        }
        var newState = clusterBlock.defaultBlockState()
                .setValue(GeodeCrystalClusterBlock.FACING, direction)
                .setValue(GeodeCrystalClusterBlock.WATERLOGGED, blockstate.getFluidState().getType() == Fluids.WATER)
                .setValue(GeodeCrystalClusterBlock.AGE, age);
        level.setBlockAndUpdate(blockpos, newState);
    }

    public static boolean canClusterGrowAtState(BlockState state) {
        return state.isAir() || state.is(Blocks.WATER) && state.getFluidState().getAmount() == 8;
    }
}