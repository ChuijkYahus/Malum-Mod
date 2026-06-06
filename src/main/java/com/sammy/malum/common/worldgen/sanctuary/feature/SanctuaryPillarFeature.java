package com.sammy.malum.common.worldgen.sanctuary.feature;

import net.minecraft.core.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.levelgen.feature.*;
import team.lodestar.lodestone.systems.worldgen.*;

import java.util.List;

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
        var data = config.pillar();
        var builder = LodestoneWorldgenBuilder.create();
        var layer = builder.createLayer();

        var mutable = new BlockPos.MutableBlockPos().set(pos);
        if (!findPillarRoot(level, mutable)) {
            return false;
        }

        generatePillar(level, layer, data, mutable);
        builder.place(level);
        return true;
    }

    public static void generatePillar(WorldGenLevel level, LodestoneWorldgenBuilderLayer layer, List<SanctuaryWallFeatureConfiguration.SegmentData> segments, BlockPos rootPos) {
        var random = level.getRandom();
        var mutable = rootPos.mutable();

        for (SanctuaryWallFeatureConfiguration.SegmentData segment : segments) {
            int i = segment.rollHeight(random);
            for (int j = 0; j < i; j++) {
                mutable.move(Direction.UP);
                var state = segment.block().getState(random, mutable);
                layer.add(mutable, state);
            }
        }
    }

    public static boolean findPillarRoot(WorldGenLevel level, BlockPos.MutableBlockPos mutable) {
        mutable.move(Direction.DOWN, 8);
        for (int i = 0; i < 16; i++) {
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