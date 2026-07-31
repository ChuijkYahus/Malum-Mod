package com.sammy.malum.client.screen.codex;

import com.google.common.collect.ImmutableList;
import com.sammy.malum.client.screen.codex.pages.BookPage;

import java.util.ArrayList;
import java.util.List;

public class BookEntryBuilder {

    protected final String identifier;

    protected List<BookPage> pages = new ArrayList<>();
    protected List<EntryBookmark> leftBookmarks = new ArrayList<>();
    protected List<EntryBookmark> rightBookmarks = new ArrayList<>();

    protected BookEntryBuilder(String identifier) {
        this.identifier = identifier;
    }

    public BookEntryBuilder addPage(BookPage page) {
        if (page.isValid()) {
            pages.add(page);
        }
        return this;
    }

    public BookEntryBuilder addLeftBookmark(EntryBookmark bookmark) {
        leftBookmarks.add(bookmark);
        return this;
    }

    public BookEntryBuilder addRightBookmark(EntryBookmark bookmark) {
        rightBookmarks.add(bookmark);
        return this;
    }

    public BookEntry build() {
        var pages = ImmutableList.copyOf(this.pages);
        var leftBookmarks = ImmutableList.copyOf(this.leftBookmarks);
        var rightBookmarks = ImmutableList.copyOf(this.rightBookmarks);
        return new BookEntry(identifier, pages, leftBookmarks, rightBookmarks);
    }
}