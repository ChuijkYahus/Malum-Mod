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
import com.sammy.malum.registry.client.MalumShaders;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
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

    public final ResourceLocation texturePath;
    public int width;
    public int height;

    public static DynamicTextureRenderer create(ResourceLocation texturePath) {
        return new DynamicTextureRenderer(texturePath);
    }
    public static DynamicTextureRenderer create(ItemLike itemLike) {
        var holder = itemLike.asItem().builtInRegistryHolder();
        var path = holder.key().location();
        return new DynamicTextureRenderer(path.withPrefix("generated/").withSuffix(".png"));
    }

    public DynamicTextureRenderer(ResourceLocation texturePath) {
        this.texturePath = texturePath;
    }

    public DynamicTextureRenderer setTextureSize(int size) {
        return setTextureSize(size, size);
    }

    public DynamicTextureRenderer setTextureSize(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public static void clearCache() {
        TEXTURE_CACHE.invalidateAll();
    }

    @Nullable
    public <T extends RenderableDynamicTexture> T getTextureIfPresent(ResourceLocation id) {
        var ifPresent = TEXTURE_CACHE.getIfPresent(id);
        return ifPresent == null || !ifPresent.isDone() ? null : (T) ifPresent.join();
    }

    public RenderableDynamicTexture requestFlatItemStackTexture(ItemStack stack) {
        return drawAndRequestTexture(t -> drawItem(t, stack), true);
    }

    public RenderableDynamicTexture requestFlatItemTexture(Item item) {
        return requestFlatItemTexture(item, null);
    }

    public RenderableDynamicTexture requestFlatItemTexture(Item item, @Nullable Consumer<NativeImage> postProcessing) {
        return requestFlatItemTexture(item, postProcessing, false);
    }

    public RenderableDynamicTexture requestFlatItemTexture(Item item, @Nullable Consumer<NativeImage> postProcessing, boolean updateEachFrame) {
        return drawAndRequestTexture(t -> {
            drawItem(t, item.getDefaultInstance());
            if (postProcessing != null) {
                t.download();
                NativeImage img = t.getPixels();
                postProcessing.accept(img);
                t.upload();
            }
        }, updateEachFrame);
    }


    public RenderableDynamicTexture requestOutline(ResourceLocation texture, int sourceWidth, int sourceHeight, int outlineWidth, boolean updateEachFrame) {
        return drawAndRequestTexture(t -> drawOutline(t, texture, sourceWidth, sourceHeight, outlineWidth), updateEachFrame);
    }

    public RenderableDynamicTexture requestTexture(ResourceLocation texture, boolean updateEachFrame) {
        return drawAndRequestTexture(t -> drawTexture(t, texture), updateEachFrame);
    }

    public RenderableDynamicTexture drawAndRequestTexture(Consumer<RenderableDynamicTexture> textureDrawingFunction, boolean updateEachFrame) {
        var t = requestTexture((rl) -> new RenderableDynamicTexture(rl, width, height, textureDrawingFunction));
        if (t != null && updateEachFrame) {
            t.setUpdateNextTick(true);
        }
        return t;
    }


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

    public void drawItem(RenderableDynamicTexture tex, ItemStack stack) {
        drawAsInGUI(tex, g -> g.renderFakeItem(stack, 0, 0));
    }

    public void drawTexture(RenderableDynamicTexture tex, ResourceLocation texture) {
        drawTexture(tex, GameRenderer::getPositionTexColorShader, texture);
    }

    public void drawOutline(RenderableDynamicTexture tex, ResourceLocation texture, int sourceWidth, int sourceHeight, int outlineWidth) {
        var outline = MalumShaders.OUTLINED_HUD_ELEMENT.getShaderInstance();
        outline.safeGetUniform("OutlineWidth").set(outlineWidth);
        outline.safeGetUniform("SourceTextureSize").set((float)sourceWidth, (float)sourceHeight);
        outline.safeGetUniform("OutputTextureSize").set((float)width, (float)height);
        drawTexture(tex, MalumShaders.OUTLINED_HUD_ELEMENT::getShaderInstance, texture);
        outline.setUniformDefaults();
    }

    public void drawTexture(RenderableDynamicTexture tex, Supplier<ShaderInstance> shader, ResourceLocation texture) {
        drawAsInGUI(tex, s -> {

            var pose = s.pose().last();
            RenderSystem.setShaderTexture(0, texture);
            RenderSystem.enableDepthTest();
            RenderSystem.depthMask(false);
            RenderSystem.enableBlend();
            RenderSystem.setShader(shader);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1);
            var tesselator = Tesselator.getInstance();
            var builder = tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
            builder.addVertex(pose, 0, height, 0).setUv(0, 0);
            builder.addVertex(pose, width, height, 0).setUv(1, 0);
            builder.addVertex(pose, width, 0, 0).setUv(1, 1);
            builder.addVertex(pose, 0, 0, 0).setUv(0, 1);

            BufferUploader.drawWithShader(builder.buildOrThrow());

            VFXBuilders.createScreen()
                    .setPositionWithWidth(0, 0, width, height)
                    .setUV(0, 1, 1, 0)
                    .setFormat(DefaultVertexFormat.POSITION_TEX)
                    .setTexture(texture)
                    .setShader(shader)
                    .blit(s.pose());
        });
    }

    public void drawNormalized(RenderableDynamicTexture tex, Consumer<PoseStack> drawFunction) {
        drawAsInGUI(tex, g -> {
            var s = g.pose();
            float scale = 1f / 16f;
            s.translate(8, 8, 0);
            s.scale(scale, scale, 1);
            drawFunction.accept(s);
        });
    }

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
        Matrix4f matrix4f = new Matrix4f().setOrtho(0.0F, width, height, 0, -1000.0F, 1000);
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