//package com.sammy.malum.compat.kubejs.match;
//
//import com.sammy.malum.common.item.spirit.*;
//import com.sammy.malum.core.systems.recipe.*;
//import dev.latvian.mods.kubejs.recipe.match.*;
//import dev.latvian.mods.rhino.*;
//import net.minecraft.world.item.*;
//import net.minecraft.world.item.crafting.*;
//import net.minecraft.world.level.*;
//
//public interface SpiritMatch extends ReplacementMatch {
//
//	boolean matches(Context cx, SpiritIngredient in, boolean exact);
//
//	default boolean matches(Context cx, ItemLike itemLike, boolean exact) {
//		var item = itemLike.asItem();
//		return item instanceof SpiritShardItem spiritShardItem && matches(cx, spiritShardItem, exact);
//	}
//
//	default boolean matchesAny(Context cx, Iterable<ItemLike> itemLikes, boolean exact) {
//		for (var item : itemLikes) {
//			if (matches(cx, item, exact)) {
//				return true;
//			}
//		}
//		return false;
//	}
//}