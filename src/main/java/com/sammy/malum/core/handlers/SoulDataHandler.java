package com.sammy.malum.core.handlers;

import com.sammy.malum.common.entity.scythe.*;
import com.sammy.malum.common.item.curiosities.weapons.scythe.*;
import com.sammy.malum.common.item.curiosities.weapons.staff.*;
import com.sammy.malum.compat.tetra.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.event.entity.*;
import net.neoforged.neoforge.event.entity.living.*;
import net.neoforged.neoforge.event.tick.*;

import java.util.function.*;

public class SoulDataHandler {

    public static void markAsSpawnerSpawned(MobSpawnEvent.PositionCheck event) {
        if (event.getSpawner() != null) {
            event.getEntity().getData(MalumAttachmentTypes.LIVING_SOUL_INFO).setSpawnerSpawned(true);
        }
    }

    public static void syncData(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof Mob mob) {
            mob.getData(MalumAttachmentTypes.LIVING_SOUL_INFO).updateSoullessBehavior(mob);
        }
    }

    public static void preventTargeting(LivingChangeTargetEvent event) {
        if (event.getEntity() instanceof Mob mob) {
            mob.getData(MalumAttachmentTypes.LIVING_SOUL_INFO).updateSoullessTargeting(event);
        }
    }

    public static void entityTick(EntityTickEvent.Pre event) {
        if (event.getEntity() instanceof LivingEntity living) {
            living.getData(MalumAttachmentTypes.LIVING_SOUL_INFO).tickDuration();
        }
    }

    public static void exposeSoul(LivingDamageEvent.Post event) {
        if (event.getOriginalDamage() <= 0) {
            return;
        }
        var target = event.getEntity();
        var source = event.getSource();
        var data = target.getData(MalumAttachmentTypes.LIVING_SOUL_INFO);
        if (source.is(MalumTags.DamageTypeTags.SOUL_SHATTER_DAMAGE)) {
            data.setExposed();
            return;
        }

        var directEntity = source.getDirectEntity();
        if (directEntity != null) {
            var projectileData = directEntity.getData(MalumAttachmentTypes.PROJECTILE_SOUL_INFO);
            if (projectileData.dealsSoulDamage()) {
                data.setExposed();
                return;
            }
        }
        if (source.getEntity() instanceof LivingEntity attacker) {
            ItemStack stack = getSoulHunterWeapon(source, attacker);
            if (stack.is(MalumTags.ItemTags.SOUL_SHATTER_CAPABLE_WEAPONS) || TetraCompat.hasSoulStrikeModifier(stack)) {
                data.setExposed();
            }
        }
    }

    //TODO: move these guys
    public static ItemStack getStaffWeapon(DamageSource source, LivingEntity attacker) {
        return getSoulHunterWeapon(source, attacker, s -> s.getItem() instanceof AbstractStaffItem);
    }

    public static ItemStack getScytheWeapon(DamageSource source, LivingEntity attacker) {
        return getSoulHunterWeapon(source, attacker, s -> s.getItem() instanceof MalumScytheItem);
    }

    public static ItemStack getSoulHunterWeapon(DamageSource source, LivingEntity attacker, Predicate<ItemStack> predicate) {
        var soulHunterWeapon = getSoulHunterWeapon(source, attacker);
        return predicate.test(soulHunterWeapon) ? soulHunterWeapon : ItemStack.EMPTY;
    }

    public static ItemStack getSoulHunterWeapon(DamageSource source, LivingEntity attacker) {
        if (source.getDirectEntity() instanceof ScytheBoomerangEntity scytheBoomerang) {
            return scytheBoomerang.getItem();
        }
        return attacker.getMainHandItem();
    }
}
