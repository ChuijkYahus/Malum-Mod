package com.sammy.malum.common.item.curiosities.weapons.scythe;

import com.sammy.malum.common.item.spirit.*;
import com.sammy.malum.core.systems.spirit.SpiritLike;
import com.sammy.malum.registry.common.magic.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.*;
import team.lodestar.lodestone.registry.common.*;
import team.lodestar.lodestone.modules.toolkit.item.*;
import team.lodestar.wayward_attributes.core.registry.WaywardAttributeTypes;


public class MagicScytheItem extends MalumScytheItem implements ISpiritAffiliatedItem {

    public MagicScytheItem(Tier tier, float attackDamage, float attackSpeed, float magicDamage, LodestoneItemProperties properties) {
        super(tier, attackDamage, attackSpeed, properties.mergeAttributes(
                ItemAttributeModifiers.builder()
                        .add(WaywardAttributeTypes.MAGIC_DAMAGE, new AttributeModifier(WaywardAttributeTypes.BASE_MAGIC_DAMAGE, magicDamage, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                        .build()));
    }

    @Override
    public SpiritLike getDefiningSpiritType() {
        return MalumSpiritTypes.WICKED_SPIRIT;
    }
}
