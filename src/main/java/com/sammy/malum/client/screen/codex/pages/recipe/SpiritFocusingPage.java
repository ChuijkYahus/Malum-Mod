package com.sammy.malum.client.screen.codex.pages.recipe;

import com.sammy.malum.MalumMod;
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

import static com.sammy.malum.client.screen.codex.ArcanaCodexHelper.*;

public class SpiritFocusingPage extends BookPage {
    private final SpiritFocusingRecipe recipe;

    public static final Component BASE = Component.translatable("malum.gui.book.entry.page.info.spirit_focusing");
    public static final Component SPIRIT = Component.translatable("malum.gui.book.entry.page.info.spirit_focusing.spirit");

    public SpiritFocusingPage(Predicate<SpiritFocusingRecipe> predicate) {
        this(LodestoneRecipeType.findRecipe(Minecraft.getInstance().level, RecipeTypeRegistry.SPIRIT_FOCUSING.get(), predicate));
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
        return new SpiritFocusingPage(s -> s.ingredient.test(inputItem.getDefaultInstance()));
    }

    public static SpiritFocusingPage fromOutput(Item outputItem) {
        return new SpiritFocusingPage(s -> s.output.is(outputItem));
    }

    @Override
    public void render(EntryScreen screen, GuiGraphics guiGraphics, int left, int top, int mouseX, int mouseY, float partialTicks, boolean isRepeat) {
        renderIngredients(screen, guiGraphics, recipe.spirits, SPIRIT, left + 63, top + 16, mouseX, mouseY, false);
        renderIngredient(screen, guiGraphics, recipe.ingredient, left + 63, top + 56, mouseX, mouseY);
        renderItem(screen, guiGraphics, recipe.output, left + 63, top + 132, mouseX, mouseY);
        screen.renderLater(() -> {
            if (screen.isHovering(mouseX, mouseY, left + 62, top + 78, 18, 18)) {
                guiGraphics.renderComponentTooltip(Minecraft.getInstance().font, wrapComponent(BASE, 180), mouseX, mouseY);
            }
        });
    }
}