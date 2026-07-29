package com.sammy.malum.client.screen.codex.pages;

import com.sammy.malum.client.screen.codex.*;
import com.sammy.malum.client.screen.codex.display.gizmo.DisplayedGizmo;

public final class EntryReference {
    public final DisplayedGizmo gizmo;
    public final BookEntry entry;

    public EntryReference(DisplayedGizmo gizmo, BookEntry entry) {
        this.gizmo = gizmo;
        this.entry = entry;
    }

    public EntryReference(DisplayedGizmo gizmo, BookEntryBuilder builder) {
        this(gizmo.noTooltip(), builder.build());
    }
}
