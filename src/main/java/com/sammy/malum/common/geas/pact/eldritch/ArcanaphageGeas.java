package com.sammy.malum.common.geas.pact.eldritch;

import com.google.common.collect.*;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.core.systems.events.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.magic.*;
import net.minecraft.core.*;
import net.minecraft.network.chat.*;
import net.minecraft.tags.*;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.item.*;
import net.minecraft.world.phys.*;
import net.neoforged.neoforge.event.entity.living.*;
import org.jetbrains.annotations.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.registry.common.tag.*;

import java.util.function.*;

public class ArcanaphageGeas extends GeasEffect {

    public ArcanaphageGeas() {
        super(MalumGeasEffectTypes.PACT_OF_THE_ARCANAPHAGE.get());
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> createAttributeModifiers(LivingEntity entity, Multimap<Holder<Attribute>, AttributeModifier> modifiers) {
        addAttributeModifier(modifiers, MalumAttributes.SPIRIT_SPOILS, 1, AttributeModifier.Operation.ADD_VALUE);
        addAttributeModifier(modifiers, MalumAttributes.SOUL_WARD_INTEGRITY, -0.5f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        return modifiers;
    }

    @Override
    public void addTooltipComponents(LivingEntity entity, Consumer<Component> tooltipAcceptor, TooltipFlag tooltipFlag) {
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("spirits_magic_boost"));
        super.addTooltipComponents(entity, tooltipAcceptor, tooltipFlag);
        tooltipAcceptor.accept(ComponentHelper.negativeGeasEffect("oops_all_magic"));
    }

    @Override
    public void spiritCollectionEvent(CollectSpiritEvent event, LivingEntity collector, double arcaneResonance) {
        var effect = MalumMobEffects.ARCANAPHAGE;
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
            target.hurt(new ArcanaphageDamageSource(event.getSource()), event.getAmount());
        }
    }

    public static final class ArcanaphageDamageSource extends DamageSource {

        public ArcanaphageDamageSource(DamageSource source) {
            this(source.typeHolder(), source.getDirectEntity(), source.getEntity(), source.getSourcePosition());
        }
        public ArcanaphageDamageSource(Holder<DamageType> type, @Nullable Entity directEntity, @Nullable Entity causingEntity, @Nullable Vec3 damageSourcePosition) {
            super(type, directEntity, causingEntity, damageSourcePosition);
        }

        @Override
        public boolean is(TagKey<DamageType> damageTypeKey) {
            if (damageTypeKey.equals(LodestoneDamageTypeTags.IS_MAGIC)) {
                return true;
            }
            return super.is(damageTypeKey);
        }
    }
}
