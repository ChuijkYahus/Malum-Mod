package com.sammy.malum.compat.kubejs.component;

import com.google.gson.*;
import com.mojang.serialization.*;
import com.sammy.malum.core.systems.recipe.*;
import dev.latvian.mods.kubejs.recipe.*;
import dev.latvian.mods.kubejs.recipe.component.*;
import dev.latvian.mods.kubejs.util.*;
import dev.latvian.mods.rhino.*;
import dev.latvian.mods.rhino.type.*;

import java.util.*;

public record SpiritJSComponent(String name, Codec<SpiritIngredient> codec) implements RecipeComponent<SpiritIngredient> {

    public static final RecipeComponent<SpiritIngredient> SPIRIT_INGREDIENT = new SpiritJSComponent("malum:spirit_ingredient", SpiritIngredient.CODEC.codec());
    public static final RecipeComponent<List<SpiritIngredient>> SPIRIT_LIST_INGREDIENT = SPIRIT_INGREDIENT.asList();
    public static final TypeInfo TYPE_INFO = TypeInfo.of(SpiritIngredient.class);

    @Override
    public TypeInfo typeInfo() {
        return TYPE_INFO;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public SpiritIngredient wrap(Context cx, KubeRecipe recipe, Object from) {
        if (from instanceof SpiritIngredient k) {
            return k;
        }

        if (from instanceof JsonObject json) {
            return this.codec.decode(JsonOps.INSTANCE, json).result().orElseThrow().getFirst();
        }

        return (SpiritIngredient) cx.jsToJava(from, this.typeInfo());
    }
}