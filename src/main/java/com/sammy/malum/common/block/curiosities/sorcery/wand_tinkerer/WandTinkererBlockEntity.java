package com.sammy.malum.common.block.curiosities.sorcery.wand_tinkerer;

import com.mojang.datafixers.util.Pair;
import com.sammy.malum.common.block.MalumBlockItemStackHandler;
import com.sammy.malum.common.data.component.WandPartsComponent;
import com.sammy.malum.common.data.custom.wand_parts.WandMaterialType;
import com.sammy.malum.common.data.custom.wand_parts.WandMaterialTypeDataReloadListener;
import com.sammy.malum.common.data.custom.wand_parts.WandPartType;
import com.sammy.malum.common.data.custom.wand_parts.WandPartType.WandPartGroup;
import com.sammy.malum.common.data.custom.wand_parts.WandPartTypeDataReloadListener;
import com.sammy.malum.registry.common.MalumContent;
import com.sammy.malum.registry.common.block.MalumBlockEntities;
import com.sammy.malum.registry.common.item.MalumDataComponents;
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
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.IItemHandler;
import team.lodestar.lodestone.modules.toolkit.blockentity.IInventoryCapabilityProvider;
import team.lodestar.lodestone.modules.toolkit.blockentity.LodestoneBlockEntity;
import team.lodestar.lodestone.modules.toolkit.blockentity.LodestoneBlockEntityType;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.sammy.malum.common.block.curiosities.sorcery.wand_tinkerer.WandTinkererContainer.WAND_TINKERER;

public class WandTinkererBlockEntity extends LodestoneBlockEntity implements IInventoryCapabilityProvider, Clearable {

    public WandPartGroup selectedGroup = WandPartGroup.CORE;

    public MalumBlockItemStackHandler coreParts;
    public MalumBlockItemStackHandler headParts;
    public MalumBlockItemStackHandler baseParts;
    public MalumBlockItemStackHandler baubleParts;
    public MalumBlockItemStackHandler ornamentParts;

    public MalumBlockItemStackHandler wandOutput;

    public WandTinkererBlockEntity(LodestoneBlockEntityType<? extends WandTinkererBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        coreParts = MalumBlockItemStackHandler.create(this, 6).limitItemSize(1).build();
        headParts = MalumBlockItemStackHandler.create(this, 9).limitItemSize(1).build();
        baseParts = MalumBlockItemStackHandler.create(this, 4).limitItemSize(1).build();
        baubleParts = MalumBlockItemStackHandler.create(this, 2).limitItemSize(1).build();
        ornamentParts = MalumBlockItemStackHandler.create(this, 6).limitItemSize(1).build();

        wandOutput = MalumBlockItemStackHandler.create(this, 1).limitItemSize(1).build();
    }

    public WandTinkererBlockEntity(BlockPos pos, BlockState state) {
        this(MalumBlockEntities.WAND_TINKERER.get(), pos, state);
    }

    public void handleInteraction(ServerLevel level, Player player, ItemStack item) {
        getInventory(selectedGroup).performInteraction(level, player, item);
        var wand = MalumContent.WAND.toStack();
        var component = createLiveComponent();
        wand.set(MalumDataComponents.WAND_PARTS, component);
        wandOutput.setStackInSlot(0, wand);
        setDirty();
    }

    public void updateGroup(WandPartGroup group) {
        this.selectedGroup = group;
        setDirty();
    }

    public MalumBlockItemStackHandler getInventory(WandPartGroup group) {
        return switch (group) {
            case CORE -> coreParts;
            case HEAD -> headParts;
            case BASE -> baseParts;
            case BAUBLE -> baubleParts;
            case ORNAMENT -> ornamentParts;
        };
    }

    public ItemStack getWand() {
        return wandOutput.getStackInSlot(0);
    }

    public Optional<WandPartsComponent> getWandData() {
        return Optional.ofNullable(getWand().get(MalumDataComponents.WAND_PARTS));
    }

    public boolean hasPart(WandPartGroup group) {
        return getWandData().map(p -> p.hasPart(group)).orElse(false);
    }

    public boolean isLocked(WandPartGroup group) {
        if (group.equals(WandPartGroup.CORE)) {
            return false;
        }
        return !hasPart(WandPartGroup.CORE);
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

        selectedGroup.save(tag);

        coreParts.save(registries, tag);
        headParts.save(registries, tag, "head_parts");
        baseParts.save(registries, tag, "base_parts");
        baubleParts.save(registries, tag, "bauble_parts");
        ornamentParts.save(registries, tag, "ornament_parts");
        wandOutput.save(registries, tag, "wand_output");
    }

    @Override
    public void loadAdditional(CompoundTag compound, HolderLookup.Provider pRegistries) {

        selectedGroup = WandPartGroup.load(compound);
        coreParts.load(pRegistries, compound);
        headParts.load(pRegistries, compound, "head_parts");
        baseParts.load(pRegistries, compound, "base_parts");
        baubleParts.load(pRegistries, compound, "bauble_parts");
        ornamentParts.load(pRegistries, compound, "ornament_parts");
        wandOutput.load(pRegistries, compound, "wand_output");
        super.loadAdditional(compound, pRegistries);
    }

    @Override
    public IItemHandler getInventory(Direction direction) {
        return coreParts;
    }

    @Override
    public void clearContent() {
        coreParts.clear();
        headParts.clear();
        baseParts.clear();
        baubleParts.clear();
        ornamentParts.clear();
        wandOutput.clear();
    }


    protected final WandPartsComponent createLiveComponent() {
        Map<WandPartType, WandMaterialType> parts = new HashMap<>();
        for (WandPartGroup group : WandPartGroup.values()) {
            var data = findPart(group);
            if (data != null) {
                parts.put(data.getFirst(), data.getSecond());
            }
        }
        return new WandPartsComponent(parts);
    }

    public Pair<WandPartType, WandMaterialType> findPart(WandPartGroup group) {
        Pair<WandPartType, WandMaterialType> result = null;
        var inventory = getInventory(group);
        var nonEmpty = inventory.getNonEmptyStacks();
        int highestCost = 0;
        for (WandPartType part : WandPartTypeDataReloadListener.DATA.getValues().values()) {
            if (!part.group().equals(group)) {
                continue;
            }
            for (WandMaterialType material : WandMaterialTypeDataReloadListener.DATA.getValues().values()) {
                var materialCost = part.materialCost();
                int i;
                for (i = 0; i < nonEmpty.size(); i++) {
                    if (!material.ingredient().test(nonEmpty.get(i))) {
                        break;
                    }
                }
                if (i == materialCost && materialCost > highestCost) {
                    highestCost = i;
                    result = Pair.of(part, material);
                }
            }
        }
        return result;
    }
}