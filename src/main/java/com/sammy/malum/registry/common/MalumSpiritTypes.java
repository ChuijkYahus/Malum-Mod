package com.sammy.malum.registry.common;

import com.sammy.malum.*;
import com.sammy.malum.core.systems.registry.*;
import com.sammy.malum.core.systems.spirit.*;
import com.sammy.malum.core.systems.spirit.type.*;
import com.sammy.malum.registry.common.item.*;

import net.minecraft.core.*;
import net.minecraft.resources.*;
import team.lodestar.lodestone.systems.easing.*;

import java.awt.*;
import java.util.List;

public class MalumSpiritTypes {

    public static ResourceKey<Registry<MalumSpiritType>> SPIRIT_TYPES_KEY = ResourceKey.createRegistryKey(MalumMod.malumPath("spirit_types"));
    public static final DeferredSpiritTypes SPIRIT_TYPES = DeferredSpiritTypes.create(MalumMod.MALUM);
    public static final Registry<MalumSpiritType> SPIRIT_TYPES_REGISTRY = SPIRIT_TYPES.makeRegistry(builder -> builder.sync(true));

    public static SpiritColorProperties SACRED_COLORS() {
        return SpiritColorProperties.create(new Color(238, 44, 136), new Color(40, 143, 243))
                .setColorCoefficient(0.8f).setColorEasing(Easing.QUAD_IN).build();
    }

    public static SpiritColorProperties WICKED_COLORS() {
        return SpiritColorProperties.create(new Color(121, 44, 236), new Color(72, 21, 255))
                .setColorCoefficient(0.8f).setColorEasing(Easing.SINE_IN_OUT).build();
    }

    public static SpiritColorProperties ARCANE_COLORS() {
        return SpiritColorProperties.create(new Color(213, 70, 255), new Color(32, 222, 229))
                .setColorCoefficient(1.1f).setColorEasing(Easing.QUAD_IN).brightenItemColor(1).build();
    }

    public static SpiritColorProperties ELDRITCH_COLORS() {
        return SpiritColorProperties.create(new Color(203, 12, 248), new Color(24, 78, 164))
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
        return SpiritColorProperties.create(new Color(19, 5, 24), new Color(7, 1, 1))
                .setItemColor(Color.WHITE).build();
    }

    public static final SpiritHolder<MalumSpiritType> SACRED_SPIRIT = SPIRIT_TYPES.register("sacred", () -> new MalumSpiritType(SACRED_COLORS(), MalumItems.SACRED_SPIRIT));
    public static final SpiritHolder<MalumSpiritType> WICKED_SPIRIT = SPIRIT_TYPES.register("wicked", () -> new MalumSpiritType(WICKED_COLORS(), MalumItems.WICKED_SPIRIT));
    public static final SpiritHolder<MalumSpiritType> ARCANE_SPIRIT = SPIRIT_TYPES.register("arcane", () -> new MalumSpiritType(ARCANE_COLORS(), MalumItems.ARCANE_SPIRIT));
    public static final SpiritHolder<MalumSpiritType> ELDRITCH_SPIRIT = SPIRIT_TYPES.register("eldritch", () -> new MalumSpiritType(ELDRITCH_COLORS(), MalumItems.ELDRITCH_SPIRIT));
    public static final SpiritHolder<MalumSpiritType> AERIAL_SPIRIT = SPIRIT_TYPES.register("aerial", () -> new MalumSpiritType(AERIAL_COLORS(), MalumItems.AERIAL_SPIRIT));
    public static final SpiritHolder<MalumSpiritType> AQUEOUS_SPIRIT = SPIRIT_TYPES.register("aqueous", () -> new MalumSpiritType(AQUEOUS_COLORS(), MalumItems.AQUEOUS_SPIRIT));
    public static final SpiritHolder<MalumSpiritType> EARTHEN_SPIRIT = SPIRIT_TYPES.register("earthen", () -> new MalumSpiritType(EARTHEN_COLORS(), MalumItems.EARTHEN_SPIRIT));
    public static final SpiritHolder<MalumSpiritType> INFERNAL_SPIRIT = SPIRIT_TYPES.register("infernal", () -> new MalumSpiritType(INFERNAL_COLORS(), MalumItems.INFERNAL_SPIRIT));
    public static final SpiritHolder<MalumSpiritType> UMBRAL_SPIRIT = SPIRIT_TYPES.register("umbral", () -> new UmbralSpiritType(UMBRAL_COLORS(), MalumItems.UMBRAL_SPIRIT));
}