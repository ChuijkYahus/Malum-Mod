package com.sammy.malum.client.screen.codex.pages.recipe;

import com.sammy.malum.MalumMod;
import com.sammy.malum.client.screen.codex.helper.*;
import com.sammy.malum.client.screen.codex.pages.BookPage;
import com.sammy.malum.client.screen.codex.screens.CodexEntryScreen;
import com.sammy.malum.common.recipe.UnchainedTransmutationRecipe;
import com.sammy.malum.registry.common.recipe.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import team.lodestar.lodestone.modules.toolkit.recipe.LodestoneInWorldRecipe;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static com.sammy.malum.client.screen.codex.helper.CodexItemHelper.renderIngredient;

public class UnchainedTransmutationRecipePage extends BookPage {
    private final Component headline;
    private final List<UnchainedTransmutationRecipe> recipes;

    public UnchainedTransmutationRecipePage(String headline, Predicate<UnchainedTransmutationRecipe> predicate) {
        super(MalumMod.malumPath("textures/gui/book/pages/transmutation_recipe_page.png"));
        this.headline = Component.translatable(BookPage.HEADLINE + "." + headline);
        final Level level = Minecraft.getInstance().level;
        if (level != null) {
            this.recipes = new ArrayList<>();
            var recipe = LodestoneRecipeType.findRecipe(level, MalumRecipeTypes.UNCHAINED_TRANSMUTATION.get(), predicate);
            if (recipe != null) {
                recipes.add(recipe);
                if (recipe.group != null) {
                    for (UnchainedTransmutationRecipe otherRecipe : LodestoneRecipeType.getRecipes(level, MalumRecipeTypes.UNCHAINED_TRANSMUTATION.get())) {
                        if (!recipe.equals(otherRecipe) && recipe.group.equals(otherRecipe.group)) {
                            recipes.add(otherRecipe);
                        }
                    }
                }
            }
        } else {
            this.recipes = null;
        }
    }

    public static UnchainedTransmutationRecipePage fromInput(String headlineTranslationKey, Item inputItem) {
        return new UnchainedTransmutationRecipePage(headlineTranslationKey, s -> s.ingredient.test(inputItem.getDefaultInstance()));
    }

    public static UnchainedTransmutationRecipePage fromOutput(String headlineTranslationKey, Item outputItem) {
        return new UnchainedTransmutationRecipePage(headlineTranslationKey, s -> s.output.is(outputItem));
    }

    @Override
    public boolean isValid() {
        return recipes != null;
    }

    @Override
    public void render(CodexEntryScreen screen, GuiGraphics guiGraphics, int left, int top, int mouseX, int mouseY, float partialTicks, boolean isRepeat) {
        CodexTextHelper.renderHeadline(guiGraphics, headline, left, top);

        UnchainedTransmutationRecipe recipe = recipes.get(getIndex());
        renderIngredient(screen, guiGraphics, recipe.ingredient, left + 63, top + 56, mouseX, mouseY);
        CodexItemHelper.renderItem(screen, guiGraphics, recipe.output, left + 63, top + 132, mouseX, mouseY);

        renderRecipeInfo(guiGraphics, screen, "unchained_transmutation", left + 62, top + 78, mouseX, mouseY);
     }

    public int getIndex() {
        return (int) (Minecraft.getInstance().level.getGameTime() % (20L * recipes.size()) / 20);
    }
}
