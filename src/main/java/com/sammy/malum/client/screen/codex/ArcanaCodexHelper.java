package com.sammy.malum.client.screen.codex;

import com.mojang.blaze3d.systems.*;
import com.mojang.blaze3d.vertex.*;
import com.sammy.malum.client.screen.codex.screens.*;
import com.sammy.malum.common.item.spirit.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.core.systems.rite.*;
import com.sammy.malum.core.systems.ritual.*;
import com.sammy.malum.core.systems.spirit.*;
import com.sammy.malum.registry.client.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.tag.*;
import net.minecraft.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screens.*;
import net.minecraft.client.renderer.*;
import net.minecraft.network.chat.*;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.*;
import net.minecraft.util.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.neoforged.neoforge.common.crafting.*;
import org.joml.*;
import org.lwjgl.opengl.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.registry.client.*;
import team.lodestar.lodestone.systems.easing.*;
import team.lodestar.lodestone.systems.rendering.*;
import team.lodestar.lodestone.systems.rendering.shader.*;

import javax.annotation.*;
import java.awt.*;
import java.lang.Math;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.*;
import java.util.function.*;
import java.util.stream.*;

import static com.sammy.malum.config.ClientConfig.*;
import static net.minecraft.util.FastColor.ARGB32.*;

public class ArcanaCodexHelper {

    public static final VFXBuilders.ScreenVFXBuilder VFX_BUILDER = VFXBuilders.createScreen();
    public static final Function<GuiGraphics, LodestoneBufferWrapper> WRAPPER_FUNCTION = Util.memoize(guiGraphics -> new LodestoneBufferWrapper(LodestoneRenderTypes.ADDITIVE_TEXT, guiGraphics.bufferSource));
    public static final TextColorData DEFAULT_TEXT_COLOR = new TextColorData(new Color(138, 79, 58), new Color(65, 41, 8), new Color(20, 44, 60), new Color(227, 39, 228));

    public static final TextColorData GEAS_POSITIVE_COLOR = new TextColorData(new Color(18, 52, 141), new Color(118, 52, 141), new Color(20, 44, 120), new Color(100, 100, 240));
    public static final TextColorData GEAS_NEGATIVE_COLOR = new TextColorData(new Color(141, 18, 52), new Color(118, 52, 141), new Color(120, 44, 20), new Color(240, 100, 100));




    public enum BookTheme {
        DEFAULT, EASY_READING;
    }
    public static <T extends AbstractProgressionCodexScreen> void renderTransitionFade(T screen, PoseStack stack) {
        final float pct = screen.transitionTimer / (float) screen.getTransitionDuration();
        float overlayAlpha = Easing.SINE_IN_OUT.ease(pct, 0, 1, 1);
        float effectStrength = Easing.QUAD_OUT.ease(pct, 0, 1, 1);
        float effectAlpha = Math.min(1, effectStrength * 1);
        float zoom = 0.5f + Math.min(0.35f, effectStrength);
        float intensity = 1f + (effectStrength > 0.5f ? (effectStrength - 0.5f) * 2.5f : 0);

        RenderSystem.enableBlend();
        RenderSystem.enableDepthTest();
        RenderSystem.disableCull();
        VFXBuilders.ScreenVFXBuilder builder = VFXBuilders.createScreen().setPositionWithWidth(screen.getInsideLeft(), screen.getInsideTop(), screen.bookInsideWidth, screen.bookInsideHeight)
                .setColor(0, 0, 0)
                .setAlpha(overlayAlpha)
                .setZLevel(200)
                .setShader(GameRenderer::getPositionColorShader)
                .blit(stack);

        ExtendedShaderInstance shaderInstance = (ExtendedShaderInstance) ShaderRegistry.TOUCH_OF_DARKNESS.getInstance().get();
        shaderInstance.safeGetUniform("Speed").set(1000f);
        Consumer<Float> setZoom = f -> shaderInstance.safeGetUniform("Zoom").set(f);
        Consumer<Float> setIntensity = f -> shaderInstance.safeGetUniform("Intensity").set(f);
        builder.setAlpha(effectAlpha).setShader(shaderInstance);

        setZoom.accept(zoom);
        setIntensity.accept(intensity);
        builder.blit(stack);

        setZoom.accept(zoom * 1.25f + 0.15f);
        setIntensity.accept(intensity * 0.8f + 0.5f);
        builder.blit(stack);

        shaderInstance.setUniformDefaults();
        RenderSystem.disableDepthTest();
        RenderSystem.disableBlend();
    }

