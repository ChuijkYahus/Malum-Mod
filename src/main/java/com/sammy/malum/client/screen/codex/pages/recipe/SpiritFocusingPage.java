package com.sammy.malum.client.screen.codex.pages.recipe;

import com.sammy.malum.MalumMod;
import com.sammy.malum.client.screen.codex.helper.*;
import com.sammy.malum.client.screen.codex.screens.*;
import com.sammy.malum.client.screen.codex.pages.*;
import com.sammy.malum.common.recipe.SpiritFocusingRecipe;
import com.sammy.malum.core.systems.recipe.SpiritBasedRecipeInput;
import com.sammy.malum.registry.common.recipe.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeType;
import team.lodestar.lodestone.modules.toolkit.recipe.LodestoneInWorldRecipe;

import java.util.function.Predicate;

public class SpiritFocusingPage extends BookRecipePage<SpiritBasedRecipeInput, SpiritFocusingRecipe> {

    public SpiritFocusingPage(Predicate<SpiritFocusingRecipe> filter) {
        super(filter);
    }


    public static SpiritFocusingPage fromOutput(Item outputItem) {
        return new SpiritFocusingPage(s -> s.getOutputRaw().is(outputItem));
    }

    @Override
    public RecipeType<SpiritFocusingRecipe> getRecipeType() {
        return MalumRecipeTypes.SPIRIT_FOCUSING.get();
    }

    @Override
    public void render(CodexEntryScreen screen, GuiGraphics guiGraphics, int left, int top, int mouseX, int mouseY, float partialTicks, boolean isRepeat) {
        CodexItemHelper.renderIngredients(screen, guiGraphics, recipe.getSpirits(), left + 63, top + 16, mouseX, mouseY, false);
        CodexItemHelper.renderIngredient(screen, guiGraphics, recipe.getInput(), left + 63, top + 56, mouseX, mouseY);
        CodexItemHelper.renderItem(screen, guiGraphics, recipe.getOutputRaw(), left + 63, top + 132, mouseX, mouseY);

        renderRecipeInfo(guiGraphics, screen, "spirit_focusing", left + 62, top + 74, mouseX, mouseY);
    }
}