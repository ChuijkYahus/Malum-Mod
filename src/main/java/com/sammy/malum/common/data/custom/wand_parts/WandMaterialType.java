package com.sammy.malum.common.data.custom.wand_parts;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;

public record WandMaterialType(ResourceLocation id, Ingredient ingredient, List<WandPartType.WandPartGroup> validGroups) {

    public static final Codec<WandMaterialType> DIRECT_CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ResourceLocation.CODEC.fieldOf("id").forGetter(WandMaterialType::id),
            Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(WandMaterialType::ingredient),
            WandPartType.GROUP_CODEC.listOf().fieldOf("validGroups").forGetter(WandMaterialType::validGroups)
    ).apply(instance, WandMaterialType::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, WandMaterialType> STREAM_CODEC =
            StreamCodec.composite(
                    ResourceLocation.STREAM_CODEC, WandMaterialType::id,
                    Ingredient.CONTENTS_STREAM_CODEC, WandMaterialType::ingredient,
                    WandPartType.LIST_STREAM_GROUP_CODEC, WandMaterialType::validGroups,
                    WandMaterialType::new
            );

    public static final StreamCodec<RegistryFriendlyByteBuf, List<WandMaterialType>> LIST_STREAM_CODEC = STREAM_CODEC.apply(ByteBufCodecs.list());


}