package com.sammy.malum.common.data.component.pouch;

import com.google.common.collect.*;
import com.mojang.serialization.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.item.*;

import net.minecraft.network.*;
import net.minecraft.network.codec.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.inventory.*;
import net.minecraft.world.inventory.tooltip.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.*;
import net.minecraft.world.level.*;
import org.apache.commons.lang3.math.*;

import javax.annotation.*;
import java.util.*;

public abstract class MalumPouchContentsComponent implements TooltipComponent {

    final List<ItemStack> items;
    final Fraction weight;

    MalumPouchContentsComponent(List<ItemStack> items, Fraction weight) {
        this.items = items;
        this.weight = weight;
    }

    public MalumPouchContentsComponent(List<ItemStack> items, PouchContentsWeightProcessor processor) {
        this(items, processor.computeContentWeight(items));
    }

    public abstract Mutable mutable();

    public abstract int getStorageSize();

    public ItemStack getItemUnsafe(int index) {
        return this.items.get(index);
    }

    public List<ItemStack> getItems() {
        return this.items;
    }

    public boolean containsItem(ItemLike item) {
        for (ItemStack stack : items) {
            if (stack.is(item.asItem())) {
                return true;
            }
        }
        return false;
    }

    public List<ItemStack> getItemsCopy() {
        return Lists.transform(this.items, ItemStack::copy);
    }

    public int size() {
        return this.items.size();
    }

    public Fraction weight() {
        return this.weight;
    }

    public boolean isEmpty() {
        return this.items.isEmpty();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else {
            return other instanceof MalumPouchContentsComponent otherContents && this.weight.equals(otherContents.weight) && ItemStack.listMatches(this.items, otherContents.items);
        }
    }

    @Override
    public int hashCode() {
        return ItemStack.hashStackList(this.items);
    }

    @Override
    public String toString() {
        return "PouchContents" + this.items;
    }

    public static abstract class Mutable {
        protected final List<ItemStack> items;
        protected final PouchContentsWeightProcessor processor;
        protected Fraction weight;

        public Mutable(MalumPouchContentsComponent contents, PouchContentsWeightProcessor processor) {
            this.items = new ArrayList<>(contents.items);
            this.processor = processor;
            this.weight = contents.weight;
        }

        public abstract MalumPouchContentsComponent immutable();

        public List<ItemStack> getItems() {
            return items;
        }

        public void clearItems() {
            this.items.clear();
            this.weight = Fraction.ZERO;
        }

        private int findStackIndex(ItemStack stack) {
            if (stack.isStackable()) {
                for (int i = 0; i < items.size(); i++) {
                    ItemStack compare = items.get(i);
                    if (compare.getCount() >= compare.getMaxStackSize()) {
                        continue;
                    }
                    if (ItemStack.isSameItemSameComponents(compare, stack)) {
                        return i;
                    }
                }
            }
            return -1;
        }

        private int getMaxAmountToAdd(ItemStack stack) {
            Fraction fraction = Fraction.ONE.subtract(weight);
            return Math.max(fraction.divideBy(processor.getWeight(stack)).intValue(), 0);
        }

        public int tryTransfer(Slot slot, Player player) {
            ItemStack itemstack = slot.getItem();
            int i = getMaxAmountToAdd(itemstack);
            return tryInsert(slot.safeTake(itemstack.getCount(), i, player));
        }

        public int tryInsert(ItemStack stack) {
            if (!stack.isEmpty() && stack.getItem().canFitInsideContainerItems()) {
                int i = Math.min(stack.getCount(), this.getMaxAmountToAdd(stack));
                if (i == 0) {
                    return 0;
                } else {
                    weight = weight.add(processor.getWeight(stack).multiplyBy(Fraction.getFraction(i, 1)));
                    int j = findStackIndex(stack);
                    if (j != -1) {
                        var itemstack = items.remove(j);
                        int transferSize = Math.min(i, stack.getMaxStackSize() - itemstack.getCount());
                        addItem(itemstack.copyWithCount(itemstack.getCount() + transferSize));
                        stack.shrink(transferSize);
                        if (!stack.isEmpty()) //Split remainder into separate stack
                        {
                            int remainder = stack.getCount();
                            weight = weight.add(processor.getWeight(stack).multiplyBy(Fraction.getFraction(remainder, 1)));
                            items.addFirst(itemstack.copyWithCount(remainder));
                            stack.shrink(remainder);
                        }
                    } else {
                        items.addFirst(stack.split(i));
                    }

                    return i;
                }
            } else {
                return 0;
            }
        }

        public void addItem(ItemStack stack) {
            items.addFirst(stack);
        }

        @Nullable
        public ItemStack removeOne() {
            if (items.isEmpty()) {
                return null;
            } else {
                ItemStack itemstack = items.removeFirst().copy();
                weight = weight.subtract(processor.getWeight(itemstack).multiplyBy(Fraction.getFraction(itemstack.getCount(), 1)));
                return itemstack;
            }
        }

        public Fraction weight() {
            return this.weight;
        }
    }
}