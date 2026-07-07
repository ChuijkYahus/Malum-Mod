package com.sammy.malum.common.block.curiosities.artifice.elemental_artifice.base;

import com.sammy.malum.common.block.curiosities.artifice.redstone.OpenStateBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import team.lodestar.lodestone.helpers.NBTHelper;
import team.lodestar.lodestone.modules.toolkit.blockentity.LodestoneBlockEntityType;

import java.util.Optional;

public abstract class SecondaryArtificeBlockEntity extends ElementalArtificeBlockEntity {

    private BlockPos ownerPos;

    public SecondaryArtificeBlockEntity(LodestoneBlockEntityType<? extends ElementalArtificeBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        if (ownerPos != null) {
            tag.put("owner_pos", NBTHelper.saveBlockPos(ownerPos));
        }
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);
        ownerPos = NBTHelper.readBlockPos(pTag.getCompound("owner_pos"));
    }

    @Override
    public void setInfo(ElementalArtificeBlockConfigInfo info) {
        getOwner().ifPresent(g -> g.setInfo(info));
    }

    @Override
    public NetworkedTinkeringInfo<? extends OpenStateBlockEntity> resetState() {
        if (getOwner().isEmpty()) {
            return new ElementalArtificeBlockConfigInfo(0, false);
        }
        return getOwner().get().resetState();
    }

    @Override
    public boolean canTinker() {
        return getOwner().isPresent();
    }

    @Override
    public OpenStateBlockEntity redirectTinkerFocus() {
        var optional = getOwner();
        if (optional.isPresent()) {
            return optional.get();
        }
        return super.redirectTinkerFocus();
    }

    @Override
    public void onBreak(@Nullable Player player) {
        var optional = getOwner();
        if (optional.isPresent()) {
            var owner = optional.get();
            level.scheduleTick(ownerPos, owner.getBlockState().getBlock(), 2);
        }
    }

    public Optional<PrimaryArtificeBlockEntity> getOwner() {
        return getOwner(PrimaryArtificeBlockEntity.class);
    }

    public <T extends PrimaryArtificeBlockEntity> Optional<T> getOwner(Class<T> ownerClass) {
        if (ownerPos == null) {
            return Optional.empty();
        }
        var instance = level.getBlockEntity(ownerPos);
        if (ownerClass.isInstance(instance)) {
            return Optional.of(ownerClass.cast(instance));
        }
        return Optional.empty();
    }

    public boolean canBind(PrimaryArtificeBlockEntity owner) {
        if (ownerPos == null) {
            return true;
        }
        return owner.getBlockPos().equals(ownerPos);
    }

    public boolean isModified() {
        return false;
//        return getOwner().map(i -> i.modified).orElse(false);
    }

    public void bind(PrimaryArtificeBlockEntity igniter) {
        ownerPos = igniter.getBlockPos();
    }

    public void unbind() {
        ownerPos = null;
        level.setBlock(getBlockPos(), getBlockState().setValue(SecondaryArtificeBlock.POWERED, false), 2);
    }
}