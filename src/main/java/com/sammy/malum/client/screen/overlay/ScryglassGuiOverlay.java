package com.sammy.malum.client.screen.overlay;

import com.mojang.blaze3d.systems.RenderSystem;
import com.sammy.malum.MalumMod;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import team.lodestar.lodestone.systems.rendering.builder.VFXBuilders;

public class ScryglassGuiOverlay implements LayeredDraw.Layer {

	private static final ResourceLocation VISIONARY_SCRYGLASS_SCOPE = MalumMod.malumPath("textures/misc/visionary_scryglass_scope.png");

	protected float scopeScale;

	@Override
	public void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
		var minecraft = Minecraft.getInstance();

		float f = deltaTracker.getGameTimeDeltaTicks();
		scopeScale = Mth.lerp(0.5F * f, scopeScale, 1.125F);
		if (minecraft.options.getCameraType().isFirstPerson()) {
			if (minecraft.player.isScoping()) {
				renderMonocleOverlay(guiGraphics, scopeScale);
			} else {
				scopeScale = 0.5F;
			}
		}
    }

	public static void renderMonocleOverlay(GuiGraphics guiGraphics, float scopeScale) {
		int height = guiGraphics.guiHeight();
		int width = guiGraphics.guiWidth();
		float smaller = Math.min(width, height);
		float relative = Math.min(width / smaller, height / smaller) * scopeScale;
		var poseStack = guiGraphics.pose();

		int size = Mth.floor(smaller * relative);
		int left = (width - size) / 2;
		int right = left + size;
		int top = (height - size) / 2;
		int bottom = top + size;

		RenderSystem.enableBlend();
		VFXBuilders.createScreen()
				.setPositionWithWidth(left, top, size, size)
				.setTexture(VISIONARY_SCRYGLASS_SCOPE)
				.setShader(GameRenderer.getPositionTexColorShader())
				.blit(poseStack);
		RenderSystem.disableBlend();

		var overlay = RenderType.guiOverlay();
		guiGraphics.fill(overlay, 0, bottom, width, height, -90, -16777216);
		guiGraphics.fill(overlay, 0, 0, width, top, -90, -16777216);
		guiGraphics.fill(overlay, 0, top, left, bottom, -90, -16777216);
		guiGraphics.fill(overlay, right, top, width, bottom, -90, -16777216);
	}
}
