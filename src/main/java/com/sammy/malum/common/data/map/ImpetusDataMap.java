package com.sammy.malum.common.data.map;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public record ImpetusDataMap(Holder<Item> fracturedImpetus) {
    public static final Codec<ImpetusDataMap> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ItemStack.ITEM_NON_AIR_CODEC.fieldOf("fractured_impetus").forGetter(ImpetusDataMap::fracturedImpetus)
    ).apply(instance, ImpetusDataMap::new));
}