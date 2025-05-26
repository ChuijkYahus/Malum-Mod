package com.sammy.malum.registry.common;

import com.sammy.malum.core.systems.spirit.*;
import com.sammy.malum.registry.common.item.*;

import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.systems.easing.*;

import java.awt.*;
import java.util.List;
import java.util.*;

public class MalumSpiritTypes {

    public static Map<String, MalumSpiritType> SPIRITS = new LinkedHashMap<>(); //convert to deferred register

    public static MalumSpiritType SACRED_SPIRIT = register(MalumSpiritType.create("sacred",
                    new SpiritVisualMotif(new Color(238, 44, 136), new Color(40, 143, 243), 0.8f, Easing.LINEAR),
                    MalumItems.SACRED_SPIRIT)
            .build());

    public static MalumSpiritType WICKED_SPIRIT = register(MalumSpiritType.create("wicked",
                    new SpiritVisualMotif(new Color(121, 44, 236), new Color(72, 21, 255),
                            0.8f, Easing.LINEAR),
                    MalumItems.WICKED_SPIRIT)
            .build());

    public static MalumSpiritType ARCANE_SPIRIT = register(MalumSpiritType.create("arcane",
                    new SpiritVisualMotif(new Color(213, 70, 255), new Color(32, 222, 229), 1.1f,
                            Easing.SINE_IN_OUT, c -> ColorHelper.brighter(c, 1)),
                    MalumItems.ARCANE_SPIRIT)
            .build());

    public static MalumSpiritType ELDRITCH_SPIRIT = register(MalumSpiritType.create("eldritch",
                    new SpiritVisualMotif(new Color(203, 12, 248), new Color(24, 78, 164),
                            0.9f, Easing.LINEAR, c -> ColorHelper.darker(c, 1)),
                    MalumItems.ELDRITCH_SPIRIT)
            .build());

    public static MalumSpiritType AERIAL_SPIRIT = register(MalumSpiritType.create("aerial",
                    new SpiritVisualMotif(new Color(75, 243, 218), new Color(243, 218, 75),
                            1.1f, Easing.SINE_IN, c -> ColorHelper.brighter(c, 1)),
                    MalumItems.AERIAL_SPIRIT)
            .build());

    public static MalumSpiritType AQUEOUS_SPIRIT = register(MalumSpiritType.create("aqueous",
                    new SpiritVisualMotif(new Color(29, 100, 232), new Color(41, 238, 133),
                            0.8f, Easing.SINE_IN_OUT, c -> ColorHelper.brighter(c, 1)),
                    MalumItems.AQUEOUS_SPIRIT)
            .build());

    public static MalumSpiritType EARTHEN_SPIRIT = register(MalumSpiritType.create("earthen",
                    new SpiritVisualMotif(new Color(72, 238, 25), new Color(208, 26, 65),
                            0.9f, Easing.SINE_IN, c -> ColorHelper.brighter(c, 1)),
                    MalumItems.EARTHEN_SPIRIT)
            .build());

    public static MalumSpiritType INFERNAL_SPIRIT = register(MalumSpiritType.create("infernal",
                    new SpiritVisualMotif(new Color(250, 154, 31), new Color(210, 39, 150),
                            0.9f, Easing.SINE_IN_OUT, c -> ColorHelper.brighter(c, 1)),
                    MalumItems.INFERNAL_SPIRIT)
            .build());

    public static SpiritTypeProperty SPIRIT_TYPE_PROPERTY = new SpiritTypeProperty("spirit_type", SPIRITS.values());

    public static MalumSpiritType UMBRAL_SPIRIT = register(MalumSpiritType.create("umbral",
                    new SpiritVisualMotif(new Color(19, 5, 24), new Color(7, 1, 1), 0.9f, Easing.SINE_IN_OUT, Color.WHITE, 4f),
                    MalumItems.UMBRAL_SPIRIT)
            .build(UmbralSpiritType::new));

    public static MalumSpiritType register(MalumSpiritType spiritType) {
        SPIRITS.put(spiritType.getIdentifier(), spiritType);
        return spiritType;
    }

    public static int getIndexForSpiritType(MalumSpiritType type) {
        List<MalumSpiritType> types = SPIRITS.values().stream().toList();
        return types.indexOf(type);
    }
}