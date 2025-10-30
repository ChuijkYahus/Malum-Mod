package com.sammy.malum.client.screen.codex.handlers;

import com.sammy.malum.client.screen.codex.*;
import com.sammy.malum.client.screen.codex.objects.*;
import com.sammy.malum.client.screen.codex.objects.progression.*;
import com.sammy.malum.client.screen.codex.screens.progression.*;
import net.minecraft.*;

import java.util.*;

public class EntryObjectHandler extends BookObjectHandler<AbstractProgressionCodexScreen> {

    protected final HashMap<PlacedBookEntry, ProgressionEntryObject> entryObjectMap = new HashMap<>();

    public EntryObjectHandler() {
        super();
    }

    public void setupEntryObjects(AbstractProgressionCodexScreen screen) {
        setupEntryObjects(screen, screen.getEntries());
    }

    public void setupEntryObjects(AbstractProgressionCodexScreen screen, List<PlacedBookEntry> entries) {
        objects.clear();
        entryObjectMap.clear();

        ArrayList<ProgressionEntryObject> objects = new ArrayList<>();
        ArrayList<SubspaceEntryObject> subspaceObjects = new ArrayList<>();
        for (PlacedBookEntry entry : entries) {
            var data = entry.getWidgetData();
            var bookObject = data.widgetSupplier().getBookObject(entry, data.xOffset(), -data.yOffset());
            var config = data.widgetConfig();
            if (config != null) {
                config.accept(bookObject);
            }
            if (bookObject instanceof SubspaceEntryObject subspaceObject) {
                subspaceObjects.add(subspaceObject);
                continue;
            }
            objects.add(bookObject);
            entryObjectMap.put(entry, bookObject);
        }
        //Subspace objects come first to enable priority for interaction
        addAll(subspaceObjects);
        addAll(objects);
    }

    public ProgressionEntryObject getOriginObject() {
        for (BookObject<AbstractProgressionCodexScreen> object : objects) {
            if (object instanceof ProgressionEntryObject progressionEntryObject && progressionEntryObject.isOrigin) {
                return progressionEntryObject;
            }
        }
        return null;
    }

    public ProgressionEntryObject getObject(String entryName) {
        for (var entry : entryObjectMap.keySet()) {
            if (entry.identifier.equals(entryName)) {
                return entryObjectMap.get(entry);
            }
        }
        return null;
    }

    public ProgressionEntryObject getObject(PlacedBookEntry entry) {
        return entryObjectMap.get(entry);
    }
}