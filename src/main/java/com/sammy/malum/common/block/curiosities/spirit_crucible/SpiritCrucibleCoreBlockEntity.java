package com.sammy.malum.common.block.curiosities.spirit_crucible;

import com.sammy.malum.common.block.*;
import com.sammy.malum.common.recipe.*;
import com.sammy.malum.core.systems.artifice.*;
import com.sammy.malum.common.block.storage.*;
import com.sammy.malum.common.item.spirit.*;
import com.sammy.malum.common.payloads.CodecUtil;
import com.sammy.malum.core.systems.recipe.*;
import com.sammy.malum.core.systems.spirit.type.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.block.*;
import com.sammy.malum.registry.common.recipe.*;
import com.sammy.malum.visual_effects.*;
import net.minecraft.core.*;
import net.minecraft.nbt.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.phys.*;
import net.neoforged.neoforge.items.IItemHandler;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.modules.toolkit.blockentity.*;
import team.lodestar.lodestone.modules.toolkit.inventory.ItemStackMultiHandler;
import team.lodestar.lodestone.modules.toolkit.inventory.LodestoneItemStackHandler;
import team.lodestar.lodestone.modules.toolkit.multiblock.*;
import team.lodestar.lodestone.modules.toolkit.recipe.LodestoneRecipeSearch;

import javax.annotation.Nullable;
import java.util.function.*;

@SuppressWarnings("NullableProblems")
public class SpiritCrucibleCoreBlockEntity extends MultiBlockCoreEntity implements IArtificeAcceptor, IMalumSpecialItemAccessPoint, IInventoryCapabilityProvider {

    public static final Vec3 CRUCIBLE_ITEM_OFFSET = new Vec3(0f, 1.1f, 0f);
    public static final Vec3 CRUCIBLE_CORE_AUGMENT_OFFSET = new Vec3(0f, 2.5f, 0f);
    public static final Supplier<MultiBlockStructure> STRUCTURE = () -> (MultiBlockStructure.of(new MultiBlockStructure.StructurePiece(0, 1, 0, MalumBlocks.SPIRIT_CRUCIBLE_COMPONENT.get().defaultBlockState())));

    public MalumBlockItemStackHandler inventory;
    public MalumBlockItemStackHandler spiritInventory;
    public MalumBlockItemStackHandler augmentInventory;
    public MalumBlockItemStackHandler coreAugmentInventory;
    public ItemStackMultiHandler inventoryHandler;

    public SpiritFocusingRecipe recipe;

    public float spiritAmount;
    public float spiritSpin;

    public float progress;
    public boolean isCrafting;

    public int queuedCracks;
    public int crackTimer;

    public ArtificeAttributeData attributes = new ArtificeAttributeData();

    public SpiritCrucibleCoreBlockEntity(LodestoneBlockEntityType<? extends SpiritCrucibleCoreBlockEntity> type, MultiBlockStructure structure, BlockPos pos, BlockState state) {
        super(type, structure, pos, state);
        inventory = MalumBlockItemStackHandler.create(this, 1).noSpirits().onContentsChanged(this::updateRecipe).build();
        spiritInventory = MalumBlockItemStackHandler.create(this, 6).onlySpirits().onContentsChanged(this::updateRecipe).build();
        augmentInventory = MalumBlockItemStackHandler.create(this, 4).onlyAugments().onContentsChanged(this::recalibrateAccelerators).build();
        coreAugmentInventory = MalumBlockItemStackHandler.create(this, 1).onlyCoreAugments().onContentsChanged(this::recalibrateAccelerators).build();
        inventoryHandler = new ItemStackMultiHandler(inventory, spiritInventory, augmentInventory, coreAugmentInventory);
    }

    public SpiritCrucibleCoreBlockEntity(BlockPos pos, BlockState state) {
        this(MalumBlockEntities.SPIRIT_CRUCIBLE.get(), STRUCTURE.get(), pos, state);
    }

    @Override
    public IItemHandler getInventory(Direction direction) {
        return inventory;
    }

    @Override
    protected void saveAdditional(CompoundTag compound, HolderLookup.Provider pRegistries) {
        super.saveAdditional(compound, pRegistries);
        if (spiritAmount != 0) {
            compound.putFloat("spiritAmount", spiritAmount);
        }
        if (progress != 0) {
            compound.putFloat("progress", progress);
        }
        if (queuedCracks != 0) {
            compound.putInt("queuedCracks", queuedCracks);
        }

        compound.put("attributeData", CodecUtil.encodeNBT(ArtificeAttributeData.CODEC, attributes));
        inventory.save(pRegistries, compound);
        spiritInventory.save(pRegistries, compound, "spiritInventory");
        augmentInventory.save(pRegistries, compound, "augmentInventory");
        coreAugmentInventory.save(pRegistries, compound, "coreAugmentInventory");
    }

    @Override
    public void loadAdditional(CompoundTag compound, HolderLookup.Provider pRegistries) {
        spiritAmount = compound.getFloat("spiritAmount");
        progress = compound.getFloat("progress");
        queuedCracks = compound.getInt("queuedCracks");

        attributes = CodecUtil.decodeNBT(ArtificeAttributeData.CODEC, compound.getCompound("attributeData"));

        inventory.load(pRegistries, compound);
        spiritInventory.load(pRegistries, compound, "spiritInventory");
        augmentInventory.load(pRegistries, compound, "augmentInventory");
        coreAugmentInventory.load(pRegistries, compound, "coreAugmentInventory");

        if (level != null) {
            updateRecipe();
        }
        super.loadAdditional(compound, pRegistries);
    }

