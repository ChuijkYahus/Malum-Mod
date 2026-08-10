package com.sammy.malum.client.screen.codex.helper;

import com.mojang.blaze3d.systems.*;
import com.sammy.malum.client.screen.codex.display.*;
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
import team.lodestar.lodestone.systems.rendering.wrapper.LodestoneBufferWrapper;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.*;
import java.util.function.*;

import static com.sammy.malum.client.screen.codex.display.CodexTextRenderer.DEFAULT_TEXT_COLOR;
import static net.minecraft.util.FastColor.ARGB32.color;

public class CodexTextHelper {
    public static final Function<GuiGraphics, LodestoneBufferWrapper> WRAPPER_FUNCTION = Util.memoize(guiGraphics -> new LodestoneBufferWrapper(LodestoneRenderTypes.ADDITIVE_TEXT, guiGraphics.bufferSource));


    @Deprecated
    public static void renderText(GuiGraphics guiGraphics, Component component, float x, float y) {
        renderText(guiGraphics, DEFAULT_TEXT_COLOR, component, x, y, 0.4f);
    }

    @Deprecated
    public static void renderText(GuiGraphics guiGraphics, Component component, float x, float y, float scale) {
        renderText(guiGraphics, DEFAULT_TEXT_COLOR, component, x, y, 0.4f, scale);
    }

    @Deprecated
    public static void renderText(GuiGraphics guiGraphics, CodexTextRenderer.TextColorData colorData, Component component, float x, float y, float glow) {
        renderText(guiGraphics, colorData, component, x, y, glow, 1f);
    }

    @Deprecated
    public static void renderText(GuiGraphics guiGraphics, CodexTextRenderer.TextColorData colorData, Component component, float x, float y, float glow, float scale) {
        renderRawText(guiGraphics, colorData, component.getVisualOrderText(), x, y, glow, scale);
    }

    @Deprecated
    private static void renderRawText(GuiGraphics guiGraphics, CodexTextRenderer.TextColorData colorData, FormattedCharSequence text, float x, float y, float glowMultiplier, float scaleMultiplier) {
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
                    buffer, Font.DisplayMode.NORMAL, 0, 15728880);

            font.drawInBatch(text, x + 1f, y, color(alpha / 2, r, g, b), false, pose,
                    buffer, Font.DisplayMode.NORMAL, 0, 15728880);
            font.drawInBatch(text, x - 1f, y, color(alpha / 3, r, g, b), false, pose,
                    buffer, Font.DisplayMode.NORMAL, 0, 15728880);
            font.drawInBatch(text, x, y + 1f, color(alpha / 2, r, g, b), false, pose,
                    buffer, Font.DisplayMode.NORMAL, 0, 15728880);
            font.drawInBatch(text, x, y - 1f, color(alpha / 3, r, g, b), false, pose,
                    buffer, Font.DisplayMode.NORMAL, 0, 15728880);


