package com.sammy.malum.client.screen.codex.pages;

import com.sammy.malum.client.screen.codex.*;
import com.sammy.malum.client.screen.codex.screens.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.resources.*;

import javax.annotation.*;

import static com.sammy.malum.client.screen.codex.screens.CodexEntryScreen.*;

public abstract class BookPage {

    public static final String TEXT = "malum.gui.book.entry.page.text";
    public static final String HEADLINE = "malum.gui.book.entry.page.headline";

    public static boolean isVoidThemed = false;

    @Nullable
    protected final ResourceLocation background;

    public BookPage(@Nullable ResourceLocation background) {
        this.background = background;
    }

    public boolean isValid() {
        return true;
    }

    public void render(CodexEntryScreen screen, GuiGraphics guiGraphics, int left, int top, int mouseX, int mouseY, float partialTicks, boolean isRepeat) {
    }

    public void renderLate(CodexEntryScreen screen, GuiGraphics guiGraphics, int left, int top, int mouseX, int mouseY, float partialTicks, boolean isRepeat) {
    }

    public void click(CodexEntryScreen screen, int left, int top, double mouseX, double mouseY, double relativeMouseX, double relativeMouseY) {
    }


    public void render(Minecraft minecraft, GuiGraphics guiGraphics, CodexEntryScreen screen, int mouseX, int mouseY, float partialTicks, boolean isRepeat) {
    }

    public ResourceLocation getBackground(boolean isRightSide) {
        return background;
    }
}