package com.sammy.malum.client.screen.codex.helper;

import com.sammy.malum.client.screen.codex.screens.*;
import com.sammy.malum.common.item.spirit.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.gui.screens.*;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.neoforged.neoforge.common.crafting.*;

import java.util.*;
import java.util.stream.*;

public class CodexItemHelper {
    public static void renderIngredient(AbstractMalumCodexScreen screen, GuiGraphics guiGraphics, ICustomIngredient ingredient, int posX, int posY, int mouseX, int mouseY) {
        renderItem(screen, guiGraphics, ingredient.getItems().toList(), posX, posY, mouseX, mouseY);
    }

    public static void renderIngredient(AbstractMalumCodexScreen screen, GuiGraphics guiGraphics, SizedIngredient ingredient, int posX, int posY, int mouseX, int mouseY) {
        renderItem(screen, guiGraphics, List.of(ingredient.getItems()), posX, posY, mouseX, mouseY);
    }

    public static void renderIngredient(AbstractMalumCodexScreen screen, GuiGraphics guiGraphics, Ingredient ingredient, int posX, int posY, int mouseX, int mouseY) {
        renderItem(screen, guiGraphics, List.of(ingredient.getItems()), posX, posY, mouseX, mouseY);
    }

    public static void renderItem(AbstractMalumCodexScreen screen, GuiGraphics guiGraphics, ICustomIngredient ingredient, int posX, int posY, int mouseX, int mouseY) {
        renderItem(screen, guiGraphics, ingredient.getItems().toList(), posX, posY, mouseX, mouseY);
    }

    public static void renderItem(AbstractMalumCodexScreen screen, GuiGraphics guiGraphics, SizedIngredient ingredient, int posX, int posY, int mouseX, int mouseY) {
        renderItem(screen, guiGraphics, List.of(ingredient.getItems()), posX, posY, mouseX, mouseY);
    }

    public static void renderItem(AbstractMalumCodexScreen screen, GuiGraphics guiGraphics, Ingredient ingredient, int posX, int posY, int mouseX, int mouseY) {
        renderItem(screen, guiGraphics, List.of(ingredient.getItems()), posX, posY, mouseX, mouseY);
    }

    public static void renderItem(AbstractMalumCodexScreen screen, GuiGraphics guiGraphics, List<ItemStack> stacks, int posX, int posY, int mouseX, int mouseY) {
        if (stacks.isEmpty()) {
            return;
        }
        if (stacks.size() == 1) {
            renderItem(screen, guiGraphics, stacks.getFirst(), posX, posY, mouseX, mouseY);
            return;
        }
        int index = (int) (Minecraft.getInstance().level.getGameTime() % (20L * stacks.size()) / 20);
        ItemStack stack = stacks.get(index);
        renderItem(screen, guiGraphics, stack, posX, posY, mouseX, mouseY);
    }

    public static void renderItem(AbstractMalumCodexScreen screen, GuiGraphics guiGraphics, ItemStack stack, int posX, int posY, int mouseX, int mouseY) {
        if (!stack.isEmpty()) {
            guiGraphics.renderItem(stack, posX, posY);
            guiGraphics.renderItemDecorations(Minecraft.getInstance().font, stack, posX, posY, null);
            if (screen.isHovering(mouseX, mouseY, posX, posY, 16, 16)) {
                List<Component> tooltip = Screen.getTooltipFromItem(Minecraft.getInstance(), stack);
                screen.renderTooltip(tooltip);
            }
        }
    }

    public static void renderIngredients(AbstractMalumCodexScreen screen, GuiGraphics guiGraphics, List<?> ingredients, int left, int top, int mouseX, int mouseY, boolean vertical) {
        if (ingredients.isEmpty()) {
            return;
        }
        final List<List<ItemStack>> stackBundles =
                Stream.of(
                        ingredients.stream().filter(o -> o instanceof ICustomIngredient).map(o -> ((ICustomIngredient) o).getItems().toList()),
                        ingredients.stream().filter(o -> o instanceof SizedIngredient).map(o -> Arrays.stream(((SizedIngredient) o).getItems()).toList()),
                        ingredients.stream().filter(o -> o instanceof Ingredient).map(o -> Arrays.stream(((Ingredient) o).getItems()).toList())
                ).flatMap(s -> s).toList();
        renderItemList(screen, guiGraphics, stackBundles, left, top, mouseX, mouseY, vertical);
    }

    public static void renderItemList(AbstractMalumCodexScreen screen, GuiGraphics guiGraphics, List<List<ItemStack>> items, int left, int top, int mouseX, int mouseY, boolean isVertical) {
        int slots = items.size();
        int startingOffset = 9 * (slots - 1);
        renderItemFrames(guiGraphics, slots, left, top, isVertical);
        if (isVertical) {
            top -= startingOffset;
        } else {
            left -= startingOffset;
        }
        for (int i = 0; i < slots; i++) {
            List<ItemStack> list = items.get(i);
            int offset = i * 18;
            int oLeft = left + (isVertical ? 0 : offset);
            int oTop = top + (isVertical ? offset : 0);
            renderItem(screen, guiGraphics, list, oLeft, oTop, mouseX, mouseY);
        }
    }

    public static void renderItemFrames(GuiGraphics guiGraphics, int slots, int left, int top, boolean isVertical) {
        var poseStack = guiGraphics.pose();
        int startingOffset = 9 * (slots - 1);
        if (isVertical) {
            top -= startingOffset;
        } else {
            left -= startingOffset;
        }
        for (int i = slots - 1; i >= 0; i--) {
            int offset = i * 18;
            int u = isVertical ? 0 : 2;
            int v = isVertical ? 2 : 0;
            int oLeft = left - 1 + (isVertical ? -2 : offset);
            int oTop = top - 1 + (isVertical ? offset : -2);
            int width = isVertical ? 22 : 18;
            int height = isVertical ? 18 : 22;
            CodexRenderHelper.renderTexture(CodexEntryScreen.ITEM_SOCKET, poseStack, oLeft, oTop, u, v, width, height, 64, 64);
        }

        if (isVertical) {
            CodexRenderHelper.renderTexture(CodexEntryScreen.ITEM_SOCKET, poseStack, left - 3, top - 3, 0, 0, 22, 2, 64, 64);
            CodexRenderHelper.renderTexture(CodexEntryScreen.ITEM_SOCKET, poseStack, left - 3, top - 1 + 18 * (slots), 0, 20, 22, 2, 64, 64);
        } else {
            CodexRenderHelper.renderTexture(CodexEntryScreen.ITEM_SOCKET, poseStack, left - 3, top - 3, 0, 0, 2, 22, 64, 64);
            CodexRenderHelper.renderTexture(CodexEntryScreen.ITEM_SOCKET, poseStack, left - 1 + 18 * (slots), top - 3, 20, 0, 2, 22, 64, 64);
        }
    }
}
