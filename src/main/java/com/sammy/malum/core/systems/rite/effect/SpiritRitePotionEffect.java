package com.sammy.malum.core.systems.rite.effect;

import com.sammy.malum.core.systems.registry.*;
import com.sammy.malum.core.systems.spirit.type.*;
import net.minecraft.core.*;
import net.minecraft.server.level.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;

import java.util.*;
import java.util.function.*;

public class SpiritRitePotionEffect<T extends LivingEntity> extends SpiritRiteEntityEffect<T> {
    protected final Class<T> targetClass;
    protected final Supplier<MobEffectInstance> effectSupplier;
    protected final List<SpiritHolder<MalumSpiritType>> spirits;


    @SafeVarargs
    public SpiritRitePotionEffect(Class<T> targetClass, Holder<MobEffect> effect, SpiritHolder<MalumSpiritType>... spirits) {
        this(targetClass, () -> new MobEffectInstance(effect, 3000, 1, true, true), spirits);
    }

    @SafeVarargs
    public SpiritRitePotionEffect(Class<T> targetClass, Holder<MobEffect> effect, int duration, int amplifier, SpiritHolder<MalumSpiritType>... spirits) {
        this(targetClass, () -> new MobEffectInstance(effect, duration, amplifier, true, true), spirits);
    }

    @SafeVarargs
    public SpiritRitePotionEffect(Class<T> targetClass, Supplier<MobEffectInstance> effectSupplier, SpiritHolder<MalumSpiritType>... spirits) {
        this.targetClass = targetClass;
        this.effectSupplier = effectSupplier;
        this.spirits = Arrays.asList(spirits);
    }

    @Override
    public Class<T> getTargetClass() {
        return targetClass;
    }

    @Override
    public void applyEffect(ServerLevel level, T target) {
        var instance = effectSupplier.get();
        if (!target.hasEffect(instance.getEffect())) {
            createEffect(level, target, spirits);
        }
        target.addEffect(instance);
    }
}
