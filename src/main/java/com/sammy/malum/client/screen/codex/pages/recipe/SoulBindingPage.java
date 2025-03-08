package com.sammy.malum.client.screen.codex.pages.recipe;

import com.sammy.malum.*;
import com.sammy.malum.client.screen.codex.pages.*;
import com.sammy.malum.client.screen.codex.screens.*;
import com.sammy.malum.common.recipe.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.item.*;
import com.sammy.malum.registry.common.recipe.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.core.*;
import net.minecraft.network.chat.*;
import net.minecraft.world.item.*;
import team.lodestar.lodestone.systems.recipe.*;

import java.util.function.*;

import static com.sammy.malum.client.screen.codex.ArcanaCodexHelper.*;

public class SoulBindingPage extends BookPage {
    private static final Component BASE = Component.translatable("malum.gui.book.entry.page.info.soulbinding");

    private final SoulBindingRecipe recipe;

    public SoulBindingPage(Predicate<SoulBindingRecipe> predicate) {
        this(LodestoneRecipeType.findRecipe(Minecraft.getInstance().level, RecipeTypeRegistry.SOUL_BINDING.get(), predicate));
    }

    public SoulBindingPage(SoulBindingRecipe recipe) {
        super(isVoidThemed
                ? MalumMod.malumPath("textures/gui/book/pages/soulbinding_page_void.png")
                : MalumMod.malumPath("textures/gui/book/pages/soulbinding_page.png"));
        this.recipe = recipe;
    }

    public String headlineTranslationKey() {
        return recipe.geas.getLangKey();
    }

    @Override
    public void render(EntryScreen screen, GuiGraphics guiGraphics, int left, int top, int mouseX, int mouseY, float partialTicks, boolean isRepeat) {
        Component component = Component.translatable(headlineTranslationKey());
        renderText(guiGraphics, component, left + 72 - Minecraft.getInstance().font.width(component.getString()) / 2f, top + 5);
        renderItem(screen, guiGraphics, recipe.geas.createDefaultStack(), left + 63, top + 38, mouseX, mouseY);

        renderIngredient(screen, guiGraphics, recipe.ingredient, left + 63, top + 87, mouseX, mouseY);
        screen.renderLater(() -> {
            if (screen.isHovering(mouseX, mouseY, left + 60, top + 105, 18, 18)) {
                guiGraphics.renderComponentTooltip(Minecraft.getInstance().font, wrapComponent(BASE, 180), mouseX, mouseY);
            }
        });
    }

    @Override
    public boolean isValid() {
        return recipe != null;
    }

    public static SoulBindingPage fromGeas(Holder<GeasEffectType> geasEffectType) {
        return new SoulBindingPage(s -> s.geas.equals(geasEffectType.value()));
    }
}