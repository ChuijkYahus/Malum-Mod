package com.sammy.malum.common.recipe.spirit_repair;

import com.google.common.collect.*;
import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;

import java.util.*;

public record SpiritRepairRegexData(String modIdRegex, String itemIdRegex) {

    public static final Codec<SpiritRepairRegexData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("modIdRegex").forGetter(SpiritRepairRegexData::modIdRegex),
            Codec.STRING.fieldOf("itemIdRegex").forGetter(SpiritRepairRegexData::itemIdRegex)
    ).apply(instance, SpiritRepairRegexData::new));

    public static final SpiritRepairRegexData EMPTY = new SpiritRepairRegexData("", "");

    public static SpiritRepairRegexData fromMod(String modIdRegex, String itemIdRegex) {
        return new SpiritRepairRegexData(itemIdRegex, modIdRegex);
    }
    public static SpiritRepairRegexData any(String itemIdRegex) {
        return new SpiritRepairRegexData("", itemIdRegex);
    }
    public boolean isEmpty() {
        return itemIdRegex.isEmpty() && modIdRegex.isEmpty();
    }

    @Override
    public String toString() {
        return "SpiritRepairRegexData{" +
                "itemIdRegex='" + itemIdRegex + '\'' +
                ", modIdRegex='" + modIdRegex + '\'' +
                '}';
    }

    @SuppressWarnings("deprecation")
    public List<Holder<Item>> modifyRepairInput(List<Holder<Item>> repairInput) {
        List<Item> extraItems = new ArrayList<>(repairInput.stream().map(Holder::value).toList());
        for (int i = 0; i < BuiltInRegistries.ITEM.size(); i++) {
            Item item = BuiltInRegistries.ITEM.byId(i);
            ItemStack stack = item.getDefaultInstance();
            if (item.isRepairable(stack)) {
                var id = BuiltInRegistries.ITEM.getKey(item);
                if (id.getPath().matches(itemIdRegex)) {
                    if (!modIdRegex.isEmpty()) {
                        if (id.getNamespace().matches(modIdRegex)) {
                            continue;
                        }
                    }
                    if (!extraItems.contains(item)) {
                        extraItems.add(item);
                    }
                }
            }
        }
        return ImmutableList.copyOf(extraItems.stream().map(Item::builtInRegistryHolder).toList());
    }
}
