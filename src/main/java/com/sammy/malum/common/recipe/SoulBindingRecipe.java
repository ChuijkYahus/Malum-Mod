package com.sammy.malum.common.recipe;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.core.systems.recipe.*;
import com.sammy.malum.registry.common.recipe.*;
import net.minecraft.world.level.*;
import net.neoforged.neoforge.common.crafting.*;
import team.lodestar.lodestone.systems.recipe.*;

import java.util.*;

public class SoulBindingRecipe extends LodestoneInWorldRecipe<SpiritBasedRecipeInput> {

    public static final MapCodec<SoulBindingRecipe> CODEC = RecordCodecBuilder.mapCodec((obj) -> obj.group(
            SizedIngredient.FLAT_CODEC.fieldOf("ingredient").forGetter(recipe -> recipe.ingredient),
            GeasEffectType.CODEC.fieldOf("geas").forGetter(recipe -> recipe.geas),
            SizedIngredient.FLAT_CODEC.listOf().optionalFieldOf("extraIngredients", List.of()).forGetter(recipe -> recipe.extraIngredients),
            SpiritIngredient.CODEC.codec().listOf().fieldOf("spirits").forGetter(recipe -> recipe.spirits),
            Codec.BOOL.optionalFieldOf("carryOverComponentData", false).forGetter(recipe -> recipe.carryOverData)
    ).apply(obj, SoulBindingRecipe::new));

    public static final String NAME = "soul_binding";

    public final SizedIngredient ingredient;
    public final GeasEffectType geas;

    public final List<SizedIngredient> extraIngredients;
    public final List<SpiritIngredient> spirits;
    public final boolean carryOverData;

    public SoulBindingRecipe(SizedIngredient ingredient, GeasEffectType geas, List<SizedIngredient> extraIngredients, List<SpiritIngredient> spirits, boolean carryOverData) {
        super(RecipeSerializerRegistry.SOUL_BINDING_RECIPE_SERIALIZER.get(), RecipeTypeRegistry.SOUL_BINDING.get());
        this.ingredient = ingredient;
        this.geas = geas;
        this.extraIngredients = extraIngredients;
        this.spirits = spirits;
        this.carryOverData = carryOverData;
    }

    @Override
    public boolean matches(SpiritBasedRecipeInput input, Level level) {
        return input.test(ingredient, extraIngredients, spirits);
    }
}
