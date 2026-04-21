package com.sammy.malum.client.screen.codex.display;

import com.sammy.malum.MalumMod;
import com.sammy.malum.client.screen.codex.screens.AbstractMalumCodexScreen;
import com.sammy.malum.client.screen.codex.screens.progression.AbstractProgressionCodexScreen;
import com.sammy.malum.core.systems.geas.GeasEffectType;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public abstract class DisplayedGizmo {

    protected final List<Component> components = new ArrayList<>();

    public void render(AbstractMalumCodexScreen screen, GuiGraphics guiGraphics, int x, int y, int mouseX, int mouseY) {
        if (screen instanceof AbstractProgressionCodexScreen) {
            return;
        }
        if (screen.isHovering(mouseX, mouseY, x, y, 16, 16)) {
            List<Component> tooltip = new ArrayList<>();
            gatherTooltip(tooltip);
            tooltip.addAll(components);
            screen.renderLater(() -> guiGraphics.renderComponentTooltip(Minecraft.getInstance().font, tooltip, mouseX, mouseY));
        }
    }

    public void gatherTooltip(List<Component> tooltip) {

    }


    public DisplayedGizmo addTitleAndSnippet(String id) {
        return addTitle(id).addSnippet(id);
    }

    public DisplayedGizmo addTitle(String id) {
        return addText(Component.translatable("malum.gui.book.snippet.title." + id).withStyle(ChatFormatting.GOLD));
    }

    public DisplayedGizmo addSnippet(String id) {
        return addText(Component.translatable("malum.gui.book.snippet." + id).withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
    }

    public DisplayedGizmo addText(Component component) {
        components.add(component);
        return this;
    }

    public abstract ResourceLocation getPageBackground();

    public static DisplayedItem geas(Holder<GeasEffectType> geas) {
        return new DisplayedItem(geas.value().createDefaultStack());
    }

    public static DisplayedItem item(ItemLike item) {
        return new DisplayedItem(item.asItem().getDefaultInstance());
    }

    public static DisplayedItem item(ItemStack stack) {
        return new DisplayedItem(stack);
    }

    public static DisplayedTexture texture(CodexIconRenderer renderer) {
        return new DisplayedTexture(renderer);
    }

    public static class DisplayedItem extends DisplayedGizmo {

        protected final ItemStack itemDisplay;

        protected boolean hasItemText = true;

        public DisplayedItem(ItemStack itemDisplay) {
            this.itemDisplay = itemDisplay;
        }

        public DisplayedItem noItemText() {
            hasItemText = false;
            return this;
        }

        @Override
        public DisplayedItem addText(Component component) {
            noItemText();
            return (DisplayedItem) super.addText(component);
        }

        @Override
        public void render(AbstractMalumCodexScreen screen, GuiGraphics guiGraphics, int x, int y, int mouseX, int mouseY) {
            guiGraphics.renderItem(itemDisplay, x, y);
            guiGraphics.renderItemDecorations(Minecraft.getInstance().font, itemDisplay, x, y, null);
            super.render(screen, guiGraphics, x, y, mouseX, mouseY);
        }

        @Override
        public void gatherTooltip(List<Component> tooltip) {
            if (hasItemText) {
                tooltip.addAll(Screen.getTooltipFromItem(Minecraft.getInstance(), itemDisplay));
            }
        }

        @Override
        public ResourceLocation getPageBackground() {
            return MalumMod.malumPath("textures/gui/book/pages/headline_item_page.png");
        }
    }

    public static class DisplayedTexture extends DisplayedGizmo {

        protected final CodexIconRenderer renderer;

        public DisplayedTexture(CodexIconRenderer renderer) {
            this.renderer = renderer;
        }

        @Override
        public void render(AbstractMalumCodexScreen screen, GuiGraphics guiGraphics, int x, int y, int mouseX, int mouseY) {
            renderer.renderIcon(guiGraphics.pose(), x, y);
            super.render(screen, guiGraphics, x, y, mouseX, mouseY);
        }

        @Override
        public ResourceLocation getPageBackground() {
            return MalumMod.malumPath("textures/gui/book/pages/headline_icon_page.png");
        }
    }
}
