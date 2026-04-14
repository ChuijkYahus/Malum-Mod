package com.sammy.malum.common.block.curiosities.artifice.spirit_catalyzer;

import com.sammy.malum.common.block.*;
import com.sammy.malum.core.systems.artifice.ArtificeModifierSourceInstance;
import com.sammy.malum.core.systems.artifice.IArtificeModifierSource;
import com.sammy.malum.registry.common.MalumContent;
import com.sammy.malum.registry.common.block.*;
import com.sammy.malum.visual_effects.*;
import net.minecraft.core.*;
import net.minecraft.nbt.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.phys.*;
import net.neoforged.neoforge.items.IItemHandler;
import team.lodestar.lodestone.modules.toolkit.blockentity.*;
import team.lodestar.lodestone.modules.toolkit.inventory.ItemStackMultiHandler;
import team.lodestar.lodestone.modules.toolkit.multiblock.*;

import javax.annotation.*;
import java.util.*;
import java.util.function.*;

public class SpiritCatalyzerCoreBlockEntity extends MultiBlockCoreEntity implements IArtificeModifierSource, IInventoryCapabilityProvider {

    public static final Supplier<HorizontalDirectionStructure> STRUCTURE = () -> (HorizontalDirectionStructure.of(new MultiBlockStructure.StructurePiece(0, 1, 0, MalumContent.Artifice.SPIRIT_CATALYZER_COMPONENT.get().defaultBlockState())));
    public static final Vec3 CATALYZER_ITEM_OFFSET = new Vec3(0.5f, 2f, 0.5f);
    public static final Vec3 CATALYZER_AUGMENT_OFFSET = new Vec3(0.5f, 2.75f, 0.5f);

    public MalumBlockItemStackHandler inventory;
    public MalumBlockItemStackHandler augmentInventory;
    public ItemStackMultiHandler inventoryHandler;
    public CatalyzerArtificeModifierSourceInstance modifier;
    public float burnTicks;

    public SpiritCatalyzerCoreBlockEntity(LodestoneBlockEntityType<? extends SpiritCatalyzerCoreBlockEntity> type, MultiBlockStructure structure, BlockPos pos, BlockState state) {
        super(type, structure, pos, state);
        inventory = MalumBlockItemStackHandler.create(this, 1).noSpirits().onContentsChanged(this::triggerRecalibration).build();
        augmentInventory = MalumBlockItemStackHandler.create(this, 1).onlyAugments().onContentsChanged(this::triggerRecalibration).build();
        inventoryHandler = new ItemStackMultiHandler(inventory, augmentInventory);
    }

    public SpiritCatalyzerCoreBlockEntity(BlockPos pos, BlockState state) {
        this(MalumBlockEntities.SPIRIT_CATALYZER.get(), STRUCTURE.get(), pos, state);
    }

    @Override
    public IItemHandler getInventory(Direction direction) {
        return inventory;
    }

    @Override
    public ArtificeModifierSourceInstance createFocusingModifierInstance() {
        return modifier = new CatalyzerArtificeModifierSourceInstance(this);
    }

    @Override
    public Optional<ArtificeModifierSourceInstance> getFocusingModifierInstance() {
        return Optional.ofNullable(modifier);
    }

    @Override
    public ItemInteractionResult onUse(Player pPlayer, InteractionHand pHand) {
        if (!(level instanceof ServerLevel serverLevel)) {
            return ItemInteractionResult.CONSUME;
        }
        if (inventoryHandler.interact(serverLevel, pPlayer, pHand)) {
            return ItemInteractionResult.SUCCESS;
        }
        return ItemInteractionResult.FAIL;
    }

    @Override
    public void onBreak(@Nullable Player player) {
        if (!level.isClientSide) {
            inventory.dumpItems(level, worldPosition);
            augmentInventory.dumpItems(level, worldPosition);
        }
        super.onBreak(player);
    }

    @Override
    public void clientTick(Level level) {
        SpiritCrucibleParticleEffects.passiveSpiritCatalyzerParticles(this);
    }

    @Override
    protected void saveAdditional(CompoundTag compound, HolderLookup.Provider registryLookup) {
        if (burnTicks != 0) {
            compound.putFloat("burnTicks", burnTicks);
        }
        inventory.save(registryLookup, compound);
        augmentInventory.save(registryLookup, compound, "augmentInventory");
        super.saveAdditional(compound, registryLookup);
    }

    @Override
    public void loadAdditional(CompoundTag compound, HolderLookup.Provider registries) {
        burnTicks = compound.getFloat("burnTicks");
        inventory.load(registries, compound);
        augmentInventory.load(registries, compound, "augmentInventory");
        super.loadAdditional(compound, registries);
    }

    public void triggerRecalibration() {
        triggerRecalibration(level, getBlockPos());
    }
}