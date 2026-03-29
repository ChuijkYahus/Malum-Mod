package com.sammy.malum.client.screen.codex.entries;

import com.sammy.malum.client.screen.codex.*;
import com.sammy.malum.client.screen.codex.pages.*;
import com.sammy.malum.client.screen.codex.pages.recipe.*;
import com.sammy.malum.client.screen.codex.pages.recipe.vanilla.*;
import com.sammy.malum.client.screen.codex.pages.text.*;
import com.sammy.malum.client.screen.codex.screens.progression.*;
import net.minecraft.world.item.*;

import static com.sammy.malum.client.screen.codex.WidgetDesignType.*;
import static com.sammy.malum.client.screen.codex.WidgetDesignType.FillingType.PAPER;
import static com.sammy.malum.client.screen.codex.WidgetDesignType.FrameType.RUNEWOOD;
import static com.sammy.malum.client.screen.codex.WidgetDesignType.FrameType.SOULWOOD;
import static com.sammy.malum.registry.common.item.MalumItems.*;
import static net.minecraft.world.item.Items.*;

public class TinkeringEntries {

    public static void setupEntries(ArcanaProgressionScreen screen) {
        Item EMPTY = ItemStack.EMPTY.getItem();
        var spellweavingEnchanting = BookEntry.create("spellweaving_tools.enchanting")
                .addPage(new HeadlineTextPage("spellweaving_tools.enchanting.weavers_propagation"))
                .addPage(new HeadlineTextPage("spellweaving_tools.enchanting.weavers_haste"));


        var metalReexamination = new EntryReference(UMBRAL_SPIRIT, BookEntry.create("spirit_metals.reexamination")
                .addPage(new HeadlineTextPage("spirit_metals.reexamination", "spirit_metals.reexamination.1"))
                .addPage(new TextPage("spirit_metals.reexamination.2"))
                .afterUmbralCrystal()
        );

        screen.addEntry("spirit_metals.soul_stained_steel", -4, 5, b -> b
                .configureWidget(w -> w.setIcon(SOUL_STAINED_STEEL_INGOT))
                .addPage(new HeadlineTextItemPage("spirit_metals.soul_stained_steel", "spirit_metals.soul_stained_steel.1", SOUL_STAINED_STEEL_INGOT.get()))
                .addPage(SpiritInfusionPage.fromOutput(SOUL_STAINED_STEEL_INGOT.get()))
                .addPage(new TextPage("spirit_metals.soul_stained_steel.2"))
                .addPage(new CyclingPage(
                        CraftingPage.toolPage(SOUL_STAINED_STEEL_PICKAXE.get(), SOUL_STAINED_STEEL_INGOT.get()),
                        CraftingPage.toolPage(SOUL_STAINED_STEEL_AXE.get(), SOUL_STAINED_STEEL_INGOT.get()),
                        CraftingPage.toolPage(SOUL_STAINED_STEEL_HOE.get(), SOUL_STAINED_STEEL_INGOT.get()),
                        CraftingPage.toolPage(SOUL_STAINED_STEEL_SHOVEL.get(), SOUL_STAINED_STEEL_INGOT.get()),
                        CraftingPage.toolPage(SOUL_STAINED_STEEL_SWORD.get(), SOUL_STAINED_STEEL_INGOT.get()),
                        CraftingPage.knifePage(SOUL_STAINED_STEEL_KNIFE.get(), SOUL_STAINED_STEEL_INGOT.get())
                ))
                .addReference(metalReexamination)
        );

        screen.addEntry("spirit_metals.hallowed_gold", -3, 7, b -> b
                .configureWidget(w -> w.setIcon(HALLOWED_GOLD_INGOT))
                .addPage(new HeadlineTextItemPage("spirit_metals.hallowed_gold", "spirit_metals.hallowed_gold.1", HALLOWED_GOLD_INGOT.get()))
                .addPage(SpiritInfusionPage.fromOutput(HALLOWED_GOLD_INGOT.get()))
                .addPage(new TextPage("spirit_metals.hallowed_gold.2"))
                .addPage(new CyclingPage(
                        new CraftingPage(GILDED_RUNEWOOD_ITEM_PEDESTAL.get(), EMPTY, EMPTY, EMPTY, HALLOWED_GOLD_NUGGET.get(), RUNEWOOD_ITEM_PEDESTAL.get(), HALLOWED_GOLD_NUGGET.get()),
                        new CraftingPage(GILDED_RUNEWOOD_ITEM_STAND.get(), EMPTY, EMPTY, EMPTY, HALLOWED_GOLD_NUGGET.get(), RUNEWOOD_ITEM_STAND.get(), HALLOWED_GOLD_NUGGET.get())
                ))
                .addReference(metalReexamination)
        );

        screen.addEntry("altar_acceleration", -4, 8, b -> b
                .configureWidget(w -> w.setIcon(RUNEWOOD_OBELISK))
                .addPage(new HeadlineTextPage("altar_acceleration.runewood_obelisk", "altar_acceleration.runewood_obelisk.1"))
                .addPage(SpiritInfusionPage.fromOutput(RUNEWOOD_OBELISK.get()))
                .addPage(new HeadlineTextPage("altar_acceleration.brilliant_obelisk", "altar_acceleration.brilliant_obelisk.1"))
                .addPage(SpiritInfusionPage.fromOutput(BRILLIANT_OBELISK.get()))
        );

        screen.addEntry("spirit_jar", -5, 8, b -> b
                .configureWidget(w -> w.setIcon(SPIRIT_JAR))
                .addPage(new HeadlineTextPage("spirit_jar", "spirit_jar.1"))
                .addPage(new CraftingPage(SPIRIT_JAR.get(), EMPTY, HALLOWED_GOLD_INGOT.get(), EMPTY, EMPTY, GLASS))
        );

        screen.addEntry("soulstained_scythe", -5, 6, b -> b
                .configureWidget(w -> w.setIcon(SOUL_STAINED_STEEL_SCYTHE))
                .addPage(new HeadlineTextPage("soulstained_scythe", "soulstained_scythe.1"))
                .addPage(SpiritInfusionPage.fromOutput(SOUL_STAINED_STEEL_SCYTHE.get()))
        );

        screen.addEntry("soulstained_armor", -6, 6, b -> b
                .configureWidget(w -> w.setIcon(SOUL_STAINED_STEEL_HELMET))
                .addPage(new HeadlineTextPage("soulstained_armor", "soulstained_armor.1"))
                .addPage(new TextPage("soulstained_armor.2"))
                .addPage(new TextPage("soulstained_armor.3"))
                .addPage(new CyclingPage(
                        SpiritInfusionPage.fromOutput(SOUL_STAINED_STEEL_HELMET.get()),
                        SpiritInfusionPage.fromOutput(SOUL_STAINED_STEEL_CHESTPLATE.get()),
                        SpiritInfusionPage.fromOutput(SOUL_STAINED_STEEL_LEGGINGS.get()),
                        SpiritInfusionPage.fromOutput(SOUL_STAINED_STEEL_BOOTS.get())
                ))
                .addPage(new CraftingPage(new ItemStack(SOUL_STAINED_STEEL_PLATING.get(), 2), EMPTY, SOUL_STAINED_STEEL_NUGGET.get(), EMPTY, SOUL_STAINED_STEEL_NUGGET.get(), SOUL_STAINED_STEEL_INGOT.get(), SOUL_STAINED_STEEL_NUGGET.get(), EMPTY, SOUL_STAINED_STEEL_NUGGET.get(), EMPTY))
        );

        screen.addEntry("spirit_trinkets", -10, 7, b -> b
                .configureWidget(w -> w.setIcon(ORNATE_RING).setDesign(GILDED, RUNEWOOD, PAPER))
                .addPage(new HeadlineTextPage("spirit_trinkets", "spirit_trinkets.1"))
                .addPage(new TextPage("spirit_trinkets.2"))
                .addPage(CraftingPage.ringPage(ORNATE_RING.get(), SOUL_STAINED_STEEL_INGOT.get()))
                .addPage(CraftingPage.necklacePage(ORNATE_NECKLACE.get(), SOUL_STAINED_STEEL_PLATING.get()))
                .addPage(CraftingPage.ringPage(GILDED_RING.get(), HALLOWED_GOLD_INGOT.get()))
                .addPage(CraftingPage.beltPage(GILDED_BELT.get(), HALLOWED_GOLD_INLAY.get()))
        );

        screen.addEntry("ring_of_esoteric_spoils", -11, 8, b -> b
                .configureWidget(w -> w.setIcon(RING_OF_ESOTERIC_SPOILS))
                .addPage(new HeadlineTextPage("ring_of_esoteric_spoils"))
                .addPage(SpiritInfusionPage.fromOutput(RING_OF_ESOTERIC_SPOILS.get()))
        );

        screen.addEntry("ring_of_esoteric_shadow", -9, 6, b -> b
                .configureWidget(w -> w.setIcon(RING_OF_ESOTERIC_SHADOW))
                .addPage(new HeadlineTextPage("ring_of_esoteric_shadow"))
                .addPage(SpiritInfusionPage.fromOutput(RING_OF_ESOTERIC_SHADOW.get()))
        );

        screen.addEntry("reactive_trinkets", -10, 5, b -> b
                .configureWidget(w -> w.setIcon(NECKLACE_OF_MYSTIC_POTENCY))
                .addPage(new HeadlineTextPage("reactive_trinkets"))
                .addPage(new TextPage("reactive_trinkets.2"))
                .addReference(new EntryReference(RING_OF_CURATIVE_TALENT.get(),
                        BookEntry.create("reactive_trinkets.ring_of_curative_talent")
                                .addPage(new HeadlineTextPage("reactive_trinkets.ring_of_curative_talent"))
                                .addPage(SpiritInfusionPage.fromOutput(RING_OF_CURATIVE_TALENT.get()))))
                .addReference(new EntryReference(RING_OF_ALCHEMICAL_MASTERY.get(),
                        BookEntry.create("reactive_trinkets.ring_of_alchemical_mastery")
                                .addPage(new HeadlineTextPage("reactive_trinkets.ring_of_alchemical_mastery"))
                                .addPage(SpiritInfusionPage.fromOutput(RING_OF_ALCHEMICAL_MASTERY.get()))))
                .addReference(new EntryReference(RING_OF_MANAWEAVING.get(),
                        BookEntry.create("reactive_trinkets.ring_of_manaweaving")
                                .addPage(new HeadlineTextPage("reactive_trinkets.ring_of_manaweaving"))
                                .addPage(SpiritInfusionPage.fromOutput(RING_OF_MANAWEAVING.get()))))
                .addReference(new EntryReference(RING_OF_ARCANE_PROWESS.get(),
                        BookEntry.create("reactive_trinkets.ring_of_prowess")
                                .addPage(new HeadlineTextPage("reactive_trinkets.ring_of_prowess"))
                                .addPage(SpiritInfusionPage.fromOutput(RING_OF_ARCANE_PROWESS.get()))
                                .addPage(new TextPage("reactive_trinkets.ring_of_prowess.2"))))
                .addReference(new EntryReference(NECKLACE_OF_MYSTIC_POTENCY.get(),
                        BookEntry.create("reactive_trinkets.necklace_of_mystic_potency")
                                .addPage(new HeadlineTextPage("reactive_trinkets.necklace_of_mystic_potency"))
                                .addPage(SpiritInfusionPage.fromOutput(NECKLACE_OF_MYSTIC_POTENCY.get()))))
        );

        screen.addEntry("bladed_trinkets", -10, 9, b -> b
                .configureWidget(w -> w.setIcon(NECKLACE_OF_THE_NARROW_EDGE))
                .addPage(new HeadlineTextPage("bladed_trinkets"))
                .addPage(new TextPage("bladed_trinkets.2"))
                .addReference(new EntryReference(NECKLACE_OF_THE_NARROW_EDGE.get(),
                        BookEntry.create("bladed_trinkets.necklace_of_the_narrow_edge")
                                .addPage(new HeadlineTextPage("bladed_trinkets.necklace_of_the_narrow_edge"))
                                .addPage(SpiritInfusionPage.fromOutput(NECKLACE_OF_THE_NARROW_EDGE.get()))
                                .addPage(new TextPage("bladed_trinkets.necklace_of_the_narrow_edge.2"))
                ))
                .addReference(new EntryReference(RING_OF_THE_RISING_EDGE.get(),
                        BookEntry.create("bladed_trinkets.ring_of_the_rising_edge")
                                .addPage(new HeadlineTextPage("bladed_trinkets.ring_of_the_rising_edge"))
                                .addPage(SpiritInfusionPage.fromOutput(RING_OF_THE_RISING_EDGE.get()))
                                .addPage(new TextPage("bladed_trinkets.ring_of_the_rising_edge.2"))
                                .addPage(new TextPage("bladed_trinkets.ring_of_the_rising_edge.3"))
                ))
                .addReference(new EntryReference(RING_OF_THE_HOWLING_MAELSTROM.get(),
                        BookEntry.create("bladed_trinkets.ring_of_the_howling_maelstrom")
                                .addPage(new HeadlineTextPage("bladed_trinkets.ring_of_the_howling_maelstrom"))
                                .addPage(SpiritInfusionPage.fromOutput(RING_OF_THE_HOWLING_MAELSTROM.get()))
                                .addPage(new TextPage("bladed_trinkets.ring_of_the_howling_maelstrom.2"))
                                .addPage(new TextPage("bladed_trinkets.ring_of_the_howling_maelstrom.3"))
                ))
        );

        screen.addEntry("something", -12, 6, b -> b
                .configureWidget(w -> w.setIcon(BARRIER))
        );

        screen.addEntry("rotten_trinkets",-14, 5, b -> b
                .configureWidget(w -> w.setIcon(BELT_OF_THE_STARVED))
                .addPage(new HeadlineTextPage("rotten_trinkets"))
                .addPage(new TextPage("rotten_trinkets.2"))
                .addPage(new HeadlineTextPage("rotten_trinkets.gluttony"))
                .addPage(new TextPage("rotten_trinkets.gluttony.2"))
                .addPage(new TextPage("rotten_trinkets.gluttony.3"))
                .addReference(new EntryReference(
                        BELT_OF_THE_STARVED.get(),
                        BookEntry.create("rotten_trinkets.belt_of_the_starved")
                                .addPage(new HeadlineTextPage("rotten_trinkets.belt_of_the_starved"))
                                .addPage(SpiritInfusionPage.fromOutput(BELT_OF_THE_STARVED.get()))
                ))
                .addReference(new EntryReference(
                        RING_OF_DESPERATE_VORACITY.get(),
                        BookEntry.create("rotten_trinkets.ring_of_desperate_voracity")
                                .addPage(new HeadlineTextPage("rotten_trinkets.ring_of_desperate_voracity"))
                                .addPage(SpiritInfusionPage.fromOutput(RING_OF_DESPERATE_VORACITY.get()))
                ))
                .addReference(new EntryReference(
                        RING_OF_SWARMING_ROT.get(),
                        BookEntry.create("rotten_trinkets.ring_of_swarming_rot")
                                .addPage(new HeadlineTextPage("rotten_trinkets.ring_of_swarming_rot"))
                                .addPage(SpiritInfusionPage.fromOutput(RING_OF_SWARMING_ROT.get()))
                ))
        );

        screen.addEntry("ravenous_scythe", -15, 4, b -> b
                .configureWidget(w -> w.setIcon(RAVENOUS_SCYTHE))
        );
        screen.addEntry("gluttonous_bludgeon", -16, 4, b -> b
                .configureWidget(w -> w.setIcon(GLUTTONOUS_BLUDGEON))
        );

        screen.addEntry("avaricious_trinkets", -11, 11, b -> b
                .configureWidget(w -> w.setIcon(BELT_OF_THE_PROSPECTOR))
                .addPage(new HeadlineTextPage("avaricious_trinkets"))
                .addPage(new TextPage("avaricious_trinkets.2"))
                .addPage(new HeadlineTextPage("avaricious_trinkets.avarice"))
                .addPage(new TextPage("avaricious_trinkets.avarice.2"))
                .addReference(new EntryReference(
                        BELT_OF_THE_PROSPECTOR.get(),
                        BookEntry.create("avaricious_trinkets.belt_of_the_prospector")
                                .addPage(new HeadlineTextPage("avaricious_trinkets.belt_of_the_prospector"))
                                .addPage(SpiritInfusionPage.fromOutput(BELT_OF_THE_PROSPECTOR.get()))
                ))
                .addReference(new EntryReference(
                        RING_OF_HEARTY_AVARICE.get(),
                        BookEntry.create("avaricious_trinkets.ring_of_hearty_avarice")
                                .addPage(new HeadlineTextPage("avaricious_trinkets.ring_of_hearty_avarice"))
                                .addPage(SpiritInfusionPage.fromOutput(RING_OF_HEARTY_AVARICE.get()))
                ))
                .addReference(new EntryReference(
                        RING_OF_HEAVY_DISCHARGE.get(),
                        BookEntry.create("avaricious_trinkets.ring_of_heavy_discharge")
                                .addPage(new HeadlineTextPage("avaricious_trinkets.ring_of_heavy_discharge"))
                                .addPage(SpiritInfusionPage.fromOutput(RING_OF_HEAVY_DISCHARGE.get()))
                ))
        );
        screen.addEntry("something", -10, 12, b -> b
                .configureWidget(w -> w.setIcon(BARRIER))
        );
        screen.addEntry("something", -10, 13, b -> b
                .configureWidget(w -> w.setIcon(BARRIER))
        );
//
//        screen.addEntry("necklace_of_blissful_harmony", -10, 5, b -> b
//                .configureWidget(w -> w.setIcon(NECKLACE_OF_BLISSFUL_HARMONY))
//                .addPage(new HeadlineTextPage("necklace_of_blissful_harmony", "necklace_of_blissful_harmony.1"))
//                .addPage(SpiritInfusionPage.fromOutput(NECKLACE_OF_BLISSFUL_HARMONY.get()))
//                .addPage(new TextPage("necklace_of_blissful_harmony.2"))
//        );

        screen.addEntry("spellweaving_pickaxe", -3, 12, b -> b
                .configureWidget(w -> w.setIcon(SPELLWEAVING_PICKAXE))
                .addPage(new HeadlineTextPage("spellweaving_pickaxe", "spellweaving_pickaxe.1"))
                .addPage(SpiritInfusionPage.fromOutput(SPELLWEAVING_PICKAXE.get()))
                .addReference(new EntryReference(ENCHANTED_BOOK, spellweavingEnchanting))
        );

        screen.addEntry("spellweaving_axe", 3, 12, b -> b
                .configureWidget(w -> w.setIcon(SPELLWEAVING_AXE))
                .addPage(new HeadlineTextPage("spellweaving_axe", "spellweaving_axe.1"))
                .addPage(SpiritInfusionPage.fromOutput(SPELLWEAVING_AXE.get()))
                .addReference(new EntryReference(ENCHANTED_BOOK, spellweavingEnchanting))
        );

        screen.addEntry("belt_of_the_magebane", -2, 18, b -> b
                .configureWidget(w -> w.setDesign(DEFAULT, SOULWOOD, PAPER).setIcon(BELT_OF_THE_MAGEBANE))
                .addPage(new HeadlineTextPage("belt_of_the_magebane", "belt_of_the_magebane.1"))
                .addPage(new TextPage("belt_of_the_magebane.2"))
                .addPage(SpiritInfusionPage.fromOutput(BELT_OF_THE_MAGEBANE.get()))
        );

        screen.addEntry("tyrving", -2, 19, b -> b
                .configureWidget(w -> w.setDesign(DEFAULT, SOULWOOD, PAPER).setIcon(TYRVING))
                .addPage(new HeadlineTextItemPage("tyrving", "tyrving.1", TYRVING.get()))
                .addPage(new TextPage("tyrving.2"))
                .addPage(new TextPage("tyrving.3"))
                .addPage(SpiritInfusionPage.fromOutput(TYRVING.get()))
                .addPage(new TextPage("tyrving.4"))
                .addPage(new SpiritRepairPage("tyrving_restoration"))
        );
    }
}
