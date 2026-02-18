package com.sammy.malum.core.handlers.enchantment;

import com.sammy.malum.common.item.curiosities.curios.sets.scythe.*;
import com.sammy.malum.common.item.curiosities.weapons.scythe.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.enchantment.*;
import com.sammy.malum.registry.common.item.*;
import com.sammy.malum.registry.common.sound.*;
import com.sammy.malum.visual_effects.networked.*;
import net.minecraft.server.level.*;
import net.minecraft.stats.*;
import net.minecraft.util.*;
import net.minecraft.world.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.phys.*;
import net.neoforged.neoforge.common.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.registry.common.*;

import static com.sammy.malum.registry.common.enchantment.EnchantmentKeys.getEnchantmentLevel;

public class AscensionHandler {

    public static void triggerAscension(Level level, Player player, InteractionHand hand, ItemStack scythe) {
        boolean isUppercut = MalumScytheItem.isEnhanced(player);
        player.resetFallDistance();
        if (level.isClientSide()) {
            Vec3 motion = player.getDeltaMovement();
            player.setDeltaMovement(motion.x, player.getJumpPower() * 2f, motion.z);
            if (player.isSprinting()) {
                float f = player.getYRot() * 0.017453292F;
                float x = -Mth.sin(f);
                float z = Mth.cos(f);

                var newMotion = player.getDeltaMovement();
                if (isUppercut) {
                    newMotion = newMotion.subtract(x * 0.4f, 0, z * 0.4f);
                } else {
                    newMotion = newMotion.add(x * 0.75f, 0, z * 0.75f);
                }
                player.setDeltaMovement(newMotion);
            }
            player.hasImpulse = true;
            CommonHooks.onLivingJump(player);
        }
        if (level instanceof ServerLevel serverLevel) {
            dealAscensionDamage(serverLevel, player, isUppercut);
            addAscensionVisuals(serverLevel, player, scythe, isUppercut);
        }
        if (!player.isCreative()) {
            int enchantmentLevel = getEnchantmentLevel(level, EnchantmentKeys.ASCENSION, scythe);
            int cooldown = 200 - 40 * (enchantmentLevel - 1);
            if (cooldown > 0) {
                player.getCooldowns().addCooldown(scythe.getItem(), cooldown);
            }
        }
        player.swing(hand, false);
        player.awardStat(Stats.ITEM_USED.get(scythe.getItem()));
    }

    protected static void addAscensionVisuals(ServerLevel level, Player player, ItemStack scythe, boolean isUppercut) {
        var random = level.getRandom();
        var particlePosition = player.position().add(0, player.getBbHeight() * 0.75, 0);
        var particleDirection = player.getLookAngle().multiply(1, 0, 1);

        MalumNetworkedWeaponParticleEffectType.MalumWeaponParticleEffectBuilder<?> particle;
        if (isUppercut) {
            particle = MalumParticleEffectTypes.SCYTHE_ASCENSION_UPPERCUT.createEffect()
                    .verticalSlashRotation()
                    .mirroredRandomly(random)
                    .mirrored()
                    .forwardOffset(0.8f);
        }
        else {
            particle = MalumParticleEffectTypes.SCYTHE_ASCENSION_SPIN.createEffect(player)
                    .mirroredRandomly(random);
        }
        particle.color(scythe.getItem()).at(particlePosition).aimedAt(particleDirection).spawn(level);
    }

    protected static void dealAscensionDamage(ServerLevel level, Player player, boolean isUppercut) {
        boolean hasFunnyRing = CurioHelper.hasCurioEquipped(player, MalumItems.RING_OF_THE_RISING_EDGE.get());
        var random = level.getRandom();
        float baseDamage = (float) player.getAttributes().getValue(Attributes.ATTACK_DAMAGE);
        float magicDamage = (float) player.getAttributes().getValue(LodestoneAttributes.MAGIC_DAMAGE);
        var area = player.getBoundingBox().inflate(4f, 1f, 4f);
        var sound = MalumGearSoundEvents.SCYTHE_SWEEP.get();

        if (isUppercut) {
            baseDamage *= 1.4f;
            magicDamage *= 1.4f;
            area = area.move(player.getLookAngle().scale(2f)).inflate(-2f, 1f, -2f);
            sound = MalumGearSoundEvents.SCYTHE_CUT.get();
        }
        if (hasFunnyRing) {
            baseDamage *= 0.5f;
            magicDamage *= 0.5f;
        }

        boolean dealtDamage = false;
        var physicalDamageType = DamageTypeHelper.create(level, MalumDamageTypes.SCYTHE_ASCENSION, player);
        var magicDamageType = DamageTypeHelper.create(level, MalumDamageTypes.VOODOO, player);
        for (Entity target : level.getEntities(player, area, t -> ascensionCanHitEntity(player, t))) {
            target.invulnerableTime = 0;

            boolean success = target.hurt(physicalDamageType, baseDamage);
            if (success && target instanceof LivingEntity living) {
                if (magicDamage > 0) {
                    if (!living.isDeadOrDying()) {
                        living.invulnerableTime = 0;
                        living.hurt(magicDamageType, magicDamage);
                    }
                }
                SoundHelper.playSound(player, sound, 0.8f, RandomHelper.randomBetween(random, 0.75f, 1.25f));
                dealtDamage = true;
                if (hasFunnyRing) {
                    CurioRisingEdgeRing.launchEntity(player, living, isUppercut);
                }
            }
        }
        if (dealtDamage) {
            player.addEffect(new MobEffectInstance(MalumMobEffects.ASCENSION, 80, 0));
        }

        for (int i = 0; i < 3; i++) {
            SoundHelper.playSound(player, sound, 0.4f, RandomHelper.randomBetween(random, 1.25f, 1.75f));
        }
        SoundHelper.playSound(player, MalumGearSoundEvents.SCYTHE_ASCENSION.get(), 0.8f, RandomHelper.randomBetween(random, 1.25f, 1.5f));
    }

    protected static boolean ascensionCanHitEntity(Player attacker, Entity pTarget) {
        if (pTarget instanceof TamableAnimal tamableAnimal) {
            if (tamableAnimal.isTame()) {
                return false;
            }
        }
        if (!pTarget.canBeHitByProjectile()) {
            return false;
        } else {
            return pTarget != attacker && !attacker.isPassengerOfSameVehicle(pTarget);
        }
    }
}