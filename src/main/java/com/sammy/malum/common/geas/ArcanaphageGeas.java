package com.sammy.malum.common.geas;

import com.google.common.collect.*;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.core.systems.events.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.core.*;
import net.minecraft.network.chat.*;
import net.minecraft.server.level.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.neoforged.neoforge.event.entity.living.*;
import net.neoforged.neoforge.event.tick.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.registry.common.tag.*;

import java.util.function.*;

public class ArcanaphageGeas extends GeasEffect {

    public ArcanaphageGeas() {
        super(MalumGeasEffectTypeRegistry.PACT_OF_THE_ARCANAPHAGE.get());
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> createAttributeModifiers(LivingEntity entity, Multimap<Holder<Attribute>, AttributeModifier> modifiers) {
        addAttributeModifier(modifiers, AttributeRegistry.SPIRIT_SPOILS, 1, AttributeModifier.Operation.ADD_VALUE);
        return modifiers;
    }

    @Override
    public void addTooltipComponents(LivingEntity entity, Consumer<Component> tooltipAcceptor, TooltipFlag tooltipFlag) {
        super.addTooltipComponents(entity, tooltipAcceptor, tooltipFlag);
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("spirits_magic_boost"));
        tooltipAcceptor.accept(ComponentHelper.negativeGeasEffect("oops_all_magic"));
    }

    @Override
    public void spiritCollectionEvent(CollectSpiritEvent event, LivingEntity collector, double arcaneResonance) {
        var effect = MobEffectRegistry.ARCANAPHAGE;
        var instance = collector.getEffect(effect);
        if (instance == null) {
            collector.addEffect(new MobEffectInstance(effect, 100, 0, true, true, true));
        } else {
            if (collector.getRandom().nextBoolean()) {
                EntityHelper.amplifyEffect(instance, collector, 1, 25);
            }
            EntityHelper.extendEffect(instance, collector, 40, 600);
        }
    }

    @Override
    public void incomingDamageEvent(LivingIncomingDamageEvent event, LivingEntity attacker, LivingEntity target, ItemStack stack) {
        if (!event.getSource().is(LodestoneDamageTypeTags.IS_MAGIC)) {
            event.setCanceled(true);
            event.getEntity().hurt(DamageTypeHelper.create(attacker.level(), DamageTypeRegistry.VOODOO), event.getAmount());
        }
    }
}
