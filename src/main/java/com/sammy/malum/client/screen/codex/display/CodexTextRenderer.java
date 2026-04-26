package com.sammy.malum.client.screen.codex.display;

import com.mojang.blaze3d.systems.*;
import com.sammy.malum.client.screen.codex.screens.*;
import net.minecraft.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.*;
import net.minecraft.network.chat.*;
import net.minecraft.network.chat.Component;
import net.minecraft.util.*;
import org.joml.*;
import team.lodestar.lodestone.modules.core.easing.*;
import team.lodestar.lodestone.registry.client.*;
import team.lodestar.lodestone.systems.rendering.*;

import java.awt.*;
import java.lang.Math;
import java.util.function.*;

import static com.sammy.malum.client.screen.codex.helper.CodexTextHelper.*;
import static net.minecraft.client.gui.Font.DisplayMode.*;
import static net.minecraft.util.FastColor.ARGB32.color;

public class CodexTextRenderer {

    public static final Function<GuiGraphics, LodestoneBufferWrapper> WRAPPER_FUNCTION = Util.memoize(guiGraphics -> new LodestoneBufferWrapper(LodestoneRenderTypes.ADDITIVE_TEXT, guiGraphics.bufferSource));
    public static final TextColorData DEFAULT_TEXT_COLOR = new TextColorData(new Color(138, 79, 58), new Color(65, 41, 8), new Color(20, 44, 60), new Color(227, 39, 228));
    public static final TextColorData GEAS_POSITIVE_COLOR = new TextColorData(new Color(18, 52, 141), new Color(118, 52, 141), new Color(20, 44, 120), new Color(100, 100, 240));
    public static final TextColorData GEAS_NEGATIVE_COLOR = new TextColorData(new Color(141, 18, 52), new Color(118, 52, 141), new Color(120, 44, 20), new Color(240, 100, 100));

    protected Minecraft minecraft = Minecraft.getInstance();
    protected Font font = minecraft.font;

    protected float scale = 1f;
    protected float glowStrength = 0.4f;
    protected TextColorData textColor = DEFAULT_TEXT_COLOR;

    public static CodexTextRenderer create() {
        return new CodexTextRenderer();
    }

    public CodexTextRenderer setScale(float scale) {
        this.scale = scale;
        return this;
    }

    public CodexTextRenderer setGlowStrength(float glowStrength) {
        this.glowStrength = glowStrength;
        return this;
    }

    public CodexTextRenderer setTextColor(TextColorData textColor) {
        this.textColor = textColor;
        return this;
    }

    public void renderWrappingText(GuiGraphics guiGraphics, Component text, float x, float y, int width) {
        float appliedScale = scale;
        var translated = text.getString();
        if (translated.startsWith("$m")) {
            int i = translated.indexOf("/$");
            float value = Float.parseFloat(translated.substring(3, i));
            text = Component.literal(translated.substring(i + 2));
            appliedScale = value;
        }

        var font = Minecraft.getInstance().font;
        var wrapped = wrapText(text, (int) (width / appliedScale));
        for (int i = 0; i < wrapped.size(); i++) {
            String currentLine = wrapped.get(i);
            var charSequence = FormattedCharSequence.forward(currentLine, Style.EMPTY);
            float offset = i * (font.lineHeight + 1) * scale;
            renderText(guiGraphics, charSequence, x, y + offset);
        }
    }

    public void renderText(GuiGraphics guiGraphics, Component text, float x, float y) {
        renderText(guiGraphics, text.getVisualOrderText(), x, y);
    }

    public void renderText(GuiGraphics guiGraphics, FormattedCharSequence text, float x, float y) {
        var poseStack = guiGraphics.pose();
        float guiScale = (float) minecraft.getWindow().getGuiScale();
        float screenWidth = minecraft.getWindow().getScreenWidth();
        float screenHeight = minecraft.getWindow().getScreenHeight();

        poseStack.pushPose();
        poseStack.translate(x, y, 0);
        if (scale != 1) {
            poseStack.scale(scale, scale, 1);
        }

        var projection = new Vector4f(1, 1, 0, 1);
        projection.mul(poseStack.last().pose());

        poseStack.translate(-font.width(text) / 2f, -font.lineHeight/2f, 0);

        float relativeX = projection.x * guiScale / screenWidth;
        float relativeY = projection.y * guiScale / screenHeight;
        float mouseX = (float) (minecraft.mouseHandler.xpos() / screenWidth);
        float mouseY = (float) (minecraft.mouseHandler.ypos() / screenHeight);

        float differenceX = Mth.abs(relativeX - mouseX) * 10;
        float differenceY = Mth.abs(relativeY - mouseY) * 10;
        float horizontalDelta = Math.clamp(1 - differenceX, 0, 1);
        float verticalDelta = Math.clamp(1 - differenceY, 0, 1);
        float delta = Easing.QUINTIC_OUT.ease(horizontalDelta) * Easing.QUINTIC_OUT.ease(verticalDelta);
        if (CodexEntryScreen.textJump > 0) {
            double jumpDelta = delta * Easing.SINE_IN_OUT.ease(CodexEntryScreen.textJump);
            glowStrength *= (float) (1 + jumpDelta);
        }

        var gray = textColor.primaryColor().getRGB();
        var dark = textColor.secondaryColor().getRGB();

        MultiBufferSource buffer = guiGraphics.bufferSource;
        var pose = poseStack.last().pose();
        drawInBatch(buffer, pose, text, -1f, 0, color(64, gray));
        drawInBatch(buffer, pose, text, +1f, 0, color(32, gray));
        drawInBatch(buffer, pose, text, 0, 0 - 1f, color(32, gray));
        drawInBatch(buffer, pose, text, 0, 0 + 1f, color(92, gray));

        drawInBatch(buffer, pose, text, 0, 0, color(255, dark));

        int alpha = Mth.floor(255 * Easing.QUARTIC_IN.lerp(delta, 0.4f, 1) * glowStrength);
        if (alpha > 15) {
            float color = Easing.CUBIC_IN.ease(delta);
            Color start = textColor.glowStart();
            Color end = textColor.glowEnd();
            int r = (int) Mth.lerp(color, start.getRed(), end.getRed());
            int g = (int) Mth.lerp(color, start.getGreen(), end.getGreen());
            int b = (int) Mth.lerp(color, start.getBlue(), end.getBlue());
            buffer = WRAPPER_FUNCTION.apply(guiGraphics);
            RenderSystem.enableBlend();
            drawInBatch(buffer, pose, text, 0f, 0, color(alpha, r, g, b));

            drawInBatch(buffer, pose, text, 1f, 0, color(alpha / 2, r, g, b));
            drawInBatch(buffer, pose, text, -1f, 0, color(alpha / 3, r, g, b));
            drawInBatch(buffer, pose, text, 0, 1f, color(alpha / 2, r, g, b));
            drawInBatch(buffer, pose, text, 0, -1f, color(alpha / 3, r, g, b));


            RenderSystem.defaultBlendFunc();
        }
        poseStack.popPose();
    }

    protected void drawInBatch(MultiBufferSource buffer, Matrix4f pose, FormattedCharSequence text, float x, float y, int color) {
        font.drawInBatch(text, x, y, color, false, pose, buffer, NORMAL, 0, 15728880);
    }

    public record TextColorData(Color primaryColor, Color secondaryColor, Color glowStart, Color glowEnd) {
    }
}