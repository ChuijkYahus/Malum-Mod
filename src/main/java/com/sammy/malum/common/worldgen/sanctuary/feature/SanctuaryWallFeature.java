package com.sammy.malum.common.worldgen.sanctuary.feature;

import net.minecraft.core.*;
import net.minecraft.util.*;
import net.minecraft.world.level.levelgen.feature.*;
import team.lodestar.lodestone.systems.worldgen.*;

import static com.sammy.malum.common.worldgen.sanctuary.feature.SanctuaryPillarFeature.findPillarRoot;
import static com.sammy.malum.common.worldgen.sanctuary.feature.SanctuaryPillarFeature.generatePillar;

public class SanctuaryWallFeature extends Feature<SanctuaryWallFeatureConfiguration> {

    public SanctuaryWallFeature() {
        super(SanctuaryWallFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<SanctuaryWallFeatureConfiguration> context) {
        var level = context.level();
        var pos = context.origin();
        var config = context.config();
        if (level.isEmptyBlock(pos.below())) {
            return false;
        }
        var rand = context.random();
        var pillarData = config.pillars();
        var wallData = config.wall();

        var builder = LodestoneWorldgenBuilder.create();
        var layer = builder.createLayer();

        int wallWidth = Mth.nextInt(rand, config.minWidth(), config.maxWidth());

        var wallDirection = Direction.from2DDataValue(rand.nextInt(4));
        var mutable = new BlockPos.MutableBlockPos().set(pos);

        if (!findPillarRoot(level, mutable)) {
            return false;
        }
        for (int i = 0; i <= wallWidth; i++) {
            mutable.set(pos);
            mutable.move(wallDirection, i);
            if (!findPillarRoot(level, mutable)) {
                return false;
            }
            boolean isWall = i > 0 && i < wallWidth;
            var usedData = isWall ? wallData : pillarData;
            generatePillar(level, layer, usedData, mutable);
        }
        builder.place(level);
        return true;
    }
}