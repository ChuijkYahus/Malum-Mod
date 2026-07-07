package com.sammy.malum.common.block.curiosities.artifice.crystallarium;

import com.sammy.malum.registry.common.*;
import net.minecraft.network.*;
import net.minecraft.network.chat.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.items.*;
import team.lodestar.lodestone.modules.toolkit.inventory.*;

public class ConjunctureCrystallariumContainer extends LodestoneBlockEntityContainer<ConjunctureCrystallariumBlockEntity> {

    public static final Component CONJUNCTURE_CRYSTALLARIUM = Component.translatable("container.malum.conjuncture_crystallarium");

    public ConjunctureCrystallariumContainer(int containerId, Inventory playerInventory, RegistryFriendlyByteBuf data) {
        this(containerId, playerInventory, ContainerLevelAccess.create(playerInventory.player.level(), data.readBlockPos()));
    }

    public ConjunctureCrystallariumContainer(int containerId, Inventory playerInventory) {
        this(containerId, playerInventory, ContainerLevelAccess.NULL);
    }

    public ConjunctureCrystallariumContainer(int containerId, Inventory playerInventory, final ContainerLevelAccess access) {
        super(MalumContainers.CONJUNCTURE_CRYSTALLARIUM.get(), containerId, playerInventory, access);

        if (blockEntity != null) {
            var itemHandler = getItemStackHandler();
            addSlot(new SlotItemHandler(itemHandler, 0, 91, 73));
            addSlot(new SlotItemHandler(itemHandler, 1, 91, 109) {
                @Override
                public boolean mayPlace(ItemStack stack) {
                    return isFuel(stack);
                }
            });

            for (int i = 0; i < 3; i++) {
                addSlot(new SlotItemHandler(itemHandler, 2+i, 146, 39+17*i) {
                    @Override
                    public boolean mayPlace(ItemStack stack) {
                        return false;
                    }
                });

            }
        }
    }

    private boolean isFuel(ItemStack stack) {
        return stack.is(MalumTags.Items.CONJUNCTURE_CRYSTALLARIUM_FUEL);
    }

    @Override
    public int[] getPlayerInventoryTopLeft() {
        return new int[]{19, 144};
    }

    @Override
    public Class<ConjunctureCrystallariumBlockEntity> getBlockEntityClass() {
        return ConjunctureCrystallariumBlockEntity.class;
    }

    @Override
    public LodestoneItemStackBlockHandler getItemStackHandler() {
        return blockEntity.inventory;
    }
}