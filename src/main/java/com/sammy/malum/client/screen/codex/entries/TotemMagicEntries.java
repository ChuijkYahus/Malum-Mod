package com.sammy.malum.client.screen.codex.entries;

import com.sammy.malum.client.screen.codex.*;
import com.sammy.malum.client.screen.codex.objects.progression.*;
import com.sammy.malum.client.screen.codex.pages.*;
import com.sammy.malum.client.screen.codex.pages.recipe.*;
import com.sammy.malum.client.screen.codex.pages.recipe.vanilla.*;
import com.sammy.malum.client.screen.codex.pages.text.*;
import com.sammy.malum.client.screen.codex.screens.*;
import com.sammy.malum.registry.common.magic.*;
import net.minecraft.core.registries.*;
import net.minecraft.world.item.*;

import static com.sammy.malum.registry.common.item.MalumItems.*;
import static net.minecraft.world.item.Items.*;

public class TotemMagicEntries {

    public static void setupEntries(ArcanaProgressionScreen screen) {
        Item EMPTY = ItemStack.EMPTY.getItem();

        var strangeCrystalReexamination = BookEntry.build("strange_crystal.reexamination")
                .addPage(new HeadlineTextPage("strange_crystal.reexamination", "strange_crystal.reexamination.1"))
                .afterVoidReader();

        var strangeCrystalRevelation = BookEntry.build("strange_crystal.revelation")
                .addPage(new HeadlineTextPage("strange_crystal.revelation", "strange_crystal.revelation.1"))
                .afterUmbralCrystal();

        screen.addEntry("totem_magic", 0, 14, b -> b
                .configureWidget(w -> w.setIcon(RUNEWOOD_TOTEM_BASE).setStyle(BookWidgetStyle.GILDED_RUNEWOOD))
                .addPage(new HeadlineTextItemPage("totem_magic", "totem_magic.1", RUNEWOOD_TOTEM_BASE.get()))
                .addPage(new TextPage("totem_magic.2"))
                .addPage(new TextPage("totem_magic.3"))
                .addPage(new TextPage("totem_magic.4"))
                .addPage(new TextPage("totem_magic.5"))
                .addPage(SpiritInfusionPage.fromOutput(RUNEWOOD_TOTEM_BASE.get()))
        );

        screen.addEntry("managing_totems", 0, 15, b -> b
                .configureWidget(w -> w.setIcon(TOTEMIC_STAFF).setStyle(BookWidgetStyle.SMALL_RUNEWOOD))
                .addPage(new HeadlineTextItemPage("managing_totems", "managing_totems.1", TOTEMIC_STAFF.get()))
                .addPage(new TextPage("managing_totems.2"))
                .addPage(new TextPage("managing_totems.3"))
                .addPage(new CraftingPage(TOTEMIC_STAFF.get(),
                        EMPTY, EMPTY, RUNEWOOD_PLANKS.get(),
                        EMPTY, STICK, EMPTY,
                        STICK, EMPTY, EMPTY
                ))
        );

        screen.addEntry("sacred_rite", -2, 15, b -> b
                .setWidgetSupplier(RiteEntryObject::new)
                .configureWidget(w -> w.setStyle(BookWidgetStyle.DARK_TOTEMIC_RUNEWOOD))
                .addPage(new SpiritRiteTextPage(MalumSpiritRiteTypes.RITE_OF_HEALING, "sacred_rite"))
                .addPage(new SpiritRiteRecipePage(MalumSpiritRiteTypes.RITE_OF_HEALING))
                .addPage(new SpiritRiteTextPage(MalumSpiritRiteTypes.RITE_OF_NURTURING, "greater_sacred_rite"))
                .addPage(new SpiritRiteRecipePage(MalumSpiritRiteTypes.RITE_OF_NURTURING))
        );

        screen.addEntry("corrupt_sacred_rite", -3, 15, b -> b
                .setWidgetSupplier(RiteEntryObject::new)
                .configureWidget(w -> w.setStyle(BookWidgetStyle.DARK_TOTEMIC_SOULWOOD))
                .addPage(new SpiritRiteTextPage(MalumSpiritRiteTypes.RITE_OF_NOURISHMENT, "corrupt_sacred_rite"))
                .addPage(new SpiritRiteRecipePage(MalumSpiritRiteTypes.RITE_OF_NOURISHMENT))
                .addPage(new SpiritRiteTextPage(MalumSpiritRiteTypes.RITE_OF_LUST, "corrupt_greater_sacred_rite"))
                .addPage(new SpiritRiteRecipePage(MalumSpiritRiteTypes.RITE_OF_LUST))
        );

        screen.addEntry("infernal_rite", -3, 16, b -> b
                .setWidgetSupplier(RiteEntryObject::new)
                .configureWidget(w -> w.setStyle(BookWidgetStyle.DARK_TOTEMIC_RUNEWOOD))
                .addPage(new SpiritRiteTextPage(MalumSpiritRiteTypes.RITE_OF_THE_BURNING_FERVOR, "infernal_rite"))
                .addPage(new SpiritRiteRecipePage(MalumSpiritRiteTypes.RITE_OF_THE_BURNING_FERVOR))
                .addPage(new SpiritRiteTextPage(MalumSpiritRiteTypes.RITE_OF_SMELTING, "greater_infernal_rite"))
                .addPage(new SpiritRiteRecipePage(MalumSpiritRiteTypes.RITE_OF_SMELTING))
                .addReference(totemicRuneEntry(RUNE_OF_BURNING_FERVOR.get()))
        );

        screen.addEntry("corrupt_infernal_rite", -4, 16, b -> b
                .configureWidget(w -> w.setStyle(BookWidgetStyle.DARK_TOTEMIC_SOULWOOD))
                .setWidgetSupplier(RiteEntryObject::new)
                .addPage(new SpiritRiteTextPage(MalumSpiritRiteTypes.RITE_OF_THE_FIERY_EMBRACE, "corrupt_infernal_rite"))
                .addPage(new SpiritRiteRecipePage(MalumSpiritRiteTypes.RITE_OF_THE_FIERY_EMBRACE))
                .addPage(new SpiritRiteTextPage(MalumSpiritRiteTypes.RITE_OF_QUICKENING, "corrupt_greater_infernal_rite"))
                .addPage(new SpiritRiteRecipePage(MalumSpiritRiteTypes.RITE_OF_QUICKENING))
                .addReference(totemicRuneEntry(RUNE_OF_FIERY_EMBRACE.get()))
        );

        screen.addEntry("earthen_rite", -3, 17, b -> b
                .setWidgetSupplier(RiteEntryObject::new)
                .configureWidget(w -> w.setStyle(BookWidgetStyle.DARK_TOTEMIC_RUNEWOOD))
                .addPage(new SpiritRiteTextPage(MalumSpiritRiteTypes.RITE_OF_THE_STONE_WARD, "earthen_rite"))
                .addPage(new SpiritRiteRecipePage(MalumSpiritRiteTypes.RITE_OF_THE_STONE_WARD))
                .addPage(new SpiritRiteTextPage(MalumSpiritRiteTypes.RITE_OF_CREATION, "greater_earthen_rite"))
                .addPage(new SpiritRiteRecipePage(MalumSpiritRiteTypes.RITE_OF_CREATION))
                .addReference(totemicRuneEntry(RUNE_OF_STONE_WARD.get()))
        );

        screen.addEntry("corrupt_earthen_rite", -4, 17, b -> b
                .configureWidget(w -> w.setStyle(BookWidgetStyle.DARK_TOTEMIC_SOULWOOD))
                .setWidgetSupplier(RiteEntryObject::new)
                .addPage(new SpiritRiteTextPage(MalumSpiritRiteTypes.RITE_OF_THE_OAKEN_MIGHT, "corrupt_earthen_rite"))
                .addPage(new SpiritRiteRecipePage(MalumSpiritRiteTypes.RITE_OF_THE_OAKEN_MIGHT))
                .addPage(new SpiritRiteTextPage(MalumSpiritRiteTypes.RITE_OF_DESTRUCTION, "corrupt_greater_earthen_rite"))
                .addPage(new SpiritRiteRecipePage(MalumSpiritRiteTypes.RITE_OF_DESTRUCTION))
                .addReference(totemicRuneEntry(RUNE_OF_OAKEN_MIGHT.get()))
        );

        screen.addEntry("wicked_rite", 2, 15, b -> b
                .setWidgetSupplier(RiteEntryObject::new)
                .configureWidget(w -> w.setStyle(BookWidgetStyle.DARK_TOTEMIC_RUNEWOOD))
                .addPage(new SpiritRiteTextPage(MalumSpiritRiteTypes.RITE_OF_HARMING, "wicked_rite"))
                .addPage(new SpiritRiteRecipePage(MalumSpiritRiteTypes.RITE_OF_HARMING))
//                .addPage(new SpiritRiteTextPage(MalumSpiritRiteTypes.ELDRITCH_WICKED_RITE, "greater_wicked_rite"))
//                .addPage(new SpiritRiteRecipePage(MalumSpiritRiteTypes.ELDRITCH_WICKED_RITE))
        );

        screen.addEntry("corrupt_wicked_rite", 3, 15, b -> b
                .configureWidget(w -> w.setStyle(BookWidgetStyle.DARK_TOTEMIC_SOULWOOD))
                .setWidgetSupplier(RiteEntryObject::new)
                .addPage(new SpiritRiteTextPage(MalumSpiritRiteTypes.RITE_OF_EMPOWERMENT, "corrupt_wicked_rite"))
                .addPage(new SpiritRiteRecipePage(MalumSpiritRiteTypes.RITE_OF_EMPOWERMENT))
                .addPage(new SpiritRiteTextPage(MalumSpiritRiteTypes.RITE_OF_CULLING, "corrupt_greater_wicked_rite"))
                .addPage(new SpiritRiteRecipePage(MalumSpiritRiteTypes.RITE_OF_CULLING))
        );

        screen.addEntry("aerial_rite", 3, 16, b -> b
                .setWidgetSupplier(RiteEntryObject::new)
                .configureWidget(w -> w.setStyle(BookWidgetStyle.DARK_TOTEMIC_RUNEWOOD))
                .addPage(new SpiritRiteTextPage(MalumSpiritRiteTypes.RITE_OF_THE_HOWLING_GALE, "aerial_rite"))
                .addPage(new SpiritRiteRecipePage(MalumSpiritRiteTypes.RITE_OF_THE_HOWLING_GALE))
                .addPage(new SpiritRiteTextPage(MalumSpiritRiteTypes.RITE_OF_GRAVITY, "greater_aerial_rite"))
                .addPage(new SpiritRiteRecipePage(MalumSpiritRiteTypes.RITE_OF_GRAVITY))
                .addReference(totemicRuneEntry(RUNE_OF_THE_HOWLING_GALE.get()))
        );

        screen.addEntry("corrupt_aerial_rite", 4, 16, b -> b
                .configureWidget(w -> w.setStyle(BookWidgetStyle.DARK_TOTEMIC_SOULWOOD))
                .setWidgetSupplier(RiteEntryObject::new)
                .addPage(new SpiritRiteTextPage(MalumSpiritRiteTypes.RITE_OF_THE_SKY_TETHER, "corrupt_aerial_rite"))
                .addPage(new SpiritRiteRecipePage(MalumSpiritRiteTypes.RITE_OF_THE_SKY_TETHER))
//                .addPage(new SpiritRiteTextPage(MalumSpiritRiteTypes.ELDRITCH_AERIAL_RITE, "corrupt_greater_aerial_rite"))
//                .addPage(new SpiritRiteRecipePage(MalumSpiritRiteTypes.ELDRITCH_AERIAL_RITE))
                .addReference(totemicRuneEntry(RUNE_OF_SKY_TETHER.get()))
        );

        screen.addEntry("aqueous_rite", 3, 17, b -> b
                .setWidgetSupplier(RiteEntryObject::new)
                .configureWidget(w -> w.setStyle(BookWidgetStyle.DARK_TOTEMIC_RUNEWOOD))
                .addPage(new SpiritRiteTextPage(MalumSpiritRiteTypes.RITE_OF_THE_FLOWING_GRASP, "aqueous_rite"))
                .addPage(new SpiritRiteRecipePage(MalumSpiritRiteTypes.RITE_OF_THE_FLOWING_GRASP))
                .addPage(new SpiritRiteTextPage(MalumSpiritRiteTypes.RITE_OF_SOAKING, "greater_aqueous_rite"))
                .addPage(new SpiritRiteRecipePage(MalumSpiritRiteTypes.RITE_OF_SOAKING))
                .addReference(totemicRuneEntry(RUNE_OF_THE_FLOWING_GRASP.get()))
        );

        screen.addEntry("corrupt_aqueous_rite", 4, 17, b -> b
                .configureWidget(w -> w.setStyle(BookWidgetStyle.DARK_TOTEMIC_SOULWOOD))
                .setWidgetSupplier(RiteEntryObject::new)
                .addPage(new SpiritRiteTextPage(MalumSpiritRiteTypes.RITE_OF_THE_GOOD_TIDES, "corrupt_aqueous_rite"))
                .addPage(new SpiritRiteRecipePage(MalumSpiritRiteTypes.RITE_OF_THE_GOOD_TIDES))
                .addPage(new SpiritRiteTextPage(MalumSpiritRiteTypes.RITE_OF_SAPPING, "corrupt_greater_aqueous_rite"))
                .addPage(new SpiritRiteRecipePage(MalumSpiritRiteTypes.RITE_OF_SAPPING))
                .addReference(totemicRuneEntry(RUNE_OF_GOOD_TIDES.get()))
        );

        screen.addEntry("arcane_rite", 0, 16, b -> b
                .setWidgetSupplier(RiteEntryObject::new)
                .configureWidget(w -> w.setStyle(BookWidgetStyle.DARK_TOTEMIC_RUNEWOOD))
                .addPage(new HeadlineTextPage("arcane_rite", "arcane_rite.description.1"))
                .addPage(new TextPage("arcane_rite.description.2"))
                .addPage(new SpiritRiteTextPage(MalumSpiritRiteTypes.UNDIRECTED_RITE, "arcane_rite"))
                .addPage(new SpiritRiteRecipePage(MalumSpiritRiteTypes.UNDIRECTED_RITE))
                .addPage(new TextPage("arcane_rite.description.3"))
                .addPage(new SpiritRiteTextPage(MalumSpiritRiteTypes.UNCHAINED_RITE, "corrupt_arcane_rite"))
                .addPage(SpiritTransmutationRecipePage.fromInput("arcane_rite.soulwood", RUNEWOOD_SAPLING.get()))
                .addPage(new TextPage("arcane_rite.description.4"))
                .addPage(SpiritInfusionPage.fromOutput(SOULWOOD_TOTEM_BASE.get()))
        );

        screen.addEntry("blight", 1, 17, b -> b
                .configureWidget(w -> w.setIcon(BLIGHTED_EARTH).setStyle(BookWidgetStyle.SMALL_SOULWOOD))
                .addPage(new HeadlineTextItemPage("blight.intro", "blight.intro.1", BLIGHTED_EARTH.get()))
                .addPage(new HeadlineTextPage("blight.composition", "blight.composition.1"))
                .addPage(new HeadlineTextPage("blight.flora", "blight.flora.1"))
                .addPage(new HeadlineTextPage("blight.spread", "blight.spread.1"))
                .addPage(new HeadlineTextPage("blight.arcane_rite", "blight.arcane_rite.1"))
        );

        screen.addEntry("soulwood", 0, 18, b -> b
                .configureWidget(w -> w.setIcon(SOULWOOD_SAPLING).setStyle(BookWidgetStyle.SMALL_SOULWOOD))
                .addPage(new HeadlineTextItemPage("soulwood", "soulwood.1", SOULWOOD_SAPLING.get()))
                .addPage(new TextPage("soulwood.2"))
                .addPage(new SmeltingPage(SOULWOOD_LOG.get(), ARCANE_CHARCOAL.get()))
                .addPage(CraftingPage.fullPage(BLOCK_OF_ARCANE_CHARCOAL.get(), ARCANE_CHARCOAL.get()))
                .addPage(new HeadlineTextPage("soulwood.blight", "soulwood.blight.1"))
                .addPage(new HeadlineTextPage("soulwood.bonemeal", "soulwood.bonemeal.1"))
                .addPage(new HeadlineTextPage("soulwood.cursed_sap", "soulwood.cursed_sap.1"))
                .addPage(new CraftingPage(new ItemStack(CURSED_SAPBALL.get()), CURSED_SAP.get()))
        );

        screen.addEntry("scarstone", -1, 19, b -> b
                .configureWidget(w -> w.setIcon(SCARSTONE).setStyle(BookWidgetStyle.SMALL_SOULWOOD))
                .addPage(new HeadlineTextItemPage("scarstone", "scarstone.1", SCARSTONE.get()))
                .addPage(new HeadlineTextPage("scarstone.material", "scarstone.material.1"))
                .addPage(new HeadlineTextPage("scarstone.creation", "scarstone.creation.1"))
                .addPage(new HeadlineTextPage("scarstone.strange_crystal", "scarstone.strange_crystal.1"))
                .addPage(new HeadlineTextItemPage("strange_crystal.material", "strange_crystal.material.1", LARGE_STRANGE_CRYSTAL.get()))
                .addPage(new HeadlineTextPage("strange_crystal.purpose", "strange_crystal.purpose.1"))
                .addPage(new TextPage("strange_crystal.purpose.2"))
                .addReference(new EntryReference(MNEMONIC_FRAGMENT.get(), strangeCrystalReexamination))
                .addReference(new EntryReference(UMBRAL_SPIRIT.get(), strangeCrystalRevelation))
        );

        screen.addEntry("unchained_transmutation", 0, 20, b -> b
                .configureWidget(w -> w.setIcon(SOUL_SAND).setStyle(BookWidgetStyle.SOULWOOD))
                .addPage(new HeadlineTextPage("unchained_transmutation", "unchained_transmutation.intro.1"))
                .addPage(new TextPage("unchained_transmutation.intro.2"))
                .addPage(new TextPage("unchained_transmutation.dirt.1"))
                .addPage(new SpiritTransmutationRecipeTreePage("unchained_transmutation.dirt", DIRT))
                .addPage(new TextPage("unchained_transmutation.stone.1"))
                .addPage(new SpiritTransmutationRecipeTreePage("unchained_transmutation.stone", STONE))
                .addPage(new TextPage("unchained_transmutation.basalt.1"))
                .addPage(new SpiritTransmutationRecipeTreePage("unchained_transmutation.basalt", BASALT))
                .addPage(new TextPage("unchained_transmutation.mud.1"))
                .addPage(new SpiritTransmutationRecipeTreePage("unchained_transmutation.mud", MUD))
                .addPage(new TextPage("unchained_transmutation.packed_mud.1"))
                .addPage(new SpiritTransmutationRecipeTreePage("unchained_transmutation.packed_mud", PACKED_MUD))
                .addPage(new TextPage("unchained_transmutation.snow.1"))
                .addPage(new SpiritTransmutationRecipeTreePage("unchained_transmutation.snow", SNOW_BLOCK))
                .addPage(new TextPage("unchained_transmutation.deepslate.1"))
                .addPage(new SpiritTransmutationRecipeTreePage("unchained_transmutation.deepslate", DEEPSLATE))
        );
    }

    public static EntryReference totemicRuneEntry(Item item) {
        String translationKey = BuiltInRegistries.ITEM.getKey(item).getPath();
        return new EntryReference(item, BookEntry.build(translationKey)
                .addPage(new HeadlineTextPage(translationKey))
                .addPage(RuneworkingPage.fromOutput(item)));
    }
}