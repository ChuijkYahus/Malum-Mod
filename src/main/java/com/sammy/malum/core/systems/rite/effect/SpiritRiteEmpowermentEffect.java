package com.sammy.malum.core.systems.rite.effect;

import com.sammy.malum.core.systems.registry.*;
import com.sammy.malum.core.systems.spirit.type.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.core.*;
import net.minecraft.server.level.*;
import net.minecraft.sounds.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.*;

import java.util.*;

public abstract class SpiritRiteEmpowermentEffect<T extends LivingEntity> extends SpiritRiteEntityEffect<T> {
    protected final List<Holder<MobEffect>> effectTypes;
    protected final List<SpiritHolder<SpiritArcanaType>> spirits;

    @SafeVarargs
    public SpiritRiteEmpowermentEffect(List<Holder<MobEffect>> effectTypes, SpiritHolder<SpiritArcanaType>... spirits) {
        this(List.of(SpiritRiteEffectTag.AURA), effectTypes, spirits);
    }

    @SafeVarargs
    public SpiritRiteEmpowermentEffect(List<SpiritRiteEffectTag> tags, List<Holder<MobEffect>> effectTypes, SpiritHolder<SpiritArcanaType>... spirits) {
        super(tags);
        this.effectTypes = effectTypes;
        this.spirits = Arrays.asList(spirits);
    }

    @Override
    public boolean canApplyEffect(ServerLevel level, T target) {
        for (Holder<MobEffect> effectType : effectTypes) {
            MobEffectInstance effect = target.getEffect(effectType);
            if (effect == null) {
                continue;
            }
            if (effect.getAmplifier() <= 1) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void applyEffect(ServerLevel level, T target) {
        applyEffect(level, target, 6000, 2);
    }

    public final void applyEffect(ServerLevel level, T target, int duration, int amplifier) {
        for (Holder<MobEffect> effectType : effectTypes) {
            if (target.hasEffect(effectType)) {
                var instance = new MobEffectInstance(effectType, duration, amplifier, true, true);
                target.addEffect(instance);
            }
        }
        createEffect(level, target, spirits);
    }

    @Override
    public Holder<SoundEvent> getImpactSound() {
        return MalumSoundEvents.SPARK_POTION_IMPACT;
    }

    @Override
    public float getImpactSoundVolume(LivingEntity target) {
        return target instanceof Player ? 1.2f : 0.6f;
    }
}
