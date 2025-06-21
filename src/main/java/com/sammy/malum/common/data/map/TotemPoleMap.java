package com.sammy.malum.common.data.map;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;

public record TotemPoleMap(Holder<Block> totemPoleVariant) {
    public static final Codec<TotemPoleMap> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BuiltInRegistries.BLOCK.holderByNameCodec().fieldOf("totemPoleVariant").forGetter(TotemPoleMap::totemPoleVariant)
    ).apply(instance, TotemPoleMap::new));
}