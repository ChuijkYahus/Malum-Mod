package com.sammy.malum.core.handlers.client;

import com.mojang.blaze3d.systems.*;
import com.sammy.malum.*;
import com.sammy.malum.client.renderer.renderpass.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.player.*;
import net.minecraft.resources.*;
import net.minecraft.util.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.neoforged.neoforge.client.event.*;
import org.joml.*;
import org.lwjgl.opengl.*;
import team.lodestar.lodestone.registry.client.*;
import team.lodestar.lodestone.systems.rendering.*;
import team.lodestar.lodestone.systems.rendering.shader.*;

import java.lang.Math;

public class SoulWardRenderHandler {
    public static int glow;

    public static void tick(ClientTickEvent event) {
        final LocalPlayer player = Minecraft.getInstance().player;
        if (player != null) {
            var data = player.getData(MalumAttachmentTypes.SOUL_WARD);
            if (data.getSoulWard() >= player.getAttributeValue(MalumAttributes.SOUL_WARD_CAPACITY)) {
                if (glow < 20) {
                    glow++;
                }
            } else {
                if (glow > 0) {
                    glow = Math.max(glow - 2, 0);
                }
            }
        }
    }

    public static void renderSoulWard(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
        var minecraft = Minecraft.getInstance();
        var poseStack = guiGraphics.pose();
        if (!minecraft.options.hideGui) {
            var player = minecraft.player;
            if (!player.isCreative() && !player.isSpectator()) {
                var data = player.getData(MalumAttachmentTypes.SOUL_WARD);
                double soulWard = data.getSoulWard();
                if (soulWard > 0) {
                    float armor = (float) player.getAttribute(Attributes.ARMOR).getValue();

                    int left = guiGraphics.guiWidth() / 2 - 91;
                    int top = guiGraphics.guiHeight() - minecraft.gui.leftHeight - 3;

                    if (armor == 0) {
                        top += 10;
                    }
                    poseStack.pushPose();
                    RenderSystem.setShaderTexture(0, getSoulWardTexture());
                    RenderSystem.depthMask(true);
                    RenderSystem.enableBlend();
                    RenderSystem.defaultBlendFunc();
                    ExtendedShaderInstance shaderInstance = LodestoneShaders.SCREEN_DISTORTED_TEXTURE.getShaderInstance();
                    shaderInstance.safeGetUniform("YFrequency").set(15f);
                    shaderInstance.safeGetUniform("XFrequency").set(15f);
                    shaderInstance.safeGetUniform("Speed").set(550f);
                    shaderInstance.safeGetUniform("Intensity").set(120f);
                    var builder = VFXBuilders.createScreen().setShader(shaderInstance);

                    int size = 13;
                    boolean forceDisplay = soulWard <= 1;
                    double soulWardAmount = forceDisplay ? 1 : Math.ceil(Math.floor(soulWard) / 3f);
                    for (int i = 0; i < soulWardAmount; i++) {
                        int row = Mth.floor(i / 10f);
                        int x = left + i % 10 * 8;
                        int y = top - row * 4;
                        int progress = Math.min(3, (int) soulWard - i * 3);
                        int xTextureOffset = forceDisplay ? 31 : 1 + (3 - progress) * 15;

                        shaderInstance.safeGetUniform("UVCoordinates").set(new Vector4f(xTextureOffset / 45f, (xTextureOffset + size) / 45f, 0, 15 / 45f));
                        shaderInstance.safeGetUniform("TimeOffset").set(i * 150f);

                        builder.setAlpha(1f).setPositionWithWidth(x - 2, y - 2, size, size).setUVWithWidth(xTextureOffset, 0, size, size, 45);
                        builder.blit(poseStack);
                        if (glow > 0 && glow < 20) {
                            float alpha = (10 - Math.abs(10 - glow)) / 10f;
                            RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
                            builder.setAlpha(alpha).blit(poseStack).setAlpha(1);
                            RenderSystem.defaultBlendFunc();
                        }
                    }
                    shaderInstance.setUniformDefaults();
                    RenderSystem.disableBlend();
                    poseStack.popPose();
                }
            }
        }
    }

    public static ResourceLocation getSoulWardTexture() {
        return MalumMod.malumPath("textures/gui/hud/soul_ward.png");
    }
}
