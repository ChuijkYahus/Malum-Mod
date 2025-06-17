package com.sammy.malum.registry.common;

import com.sammy.malum.registry.common.block.*;
import com.sammy.malum.registry.common.item.*;

import static com.sammy.malum.MalumMod.malumPath;

public class MalumRegistryAliases {

    public static void registerAliases() {
        MalumBlocks.BLOCKS.addAlias(malumPath("soulwood_growth"), malumPath("soulwood_sapling"));
        MalumItems.ITEMS.addAlias(malumPath("soulwood_growth"), malumPath("soulwood_sapling"));

        MalumItems.ITEMS.addAlias(malumPath("rune_of_aliment_cleansing"), malumPath("rune_of_ailment_cleansing"));
        MalumItems.ITEMS.addAlias(malumPath("rune_of_idle_restoration"), malumPath("rune_of_vitality"));
        MalumItems.ITEMS.addAlias(malumPath("rune_of_reactive_shielding"), malumPath("rune_of_protection"));
        MalumItems.ITEMS.addAlias(malumPath("rune_of_sacrificial_empowerment"), malumPath("rune_of_radial_empowerment"));
        MalumItems.ITEMS.addAlias(malumPath("rune_of_the_heretic"), malumPath("rune_of_heresy"));
        MalumItems.ITEMS.addAlias(malumPath("rune_of_toughness"), malumPath("rune_of_indomitability"));
    }
}
