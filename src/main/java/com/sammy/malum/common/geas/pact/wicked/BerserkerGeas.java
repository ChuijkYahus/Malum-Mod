package com.sammy.malum.common.geas.pact.wicked;

import com.sammy.malum.common.worldevent.DelayedDamageWorldEvent;
import com.sammy.malum.core.helpers.ComponentHelper;
import com.sammy.malum.core.systems.geas.GeasEffect;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.visual_effects.networked.*;
import net.minecraft.network.chat.Component;
import net.minecraft.util.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.event.entity.living.*;
import net.neoforged.neoforge.event.tick.*;
import team.lodestar.lodestone.handlers.WorldEventHandler;

import java.util.function.Consumer;

public class BerserkerGeas extends GeasEffect {

    private float storedDamage;
    private boolean resetDamageNextTick;

    public BerserkerGeas() {
        super(MalumGeasEffectTypeRegistry.PACT_OF_THE_REAPER.get());
    }

    @Override
    public void incomingDamageEvent(LivingIncomingDamageEvent event, LivingEntity attacker, LivingEntity target, ItemStack stack) {
        storedDamage = (storedDamage + event.getAmount()) * 0.8f;
    }

    @Override
    public void incomingDamageEvent(LivingDamageEvent.Pre event, LivingEntity attacker, LivingEntity target, ItemStack stack) {
        event.setNewDamage(event.getNewDamage() * 1.25f);
    }

    @Override
    public void addTooltipComponents(LivingEntity entity, Consumer<Component> tooltipAcceptor, TooltipFlag tooltipFlag) {
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("damage_buildup"));
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("damage_release"));
        tooltipAcceptor.accept(ComponentHelper.negativeGeasEffect("more_damage"));
        super.addTooltipComponents(entity, tooltipAcceptor, tooltipFlag);
    }

    @Override
    public void update(EntityTickEvent.Pre event, LivingEntity entity) {
        if (entity.level().isClientSide) {
            return;
        }
        if (resetDamageNextTick) {
            storedDamage = 0;
        }
        else if (entity.level().getGameTime() % 40L == 0) {
            storedDamage *= 0.95f;
        }
    }

    @Override
    public void outgoingDamageEvent(LivingDamageEvent.Pre event, LivingEntity attacker, LivingEntity target, ItemStack stack) {
        if (target.level().isClientSide) {
            return;
        }
        if (event.getSource().is(DamageTypeRegistry.BERSERKER_SPIRIT_IMPACT)) {
            return;
        }
        if (storedDamage >= 2) {
            int hits = Mth.ceil(storedDamage/2);
            for (int i = 0; i < hits; i++) {
                float pitch = 0.8f + i * 0.1f;
                WorldEventHandler.addWorldEvent(target.level(),
                        new DelayedDamageWorldEvent(target)
                                .setAttacker(attacker)
                                .setDamageData(0, 2, Mth.ceil(storedDamage))
                                .setMagicDamageType(DamageTypeRegistry.BERSERKER_SPIRIT_IMPACT)
                                .setImpactParticleEffect(ParticleEffectTypeRegistry.BERSERKER_IMPACT, new MalumNetworkedParticleEffectColorData(SpiritTypeRegistry.WICKED_SPIRIT, SpiritTypeRegistry.ELDRITCH_SPIRIT))
                                .setSound(SoundRegistry.SPIRIT_BLAST, pitch, pitch+0.1f, 1));
            }
            resetDamageNextTick = true;
        }
    }
}