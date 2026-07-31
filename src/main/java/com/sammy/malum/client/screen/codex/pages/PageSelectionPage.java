package com.sammy.malum.client.screen.codex.pages;

import com.sammy.malum.client.screen.codex.display.gizmo.DisplayedGizmo;
import com.sammy.malum.client.screen.codex.handlers.BookObjectHandler;
import com.sammy.malum.client.screen.codex.objects.button.PageSelectionObject;
import com.sammy.malum.client.screen.codex.screens.CodexEntryScreen;
import net.minecraft.util.Mth;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

import static com.sammy.malum.client.screen.codex.display.gizmo.DisplayedItem.item;
import static com.sammy.malum.client.screen.codex.pages.text.HeadlineTextPage.headlineText;

public class PageSelectionPage extends CyclingPage {

    public static boolean FLAG = false;

    public static class PageSelectionBuilder {
        protected final List<Selection> data = new ArrayList<>();

        public PageSelectionBuilder addHeadline(DisplayedGizmo display, String text) {
            return add(display, headlineText(text));
        }

        public PageSelectionBuilder add(DisplayedGizmo display, BookPage page) {
            data.add(new Selection(page, display));
            return this;
        }

        public PageSelectionBuilder add(DisplayedGizmo display, Function<DisplayedGizmo, BookPage> page) {
            data.add(new Selection(page.apply(display), display));
            return this;
        }
    }

    public record Selection(BookPage page, DisplayedGizmo gizmo) {

    }

    protected int index;
    public final List<DisplayedGizmo> displays;

    public static PageSelectionPage create(Consumer<PageSelectionBuilder> builder) {
        var result = new PageSelectionBuilder();
        FLAG = true;
        builder.accept(result);
        FLAG = false;
        return new PageSelectionPage(result);
    }

    public PageSelectionPage(PageSelectionBuilder builder) {
        super(builder.data.stream().map(Selection::page).toList());

        this.displays = builder.data.stream().map(Selection::gizmo).toList();
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public BookObjectHandler<CodexEntryScreen> addObjects(CodexEntryScreen screen, int left, int top) {
        BookObjectHandler<CodexEntryScreen> handler = new BookObjectHandler<>();

        int total = pages.size();
        int step = 30;
        int objectStart = getPageMiddle(0) - (total * step) / 2;
        int objectTop = Mth.floor(CodexEntryScreen.PAGE_HEIGHT * 0.95f);
        for (int i = 0; i < pages.size(); i++) {
            int objectLeft = objectStart + i * step;
            handler.add(new PageSelectionObject(this, displays.get(i), i, objectLeft, objectTop));
        }
        return handler;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}