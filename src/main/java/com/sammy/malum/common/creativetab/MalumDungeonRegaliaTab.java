package com.sammy.malum.common.creativetab;

import team.lodestar.lodestone.modules.toolkit.creative_tab.CategorizedBuilder;

import static com.sammy.malum.registry.common.MalumContent.DungeonBlockSets.*;
import static com.sammy.malum.registry.common.MalumContent.DungeonGear.*;

public class MalumDungeonRegaliaTab extends AbstractMalumCreativeTab {

        public static final String DUNGEON_REGALIA = "dungeon_regalia";
        public static final String DUNGEON_ODDITIES = "dungeon_distortions";

        public MalumDungeonRegaliaTab(CategorizedBuilder categorizedBuilder) {
                super(categorizedBuilder);
        }

        public void buildCategories() {

                createCategory(DUNGEON_REGALIA)
                        .addItems(
                                SHAPED_SLAB
                        ).nextLine()
                        .addItems(
                                IRON_CROWN, MEDITATING_EFFIGY
                        )
                        .bake();
                createCategory(DUNGEON_ODDITIES)
                        .addItems(
                                WRITHING_FLESH, COLUMNAR_FLESH, FLESHBULB
                        )
                        .bake();
        }
}