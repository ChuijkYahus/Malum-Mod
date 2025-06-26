package com.sammy.malum.compat.kubejs.component;

import com.google.gson.*;
import com.mojang.brigadier.*;
import com.mojang.brigadier.exceptions.*;
import com.mojang.serialization.*;
import com.sammy.malum.common.recipe.spirit_repair.*;
import com.sammy.malum.core.systems.recipe.*;
import dev.latvian.mods.kubejs.recipe.*;
import dev.latvian.mods.kubejs.recipe.component.*;
import dev.latvian.mods.kubejs.recipe.match.*;
import dev.latvian.mods.kubejs.util.*;
import dev.latvian.mods.rhino.*;
import dev.latvian.mods.rhino.type.*;

public record SpiritRepairRegexDataJSComponent(String name, Codec<SpiritRepairRegexData> codec) implements RecipeComponent<SpiritRepairRegexData> {

    public static final RecipeComponent<SpiritRepairRegexData> REGEX_DATA = new SpiritRepairRegexDataJSComponent("malum:repair_regex", SpiritRepairRegexData.CODEC);
    public static final TypeInfo TYPE_INFO = TypeInfo.of(SpiritIngredient.class);

    @Override
    public boolean matches(Context cx, KubeRecipe recipe, SpiritRepairRegexData value, ReplacementMatchInfo match) {
        return false;
    }

    @Override
    public TypeInfo typeInfo() {
        return TYPE_INFO;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public SpiritRepairRegexData wrap(Context cx, KubeRecipe recipe, Object from) {
        if (from instanceof JsonObject json) {
            return this.codec.decode(JsonOps.INSTANCE, json).result().orElseThrow().getFirst();
        }
        return fromObject(RegistryAccessContainer.of(cx), from);
    }

    public static SpiritRepairRegexData fromObject(RegistryAccessContainer registries, Object from) {
        if (from instanceof SpiritRepairRegexData repairRegexData) {
            return repairRegexData;
        }
        if (from instanceof CharSequence) {
            try {
                return read(new StringReader(from.toString()));
            } catch (Exception exception) {
                throw new IllegalArgumentException("Failed to read SpiritRepairRegexData from string: " + from, exception);
            }
        }

        throw new IllegalArgumentException("Can't create SpiritRepairRegexData from object: " + from);
    }

    public static SpiritRepairRegexData read(StringReader reader) throws CommandSyntaxException {
        reader.skipWhitespace();
        String modIdRegex = reader.readString();
        if (modIdRegex.isEmpty()) {
            return SpiritRepairRegexData.EMPTY;
        }
        reader.skipWhitespace();
        String itemIdRegex = reader.readString();

        if (itemIdRegex.isEmpty()) {
            itemIdRegex = modIdRegex;
        }
        return new SpiritRepairRegexData(modIdRegex, itemIdRegex);
    }
}