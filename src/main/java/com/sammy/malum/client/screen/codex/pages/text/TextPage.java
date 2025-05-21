package com.sammy.malum.client.screen.codex.pages.text;

import com.sammy.malum.client.screen.codex.pages.*;
import com.sammy.malum.client.screen.codex.screens.*;
import net.minecraft.client.gui.*;
import net.minecraft.network.chat.*;

import static com.sammy.malum.client.screen.codex.ArcanaCodexHelper.*;

public class TextPage extends BookPage {
    public final Component text;

    public TextPage(String text) {
        super(null);
        this.text = Component.translatable(BookPage.TEXT + "." + text);
    }

    @Override
    public void render(EntryScreen screen, GuiGraphics guiGraphics, int left, int top, int mouseX, int mouseY, float partialTicks, boolean isRepeat) {
        renderWrappingText(guiGraphics, text, left + 6, top + 5, 130);
    }
}
