package com.sammy.malum.common.block.curiosities.sorcery.magehand_coffer;

import com.mojang.datafixers.util.Pair;
import com.sammy.malum.common.block.MalumBlockItemStackHandler;
import com.sammy.malum.common.data.component.WandPartsComponent;
import com.sammy.malum.common.data.custom.wand_parts.WandMaterialType;
import com.sammy.malum.common.data.custom.wand_parts.WandMaterialTypeDataReloadListener;
import com.sammy.malum.common.data.custom.wand_parts.WandPartType;
import com.sammy.malum.common.data.custom.wand_parts.WandPartType.WandPartGroup;
import com.sammy.malum.common.data.custom.wand_parts.WandPartTypeDataReloadListener;
import com.sammy.malum.registry.common.block.MalumBlockEntities;
import com.sammy.malum.visual_effects.block.MagehandCofferParticleEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Clearable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.IItemHandler;
import team.lodestar.lodestone.modules.toolkit.blockentity.IInventoryCapabilityProvider;
import team.lodestar.lodestone.modules.toolkit.blockentity.LodestoneBlockEntity;
import team.lodestar.lodestone.modules.toolkit.blockentity.LodestoneBlockEntityType;

import java.util.HashMap;
import java.util.Map;

import static com.sammy.malum.common.block.curiosities.sorcery.wand_tinkerer.WandTinkererContainer.WAND_TINKERER;

public class MagehandCofferBlockEntity extends LodestoneBlockEntity implements IInventoryCapabilityProvider, Clearable {

    public WandPartGroup selectedGroup = WandPartGroup.CORE;

    public MalumBlockItemStackHandler inventory;

    public MagehandCofferBlockEntity(LodestoneBlockEntityType<? extends MagehandCofferBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        inventory = MalumBlockItemStackHandler.create(this, 18).build();
    }

    public MagehandCofferBlockEntity(BlockPos pos, BlockState state) {
        this(MalumBlockEntities.MAGEHAND_COFFER.get(), pos, state);
    }

    @Override
    public ItemInteractionResult onUse(Player player, InteractionHand pHand) {
        if (player instanceof ServerPlayer serverPlayer) {
            var container = new SimpleMenuProvider((w, p, pl) -> new MagehandCofferContainer(w, p, ContainerLevelAccess.create(pl.level(), getBlockPos())), WAND_TINKERER);
            serverPlayer.openMenu(container, buf -> buf.writeBlockPos(this.getBlockPos()));
        }
        return ItemInteractionResult.SUCCESS;
    }

    @Override
    public void clientTick(Level level) {
        MagehandCofferParticleEffects.magehandParticle(level, getBlockPos().getCenter().add(0, 1, 0));
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);

        selectedGroup.save(tag);

        inventory.save(registries, tag);
    }

    @Override
    public void loadAdditional(CompoundTag compound, HolderLookup.Provider pRegistries) {

        inventory.load(pRegistries, compound);
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