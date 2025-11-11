package com.sammy.malum.client.screen.waveform;

import com.mojang.blaze3d.systems.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.gui.screens.*;
import net.minecraft.core.*;
import net.minecraft.network.chat.*;
import net.minecraft.resources.*;
import net.minecraft.sounds.*;
import net.minecraft.util.*;
import org.jetbrains.annotations.*;
import org.lwjgl.glfw.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.registry.client.*;
import team.lodestar.lodestone.systems.rendering.*;

import java.util.function.*;

import static com.sammy.malum.MalumMod.*;

public abstract class AbstractValueConfigurationScreen extends Screen {

    protected static final Function<GuiGraphics, LodestoneBufferWrapper> WRAPPER_FUNCTION = Util.memoize(guiGraphics -> new LodestoneBufferWrapper(LodestoneRenderTypes.ADDITIVE_TEXT, guiGraphics.bufferSource));

    protected static final ResourceLocation WIDGETS = malumPath("textures/gui/waveform_artifice/waveform_widgets.png");
    protected static final ResourceLocation DIAL_TEXTURE = malumPath("textures/gui/waveform_artifice/waveform_configuration_dial.png");

    protected static final int FADE_SIZE = 6;
    protected static final int BORDER_SIZE = 5;

    protected final int screenWidth;
    protected final int screenHeight;
    protected final int interfaceWidth;
    protected final int interfaceHeight;

    protected int guiLeft, guiTop, xCenter, yCenter, dialLeft, dialTop, xDialCenter, yDialCenter;

    protected boolean disableMouse;

    protected int ticksOpen = 0;

    public AbstractValueConfigurationScreen(Component component, int screenWidth, int screenHeight, int interfaceWidth, int interfaceHeight) {
        super(component);
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.interfaceWidth = interfaceWidth;
        this.interfaceHeight = interfaceHeight;
    }

    protected abstract void notifyServer(boolean isOpen);

    protected abstract boolean hasChanged();

    @Override
    protected void init() {
        guiLeft = (width - screenWidth) / 2;
        guiTop = (height - screenHeight) / 2;
        xCenter = guiLeft + screenWidth / 2;
        yCenter = guiTop + screenHeight / 2;
        dialLeft = xCenter - interfaceWidth / 2;
        dialTop = guiTop + 20;

        xDialCenter = dialLeft + interfaceWidth / 2;
        yDialCenter = dialTop + interfaceHeight / 2;
        notifyServer(true);
    }

    @SuppressWarnings("DataFlowIssue")
    public void playSound(Holder<SoundEvent> soundEvent) {
        var player = minecraft.player;
        var level = minecraft.level;
        level.playSound(player, player.blockPosition(), soundEvent.value(), SoundSource.BLOCKS, 0.8f, RandomHelper.randomBetween(level.getRandom(), 0.9f, 1.1f));
    }

    @Override
    public void tick() {
        super.tick();
        ticksOpen++;
        if (hasChanged()) {
            playSound(MalumSoundEvents.SPIRIT_DIODE_TICK);
        }
        if (ticksOpen % 20 == 0) {
            notifyServer(true);
        }
    }

    @Override
    public void mouseMoved(double mouseX, double mouseY) {
        disableMouse = false;
    }

    public static boolean isHovering(double mouseX, double mouseY, float posX, float posY, int width, int height) {
        return mouseX > posX && mouseX < posX + width && mouseY > posY && mouseY < posY + height;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        playSound(MalumSoundEvents.SPIRIT_DIODE_LONG_TICK);
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
        disableMouse = true;
        return super.mouseScrolled(mouseX, mouseY, scrollX, scrollY);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        float delta = Math.min(1, (ticksOpen + partialTick) / 5f);
        int fadeXStart = (int) (guiLeft + ((screenWidth) * (0.5f - delta / 2))) - FADE_SIZE;
        int fadeXEnd = (int) (guiLeft + ((screenWidth) * (0.5f + delta / 2))) + FADE_SIZE;
        int fadeYStart = guiTop - FADE_SIZE;
        int fadeYEnd = guiTop + screenHeight + FADE_SIZE;

        int a = ((int) (0x80 * delta) << 24);
        guiGraphics.fillGradient(fadeXStart, fadeYStart, fadeXEnd, fadeYEnd, 0x101010 | a, 0x101010 | a);

        guiGraphics.drawString(font, title, xCenter - font.width(title) / 2, guiTop, 0xdddddd, false);
    }

