package com.sammy.malum.common.block.curiosities.totem;

import com.sammy.malum.core.systems.spirit.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import team.lodestar.lodestone.modules.toolkit.block.LodestoneEntityBlock;

import java.util.function.Supplier;

public class TotemPoleBlock<T extends TotemPoleBlockEntity> extends LodestoneEntityBlock<T> {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final SpiritTypeProperty SPIRIT = SpiritTypeProperty.SPIRIT;

    public TotemPoleBlock(Properties properties, Supplier<? extends Block> logBlock) {
        super(properties.lootFrom(logBlock));
        registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH));
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState pState) {
        return true;
    }

    @Override
    protected int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        if (level.getBlockEntity(pos) instanceof TotemPoleBlockEntity totemPole) {
            return totemPole.spirit.getAnalogSignal();
        }
        return 0;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, SPIRIT);
    }

    public static BlockState createTotemPoleState(TotemPoleBlock<?> totemPole, Direction direction, SpiritLike spiritType) {
        return SPIRIT.setSpirit(totemPole.defaultBlockState(), spiritType)
                .setValue(FACING, direction);
    }
}