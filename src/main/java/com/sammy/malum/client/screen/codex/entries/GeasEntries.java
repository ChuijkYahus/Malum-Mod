package com.sammy.malum.client.screen.codex.entries;

import com.sammy.malum.client.screen.codex.BookWidgetStyle;
import com.sammy.malum.client.screen.codex.pages.text.HeadlineTextItemPage;
import com.sammy.malum.client.screen.codex.pages.text.TextPage;
import com.sammy.malum.client.screen.codex.screens.ArcanaProgressionScreen;
import com.sammy.malum.registry.common.MalumGeasEffectTypeRegistry;
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
        );
        screen.addEntry("undoing_geas_bindings", 0, 11, b -> b
                .configureWidget(w -> w.setIcon(BARRIER).setStyle(BookWidgetStyle.SMALL_RUNEWOOD))
                .addPage(new HeadlineTextItemPage("undoing_geas_bindings", "undoing_geas_bindings.1", SOUL_BRAZIER.get()))
                .addPage(new TextPage("geas_magic.2"))
                .addPage(new TextPage("geas_magic.3"))
        );

        screen.addEntry("pact_of_the_dayblessed", 1, 11, b -> b
                .configureWidget(w -> w.setIcon(MalumGeasEffectTypeRegistry.PACT_OF_THE_DAYBLESSED).setStyle(BookWidgetStyle.DARK_RUNEWOOD))
        );
        screen.addEntry("pact_of_the_nightchild", -1, 11, b -> b
                .configureWidget(w -> w.setIcon(MalumGeasEffectTypeRegistry.PACT_OF_THE_NIGHTCHILD).setStyle(BookWidgetStyle.DARK_RUNEWOOD))
        );
        screen.addEntry("pact_of_the_shattering_addict", 0, 12, b -> b
                .configureWidget(w -> w.setIcon(MalumGeasEffectTypeRegistry.PACT_OF_THE_SHATTERING_ADDICT).setStyle(BookWidgetStyle.DARK_RUNEWOOD))
        );

        screen.addEntry("pact_of_the_shield", -2, 11, b -> b
                .configureWidget(w -> w.setIcon(MalumGeasEffectTypeRegistry.PACT_OF_THE_SHIELD).setStyle(BookWidgetStyle.DARK_RUNEWOOD))
        );
        screen.addEntry("pact_of_the_reaper", -2, 12, b -> b
                .configureWidget(w -> w.setIcon(MalumGeasEffectTypeRegistry.PACT_OF_THE_REAPER).setStyle(BookWidgetStyle.DARK_RUNEWOOD))
        );
        screen.addEntry("pact_of_the_fortress", -3, 12, b -> b
                .configureWidget(w -> w.setIcon(MalumGeasEffectTypeRegistry.PACT_OF_THE_FORTRESS).setStyle(BookWidgetStyle.DARK_RUNEWOOD))
        );

        screen.addEntry("pact_of_the_lifelong", 2, 11, b -> b
                .configureWidget(w -> w.setIcon(MalumGeasEffectTypeRegistry.PACT_OF_THE_LIFELONG).setStyle(BookWidgetStyle.DARK_RUNEWOOD))
        );
        screen.addEntry("pact_of_the_cloudskipper", 2, 12, b -> b
                .configureWidget(w -> w.setIcon(MalumGeasEffectTypeRegistry.PACT_OF_THE_CLOUDSKIPPER).setStyle(BookWidgetStyle.DARK_RUNEWOOD))
        );
        screen.addEntry("pact_of_the_enduring", 3, 12, b -> b
                .configureWidget(w -> w.setIcon(MalumGeasEffectTypeRegistry.PACT_OF_THE_ENDURING).setStyle(BookWidgetStyle.DARK_RUNEWOOD))
        );
    }
}