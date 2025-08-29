package com.sammy.malum.client.screen.codex.entries;

import com.sammy.malum.client.screen.codex.*;
import com.sammy.malum.client.screen.codex.objects.progression.*;
import com.sammy.malum.client.screen.codex.pages.*;
import com.sammy.malum.client.screen.codex.pages.recipe.*;
import com.sammy.malum.client.screen.codex.pages.recipe.vanilla.*;
import com.sammy.malum.client.screen.codex.pages.text.*;
import com.sammy.malum.client.screen.codex.screens.progression.*;
import com.sammy.malum.core.systems.registry.rite.RiteHolder;
import com.sammy.malum.core.systems.rite.SpiritRiteType;
import net.minecraft.core.registries.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.function.*;

import static com.sammy.malum.client.screen.codex.WidgetDesignType.*;
import static com.sammy.malum.client.screen.codex.WidgetDesignType.FillingType.*;
import static com.sammy.malum.client.screen.codex.WidgetDesignType.FrameType.*;
import static com.sammy.malum.client.screen.codex.WidgetDesignType.FillingType.PAPER;
import static com.sammy.malum.client.screen.codex.WidgetDesignType.FrameType.RUNEWOOD;
import static com.sammy.malum.client.screen.codex.WidgetDesignType.FrameType.SOULWOOD;
import static com.sammy.malum.registry.common.item.MalumItems.*;
import static com.sammy.malum.registry.common.magic.rite.MalumSpiritRiteTypes.*;
import static com.sammy.malum.registry.common.magic.rite.MalumSpiritRiteTypes.RITE_OF_THE_FIERY_EMBRACE;
import static net.minecraft.world.item.Items.*;

public class TotemMagicEntries {

