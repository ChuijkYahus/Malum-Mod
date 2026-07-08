package com.sammy.malum.common.block.curiosities.totem;

import com.sammy.malum.core.systems.spirit.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;
import team.lodestar.lodestone.modules.toolkit.block.LodestoneEntityBlock;

import java.util.function.Supplier;

public class TotemPoleBlock<T extends TotemPoleBlockEntity> extends LodestoneEntityBlock<T> {

    public static final DirectionProperty HORIZONTAL_FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final SpiritTypeProperty SPIRIT_TYPE = SpiritTypeProperty.SPIRIT_TYPE;

    protected final Supplier<? extends Block> logBlock;
    protected final boolean isSoulwood;

    public TotemPoleBlock(Properties properties, Supplier<? extends Block> logBlock, boolean isSoulwood) {
        super(properties.lootFrom(logBlock));
        this.logBlock = logBlock;
        this.isSoulwood = isSoulwood;
        this.registerDefaultState(this.stateDefinition.any().setValue(HORIZONTAL_FACING, Direction.NORTH).setValue(SPIRIT_TYPE, "sacred"));
    }

    public Block getLogBlock() {
        return logBlock.get();
    }

    public boolean isSoulwood() {
        return isSoulwood;
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
    public @NotNull ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader world, BlockPos pos, Player player) {
        return logBlock.get().getCloneItemStack(world, pos, state);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(HORIZONTAL_FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HORIZONTAL_FACING, SPIRIT_TYPE);
    }

    public static BlockState createTotemPoleState(TotemPoleBlock<?> totemPole, Direction direction, SpiritLike spiritType) {
        return SpiritTypeProperty.setSpiritType(totemPole.defaultBlockState(), spiritType).setValue(HORIZONTAL_FACING, direction);
    }
}