package com.sammy.malum.registry.client;

import com.google.common.collect.Sets;
import com.sammy.malum.MalumMod;
import com.sammy.malum.client.VoidRevelationHandler;
import com.sammy.malum.core.handlers.hiding.HiddenTagHandler;
import com.sammy.malum.core.handlers.hiding.flags.FeatureFlagCacher;
import com.sammy.malum.core.handlers.hiding.flags.FeatureFlagExpandedUniverseSet;
import com.sammy.malum.registry.common.item.ItemTagRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.protocol.configuration.ClientboundUpdateEnabledFeaturesPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.*;
import net.neoforged.bus.api.*;
import net.neoforged.fml.common.*;
import net.neoforged.neoforge.event.*;

import java.util.List;

import static com.sammy.malum.client.VoidRevelationHandler.RevelationType.BLACK_CRYSTAL;
import static com.sammy.malum.client.VoidRevelationHandler.RevelationType.VOID_READER;

public class HiddenTagRegistry {

	public static void registerHiddenTags() {
		HiddenTagHandler.hideTagWhen(ItemTagRegistry.HIDDEN_ALWAYS, () -> true);
		HiddenTagHandler.hideTagWhen(ItemTagRegistry.HIDDEN_UNTIL_VOID, () -> !VoidRevelationHandler.hasSeenTheRevelation(VOID_READER));
		HiddenTagHandler.hideTagWhen(ItemTagRegistry.HIDDEN_UNTIL_BLACK_CRYSTAL, () -> !VoidRevelationHandler.hasSeenTheRevelation(BLACK_CRYSTAL));

		HiddenTagHandler.registerHiddenItemListener(HiddenTagRegistry::rebuildHidingTags);
	}

	public static void blankOutHidingTags() {
		Minecraft.getInstance().submit(HiddenTagRegistry::rebuildTags);
	}

	public static void rebuildHidingTags() {
		Minecraft.getInstance().submit(HiddenTagRegistry::rebuildTags);
	}

	private static void rebuildTags() {
		ClientPacketListener connection = Minecraft.getInstance().getConnection();
		if (connection != null) {
			var cachedFlags = ((FeatureFlagCacher) connection).malum$cachedFeatureFlags();
			if (cachedFlags != null)
				connection.send(new ClientboundUpdateEnabledFeaturesPacket(Sets.newHashSet(cachedFlags)));
		}
	}
}
