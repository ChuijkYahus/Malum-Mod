package com.sammy.malum.client.screen.waveform;

import com.mojang.blaze3d.platform.*;
import com.mojang.blaze3d.systems.*;
import com.mojang.blaze3d.vertex.*;
import com.sammy.malum.client.screen.codex.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.gui.screens.*;
import net.minecraft.client.renderer.*;
import net.minecraft.network.chat.*;
import net.minecraft.resources.*;
import org.jetbrains.annotations.*;
import org.joml.*;
import org.lwjgl.opengl.*;
import team.lodestar.lodestone.registry.client.*;
import team.lodestar.lodestone.systems.rendering.*;
import team.lodestar.lodestone.systems.rendering.shader.*;

import java.lang.Math;
import java.util.function.*;

import static com.sammy.malum.MalumMod.malumPath;
import static com.sammy.malum.client.screen.codex.ArcanaCodexHelper.renderTexture;

public class ValueSettingsScreen extends Screen {

    private static final VFXBuilders.ScreenVFXBuilder VFX_BUILDER = VFXBuilders.createScreen();

    public static final ResourceLocation TEXTURE = malumPath("textures/gui/waveform_configuration.png");
    public static final ResourceLocation DIAL_TEXTURE = malumPath("textures/gui/waveform_configuration_dial.png");

    private static final int BORDER_SIZE = 6;
    private static final int DIAL_SIZE = 64;
    private int ticksOpen = 0;

    private final int interfaceWidth;
    private final int interfaceHeight;

    public ValueSettingsScreen(Component pTitle) {
        super(pTitle);
        interfaceWidth = 120 + DIAL_SIZE;
        interfaceHeight = 50 + DIAL_SIZE;
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
        var middleText = "Scroll To Modify Measurement";
        var bottomText = "Release Right Button To Confirm";
        int xCenter = guiLeft + interfaceWidth / 2;
        guiGraphics.drawString(font, topText, xCenter - font.width(topText) / 2, guiTop, 0xdddddd, false);
        guiGraphics.drawString(font, middleText, xCenter - font.width(middleText) / 2, guiTop + interfaceHeight - 17, 0xdddddd, false);
        guiGraphics.drawString(font, bottomText, xCenter - font.width(bottomText) / 2, guiTop + interfaceHeight - 7, 0xdddddd, false);

        int dialLeft = xCenter - DIAL_SIZE/2;
        int dialTop = guiTop + 20;
        renderBorderBackground(guiGraphics, dialLeft, dialTop, DIAL_SIZE, DIAL_SIZE);
        renderDialTexture(guiGraphics, dialLeft, dialTop);
        renderBorder(guiGraphics, dialLeft, dialTop, DIAL_SIZE, DIAL_SIZE);
    }

    @Override
    public void renderBackground(@NotNull GuiGraphics graphics, int pMouseX, int pMouseY, float pPartialTick) {
        int a = ((int) (0x50 * Math.min(1, (ticksOpen + pPartialTick) / 20f))) << 24;
        graphics.fillGradient(0, 0, this.width, this.height, 0x101010 | a, 0x101010 | a);
    }

    public void renderBorderBackground(GuiGraphics graphics, int x, int y, int width, int height) {
        int borderSize = 5;
        int startX = x - borderSize;
        int startY = y - borderSize;
        int endX = x + width;
        int endY = y + height;
        graphics.fillGradient(startX + 1, startY + 1, endX + borderSize - 1, endY + borderSize - 1, 0, 0xFF0F0306, 0xFF1A1314);
    }

    public void renderBorder(GuiGraphics graphics, int x, int y, int width, int height) {
        int borderSize = 5;
        int startX = x - borderSize;
        int startY = y - borderSize;
        int endX = x + width;
        int endY = y + height;

        renderTexture(graphics, startX, startY, 0, 0, borderSize, borderSize);
        renderTexture(graphics, endX, startY, 9, 0, borderSize, borderSize);
        renderTexture(graphics, endX, endY, 9, 9, borderSize, borderSize);
        renderTexture(graphics, startX, endY, 0, 9, borderSize, borderSize);


        renderTexture(graphics, startX + 5, startY, 6, 0, endX - startX - 5, 4, 2, 4);
        renderTexture(graphics, startX + 5, endY + 1, 6, 10, endX - startX - 5, 4, 2, 4);

        renderTexture(graphics, startX, startY + 5, 0, 6, 4, endY - startY - 5, 4, 2);
        renderTexture(graphics, endX + 1, startY + 5, 10, 6, 4, endY - startY - 5, 4, 2);
    }

    public void renderTexture(GuiGraphics graphics, int x, int y, int u, int v, int width, int height) {
        ArcanaCodexHelper.renderTexture(TEXTURE, graphics.pose(), VFX_BUILDER, x, y, u, v, width, height, 32, 32);
    }

    public void renderTexture(GuiGraphics graphics, int x, int y, int u, int v, int xCoverage, int yCoverage, int width, int height) {
        ArcanaCodexHelper.renderTexture(TEXTURE, graphics.pose(), VFX_BUILDER, x, y, u, v, xCoverage, yCoverage, width, height, 32, 32);
    }

    public void renderDialTexture(GuiGraphics graphics, int x, int y) {
        ExtendedShaderInstance shaderInstance = (ExtendedShaderInstance) LodestoneShaders.SCREEN_DISTORTED_TEXTURE.getInstance().get();
        shaderInstance.safeGetUniform("YFrequency").set(10f);
        shaderInstance.safeGetUniform("XFrequency").set(10f);
        shaderInstance.safeGetUniform("Speed").set(1000f);
        shaderInstance.safeGetUniform("Intensity").set(100f);
        shaderInstance.safeGetUniform("UVCoordinates").set(new Vector4f(-1f, 2f, -1f, 2f));
        Supplier<ShaderInstance> shaderInstanceSupplier = () -> shaderInstance;

        VFXBuilders.ScreenVFXBuilder builder = VFXBuilders.createScreen()
                .setShader(shaderInstanceSupplier)
                .setAlpha(0.9f)
                .setColor(0.7f, 0.1f, 0.1f)
                .setZLevel(0)
                .setShader(() -> shaderInstance);

        RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        renderDialTexture(graphics, builder, x, y, 0);
        builder.setAlpha(0.2f);
        shaderInstance.safeGetUniform("Speed").set(2000f);
        renderDialTexture(graphics, builder, x - 1, y, 1);
        renderDialTexture(graphics, builder, x + 1, y, 2);
        renderDialTexture(graphics, builder, x, y - 1, 3);
        renderDialTexture(graphics, builder, x, y + 1, 4);
        shaderInstance.setUniformDefaults();
        RenderSystem.defaultBlendFunc();
    }

    public void renderDialTexture(GuiGraphics graphics, VFXBuilders.ScreenVFXBuilder builder, int x, int y, int z) {
        RenderSystem.enableBlend();
        RenderSystem.enableDepthTest();
        builder.setZLevel(z)
                .setShaderTexture(DIAL_TEXTURE).setPositionWithWidth(x, y, DIAL_SIZE, DIAL_SIZE)
                .blit(graphics.pose());
        RenderSystem.disableDepthTest();
        RenderSystem.disableBlend();
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