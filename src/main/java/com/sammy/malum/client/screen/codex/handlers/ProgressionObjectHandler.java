package com.sammy.malum.client.screen.codex.handlers;

import com.sammy.malum.client.screen.codex.*;
import com.sammy.malum.client.screen.codex.objects.*;
import com.sammy.malum.client.screen.codex.screens.progression.*;

import java.util.*;

public class ProgressionObjectHandler extends BookObjectHandler<AbstractProgressionCodexScreen> {

    public ProgressionObjectHandler() {
        super();
    }

    public void setupEntryObjects(AbstractProgressionCodexScreen screen, EntryStorage entryStorage) {
        objects.clear();

        var baked = entryStorage.placeEntries();
        var objects = new ArrayList<ProgressionEntryObject>();
        for (PlacedBookEntry entry : baked) {
            var bookObject = entry.createBookObject();
            objects.add(bookObject);
            bookObject.additionalSetup(screen);
        }
        addAll(objects);
    }
}