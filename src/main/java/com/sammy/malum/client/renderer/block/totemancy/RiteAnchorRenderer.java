package com.sammy.malum.client.renderer.block.totemancy;

import com.mojang.blaze3d.vertex.*;
import com.mojang.math.*;
import com.sammy.malum.client.*;
import com.sammy.malum.common.block.curiosities.totem.anchor.*;
import com.sammy.malum.core.systems.spirit.type.*;
import com.sammy.malum.registry.client.*;
import net.minecraft.client.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.blockentity.*;
import net.minecraft.core.*;
import net.minecraft.util.*;
import org.jetbrains.annotations.*;
import org.joml.*;
import team.lodestar.lodestone.registry.client.*;
import team.lodestar.lodestone.systems.easing.*;
import team.lodestar.lodestone.systems.rendering.*;
import team.lodestar.lodestone.systems.rendering.rendeertype.*;

import java.awt.*;
import java.lang.Math;

public class RiteAnchorRenderer implements BlockEntityRenderer<RiteAnchorBlockEntity> {

    public RiteAnchorRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(RiteAnchorBlockEntity blockEntityIn, float partialTicks, PoseStack poseStack, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        var spiritType = blockEntityIn.getSpirit();
        if (spiritType == null) {
            return;
        }

        float delta = blockEntityIn.getGlowDelta();

        var facing = blockEntityIn.getBlockState().getValue(RiteAnchorBlock.FACING);
        poseStack.pushPose();
        poseStack.translate(0.5f, 0.5f, 0.5f);
        if (!facing.equals(Direction.UP)) {
            if (facing.getAxis().isHorizontal()) {
                int horizontal = facing.get2DDataValue() * 90;
                poseStack.mulPose(Axis.YN.rotationDegrees(horizontal));
                poseStack.mulPose(Axis.XP.rotationDegrees(90));
            }
            else {
                poseStack.mulPose(Axis.XP.rotationDegrees(180));
            }
        }
        for (int i = 0; i < 5; i++) {
            var token = i == 4 ? MalumRenderTypeTokens.RITE_ANCHOR_GLOW_TOP : MalumRenderTypeTokens.RITE_ANCHOR_GLOW_SIDE;
            var vertices = i == 4 ? getTopVertices() : getSideVertices();
            poseStack.pushPose();
            if (i < 4) {
                poseStack.mulPose(Axis.YN.rotationDegrees(i * 90));
            }
            renderFace(poseStack, spiritType, token, vertices, delta, partialTicks);
            poseStack.popPose();
        }
        var aimDirection = blockEntityIn.getAimDirection();
        if (aimDirection != null) {
            poseStack.pushPose();
            if (aimDirection.getData2d() != -1) {
                var vertices = getTopVertices();
                for (Vector3f vertex : vertices) {
                    vertex.add(0, 0.125f, 0);
                }
                int rotation = aimDirection.getData2d();
                if (facing.equals(Direction.DOWN)) {
                    rotation = -rotation + 90;
                }
                poseStack.mulPose(Axis.YN.rotationDegrees(180 + rotation * 90));
                renderFace(poseStack, spiritType, MalumRenderTypeTokens.RITE_ANCHOR_GLOW_POINTER, vertices, delta, partialTicks);
            }
            else {
                for (int i = 0; i < 4; i++) {
                    var vertices = getSideVertices();
                    for (Vector3f vertex : vertices) {
                        vertex.add(0, 0.3125f, 0);
                    }
                    poseStack.pushPose();
                    poseStack.mulPose(Axis.YN.rotationDegrees(i * 90));
                    if (aimDirection == RiteAnchorBlockEntity.AimState.PULL) {
                        poseStack.mulPose(Axis.XN.rotationDegrees(180));
                        for (Vector3f vertex : vertices) {
                            vertex.add(0, -0.625f, 0);
                        }
                    }
                    renderFace(poseStack, spiritType, MalumRenderTypeTokens.RITE_ANCHOR_GLOW_POINTER_SMALL, vertices, delta, partialTicks);
                    poseStack.popPose();
                }
            }
            poseStack.popPose();
        }
        poseStack.popPose();
    }

    private static void renderFace(PoseStack poseStack, SpiritArcanaType spiritType, RenderTypeToken token, Vector3f[] vertices, float delta, float partialTicks) {
        var level = Minecraft.getInstance().level;
        float gameTime = level.getGameTime() + partialTicks;
        float alpha = delta * 0.7f;
        float ease = Easing.SINE_OUT.ease(delta, 0, 1, 1);
        float offsetDistance = 0.2f - ease * 0.2f;
        float wobbleStrength = 0.1f - ease * 0.075f;
        int time = 160;
        float timer = ((gameTime % time) / time);
        var renderType = LodestoneRenderTypes.ADDITIVE_TEXTURE.apply(token).withModifier(r -> r.setCullState(RenderStateShard.NO_CULL));
        for (int j = 0; j < 4; j++) {
            var color = j <= 1 ? spiritType.getPrimaryColor() : spiritType.getSecondaryColor();
            double offset = 0;
            if (offsetDistance > 0) {
                double angle = j / 4f * (Math.PI * 2);
                angle += timer * (Math.PI * 2);
                offset = (offsetDistance * Math.cos(angle));
                if (j % 2 == 0) {
                    offset *= -1;
                }
            }

            poseStack.pushPose();
            poseStack.translate(offset, 0, 0);
            applyWobble(vertices, wobbleStrength);
            SpiritBasedWorldVFXBuilder.create(spiritType)
                    .setColor(color, alpha)
                    .setRenderType(renderType)
                    .renderQuad(poseStack, vertices, 1f);
            poseStack.popPose();
            alpha *= (1 - (delta + 0.2f) * 0.4f);
        }
    }

    private static @NotNull Vector3f[] getSideVertices() {
            return new Vector3f[]{
                    new Vector3f(-0.55f, -0.5f, 0.55f), new Vector3f(0.55f, -0.5f, 0.55f),
                    new Vector3f(0.55f, 0.5f, 0.55f), new Vector3f(-0.55f, 0.5f, 0.55f)};
    }

    private static @NotNull Vector3f[] getTopVertices() {
        return new Vector3f[]{
                new Vector3f(-0.55f, 0.55f, 0.55f), new Vector3f(0.55f, 0.55f, 0.55f),
                new Vector3f(0.55f, 0.55f, -0.55f), new Vector3f(-0.55f, 0.55f, -0.55f)};
    }

    public static void applyWobble(Vector3f[] offsets, float strength) {
        float offset = 0;
        for (Vector3f vector3f : offsets) {
            double time = ((Minecraft.getInstance().level.getGameTime() / 40.0F) % Math.PI * 2);
            float sine = Mth.sin((float) (time + (offset * Math.PI * 2))) * strength;
            vector3f.add(sine, -sine, 0);
            offset += 0.25f;
        }
    }
}