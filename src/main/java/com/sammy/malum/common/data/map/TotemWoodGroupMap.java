package com.sammy.malum.common.data.map;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.sammy.malum.registry.common.MalumDataMaps;
import net.minecraft.world.level.block.state.BlockState;

public record TotemWoodGroupMap(String id) {

    public static final Codec<TotemWoodGroupMap> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("id").forGetter(TotemWoodGroupMap::id)
    ).apply(instance, TotemWoodGroupMap::new));

    public static boolean matches(BlockState state, BlockState other) {
        var data = state.getBlockHolder().getData(MalumDataMaps.TOTEM_WOOD_GROUP);
        var otherData = other.getBlockHolder().getData(MalumDataMaps.TOTEM_WOOD_GROUP);
        if (data == null || otherData == null) {
            return false;
        }
        return data.id.equals(otherData.id);
    }
}