package com.sammy.malum.client.screen.codex.entries;

import com.sammy.malum.client.screen.codex.pages.*;
import com.sammy.malum.client.screen.codex.pages.recipe.vanilla.*;
import com.sammy.malum.client.screen.codex.pages.text.*;
import com.sammy.malum.client.screen.codex.screens.progression.*;
import com.sammy.malum.registry.common.content.MalumContent;
import net.minecraft.world.item.*;

import static com.sammy.malum.client.screen.codex.WidgetDesignType.*;
import static com.sammy.malum.client.screen.codex.WidgetDesignType.FillingType.*;
import static com.sammy.malum.client.screen.codex.WidgetDesignType.FillingType.PAPER;
import static com.sammy.malum.client.screen.codex.WidgetDesignType.FrameType.RUNEWOOD;
import static com.sammy.malum.client.screen.codex.display.DisplayedGizmo.item;
import static net.minecraft.world.item.Items.*;

public class IntroductionEntries {

    public static void setupEntries(ArcanaProgressionScreen screen) {
        Item EMPTY = ItemStack.EMPTY.getItem();

//        var soulstoneAndBrillianceReexamination = BookEntry.create("spirit_minerals.reexamination")
//                .addPage(new HeadlineTextPage("spirit_minerals.reexamination"))
//                .addPage(new TextPage("spirit_minerals.reexamination.2"))
//                .afterUmbralCrystal();
//
//        var cthonicGoldReexamination = BookEntry.create("cthonic_gold.reexamination")
//                .addPage(new HeadlineTextPage("cthonic_gold.reexamination"))
//                .addPage(new TextPage("cthonic_gold.reexamination.2"))
//                .afterUmbralCrystal();


        screen.addEntry("introduction", 0, 0, b -> b
                .configureWidget(w -> w
                        .setIcon(item(MalumContent.ENCYCLOPEDIA_ARCANA))
                        .setDesign(GILDED, RUNEWOOD, PAPER)
                        .setOrigin()
                )
                .addPage(new HeadlineTextGizmoPage("introduction", item(MalumContent.ENCYCLOPEDIA_ARCANA)))
                .addPage(new TextPage("introduction.2"))
                .addPage(new TextPage("introduction.3"))
                .addPage(new TextPage("introduction.4"))
                .addPage(new TextPage("introduction.5"))
        );

        screen.addEntry("spirit_crystals", 1, 1, b -> b
                .configureWidget(w -> w
                        .setIcon(EntryCommons.SOUL_SHARD)
                        .setDesign(SMALL, RUNEWOOD, DARK)
                )
                .configureWidget(w -> w.setDesign(SMALL, RUNEWOOD, DARK))
                .addPage(new HeadlineTextGizmoPage("spirit_crystals", EntryCommons.SOUL_SHARD))
                .addPage(new TextPage("spirit_crystals.2"))
                .addPage(new TextPage("spirit_crystals.3"))
        );

        screen.addEntry("runewood", 0, 2, b -> b
                .configureWidget(w -> w.setIcon(item(RUNEWOOD_SAPLING)))
                .addPage(new HeadlineTextGizmoPage("runewood", item(RUNEWOOD_SAPLING)))
                .addPage(PageSelectionPage.create(s -> s
                                .add(item(GRASS_BLOCK).addTitleAndSnippet("runewood.placement"),
                                        new HeadlineTextPage("runewood.placement"))
                                .add(item(RUNEWOOD_SAPLING).addTitleAndSnippet("runewood.genesis"),
                                        new HeadlineTextPage("runewood.genesis"))
                                .add(item(AZURE_RUNEWOOD_SAPLING).addTitleAndSnippet("runewood.azure"),
                                        new HeadlineTextPage("runewood.azure"))
                        )
                )
                .addPage(new HeadlineTextGizmoPage("runewood.arcane_charcoal", item(MalumContent.Materials.ARCANE_CHARCOAL)))
                .addPage(PageSelectionPage.create(s -> s
                                .add(item(MalumContent.Materials.ARCANE_CHARCOAL).addTitleAndSnippet("runewood.arcane_charcoal.smelting"), new SmeltingPage(item(RUNEWOOD_LOG), item(MalumContent.Materials.ARCANE_CHARCOAL)))
                                .add(item(BLOCK_OF_ARCANE_CHARCOAL).addTitleAndSnippet("runewood.arcane_charcoal.compacting"), CraftingPage.fullBlock(item(BLOCK_OF_ARCANE_CHARCOAL), item(MalumContent.Materials.ARCANE_CHARCOAL)))
                        )
                )
                .addPage(new HeadlineTextGizmoPage("runewood.runic_sap", item(MalumContent.Materials.RUNIC_SAP_BOTTLE)))
                .addPage(PageSelectionPage.create(s -> s
                                .add(item(STRIPPED_SAPPY_RUNEWOOD_LOG).addTitleAndSnippet("runewood.runic_sap.stripping"), InteractionPage.stripping(item(SAPPY_RUNEWOOD_LOG), item(STRIPPED_SAPPY_RUNEWOOD_LOG)))
                                .add(item(MalumContent.Materials.RUNIC_SAP_BOTTLE).addTitleAndSnippet("runewood.runic_sap.bottling"), InteractionPage.bottling(item(STRIPPED_SAPPY_RUNEWOOD_LOG), item(MalumContent.Materials.RUNIC_SAP_BOTTLE)))
                                .add(item(MalumContent.Materials.RUNIC_SAPBALL).addTitleAndSnippet("runewood.runic_sap.mixing"), new CraftingPage(item(MalumContent.Materials.RUNIC_SAPBALL), c -> c.top(item(WHEAT)).middle(item(MalumContent.Materials.RUNIC_SAP_BOTTLE))))
                        )
                )
        );

        screen.addEntry("arcane_wonders", -1, 3, b -> b
                .configureWidget(w -> w
                        .setIcon(EntryCommons.OVERWORLD)
                        .setDesign(SMALL, RUNEWOOD, DARK)
                )
                .addPage(new HeadlineTextGizmoPage("arcane_wonders", EntryCommons.OVERWORLD))
                .addPage(new TextPage("arcane_wonders.2"))
                .addPage(new TextPage("arcane_wonders.3"))
                .addPage(new TextPage("arcane_wonders.4"))
        );

        screen.addEntry("soulstone", 0, 4, b -> b
                .configureWidget(w -> w.setIcon(item(MalumContent.Materials.REFINED_SOULSTONE)))
                .addPage(new HeadlineTextGizmoPage("soulstone", item(MalumContent.Materials.REFINED_SOULSTONE)))
                .addPage(new TextPage("soulstone.2"))
                .addPage(PageSelectionPage.create(s -> s
                                .add(item(MalumContent.Materials.REFINED_SOULSTONE), new SmeltingPage(item(MalumContent.Materials.RAW_SOULSTONE), item(MalumContent.Materials.REFINED_SOULSTONE)))
                                .add(item(BLOCK_OF_SOULSTONE), CraftingPage.fullBlock(item(BLOCK_OF_SOULSTONE), item(MalumContent.Materials.REFINED_SOULSTONE)))
                                .add(item(BLOCK_OF_RAW_SOULSTONE), CraftingPage.fullBlock(item(BLOCK_OF_RAW_SOULSTONE), item(MalumContent.Materials.RAW_SOULSTONE)))
                        )
                )
//                .addReference(new EntryReference(UMBRAL_SPIRIT, soulstoneAndBrillianceReexamination))
        );


//        screen.addEntry("natural_quartz", 3, 1, b -> b
//                .configureWidget(w -> w.setIcon(NATURAL_QUARTZ).setDesign(SMALL, RUNEWOOD, PAPER))
//                .addPage(new HeadlineTextItemPage("natural_quartz", NATURAL_QUARTZ.get()))
//        );
//
//        screen.addEntry("blazing_quartz", 4, 2, b -> b
//                .configureWidget(w -> w.setIcon(BLAZING_QUARTZ).setDesign(SMALL, RUNEWOOD, PAPER))
//                .addPage(new HeadlineTextItemPage("blazing_quartz", BLAZING_QUARTZ.get()))
//                .addPage(CraftingPage.fullPage(BLOCK_OF_BLAZING_QUARTZ.get(), BLAZING_QUARTZ.get()))
//        );
//
//        screen.addEntry("brilliance", -3, 1, b -> b
//                .configureWidget(w -> w.setIcon(RAW_BRILLIANCE).setDesign(SMALL, RUNEWOOD, PAPER))
//                .addPage(new HeadlineTextItemPage("brilliance", RAW_BRILLIANCE.get()))
//                .addPage(new TextPage("brilliance.2"))
//                .addPage(new TextPage("brilliance.3"))
//                .addPage(PageSelectionPage.create(s -> s
//                                .add(REFINED_BRILLIANCE.get(), new SmeltingPage(RAW_BRILLIANCE.get().getDefaultInstance(), new ItemStack(REFINED_BRILLIANCE.get(), 2))
//                                )
//                                .add(BLOCK_OF_BRILLIANCE.get(), CraftingPage.fullPage(BLOCK_OF_BRILLIANCE.get(), REFINED_BRILLIANCE.get())
//                                )
//                                .add(BLOCK_OF_RAW_BRILLIANCE.get(), CraftingPage.fullPage(BLOCK_OF_RAW_BRILLIANCE.get(), RAW_BRILLIANCE.get())
//                                )
//                                .add(EXPERIENCE_BOTTLE, CraftingPage.shapeless(EXPERIENCE_BOTTLE, EMPTY, REFINED_BRILLIANCE.get(), EMPTY, EMPTY, GLASS_BOTTLE)
//                                )
//                        )
//                )
////                .addReference(new EntryReference(UMBRAL_SPIRIT, soulstoneAndBrillianceReexamination))
//        );
//
//        screen.addEntry("cthonic_gold", -4, 2, b -> b
//                .configureWidget(w -> w.setIcon(CTHONIC_GOLD).setDesign(SMALL, RUNEWOOD, PAPER))
//                .addPage(new HeadlineTextItemPage("cthonic_gold", CTHONIC_GOLD.get()))
//                .addPage(new TextPage("cthonic_gold.2"))
//                .addPage(new TextPage("cthonic_gold.3"))
//                .addPage(new TextPage("cthonic_gold.4"))
////                .addReference(new EntryReference(UMBRAL_SPIRIT, cthonicGoldReexamination))
//        );
//
//        screen.addEntry("soulstone", -1, 2, b -> b
//                .configureWidget(w -> w.setIcon(REFINED_SOULSTONE))
//                .addPage(new HeadlineTextItemPage("soulstone", REFINED_SOULSTONE.get()))
//                .addPage(new TextPage("soulstone.2"))
//                .addPage(new SmeltingPage(new ItemStack(RAW_SOULSTONE.get()), new ItemStack(REFINED_SOULSTONE.get(), 2)))
//                .addPage(new CyclingPage(
//                        CraftingPage.fullPage(BLOCK_OF_SOULSTONE.get(), REFINED_SOULSTONE.get()),
//                        CraftingPage.fullPage(BLOCK_OF_RAW_SOULSTONE.get(), RAW_SOULSTONE.get())
//                ))
////                .addReference(new EntryReference(UMBRAL_SPIRIT, soulstoneAndBrillianceReexamination))
//        );
//
//        screen.addEntry("scythes", 0, 3, b -> b
//                .configureWidget(w -> w.setIcon(CRUDE_SCYTHE))
//                .addPage(new HeadlineTextPage("scythes"))
//                .addPage(CraftingPage.scythePage(MalumItems.CRUDE_SCYTHE.get(), Items.IRON_INGOT, REFINED_SOULSTONE.get()))
//                .addPage(new TextPage("scythes.2"))
//                .addReference(new EntryReference(ENCHANTED_BOOK,
//                        BookEntry.create("scythes.enchanting")
//                                .addPage(new HeadlineTextPage("scythes.enchanting"))
//                                .addPage(new HeadlineTextPage("scythes.enchanting.spirit_plunder"))
//                                .addPage(new HeadlineTextPage("scythes.enchanting.haunted"))
//                                .addPage(new HeadlineTextPage("scythes.enchanting.animated"))
//                                .addPage(new HeadlineTextPage("scythes.enchanting.rebound"))
//                                .addPage(new HeadlineTextPage("scythes.enchanting.ascension"))
//                ))
//        );
//
//        screen.addEntry("spirit_infusion", 0, 5, b -> b
//                .configureWidget(w -> w.setIcon(SPIRIT_ALTAR).setDesign(GILDED, RUNEWOOD, PAPER))
//                .addPage(new HeadlineTextPage("spirit_infusion"))
//                .addPage(new CraftingPage(SPIRIT_ALTAR.get(), AIR, REFINED_SOULSTONE.get(), AIR, GOLD_INGOT, RUNEWOOD_PLANKS.get(), GOLD_INGOT, RUNEWOOD_PLANKS.get(), RUNEWOOD_PLANKS.get(), RUNEWOOD_PLANKS.get()))
//                .addPage(new TextPage("spirit_infusion.2"))
//                .addPage(new TextPage("spirit_infusion.3"))
//                .addPage(CraftingPage.itemPedestalPage(RUNEWOOD_ITEM_PEDESTAL.get(), RUNEWOOD_PLANKS.get(), RUNEWOOD_PLANKS_SLAB.get()))
//                .addPage(CraftingPage.itemStandPage(RUNEWOOD_ITEM_STAND.get(), RUNEWOOD_PLANKS.get(), RUNEWOOD_PLANKS_SLAB.get()))
//                .addReference(new EntryReference(HEX_ASH.get(),
//                        BookEntry.create("spirit_infusion.hex_ash")
//                                .addPage(new HeadlineTextPage("spirit_infusion.hex_ash"))
//                                .addPage(SpiritInfusionPage.fromOutput(HEX_ASH.get()))
//                ))
//                .addReference(new EntryReference(LIVING_FLESH.get(), BookEntry.create("spirit_infusion.living_flesh")
//                        .addPage(new HeadlineTextPage("spirit_infusion.living_flesh"))
//                        .addPage(SpiritInfusionPage.fromOutput(LIVING_FLESH.get()))
//                ))
//                .addReference(new EntryReference(ALCHEMICAL_CALX.get(), BookEntry.create("spirit_infusion.alchemical_calx")
//                        .addPage(new HeadlineTextPage("spirit_infusion.alchemical_calx"))
//                        .addPage(SpiritInfusionPage.fromOutput(ALCHEMICAL_CALX.get()))
//                ))
//        );
//
//        screen.addEntry("esoteric_reaping", 0, 6, b -> b
//                .configureWidget(w -> w.setIcon(GRIM_TALC))
//                .addPage(new HeadlineTextPage("esoteric_reaping"))
//                .addPage(new TextPage("esoteric_reaping.2"))
//                .addReference(new EntryReference(ROTTING_ESSENCE.get(), BookEntry.create("esoteric_reaping.rotting_essence")
//                        .addPage(new HeadlineTextItemPage("esoteric_reaping.rotting_essence", ROTTING_ESSENCE.get()))
//                        .addPage(new TextPage("esoteric_reaping.rotting_essence.2"))
//                ))
//                .addReference(new EntryReference(GRIM_TALC.get(), BookEntry.create("esoteric_reaping.grim_talc")
//                        .addPage(new HeadlineTextItemPage("esoteric_reaping.grim_talc", GRIM_TALC.get()))
//                ))
//                .addReference(new EntryReference(EERIE_WEAVE.get(), BookEntry.create("esoteric_reaping.eerie_weave")
//                        .addPage(new HeadlineTextItemPage("esoteric_reaping.eerie_weave", EERIE_WEAVE.get()))
//                        .addPage(new TextPage("esoteric_reaping.eerie_weave.2"))
//                ))
//                .addReference(new EntryReference(WARP_FLUX.get(), BookEntry.create("esoteric_reaping.warp_flux")
//                        .addPage(new HeadlineTextItemPage("esoteric_reaping.warp_flux", WARP_FLUX.get()))
////                        .addPage(new TextPage("esoteric_reaping.warp_flux.2"))
//                ))
//                .addReference(new EntryReference(WIND_NUCLEUS.get(), BookEntry.create("esoteric_reaping.core_keeping")
//                        .addPage(new HeadlineTextPage("esoteric_reaping.core_keeping"))
//                        .addPage(new TextPage("esoteric_reaping.core_keeping.2"))
//                        .addPage(new TextPage("esoteric_reaping.core_keeping.3"))
//                        .addPage(new TextPage("esoteric_reaping.core_keeping.4"))
//                        .addPage(new HeadlineTextItemPage("esoteric_reaping.core_keeping.wind_nucleus", WIND_NUCLEUS.get()))
//                        .addPage(new HeadlineTextItemPage("esoteric_reaping.core_keeping.pyre_nucleus", PYRE_NUCLEUS.get()))
//                ))
//        );
//
//        screen.addEntry("primary_arcana", -2, 4, b -> b
//                .configureWidget(w -> w.setIcon(SACRED_SPIRIT))
//                .addPage(new HeadlineTextItemPage("primary_arcana.sacred", "primary_arcana.sacred.1", SACRED_SPIRIT.get()))
//                .addPage(new TextPage("primary_arcana.sacred.2"))
//                .addPage(new HeadlineTextItemPage("primary_arcana.wicked", "primary_arcana.wicked.1", WICKED_SPIRIT.get()))
//                .addPage(new TextPage("primary_arcana.wicked.2"))
//                .addPage(new HeadlineTextItemPage("primary_arcana.arcane", "primary_arcana.arcane.1", ARCANE_SPIRIT.get()))
//                .addPage(new TextPage("primary_arcana.arcane.2"))
//                .addPage(new TextPage("primary_arcana.arcane.3"))
//        );
//
//        screen.addEntry("elemental_arcana", 2, 4, b -> b
//                .configureWidget(w -> w.setIcon(EARTHEN_SPIRIT))
//                .addPage(new HeadlineTextItemPage("elemental_arcana.aerial", "elemental_arcana.aerial.1", AERIAL_SPIRIT.get()))
//                .addPage(new TextPage("elemental_arcana.aerial.2"))
//                .addPage(new HeadlineTextItemPage("elemental_arcana.earthen", "elemental_arcana.earthen.1", EARTHEN_SPIRIT.get()))
//                .addPage(new TextPage("elemental_arcana.earthen.2"))
//                .addPage(new HeadlineTextItemPage("elemental_arcana.infernal", "elemental_arcana.infernal.1", INFERNAL_SPIRIT.get()))
//                .addPage(new TextPage("elemental_arcana.infernal.2"))
//                .addPage(new HeadlineTextItemPage("elemental_arcana.aqueous", "elemental_arcana.aqueous.1", AQUEOUS_SPIRIT.get()))
//                .addPage(new TextPage("elemental_arcana.aqueous.2"))
//        );
//
//        screen.addEntry("eldritch_arcana", 0, 7, b -> b
//                .configureWidget(w -> w.setIcon(ELDRITCH_SPIRIT))
//                .addPage(new HeadlineTextItemPage("eldritch_arcana", "eldritch_arcana.1", ELDRITCH_SPIRIT.get()))
//                .addPage(new TextPage("eldritch_arcana.2"))
//        );
    }
}
