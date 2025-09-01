package com.sammy.malum.client.screen.codex;

import com.google.common.collect.ImmutableList;
import com.sammy.malum.client.VoidRevelationHandler;
import com.sammy.malum.client.screen.codex.pages.BookPage;
import com.sammy.malum.client.screen.codex.pages.EntryReference;
import com.sammy.malum.core.systems.spirit.type.*;
import net.minecraft.client.*;
import net.minecraft.network.chat.Style;
import net.minecraft.stats.*;

import javax.annotation.*;
import java.util.function.BooleanSupplier;
import java.util.function.UnaryOperator;

import static com.sammy.malum.client.VoidRevelationHandler.RevelationType.*;

public class BookEntry {

    public static final BooleanSupplier AFTER_SOME_TIME = () -> Minecraft.getInstance().player != null && (Minecraft.getInstance().player.getName().getString().equals("Dev") || Minecraft.getInstance().player.getStats().getValue(Stats.CUSTOM.get(Stats.PLAY_TIME)) > 14400);
    public static final BooleanSupplier AFTER_VOID_READER = () -> VoidRevelationHandler.hasSeenTheRevelation(VOID_READER);
    public static final BooleanSupplier AFTER_UMBRAL_CRYSTAL = () -> VoidRevelationHandler.hasSeenTheRevelation(BLACK_CRYSTAL);

    public final String identifier;
    public final boolean isVoid;
    public final ImmutableList<BookPage> pages;
    public final ImmutableList<EntryReference> references;
    public final BooleanSupplier condition;

    public final @Nullable SpiritLike associatedSpirit;
    public final boolean isFragment;

    public final UnaryOperator<Style> titleStyle;
    public final UnaryOperator<Style> subtitleStyle;
    public final boolean tooltipDisabled;


    public BookEntry(String identifier, boolean isVoid,
                     ImmutableList<BookPage> pages, ImmutableList<EntryReference> references, BooleanSupplier condition,
                     @Nullable SpiritLike associatedSpirit, boolean isFragment,
                     UnaryOperator<Style> titleStyle, UnaryOperator<Style> subtitleStyle, boolean tooltipDisabled) {
        this.identifier = identifier;
        this.isVoid = isVoid;
        this.pages = pages;
        this.references = references;
        this.condition = condition;
        this.associatedSpirit = associatedSpirit;
        this.isFragment = isFragment;
        this.titleStyle = titleStyle;
        this.subtitleStyle = subtitleStyle;
        this.tooltipDisabled = tooltipDisabled;
    }

    public String translationKey() {
        return "malum.gui.book.entry." + identifier;
    }

    public String descriptionTranslationKey() {
        return "malum.gui.book.entry." + identifier + ".description";
    }

    public boolean hasContents() {
        return !pages.isEmpty();
    }

    public boolean shouldShow() {
        return condition.getAsBoolean();
    }

    public boolean hasTooltip() {
        return !tooltipDisabled;
    }

    public static PlacedBookEntryBuilder create(String identifier, int xOffset, int yOffset) {
        return new PlacedBookEntryBuilder(identifier, xOffset, yOffset);
    }

    public static BookEntryBuilder create(String identifier) {
        return new BookEntryBuilder(identifier);
    }
}
