package com.sammy.malum.client.screen.codex;

import java.util.*;

public class EntryStorage {

    protected final List<PlacedBookEntryBuilder> unbaked = new ArrayList<>();
    protected final List<PlacedBookEntry> entries = new ArrayList<>();

    protected boolean isBaked;

    public List<PlacedBookEntry> bakeEntries() {
        if (isBaked) {
            return entries;
        }
        for (PlacedBookEntryBuilder entry : getUnbakedEntries()) {
            if (entry.hasFragment()) {
                var fragment = entry.buildFragment();
                getEntries().add(fragment);
            }
            var build = entry.build();
            getEntries().add(build);
        }
        isBaked = true;
        return entries;
    }

    public void add(PlacedBookEntryBuilder entryBuilder) {
        unbaked.add(entryBuilder);
    }

    public List<PlacedBookEntry> getEntries() {
        return entries;
    }

    public List<PlacedBookEntryBuilder> getUnbakedEntries() {
        return unbaked;
    }
}
