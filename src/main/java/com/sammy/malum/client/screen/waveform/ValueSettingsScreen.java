package com.sammy.malum.client.screen.waveform;

import com.mojang.blaze3d.platform.*;
import com.sammy.malum.client.screen.codex.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.gui.screens.*;
import net.minecraft.network.chat.*;
import net.minecraft.resources.*;
import org.jetbrains.annotations.*;
import team.lodestar.lodestone.systems.rendering.*;

import static com.sammy.malum.MalumMod.malumPath;
import static com.sammy.malum.client.screen.codex.ArcanaCodexHelper.renderTexture;

public class ValueSettingsScreen extends Screen {

    public static final VFXBuilders.ScreenVFXBuilder VFX_BUILDER = VFXBuilders.createScreen();

    private static final int ROW_WIDTH = 22;
    private static final int ROW_HEIGHT = 8;
    private static final int ROW_SEPARATION = 4;
    private static final int BORDER_SIZE = 6;

    public static final ResourceLocation TEXTURE = malumPath("textures/gui/waveform_configuration.png");
    private int ticksOpen = 0;
    private final int rows;
    private final int columns;
    private final int interfaceWidth;
    private final int interfaceHeight;

    public ValueSettingsScreen(Component pTitle) {
        super(pTitle);
        rows = 10;
        columns = 3;
        interfaceWidth = 80 + rows * ROW_WIDTH - 15;
        interfaceHeight = 40 + columns * ROW_HEIGHT + (columns-1) * ROW_SEPARATION;
    }

    public boolean isHovering(double mouseX, double mouseY, float posX, float posY, int width, int height) {
        return ArcanaCodexHelper.isHovering(mouseX, mouseY, posX, posY, width, height);
    }

    @Override
    public void tick() {
        super.tick();
        ticksOpen++;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        int guiLeft = getGuiLeft();
        int guiTop = getGuiTop();

        float delta = Math.min(1, (ticksOpen + partialTick) / 5f);
        int fadeStart = (int) (guiLeft + ((interfaceWidth) * (0.5f - delta / 2))) - BORDER_SIZE;
        int fadeEnd = (int) (guiLeft + ((interfaceWidth) * (0.5f + delta / 2))) + BORDER_SIZE;

        int a = ((int) (0x80 * delta) << 24);
        guiGraphics.fillGradient(fadeStart, guiTop - BORDER_SIZE, fadeEnd, guiTop + interfaceHeight + BORDER_SIZE, 0x101010 | a, 0x101010 | a);

        var topText = "Redstone Interval";
        var bottomText = "Release Right Button To Confirm";
        int textCenter = guiLeft + interfaceWidth / 2;
        guiGraphics.drawString(font, topText, textCenter - font.width(topText) / 2, guiTop, 0xdddddd, false);
        guiGraphics.drawString(font, bottomText, textCenter - font.width(bottomText) / 2, guiTop+interfaceHeight-7, 0xdddddd, false);


        int selectionTypeAreaTop = guiTop+20;
        renderBorder(guiGraphics, guiLeft, guiTop+20, 60, interfaceHeight-40);
        guiGraphics.drawString(font, "Ticks", guiLeft, selectionTypeAreaTop,0xdddddd, false);
        guiGraphics.drawString(font, "Seconds", guiLeft, selectionTypeAreaTop+12,0xdddddd, false);
        guiGraphics.drawString(font, "Minutes", guiLeft, selectionTypeAreaTop+24,0xdddddd, false);

        int selectionAreaLeft = guiLeft+80;
        int selectionAreaTop = guiTop+20;
        int selectionAreaWidth = interfaceWidth-80;
        int selectionAreaHeight = interfaceHeight-40;

        renderBorder(guiGraphics, selectionAreaLeft, selectionAreaTop, selectionAreaWidth, selectionAreaHeight);
        for (int i = 0; i < columns; i++) {
            int y = selectionAreaTop + i * (ROW_HEIGHT + ROW_SEPARATION);
            for (int j = 0; j < rows; j++) {
                int x = selectionAreaLeft + j * ROW_WIDTH;
                renderTexture(guiGraphics, x, y, 0, 8, 7, 8);
                if (j != rows -1) {
                    renderTexture(guiGraphics, x + 7, y, 0, 0, 15, 8);
                }
            }
            guiGraphics.fillGradient(selectionAreaLeft+1, y+9, selectionAreaLeft+(rows-1)*ROW_WIDTH+6, y+11, 0x80080503, 0x80080503);
        }
    }


