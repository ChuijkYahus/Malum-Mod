package com.sammy.malum.common.block.soulstone;

import com.mojang.serialization.MapCodec;
import com.sammy.malum.common.data.map.SoulstoneOreConversionMap.SoulstoneOreConversion;
import com.sammy.malum.registry.common.MalumDataMaps;
import com.sammy.malum.registry.common.MalumTags;
import com.sammy.malum.registry.common.sound.MalumBlockSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.piston.MovingPistonBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

@SuppressWarnings("NullableProblems")
public class SoulstoneBudBlock extends ArchaicSoulstoneBudBlock {

    public static final MapCodec<SoulstoneBudBlock> CODEC = simpleCodec(SoulstoneBudBlock::new);

    public static final IntegerProperty STAGE = IntegerProperty.create("stage", 0, 3);

    public SoulstoneBudBlock(Properties builder) {
        super(builder.lightLevel(s -> s.getValue(STAGE)*3));
    }

    @Override
    public IntegerProperty getStage() {
        return STAGE;
    }

    @Override
    public SoundType getSoundType(BlockState state, LevelReader level, BlockPos pos, @Nullable Entity entity) {
        if (state.getValue(getStage()) == 3) {
            return MalumBlockSoundEvents.REALIZED_SOULSTONE_BUD;
        }
        return super.getSoundType(state, level, pos, entity);
    }

    @Override
    protected MapCodec<? extends SoulstoneBudBlock> codec() {
        return CODEC;
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        var attachedPos = getAttachedPos(state, pos);
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
