package com.sammy.malum.client.screen.codex.entries;

import com.sammy.malum.client.screen.codex.*;
import com.sammy.malum.client.screen.codex.objects.progression.*;
import com.sammy.malum.client.screen.codex.pages.*;
import com.sammy.malum.client.screen.codex.pages.recipe.*;
import com.sammy.malum.client.screen.codex.pages.recipe.vanilla.*;
import com.sammy.malum.client.screen.codex.pages.text.*;
import com.sammy.malum.client.screen.codex.screens.progression.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.core.systems.registry.rite.*;
import com.sammy.malum.core.systems.rite.*;
import com.sammy.malum.registry.common.magic.MalumGeasEffectTypes;
import net.minecraft.core.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import static com.sammy.malum.client.screen.codex.WidgetDesignType.*;
import static com.sammy.malum.client.screen.codex.WidgetDesignType.FillingType.*;
import static com.sammy.malum.client.screen.codex.WidgetDesignType.FrameType.RUNEWOOD;
import static com.sammy.malum.client.screen.codex.WidgetDesignType.FrameType.SOULWOOD;
import static com.sammy.malum.registry.common.item.MalumItems.*;

public class GeasEntries {

    public static void setupEntries(ArcanaProgressionScreen screen) {
        Item EMPTY = ItemStack.EMPTY.getItem();

        screen.addEntry("geas_magic", 0, 9, b -> b
                .configureWidget(w -> w.setIcon(SOUL_BRAZIER).setDesign(GILDED, RUNEWOOD, PAPER))
                .addPage(new HeadlineTextPage("geas_magic", "geas_magic.1"))
                .addPage(new CraftingPage(SOUL_BRAZIER.get(),
                        HALLOWED_GOLD_INGOT.get(), CTHONIC_GOLD.get(), HALLOWED_GOLD_INGOT.get(),
                        RUNEWOOD_PLANKS.get(), RUNEWOOD_PLANKS.get(), RUNEWOOD_PLANKS.get(),
                        TAINTED_ROCK.get(), RUNEWOOD_PLANKS.get(), TAINTED_ROCK.get()))
                .addPage(new TextPage("geas_magic.2"))
                .addPage(new TextPage("geas_magic.3"))
                .addPage(new TextPage("geas_magic.4"))
                .addPage(new TextPage("geas_magic.5"))
                .addReference(new EntryReference(PARACAUSAL_FLAME, BookEntry.create("undoing_geas_bindings")
                        .addPage(new HeadlineTextItemPage("undoing_geas_bindings", "undoing_geas_bindings.1", PARACAUSAL_FLAME.get()))
                        .addPage(new TextPage("undoing_geas_bindings.2"))
                        .addPage(SpiritInfusionPage.fromOutput(PARACAUSAL_FLAME.get()))
                ))
        );
        addBundledGeasEntry(screen, "pacts_of_an_uneven_conscience", 2, 10,
                MalumGeasEffectTypes.PACT_OF_DEFIANCE,
                MalumGeasEffectTypes.PACT_OF_THE_PARASITE,
                MalumGeasEffectTypes.PACT_OF_THE_WARLOCK,
                MalumGeasEffectTypes.PACT_OF_THE_REAPER
        );

        addBundledGeasEntry(screen, "pacts_of_an_inward_soul", -2, 10,
                MalumGeasEffectTypes.PACT_OF_THE_FORTRESS,
                MalumGeasEffectTypes.PACT_OF_THE_SHIELD,
                MalumGeasEffectTypes.PACT_OF_THE_SHATTERING_ADDICT,
                MalumGeasEffectTypes.PACT_OF_THE_ARCANAPHAGE
        );

        addBundledGeasEntry(screen, "pacts_of_an_unbound_wayfarer", 4, 11,
                MalumGeasEffectTypes.PACT_OF_SELF_CARE,
                MalumGeasEffectTypes.PACT_OF_THE_HIGH_PRIEST,
                MalumGeasEffectTypes.PACT_OF_THE_WINDSWEPT,
                MalumGeasEffectTypes.PACT_OF_THE_CONTINUING_SHOT
        );

        addBundledGeasEntry(screen, "pacts_of_an_igneous_shaper", -4, 11,
                MalumGeasEffectTypes.PACT_OF_CONTENTEDNESS,
                MalumGeasEffectTypes.PACT_OF_THE_LONE_DRUID,
                MalumGeasEffectTypes.PACT_OF_COMBUSTION,
                MalumGeasEffectTypes.PACT_OF_THE_PROSPECTOR
        );

        addGeasEntry(screen, MalumGeasEffectTypes.PACT_OF_THE_LIFEWEAVER, 15, 14);
        addGeasEntry(screen, MalumGeasEffectTypes.PACT_OF_THE_BERSERKER, 14, 15);

        addGeasEntry(screen, MalumGeasEffectTypes.PACT_OF_THE_SKYBREAKER, 13, 17);
        addGeasEntry(screen, MalumGeasEffectTypes.PACT_OF_THE_CLOUDSKIPPER, 15, 16);

        addGeasEntry(screen, MalumGeasEffectTypes.PACT_OF_TIDAL_AFFINITY, 14, 18);
        addGeasEntry(screen, MalumGeasEffectTypes.PACT_OF_PATIENCE_REPAID, 16, 17);

        addGeasEntry(screen, MalumGeasEffectTypes.PACT_OF_RECIPROCATION, -15, 14);
        addGeasEntry(screen, MalumGeasEffectTypes.PACT_OF_RUNE_EXPLOITATION, -14, 15);

        addGeasEntry(screen, MalumGeasEffectTypes.PACT_OF_THE_PROFANE_ASCETIC, -13, 17);
        addGeasEntry(screen, MalumGeasEffectTypes.PACT_OF_THE_PROFANE_GLUTTON, -15, 16);

        addGeasEntry(screen, MalumGeasEffectTypes.PACT_OF_WYRD_RECONSTRUCTION, -14, 18);
        addGeasEntry(screen, MalumGeasEffectTypes.PACT_OF_THE_PYROMANIAC, -16, 17);
    }

