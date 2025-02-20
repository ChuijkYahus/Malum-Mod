package com.sammy.malum.common.geas;

import com.sammy.malum.core.handlers.*;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.network.chat.*;
import net.minecraft.world.*;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.event.entity.living.*;

import java.util.function.*;

public class SkyBreakerGeas extends GeasEffect {

    public SkyBreakerGeas() {
        super(MalumGeasEffectTypeRegistry.PACT_OF_THE_SKYBREAKER.get());
    }

    @Override
    public void addTooltipComponents(LivingEntity entity, Consumer<Component> tooltipAcceptor, TooltipFlag tooltipFlag) {
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("fall_damage_auto_attack"));
        tooltipAcceptor.accept(ComponentHelper.negativeGeasEffect("more_knockback"));
        super.addTooltipComponents(entity, tooltipAcceptor, tooltipFlag);
    }

    public static void scaleKnockback(LivingKnockBackEvent event) {
        final LivingEntity entity = event.getEntity();
        var geas = GeasEffectHandler.getGeasEffect(entity, MalumGeasEffectTypeRegistry.PACT_OF_THE_SKYBREAKER);
        if (geas != null) {
            event.setStrength(event.getStrength() * 2);
        }
    }

    @Override
    public void finalizedOutgoingDamageEvent(LivingDamageEvent.Post event, LivingEntity attacker, LivingEntity target, ItemStack stack) {
        if (attacker instanceof Player player) {
            if (event.getSource().is(DamageTypes.FALL)) {
                if (target.isAlive()) {
                    player.attackStrengthTicker = 1000;
                    player.swing(InteractionHand.MAIN_HAND, true);
                    target.invulnerableTime = 0;
                    player.attack(target);
                    target.invulnerableTime = 0;
                }
            }
        }
    }
}