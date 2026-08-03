package com.sammy.malum.registry.common.enchantment;

import com.sammy.malum.*;
import net.minecraft.core.component.*;
import net.minecraft.core.registries.*;
import net.minecraft.world.item.enchantment.*;
import net.minecraft.world.item.enchantment.effects.*;
import net.neoforged.neoforge.registries.*;
import team.lodestar.lodestone.registry.common.*;
import team.lodestar.lodestone.registry.common.LodestoneEnchantmentComponents.EntityEffectDataComponent;
import team.lodestar.lodestone.registry.common.LodestoneEnchantmentComponents.TargetedEntityEffectDataComponent;
import team.lodestar.lodestone.registry.common.LodestoneEnchantmentComponents.ValueEffectDataComponent;

import java.util.*;
import java.util.function.*;

public class ModEnchantmentComponents {
    public static final DeferredRegister<DataComponentType<?>> ENCHANTMENT_COMPONENTS = DeferredRegister.create(BuiltInRegistries.ENCHANTMENT_EFFECT_COMPONENT_TYPE, MalumMod.MALUM);

    public static final ValueEffectDataComponent LOCUS_COUNT = valueEffect("locus_count");
    public static final ValueEffectDataComponent LOCUS_SPEED = valueEffect("locus_speed");


    private static ValueEffectDataComponent valueEffect(String name) {
        return LodestoneEnchantmentComponents.valueEffect(ENCHANTMENT_COMPONENTS, name);
    }

    private static EntityEffectDataComponent entityEffect(String name) {
        return LodestoneEnchantmentComponents.entityEffect(ENCHANTMENT_COMPONENTS, name);
    }

    private static TargetedEntityEffectDataComponent targetedEffect(String name) {
        return LodestoneEnchantmentComponents.targetedEffect(ENCHANTMENT_COMPONENTS, name);
    }
}