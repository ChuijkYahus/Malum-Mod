package com.sammy.malum.common.worldgen.ore;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class DistributedOreFeature extends Feature<DistributedOreFeatureConfiguration> {
    public DistributedOreFeature() {
        super(DistributedOreFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<DistributedOreFeatureConfiguration> context) {
        return false;
    }
}