    public static void renderRitualIcon(MalumRitualType rite, PoseStack stack, boolean corrupted, float glowAlpha, float x, float y) {
        renderRiteIcon(rite.getIcon(), stack, rite.spirit, corrupted, glowAlpha, x, y, 0);
    }

    public static void renderRiteIcon(TotemicRiteType rite, PoseStack stack, boolean corrupted, float glowAlpha, float x, float y) {
        renderRiteIcon(rite.getIcon(), stack, rite.getIdentifyingSpirit(), corrupted, glowAlpha, x, y, 0);
    }

    public static void renderRiteIcon(ResourceLocation texture, PoseStack stack, MalumSpiritType spiritType, boolean corrupted, float glowAlpha, float x, float y) {
        renderRiteIcon(texture, stack, spiritType, corrupted, glowAlpha, x, y, 0);
    }

    public static void renderRiteIcon(ResourceLocation texture, PoseStack stack, MalumSpiritType spiritType, boolean corrupted, float glowAlpha, float x, float y, int z) {
        ExtendedShaderInstance shaderInstance = (ExtendedShaderInstance) LodestoneShaders.SCREEN_DISTORTED_TEXTURE.getInstance().get();
        shaderInstance.safeGetUniform("YFrequency").set(corrupted ? 5f : 11f);
        shaderInstance.safeGetUniform("XFrequency").set(corrupted ? 12f : 17f);
        shaderInstance.safeGetUniform("Speed").set(1500f * (corrupted ? -0.75f : 1));
        shaderInstance.safeGetUniform("Intensity").set(corrupted ? 14f : 50f);
        Supplier<ShaderInstance> shaderInstanceSupplier = () -> shaderInstance;

        VFXBuilders.ScreenVFXBuilder builder = VFXBuilders.createScreen()
                .setShader(shaderInstanceSupplier)
                .setColor(spiritType.getPrimaryColor())
                .setAlpha(0.9f)
                .setZLevel(z);

        RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        renderTexture(texture, stack, builder, x, y, 0, 0, 16, 16, 16, 16);
        builder.setAlpha(glowAlpha);
        renderTexture(texture, stack, builder, x - 1, y, 0, 0, 16, 16, 16, 16);
        renderTexture(texture, stack, builder, x + 1, y, 0, 0, 16, 16, 16, 16);
        renderTexture(texture, stack, builder, x, y - 1, 0, 0, 16, 16, 16, 16);
        if (corrupted) {
            builder.setColor(spiritType.getSecondaryColor());
        }
        renderTexture(texture, stack, builder, x, y + 1, 0, 0, 16, 16, 16, 16);
        shaderInstance.setUniformDefaults();
        RenderSystem.defaultBlendFunc();
    }

    public static void renderGeasIcon(ResourceLocation location, PoseStack stack, GeasEffectType type, float x, float y) {
        renderGeasIcon(location, stack, type, x, y, 0);
    }

    public static void renderGeasIcon(ResourceLocation location, PoseStack stack, GeasEffectType type, float x, float y, int z) {
        renderGeasIcon(location, stack, type, x, y, z, 16, 16);
    }

