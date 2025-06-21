package com.sammy.malum.common.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.sammy.malum.registry.common.recipe.*;
import net.minecraft.core.*;
import net.minecraft.sounds.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.*;
import net.neoforged.neoforge.common.crafting.*;
import team.lodestar.lodestone.systems.recipe.*;

public class RunicWorkbenchRecipe extends LodestoneInWorldRecipe<RunicWorkbenchRecipe.RunicWorkbenchRecipeInput> {

    public static final MapCodec<RunicWorkbenchRecipe> CODEC = RecordCodecBuilder.mapCodec(obj -> obj.group(
            SizedIngredient.FLAT_CODEC.fieldOf("primaryInput").forGetter(recipe -> recipe.primaryInput),
            SizedIngredient.FLAT_CODEC.fieldOf("secondaryInput").forGetter(recipe -> recipe.secondaryInput),
            ItemStack.CODEC.fieldOf("result").forGetter(recipe -> recipe.output),
            SoundEvent.CODEC.fieldOf("soundType").forGetter(recipe -> recipe.soundType)
    ).apply(obj, RunicWorkbenchRecipe::new));

    public static final String NAME = "runeworking";

    public final SizedIngredient primaryInput;
    public final SizedIngredient secondaryInput;
    public final Holder<SoundEvent> soundType;

    public RunicWorkbenchRecipe(SizedIngredient primaryInput, SizedIngredient secondaryInput, ItemStack output, Holder<SoundEvent> soundType) {
        super(MalumRecipeSerializers.RUNEWORKING_RECIPE_SERIALIZER.get(), MalumRecipeTypes.RUNEWORKING.get(), output);
        this.primaryInput = primaryInput;
        this.secondaryInput = secondaryInput;
        this.soundType = soundType;
    }

    @Override
    public boolean matches(RunicWorkbenchRecipeInput input, Level level) {
        return input.test(primaryInput, secondaryInput);
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