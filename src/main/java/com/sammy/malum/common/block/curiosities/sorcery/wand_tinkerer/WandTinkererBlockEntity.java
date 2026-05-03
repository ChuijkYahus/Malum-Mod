package com.sammy.malum.common.block.curiosities.sorcery.wand_tinkerer;

import com.sammy.malum.common.block.MalumBlockItemStackHandler;
import com.sammy.malum.common.block.storage.IMalumSpecialItemAccessPoint;
import com.sammy.malum.common.block.storage.pedestal.ItemPedestalBlockEntity;
import com.sammy.malum.registry.common.block.MalumBlockEntities;
import com.sammy.malum.visual_effects.block.ConjunctureCrystallariumParticleEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Clearable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.IItemHandler;
import team.lodestar.lodestone.modules.toolkit.blockentity.IInventoryCapabilityProvider;
import team.lodestar.lodestone.modules.toolkit.blockentity.LodestoneBlockEntity;
import team.lodestar.lodestone.modules.toolkit.blockentity.LodestoneBlockEntityType;
import team.lodestar.lodestone.modules.toolkit.inventory.LodestoneItemStackHandler;

import static com.sammy.malum.common.block.curiosities.artifice.crystallarium.ConjunctureCrystallariumContainer.CONJUNCTURE_CRYSTALLARIUM;
import static com.sammy.malum.common.block.curiosities.sorcery.wand_tinkerer.WandTinkererContainer.WAND_TINKERER;

public class WandTinkererBlockEntity extends LodestoneBlockEntity implements IInventoryCapabilityProvider, Clearable {

    public MalumBlockItemStackHandler buffer;
    public MalumBlockItemStackHandler inventory;

    public WandTinkererBlockEntity(LodestoneBlockEntityType<? extends WandTinkererBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        buffer = MalumBlockItemStackHandler.create(this, 1).onContentsChanged(this::updateBuffer).limitItemSize(1).build();
        inventory = MalumBlockItemStackHandler.create(this, 99).build();
    }

    public WandTinkererBlockEntity(BlockPos pos, BlockState state) {
        this(MalumBlockEntities.WAND_TINKERER.get(), pos, state);
    }

    public void updateBuffer() {
        if (!(level instanceof ServerLevel serverLevel)) {
            return;
        }
        boolean isEmptyBuffer = buffer.isEmpty();
        boolean isEmptyInventory = inventory.isEmpty();
        if (!isEmptyBuffer) {
            var moved = buffer.extractItem(serverLevel, 1).result();
            inventory.insertItem(serverLevel, moved);
        }
        else if (isEmptyInventory) {
            var moved = inventory.extractItem(serverLevel, 1).result();
            buffer.insertItem(serverLevel, moved);
        }
    }

    @Override
    public ItemInteractionResult onUse(Player player, InteractionHand pHand) {
        if (player instanceof ServerPlayer serverPlayer) {
            var container = new SimpleMenuProvider((w, p, pl) -> new WandTinkererContainer(w, p, ContainerLevelAccess.create(pl.level(), getBlockPos())), WAND_TINKERER);
            serverPlayer.openMenu(container, buf -> buf.writeBlockPos(this.getBlockPos()));
        }
        return ItemInteractionResult.SUCCESS;
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        inventory.save(registries, tag);
        buffer.save(registries, tag, "buffer");
    }

    @Override
    public void loadAdditional(CompoundTag compound, HolderLookup.Provider pRegistries) {
        inventory.load(pRegistries, compound);
        buffer.load(pRegistries, compound, "buffer");
        super.loadAdditional(compound, pRegistries);
    }

    @Override
    public IItemHandler getInventory(Direction direction) {
        return inventory;
    }

    @Override
    public void clearContent() {
        inventory.clear();
    }
}