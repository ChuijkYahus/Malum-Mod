package com.sammy.malum.client.screen.codex.pages.text;

import com.sammy.malum.MalumMod;
import com.sammy.malum.client.screen.codex.helper.*;
import com.sammy.malum.client.screen.codex.pages.*;
import com.sammy.malum.client.screen.codex.screens.*;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.*;

public class HeadlineTextPage extends BookPage {

    protected final Component headline;
    protected final Component text;

    public HeadlineTextPage(String text) {
        this(text, text +".1");
    }

    public HeadlineTextPage(String headline, String text) {
        this.headline = Component.translatable(BookPage.HEADLINE + "." + headline);
        this.text = Component.translatable(BookPage.TEXT + "." + text);
    }

    @Override
    public ResourceLocation getBackground() {
        return MalumMod.malumPath("textures/gui/book/pages/headline_page.png");
    }

    @Override
    public void render(CodexEntryScreen screen, GuiGraphics guiGraphics, int left, int top, int mouseX, int mouseY, float partialTicks, boolean isRepeat) {
        CodexTextHelper.renderHeadline(guiGraphics, headline, left, top);
        CodexTextHelper.renderWrappingText(guiGraphics, text, left + 6, top + 32, 140);
    }
}