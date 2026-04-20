package com.sammy.malum.common.block.flora;

import com.mojang.serialization.MapCodec;
import com.sammy.malum.registry.common.MalumTags;
import com.sammy.malum.registry.common.MalumContent;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BambooLeaves;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.CommonHooks;

import javax.annotation.Nullable;


@SuppressWarnings("NullableProblems")
public class EbonyStalkBlock extends Block implements BonemealableBlock {

    public static final MapCodec<EbonyStalkBlock> CODEC = simpleCodec(EbonyStalkBlock::new);

    protected static final VoxelShape SMALL_SHAPE = Block.box(5.0, 0.0, 5.0, 11.0, 16.0, 11.0);
    protected static final VoxelShape LARGE_SHAPE = Block.box(3.0, 0.0, 3.0, 13.0, 16.0, 13.0);
    protected static final VoxelShape COLLISION_SHAPE = Block.box(3.5, 0.0, 3.5, 12.5, 16.0, 12.5);
    public static final IntegerProperty AGE = BlockStateProperties.AGE_1;
    public static final EnumProperty<BambooLeaves> LEAVES = BlockStateProperties.BAMBOO_LEAVES;
    public static final IntegerProperty STAGE = BlockStateProperties.STAGE;


    @Override
    public MapCodec<EbonyStalkBlock> codec() {
        return CODEC;
    }

    public EbonyStalkBlock(BlockBehaviour.Properties properties) {
        super(properties);
        registerDefaultState(
                stateDefinition.any().setValue(AGE, 0).setValue(LEAVES, BambooLeaves.NONE).setValue(STAGE, 0)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE, LEAVES, STAGE);
    }

    @Override
    protected boolean propagatesSkylightDown(BlockState state, BlockGetter level, BlockPos pos) {
        return true;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        VoxelShape voxelshape = state.getValue(LEAVES) == BambooLeaves.LARGE ? LARGE_SHAPE : SMALL_SHAPE;
        Vec3 vec3 = state.getOffset(level, pos);
        return voxelshape.move(vec3.x, vec3.y, vec3.z);
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
        return false;
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Vec3 vec3 = state.getOffset(level, pos);
        return COLLISION_SHAPE.move(vec3.x, vec3.y, vec3.z);
    }

    @Override
    protected boolean isCollisionShapeFullBlock(BlockState state, BlockGetter level, BlockPos pos) {
        return false;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        var level = context.getLevel();
        var clickedPos = context.getClickedPos();
        var fluidstate = level.getFluidState(clickedPos);
        if (!fluidstate.isEmpty()) {
            return null;
        }
        var below = clickedPos.below();
        var blockstate = level.getBlockState(below);
        var defaultState = defaultBlockState();
        var soilDecision = blockstate.canSustainPlant(level, below, Direction.UP, defaultState);
        if (soilDecision.isDefault() ? blockstate.is(MalumTags.Blocks.PREFERRED_EBONY_SURFACE) : soilDecision.isTrue()) {
            var sapling = MalumContent.Materials.EBONY_SAPLING.get();
            if (blockstate.is(sapling)) {
                return defaultState.setValue(AGE, 0);
            }
            var ebony = MalumContent.Materials.EBONY_STALK.get();
            if (blockstate.is(ebony)) {
                int i = blockstate.getValue(AGE) > 0 ? 1 : 0;
                return defaultState.setValue(AGE, i);
            }
            BlockState blockstate1 = level.getBlockState(clickedPos.above());
            return blockstate1.is(ebony)
                    ? defaultState.setValue(AGE, blockstate1.getValue(AGE))
                    : sapling.defaultBlockState();


        }
        return null;
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!state.canSurvive(level, pos)) {
            level.destroyBlock(pos, true);
        }
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return state.getValue(STAGE) == 0;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (state.getValue(STAGE) != 0) {
            return;
        }
        if (!level.isEmptyBlock(pos.above()) || level.getRawBrightness(pos.above(), 0) < 9) {
            return;
        }
        int i = getHeightBelowUpToMax(level, pos) + 1;
        if (i < 16 && CommonHooks.canCropGrow(level, pos, state, random.nextInt(3) == 0)) {
            growEbony(state, level, pos, random, i);
            CommonHooks.fireCropGrowPost(level, pos, state);
        }
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        var belowPos = pos.below();
        var belowState = level.getBlockState(belowPos);
        var soilDecision = belowState.canSustainPlant(level, belowPos, Direction.UP, state);
        if (!soilDecision.isDefault()) return soilDecision.isTrue();
        return belowState.is(MalumTags.Blocks.PREFERRED_EBONY_SURFACE);
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (!state.canSurvive(level, pos)) {
            level.scheduleTick(pos, this, 1);
        }

