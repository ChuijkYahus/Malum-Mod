package com.sammy.malum.client.screen.codex.entries;

import com.sammy.malum.client.screen.codex.*;
import com.sammy.malum.client.screen.codex.pages.*;
import com.sammy.malum.client.screen.codex.pages.recipe.*;
import com.sammy.malum.client.screen.codex.pages.recipe.vanilla.*;
import com.sammy.malum.client.screen.codex.pages.text.*;
import com.sammy.malum.client.screen.codex.screens.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.MalumGeasEffectTypes;
import net.minecraft.core.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import static com.sammy.malum.registry.common.item.MalumItems.*;

public class GeasEntries {

    public static void setupEntries(ArcanaProgressionScreen screen) {
        Item EMPTY = ItemStack.EMPTY.getItem();

        screen.addEntry("geas_magic", 0, 10, b -> b
                .configureWidget(w -> w.setIcon(SOUL_BRAZIER).setStyle(BookWidgetStyle.GILDED_RUNEWOOD))
                .addPage(new HeadlineTextPage("geas_magic", "geas_magic.1"))
                .addPage(new CraftingPage(SOUL_BRAZIER.get(),
                        HALLOWED_GOLD_INGOT.get(), CTHONIC_GOLD.get(), HALLOWED_GOLD_INGOT.get(),
                        RUNEWOOD_PLANKS.get(), RUNEWOOD_PLANKS.get(), RUNEWOOD_PLANKS.get(),
                        TAINTED_ROCK.get(), RUNEWOOD_PLANKS.get(), TAINTED_ROCK.get()))
                .addPage(new TextPage("geas_magic.2"))
                .addPage(new TextPage("geas_magic.3"))
                .addPage(new TextPage("geas_magic.4"))
                .addPage(new TextPage("geas_magic.5"))
                .addReference(new EntryReference(PARACAUSAL_FLAME, BookEntry.build("undoing_geas_bindings")
                        .addPage(new HeadlineTextItemPage("undoing_geas_bindings", "undoing_geas_bindings.1", PARACAUSAL_FLAME.get()))
                        .addPage(new TextPage("undoing_geas_bindings.2"))
                        .addPage(SpiritInfusionPage.fromOutput(PARACAUSAL_FLAME.get()))
                ))
        );

        addGeasEntry(screen, MalumGeasEffectTypes.PACT_OF_DEFIANCE, 2, 11);
        addGeasEntry(screen, MalumGeasEffectTypes.PACT_OF_THE_PARASITE, 3, 12);

        addGeasEntry(screen, MalumGeasEffectTypes.PACT_OF_THE_WARLOCK, 1, 11);
        addGeasEntry(screen, MalumGeasEffectTypes.PACT_OF_THE_REAPER, 2, 12);

        addGeasEntry(screen, MalumGeasEffectTypes.PACT_OF_THE_FORTRESS, -1, 11);
        addGeasEntry(screen, MalumGeasEffectTypes.PACT_OF_THE_SHIELD, -2, 12);

        addGeasEntry(screen, MalumGeasEffectTypes.PACT_OF_THE_SHATTERING_ADDICT, -2, 11);
        addGeasEntry(screen, MalumGeasEffectTypes.PACT_OF_THE_ARCANAPHAGE, -3, 12);

        addGeasEntry(screen, MalumGeasEffectTypes.PACT_OF_SELF_CARE, 4, 13);
        addGeasEntry(screen, MalumGeasEffectTypes.PACT_OF_THE_HIGH_PRIEST, 5, 14);

        addGeasEntry(screen, MalumGeasEffectTypes.PACT_OF_THE_WINDSWEPT, 5, 13);
        addGeasEntry(screen, MalumGeasEffectTypes.PACT_OF_THE_CONTINUING_SHOT, 6, 14);

        addGeasEntry(screen, MalumGeasEffectTypes.PACT_OF_CONTENTEDNESS, -5, 13);
        addGeasEntry(screen, MalumGeasEffectTypes.PACT_OF_THE_LONE_DRUID, -6, 14);

        addGeasEntry(screen, MalumGeasEffectTypes.PACT_OF_THE_PROSPECTOR, -4, 13);
        addGeasEntry(screen, MalumGeasEffectTypes.PACT_OF_COMBUSTION, -5, 14);

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

    public static void addGeasEntry(AbstractProgressionCodexScreen screen, Holder<GeasEffectType> geas, int x, int y) {
        screen.addEntry(geas.value().getRegistryName().getPath(), x, y, b -> b
                .configureWidget(w -> w.setIcon(geas).setStyle(BookWidgetStyle.DARK_RUNEWOOD))
                .addPage(SoulBindingPage.fromGeas(geas))
                .addPage(new GeasInfoPage(geas))
        );
    }
    public static void addSoulwoodGeasEntry(AbstractProgressionCodexScreen screen, Holder<GeasEffectType> geas, int x, int y) {
        screen.addEntry(geas.value().getRegistryName().getPath(), x, y, b -> b
                .configureWidget(w -> w.setIcon(geas).setStyle(BookWidgetStyle.DARK_SOULWOOD))
                .addPage(SoulBindingPage.fromGeas(geas))
                .addPage(new GeasInfoPage(geas))
        );
    }
}