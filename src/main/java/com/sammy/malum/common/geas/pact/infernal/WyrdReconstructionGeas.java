package com.sammy.malum.common.geas.pact.infernal;

import com.sammy.malum.core.handlers.*;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.core.systems.events.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.visual_effects.networked.MalumNetworkedParticleEffectColorData;
import com.sammy.malum.visual_effects.networked.geas.*;
import net.minecraft.network.chat.*;
import net.minecraft.server.level.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.common.*;
import net.neoforged.neoforge.event.entity.living.*;
import net.neoforged.neoforge.event.tick.*;
import team.lodestar.lodestone.helpers.*;

import java.util.function.*;

public class WyrdReconstructionGeas extends GeasEffect {

    private static final int COOLDOWN_DURATION = 48000;
    private static final int TRIGGERS = 12;
    public int spiritCollectionActivations;
    public int delay;

    public WyrdReconstructionGeas() {
        super(MalumGeasEffectTypeRegistry.PACT_OF_WYRD_RECONSTRUCTION.get());
    }

    @Override
    public void addTooltipComponents(LivingEntity entity, Consumer<Component> tooltipAcceptor, TooltipFlag tooltipFlag) {
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("wyrd_reconstruction"));
        tooltipAcceptor.accept(ComponentHelper.negativeGeasEffect("wyrd_reconstruction_cooldown"));
        tooltipAcceptor.accept(ComponentHelper.negativeGeasEffect("spirits_hunger"));
        super.addTooltipComponents(entity, tooltipAcceptor, tooltipFlag);
    }

    @Override
    public void spiritCollectionEvent(CollectSpiritEvent event, LivingEntity collector, double arcaneResonance) {
        if (collector instanceof Player player) {
            player.causeFoodExhaustion(0.6f);
        }
    }

    @Override
    public void update(EntityTickEvent.Pre event, LivingEntity entity) {
        if (delay > 0) {
            delay--;
            return;
        }
        if (spiritCollectionActivations > 0) {
            if (delay == 0) {
                SoulHarvestHandler.triggerSpiritCollection(entity);
                delay = 2;
                spiritCollectionActivations--;
                if (spiritCollectionActivations == 0) {
                    entity.addEffect(new MobEffectInstance(MobEffectRegistry.WYRD_EXHAUSTION, COOLDOWN_DURATION, 0, true, true));
                }
            }
        }
    }

    @Override
    public void incomingDeathEvent(LivingDeathEvent event, LivingEntity attacker, LivingEntity target, ItemStack stack) {
        var source = event.getSource();
        if (source.is(Tags.DamageTypes.IS_TECHNICAL)) {
            return;
        }
        if (source.is(DamageTypeRegistry.VOID)) {
            return;
        }
        if (target.hasEffect(MobEffectRegistry.WYRD_EXHAUSTION)) {
            return;
        }
        if (delay > 0) {
            event.setCanceled(true);
            target.setHealth(1);
            return;
        }
        float health = RandomHelper.randomBetween(target.getRandom(), 1, target.getMaxHealth() * 0.66f);
        if (target.level() instanceof ServerLevel serverLevel) {
            for (Entity knockbackTarget : serverLevel.getEntities(target, target.getBoundingBox().inflate(2f), t -> canApplyKnockback(target, t))) {
                knockbackTarget.setDeltaMovement(knockbackTarget.position().subtract(target.position()).normalize().scale(2f).add(0, 0.5f, 0));
            }
            SoundHelper.playSound(target, SoundRegistry.WYRD_RECONSTRUCTION.get(), 1, 1);
            ParticleEffectTypeRegistry.WYRD_RECONSTRUCTION_REVIVE.createEffect(target)
                    .color(new MalumNetworkedParticleEffectColorData(SpiritTypeRegistry.SACRED_SPIRIT, SpiritTypeRegistry.INFERNAL_SPIRIT))
                    .customData(new WyrdReconstructionReviveParticleEffect.WyrdReconstructionEffectData(target.getId()))
                    .spawn(serverLevel);
        }

        event.setCanceled(true);
        target.setHealth(health);
        delay = 15;
        spiritCollectionActivations = TRIGGERS;
    }

    protected static boolean canApplyKnockback(LivingEntity attacker, Entity pTarget) {
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