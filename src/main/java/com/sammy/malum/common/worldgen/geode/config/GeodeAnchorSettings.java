package com.sammy.malum.common.worldgen.geode.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.FloatProvider;
import net.minecraft.util.valueproviders.IntProvider;

import java.util.List;

import static net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider.simple;

public record GeodeAnchorSettings(List<GeodeAnchor> anchors) {

    public static final Codec<GeodeAnchorSettings> CODEC = RecordCodecBuilder.create(
            obj -> obj.group(
                    GeodeAnchor.CODEC.listOf().fieldOf("anchors").forGetter(GeodeAnchorSettings::anchors)
            ).apply(obj, GeodeAnchorSettings::new)
    );

    public record GeodeAnchor(IntProvider anchorOffset, FloatProvider noiseIntensity, FloatProvider scale) {

        public static final Codec<GeodeAnchor> CODEC = RecordCodecBuilder.create(
                obj -> obj.group(
                        IntProvider.codec(0, 32).fieldOf("anchor_offset").forGetter(GeodeAnchor::anchorOffset),
                        FloatProvider.codec(0, 4).fieldOf("anchor_noise").forGetter(GeodeAnchor::noiseIntensity),
                        FloatProvider.codec(0.01f, 32).fieldOf("scale").forGetter(GeodeAnchor::scale)
                ).apply(obj, GeodeAnchor::new)
        );
    }
}