    @Override
    public void onBreak(@Nullable Player player) {
        inventory.dumpItems(level, worldPosition);
        spiritInventory.dumpItems(level, worldPosition);
        augmentInventory.dumpItems(level, worldPosition);
        coreAugmentInventory.dumpItems(level, worldPosition);
        if (!level.isClientSide) {
            invalidateModifiers(level);
        }
        super.onBreak(player);
    }

    @Override
    public ItemInteractionResult onUseWithItem(Player pPlayer, ItemStack pStack, InteractionHand pHand) {
        if (!(level instanceof ServerLevel serverLevel)) {
            return ItemInteractionResult.CONSUME;
        }
        if (pStack.is(MalumTags.Items.IS_ARTIFICE_TOOL)) {
            attributes.applyTuningForkBuff(serverLevel, worldPosition);
            return ItemInteractionResult.SUCCESS;
        }
        return super.onUseWithItem(pPlayer, pStack, pHand);
    }

    @Override
    public ItemInteractionResult onUse(Player player, InteractionHand hand) {
        if (!(level instanceof ServerLevel serverLevel)) {
            return ItemInteractionResult.CONSUME;
        }
        if (inventoryHandler.interact(serverLevel, player, hand)) {
            return ItemInteractionResult.SUCCESS;
        }
        return super.onUse(player, hand);
    }

    @Override
    public void serverTick(ServerLevel level) {
        SpiritCrucibleInternals.addCrackFeedback(level, this);
        SpiritCrucibleInternals.tickFocusingCycle(level, this);
    }

    @Override
    public void clientTick(Level level) {
        float speed = attributes.focusingSpeed.getValue(attributes);
        spiritAmount = Math.max(1, Mth.lerp(0.1f, spiritAmount, spiritInventory.getFilledSlotCount()));
        spiritSpin += 1 + speed * 0.1f;
        SpiritCrucibleParticleEffects.passiveCrucibleParticles(this);
    }

    @Override
    public Vec3 getVisualAccelerationPoint() {
        return getItemPos();
    }

    @Override
    public ArtificeAttributeData getAttributes() {
        return attributes;
    }

    @Override
    public void setAttributes(ArtificeAttributeData attributes) {
        this.attributes = attributes;
    }

    @Override
    public SpiritArcanaType getActiveSpiritType() {
        int spiritCount = spiritInventory.getFilledSlotCount();
        Item currentItem = spiritInventory.getStackInSlot(0).getItem();
        if (spiritCount > 1) {
            float duration = 60f * spiritCount;
            float gameTime = (getLevel().getGameTime() % duration) / 60f;
            currentItem = spiritInventory.getStackInSlot(Mth.floor(gameTime)).getItem();
        }
        if (!(currentItem instanceof SpiritShardItem spiritItem)) {
            return null;
        }
        return spiritItem.getSpirit();
    }

    @Override
    public void applyAugments(Consumer<ItemStack> augmentConsumer) {
        augmentInventory.getNonEmptyStacks().forEach(augmentConsumer);
        coreAugmentInventory.getNonEmptyStacks().forEach(augmentConsumer);
    }

    @Override
    public Vec3 getItemPos(float partialTicks) {
        return getBlockPos().getCenter().add(CRUCIBLE_ITEM_OFFSET);
    }

    @Override
    public BlockPos getAccessPointBlockPos() {
        return getBlockPos();
    }

    @Override
    public LodestoneItemStackHandler getSuppliedInventory() {
        return inventory;
    }

    private void recalibrateAccelerators() {
        recalibrateAccelerators(level);
    }

    public void updateRecipe() {
        spiritInventory.updateCaches();
        SpiritBasedRecipeInput input = new SpiritBasedRecipeInput(inventory, spiritInventory);
        recipe = LodestoneRecipeSearch.search(level, MalumRecipeTypes.SPIRIT_FOCUSING::get).findRecipe(input);
    }

    public Vec3 getSpiritItemOffset(int slot, float partialTicks) {
        float predictedSpiritSpin = spiritSpin + 1 + attributes.focusingSpeed.getValue(attributes) * 0.1f;
        float spinLerp = spiritSpin + partialTicks * (predictedSpiritSpin - spiritSpin);
        float distance = 0.75f + (float) Math.sin(((spinLerp + partialTicks) / 20f) % 6.28f) * 0.025f;
        float height = 1.8f;
        return VecHelper.rotatingRadialOffset(new Vec3(0.5f, height, 0.5f), distance, slot, spiritAmount, spinLerp, 360);
    }

    public Vec3 getAugmentItemOffset(int slot, float partialTicks) {
        float predictedSpiritSpin = spiritSpin + 1 + attributes.focusingSpeed.getValue(attributes) * 0.1f;
        float spinLerp = spiritSpin + partialTicks * (predictedSpiritSpin - spiritSpin);
        float distance = 0.6f + (float) Math.sin(((spiritSpin + partialTicks) / 20f) % 6.28f) * 0.025f;
        float height = 1.6f;
        return VecHelper.rotatingRadialOffset(new Vec3(0.5f, height, 0.5f), distance, slot, augmentInventory.getSlotCount(), spinLerp, 240);
    }
}