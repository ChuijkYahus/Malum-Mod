package com.sammy.malum.client.screen.codex.display.texture;

import com.mojang.blaze3d.pipeline.RenderCall;
import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.pipeline.TextureTarget;
import com.mojang.blaze3d.platform.NativeImage;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.Tickable;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

import static net.minecraft.client.Minecraft.ON_OSX;

public class RenderableDynamicTexture extends DynamicTexture implements Tickable {

    @NotNull
    protected final Consumer<RenderableDynamicTexture> drawingFunction;

    private RenderTarget writeTarget;

    private final int width;
    private final int height;
    private final ResourceLocation writtenTextureLocation;

    private volatile boolean shouldTick = true;
    public boolean closed = false;

    public RenderableDynamicTexture(ResourceLocation resourceLocation, int width, int height, @NotNull Consumer<RenderableDynamicTexture> textureDrawingFunction) {
        super(width, height, false);
        RenderSystem.assertOnRenderThread();
        this.width = width;
        this.height = height;
        this.writtenTextureLocation = resourceLocation;
        this.drawingFunction = textureDrawingFunction;
        this.setUpdateNextTick(true);
    }

    public RenderableDynamicTexture(ResourceLocation resourceLocation, int size, @NotNull Consumer<RenderableDynamicTexture> textureDrawingFunction) {
        this(resourceLocation, size, size, textureDrawingFunction);
    }

    public ResourceLocation getWrittenTextureLocation() {
        return writtenTextureLocation;
    }

    private static void renderCall(RenderCall call) {
        if (!RenderSystem.isOnRenderThreadOrInit()) {
            RenderSystem.recordRenderCall(call);
        } else {
            call.execute();
        }
    }

    public void redraw() {
        if (closed) {
            return;
        }
        renderCall(() -> {
            bind();
            writeTarget.bindWrite(true);
            drawingFunction.accept(this);
            writeTarget.unbindWrite();
        });
    }

    public RenderTarget getRenderTarget() {
        return writeTarget;
    }

    public void bind(int id) {
        if (closed) {
            return;
        }
        RenderSystem.setShaderTexture(id, getRenderTarget().getColorTextureId());
    }

    @Override
    public void bind() {
        if (closed) {
            return;
        }
        super.bind();
    }

    @Override
    public int getId() {
        if (closed) {
            return 0;
        }
        RenderSystem.assertOnRenderThreadOrInit();
        if (writeTarget == null) {
            var pixels = getPixels();
            int w = pixels.getWidth();
            int h = pixels.getHeight();
            writeTarget = new TextureTarget(w, h, true, ON_OSX);
        }
        return writeTarget.getColorTextureId();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public void releaseId() {
        closed = true;
        super.releaseId();
        renderCall(() -> {
            if (writeTarget != null) {
                writeTarget.destroyBuffers();
                writeTarget = null;
            }
        });
    }

    public void download() {
        if (closed) {
            return;
        }
        bind();
        getPixels().downloadTexture(0, false);
    }

    public void setUpdateNextTick(boolean shouldTick) {
        this.shouldTick = shouldTick;
    }

    @ApiStatus.Internal
    @Override
    public void tick() {
        if (!shouldTick) return;
        shouldTick = false;
        redraw();
    }


    public void register() {
        Minecraft.getInstance().getTextureManager().register(writtenTextureLocation, this);
    }

    public void unregister() {
        var tm = Minecraft.getInstance().getTextureManager();
        var t = tm.getTexture(writtenTextureLocation);
        if (t == this) {
            tm.release(writtenTextureLocation);
        }
    }

    public boolean isClosed() {
        return closed;
    }

}