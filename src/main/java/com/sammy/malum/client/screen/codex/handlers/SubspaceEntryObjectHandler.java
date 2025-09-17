package com.sammy.malum.client.screen.codex.handlers;

import com.sammy.malum.client.screen.codex.*;
import com.sammy.malum.client.screen.codex.objects.*;
import com.sammy.malum.client.screen.codex.screens.progression.*;

import java.util.*;

public class SubspaceEntryObjectHandler extends EntryObjectHandler {

    @Override
    public void setupEntryObjects(AbstractProgressionCodexScreen screen, List<PlacedBookEntry> entries) {
        super.setupEntryObjects(screen, entries);
        for (BookObject<AbstractProgressionCodexScreen> object : objects) {
            //A bit ugly, ideally this should be part of some sorta builder, but who cares
            object.isSubspace = true;
        }
    }
}
