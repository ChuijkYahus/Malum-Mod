package com.sammy.malum.common.geas.pact.aqueous;

import com.google.common.collect.*;
import com.sammy.malum.common.worldevent.*;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.visual_effects.networked.MalumNetworkedParticleEffectColorData;
import net.minecraft.core.*;
import net.minecraft.network.chat.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.event.entity.living.*;
import net.neoforged.neoforge.event.tick.*;
import team.lodestar.lodestone.handlers.*;

import java.util.function.*;

public class HighPriestGeas extends GeasEffect {

    private static final int COOLDOWN_DURATION = 600;

    public boolean hasEffect = false;
    public HighPriestGeas() {
        super(MalumGeasEffectTypes.PACT_OF_THE_HIGH_PRIEST.get());
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> createAttributeModifiers(LivingEntity entity, Multimap<Holder<Attribute>, AttributeModifier> modifiers) {
        if (!hasEffect) {
            addAttributeModifier(modifiers, Attributes.ENTITY_INTERACTION_RANGE, 0.1f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
            addAttributeModifier(modifiers, Attributes.BLOCK_INTERACTION_RANGE, 0.2f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
        }
        return modifiers;
    }

    @Override
    public void addTooltipComponents(LivingEntity entity, Consumer<Component> tooltipAcceptor, TooltipFlag tooltipFlag) {
//        super.addTooltipComponents(entity, tooltipAcceptor, tooltipFlag);
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("bonus_reach"));
        tooltipAcceptor.accept(ComponentHelper.negativeGeasEffect("fragile_reach"));
        tooltipAcceptor.accept(ComponentHelper.negativeGeasEffect("fragile_reach_damage"));
    }

    @Override
    public void update(EntityTickEvent.Pre event, LivingEntity entity) {
        if (entity.level().getGameTime() % 5L == 0) {
            var hadEffect = hasEffect;
            hasEffect = entity.hasEffect(MalumMobEffects.SHAKEN_FAITH);
            if (hadEffect != hasEffect) {
                setDirty();
            }
        }
    }

    @Override
    public void incomingDamageEvent(LivingDamageEvent.Pre event, LivingEntity attacker, LivingEntity target, ItemStack stack) {
        if (event.getSource().getDirectEntity() != null && event.getSource().getEntity() != null) {
            if (hasEffect) {
                return;
            }
            WorldEventHandler.addWorldEvent(target.level(),
                    new DelayedDamageWorldEvent(target)
                            .setDamageData(0, 4, 2)
                            .setMagicDamageType(MalumDataTypes.KARMIC)
                            .setImpactParticleEffect(MalumParticleEffectTypes.SHAKEN_FAITH, new MalumNetworkedParticleEffectColorData(MalumSpiritTypes.AQUEOUS_SPIRIT))
                            .setSound(MalumSoundEvents.SCYTHE_SWEEP, 0.5f, 1.5f, 0.3f));
            target.addEffect(new MobEffectInstance(MalumMobEffects.SHAKEN_FAITH, COOLDOWN_DURATION, 0, true, true, true));
            setDirty();
        }
    }
}
