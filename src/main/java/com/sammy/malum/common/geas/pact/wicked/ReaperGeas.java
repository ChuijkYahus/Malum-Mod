package com.sammy.malum.common.geas.pact.wicked;

import com.sammy.malum.common.item.curiosities.weapons.scythe.*;
import com.sammy.malum.common.worldevent.*;
import com.sammy.malum.core.handlers.*;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.magic.*;
import com.sammy.malum.registry.common.sound.*;
import net.minecraft.network.chat.*;
import net.minecraft.server.level.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.event.entity.living.*;
import team.lodestar.lodestone.handlers.*;
import team.lodestar.lodestone.modules.core.easing.Easing;

import java.util.function.*;

import static net.minecraft.world.entity.EquipmentSlot.MAINHAND;

public class ReaperGeas extends GeasEffect {

    public ReaperGeas() {
        super(MalumGeasEffectTypes.PACT_OF_THE_REAPER.get());
    }

    @Override
    public void addTooltipComponents(LivingEntity entity, Consumer<Component> tooltipAcceptor, TooltipFlag tooltipFlag) {
        tooltipAcceptor.accept(TooltipComponentHelper.positiveGeasEffect("scythe_combo"));
        tooltipAcceptor.accept(TooltipComponentHelper.negativeGeasEffect("only_scythe"));
        super.addTooltipComponents(entity, tooltipAcceptor, tooltipFlag);
    }

    @Override
    public void outgoingDamageEvent(LivingDamageEvent.Pre event, LivingEntity attacker, LivingEntity target, ItemStack stack) {
        if (attacker.level() instanceof ServerLevel level) {
            var source = event.getSource();
            var heldItem = attacker.getMainHandItem();
            if (source.is(net.minecraft.world.damagesource.DamageTypes.PLAYER_ATTACK) || source.is(MalumDamageTypes.TYRVING)) {
                if (!heldItem.isEmpty()) {
                    event.setNewDamage(event.getNewDamage() * 0.1f);
                    if (heldItem.isDamageableItem()) {
                        heldItem.hurtAndBreak(10, attacker, MAINHAND);
                    }
                }
                return;
            }
            if (source.is(MalumTags.DamageTypes.TRIGGERS_SCYTHE_COMBO)) {
                MalumScytheItem.ScytheDamage damage = MalumScytheItem.getScytheDamage(source, attacker);
                float physicalDamage = damage.physicalDamage();
                float magicDamage = damage.magicDamage();
                float damageScalar = 0.1f;
                float chance = 0.3f;
                int extraHits = 2;
                if (damage.isBoomerang()) {
                    chance *= 2;
                }
                float average = (physicalDamage + magicDamage) / 2;
                physicalDamage *= physicalDamage / average * damageScalar;
                magicDamage *= magicDamage / average * damageScalar;
                if (MalumScytheItem.isEnhanced(attacker)) {
                    extraHits++;
                    chance += 0.1f;
                }
                if (attacker.getRandom().nextFloat() < chance) {
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

    @Override
    public void finalizedOutgoingDamageEvent(LivingDamageEvent.Post event, LivingEntity attacker, LivingEntity target, ItemStack stack) {
        if (attacker.level() instanceof ServerLevel level) {
            var random = attacker.getRandom();
            var source = event.getSource();
            if (source.is(MalumDamageTypes.SCYTHE_COMBO)) {
                var scytheStack = SoulDataHandler.getScytheWeapon(source, attacker);
                var particle = MalumParticleEffectTypes.SCYTHE_SLASH.createEffect()
                        .originatesFrom(attacker)
                        .targets(target)
                        .tiedToTarget()
                        .forwardOffset(-2f)
                        .upwardOffset(-0.5f)
                        .color(scytheStack.getItem())
                        .mirroredRandomly(random);
                if (MalumScytheItem.canSweep(attacker)) {
                    MalumScytheItem.trySweep(attacker, target, event.getNewDamage());
                } else {
                    particle.verticalSlashRotation();
                }
                particle.slashRotation(particle.getSlashRotation() + Easing.SINE_IN_OUT.asWeighedRandom(random, -0.3f, 0.3f));
                particle.spawn(level);
            }
        }
    }
}