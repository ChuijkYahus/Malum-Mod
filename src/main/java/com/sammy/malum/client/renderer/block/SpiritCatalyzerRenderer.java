package com.sammy.malum.client.renderer.block;

import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Vector3f;
import com.sammy.malum.*;
import com.sammy.malum.common.block.curiosities.spirit_crucible.catalyzer.SpiritCatalyzerCoreBlockEntity;
import com.sammy.malum.core.systems.spirit.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.*;
import net.minecraft.resources.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import team.lodestar.lodestone.handlers.*;
import team.lodestar.lodestone.setup.*;
import team.lodestar.lodestone.systems.easing.*;
import team.lodestar.lodestone.systems.rendering.*;

import java.awt.*;
import java.util.*;

import static com.sammy.malum.MalumMod.malumPath;
import static net.minecraft.client.renderer.texture.OverlayTexture.NO_OVERLAY;


public class SpiritCatalyzerRenderer implements BlockEntityRenderer<SpiritCatalyzerCoreBlockEntity> {

    private static final ResourceLocation LIGHT_TRAIL = malumPath("textures/vfx/concentrated_trail.png");
    private static final RenderType TRAIL_TYPE = LodestoneRenderTypeRegistry.ADDITIVE_TEXTURE.apply(LIGHT_TRAIL);

    public SpiritCatalyzerRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(SpiritCatalyzerCoreBlockEntity blockEntityIn, float partialTicks, PoseStack poseStack, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        Level level = Minecraft.getInstance().level;
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        ItemStack stack = blockEntityIn.inventory.getStackInSlot(0);
        if (!stack.isEmpty()) {
            poseStack.pushPose();
            Vec3 offset = blockEntityIn.getItemOffset();
            poseStack.translate(offset.x, offset.y, offset.z);
            poseStack.mulPose(Vector3f.YP.rotationDegrees(((level.getGameTime() % 360) + partialTicks) * 3));
            poseStack.scale(0.45f, 0.45f, 0.45f);
            itemRenderer.renderStatic(stack, ItemTransforms.TransformType.FIXED, combinedLightIn, NO_OVERLAY, poseStack, bufferIn, 0);
            poseStack.popPose();
        }
        if (blockEntityIn.getTarget() != null && blockEntityIn.intensity != null) {
            poseStack.pushPose();
            final BlockPos blockPos = blockEntityIn.getBlockPos();
            poseStack.translate(-blockPos.getX(), -blockPos.getY(), -blockPos.getZ());
            for (Map.Entry<MalumSpiritType, Integer> entry : blockEntityIn.intensity.entrySet()) {
                if (entry.getValue() > 0) {
                    renderBeam(blockEntityIn, poseStack, entry.getKey(), entry.getValue());
                }
            }
            poseStack.popPose();
        }
    }

    public void renderBeam(SpiritCatalyzerCoreBlockEntity catalyzer, PoseStack poseStack, MalumSpiritType spiritType, int intensity) {
        VertexConsumer consumer = RenderHandler.DELAYED_RENDER.getBuffer(TRAIL_TYPE);
        BlockPos catalyzerPos = catalyzer.getBlockPos();
        Vec3 startPos = catalyzer.getItemOffset().add(catalyzerPos.getX(), catalyzerPos.getY(), catalyzerPos.getZ());
        Vec3 targetPos = catalyzer.getTarget().getAccelerationPoint();
        Vec3 difference = targetPos.subtract(startPos);
        float distance = 0.35f + Easing.SINE_OUT.ease(intensity / 60f, 0, 0.35f, 1);
        float alpha = intensity / 60f;
        Vec3 midPoint = startPos.add(difference.scale(distance));
        VFXBuilders.createWorld()
                .setPosColorTexLightmapDefaultFormat()
                .setColor(spiritType.getPrimaryColor())
                .setAlpha(alpha)
                .renderBeam(consumer, poseStack.last().pose(), startPos, midPoint, 0.4f, b -> b.setColor(spiritType.getSecondaryColor()).setAlpha(0f));
    }
}