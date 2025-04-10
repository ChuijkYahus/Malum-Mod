package com.sammy.malum.core.handlers.hiding;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.flag.FeatureFlagRegistry;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.*;
import java.util.function.BooleanSupplier;

public class HiddenTagHandler {
	private static final Map<TagKey<Item>, BooleanSupplier> ITEMS_TO_HIDE = new HashMap<>();
	private static final HashMap<UUID, Runnable> INVOKED_WHEN_CONDITIONS_CHANGE = new HashMap<>();

	public static void hideTagWhen(TagKey<Item> item, BooleanSupplier condition) {
		ITEMS_TO_HIDE.put(item, condition);
	}

	public static UUID registerHiddenItemListener(Runnable runnable) {
		runnable.run();
		UUID uuid = UUID.randomUUID();
		INVOKED_WHEN_CONDITIONS_CHANGE.put(uuid, runnable);
		return uuid;
	}

	public static void removeListener(UUID listener) {
		INVOKED_WHEN_CONDITIONS_CHANGE.remove(listener);
	}

	public static void conditionsChanged() {
		INVOKED_WHEN_CONDITIONS_CHANGE.values().forEach(Runnable::run);
	}

	public static List<TagKey<Item>> tagsToHide() {
		List<TagKey<Item>> tags = new ArrayList<>();
		for (var entry : ITEMS_TO_HIDE.entrySet()) {
			if (entry.getValue().getAsBoolean())
				tags.add(entry.getKey());
		}
		return tags;
	}

	public static void hideItems(Collection<ItemStack> items) {
		Set<TagKey<Item>> disabledTags = new HashSet<>(HiddenTagHandler.tagsToHide());
		Iterator<ItemStack> iterator = items.iterator();
		while (iterator.hasNext()) {
			ItemStack entry = iterator.next();
			boolean remove = false;
			for (TagKey<Item> disabledTag : disabledTags) {
				if (entry.is(disabledTag)) {
					remove = true;
					break;
				}
			}
			if (remove) {
				iterator.remove();
			}
		}
	}
}
