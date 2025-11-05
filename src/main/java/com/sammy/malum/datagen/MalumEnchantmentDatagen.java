package com.sammy.malum.datagen;

import com.sammy.malum.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.enchantment.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.data.worldgen.*;
import net.minecraft.resources.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.item.enchantment.*;
import net.minecraft.world.item.enchantment.effects.*;
import team.lodestar.lodestone.registry.common.*;

import static net.minecraft.world.item.enchantment.Enchantment.enchantment;

public class MalumEnchantmentDatagen {

    public static void bootstrap(BootstrapContext<Enchantment> context) {
        HolderGetter<net.minecraft.world.item.Item> items = context.lookup(Registries.ITEM);
        HolderGetter<Enchantment> enchantments = context.lookup(Registries.ENCHANTMENT);

        register(context, EnchantmentKeys.ANIMATED, enchantment(
                Enchantment.definition(items.getOrThrow(MalumTags.ItemTags.ANIMATED_ENCHANTABLE), 2, 2,
                        Enchantment.dynamicCost(10, 10),
                        Enchantment.dynamicCost(20, 10), 3, EquipmentSlotGroup.MAINHAND))

                .withEffect(EnchantmentEffectComponents.ATTRIBUTES,
                        new EnchantmentAttributeEffect(
                                MalumMod.malumPath("enchantment.attack_speed"),
                                Attributes.ATTACK_SPEED,
                                LevelBasedValue.perLevel(0.15F),
                                AttributeModifier.Operation.ADD_VALUE
                        )
                )
                .exclusiveWith(HolderSet.direct(enchantments.getOrThrow(EnchantmentKeys.HAUNTED)))
        );
        register(context, EnchantmentKeys.HAUNTED, enchantment(
                Enchantment.definition(items.getOrThrow(MalumTags.ItemTags.HAUNTED_ENCHANTABLE), 2, 2,
                        Enchantment.dynamicCost(10, 10),
                        Enchantment.dynamicCost(20, 10), 3, EquipmentSlotGroup.MAINHAND))

                .withEffect(EnchantmentEffectComponents.ATTRIBUTES,
                        new EnchantmentAttributeEffect(
                                MalumMod.malumPath("enchantment.magic_damage"),
                                LodestoneAttributes.MAGIC_DAMAGE,
                                LevelBasedValue.perLevel(1F),
                                AttributeModifier.Operation.ADD_VALUE
                        )
                )
                .exclusiveWith(HolderSet.direct(enchantments.getOrThrow(EnchantmentKeys.ANIMATED)))
        );


        register(context, EnchantmentKeys.REBOUND, enchantment(
                Enchantment.definition(items.getOrThrow(MalumTags.ItemTags.REBOUND_ENCHANTABLE), 1, 3,
                        Enchantment.dynamicCost(10, 10),
                        Enchantment.dynamicCost(30, 10), 8, EquipmentSlotGroup.MAINHAND))

                .exclusiveWith(HolderSet.direct(enchantments.getOrThrow(EnchantmentKeys.ASCENSION)))
        );

        register(context, EnchantmentKeys.ASCENSION, enchantment(
                Enchantment.definition(items.getOrThrow(MalumTags.ItemTags.ASCENSION_ENCHANTABLE), 1, 3,
                        Enchantment.dynamicCost(10, 10),
                        Enchantment.dynamicCost(30, 10), 8, EquipmentSlotGroup.MAINHAND))

                .exclusiveWith(HolderSet.direct(enchantments.getOrThrow(EnchantmentKeys.REBOUND)))
        );

        register(context, EnchantmentKeys.WEAVERS_PROPAGATION, enchantment(
                Enchantment.definition(items.getOrThrow(MalumTags.ItemTags.WEAVERS_PROPAGATION_ENCHANTABLE), 1, 4,
                        Enchantment.dynamicCost(10, 10),
                        Enchantment.dynamicCost(20, 10), 8, EquipmentSlotGroup.MAINHAND))
                .withEffect(ModEnchantmentComponents.LOCUS_COUNT.get(),
                        new AddValue(LevelBasedValue.perLevel(1f)))
                .exclusiveWith(HolderSet.direct(enchantments.getOrThrow(EnchantmentKeys.WEAVERS_HASTE)))
        );
        register(context, EnchantmentKeys.WEAVERS_HASTE, enchantment(
                Enchantment.definition(items.getOrThrow(MalumTags.ItemTags.WEAVERS_HASTE_ENCHANTABLE), 1, 4,
                        Enchantment.dynamicCost(10, 10),
                        Enchantment.dynamicCost(20, 10), 8, EquipmentSlotGroup.MAINHAND))
                .withEffect(ModEnchantmentComponents.LOCUS_SPEED.get(),
                        new AddValue(LevelBasedValue.perLevel(0.5f)))
                .exclusiveWith(HolderSet.direct(enchantments.getOrThrow(EnchantmentKeys.WEAVERS_PROPAGATION)))
        );

        register(context, EnchantmentKeys.REPLENISHING, enchantment(
                Enchantment.definition(items.getOrThrow(MalumTags.ItemTags.REPLENISHING_ENCHANTABLE), 1, 2,
                        Enchantment.dynamicCost(10, 10),
                        Enchantment.dynamicCost(20, 10), 8, EquipmentSlotGroup.MAINHAND))

                .exclusiveWith(HolderSet.direct(enchantments.getOrThrow(EnchantmentKeys.CAPACITOR)))
        );

        register(context, EnchantmentKeys.CAPACITOR, enchantment(
                Enchantment.definition(items.getOrThrow(MalumTags.ItemTags.CAPACITOR_ENCHANTABLE), 1, 2,
                        Enchantment.dynamicCost(10, 10),
                        Enchantment.dynamicCost(20, 10), 8, EquipmentSlotGroup.MAINHAND))

                .withEffect(EnchantmentEffectComponents.ATTRIBUTES,
                        new EnchantmentAttributeEffect(
                                MalumMod.malumPath("enchantment.charge_capacity"),
                                MalumAttributes.CHARGE_CAPACITY,
                                LevelBasedValue.perLevel(1F),
                                AttributeModifier.Operation.ADD_VALUE
                        )
                )
                .exclusiveWith(HolderSet.direct(enchantments.getOrThrow(EnchantmentKeys.REPLENISHING)))

        );

        register(context, EnchantmentKeys.SPIRIT_PLUNDER, enchantment(
                Enchantment.definition(items.getOrThrow(MalumTags.ItemTags.SPIRIT_PLUNDER_ENCHANTABLE), 1, 2,
                        Enchantment.dynamicCost(20, 10),
                        Enchantment.dynamicCost(30, 10), 5, EquipmentSlotGroup.MAINHAND))

                .withEffect(EnchantmentEffectComponents.ATTRIBUTES,
                        new EnchantmentAttributeEffect(
                                MalumMod.malumPath("enchantment.spirit_spoils"),
                                MalumAttributes.SPIRIT_SPOILS,
                                LevelBasedValue.perLevel(1),
                                AttributeModifier.Operation.ADD_VALUE
                        )
                )
        );
    }

    private static void register(BootstrapContext<Enchantment> context, ResourceKey<Enchantment> key, Enchantment.Builder builder) {
        context.register(key, builder.build(key.location()));
    }
}