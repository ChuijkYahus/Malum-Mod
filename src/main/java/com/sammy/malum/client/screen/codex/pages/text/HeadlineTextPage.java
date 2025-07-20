package com.sammy.malum.client.screen.codex.pages.text;

import com.sammy.malum.MalumMod;
import com.sammy.malum.client.screen.codex.pages.*;
import com.sammy.malum.client.screen.codex.screens.*;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

import static com.sammy.malum.client.screen.codex.ArcanaCodexHelper.*;

public class HeadlineTextPage extends BookPage {

    private final Component headline;
    private final Component text;

    public HeadlineTextPage(String text) {
        this(text, text +".1");
    }
    public HeadlineTextPage(String headline, String text) {
        super(MalumMod.malumPath("textures/gui/book/pages/headline_page.png"));
        this.headline = Component.translatable(BookPage.HEADLINE + "." + headline);
        this.text = Component.translatable(BookPage.TEXT + "." + text);
    }

    @Override
    public void render(EntryScreen screen, GuiGraphics guiGraphics, int left, int top, int mouseX, int mouseY, float partialTicks, boolean isRepeat) {
        renderHeadline(guiGraphics, headline, left, top);
        renderWrappingText(guiGraphics, text, left + 6, top + 25, 130);
    }
}