package com.sammy.malum.client.screen.codex.helper;

import com.mojang.blaze3d.systems.*;
import com.sammy.malum.client.screen.codex.screens.*;
import net.minecraft.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.*;
import net.minecraft.network.chat.Component;
import net.minecraft.util.*;
import team.lodestar.lodestone.registry.client.*;
import team.lodestar.lodestone.modules.core.easing.Easing;
import team.lodestar.lodestone.systems.rendering.*;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.*;

import static com.sammy.malum.config.ClientConfig.BOOK_THEME;
import static net.minecraft.util.FastColor.ARGB32.color;

public class CodexTextHelper {
    public static final Function<GuiGraphics, LodestoneBufferWrapper> WRAPPER_FUNCTION = Util.memoize(guiGraphics -> new LodestoneBufferWrapper(LodestoneRenderTypes.ADDITIVE_TEXT, guiGraphics.bufferSource));
    public static final TextColorData DEFAULT_TEXT_COLOR = new TextColorData(new Color(138, 79, 58), new Color(65, 41, 8), new Color(20, 44, 60), new Color(227, 39, 228));
    public static final TextColorData GEAS_POSITIVE_COLOR = new TextColorData(new Color(18, 52, 141), new Color(118, 52, 141), new Color(20, 44, 120), new Color(100, 100, 240));
    public static final TextColorData GEAS_NEGATIVE_COLOR = new TextColorData(new Color(141, 18, 52), new Color(118, 52, 141), new Color(120, 44, 20), new Color(240, 100, 100));

    public static void renderWrappingText(GuiGraphics guiGraphics, String text, float x, float y, int width) {
        renderWrappingText(guiGraphics, Component.translatable(text), x, y, width);
    }

    public static void renderWrappingText(GuiGraphics guiGraphics, Component text, float x, float y, int width) {
        renderWrappingText(guiGraphics, DEFAULT_TEXT_COLOR, text, x, y, width);
    }

    public static void renderWrappingText(GuiGraphics guiGraphics, TextColorData colorData, String text, float x, float y, int width) {
        renderWrappingText(guiGraphics, colorData, Component.translatable(text), x, y, width);
    }

    public static void renderWrappingText(GuiGraphics guiGraphics, TextColorData colorData, Component text, float x, float y, int width) {
        float scale = 1;
        String translated = text.getString();
        if (translated.startsWith("$m")) {
            final int i = translated.indexOf("/$");
            float value = Float.parseFloat(translated.substring(3, i));
            text = Component.literal(translated.substring(i + 2));
            scale = value;
        }
        renderWrappingText(guiGraphics, colorData, text, x, y, width, scale);
    }

    public static void renderWrappingText(GuiGraphics guiGraphics, TextColorData colorData, Component text, float x, float y, int width, float scaleMultiplier) {
        Font font = Minecraft.getInstance().font;
        var wrapped = wrapText(text, (int) (width / scaleMultiplier));
        for (int i = 0; i < wrapped.size(); i++) {
            String currentLine = wrapped.get(i);
            float textX = x;
            float textY = y;
            if (scaleMultiplier != 1) {
                textX /= scaleMultiplier;
                textY /= scaleMultiplier;
            }
            renderRawText(guiGraphics, colorData, currentLine, textX, textY + i * (font.lineHeight + 1), 0.2f, scaleMultiplier);
        }
    }

    public static void renderHeadline(GuiGraphics graphics, Component component, int left, int top) {
        int width = Minecraft.getInstance().font.width(component.getString());
        float scale = 1f;
        if (width > 100) {
            scale -= (width - 100) / 200f;
        }
        float textLeft = left + 72;
        float textTop = top + 7;

        if (scale != 1) {
            textLeft /= scale;
            textTop /= scale;
            textTop += 5 * (1 - scale);
        }
        renderText(graphics, component, textLeft - width / 2f, textTop, scale);
    }

    public static void renderText(GuiGraphics guiGraphics, Component component, float x, float y) {
        renderText(guiGraphics, DEFAULT_TEXT_COLOR, component, x, y, 0.4f);
    }

