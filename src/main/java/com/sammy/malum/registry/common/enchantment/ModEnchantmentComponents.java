package com.sammy.malum.registry.common.enchantment;

import com.sammy.malum.*;
import net.minecraft.core.component.*;
import net.minecraft.core.registries.*;
import net.minecraft.world.item.enchantment.*;
import net.minecraft.world.item.enchantment.effects.*;
import net.neoforged.neoforge.registries.*;
import team.lodestar.lodestone.registry.common.*;

import java.util.*;
import java.util.function.*;

import static team.lodestar.lodestone.registry.common.LodestoneEnchantmentComponents.valueEffect;

public class ModEnchantmentComponents {
    public static final DeferredRegister<DataComponentType<?>> ENCHANTMENT_COMPONENTS = DeferredRegister.create(BuiltInRegistries.ENCHANTMENT_EFFECT_COMPONENT_TYPE, MalumMod.MALUM);

    public static final Supplier<DataComponentType<List<ConditionalEffect<EnchantmentValueEffect>>>> LOCUS_COUNT =
            valueEffect("locus_count");
    public static final Supplier<DataComponentType<List<ConditionalEffect<EnchantmentValueEffect>>>> LOCUS_SPEED =
            valueEffect("locus_speed");


    private static Supplier<DataComponentType<List<ConditionalEffect<EnchantmentValueEffect>>>> valueEffect(String name) {
        return LodestoneEnchantmentComponents.valueEffect(ENCHANTMENT_COMPONENTS, name);
    }

    private static Supplier<DataComponentType<List<ConditionalEffect<EnchantmentEntityEffect>>>> entityEffect(String name) {
        return LodestoneEnchantmentComponents.entityEffect(ENCHANTMENT_COMPONENTS, name);
    }

    private static Supplier<DataComponentType<List<TargetedConditionalEffect<EnchantmentEntityEffect>>>> targetedEffect(String name) {
        return LodestoneEnchantmentComponents.targetedEffect(ENCHANTMENT_COMPONENTS, name);
    }
}