package com.sammy.malum.common.worldgen.sanctuary.feature;

import net.minecraft.core.*;
import net.minecraft.util.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.levelgen.feature.*;
import team.lodestar.lodestone.systems.worldgen.*;

import static com.sammy.malum.common.worldgen.WorldgenHelper.*;

public class SanctuaryPillarFeature extends Feature<SanctuaryPillarFeatureConfiguration> {

    public SanctuaryPillarFeature() {
        super(SanctuaryPillarFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<SanctuaryPillarFeatureConfiguration> context) {
        var level = context.level();
        var pos = context.origin();
        var config = context.config();
        if (level.isEmptyBlock(pos.below())) {
            return false;
        }
        var rand = context.random();
        var block = config.block().defaultBlockState();
        var topBlock = config.topBlock().defaultBlockState();

        var builder = LodestoneWorldgenBuilder.create();
        var layer = builder.createLayer();

        int pillarHeight = Mth.nextInt(rand, config.minHeight(), config.maxHeight());

        var mutable = new BlockPos.MutableBlockPos().set(pos);
        if (!findStartingY(level, mutable)) {
            return false;
        }
        for (int i = 0; i <= pillarHeight; i++) {
            if (!canPlace(level, mutable)) {
                return false;
            }
            layer.add(mutable, i == pillarHeight ? topBlock : block);
            mutable.move(Direction.UP);
        }
        builder.place(level);
        return true;
    }

    public static boolean findStartingY(WorldGenLevel level, BlockPos.MutableBlockPos mutable) {
        mutable.move(Direction.DOWN, 5);
        for (int i = 0; i < 10; i++) {
            if (canPlace(level, mutable)) {
                var belowPos = mutable.below();
                if (Block.canSupportCenter(level, belowPos, Direction.UP)) {
                    return true;
                }
            }
            mutable.move(Direction.UP);
        }
        return false;
    }
}