package com.sammy.malum.registry.common.enchantment;

import com.sammy.malum.*;
import net.minecraft.core.component.*;
import net.minecraft.core.registries.*;
import net.minecraft.world.item.enchantment.*;
import net.minecraft.world.item.enchantment.effects.*;
import net.neoforged.neoforge.registries.*;
import team.lodestar.lodestone.registry.common.*;
import team.lodestar.lodestone.registry.common.LodestoneEnchantmentComponents.EntityEffectSupplier;
import team.lodestar.lodestone.registry.common.LodestoneEnchantmentComponents.TargetedEntityEffectSupplier;
import team.lodestar.lodestone.registry.common.LodestoneEnchantmentComponents.ValueEffectSupplier;

import java.util.*;
import java.util.function.*;

public class ModEnchantmentComponents {
    public static final DeferredRegister<DataComponentType<?>> ENCHANTMENT_COMPONENTS = DeferredRegister.create(BuiltInRegistries.ENCHANTMENT_EFFECT_COMPONENT_TYPE, MalumMod.MALUM);

    public static final ValueEffectSupplier LOCUS_COUNT = valueEffect("locus_count");
    public static final ValueEffectSupplier LOCUS_SPEED = valueEffect("locus_speed");


    private static ValueEffectSupplier valueEffect(String name) {
        return LodestoneEnchantmentComponents.valueEffect(ENCHANTMENT_COMPONENTS, name);
    }

    private static EntityEffectSupplier entityEffect(String name) {
        return LodestoneEnchantmentComponents.entityEffect(ENCHANTMENT_COMPONENTS, name);
    }

    private static TargetedEntityEffectSupplier targetedEffect(String name) {
        return LodestoneEnchantmentComponents.targetedEffect(ENCHANTMENT_COMPONENTS, name);
    }
}