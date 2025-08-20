package com.sammy.malum.client.renderer.renderpass;

import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.pipeline.TextureTarget;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.sammy.malum.MalumMod;
import com.sammy.malum.registry.client.MalumRenderTypes;
import com.sammy.malum.registry.client.MalumShaders;
import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix4f;
import org.joml.Matrix4fStack;
import org.joml.Vector3f;
import team.lodestar.lodestone.helpers.StateShardHelper;
import team.lodestar.lodestone.helpers.TextureHelper;
import team.lodestar.lodestone.systems.rendering.renderpass.BeforeLevelRenderPass;
import team.lodestar.lodestone.systems.texture.CubeMapTexture;

public class ParallelWorldRenderer extends BeforeLevelRenderPass {
    public static ParallelWorldRenderer INSTANCE;
    private final RenderTarget target = TextureHelper.generateTextureTarget(true);
    private static final RenderStateShard.OutputStateShard outputState = StateShardHelper.createOutputState("parallelWorld", () -> INSTANCE.target.bindWrite(false));
    private final ResourceLocation skybox = MalumMod.malumPath("textures/skybox/sky");
    private final AbstractTexture texture = new CubeMapTexture(skybox, true);

    public ParallelWorldRenderer() {
        INSTANCE = this;
        Minecraft.getInstance().getTextureManager().register(skybox, texture);
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

        VertexConsumer vertexConsumer = bufferSource.getBuffer(MalumRenderTypes.PARALLEL_WORLD_SKYBOX);

        PoseStack poseStack = new PoseStack();
        poseStack.pushPose();
        Vector3f cameraPosition = camera.getPosition().toVector3f();
        poseStack.scale(100, 100, 100);
        //poseStack.translate(-cameraPosition.x(), -cameraPosition.y(), -cameraPosition.z());

        for (int i = 0; i < skyboxVertices.length; i += 3) {
            vertexConsumer.addVertex(poseStack.last().pose(), skyboxVertices[i], skyboxVertices[i + 1], skyboxVertices[i + 2]);
        }
        poseStack.popPose();

        MalumShaders.PARALLEL_WORLD_SKYBOX.getShaderInstance().setSampler("SkyboxSampler", texture.getId());
        bufferSource.endBatch(MalumRenderTypes.PARALLEL_WORLD_SKYBOX);

        matrix4fstack.popMatrix();
        RenderSystem.applyModelViewMatrix();
        Minecraft.getInstance().getMainRenderTarget().bindWrite(false);
    }

    @Override
    public boolean shouldRender(DeltaTracker deltaTracker, Camera camera, GameRenderer gameRenderer, Matrix4f matrix4f, Matrix4f matrix4f1) {
        return false;
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
