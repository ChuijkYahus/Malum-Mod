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
import net.minecraft.util.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.piston.MovingPistonBlock;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.phys.*;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import team.lodestar.lodestone.modules.toolkit.block.*;

import java.util.Optional;

import static com.sammy.malum.common.block.soulstone.SoulstoneBudBlock.*;

@SuppressWarnings("NullableProblems")
public class ArchaicSoulstoneBudBlock extends LodestoneDirectionalBlock {

    public static final MapCodec<ArchaicSoulstoneBudBlock> CODEC = simpleCodec(ArchaicSoulstoneBudBlock::new);

    public static final IntegerProperty STAGE = IntegerProperty.create("stage", 0, 2);

    public static final VoxelShapeRotator[] SHAPES = new VoxelShapeRotator[]{
            new VoxelShapeRotator(box(5, 0, 5, 11, 6, 11)),
            new VoxelShapeRotator(box(4, 0, 4, 12, 8, 12)),
            new VoxelShapeRotator(box(3, 0, 3, 13, 10, 13)),
            new VoxelShapeRotator(box(1, 0, 1, 15, 14, 15))
    };

    public static final BlockBehaviour.OffsetFunction BUD_OFFSET = (state, level, pos) -> {
        var block = state.getBlock();
        var direction = state.getValue(FACING);
        long i = Mth.getSeed(pos.getX(), 0, pos.getZ());
        float w = block.getMaxHorizontalOffset();
        float h = block.getMaxVerticalOffset();

        float x = (i & 15L) / 15f;
        float y = (i >> 4 & 15L) / 15f;
        float z = (i >> 8 & 15L) / 15f;
        x = (x - 0.5f) * 0.5f;
        y = (y - 0.5f) * h;
        z = (z - 0.5f) * 0.5f;
        x = Mth.clamp(x, -w, w);
        y = Mth.clamp(y, -h, 0);
        z = Mth.clamp(z, -w, w);

        float cachedX = x;
        float cachedZ = z;

        switch (direction) {
            case DOWN -> y *= -1;

            case NORTH -> {
                z = -y;
                y = cachedZ;
            }
            case SOUTH -> {
                z = y;
                y = cachedZ;
            }
            case WEST -> {
                x = -y;
                y = cachedX;
            }
            case EAST -> {
                x = y;
                y = cachedX;
            }
        }
        return new Vec3(x, y, z);
    };

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
        return SHAPES[stage].getShape(state).move(offset.x, offset.y, offset.z);
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
        if (!Block.canSupportCenter(level, attachedPos, state.getValue(FACING))) {
            return false;
        }
        var attachedState = level.getBlockState(attachedPos);
        return attachedState.is(MalumTags.Blocks.NATURAL_SOULSTONE_BUD_SURFACE);
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