    public static void renderGeasIcon(ResourceLocation location, PoseStack stack, GeasEffectType type, float x, float y, int z, int textureWidth, int textureHeight) {
        ExtendedShaderInstance shaderInstance = (ExtendedShaderInstance) LodestoneShaders.SCREEN_DISTORTED_TEXTURE.getInstance().get();
        shaderInstance.safeGetUniform("YFrequency").set(10f);
        shaderInstance.safeGetUniform("XFrequency").set(12f);
        shaderInstance.safeGetUniform("Speed").set(1000f);
        shaderInstance.safeGetUniform("Intensity").set(50f);
        shaderInstance.safeGetUniform("UVCoordinates").set(new Vector4f(0f, 1f, 0f, 1f));
        Supplier<ShaderInstance> shaderInstanceSupplier = () -> shaderInstance;
        VFXBuilders.ScreenVFXBuilder builder = VFXBuilders.createScreen()
                .setShader(shaderInstanceSupplier)
                .setZLevel(z)
                .setShader(() -> shaderInstance);

        RenderSystem.depthMask(false);
        RenderSystem.defaultBlendFunc();

        var cycle = new AtomicInteger();
        var spiritTypes = type.spiritTypes;
        Supplier<MalumSpiritType> colorSupplier = () -> spiritTypes.get(cycle.getAndIncrement() % spiritTypes.size());
        var mainColor = colorSupplier.get().getPrimaryColor();
        if (spiritTypes.getFirst().equals(SpiritTypeRegistry.AQUEOUS_SPIRIT) || spiritTypes.getFirst().equals(SpiritTypeRegistry.WICKED_SPIRIT)) {
            //Aqueous has a really dark color compared to other spirits to avoid it clashing with Aerial
            //Other spirits are brighter which leads to the effect looking extremely blurry since the outline is more significant than the main layer
            mainColor = ColorHelper.brighter(mainColor, 2);
        }
        if (spiritTypes.getFirst().equals(SpiritTypeRegistry.SACRED_SPIRIT)) {
            //Aqueous has a really dark color compared to other spirits to avoid it clashing with Aerial
            //Other spirits are brighter which leads to the effect looking extremely blurry since the outline is more significant than the main layer
            mainColor = ColorHelper.brighter(mainColor, 1);
        }

        builder.setColor(colorSupplier.get().getPrimaryColor()).multiplyColor(0.24f).setAlpha(0.6f);
        shaderInstance.safeGetUniform("Speed").set(2000f);
        renderTexture(location, stack, builder, x - 1, y, 0, 0, 0, textureWidth, textureHeight);
        renderTexture(location, stack, builder, x + 1, y, 1, 0, 0, textureWidth, textureHeight);
        builder.setColor(colorSupplier.get().getPrimaryColor()).multiplyColor(0.24f);
        renderTexture(location, stack, builder, x, y - 1, 2, 0, 0, textureWidth, textureHeight);
        renderTexture(location, stack, builder, x, y + 0.8f, 3, 0, 0, textureWidth, textureHeight);

        RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        builder.setColor(mainColor).setAlpha(0.7f);
        shaderInstance.safeGetUniform("Speed").set(1000f);
        renderTexture(location, stack, builder, x, y, 4, 0, 0, textureWidth, textureHeight);

        builder.setColor(ColorHelper.brighter(mainColor, 4)).setAlpha(0.2f);
        shaderInstance.safeGetUniform("Speed").set(400f);
        renderTexture(location, stack, builder, x + 2, y + 2, 5, 2, 2, 12, 12, textureWidth, textureHeight);

        builder.setColor(colorSupplier.get().getSecondaryColor());
        shaderInstance.safeGetUniform("Speed").set(2000f);
        renderTexture(location, stack, builder, x + 1, y, 6, 0, 0, textureWidth, textureHeight);
        renderTexture(location, stack, builder, x - 1, y, 7, 0, 0, textureWidth, textureHeight);
        builder.setColor(colorSupplier.get().getSecondaryColor());
        renderTexture(location, stack, builder, x, y + 1, 8, 0, 0, textureWidth, textureHeight);
        renderTexture(location, stack, builder, x, y - 1, 9, 0, 0, textureWidth, textureHeight);

        RenderSystem.defaultBlendFunc();
        RenderSystem.depthMask(true);
        shaderInstance.setUniformDefaults();
    }


