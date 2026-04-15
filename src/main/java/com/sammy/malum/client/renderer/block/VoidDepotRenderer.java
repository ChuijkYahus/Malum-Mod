package com.sammy.malum.client.renderer.block;

import com.mojang.blaze3d.vertex.*;
import com.mojang.math.*;
import com.sammy.malum.common.block.curiosities.weeping_well.void_depot.*;
import com.sammy.malum.registry.client.*;
import com.sammy.malum.registry.common.magic.*;
import net.minecraft.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.blockentity.*;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.*;
import net.minecraft.util.*;
import org.joml.*;
import team.lodestar.lodestone.handlers.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.registry.client.*;
import team.lodestar.lodestone.modules.core.easing.Easing;
import team.lodestar.lodestone.systems.rendering.*;
import team.lodestar.lodestone.systems.rendering.rendeertype.*;

import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.stream.*;

import static com.sammy.malum.registry.client.MalumRenderTypeTokens.VOID_NOISE;
import static com.sammy.malum.registry.client.MalumRenderTypeTokens.VOID_VIGNETTE;


public class VoidDepotRenderer implements BlockEntityRenderer<VoidDepotBlockEntity> {

    private static final MultiBufferSource ADDITIVE = new LodestoneBufferWrapper(LodestoneRenderTypes.ADDITIVE_TEXT, LodestoneRenderHandler.LATE_DEFERRED_RENDER.getTarget());
    private static final MultiBufferSource TRANSPARENT = new LodestoneBufferWrapper(LodestoneRenderTypes.TRANSPARENT_TEXT, LodestoneRenderHandler.DEFERRED_RENDER.getTarget());

