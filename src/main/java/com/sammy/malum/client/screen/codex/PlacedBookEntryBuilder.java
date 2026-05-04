package com.sammy.malum.client.screen.codex;

import com.google.common.collect.*;
import com.sammy.malum.client.screen.codex.objects.progression.*;
import com.sammy.malum.client.screen.codex.pages.*;
import net.minecraft.ChatFormatting;

import javax.annotation.Nullable;
import java.util.function.*;

public class PlacedBookEntryBuilder extends BookEntryBuilder {

    public static final int SPACING = 40;

    protected PlacedBookEntry.WidgetSupplier widgetSupplier = ProgressionEntryObject::new;
    @Nullable
    protected Consumer<ProgressionEntryObject> widgetConfig = null;

    @Nullable
    protected Consumer<PlacedBookEntryBuilder> fragmentProperties = null;
    protected boolean isFragment = false;

    protected final int xOffset;
    protected final int yOffset;

    protected PlacedBookEntryBuilder(String identifier, boolean isVoid, int xOffset, int yOffset) {
        super(identifier, isVoid);
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public PlacedBookEntryBuilder(String identifier, int xOffset, int yOffset) {
        super(identifier);
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public PlacedBookEntryBuilder setWidgetSupplier(PlacedBookEntry.WidgetSupplier widgetSupplier) {
        this.widgetSupplier = widgetSupplier;
        return this;
    }

    public PlacedBookEntryBuilder configureWidget(Consumer<ProgressionEntryObject> configure) {
        this.widgetConfig = this.widgetConfig == null ? configure : this.widgetConfig.andThen(configure);
        return this;
    }

    public PlacedBookEntryBuilder withEmptyFragmentEntry(WidgetDesignType designType) {
        this.fragmentProperties = b -> b
            .configureWidget(widget -> widget.setDesign(designType.createDesign(d -> d.withFilling(WidgetDesignType.FillingType.DARK))))
            .withTitleStyle(s -> s.withColor(ChatFormatting.GRAY))
            .withSubtitleStyle(s -> s.withColor(ChatFormatting.DARK_GRAY));
        return this;
    }

    public PlacedBookEntryBuilder withTraceFragmentEntry() {
        this.fragmentProperties = b -> b
            .configureWidget(widget -> widget.setDesign(WidgetDesignType.EMPTY.createDesign(null, null))) // todo: add cool visual effects for Traces
            .disableTooltip();

        return this;
    }

    public PlacedBookEntryBuilder withFragmentEntry(Consumer<PlacedBookEntryBuilder> properties) {
        this.fragmentProperties = this.fragmentProperties == null ? properties : this.fragmentProperties.andThen(properties);
        return this;
    }

    // Should only be invoked internally
    protected PlacedBookEntryBuilder setFragment() {
        this.isFragment = true;
        return this;
    }

    public boolean hasFragment() {
        return fragmentProperties != null;
    }

    public PlacedBookEntry buildFragment() {
        if (fragmentProperties == null)
            return null;

        PlacedBookEntryBuilder fragment = new PlacedBookEntryBuilder("fragment." + identifier, isVoid, xOffset, yOffset);
        fragment
            .setFragment()
            .configureWidget(widgetConfig)
            .setWidgetSupplier(widgetSupplier)
            .setEntryCondition(() -> !condition.getAsBoolean())
            .withTitleStyle(style -> style.withItalic(true))
            .withSubtitleStyle(style -> style.withItalic(true));
        fragmentProperties.accept(fragment);
        return fragment.build();
    }

    @Override
    public PlacedBookEntry build() {
        var pages = ImmutableList.copyOf(this.pages);
        var leftBookmarks = ImmutableList.copyOf(this.leftBookmarks);
        var rightBookmarks = ImmutableList.copyOf(this.rightBookmarks);
        var data = new PlacedBookEntry.BookEntryWidgetPlacementData(xOffset*SPACING, yOffset*SPACING, widgetSupplier, widgetConfig);
        return new PlacedBookEntry(identifier, isVoid, data, pages, leftBookmarks, rightBookmarks, condition, associatedSpirit, isFragment, titleStyle, subtitleStyle, hasTooltip);
    }
}
