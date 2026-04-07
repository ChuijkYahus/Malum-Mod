package com.sammy.malum.common.worldgen.ore;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public record DistributedOreFeatureConfiguration(Holder<ConfiguredFeature<?, ?>> distributedFeature, int height, int minDistributions, int maxDistributions) implements FeatureConfiguration {
    public static final Codec<DistributedOreFeatureConfiguration> CODEC = RecordCodecBuilder.create(obj -> obj.group(
                            ConfiguredFeature.CODEC.fieldOf("feature").forGetter(DistributedOreFeatureConfiguration::distributedFeature),
                            Codec.INT.fieldOf("height").forGetter(DistributedOreFeatureConfiguration::height),
                            Codec.INT.fieldOf("minDistributions").forGetter(DistributedOreFeatureConfiguration::minDistributions),
                            Codec.INT.fieldOf("maxDistributions").forGetter(DistributedOreFeatureConfiguration::maxDistributions)
                    )
                    .apply(obj, DistributedOreFeatureConfiguration::new)
    );
}