    public VoidDepotRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(VoidDepotBlockEntity blockEntityIn, float partialTicks, PoseStack poseStack, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        float height = 0.9375f;
        float width = 0.3125f;

        Vector3f[] positions = new Vector3f[]{new Vector3f(-width, height, width), new Vector3f(width, height, width), new Vector3f(width, height, -width), new Vector3f(-width, height, -width)};
        VFXBuilders.WorldVFXBuilder builder = VFXBuilders.createWorld();
        poseStack.pushPose();
        poseStack.translate(0.5f, 0.01f, 0.5f);

        builder.replaceBufferSource(LodestoneRenderHandler.LATE_DEFERRED_RENDER.getTarget())
                .setRenderType(LodestoneRenderTypes.TRANSPARENT_TEXTURE.apply(VOID_VIGNETTE))
                .renderQuad(poseStack, positions, 1f);
        final long gameTime = blockEntityIn.getLevel().getGameTime();
        float uOffset = ((gameTime + partialTicks) % 4000) / 2000f;
        float vOffset = ((gameTime + 500f + partialTicks) % 8000) / 8000f;
        float alpha = 0.05f;

        var distortion = MalumRenderTypes.WEEPING_WELL_DISTORTED_TEXTURE.apply(VOID_NOISE);
        builder.replaceBufferSource(LodestoneRenderHandler.DEFERRED_RENDER.getTarget());
        for (int i = 0; i < 2; i++) {
            float speed = 1000f + 250f * i;
            final ShaderUniformHandler uniforms = new ShaderUniformHandler()
                    .modifyUniform("Speed", speed)
                    .modifyUniform("Width", 16f)
                    .modifyUniform("Height", 16f);
            builder.setColor(MalumSpiritTypes.WICKED_SPIRIT.getPrimaryColor())
                    .setRenderType(distortion.withUniformHandler(uniforms));

            builder.setAlpha(alpha);
            builder.setUV(-uOffset, vOffset, 1 - uOffset, 1 + vOffset).renderQuad(poseStack, positions, 1f);
            builder.setUV(uOffset, -vOffset, 1 + uOffset, 1 - vOffset).renderQuad(poseStack, positions, 1f);
            alpha -= 0.0125f;
            uOffset = -uOffset - 0.2f;
            vOffset = -vOffset + 0.4f;
            poseStack.translate(0, 0.05f, 0);
            poseStack.mulPose(Axis.YP.rotationDegrees(90));
            if (i == 0) {
                builder.setColor(MalumSpiritTypes.ELDRITCH_SPIRIT.getPrimaryColor());
            }
        }
        poseStack.popPose();


        if (blockEntityIn.textVisibility > 12) {
            final Font font = Minecraft.getInstance().font;
            float timer = Mth.clamp((blockEntityIn.textVisibility + (blockEntityIn.nearTimer > 0 ? 1 : -1) * partialTicks), 0, 40);
            float eased = Easing.SINE_IN_OUT.ease(timer/40f);
            float scale = 0.016F - (1-eased)*0.004f;
            final Font.DisplayMode display = Font.DisplayMode.NORMAL;

            List<VoidDepotBlockEntity.VoidDepotGoal> goals = blockEntityIn.goals;
            List<MutableComponent> components = new ArrayList<>();
            if (!blockEntityIn.goals.isEmpty()) {
                components = goals.stream().map(g -> Component.literal(g.index + ": <" + g.deliveredAmount + "/" + g.amount + ">")).collect(Collectors.toCollection(ArrayList::new));
            }
            components.addAll(blockEntityIn.textToDisplay.stream().map(Component::literal).toList());

            poseStack.pushPose();
            poseStack.translate(0.5f, 2f, 0.5f);
            poseStack.mulPose(Minecraft.getInstance().getEntityRenderDispatcher().cameraOrientation());
            poseStack.mulPose(Axis.YP.rotationDegrees(180f));
            for (int i = 0; i < components.size(); i++) {
                for (int j = 0; j < 2; j++) {
                    final MutableComponent text = components.get(i).copy();
                    final boolean isAdditive = j == 0;
                    MultiBufferSource bufferToUse = isAdditive ? ADDITIVE : TRANSPARENT;
                    MutableComponent outlineText = text.copy();

                    text.withStyle(isAdditive ? style -> style.withColor(TextColor.fromRgb(MalumSpiritTypes.WICKED_SPIRIT.getPrimaryColor().getRGB())) : style -> style.withColor(TextColor.fromRgb(new Color(50, 0, 50).getRGB())));
                    outlineText.withStyle(isAdditive ? ChatFormatting.RED : ChatFormatting.BLACK);

                    poseStack.pushPose();
                    Matrix4f pose = poseStack.last().pose();
                    poseStack.translate(0, i * 0.15f, 0);
                    if (isAdditive) {
                        poseStack.translate(0f, 0, 0.05f);
                    }
                    poseStack.scale(scale, -scale, -scale);

                    float offset = isAdditive ? 0.4f : 0.8f;
                    float f = (-font.width(text) / 2f);
                    float xPos = 0 + f;
                    int color = ColorHelper.getColor(1, 1, 1, (isAdditive ? 0.3f : 0.9f)*eased);
                    font.drawInBatch(text, xPos, 0, color, false, pose, bufferToUse, display, 0, LightTexture.FULL_BRIGHT);

                    color = ColorHelper.getColor(1, 1, 1, (isAdditive ? 0.15f : 0.7f)*eased);
                    font.drawInBatch(text, xPos - offset, 0, color, false, pose, bufferToUse, display, 0, LightTexture.FULL_BRIGHT);
                    font.drawInBatch(text, xPos - offset, 0, color, false, pose, bufferToUse, display, 0, LightTexture.FULL_BRIGHT);
                    font.drawInBatch(text, xPos, offset, color, false, pose, bufferToUse, display, 0, LightTexture.FULL_BRIGHT);
                    font.drawInBatch(text, xPos, -offset, color, false, pose, bufferToUse, display, 0, LightTexture.FULL_BRIGHT);

                    color = ColorHelper.getColor(1, 1, 1, (isAdditive ? 0.1f : 0.5f)*eased);
                    font.drawInBatch(text, xPos - 2*offset, 0, color, false, pose, bufferToUse, display, 0, LightTexture.FULL_BRIGHT);
                    font.drawInBatch(outlineText, xPos + 2*offset, 0, color, false, pose, bufferToUse, display, 0, LightTexture.FULL_BRIGHT);
                    font.drawInBatch(outlineText, xPos, 2*offset, color, false, pose, bufferToUse, display, 0, LightTexture.FULL_BRIGHT);
                    font.drawInBatch(text, xPos, -2*offset, color, false, pose, bufferToUse, display, 0, LightTexture.FULL_BRIGHT);

                    font.drawInBatch(outlineText, xPos - offset, -offset, color, false, pose, bufferToUse, display, 0, LightTexture.FULL_BRIGHT);
                    font.drawInBatch(text, xPos - offset, offset, color, false, pose, bufferToUse, display, 0, LightTexture.FULL_BRIGHT);
                    font.drawInBatch(outlineText, xPos + offset, offset, color, false, pose, bufferToUse, display, 0, LightTexture.FULL_BRIGHT);
                    font.drawInBatch(text, xPos + offset, -offset, color, false, pose, bufferToUse, display, 0, LightTexture.FULL_BRIGHT);
                    poseStack.popPose();
                }
            }
            poseStack.popPose();
        }
    }
}