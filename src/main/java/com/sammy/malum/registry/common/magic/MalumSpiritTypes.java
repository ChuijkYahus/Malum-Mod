package com.sammy.malum.registry.common.magic;

import com.mojang.serialization.*;
import com.sammy.malum.*;
import com.sammy.malum.core.systems.registry.*;
import com.sammy.malum.core.systems.rite.*;
import com.sammy.malum.core.systems.spirit.*;
import com.sammy.malum.core.systems.spirit.umbral.UmbralSpiritArcanaType;
import com.sammy.malum.registry.common.MalumContent;

import com.sammy.malum.registry.common.magic.rite.*;
import net.minecraft.core.*;
import net.minecraft.resources.*;
import team.lodestar.lodestone.modules.core.easing.Easing;

import java.awt.*;

public class MalumSpiritTypes {

    public static ResourceKey<Registry<SpiritArcanaType>> SPIRIT_TYPES_KEY = ResourceKey.createRegistryKey(MalumMod.malumPath("spirit_types"));
    public static final DeferredSpiritTypes SPIRIT_TYPES = DeferredSpiritTypes.create(MalumMod.MALUM);
    public static final Registry<SpiritArcanaType> SPIRIT_TYPES_REGISTRY = SPIRIT_TYPES.makeRegistry(builder -> builder.sync(true)
            .defaultKey(MalumMod.malumPath("arcane")));


    public static SpiritColorProperties SACRED_COLORS() {
        return SpiritColorProperties.create(new Color(238, 44, 136), new Color(40, 143, 243))
                .setColorCoefficient(0.8f).setColorEasing(Easing.QUAD_IN).build();
    }

    public static SpiritColorProperties WICKED_COLORS() {
        return SpiritColorProperties.create(new Color(121, 44, 236), new Color(72, 21, 255))
                .setColorCoefficient(0.8f).setColorEasing(Easing.SINE_IN_OUT).build();
    }

    public static SpiritColorProperties ARCANE_COLORS() {
        return SpiritColorProperties.create(new Color(234, 99, 255), new Color(32, 222, 229))
                .setColorCoefficient(1.1f).setColorEasing(Easing.QUAD_IN).brightenItemColor(1).build();
    }

    public static SpiritColorProperties ELDRITCH_COLORS() {
        return SpiritColorProperties.create(new Color(193, 11, 235), new Color(24, 78, 164))
                .setColorCoefficient(0.9f).setColorEasing(Easing.BOUNCE_IN_OUT).darkenItemColor(1).build();
    }

    public static SpiritColorProperties AERIAL_COLORS() {
        return SpiritColorProperties.create(new Color(75, 243, 218), new Color(243, 218, 75))
                .setColorCoefficient(1.1f).setColorEasing(Easing.SINE_IN).build();
    }

    public static SpiritColorProperties AQUEOUS_COLORS() {
        return SpiritColorProperties.create(new Color(29, 100, 232), new Color(41, 238, 133))
                .setColorCoefficient(0.8f).setColorEasing(Easing.QUAD_IN_OUT).brightenItemColor(1).build();
    }

    public static SpiritColorProperties EARTHEN_COLORS() {
        return SpiritColorProperties.create(new Color(72, 238, 25), new Color(208, 26, 65))
                .setColorCoefficient(0.9f).setColorEasing(Easing.SINE_IN).brightenItemColor(1).build();
    }

    public static SpiritColorProperties INFERNAL_COLORS() {
        return SpiritColorProperties.create(new Color(250, 154, 31), new Color(210, 39, 150))
                .setColorCoefficient(0.9f).setColorEasing(Easing.SINE_IN_OUT).brightenItemColor(2).build();
    }

    public static SpiritColorProperties UMBRAL_COLORS() {
        return SpiritColorProperties.create(new Color(65, 27, 98), new Color(7, 1, 1))
                .setItemColor(Color.WHITE).build();
    }

    public static final SpiritHolder<SpiritArcanaType> SACRED_SPIRIT = SPIRIT_TYPES.register("sacred", () -> new SpiritArcanaType(SACRED_COLORS(), MalumContent.Spirits.SACRED_SPIRIT));
    public static final SpiritHolder<SpiritArcanaType> WICKED_SPIRIT = SPIRIT_TYPES.register("wicked", () -> new SpiritArcanaType(WICKED_COLORS(), MalumContent.Spirits.WICKED_SPIRIT));
    public static final SpiritHolder<SpiritArcanaType> ARCANE_SPIRIT = SPIRIT_TYPES.register("arcane", () -> new SpiritArcanaType(ARCANE_COLORS(), MalumContent.Spirits.ARCANE_SPIRIT));
    public static final SpiritHolder<SpiritArcanaType> ELDRITCH_SPIRIT = SPIRIT_TYPES.register("eldritch", () -> new SpiritArcanaType(ELDRITCH_COLORS(), MalumContent.Spirits.ELDRITCH_SPIRIT));
    public static final SpiritHolder<SpiritArcanaType> AERIAL_SPIRIT = SPIRIT_TYPES.register("aerial", () -> new SpiritArcanaType(AERIAL_COLORS(), MalumContent.Spirits.AERIAL_SPIRIT));
    public static final SpiritHolder<SpiritArcanaType> AQUEOUS_SPIRIT = SPIRIT_TYPES.register("aqueous", () -> new SpiritArcanaType(AQUEOUS_COLORS(), MalumContent.Spirits.AQUEOUS_SPIRIT));
    public static final SpiritHolder<SpiritArcanaType> EARTHEN_SPIRIT = SPIRIT_TYPES.register("earthen", () -> new SpiritArcanaType(EARTHEN_COLORS(), MalumContent.Spirits.EARTHEN_SPIRIT));
    public static final SpiritHolder<SpiritArcanaType> INFERNAL_SPIRIT = SPIRIT_TYPES.register("infernal", () -> new SpiritArcanaType(INFERNAL_COLORS(), MalumContent.Spirits.INFERNAL_SPIRIT));
    public static final SpiritHolder<SpiritArcanaType> UMBRAL_SPIRIT = SPIRIT_TYPES.register("umbral", () -> new UmbralSpiritArcanaType(UMBRAL_COLORS(), MalumContent.Spirits.UMBRAL_SPIRIT));
}