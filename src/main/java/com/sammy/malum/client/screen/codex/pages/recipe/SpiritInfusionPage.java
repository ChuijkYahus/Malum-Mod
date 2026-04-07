package com.sammy.malum.client.screen.codex.pages.recipe;

import com.sammy.malum.MalumMod;
import com.sammy.malum.client.screen.codex.helper.*;
import com.sammy.malum.client.screen.codex.pages.BookPage;
import com.sammy.malum.client.screen.codex.screens.CodexEntryScreen;
import com.sammy.malum.common.data.component.SoulwovenBannerPatternDataComponent;
import com.sammy.malum.common.recipe.SpiritInfusionRecipe;
import com.sammy.malum.core.systems.recipe.SpiritBasedRecipeInput;
import com.sammy.malum.registry.common.recipe.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.*;
import net.minecraft.resources.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.*;
import team.lodestar.lodestone.modules.toolkit.recipe.LodestoneInWorldRecipe;
import team.lodestar.lodestone.modules.toolkit.recipe.LodestoneRecipeSearch;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class SpiritInfusionPage extends BookRecipePage<SpiritBasedRecipeInput, SpiritInfusionRecipe> {

    public static SpiritInfusionPage fromOutput(Item outputItem) {
        return new SpiritInfusionPage(s -> s.result.is(outputItem));
    }

    public SpiritInfusionPage(SoulwovenBannerPatternDataComponent component) {
        super(component.getRecipeId());
    }

    public SpiritInfusionPage(Predicate<SpiritInfusionRecipe> filter) {
        super(filter);
    }

    @Override
    public RecipeType<SpiritInfusionRecipe> getRecipeType() {
        return MalumRecipeTypes.SPIRIT_INFUSION.get();
    }

    @Override
    public void render(CodexEntryScreen screen, GuiGraphics guiGraphics, int left, int top, int mouseX, int mouseY, float partialTicks, boolean isRepeat) {
        CodexItemHelper.renderIngredients(screen, guiGraphics, recipe.spirits, left + 13, top + 75, mouseX, mouseY, true);
        CodexItemHelper.renderIngredients(screen, guiGraphics, recipe.extraInputs, left + 113, top + 75, mouseX, mouseY, true);
        CodexItemHelper.renderIngredient(screen, guiGraphics, recipe.input, left + 63, top + 56, mouseX, mouseY);
        CodexItemHelper.renderItem(screen, guiGraphics, recipe.result, left + 63, top + 132, mouseX, mouseY);

        renderRecipeInfo(guiGraphics, screen, "spirit_infusion", left + 62, top + 74, mouseX, mouseY);
    }
}