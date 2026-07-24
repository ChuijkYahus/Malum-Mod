package com.sammy.malum.common.block.curiosities.artifice.soul_link;

import com.sammy.malum.core.systems.spirit.SpiritTypeProperty;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import team.lodestar.lodestone.modules.toolkit.block.LodestoneEntityBlock;

public class SoulLinkBlock extends LodestoneEntityBlock<SoulLinkBlockEntity> {

    public static final SpiritTypeProperty OPTIONAL_SPIRIT = SpiritTypeProperty.OPTIONAL_SPIRIT;
    public static final IntegerProperty POWER = BlockStateProperties.POWER;
    public static final BooleanProperty OPEN = BlockStateProperties.OPEN;

    public SoulLinkBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(POWER, 0).setValue(OPEN, false));
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (level.getBlockEntity(pos) instanceof SoulLinkBlockEntity soulLink) {
            var currentSignal = state.getValue(POWER);
            int outputSignal = soulLink.getOutputSignal(level);
            int tickInterval = soulLink.getTickInterval(level);
            outputSignal = Mth.clamp(outputSignal, 0, 15);
            if (currentSignal != outputSignal) {
                level.setBlock(pos, state.setValue(POWER, outputSignal), 2);
            }
            level.scheduleTick(pos, this, tickInterval);
        }
    }

    @Override
    protected boolean isSignalSource(BlockState state) {
        return true;
    }

    @Override
    protected int getSignal(BlockState state, BlockGetter level, BlockPos pos, Direction side) {
        return state.getValue(POWER);
	}

    @Override
    public boolean getWeakChanges(BlockState state, LevelReader level, BlockPos pos) {
        return true;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(OPTIONAL_SPIRIT, POWER, OPEN);
    }
}
