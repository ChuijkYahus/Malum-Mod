package com.sammy.malum.common.worldgen.sanctuary.feature;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import net.minecraft.core.registries.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.levelgen.feature.configurations.*;

public record SanctuaryWallFeatureConfiguration(Block block, Block topBlock, Block wallFilling, int minHeight, int maxHeight, int minWidth, int maxWidth) implements FeatureConfiguration {

    public static final Codec<SanctuaryWallFeatureConfiguration> CODEC =
            RecordCodecBuilder.create(inst -> inst.group(
                    BuiltInRegistries.BLOCK.byNameCodec().fieldOf("block").forGetter(obj -> obj.block),
                    BuiltInRegistries.BLOCK.byNameCodec().fieldOf("topBlock").forGetter(obj -> obj.topBlock),
                    BuiltInRegistries.BLOCK.byNameCodec().fieldOf("wallFilling").forGetter(obj -> obj.wallFilling),
                    Codec.INT.fieldOf("minHeight").forGetter(obj -> obj.minHeight),
                    Codec.INT.fieldOf("maxHeight").forGetter(obj -> obj.maxHeight),
                    Codec.INT.fieldOf("minWidth").forGetter(obj -> obj.minWidth),
                    Codec.INT.fieldOf("maxWidth").forGetter(obj -> obj.maxWidth)
            ).apply(inst, SanctuaryWallFeatureConfiguration::new));
}