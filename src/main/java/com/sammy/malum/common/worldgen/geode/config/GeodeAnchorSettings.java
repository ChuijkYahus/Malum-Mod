package com.sammy.malum.common.worldgen.geode.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.List;

import static net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider.simple;

public record GeodeAnchorSettings(List<GeodeAnchor> anchors) {

    public static final Codec<GeodeAnchorSettings> CODEC = RecordCodecBuilder.create(
            obj -> obj.group(
                    GeodeAnchor.CODEC.listOf().fieldOf("anchors").forGetter(GeodeAnchorSettings::anchors)
            ).apply(obj, GeodeAnchorSettings::new)
    );

}
