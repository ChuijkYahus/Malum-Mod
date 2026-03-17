package com.sammy.malum.common.block.curiosities.totem.channel;

import com.sammy.malum.common.block.curiosities.totem.anchor.*;
import com.sammy.malum.common.block.curiosities.totem.unweaver.*;
import com.sammy.malum.common.block.dungeon.*;
import net.minecraft.core.*;
import net.minecraft.util.*;
import net.minecraft.world.item.context.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;
import org.apache.commons.lang3.*;
import org.jetbrains.annotations.*;
import team.lodestar.lodestone.modules.toolkit.block.*;

import java.lang.reflect.*;
import java.util.*;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.HORIZONTAL_FACING;

public class RiteChannelBlock extends LodestoneEntityBlock<RiteChannelBlockEntity> {

    public enum RiteChannelType implements StringRepresentable {
        BASE(),
        STRAIGHT_X(Direction.EAST, Direction.WEST),
        STRAIGHT_Z(Direction.NORTH, Direction.SOUTH),
        END_NORTH(Direction.NORTH),
        END_EAST(Direction.EAST),
        END_SOUTH(Direction.SOUTH),
        END_WEST(Direction.WEST),
        BEND_NORTH_EAST(Direction.NORTH, Direction.EAST),
        BEND_EAST_SOUTH(Direction.EAST, Direction.SOUTH),
        BEND_SOUTH_WEST(Direction.SOUTH, Direction.WEST),
        BEND_WEST_NORTH(Direction.WEST, Direction.NORTH);

        final String name = name().toLowerCase(Locale.ROOT);
        final Direction[] directions;

        RiteChannelType(Direction... directions) {
            this.directions = directions;
        }

        @Override
        public String getSerializedName() {
            return name;
        }
    }

    public static final EnumProperty<RiteChannelType> CHANNEL_TYPE = EnumProperty.create("channel_type", RiteChannelType.class);

    public RiteChannelBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(CHANNEL_TYPE);
    }


    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return getState(context.getLevel(), context.getClickedPos());
    }

    @Override
    public @NotNull BlockState updateShape(BlockState state, @NotNull Direction facing, @NotNull BlockState facingState, @NotNull LevelAccessor level, @NotNull BlockPos pos, @NotNull BlockPos facingPos) {
        if (canChangeChannel(level, pos, facing)) {
            return getState(level, pos);
        }
        return state;
    }

    protected BlockState getState(LevelAccessor level, BlockPos pos) {
        return defaultBlockState()
                .setValue(CHANNEL_TYPE, getChannelType(level, pos));
    }

    protected boolean canChangeChannel(LevelAccessor level, BlockPos pos, Direction inboundChange) {
        var state = level.getBlockState(pos);
        var type = state.getValue(CHANNEL_TYPE);
        var directions = type.directions;
        if (directions.length < 2) {
            return true;
        }
        if (Arrays.stream(directions).noneMatch(inboundChange::equals)) {
            return false;
        }
        for (Direction direction : directions) {
            if (!canConnectTo(level, pos, direction)) {
                return true;
            }
        }
        return false;
    }

    protected RiteChannelType getChannelType(LevelAccessor level, BlockPos pos) {
        Direction[] directions = {};
        for (int i = 0; i < 4; i++) {
            var direction = Direction.from2DDataValue(i);
            boolean canConnect = canConnectTo(level, pos, direction);
            if (canConnect) {
                directions = ArrayUtils.add(directions, direction);
            }
            if (directions.length >= 2) {
                break;
            }
        }
        for (RiteChannelType possibleValue : CHANNEL_TYPE.getPossibleValues()) {
            boolean matches = true;
            for (Direction direction : possibleValue.directions) {
                if (!ArrayUtils.contains(directions, direction)) {
                    matches = false;
                    break;
                }
            }
            if (matches && directions.length == possibleValue.directions.length) {
                return possibleValue;
            }
        }
        return RiteChannelType.BASE;
    }

    protected boolean canConnectTo(LevelAccessor level, BlockPos pos, Direction direction) {
        BlockState state = level.getBlockState(pos.relative(direction));
        if (state.getBlock() instanceof RiteChannelBlock) {
            var type = state.getValue(CHANNEL_TYPE);
            if (type.directions.length < 2) {
                return true;
            }
            return ArrayUtils.contains(type.directions, direction.getOpposite());
        }
        if (state.getBlock() instanceof RiteAnchorBlock) {
            return true;
        }
        return false;
    }
}