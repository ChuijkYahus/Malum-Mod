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
                    float chance = (int) (0.25f * level);
                    if (attacker.getRandom().nextFloat() < chance) {
                        attacker.getData(AttachmentTypeRegistry.STAFF_ABILITIES).reduceStaffChargeDebt();
                    }
                }
            }
        }
    }

    public static void replenishStaffCooldown(AbstractStaffItem staff, Player player, int pLevel) {
        ItemCooldowns cooldowns = player.getCooldowns();
        int ratio = (int) (staff.getCooldownDuration(player.level(), player) * (0.25f * pLevel));
        cooldowns.tickCount += ratio;
        for (Map.Entry<Item, ItemCooldowns.CooldownInstance> itemCooldownInstanceEntry : cooldowns.cooldowns.entrySet()) {
            if (itemCooldownInstanceEntry.getKey().equals(staff)) {
                continue;
            }
            ItemCooldowns.CooldownInstance value = itemCooldownInstanceEntry.getValue();
            ItemCooldowns.CooldownInstance cooldownInstance = new ItemCooldowns.CooldownInstance(value.startTime + ratio, value.endTime + ratio);
            cooldowns.cooldowns.put(itemCooldownInstanceEntry.getKey(), cooldownInstance);
        }
    }
}
