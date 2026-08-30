package com.sammy.malum.common.item.curiosities.weapons;

import com.sammy.malum.registry.common.*;
import net.minecraft.resources.*;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import team.lodestar.lodestone.helpers.*;

import java.util.*;

public interface ICustomMeleeDamageTypeItem {

    default boolean shouldPlaySweepingSound(Player player, ItemStack weapon) {
        return false;
    }

    default boolean shouldDisplaySweepingParticle(Player player, ItemStack weapon) {
        return false;
    }

    ResourceKey<DamageType> getDirectDamageType(Player player, ItemStack weapon);

    ResourceKey<DamageType> getSweepingDamageType(Player player, ItemStack weapon);

    default DamageSource replaceDirectDamage(Player player, ItemStack weapon) {
        return replaceDamageSource(player, weapon, getDirectDamageType(player, weapon));
    }

    default DamageSource replaceSweepingDamage(Player player, ItemStack weapon) {
        return replaceDamageSource(player, weapon, getSweepingDamageType(player, weapon));
    }

    default DamageSource replaceDamageSource(Player player, ItemStack weapon, ResourceKey<DamageType> damageType) {
        return DamageTypeHelper.create(player.level(), damageType, player);
    }
}
