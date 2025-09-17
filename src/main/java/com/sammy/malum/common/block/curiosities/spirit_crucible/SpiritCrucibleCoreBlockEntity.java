package com.sammy.malum.common.block.curiosities.spirit_crucible;

import com.sammy.malum.common.block.*;
import com.sammy.malum.common.data.map.ImpetusDataMap;
import com.sammy.malum.common.item.augment.MendingDiffuserItem;
import com.sammy.malum.common.item.augment.ShieldingApparatusItem;
import com.sammy.malum.common.item.augment.WarpingEngineItem;
import com.sammy.malum.common.item.augment.core.*;
import com.sammy.malum.common.recipe.*;
import com.sammy.malum.core.systems.artifice.*;
import com.sammy.malum.common.block.storage.*;
import com.sammy.malum.common.item.spirit.*;
import com.sammy.malum.common.payloads.CodecUtil;
import com.sammy.malum.core.systems.recipe.*;
import com.sammy.malum.core.systems.spirit.type.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.block.*;
import com.sammy.malum.registry.common.item.*;
import com.sammy.malum.registry.common.recipe.*;
import com.sammy.malum.visual_effects.*;
import com.sammy.malum.visual_effects.networked.MalumNetworkedParticleEffectColorData;
import net.minecraft.core.*;
import net.minecraft.nbt.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.*;
import net.minecraft.util.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.item.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.phys.*;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.wrapper.CombinedInvWrapper;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.helpers.block.*;
import team.lodestar.lodestone.systems.blockentity.*;
import team.lodestar.lodestone.systems.multiblock.*;
import team.lodestar.lodestone.systems.recipe.*;

import javax.annotation.Nullable;
import java.util.function.*;

@SuppressWarnings("deprecation")
public class SpiritCrucibleCoreBlockEntity extends MultiBlockCoreEntity implements IArtificeAcceptor, IMalumSpecialItemAccessPoint, IItemHandlerSupplier {

    public static final Vec3 CRUCIBLE_ITEM_OFFSET = new Vec3(0f, 1.1f, 0f);
    public static final Vec3 CRUCIBLE_CORE_AUGMENT_OFFSET = new Vec3(0f, 2.5f, 0f);
    public static final Supplier<MultiBlockStructure> STRUCTURE = () -> (MultiBlockStructure.of(new MultiBlockStructure.StructurePiece(0, 1, 0, MalumBlocks.SPIRIT_CRUCIBLE_COMPONENT.get().defaultBlockState())));

    public LodestoneBlockEntityInventory inventory;
    public LodestoneBlockEntityInventory spiritInventory;
    public LodestoneBlockEntityInventory augmentInventory;
    public LodestoneBlockEntityInventory coreAugmentInventory;
    public SpiritFocusingRecipe recipe;

    public float spiritAmount;
    public float spiritSpin;

    public float progress;
    public boolean isCrafting;

    public int queuedCracks;
    public int crackTimer;

    public ArtificeAttributeData attributes = new ArtificeAttributeData();
    private final Supplier<IItemHandler> exposedInventory = () -> new CombinedInvWrapper(inventory, spiritInventory);

    public SpiritCrucibleCoreBlockEntity(BlockEntityType<? extends SpiritCrucibleCoreBlockEntity> type, MultiBlockStructure structure, BlockPos pos, BlockState state) {
        super(type, structure, pos, state);
        inventory = MalumBlockEntityInventory.singleNotSpirit(this).onContentsChanged(this::updateRecipe);
        spiritInventory = MalumSpiritBlockEntityInventory.spiritStacks(this, 4).onContentsChanged(this::updateRecipe);
        augmentInventory = AugmentBlockEntityInventory.augmentInventory(this, 4).onContentsChanged(() -> recalibrateAccelerators(level));
        coreAugmentInventory = AugmentBlockEntityInventory.coreAugmentInventory(this, 1).onContentsChanged(() -> recalibrateAccelerators(level));
    }

