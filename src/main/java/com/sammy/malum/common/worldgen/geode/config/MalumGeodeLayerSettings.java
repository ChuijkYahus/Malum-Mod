package com.sammy.malum.common.worldgen.geode.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import java.util.List;

import static net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider.simple;

public record MalumGeodeLayerSettings(List<GeodeLayer> layers, TagKey<Block> cannotReplace, TagKey<Block> invalidBlocks) {
    public static final Codec<MalumGeodeLayerSettings> CODEC = RecordCodecBuilder.create(
            obj -> obj.group(
                            GeodeLayer.CODEC.listOf().fieldOf("layers").forGetter(MalumGeodeLayerSettings::layers),
                            TagKey.hashedCodec(Registries.BLOCK).fieldOf("cannot_replace").forGetter(MalumGeodeLayerSettings::cannotReplace),
                            TagKey.hashedCodec(Registries.BLOCK).fieldOf("invalid_blocks").forGetter(MalumGeodeLayerSettings::invalidBlocks)
                    )
                    .apply(obj, MalumGeodeLayerSettings::new)
    );
}
