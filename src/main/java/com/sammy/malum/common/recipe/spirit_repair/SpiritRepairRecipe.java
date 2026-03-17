package com.sammy.malum.common.recipe.spirit_repair;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.sammy.malum.core.systems.recipe.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.recipe.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.*;
import net.neoforged.neoforge.common.crafting.SizedIngredient;
import team.lodestar.lodestone.modules.toolkit.recipe.LodestoneInWorldRecipe;

import java.util.*;

public class SpiritRepairRecipe extends LodestoneInWorldRecipe<SpiritBasedRecipeInput> {

    public static final MapCodec<SpiritRepairRecipe> CODEC = RecordCodecBuilder.mapCodec(obj -> obj.group(
            BuiltInRegistries.ITEM.holderByNameCodec().listOf().fieldOf("validItems").forGetter(recipe -> recipe.validItems),
            SpiritIngredient.CODEC.codec().listOf().fieldOf("spirits").forGetter(recipe -> recipe.spirits),
            SizedIngredient.FLAT_CODEC.fieldOf("repairMaterial").forGetter(recipe -> recipe.repairMaterial),
            Codec.FLOAT.optionalFieldOf("durabilityPercentage", 0.5f).forGetter(recipe -> recipe.repairEfficiency),
            SpiritRepairRegexData.CODEC.optionalFieldOf("regex", SpiritRepairRegexData.EMPTY).forGetter(recipe -> recipe.regex)
    ).apply(obj, SpiritRepairRecipe::new));

    public static final String NAME = "spirit_repair";

    public final List<Holder<Item>> validItems;
    public final List<SpiritIngredient> spirits;
    public final SizedIngredient repairMaterial;
    public final float repairEfficiency;

    public final SpiritRepairRegexData regex;

    public SpiritRepairRecipe(List<Holder<Item>> validItems, List<SpiritIngredient> spirits, SizedIngredient repairMaterial, float repairEfficiency, SpiritRepairRegexData regex) {
        super(MalumRecipeSerializers.REPAIR_RECIPE_SERIALIZER.get(), MalumRecipeTypes.SPIRIT_REPAIR.get());
        this.validItems = regex.isEmpty() ? validItems : regex.modifyRepairInput(validItems);
        this.spirits = spirits;
        this.repairMaterial = repairMaterial;
        this.repairEfficiency = repairEfficiency;
        this.regex = regex;
    }

    public boolean isValidItemForRepair(ItemLike input) {
        for (Holder<Item> validItem : validItems) {
            if (validItem.value().equals(input)) {
                return true;
            }
        }
        return false;
    }

    public ItemStack getResultItem(ItemStack input) {
        var holder = input.getItemHolder();
        var data = holder.getData(MalumDataMaps.REPAIRED_IMPETUS_VARIANT);
        if (data != null) {
            var otherImpetus = data.otherImpetus().value();
            return otherImpetus.getDefaultInstance();
        }
        var output = input.copy();
        output.setDamageValue(Math.max(0, input.getDamageValue() - (int) (output.getMaxDamage() * repairEfficiency)));
        return output;
    }

    public List<ItemStack> getRepairedItems() {
        List<ItemStack> repairedItems = new ArrayList<>();
        for (Holder<Item> holder : validItems) {
            Item item = holder.value();
            ItemStack input = item.getDefaultInstance();
            repairedItems.add(getResultItem(input));
        }
        return repairedItems;
    }

    public List<ItemStack> getDamagedItems() {
        List<ItemStack> damagedItems = new ArrayList<>();
        for (Holder<Item> holder : validItems) {
            Item item = holder.value();
            ItemStack input = item.getDefaultInstance();
            input.setDamageValue((int) (input.getMaxDamage() * repairEfficiency));
            damagedItems.add(input);
        }
        return damagedItems;
    }

    @Override
    public boolean matches(SpiritBasedRecipeInput input, Level level) {
        return input.test(repairMaterial, spirits);
    }

    public boolean matches(SpiritBasedRecipeInput input, ItemStack repairTarget) {
        return input.test(repairMaterial, spirits) && validItems.stream().anyMatch(repairTarget::is);
    }

    @Override
    public final ItemStack getResultItem(HolderLookup.Provider registries) {
        return repairMaterial.getItems()[0];
    }
}