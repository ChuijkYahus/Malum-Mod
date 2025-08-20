package com.sammy.malum.client.screen.codex.pages.recipe;

import com.sammy.malum.*;
import com.sammy.malum.client.screen.codex.helper.*;
import com.sammy.malum.client.screen.codex.pages.*;
import com.sammy.malum.client.screen.codex.screens.*;
import com.sammy.malum.common.recipe.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.recipe.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.core.*;
import net.minecraft.network.chat.*;
import team.lodestar.lodestone.systems.recipe.*;

import java.util.function.*;

import static com.sammy.malum.client.screen.codex.helper.CodexTextHelper.renderHeadline;

public class SoulBindingPage extends BookPage {
    private static final Component BASE = Component.translatable("malum.gui.book.entry.page.info.soulbinding");
    private static final Component SPIRIT = Component.translatable("malum.gui.book.entry.page.info.soulbinding.spirit");
    private static final Component ITEM = Component.translatable("malum.gui.book.entry.page.info.soulbinding.item");

    private final SoulBindingRecipe recipe;

    public SoulBindingPage(Predicate<SoulBindingRecipe> predicate) {
        this(LodestoneRecipeType.findRecipe(Minecraft.getInstance().level, MalumRecipeTypes.SOUL_BINDING.get(), predicate));
    }

    public SoulBindingPage(SoulBindingRecipe recipe) {
        super(isVoidThemed
                ? MalumMod.malumPath("textures/gui/book/pages/soulbinding_page_void.png")
                : MalumMod.malumPath("textures/gui/book/pages/soulbinding_page.png"));
        this.recipe = recipe;
    }

    public String headlineTranslationKey() {
        return recipe.result.getLangKey();
    }

    @Override
    public void render(CodexEntryScreen screen, GuiGraphics guiGraphics, int left, int top, int mouseX, int mouseY, float partialTicks, boolean isRepeat) {
        renderHeadline(guiGraphics, Component.translatable(headlineTranslationKey()), left, top);
        CodexItemHelper.renderItem(screen, guiGraphics, recipe.result.createDefaultStack(), left + 63, top + 38, mouseX, mouseY);
        CodexItemHelper.renderIngredient(screen, guiGraphics, recipe.input, left + 63, top + 87, mouseX, mouseY);

        CodexItemHelper.renderIngredients(screen, guiGraphics, recipe.spirits, SPIRIT, left + 13, top + 87, mouseX, mouseY, true);
        if (!recipe.extraInputs.isEmpty()) {
            CodexItemHelper.renderIngredients(screen, guiGraphics, recipe.extraInputs, ITEM, left + 113, top + 87, mouseX, mouseY, true);
        }
        screen.renderLater(() -> {
            if (screen.isHovering(mouseX, mouseY, left + 60, top + 105, 18, 18)) {
                guiGraphics.renderComponentTooltip(Minecraft.getInstance().font, CodexTextHelper.wrapComponent(BASE, 180), mouseX, mouseY);
            }
        });
    }

    @Override
    public boolean isValid() {
        return recipe != null;
    }

    public static SoulBindingPage fromGeas(Holder<GeasEffectType> geasEffectType) {
        return new SoulBindingPage(s -> s.result.equals(geasEffectType.value()));
    }
}