    public SpiritCrucibleCoreBlockEntity(BlockPos pos, BlockState state) {
        this(MalumBlockEntities.SPIRIT_CRUCIBLE.get(), STRUCTURE.get(), pos, state);
    }

    @Override
    public IItemHandler getInventory(Direction direction) {
        return exposedInventory.get();
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
            if (level.isClientSide) {
                if (recipe != null) {
                    CrucibleSoundInstance.playSound(this);
                }
            }
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
        if (pStack.is(MalumTags.ItemTags.IS_ARTIFICE_TOOL)) {
            attributes.applyTuningForkBuff(serverLevel, worldPosition);
            return ItemInteractionResult.SUCCESS;
        }
        return super.onUseWithItem(pPlayer, pStack, pHand);
    }

    @Override
    public ItemInteractionResult onUse(Player pPlayer, InteractionHand pHand) {
        if (!(level instanceof ServerLevel serverLevel)) {
            return ItemInteractionResult.CONSUME;
        }
        var heldStack = pPlayer.getItemInHand(pHand);
        boolean isAugment = heldStack.has(MalumDataComponents.ARTIFICE_AUGMENT);
        if (!isAugment || heldStack.isEmpty()) {
            var spiritResult = spiritInventory.interact(serverLevel, pPlayer, pHand);
            if (!spiritResult.isEmpty()) {
                return ItemInteractionResult.SUCCESS;
            }
            var impetusResult = inventory.interact(serverLevel, pPlayer, pHand);
            if (!impetusResult.isEmpty()) {
                return ItemInteractionResult.SUCCESS;
            }
        }
        if (isAugment || heldStack.isEmpty()) {
            if (heldStack.isEmpty() || !heldStack.get(MalumDataComponents.ARTIFICE_AUGMENT).isCoreAugment()) {
                var augment = augmentInventory.interact(serverLevel, pPlayer, pHand);
                if (!augment.isEmpty()) {
                    return ItemInteractionResult.SUCCESS;
                }
            }
            var coreAugment = coreAugmentInventory.interact(serverLevel, pPlayer, pHand);
            if (!coreAugment.isEmpty()) {
                return ItemInteractionResult.SUCCESS;
            }
        }
        return ItemInteractionResult.FAIL;
    }

    @Override
    public void serverTick(ServerLevel level) {
        if (queuedCracks > 0) {
            crackTimer++;
            if (crackTimer % 5 == 0) {
                if (crackTimer >= 15) {
                    crackTimer = 0;
                }
                float pitch = RandomHelper.randomBetween(level.getRandom(), 0.9f, 1.1f) * (0.95f + (crackTimer - 8) * 0.015f);
                level.playSound(null, worldPosition, MalumSoundEvents.IMPETUS_CRACK.get(), SoundSource.BLOCKS, 0.7f, pitch);
                queuedCracks--;
                if (queuedCracks == 0) {
                    crackTimer = 0;
                }
            }
        }
        if ((!isCrafting && recipe != null) || (isCrafting && recipe == null)) {
            isCrafting = !isCrafting;
            setDirty();
        }
        if (isCrafting) {
            float speed = attributes.focusingSpeed.getValue(attributes);
            attributes.getInfluenceData(level).ifPresent(d -> {
                for (ArtificeModifierSourceInstance modifier : d.modifiers()) {
                    modifier.tickFocusing(attributes);
                    if (!modifier.canModifyFocusing(attributes)) {
                        recalibrateAccelerators(level);
                    }
                }
            });
            progress += speed;
            if (progress >= recipe.time) {
                craft(level);
            }
        } else {
            if (progress != 0) {
                progress = 0;
                invalidateModifiers(level);
            }
        }
    }

    @Override
    public void clientTick(Level level) {
        float speed = attributes.focusingSpeed.getValue(attributes);
        spiritAmount = Math.max(1, Mth.lerp(0.1f, spiritAmount, spiritInventory.getFilledSlotCount()));
        spiritSpin += 1 + speed * 0.1f;
        SpiritCrucibleParticleEffects.passiveCrucibleParticles(this);
    }

