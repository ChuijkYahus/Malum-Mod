package com.sammy.malum.client.screen.codex.entries;

import com.sammy.malum.client.screen.codex.*;
import com.sammy.malum.client.screen.codex.objects.progression.*;
import com.sammy.malum.client.screen.codex.pages.*;
import com.sammy.malum.client.screen.codex.pages.recipe.*;
import com.sammy.malum.client.screen.codex.pages.recipe.vanilla.*;
import com.sammy.malum.client.screen.codex.pages.text.*;
import com.sammy.malum.client.screen.codex.screens.*;
import com.sammy.malum.core.systems.registry.rite.RiteHolder;
import com.sammy.malum.core.systems.rite.SpiritRiteType;
import com.sammy.malum.registry.common.magic.rite.*;
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

        screen.addEntry("totem_magic", 0, 15, b -> b
                .configureWidget(w -> w.setIcon(RUNEWOOD_TOTEM_BASE).setStyle(BookWidgetStyle.GILDED_RUNEWOOD))
                .addPage(new HeadlineTextItemPage("totem_magic", "totem_magic.1", RUNEWOOD_TOTEM_BASE.get()))
                .addPage(new TextPage("totem_magic.2"))
                .addPage(new TextPage("totem_magic.3"))
                .addPage(new TextPage("totem_magic.4"))
                .addPage(new TextPage("totem_magic.5"))
                .addPage(SpiritInfusionPage.fromOutput(RUNEWOOD_TOTEM_BASE.get()))
        );

        screen.addEntry("managing_totems", 0, 16, b -> b
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

        screen.addEntry("arcane_rites", 0, 17, b -> b
                .setWidgetSupplier(RiteEntryObject::new)
                .configureWidget(w -> w.setStyle(BookWidgetStyle.DARK_TOTEMIC_RUNEWOOD))
                .addPage(new HeadlineTextPage("arcane_rites", "arcane_rites.description.1"))
                .addPage(new TextPage("arcane_rites.description.2"))
                .addPage(new SpiritRiteTextPage(MalumSpiritRiteTypes.UNDIRECTED_RITE))
                .addPage(new SpiritRiteRecipePage(MalumSpiritRiteTypes.UNDIRECTED_RITE))
                .addPage(new TextPage("arcane_rites.description.3"))
                .addPage(new SpiritRiteTextPage(MalumSpiritRiteTypes.UNCHAINED_RITE))
                .addPage(SpiritTransmutationRecipePage.fromOutput("arcane_rites.soulwood", SOULWOOD_SAPLING.get()))
                .addPage(new TextPage("arcane_rites.description.4"))
                .addPage(SpiritInfusionPage.fromOutput(SOULWOOD_TOTEM_BASE.get()))
        );

        screen.addEntry("blight", 1, 18, b -> b
                .configureWidget(w -> w.setIcon(BLIGHTED_EARTH).setStyle(BookWidgetStyle.SMALL_SOULWOOD))
                .addPage(new HeadlineTextItemPage("blight.intro", "blight.intro.1", BLIGHTED_EARTH.get()))
                .addPage(new HeadlineTextPage("blight.composition", "blight.composition.1"))
                .addPage(new HeadlineTextPage("blight.flora", "blight.flora.1"))
                .addPage(new HeadlineTextPage("blight.spread", "blight.spread.1"))
                .addPage(new HeadlineTextPage("blight.arcane_rite", "blight.arcane_rite.1"))
        );

        screen.addEntry("soulwood", 0, 19, b -> b
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

        screen.addEntry("scarstone", -1, 20, b -> b
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

        screen.addEntry("unchained_transmutation", 0, 21, b -> b
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

        addRiteEntry(screen, MalumSpiritRiteTypes.RITE_OF_HEALING, 2, 16);
        addRiteEntry(screen, MalumSpiritRiteTypes.RITE_OF_NOURISHMENT, 3, 16);
        addRiteEntry(screen, MalumSpiritRiteTypes.RITE_OF_NURTURING, 3, 17);
        addRiteEntry(screen, MalumSpiritRiteTypes.RITE_OF_LUST, 4, 17);

        addRiteEntry(screen, MalumSpiritRiteTypes.RITE_OF_HARMING, -2, 16);
        addRiteEntry(screen, MalumSpiritRiteTypes.RITE_OF_EMPOWERMENT, -3, 16);
        addRiteEntry(screen, MalumSpiritRiteTypes.RITE_OF_CULLING, -3, 17);
        addRiteEntry(screen, MalumSpiritRiteTypes.RITE_OF_RAISING, -4, 17);
    }

    public static void addRiteEntry(AbstractProgressionCodexScreen screen, RiteHolder<SpiritRiteType> riteType, int x, int y) {
        screen.addEntry(riteType.value().getName(), x, y, b -> b
                .setWidgetSupplier(RiteEntryObject::new)
                .configureWidget(w -> w.setStyle(riteType.get().isCorrupted() ? BookWidgetStyle.DARK_TOTEMIC_SOULWOOD : BookWidgetStyle.DARK_TOTEMIC_RUNEWOOD))
                .addPage(new SpiritRiteTextPage(riteType))
                .addPage(new SpiritRiteRecipePage(riteType))
        );
    }
}