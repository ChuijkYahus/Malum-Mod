package com.sammy.malum.client.screen.codex.handlers;

import com.mojang.blaze3d.platform.*;
import com.sammy.malum.client.screen.codex.*;
import com.sammy.malum.client.screen.codex.objects.progression.*;
import com.sammy.malum.client.screen.codex.screens.progression.*;
import net.minecraft.client.*;

import java.util.*;

public class EntryObjectHandler extends BookObjectHandler<AbstractProgressionCodexScreen> {
    public EntryObjectHandler() {
        super();
    }

    public void setupEntryObjects(AbstractProgressionCodexScreen screen) {
        setupEntryObjects(screen, screen.getEntries());
    }

    public void setupEntryObjects(AbstractProgressionCodexScreen screen, List<PlacedBookEntry> entries) {
        objects.clear();
        //Cherry Picked Values
        int left = 388;
        int top = 60;
        for (PlacedBookEntry entry : entries) {
            var data = entry.getWidgetData();
            var bookObject = data.widgetSupplier().getBookObject(entry, left + data.xOffset(), top - data.yOffset());
            var config = data.widgetConfig();
            if (config != null) {
                config.accept(bookObject);
            }
            add(bookObject);
        }
    }
}