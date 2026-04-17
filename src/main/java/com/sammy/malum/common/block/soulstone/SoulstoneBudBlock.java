package com.sammy.malum.common.block.soulstone;

import com.mojang.serialization.MapCodec;
import com.sammy.malum.common.data.map.SoulstoneOreConversionMap.SoulstoneOreConversion;
import com.sammy.malum.registry.common.MalumDataMaps;
import com.sammy.malum.registry.common.MalumTags;
import com.sammy.malum.registry.common.sound.MalumBlockSoundEvents;
import com.sammy.malum.registry.common.sound.MalumSoundEvents;
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
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import team.lodestar.lodestone.modules.toolkit.block.LodestoneDirectionalBlock;

import java.util.Optional;

@SuppressWarnings("NullableProblems")
public class SoulstoneBudBlock extends LodestoneDirectionalBlock {

    public static final MapCodec<SoulstoneBudBlock> CODEC = simpleCodec(SoulstoneBudBlock::new);

    public static final IntegerProperty STAGE = IntegerProperty.create("stage", 0, 3);

    public static final VoxelShape[] SHAPES = new VoxelShape[] {
            Shapes.box(5/16f, 0, 5/16f, 11/16f, 6/16f, 11/16f),
            Shapes.box(4/16f, 0, 4/16f, 12/16f, 8/16f, 12/16f),
            Shapes.box(3/16f, 0, 3/16f, 13/16f, 10/16f, 13/16f),
            Shapes.box(1/16f, 0, 1/16f, 15/16f, 14/16f, 15/16f)
    };

    public SoulstoneBudBlock(Properties builder) {
        super(builder.lightLevel(s -> s.getValue(STAGE)*3));
        registerDefaultState(defaultBlockState().setValue(STAGE, 0));
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        int stage = state.getValue(STAGE);
        var offset = state.getOffset(level, pos);
        return SHAPES[stage].move(offset.x, offset.y, offset.z);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(STAGE);
    }

    @Override
    public SoundType getSoundType(BlockState state, LevelReader level, BlockPos pos, @Nullable Entity entity) {
        if (state.getValue(STAGE) == 3) {
            return MalumBlockSoundEvents.MATURE_SOULSTONE_BUD;
        }
        return super.getSoundType(state, level, pos, entity);
    }

    @Override
    protected MapCodec<? extends SoulstoneBudBlock> codec() {
        return CODEC;
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockState attachedState = getAttachedState(level, state, pos);
        if (attachedState.getBlock() instanceof MovingPistonBlock) {
            return true;
        }
        return attachedState.is(MalumTags.Blocks.SOULSTONE_BUD_PLANTABLE_ON);
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        return !state.canSurvive(level, currentPos)
                ? Blocks.AIR.defaultBlockState()
                : super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        var direction = context.getClickedFace();
        var pos = context.getClickedPos().relative(direction.getOpposite());
        var attachedState = context.getLevel().getBlockState(pos);
        if (attachedState.is(MalumTags.Blocks.SOULSTONE_BUD_PLANTABLE_ON)) {
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
            var attachedTo = getAttachedPos(state, pos);
            var attachedState = getAttachedState(level, state, pos);
            SoulstoneOreConversion conversion = getValidConversion(random, attachedState);
            if (conversion == null) {
                return;
            }

            level.levelEvent(2001, attachedTo, Block.getId(attachedState));
            level.setBlock(attachedTo, conversion.result(), 3);

            int stage = state.getValue(STAGE);
            var sound = stage == 2 ? MalumBlockSoundEvents.SOULSTONE_BUD_FULLY_MATURES : MalumBlockSoundEvents.SOULSTONE_BUD_GROWS;
            level.playSound(null, pos.getX(), pos.getY(), pos.getZ(), sound.get(), SoundSource.BLOCKS);
            level.setBlock(pos, state.setValue(STAGE, stage+1), 3);
        }
        super.randomTick(state, level, pos, random);
    }

    public BlockState getAttachedState(LevelReader level, BlockState state, BlockPos pos) {
        var attachedTo = getAttachedPos(state, pos);
        return level.getBlockState(attachedTo);
    }

    public BlockPos getAttachedPos(BlockState state, BlockPos pos) {
        var direction = state.getValue(FACING).getOpposite();
        return pos.relative(direction);
    }

    public SoulstoneOreConversion getValidConversion(RandomSource random, BlockState state) {
        var conversion = state.getBlockHolder().getData(MalumDataMaps.SOULSTONE_ORE_CONVERSION);
        if (conversion == null) {
            return null;
        }
        var conversions = conversion.possibleConversions();
        for (SoulstoneOreConversion possibleConversion : conversions) {
            Optional<RuleTest> optional = possibleConversion.condition();
            if (optional.isEmpty()) {
                return possibleConversion;
            }
            var condition = optional.get();
            if (condition.test(state, random)) {
                return possibleConversion;
            }
        }
        return null;
    }
}
