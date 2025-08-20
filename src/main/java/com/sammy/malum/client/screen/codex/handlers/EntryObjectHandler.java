package com.sammy.malum.client.screen.codex.handlers;

import com.sammy.malum.client.screen.codex.*;
import com.sammy.malum.client.screen.codex.objects.progression.*;
import com.sammy.malum.client.screen.codex.screens.progression.*;

public class EntryObjectHandler extends BookObjectHandler<AbstractProgressionCodexScreen> {
    public EntryObjectHandler() {
        super();
    }

    public void setupEntryObjects(AbstractProgressionCodexScreen screen) {
        objects.clear();

        int left = screen.getGuiLeft() + AbstractProgressionCodexScreen.BOOK_INSIDE_WIDTH;
        int top = screen.getGuiTop() + AbstractProgressionCodexScreen.BOOK_INSIDE_HEIGHT;
        for (PlacedBookEntry entry : screen.getEntries()) {
            var data = entry.getWidgetData();
            var bookObject = data.widgetSupplier().getBookObject(entry, left + data.xOffset(), top - data.yOffset());
            var config = data.widgetConfig();
            if (config != null) {
                config.accept(bookObject);
            }
            add(bookObject);
        }
        screen.faceObject(objects.get(1));
    }
}