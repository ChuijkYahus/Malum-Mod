package com.sammy.malum.common.geas.pact.infernal;

import com.sammy.malum.core.handlers.*;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.network.chat.*;
import net.minecraft.tags.*;
import net.minecraft.util.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.neoforged.neoforge.event.entity.living.*;
import net.neoforged.neoforge.event.level.*;
import team.lodestar.lodestone.helpers.*;

import java.util.function.*;

public class PyromaniacGeas extends GeasEffect {

    public PyromaniacGeas() {
        super(MalumGeasEffectTypes.PACT_OF_THE_PYROMANIAC.get());
    }

    @Override
    public void addTooltipComponents(LivingEntity entity, Consumer<Component> tooltipAcceptor, TooltipFlag tooltipFlag) {
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("explosion_lover"));
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("pyromaniac"));
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("explosion_resistance"));
        tooltipAcceptor.accept(ComponentHelper.negativeGeasEffect("explosion_fire"));
        tooltipAcceptor.accept(ComponentHelper.negativeGeasEffect("scary_fire"));
        super.addTooltipComponents(entity, tooltipAcceptor, tooltipFlag);
    }

    @Override
    public void incomingDamageEvent(LivingDamageEvent.Pre event, LivingEntity attacker, LivingEntity target, ItemStack stack) {
        if (event.getSource().is(DamageTypeTags.IS_FIRE)) {
            event.setNewDamage(event.getNewDamage() * 2f);
        }
        if (event.getSource().is(DamageTypeTags.IS_EXPLOSION)) {
            float health = target.getHealth();
            event.setNewDamage(Mth.clamp(event.getNewDamage() * 0.25f, 0, health * 0.5f));
        }
    }

    public static void processExplosion(ExplosionEvent.Detonate event) {
        final Explosion explosion = event.getExplosion();
        for (Entity entity : event.getAffectedEntities()) {
            if (entity instanceof LivingEntity livingEntity) {
                if (explosion.damageCalculator.shouldDamageEntity(explosion, livingEntity)) {
                    if (GeasEffectHandler.hasGeasEffect(livingEntity, MalumGeasEffectTypes.PACT_OF_THE_PYROMANIAC)) {
                        applyPyromaniac(livingEntity, explosion);
                    }
                }
            }
        }
    }


    public static void applyPyromaniac(LivingEntity entity, Explosion explosion) {
        int pyromaniacStacks = 2;
        if (!entity.equals(explosion.getIndirectSourceEntity()) && !entity.equals(explosion.getDirectSourceEntity())) {
            pyromaniacStacks = 4;
        }
        final MobEffectInstance instance = entity.getEffect(MalumMobEffects.PYROMANIACS_FERVOR);
        if (instance != null) {
            if (instance.getAmplifier() >= 5) {
                entity.igniteForSeconds(5);
            }
            EntityHelper.extendEffect(instance, entity, 300, 1200);
            EntityHelper.amplifyEffect(instance, entity, pyromaniacStacks, 9);
        } else {
            entity.addEffect(new MobEffectInstance(MalumMobEffects.PYROMANIACS_FERVOR, 600, pyromaniacStacks - 1));
        }
    }
}