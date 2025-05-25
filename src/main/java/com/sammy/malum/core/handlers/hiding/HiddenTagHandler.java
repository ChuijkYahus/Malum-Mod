package com.sammy.malum.core.handlers.hiding;

import com.sammy.malum.common.item.*;
import com.sammy.malum.core.handlers.*;
import com.sammy.malum.registry.common.tag.*;
import net.minecraft.tags.TagKey;
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

	public static void hideItems(Collection<ItemStack> items) {
		items.removeIf(HiddenTagHandler::isHiddenItem);
	}

	public static boolean isHiddenItem(ItemStack stack) {
		if (stack.getItem() instanceof GeasItem) {
			if (ITEMS_TO_HIDE.get(ItemTagRegistry.HIDDEN_UNTIL_BLACK_CRYSTAL).getAsBoolean()) {
				return GeasEffectHandler.getStoredGeasEffect(stack).map(g -> g.geasEffectType().is(GeasTagRegistry.HIDDEN_UNTIL_BLACK_CRYSTAL)).orElse(false);
			}
		}
		for (TagKey<Item> tag : getTagsToHide()) {
			if (stack.is(tag)) {
				return true;
			}
		}
		return false;
	}

	public static List<TagKey<Item>> getTagsToHide() {
		List<TagKey<Item>> tags = new ArrayList<>();
		for (var entry : ITEMS_TO_HIDE.entrySet()) {
			if (entry.getValue().getAsBoolean()) {
				tags.add(entry.getKey());
			}
		}
		return tags;
	}
}
