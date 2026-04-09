package com.sammy.malum.common.block.flora;

import com.mojang.serialization.MapCodec;
import com.sammy.malum.registry.common.MalumTags;
import com.sammy.malum.registry.common.content.MalumContent;
import com.sammy.malum.registry.common.content.item.MalumItemProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BambooLeaves;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;


@SuppressWarnings({"NullableProblems", "deprecation"})
public class EbonySaplingBlock extends Block implements BonemealableBlock {

    public static final MapCodec<EbonySaplingBlock> CODEC = simpleCodec(EbonySaplingBlock::new);

    protected static final VoxelShape SAPLING_SHAPE = Block.box(4.0, 0.0, 4.0, 12.0, 12.0, 12.0);

    @Override
    public MapCodec<EbonySaplingBlock> codec() {
        return CODEC;
    }

    public EbonySaplingBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Vec3 vec3 = state.getOffset(level, pos);
        return SAPLING_SHAPE.move(vec3.x, vec3.y, vec3.z);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (random.nextInt(4) > 0) {
            return;
        }
        if (!level.isEmptyBlock(pos.above())) {
            return;
        }
        if (level.getRawBrightness(pos.above(), 0) < 4) {
            return;
        }
        this.growEbony(level, pos);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        var belowPos = pos.below();
        var belowState = level.getBlockState(belowPos);
        var soilDecision = belowState.canSustainPlant(level, belowPos, Direction.UP, state);
        if (!soilDecision.isDefault()) {
            return soilDecision.isTrue();
        }
        return belowState.is(MalumTags.Blocks.EBONY_PLANTABLE_ON);
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        if (!state.canSurvive(level, currentPos)) {
            return net.minecraft.world.level.block.Blocks.AIR.defaultBlockState();
        } else {
            if (facing == Direction.UP && facingState.is(MalumContent.Materials.EBONY_STALK)) {
                level.setBlock(currentPos, MalumContent.Materials.EBONY_STALK.get().defaultBlockState(), 2);
            }

            return super.updateShape(state, facing, facingState, level, currentPos, facingPos);
        }
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
        return new ItemStack(MalumItemProperties.EBONY_STALK.get());
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return level.getBlockState(pos.above()).isAir();
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        this.growEbony(level, pos);
    }

    /**
     * Get the hardness of this Block relative to the ability of the given player
     */
    @Override
    protected float getDestroyProgress(BlockState state, Player player, BlockGetter level, BlockPos pos) {
        return player.getMainHandItem().canPerformAction(net.neoforged.neoforge.common.ItemAbilities.SWORD_DIG) ? 1.0F : super.getDestroyProgress(state, player, level, pos);
    }

    protected void growEbony(Level level, BlockPos state) {
        level.setBlock(state.above(), MalumContent.Materials.EBONY_STALK.get().defaultBlockState().setValue(EbonyStalkBlock.LEAVES, BambooLeaves.SMALL), 3);
    }
}