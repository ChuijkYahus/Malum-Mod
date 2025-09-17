package com.sammy.malum.common.item.curiosities.weapons.scythe;

import com.mojang.datafixers.util.*;
import com.sammy.malum.common.entity.scythe.*;
import com.sammy.malum.common.item.*;
import com.sammy.malum.core.handlers.enchantment.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.enchantment.*;
import com.sammy.malum.registry.common.item.*;

import net.minecraft.core.*;
import net.minecraft.server.level.*;
import net.minecraft.sounds.*;
import net.minecraft.util.*;
import net.minecraft.world.*;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.*;
import net.minecraft.world.level.*;
import net.neoforged.neoforge.event.entity.living.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.registry.common.*;
import team.lodestar.lodestone.systems.item.*;

public class MalumScytheItem extends LodestoneCombatItem implements IMalumEventResponder {

    public MalumScytheItem(Tier tier, float attackDamage, float attackSpeed, LodestoneItemProperties properties) {
        super(tier, attackDamage + 3, attackSpeed - 3.2f, properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        var stack = player.getItemInHand(hand);
        if (EnchantmentKeys.getEnchantmentLevel(level, EnchantmentKeys.REBOUND, stack) > 0) {
            ReboundHandler.throwScythe(level, player, hand, stack);
            return InteractionResultHolder.success(stack);
        }
        if (EnchantmentKeys.getEnchantmentLevel(level, EnchantmentKeys.ASCENSION, stack) > 0) {
            AscensionHandler.triggerAscension(level, player, hand, stack);
            return InteractionResultHolder.success(stack);
        }
        return super.use(level, player, hand);
    }

    @Override
    public void outgoingDamageEvent(LivingDamageEvent.Pre event, LivingEntity attacker, LivingEntity target, ItemStack stack) {
        var level = attacker.level();
        if (!(level instanceof ServerLevel serverLevel)) {
            return;
        }
        if (!event.getSource().is(MalumDamageTypes.SCYTHE_MELEE)) {
            return;
        }
        var particle = MalumParticleEffectTypes.SCYTHE_SLASH.createEffect()
                .originatesFrom(attacker)
                .targets(target)
                .color(stack.getItem())
                .upwardOffset(-0.4f)
                .forwardOffset(0.8f);
        if (!canSweep(attacker)) {
            SoundHelper.playSound(attacker, getScytheSound(false).value(), 1, 0.75f);
            particle.verticalSlashRotation().horizontalOffset(0.6f).spawn(serverLevel);
            return;
        }
        SoundHelper.playSound(attacker, getScytheSound(true).value(), 1, 1);
        particle.mirroredRandomly(attacker.getRandom()).spawn(serverLevel);

        trySweep(attacker, target, event.getNewDamage());

    }
    public Holder<SoundEvent> getScytheSound(boolean canSweep) {
        return canSweep ? MalumSoundEvents.SCYTHE_SWEEP : MalumSoundEvents.SCYTHE_CUT;
    }

    @Override
    public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
        if (enchantment.is(Enchantments.SWEEPING_EDGE)) {
            return true;
        }
        return super.supportsEnchantment(stack, enchantment);
    }

    public static void trySweep(LivingEntity attacker, LivingEntity target, float baseDamage) {
        var level = attacker.level();
        var sweeping = attacker.getAttribute(Attributes.SWEEPING_DAMAGE_RATIO);
        if (sweeping != null) {
            //TODO: Scythes can't get sweeping edge, but they can still benefit from the sweeping here
            float sweepingRatio = (float) sweeping.getValue();
            float damage = baseDamage * (0.5f + sweepingRatio * 0.33f);
            float radius = 1 + sweepingRatio * 0.25f;
            level.getEntities(attacker, target.getBoundingBox().inflate(radius)).forEach(e -> {
                if (e instanceof LivingEntity sweepTarget) {
                    if (sweepTarget.isAlive() && sweepTarget != target) {
                        sweepTarget.hurt((DamageTypeHelper.create(level, MalumDamageTypes.SCYTHE_SWEEP, attacker)), damage);
                        sweepTarget.knockback(0.4F,
                                Mth.sin(attacker.getYRot() * ((float) Math.PI / 180F)),
                                (-Mth.cos(attacker.getYRot() * ((float) Math.PI / 180F))));
                    }
                }
            });
        }
    }

    public static ScytheDamage getScytheDamage(DamageSource source, LivingEntity attacker) {
        float physicalDamage;
        float magicDamage;
        boolean isBoomerang = false;
        if (source.getDirectEntity() instanceof ScytheBoomerangEntity scytheBoomerang) {
            physicalDamage = scytheBoomerang.damage;
            magicDamage = scytheBoomerang.magicDamage;
            isBoomerang = true;
        } else {
            physicalDamage = (float) (attacker.getAttribute(Attributes.ATTACK_DAMAGE).getValue());
            magicDamage = (float) (attacker.getAttribute(LodestoneAttributes.MAGIC_DAMAGE).getValue());
        }
        return new ScytheDamage(physicalDamage, magicDamage, isBoomerang);
    }

    public static boolean canSweep(LivingEntity attacker) {
        return !isEnhanced(attacker) && !CurioHelper.hasCurioEquipped(attacker, MalumItems.NECKLACE_OF_THE_HIDDEN_BLADE.get());
    }

    public static boolean isEnhanced(LivingEntity attacker) {
        return CurioHelper.hasCurioEquipped(attacker, MalumItems.NECKLACE_OF_THE_NARROW_EDGE.get());
    }

    public static DamageSource replaceDamageSource(Player player, DamageSource source) {
        if (player.getMainHandItem().is(MalumTags.ItemTags.SCYTHES)) {
            return DamageTypeHelper.create(player.level(), MalumDamageTypes.SCYTHE_MELEE, player);
        }
        return source;
    }

    public record ScytheDamage(float physicalDamage, float magicDamage, boolean isBoomerang) {
    }
}