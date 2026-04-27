package com.sammy.malum.client.screen.codex;

import com.sammy.malum.client.screen.codex.pages.*;
import net.minecraft.*;

import java.util.*;
import java.util.function.*;

public interface PlacedEntryAcceptor {

    default PlacedBookEntryBuilder addEntry(String identifier, int xOffset, int yOffset) {
        var builder = PlacedBookEntry.create(identifier, xOffset, yOffset);
        getEntryStorage().add(builder);
        return builder;
    }

    default void bakeEntries() {
        getEntryStorage().bakeEntries();
    }

    EntryStorage getEntryStorage();
}
