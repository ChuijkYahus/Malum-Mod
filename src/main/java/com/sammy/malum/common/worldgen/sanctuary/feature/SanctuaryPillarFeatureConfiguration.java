package com.sammy.malum.common.worldgen.sanctuary.feature;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import net.minecraft.core.registries.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.levelgen.feature.configurations.*;

public record SanctuaryPillarFeatureConfiguration(Block block, Block topBlock, int minHeight, int maxHeight) implements FeatureConfiguration {

    public static final Codec<SanctuaryPillarFeatureConfiguration> CODEC =
            RecordCodecBuilder.create(inst -> inst.group(
                    BuiltInRegistries.BLOCK.byNameCodec().fieldOf("block").forGetter(obj -> obj.block),
                    BuiltInRegistries.BLOCK.byNameCodec().fieldOf("topBlock").forGetter(obj -> obj.topBlock),
                    Codec.INT.fieldOf("minHeight").forGetter(obj -> obj.minHeight),
                    Codec.INT.fieldOf("maxHeight").forGetter(obj -> obj.maxHeight)
            ).apply(inst, SanctuaryPillarFeatureConfiguration::new));
}