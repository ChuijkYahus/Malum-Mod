package com.sammy.malum.client.screen.codex;

import com.google.common.collect.ImmutableList;
import com.sammy.malum.client.screen.codex.pages.BookPage;
import com.sammy.malum.client.screen.codex.pages.EntryReference;
import com.sammy.malum.core.systems.spirit.type.*;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Style;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.UnaryOperator;

public class BookEntryBuilder {

    protected final String identifier;
    protected final boolean isVoid;

    protected List<BookPage> pages = new ArrayList<>();
    protected List<EntryReference> leftBookmarks = new ArrayList<>();
    protected List<EntryReference> rightBookmarks = new ArrayList<>();
    protected BooleanSupplier condition = () -> true;
    protected SpiritLike associatedSpirit;
    protected UnaryOperator<Style> titleStyle;
    protected UnaryOperator<Style> subtitleStyle;
    protected boolean hasTooltip = true;

    protected BookEntryBuilder(String identifier, boolean isVoid) {
        this.identifier = identifier;
        this.isVoid = isVoid;
        this.titleStyle = (style) -> style.withColor(isVoid ? ChatFormatting.DARK_PURPLE : ChatFormatting.GOLD);
        this.subtitleStyle = (style) -> style.withColor(ChatFormatting.GRAY).withItalic(true);
    }

    public BookEntryBuilder(String identifier) {
        this(identifier, identifier.startsWith("void.") || BookPage.isVoidThemed);
    }

    public BookEntryBuilder addPage(BookPage page) {
        if (page.isValid()) {
            pages.add(page);
        }
        return this;
    }

    public BookEntryBuilder addLeftReference(EntryReference reference) {
        leftBookmarks.add(reference);
        return this;
    }

    public BookEntryBuilder addRightReference(EntryReference reference) {
        leftBookmarks.add(reference);
        return this;
    }

    public BookEntryBuilder setEntryCondition(BooleanSupplier condition) {
        this.condition = condition;
        return this;
    }

    public BookEntryBuilder setAssociatedSpirit(SpiritLike associatedSpirit) {
        this.associatedSpirit = associatedSpirit;
        return this;
    }

    public BookEntryBuilder afterSomeTime() {
        this.condition = BookEntry.AFTER_SOME_TIME;
        return this;
    }

    public BookEntryBuilder afterVoidReader() {
        this.condition = BookEntry.AFTER_VOID_READER;
        return this;
    }

    public BookEntryBuilder afterUmbralCrystal() {
        this.condition = BookEntry.AFTER_UMBRAL_CRYSTAL;
        return this;
    }

    public BookEntryBuilder withTitleStyle(UnaryOperator<Style> styleFunction) {
        final UnaryOperator<Style> existingStyle = titleStyle;
        titleStyle = (style) -> styleFunction.apply(existingStyle.apply(style));
        return this;
    }

    public BookEntryBuilder withSubtitleStyle(UnaryOperator<Style> styleFunction) {
        final UnaryOperator<Style> existingStyle = subtitleStyle;
        subtitleStyle = (style) -> styleFunction.apply(existingStyle.apply(style));
        return this;
    }

    public BookEntryBuilder disableTooltip() {
        this.hasTooltip = false;
        return this;
    }

    public BookEntry build() {
        ImmutableList<BookPage> bookPages = ImmutableList.copyOf(pages);
        ImmutableList<EntryReference> entryReferences = ImmutableList.copyOf(leftBookmarks);
        return new BookEntry(identifier, isVoid, bookPages, entryReferences, condition, associatedSpirit, false, titleStyle, subtitleStyle, hasTooltip);
    }
}