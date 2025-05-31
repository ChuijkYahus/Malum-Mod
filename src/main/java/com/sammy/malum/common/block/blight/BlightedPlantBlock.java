package com.sammy.malum.common.block.blight;

import com.mojang.serialization.*;
import net.minecraft.core.*;
import net.minecraft.server.level.*;
import net.minecraft.util.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.phys.*;
import net.minecraft.world.phys.shapes.*;
import net.neoforged.neoforge.common.*;

import static com.sammy.malum.registry.common.MalumTags.BlockTags.BLIGHT_PLACEABLE_ON;

public class BlightedPlantBlock extends BushBlock implements BonemealableBlock, IShearable {
    public static final MapCodec<BlightedPlantBlock> CODEC = simpleCodec(BlightedPlantBlock::new);

    protected static final VoxelShape SHAPE = Block.box(4.0, 0.0, 4.0, 12.0, 10.0, 12.0);

    public BlightedPlantBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        final BlockPos below = pPos.below();
        final BlockState state = pLevel.getBlockState(below);
        final Block block = state.getBlock();
        return block instanceof BlightedEarthBlock blightedEarthBlock && blightedEarthBlock.isBonemealSuccess(pLevel, pRandom, below, state);
    }

    @Override
    public void performBonemeal(ServerLevel pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        final BlockPos below = pPos.below();
        final BlockState state = pLevel.getBlockState(below);
        if (state.getBlock() instanceof BlightedEarthBlock blightedEarthBlock) {
            blightedEarthBlock.performBonemeal(pLevel, pRandom, below, state);
        }
    }

    @Override
    protected MapCodec<? extends BushBlock> codec() {
        return CODEC;
    }

    @Override
    protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        if (pState.is(BLIGHT_PLACEABLE_ON)) {
            return true;
        }
        return super.mayPlaceOn(pState, pLevel, pPos);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Vec3 vec3 = state.getOffset(level, pos);
        return SHAPE.move(vec3.x, vec3.y, vec3.z);
    }
}