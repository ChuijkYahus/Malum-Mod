package com.sammy.malum.client.screen.waveform;

import com.mojang.blaze3d.platform.*;
import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.*;
import com.sammy.malum.common.block.curiosities.redstone.*;
import com.sammy.malum.common.payloads.diode.*;
import net.minecraft.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.network.chat.Component;
import net.minecraft.util.*;
import net.neoforged.neoforge.network.*;
import org.lwjgl.opengl.*;
import team.lodestar.lodestone.registry.client.*;
import team.lodestar.lodestone.systems.rendering.*;
import team.lodestar.lodestone.systems.rendering.shader.*;

import java.lang.Math;
import java.util.function.*;

import static com.sammy.malum.client.screen.codex.helper.CodexRenderHelper.renderTexture;
import static net.minecraft.util.FastColor.ARGB32.color;

public class RadialValueConfigurationScreen extends AbstractValueConfigurationScreen {

    private static final int DIAL_SIZE = 64;

    private final SpiritDiodeBlockEntity diode;
    private SpiritDiodeBlockEntity.TimeIntervalType timeInterval;
    private int oldAngle, angle;

    public RadialValueConfigurationScreen(SpiritDiodeBlockEntity diode) {
        super(diode.getTitleComponent(), 180+DIAL_SIZE, 60+DIAL_SIZE, DIAL_SIZE, DIAL_SIZE);
        this.diode = diode;
        this.angle = diode.frequency;
        this.timeInterval = diode.type;
    }

    @Override
    protected void notifyServer(boolean isOpen) {
        PacketDistributor.sendToServer(new SpiritDiodeStateUpdatePayload(diode.getBlockPos(), isOpen, timeInterval, angle));
    }

    @Override
    protected boolean hasChanged() {
        if (oldAngle != angle) {
            oldAngle = angle;
            return true;
        }
        return false;
    }

    @Override
    protected void init() {
        super.init();
        Window window = minecraft.getWindow();
        double x = minecraft.mouseHandler.xpos() * window.getGuiScaledWidth() / window.getScreenWidth();
        double y = minecraft.mouseHandler.ypos() * window.getGuiScaledHeight() / window.getScreenHeight();
        updateMousePosition(x, y);
    }

    @Override
    public void mouseMoved(double mouseX, double mouseY) {
        if (!disableMouse) {
            if (isHovering(mouseX, mouseY, dialLeft, dialTop, DIAL_SIZE, DIAL_SIZE)) {
                double offsetX = xDialCenter - mouseX;
                double offsetY = yDialCenter - mouseY;
                angle = clampAngle(Mth.ceil(Math.toDegrees(Math.atan2(offsetX, -offsetY))) + 180);
            }
        }
        disableMouse = false;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        timeInterval = SpiritDiodeBlockEntity.TimeIntervalType.values()[(timeInterval.ordinal() + 1) % 3];
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
        angle = clampAngle(angle + (scrollY > 0 ? 1 : -1));
        updateMousePosition(mouseX, mouseY);
        return super.mouseScrolled(mouseX, mouseY, scrollX, scrollY);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        for (int i = 0; i < 3; i++) {
            Component guideText = Component.translatable("malum.waveform_artifice.guide." + i);
            int y = guiTop + screenHeight - 10 * (3 - i);
            guiGraphics.drawString(font, guideText, xCenter - font.width(guideText) / 2, y, 0xdddddd, false);
        }

        renderBorderBackground(guiGraphics, dialLeft, dialTop, DIAL_SIZE, DIAL_SIZE);
        renderDial(guiGraphics, dialLeft, dialTop);
        for (int i = 0; i < 3; i++) {
            var type = SpiritDiodeBlockEntity.TimeIntervalType.values()[i];
            renderTextWidget(guiGraphics, type.getText(true), dialLeft - BORDER_SIZE, dialTop + 13 * i, type.equals(timeInterval), partialTick);
        }
        renderBorder(guiGraphics, dialLeft, dialTop, DIAL_SIZE, DIAL_SIZE);
        var text = Component.literal("" + (angle));
        renderText(guiGraphics, text, xDialCenter + 0.5f - font.width(text) / 2f, yDialCenter + 0.5f - font.lineHeight / 2f, true, partialTick);
    }

    public int clampAngle(int angle) {
        int newAngle = angle;
        if (InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), 340) || InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), 344)) {
            if (oldAngle > angle) {
                newAngle = Mth.floor((angle / 10f)) * 10;
            } else {
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

    public void updateMousePosition(double mouseX, double mouseY) {
//        double offsetX = xDialCenter - mouseX;
//        double offsetY = yDialCenter - mouseY;
        double distance = DIAL_SIZE * 0.45f;//Math.sqrt(offsetX * offsetX + offsetY * offsetY);
        double rad = Math.toRadians(-angle + 180);
        double newMouseX = xDialCenter + Math.sin(rad) * distance;
        double newMouseY = yDialCenter + Math.cos(rad) * distance;
        setCursor(newMouseX, newMouseY);
    }

    public void renderDial(GuiGraphics graphics, int x, int y) {
        ExtendedShaderInstance shaderInstance = LodestoneShaders.SCREEN_DISTORTED_TEXTURE.getShaderInstance();
        shaderInstance.safeGetUniform("YFrequency").set(10f);
        shaderInstance.safeGetUniform("XFrequency").set(10f);
        shaderInstance.safeGetUniform("Speed").set(400f);
        shaderInstance.safeGetUniform("Intensity").set(100f);

        VFXBuilders.ScreenVFXBuilder builder = VFXBuilders.createScreen()
                .setShader(shaderInstance)
                .setAlpha(0.9f)
                .setColor(0.7f, 0.1f, 0.1f);

        RenderSystem.enableBlend();
        RenderSystem.enableDepthTest();
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
        RenderSystem.disableDepthTest();
        RenderSystem.disableBlend();
    }

    public void renderDialTexture(GuiGraphics graphics, VFXBuilders.ScreenVFXBuilder builder, int x, int y) {
        builder.setTexture(DIAL_TEXTURE).setPositionWithWidth(x, y, DIAL_SIZE, DIAL_SIZE).blit(graphics.pose());
    }
}