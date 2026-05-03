package com.sammy.malum.common.block.curiosities.sorcery.wand_tinkerer;

import com.sammy.malum.registry.common.MalumContainers;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.SlotItemHandler;
import team.lodestar.lodestone.modules.toolkit.inventory.LodestoneBlockEntityContainer;
import team.lodestar.lodestone.modules.toolkit.inventory.LodestoneItemStackBlockHandler;

public class WandTinkererContainer extends LodestoneBlockEntityContainer<WandTinkererBlockEntity> {

    public static final Component WAND_TINKERER = Component.translatable("container.malum.wand_tinkerer");

    public WandTinkererContainer(int containerId, Inventory playerInventory, RegistryFriendlyByteBuf data) {
        this(containerId, playerInventory, ContainerLevelAccess.create(playerInventory.player.level(), data.readBlockPos()));
    }

    public WandTinkererContainer(int containerId, Inventory playerInventory) {
        this(containerId, playerInventory, ContainerLevelAccess.NULL);
    }

    public WandTinkererContainer(int containerId, Inventory playerInventory, ContainerLevelAccess access) {
        super(MalumContainers.WAND_TINKERER.get(), containerId, playerInventory, access);

        if (blockEntity != null) {
            var itemHandler = getItemStackHandler();
            for (int x = 0; x < 4; x++) {
                for (int y = 0; y < 4; y++) {
                    int slotX = 56 + x * 16;
                    int slotY = 23 + y * 16;
                    addSlot(new SlotItemHandler(itemHandler, 0, slotX, slotY));
                }
            }
        }
    }



    @Override
    public int[] getPlayerInventoryTopLeft() {
        return new int[]{8, 133};
    }

    @Override
    public Class<WandTinkererBlockEntity> getBlockEntityClass() {
        return WandTinkererBlockEntity.class;
    }

    @Override
    public LodestoneItemStackBlockHandler getItemStackHandler() {
        return blockEntity.buffer;
    }


}