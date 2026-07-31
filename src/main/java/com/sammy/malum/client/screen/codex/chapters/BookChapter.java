package com.sammy.malum.client.screen.codex.chapters;

import com.sammy.malum.client.screen.codex.EntryStorage;
import com.sammy.malum.client.screen.codex.EntryAcceptor;
import com.sammy.malum.client.screen.codex.handlers.ProgressionObjectHandler;
import com.sammy.malum.client.screen.codex.screens.progression.AbstractProgressionCodexScreen;

import java.awt.*;

public abstract class BookChapter implements EntryAcceptor {

    protected final EntryStorage entries;

    public BookChapter() {
        this.entries = new EntryStorage();
        init();
    }

    public abstract void init();

    @Override
    public EntryStorage getEntryStorage() {
        return entries;
    }

    public void place(AbstractProgressionCodexScreen screen, ProgressionObjectHandler handler) {
        handler.setupEntryObjects(screen, entries);
    }
}
