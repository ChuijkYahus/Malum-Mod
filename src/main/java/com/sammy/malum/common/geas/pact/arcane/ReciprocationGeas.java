package com.sammy.malum.common.geas.pact.arcane;

import com.google.common.collect.*;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.core.*;
import net.minecraft.network.chat.*;
import net.minecraft.server.level.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.event.entity.living.*;
import team.lodestar.lodestone.registry.common.tag.*;

import java.util.function.*;

public class ReciprocationGeas extends GeasEffect {

    public ReciprocationGeas() {
        super(MalumGeasEffectTypes.PACT_OF_RECIPROCATION.get());
    }

    @Override
    public void outgoingDamageEvent(LivingDamageEvent.Pre event, LivingEntity attacker, LivingEntity target, ItemStack stack) {
        if (attacker instanceof ServerPlayer player) {
            if (event.getSource().is(LodestoneDamageTypeTags.IS_MAGIC)) {
                var data = player.getData(MalumAttachmentTypes.SOUL_WARD);
                double attackSpeed = Math.max(attacker.getAttributeValue(Attributes.ATTACK_SPEED), 0.01f);
                data.recoverSoulWard(player, 0.25f / attackSpeed);
            }
        }
    }

    @Override
    public void addTooltipComponents(LivingEntity entity, Consumer<Component> tooltipAcceptor, TooltipFlag tooltipFlag) {
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("soul_ward_on_hit"));
        super.addTooltipComponents(entity, tooltipAcceptor, tooltipFlag);
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> createAttributeModifiers(LivingEntity entity, Multimap<Holder<Attribute>, AttributeModifier> modifiers) {
        addAttributeModifier(modifiers, MalumAttributes.SOUL_WARD_CAPACITY, 12, AttributeModifier.Operation.ADD_VALUE);
        addAttributeModifier(modifiers, MalumAttributes.SOUL_WARD_RECOVERY_MULTIPLIER, 1f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        addAttributeModifier(modifiers, MalumAttributes.SOUL_WARD_RECOVERY_RATE, -1f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        return modifiers;
    }
}
