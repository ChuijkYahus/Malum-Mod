package com.sammy.malum.common.recipe;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import com.sammy.malum.registry.common.recipe.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import team.lodestar.lodestone.modules.toolkit.recipe.LodestoneInWorldRecipe;

import java.util.*;

@SuppressWarnings("NullableProblems")
public class UnchainedTransmutationRecipe extends LodestoneInWorldRecipe<SingleRecipeInput> {

    public static final MapCodec<UnchainedTransmutationRecipe> CODEC = RecordCodecBuilder.mapCodec((obj) -> obj.group(
            Ingredient.CODEC.fieldOf("input").forGetter((recipe) -> recipe.input),
            ItemStack.CODEC.fieldOf("result").forGetter((recipe) -> recipe.output),
            Codec.STRING.optionalFieldOf("group", "").forGetter((recipe) -> recipe.group)
    ).apply(obj, UnchainedTransmutationRecipe::new));

    public static final String NAME = "unchained_transmutation";

    protected final Ingredient input;
    protected final ItemStack output;

    protected final String group;

    public UnchainedTransmutationRecipe(Ingredient input, ItemStack output, String group) {
        super(MalumRecipeSerializers.SPIRIT_TRANSMUTATION_RECIPE_SERIALIZER.get(), MalumRecipeTypes.UNCHAINED_TRANSMUTATION.get(), output);
        this.input = input;
        this.output = output;
        this.group = group;
    }

    @Override
    public String getGroup() {
        return group;
    }

    @Override
    public boolean matches(SingleRecipeInput input, Level level) {
        return this.input.test(input.item());
    }

    public Ingredient getInput() {
        return input;
    }

    public ItemStack getOutputRaw() {
        return output;
    }

    public Optional<Block> createOutput() {
        if (output.getItem() instanceof BlockItem blockItem) {
            return Optional.of(blockItem.getBlock());
        }
        return Optional.empty();
    }
}
