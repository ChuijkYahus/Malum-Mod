package com.sammy.malum.core.handlers.client;

import com.mojang.blaze3d.systems.*;
import com.sammy.malum.*;
import com.sammy.malum.registry.client.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.player.*;
import net.minecraft.resources.*;
import net.minecraft.util.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.neoforged.neoforge.client.event.*;
import org.lwjgl.opengl.*;
import team.lodestar.lodestone.registry.client.*;
import team.lodestar.lodestone.systems.easing.*;
import team.lodestar.lodestone.systems.rendering.*;
import team.lodestar.lodestone.systems.rendering.shader.*;

import java.lang.Math;

public class SoulWardRenderHandler {

    public static ResourceLocation BUBBLE = MalumMod.malumPath("textures/gui/hud/display_bubble.png");

    public static ResourceLocation SOUL_WARD = MalumMod.malumPath("textures/gui/hud/soul_ward.png");
    public static ResourceLocation GLOW = MalumMod.malumPath("textures/gui/hud/soul_ward_glow.png");
    public static ResourceLocation EMPTY = MalumMod.malumPath("textures/gui/hud/soul_ward_empty.png");
    public static ResourceLocation DISSOLVEMENT = MalumMod.malumPath("textures/gui/hud/soul_ward_dissolvement.png");

    public static int glow;
    public static int fadeout;

    public static float soulWard;

    public static void tick(ClientTickEvent event) {
        final LocalPlayer player = Minecraft.getInstance().player;
        if (player != null) {
            var data = player.getData(MalumAttachmentTypes.SOUL_WARD);
            if (data.getSoulWard() >= player.getAttributeValue(MalumAttributes.SOUL_WARD_CAPACITY)) {
                if (glow < 40) {
                    glow++;
                }
                else if (fadeout < 80) {
                    fadeout++;
                }
            } else {
                if (fadeout > 0) {
                    fadeout = 0;
                    glow = 20;
                }
                if (glow > 0) {
                    glow--;
                }
            }
            soulWard = Mth.lerp(0.2f, soulWard, (float) data.getSoulWard());
        }
    }

    public static void renderSoulWard(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
        var minecraft = Minecraft.getInstance();
        var poseStack = guiGraphics.pose();
        if (!minecraft.options.hideGui) {
            var player = minecraft.player;
            if (!player.isCreative() && !player.isSpectator()) {
                double capacity = player.getAttributeValue(MalumAttributes.SOUL_WARD_CAPACITY);
                if (soulWard > 0 && capacity > 0) {
                    float delta = (float) (soulWard / capacity);
                    float dissolvement = Easing.QUAD_OUT.ease(delta, 0, 1f);
                    float alpha = fadeout / 80f;
                    int left = guiGraphics.guiWidth() / 2;
                    int top = guiGraphics.guiHeight() - 47;

                    poseStack.pushPose();
                    RenderSystem.depthMask(true);
                    RenderSystem.enableBlend();
                    RenderSystem.defaultBlendFunc();
                    var distorted = LodestoneShaders.SCREEN_DISTORTED_TEXTURE.getShaderInstance();
                    distorted.safeGetUniform("YFrequency").set(24f);
                    distorted.safeGetUniform("XFrequency").set(16f);
                    distorted.safeGetUniform("Speed").set(1000f);
                    distorted.safeGetUniform("Intensity").set(80f);
                    distorted.safeGetUniform("Width").set(64f);
                    distorted.safeGetUniform("Height").set(64f);

                    var builder = VFXBuilders.createScreen().setShader(distorted);
                    builder.setPositionWithWidth(left - 16, top - 16, 32, 32);
                    builder.setAlpha(alpha).setTexture(EMPTY).blit(poseStack);

                    var hud = MalumShaders.SOUL_WARD_HUD.getShaderInstance();
                    RenderSystem.setShaderTexture(1, DISSOLVEMENT);
                    hud.safeGetUniform("YFrequency").set(24f);
                    hud.safeGetUniform("XFrequency").set(16f);
                    hud.safeGetUniform("Speed").set(1000f);
                    hud.safeGetUniform("Intensity").set(80f);
                    hud.safeGetUniform("Dissolvement").set(dissolvement);
                    hud.safeGetUniform("Width").set(64f);
                    hud.safeGetUniform("Height").set(64f);

                    builder.setShader(hud).setTexture(SOUL_WARD).blit(poseStack);
                    RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
                    builder.setAlpha(0.2f * alpha).blit(poseStack);
                    if (glow > 0 && glow < 40) {
                        float time = minecraft.level.getGameTime() + deltaTracker.getGameTimeDeltaPartialTick(true);
                        float glowAlpha = (20 - Math.abs(20 - glow)) / 20f;
                        int angle = Mth.floor((time * 20) % 360);
                        float range = Easing.SINE_IN_OUT.ease(glowAlpha, 0, 320f);
                        var light = LodestoneShaders.RADIAL_DISTORTED_SCREEN_LIGHT.getShaderInstance();
                        light.safeGetUniform("YFrequency").set(24f);
                        light.safeGetUniform("XFrequency").set(16f);
                        light.safeGetUniform("Speed").set(1000f);
                        light.safeGetUniform("Intensity").set(80f);
                        light.safeGetUniform("Width").set(64f);
                        light.safeGetUniform("Height").set(64f);
                        light.safeGetUniform("Angle").set(angle);
                        light.safeGetUniform("LightAngleRange").set(range);
                        RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
                        builder.setShader(light).setAlpha(alpha);
                        builder.setTexture(SOUL_WARD).blit(poseStack);
                        builder.setTexture(GLOW).blit(poseStack);
                        light.setUniformDefaults();
                    }
                    distorted.setUniformDefaults();
                    hud.setUniformDefaults();
                    RenderSystem.defaultBlendFunc();
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
