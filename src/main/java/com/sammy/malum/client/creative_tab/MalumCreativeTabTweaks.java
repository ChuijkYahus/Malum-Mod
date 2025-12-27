package com.sammy.malum.client.creative_tab;

import com.mojang.datafixers.util.Either;
import com.sammy.malum.MalumMod;
import com.sammy.malum.core.handlers.hiding.HiddenTagHandler;
import com.sammy.malum.registry.common.MalumCreativeTabs;
import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.ibm.icu.impl.ValidIdentifiers.Datatype.x;
import static com.sammy.malum.core.handlers.hiding.HiddenTagHandler.registerHiddenItemListener;

public class MalumCreativeTabTweaks {

    private static final ResourceLocation SLOT_WRAPPER = MalumMod.malumPath("slot_wrapper");
    private static final ResourceLocation SLOT_WRAPPER_LEFT = MalumMod.malumPath("slot_wrapper_left");
    private static final ResourceLocation SLOT_WRAPPER_RIGHT = MalumMod.malumPath("slot_wrapper_right");
    private static final ResourceLocation EMPTY_SLOT = MalumMod.malumPath("empty_slot");

    public static final HashMap<String, Category> CATEGORIES = new LinkedHashMap<>();
    public static final Int2ObjectArrayMap<CategoryHeader> HEADERS = new Int2ObjectArrayMap<>();

    public static void ensureCategoriesAreReal() {
        if (CATEGORIES.isEmpty()) {
            Categories.buildCategories();
        }
    }

    public static void modifyTab(CreativeModeInventoryScreen screen, CreativeModeInventoryScreen.ItemPickerMenu menu, CreativeModeTab selectedTab) {
        if (selectedTab.equals(MalumCreativeTabs.CONTENT.get())) {
            fillMenu(screen, menu);
        }
    }