    public static void renderText(GuiGraphics guiGraphics, Component component, float x, float y, float scale) {
        renderRawText(guiGraphics, DEFAULT_TEXT_COLOR, component.getString(), x, y, 0.4f, scale);
    }

    public static void renderText(GuiGraphics guiGraphics, TextColorData colorData, Component component, float x, float y, float glow) {
        renderText(guiGraphics, colorData, component, x, y, glow, 1f);
    }

    public static void renderText(GuiGraphics guiGraphics, TextColorData colorData, Component component, float x, float y, float glow, float scale) {
        String text = component.getString();
        renderRawText(guiGraphics, colorData, text, x, y, glow, scale);
    }

    private static void renderRawText(GuiGraphics guiGraphics, TextColorData colorData, String text, float x, float y, float glowMultiplier, float scaleMultiplier) {
        var minecraft = Minecraft.getInstance();
        var poseStack = guiGraphics.pose();
        var font = minecraft.font;
        float guiScale = (float) minecraft.getWindow().getGuiScale();
        float inverseScale = (4 / guiScale) * 4 * scaleMultiplier;

        float width = font.width(text) / 2f;
        float screenWidth = minecraft.getWindow().getScreenWidth();
        float screenHeight = minecraft.getWindow().getScreenHeight();
        float mouseX = (float) (minecraft.mouseHandler.xpos() / screenWidth);
        float mouseY = (float) (minecraft.mouseHandler.ypos() / screenHeight);
        float lineHeight = font.lineHeight;
        if (scaleMultiplier != 1) {
            poseStack.pushPose();
            poseStack.scale(scaleMultiplier, scaleMultiplier, 1);
            mouseX /= scaleMultiplier;
            mouseY /= scaleMultiplier;
        }
        float textX = ((x + width) * guiScale) / screenWidth;
        float textY = ((y + lineHeight) * guiScale) / screenHeight;
        float differenceX = (textX - mouseX);
        float differenceY = (textY - mouseY);
        float horizontalDelta = Math.clamp(1 - Mth.abs(differenceX) * inverseScale, 0, 1);
        float verticalDelta = Math.clamp(1 - Mth.abs(differenceY) * inverseScale, 0, 1);
        if (differenceY > 0) {
            verticalDelta = (float) Math.pow(verticalDelta * (1 - differenceY), 3);
        }
        float delta = Easing.QUINTIC_OUT.ease(horizontalDelta) * Easing.QUINTIC_OUT.ease(verticalDelta);
        if (CodexEntryScreen.textJump > 0) {
            double jumpDelta = delta * Easing.SINE_IN_OUT.ease(CodexEntryScreen.textJump);
            glowMultiplier *= (float) (1 + jumpDelta);
        }

        if (BOOK_THEME.getConfigValue().equals(BookTheme.EASY_READING)) {
            Color color = colorData.secondaryColor();
            guiGraphics.drawString(font, text, x, y, 0, false);
            font.drawInBatch(text, x, y, color(1, color.getRGB()), false, poseStack.last().pose(),
                    guiGraphics.bufferSource, Font.DisplayMode.NORMAL, 0, 15728880, font.isBidirectional());
        } else {
            Color gray = colorData.primaryColor();
            Color dark = colorData.secondaryColor();

            guiGraphics.drawString(font, text, x - 1f, y, color(64, gray.getRGB()), false);
            guiGraphics.drawString(font, text, x + 1f, y, color(32, gray.getRGB()), false);
            guiGraphics.drawString(font, text, x, y - 1f, color(32, gray.getRGB()), false);
            guiGraphics.drawString(font, text, x, y + 1f, color(92, gray.getRGB()), false);

            guiGraphics.drawString(font, text, x, y, color(255, dark.getRGB()), false);

            int alpha = Mth.floor(255 * Easing.QUARTIC_IN.lerp(delta, 0.4f, 1) * glowMultiplier);
            if (alpha > 15) {
                float color = Easing.CUBIC_IN.ease(delta);
                Color start = colorData.glowStart();
                Color end = colorData.glowEnd();
                int r = (int) Mth.lerp(color, start.getRed(), end.getRed());
                int g = (int) Mth.lerp(color, start.getGreen(), end.getGreen());
                int b = (int) Mth.lerp(color, start.getBlue(), end.getBlue());
                var buffer = WRAPPER_FUNCTION.apply(guiGraphics);
                var pose = poseStack.last().pose();
                RenderSystem.enableBlend();
                font.drawInBatch(text, x, y, color(alpha, r, g, b), false, pose,
                        buffer, Font.DisplayMode.NORMAL, 0, 15728880, font.isBidirectional());

                font.drawInBatch(text, x + 1f, y, color(alpha / 2, r, g, b), false, pose,
                        buffer, Font.DisplayMode.NORMAL, 0, 15728880, font.isBidirectional());
                font.drawInBatch(text, x - 1f, y, color(alpha / 3, r, g, b), false, pose,
                        buffer, Font.DisplayMode.NORMAL, 0, 15728880, font.isBidirectional());
                font.drawInBatch(text, x, y + 1f, color(alpha / 2, r, g, b), false, pose,
                        buffer, Font.DisplayMode.NORMAL, 0, 15728880, font.isBidirectional());
                font.drawInBatch(text, x, y - 1f, color(alpha / 3, r, g, b), false, pose,
                        buffer, Font.DisplayMode.NORMAL, 0, 15728880, font.isBidirectional());


                RenderSystem.defaultBlendFunc();
            }
        }
        if (scaleMultiplier != 1) {
            poseStack.popPose();
        }
    }


