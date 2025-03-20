package com.sammy.malum.client.screen.codex.entries;

import com.sammy.malum.client.screen.codex.BookWidgetStyle;
import com.sammy.malum.client.screen.codex.pages.recipe.*;
import com.sammy.malum.client.screen.codex.pages.text.*;
import com.sammy.malum.client.screen.codex.screens.ArcanaProgressionScreen;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.MalumGeasEffectTypeRegistry;
import net.minecraft.core.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import static com.sammy.malum.registry.common.item.ItemRegistry.*;
import static net.minecraft.world.item.Items.*;

public class GeasEntries {

    public static void setupEntries(ArcanaProgressionScreen screen) {
        Item EMPTY = ItemStack.EMPTY.getItem();

        screen.addEntry("geas_magic", 0, 10, b -> b
                .configureWidget(w -> w.setIcon(SOUL_BRAZIER).setStyle(BookWidgetStyle.GILDED_RUNEWOOD))
                .addPage(new HeadlineTextItemPage("geas_magic", "geas_magic.1", SOUL_BRAZIER.get()))
                .addPage(new TextPage("geas_magic.2"))
                .addPage(new TextPage("geas_magic.3"))
                .addPage(SpiritInfusionPage.fromOutput(SOUL_BRAZIER.get()))
        );
        screen.addEntry("undoing_geas_bindings", 0, 11, b -> b
                .configureWidget(w -> w.setIcon(BARRIER).setStyle(BookWidgetStyle.SMALL_RUNEWOOD))
                .addPage(new HeadlineTextItemPage("undoing_geas_bindings", "undoing_geas_bindings.1", BARRIER))
                .addPage(new TextPage("undoing_geas_bindings.2"))
                .addPage(SpiritInfusionPage.fromOutput(FLAME_OF_UNDOING.get()))
        );

        addGeasEntry(screen, MalumGeasEffectTypeRegistry.PACT_OF_THE_DAYBLESSED, 1, 11);
        addGeasEntry(screen, MalumGeasEffectTypeRegistry.PACT_OF_THE_NIGHTCHILD, -1, 11);
        addGeasEntry(screen, MalumGeasEffectTypeRegistry.PACT_OF_THE_SHATTERING_ADDICT, 0, 12);

        addGeasEntry(screen, MalumGeasEffectTypeRegistry.PACT_OF_THE_SHADEWALKER, -2, 12);
        addGeasEntry(screen, MalumGeasEffectTypeRegistry.PACT_OF_THE_SUNKISSED, 2, 12);

        addGeasEntry(screen, MalumGeasEffectTypeRegistry.PACT_OF_THE_SHIELD, -2, 11);
        addGeasEntry(screen, MalumGeasEffectTypeRegistry.PACT_OF_THE_FORTRESS, -3, 12);

        addGeasEntry(screen, MalumGeasEffectTypeRegistry.PACT_OF_THE_DEFIANT, 2, 11);
        addGeasEntry(screen, MalumGeasEffectTypeRegistry.PACT_OF_THE_LIFEWEAVER, 3, 12);

        addGeasEntry(screen, MalumGeasEffectTypeRegistry.PACT_OF_THE_PROFANE_ASCETIC, -13, 17);
        addGeasEntry(screen, MalumGeasEffectTypeRegistry.PACT_OF_THE_PROFANE_GLUTTON, -15, 16);
        addGeasEntry(screen, MalumGeasEffectTypeRegistry.PACT_OF_WYRD_RECONSTRUCTION, -17, 15);
        addGeasEntry(screen, MalumGeasEffectTypeRegistry.PACT_OF_THE_REAPER, -18, 13);
        addGeasEntry(screen, MalumGeasEffectTypeRegistry.PACT_OF_RECIPROCATION, -19, 11);

        addGeasEntry(screen, MalumGeasEffectTypeRegistry.PACT_OF_THE_SKYBREAKER, 13, 17);
        addGeasEntry(screen, MalumGeasEffectTypeRegistry.PACT_OF_THE_CLOUDSKIPPER, 15, 16);
        addGeasEntry(screen, MalumGeasEffectTypeRegistry.PACT_OF_THE_PYROMANIAC, 17, 15);
//        addGeasEntry(screen, MalumGeasEffectTypeRegistry.PACT_OF_THE_REAPER, 18, 13);
//        addGeasEntry(screen, MalumGeasEffectTypeRegistry.PACT_OF_RECIPROCATION, 19, 11);

    }

    public static void addGeasEntry(ArcanaProgressionScreen screen, Holder<GeasEffectType> geas, int x, int y) {
        screen.addEntry(geas.value().getId().getPath(), x, y, b -> b
                .configureWidget(w -> w.setIcon(geas).setStyle(BookWidgetStyle.DARK_RUNEWOOD))
                .addPage(SoulBindingPage.fromGeas(geas))
                .addPage(new GeasInfoPage(geas))
        );
    }
}