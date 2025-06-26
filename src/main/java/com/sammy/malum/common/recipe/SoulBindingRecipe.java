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
            SizedIngredient.FLAT_CODEC.fieldOf("input").forGetter(recipe -> recipe.input),
            GeasEffectType.CODEC.fieldOf("result").forGetter(recipe -> recipe.result),
            SizedIngredient.FLAT_CODEC.listOf().optionalFieldOf("extraInputs", List.of()).forGetter(recipe -> recipe.extraInputs),
            SpiritIngredient.CODEC.codec().listOf().fieldOf("spirits").forGetter(recipe -> recipe.spirits)
    ).apply(obj, SoulBindingRecipe::new));

    public static final String NAME = "soul_binding";

    public final SizedIngredient input;
    public final GeasEffectType result;

    public final List<SizedIngredient> extraInputs;
    public final List<SpiritIngredient> spirits;

    public SoulBindingRecipe(SizedIngredient input, GeasEffectType result, List<SizedIngredient> extraInputs, List<SpiritIngredient> spirits) {
        super(MalumRecipeSerializers.SOUL_BINDING_RECIPE_SERIALIZER.get(), MalumRecipeTypes.SOUL_BINDING.get());
        this.input = input;
        this.result = result;
        this.extraInputs = extraInputs;
        this.spirits = spirits;
    }

    @Override
    public boolean matches(SpiritBasedRecipeInput input, Level level) {
        return input.test(this.input, extraInputs, spirits);
    }
}
