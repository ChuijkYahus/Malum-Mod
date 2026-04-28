package com.sammy.malum.client.screen.codex.display.texture;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.platform.NativeImage;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.sammy.malum.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import team.lodestar.lodestone.systems.rendering.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.*;

/**
 * <a href="https://github.com/MehVahdJukaar/Moonlight/blob/1.21/common/src/main/java/net/mehvahdjukaar/moonlight/api/client/texture_renderer/RenderableDynamicTexture.java">Brought over from Moonlight Lib implementation</a>
 */
@SuppressWarnings({"unchecked", "unused"})
public class DynamicTextureRenderer {

    private static final LoadingCache<ResourceLocation, CompletableFuture<RenderableDynamicTexture>> TEXTURE_CACHE =
            CacheBuilder.newBuilder()
                    .<ResourceLocation, CompletableFuture<RenderableDynamicTexture>>removalListener(i -> {
                        CompletableFuture<RenderableDynamicTexture> future = i.getValue();
                        if (future == null) return;
                        //this unregisters calls close which makes textureMatrix as closed
                        future.thenAccept(texture -> RenderSystem.recordRenderCall(texture::unregister));
                    }).expireAfterAccess(2, TimeUnit.MINUTES)
                    .build(new CacheLoader<>() {
                        @Override
                        public CompletableFuture<RenderableDynamicTexture> load(ResourceLocation key) {
                            return new CompletableFuture<>();
                        }
                    });

    protected final ResourceLocation texturePath;
    protected int size;

    public DynamicTextureRenderer(ResourceLocation texturePath) {
        this.texturePath = texturePath;
    }

    public DynamicTextureRenderer setTextureSize(int size) {
        this.size = size;
        return this;
    }

    //clears the texture cache and forge all to be re-rendered
    public static void clearCache() {
        TEXTURE_CACHE.invalidateAll();
    }

    @Nullable
    public <T extends RenderableDynamicTexture> T getTextureIfPresent(ResourceLocation id) {
        var ifPresent = TEXTURE_CACHE.getIfPresent(id);
        return ifPresent == null || !ifPresent.isDone() ? null : (T) ifPresent.join();
    }

    public RenderableDynamicTexture requestFlatItemStackTexture(ItemStack stack) {
        return requestTexture(t -> drawItem(t, stack), true);
    }

    public RenderableDynamicTexture requestFlatItemTexture(Item item) {
        return requestFlatItemTexture(item, null);
    }

    public RenderableDynamicTexture requestFlatItemTexture(Item item, @Nullable Consumer<NativeImage> postProcessing) {
        return requestFlatItemTexture(item, postProcessing, false);
    }

    /**
     * Draws a flax GUI-like item onto this texture with the given size
     *
     * @param item           item you want to draw
     * @param postProcessing some extra drawing functions to be applied on the native image. Can be slow as its cpu sided
     */
    public RenderableDynamicTexture requestFlatItemTexture(Item item,
                                                           @Nullable Consumer<NativeImage> postProcessing, boolean updateEachFrame) {
        return requestTexture(t -> {
            drawItem(t, item.getDefaultInstance());
            if (postProcessing != null) {
                t.download();
                NativeImage img = t.getPixels();
                postProcessing.accept(img);
                t.upload();
            }
        }, updateEachFrame);
    }

    /**
     * Gets a texture object on which you'll be able to directly draw onto as its in essence a frame buffer
     * Remember to call isInitialized() as the returned texture might be empty
     * For practical purposes you are only interested to call something like buffer.getBuffer(RenderType.entityCutout(texture.getTextureLocation()));
     *
     * @param textureDrawingFunction this is the function responsible to draw things onto this texture
     * @return texture instance
     */
    public RenderableDynamicTexture requestTexture(Consumer<RenderableDynamicTexture> textureDrawingFunction, boolean updateEachFrame) {
        var t = requestTexture((rl) -> new RenderableDynamicTexture(rl, size, textureDrawingFunction));
        if (t != null && updateEachFrame) {
            t.setUpdateNextTick(true);
        }
        return t;
    }

