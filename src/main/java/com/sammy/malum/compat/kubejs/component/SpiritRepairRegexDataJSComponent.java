package com.sammy.malum.compat.kubejs.component;

import com.google.gson.*;
import com.mojang.brigadier.*;
import com.mojang.brigadier.exceptions.*;
import com.mojang.serialization.*;
import com.sammy.malum.*;
import com.sammy.malum.common.recipe.spirit_repair.*;
import dev.latvian.mods.kubejs.recipe.*;
import dev.latvian.mods.kubejs.recipe.component.*;
import dev.latvian.mods.kubejs.util.*;
import dev.latvian.mods.rhino.type.*;

public record SpiritRepairRegexDataJSComponent(RecipeComponentType<?> type) implements RecipeComponent<SpiritRepairRegexData> {

    public static final RecipeComponentType<SpiritRepairRegexData> REPAIR_REGEX = RecipeComponentType.unit(
            MalumMod.malumPath("repair_regex"),
            SpiritRepairRegexDataJSComponent::new
    );

    @Override
    public RecipeComponentType<?> type() {
        return REPAIR_REGEX;
    }

    @Override
    public Codec<SpiritRepairRegexData> codec() {
        return SpiritRepairRegexData.CODEC;
    }

    @Override
    public TypeInfo typeInfo() {
        return TypeInfo.of(SpiritRepairRegexData.class);
    }

    @Override
    public String toString() {
        return type().id().getPath();
    }

    @Override
    public SpiritRepairRegexData wrap(RecipeScriptContext cx, Object from) {
        if (from instanceof JsonObject json) {
            return codec().decode(JsonOps.INSTANCE, json).result().orElseThrow().getFirst();
        }
        return fromObject(RegistryAccessContainer.of(cx.cx()), from);
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
        if (!reader.canRead()) {
            return SpiritRepairRegexData.EMPTY;
        }
        reader.skipWhitespace();
        String string = reader.readString();
        if (string.contains(":")) {
            String[] parts = string.split(":", 2);
            String modIdRegex = parts[0];
            String itemIdRegex = parts[1];
            return new SpiritRepairRegexData(modIdRegex, itemIdRegex, null);
        }
        if (string.startsWith("#")) {
            String tag = string.substring(1);
            return SpiritRepairRegexData.tag(tag);
        }
        return SpiritRepairRegexData.simple(string);
    }
}