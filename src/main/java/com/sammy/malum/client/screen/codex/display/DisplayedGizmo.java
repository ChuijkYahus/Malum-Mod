package com.sammy.malum.client.screen.codex.display;

import com.mojang.datafixers.util.*;
import com.sammy.malum.MalumMod;
import com.sammy.malum.client.screen.codex.objects.*;
import com.sammy.malum.client.screen.codex.objects.progression.*;
import com.sammy.malum.client.screen.codex.pages.*;
import com.sammy.malum.client.screen.codex.pages.text.*;
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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

import javax.annotation.*;
import java.util.*;

public abstract class DisplayedGizmo {

    public static String title(String key) {
        return "malum.gui.book.gizmo." + key + ".title";
    }

    public static String subtext(String key) {
        return "malum.gui.book.gizmo." + key + ".subtext";
    }

    protected String id = "";

    public final void render(AbstractMalumCodexScreen screen, IGizmoHolder holder, GuiGraphics guiGraphics, int x, int y, int mouseX, int mouseY) {
        renderDecals(screen, holder, guiGraphics, x, y, mouseX, mouseY);
        if (screen instanceof AbstractProgressionCodexScreen) {
            return;
        }
        if (screen.isHovering(mouseX, mouseY, x, y, 16, 16)) {
            var tooltip = new ArrayList<Component>();
            addDefaultTooltip(holder, tooltip);
            gatherTooltip(holder, tooltip);
            screen.renderLater(() -> guiGraphics.renderComponentTooltip(Minecraft.getInstance().font, tooltip, mouseX, mouseY));
        }
    }

    protected final void addDefaultTooltip(IGizmoHolder holder, ArrayList<Component> tooltip) {
        var usedId = holder.getGizmoId();
        if (usedId.isEmpty()) {
            usedId = id;
        }
        if (!usedId.isEmpty()) {
            var title = holder instanceof HeadlineTextPage ? BookPage.headlineKey(usedId) : title(usedId);
            tooltip.add(Component.translatable(title).withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable(subtext(usedId)).withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
        }
    }

    public DisplayedGizmo setId(String id) {
        this.id = id;
        return this;
    }

    public abstract void renderDecals(AbstractMalumCodexScreen screen, IGizmoHolder holder, GuiGraphics guiGraphics, int x, int y, int mouseX, int mouseY);

    public void gatherTooltip(IGizmoHolder holder, List<Component> tooltip) {

    }

    public abstract ResourceLocation getPageBackground();



    public static DisplayedItem geas(Holder<GeasEffectType> geas) {
        return new DisplayedItem(geas.value().createDefaultStack());
    }

    public static DisplayedItem item(ItemLike item) {
        return new DisplayedItem(item.asItem().getDefaultInstance());
    }

    public static DisplayedItem item(ItemLike item, int count) {
        return new DisplayedItem(new ItemStack(item, count));
    }

    public static DisplayedItem item(ItemStack stack) {
        return new DisplayedItem(stack);
    }

    public static DisplayedTexture texture(CodexIconRenderer renderer) {
        return new DisplayedTexture(renderer);
    }

    public static class DisplayedItem extends DisplayedGizmo {

        protected final ItemStack itemDisplay;

        public DisplayedItem(ItemStack itemDisplay) {
            this.itemDisplay = itemDisplay;
        }

        @Override
        public void renderDecals(AbstractMalumCodexScreen screen, IGizmoHolder holder, GuiGraphics guiGraphics, int x, int y, int mouseX, int mouseY) {
            guiGraphics.renderItem(itemDisplay, x, y);
            guiGraphics.renderItemDecorations(Minecraft.getInstance().font, itemDisplay, x, y, null);
        }

        @Override
        public void gatherTooltip(IGizmoHolder holder, List<Component> tooltip) {
            if (tooltip.isEmpty()) {
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
        public void renderDecals(AbstractMalumCodexScreen screen, IGizmoHolder holder, GuiGraphics guiGraphics, int x, int y, int mouseX, int mouseY) {
            renderer.renderIcon(guiGraphics.pose(), x, y);
        }

        @Override
        public ResourceLocation getPageBackground() {
            return MalumMod.malumPath("textures/gui/book/pages/headline_icon_page.png");
        }
    }
}
