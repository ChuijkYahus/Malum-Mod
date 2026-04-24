package com.sammy.malum.common.block.soulstone;

import com.sammy.malum.registry.common.MalumTags;
import com.sammy.malum.registry.common.sound.MalumBlockSoundEvents;
import net.minecraft.core.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.piston.MovingPistonBlock;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.phys.shapes.*;
import org.jetbrains.annotations.Nullable;
import team.lodestar.lodestone.modules.toolkit.block.*;

import static com.sammy.malum.common.block.soulstone.ArchaicSoulstoneBudBlock.*;

@SuppressWarnings("NullableProblems")
public class SoulstoneBudBlock extends LodestoneEntityBlock<SoulstoneBudBlockEntity> {

    public static final DirectionProperty FACING = BlockStateProperties.FACING;

    public static final IntegerProperty STAGE = IntegerProperty.create("stage", 0, 3);

    public SoulstoneBudBlock(Properties builder) {
        super(builder.lightLevel(s -> s.getValue(STAGE) * 3));
        registerDefaultState(defaultBlockState().setValue(STAGE, 0));
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        return !state.canSurvive(level, currentPos)
                ? Blocks.AIR.defaultBlockState()
                : super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        int stage = state.getValue(STAGE);
        var offset = state.getOffset(level, pos);
        return SoulstoneBudCommons.SHAPES[stage].getShape(state).move(offset.x, offset.y, offset.z);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(STAGE);
        builder.add(FACING);
    }

    @Override
    public SoundType getSoundType(BlockState state, LevelReader level, BlockPos pos, @Nullable Entity entity) {
        if (state.getValue(STAGE) == 3) {
            return MalumBlockSoundEvents.REALIZED_SOULSTONE_BUD;
        }
        return super.getSoundType(state, level, pos, entity);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        var attachedPos = SoulstoneBudCommons.getAttachedPos(state, pos);
        var attachedState = level.getBlockState(attachedPos);
        if (attachedState.getBlock() instanceof MovingPistonBlock) {
            return true;
        }
        if (!Block.canSupportCenter(level, attachedPos, state.getValue(FACING))) {
            return false;
        }
        return attachedState.is(MalumTags.Blocks.PREFERRED_SOULSTONE_BUD_SURFACE);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        var direction = context.getClickedFace();
        var pos = context.getClickedPos().relative(direction.getOpposite());
        var attachedState = context.getLevel().getBlockState(pos);
        if (attachedState.is(MalumTags.Blocks.PREFERRED_SOULSTONE_BUD_SURFACE)) {
            return defaultBlockState().setValue(FACING, direction);
        }
        return null;
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return state.getValue(STAGE) < 3;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (level.getRandom().nextFloat() < 0.5f) {
            var attachedPos = SoulstoneBudCommons.getAttachedPos(state, pos);
            var attachedState = level.getBlockState(attachedPos);
            var conversion = SoulstoneBudCommons.getValidConversion(random, attachedState);
            if (conversion == null) {
                return;
            }

            level.levelEvent(2001, attachedPos, Block.getId(attachedState));
            level.setBlock(attachedPos, conversion.result(), 3);

            int stage = state.getValue(STAGE);
            var sound = stage == 2 ? MalumBlockSoundEvents.SOULSTONE_BUD_FULLY_MATURES : MalumBlockSoundEvents.SOULSTONE_BUD_GROWS;
            level.playSound(null, pos.getX(), pos.getY(), pos.getZ(), sound.get(), SoundSource.BLOCKS);
            level.setBlock(pos, state.setValue(STAGE, stage + 1), 3);

            if (level.getBlockEntity(pos) instanceof SoulstoneBudBlockEntity blockEntity) {
                blockEntity.budData = blockEntity.budData.addMetal(conversion.metalData());
            }
        }
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.setValue(FACING, mirrorIn.mirror(state.getValue(FACING)));
    }
}