    @Override
    public void renderBackground(@NotNull GuiGraphics graphics, int pMouseX, int pMouseY, float pPartialTick) {
        int a = ((int) (0x50 * Math.min(1, (ticksOpen + pPartialTick) / 20f))) << 24;
        graphics.fillGradient(0, 0, this.width, this.height, 0x101010 | a, 0x101010 | a);
    }

    public void renderBorder(GuiGraphics graphics, int x, int y, int width, int height) {
        int borderSize = 5;
        int startX = x - borderSize - 2;
        int startY = y - borderSize - 2;
        int endX = x + width + 2;
        int endY = y + height + 2;

        graphics.fillGradient(startX+1, startY+1, endX+borderSize-1, endY+borderSize-1, 0xFF0F0306, 0xFF1A1314);


        renderTexture(graphics, startX, startY, 7, 8, borderSize, borderSize);
        renderTexture(graphics, endX, startY, 16, 8, borderSize, borderSize);
        renderTexture(graphics, endX, endY, 16, 17, borderSize, borderSize);
        renderTexture(graphics, startX, endY, 7, 17, borderSize, borderSize);


        renderTexture(graphics, startX + 5, startY, 13, 8, endX-startX - 5, 4, 2, 4);
        renderTexture(graphics, startX + 5, endY+1, 13, 18, endX-startX - 5, 4, 2, 4);

        renderTexture(graphics, startX, startY + 5, 7, 14, 4, endY-startY - 5, 4, 2);
        renderTexture(graphics, endX + 1, startY + 5, 17, 14, 4, endY-startY - 5, 4, 2);
    }

    public void renderTexture(GuiGraphics graphics, int x, int y, int u, int v, int width, int height) {
        ArcanaCodexHelper.renderTexture(TEXTURE, graphics.pose(), VFX_BUILDER, x, y, u, v, width, height, 32, 32);
    }
    public void renderTexture(GuiGraphics graphics, int x, int y, int u, int v, int xCoverage, int yCoverage, int width, int height) {
        ArcanaCodexHelper.renderTexture(TEXTURE, graphics.pose(), VFX_BUILDER, x, y, u, v, xCoverage, yCoverage, width, height, 32, 32);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public boolean keyReleased(int pKeyCode, int pScanCode, int pModifiers) {
        if (minecraft.options.keyUse.matches(pKeyCode, pScanCode)) {
            Window window = minecraft.getWindow();
            double x = minecraft.mouseHandler.xpos() * window.getGuiScaledWidth() / window.getScreenWidth();
            double y = minecraft.mouseHandler.ypos() * window.getGuiScaledHeight() / window.getScreenHeight();
            saveAndClose(x, y);
            return true;
        }
        return super.keyReleased(pKeyCode, pScanCode, pModifiers);
    }

    @Override
    public boolean mouseReleased(double pMouseX, double pMouseY, int pButton) {
        if (minecraft.options.keyUse.matchesMouse(pButton)) {
            saveAndClose(pMouseX, pMouseY);
            return true;
        }
        return super.mouseReleased(pMouseX, pMouseY, pButton);
    }

    protected void saveAndClose(double pMouseX, double pMouseY) {
//        ValueSettings closest = getClosestCoordinate((int) pMouseX, (int) pMouseY);
//        // FIXME: value settings may be face-sensitive on future components
//        CatnipServices.NETWORK.sendToServer(new ValueSettingsPacket(pos, closest.row(), closest.value(), null, null, Direction.UP,
//                AllKeys.ctrlDown(), netId));
        onClose();
    }
    public int getGuiLeft() {
        return (width - interfaceWidth) / 2;
    }

    public int getGuiTop() {
        return (height - interfaceHeight) / 2;
    }
}