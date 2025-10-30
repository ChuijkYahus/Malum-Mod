package com.sammy.malum.client.screen.codex.entries;

import com.sammy.malum.client.screen.codex.pages.recipe.*;
import com.sammy.malum.client.screen.codex.pages.recipe.vanilla.*;
import com.sammy.malum.client.screen.codex.pages.text.*;
import com.sammy.malum.client.screen.codex.screens.progression.*;
import net.minecraft.world.item.*;

import static com.sammy.malum.client.screen.codex.WidgetDesignType.FillingType.PAPER;
import static com.sammy.malum.client.screen.codex.WidgetDesignType.FrameType.RUNEWOOD;
import static com.sammy.malum.client.screen.codex.WidgetDesignType.GILDED;
import static com.sammy.malum.registry.common.item.MalumItems.*;

public class RuneWorkingEntries {

    public static void setupEntries(ArcanaProgressionScreen screen) {
        Item EMPTY = ItemStack.EMPTY.getItem();

        screen.addEntry("runeworking", -13, 8, b -> b
                .configureWidget(w -> w.setIcon(RUNIC_WORKBENCH).setDesign(GILDED, RUNEWOOD, PAPER))
                .addPage(new HeadlineTextItemPage("runeworking", "runeworking.1", RUNIC_WORKBENCH.get()))
                .addPage(new TextPage("runeworking.2"))
                .addPage(new TextPage("runeworking.3"))
                .addPage(SpiritInfusionPage.fromOutput(RUNIC_WORKBENCH.get()))
        );

        screen.addEntry("runic_brooch", -13, 10, b -> b
                .configureWidget(w -> w.setIcon(RUNIC_BROOCH))
                .addPage(new HeadlineTextPage("runic_brooch", "runic_brooch.1"))
                .addPage(CraftingPage.broochPage(RUNIC_BROOCH.get(), HALLOWED_GOLD_INGOT.get(), BLOCK_OF_HALLOWED_GOLD.get()))
        );

        screen.addEntry("elaborate_brooch", -12, 11, b -> b
                .configureWidget(w -> w.setIcon(ELABORATE_BROOCH))
                .addPage(new HeadlineTextPage("elaborate_brooch", "elaborate_brooch.1"))
                .addPage(CraftingPage.broochPage(ELABORATE_BROOCH.get(), SOUL_STAINED_STEEL_INGOT.get(), BLOCK_OF_SOUL_STAINED_STEEL.get()))
        );

        screen.addEntry("glass_brooch", -14, 11, b -> b
                .configureWidget(w -> w.setIcon(GLASS_BROOCH))
                .addPage(new HeadlineTextPage("glass_brooch", "glass_brooch.1"))
                .addPage(SpiritInfusionPage.fromOutput(GLASS_BROOCH.get()))
        );

        screen.addEntry("gluttonous_brooch", -13, 12, b -> b
                .configureWidget(w -> w.setIcon(GLUTTONOUS_BROOCH))
                .addPage(new HeadlineTextPage("gluttonous_brooch", "gluttonous_brooch.1"))
                .addPage(SpiritInfusionPage.fromOutput(GLUTTONOUS_BROOCH.get()))
        );

        screen.addEntry("rune_of_vitality", -14, 7, b -> b
                .configureWidget(w -> w.setIcon(RUNE_OF_VITALITY))
                .addPage(new HeadlineTextPage("rune_of_vitality", "rune_of_vitality.1"))
                .addPage(RuneworkingPage.fromOutput(RUNE_OF_VITALITY.get()))
        );

        screen.addEntry("rune_of_culling", -15, 7, b -> b
                .configureWidget(w -> w.setIcon(RUNE_OF_CULLING))
                .addPage(new HeadlineTextPage("rune_of_culling", "rune_of_culling.1"))
                .addPage(RuneworkingPage.fromOutput(RUNE_OF_CULLING.get()))
        );

        screen.addEntry("rune_of_dexterity", -16, 8, b -> b
                .configureWidget(w -> w.setIcon(RUNE_OF_DEXTERITY))
                .addPage(new HeadlineTextPage("rune_of_dexterity", "rune_of_dexterity.1"))
                .addPage(RuneworkingPage.fromOutput(RUNE_OF_DEXTERITY.get()))
        );

        screen.addEntry("rune_of_scorching", -15, 8, b -> b
                .configureWidget(w -> w.setIcon(RUNE_OF_SCORCHING))
                .addPage(new HeadlineTextPage("rune_of_scorching", "rune_of_scorching.1"))
                .addPage(RuneworkingPage.fromOutput(RUNE_OF_SCORCHING.get()))
        );

        screen.addEntry("rune_of_ailment_cleansing", -15, 9, b -> b
                .configureWidget(w -> w.setIcon(RUNE_OF_AILMENT_CLEANSING))
                .addPage(new HeadlineTextPage("rune_of_ailment_cleansing", "rune_of_ailment_cleansing.1"))
                .addPage(RuneworkingPage.fromOutput(RUNE_OF_AILMENT_CLEANSING.get()))
        );

        screen.addEntry("rune_of_protection", -16, 9, b -> b
                .configureWidget(w -> w.setIcon(RUNE_OF_PROTECTION))
                .addPage(new HeadlineTextPage("rune_of_protection", "rune_of_protection.1"))
                .addPage(RuneworkingPage.fromOutput(RUNE_OF_PROTECTION.get()))
        );

        screen.addEntry("rune_of_reinforcement", -16, 10, b -> b
                .configureWidget(w -> w.setIcon(RUNE_OF_REINFORCEMENT))
                .addPage(new HeadlineTextPage("rune_of_reinforcement", "rune_of_reinforcement.1"))
                .addPage(RuneworkingPage.fromOutput(RUNE_OF_REINFORCEMENT.get()))
        );

        screen.addEntry("rune_of_volatile_distortion", -17, 10, b -> b
                .configureWidget(w -> w.setIcon(RUNE_OF_VOLATILE_DISTORTION))
                .addPage(new HeadlineTextPage("rune_of_volatile_distortion", "rune_of_volatile_distortion.1"))
                .addPage(RuneworkingPage.fromOutput(RUNE_OF_VOLATILE_DISTORTION.get()))
        );
    }

}
