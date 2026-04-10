package com.sammy.malum.common.block.curiosities.mana_mote;

import com.sammy.malum.core.systems.spirit.SpiritTypeProperty;
import com.sammy.malum.core.systems.spirit.type.*;
import net.minecraft.core.*;
import net.minecraft.world.item.context.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import org.jetbrains.annotations.*;
import team.lodestar.lodestone.modules.toolkit.block.LodestoneEntityBlock;

public class ManaMoteBlock extends LodestoneEntityBlock<ManaMoteBlockEntity> {

    public static final BooleanProperty UP = BooleanProperty.create("up");
    public static final BooleanProperty DOWN = BooleanProperty.create("down");
    public static final BooleanProperty NORTH = BooleanProperty.create("north");
    public static final BooleanProperty SOUTH = BooleanProperty.create("south");
    public static final BooleanProperty EAST = BooleanProperty.create("east");
    public static final BooleanProperty WEST = BooleanProperty.create("west");

    public static final SpiritTypeProperty SPIRIT_TYPE = SpiritTypeProperty.SPIRIT_TYPE;

    public ManaMoteBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(UP, false)
                .setValue(DOWN, false)
                .setValue(NORTH, false)
                .setValue(SOUTH, false)
                .setValue(EAST, false)
                .setValue(WEST, false)
                .setValue(SPIRIT_TYPE, "sacred"));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(UP, DOWN, NORTH, SOUTH, EAST, WEST);
        builder.add(SPIRIT_TYPE);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        var state = super.getStateForPlacement(context);
        if (state == null) {
            return null;
        }
        for (Direction direction : Direction.values()) {
            var property = getDirectionProperty(direction);
            var pos = context.getClickedPos();
            var relative = pos.relative(direction);
            boolean value = context.getLevel().getBlockState(relative).getBlock() instanceof ManaMoteBlock;
            state = state.setValue(property, value);
        }
        return state;
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        super.neighborChanged(state, level, pos, block, fromPos, isMoving);
        Direction direction = Direction.fromDelta(fromPos.getX() - pos.getX(), fromPos.getY() - pos.getY(), fromPos.getZ() - pos.getZ());
        if (direction != null) {
            var property = getDirectionProperty(direction);
            boolean value = level.getBlockState(fromPos).getBlock() instanceof ManaMoteBlock;
            level.setBlock(pos, state.setValue(property, value), 2);
        }
    }

    public static BlockState createManaMoteState(BlockState state, SpiritLike spiritType) {
        return SpiritTypeProperty.setSpiritType(state, spiritType);
    }

    public BooleanProperty getDirectionProperty(Direction direction) {
        return switch (direction) {
            case UP -> UP;
            case DOWN -> DOWN;
            case NORTH -> NORTH;
            case SOUTH -> SOUTH;
            case EAST -> EAST;
            case WEST -> WEST;
        };
    }

    public static boolean isOccluded(BlockState state, Direction direction) {
        return switch (direction) {
            case UP -> state.getValue(UP);
            case DOWN -> state.getValue(DOWN);
            case NORTH -> state.getValue(NORTH);
            case SOUTH -> state.getValue(SOUTH);
            case EAST -> state.getValue(EAST);
            case WEST -> state.getValue(WEST);
        };
    }
}
