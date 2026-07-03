package com.sammy.malum.common.block.soulstone;

import com.sammy.malum.common.data.map.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.core.*;
import net.minecraft.util.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;
import net.minecraft.world.phys.*;
import team.lodestar.lodestone.modules.toolkit.block.*;

import java.util.*;

import static net.minecraft.world.level.block.Block.box;

public class SoulstoneBudCommons {
    public static final VoxelShapeRotator[] SHAPES = new VoxelShapeRotator[]{
            new VoxelShapeRotator(box(5, 0, 5, 11, 6, 11)),
            new VoxelShapeRotator(box(4, 0, 4, 12, 8, 12)),
            new VoxelShapeRotator(box(3, 0, 3, 13, 10, 13)),
            new VoxelShapeRotator(box(1, 0, 1, 15, 14, 15))
    };
    public static final BlockBehaviour.OffsetFunction BUD_OFFSET = (state, level, pos) -> {
        var block = state.getBlock();
        var direction = state.getValue(DirectionalBlock.FACING);
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

    public static SoulstoneOreConversionMap.SoulstoneOreConversion getConversionById(Level level, RandomSource random, BlockState state) {

        var conversion = state.getBlockHolder().getData(MalumDataMaps.SOULSTONE_ORE_CONVERSION);
        if (conversion == null) {
            return null;
        }
        var conversions = conversion.possibleConversions();
        for (SoulstoneOreConversionMap.SoulstoneOreConversion possibleConversion : conversions) {
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

    public static SoulstoneOreConversionMap.SoulstoneOreConversion getValidConversion(RandomSource random, BlockState state) {
        var conversion = state.getBlockHolder().getData(MalumDataMaps.SOULSTONE_ORE_CONVERSION);
        if (conversion == null) {
            return null;
        }
        var conversions = conversion.possibleConversions();
        for (SoulstoneOreConversionMap.SoulstoneOreConversion possibleConversion : conversions) {
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

    public static BlockPos getAttachedPos(BlockState state, BlockPos pos) {
        var direction = state.getValue(DirectionalBlock.FACING).getOpposite();
        return pos.relative(direction);
    }
}