    public static void renderWavyIcon(ResourceLocation location, PoseStack stack, float x, float y) {
        renderWavyIcon(location, stack, x, y, 0);
    }

    public static void renderWavyIcon(ResourceLocation location, PoseStack stack, float x, float y, int z) {
        renderWavyIcon(location, stack, x, y, z, 16, 16);
    }

    public static void renderWavyIcon(ResourceLocation location, PoseStack stack, float x, float y, int z, int textureWidth, int textureHeight) {
        ExtendedShaderInstance shaderInstance = (ExtendedShaderInstance) LodestoneShaders.SCREEN_DISTORTED_TEXTURE.getInstance().get();
        shaderInstance.safeGetUniform("YFrequency").set(10f);
        shaderInstance.safeGetUniform("XFrequency").set(12f);
        shaderInstance.safeGetUniform("Speed").set(1000f);
        shaderInstance.safeGetUniform("Intensity").set(50f);
        shaderInstance.safeGetUniform("UVCoordinates").set(new Vector4f(0f, 1f, 0f, 1f));
        Supplier<ShaderInstance> shaderInstanceSupplier = () -> shaderInstance;

        VFXBuilders.ScreenVFXBuilder builder = VFXBuilders.createScreen()
                .setShader(shaderInstanceSupplier)
                .setAlpha(0.7f)
                .setZLevel(z)
                .setShader(() -> shaderInstance);

        RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        renderTexture(location, stack, builder, x, y, 0, 0, 0, textureWidth, textureHeight);
        builder.setAlpha(0.1f);
        shaderInstance.safeGetUniform("Speed").set(2000f);
        renderTexture(location, stack, builder, x - 1, y, 1, 0, 0, textureWidth, textureHeight);
        renderTexture(location, stack, builder, x + 1, y, 2, 0, 0, textureWidth, textureHeight);
        renderTexture(location, stack, builder, x, y - 1, 3, 0, 0, textureWidth, textureHeight);
        renderTexture(location, stack, builder, x, y + 1, 4, 0, 0, textureWidth, textureHeight);
        shaderInstance.setUniformDefaults();
        RenderSystem.defaultBlendFunc();
    }

    public static void renderTexture(ResourceLocation texture, PoseStack poseStack, float x, float y, float u, float v, int width, int height) {
        renderTexture(texture, poseStack, x, y, u, v, width, height, width, height);
    }

    public static void renderTexture(ResourceLocation texture, PoseStack poseStack, float x, float y, int z, float u, float v, int width, int height) {
        renderTexture(texture, poseStack, x, y, z, u, v, width, height, width, height);
    }

    public static void renderTexture(ResourceLocation texture, PoseStack poseStack, float x, float y, float u, float v, int width, int height, int canvasWidth, int canvasHeight) {
        renderTexture(texture, poseStack, VFX_BUILDER, x, y, 0, u, v, width, height, canvasWidth, canvasHeight);
    }

    public static void renderTexture(ResourceLocation texture, PoseStack poseStack, float x, float y, int z, float u, float v, int width, int height, int canvasWidth, int canvasHeight) {
        renderTexture(texture, poseStack, VFX_BUILDER, x, y, z, u, v, width, height, canvasWidth, canvasHeight);
    }

    public static void renderTexture(ResourceLocation texture, PoseStack poseStack, VFXBuilders.ScreenVFXBuilder builder, float x, float y, int z, float u, float v, int width, int height) {
        renderTexture(texture, poseStack, builder, x, y, z, u, v, width, height, width, height);
    }

