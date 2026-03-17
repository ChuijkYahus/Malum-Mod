package com.sammy.malum.common.block;

import com.sammy.malum.registry.common.item.MalumDataComponents;
import com.sammy.malum.registry.common.sound.*;
import net.minecraft.sounds.*;
import net.minecraft.world.item.*;
import team.lodestar.lodestone.systems.blockentity.LodestoneBlockEntity;

public class AugmentBlockItemStackHandler extends MalumBlockItemStackHandler {

    public static AugmentBlockItemStackHandler augmentInventory(LodestoneBlockEntity blockEntity, int slotCount) {
        return new AugmentBlockItemStackHandler(blockEntity, slotCount, false);
    }
    public static AugmentBlockItemStackHandler coreAugmentInventory(LodestoneBlockEntity blockEntity, int slotCount) {
        return new AugmentBlockItemStackHandler(blockEntity, slotCount, true);
    }

    public AugmentBlockItemStackHandler(LodestoneBlockEntity blockEntity, int slotCount, boolean coreAugment) {
        super(blockEntity, slotCount, 1);
        setInputPredicate(s -> s.has(MalumDataComponents.ARTIFICE_AUGMENT) && coreAugment == s.get(MalumDataComponents.ARTIFICE_AUGMENT).isCoreAugment());
    }

    @Override
    public SoundEvent getInsertSound(ItemStack stack) {
        return MalumSoundEvents.CRUCIBLE_AUGMENT_APPLY.get();
    }

    @Override
    public SoundEvent getExtractSound(ItemStack stack) {
        return MalumSoundEvents.CRUCIBLE_AUGMENT_REMOVE.get();
    }
}
