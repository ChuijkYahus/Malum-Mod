package com.sammy.malum.client.screen.codex.pages.text;

import com.sammy.malum.MalumMod;
import com.sammy.malum.client.screen.codex.helper.*;
import com.sammy.malum.client.screen.codex.pages.*;
import com.sammy.malum.client.screen.codex.screens.*;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import static com.sammy.malum.client.screen.codex.helper.CodexTextHelper.renderHeadline;

public class HeadlineTextItemPage extends HeadlineTextPage {
    private final ItemStack stack;

    public HeadlineTextItemPage(String headline, String text, ItemStack stack) {
        super(headline, text);
        this.stack = stack;
    }

    public HeadlineTextItemPage(String text, ItemStack stack) {
        this(text, text + ".1", stack);
    }

    public HeadlineTextItemPage(String headline, String text, Item item) {
        this(headline, text, item.getDefaultInstance());
    }

    public HeadlineTextItemPage(String text, Item item) {
        this(text, item.getDefaultInstance());
    }

    @Override
    public ResourceLocation getBackground(boolean isRightSide) {
        return MalumMod.malumPath("textures/gui/book/pages/headline_item_page.png");
    }

    @Override
    public void render(CodexEntryScreen screen, GuiGraphics guiGraphics, int left, int top, int mouseX, int mouseY, float partialTicks, boolean isRepeat) {
        renderHeadline(guiGraphics, headline, left, top);
        CodexTextHelper.renderWrappingText(guiGraphics, text, left + 6, top + 75, 130);
        CodexItemHelper.renderItem(screen, guiGraphics, stack, left + 63, top + 38, mouseX, mouseY);
    }
}