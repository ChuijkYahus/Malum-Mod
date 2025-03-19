package com.sammy.malum.common.geas.explosion;

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
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.neoforged.neoforge.event.entity.living.*;
import net.neoforged.neoforge.event.level.*;
import net.neoforged.neoforge.event.tick.*;
import team.lodestar.lodestone.helpers.*;

import java.util.function.*;

public class PyromaniacGeas extends GeasEffect {

    public PyromaniacGeas() {
        super(MalumGeasEffectTypeRegistry.PACT_OF_THE_PYROMANIAC.get());
    }

    @Override
    public void addTooltipComponents(LivingEntity entity, Consumer<Component> tooltipAcceptor, TooltipFlag tooltipFlag) {
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("explosion_lover"));
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("explosion_resistance"));
        tooltipAcceptor.accept(ComponentHelper.negativeGeasEffect("explosion_fire"));
        tooltipAcceptor.accept(ComponentHelper.negativeGeasEffect("scary_fire"));
        super.addTooltipComponents(entity, tooltipAcceptor, tooltipFlag);
    }

    @Override
    public void incomingDamageEvent(LivingDamageEvent.Pre event, LivingEntity attacker, LivingEntity target, ItemStack stack) {
        if (event.getSource().is(DamageTypeTags.IS_FIRE)) {
            event.setNewDamage(event.getNewDamage() * 1.5f);
        }
        if (event.getSource().is(DamageTypeTags.IS_EXPLOSION)) {
            float health = target.getHealth();
            event.setNewDamage(Mth.clamp(event.getNewDamage() * 0.25f, 0, health*0.5f));
        }
    }

    public static void processExplosion(ExplosionEvent.Detonate event) {
        final Explosion explosion = event.getExplosion();
        for (Entity entity : event.getAffectedEntities()) {
            if (entity instanceof LivingEntity livingEntity) {
                if (explosion.damageCalculator.shouldDamageEntity(explosion, livingEntity)) {
                    var geas = GeasEffectHandler.getGeasEffect(livingEntity, MalumGeasEffectTypeRegistry.PACT_OF_THE_PYROMANIAC);
                    if (geas != null) {
                        int pyromaniacStacks = 2;
                        if (!entity.equals(explosion.getIndirectSourceEntity()) && !entity.equals(explosion.getDirectSourceEntity())) {
                            pyromaniacStacks = 4;
                        }
                        final MobEffectInstance instance = livingEntity.getEffect(MobEffectRegistry.PYROMANIACS_FERVOR);
                        if (instance != null) {
                            if (instance.getAmplifier() >= 5) {
                                livingEntity.igniteForSeconds(5);
                            }
                            EntityHelper.extendEffect(instance, livingEntity, 300, 1200);
                            EntityHelper.amplifyEffect(instance, livingEntity, pyromaniacStacks, 9);
                        }
                        else {
                            livingEntity.addEffect(new MobEffectInstance(MobEffectRegistry.PYROMANIACS_FERVOR, 600, pyromaniacStacks-1));
                        }
                        return;
                    }
                }
            }
        }
    }
}
