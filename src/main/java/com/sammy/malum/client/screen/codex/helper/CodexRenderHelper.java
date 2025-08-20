package com.sammy.malum.client.screen.codex.helper;

import com.mojang.blaze3d.systems.*;
import com.mojang.blaze3d.vertex.*;
import com.sammy.malum.client.screen.codex.screens.progression.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.core.systems.rite.*;
import com.sammy.malum.core.systems.spirit.type.*;
import com.sammy.malum.registry.client.*;
import com.sammy.malum.registry.common.magic.*;
import net.minecraft.client.renderer.*;
import net.minecraft.resources.*;
import org.joml.*;
import org.lwjgl.opengl.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.registry.client.*;
import team.lodestar.lodestone.systems.easing.*;
import team.lodestar.lodestone.systems.rendering.*;
import team.lodestar.lodestone.systems.rendering.shader.*;

import java.awt.*;
import java.lang.Math;
import java.util.concurrent.atomic.*;
import java.util.function.*;

public class CodexRenderHelper {

    protected static final VFXBuilders.ScreenVFXBuilder VFX_BUILDER = VFXBuilders.createScreen();

    public static <T extends AbstractProgressionCodexScreen> void renderTransitionFade(T screen, PoseStack stack) {
        float pct = screen.getVoidFadeoutDelta();
        float overlayAlpha = Easing.SINE_IN_OUT.ease(pct, 0, 1, 1);
        float effectStrength = Easing.QUAD_OUT.ease(pct, 0, 1, 1);
        float effectAlpha = Math.min(1, effectStrength * 1);
        float zoom = 0.5f + Math.min(0.35f, effectStrength);
        float intensity = 1f + (effectStrength > 0.5f ? (effectStrength - 0.5f) * 2.5f : 0);

        RenderSystem.enableBlend();
        RenderSystem.enableDepthTest();
        RenderSystem.disableCull();
        int left = screen.getInsideLeft();
        int top = screen.getInsideTop();
        int width = AbstractProgressionCodexScreen.BOOK_INSIDE_WIDTH;
        int height = AbstractProgressionCodexScreen.BOOK_INSIDE_HEIGHT;
        VFXBuilders.ScreenVFXBuilder builder = VFXBuilders.createScreen().setPositionWithWidth(left, top, width, height)
                .setColor(0, 0, 0)
                .setAlpha(overlayAlpha)
                .setZLevel(200)
                .setShader(GameRenderer::getPositionColorShader)
                .blit(stack);

        ExtendedShaderInstance shaderInstance = MalumShaders.TOUCH_OF_DARKNESS.getShaderInstance();
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

    public static void renderRiteIcon(SpiritRiteType rite, PoseStack stack, float x, float y) {
        renderRiteIcon(rite.getIcon(), stack, rite.getIdentifyingSpirit(), rite.isCorrupted(), x, y, 0);
    }

    public static void renderRiteIcon(ResourceLocation texture, PoseStack stack, SpiritLike spiritType, boolean corrupted, float x, float y) {
        renderRiteIcon(texture, stack, spiritType, corrupted, x, y, 0);
    }

    public static void renderRiteIcon(ResourceLocation texture, PoseStack stack, SpiritLike spiritType, boolean corrupted, float x, float y, int z) {
        ExtendedShaderInstance shaderInstance = LodestoneShaders.SCREEN_DISTORTED_TEXTURE.getShaderInstance();
        float intensity = corrupted ? 30f : 50f;
        shaderInstance.safeGetUniform("YFrequency").set(corrupted ? 5f : 10f);
        shaderInstance.safeGetUniform("XFrequency").set(corrupted ? 9f : 18f);
        shaderInstance.safeGetUniform("Speed").set(corrupted ? -1000f : 1500f);
        shaderInstance.safeGetUniform("Intensity").set(intensity);
        shaderInstance.safeGetUniform("UVCoordinates").set(new Vector4f(0f, 1f, 0f, 1f));
        VFXBuilders.ScreenVFXBuilder builder = VFXBuilders.createScreen()
                .setShader(shaderInstance)
                .setZLevel(z);

        RenderSystem.depthMask(false);
        RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);

        var color = spiritType.getPrimaryColor();
        var secondaryColor = spiritType.getPrimaryColor();

        builder.setColor(color).setAlpha(0.8f);
        renderTexture(texture, stack, builder, x, y, 0, 0, 0, 16, 16);

        builder.setColor(ColorHelper.brighter(color, 4)).setAlpha(0.1f);
        renderTexture(texture, stack, builder, x + 2, y + 2, 1, 2, 2, 12, 12, 16, 16);

        builder.setAlpha(0.2f);
        renderTexture(texture, stack, builder, x + 1, y, 2, 0, 0, 16, 16);
        renderTexture(texture, stack, builder, x - 1, y, 3, 0, 0, 16, 16);
        builder.setColor(secondaryColor).setAlpha(0.3f);
        shaderInstance.safeGetUniform("Intensity").set(-intensity);
        renderTexture(texture, stack, builder, x, y + 1, 4, 0, 0, 16, 16);
        renderTexture(texture, stack, builder, x, y - 1, 5, 0, 0, 16, 16);

        RenderSystem.defaultBlendFunc();
        RenderSystem.depthMask(true);
        shaderInstance.setUniformDefaults();
    }

