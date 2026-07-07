package com.sammy.malum.compat.kubejs.component;

import com.google.gson.*;
import com.mojang.brigadier.*;
import com.mojang.brigadier.exceptions.*;
import com.mojang.serialization.*;
import com.sammy.malum.*;
import com.sammy.malum.common.item.spirit.*;
import com.sammy.malum.core.systems.recipe.*;
import com.sammy.malum.core.systems.spirit.SpiritArcanaType;
import com.sammy.malum.registry.common.magic.*;
import dev.latvian.mods.kubejs.recipe.*;
import dev.latvian.mods.kubejs.recipe.component.*;
import dev.latvian.mods.kubejs.util.*;
import dev.latvian.mods.rhino.type.*;
import net.minecraft.core.*;
import net.minecraft.resources.*;
import net.minecraft.util.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.*;
import net.neoforged.neoforge.common.crafting.*;

public record SpiritJSComponent(RecipeComponentType<?> type) implements RecipeComponent<SpiritIngredient> {

    public static final Codec<SpiritIngredient> CODEC = SpiritIngredient.CODEC.codec();
    public static final RecipeComponentType<SpiritIngredient> SPIRIT_INGREDIENT = RecipeComponentType.unit(
            MalumMod.malumPath("spirit_ingredient"),
            SpiritJSComponent::new
    );

    @Override
    public RecipeComponentType<?> type() {
        return SPIRIT_INGREDIENT;
    }

    @Override
    public Codec<SpiritIngredient> codec() {
        return CODEC;
    }

    @Override
    public TypeInfo typeInfo() {
        return TypeInfo.of(SpiritIngredient.class);
    }

    @Override
    public String toString() {
        return type().id().getPath();
    }

    @Override
    public SpiritIngredient wrap(RecipeScriptContext cx, Object from) {
        if (from instanceof JsonObject json) {
            return codec().decode(JsonOps.INSTANCE, json).result().orElseThrow().getFirst();
        }
        return fromObject(RegistryAccessContainer.of(cx.cx()), from);
    }

    public static SpiritIngredient fromObject(RegistryAccessContainer registries, Object from) {
        if (from instanceof SpiritIngredient spiritIngredient) {
            return spiritIngredient;
        }
        if (from instanceof SpiritShardItem shardItem) {
            return fromItemStack(shardItem, 1);
        }
        if (from instanceof SizedIngredient sizedIngredient) {
            return fromSizedIngredient(sizedIngredient);
        }
        if (from instanceof ItemStack stack) {
            return fromItemStack(stack);
        }
        if (from instanceof ItemLike itemLike) {
            return fromItem(itemLike);
        }
        if (from instanceof Ingredient ingredient) {
            return fromItemStack(ingredient.getItems()[0]);
        }
        if (from instanceof CharSequence) {
            try {
                return read(new StringReader(from.toString()));
            } catch (Exception exception) {
                throw new IllegalArgumentException("Failed to read SpiritIngredient from string: " + from, exception);
            }
        }
        throw new IllegalArgumentException("Can't create SpiritIngredient from object: " + from);
    }

    public static SpiritIngredient read(StringReader reader) throws CommandSyntaxException {
        int count = 1;
        if (StringReader.isAllowedNumber(reader.peek())) {
            count = Mth.ceil(reader.readDouble());
            reader.skipWhitespace();
            reader.expect('x');
            reader.skipWhitespace();
            if (count < 1) {
                throw new IllegalArgumentException("SpiritIngredient count smaller than 1 is not allowed!");
            }
        }

        reader.skipWhitespace();
        String spirit = reader.readString();
        if (!spirit.contains(":")) {
            spirit = "malum:" + spirit;
        }
        ResourceLocation parse = ResourceLocation.parse(spirit);
        ResourceKey<SpiritArcanaType> key = ResourceKey.create(MalumSpiritTypes.SPIRIT_TYPES_KEY, parse);
        Holder<SpiritArcanaType> spiritHolder = MalumSpiritTypes.SPIRIT_TYPES_REGISTRY.getHolderOrThrow(key);
        return new SpiritIngredient(spiritHolder, count);
    }

    public static SpiritIngredient fromSizedIngredient(SizedIngredient sizedIngredient) {
        ItemStack[] items = sizedIngredient.getItems();
        if (items.length < 1) {
            throw new IllegalArgumentException("Can't create SpiritIngredient");
        }
        if ((items[0].getItem() instanceof SpiritShardItem shardItem)) {
            return fromItemStack(shardItem, sizedIngredient.count());
        }
        throw new IllegalArgumentException("SizedIngredient must hold a SpiritShardItem to be converted to a SpiritIngredient!");
    }

    public static SpiritIngredient fromItem(ItemLike itemLike) {
        if (itemLike instanceof SpiritShardItem shardItem) {
            return fromItemStack(shardItem, 1);
        }
        throw new IllegalArgumentException("Can't create SpiritIngredient from ItemLike");
    }

    public static SpiritIngredient fromItemStack(ItemStack stack) {
        if (stack.getItem() instanceof SpiritShardItem shardItem) {
            return fromItemStack(shardItem, stack.getCount());
        }
        throw new IllegalArgumentException("Can't create SpiritIngredient");
    }

    public static SpiritIngredient fromItemStack(SpiritShardItem shardItem, int count) {
        return new SpiritIngredient(shardItem.getHolder(), count);
    }
}