    public static MutableComponent convertToComponent(String text) {
        return convertToComponent(text, UnaryOperator.identity());
    }

    public static MutableComponent convertToComponent(String text, UnaryOperator<Style> styleModifier) {
        text = Component.translatable(text).getString();

        MutableComponent raw = Component.empty();

        boolean italic = false;
        boolean bold = false;
        boolean strikethrough = false;
        boolean underline = false;
        boolean obfuscated = false;

        StringBuilder line = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char chr = text.charAt(i);
            if (chr == '$') {
                if (i != text.length() - 1) {
                    char peek = text.charAt(i + 1);
                    switch (peek) {
                        case 'i' -> {
                            line = commitComponent(raw, italic, bold, strikethrough, underline, obfuscated, line, styleModifier);
                            italic = true;
                            i++;
                        }
                        case 'b' -> {
                            line = commitComponent(raw, italic, bold, strikethrough, underline, obfuscated, line, styleModifier);
                            bold = true;
                            i++;
                        }
                        case 's' -> {
                            line = commitComponent(raw, italic, bold, strikethrough, underline, obfuscated, line, styleModifier);
                            strikethrough = true;
                            i++;
                        }
                        case 'u' -> {
                            line = commitComponent(raw, italic, bold, strikethrough, underline, obfuscated, line, styleModifier);
                            underline = true;
                            i++;
                        }
                        case 'k' -> {
                            line = commitComponent(raw, italic, bold, strikethrough, underline, obfuscated, line, styleModifier);
                            obfuscated = true;
                            i++;
                        }
                        default -> line.append(chr);
                    }
                } else {
                    line.append(chr);
                }
            } else if (chr == '/') {
                if (i != text.length() - 1) {
                    char peek = text.charAt(i + 1);
                    if (peek == '$') {
                        line = commitComponent(raw, italic, bold, strikethrough, underline, obfuscated, line, styleModifier);
                        italic = bold = strikethrough = underline = obfuscated = false;
                        i++;
                    } else
                        line.append(chr);
                } else
                    line.append(chr);
            } else {
                line.append(chr);
            }
        }
        commitComponent(raw, italic, bold, strikethrough, underline, obfuscated, line, styleModifier);

