package com.sammy.malum.common.block.soulstone;

import com.mojang.serialization.MapCodec;
import com.sammy.malum.common.data.map.SoulstoneOreConversionMap;
import com.sammy.malum.common.data.map.SoulstoneOreConversionMap.SoulstoneOreConversion;
import com.sammy.malum.datagen.MalumDataMapDatagen;
import com.sammy.malum.registry.common.MalumDataMaps;
import com.sammy.malum.registry.common.MalumTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import team.lodestar.lodestone.modules.toolkit.block.LodestoneDirectionalBlock;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("NullableProblems")
public class SoulstoneBudBlock extends LodestoneDirectionalBlock {

    public static final MapCodec<SoulstoneBudBlock> CODEC = simpleCodec(SoulstoneBudBlock::new);

    public SoulstoneBudBlock(Properties builder) {
        super(builder);
    }

    @Override
    protected MapCodec<? extends SoulstoneBudBlock> codec() {
        return CODEC;
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return getAttachedState(level, pos, state).is(MalumTags.Blocks.SOULSTONE_BUD_PLANTABLE_ON);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        var direction = context.getClickedFace();
        var pos = context.getClickedPos();
        var attachedState = context.getLevel().getBlockState(pos);
        if (attachedState.is(MalumTags.Blocks.SOULSTONE_BUD_PLANTABLE_ON)) {
            return defaultBlockState().setValue(FACING, direction);
        }
        return null;
    }

    public BlockState getAttachedState(LevelReader level, BlockPos pos, BlockState state) {
        var direction = state.getValue(FACING).getOpposite();
        var blockpos = pos.relative(direction);
        return level.getBlockState(blockpos);
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
