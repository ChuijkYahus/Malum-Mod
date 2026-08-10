package com.sammy.malum.client.screen.codex.pages;

import com.sammy.malum.MalumMod;
import com.sammy.malum.client.screen.codex.handlers.BookObjectHandler;
import com.sammy.malum.client.screen.codex.helper.*;
import com.sammy.malum.client.screen.codex.screens.*;
import net.minecraft.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.network.chat.*;
import net.minecraft.resources.*;

import java.util.*;
import java.util.stream.*;


public abstract class BookPage {

    public static String headlineKey(String key) {
        return "malum.gui.book.entry." + key + ".headline";
    }

    public static String textKey(String key) {
        return "malum.gui.book.entry." + key + ".text";
    }

    public static String getRecipeInfoHeadlineKey(String recipeType) {
        return "malum.gui.book.entry.page.info." + recipeType + ".headline";
    }

    public static String getRecipeInfoKey(String recipeType) {
        return "malum.gui.book.entry.page.info." + recipeType;
    }

    public static boolean isVoidThemed = false;

    public boolean isValid() {
        return true;
    }

    public void render(CodexEntryScreen screen, GuiGraphics guiGraphics, int left, int top, int mouseX, int mouseY, float partialTicks, boolean isRepeat) {
    }

    public void renderLate(CodexEntryScreen screen, GuiGraphics guiGraphics, int left, int top, int mouseX, int mouseY, float partialTicks, boolean isRepeat) {
    }

    public void tick(CodexEntryScreen screen, int left, int top, boolean isRepeat) {
    }

    public void click(CodexEntryScreen screen, int left, int top, double mouseX, double mouseY, double relativeMouseX, double relativeMouseY) {
    }

    public BookObjectHandler<CodexEntryScreen> addObjects(CodexEntryScreen screen, int left, int top) {
        return null;
    }


    public abstract ResourceLocation getBackground();

    public final ResourceLocation getBackground(String path){
        var voidPage = MalumMod.malumPath("textures/gui/book/pages/" + path + "_void.png");
        var manager = Minecraft.getInstance().getResourceManager();
        var resource = manager.getResource(voidPage);
        if (resource.isPresent()) {
            return voidPage;
        }
        return MalumMod.malumPath("textures/gui/book/pages/" + path + ".png");
    }

    protected void renderRecipeInfo(GuiGraphics guiGraphics, CodexEntryScreen screen, String recipeName, int left, int top, int mouseX, int mouseY) {
        var headline = Component.translatable(getRecipeInfoHeadlineKey(recipeName)).withStyle(isVoidThemed ? ChatFormatting.DARK_PURPLE : ChatFormatting.GOLD);
        var header = Component.literal("┇ ").withStyle(ChatFormatting.DARK_GRAY);
        var info = Component.translatable(getRecipeInfoKey(recipeName)).withStyle(ChatFormatting.GRAY);
        var wrapped = CodexTextHelper.wrapComponent(info, header, 300);
        List<Component> tooltip = Stream.concat(Stream.of(headline), wrapped.stream()).toList();
        screen.renderTooltip(tooltip);
    }

    public int getPageMiddle(int left) {
        return left + CodexEntryScreen.PAGE_WIDTH/2 + 1;
    }
}