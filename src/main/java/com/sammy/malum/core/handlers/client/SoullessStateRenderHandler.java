package com.sammy.malum.core.handlers.client;

import com.mojang.blaze3d.systems.*;
import com.mojang.blaze3d.vertex.*;
import com.sammy.malum.registry.client.*;
import net.minecraft.client.*;
import net.minecraft.client.model.geom.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.resources.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.*;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector2f;
import org.joml.Vector3f;
import team.lodestar.lodestone.handlers.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.registry.client.*;
import team.lodestar.lodestone.systems.rendering.vertexconsumer.*;
import team.lodestar.lodestone.systems.rendering.vertexconsumer.offset.*;

import java.awt.*;

public class SoullessStateRenderHandler {

    private static boolean renderingSoullessCreature = false;
    private static ResourceLocation entityTexture;
    private static PoseStack poseStack;

    public static void startRenderingSoullessOutline(LivingEntityRenderer<LivingEntity, ?> renderer, LivingEntity entity, PoseStack stack) {
        entityTexture = renderer.getTextureLocation(entity);
        poseStack = stack;
        renderingSoullessCreature = true;
    }

    public static void endRenderingSoullessOutline(LivingEntity entity) {
        renderingSoullessCreature = false;
    }

    public static void renderSoullessModelPart(ModelPart modelPart, PoseStack.Pose pose, VertexConsumer buffer, int packedLight, int packedOverlay) {
        if (renderingSoullessCreature) {
            AbstractTexture activeTexture = Minecraft.getInstance().getTextureManager().getTexture(entityTexture);
            int maskTexture = activeTexture.getId();
            var renderType = MalumRenderTypes.SOULLESS_OUTLINE.apply(MalumRenderTypeTokens.VOID_NOISE).withUniformHandler(
                    shader -> shader.setSamplerTexture("Mask", maskTexture)
            );

            var vertexConsumer = LodestoneRenderHandler.DEFERRED_RENDER.getTarget().getBuffer(renderType);

            for (int i = 0; i < 4; i++) {
                Color color = new Color(42 + i * 20, 32, 60, 80);
                float size = 1f + ((i+1) * 0.01F);
                float rate = 0.25f + (i * 0.15F);
                if (i % 2 == 0) {
                    rate *= -1;
                }
                renderOutline(modelPart, vertexConsumer, pose, color, size, rate, packedLight, packedOverlay);
            }

            //            Soul Ward Test
//            Color bright = new Color(255, 238, 163, 200);
//            Color wa = new Color(251, 102, 221, 150);
//            Color wawa = new Color(89, 26, 99, 100);
//            var renderType = LodestoneRenderTypes.TRANSPARENT_TEXTURE.apply(MalumRenderTypeTokens.VOID_NOISE);
//            var vertexConsumer = LodestoneRenderHandler.DEFERRED_RENDER.getTarget().getBuffer(renderType);
//            renderOutline(modelPart, vertexConsumer, pose, bright, 1.03F, 0.5F, RenderHelper.FULL_BRIGHT, packedOverlay);
//            renderOutline(modelPart, vertexConsumer, pose, wa, 1.02F, 1F, RenderHelper.FULL_BRIGHT, packedOverlay);
//            renderOutline(modelPart, vertexConsumer, pose, wawa, 1.01F, 1.5F, RenderHelper.FULL_BRIGHT, packedOverlay);
//
//            renderType = LodestoneRenderTypes.ADDITIVE_TEXTURE.apply(MalumRenderTypeTokens.VOID_NOISE);
//            vertexConsumer = LodestoneRenderHandler.LATE_DEFERRED_RENDER.getTarget().getBuffer(renderType);
//            renderOutline(modelPart, vertexConsumer, pose, bright, 1.04F, 0.5F, RenderHelper.FULL_BRIGHT, packedOverlay);
//            renderOutline(modelPart, vertexConsumer, pose, wa, 1.03F, 1F, RenderHelper.FULL_BRIGHT, packedOverlay);
//            renderOutline(modelPart, vertexConsumer, pose, wawa, 1.02F, 1.5F, RenderHelper.FULL_BRIGHT, packedOverlay);
        }
    }

