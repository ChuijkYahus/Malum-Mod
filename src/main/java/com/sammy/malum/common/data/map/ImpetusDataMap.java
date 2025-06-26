package com.sammy.malum.common.data.map;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public record ImpetusDataMap(Holder<Item> otherImpetus) {
    public static final Codec<ImpetusDataMap> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ItemStack.ITEM_NON_AIR_CODEC.fieldOf("other_impetus").forGetter(ImpetusDataMap::otherImpetus)
    ).apply(instance, ImpetusDataMap::new));
}