    public static void renderGeasIcon(ResourceLocation location, PoseStack stack, GeasEffectType type, float x, float y) {
        renderGeasIcon(location, stack, type, x, y, 0);
    }

    public static void renderGeasIcon(ResourceLocation texture, PoseStack stack, GeasEffectType type, float x, float y, int z) {
        ExtendedShaderInstance shaderInstance = LodestoneShaders.SCREEN_DISTORTED_TEXTURE.getShaderInstance();
        shaderInstance.safeGetUniform("YFrequency").set(10f);
        shaderInstance.safeGetUniform("XFrequency").set(12f);
        shaderInstance.safeGetUniform("Speed").set(2000f);
        shaderInstance.safeGetUniform("Intensity").set(50f);
        shaderInstance.safeGetUniform("UVCoordinates").set(new Vector4f(0f, 1f, 0f, 1f));
        VFXBuilders.ScreenVFXBuilder builder = VFXBuilders.createScreen()
                .setShader(shaderInstance)
                .setZLevel(z);

        RenderSystem.depthMask(false);
        RenderSystem.defaultBlendFunc();

        var cycle = new AtomicInteger();
        var spirits = type.spiritTypes;
        Function<Function<SpiritLike, Color>, Color> colorSupplier = (b) -> b.apply(spirits.get(cycle.getAndIncrement() % spirits.size())); //TODO: this kinda smells...
        var mainColor = colorSupplier.apply(SpiritLike::getPrimaryColor);
        if (spirits.getFirst().equals(MalumSpiritTypes.AQUEOUS_SPIRIT)) {
            mainColor = ColorHelper.brighter(mainColor, 2);
        }
        if (spirits.getFirst().equals(MalumSpiritTypes.SACRED_SPIRIT)  || spirits.getFirst().equals(MalumSpiritTypes.WICKED_SPIRIT)) {
            mainColor = ColorHelper.brighter(mainColor, 1);
        }

        builder.setColor(colorSupplier.apply(SpiritLike::getPrimaryColor)).multiplyColor(0.24f).setAlpha(0.6f);
        renderTexture(texture, stack, builder, x - 1, y, 0, 0, 0, 16, 16);
        renderTexture(texture, stack, builder, x + 1, y, 1, 0, 0, 16, 16);
        builder.setColor(colorSupplier.apply(SpiritLike::getPrimaryColor)).multiplyColor(0.24f);
        renderTexture(texture, stack, builder, x, y - 1, 2, 0, 0, 16, 16);
        renderTexture(texture, stack, builder, x, y + 0.8f, 3, 0, 0, 16, 16);

        RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        builder.setColor(mainColor).setAlpha(0.7f);
        shaderInstance.safeGetUniform("Speed").set(1000f);
        renderTexture(texture, stack, builder, x, y, 4, 0, 0, 16, 16);

        builder.setColor(ColorHelper.brighter(mainColor, 4)).setAlpha(0.2f);
        shaderInstance.safeGetUniform("Speed").set(400f);
        renderTexture(texture, stack, builder, x + 2, y + 2, 5, 2, 2, 12, 12, 16, 16);

        builder.setColor(colorSupplier.apply(SpiritLike::getSecondaryColor));
        shaderInstance.safeGetUniform("Speed").set(2000f);
        renderTexture(texture, stack, builder, x + 1, y, 6, 0, 0, 16, 16);
        renderTexture(texture, stack, builder, x - 1, y, 7, 0, 0, 16, 16);
        builder.setColor(colorSupplier.apply(SpiritLike::getSecondaryColor));
        renderTexture(texture, stack, builder, x, y + 1, 8, 0, 0, 16, 16);
        renderTexture(texture, stack, builder, x, y - 1, 9, 0, 0, 16, 16);

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
        ExtendedShaderInstance shaderInstance = LodestoneShaders.SCREEN_DISTORTED_TEXTURE.getShaderInstance();
        shaderInstance.safeGetUniform("YFrequency").set(10f);
        shaderInstance.safeGetUniform("XFrequency").set(12f);
        shaderInstance.safeGetUniform("Speed").set(1000f);
        shaderInstance.safeGetUniform("Intensity").set(50f);
        shaderInstance.safeGetUniform("UVCoordinates").set(new Vector4f(0f, 1f, 0f, 1f));

        VFXBuilders.ScreenVFXBuilder builder = VFXBuilders.createScreen()
                .setShader(shaderInstance)
                .setAlpha(0.7f)
                .setZLevel(z);

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

}