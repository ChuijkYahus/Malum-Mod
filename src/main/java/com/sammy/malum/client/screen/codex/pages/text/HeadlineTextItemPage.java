package com.sammy.malum.client.screen.codex.pages.text;

import com.sammy.malum.MalumMod;
import com.sammy.malum.client.screen.codex.helper.*;
import com.sammy.malum.client.screen.codex.pages.*;
import com.sammy.malum.client.screen.codex.screens.*;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import static com.sammy.malum.client.screen.codex.helper.CodexTextHelper.renderHeadline;

public class HeadlineTextItemPage extends BookPage {
    private final Component headline;
    private final Component text;
    private final ItemStack spiritStack;

    public HeadlineTextItemPage(String headline, String text, ItemStack spiritStack) {
        super(MalumMod.malumPath("textures/gui/book/pages/headline_item_page.png"));
        this.headline = Component.translatable(BookPage.HEADLINE + "." + headline);
        this.text = Component.translatable(BookPage.TEXT + "." + text);
        this.spiritStack = spiritStack;
    }

    public HeadlineTextItemPage(String headline, String text, Item spirit) {
        this(headline, text, spirit.getDefaultInstance());
    }

    @Override
    public void render(CodexEntryScreen screen, GuiGraphics guiGraphics, int left, int top, int mouseX, int mouseY, float partialTicks, boolean isRepeat) {
        renderHeadline(guiGraphics, headline, left, top);
        CodexTextHelper.renderWrappingText(guiGraphics, text, left + 6, top + 75, 130);
        CodexItemHelper.renderItem(screen, guiGraphics, spiritStack, left + 63, top + 38, mouseX, mouseY);
    }
}