package com.sammy.malum.common.worldgen.sanctuary.feature;

import net.minecraft.core.*;
import net.minecraft.util.*;
import net.minecraft.world.level.levelgen.feature.*;
import team.lodestar.lodestone.systems.worldgen.*;

import static com.sammy.malum.common.worldgen.WorldgenHelper.*;
import static com.sammy.malum.common.worldgen.sanctuary.feature.SanctuaryPillarFeature.findStartingY;

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
        var block = config.block().defaultBlockState();
        var topBlock = config.topBlock().defaultBlockState();
        var filling = config.wallFilling().defaultBlockState();

        var builder = LodestoneWorldgenBuilder.create();
        var layer = builder.createLayer();

        int wallWidth = Mth.nextInt(rand, config.minWidth(), config.maxWidth());

        var wallDirection = Direction.from2DDataValue(rand.nextInt(4));
        var mutable = new BlockPos.MutableBlockPos().set(pos);

        if (!findStartingY(level, mutable)) {
            return false;
        }
        for (int i = 0; i <= wallWidth; i++) {
            mutable.set(pos);
            mutable.move(wallDirection, i);
            if (!findStartingY(level, mutable)) {
                return false;
            }
            int wallHeight = Mth.nextInt(rand, config.minHeight(), config.maxHeight());
            if (i == 0 || i == wallWidth) {
                wallHeight += 2;
            }
            for (int j = 0; j <= wallHeight; j++) {
                if (!canPlace(level, mutable)) {
                    return false;
                }
                var toPlace = i > 0 && i < wallWidth
                        ? filling
                        : j == wallHeight ? topBlock : block;
                layer.add(mutable, toPlace);
                mutable.move(Direction.UP);
            }
        }
        builder.place(level);
        return true;
    }
}