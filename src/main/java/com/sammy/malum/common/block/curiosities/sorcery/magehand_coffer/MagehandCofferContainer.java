package com.sammy.malum.common.block.curiosities.sorcery.magehand_coffer;

import com.sammy.malum.registry.common.MalumContainers;
import com.sammy.malum.registry.common.item.MalumDataComponents;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.SlotItemHandler;
import team.lodestar.lodestone.modules.toolkit.inventory.LodestoneBlockEntityContainer;
import team.lodestar.lodestone.modules.toolkit.inventory.LodestoneItemStackBlockHandler;

public class MagehandCofferContainer extends LodestoneBlockEntityContainer<MagehandCofferBlockEntity> {

    public static final Component MAGEHAND_COFFER = Component.translatable("container.malum.magehand_coffer");

    public MagehandCofferContainer(int containerId, Inventory playerInventory, RegistryFriendlyByteBuf data) {
        this(containerId, playerInventory, ContainerLevelAccess.create(playerInventory.player.level(), data.readBlockPos()));
    }

    public MagehandCofferContainer(int containerId, Inventory playerInventory) {
        this(containerId, playerInventory, ContainerLevelAccess.NULL);
    }

    public MagehandCofferContainer(int containerId, Inventory playerInventory, ContainerLevelAccess access) {
        super(MalumContainers.MAGEHAND_COFFER.get(), containerId, playerInventory, access);
        if (blockEntity != null) {
            var itemHandler = getItemStackHandler();

            addSlot(new SlotItemHandler(itemHandler, 0, 199, 64));
        }
    }

    @Override
    public int[] getPlayerInventoryTopLeft() {
        return new int[]{18, 161};
    }

    @Override
    public Class<MagehandCofferBlockEntity> getBlockEntityClass() {
        return MagehandCofferBlockEntity.class;
    }

    @Override
    public LodestoneItemStackBlockHandler getItemStackHandler() {
        return blockEntity.inventory;
    }
}