        return raw;
    }

    public static List<Component> wrapComponent(Component source, MutableComponent header, int width) {
        var wrapped = wrapText(source, width).stream().map(Component::literal).toList();
        var result = new ArrayList<Component>();
        for (MutableComponent mutableComponent : wrapped) {
            result.add(header.copy().append(mutableComponent.setStyle(source.getStyle())));
        }
        return result;
    }

    public static List<Component> wrapComponent(Component source, int width) {
        return wrapText(source, width).stream().map(Component::literal).peek(component -> component.setStyle(source.getStyle())).map(Component.class::cast).toList();
    }

    public static List<String> wrapText(Component component, int width) {
        Font font = Minecraft.getInstance().font;
        String text = component.getString() + "\n";
        List<String> lines = new ArrayList<>();

        boolean italic = false;
        boolean bold = false;
        boolean strikethrough = false;
        boolean underline = false;
        boolean obfuscated = false;

        StringBuilder line = new StringBuilder();
        StringBuilder word = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char chr = text.charAt(i);
            if (chr == ' ' || chr == '\n') {
                if (!word.isEmpty()) {
                    if (font.width(line.toString()) + font.width(word.toString()) > width) {
                        line = newLine(lines, italic, bold, strikethrough, underline, obfuscated, line);
                    }
                    line.append(word).append(' ');
                    word = new StringBuilder();
                }

                String noFormatting = ChatFormatting.stripFormatting(line.toString());

                if (chr == '\n' && noFormatting != null) {
                    line = newLine(lines, italic, bold, strikethrough, underline, obfuscated, line);
                }
            } else if (chr == '$') {
                if (i != text.length() - 1) {
                    char peek = text.charAt(i + 1);
                    switch (peek) {
                        case 'i' -> {
                            word.append(ChatFormatting.ITALIC);
                            italic = true;
                            i++;
                        }
                        case 'b' -> {
                            word.append(ChatFormatting.BOLD);
                            bold = true;
                            i++;
                        }
                        case 's' -> {
                            word.append(ChatFormatting.STRIKETHROUGH);
                            strikethrough = true;
                            i++;
                        }
                        case 'u' -> {
                            word.append(ChatFormatting.UNDERLINE);
                            underline = true;
                            i++;
                        }
                        case 'k' -> {
                            word.append(ChatFormatting.OBFUSCATED);
                            obfuscated = true;
                            i++;
                        }
                        default -> word.append(chr);
                    }
                } else {
                    word.append(chr);
                }
            } else if (chr == '/') {
                if (i != text.length() - 1) {
                    char peek = text.charAt(i + 1);
                    if (peek == '$') {
                        italic = bold = strikethrough = underline = obfuscated = false;
                        word.append(ChatFormatting.RESET);
                        i++;
                    } else
                        word.append(chr);
                } else
                    word.append(chr);
            } else {
                word.append(chr);
            }
        }
        return lines;
    }

    private static StringBuilder commitComponent(MutableComponent component, boolean italic, boolean bold, boolean strikethrough, boolean underline, boolean obfuscated, StringBuilder line, UnaryOperator<Style> styleModifier) {
        component.append(Component.literal(line.toString())
                .withStyle((style) -> style.withItalic(italic).withBold(bold).withStrikethrough(strikethrough).withUnderlined(underline).withObfuscated(obfuscated))
                .withStyle(styleModifier));
        line = new StringBuilder();
        return line;
    }

    private static StringBuilder newLine(List<String> lines, boolean italic, boolean bold, boolean strikethrough, boolean underline, boolean obfuscated, StringBuilder line) {
        lines.add(line.toString());
        line = new StringBuilder();
        if (italic) line.append(ChatFormatting.ITALIC);
        if (bold) line.append(ChatFormatting.BOLD);
        if (strikethrough) line.append(ChatFormatting.STRIKETHROUGH);
        if (underline) line.append(ChatFormatting.UNDERLINE);
        if (obfuscated) line.append(ChatFormatting.OBFUSCATED);
        return line;
    }

    public enum BookTheme {
        DEFAULT, EASY_READING;
    }

    public record TextColorData(Color primaryColor, Color secondaryColor, Color glowStart, Color glowEnd) {
    }
}