package com.sammy.malum.common.worldgen.sanctuary.feature;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import net.minecraft.core.registries.*;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

import java.util.List;

public record SanctuaryWallFeatureConfiguration(List<SegmentData> pillars, List<SegmentData> wall, int minWidth, int maxWidth) implements FeatureConfiguration {

    public static final Codec<SanctuaryWallFeatureConfiguration> CODEC =
            RecordCodecBuilder.create(inst -> inst.group(
                    SegmentData.CODEC.listOf().fieldOf("pillars").forGetter(SanctuaryWallFeatureConfiguration::pillars),
                    SegmentData.CODEC.listOf().fieldOf("wall").forGetter(SanctuaryWallFeatureConfiguration::wall),

                    Codec.INT.fieldOf("minWidth").forGetter(obj -> obj.minWidth),
                    Codec.INT.fieldOf("maxWidth").forGetter(obj -> obj.maxWidth)
            ).apply(inst, SanctuaryWallFeatureConfiguration::new));

    public record SegmentData(BlockStateProvider block, int minHeight, int maxHeight) {

        public static final Codec<SegmentData> CODEC =
                RecordCodecBuilder.create(inst -> inst.group(
                        BlockStateProvider.CODEC.fieldOf("block").forGetter(SegmentData::block),
                        Codec.INT.fieldOf("minHeight").forGetter(SegmentData::minHeight),
                        Codec.INT.fieldOf("maxHeight").forGetter(SegmentData::maxHeight)
                ).apply(inst, SegmentData::new));


        public int rollHeight(RandomSource randomSource) {
            if (minHeight > maxHeight) {
                return minHeight;
            }
            return randomSource.nextInt(minHeight, maxHeight);
        }
    }
}