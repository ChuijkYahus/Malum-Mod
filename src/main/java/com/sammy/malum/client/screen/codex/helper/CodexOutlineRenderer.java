package com.sammy.malum.client.screen.codex.helper;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.sammy.malum.client.screen.codex.WidgetDesignType;
import com.sammy.malum.core.systems.spirit.type.SpiritArcanaType;
import com.sammy.malum.registry.common.magic.MalumSpiritTypes;
import it.unimi.dsi.fastutil.ints.Int2ObjectFunction;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.lwjgl.opengl.GL11;
import team.lodestar.lodestone.registry.client.LodestoneShaders;
import team.lodestar.lodestone.systems.rendering.VFXBuilders;

import java.awt.*;

public class CodexOutlineRenderer {

    protected final ResourceLocation glowTexture;
    protected final ResourceLocation outlineTexture;
    protected final int left;
    protected final int top;
    protected final int width;
    protected final int height;

    protected float effectStrength;

    public static CodexOutlineRenderer create(WidgetDesignType designType, int left, int top) {
        return new CodexOutlineRenderer(designType.getGlowTexture(), designType.getOutlineTexture(), left, top, 64, 64);
    }

    public static CodexOutlineRenderer create(ResourceLocation glowTexture, ResourceLocation outlineTexture, int left, int top) {
        return new CodexOutlineRenderer(glowTexture, outlineTexture, left, top, 64, 64);
    }

    protected CodexOutlineRenderer(ResourceLocation glowTexture, ResourceLocation outlineTexture, int left, int top, int width, int height) {
        this.glowTexture = glowTexture;
        this.outlineTexture = outlineTexture;
        this.left = left;
        this.top = top;
        this.width = width;
        this.height = height;
    }

    public CodexOutlineRenderer setEffectStrength(float oldStrength, float effectStrength, float total) {
        var minecraft = Minecraft.getInstance();
        float delta = minecraft.getTimer().getGameTimeDeltaPartialTick(true);
        this.effectStrength = Mth.lerp(delta, oldStrength, effectStrength) / total;
        return this;
    }

    public CodexOutlineRenderer setEffectStrength(float effectStrength) {
        this.effectStrength = effectStrength;
        return this;
    }

    public void renderOutline(PoseStack poseStack) {
        if (effectStrength <= 0 || effectStrength > 1) {
            return;
        }
        float distortionIntensity = 5f + 35f * effectStrength;
        RenderSystem.depthMask(true);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        float darknessAlpha = Math.min(effectStrength * 2.5f, 1f);
        renderOutline(poseStack, outlineTexture, distortionIntensity, darknessAlpha, i -> Color.BLACK);

        if (effectStrength >= 0.5f) {
            float glowAlpha = (effectStrength - 0.5f) * 2f;
            RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
            renderOutline(poseStack, glowTexture, distortionIntensity, glowAlpha, this::getSpiritColor);
        }

        RenderSystem.defaultBlendFunc();
        RenderSystem.disableBlend();
    }

    /**
     * @param distortion Controls how much wobbliness the shader used is to apply. Lower values result in more distortion
     * @param glow Controls the range and opacity of applied light
     */
    protected void renderOutline(PoseStack poseStack, ResourceLocation texture, float distortion, float glow, Int2ObjectFunction<Color> colorSupplier) {
        var minecraft = Minecraft.getInstance();
        float delta = minecraft.getTimer().getGameTimeDeltaPartialTick(true);
        var light = LodestoneShaders.RADIAL_DISTORTED_SCREEN_LIGHT.getShaderInstance();
        light.safeGetUniform("YFrequency").set(24f);
        light.safeGetUniform("XFrequency").set(32f);
        light.safeGetUniform("Speed").set(2000f);
        light.safeGetUniform("Intensity").set(distortion);
        light.safeGetUniform("LumiTransparency").set(1f);
        light.safeGetUniform("Width").set(80f);
        light.safeGetUniform("Height").set(80f);
        var builder = VFXBuilders.createScreen()
                .setTexture(texture)
                .setPositionWithWidth(left, top, width, height)
                .setShader(light);
        float offset = texture.hashCode() % 3600;
        float time = (minecraft.level.getGameTime() + delta) * 0.4f + offset;
        for (int i = 0; i < 8; i++) {
            int angle = (int) ((time * 2 + i * 90) % 720);
            float glowAlpha = 1 - Mth.abs(1 - (angle / 180f));
            if (angle >= 360) {
                continue;
            }
            light.safeGetUniform("Angle").set(angle);
            builder.setColor(colorSupplier.get(i));
            for (int j = 0; j < 2; j++) {
                float range = 120f * (j+1) * glow;
                light.safeGetUniform("LightAngleRange").set(range);
                builder.setAlpha(glowAlpha * glow).blit(poseStack);
            }
        }
        light.setUniformDefaults();
    }

    public Color getSpiritColor(int index) {
        var spirits = new SpiritArcanaType[] {
                MalumSpiritTypes.SACRED_SPIRIT.get(),
                MalumSpiritTypes.AERIAL_SPIRIT.get(),
                MalumSpiritTypes.WICKED_SPIRIT.get(),
                MalumSpiritTypes.AQUEOUS_SPIRIT.get(),
                MalumSpiritTypes.ARCANE_SPIRIT.get(),
                MalumSpiritTypes.EARTHEN_SPIRIT.get(),
                MalumSpiritTypes.ELDRITCH_SPIRIT.get(),
                MalumSpiritTypes.INFERNAL_SPIRIT.get()
        };
        return spirits[index].getPrimaryColor();
    }
}