    public void craft(ServerLevel level) {
        var impetus = inventory.getStackInSlot(0);
        var outputStack = recipe.output.copy();
        var itemPos = getItemPos();
        var random = level.random;
        float speed = attributes.focusingSpeed.getValue(attributes);
        float instability = attributes.instability.getValue(attributes);
        float fortuneChance = attributes.fortuneChance.getValue(attributes);
        int durabilityCost = 0;
        if (!ShieldingApparatusItem.shieldImpetus(level, worldPosition, attributes)) {
            if (recipe.durabilityCost != 0 && impetus.isDamageableItem()) {
                durabilityCost = recipe.durabilityCost;
                if (instability > 0 && random.nextFloat() < instability) {
                    durabilityCost *= 2;
                    if (instability > 1) {
                        durabilityCost = Math.round(durabilityCost * (instability));
                    }
                }
                queuedCracks += durabilityCost;
            }
        }
        for (SpiritIngredient spirit : recipe.spirits) {
            for (int i = 0; i < spiritInventory.slotCount; i++) {
                ItemStack spiritStack = spiritInventory.getStackInSlot(i);
                if (spirit.test(spiritStack)) {
                    spiritStack.shrink(spirit.count());
                    break;
                }
            }
        }
        if (coreAugmentInventory.getStackInSlot(0).getItem() instanceof SuspiciousDeviceItem) {
            SuspiciousDeviceItem.blowUp(level, getBlockPos());
        }
        boolean skippedForward = WarpingEngineItem.skipForward(level, worldPosition, attributes);
        if (skippedForward) {
            progress = recipe.time - 10 * speed;
        }
        else {
            SympathyDrive.completeCycle(attributes, durabilityCost);
            progress = 0;
        }

        MalumParticleEffectTypes.SPIRIT_CRUCIBLE_CRAFTS.createEffect(worldPosition)
                .color(MalumNetworkedParticleEffectColorData.fromSpirits(recipe.spirits))
                .spawn(level);

        level.playSound(null, worldPosition, MalumSoundEvents.CRUCIBLE_CRAFT.get(), SoundSource.BLOCKS, 1, 0.75f + random.nextFloat() * 0.5f);
        level.addFreshEntity(new ItemEntity(level, itemPos.x, itemPos.y, itemPos.z, outputStack));
        while (fortuneChance > 0) {
            if (fortuneChance >= 1 || random.nextFloat() < fortuneChance) {
                level.addFreshEntity(new ItemEntity(level, itemPos.x, itemPos.y, itemPos.z, outputStack.copy()));
            }
            fortuneChance -= 1;
        }
        if (durabilityCost > 0) {
            impetus.hurtAndBreak(durabilityCost, level, null, brokenStack -> {
                ImpetusDataMap data = brokenStack.builtInRegistryHolder().getData(MalumDataMaps.FRACTURED_IMPETUS_VARIANT);
                if (data != null) {
                    inventory.setStackInSlot(0, data.otherImpetus().value().getDefaultInstance());
                }
            });
            if (MendingDiffuserItem.repairImpetus(level, attributes, impetus)) {
                SympathyDrive.repairImpetus(level, attributes, impetus);
            }
        }
        updateRecipe();
        BlockStateHelper.updateAndNotifyState(level, worldPosition);
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
        net.minecraft.world.item.Item currentItem = spiritInventory.getStackInSlot(0).getItem();
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
    public LodestoneBlockEntityInventory getSuppliedInventory() {
        return inventory;
    }

    public void updateRecipe() {
        spiritInventory.updateInventoryCaches();
        recipe = LodestoneRecipeType.getRecipe(level, MalumRecipeTypes.SPIRIT_FOCUSING.get(), new SpiritBasedRecipeInput(inventory.getStackInSlot(0), spiritInventory.nonEmptyItemStacks));
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
        return VecHelper.rotatingRadialOffset(new Vec3(0.5f, height, 0.5f), distance, slot, augmentInventory.slotCount, spinLerp, 240);
    }
}