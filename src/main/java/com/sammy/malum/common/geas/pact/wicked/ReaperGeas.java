package com.sammy.malum.common.geas.pact.wicked;

import com.sammy.malum.common.entity.scythe.*;
import com.sammy.malum.common.item.curiosities.weapons.scythe.*;
import com.sammy.malum.common.worldevent.*;
import com.sammy.malum.core.handlers.*;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.enchantment.*;
import com.sammy.malum.registry.common.magic.*;
import net.minecraft.network.chat.*;
import net.minecraft.server.level.*;
import net.minecraft.util.*;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.*;
import net.neoforged.neoforge.event.entity.living.*;
import team.lodestar.lodestone.handlers.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.registry.common.*;

import java.util.function.*;

import static net.minecraft.world.entity.EquipmentSlot.MAINHAND;

public class ReaperGeas extends GeasEffect {

    public ReaperGeas() {
        super(MalumGeasEffectTypes.PACT_OF_THE_REAPER.get());
    }

    @Override
    public void addTooltipComponents(LivingEntity entity, Consumer<Component> tooltipAcceptor, TooltipFlag tooltipFlag) {
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("scythe_combo"));
        tooltipAcceptor.accept(ComponentHelper.negativeGeasEffect("only_scythe"));
        super.addTooltipComponents(entity, tooltipAcceptor, tooltipFlag);
    }

    //TODO: This thing is rlly needlessly complicated
    @Override
    public void outgoingDamageEvent(LivingDamageEvent.Pre event, LivingEntity attacker, LivingEntity target, ItemStack stack) {
        if (attacker.level() instanceof ServerLevel level) {
            var source = event.getSource();
            var heldItem = attacker.getMainHandItem();
            if (!heldItem.isEmpty()) {
                if (source.is(DamageTypes.PLAYER_ATTACK) || source.is(MalumDamageTypes.TYRVING)) {
                    event.setNewDamage(event.getNewDamage() * 0.1f);
                    if (heldItem.isDamageableItem()) {
                        heldItem.hurtAndBreak(10, attacker, MAINHAND);
                    }
                    return;
                }
            }

            boolean canSweep = MalumScytheItem.canSweep(attacker);
            if (source.is(MalumDamageTypes.SCYTHE_COMBO)) {
                var scytheStack = SoulDataHandler.getScytheWeapon(source, attacker);
                var particle = MalumParticleEffectTypes.SCYTHE_SLASH.createEffect()
                        .originatesFrom(attacker)
                        .targets(target)
                        .tiedToTarget()
                        .forwardOffset(-2f)
                        .color(scytheStack.getItem())
                        .mirroredRandomly(attacker.getRandom());
                if (canSweep) {
                    int sweeping = EnchantmentKeys.getEnchantmentLevel(level, Enchantments.SWEEPING_EDGE, stack);
                    float damage = event.getNewDamage() * (0.66f + sweeping * 0.33f);
                    float radius = 1.5f + sweeping * 0.25f;
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
                else {
                    particle.verticalSlashRotation();
                }
                particle.spawn(level);
                return;
            }
            if (source.is(MalumTags.DamageTypeTags.IS_SCYTHE)) {
                float chance = 0.3f;
                int extraHits = 2;
                float physicalDamage;
                float magicDamage;
                if (source.getDirectEntity() instanceof ScytheBoomerangEntity scytheBoomerang) {
                    physicalDamage = scytheBoomerang.damage;
                    magicDamage = scytheBoomerang.magicDamage;
                    chance *= 2;
                } else {
                    physicalDamage = (float) (attacker.getAttribute(Attributes.ATTACK_DAMAGE).getValue());
                    magicDamage = (float) (attacker.getAttribute(LodestoneAttributes.MAGIC_DAMAGE).getValue());
                }
                float average = (physicalDamage + magicDamage) / 2;
                physicalDamage *= physicalDamage / average * 0.1f;
                magicDamage *= magicDamage / average * 0.1f;
                if (!canSweep) {
                    extraHits++;
                    chance += 0.1f;
                }
                if (level.getRandom().nextFloat() > chance) {
                    return;
                }
                for (int i = 0; i < extraHits; i++) {
                    int delay = 4 + i * 3;
                    WorldEventHandler.addWorldEvent(level,
                            new DelayedDamageWorldEvent(target)
                                    .setAttacker(attacker, source.getDirectEntity())
                                    .setDamageData(physicalDamage, magicDamage, delay)
                                    .setPhysicalDamageType(MalumDamageTypes.SCYTHE_COMBO)
                                    .setSound(MalumSoundEvents.REAPER_CUT, 0.9f, 1.1f, 1));

                }
            }
        }
    }
}