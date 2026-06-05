package com.sammy.malum.common.worldgen.sanctuary.feature;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import net.minecraft.core.registries.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.levelgen.feature.configurations.*;

import java.util.List;

public record SanctuaryPillarFeatureConfiguration(List<SanctuaryWallFeatureConfiguration.SegmentData> pillar) implements FeatureConfiguration {

    public static final Codec<SanctuaryPillarFeatureConfiguration> CODEC =
            RecordCodecBuilder.create(inst -> inst.group(
                    SanctuaryWallFeatureConfiguration.SegmentData.CODEC.listOf().fieldOf("pillar").forGetter(SanctuaryPillarFeatureConfiguration::pillar)
            ).apply(inst, SanctuaryPillarFeatureConfiguration::new));
}