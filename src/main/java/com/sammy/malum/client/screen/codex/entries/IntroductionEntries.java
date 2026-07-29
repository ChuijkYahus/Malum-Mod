package com.sammy.malum.client.screen.codex.entries;

import com.sammy.malum.client.screen.codex.BookEntry;
import com.sammy.malum.client.screen.codex.PlacedEntryAcceptor;
import com.sammy.malum.client.screen.codex.objects.progression.SubspaceEntryObject;
import com.sammy.malum.client.screen.codex.pages.*;
import com.sammy.malum.client.screen.codex.pages.display.*;
import com.sammy.malum.client.screen.codex.pages.recipe.vanilla.*;
import com.sammy.malum.client.screen.codex.pages.text.HeadlineTextPage;
import com.sammy.malum.client.screen.codex.pages.text.TextPage;
import com.sammy.malum.client.screen.codex.screens.progression.*;
import com.sammy.malum.core.systems.spirit.SpiritLike;
import com.sammy.malum.registry.common.magic.MalumSpiritTypes;
import net.minecraft.world.item.*;

import static com.sammy.malum.client.screen.codex.WidgetDesignType.*;
import static com.sammy.malum.client.screen.codex.WidgetDesignType.FillingType.*;
import static com.sammy.malum.client.screen.codex.WidgetDesignType.FillingType.PAPER;
import static com.sammy.malum.client.screen.codex.WidgetDesignType.FrameType.RUNEWOOD;
import static com.sammy.malum.client.screen.codex.display.gizmo.DisplayedItem.item;
import static com.sammy.malum.client.screen.codex.pages.InteractionPage.*;
import static com.sammy.malum.client.screen.codex.pages.recipe.vanilla.CraftingPage.*;
import static com.sammy.malum.client.screen.codex.pages.text.HeadlineTextGizmoPage.headlineTextGizmoPage;
import static com.sammy.malum.client.screen.codex.pages.text.HeadlineTextPage.headlineText;
import static com.sammy.malum.client.screen.codex.pages.text.TextPage.textPage;
import static com.sammy.malum.registry.common.MalumContent.AlchemyAndMetallics.*;
import static com.sammy.malum.registry.common.MalumContent.BlockSets.*;
import static com.sammy.malum.registry.common.MalumContent.CompactBlocks.*;
import static com.sammy.malum.registry.common.MalumContent.ENCYCLOPEDIA_ARCANA;
import static com.sammy.malum.registry.common.MalumContent.Materials.*;
import static net.minecraft.world.item.Items.*;

public class IntroductionEntries {

