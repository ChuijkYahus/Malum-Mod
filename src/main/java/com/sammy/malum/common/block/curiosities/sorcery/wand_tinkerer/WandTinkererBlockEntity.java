package com.sammy.malum.common.block.curiosities.sorcery.wand_tinkerer;

import com.sammy.malum.common.block.MalumBlockItemStackHandler;
import com.sammy.malum.registry.common.block.MalumBlockEntities;
import com.sammy.malum.visual_effects.block.ConjunctureCrystallariumParticleEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import team.lodestar.lodestone.modules.toolkit.blockentity.LodestoneBlockEntity;
import team.lodestar.lodestone.modules.toolkit.blockentity.LodestoneBlockEntityType;

import static com.sammy.malum.common.block.curiosities.artifice.crystallarium.ConjunctureCrystallariumContainer.CONJUNCTURE_CRYSTALLARIUM;
import static com.sammy.malum.common.block.curiosities.sorcery.wand_tinkerer.WandTinkererContainer.WAND_TINKERER;

public class WandTinkererBlockEntity extends LodestoneBlockEntity {

    public MalumBlockItemStackHandler inventory;

    public WandTinkererBlockEntity(LodestoneBlockEntityType<? extends WandTinkererBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        inventory = MalumBlockItemStackHandler.create(this, 5).build();
    }

    public WandTinkererBlockEntity(BlockPos pos, BlockState state) {
        this(MalumBlockEntities.WAND_TINKERER.get(), pos, state);
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
    }

    @Override
    public void loadAdditional(CompoundTag compound, HolderLookup.Provider pRegistries) {
        inventory.load(pRegistries, compound);
        super.loadAdditional(compound, pRegistries);
    }
}