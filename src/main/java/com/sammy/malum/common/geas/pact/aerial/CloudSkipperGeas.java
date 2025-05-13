package com.sammy.malum.common.geas.pact.aerial;

import com.google.common.collect.*;
import com.sammy.malum.core.handlers.*;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.core.*;
import net.minecraft.network.chat.*;
import net.minecraft.tags.*;
import net.minecraft.util.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.phys.*;
import net.neoforged.neoforge.event.entity.living.*;
import net.neoforged.neoforge.event.level.*;
import net.neoforged.neoforge.event.tick.*;
import net.neoforged.neoforge.network.*;

import java.util.*;
import java.util.function.*;

public class CloudSkipperGeas extends GeasEffect {

    protected static final int FALLOFF_DURATION = 60;
    protected static final int STAMINA_FALLOFF_START = 5;
    protected int cooldown;
    public int streak;

    public CloudSkipperGeas() {
        super(MalumGeasEffectTypeRegistry.PACT_OF_THE_CLOUDSKIPPER.get());
    }

    public static void onExplosionKnockback(ExplosionKnockbackEvent event) {
        final Explosion explosion = event.getExplosion();

        List<LivingEntity> entities = new ArrayList<>();
        if (event.getAffectedEntity() instanceof LivingEntity livingEntity) {
            entities.add(livingEntity);
        }
        if (explosion.getIndirectSourceEntity() != null) {
            entities.add(explosion.getIndirectSourceEntity());
        }
        if (explosion.getDirectSourceEntity() instanceof LivingEntity livingEntity) {
            entities.add(livingEntity);
        }
        GeasEffect instance = null;
        for (LivingEntity entity : entities) {
            instance = getInstance(entity);
            if (instance != null) {
                break;
            }
        }
        if (instance instanceof CloudSkipperGeas cloudSkipper) {
            var entity = event.getAffectedEntity();
            if (!explosion.damageCalculator.shouldDamageEntity(explosion, entity)) {
                float minimumUpwardsVelocity = 0.5f;
                double horizontalScalar = 2f;
                double verticalScalar = entity instanceof Player ? 1.25f : 1.75f;
                var knockbackVelocity = event.getKnockbackVelocity();
                if (knockbackVelocity.y < minimumUpwardsVelocity) {
                    final double length = knockbackVelocity.length();
                    knockbackVelocity = knockbackVelocity.normalize().multiply(length, minimumUpwardsVelocity, length);
                }
                event.setKnockbackVelocity(knockbackVelocity.multiply(horizontalScalar, verticalScalar, horizontalScalar));
                if (entity instanceof Player player) {
                    player.addEffect(new MobEffectInstance(MobEffectRegistry.ASCENSION, 100, 1));
                    cloudSkipper.streak++;
                    cloudSkipper.setDirty();
                }
            }
        }
    }

    public static GeasEffect getInstance(LivingEntity entity) {
        return GeasEffectHandler.getGeasEffect(entity, MalumGeasEffectTypeRegistry.PACT_OF_THE_CLOUDSKIPPER);
    }

    @Override
    public void update(EntityTickEvent.Pre event, LivingEntity entity) {
        if (streak == 0) {
            return;
        }
        cooldown++;
        if (cooldown == FALLOFF_DURATION) {
            streak = Math.max(Mth.floor(streak-2), 0);
            cooldown = 0;
            setDirty();
        }
    }

    @Override
    public void incomingDamageEvent(LivingDamageEvent.Pre event, LivingEntity attacker, LivingEntity target, ItemStack stack) {
        if (event.getSource().is(DamageTypeTags.IS_FALL)) {
            event.setNewDamage(event.getNewDamage() * 2f);
        }
    }

    @Override
    public void addTooltipComponents(LivingEntity entity, Consumer<Component> tooltipAcceptor, TooltipFlag tooltipFlag) {
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("rocket_jumping"));
        tooltipAcceptor.accept(ComponentHelper.negativeGeasEffect("wind_charge_exhaustion"));
        tooltipAcceptor.accept(ComponentHelper.negativeGeasEffect("weak_legs"));
        super.addTooltipComponents(entity, tooltipAcceptor, tooltipFlag);
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> createAttributeModifiers(LivingEntity entity, Multimap<Holder<Attribute>, AttributeModifier> modifiers) {
        if (streak >= STAMINA_FALLOFF_START) {
            float modifier = 0.06f * (streak - STAMINA_FALLOFF_START);
            addAttributeModifier(modifiers, Attributes.GRAVITY, modifier, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        }
        return modifiers;
    }
}