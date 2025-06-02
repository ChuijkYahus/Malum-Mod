package com.sammy.malum.registry.common;

import com.sammy.malum.registry.common.block.*;
import com.sammy.malum.registry.common.item.*;

import static com.sammy.malum.MalumMod.malumPath;

public class MalumRegistryAliases {

    public static void registerAliases() {
        MalumBlocks.BLOCKS.addAlias(malumPath("soulwood_growth"), malumPath("soulwood_sapling"));
        MalumItems.ITEMS.addAlias(malumPath("soulwood_growth"), malumPath("soulwood_sapling"));

        MalumItems.ITEMS.addAlias(malumPath("rune_of_aliment_cleansing"), malumPath("rune_of_ailment_cleansing"));

    }
}
