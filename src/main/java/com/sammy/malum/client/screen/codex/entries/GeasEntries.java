package com.sammy.malum.client.screen.codex.entries;

import com.sammy.malum.client.screen.codex.BookEntry;
import com.sammy.malum.client.screen.codex.BookWidgetStyle;
import com.sammy.malum.client.screen.codex.objects.progression.RiteEntryObject;
import com.sammy.malum.client.screen.codex.pages.CyclingPage;
import com.sammy.malum.client.screen.codex.pages.EntryReference;
import com.sammy.malum.client.screen.codex.pages.EntrySelectorPage;
import com.sammy.malum.client.screen.codex.pages.recipe.*;
import com.sammy.malum.client.screen.codex.pages.recipe.vanilla.CraftingPage;
import com.sammy.malum.client.screen.codex.pages.recipe.vanilla.SmeltingPage;
import com.sammy.malum.client.screen.codex.pages.text.HeadlineTextItemPage;
import com.sammy.malum.client.screen.codex.pages.text.HeadlineTextPage;
import com.sammy.malum.client.screen.codex.pages.text.SpiritRiteTextPage;
import com.sammy.malum.client.screen.codex.pages.text.TextPage;
import com.sammy.malum.client.screen.codex.screens.ArcanaProgressionScreen;
import com.sammy.malum.registry.common.MalumGeasEffectTypeRegistry;
import com.sammy.malum.registry.common.SpiritRiteRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
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
                .configureWidget(w -> w.setIcon(MalumGeasEffectTypeRegistry.PACT_OF_THE_DAYBLESSED))
        );
        screen.addEntry("pact_of_the_nightchild", -1, 11, b -> b
                .configureWidget(w -> w.setIcon(MalumGeasEffectTypeRegistry.PACT_OF_THE_NIGHTCHILD))
        );
        screen.addEntry("pact_of_the_shattering_addict", 0, 12, b -> b
                .configureWidget(w -> w.setIcon(MalumGeasEffectTypeRegistry.PACT_OF_THE_SHATTERING_ADDICT))
        );

        screen.addEntry("pact_of_the_shield", -2, 11, b -> b
                .configureWidget(w -> w.setIcon(MalumGeasEffectTypeRegistry.PACT_OF_THE_SHIELD))
        );
        screen.addEntry("pact_of_the_fortress", -3, 12, b -> b
                .configureWidget(w -> w.setIcon(MalumGeasEffectTypeRegistry.PACT_OF_THE_FORTRESS))
        );

        screen.addEntry("pact_of_the_animated", 2, 11, b -> b
                .configureWidget(w -> w.setIcon(MalumGeasEffectTypeRegistry.PACT_OF_THE_ANIMATED))
        );
        screen.addEntry("pact_of_the_wise", 3, 12, b -> b
                .configureWidget(w -> w.setIcon(MalumGeasEffectTypeRegistry.PACT_OF_THE_ANIMATED))
        );
    }
}