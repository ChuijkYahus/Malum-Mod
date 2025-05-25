package com.sammy.malum.common.geas.pact.sacred;

import com.google.common.collect.*;
import com.sammy.malum.core.handlers.*;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.visual_effects.networked.geas.*;
import net.minecraft.core.*;
import net.minecraft.network.chat.*;
import net.minecraft.server.level.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.event.entity.living.*;
import team.lodestar.lodestone.helpers.*;

import java.util.function.*;

public class LifeweaverGeas extends GeasEffect {

    public LifeweaverGeas() {
        super(MalumGeasEffectTypes.PACT_OF_THE_LIFEWEAVER.get());
    }

    @Override
    public void addTooltipComponents(LivingEntity entity, Consumer<Component> tooltipAcceptor, TooltipFlag tooltipFlag) {
        super.addTooltipComponents(entity, tooltipAcceptor, tooltipFlag);
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("healing_aura"));
        tooltipAcceptor.accept(ComponentHelper.negativeGeasEffect("healing_aura_no_filter"));
    }
    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> createAttributeModifiers(LivingEntity entity, Multimap<Holder<Attribute>, AttributeModifier> modifiers) {
        addAttributeModifier(modifiers, Attributes.MAX_HEALTH, 0.2f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        return modifiers;
    }

    public static void onHeal(LivingHealEvent event) {
        var entity = event.getEntity();
        if (entity.level() instanceof ServerLevel level) {
            if (GeasEffectHandler.hasGeasEffect(entity, MalumGeasEffectTypes.PACT_OF_THE_LIFEWEAVER)) {
                float horizontalRadius = 6f;
                float verticalRadius = 4f;
                var aabb = entity.getBoundingBox().inflate(horizontalRadius, verticalRadius, horizontalRadius);
                boolean success = false;
                for (LivingEntity target : level.getEntitiesOfClass(LivingEntity.class, aabb)) {
                    if (target == entity) {
                        continue;
                    }
                    if (!GeasEffectHandler.hasGeasEffect(target, MalumGeasEffectTypes.PACT_OF_THE_LIFEWEAVER)) {
                        float health = target.getHealth();
                        if (target.isInvertedHealAndHarm()) {
                            target.hurt(DamageTypeHelper.create(level, MalumDataTypes.VOODOO, entity), event.getAmount() * 2f);
                        }
                        else {
                            target.heal(event.getAmount() * 1.5f);
                        }
                        if (health != target.getHealth()) {
                            MalumParticleEffectTypes.HEALING_BEAM.createEffect(entity)
                                    .color(MalumSpiritTypes.SACRED_SPIRIT, MalumSpiritTypes.ARCANE_SPIRIT)
                                    .customData(new LifeweaverHealingBeamParticleEffect.LifeweaverHealingBeamEffectData(target.getId(), entity.getId()))
                                    .spawn(level);
                            success = true;
                        }
                    }
                }
                if (success) {
                    event.setAmount(event.getAmount() * 1.25f);
                }
            }
        }
    }
}
