package com.sammy.malum.common.creativetab;

import team.lodestar.lodestone.modules.toolkit.creative_tab.CategorizedBuilder;

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
                                BROKEN_BLADE, SHAPED_SLAB
                        ).nextLine()
                        .addItems(
                                IRON_CROWN, MEDITATING_EFFIGY
                        )
                        .bake();
                createCategory(DUNGEON_ARCHITECTURE)
                        .addItems(
                                ODD_SCRIPTURES_I, ODD_SCRIPTURES_II, ODD_SCRIPTURES_III,
                                ODD_SCRIPTURES_IV, ODD_SCRIPTURES_V, ODD_SCRIPTURES_VI,
                                ODD_SCRIPTURES_VII, ODD_SCRIPTURES_VIII, ODD_SCRIPTURES_IX
                        )
                        .bake();
                createCategory(DUNGEON_ODDITIES)
                        .addItems(
                                WRITHING_FLESH, COLUMNAR_FLESH, FLESHBULB
                        )
                        .bake();
        }
}