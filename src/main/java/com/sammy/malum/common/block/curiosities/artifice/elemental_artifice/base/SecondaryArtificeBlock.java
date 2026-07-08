package com.sammy.malum.common.block.curiosities.artifice.elemental_artifice.base;

import com.sammy.malum.common.block.curiosities.artifice.elemental_artifice.aerial.WindTunnelBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.Nullable;

public class SecondaryArtificeBlock<T extends SecondaryArtificeBlockEntity> extends ElementalArtificeBlock<T> {

    public static final BooleanProperty UP = BooleanProperty.create("up");
    public static final BooleanProperty DOWN = BooleanProperty.create("down");
    public static final BooleanProperty LEFT = BooleanProperty.create("left");
    public static final BooleanProperty RIGHT = BooleanProperty.create("right");

    public SecondaryArtificeBlock(Properties properties) {
        super(properties);

        registerDefaultState(defaultBlockState()
                .setValue(UP, false)
                .setValue(DOWN, false)
                .setValue(LEFT, false)
                .setValue(RIGHT, false)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(UP, DOWN, LEFT, RIGHT);
        super.createBlockStateDefinition(builder);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        var state = super.getStateForPlacement(context);
        if (state == null) {
            return null;
        }
        var level = context.getLevel();
        var pos = context.getClickedPos();
        var direction = context.getClickedFace().getOpposite();
        var relative = pos.relative(direction);
        var clickedState = level.getBlockState(relative);
        Block block = clickedState.getBlock();
        if (block instanceof PrimaryArtificeBlock || block.equals(this)) {
            state = state.setValue(FACING, clickedState.getValue(FACING));
        }
        return updateOcclusion(context.getLevel(), state, context.getClickedPos());
    }


    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        super.neighborChanged(state, level, pos, block, fromPos, isMoving);
        var direction = Direction.fromDelta(fromPos.getX() - pos.getX(), fromPos.getY() - pos.getY(), fromPos.getZ() - pos.getZ());
        if (direction != null) {
            level.setBlock(pos, updateOcclusion(level, state, pos), 3);
        }
    }

    public BlockState updateOcclusion(Level level, BlockState state, BlockPos pos) {
        Direction[] toCheck = getRelevantFaces(state);
        for (int i = 0; i < toCheck.length; i++) {
            var direction = toCheck[i];
            var property = getDirectionProperty(i);
            var relative = pos.relative(direction);
            var neighborState = level.getBlockState(relative);
            boolean isValidNeighbor = neighborState.getBlock() instanceof SecondaryArtificeBlock && neighborState.getValue(FACING).equals(state.getValue(FACING));
            state = state.setValue(property, isValidNeighbor);
            if (!isValidNeighbor) {
                continue;
            }
            if (!state.getValue(POWERED) && neighborState.getValue(POWERED)) {
                if (level.getBlockEntity(relative) instanceof SecondaryArtificeBlockEntity neighbour) {
                    var optional = neighbour.getOwner();
                    if (optional.isPresent()) {
                        var igniter = optional.get();
                        state = state.setValue(POWERED, true);
                        level.scheduleTick(igniter.getBlockPos(), igniter.getBlockState().getBlock(), 1);
                    }
                }
            }
        }
        return state;
    }

    public static Direction[] getRelevantFaces(BlockState state) {
        Direction[] toCheck = new Direction[4];
        var facing = state.getValue(FACING);
        if (facing.getAxis().isHorizontal()) {
            toCheck[2] = Direction.UP;
            toCheck[0] = Direction.DOWN;
            toCheck[1] = facing.getClockWise();
            toCheck[3] = facing.getCounterClockWise();
        } else {
            for (int i = 0; i < 4; i++) {
                Direction direction = Direction.from2DDataValue(i);
                toCheck[i] = direction;
            }
            if (facing.equals(Direction.DOWN)) {
                toCheck[1] = toCheck[1].getOpposite();
                toCheck[3] = toCheck[3].getOpposite();
            }
        }
        return toCheck;
    }

    public static BooleanProperty getDirectionProperty(int direction) {
        return switch (direction) {
            case 2 -> UP;
            case 0 -> DOWN;
            case 1 -> LEFT;
            case 3 -> RIGHT;
            default -> throw new IllegalStateException("Unexpected value: " + direction);
        };
    }
}