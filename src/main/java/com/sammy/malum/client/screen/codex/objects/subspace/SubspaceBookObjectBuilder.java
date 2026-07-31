package com.sammy.malum.client.screen.codex.objects.subspace;

import com.sammy.malum.client.screen.codex.EntryAcceptor;
import com.sammy.malum.client.screen.codex.EntryStorage;
import com.sammy.malum.client.screen.codex.PlacedBookEntry;
import com.sammy.malum.client.screen.codex.PlacedBookEntryBuilder;
import com.sammy.malum.client.screen.codex.objects.ProgressionEntryObject;

import java.util.function.Consumer;

public class SubspaceBookObjectBuilder implements PlacedBookEntry.BookObjectBuilder, EntryAcceptor {

    protected final EntryStorage entryStorage = new EntryStorage();
    protected final int x, y;
    protected int size = 160;

    public SubspaceBookObjectBuilder(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public SubspaceBookObjectBuilder setSize(int size) {
        this.size = size;
        return this;
    }

    @Override
    public PlacedBookEntryBuilder addEntry(String identifier, int xOffset, int yOffset) {
        return EntryAcceptor.super.addEntry(identifier, x+xOffset, y-yOffset);
    }

    @Override
    public SubspaceBookObjectBuilder addSubspaceEntry(String identifier, int x, int y, Consumer<PlacedBookEntryBuilder> config) {
        throw new UnsupportedOperationException();
    }

    @Override
    public EntryStorage getEntryStorage() {
        return entryStorage;
    }

    @Override
    public ProgressionEntryObject createBookObject(PlacedBookEntry entry) {
        return new SubspaceEntryObject(entry, entryStorage, size);
    }
}