    /**
     * Gets a texture object on which you'll be able to directly draw onto as its in essence a frame buffer
     * Remember to call isInitialized() as the returned texture might be empty
     * For practical purposes you are only interested to call something like buffer.getBuffer(RenderType.entityCutout(texture.getTextureLocation()));
     * **/
    public <T extends RenderableDynamicTexture> T requestTexture(
            Function<ResourceLocation, T> textureSupplier
    ) {
        var future =
                TEXTURE_CACHE.asMap().computeIfAbsent(texturePath, key -> {
                    var nested = new CompletableFuture<RenderableDynamicTexture>();

                    RenderSystem.recordRenderCall(() -> {
                        try {
                            T texture = textureSupplier.apply(key);
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
                });

        if (!future.isDone()) {
            return null;
        }

        var t = (T) future.join();
        if (t.isClosed()) {
            TEXTURE_CACHE.invalidate(texturePath);
            MalumMod.LOGGER.error("get texture on closed");
            return null;
        }
        return t;
    }

    protected void drawItem(RenderableDynamicTexture tex, ItemStack stack) {
        drawAsInGUI(tex, g -> g.renderFakeItem(stack, 0, 0));
    }

    protected void drawTexture(RenderableDynamicTexture tex, ResourceLocation texture) {
        drawAsInGUI(tex, s -> {
            var pose = s.pose().last();
            RenderSystem.setShaderTexture(0, texture);
            RenderSystem.disableDepthTest();
            RenderSystem.depthMask(false);
            RenderSystem.disableBlend();
            RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1);
            var tesselator = Tesselator.getInstance();
            var builder = tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
            builder.addVertex(pose, 0, size, 0).setUv(0, 0);
            builder.addVertex(pose, size, size, 0).setUv(1, 0);
            builder.addVertex(pose, size, 0, 0).setUv(1, 1);
            builder.addVertex(pose, 0, 0, 0).setUv(0, 1);

            BufferUploader.drawWithShader(builder.buildOrThrow());
        });
    }

    /**
     * Coordinates here are from 0 to 1
     */
    protected void drawNormalized(RenderableDynamicTexture tex, Consumer<PoseStack> drawFunction) {
        drawAsInGUI(tex, g -> {
            var s = g.pose();
            float scale = 1f / 16f;
            s.translate(8, 8, 0);
            s.scale(scale, scale, 1);
            drawFunction.accept(s);
        });
    }

    /**
     * Utility method that sets up an environment akin to gui rendering with a box from 0 t0 16.
     * If you render an item at 0,0 it will be centered
     */
    public void drawAsInGUI(RenderableDynamicTexture tex, Consumer<GuiGraphics> drawFunction) {
        //fog bs that idk why its needed with flywheel. MC gui code doesnt need that
        float fogStart = RenderSystem.getShaderFogStart();
        float fogEnd = RenderSystem.getShaderFogEnd();
        RenderSystem.setShaderFogStart(Integer.MAX_VALUE);
        RenderSystem.setShaderFogEnd(Integer.MAX_VALUE);

        RenderSystem.clear(256, Minecraft.ON_OSX);

        Minecraft mc = Minecraft.getInstance();
        RenderTarget frameBuffer = tex.getRenderTarget();
        frameBuffer.clear(Minecraft.ON_OSX);

        //render to this one
        frameBuffer.bindWrite(true);

        //save old projection and sets new orthographic
        RenderSystem.backupProjectionMatrix();
        //like this so object center is exactly at 0 0 0
        Matrix4f matrix4f = new Matrix4f().setOrtho(0.0F, size, size, 0, -1000.0F, 1000);
        RenderSystem.setProjectionMatrix(matrix4f, VertexSorting.ORTHOGRAPHIC_Z);

        //model view stuff
        var stack = RenderSystem.getModelViewStack();
        stack.pushMatrix();
        stack.set(new Matrix4f().identity());

        //apply new model view transformation
        RenderSystem.applyModelViewMatrix();
        Lighting.setupFor3DItems();
        //end gui setup code

        //item renderer needs a new pose stack as it applies its last to render system itself. for the rest tbh idk
        GuiGraphics guiGraphics = new GuiGraphics(mc, mc.renderBuffers().bufferSource());
        drawFunction.accept(guiGraphics);
        guiGraphics.flush();

        //reset stuff
        stack.popMatrix();
        RenderSystem.applyModelViewMatrix();
        RenderSystem.restoreProjectionMatrix();
        //returns render calls to main render target
        mc.getMainRenderTarget().bindWrite(true);

        //and apparently not resetting causes clouds to be messed up
        RenderSystem.setShaderFogStart(fogStart);
        RenderSystem.setShaderFogEnd(fogEnd);
    }
}