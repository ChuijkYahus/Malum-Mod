package com.sammy.malum.common.geas.authority;

import com.google.common.collect.*;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.tag.*;
import net.minecraft.core.*;
import net.minecraft.network.chat.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.event.entity.living.*;
import team.lodestar.lodestone.helpers.*;

import java.util.function.*;

public class GleefulTargetAuthority extends GeasEffect {

    public GleefulTargetAuthority() {
        super(MalumGeasEffectTypeRegistry.AUTHORITY_OF_THE_GLEEFUL_TARGET.get());
    }

    @Override
    public void addTooltipComponents(LivingEntity entity, Consumer<Component> tooltipAcceptor, TooltipFlag tooltipFlag) {
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("gleeful_target"));
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("gleeful_target_arcane_resonance"));
        super.addTooltipComponents(entity, tooltipAcceptor, tooltipFlag);
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> createAttributeModifiers(LivingEntity entity, Multimap<Holder<Attribute>, AttributeModifier> modifiers) {
        addAttributeModifier(modifiers, AttributeRegistry.HEALING_MULTIPLIER, -0.75f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        return modifiers;
    }

    @Override
    public void incomingDamageEvent(LivingDamageEvent.Pre event, LivingEntity attacker, LivingEntity target, ItemStack stack) {
        if (event.getSource().is(DamageTypeTagRegistry.GLEEFUL_TARGET_BLACKLIST)) {
            return;
        }
        var gleefulTarget = target.getEffect(MobEffectRegistry.GLEEFUL_TARGET);
        int addedAmount = (int) (100 * target.getAttributeValue(AttributeRegistry.ARCANE_RESONANCE));
        if (gleefulTarget == null) {
            target.addEffect(new MobEffectInstance(MobEffectRegistry.GLEEFUL_TARGET, addedAmount * 4, 0, true, true, true));
        }
        else {
            EntityHelper.extendEffect(gleefulTarget, target, addedAmount, 36000);
        }
    }

    public static boolean pausePotionEffects(LivingEntity entity, MobEffectInstance instance) {
        var gleefulTarget = entity.getEffect(MobEffectRegistry.GLEEFUL_TARGET);
        if (gleefulTarget != null && !instance.getEffect().equals(MobEffectRegistry.GLEEFUL_TARGET)) {
            final MobEffect type = instance.getEffect().value();
            return !type.isInstantenous();
        }
        return false;
    }
}
