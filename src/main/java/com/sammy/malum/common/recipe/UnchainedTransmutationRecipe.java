package com.sammy.malum.common.recipe;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import com.sammy.malum.registry.common.recipe.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.*;
import team.lodestar.lodestone.systems.recipe.*;

public class UnchainedTransmutationRecipe extends LodestoneInWorldRecipe<SingleRecipeInput> {

    public static final MapCodec<UnchainedTransmutationRecipe> CODEC = RecordCodecBuilder.mapCodec((obj) -> obj.group(
            Ingredient.CODEC.fieldOf("input").forGetter((recipe) -> recipe.ingredient),
            ItemStack.CODEC.fieldOf("result").forGetter((recipe) -> recipe.output),
            Codec.STRING.optionalFieldOf("group", "").forGetter((recipe) -> recipe.group)
    ).apply(obj, UnchainedTransmutationRecipe::new));

    public static final String NAME = "unchained_transmutation";

    public final Ingredient ingredient;

    public final ItemStack output;

    public final String group;

    public UnchainedTransmutationRecipe(Ingredient ingredient, ItemStack output, String group) {
        super(MalumRecipeSerializers.SPIRIT_TRANSMUTATION_RECIPE_SERIALIZER.get(), MalumRecipeTypes.SPIRIT_TRANSMUTATION.get(), output);
        this.ingredient = ingredient;
        this.output = output;
        this.group = group;
    }

    @Override
    public String getGroup() {
        return group;
    }

    @Override
    public boolean matches(SingleRecipeInput input, Level level) {
        return this.ingredient.test(input.item());
    }
}
