package com.sammy.malum.registry.common;

import com.sammy.malum.registry.common.block.*;
import com.sammy.malum.registry.common.item.*;
import net.minecraft.resources.*;

import static com.sammy.malum.MalumMod.malumPath;

public class MalumRegistryAliases {

    public static void registerAliases() {
        fixUpRocks();

        addBlockAndItemAlias("block_of_astral_weave", "block_of_eerie_weave");
        MalumItems.ITEMS.addAlias(malumPath("astral_weave"), malumPath("eerie_weave"));

        MalumBlocks.BLOCKS.addAlias(malumPath("soulwood_growth"), malumPath("soulwood_sapling"));
        MalumItems.ITEMS.addAlias(malumPath("soulwood_growth"), malumPath("soulwood_sapling"));

        MalumItems.ITEMS.addAlias(malumPath("rune_of_aliment_cleansing"), malumPath("rune_of_ailment_cleansing"));
        MalumItems.ITEMS.addAlias(malumPath("rune_of_idle_restoration"), malumPath("rune_of_vitality"));
        MalumItems.ITEMS.addAlias(malumPath("rune_of_reactive_shielding"), malumPath("rune_of_protection"));
        MalumItems.ITEMS.addAlias(malumPath("rune_of_sacrificial_empowerment"), malumPath("rune_of_radial_empowerment"));
        MalumItems.ITEMS.addAlias(malumPath("rune_of_the_heretic"), malumPath("rune_of_heresy"));
        MalumItems.ITEMS.addAlias(malumPath("rune_of_toughness"), malumPath("rune_of_indomitability"));
    }

    public static void fixUpRocks() {
        addBlockAndItemAlias("smooth_tainted_rock", "tainted_rock");
        addBlockAndItemAlias("smooth_tainted_rock_slab", "tainted_rock_slab");
        addBlockAndItemAlias("smooth_tainted_rock_stairs", "tainted_rock_stairs");
        addBlockAndItemAlias("smooth_tainted_rock_wall", "tainted_rock_wall");

        addBlockAndItemAlias("small_tainted_rock_bricks", "tainted_rock_bricks");
        addBlockAndItemAlias("small_tainted_rock_bricks_slab", "tainted_rock_bricks_slab");
        addBlockAndItemAlias("small_tainted_rock_bricks_stairs", "tainted_rock_bricks_stairs");
        addBlockAndItemAlias("small_tainted_rock_bricks_wall", "tainted_rock_bricks_wall");

        addBlockAndItemAlias("runic_tainted_rock_bricks", "tainted_rock_bricks");
        addBlockAndItemAlias("runic_tainted_rock_bricks_slab", "tainted_rock_bricks_slab");
        addBlockAndItemAlias("runic_tainted_rock_bricks_stairs", "tainted_rock_bricks_stairs");
        addBlockAndItemAlias("runic_tainted_rock_bricks_wall", "tainted_rock_bricks_wall");

        addBlockAndItemAlias("runic_tainted_rock_tiles", "tainted_rock_tiles");
        addBlockAndItemAlias("runic_tainted_rock_tiles_slab", "tainted_rock_tiles_slab");
        addBlockAndItemAlias("runic_tainted_rock_tiles_stairs", "tainted_rock_tiles_stairs");
        addBlockAndItemAlias("runic_tainted_rock_tiles_wall", "tainted_rock_tiles_wall");

        addBlockAndItemAlias("runic_small_tainted_rock_bricks", "tainted_rock_mosaic");
        addBlockAndItemAlias("runic_small_tainted_rock_bricks_slab", "tainted_rock_mosaic_slab");
        addBlockAndItemAlias("runic_small_tainted_rock_bricks_stairs", "tainted_rock_mosaic_stairs");
        addBlockAndItemAlias("runic_small_tainted_rock_bricks_wall", "tainted_rock_mosaic_wall");

        addBlockAndItemAlias("tainted_rock_column_cap", "tainted_rock_column");

        addBlockAndItemAlias("checkered_tainted_rock", "cut_tainted_rock");

        addBlockAndItemAlias("smooth_twisted_rock", "twisted_rock");
        addBlockAndItemAlias("smooth_twisted_rock_slab", "twisted_rock_slab");
        addBlockAndItemAlias("smooth_twisted_rock_stairs", "twisted_rock_stairs");
        addBlockAndItemAlias("smooth_twisted_rock_wall", "twisted_rock_wall");

        addBlockAndItemAlias("small_twisted_rock_bricks", "twisted_rock_bricks");
        addBlockAndItemAlias("small_twisted_rock_bricks_slab", "twisted_rock_bricks_slab");
        addBlockAndItemAlias("small_twisted_rock_bricks_stairs", "twisted_rock_bricks_stairs");
        addBlockAndItemAlias("small_twisted_rock_bricks_wall", "twisted_rock_bricks_wall");

        addBlockAndItemAlias("runic_twisted_rock_bricks", "twisted_rock_bricks");
        addBlockAndItemAlias("runic_twisted_rock_bricks_slab", "twisted_rock_bricks_slab");
        addBlockAndItemAlias("runic_twisted_rock_bricks_stairs", "twisted_rock_bricks_stairs");
        addBlockAndItemAlias("runic_twisted_rock_bricks_wall", "twisted_rock_bricks_wall");

        addBlockAndItemAlias("runic_twisted_rock_tiles", "twisted_rock_tiles");
        addBlockAndItemAlias("runic_twisted_rock_tiles_slab", "twisted_rock_tiles_slab");
        addBlockAndItemAlias("runic_twisted_rock_tiles_stairs", "twisted_rock_tiles_stairs");
        addBlockAndItemAlias("runic_twisted_rock_tiles_wall", "twisted_rock_tiles_wall");

        addBlockAndItemAlias("runic_small_twisted_rock_bricks", "twisted_rock_mosaic");
        addBlockAndItemAlias("runic_small_twisted_rock_bricks_slab", "twisted_rock_mosaic_slab");
        addBlockAndItemAlias("runic_small_twisted_rock_bricks_stairs", "twisted_rock_mosaic_stairs");
        addBlockAndItemAlias("runic_small_twisted_rock_bricks_wall", "twisted_rock_mosaic_wall");

        addBlockAndItemAlias("twisted_rock_column_cap", "twisted_rock_column");

        addBlockAndItemAlias("checkered_twisted_rock", "cut_twisted_rock");
    }

    public static void addBlockAndItemAlias(String from, String to) {
        var fromId = malumPath(from);
        var toId = malumPath(to);
        MalumBlocks.BLOCKS.addAlias(fromId, toId);
        MalumItems.ITEMS.addAlias(fromId, toId);
    }
}
