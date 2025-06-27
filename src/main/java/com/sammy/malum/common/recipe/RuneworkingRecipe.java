package com.sammy.malum.common.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.sammy.malum.registry.common.recipe.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.sounds.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.*;
import net.neoforged.neoforge.common.crafting.*;
import team.lodestar.lodestone.systems.recipe.*;

public class RuneworkingRecipe extends LodestoneInWorldRecipe<RuneworkingRecipe.RunicWorkbenchRecipeInput> {

    public static final MapCodec<RuneworkingRecipe> CODEC = RecordCodecBuilder.mapCodec(obj -> obj.group(
            SizedIngredient.FLAT_CODEC.fieldOf("input").forGetter(recipe -> recipe.input),
            SizedIngredient.FLAT_CODEC.fieldOf("secondaryInput").forGetter(recipe -> recipe.secondaryInput),
            ItemStack.CODEC.fieldOf("result").forGetter(recipe -> recipe.output),
            BuiltInRegistries.SOUND_EVENT.byNameCodec().fieldOf("soundType").forGetter(recipe -> recipe.soundType)
    ).apply(obj, RuneworkingRecipe::new));

    public static final String NAME = "runeworking";

    public final SizedIngredient input;
    public final SizedIngredient secondaryInput;
    public final SoundEvent soundType;

    public RuneworkingRecipe(SizedIngredient input, SizedIngredient secondaryInput, ItemStack output, SoundEvent soundType) {
        super(MalumRecipeSerializers.RUNEWORKING_RECIPE_SERIALIZER.get(), MalumRecipeTypes.RUNEWORKING.get(), output);
        this.input = input;
        this.secondaryInput = secondaryInput;
        this.soundType = soundType;
    }

    @Override
    public boolean matches(RunicWorkbenchRecipeInput input, Level level) {
        return input.test(this.input, secondaryInput);
    }

    public record RunicWorkbenchRecipeInput(ItemStack primaryInput, ItemStack secondaryInput) implements RecipeInput {

        public boolean test(SizedIngredient primaryInput, SizedIngredient secondaryInput) {
            return primaryInput.test(this.primaryInput) && secondaryInput.test(this.secondaryInput);
        }

        @Override
        public ItemStack getItem(int index) {
            return index == 0 ? primaryInput : secondaryInput;
        }

        @Override
        public int size() {
            return 2;
        }
    }
}