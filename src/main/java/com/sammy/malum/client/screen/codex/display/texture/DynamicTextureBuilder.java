package com.sammy.malum.client.screen.codex.display.texture;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.sammy.malum.*;
import com.sammy.malum.registry.client.MalumShaders;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import org.joml.Matrix4f;
import team.lodestar.lodestone.systems.rendering.builder.VFXBuilders;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.*;

/**
 * <a href="https://github.com/MehVahdJukaar/Moonlight/blob/1.21/common/src/main/java/net/mehvahdjukaar/moonlight/api/client/texture_renderer/RenderableDynamicTexture.java">Brought over from Moonlight Lib implementation</a>
 */
@SuppressWarnings({"unused"})
public class DynamicTextureBuilder {

    private static final LoadingCache<ResourceLocation, CompletableFuture<RenderableDynamicTexture>> TEXTURE_CACHE =
            CacheBuilder.newBuilder()
                    .<ResourceLocation, CompletableFuture<RenderableDynamicTexture>>removalListener(i -> {
                        var future = i.getValue();
                        if (future == null) return;
                        future.thenAccept(texture -> RenderSystem.recordRenderCall(texture::unregister));
                    }).expireAfterAccess(2, TimeUnit.MINUTES)
                    .build(new CacheLoader<>() {
                        @Override
                        public CompletableFuture<RenderableDynamicTexture> load(ResourceLocation key) {
                            return new CompletableFuture<>();
                        }
                    });

    protected final ResourceLocation texturePath;
    protected int width, height;
    protected float hScale = 1, vScale = 1;

    protected boolean isTicking = false;

    public static DynamicTextureBuilder create(ResourceLocation texturePath) {
        return new DynamicTextureBuilder(texturePath);
    }

    public static DynamicTextureBuilder create(ItemLike itemLike) {
        var holder = itemLike.asItem().builtInRegistryHolder();
        var path = holder.key().location();
        return new DynamicTextureBuilder(path.withPrefix("generated/").withSuffix(".png"));
    }

    public DynamicTextureBuilder(ResourceLocation texturePath) {
        this.texturePath = texturePath;
    }

    public DynamicTextureBuilder setTextureSize(int size) {
        return setTextureSize(size, size);
    }

