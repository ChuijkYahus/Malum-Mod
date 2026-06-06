package com.sammy.malum.common.creativetab.button;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import team.lodestar.lodestone.modules.toolkit.creative_tab.CategorizedCreativeTab;
import team.lodestar.lodestone.modules.toolkit.creative_tab.entries.CreativeTabCategoryEntry;
import team.lodestar.lodestone.modules.toolkit.creative_tab.slot.SlotLocation;
import team.lodestar.lodestone.modules.toolkit.creative_tab.slot.SlotStorage;

import java.util.function.Supplier;

public class ItemChoiceEntry extends CreativeTabCategoryEntry {

    protected final ItemLike left;
    protected final ItemLike right;

    public static ItemChoiceEntry choice(ItemLike left, ItemLike right) {
        return new ItemChoiceEntry(left, right);
    }

    protected ItemChoiceEntry(ItemLike left, ItemLike right) {
        super();
        this.left = left;
        this.right = right;
    }

    @Override
    public SlotStorage bake(CategorizedCreativeTab tab, SlotLocation location) {
        return new ItemChoiceSlotStorage(location, left.asItem().getDefaultInstance(), right.asItem().getDefaultInstance());
    }

}