    public static void renderTexture(ResourceLocation texture, PoseStack poseStack, VFXBuilders.ScreenVFXBuilder builder, float x, float y, float u, float v, int width, int height, int canvasWidth, int canvasHeight) {
        renderTexture(texture, poseStack, builder, x, y, 0, u, v, width, height, canvasWidth, canvasHeight);
    }

    public static void renderTexture(ResourceLocation texture, PoseStack poseStack, VFXBuilders.ScreenVFXBuilder builder, float x, float y, int z, float u, float v, int width, int height, int canvasWidth, int canvasHeight) {
        renderTexture(poseStack, builder.setShaderTexture(texture).setPositionWithWidth(x, y, width, height), z, u, v, width, height, canvasWidth, canvasHeight);
    }

    public static void renderTexture(ResourceLocation texture, PoseStack poseStack, VFXBuilders.ScreenVFXBuilder builder, float x, float y, float u, float v, int width, int height, int textureWidth, int textureHeight, int canvasWidth, int canvasHeight) {
        renderTexture(texture, poseStack, builder, x, y, 0, u, v, width, height, textureWidth, textureHeight, canvasWidth, canvasHeight);
    }

    public static void renderTexture(ResourceLocation texture, PoseStack poseStack, VFXBuilders.ScreenVFXBuilder builder, float x, float y, int z, float u, float v, int width, int height, int textureWidth, int textureHeight, int canvasWidth, int canvasHeight) {
        renderTexture(poseStack, builder.setShaderTexture(texture).setPositionWithWidth(x, y, width, height), z, u, v, textureWidth, textureHeight, canvasWidth, canvasHeight);
    }

    private static void renderTexture(PoseStack poseStack, VFXBuilders.ScreenVFXBuilder builder, int z, float u, float v, int width, int height, int canvasWidth, int canvasHeight) {
        RenderSystem.enableBlend();
        RenderSystem.enableDepthTest();
        builder.setZLevel(z)
                .setUVWithWidth(u, v, width, height, canvasWidth, canvasHeight)
                .blit(poseStack);
        RenderSystem.disableDepthTest();
        RenderSystem.disableBlend();
    }

    public static void renderIngredient(AbstractMalumScreen screen, GuiGraphics guiGraphics, ICustomIngredient ingredient, int posX, int posY, int mouseX, int mouseY) {
        renderItem(screen, guiGraphics, ingredient.getItems().toList(), posX, posY, mouseX, mouseY);
    }

    public static void renderIngredient(AbstractMalumScreen screen, GuiGraphics guiGraphics, SizedIngredient ingredient, int posX, int posY, int mouseX, int mouseY) {
        renderItem(screen, guiGraphics, List.of(ingredient.getItems()), posX, posY, mouseX, mouseY);
    }

    public static void renderIngredient(AbstractMalumScreen screen, GuiGraphics guiGraphics, Ingredient ingredient, int posX, int posY, int mouseX, int mouseY) {
        renderItem(screen, guiGraphics, List.of(ingredient.getItems()), posX, posY, mouseX, mouseY);
    }

    public static void renderItem(AbstractMalumScreen screen, GuiGraphics guiGraphics, ICustomIngredient ingredient, int posX, int posY, int mouseX, int mouseY) {
        renderItem(screen, guiGraphics, ingredient.getItems().toList(), posX, posY, mouseX, mouseY);
    }

    public static void renderItem(AbstractMalumScreen screen, GuiGraphics guiGraphics, SizedIngredient ingredient, int posX, int posY, int mouseX, int mouseY) {
        renderItem(screen, guiGraphics, List.of(ingredient.getItems()), posX, posY, mouseX, mouseY);
    }

    public static void renderItem(AbstractMalumScreen screen, GuiGraphics guiGraphics, Ingredient ingredient, int posX, int posY, int mouseX, int mouseY) {
        renderItem(screen, guiGraphics, List.of(ingredient.getItems()), posX, posY, mouseX, mouseY);
    }