    public static void setupEntries(ArcanaProgressionScreen screen) {
        Item EMPTY = ItemStack.EMPTY.getItem();

//        var soulstoneAndBrillianceReexamination = BookEntry.create("spirit_minerals.reexamination")
//                .addPage(headlineText("spirit_minerals.reexamination"))
//                .addPage(text("spirit_minerals.reexamination.2"))
//                .afterUmbralCrystal();
//
//        var cthonicGoldReexamination = BookEntry.create("cthonic_gold.reexamination")
//                .addPage(headlineText("cthonic_gold.reexamination"))
//                .addPage(text("cthonic_gold.reexamination.2"))
//                .afterUmbralCrystal();


        screen.addEntry("introduction", 0, 0)
                .configureWidget(w -> w.setIcon(item(ENCYCLOPEDIA_ARCANA)).setDesign(GILDED, RUNEWOOD, PAPER).setOrigin())
                .addPage(headlineTextGizmoPage("introduction", item(ENCYCLOPEDIA_ARCANA)))
                .addPage(textPage("introduction.2"))
                .addPage(textPage("introduction.3"))
                .addPage(textPage("introduction.4"))
                .addPage(textPage("introduction.5"));

        screen.addEntry("spirit_crystals", 1, 1)
                .configureWidget(w -> w.setIcon(CodexCommons.SOUL_SHARD).setDesign(SMALL, RUNEWOOD, DARK))
                .addPage(headlineTextGizmoPage("spirit_crystals", CodexCommons.SOUL_SHARD))
                .addPage(textPage("spirit_crystals.2"))
                .addPage(textPage("spirit_crystals.3"));

        screen.addEntry("runewood", 0, 2)
                .configureWidget(w -> w.setIcon(item(RUNEWOOD_SAPLING)))
                .addPage(headlineTextGizmoPage("runewood", item(RUNEWOOD_SAPLING)))
                .addPage(PageSelectionPage.create(s -> s
                                .add(item(GRASS_BLOCK), headlineText("runewood.placement"))
                                .add(item(RUNEWOOD_SAPLING), headlineText("runewood.genesis"))
                                .add(item(AZURE_RUNEWOOD_SAPLING), headlineText("runewood.azure"))
                        )
                )
                .addPage(headlineTextGizmoPage("runewood.arcane_charcoal", item(ARCANE_CHARCOAL)))
                .addPage(PageSelectionPage.create(s -> s
                                .add(item(ARCANE_CHARCOAL).setId("runewood.arcane_charcoal.smelting"),
                                        new SmeltingPage(item(RUNEWOOD_SET.log), item(ARCANE_CHARCOAL)))
                                .add(item(BLOCK_OF_ARCANE_CHARCOAL).setId("runewood.arcane_charcoal.compacting"),
                                        compacting(item(BLOCK_OF_ARCANE_CHARCOAL), item(ARCANE_CHARCOAL)))
                        )
                )
                .addPage(headlineTextGizmoPage("runewood.runic_sap", item(RUNIC_SAP_BOTTLE)))
                .addPage(PageSelectionPage.create(s -> s
                                .add(item(RUNEWOOD_SET.strippedSappyLog).setId("runewood.runic_sap.stripping"),
                                        stripping(item(RUNEWOOD_SET.sappyLog), item(RUNEWOOD_SET.strippedSappyLog)))
                                .add(item(RUNIC_SAP_BOTTLE).setId("runewood.runic_sap.bottling"),
                                        bottling(item(RUNEWOOD_SET.strippedSappyLog), item(RUNIC_SAP_BOTTLE)))
                                .add(item(RUNIC_SAPBALL).setId("runewood.runic_sap.mixing"),
                                        crafting(item(RUNIC_SAPBALL), c -> c.top(item(WHEAT)).middle(item(RUNIC_SAP_BOTTLE))))
                        )
                );

        screen.addEntry("arcane_wonders", -1, 3)
                .configureWidget(w -> w
                        .setIcon(CodexCommons.OVERWORLD)
                        .setDesign(SMALL, RUNEWOOD, DARK)
                )
                .addPage(headlineTextGizmoPage("arcane_wonders", CodexCommons.OVERWORLD))
                .addPage(textPage("arcane_wonders.2"))
                .addPage(textPage("arcane_wonders.3"))
                .addPage(textPage("arcane_wonders.4"));

        screen.addEntry("soulstone", 0, 4)
                .configureWidget(w -> w.setIcon(CodexCommons.RAW_SOULSTONE))
                .addPage(headlineTextGizmoPage("soulstone", CodexCommons.RAW_SOULSTONE))
                .addPage(PageSelectionPage.create(s -> s
                                .addHeadline(item(SOULSTONE_ORE), "soulstone.synopsis")
                                .addHeadline(item(RAW_SOULSTONE), "soulstone.ore_deposits")
                                .addHeadline(item(SOULSTONE_BUD), "soulstone.buds")
                        )
                )
                .addPage(headlineTextGizmoPage("soulstone.refinement", CodexCommons.REFINED_SOULSTONE))
                .addPage(PageSelectionPage.create(s -> s
                                .add(item(REFINED_SOULSTONE).setId("soulstone.refinement.smelting"), new CyclingPage(
                                        new SmeltingPage(item(RAW_SOULSTONE), item(REFINED_SOULSTONE, 2)),
                                        new SmeltingPage(item(SOULSTONE_BUD), item(REFINED_SOULSTONE, 2))
                                ))
                                .add(item(BLOCK_OF_REFINED_SOULSTONE).setId("soulstone.refinement.refined_compacting"),
                                        compacting(item(BLOCK_OF_REFINED_SOULSTONE), CodexCommons.REFINED_SOULSTONE))
                                .add(item(BLOCK_OF_RAW_SOULSTONE).setId("soulstone.refinement.raw_compacting"),
                                        compacting(item(BLOCK_OF_RAW_SOULSTONE), CodexCommons.RAW_SOULSTONE))
                        )
                );

        screen.addEntry("soulstone_buds", 2, 5)
                .configureWidget(w -> w.setIcon(item(SOULSTONE_BUD)))
                .addPage(headlineTextGizmoPage("soulstone_buds", CodexCommons.SOULSTONE_BUD))
                .addPage(textPage("soulstone_buds.2"))
                .addPage(headlineTextGizmoPage("realizing_soulstone_buds", CodexCommons.REALIZED_SOULSTONE_BUD))
                .addPage(new SoulstoneGrowthStagePage());

        screen.addEntry("derealized_metal", 3, 4)
                .configureWidget(w -> w.setIcon(item(IRON_METALLICS.getDerealizedMetal())));

        screen.addEntry("scythes", 0, 6).configureWidget(w -> w.setIcon(CodexCommons.CRUDE_SCYTHE))
                .addPage(headlineTextGizmoPage("scythes", CodexCommons.CRUDE_SCYTHE))
                .addPage(textPage("scythes.2"))
                .addPage(textPage("scythes.3"))
                .addPage(crafting(CodexCommons.CRUDE_SCYTHE, c -> c
                        .fill(item(IRON_INGOT), CraftingGridContents::topLeft, CraftingGridContents::top, CraftingGridContents::right)
                        .fill(item(STICK), CraftingGridContents::middle, CraftingGridContents::bottomLeft)
                        .fill(item(REFINED_SOULSTONE), CraftingGridContents::topRight)
                ));

        screen.addEntry("spirit_infusion", 0, 8)
                .configureWidget(w -> w.setIcon(CodexCommons.SPIRIT_ALTAR).setDesign(GILDED, RUNEWOOD, PAPER))
                .addPage(headlineTextGizmoPage("spirit_infusion", CodexCommons.SPIRIT_ALTAR))
                .addPage(textPage("spirit_infusion.2"))
                .addPage(textPage("spirit_infusion.3"))
                .addPage(PageSelectionPage.create(s -> s
                                .add(CodexCommons.SPIRIT_ALTAR, crafting(CodexCommons.SPIRIT_ALTAR, c -> c
                                        .fill(item(REFINED_SOULSTONE), CraftingGridContents::top)
                                        .fill(item(GOLD_INGOT), CraftingGridContents::left, CraftingGridContents::right)
                                        .fill(item(RUNEWOOD_SET.planks.block), CraftingGridContents::middle, CraftingGridContents::bottomLayer)
                                ))
                                .add(item(RUNEWOOD_SET.itemPedestal).setId("spirit_infusion.item_pedestal"), CraftingPage.pedestal(RUNEWOOD_SET))
                                .add(item(RUNEWOOD_SET.itemStand).setId("spirit_infusion.item_stand"), CraftingPage.stand(RUNEWOOD_SET))

                        )
                );

        screen.addEntry("common_reagents", 2, 9).configureWidget(w -> w.setIcon(CodexCommons.ALCHEMICAL_CALX))
                .addPage(headlineText("common_reagents"))
                .addPage(textPage("common_reagents.2"))
                .addRightReference(new EntryReference(CodexCommons.HEX_ASH,
                        BookEntry.create("common_reagents.hex_ash")
                                .addPage(headlineTextGizmoPage("common_reagents.hex_ash", CodexCommons.HEX_ASH))
                ))
                .addRightReference(new EntryReference(CodexCommons.LIVING_FLESH,
                        BookEntry.create("common_reagents.living_flesh")
                                .addPage(headlineTextGizmoPage("common_reagents.living_flesh", CodexCommons.LIVING_FLESH))
                ))
                .addRightReference(new EntryReference(CodexCommons.ALCHEMICAL_CALX,
                        BookEntry.create("common_reagents.alchemical_calx")
                                .addPage(headlineTextGizmoPage("common_reagents.alchemical_calx", CodexCommons.ALCHEMICAL_CALX))
                ));


        screen.addEntry("esoteric_reaping", -2, 9).configureWidget(w -> w.setIcon(CodexCommons.EERIE_WEAVE))
                .addPage(headlineText("esoteric_reaping"))
                .addPage(textPage("esoteric_reaping.2"))
                .addRightReference(new EntryReference(CodexCommons.GRIM_TALC,
                        BookEntry.create("esoteric_reaping.grim_talc")
                                .addPage(headlineTextGizmoPage("esoteric_reaping.grim_talc", CodexCommons.GRIM_TALC))
                ))
                .addRightReference(new EntryReference(CodexCommons.ROTTING_ESSENCE,
                        BookEntry.create("esoteric_reaping.rotting_essence")
                                .addPage(headlineTextGizmoPage("esoteric_reaping.rotting_essence", CodexCommons.ROTTING_ESSENCE))
                ))
                .addRightReference(new EntryReference(CodexCommons.EERIE_WEAVE,
                        BookEntry.create("esoteric_reaping.eerie_weave")
                                .addPage(headlineTextGizmoPage("esoteric_reaping.eerie_weave", CodexCommons.EERIE_WEAVE))
                ))
                .addRightReference(new EntryReference(CodexCommons.WARP_FLUX,
                        BookEntry.create("esoteric_reaping.warp_flux")
                                .addPage(headlineTextGizmoPage("esoteric_reaping.warp_flux", CodexCommons.WARP_FLUX))
                ))

                .addLeftReference(new EntryReference(CodexCommons.CORE_KEEPING,
                        BookEntry.create("common_reagents.core_keeping")
                                .addPage(headlineTextGizmoPage("common_reagents.core_keeping", CodexCommons.CORE_KEEPING))
                                .addPage(textPage("common_reagents.core_keeping.2"))
                                .addPage(textPage("common_reagents.core_keeping.3"))
                                .addPage(textPage("common_reagents.core_keeping.4"))
                                .addRightReference(new EntryReference(CodexCommons.WIND_NUCLEUS,
                                        BookEntry.create("common_reagents.core_keeping.wind_nucleus")
                                                .addPage(headlineTextGizmoPage("common_reagents.core_keeping.wind_nucleus", CodexCommons.WIND_NUCLEUS))
                                ))
                                .addRightReference(new EntryReference(CodexCommons.PYRE_NUCLEUS,
                                        BookEntry.create("common_reagents.core_keeping.pyre_nucleus")
                                                .addPage(headlineTextGizmoPage("common_reagents.core_keeping.pyre_nucleus", CodexCommons.PYRE_NUCLEUS))
                                ))
                ));

        screen.addEntry("the_arcanas", 0, 12)
                .setWidgetSupplier(spiritSubspace(0, 12))
                .setAssociatedSpirit(MalumSpiritTypes.ELDRITCH_SPIRIT);


        //        screen.addEntry("spirit_infusion", 0, 5)
//                .configureWidget(w -> w.setIcon(SPIRIT_ALTAR).setDesign(GILDED, RUNEWOOD, PAPER))
//                .addPage(headlineText("spirit_infusion"))
//                .addPage(new CraftingPage(SPIRIT_ALTAR.get(), AIR, REFINED_SOULSTONE.get(), AIR, GOLD_INGOT, RUNEWOOD_SET.planks.block.get(), GOLD_INGOT, RUNEWOOD_SET.planks.block.get(), RUNEWOOD_SET.planks.block.get(), RUNEWOOD_SET.planks.block.get()))
//                .addPage(text("spirit_infusion.2"))
//                .addPage(text("spirit_infusion.3"))
//                .addPage(CraftingPage.itemPedestalPage(RUNEWOOD_ITEM_PEDESTAL.get(), RUNEWOOD_SET.planks.block.get(), RUNEWOOD_PLANKS_SLAB.get()))
//                .addPage(CraftingPage.itemStandPage(RUNEWOOD_ITEM_STAND.get(), RUNEWOOD_SET.planks.block.get(), RUNEWOOD_PLANKS_SLAB.get()))
//                .addReference(new EntryReference(HEX_ASH.get(),
//                        BookEntry.create("spirit_infusion.hex_ash")
//                                .addPage(headlineText("spirit_infusion.hex_ash"))
//                                .addPage(SpiritInfusionPage.fromOutput(HEX_ASH.get()))
//                ))
//                .addReference(new EntryReference(LIVING_FLESH.get(), BookEntry.create("spirit_infusion.living_flesh")
//                        .addPage(headlineText("spirit_infusion.living_flesh"))
//                        .addPage(SpiritInfusionPage.fromOutput(LIVING_FLESH.get()))
//                ))
//                .addReference(new EntryReference(ALCHEMICAL_CALX.get(), BookEntry.create("spirit_infusion.alchemical_calx")
//                        .addPage(headlineText("spirit_infusion.alchemical_calx"))
//                        .addPage(SpiritInfusionPage.fromOutput(ALCHEMICAL_CALX.get()))
//                ))
//        );


//        screen.addEntry("natural_quartz", 3, 1)
//                .configureWidget(w -> w.setIcon(NATURAL_QUARTZ).setDesign(SMALL, RUNEWOOD, PAPER))
//                .addPage(new HeadlineTextItemPage("natural_quartz", NATURAL_QUARTZ.get()))
//        );
//
//        screen.addEntry("blazing_quartz", 4, 2)
//                .configureWidget(w -> w.setIcon(BLAZING_QUARTZ).setDesign(SMALL, RUNEWOOD, PAPER))
//                .addPage(new HeadlineTextItemPage("blazing_quartz", BLAZING_QUARTZ.get()))
//                .addPage(CraftingPage.fullPage(BLOCK_OF_BLAZING_QUARTZ.get(), BLAZING_QUARTZ.get()))
//        );
//
//        screen.addEntry("brilliance", -3, 1)
//                .configureWidget(w -> w.setIcon(RAW_BRILLIANCE).setDesign(SMALL, RUNEWOOD, PAPER))
//                .addPage(new HeadlineTextItemPage("brilliance", RAW_BRILLIANCE.get()))
//                .addPage(text("brilliance.2"))
//                .addPage(text("brilliance.3"))
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
//        screen.addEntry("cthonic_gold", -4, 2)
//                .configureWidget(w -> w.setIcon(CTHONIC_GOLD).setDesign(SMALL, RUNEWOOD, PAPER))
//                .addPage(new HeadlineTextItemPage("cthonic_gold", CTHONIC_GOLD.get()))
//                .addPage(text("cthonic_gold.2"))
//                .addPage(text("cthonic_gold.3"))
//                .addPage(text("cthonic_gold.4"))
////                .addReference(new EntryReference(UMBRAL_SPIRIT, cthonicGoldReexamination))
//        );
//
//
//        screen.addEntry("scythes", 0, 3)
//                .configureWidget(w -> w.setIcon(CRUDE_SCYTHE))
//                .addPage(headlineText("scythes"))
//                .addPage(CraftingPage.scythePage(MalumItems.CRUDE_SCYTHE.get(), Items.IRON_INGOT, REFINED_SOULSTONE.get()))
//                .addPage(text("scythes.2"))
//                .addReference(new EntryReference(ENCHANTED_BOOK,
//                        BookEntry.create("scythes.enchanting")
//                                .addPage(headlineText("scythes.enchanting"))
//                                .addPage(headlineText("scythes.enchanting.spirit_plunder"))
//                                .addPage(headlineText("scythes.enchanting.haunted"))
//                                .addPage(headlineText("scythes.enchanting.animated"))
//                                .addPage(headlineText("scythes.enchanting.rebound"))
//                                .addPage(headlineText("scythes.enchanting.ascension"))
//                ))
//        );
//
//        screen.addEntry("spirit_infusion", 0, 5)
//                .configureWidget(w -> w.setIcon(SPIRIT_ALTAR).setDesign(GILDED, RUNEWOOD, PAPER))
//                .addPage(headlineText("spirit_infusion"))
//                .addPage(new CraftingPage(SPIRIT_ALTAR.get(), AIR, REFINED_SOULSTONE.get(), AIR, GOLD_INGOT, RUNEWOOD_SET.planks.block.get(), GOLD_INGOT, RUNEWOOD_SET.planks.block.get(), RUNEWOOD_SET.planks.block.get(), RUNEWOOD_SET.planks.block.get()))
//                .addPage(text("spirit_infusion.2"))
//                .addPage(text("spirit_infusion.3"))
//                .addPage(CraftingPage.itemPedestalPage(RUNEWOOD_ITEM_PEDESTAL.get(), RUNEWOOD_SET.planks.block.get(), RUNEWOOD_PLANKS_SLAB.get()))
//                .addPage(CraftingPage.itemStandPage(RUNEWOOD_ITEM_STAND.get(), RUNEWOOD_SET.planks.block.get(), RUNEWOOD_PLANKS_SLAB.get()))
//                .addReference(new EntryReference(HEX_ASH.get(),
//                        BookEntry.create("spirit_infusion.hex_ash")
//                                .addPage(headlineText("spirit_infusion.hex_ash"))
//                                .addPage(SpiritInfusionPage.fromOutput(HEX_ASH.get()))
//                ))
//                .addReference(new EntryReference(LIVING_FLESH.get(), BookEntry.create("spirit_infusion.living_flesh")
//                        .addPage(headlineText("spirit_infusion.living_flesh"))
//                        .addPage(SpiritInfusionPage.fromOutput(LIVING_FLESH.get()))
//                ))
//                .addReference(new EntryReference(ALCHEMICAL_CALX.get(), BookEntry.create("spirit_infusion.alchemical_calx")
//                        .addPage(headlineText("spirit_infusion.alchemical_calx"))
//                        .addPage(SpiritInfusionPage.fromOutput(ALCHEMICAL_CALX.get()))
//                ))
//        );
//
//        screen.addEntry("esoteric_reaping", 0, 6)
//                .configureWidget(w -> w.setIcon(GRIM_TALC))
//                .addPage(headlineText("esoteric_reaping"))
//                .addPage(text("esoteric_reaping.2"))
//                .addReference(new EntryReference(ROTTING_ESSENCE.get(), BookEntry.create("esoteric_reaping.rotting_essence")
//                        .addPage(new HeadlineTextItemPage("esoteric_reaping.rotting_essence", ROTTING_ESSENCE.get()))
//                        .addPage(text("esoteric_reaping.rotting_essence.2"))
//                ))
//                .addReference(new EntryReference(GRIM_TALC.get(), BookEntry.create("esoteric_reaping.grim_talc")
//                        .addPage(new HeadlineTextItemPage("esoteric_reaping.grim_talc", GRIM_TALC.get()))
//                ))
//                .addReference(new EntryReference(EERIE_WEAVE.get(), BookEntry.create("esoteric_reaping.eerie_weave")
//                        .addPage(new HeadlineTextItemPage("esoteric_reaping.eerie_weave", EERIE_WEAVE.get()))
//                        .addPage(text("esoteric_reaping.eerie_weave.2"))
//                ))
//                .addReference(new EntryReference(WARP_FLUX.get(), BookEntry.create("esoteric_reaping.warp_flux")
//                        .addPage(new HeadlineTextItemPage("esoteric_reaping.warp_flux", WARP_FLUX.get()))
////                        .addPage(text("esoteric_reaping.warp_flux.2"))
//                ))
//                .addReference(new EntryReference(WIND_NUCLEUS.get(), BookEntry.create("esoteric_reaping.core_keeping")
//                        .addPage(headlineText("esoteric_reaping.core_keeping"))
//                        .addPage(text("esoteric_reaping.core_keeping.2"))
//                        .addPage(text("esoteric_reaping.core_keeping.3"))
//                        .addPage(text("esoteric_reaping.core_keeping.4"))
//                        .addPage(new HeadlineTextItemPage("esoteric_reaping.core_keeping.wind_nucleus", WIND_NUCLEUS.get()))
//                        .addPage(new HeadlineTextItemPage("esoteric_reaping.core_keeping.pyre_nucleus", PYRE_NUCLEUS.get()))
//                ))
//        );
//
//        screen.addEntry("primary_arcana", -2, 4)
//                .configureWidget(w -> w.setIcon(SACRED_SPIRIT))
//                .addPage(new HeadlineTextItemPage("primary_arcana.sacred", "primary_arcana.sacred.1", SACRED_SPIRIT.get()))
//                .addPage(text("primary_arcana.sacred.2"))
//                .addPage(new HeadlineTextItemPage("primary_arcana.wicked", "primary_arcana.wicked.1", WICKED_SPIRIT.get()))
//                .addPage(text("primary_arcana.wicked.2"))
//                .addPage(new HeadlineTextItemPage("primary_arcana.arcane", "primary_arcana.arcane.1", ARCANE_SPIRIT.get()))
//                .addPage(text("primary_arcana.arcane.2"))
//                .addPage(text("primary_arcana.arcane.3"))
//        );
//
//        screen.addEntry("elemental_arcana", 2, 4)
//                .configureWidget(w -> w.setIcon(EARTHEN_SPIRIT))
//                .addPage(new HeadlineTextItemPage("elemental_arcana.aerial", "elemental_arcana.aerial.1", AERIAL_SPIRIT.get()))
//                .addPage(text("elemental_arcana.aerial.2"))
//                .addPage(new HeadlineTextItemPage("elemental_arcana.earthen", "elemental_arcana.earthen.1", EARTHEN_SPIRIT.get()))
//                .addPage(text("elemental_arcana.earthen.2"))
//                .addPage(new HeadlineTextItemPage("elemental_arcana.infernal", "elemental_arcana.infernal.1", INFERNAL_SPIRIT.get()))
//                .addPage(text("elemental_arcana.infernal.2"))
//                .addPage(new HeadlineTextItemPage("elemental_arcana.aqueous", "elemental_arcana.aqueous.1", AQUEOUS_SPIRIT.get()))
//                .addPage(text("elemental_arcana.aqueous.2"))
//        );
//
//        screen.addEntry("eldritch_arcana", 0, 7)
//                .configureWidget(w -> w.setIcon(ELDRITCH_SPIRIT))
//                .addPage(new HeadlineTextItemPage("eldritch_arcana", "eldritch_arcana.1", ELDRITCH_SPIRIT.get()))
//                .addPage(text("eldritch_arcana.2"))
//        );
    }

