package com.sammy.malum.common.recipe.spirit_repair;

import com.google.common.collect.*;
import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;

import java.util.*;

public class SpiritRepairRegexData {

    public static final Codec<SpiritRepairRegexData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.optionalFieldOf("modIdRegex", "").forGetter(SpiritRepairRegexData::modIdRegex),
            Codec.STRING.fieldOf("itemIdRegex").forGetter(SpiritRepairRegexData::itemIdRegex),
            TagKey.codec(Registries.ITEM).optionalFieldOf("tagRegex").forGetter(t -> Optional.ofNullable(t.tagRegex))
    ).apply(instance, (modIdRegex, itemIdRegex, tagRegex) -> new SpiritRepairRegexData(
            modIdRegex, itemIdRegex, tagRegex.orElse(null)
    )));

    public static final SpiritRepairRegexData EMPTY = new SpiritRepairRegexData("", "", null);
    private String modIdRegex;
    private String itemIdRegex;
    private TagKey<Item> tagRegex;

    public SpiritRepairRegexData(String modIdRegex, String itemIdRegex, TagKey<Item> tagRegex) {
        this.modIdRegex = modIdRegex;
        this.itemIdRegex = itemIdRegex;
        this.tagRegex = tagRegex;
    }

    public SpiritRepairRegexData withMod(String modIdRegex) {
        this.modIdRegex = modIdRegex;
        return this;
    }

    public SpiritRepairRegexData withItemId(String itemIdRegex) {
        this.itemIdRegex = itemIdRegex;
        return this;
    }

    public SpiritRepairRegexData withTag(TagKey<Item> tagRegex) {
        this.tagRegex = tagRegex;
        return this;
    }

    public static SpiritRepairRegexData tag(String tag) {
        return new SpiritRepairRegexData("", "", TagKey.create(Registries.ITEM, ResourceLocation.parse(tag)));
    }
    public static SpiritRepairRegexData simple(String itemIdRegex) {
        return new SpiritRepairRegexData("", itemIdRegex, null);
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

    public String modIdRegex() {
        return modIdRegex;
    }

    public String itemIdRegex() {
        return itemIdRegex;
    }

    public TagKey<Item> tagRegex() {
        return tagRegex;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (SpiritRepairRegexData) obj;
        return Objects.equals(this.modIdRegex, that.modIdRegex) &&
                Objects.equals(this.itemIdRegex, that.itemIdRegex) &&
                Objects.equals(this.tagRegex, that.tagRegex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modIdRegex, itemIdRegex, tagRegex);
    }

}
