package com.sammy.malum.client.screen.codex.entries;

import com.sammy.malum.client.screen.codex.*;
import com.sammy.malum.client.screen.codex.objects.progression.*;
import com.sammy.malum.client.screen.codex.pages.*;
import com.sammy.malum.client.screen.codex.pages.recipe.*;
import com.sammy.malum.client.screen.codex.pages.recipe.vanilla.*;
import com.sammy.malum.client.screen.codex.pages.text.*;
import com.sammy.malum.client.screen.codex.screens.progression.*;
import com.sammy.malum.registry.common.item.*;
import net.minecraft.world.item.*;

import static com.sammy.malum.MalumMod.*;
import static com.sammy.malum.client.screen.codex.WidgetDesignType.*;
import static com.sammy.malum.client.screen.codex.WidgetDesignType.FillingType.*;
import static com.sammy.malum.client.screen.codex.WidgetDesignType.FillingType.PAPER;
import static com.sammy.malum.client.screen.codex.WidgetDesignType.FrameType.RUNEWOOD;
import static com.sammy.malum.registry.common.item.MalumItems.*;
import static net.minecraft.world.item.Items.*;

public class IntroductionEntries {


    public static void setupEntries(ArcanaProgressionScreen screen) {
        Item EMPTY = ItemStack.EMPTY.getItem();

        var soulstoneAndBrillianceReexamination = BookEntry.create("spirit_minerals.reexamination")
                .addPage(new HeadlineTextPage("spirit_minerals.reexamination"))
                .addPage(new TextPage("spirit_minerals.reexamination.2"))
                .afterUmbralCrystal();

        var cthonicGoldReexamination = BookEntry.create("cthonic_gold.reexamination")
                .addPage(new HeadlineTextPage("cthonic_gold.reexamination"))
                .addPage(new TextPage("cthonic_gold.reexamination.2"))
                .afterUmbralCrystal();

        screen.addEntry("introduction", 0, 0, b -> b
                .configureWidget(w -> w.setIcon(ENCYCLOPEDIA_ARCANA).setDesign(GILDED, RUNEWOOD, PAPER).setOrigin())
                .addPage(new HeadlineTextItemPage("introduction", ENCYCLOPEDIA_ARCANA.get()))
                .addPage(new TextPage("introduction.2"))
                .addPage(new TextPage("introduction.3"))
                .addPage(new TextPage("introduction.4"))
                .addPage(new TextPage("introduction.5"))
        );

        screen.addEntry("spirit_crystals", 0, 1, b -> b
                .setWidgetSupplier((e, x, y) -> new IconObject(e, x, y, malumPath("textures/gui/book/icons/soul_shard.png")))
                .configureWidget(w -> w.setDesign(SMALL, RUNEWOOD, DARK))
                .addPage(new HeadlineTextPage("spirit_crystals"))
                .addPage(new TextPage("spirit_crystals.2"))
                .addPage(new TextPage("spirit_crystals.3"))
        );

        screen.addEntry("runewood", 1, 2, b -> b
                .configureWidget(w -> w.setIcon(RUNEWOOD_SAPLING))
                .addPage(new HeadlineTextItemPage("runewood", RUNEWOOD_SAPLING.get()))
                .addPage(new TextPage("runewood.2"))
                .addPage(new HeadlineTextItemPage("runewood.arcane_charcoal", ARCANE_CHARCOAL.get()))
                .addPage(new CyclingPage(
                        new SmeltingPage(RUNEWOOD_LOG.get(), ARCANE_CHARCOAL.get()),
                        CraftingPage.fullPage(BLOCK_OF_ARCANE_CHARCOAL.get(), ARCANE_CHARCOAL.get())
                ))
                .addPage(new HeadlineTextItemPage("runewood.runic_sap", RUNIC_SAP.get()))
                .addPage(new TextPage("runewood.runic_sap.2"))
                .addPage(new TextPage("runewood.runic_sap.3"))
                .addPage(new CraftingPage(new ItemStack(RUNIC_SAPBALL.get()), RUNIC_SAP.get()))
        );


        screen.addEntry("natural_quartz", 3, 1, b -> b
                .configureWidget(w -> w.setIcon(NATURAL_QUARTZ).setDesign(SMALL, RUNEWOOD, PAPER))
                .addPage(new HeadlineTextItemPage("natural_quartz", NATURAL_QUARTZ.get()))
        );

        screen.addEntry("blazing_quartz", 4, 2, b -> b
                .configureWidget(w -> w.setIcon(BLAZING_QUARTZ).setDesign(SMALL, RUNEWOOD, PAPER))
                .addPage(new HeadlineTextItemPage("blazing_quartz", BLAZING_QUARTZ.get()))
                .addPage(CraftingPage.fullPage(BLOCK_OF_BLAZING_QUARTZ.get(), BLAZING_QUARTZ.get()))
        );

        screen.addEntry("brilliance", -3, 1, b -> b
                .configureWidget(w -> w.setIcon(RAW_BRILLIANCE).setDesign(SMALL, RUNEWOOD, PAPER))
                .addPage(new HeadlineTextItemPage("brilliance", RAW_BRILLIANCE.get()))
                .addPage(new TextPage("brilliance.2"))
                .addPage(new SmeltingPage(new ItemStack(RAW_BRILLIANCE.get()), new ItemStack(REFINED_BRILLIANCE.get(), 2)))
                .addPage(new CyclingPage(
                        CraftingPage.fullPage(BLOCK_OF_BRILLIANCE.get(), REFINED_BRILLIANCE.get()),
                        CraftingPage.fullPage(BLOCK_OF_RAW_BRILLIANCE.get(), RAW_BRILLIANCE.get())
                ))
                .addReference(new EntryReference(UMBRAL_SPIRIT, soulstoneAndBrillianceReexamination))
        );

        screen.addEntry("cthonic_gold", -4, 2, b -> b
                .configureWidget(w -> w.setIcon(CTHONIC_GOLD).setDesign(SMALL, RUNEWOOD, PAPER))
                .addPage(new HeadlineTextItemPage("cthonic_gold", CTHONIC_GOLD.get()))
                .addPage(new TextPage("cthonic_gold.2"))
                .addPage(new TextPage("cthonic_gold.3"))
                .addPage(new TextPage("cthonic_gold.4"))
                .addReference(new EntryReference(UMBRAL_SPIRIT, cthonicGoldReexamination))
        );

        screen.addEntry("soulstone", -1, 2, b -> b
                .configureWidget(w -> w.setIcon(REFINED_SOULSTONE))
                .addPage(new HeadlineTextItemPage("soulstone", REFINED_SOULSTONE.get()))
                .addPage(new TextPage("soulstone.2"))
                .addPage(new SmeltingPage(new ItemStack(RAW_SOULSTONE.get()), new ItemStack(REFINED_SOULSTONE.get(), 2)))
                .addPage(new CyclingPage(
                        CraftingPage.fullPage(BLOCK_OF_SOULSTONE.get(), REFINED_SOULSTONE.get()),
                        CraftingPage.fullPage(BLOCK_OF_RAW_SOULSTONE.get(), RAW_SOULSTONE.get())
                ))
                .addReference(new EntryReference(UMBRAL_SPIRIT, soulstoneAndBrillianceReexamination))
        );

        screen.addEntry("scythes", 0, 3, b -> b
                .configureWidget(w -> w.setIcon(CRUDE_SCYTHE))
                .addPage(new HeadlineTextPage("scythes"))
                .addPage(CraftingPage.scythePage(MalumItems.CRUDE_SCYTHE.get(), Items.IRON_INGOT, REFINED_SOULSTONE.get()))
                .addPage(new TextPage("scythes.2"))
                .addReference(new EntryReference(ENCHANTED_BOOK,
                        BookEntry.create("scythes.enchanting")
                                .addPage(new HeadlineTextPage("scythes.enchanting"))
                                .addPage(new HeadlineTextPage("scythes.enchanting.spirit_plunder"))
                                .addPage(new HeadlineTextPage("scythes.enchanting.haunted"))
                                .addPage(new HeadlineTextPage("scythes.enchanting.animated"))
                                .addPage(new HeadlineTextPage("scythes.enchanting.rebound"))
                                .addPage(new HeadlineTextPage("scythes.enchanting.ascension"))
                ))
        );

        screen.addEntry("spirit_infusion", 0, 5, b -> b
                .configureWidget(w -> w.setIcon(SPIRIT_ALTAR).setDesign(GILDED, RUNEWOOD, PAPER))
                .addPage(new HeadlineTextPage("spirit_infusion"))
                .addPage(new CraftingPage(SPIRIT_ALTAR.get(), AIR, REFINED_SOULSTONE.get(), AIR, GOLD_INGOT, RUNEWOOD_PLANKS.get(), GOLD_INGOT, RUNEWOOD_PLANKS.get(), RUNEWOOD_PLANKS.get(), RUNEWOOD_PLANKS.get()))
                .addPage(new TextPage("spirit_infusion.2"))
                .addPage(new TextPage("spirit_infusion.3"))
                .addPage(CraftingPage.itemPedestalPage(RUNEWOOD_ITEM_PEDESTAL.get(), RUNEWOOD_PLANKS.get(), RUNEWOOD_PLANKS_SLAB.get()))
                .addPage(CraftingPage.itemStandPage(RUNEWOOD_ITEM_STAND.get(), RUNEWOOD_PLANKS.get(), RUNEWOOD_PLANKS_SLAB.get()))
                .addReference(new EntryReference(HEX_ASH.get(),
                        BookEntry.create("spirit_infusion.hex_ash")
                                .addPage(new HeadlineTextPage("spirit_infusion.hex_ash"))
                                .addPage(SpiritInfusionPage.fromOutput(HEX_ASH.get()))
                ))
                .addReference(new EntryReference(LIVING_FLESH.get(), BookEntry.create("spirit_infusion.living_flesh")
                        .addPage(new HeadlineTextPage("spirit_infusion.living_flesh"))
                        .addPage(SpiritInfusionPage.fromOutput(LIVING_FLESH.get()))
                ))
                .addReference(new EntryReference(ALCHEMICAL_CALX.get(), BookEntry.create("spirit_infusion.alchemical_calx")
                        .addPage(new HeadlineTextPage("spirit_infusion.alchemical_calx"))
                        .addPage(SpiritInfusionPage.fromOutput(ALCHEMICAL_CALX.get()))
                ))
        );

        screen.addEntry("esoteric_reaping", 0, 6, b -> b
                .configureWidget(w -> w.setIcon(GRIM_TALC))
                .addPage(new HeadlineTextPage("esoteric_reaping"))
                .addPage(new TextPage("esoteric_reaping.2"))
                .addReference(new EntryReference(ROTTING_ESSENCE.get(), BookEntry.create("esoteric_reaping.rotting_essence")
                        .addPage(new HeadlineTextItemPage("esoteric_reaping.rotting_essence", ROTTING_ESSENCE.get()))
                        .addPage(new TextPage("esoteric_reaping.rotting_essence.2"))
                ))
                .addReference(new EntryReference(GRIM_TALC.get(), BookEntry.create("esoteric_reaping.grim_talc")
                        .addPage(new HeadlineTextItemPage("esoteric_reaping.grim_talc", GRIM_TALC.get()))
                ))
                .addReference(new EntryReference(EERIE_WEAVE.get(), BookEntry.create("esoteric_reaping.eerie_weave")
                        .addPage(new HeadlineTextItemPage("esoteric_reaping.eerie_weave", EERIE_WEAVE.get()))
                        .addPage(new TextPage("esoteric_reaping.eerie_weave.2"))
                ))
                .addReference(new EntryReference(WARP_FLUX.get(), BookEntry.create("esoteric_reaping.warp_flux")
                        .addPage(new HeadlineTextItemPage("esoteric_reaping.warp_flux", WARP_FLUX.get()))
//                        .addPage(new TextPage("esoteric_reaping.warp_flux.2"))
                ))
                .addReference(new EntryReference(WIND_NUCLEUS.get(), BookEntry.create("esoteric_reaping.core_keeping")
                        .addPage(new HeadlineTextPage("esoteric_reaping.core_keeping"))
                        .addPage(new TextPage("esoteric_reaping.core_keeping.2"))
                        .addPage(new TextPage("esoteric_reaping.core_keeping.3"))
                        .addPage(new TextPage("esoteric_reaping.core_keeping.4"))
                        .addPage(new HeadlineTextItemPage("esoteric_reaping.core_keeping.wind_nucleus", WIND_NUCLEUS.get()))
                        .addPage(new HeadlineTextItemPage("esoteric_reaping.core_keeping.pyre_nucleus", PYRE_NUCLEUS.get()))
                ))
        );

        screen.addEntry("primary_arcana", -2, 4, b -> b
                .configureWidget(w -> w.setIcon(SACRED_SPIRIT))
                .addPage(new HeadlineTextItemPage("primary_arcana.sacred", "primary_arcana.sacred.1", SACRED_SPIRIT.get()))
                .addPage(new TextPage("primary_arcana.sacred.2"))
                .addPage(new HeadlineTextItemPage("primary_arcana.wicked", "primary_arcana.wicked.1", WICKED_SPIRIT.get()))
                .addPage(new TextPage("primary_arcana.wicked.2"))
                .addPage(new HeadlineTextItemPage("primary_arcana.arcane", "primary_arcana.arcane.1", ARCANE_SPIRIT.get()))
                .addPage(new TextPage("primary_arcana.arcane.2"))
                .addPage(new TextPage("primary_arcana.arcane.3"))
        );

        screen.addEntry("elemental_arcana", 2, 4, b -> b
                .configureWidget(w -> w.setIcon(EARTHEN_SPIRIT))
                .addPage(new HeadlineTextItemPage("elemental_arcana.aerial", "elemental_arcana.aerial.1", AERIAL_SPIRIT.get()))
                .addPage(new TextPage("elemental_arcana.aerial.2"))
                .addPage(new HeadlineTextItemPage("elemental_arcana.earthen", "elemental_arcana.earthen.1", EARTHEN_SPIRIT.get()))
                .addPage(new TextPage("elemental_arcana.earthen.2"))
                .addPage(new HeadlineTextItemPage("elemental_arcana.infernal", "elemental_arcana.infernal.1", INFERNAL_SPIRIT.get()))
                .addPage(new TextPage("elemental_arcana.infernal.2"))
                .addPage(new HeadlineTextItemPage("elemental_arcana.aqueous", "elemental_arcana.aqueous.1", AQUEOUS_SPIRIT.get()))
                .addPage(new TextPage("elemental_arcana.aqueous.2"))
        );

        screen.addEntry("eldritch_arcana", 0, 7, b -> b
                .configureWidget(w -> w.setIcon(ELDRITCH_SPIRIT))
                .addPage(new HeadlineTextItemPage("eldritch_arcana", "eldritch_arcana.1", ELDRITCH_SPIRIT.get()))
                .addPage(new TextPage("eldritch_arcana.2"))
        );
    }
}
