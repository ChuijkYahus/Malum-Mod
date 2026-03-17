package com.sammy.malum.common.block;

import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.magic.*;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import team.lodestar.lodestone.modules.toolkit.blockentity.LodestoneBlockEntity;

public class MalumSpiritBlockItemStackHandler extends MalumBlockItemStackHandler {

    public static MalumBlockItemStackHandler singleSpiritStack(LodestoneBlockEntity blockEntity) {
        return new MalumSpiritBlockItemStackHandler(blockEntity, 1, 64);
    }
    public static MalumBlockItemStackHandler spiritStacks(LodestoneBlockEntity blockEntity) {
        return new MalumSpiritBlockItemStackHandler(blockEntity, MalumSpiritTypes.SPIRIT_TYPES_REGISTRY.size(), 64);
    }
    public static MalumBlockItemStackHandler spiritStacks(LodestoneBlockEntity blockEntity, int slotCount) {
        return new MalumSpiritBlockItemStackHandler(blockEntity, slotCount, 64);
    }

    protected MalumSpiritBlockItemStackHandler(LodestoneBlockEntity blockEntity, int slotCount, int allowedItemSize) {
        super(slotCount, allowedItemSize);
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
