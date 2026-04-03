package com.sammy.malum.client.screen.codex.pages;

import com.sammy.malum.client.screen.codex.handlers.BookObjectHandler;
import com.sammy.malum.client.screen.codex.objects.PageSelectionObject;
import com.sammy.malum.client.screen.codex.screens.CodexEntryScreen;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class PageSelectionPage extends CyclingPage {

    public static class PageSelectionBuilder {
        protected final List<Selection> data = new ArrayList<>();

        public PageSelectionBuilder add(BookPage page, ItemStack icon) {
            data.add(new Selection(page, icon));
            return this;
        }

        public PageSelectionBuilder add(BookPage page, Item icon) {
            return add(page, icon.getDefaultInstance());
        }
    }

    public record Selection(BookPage page, ItemStack icon) {

    }
    protected int index;
    public final List<ItemStack> icons;

    public static PageSelectionPage create(Consumer<PageSelectionBuilder> builder) {
        var result = new PageSelectionBuilder();
        builder.accept(result);
        return new PageSelectionPage(result);
    }

    public PageSelectionPage(PageSelectionBuilder builder) {
        super(builder.data.stream().map(Selection::page).toList());

        this.icons = builder.data.stream().map(Selection::icon).toList();
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public BookObjectHandler<CodexEntryScreen> addObjects(CodexEntryScreen screen, int left, int top) {
        BookObjectHandler<CodexEntryScreen> handler = new BookObjectHandler<>();

        int total = pages.size();
        int step = 32;
        int objectStart = getPageMiddle(left) - (total * step)/2;
        int objectTop = top + Mth.floor(CodexEntryScreen.PAGE_HEIGHT * 0.9f);
        for (int i = 0; i < pages.size(); i++) {
            int objectLeft = objectStart + i * step;
            handler.add(new PageSelectionObject(this, i, icons.get(i), objectLeft, objectTop));
        }
        return handler;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}