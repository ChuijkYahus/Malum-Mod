package com.sammy.malum.client.screen.waveform;

import com.mojang.blaze3d.platform.*;
import com.sammy.malum.common.block.curiosities.artifice.waveform.*;
import com.sammy.malum.common.payloads.waveform.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.*;
import net.minecraft.util.*;
import net.neoforged.neoforge.network.*;
import team.lodestar.lodestone.modules.core.easing.Easing;

import java.lang.Math;

import static com.sammy.malum.MalumMod.malumPath;
import static com.sammy.malum.client.screen.codex.helper.CodexRenderHelper.renderTexture;
import static net.minecraft.util.FastColor.ARGB32.color;

public class DiodeValueConfigurationScreen extends AbstractValueConfigurationScreen {

    private static final int DIAL_SIZE = 64;

    protected static final ResourceLocation DIAL_TEXTURE = malumPath("textures/gui/waveform_artifice/waveform_configuration_dial.png");
    protected static final ResourceLocation DIAL_OVERLAY = malumPath("textures/gui/waveform_artifice/waveform_configuration_dial_overlay.png");

    private final SpiritDiodeBlockEntity diode;
    private SpiritDiodeBlockEntity.TimeIntervalType timeInterval;
    private float displayedAngle, displayedDelta;
    private int oldAngle, angle;

    public DiodeValueConfigurationScreen(SpiritDiodeBlockEntity diode) {
        super(getTitleComponent(diode), 180+DIAL_SIZE, 60+DIAL_SIZE, DIAL_SIZE, DIAL_SIZE);
        this.diode = diode;
        this.angle = diode.frequency;
        this.timeInterval = diode.type;
    }

    @Override
    protected void notifyServer(boolean isOpen) {
        PacketDistributor.sendToServer(new SpiritDiodeStateUpdatePayload(diode.getBlockPos(), isOpen, new SpiritDiodeBlockEntity.SpiritDiodeInfo(timeInterval, angle)));
    }

    @Override
    public void updateMousePosition(double mouseX, double mouseY) {
//        double offsetX = xDialCenter - mouseX;
//        double offsetY = yDialCenter - mouseY;
        double distance = DIAL_SIZE * 0.45f;//Math.sqrt(offsetX * offsetX + offsetY * offsetY);
        double rad = Math.toRadians(-angle + 180);
        double newMouseX = xDialCenter + Math.sin(rad) * distance;
        double newMouseY = yDialCenter + Math.cos(rad) * distance;
        setCursor(newMouseX, newMouseY);
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

    @Override
    public void tick() {
        displayedAngle = Mth.rotLerp(0.2f, displayedAngle, angle);
        displayedDelta = Mth.lerp(0.1f, displayedDelta, angle);
        super.tick();
    }

    public void renderDial(GuiGraphics graphics, int x, int y) {
        renderDial(graphics, DIAL_TEXTURE, x, y);
        int angle = Mth.floor(displayedAngle);
        float delta = displayedDelta / 360f;
        float range = Easing.SINE_IN_OUT.lerp(delta, 40f, 90f);
        float alpha = Easing.EXPO_IN_OUT.lerp(delta, 0.25f, 1f);
        renderDialOverlay(graphics, DIAL_TEXTURE, x, y, angle, range, alpha);
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
}