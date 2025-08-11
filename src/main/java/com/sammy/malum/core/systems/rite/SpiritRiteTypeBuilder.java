package com.sammy.malum.core.systems.rite;

import com.sammy.malum.core.systems.registry.*;
import com.sammy.malum.core.systems.registry.rite.*;
import com.sammy.malum.core.systems.rite.effect.*;
import com.sammy.malum.core.systems.spirit.type.SpiritArcanaType;
import com.sammy.malum.registry.common.magic.*;

import java.util.*;
import java.util.function.*;

public class SpiritRiteTypeBuilder {

    private final List<SpiritHolder<SpiritArcanaType>> spirits;
    private boolean isCorrupted = false;

    public static SpiritRiteTypeBuilder minorTotemRite(SpiritHolder<SpiritArcanaType> spirit) {
        return new SpiritRiteTypeBuilder(List.of(MalumSpiritTypes.ARCANE_SPIRIT, spirit, spirit));
    }

    public static SpiritRiteTypeBuilder majorTotemRite(SpiritHolder<SpiritArcanaType> spirit) {
        return new SpiritRiteTypeBuilder(List.of(MalumSpiritTypes.ELDRITCH_SPIRIT, MalumSpiritTypes.ARCANE_SPIRIT, spirit, spirit));
    }

    public static SpiritRiteTypeBuilder specialTotemRite(SpiritHolder<SpiritArcanaType> spirit) {
        return new SpiritRiteTypeBuilder(List.of(spirit, spirit, spirit, spirit, spirit));
    }

    @SafeVarargs
    public static SpiritRiteTypeBuilder create(SpiritHolder<SpiritArcanaType>... spirits) {
        return new SpiritRiteTypeBuilder(spirits);
    }

    @SafeVarargs
    public SpiritRiteTypeBuilder(SpiritHolder<SpiritArcanaType>... spirits) {
        this(Arrays.asList(spirits));
    }

    public SpiritRiteTypeBuilder(List<SpiritHolder<SpiritArcanaType>> spirits) {
        this.spirits = new ArrayList<>(spirits);
    }

    public SpiritRiteTypeBuilder setCorrupted() {
        this.isCorrupted = true;
        return this;
    }

    public SpiritRiteType build(RiteEffectHolder<? extends SpiritRiteEffect> effect) {
        if (spirits.isEmpty()) {
            throw new IllegalStateException("SpiritRiteType must have at least one spirit.");
        }
        return new SpiritRiteType(effect, isCorrupted, spirits);
    }
}