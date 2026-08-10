package com.sammy.malum.core.handlers.enchantment;

import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.enchantment.*;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.*;
import team.lodestar.lodestone.registry.common.tag.*;
import team.lodestar.wayward_attributes.WaywardTags;

import static com.sammy.malum.registry.common.enchantment.EnchantmentKeys.getEnchantmentLevel;

public class ReplenishingHandler {

    public static void triggerReplenishing(DamageSource source, LivingEntity attacker, ItemStack stack) {
        if (!attacker.level().isClientSide) {
            if (source.is(WaywardTags.DamageTypeTags.CAN_TRIGGER_MAGIC_DAMAGE)) {
                int level = getEnchantmentLevel(attacker.level(), EnchantmentKeys.REPLENISHING, stack);
                if (level > 0) {
                    float chance = 0.4f * level;
                    while (chance > 0) {
                        if (chance >= 1 || attacker.getRandom().nextFloat() < chance) {
                            attacker.getData(MalumAttachmentTypes.STAFF_ABILITIES).reduceStaffChargeDebt(attacker);
                        }
                        chance--;
                    }
                }
            }
        }
    }
}