    public static boolean renderSlot(GuiGraphics guiGraphics, Slot slot) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.screen instanceof CreativeModeInventoryScreen screen) {
            if (!(slot instanceof CreativeModeInventoryScreen.CustomCreativeSlot)) {
                return false;
            }
            if (!CreativeModeInventoryScreen.selectedTab.equals(MalumCreativeTabs.CONTENT.get())) {
                return false;
            }
            var item = slot.getItem();
            if (item.isEmpty()) {
                var menu = screen.getMenu();
                int row = menu.getRowIndexForScroll(screen.scrollOffs);
                int itemIndex = row * 9 + Mth.floor(slot.getSlotIndex()/9f)*9;
                var pose = guiGraphics.pose();
                pose.pushPose();
                pose.translate(0.0F, 0.0F, 100.0F);

                if (HEADERS.containsKey(itemIndex)) {
                    var header = HEADERS.get(itemIndex);
                    var texture = SLOT_WRAPPER;
                    int containerSlot = slot.getContainerSlot() % 9;
                    if (containerSlot == 0) {
                        var font = minecraft.font;
                        var title = Component.translatable(header.category().getHeaderLangKey());
                        int x = slot.x + 80 - font.width(title) / 2;
                        int y = slot.y + 1;
                        pose.pushPose();
                        pose.translate(0.0F, 0.0F, 100.0F);
                        guiGraphics.drawString(font, title, x, y, 4210752, false);
                        pose.popPose();
                        texture = SLOT_WRAPPER_LEFT;
                    } else if (containerSlot == 8) {
                        texture = SLOT_WRAPPER_RIGHT;
                    }
                    var sprite = minecraft.getGuiSprites().getSprite(texture);
                    guiGraphics.blit(slot.x - 1, slot.y - 2, 0, 18, 20, sprite);
                }
                else {
                    var sprite = minecraft.getGuiSprites().getSprite(EMPTY_SLOT);
                    guiGraphics.blit(slot.x, slot.y, 0, 16, 16, sprite);
                }
                pose.popPose();
                return true;
            }

        }
        return false;
    }

    public static boolean disableSlotHighlight(Slot slot) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.screen instanceof CreativeModeInventoryScreen screen) {
            if (!(slot instanceof CreativeModeInventoryScreen.CustomCreativeSlot)) {
                return false;
            }
            if (!CreativeModeInventoryScreen.selectedTab.equals(MalumCreativeTabs.CONTENT.get())) {
                return false;
            }
            return slot.getItem().isEmpty();
        }
        return false;
    }

    public static void fillMenu(CreativeModeInventoryScreen screen, CreativeModeInventoryScreen.ItemPickerMenu menu, String... categories) {
        var mapped = new ArrayList<Category>();
        for (String category : categories) {
            if (CATEGORIES.containsKey(category)) {
                mapped.add(CATEGORIES.get(category));
            }
        }
        fillMenu(screen, menu, mapped);
    }
    public static void fillMenu(CreativeModeInventoryScreen screen, CreativeModeInventoryScreen.ItemPickerMenu menu) {
        fillMenu(screen, menu, CATEGORIES.values());
    }

    public static void fillMenu(CreativeModeInventoryScreen screen, CreativeModeInventoryScreen.ItemPickerMenu menu, Collection<Category> categories) {
        var items = menu.items;
        HEADERS.clear();
        items.clear();
        for (Category category : categories) {
            addCategoryHeader(screen, menu, category);
            for (Either<ItemStack, Operation> either : category.items) {
                either.ifLeft(i -> {
                    if (!HiddenTagHandler.isHiddenItem(i)) {
                        items.add(i);
                    }
                });
                either.ifRight(e -> clearRow(menu, false));
            }
            clearRow(menu, false);
        }
    }

    public static void addCategoryHeader(CreativeModeInventoryScreen screen, CreativeModeInventoryScreen.ItemPickerMenu menu, Category category) {
        var items = menu.items;
        var index = items.size();
        clearRow(menu, true);
        HEADERS.put(index, new CategoryHeader(category));
    }

    public static void clearRow(CreativeModeInventoryScreen.ItemPickerMenu menu, boolean force) {
        var items = menu.items;
        int missing = 9 - items.size() % 9;
        if (force || missing != 9) {
            for (int i = 0; i < missing; i++) {
                items.add(ItemStack.EMPTY);
            }
        }
    }

    public static CategoryBuilder create(String id) {
        return new CategoryBuilder(id);
    }

    public static class CategoryBuilder {

        protected final String id;
        protected final ArrayList<Either<ItemStack, Operation>> items = new ArrayList<>();

        public CategoryBuilder(String id) {
            this.id = id;
        }

        public final<T extends Item> CategoryBuilder addItems(Consumer<CategoryBuilder> itemAdder) {
            itemAdder.accept(this);
            return this;
        }

        @SafeVarargs
        public final<T extends Item> CategoryBuilder addItems(DeferredHolder<Item, T>... items) {
            for (DeferredHolder<Item, T> item : items) {
                addItem(item::get);
            }
            return this;
        }

        public CategoryBuilder addItems(Item... items) {
            for (Item item : items) {
                addItem(item);
            }
            return this;
        }

        public CategoryBuilder addItem(Supplier<Item> item) {
            return addItem(item.get());
        }

        public CategoryBuilder addItem(Item item) {
            return addItem(item.getDefaultInstance());
        }

        public CategoryBuilder addItem(ItemStack item) {
            items.add(Either.left(item));
            return this;
        }

        public CategoryBuilder nextLine() {
            items.add(Either.right(Operation.NEXT_LINE));
            return this;
        }

        public void bake() {
            CATEGORIES.put(id, new Category(id, items));
        }
    }

    public record Category(String id, List<Either<ItemStack, Operation>> items) {

        public String getHeaderLangKey() {
            return "malum.itemGroup.header." + id;
        }
    }

    public record CategoryHeader(Category category) {

    }

    public enum Operation {
        NEXT_LINE
    }
}