package com.sammy.malum.common.recipe;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import com.sammy.malum.registry.common.recipe.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.*;
import team.lodestar.lodestone.systems.recipe.*;

public class VoidFavorRecipe extends LodestoneInWorldRecipe<SingleRecipeInput> {

    public static final MapCodec<VoidFavorRecipe> CODEC = RecordCodecBuilder.mapCodec((obj) -> obj.group(
            Ingredient.CODEC.fieldOf("input").forGetter((recipe) -> recipe.input),
            ItemStack.CODEC.fieldOf("result").forGetter((recipe) -> recipe.result)
    ).apply(obj, VoidFavorRecipe::new));

    public static final String NAME = "void_favor";

    public final Ingredient input;

    public final ItemStack result;

    public VoidFavorRecipe(Ingredient input, ItemStack result) {
        super(MalumRecipeSerializers.VOID_FAVOR_RECIPE_SERIALIZER.get(), MalumRecipeTypes.VOID_FAVOR.get(), result);
        this.input = input;
        this.result = result;
    }

    @Override
    public boolean matches(SingleRecipeInput input, Level level) {
        return this.input.test(input.item());
    }
}