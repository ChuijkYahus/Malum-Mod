package com.sammy.malum.client.screen.codex.pages.recipe;

import com.sammy.malum.*;
import com.sammy.malum.client.screen.codex.pages.*;
import com.sammy.malum.client.screen.codex.screens.*;
import com.sammy.malum.common.recipe.*;
import com.sammy.malum.registry.common.recipe.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.network.chat.*;
import net.minecraft.world.item.*;
import team.lodestar.lodestone.systems.recipe.*;

import java.util.function.*;

import static com.sammy.malum.client.screen.codex.ArcanaCodexHelper.*;

public class RuneworkingPage extends BookPage {
    private static final Component BASE = Component.translatable("malum.gui.book.entry.page.info.runeworking");

    private final RuneworkingRecipe recipe;

    public RuneworkingPage(Predicate<RuneworkingRecipe> predicate) {
        this(LodestoneRecipeType.findRecipe(Minecraft.getInstance().level, MalumRecipeTypes.RUNEWORKING.get(), predicate));
    }

    public RuneworkingPage(RuneworkingRecipe recipe) {
        super(isVoidThemed
                ? MalumMod.malumPath("textures/gui/book/pages/runeworking_page_void.png")
                : MalumMod.malumPath("textures/gui/book/pages/runeworking_page.png"));
        this.recipe = recipe;
    }

    @Override
    public void render(EntryScreen screen, GuiGraphics guiGraphics, int left, int top, int mouseX, int mouseY, float partialTicks, boolean isRepeat) {
        renderIngredient(screen, guiGraphics, recipe.input, left + 63, top + 56, mouseX, mouseY);
        renderIngredient(screen, guiGraphics, recipe.secondaryInput, left + 63, top + 21, mouseX, mouseY);
        renderItem(screen, guiGraphics, recipe.output, left + 63, top + 132, mouseX, mouseY);
        screen.renderLater(() -> {
            if (screen.isHovering(mouseX, mouseY, left + 62, top + 74, 18, 18)) {
                guiGraphics.renderComponentTooltip(Minecraft.getInstance().font, wrapComponent(BASE, 180), mouseX, mouseY);
            }
        });
    }

    @Override
    public boolean isValid() {
        return recipe != null;
    }

    public static RuneworkingPage fromOutput(Item outputItem) {
        return new RuneworkingPage(s -> s.output.is(outputItem));
    }
}