package com.sammy.malum.client.screen.codex;

import com.sammy.malum.client.screen.codex.objects.subspace.SubspaceBookObjectBuilder;

import java.util.function.Consumer;

public interface EntryAcceptor {

    default SubspaceBookObjectBuilder addSubspaceEntry(String identifier, int x, int y, Consumer<PlacedBookEntryBuilder> config) {
        var subspace = new SubspaceBookObjectBuilder(x, y);
        var builder = addEntry(identifier, x, y);
        builder.setObjectBuilder(subspace);
        config.accept(builder);
        return subspace;
    }
    default PlacedBookEntryBuilder addEntry(String identifier, int x, int y) {
        var builder = PlacedBookEntry.create(identifier, x, -y);
        getEntryStorage().add(builder);
        return builder;
    }

    EntryStorage getEntryStorage();
}
