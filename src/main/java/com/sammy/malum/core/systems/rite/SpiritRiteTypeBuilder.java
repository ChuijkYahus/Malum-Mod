package com.sammy.malum.core.systems.rite;

import com.sammy.malum.core.systems.registry.*;
import com.sammy.malum.core.systems.rite.effect.SpiritRiteEffect;
import com.sammy.malum.core.systems.spirit.type.MalumSpiritType;
import com.sammy.malum.registry.common.magic.*;

import java.util.*;
import java.util.function.*;

public class SpiritRiteTypeBuilder {

    private final List<SpiritHolder<MalumSpiritType>> spirits;
    private boolean isCorrupted = false;
    private SpiritRiteEffect effect;


    public static SpiritRiteTypeBuilder createArcane(SpiritHolder<MalumSpiritType> spirit) {
        return new SpiritRiteTypeBuilder(List.of(MalumSpiritTypes.ARCANE_SPIRIT, spirit, spirit));
    }

    public static SpiritRiteTypeBuilder createEldritch(SpiritHolder<MalumSpiritType> spirit) {
        return new SpiritRiteTypeBuilder(List.of(MalumSpiritTypes.ELDRITCH_SPIRIT, MalumSpiritTypes.ARCANE_SPIRIT, spirit, spirit));
    }

    @SafeVarargs
    public static SpiritRiteTypeBuilder create(SpiritHolder<MalumSpiritType>... spirits) {
        return new SpiritRiteTypeBuilder(spirits);
    }

    @SafeVarargs
    public SpiritRiteTypeBuilder(SpiritHolder<MalumSpiritType>... spirits) {
        this(Arrays.asList(spirits));
    }
    public SpiritRiteTypeBuilder(List<SpiritHolder<MalumSpiritType>> spirits) {
        this.spirits = new ArrayList<>(spirits);
    }

    public SpiritRiteTypeBuilder effect(Supplier<SpiritRiteEffect> effect) {
        this.effect = effect.get();
        return this;
    }

    public SpiritRiteTypeBuilder corrupted() {
        this.isCorrupted = true;
        return this;
    }

    public SpiritRiteType build() {
        if (effect == null) {
            throw new IllegalStateException("SpiritRiteType must have an effect.");
        }
        if (spirits.isEmpty()) {
            throw new IllegalStateException("SpiritRiteType must have at least one spirit.");
        }
        return new SpiritRiteType(effect, isCorrupted, spirits);
    }
}
