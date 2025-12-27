package com.sammy.malum.common.block.ether;

import com.sammy.malum.common.item.ether.EtherItem;
import com.sammy.malum.registry.common.item.MalumDataComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import team.lodestar.lodestone.systems.block.WaterLoggedEntityBlock;

public class EtherCandleBlock<T extends EtherCandleBlockEntity> extends WaterLoggedEntityBlock<T> {

    public static final IntegerProperty CANDLES = BlockStateProperties.CANDLES;

    private static final VoxelShape ONE_AABB = Block.box(7.0, 0.0, 7.0, 9.0, 6.0, 9.0);
    private static final VoxelShape TWO_AABB = Block.box(5.0, 0.0, 6.0, 11.0, 6.0, 9.0);
    private static final VoxelShape THREE_AABB = Block.box(5.0, 0.0, 6.0, 10.0, 6.0, 11.0);
    private static final VoxelShape FOUR_AABB = Block.box(5.0, 0.0, 5.0, 11.0, 6.0, 10.0);

    public EtherCandleBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(
                this.stateDefinition
                        .any()
                        .setValue(CANDLES, 1)
                        .setValue(WATERLOGGED, false)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(CANDLES);
        super.createBlockStateDefinition(builder);
    }

    @Override
    protected boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {
        if (!useContext.isSecondaryUseActive()) {
            ItemStack stack = useContext.getItemInHand();
            if (stack.getItem().equals(asItem())) {
                if (state.getValue(CANDLES) < 4) {
                    Level level = useContext.getLevel();
                    if (level.getBlockEntity(useContext.getClickedPos()) instanceof EtherBlockEntity ether) {
                        if (matches(ether, stack)) {
                            return true;
                        }
                    }
                }
            }
        }
        return super.canBeReplaced(state, useContext);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return Block.canSupportCenter(level, pos.below(), Direction.UP);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        var blockstate = context.getLevel().getBlockState(context.getClickedPos());
        if (blockstate.is(this)) {
            return blockstate.cycle(CANDLES);
        } else {
            return super.getStateForPlacement(context);
        }
    }

    @Override
    protected @NotNull VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(CANDLES)) {
            default -> ONE_AABB;
            case 2 -> TWO_AABB;
            case 3 -> THREE_AABB;
            case 4 -> FOUR_AABB;
        };
    }

    protected boolean matches(EtherBlockEntity ether, ItemStack item) {
        if (EtherItem.isIridescent(item)) {
            if (ether.secondColor.rgb() != EtherItem.getSecondaryColor(item)) {
                return false;
            }
        }
        return ether.firstColor.rgb() == EtherItem.getPrimaryColor(item);
    }
}