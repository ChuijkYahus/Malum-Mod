package com.sammy.malum.common.data.map;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;

public record BlockCarvingMap(Holder<Block> carvedVariant) {
    public static final Codec<BlockCarvingMap> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BuiltInRegistries.BLOCK.holderByNameCodec().fieldOf("carvedVariant").forGetter(BlockCarvingMap::carvedVariant)
    ).apply(instance, BlockCarvingMap::new));
}