package com.sammy.malum.common.geas.pact.infernal;

import com.google.common.collect.*;
import com.sammy.malum.core.handlers.*;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.item.*;
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
import net.minecraft.world.level.*;
import net.minecraft.world.phys.*;
import net.neoforged.neoforge.event.entity.living.*;
import net.neoforged.neoforge.event.level.*;
import net.neoforged.neoforge.event.tick.*;
import team.lodestar.lodestone.helpers.*;

import javax.annotation.*;
import java.util.*;
import java.util.function.*;

public class BlastweaverGeas extends GeasEffect {

    public int oldAmplifier, amplifier;

    public BlastweaverGeas() {
        super(MalumGeasEffectTypes.PACT_OF_THE_BLASTWEAVER.get());
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> createAttributeModifiers(LivingEntity entity, Multimap<Holder<Attribute>, AttributeModifier> modifiers) {
        if (amplifier >= 0) {
            var buff = (amplifier+1) * 0.05f;
            addAttributeModifier(modifiers, Attributes.MOVEMENT_SPEED, buff, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
            addAttributeModifier(modifiers, Attributes.BLOCK_BREAK_SPEED, buff, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
            addAttributeModifier(modifiers, Attributes.ATTACK_SPEED, buff, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        }
        return modifiers;
    }
    @Override
    public void addTooltipComponents(LivingEntity entity, Consumer<Component> tooltipAcceptor, TooltipFlag tooltipFlag) {
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("vastly_bigger_explosions"));
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("explosion_lover"));
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("avarice_fortune"));
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("avarice_fervor"));
        tooltipAcceptor.accept(ComponentHelper.negativeGeasEffect("avarice_vulnerability"));
    }

    @Override
    public void update(EntityTickEvent.Pre event, LivingEntity entity) {
        var effect = entity.getEffect(MalumMobEffects.AVARICE);
        if (effect == null) {
            amplifier = -1;
        } else {
            amplifier = effect.getAmplifier();
        }
        if (oldAmplifier != amplifier) {
            oldAmplifier = amplifier;
            setDirty();
        }
    }

    @Override
    public void incomingDamageEvent(LivingDamageEvent.Pre event, LivingEntity attacker, LivingEntity target, ItemStack stack) {
        if (event.getSource().is(DamageTypeTags.IS_EXPLOSION)) {
            var effect = attacker.getEffect(MalumMobEffects.AVARICE);
            if (effect != null) {
                float modifier = 1 + (effect.getAmplifier() + 1) * 0.1f;
                event.setNewDamage(event.getNewDamage() * modifier);
            }
        }
    }

    public static float increaseExplosionRadius(LivingEntity source, float original) {
        if (source != null && GeasEffectHandler.hasGeasEffect(source, MalumGeasEffectTypes.PACT_OF_THE_BLASTWEAVER)) {
            return original + 2;
        }
        return original;
    }

    public static void processExplosion(ExplosionEvent.Detonate event) {
        var explosion = event.getExplosion();
        var owner = explosion.getIndirectSourceEntity();
        if (owner != null) {
            if (GeasEffectHandler.hasGeasEffect(owner, MalumGeasEffectTypes.PACT_OF_THE_BLASTWEAVER)) {
                var explosionAffectedEntities = getExplosionAffectedEntities(owner.level(), owner, explosion.center(), explosion.radius()*2);
                for (Entity entity : explosionAffectedEntities) {
                    if (entity instanceof LivingEntity living) {
                        ProspectorGeas.applyAvarice(living);
                    }
                }
            }
        }
    }

    public static List<Entity> getExplosionAffectedEntities(Level level, @Nullable LivingEntity entity, Vec3 pos, float radius) {
        float f2 = radius * 2.0F;
        int k1 = Mth.floor(pos.x - (double)f2 - 1.0);
        int l1 = Mth.floor(pos.x + (double)f2 + 1.0);
        int i2 = Mth.floor(pos.y - (double)f2 - 1.0);
        int i1 = Mth.floor(pos.y + (double)f2 + 1.0);
        int j2 = Mth.floor(pos.z - (double)f2 - 1.0);
        int j1 = Mth.floor(pos.z + (double)f2 + 1.0);
        return level.getEntities(entity, new AABB(k1, i2, j2, l1, i1, j1));
    }
}