    @SafeVarargs
    public static void addBundledGeasEntry(AbstractProgressionCodexScreen screen, String name, int x, int y,
                                           Holder<GeasEffectType>... geasa) {
        var definingSpirit = geasa[0].value().spiritTypes.getFirst();
        var acceptor = new SubspaceEntryObject.SubspaceWidgetSupplier();

        int horizontalOffset = x > 0 ? 1 : -1;
        addGeasEntry(acceptor, geasa[0], x-horizontalOffset, y);
        addGeasEntry(acceptor, geasa[1], x, y+1);
        addGeasEntry(acceptor, geasa[2], x, y-1);
        addGeasEntry(acceptor, geasa[3], x+horizontalOffset, y);

        screen.addEntry(name, x, y, b -> b
                .setWidgetSupplier(acceptor)
                .configureWidget(w -> w.setDesign(SUBENTRY, RUNEWOOD, DARK))
                .setAssociatedSpirit(definingSpirit));
    }

    public static void addGeasEntry(PlacedEntryAcceptor acceptor, Holder<GeasEffectType> geas, int x, int y) {
        acceptor.addEntry(geas.value().getRegistryName().getPath(), x, y, b -> b
                .configureWidget(w -> w.setIcon(geas).setDesign(DEFAULT, RUNEWOOD, DARK))
                .setAssociatedSpirit(geas.value().getIdentifyingSpirit())
                .addPage(SoulBindingPage.fromGeas(geas))
                .addPage(new GeasInfoPage(geas))
        );
    }

    public static void addSoulwoodGeasEntry(PlacedEntryAcceptor acceptor, Holder<GeasEffectType> geas, int x, int y) {
        acceptor.addEntry(geas.value().getRegistryName().getPath(), x, y, b -> b
                .configureWidget(w -> w.setIcon(geas).setDesign(DEFAULT, SOULWOOD, DARK))
                .addPage(SoulBindingPage.fromGeas(geas))
                .addPage(new GeasInfoPage(geas))
        );
    }
}