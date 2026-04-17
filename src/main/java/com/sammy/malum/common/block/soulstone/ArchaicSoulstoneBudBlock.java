package com.sammy.malum.common.block.soulstone;

import com.mojang.serialization.MapCodec;
import com.sammy.malum.common.data.map.SoulstoneOreConversionMap.SoulstoneOreConversion;
import com.sammy.malum.registry.common.MalumDataMaps;
import com.sammy.malum.registry.common.MalumTags;
import com.sammy.malum.registry.common.sound.MalumBlockSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.piston.MovingPistonBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import team.lodestar.lodestone.modules.toolkit.block.LodestoneDirectionalBlock;

import java.util.Optional;

import static com.sammy.malum.common.block.soulstone.SoulstoneBudBlock.*;

@SuppressWarnings("NullableProblems")
public class ArchaicSoulstoneBudBlock extends LodestoneDirectionalBlock {

    public static final MapCodec<ArchaicSoulstoneBudBlock> CODEC = simpleCodec(ArchaicSoulstoneBudBlock::new);

    public static final IntegerProperty STAGE = IntegerProperty.create("stage", 0, 2);

    public ArchaicSoulstoneBudBlock(Properties builder) {
        super(builder);
        registerDefaultState(defaultBlockState().setValue(getStage(), 0));
    }

    public IntegerProperty getStage() {
        return STAGE;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        int stage = state.getValue(getStage());
        var offset = state.getOffset(level, pos);
        return SHAPES[stage].move(offset.x, offset.y, offset.z);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(getStage());
    }

    @Override
    protected MapCodec<? extends ArchaicSoulstoneBudBlock> codec() {
        return CODEC;
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        var attachedPos = getAttachedPos(state, pos);
        return Block.canSupportCenter(level, attachedPos, state.getValue(FACING));
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        return !state.canSurvive(level, currentPos)
                ? Blocks.AIR.defaultBlockState()
                : super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }

    public BlockState getAttachedState(LevelReader level, BlockState state, BlockPos pos) {
        var attachedTo = getAttachedPos(state, pos);
        return level.getBlockState(attachedTo);
    }

    public BlockPos getAttachedPos(BlockState state, BlockPos pos) {
        var direction = state.getValue(FACING).getOpposite();
        return pos.relative(direction);
    }
}