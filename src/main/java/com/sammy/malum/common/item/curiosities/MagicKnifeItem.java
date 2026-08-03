package com.sammy.malum.common.item.curiosities;

import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import team.lodestar.lodestone.registry.common.*;
import team.lodestar.lodestone.modules.toolkit.item.*;
import team.lodestar.wayward_attributes.core.registry.WaywardAttributeTypes;


public class MagicKnifeItem extends MalumKnifeItem {
    public MagicKnifeItem(Tier tier, float attackDamage, float attackSpeed, float magicDamage, LodestoneItemProperties properties) {
        super(tier, attackDamage, attackSpeed, properties.mergeAttributes(
                ItemAttributeModifiers.builder()
                        .add(WaywardAttributeTypes.MAGIC_DAMAGE, new AttributeModifier(WaywardAttributeTypes.BASE_MAGIC_DAMAGE, magicDamage, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                        .build()));
    }
}