    public static void setupEntries(ArcanaProgressionScreen screen) {
        Item EMPTY = ItemStack.EMPTY.getItem();

        var strangeCrystalReexamination = BookEntry.create("strange_crystal.reexamination")
                .addPage(new HeadlineTextPage("strange_crystal.reexamination", "strange_crystal.reexamination.1"))
                .afterVoidReader();

        var strangeCrystalRevelation = BookEntry.create("strange_crystal.revelation")
                .addPage(new HeadlineTextPage("strange_crystal.revelation", "strange_crystal.revelation.1"))
                .afterUmbralCrystal();

        screen.addEntry("totem_magic", 0, 15, b -> b
                .configureEntry(w -> w.setIcon(RUNEWOOD_TOTEM_BASE).setDesign(GILDED, RUNEWOOD, PAPER))
                .addPage(new HeadlineTextItemPage("totem_magic", "totem_magic.1", RUNEWOOD_TOTEM_BASE.get()))
                .addPage(new TextPage("totem_magic.2"))
                .addPage(new TextPage("totem_magic.3"))
                .addPage(SpiritInfusionPage.fromOutput(RUNEWOOD_TOTEM_BASE.get()))
        );

        screen.addEntry("managing_totems", 0, 16, b -> b
                .configureEntry(w -> w.setIcon(TOTEMIC_STAFF).setDesign(SMALL, RUNEWOOD, PAPER))
                .addPage(new HeadlineTextItemPage("managing_totems", "managing_totems.1", TOTEMIC_STAFF.get()))
                .addPage(new TextPage("managing_totems.2"))
                .addPage(new TextPage("managing_totems.3"))
                .addPage(new CraftingPage(TOTEMIC_STAFF.get(),
                        EMPTY, EMPTY, RUNEWOOD_PLANKS.get(),
                        EMPTY, STICK, EMPTY,
                        STICK, EMPTY, EMPTY
                ))
        );

        screen.addEntry("undirected_rite", 0, 17, b -> b
                .setWidgetSupplier(RiteEntryObject::new)
                .configureEntry(w -> w.setDesign(TOTEMIC, RUNEWOOD, DARK))
                .addPage(new HeadlineTextPage("undirected_rite"))
                .addPage(new TextPage("undirected_rite.2"))
                .addPage(new SpiritRiteTextPage(UNDIRECTED_RITE))
                .addPage(new SpiritRiteRecipePage(UNDIRECTED_RITE))
        );

        screen.addEntry("unchained_rite", 0, 19, b -> b
                .setWidgetSupplier(RiteEntryObject::new)
                .configureEntry(w -> w.setDesign(TOTEMIC, SOULWOOD, DARK))
                .addPage(new HeadlineTextPage("unchained_rite"))
                .addPage(new TextPage("unchained_rite.2"))
                .addPage(new SpiritRiteTextPage(UNCHAINED_RITE))
                .addPage(new SpiritRiteRecipePage(UNCHAINED_RITE))
                .addPage(SpiritTransmutationRecipePage.fromOutput("unchained_rite.soulwood", SOULWOOD_SAPLING.get()))
                .addPage(new TextPage("unchained_rite.soulwood"))
                .addPage(SpiritInfusionPage.fromOutput(SOULWOOD_TOTEM_BASE.get()))
                .addPage(new TextPage("unchained_rite.description.4"))
        );

        screen.addEntry("blight", 1, 20, b -> b
                .configureEntry(w -> w.setIcon(BLIGHTED_EARTH).setDesign(SMALL, SOULWOOD, PAPER))
                .addPage(new HeadlineTextItemPage("blight.intro", "blight.intro.1", BLIGHTED_EARTH.get()))
                .addPage(new HeadlineTextPage("blight.composition", "blight.composition.1"))
                .addPage(new HeadlineTextPage("blight.flora", "blight.flora.1"))
                .addPage(new HeadlineTextPage("blight.spread", "blight.spread.1"))
                .addPage(new HeadlineTextPage("blight.arcane_rite", "blight.arcane_rite.1"))
        );

        screen.addEntry("soulwood", 0, 21, b -> b
                .configureEntry(w -> w.setIcon(SOULWOOD_SAPLING).setDesign(SMALL, SOULWOOD, PAPER))
                .addPage(new HeadlineTextItemPage("soulwood", "soulwood.1", SOULWOOD_SAPLING.get()))
                .addPage(new TextPage("soulwood.2"))
                .addPage(new SmeltingPage(SOULWOOD_LOG.get(), ARCANE_CHARCOAL.get()))
                .addPage(CraftingPage.fullPage(BLOCK_OF_ARCANE_CHARCOAL.get(), ARCANE_CHARCOAL.get()))
                .addPage(new HeadlineTextPage("soulwood.blight", "soulwood.blight.1"))
                .addPage(new HeadlineTextPage("soulwood.bonemeal", "soulwood.bonemeal.1"))
                .addPage(new HeadlineTextPage("soulwood.cursed_sap", "soulwood.cursed_sap.1"))
                .addPage(new CraftingPage(new ItemStack(CURSED_SAPBALL.get()), CURSED_SAP.get()))
        );

        screen.addEntry("scarstone", -1, 22, b -> b
                .configureEntry(w -> w.setIcon(SCARSTONE).setDesign(SMALL, SOULWOOD, PAPER))
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

        screen.addEntry("unchained_transmutation", 0, 23, b -> b
                .configureEntry(w -> w.setIcon(SOUL_SAND).setDesign(DEFAULT, SOULWOOD, PAPER))
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

        addBundledRiteEntry(screen, "sacred_rites", 2, 16,
                RITE_OF_HEALING, RITE_OF_NOURISHMENT, RITE_OF_NURTURING, RITE_OF_LUST);

        addBundledRiteEntry(screen, "wicked_rites", -2, 16,
                RITE_OF_HARMING, RITE_OF_EMPOWERMENT, RITE_OF_CULLING, RITE_OF_RAISING);

        addBundledRiteEntry(screen, "aqueous_rites", 3, 18,
                RITE_OF_THE_FLOWING_GRASP, RITE_OF_THE_GOOD_TIDES, RITE_OF_SOAKING, RITE_OF_SAPPING,
                RUNE_OF_FLOWING_GRASP, RUNE_OF_GOOD_TIDES);

        addBundledRiteEntry(screen, "aerial_rites", -3, 18,
                RITE_OF_THE_HOWLING_GALE, RITE_OF_THE_SKY_TETHER, RITE_OF_GRAVITY, RITE_OF_ASCENSION,
                RUNE_OF_HOWLING_GALE, RUNE_OF_SKY_TETHER);

        addBundledRiteEntry(screen, "earthen_rites", 4, 20,
                RITE_OF_THE_STONE_WARD, RITE_OF_THE_OAKEN_MIGHT, RITE_OF_CREATION, RITE_OF_DESTRUCTION,
                RUNE_OF_STONE_WARD, RUNE_OF_OAKEN_MIGHT);

        addBundledRiteEntry(screen, "infernal_rites", -4, 20,
                RITE_OF_THE_BURNING_FERVOR, RITE_OF_THE_FIERY_EMBRACE, RITE_OF_SMELTING, RITE_OF_QUICKENING,
                RUNE_OF_BURNING_FERVOR, RUNE_OF_FIERY_EMBRACE);
    }

    public static void addBundledRiteEntry(AbstractProgressionCodexScreen screen, String name, int x, int y,
                                           RiteHolder<SpiritRiteType> minorRunewood, RiteHolder<SpiritRiteType> majorRunewood, RiteHolder<SpiritRiteType> minorSoulwood, RiteHolder<SpiritRiteType> majorSoulwood) {
        var acceptor = new SubspaceEntryObject.SubspaceWidgetSupplier();

        int horizontalOffset = y > 0 ? -1 : 1;
        addRiteEntry(acceptor, minorRunewood, x - horizontalOffset, y);
        addRiteEntry(acceptor, majorRunewood, x, y + 1);
        addRiteEntry(acceptor, minorSoulwood, x, y - 1);
        addRiteEntry(acceptor, majorSoulwood, x + horizontalOffset, y);

        screen.addEntry(name, x, y, b -> b.setWidgetSupplier(acceptor));
    }
    public static void addBundledRiteEntry(AbstractProgressionCodexScreen screen, String name, int x, int y,
                                           RiteHolder<SpiritRiteType> minorRunewood, RiteHolder<SpiritRiteType> majorRunewood, RiteHolder<SpiritRiteType> minorSoulwood, RiteHolder<SpiritRiteType> majorSoulwood,
                                           Supplier<Item> runewoodRune, Supplier<Item> soulwoodRune) {
        var acceptor = new SubspaceEntryObject.SubspaceWidgetSupplier();

        int horizontalOffset = y > 0 ? -1 : 1;
        addRiteEntry(acceptor, minorRunewood, runewoodRune, x - horizontalOffset, y);
        addRiteEntry(acceptor, majorRunewood, x, y + 1);
        addRiteEntry(acceptor, minorSoulwood, soulwoodRune, x, y - 1);
        addRiteEntry(acceptor, majorSoulwood, x + horizontalOffset, y);

        screen.addEntry(name, x, y, b -> b.setWidgetSupplier(acceptor));
    }

    public static void addRiteEntry(PlacedEntryAcceptor acceptor, RiteHolder<SpiritRiteType> riteType, int x, int y) {
        SpiritRiteType rite = riteType.value();
        acceptor.addEntry(rite.getName(), x, y, b -> b
                .setWidgetSupplier(RiteEntryObject::new)
                .configureEntry(w -> w.setDesign(TOTEMIC, rite.isCorrupted() ? SOULWOOD : RUNEWOOD, DARK))
                .addPage(new SpiritRiteTextPage(riteType))
                .addPage(new SpiritRiteRecipePage(riteType))
        );
    }

    public static void addRiteEntry(PlacedEntryAcceptor acceptor, RiteHolder<SpiritRiteType> riteType, Supplier<Item> rune, int x, int y) {
        SpiritRiteType rite = riteType.value();
        acceptor.addEntry(rite.getName(), x, y, b -> b
                .setWidgetSupplier(RiteEntryObject::new)
                .configureEntry(w -> w.setDesign(TOTEMIC, rite.isCorrupted() ? SOULWOOD : RUNEWOOD, DARK))
                .addPage(new SpiritRiteTextPage(riteType))
                .addPage(new SpiritRiteRecipePage(riteType))
                .addReference(totemicRuneEntry(rune.get()))
        );
    }

    public static EntryReference totemicRuneEntry(Item item) {
        String translationKey = BuiltInRegistries.ITEM.getKey(item).getPath();
        return new EntryReference(item, BookEntry.create(translationKey)
                .addPage(new HeadlineTextPage(translationKey))
                .addPage(RuneworkingPage.fromOutput(item)));
    }
}