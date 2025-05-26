package com.sammy.malum.common.block;

import com.sammy.malum.registry.common.*;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import team.lodestar.lodestone.systems.blockentity.LodestoneBlockEntity;

public class MalumSpiritBlockEntityInventory extends MalumBlockEntityInventory{

    public static MalumBlockEntityInventory singleSpiritStack(LodestoneBlockEntity blockEntity) {
        return new MalumSpiritBlockEntityInventory(blockEntity, 1, 64);
    }
    public static MalumBlockEntityInventory spiritStacks(LodestoneBlockEntity blockEntity, int slotCount) {
        return new MalumSpiritBlockEntityInventory(blockEntity, slotCount, 64);
    }

    protected MalumSpiritBlockEntityInventory(LodestoneBlockEntity blockEntity, int slotCount, int allowedItemSize) {
        super(blockEntity, slotCount, allowedItemSize);
        setInputPredicate(p -> p.is(MalumTags.ItemTags.SPIRITS));
    }

    @Override
    public boolean isItemValid(int slot, @NotNull ItemStack stack) {
        for (int i = 0; i < getSlots(); i++) {
            if (i != slot) {
                ItemStack stackInSlot = getStackInSlot(i);
                if (!stackInSlot.isEmpty() && stackInSlot.is(stack.getItem()))
                    return false;
            }
        }
        return super.isItemValid(slot, stack);
    }
}
