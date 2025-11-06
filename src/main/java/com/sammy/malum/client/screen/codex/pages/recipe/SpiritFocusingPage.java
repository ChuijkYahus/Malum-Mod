package com.sammy.malum.client.screen.codex.pages.recipe;

import com.sammy.malum.MalumMod;
import com.sammy.malum.client.screen.codex.helper.*;
import com.sammy.malum.client.screen.codex.screens.*;
import com.sammy.malum.client.screen.codex.pages.*;
import com.sammy.malum.common.recipe.SpiritFocusingRecipe;
import com.sammy.malum.registry.common.recipe.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.*;
import net.minecraft.world.item.Item;
import team.lodestar.lodestone.systems.recipe.*;

import java.util.function.Predicate;

public class SpiritFocusingPage extends BookPage {
    private final SpiritFocusingRecipe recipe;

    public SpiritFocusingPage(Predicate<SpiritFocusingRecipe> predicate) {
        this(LodestoneRecipeType.findRecipe(Minecraft.getInstance().level, MalumRecipeTypes.SPIRIT_FOCUSING.get(), predicate));
    }

    public SpiritFocusingPage(SpiritFocusingRecipe recipe) {
        super(MalumMod.malumPath("textures/gui/book/pages/spirit_focusing_page.png"));
        this.recipe = recipe;
    }

    @Override
    public boolean isValid() {
        return recipe != null;
    }

    public static SpiritFocusingPage fromInput(Item inputItem) {
        return new SpiritFocusingPage(s -> s.input.test(inputItem.getDefaultInstance()));
    }

    public static SpiritFocusingPage fromOutput(Item outputItem) {
        return new SpiritFocusingPage(s -> s.output.is(outputItem));
    }

    @Override
    public void render(CodexEntryScreen screen, GuiGraphics guiGraphics, int left, int top, int mouseX, int mouseY, float partialTicks, boolean isRepeat) {
        CodexItemHelper.renderIngredients(screen, guiGraphics, recipe.spirits, left + 63, top + 16, mouseX, mouseY, false);
        CodexItemHelper.renderIngredient(screen, guiGraphics, recipe.input, left + 63, top + 56, mouseX, mouseY);
        CodexItemHelper.renderItem(screen, guiGraphics, recipe.output, left + 63, top + 132, mouseX, mouseY);

        renderRecipeInfo(guiGraphics, screen, "spirit_focusing", left + 62, top + 74, mouseX, mouseY);
    }
}