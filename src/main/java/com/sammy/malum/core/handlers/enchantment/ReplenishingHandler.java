package com.sammy.malum.core.handlers.enchantment;

import com.sammy.malum.common.item.curiosities.weapons.staff.*;
import com.sammy.malum.common.packets.*;
import com.sammy.malum.compability.irons_spellbooks.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.item.*;
import net.minecraft.server.level.*;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.network.*;
import team.lodestar.lodestone.registry.common.tag.*;

import java.util.*;

import static com.sammy.malum.registry.common.item.EnchantmentRegistry.getEnchantmentLevel;

public class ReplenishingHandler {

    public static void triggerReplenishing(DamageSource source, LivingEntity attacker, ItemStack stack) {
        if (!attacker.level().isClientSide) {
            if (source.is(LodestoneDamageTypeTags.CAN_TRIGGER_MAGIC)) {
                int level = getEnchantmentLevel(attacker.level(), EnchantmentRegistry.REPLENISHING, stack);
                if (level > 0) {
                    float chance = 0.4f * level;
                    while (chance > 0) {
                        if (chance >= 1 || attacker.getRandom().nextFloat() < chance) {
                            attacker.getData(AttachmentTypeRegistry.STAFF_ABILITIES).reduceStaffChargeDebt(attacker);
                        }
                        chance--;
                    }
                }
            }
        }
    }
}