    public static void renderOutline(ModelPart modelPart, VertexConsumer vertexConsumer, PoseStack.Pose pose, Color color, float size, float rate, int packedLight, int packedOverlay) {
        float inverse = 1 / size;
        var minecraft = Minecraft.getInstance();
        long timeOffset = modelPart.hashCode() % 1000;
        long gameTime = minecraft.level.getGameTime() + timeOffset;
        float uInterval = 800 * rate;
        float vInterval = uInterval * 4;
        float uOffset = (gameTime % uInterval) / uInterval;
        float vOffset = (gameTime % vInterval) / vInterval;
        var effectBuffer = new ModifiedVertexConsumer(vertexConsumer);
        //TODO: Uv offset should not apply to the mask, which at the moment they do
//        effectBuffer.setOffset(uOffset, vOffset);
        poseStack.scale(size, size, size);
        modelPart.compile(pose, effectBuffer, packedLight, packedOverlay, ColorHelper.getColor(color));
        poseStack.scale(inverse, inverse, inverse);
    }

    public static void generateTangentBitangent(PoseStack.Pose pose, VertexConsumer buffer, ModelPart.Polygon polygon, Vector3f normal) {
        if (buffer instanceof ModifiedVertexConsumer vc) {
            if (polygon.vertices.length >= 3) {
                ModelPart.Vertex v1 = polygon.vertices[0];
                ModelPart.Vertex v2 = polygon.vertices[1];
                ModelPart.Vertex v3 = polygon.vertices[2];

                Vector3f pos1 = v1.pos;
                Vector3f pos2 = v2.pos;
                Vector3f pos3 = v3.pos;

                Vector2f uv1 = new Vector2f(v1.u, v1.v);
                Vector2f uv2 = new Vector2f(v2.u, v2.v);
                Vector2f uv3 = new Vector2f(v3.u, v3.v);

                Vector3f edge1 = pos2.sub(pos1, new Vector3f());
                Vector3f edge2 = pos3.sub(pos1, new Vector3f());
                Vector2f deltaUV1 = uv2.sub(uv1, new Vector2f());
                Vector2f deltaUV2 = uv3.sub(uv1, new Vector2f());

                float f = 1.0F / (deltaUV1.x * deltaUV2.y - deltaUV1.y * deltaUV2.x);
                Vector3f tangent = new Vector3f(f);
                tangent.mul(
                        deltaUV2.y * edge1.x - deltaUV1.y * edge2.x,
                        deltaUV2.y * edge1.y - deltaUV1.y * edge2.y,
                        deltaUV2.y * edge1.z - deltaUV1.y * edge2.z
                );

                Vector3f bitangent = new Vector3f(f);
                bitangent.mul(
                        -deltaUV2.x * edge1.x + deltaUV1.x * edge2.x,
                        -deltaUV2.x * edge1.y + deltaUV1.x * edge2.y,
                        -deltaUV2.x * edge1.z + deltaUV1.x * edge2.z
                );

                vc.setExtraData(
                        pose.transformNormal(tangent, new Vector3f()),
                        pose.transformNormal(bitangent, new Vector3f())
                );
            }
        }
    }

    // TODO: Uncomment the code here once we use the parallax soulless rendertype
    public static class ModifiedVertexConsumer extends UVOffsetVertexConsumer {
        public Vector3f tangent = new Vector3f();
        public Vector3f bitangent = new Vector3f();

        public ModifiedVertexConsumer(VertexConsumer consumer) {
            super(consumer);
        }

        public void setExtraData(Vector3f tangent, Vector3f bitangent) {
            this.tangent.set(tangent);
            this.bitangent.set(bitangent);
        }

        @Override
        public void addVertex(float x, float y, float z, int color, float u, float v, int packedOverlay, int packedLight, float normalX, float normalY, float normalZ) {
            this.addVertex(x, y, z);
            this.setColor(color);
            this.setUv(u, v);
            this.setLight(packedLight);
            this.setNormal(normalX, normalY, normalZ);
            this.setTangent(tangent.x, tangent.y, tangent.z);
            this.setBitangent(bitangent.x, bitangent.y, bitangent.z);
        }

        public @NotNull VertexConsumer setTangent(float x, float y, float z) {
            LodestoneBufferBuilder buffer = new LodestoneBufferBuilder(consumer);
            buffer.beginElement(LodestoneVertexFormats.TANGENT).putBytes(
                    normalIntValue(x),
                    normalIntValue(y),
                    normalIntValue(z)
            );
            return this;
        }

        public @NotNull VertexConsumer setBitangent(float x, float y, float z) {
            LodestoneBufferBuilder buffer = new LodestoneBufferBuilder(consumer);
            buffer.beginElement(LodestoneVertexFormats.BITANGENT).putBytes(
                    normalIntValue(x),
                    normalIntValue(y),
                    normalIntValue(z)
            );
            return this;
        }

        private static byte normalIntValue(float value) {
            return (byte)((int)(Mth.clamp(value, -1.0F, 1.0F) * 127.0F) & 0xFF);
        }
    }
}