            RenderSystem.defaultBlendFunc();
        }
        if (scaleMultiplier != 1) {
            poseStack.popPose();
        }
    }

    public static List<Component> wrapComponent(Component source, MutableComponent header, int width) {
        var wrapped = wrapComponent(source, width);
        var result = new ArrayList<Component>();
        for (MutableComponent mutableComponent : wrapped) {
            result.add(header.copy().append(mutableComponent.setStyle(source.getStyle())));
        }
        return result;
    }

    public static List<MutableComponent> wrapComponent(Component source, int width) {
        return wrapText(source, width).stream().map(CodexTextHelper::convertToComponent).peek(component -> component.setStyle(source.getStyle())).map(MutableComponent.class::cast).toList();
    }

    public static List<String> wrapText(Component component, int width) {
        var font = Minecraft.getInstance().font;
        var text = component.getString() + "\n";
        var lines = new ArrayList<String>();

        var italic = new AtomicBoolean();
        var bold = new AtomicBoolean();
        var strikethrough = new AtomicBoolean();
        var underline = new AtomicBoolean();
        var obfuscated = new AtomicBoolean();

        var line = new StringBuilder();
        var word = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char chr = text.charAt(i);
            if (chr == ' ' || chr == '\n') {
                if (!line.isEmpty()) {
                    int currentWidth = font.width(line.toString());
                    int addedWidth = font.width(word.toString());
                    if (currentWidth + addedWidth > width) {
                        lines.add(line.toString());
                        line = new StringBuilder();
                        applyModifiers(line, italic, bold, strikethrough, underline, obfuscated);
                    }
                }

                line.append(word).append(" ");
                word = new StringBuilder();
                applyModifiers(word, italic, bold, strikethrough, underline, obfuscated);
            }
            else if (chr == '$') {
                if (i != text.length() - 1) {
                    char peek = text.charAt(i + 1);
                    var optional = findModifier(peek, italic, bold, strikethrough, underline, obfuscated);
                    if (optional.isPresent()) {
                        word.append(optional.get());
                        i++;
                        continue;
                    }
                }
            }
            else if (chr == '/') {
                if (i != text.length() - 1) {
                    char peek = text.charAt(i + 1);
                    if (peek == '$') {
                        word.append(ChatFormatting.RESET);
                        italic.set(false);
                        bold.set(false);
                        strikethrough.set(false);
                        underline.set(false);
                        obfuscated.set(false);
                        i++;
                        continue;
                    }
                }
            }

            if (chr == ' ' || chr == '\n') {
                continue;
            }
            word.append(chr);
        }
        if (!line.isEmpty()) {
            lines.add(line.toString());
        }
        return lines;
    }

    public static MutableComponent convertToComponent(String text) {
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
                            line = commitComponent(raw, italic, bold, strikethrough, underline, obfuscated, line);
                            italic = true;
                            i++;
                        }
                        case 'b' -> {
                            line = commitComponent(raw, italic, bold, strikethrough, underline, obfuscated, line);
                            bold = true;
                            i++;
                        }
                        case 's' -> {
                            line = commitComponent(raw, italic, bold, strikethrough, underline, obfuscated, line);
                            strikethrough = true;
                            i++;
                        }
                        case 'u' -> {
                            line = commitComponent(raw, italic, bold, strikethrough, underline, obfuscated, line);
                            underline = true;
                            i++;
                        }
                        case 'k' -> {
                            line = commitComponent(raw, italic, bold, strikethrough, underline, obfuscated, line);
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
                        line = commitComponent(raw, italic, bold, strikethrough, underline, obfuscated, line);
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
        commitComponent(raw, italic, bold, strikethrough, underline, obfuscated, line);

        return raw;
    }

    private static void applyModifiers(StringBuilder word, AtomicBoolean italic, AtomicBoolean bold, AtomicBoolean strikethrough, AtomicBoolean underline, AtomicBoolean obfuscated) {
        if (italic.get()) word.append(ChatFormatting.ITALIC);
        if (bold.get()) word.append(ChatFormatting.BOLD);
        if (strikethrough.get()) word.append(ChatFormatting.STRIKETHROUGH);
        if (underline.get()) word.append(ChatFormatting.UNDERLINE);
        if (obfuscated.get()) word.append(ChatFormatting.OBFUSCATED);
    }

    private static Optional<ChatFormatting> findModifier(char modifierChar, AtomicBoolean italic, AtomicBoolean bold, AtomicBoolean strikethrough, AtomicBoolean underline, AtomicBoolean obfuscated) {
        switch (modifierChar) {
            case 'i' -> {
                italic.set(true);
                return Optional.of(ChatFormatting.ITALIC);
            }
            case 'b' -> {
                bold.set(true);
                return Optional.of(ChatFormatting.BOLD);
            }
            case 's' -> {
                strikethrough.set(true);
                return Optional.of(ChatFormatting.STRIKETHROUGH);
            }
            case 'u' -> {
                underline.set(true);
                return Optional.of(ChatFormatting.UNDERLINE);
            }
            case 'k' -> {
                obfuscated.set(true);
                return Optional.of(ChatFormatting.OBFUSCATED);
            }
        }
        return Optional.empty();
    }

    private static StringBuilder commitComponent(MutableComponent component, boolean italic, boolean bold, boolean strikethrough, boolean underline, boolean obfuscated, StringBuilder line) {
        component.append(Component.literal(line.toString()).withStyle((style) -> style.withItalic(italic).withBold(bold).withStrikethrough(strikethrough).withUnderlined(underline).withObfuscated(obfuscated)));
        line = new StringBuilder();
        return line;
    }

    public enum BookTheme {
        DEFAULT, EASY_READING;
    }

}