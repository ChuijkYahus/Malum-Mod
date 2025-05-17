package com.sammy.malum.common.geas.pact.aqueous;

import com.google.common.collect.*;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.core.*;
import net.minecraft.network.chat.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.common.*;
import net.neoforged.neoforge.event.entity.living.*;
import net.neoforged.neoforge.event.tick.*;
import team.lodestar.lodestone.registry.common.*;

import java.util.function.*;

public class TidalAffinityGeas extends GeasEffect {

    private boolean isInWater;
    private boolean hasConduitEffect;

    public TidalAffinityGeas() {
        super(MalumGeasEffectTypeRegistry.PACT_OF_TIDAL_AFFINITY.get());
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> createAttributeModifiers(LivingEntity entity, Multimap<Holder<Attribute>, AttributeModifier> modifiers) {
        if (isInWater) {
            float swimSpeed = hasConduitEffect ? 0.4f : 0.2f;
            float attackSpeed = hasConduitEffect ? 0.2f : 0.1f;
            addAttributeModifier(modifiers, NeoForgeMod.SWIM_SPEED, swimSpeed, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
            addAttributeModifier(modifiers, Attributes.ATTACK_SPEED, attackSpeed, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
            addAttributeModifier(modifiers, Attributes.BLOCK_BREAK_SPEED, attackSpeed, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
            addAttributeModifier(modifiers, Attributes.SUBMERGED_MINING_SPEED, 4f, AttributeModifier.Operation.ADD_VALUE);
            if (hasConduitEffect) {
                addAttributeModifier(modifiers, AttributeRegistry.HEALING_MULTIPLIER, 0.5f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
            }
        }
        else {
            addAttributeModifier(modifiers, AttributeRegistry.HEALING_MULTIPLIER, -1f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        }
        return modifiers;
    }

    @Override
    public void addTooltipComponents(LivingEntity entity, Consumer<Component> tooltipAcceptor, TooltipFlag tooltipFlag) {
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("water_agility"));
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("water_damage_resistance"));
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("conduit_bonus"));
        super.addTooltipComponents(entity, tooltipAcceptor, tooltipFlag);
        tooltipAcceptor.accept(ComponentHelper.negativeGeasEffect("fish_healing"));
    }


    @Override
    public void incomingDamageEvent(LivingDamageEvent.Pre event, LivingEntity attacker, LivingEntity target, ItemStack stack) {
        float damageScalar = 1f - (isInWater ? 0.25f : 0f) - (hasConduitEffect ? 0.25f : 0f);
        event.setNewDamage(event.getNewDamage() * damageScalar);
    }

    @Override
    public void update(EntityTickEvent.Pre event, LivingEntity entity) {
        if (entity.level().getGameTime() % 10L == 0) {
            boolean wasInWater = isInWater;
            boolean hadConduitEffect = hasConduitEffect;
            isInWater = entity.isInWater();
            hasConduitEffect = entity.hasEffect(MobEffects.CONDUIT_POWER);
            if (isInWater && hasConduitEffect) {
                entity.heal(1);
            }
            if (wasInWater != isInWater || hadConduitEffect != hasConduitEffect) {
                setDirty();
            }
        }
    }
}