package com.sammy.malum.common.creativetab;

import com.sammy.malum.common.creativetab.button.ItemChoiceEntry;
import com.sammy.malum.registry.common.MalumContent;
import team.lodestar.lodestone.modules.toolkit.creative_tab.CategorizedBuilder;

import static com.sammy.malum.registry.common.MalumContent.*;
import static com.sammy.malum.registry.common.MalumContent.DungeonBlockSets.*;
import static com.sammy.malum.registry.common.MalumContent.DungeonGear.*;

public class MalumDungeonRegaliaTab extends AbstractMalumCreativeTab {

        public static final String DUNGEON_REGALIA = "dungeon_regalia";
        public static final String DUNGEON_ARCHITECTURE = "dungeon_architecture";
        public static final String DUNGEON_ODDITIES = "dungeon_distortions";

        public MalumDungeonRegaliaTab(CategorizedBuilder categorizedBuilder) {
                super(categorizedBuilder);
        }

        public void buildCategories() {

                createCategory(DUNGEON_REGALIA)
                        .addItems(
                                OMINOUS_ALTAR, OMINOUS_OBELISK
                        )
                        .addItems(
                                BROKEN_BLADE, SHAPED_SLAB
                        ).nextLine()
                        .addItems(
                                IRON_CROWN, VEILED_EFFIGY, CORRUPT_EFFIGY, CRACKED_EFFIGY
                        )
                        .bake();
                createCategory(DUNGEON_ARCHITECTURE)
                        .addItems(
                                ODD_SCRIPTURES_I, ODD_SCRIPTURES_II, ODD_SCRIPTURES_III,
                                ODD_SCRIPTURES_IV, ODD_SCRIPTURES_V, ODD_SCRIPTURES_VI,
                                ODD_SCRIPTURES_VII, ODD_SCRIPTURES_VIII, ODD_SCRIPTURES_IX
                        )
                        .addItems(
                                DROSS_STONE, POLISHED_DROSS_STONE, DROSS_STONE_BRICKS,
                                DROSS_STONE_TILES, DROSS_STONE_MOSAIC,
                                DARK_DROSS_TILES, GRAY_DROSS_TILES
                        )
                        .addItems(
                                DROSS_STONE_COLUMN, DROSS_STONE_ALTAR
                        ).nextLine()
                        .addItems(
                                DROSS_STONE_STAIRS, POLISHED_DROSS_STONE_STAIRS, DROSS_STONE_BRICKS_STAIRS,
                                DROSS_STONE_TILES_STAIRS, DROSS_STONE_MOSAIC_STAIRS,
                                DARK_DROSS_TILES_STAIRS, GRAY_DROSS_TILES_STAIRS
                        )
                        .addItems(
                                CUT_DROSS_STONE, CHISELED_DROSS_STONE
                        ).nextLine()
                        .addItems(
                                DROSS_STONE_SLAB, POLISHED_DROSS_STONE_SLAB, DROSS_STONE_BRICKS_SLAB,
                                DROSS_STONE_TILES_SLAB, DROSS_STONE_MOSAIC_SLAB,
                                DARK_DROSS_TILES_SLAB, GRAY_DROSS_TILES_SLAB
                        )
                        .addItems(
                                DROSS_STONE_ITEM_PEDESTAL, DROSS_STONE_ITEM_STAND
                        ).nextLine()
                        .addItems(
                                DROSS_STONE_WALL, POLISHED_DROSS_STONE_WALL, DROSS_STONE_BRICKS_WALL,
                                DROSS_STONE_TILES_WALL, DROSS_STONE_MOSAIC_WALL,
                                DARK_DROSS_TILES_WALL, GRAY_DROSS_TILES_WALL
                        )
                        .addItems(
                                DROSS_STONE_PRESSURE_PLATE, DROSS_STONE_BUTTON
                        )
                        .bake();
                createCategory(DUNGEON_ODDITIES)
                        .addItems(
                                WRITHING_FLESH, COLUMNAR_FLESH, FLESHBULB
                        )
                        .bake();
        }
}