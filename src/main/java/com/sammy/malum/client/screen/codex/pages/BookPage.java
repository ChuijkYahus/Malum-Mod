package com.sammy.malum.client.screen.codex.pages;

import com.sammy.malum.client.screen.codex.*;
import com.sammy.malum.client.screen.codex.helper.*;
import com.sammy.malum.client.screen.codex.screens.*;
import net.minecraft.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.network.chat.*;
import net.minecraft.resources.*;

import javax.annotation.*;
import java.util.*;
import java.util.stream.*;


public abstract class BookPage {

    public static final String TEXT = "malum.gui.book.entry.page.text";
    public static final String HEADLINE = "malum.gui.book.entry.page.headline";

    public static String getRecipeInfoHeadlineKey(String recipeType) {
        return "malum.gui.book.entry.page.info." + recipeType + ".headline";
    }
    public static String getRecipeInfoKey(String recipeType) {
        return "malum.gui.book.entry.page.info." + recipeType;
    }

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
    
    public ResourceLocation getBackground(boolean isRightSide) {
        return background;
    }

    protected void renderRecipeInfo(GuiGraphics guiGraphics, CodexEntryScreen screen, String recipeName, int left, int top, int mouseX, int mouseY){
        screen.renderLater(() -> {
            if (screen.isHovering(mouseX, mouseY, left, top, 18, 18)) {
                var headline = Component.translatable(getRecipeInfoHeadlineKey(recipeName)).withStyle(isVoidThemed ? ChatFormatting.DARK_PURPLE : ChatFormatting.GOLD);
                var header = Component.literal("┇ ").withStyle(ChatFormatting.DARK_GRAY);
                var info = Component.translatable(getRecipeInfoKey(recipeName)).withStyle(ChatFormatting.GRAY);
                var wrapped = CodexTextHelper.wrapComponent(info, header, 300);
                List<Component> tooltip = Stream.concat(Stream.of(headline), wrapped.stream()).toList();
                guiGraphics.renderComponentTooltip(Minecraft.getInstance().font, tooltip, mouseX, mouseY);
            }
        });
    }
}