package com.sammy.malum.client.screen.codex.pages.recipe;

import com.sammy.malum.*;
import com.sammy.malum.client.screen.codex.helper.*;
import com.sammy.malum.client.screen.codex.pages.*;
import com.sammy.malum.client.screen.codex.screens.*;
import com.sammy.malum.common.recipe.*;
import com.sammy.malum.core.systems.recipe.SpiritBasedRecipeInput;
import com.sammy.malum.registry.common.recipe.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.network.chat.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.RecipeType;
import team.lodestar.lodestone.modules.toolkit.recipe.LodestoneInWorldRecipe;

import java.util.function.*;

public class RuneworkingPage extends BookRecipePage<RuneworkingRecipe.RunicWorkbenchRecipeInput, RuneworkingRecipe> {

    public RuneworkingPage(Predicate<RuneworkingRecipe> filter) {
        super(filter);
    }

    @Override
    public void render(CodexEntryScreen screen, GuiGraphics guiGraphics, int left, int top, int mouseX, int mouseY, float partialTicks, boolean isRepeat) {
        CodexItemHelper.renderIngredient(screen, guiGraphics, recipe.input, left + 63, top + 56, mouseX, mouseY);
        CodexItemHelper.renderIngredient(screen, guiGraphics, recipe.secondaryInput, left + 63, top + 21, mouseX, mouseY);
        CodexItemHelper.renderItem(screen, guiGraphics, recipe.output, left + 63, top + 132, mouseX, mouseY);

        renderRecipeInfo(guiGraphics, screen, "runeworking", left + 62, top + 74, mouseX, mouseY);
    }

    public static RuneworkingPage fromOutput(Item outputItem) {
        return new RuneworkingPage(s -> s.output.is(outputItem));
    }

    @Override
    public RecipeType<RuneworkingRecipe> getRecipeType() {
        return MalumRecipeTypes.RUNEWORKING.get();
    }
}