    public DynamicTextureBuilder setTextureSize(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public DynamicTextureBuilder setScale(float scale) {
        this.hScale = scale;
        this.vScale = scale;
        return this;
    }

    public DynamicTextureBuilder setTicking(boolean isTicking) {
        this.isTicking = isTicking;
        return this;
    }

    public static void clearCache() {
        TEXTURE_CACHE.invalidateAll();
    }

    public RenderableDynamicTexture bakeItemTexture(ItemLike item) {
        return bakeItemTexture(item.asItem().getDefaultInstance());
    }

    public RenderableDynamicTexture bakeItemTexture(ItemStack stack) {
        if (stack.getItem() instanceof BlockItem) {
            setTextureSize(64);
            setScale(4);
        }
        return bakeTexture(t -> drawItem(t, stack));
    }

    public RenderableDynamicTexture bakeOutlineTexture(ResourceLocation texture, int sourceWidth, int sourceHeight, int outlineWidth) {
        return bakeTexture(t -> drawOutline(t, texture, sourceWidth, sourceHeight, outlineWidth));
    }

    public RenderableDynamicTexture bakeTexture(ResourceLocation texture) {
        return bakeTexture(t -> drawTexture(t, texture));
    }

    public RenderableDynamicTexture bakeTexture(Consumer<RenderableDynamicTexture> textureDrawingFunction) {
        var future = TEXTURE_CACHE.asMap().computeIfAbsent(texturePath, key -> {
                    var nested = new CompletableFuture<RenderableDynamicTexture>();
                    RenderSystem.recordRenderCall(() -> {
                        try {
                            var texture = new RenderableDynamicTexture(key, width, height, textureDrawingFunction);
                            texture.register();
                            texture.redraw();
                            nested.complete(texture);
                        } catch (Throwable t) {
                            MalumMod.LOGGER.error("Failed to create dynamic texture for id {}", key, t);
                            nested.completeExceptionally(t);
                            TEXTURE_CACHE.invalidate(key);
                        }
                    });
                    return nested;
                }
        );
        if (!future.isDone()) {
            return null;
        }
        var renderedTexture = future.join();

        if (renderedTexture.isClosed()) {
            TEXTURE_CACHE.invalidate(texturePath);
            return null;
        }
        if (isTicking) {
            renderedTexture.setUpdateNextTick(true);
        }

        return renderedTexture;
    }

    protected void drawItem(RenderableDynamicTexture tex, ItemStack stack) {
        writeTexture(tex, g -> g.renderFakeItem(stack, 0, 0));
    }

    protected void drawTexture(RenderableDynamicTexture tex, ResourceLocation texture) {
        drawTexture(tex, GameRenderer::getPositionTexColorShader, texture);
    }

    protected void drawOutline(RenderableDynamicTexture tex, ResourceLocation texture, int sourceWidth, int sourceHeight, int outlineWidth) {
        var outline = MalumShaders.OUTLINED_HUD_ELEMENT.getShaderInstance();
        outline.safeGetUniform("OutlineWidth").set(outlineWidth);
        outline.safeGetUniform("SourceTextureSize").set((float)sourceWidth, (float)sourceHeight);
        outline.safeGetUniform("OutputTextureSize").set((float)width, (float)height);
        drawTexture(tex, MalumShaders.OUTLINED_HUD_ELEMENT::getShaderInstance, texture);
        outline.setUniformDefaults();
    }

    protected void drawTexture(RenderableDynamicTexture tex, Supplier<ShaderInstance> shader, ResourceLocation texture) {
        writeTexture(tex, guiGraphics -> {
            var stack = guiGraphics.pose();
            RenderSystem.enableDepthTest();
            RenderSystem.depthMask(false);
            RenderSystem.enableBlend();
            VFXBuilders.createScreen()
                    .setPositionWithWidth(0, 0, width, height)
                    .setUV(0, 1, 1, 0)
                    .setFormat(DefaultVertexFormat.POSITION_TEX)
                    .setShader(shader)
                    .setTexture(texture)
                    .blit(stack);
            RenderSystem.disableDepthTest();
            RenderSystem.depthMask(true);
            RenderSystem.disableBlend();
        });
    }

    public void writeTexture(RenderableDynamicTexture tex, Consumer<GuiGraphics> drawFunction) {
        float fogStart = RenderSystem.getShaderFogStart();
        float fogEnd = RenderSystem.getShaderFogEnd();
        RenderSystem.setShaderFogStart(Integer.MAX_VALUE);
        RenderSystem.setShaderFogEnd(Integer.MAX_VALUE);

        RenderSystem.clear(256, Minecraft.ON_OSX);

        Minecraft mc = Minecraft.getInstance();
        RenderTarget frameBuffer = tex.getRenderTarget();
        frameBuffer.clear(Minecraft.ON_OSX);

        frameBuffer.bindWrite(true);

        RenderSystem.backupProjectionMatrix();
        Matrix4f matrix4f = new Matrix4f().setOrtho(0.0F, width/hScale, height/vScale, 0, -1000.0F, 1000);
        RenderSystem.setProjectionMatrix(matrix4f, VertexSorting.ORTHOGRAPHIC_Z);

        var stack = RenderSystem.getModelViewStack();
        stack.pushMatrix();
        stack.set(new Matrix4f().identity());

        RenderSystem.applyModelViewMatrix();
        Lighting.setupFor3DItems();

        GuiGraphics guiGraphics = new GuiGraphics(mc, mc.renderBuffers().bufferSource());
        drawFunction.accept(guiGraphics);
        guiGraphics.flush();

        stack.popMatrix();
        RenderSystem.applyModelViewMatrix();
        RenderSystem.restoreProjectionMatrix();
        mc.getMainRenderTarget().bindWrite(true);

        RenderSystem.setShaderFogStart(fogStart);
        RenderSystem.setShaderFogEnd(fogEnd);
    }
}