package com.sammy.malum.client.screen.codex;

import java.util.*;
import java.util.function.*;

public interface PlacedEntryAcceptor {

    default void addEntry(String identifier, int xOffset, int yOffset) {
        addEntry(identifier, xOffset, yOffset, b -> {
        });
    }

    default void addEntry(String identifier, int xOffset, int yOffset, Consumer<PlacedBookEntryBuilder> modifier) {
        var builder = PlacedBookEntry.create(identifier, xOffset, yOffset);
        modifier.accept(builder);
        if (builder.hasFragment()) {
            getEntries().add(builder.buildFragment());
        }
        getEntries().add(builder.build());
    }

    Collection<PlacedBookEntry> getEntries();
}
