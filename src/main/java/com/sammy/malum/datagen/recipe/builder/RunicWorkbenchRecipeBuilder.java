package com.sammy.malum.datagen.recipe.builder;

import com.sammy.malum.common.item.curiosities.curios.runes.*;
import com.sammy.malum.common.item.curiosities.curios.runes.madness.*;
import com.sammy.malum.common.item.curiosities.curios.runes.miracle.*;
import com.sammy.malum.common.recipe.RunicWorkbenchRecipe;
import com.sammy.malum.core.systems.spirit.type.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.core.*;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.*;
import net.minecraft.sounds.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.*;
import net.neoforged.neoforge.common.crafting.SizedIngredient;
import team.lodestar.lodestone.recipe.builder.LodestoneRecipeBuilder;

public class RunicWorkbenchRecipeBuilder implements LodestoneRecipeBuilder<RunicWorkbenchRecipe> {
    private final ItemStack output;
    private final Holder<SoundEvent> soundType;

    private ItemStack primaryInput;
    private SizedIngredient secondaryInput;

    public RunicWorkbenchRecipeBuilder(ItemLike output, int outputCount) {
        this(getRuneSound(output), output, outputCount);
    }
    public RunicWorkbenchRecipeBuilder(Holder<SoundEvent> soundType, ItemLike output, int outputCount) {
        this.soundType = soundType;
        this.output = new ItemStack(output.asItem(), outputCount);
    }

    public RunicWorkbenchRecipeBuilder setPrimaryInput(ItemStack primaryInput) {
        this.primaryInput = primaryInput;
        return this;
    }

    public RunicWorkbenchRecipeBuilder setPrimaryInput(ItemLike primaryInput, int primaryInputCount) {
        return setPrimaryInput(new ItemStack(primaryInput, primaryInputCount));
    }

    public RunicWorkbenchRecipeBuilder setSecondaryInput(SpiritLike type, int amount) {
        return setSecondaryInput((Item) type.getSpiritShard(), amount);
    }
    public RunicWorkbenchRecipeBuilder setSecondaryInput(Item item, int amount) {
        this.secondaryInput = new SizedIngredient(Ingredient.of(item), amount);
        return this;
    }

    public void save(RecipeOutput recipeOutput) {
        this.save(recipeOutput, output.getItem());
    }

    public static Holder<SoundEvent> getRuneSound(ItemLike output) {
        if (output instanceof MiracleRuneCurioItem) {
            return MalumSoundEvents.RUNIC_WORKBENCH_SHAPES_RUNE_STONE;
        }
        if (output instanceof TotemicRuneCurioItem) {
            return MalumSoundEvents.RUNIC_WORKBENCH_SHAPES_RUNE_WOODEN;
        }
        if (output instanceof MadnessRuneCurioItem) {
            return MalumSoundEvents.RUNIC_WORKBENCH_SHAPES_RUNE_VOID;
        }
        throw new IllegalArgumentException("Ehehehe :33333");
    }

    @Override
    public RunicWorkbenchRecipe buildRecipe(ResourceLocation resourceLocation) {
        return new RunicWorkbenchRecipe(
                SizedIngredient.of(primaryInput.getItem(), primaryInput.getCount()),
                secondaryInput, output, soundType);
    }

    @Override
    public String getRecipeSubfolder() {
        return "runeworking";
    }
}