package com.sammy.malum.client.renderer.renderpass;

import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.*;
import com.sammy.malum.registry.client.*;
import dev.kosmx.playerAnim.core.util.*;
import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.util.*;
import org.joml.*;
import team.lodestar.lodestone.helpers.StateShardHelper;
import team.lodestar.lodestone.helpers.TextureHelper;
import team.lodestar.lodestone.systems.rendering.*;
import team.lodestar.lodestone.systems.rendering.rendeertype.*;
import team.lodestar.lodestone.systems.rendering.cube.CubeVertexData;
import team.lodestar.lodestone.systems.rendering.renderpass.BeforeLevelRenderPass;

import java.awt.*;
import java.lang.Math;

public class ParallelWorldRenderer extends BeforeLevelRenderPass {
    public static ParallelWorldRenderer INSTANCE;
    private final RenderTarget target = TextureHelper.generateTextureTarget(true);
    private static final RenderStateShard.OutputStateShard outputState = StateShardHelper.createOutputState("parallelWorld", () -> INSTANCE.target.bindWrite(false));

    public ParallelWorldRenderer() {
        INSTANCE = this;
    }

    @Override
    public void render(DeltaTracker deltaTracker, Camera camera, GameRenderer gameRenderer, Matrix4f viewMat, Matrix4f projMat) {
        Minecraft mc = Minecraft.getInstance();
        long gameTime = mc.level.getGameTime();
        var partialTicks = deltaTracker.getGameTimeDeltaTicks();
        float uOffset = ((gameTime + partialTicks) % 4000) / 2000f;
        float vOffset = ((gameTime + 500f + partialTicks) % 8000) / 8000f;
        float alpha = 0.95f;

        target.clear(Minecraft.ON_OSX);
        target.setClearColor(0, 0, 0, 0);
        MultiBufferSource.BufferSource bufferSource = mc.renderBuffers().bufferSource();
        Matrix4fStack matrix4fstack = RenderSystem.getModelViewStack();
        matrix4fstack.pushMatrix();
        matrix4fstack.mul(viewMat);
        RenderSystem.applyModelViewMatrix();


        PoseStack poseStack = new PoseStack();
        poseStack.pushPose();
        int cubeScale = 10;
        poseStack.scale(cubeScale, cubeScale, cubeScale);
//        Vector3f cameraPosition = camera.getPosition().toVector3f();
//        poseStack.translate(-cameraPosition.x(), -cameraPosition.y(), -cameraPosition.z());


        for (int i = 0; i < 8; i++) {
            float speed = 1000f + 750f * i;
            float distortion = 4f + 2 * i;
            float scale = (2 - 0.05f * i);
            float colorDelta = (gameTime + partialTicks) * ((i+1) * 0.01f);
            float red = Math.abs(0.1f + Mth.sin(colorDelta % 6.28f) * 0.3f);
            float blue = Math.abs(0.1f + Mth.sin((colorDelta * 2) % 6.28f) * 0.25f);
            Color color = new Color(red, 0, blue);

            var uniforms = new ShaderUniformHandler()
                    .modifyUniform("Speed", speed)
                    .modifyUniform("Distortion", distortion)
                    .modifyUniform("Width", 128f)
                    .modifyUniform("Height", 128f)
                    .modifyUniform("UVCoordinates", -20f, 40f, -20f, 40f);

            var builder = MalumRenderTypes.WEEPING_SKYBOX.apply(MalumRenderTypeTokens.VOID_NOISE)
                    .withModifier(b -> b.setTransparencyState(StateShards.ADDITIVE_TRANSPARENCY))
                    .withUniformHandler(uniforms);

            var renderType = builder.getRenderType();
            VertexConsumer consumer = bufferSource.getBuffer(renderType);
            poseStack.mulPose(Axis.XP.rotationDegrees(((gameTime + partialTicks) * 0.4f) % 360));
            poseStack.mulPose(Axis.YP.rotationDegrees(((gameTime + partialTicks) * 0.2f) % 360));
            poseStack.mulPose(Axis.ZP.rotationDegrees(((gameTime + partialTicks) * 0.1f) % 360));

            var vfxBuilder = VFXBuilders.createWorld()
                    .setVertexConsumer(consumer)
                    .setRenderType(renderType)
                    .setColor(color)
                    .setAlpha(alpha);
            var cubeData = CubeVertexData.makeCubePositions(-scale).applyWobble(0, 0.5f, 0.015f);
            vfxBuilder.setUV(-uOffset, vOffset, 1 - uOffset, 1 + vOffset).renderCube(poseStack, cubeData);
            vfxBuilder.setUV(uOffset, -vOffset, 1 + uOffset, 1 - vOffset).renderCube(poseStack, cubeData);

            uOffset = -uOffset * 1.25f - 0.2f;
            vOffset = -vOffset * 1.25f + 0.4f;
            alpha -= 0.05f;
        }
        bufferSource.endBatch();
        poseStack.popPose();

        matrix4fstack.popMatrix();
        RenderSystem.applyModelViewMatrix();
        MalumShaders.SOULLESS_OUTLINE.getShaderInstance().setSampler("Skybox", ParallelWorldRenderer.INSTANCE.getTarget().getColorTextureId());
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
