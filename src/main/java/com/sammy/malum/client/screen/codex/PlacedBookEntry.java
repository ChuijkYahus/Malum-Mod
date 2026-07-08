package com.sammy.malum.client.screen.codex;

import com.google.common.collect.ImmutableList;
import com.sammy.malum.client.screen.codex.objects.progression.ProgressionEntryObject;
import com.sammy.malum.client.screen.codex.pages.BookPage;
import com.sammy.malum.client.screen.codex.pages.EntryReference;
import com.sammy.malum.core.systems.spirit.SpiritLike;
import net.minecraft.network.chat.Style;

import javax.annotation.*;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public class PlacedBookEntry extends BookEntry {
    private final BookEntryWidgetPlacementData widgetData;

    public PlacedBookEntry(String identifier, boolean isVoid,
                           BookEntryWidgetPlacementData widgetData,
                           ImmutableList<BookPage> bookPages, ImmutableList<EntryReference> leftBookmarks, ImmutableList<EntryReference> rightBookmarks, BooleanSupplier condition,
                           @Nullable SpiritLike associatedSpirit, boolean isFragment,
                           UnaryOperator<Style> titleStyle, UnaryOperator<Style> subtitleStyle, boolean hasTooltip) {
        super(identifier, isVoid, bookPages, leftBookmarks, rightBookmarks, condition, associatedSpirit, isFragment, titleStyle, subtitleStyle, hasTooltip);
        this.widgetData = widgetData;
    }

    public BookEntryWidgetPlacementData getWidgetData() {
        return widgetData;
    }

    public interface WidgetSupplier {
        ProgressionEntryObject getBookObject(BookEntry entry, int x, int y);
    }

    public record BookEntryWidgetPlacementData(int xOffset, int yOffset, WidgetSupplier widgetSupplier, Consumer<ProgressionEntryObject> widgetConfig) {
    }
}