    public static SubspaceEntryObject.SubspaceWidgetSupplier spiritSubspace(int x, int y) {

        var spiritSubspace = new SubspaceEntryObject.SubspaceWidgetSupplier().setSize(300);
        addSpiritEntry(spiritSubspace, MalumSpiritTypes.SACRED_SPIRIT, x+3, y-3);
        addSpiritEntry(spiritSubspace, MalumSpiritTypes.WICKED_SPIRIT, x-3, y+3);

        addSpiritEntry(spiritSubspace, MalumSpiritTypes.ARCANE_SPIRIT, x-4, y);
        addSpiritEntry(spiritSubspace, MalumSpiritTypes.ELDRITCH_SPIRIT, x+4, y);

        addSpiritEntry(spiritSubspace, MalumSpiritTypes.AERIAL_SPIRIT, x-2, y);
        addSpiritEntry(spiritSubspace, MalumSpiritTypes.AQUEOUS_SPIRIT, x, y-2);
        addSpiritEntry(spiritSubspace, MalumSpiritTypes.EARTHEN_SPIRIT, x+2, y);
        addSpiritEntry(spiritSubspace, MalumSpiritTypes.INFERNAL_SPIRIT, x, y+2);
        return spiritSubspace;

    }

    public static void addSpiritEntry(PlacedEntryAcceptor acceptor, SpiritLike spirit, int x, int y) {
        var translationKey = spirit.getRegistryName().getPath();
        acceptor.addEntry(translationKey, x, y)
                .configureWidget(w -> w.setIcon(item(spirit.getSpiritStack())))
                .addPage(HeadlineTextPage.headlineText(translationKey))
                .addPage(TextPage.textPage(translationKey + ".2"));
    }
}