        if (direction == Direction.UP && neighborState.is(MalumContent.Materials.EBONY_STALK.get()) && neighborState.getValue(AGE) > state.getValue(AGE)) {
            level.setBlock(pos, state.cycle(AGE), 2);
        }

        return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        int i = getHeightAboveUpToMax(level, pos);
        int j = getHeightBelowUpToMax(level, pos);
        return i + j + 1 < 16 && level.getBlockState(pos.above(i)).getValue(STAGE) != 1;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        int i = getHeightAboveUpToMax(level, pos);
        int j = getHeightBelowUpToMax(level, pos);
        int k = i + j + 1;
        int l = 1 + random.nextInt(2);

        for (int i1 = 0; i1 < l; i1++) {
            var blockpos = pos.above(i);
            var blockstate = level.getBlockState(blockpos);
            if (k >= 16 || blockstate.getValue(STAGE) == 1 || !level.isEmptyBlock(blockpos.above())) {
                return;
            }

            growEbony(blockstate, level, blockpos, random, k);
            i++;
            k++;
        }
    }

    /**
     * Get the hardness of this Block relative to the ability of the given player
     */
    @Override
    protected float getDestroyProgress(BlockState state, Player player, BlockGetter level, BlockPos pos) {
        return player.getMainHandItem().canPerformAction(net.neoforged.neoforge.common.ItemAbilities.SWORD_DIG) ? 1.0F : super.getDestroyProgress(state, player, level, pos);
    }

    protected void growEbony(BlockState state, Level level, BlockPos pos, RandomSource random, int age) {
        var belowPos = pos.below();
        var belowState = level.getBlockState(belowPos);
        var belowBelowPos = pos.below(2);
        var belowBelowState = level.getBlockState(belowBelowPos);
        var bambooleaves = BambooLeaves.NONE;
        var ebony = MalumContent.Materials.EBONY_STALK.get();
        if (age >= 1) {
            if (!belowState.is(ebony) || belowState.getValue(LEAVES) == BambooLeaves.NONE) {
                bambooleaves = BambooLeaves.SMALL;
            } else if (belowState.is(ebony) && belowState.getValue(LEAVES) != BambooLeaves.NONE) {
                bambooleaves = BambooLeaves.LARGE;
                if (belowBelowState.is(ebony)) {
                    level.setBlock(belowPos, belowState.setValue(LEAVES, BambooLeaves.SMALL), 3);
                    level.setBlock(belowBelowPos, belowBelowState.setValue(LEAVES, BambooLeaves.NONE), 3);
                }
            }
        }

        int i = state.getValue(AGE) != 1 && !belowBelowState.is(ebony) ? 0 : 1;
        int j = (age < 11 || !(random.nextFloat() < 0.25F)) && age != 15 ? 0 : 1;
        level.setBlock(
                pos.above(), defaultBlockState().setValue(AGE, i).setValue(LEAVES, bambooleaves).setValue(STAGE, j), 3
        );
    }

    protected int getHeightAboveUpToMax(BlockGetter level, BlockPos pos) {
        int i = 0;

        while (i < 16 && level.getBlockState(pos.above(i + 1)).is(MalumContent.Materials.EBONY_STALK.get())) {
            i++;
        }

        return i;
    }

    protected int getHeightBelowUpToMax(BlockGetter level, BlockPos pos) {
        int i = 0;

        while (i < 16 && level.getBlockState(pos.below(i + 1)).is(MalumContent.Materials.EBONY_STALK.get())) {
            i++;
        }

        return i;
    }
}