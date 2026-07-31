package com.sammy.malum.client.screen.codex.handlers;

import com.sammy.malum.client.screen.codex.*;
import com.sammy.malum.client.screen.codex.objects.*;
import com.sammy.malum.client.screen.codex.screens.progression.*;

public class SubspaceProgressionObjectHandler extends ProgressionObjectHandler {

    @Override
    public void setupEntryObjects(AbstractProgressionCodexScreen screen, EntryStorage entryStorage) {
        super.setupEntryObjects(screen, entryStorage);
        for (BookObject<AbstractProgressionCodexScreen> object : objects) {
            //A bit ugly, ideally this should be part of some sorta builder, but who cares
            object.isInSubspace = true;
        }
    }
}
