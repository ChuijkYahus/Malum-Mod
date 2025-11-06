package com.sammy.malum.client.screen.codex;

import com.sammy.malum.client.screen.codex.pages.*;
import net.minecraft.*;

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
        final PlacedBookEntry build = builder.build();

        getEntries().add(build);
    }

    Collection<PlacedBookEntry> getEntries();
}
