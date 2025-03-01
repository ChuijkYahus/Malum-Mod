package com.sammy.malum.client.extensions;

import com.sammy.malum.client.renderer.item.GeasItemRenderer;
import com.sammy.malum.client.renderer.item.SpiritJarItemRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

public class GeasClientItemExtension implements IClientItemExtensions {

	private BlockEntityWithoutLevelRenderer renderer;

	@Override
	public BlockEntityWithoutLevelRenderer getCustomRenderer() {
		if (renderer == null) {
			renderer = new GeasItemRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(),
				Minecraft.getInstance().getEntityModels());
		}
		return renderer;
	}
}
