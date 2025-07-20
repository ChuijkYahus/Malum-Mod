package com.sammy.malum.core.systems.rite.effect;

import com.sammy.malum.core.systems.registry.*;
import com.sammy.malum.core.systems.spirit.type.*;
import net.minecraft.core.*;
import net.minecraft.server.level.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;

import java.util.*;

public abstract class SpiritRitePotionEffect<T extends LivingEntity> extends SpiritRiteEntityEffect<T> {
    protected final Holder<MobEffect> effectType;
    protected final List<SpiritHolder<SpiritArcanaType>> spirits;

    @SafeVarargs
    public SpiritRitePotionEffect(Holder<MobEffect> effectType, SpiritHolder<SpiritArcanaType>... spirits) {
        this(List.of(SpiritRiteEffectTag.AURA), effectType, spirits);
    }

    @SafeVarargs
    public SpiritRitePotionEffect(List<SpiritRiteEffectTag> tags, Holder<MobEffect> effectType, SpiritHolder<SpiritArcanaType>... spirits) {
        super(tags);
        this.effectType = effectType;
        this.spirits = Arrays.asList(spirits);
    }

    @SuppressWarnings("unchecked")
    public void applyRuneEffect(ServerLevel level, LivingEntity target) {
        if (getTargetClass().isInstance(target)) {
            applyEffect(level, (T) target, 600, 0);
        }
    }

    @Override
    public void applyEffect(ServerLevel level, T target) {
        applyEffect(level, target, 3000, 1);
    }

    public void applyEffect(ServerLevel level, T target, int duration, int amplifier) {
        var instance = new MobEffectInstance(effectType, duration, amplifier, true, true);
        if (!target.hasEffect(effectType)) {
            createEffect(level, target, spirits);
        }
        target.addEffect(instance);
    }

    public Holder<MobEffect> getEffect() {
        return effectType;
    }
}
