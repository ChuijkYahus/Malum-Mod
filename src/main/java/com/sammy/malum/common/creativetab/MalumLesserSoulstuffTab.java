package com.sammy.malum.common.creativetab;

import com.sammy.malum.common.block.curiosities.decor.banner.SoulwovenBannerBlockItem;
import com.sammy.malum.registry.common.util.building.MinorBuildingSet;
import team.lodestar.lodestone.modules.toolkit.creative_tab.CategorizedBuilder;

import static com.sammy.malum.registry.common.MalumContent.*;

public class MalumLesserSoulstuffTab extends AbstractMalumCreativeTab {

        public MalumLesserSoulstuffTab(CategorizedBuilder categorizedBuilder) {
                super(categorizedBuilder);
        }

        public void buildCategories() {
                createCategory("soulstuff")
                        .addItems(
                                BlockSets.SACRED_SPIRITED_GLASS,
                                BlockSets.WICKED_SPIRITED_GLASS,
                                BlockSets.ARCANE_SPIRITED_GLASS,
                                BlockSets.ELDRITCH_SPIRITED_GLASS,
                                BlockSets.AERIAL_SPIRITED_GLASS,
                                BlockSets.AQUEOUS_SPIRITED_GLASS,
                                BlockSets.EARTHEN_SPIRITED_GLASS,
                                BlockSets.INFERNAL_SPIRITED_GLASS,
                                BlockSets.NULL_SPIRITED_GLASS
                        ).nextLine()
                        .addItems(
                                BlockSets.SACRED_VARNISHED_TERRACOTTA,
                                BlockSets.WICKED_VARNISHED_TERRACOTTA,
                                BlockSets.ARCANE_VARNISHED_TERRACOTTA,
                                BlockSets.ELDRITCH_VARNISHED_TERRACOTTA,
                                BlockSets.AERIAL_VARNISHED_TERRACOTTA,
                                BlockSets.AQUEOUS_VARNISHED_TERRACOTTA,
                                BlockSets.EARTHEN_VARNISHED_TERRACOTTA,
                                BlockSets.INFERNAL_VARNISHED_TERRACOTTA,
                                BlockSets.NULL_VARNISHED_TERRACOTTA
                        ).nextLine()
                        .addItems(SoulwovenBannerBlockItem::addBannerPatterns)
                        .bake();
                createCategory("common_rock")
                        .addItems(MinorBuildingSet::addCommonRock)
                        .bake();
                createCategory("ether")
                        .addItems(
                                BlockSets.ETHER,
                                BlockSets.ETHER_CANDLE,
                                BlockSets.ETHER_TORCH,
                                BlockSets.ETHER_BRAZIER,
                                BlockSets.ETHER_CRESSET
                        ).nextLine()
                        .addItems(
                                BlockSets.IRIDESCENT_ETHER,
                                BlockSets.IRIDESCENT_ETHER_CANDLE,
                                BlockSets.IRIDESCENT_ETHER_TORCH,
                                BlockSets.IRIDESCENT_ETHER_BRAZIER,
                                BlockSets.IRIDESCENT_ETHER_CRESSET
                        )
                        .bake();
                createCategory("runewood")
                        .addItems(
                                Materials.RUNIC_SAP_BOTTLE,
                                Materials.RUNIC_SAPBALL,
                                BlockSets.RUNEWOOD_SAPLING,
                                BlockSets.RUNEWOOD_LEAVES,
                                BlockSets.HANGING_RUNEWOOD_LEAVES,
                                BlockSets.AZURE_RUNEWOOD_SAPLING,
                                BlockSets.AZURE_RUNEWOOD_LEAVES,
                                BlockSets.HANGING_AZURE_RUNEWOOD_LEAVES
                        ).nextLine()
                        .addItems(BlockSets.RUNEWOOD_SET::addToCreativeTab)
                        .addItems(
                                BlockSets.RUNEWOOD_BOAT,
                                BlockSets.RUNEWOOD_CHEST_BOAT
                        )
                        .bake();
                createCategory("soulwood")
                        .addItems(
                                Materials.AZOIC_SAP_BOTTLE,
                                Materials.AZOIC_SAPBALL,
                                BlockSets.SOULWOOD_SAPLING,
                                BlockSets.SOULWOOD_LEAVES,
                                BlockSets.HANGING_SOULWOOD_LEAVES
                        ).nextLine()
                        .addItems(BlockSets.SOULWOOD_SET::addToCreativeTab)
                        .addItems(
                                BlockSets.SOULWOOD_BOAT,
                                BlockSets.SOULWOOD_CHEST_BOAT
                        )
                        .bake();
                createCategory("blight")
                        .addItems(
                                BlockSets.BLIGHTED_SOULWOOD,
                                Blight.COLUMNAR_BLIGHT,
                                Blight.BLIGHTED_EARTH,
                                Blight.BLIGHT,
                                Blight.BLIGHTED_GUNK,
                                Blight.BLIGHTPEARL,
                                Blight.BLIGHTROOT
                        ).nextLine()
                        .addItems(
                                Blight.SCARSTONE,
                                Blight.LARGE_STRANGE_CRYSTAL,
                                Blight.STRANGE_CRYSTAL,
                                Blight.STRANGEROOT
                        )
                        .bake();

                createCategory("tainted_rock")
                        .addItems(BlockSets.TAINTED_ROCK_SET::addToCreativeTab)
                        .bake();
                createCategory("twisted_rock")
                        .addItems(BlockSets.TWISTED_ROCK_SET::addToCreativeTab)
                        .bake();
        }
}