    @Override
    public void renderBackground(@NotNull GuiGraphics graphics, int pMouseX, int pMouseY, float pPartialTick) {
        int a = ((int) (0x50 * Math.min(1, (ticksOpen + pPartialTick) / 20f))) << 24;
        graphics.fillGradient(0, 0, this.width, this.height, 0x101010 | a, 0x101010 | a);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public boolean keyReleased(int pKeyCode, int pScanCode, int pModifiers) {
        if (minecraft.options.keyUse.matches(pKeyCode, pScanCode)) {
            onClose();
            return true;
        }
        return super.keyReleased(pKeyCode, pScanCode, pModifiers);
    }

    @Override
    public boolean mouseReleased(double pMouseX, double pMouseY, int pButton) {
        if (minecraft.options.keyUse.matchesMouse(pButton)) {
            onClose();
            return true;
        }
        return super.mouseReleased(pMouseX, pMouseY, pButton);
    }

    @Override
    public void onClose() {
        notifyServer(false);
        super.onClose();
    }

    protected void setCursor(double mouseX, double mouseY) {
        double guiScale = minecraft.getWindow()
                .getGuiScale();
        GLFW.glfwSetCursorPos(minecraft.getWindow()
                .getWindow(), mouseX * guiScale, mouseY * guiScale);
    }

    protected void renderBorderBackground(GuiGraphics graphics, int x, int y, int width, int height) {
        int startX = x - BORDER_SIZE;
        int startY = y - BORDER_SIZE;
        int endX = x + width;
        int endY = y + height;
        graphics.fillGradient(startX + 1, startY + 1, endX + BORDER_SIZE - 1, endY + BORDER_SIZE - 1, 0, 0xFF0F0306, 0xFF1A1314);
    }

    protected void renderTextWidget(GuiGraphics graphics, Component text, int x, int y, boolean powered, float pPartialTick) {
        int offset = powered ? 7 : 5;
        VFXBuilders.createScreen()
                .setTexture(WIDGETS)
                .setUVWithWidth(powered ? 0 : 7, 16, 7, 12, 32)
                .setPositionWithWidth(x - offset, y, 7, 12)
                .blit(graphics.pose());
        renderText(graphics, text, x - font.width(text) - offset, y + 1.5f, powered, pPartialTick);
    }

    public void renderBorder(GuiGraphics graphics, int x, int y, int width, int height) {
        int startX = x - BORDER_SIZE;
        int startY = y - BORDER_SIZE;
        int endX = x + width;
        int endY = y + height;

        renderWidgetTexture(graphics, startX, startY, 0, 0, BORDER_SIZE, BORDER_SIZE);
        renderWidgetTexture(graphics, endX, startY, 9, 0, BORDER_SIZE, BORDER_SIZE);
        renderWidgetTexture(graphics, endX, endY, 9, 9, BORDER_SIZE, BORDER_SIZE);
        renderWidgetTexture(graphics, startX, endY, 0, 9, BORDER_SIZE, BORDER_SIZE);


        renderWidgetTexture(graphics, startX + 5, startY, 6, 0, endX - startX - 5, 4, 2, 4);
        renderWidgetTexture(graphics, startX + 5, endY + 1, 6, 10, endX - startX - 5, 4, 2, 4);

        renderWidgetTexture(graphics, startX, startY + 5, 0, 6, 4, endY - startY - 5, 4, 2);
        renderWidgetTexture(graphics, endX + 1, startY + 5, 10, 6, 4, endY - startY - 5, 4, 2);
    }

    public void renderWidgetTexture(GuiGraphics graphics, int x, int y, int u, int v, int width, int height) {
        VFXBuilders.createScreen().setTexture(WIDGETS)
                .setPositionWithWidth(x, y, width, height)
                .setUVWithWidth(u, v, width, height, 32)
                .blit(graphics.pose());
    }

    public void renderWidgetTexture(GuiGraphics graphics, int x, int y, int u, int v, int xCoverage, int yCoverage, int width, int height) {
        VFXBuilders.createScreen().setTexture(WIDGETS)
                .setPositionWithWidth(x, y, xCoverage, yCoverage)
                .setUVWithWidth(u, v, width, height, 32)
                .blit(graphics.pose());
    }

    protected void renderText(GuiGraphics guiGraphics, Component component, float x, float y, boolean isPowered, float partialTick) {
        var text = component.getString();
        var font = minecraft.font;

        guiGraphics.drawString(font, text, x - 1f, y, 0x80320A0A, false);
        guiGraphics.drawString(font, text, x + 1f, y, 0x50320A0A, false);
        guiGraphics.drawString(font, text, x, y - 1f, 0x50A31818, false);
        guiGraphics.drawString(font, text, x, y + 1f, 0x60320A0A, false);

        guiGraphics.drawString(font, text, x, y, 0xA31818, false);

        if (isPowered) {
            float gameTime = (minecraft.level.getGameTime() + partialTick);
            int alpha = Mth.floor(255 * (0.4f + Mth.abs(0.3f * (Mth.sin((gameTime / 20f) % 6.28f)))));
            int base = (alpha << 24) | 0xE61919;
            int dim = base & 0xFFFFFF | (alpha / 3) << 24;
            int dimmer = base & 0xFFFFFF | (alpha / 6) << 24;

            var buffer = WRAPPER_FUNCTION.apply(guiGraphics);
            var pose = guiGraphics.pose().last().pose();
            RenderSystem.enableBlend();

            float offsetMultiplier = Mth.sin((gameTime / 10f) % 6.28f);
            float xOffset = 1.25f * offsetMultiplier;
            float yOffset = 2f * offsetMultiplier;

            font.drawInBatch(text, x, y, base, false, pose,
                    buffer, Font.DisplayMode.NORMAL, 0, 15728880, font.isBidirectional());

            font.drawInBatch(text, x + 1, y, dim, false, pose,
                    buffer, Font.DisplayMode.NORMAL, 0, 15728880, font.isBidirectional());
            font.drawInBatch(text, x - 1, y, dimmer, false, pose,
                    buffer, Font.DisplayMode.NORMAL, 0, 15728880, font.isBidirectional());
            font.drawInBatch(text, x, y + 1, dim, false, pose,
                    buffer, Font.DisplayMode.NORMAL, 0, 15728880, font.isBidirectional());
            font.drawInBatch(text, x, y - 1, dimmer, false, pose,
                    buffer, Font.DisplayMode.NORMAL, 0, 15728880, font.isBidirectional());

            font.drawInBatch(text, x + xOffset, y, dim, false, pose,
                    buffer, Font.DisplayMode.NORMAL, 0, 15728880, font.isBidirectional());
            font.drawInBatch(text, x - xOffset, y, dimmer, false, pose,
                    buffer, Font.DisplayMode.NORMAL, 0, 15728880, font.isBidirectional());
            font.drawInBatch(text, x, y + yOffset, dim, false, pose,
                    buffer, Font.DisplayMode.NORMAL, 0, 15728880, font.isBidirectional());
            font.drawInBatch(text, x, y - yOffset, dimmer, false, pose,
                    buffer, Font.DisplayMode.NORMAL, 0, 15728880, font.isBidirectional());
            RenderSystem.defaultBlendFunc();
        }
    }
}