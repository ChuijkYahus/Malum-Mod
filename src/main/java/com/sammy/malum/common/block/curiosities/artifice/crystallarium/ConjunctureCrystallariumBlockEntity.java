package com.sammy.malum.common.block.curiosities.artifice.crystallarium;

import com.sammy.malum.common.block.*;
import com.sammy.malum.registry.common.block.MalumBlockEntities;
import com.sammy.malum.visual_effects.block.ConjunctureCrystallariumParticleEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import team.lodestar.lodestone.modules.toolkit.blockentity.*;

import static com.sammy.malum.common.block.curiosities.artifice.crystallarium.ConjunctureCrystallariumContainer.*;

public class ConjunctureCrystallariumBlockEntity extends LodestoneBlockEntity {

    public MalumBlockItemStackHandler inventory;

    public ConjunctureCrystallariumBlockEntity(LodestoneBlockEntityType<? extends ConjunctureCrystallariumBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        inventory = MalumBlockItemStackHandler.create(this, 5).build();
    }

    public ConjunctureCrystallariumBlockEntity(BlockPos pos, BlockState state) {
        this(MalumBlockEntities.CONJUNCTURE_CRYSTALLARIUM.get(), pos, state);
    }

    @Override
    public ItemInteractionResult onUse(Player player, InteractionHand pHand) {
        if (player instanceof ServerPlayer serverPlayer) {
            var container = new SimpleMenuProvider((w, p, pl) -> new ConjunctureCrystallariumContainer(w, p, ContainerLevelAccess.create(pl.level(), getBlockPos())), CONJUNCTURE_CRYSTALLARIUM);
            serverPlayer.openMenu(container, buf -> buf.writeBlockPos(this.getBlockPos()));
        }
        return ItemInteractionResult.SUCCESS;
    }

    @Override
    public void clientTick(Level level) {
        ConjunctureCrystallariumParticleEffects.passiveCrystallariumParticles(this);
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