    public static void renderItem(AbstractMalumScreen screen, GuiGraphics guiGraphics, List<ItemStack> stacks, int posX, int posY, int mouseX, int mouseY) {
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

    public static void renderItem(AbstractMalumScreen screen, GuiGraphics guiGraphics, ItemStack stack, int posX, int posY, int mouseX, int mouseY) {
        if (!stack.isEmpty()) {
            guiGraphics.renderItem(stack, posX, posY);
            guiGraphics.renderItemDecorations(Minecraft.getInstance().font, stack, posX, posY, null);
            if (screen.isHovering(mouseX, mouseY, posX, posY, 16, 16)) {
                screen.renderLater(() -> guiGraphics.renderComponentTooltip(Minecraft.getInstance().font, Screen.getTooltipFromItem(Minecraft.getInstance(), stack), mouseX, mouseY));
            }
        }
    }

    public static void renderIngredients(AbstractMalumScreen screen, GuiGraphics guiGraphics, List<?> ingredients, Component hoverComponent, int left, int top, int mouseX, int mouseY, boolean vertical) {
        final List<List<ItemStack>> stackBundles =
                Stream.of(
                        ingredients.stream().filter(o -> o instanceof ICustomIngredient).map(o -> ((ICustomIngredient) o).getItems().toList()),
                        ingredients.stream().filter(o -> o instanceof SizedIngredient).map(o -> Arrays.stream(((SizedIngredient) o).getItems()).toList()),
                        ingredients.stream().filter(o -> o instanceof Ingredient).map(o -> Arrays.stream(((Ingredient) o).getItems()).toList())
                ).flatMap(s -> s).toList();
        renderItemList(screen, guiGraphics, stackBundles, hoverComponent, left, top, mouseX, mouseY, vertical);
    }

    public static void renderItemList(AbstractMalumScreen screen, GuiGraphics guiGraphics, List<List<ItemStack>> items, Component hoverComponent, int left, int top, int mouseX, int mouseY, boolean isVertical) {
        int slots = items.size();
        int startingOffset = 9 * (slots - 1);
        screen.renderLater(renderItemFrames(guiGraphics, hoverComponent, slots, left, top, mouseX, mouseY, items.getFirst().getFirst().getItem() instanceof SpiritShardItem, isVertical));
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

    public static void renderItemFrames(GuiGraphics guiGraphics, int slots, int left, int top, double mouseX, double mouseY, boolean isSpirits, boolean isVertical) {
        renderItemFrames(guiGraphics, null, slots, left, top, mouseX, mouseY, isSpirits, isVertical);
    }

    public static Runnable renderItemFrames(GuiGraphics guiGraphics, @Nullable Component hoverComponent, int slots, int left, int top, double mouseX, double mouseY, boolean isSpirits, boolean isVertical) {
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
            renderTexture(EntryScreen.ITEM_SOCKET, poseStack, oLeft, oTop, u, v, width, height, 64, 64);
        }

        if (isVertical) {
            renderTexture(EntryScreen.ITEM_SOCKET, poseStack, left - 3, top - 3, 0, 0, 22, 2, 64, 64);
            renderTexture(EntryScreen.ITEM_SOCKET, poseStack, left - 3, top - 1 + 18 * (slots), 0, 20, 22, 2, 64, 64);
        } else {
            renderTexture(EntryScreen.ITEM_SOCKET, poseStack, left - 3, top - 3, 0, 0, 2, 22, 64, 64);
            renderTexture(EntryScreen.ITEM_SOCKET, poseStack, left - 1 + 18 * (slots), top - 3, 20, 0, 2, 22, 64, 64);
        }

        return () -> {
            if (hoverComponent != null) {
//                if (isHovering(mouseX, mouseY, crownLeft + 3, plaqueTop + 2, 10, 11)) {
//                    guiGraphics.renderComponentTooltip(Minecraft.getInstance().font, wrapComponent(hoverComponent, 180), (int) mouseX, (int) mouseY);
//                }
            }
        };
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
        final List<String> lines = wrapText(text, (int) (width / scaleMultiplier));
        for (int i = 0; i < lines.size(); i++) {
            String currentLine = lines.get(i);
            float textX = x;
            float textY = y;
            if (scaleMultiplier != 1) {
                textX /= scaleMultiplier;
                textY /= scaleMultiplier;
            }
            renderRawText(guiGraphics, colorData, currentLine, textX, textY + i * (font.lineHeight + 1), 0.2f, scaleMultiplier);
        }
    }

    public static List<Component> wrapComponent(String text, int width) {
        return wrapText(text, width).stream().map(Component::literal).map(Component.class::cast).toList();
    }

    public static List<Component> wrapComponent(Component component, int width) {
        return wrapText(component, width).stream().map(Component::literal).map(Component.class::cast).toList();
    }

    public static List<String> wrapText(String text, int width) {
        return wrapText(Component.translatable(text), width);
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

    public static void renderHeadline(GuiGraphics graphics, Component component, int left, int top) {
        final int width = Minecraft.getInstance().font.width(component.getString());
        float scale = 1f;
        if (width > 100) {
            scale -= (width-100) / 200f;
        }
        float textLeft = left + 72;
        float textTop = top + 5;

        if (scale != 1) {
            textLeft /= scale;
            textTop /= scale;
            textTop += 5 * (1 - scale);
        }
        renderText(graphics, component, textLeft- width / 2f, textTop, scale);
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
        double horizontalDelta = Math.clamp(1 - Mth.abs(differenceX) * inverseScale, 0, 1);
        double verticalDelta = Math.clamp(1 - Mth.abs(differenceY) * inverseScale, 0, 1);
        if (differenceY > 0) {
            verticalDelta = Math.pow(verticalDelta * (1 - differenceY), 3);
        }
        double delta = Easing.QUINTIC_OUT.ease(horizontalDelta, 0, 1) * Easing.QUINTIC_OUT.ease(verticalDelta, 0, 1);
        if (EntryScreen.textJump > 0) {
            double jumpDelta = delta * Easing.SINE_IN_OUT.ease(EntryScreen.textJump, 0, 1);
            glowMultiplier *= (float) (1 + jumpDelta);
        }

        if (BOOK_THEME.getConfigValue().equals(BookTheme.EASY_READING)) {
            guiGraphics.drawString(font, text, x, y, 0, false);
            return;
        }

        Color gray = colorData.primaryColor();
        Color dark = colorData.secondaryColor();

        guiGraphics.drawString(font, text, x - 1f, y, color(64, gray.getRGB()), false);
        guiGraphics.drawString(font, text, x + 1f, y, color(32, gray.getRGB()), false);
        guiGraphics.drawString(font, text, x, y - 1f, color(32, gray.getRGB()), false);
        guiGraphics.drawString(font, text, x, y + 1f, color(92, gray.getRGB()), false);

        guiGraphics.drawString(font, text, x, y, color(255, dark.getRGB()), false);

        int alpha = Mth.floor(255 * Easing.QUARTIC_IN.ease(delta, 0.4f, 1, 1) * glowMultiplier);
        if (alpha > 15) {
            float color = Easing.CUBIC_IN.ease(delta, 0, 1, 1);
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
        if (scaleMultiplier != 1) {
            poseStack.popPose();
        }
    }

    public static boolean isHovering(double mouseX, double mouseY, float posX, float posY, int width, int height) {
        return mouseX > posX && mouseX < posX + width && mouseY > posY && mouseY < posY + height;
    }

    public record TextColorData(Color primaryColor, Color secondaryColor, Color glowStart, Color glowEnd) {
    }
}