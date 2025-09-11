package com.sammy.malum.client.renderer.renderpass;

import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.pipeline.TextureTarget;
import com.mojang.blaze3d.platform.*;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.sammy.malum.MalumMod;
import com.sammy.malum.registry.client.*;
import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix4f;
import org.joml.Matrix4fStack;
import org.joml.Vector3f;
import team.lodestar.lodestone.helpers.StateShardHelper;
import team.lodestar.lodestone.helpers.TextureHelper;
import team.lodestar.lodestone.systems.rendering.*;
import team.lodestar.lodestone.systems.rendering.rendeertype.*;
import team.lodestar.lodestone.systems.rendering.cube.CubeVertexData;
import team.lodestar.lodestone.systems.rendering.renderpass.BeforeLevelRenderPass;

public class ParallelWorldRenderer extends BeforeLevelRenderPass {
    public static ParallelWorldRenderer INSTANCE;
    private final RenderTarget target = TextureHelper.generateTextureTarget(true);
    private static final RenderStateShard.OutputStateShard outputState = StateShardHelper.createOutputState("parallelWorld", () -> INSTANCE.target.bindWrite(false));

    public ParallelWorldRenderer() {
        INSTANCE = this;
    }

    float[] skyboxVertices = {
            -1.0f,  1.0f, -1.0f,
            -1.0f, -1.0f, -1.0f,
            1.0f, -1.0f, -1.0f,
            1.0f, -1.0f, -1.0f,
            1.0f,  1.0f, -1.0f,
            -1.0f,  1.0f, -1.0f,

            -1.0f, -1.0f,  1.0f,
            -1.0f, -1.0f, -1.0f,
            -1.0f,  1.0f, -1.0f,
            -1.0f,  1.0f, -1.0f,
            -1.0f,  1.0f,  1.0f,
            -1.0f, -1.0f,  1.0f,

            1.0f, -1.0f, -1.0f,
            1.0f, -1.0f,  1.0f,
            1.0f,  1.0f,  1.0f,
            1.0f,  1.0f,  1.0f,
            1.0f,  1.0f, -1.0f,
            1.0f, -1.0f, -1.0f,

            -1.0f, -1.0f,  1.0f,
            -1.0f,  1.0f,  1.0f,
            1.0f,  1.0f,  1.0f,
            1.0f,  1.0f,  1.0f,
            1.0f, -1.0f,  1.0f,
            -1.0f, -1.0f,  1.0f,

            -1.0f,  1.0f, -1.0f,
            1.0f,  1.0f, -1.0f,
            1.0f,  1.0f,  1.0f,
            1.0f,  1.0f,  1.0f,
            -1.0f,  1.0f,  1.0f,
            -1.0f,  1.0f, -1.0f,

            -1.0f, -1.0f, -1.0f,
            -1.0f, -1.0f,  1.0f,
            1.0f, -1.0f, -1.0f,
            1.0f, -1.0f, -1.0f,
            -1.0f, -1.0f,  1.0f,
            1.0f, -1.0f,  1.0f
    };


    @Override
    public void render(DeltaTracker deltaTracker, Camera camera, GameRenderer gameRenderer, Matrix4f viewMat, Matrix4f projMat) {
        target.clear(Minecraft.ON_OSX);
        Minecraft mc = Minecraft.getInstance();
        MultiBufferSource.BufferSource bufferSource = mc.renderBuffers().bufferSource();

        Matrix4fStack matrix4fstack = RenderSystem.getModelViewStack();
        matrix4fstack.pushMatrix();
        matrix4fstack.mul(viewMat);
        RenderSystem.applyModelViewMatrix();


        PoseStack poseStack = new PoseStack();
        poseStack.pushPose();
        poseStack.scale(100, 100, 100);
        Vector3f cameraPosition = camera.getPosition().toVector3f();
        poseStack.translate(-cameraPosition.x(), -cameraPosition.y(), -cameraPosition.z());

        long gameTime = mc.level.getGameTime();
        var partialTicks = deltaTracker.getGameTimeDeltaTicks();
        float uOffset = ((gameTime + partialTicks) % 4000) / 2000f;
        float vOffset = ((gameTime + 500f + partialTicks) % 8000) / 8000f;
        float color = 0.3f;
        float alpha = 0.8f;
        for (int i = 0; i < 3; i++) {
            float speed = 1000f + 750f * i;
            float scale = (1 - 0.025f * i);
            final ShaderUniformHandler uniforms = new ShaderUniformHandler()
                    .modifyUniform("Speed", speed)
                    .modifyUniform("Width", 16f)
                    .modifyUniform("Height", 16f);

            var builder = MalumRenderTypes.WORLD_SKYBOX.apply(MalumRenderTypeTokens.VOID_NOISE).withUniformHandler(uniforms);
            if (i == 2) {
                builder.withModifier(b -> b.setTransparencyState(StateShards.ADDITIVE_TRANSPARENCY));
            }
            var renderType = builder.getRenderType();
            VertexConsumer consumer = bufferSource.getBuffer(renderType);

            var vfxBuilder = VFXBuilders.createWorld()
                    .setVertexConsumer(consumer)
                    .setRenderType(renderType)
                    .setColor(color*1.25f, color, color)
                    .setAlpha(alpha);
            var cubeData = CubeVertexData.makeCubePositions(-scale).applyWobble(0, 0.5f, 0.015f);
            vfxBuilder.setUV(-uOffset, vOffset, 1 - uOffset, 1 + vOffset).renderCube(poseStack, cubeData);
            vfxBuilder.setUV(uOffset, -vOffset, 1 + uOffset, 1 - vOffset).renderCube(poseStack, cubeData);

            bufferSource.endBatch(renderType);
            uOffset = -uOffset - 0.2f;
            vOffset = -vOffset + 0.4f;
            color *= 0.75f;
            alpha /= 2f;
        }
        poseStack.popPose();

        matrix4fstack.popMatrix();
        RenderSystem.applyModelViewMatrix();
        Minecraft.getInstance().getMainRenderTarget().bindWrite(true);
    }

    @Override
    public boolean shouldRender(DeltaTracker deltaTracker, Camera camera, GameRenderer gameRenderer, Matrix4f matrix4f, Matrix4f matrix4f1) {
        return true;
    }

    @Override
    public void resize(int width, int height) {
        target.resize(width, height, Minecraft.ON_OSX);
    }

    public RenderTarget getTarget() {
        return target;
    }

    public static RenderStateShard.OutputStateShard getOutputState() {
        return outputState;
    }
}
