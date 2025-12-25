package com.sammy.malum.client.creative_tab;

import com.mojang.datafixers.util.Either;
import com.sammy.malum.registry.common.MalumCreativeTabs;
import com.sammy.malum.registry.common.item.MalumItems;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class MalumCreativeTabTweaks {

    public static final List<Category> CATEGORIES = new ArrayList<>();

    public static void modifyTab(CreativeModeInventoryScreen screen, CreativeModeInventoryScreen.ItemPickerMenu menu, CreativeModeTab selectedTab) {
        if (selectedTab.equals(MalumCreativeTabs.CONTENT.get())) {
            NonNullList<ItemStack> items = menu.items;
            Set<Item> itemTypes = new HashSet<>();
            items.clear();
            for (Category category : CATEGORIES) {
                for (Either<ItemStack, Operation> either : category.items) {
                    either.ifLeft(i -> {
                        items.add(i);
                        itemTypes.add(i.getItem());
                    });
                    either.ifRight(e -> {
                        int missing = 9 - items.size() % 9;
                        for (int i = 0; i < missing; i++) {
                            items.add(ItemStack.EMPTY);
                        }
                    });
                }
            }
            var missingItems = new ArrayList<Item>();
            for (DeferredHolder<Item, ? extends Item> entry : MalumItems.ITEMS.getEntries()) {
                Item item = entry.get();
                if (!itemTypes.contains(item)) {
                    missingItems.add(item);
                }
            }
            for (Item missingItem : missingItems) {
                ItemStack stack = missingItem.getDefaultInstance();
                items.add(stack);
            }
        }
    }

    public static void ensureCategoriesAreReal() {
        if (CATEGORIES.isEmpty()) {
            Categories.buildCategories();
        }
    }

    public static void addCategory(String name, CategoryBuilder builder) {
        CATEGORIES.add(new Category(name, List.copyOf(builder.items)));
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

        public CategoryBuilder addItem(Item item) {
            return addItem(() -> item);
        }

        public CategoryBuilder addItem(Supplier<Item> item) {
            items.add(Either.left(item.get().getDefaultInstance()));
            return this;
        }

        public CategoryBuilder nextLine() {
            items.add(Either.right(Operation.NEXT_LINE));
            return this;
        }

        public void bake() {
            CATEGORIES.add(new Category(id, items));
        }
    }

    public record Category(String id, List<Either<ItemStack, Operation>> items) {

    }

    public enum Operation {
        NEXT_LINE
    }
}