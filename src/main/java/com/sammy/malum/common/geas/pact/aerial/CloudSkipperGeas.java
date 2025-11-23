package com.sammy.malum.common.geas.pact.aerial;

import com.google.common.collect.*;
import com.sammy.malum.core.handlers.*;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.magic.*;
import net.minecraft.core.*;
import net.minecraft.network.chat.*;
import net.minecraft.tags.*;
import net.minecraft.util.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.event.entity.living.*;
import net.neoforged.neoforge.event.level.*;
import net.neoforged.neoforge.event.tick.*;

import java.util.*;
import java.util.function.*;

public class CloudSkipperGeas extends GeasEffect {

    protected static final float WARMUP_DURATION = 40;
    protected int ascensionTime;

    public CloudSkipperGeas() {
        super(MalumGeasEffectTypes.PACT_OF_THE_CLOUDSKIPPER.get());
    }

    public static void onExplosionKnockback(ExplosionKnockbackEvent event) {
        var explosion = event.getExplosion();

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
        if (instance instanceof CloudSkipperGeas) {
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
                    player.addEffect(new MobEffectInstance(MalumMobEffects.ASCENSION, 200, 3));
                }
            }
        }
    }

    public static GeasEffect getInstance(LivingEntity entity) {
        return GeasEffectHandler.getGeasEffect(entity, MalumGeasEffectTypes.PACT_OF_THE_CLOUDSKIPPER);
    }

    @Override
    public void update(EntityTickEvent.Pre event, LivingEntity entity) {
        if (entity.onGround() || entity.isInWater() || entity.isInLava()) {
            if (ascensionTime > 0) {
                ascensionTime--;
            }
            return;
        }
        if (entity.hasEffect(MalumMobEffects.ASCENSION) || entity.hasEffect(MalumMobEffects.LIFTED)) {
            if (entity instanceof Player player && player.level().isClientSide) {
                var velocity = player.getDeltaMovement();
                var angle = player.getLookAngle();
                if (angle.y > -0.6f && angle.y < 0.6f) {
                    float delta = (float) Math.clamp(velocity.y * 2f, 0, 1);
                    if (delta > 0) {
                        float target = (float) (0.2f + player.getAttributeValue(Attributes.MOVEMENT_SPEED)) * 0.45f;
                        var added = angle.scale(target).multiply(delta, 0.5f, delta);
                        player.setDeltaMovement(velocity.add(added).multiply(0.95f, 1f, 0.95f));
                    }
                }
            }
            if (ascensionTime < WARMUP_DURATION) {
                ascensionTime++;
            }
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
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("wind_gliding"));
        tooltipAcceptor.accept(ComponentHelper.negativeGeasEffect("weak_legs"));
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> createAttributeModifiers(LivingEntity entity, Multimap<Holder<Attribute>, AttributeModifier> modifiers) {
        float delta = Math.min(ascensionTime, WARMUP_DURATION)/WARMUP_DURATION;
        if (delta > 0) {
            float modifier = 0.75f * delta;
            addAttributeModifier(modifiers, Attributes.MOVEMENT_SPEED, modifier, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        }
        return modifiers;
    }
}