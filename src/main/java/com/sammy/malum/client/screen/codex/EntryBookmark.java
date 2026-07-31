package com.sammy.malum.client.screen.codex;

import com.sammy.malum.client.screen.codex.display.gizmo.DisplayedGizmo;

public final class EntryBookmark {

    public final DisplayedGizmo icon;
    public final BookEntry entry;

    public EntryBookmark(DisplayedGizmo icon, BookEntry entry) {
        this.icon = icon;
        this.entry = entry;
    }

    public EntryBookmark(DisplayedGizmo icon, BookEntryBuilder builder) {
        this(icon, builder.build());
    }
}