package com.sammy.malum.common.item.curiosities.weapons;

import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import team.lodestar.lodestone.modules.toolkit.item.LodestoneItemProperties;
import team.lodestar.lodestone.modules.toolkit.item.tools.LodestoneSwordItem;

public class ShapedSlabSwordItem extends LodestoneSwordItem {

    public ShapedSlabSwordItem(Tier tier, float attackDamage, float attackSpeed, LodestoneItemProperties properties) {
        super(tier, attackDamage, attackSpeed, properties);
    }

    @Override
    public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
        if (enchantment.is(Enchantments.BREACH)) {
            return true;
        }
        return super.supportsEnchantment(stack, enchantment);
    }
}