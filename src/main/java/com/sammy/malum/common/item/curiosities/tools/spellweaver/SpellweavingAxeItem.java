package com.sammy.malum.common.item.curiosities.tools.spellweaver;

import com.sammy.malum.common.item.spirit.*;
import com.sammy.malum.core.systems.spirit.SpiritLike;
import com.sammy.malum.registry.common.magic.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.*;
import net.minecraft.world.level.*;
import team.lodestar.lodestone.modules.toolkit.item.*;
import team.lodestar.wayward_attributes.util.MagicAxeItem;

public class SpellweavingAxeItem extends MagicAxeItem implements ISpiritAffiliatedItem, ISpellweavingTool {

    public SpellweavingAxeItem(Tier tier, float attackDamage, float attackSpeed, float magicDamage, LodestoneItemProperties properties) {
        super(tier, attackDamage, attackSpeed, magicDamage, properties.mergeAttributes(
                ItemAttributeModifiers.builder()
                        .add(Attributes.BLOCK_INTERACTION_RANGE, new AttributeModifier(SpellweavingPickaxeItem.BASE_INTERACTION_RANGE, 1.5f, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                        .build()));
    }

    @Override
    public SpiritLike getDefiningSpiritType() {
        return MalumSpiritTypes.AQUEOUS_SPIRIT;
    }

    @Override
    public Mode getMode() {
        return Mode.FURTHEST;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        if (SpellweavingPickaxeItem.toggleState(player, usedHand)) {
            return InteractionResultHolder.success(player.getItemInHand(usedHand));
        }
        return super.use(level, player, usedHand);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        SpellweavingPickaxeItem.inventoryTick(pStack, pEntity, pIsSelected);
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
    }
}