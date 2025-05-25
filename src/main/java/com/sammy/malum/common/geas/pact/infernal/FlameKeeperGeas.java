package com.sammy.malum.common.geas.pact.infernal;

import com.sammy.malum.core.handlers.*;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.network.chat.*;
import net.minecraft.tags.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.event.entity.living.*;
import net.neoforged.neoforge.event.level.*;
import team.lodestar.lodestone.helpers.*;

import java.util.function.*;

public class FlameKeeperGeas extends GeasEffect {

    public FlameKeeperGeas() {
        super(MalumGeasEffectTypeRegistry.PACT_OF_THE_FLAMEKEEPER.get());
    }

    @Override
    public void addTooltipComponents(LivingEntity entity, Consumer<Component> tooltipAcceptor, TooltipFlag tooltipFlag) {
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("mining_buffs"));
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("flamekeeper"));
        tooltipAcceptor.accept(ComponentHelper.negativeGeasEffect("self_immolation"));
        super.addTooltipComponents(entity, tooltipAcceptor, tooltipFlag);
    }

    @Override
    public void finalizedIncomingDamageEvent(LivingDamageEvent.Post event, LivingEntity attacker, LivingEntity target, ItemStack stack) {
        if (!event.getSource().is(DamageTypeTags.IS_FIRE)) {
            var effect = target.getEffect(MobEffectRegistry.FLAMEKEEPERS_FERVOR);
            if (effect != null && effect.amplifier >= 14) {
                target.igniteForSeconds(2);
            }
            applyEffect(target);
        }
    }

    public static void breakBlock(BlockEvent.BreakEvent event) {
        if (GeasEffectHandler.hasGeasEffect(event.getPlayer(), MalumGeasEffectTypeRegistry.PACT_OF_THE_FLAMEKEEPER)) {
            applyEffect(event.getPlayer());
        }
    }

    public static void applyEffect(LivingEntity target) {
        var effect = MobEffectRegistry.FLAMEKEEPERS_FERVOR;
        var instance = target.getEffect(effect);
        if (instance == null) {
            target.addEffect(new MobEffectInstance(effect, 100, 0, true, true, true));
        } else {
            if (target.getRandom().nextFloat() < 0.2f) {
                EntityHelper.amplifyEffect(instance, target, 1, 25);
            }
            EntityHelper.extendEffect(instance, target, 40, 600);
        }
    }
}
