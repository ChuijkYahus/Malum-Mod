package com.sammy.malum.registry.common.enchantment;

import com.sammy.malum.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.resources.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.*;
import net.minecraft.world.level.*;

public class EnchantmentKeys {

    public static final ResourceKey<Enchantment> ANIMATED = register("animated");
    public static final ResourceKey<Enchantment> HAUNTED = register("haunted");
    public static final ResourceKey<Enchantment> SPIRIT_PLUNDER = register("spirit_plunder");

    public static final ResourceKey<Enchantment> REBOUND = register("rebound");
    public static final ResourceKey<Enchantment> ASCENSION = register("ascension");

    public static final ResourceKey<Enchantment> WEAVERS_PROPAGATION = register("weavers_propagation");
    public static final ResourceKey<Enchantment> WEAVERS_HASTE = register("weavers_haste");

    public static final ResourceKey<Enchantment> REPLENISHING = register("replenishing");
    public static final ResourceKey<Enchantment> CAPACITOR = register("capacitor");

    public static ResourceKey<Enchantment> register(String id) {
        return ResourceKey.create(Registries.ENCHANTMENT, MalumMod.malumPath(id));
    }
    //TODO: move this to lodestone
    public static int getEnchantmentLevel(Level level, ResourceKey<Enchantment> key, ItemStack stack) {
        HolderGetter<Enchantment> enchantmentLookup = level.registryAccess().asGetterLookup().lookupOrThrow(Registries.ENCHANTMENT);
        return stack.getEnchantmentLevel(enchantmentLookup.getOrThrow(key));
    }
}
