package com.sammy.malum.common.creativetab.button;

import net.minecraft.world.item.ItemStack;
import team.lodestar.lodestone.modules.toolkit.creative_tab.slot.SlotLocation;
import team.lodestar.lodestone.modules.toolkit.creative_tab.slot.SlotStorage;

public class ItemChoiceSlotStorage extends SlotStorage {

    public ItemChoiceSlotStorage(SlotLocation location, ItemStack left, ItemStack right) {
        super(location, left, right);
    }
}
