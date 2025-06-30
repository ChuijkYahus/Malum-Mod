package com.sammy.malum.client.screen.waveform;

import com.mojang.blaze3d.platform.*;
import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.*;
import com.sammy.malum.client.screen.codex.*;
import com.sammy.malum.common.block.curiosities.redstone.*;
import com.sammy.malum.common.payloads.spirit_diode.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screens.*;
import net.minecraft.client.renderer.*;
import net.minecraft.core.*;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.*;
import net.minecraft.sounds.*;
import net.minecraft.util.*;
import net.neoforged.neoforge.network.*;
import org.jetbrains.annotations.*;
import org.joml.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.registry.client.*;
import team.lodestar.lodestone.systems.rendering.*;
import team.lodestar.lodestone.systems.rendering.shader.*;

import java.lang.Math;
import java.util.function.*;

import static com.sammy.malum.MalumMod.malumPath;
import static com.sammy.malum.client.screen.codex.ArcanaCodexHelper.renderTexture;
import static net.minecraft.util.FastColor.ARGB32.color;

public class ValueSettingsScreen extends Screen {

    private static final Function<GuiGraphics, LodestoneBufferWrapper> WRAPPER_FUNCTION = Util.memoize(guiGraphics -> new LodestoneBufferWrapper(LodestoneRenderTypes.ADDITIVE_TEXT, guiGraphics.bufferSource));
    private static final VFXBuilders.ScreenVFXBuilder VFX_BUILDER = VFXBuilders.createScreen();

    private static final ResourceLocation TEXTURE = malumPath("textures/gui/waveform_configuration.png");
    private static final ResourceLocation DIAL_TEXTURE = malumPath("textures/gui/waveform_configuration_dial.png");
    private static final int FADE_SIZE = 6;
    private static final int BORDER_SIZE = 5;
    private static final int DIAL_SIZE = 64;

    private final BlockPos pos;
    private final int interfaceWidth;
    private final int interfaceHeight;

    private int guiLeft, guiTop, xCenter, yCenter, dialLeft, dialTop, xDialCenter, yDialCenter;

    private boolean disableMouse;

    private int ticksOpen = 0;

    private SpiritDiodeBlockEntity.TimeIntervalType timeInterval;
    private int oldAngle, angle;

    public ValueSettingsScreen(SpiritDiodeBlockEntity diode) {
        super(diode.getTitleComponent());
        this.pos = diode.getBlockPos();
        this.angle = diode.frequency;
        this.timeInterval = diode.type;
        interfaceWidth = 180 + DIAL_SIZE;
        interfaceHeight = 60 + DIAL_SIZE;
        notifyServer(true);
    }

    @Override
    protected void init() {
        guiLeft = (width - interfaceWidth) / 2;
        guiTop = (height - interfaceHeight) / 2;
        xCenter = guiLeft + interfaceWidth / 2;
        yCenter = guiTop + interfaceHeight / 2;
        dialLeft = xCenter - DIAL_SIZE / 2;
        dialTop = guiTop + 20;

        xDialCenter = dialLeft + DIAL_SIZE / 2;
        yDialCenter = dialTop + DIAL_SIZE / 2;

        Window window = minecraft.getWindow();
        double x = minecraft.mouseHandler.xpos() * window.getGuiScaledWidth() / window.getScreenWidth();
        double y = minecraft.mouseHandler.ypos() * window.getGuiScaledHeight() / window.getScreenHeight();
        updateMousePosition(x, y);
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
        if (oldAngle != angle) {
            playSound(MalumSoundEvents.SPIRIT_DIODE_TICK);
            oldAngle = angle;
        }
        if (ticksOpen % 20 == 0) {
            notifyServer(true);
        }
    }

