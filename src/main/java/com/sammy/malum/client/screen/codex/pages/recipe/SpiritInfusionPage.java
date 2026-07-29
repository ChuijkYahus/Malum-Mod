package com.sammy.malum.client.screen.codex.pages.recipe;

import com.sammy.malum.client.screen.codex.helper.*;
import com.sammy.malum.client.screen.codex.screens.CodexEntryScreen;
import com.sammy.malum.common.data.component.SoulwovenBannerPatternDataComponent;
import com.sammy.malum.common.recipe.SpiritInfusionRecipe;
import com.sammy.malum.core.systems.recipe.SpiritBasedRecipeInput;
import com.sammy.malum.core.systems.recipe.SpiritIngredient;
import com.sammy.malum.core.systems.spirit.SpiritArcanaType;
import com.sammy.malum.registry.common.recipe.*;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.*;
import net.minecraft.resources.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.*;

import java.awt.*;
import java.util.Map;
import java.util.function.Predicate;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.*;

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
    private static final Map<SpiritArcanaType, Point> SPIRIT_POSITIONS = Map.of(
            SACRED_SPIRIT.getSpirit(),   new Point(15, 69),
            WICKED_SPIRIT.getSpirit(),   new Point(95, 102),
            ARCANE_SPIRIT.getSpirit(),   new Point(95, 36),
            ELDRITCH_SPIRIT.getSpirit(), new Point(109, 69),
            AERIAL_SPIRIT.getSpirit(),   new Point(62, 116),
            AQUEOUS_SPIRIT.getSpirit(),  new Point(62, 22),
            EARTHEN_SPIRIT.getSpirit(),  new Point(29, 36),
            INFERNAL_SPIRIT.getSpirit(), new Point(29, 102),
            UMBRAL_SPIRIT.getSpirit(),   new Point(62, 0)
    );
    @Override
    public void render(CodexEntryScreen screen, GuiGraphics guiGraphics, int left, int top, int mouseX, int mouseY, float partialTicks, boolean isRepeat) {
//        CodexItemHelper.renderIngredients(screen, guiGraphics, recipe.spirits, left + 13, top + 75, mouseX, mouseY, true);
        for (SpiritIngredient spirit : recipe.spirits) {
            Point p = SPIRIT_POSITIONS.get(spirit.getSpirit());

            CodexItemHelper.renderIngredient(
                    screen,
                    guiGraphics,
                    spirit,
                    left + p.x,
                    top + p.y,
                    mouseX,
                    mouseY
            );
        }

//        CodexItemHelper.renderIngredients(screen, guiGraphics, recipe.extraInputs, left + 113, top + 200, mouseX, mouseY, true);
        int count = recipe.extraInputs.size();
        float radius = 24;
        for (int i = 0; i < count; i++) {
            double angle = -Math.PI / 2 + (Math.PI * 2 * i / count);

            int x = (int) Math.round(left + 63 + Math.cos(angle) * radius);
            int y = (int) Math.round(top + 70 + Math.sin(angle) * radius);

            CodexItemHelper.renderIngredient(
                    screen,
                    guiGraphics,
                    recipe.extraInputs.get(i),
                    x,
                    y,
                    mouseX,
                    mouseY
            );
        }

        CodexItemHelper.renderIngredient(screen, guiGraphics, recipe.input, left + 63, top + 70, mouseX, mouseY);
        CodexItemHelper.renderItem(screen, guiGraphics, recipe.result, left + 63, top + 162, mouseX, mouseY);

        renderRecipeInfo(guiGraphics, screen, "spirit_infusion", left + 62, top + 74, mouseX, mouseY);
    }
}