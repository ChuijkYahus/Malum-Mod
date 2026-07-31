package com.sammy.malum.client.screen.codex;

import java.awt.*;
import java.util.*;
import java.util.List;

public class EntryStorage {

    protected final List<PlacedBookEntryBuilder> unbaked = new ArrayList<>();
    protected final List<PlacedBookEntry> baked = new ArrayList<>();

    protected boolean isBaked;

    public List<PlacedBookEntry> placeEntries() {
        if (isBaked) {
            return baked;
        }
        var baked = getBaked();
        for (PlacedBookEntryBuilder builder : unbaked) {
            baked.add(builder.place());
        }
        isBaked = true;
        return baked;
    }

    public void add(PlacedBookEntryBuilder builder) {
        unbaked.add(builder);
    }

    public List<PlacedBookEntry> getBaked() {
        return baked;
    }
}