    @Override
    public void mouseMoved(double mouseX, double mouseY) {
        if (!disableMouse) {
            if (ArcanaCodexHelper.isHovering(mouseX, mouseY, dialLeft, dialTop, DIAL_SIZE, DIAL_SIZE)) {
                double offsetX = xDialCenter - mouseX;
                double offsetY = yDialCenter - mouseY;
                angle = clampAngle(Mth.ceil(Math.toDegrees(Math.atan2(offsetX, -offsetY)))+180);
            }
        }
        disableMouse = false;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        timeInterval = SpiritDiodeBlockEntity.TimeIntervalType.values()[(timeInterval.ordinal() + 1) % 3];
        playSound(MalumSoundEvents.SPIRIT_DIODE_LONG_TICK);
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
        angle = clampAngle(angle + (scrollY > 0 ? 1 : -1));
        updateMousePosition(mouseX, mouseY);
        disableMouse = true;
        return super.mouseScrolled(mouseX, mouseY, scrollX, scrollY);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        float delta = Math.min(1, (ticksOpen + partialTick) / 5f);
        int fadeXStart = (int) (guiLeft + ((interfaceWidth) * (0.5f - delta / 2))) - FADE_SIZE;
        int fadeXEnd = (int) (guiLeft + ((interfaceWidth) * (0.5f + delta / 2))) + FADE_SIZE;
        int fadeYStart = guiTop - FADE_SIZE;
        int fadeYEnd = guiTop + interfaceHeight + FADE_SIZE;

        int a = ((int) (0x80 * delta) << 24);
        guiGraphics.fillGradient(fadeXStart, fadeYStart, fadeXEnd, fadeYEnd, 0x101010 | a, 0x101010 | a);

        guiGraphics.drawString(font, title, xCenter - font.width(title) / 2, guiTop, 0xdddddd, false);
        for (int i = 0; i < 3; i++) {
            Component guideText = Component.translatable("malum.waveform_artifice.guide." + i);
            int y = guiTop + interfaceHeight - 10 * (3 - i);
            guiGraphics.drawString(font, guideText, xCenter - font.width(guideText) / 2, y, 0xdddddd, false);
        }

        renderBorderBackground(guiGraphics, dialLeft, dialTop, DIAL_SIZE, DIAL_SIZE);
        renderDialTexture(guiGraphics, dialLeft, dialTop);
        for (int i = 0; i < 3; i++) {
            var type = SpiritDiodeBlockEntity.TimeIntervalType.values()[i];
            renderIntervalDisplay(guiGraphics, type.getText(), dialLeft - BORDER_SIZE, dialTop + 13 * i, type.equals(timeInterval), partialTick);
        }
        renderBorder(guiGraphics, dialLeft, dialTop, DIAL_SIZE, DIAL_SIZE);
        var text = Component.literal("" + (angle));
        renderText(guiGraphics, text, xDialCenter+0.5f - font.width(text) / 2f, yDialCenter+0.5f - font.lineHeight / 2f, true, partialTick);
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

    public int clampAngle(int angle) {
        int newAngle = angle;
        if (InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), 340) || InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), 344)) {
            if (oldAngle > angle) {
                newAngle = Mth.floor((angle / 10f)) * 10;
            }
            else {
                newAngle = Mth.ceil((angle / 10f)) * 10;
            }
        }
        if (newAngle > 360) {
            newAngle -= 360;
        } else if (newAngle < 1) {
            newAngle += 360;
        }
        return newAngle;
    }
    protected void notifyServer(boolean isOpen) {
        PacketDistributor.sendToServer(new SpiritDiodeStateUpdatePayload(pos, isOpen, timeInterval, angle));
    }

    public void updateMousePosition(double mouseX, double mouseY) {
//        double offsetX = xDialCenter - mouseX;
//        double offsetY = yDialCenter - mouseY;
        double distance = DIAL_SIZE * 0.45f;//Math.sqrt(offsetX * offsetX + offsetY * offsetY);
        double rad = Math.toRadians(-angle+180);
        double newMouseX = xDialCenter + Math.sin(rad) * distance;
        double newMouseY = yDialCenter + Math.cos(rad) * distance;
        setCursor(newMouseX, newMouseY);
    }

    private void setCursor(double mouseX, double mouseY) {
        double guiScale = minecraft.getWindow()
                .getGuiScale();
        GLFW.glfwSetCursorPos(minecraft.getWindow()
                .getWindow(), mouseX * guiScale, mouseY * guiScale);
    }

    public void renderBorderBackground(GuiGraphics graphics, int x, int y, int width, int height) {
        int startX = x - BORDER_SIZE;
        int startY = y - BORDER_SIZE;
        int endX = x + width;
        int endY = y + height;
        graphics.fillGradient(startX + 1, startY + 1, endX + BORDER_SIZE - 1, endY + BORDER_SIZE - 1, 0, 0xFF0F0306, 0xFF1A1314);
    }

    public void renderIntervalDisplay(GuiGraphics graphics, Component text, int x, int y, boolean powered, float pPartialTick) {
        int offset = powered ? 7 : 5;
        renderTexture(graphics, x - offset, y, powered ? 0 : 7, 16, 7, 12);
        renderText(graphics, text, x - font.width(text) - offset, y + 1.5f, powered, pPartialTick);
    }

    public void renderBorder(GuiGraphics graphics, int x, int y, int width, int height) {
        int startX = x - BORDER_SIZE;
        int startY = y - BORDER_SIZE;
        int endX = x + width;
        int endY = y + height;

        renderTexture(graphics, startX, startY, 0, 0, BORDER_SIZE, BORDER_SIZE);
        renderTexture(graphics, endX, startY, 9, 0, BORDER_SIZE, BORDER_SIZE);
        renderTexture(graphics, endX, endY, 9, 9, BORDER_SIZE, BORDER_SIZE);
        renderTexture(graphics, startX, endY, 0, 9, BORDER_SIZE, BORDER_SIZE);


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
        shaderInstance.safeGetUniform("Speed").set(400f);
        shaderInstance.safeGetUniform("Intensity").set(100f);
        shaderInstance.safeGetUniform("UVCoordinates").set(new Vector4f(-1f, 2f, -1f, 2f));
        Supplier<ShaderInstance> shaderInstanceSupplier = () -> shaderInstance;

        VFXBuilders.ScreenVFXBuilder builder = VFXBuilders.createScreen()
                .setShader(shaderInstanceSupplier)
                .setAlpha(0.9f)
                .setColor(0.7f, 0.1f, 0.1f)
                .setShader(() -> shaderInstance);

        RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        renderDialTexture(graphics, builder, x, y);
        builder.setAlpha(0.2f);
        shaderInstance.safeGetUniform("Speed").set(800f);
        renderDialTexture(graphics, builder, x - 1, y);
        renderDialTexture(graphics, builder, x + 1, y);
        renderDialTexture(graphics, builder, x, y - 1);
        renderDialTexture(graphics, builder, x, y + 1);
        shaderInstance.setUniformDefaults();
        RenderSystem.defaultBlendFunc();
    }

    private void renderText(GuiGraphics guiGraphics, Component component, float x, float y, boolean isPowered, float partialTick) {
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

    public void renderDialTexture(GuiGraphics graphics, VFXBuilders.ScreenVFXBuilder builder, int x, int y) {
        RenderSystem.enableBlend();
        RenderSystem.enableDepthTest();
        builder.setShaderTexture(DIAL_TEXTURE).setPositionWithWidth(x, y, DIAL_SIZE, DIAL_SIZE)
                .blit(graphics.pose());
        RenderSystem.disableDepthTest();
        RenderSystem.disableBlend();
    }
}