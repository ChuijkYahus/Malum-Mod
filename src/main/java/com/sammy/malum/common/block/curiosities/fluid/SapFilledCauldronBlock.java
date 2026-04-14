package com.sammy.malum.common.block.curiosities.fluid;

import com.mojang.serialization.MapCodec;
import com.sammy.malum.common.block.flora.EbonyStalkBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import team.lodestar.lodestone.modules.toolkit.block.LodestoneBlockProperties;

@SuppressWarnings("NullableProblems")
public class SapFilledCauldronBlock extends AbstractCauldronBlock {

    public static final MapCodec<EbonyStalkBlock> CODEC = simpleCodec(EbonyStalkBlock::new);

    public static final IntegerProperty LEVEL = IntegerProperty.create("level", 1, 5);

    @Override
    protected MapCodec<? extends AbstractCauldronBlock> codec() {
        return null;
    }

    public SapFilledCauldronBlock() {
        super(LodestoneBlockProperties.copy(Blocks.CAULDRON), CauldronInteraction.EMPTY);
    }

    @Override
    public boolean isFull(BlockState state) {
        return state.getValue(LEVEL) == 5;
    }

    @Override
    protected double getContentHeight(BlockState state) {
        return (4.0 + (double) state.getValue(LEVEL) * 2.0) / 16.0;
    }

    public static void lowerFillLevel(BlockState state, Level level, BlockPos pos) {
        int i = state.getValue(LEVEL) - 1;
        BlockState blockstate = i == 0 ? Blocks.CAULDRON.defaultBlockState() : state.setValue(LEVEL, i);
        level.setBlockAndUpdate(pos, blockstate);
        level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(blockstate));
    }

    @Override
    public void handlePrecipitation(BlockState state, Level level, BlockPos pos, Biome.Precipitation precipitation) {

    }

    @Override
    protected int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        return state.getValue(LEVEL);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LEVEL);
    }
}
