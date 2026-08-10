package com.sammy.malum.client.screen.codex;

import com.google.common.collect.ImmutableList;
import com.sammy.malum.client.screen.codex.pages.BookPage;

public class BookEntry {

    public final String identifier;
    public final ImmutableList<BookPage> pages;
    public final ImmutableList<EntryBookmark> leftBookmarks;
    public final ImmutableList<EntryBookmark> rightBookmarks;

    public BookEntry(String identifier, ImmutableList<BookPage> pages, ImmutableList<EntryBookmark> leftBookmarks, ImmutableList<EntryBookmark> rightBookmarks) {
        this.identifier = identifier;
        this.pages = pages;
        this.leftBookmarks = leftBookmarks;
        this.rightBookmarks = rightBookmarks;
    }

    public String translationKey() {
        return "malum.gui.book.entry." + identifier;
    }

    public String descriptionTranslationKey() {
        return "malum.gui.book.entry." + identifier + ".subtext";
    }

    public boolean hasContents() {
        return !pages.isEmpty();
    }

    public boolean shouldShow() {
        return true;
    }

    public static BookEntryBuilder create(String identifier) {
        return new BookEntryBuilder(identifier);
    }
}
