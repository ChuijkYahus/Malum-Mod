package com.sammy.malum.common.block.curiosities.spirit_altar;

import com.sammy.malum.common.block.MalumBlockItemStackHandler;
import com.sammy.malum.common.block.MalumSpiritBlockItemStackHandler;
import com.sammy.malum.common.block.storage.IMalumSpecialItemAccessPoint;
import com.sammy.malum.common.recipe.SpiritInfusionRecipe;
import com.sammy.malum.core.systems.recipe.SpiritBasedRecipeInput;
import com.sammy.malum.core.systems.recipe.SpiritIngredient;
import com.sammy.malum.registry.common.MalumParticleEffectTypes;
import com.sammy.malum.registry.common.sound.MalumSoundEvents;
import com.sammy.malum.registry.common.block.MalumBlockEntities;
import com.sammy.malum.registry.common.recipe.MalumRecipeTypes;
import com.sammy.malum.visual_effects.SpiritAltarParticleEffects;
import com.sammy.malum.visual_effects.networked.altar.SpiritAltarEatItemParticleEffect;
import com.sammy.malum.visual_effects.networked.MalumNetworkedParticleEffectColorData;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.*;
import net.neoforged.neoforge.common.crafting.SizedIngredient;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.IItemHandlerModifiable;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.helpers.block.*;
import team.lodestar.lodestone.modules.toolkit.blockentity.*;
import team.lodestar.lodestone.modules.core.easing.Easing;
import team.lodestar.lodestone.modules.toolkit.inventory.ItemStackMultiHandler;
import team.lodestar.lodestone.modules.toolkit.inventory.LodestoneItemStackHandler;
import team.lodestar.lodestone.modules.toolkit.recipe.LodestoneRecipeSearch;
import team.lodestar.lodestone.modules.toolkit.recipe.LodestoneRecipeType;

import javax.annotation.Nullable;
import java.util.*;

public class SpiritAltarBlockEntity extends LodestoneBlockEntity implements IInventoryCapabilityProvider {

    public static final Vec3 ALTAR_ITEM_OFFSET = new Vec3(0.5f, 1.25f, 0.5f);
    public static final int HORIZONTAL_RANGE = 4;
    public static final int VERTICAL_RANGE = 3;
    private static final int WARMUP_DURATION = 30;

    public float speed = 1f;
    public int progress;
    public int idleProgress;
    public boolean isCrafting;
    public float warmupTimer;

    public float spiritAmount;
    public float spiritSpin;

    public List<BlockPos> acceleratorPositions = new ArrayList<>();
    public List<IAltarAccelerator> accelerators = new ArrayList<>();

    public MalumBlockItemStackHandler inventory;
    public MalumBlockItemStackHandler extrasInventory;
    public MalumBlockItemStackHandler spiritInventory;
    public ItemStackMultiHandler inventoryHandler;

    public Map<SpiritInfusionRecipe, AltarCraftingHelper.Ranking> possibleRecipes = new HashMap<>();
    public SpiritInfusionRecipe recipe;

    public SpiritAltarBlockEntity(BlockEntityType<? extends SpiritAltarBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        inventory = MalumBlockItemStackHandler.create(this, 1).noSpirits().onContentsChanged(this::recalculateRecipes).build();
        extrasInventory = MalumBlockItemStackHandler.create(this, 32).noSpirits().build();
        spiritInventory = MalumBlockItemStackHandler.create(this, 9).onlySpirits().onContentsChanged(this::recalculateRecipes).build();
        inventoryHandler = new ItemStackMultiHandler(inventory, extrasInventory, spiritInventory);
    }

    public SpiritAltarBlockEntity(BlockPos pos, BlockState state) {
        this(MalumBlockEntities.SPIRIT_ALTAR.get(), pos, state);
    }

    @Override
    public IItemHandler getInventory(Direction direction) {
        return inventory;
    }

    @Override
    protected void saveAdditional(CompoundTag compound, HolderLookup.Provider pRegistries) {
        super.saveAdditional(compound, pRegistries);
        compound.putFloat("speed", speed);
        compound.putInt("progress", progress);
        compound.putInt("idleProgress", idleProgress);
        compound.putBoolean("isCrafting", isCrafting);
        compound.putFloat("warmupTimer", warmupTimer);

        var acceleratorData = new CompoundTag();
        acceleratorData.putInt("acceleratorAmount", acceleratorPositions.size());
        for (int i = 0; i < acceleratorPositions.size(); i++) {
            acceleratorData.put("acceleratorPosition_" + i, NBTHelper.saveBlockPos(acceleratorPositions.get(i)));
        }
        compound.put("acceleratorData", acceleratorData);

        inventory.save(pRegistries, compound);
        spiritInventory.save(pRegistries, compound, "spiritInventory");
        extrasInventory.save(pRegistries, compound, "extrasInventory");
    }

    @Override
    public void loadAdditional(CompoundTag compound, HolderLookup.Provider pRegistries) {
        speed = compound.getFloat("speed");
        progress = compound.getInt("progress");
        idleProgress = compound.getInt("idleProgress");
        isCrafting = compound.getBoolean("isCrafting");
        warmupTimer = compound.getFloat("warmupTimer");

        acceleratorPositions.clear();
        accelerators.clear();
        var acceleratorData = compound.getCompound("acceleratorData");
        int amount = acceleratorData.getInt("acceleratorAmount");
        for (int i = 0; i < amount; i++) {
            BlockPos pos = NBTHelper.readBlockPos(acceleratorData.getCompound("acceleratorPosition_" + i));
            if (pos != null) {
                if (level != null && level.getBlockEntity(pos) instanceof IAltarAccelerator accelerator) {
                    acceleratorPositions.add(pos);
                    accelerators.add(accelerator);
                }
            }
        }
        inventory.load(pRegistries, compound);
        spiritInventory.load(pRegistries, compound, "spiritInventory");
        extrasInventory.load(pRegistries, compound, "extrasInventory");

        if (level != null) {
            recalculateRecipes();
            recalibrateAccelerators();
            if (level.isClientSide && isCrafting) {
                AltarSoundInstance.playSound(this);
            }
        }
        super.loadAdditional(compound, pRegistries);
    }

    @Override
    public void onBreak(@Nullable Player player) {
        inventory.dumpItems(level, worldPosition);
        spiritInventory.dumpItems(level, worldPosition);
        extrasInventory.dumpItems(level, worldPosition);
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
        var primeItem = inventory.getStackInSlot(0);
        if (!primeItem.isEmpty()) {
            idleProgress++;
            int progressCap = (int) (20 / speed);
            if (idleProgress >= progressCap) {
                recalculateRecipes();
                idleProgress = 0;
            }
        }
        progress = isCrafting ? progress + 1 : progress;
        if ((!isCrafting && recipe != null) || (isCrafting && recipe == null)) {
            isCrafting = !isCrafting;
            setDirty();
        }
        if (isCrafting) {
            if (level.getGameTime() % 20L == 0) {
                boolean canAccelerate = accelerators.stream().allMatch(iAltarAccelerator -> iAltarAccelerator.canAccelerate(this));
                if (!canAccelerate) {
                    recalibrateAccelerators();
                }
            }
            int progressCap = (int) (300 / speed);
            if (progress >= progressCap) {
                boolean success = consume(level);
                if (success) {
                    craft(level);
                }
            }
        }
    }

    @Override
    public void clientTick(Level level) {
        spiritAmount = Math.max(1, Mth.lerp(0.1f, spiritAmount, spiritInventory.getFilledSlotCount()));
        spiritSpin += 1 + getSpinUp(Easing.SINE_IN_OUT) * 0.05f + speed * 0.5f;
        SpiritAltarParticleEffects.passiveSpiritAltarParticles(this);
    }

    @Override
    public void commonTick(Level level) {
        if (!possibleRecipes.isEmpty()) {
            warmupTimer++;
        } else {
            isCrafting = false;
            progress = 0;
            warmupTimer = Mth.clamp(warmupTimer - 1, 0, WARMUP_DURATION);
        }
    }

    private void recalculateRecipes() {
        boolean hadRecipe = recipe != null;
        inventory.updateCaches();
        ItemStack stack = inventory.getStackInSlot(0);
        if (!stack.isEmpty()) {
            var input = new SpiritBasedRecipeInput(inventory, spiritInventory);
            var recipes = LodestoneRecipeSearch.search(level, MalumRecipeTypes.SPIRIT_INFUSION::get).findRecipes(input);
            possibleRecipes.clear();
            IItemHandlerModifiable pedestalItems = AltarCraftingHelper.createPedestalInventoryCapture(AltarCraftingHelper.capturePedestals(level, worldPosition));
            for (SpiritInfusionRecipe recipe : recipes) {
                possibleRecipes.put(recipe, AltarCraftingHelper.rankRecipe(recipe, stack, spiritInventory, pedestalItems, extrasInventory));
            }
            recipe = possibleRecipes.entrySet().stream().filter(it -> it.getValue() != null).max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(null);
        } else {
            recipe = null;
            possibleRecipes.clear();
        }

        if (hadRecipe && recipe == null && level != null) {
            extrasInventory.dumpItems(level, worldPosition);
        }
        if (recipe != null) {
            isCrafting = true;
        }
    }

    public boolean consume(ServerLevel level) {
        if (recipe == null) {
            return false;
        } else if (recipe.extraInputs.isEmpty())
            return true;

        List<IMalumSpecialItemAccessPoint> pedestalItems = AltarCraftingHelper.capturePedestals(level, worldPosition);
        ItemStack stack = inventory.getStackInSlot(0);
        AltarCraftingHelper.Ranking reranking = AltarCraftingHelper.rankRecipe(recipe, stack, spiritInventory, AltarCraftingHelper.createPedestalInventoryCapture(pedestalItems), extrasInventory);
        if (!Objects.equals(reranking, possibleRecipes.get(recipe))) {
            recalculateRecipes();
            return false;
        }

        SizedIngredient nextIngredient = AltarCraftingHelper.getNextIngredientToTake(recipe, extrasInventory);
        if (nextIngredient != null) {
            for (IMalumSpecialItemAccessPoint provider : pedestalItems) {
                var providerInventory = provider.getSuppliedInventory();
                var providedStack = providerInventory.extractItem(0, nextIngredient.count(), true);

                if (nextIngredient.ingredient().test(providedStack)) {
                    level.playSound(null, provider.getAccessPointBlockPos(), MalumSoundEvents.ALTAR_CONSUME.get(), SoundSource.BLOCKS, 1, 1.1f + level.random.nextFloat() * 0.5f);
                    MalumParticleEffectTypes.SPIRIT_ALTAR_EATS_ITEM
                            .createEffect(worldPosition)
                            .color(MalumNetworkedParticleEffectColorData.fromSpirits(recipe.spirits))
                            .customData(new SpiritAltarEatItemParticleEffect.SpiritAltarEatItemEffectData(provider.getAccessPointBlockPos(), providedStack))
                            .spawn(level);
                    var extractedStack = providerInventory.extractItem(0, nextIngredient.count(), false);
                    extrasInventory.insertItem(level, extractedStack);
                    break;
                }
            }
            progress = (int) (progress * 0.8f);
            if (extrasInventory.isEmpty()) {
                return false;
            }
            return AltarCraftingHelper.extractIngredient(extrasInventory, nextIngredient.ingredient(), nextIngredient.count(), true).isEmpty();
        }
        return true;
    }

    public void craft(ServerLevel level) {
        ItemStack stack = inventory.getStackInSlot(0);
        ItemStack outputStack = recipe.getOutput(level, stack);
        Vec3 itemPos = getItemPos();
        extrasInventory.clear();
        progress -= (int) (progress * 0.2f);
        stack.shrink(recipe.input.count());
        level.addFreshEntity(new ItemEntity(level, itemPos.x, itemPos.y, itemPos.z, outputStack));

        spendSpiritsOnRecipe();
        MalumParticleEffectTypes.SPIRIT_ALTAR_CRAFTS
                .createEffect(worldPosition)
                .color(MalumNetworkedParticleEffectColorData.fromSpirits(recipe.spirits))
                .spawn(level);
        level.playSound(null, worldPosition, MalumSoundEvents.ALTAR_CRAFT.get(), SoundSource.BLOCKS, 1, 0.9f + level.random.nextFloat() * 0.2f);
        recalibrateAccelerators();
        accelerators.forEach(a -> a.completeSpiritInfusion(level, this));
        recalculateRecipes();
        notifyObservers();
        setDirty();
    }

    public void spendSpiritsOnRecipe() {
        for (SpiritIngredient spiritIngredient : recipe.spirits) {
            for (ItemStack spirit : spiritInventory.getNonEmptyStacks()) {
                if (spiritIngredient.test(spirit)) {
                    spirit.shrink(spiritIngredient.count());
                    break;
                }
            }
        }
    }

    public void recalibrateAccelerators() {
        speed = 1f;
        accelerators.clear();
        acceleratorPositions.clear();
        Collection<IAltarAccelerator> nearbyAccelerators = BlockEntityHelper.getBlockEntities(IAltarAccelerator.class, level, worldPosition, HORIZONTAL_RANGE, VERTICAL_RANGE, HORIZONTAL_RANGE);
        Map<IAltarAccelerator.AltarAcceleratorType, Integer> entries = new HashMap<>();
        for (IAltarAccelerator accelerator : nearbyAccelerators) {
            if (accelerator.canAccelerate(this)) {
                int max = accelerator.getAcceleratorType().maximumEntries();
                int amount = entries.computeIfAbsent(accelerator.getAcceleratorType(), (a) -> 0);
                if (amount < max) {
                    accelerators.add(accelerator);
                    acceleratorPositions.add(((BlockEntity) accelerator).getBlockPos());
                    speed += accelerator.getAcceleration();
                    entries.replace(accelerator.getAcceleratorType(), amount + 1);
                }
            }
        }
    }

    public Vec3 getCentralItemOffset() {
        return ALTAR_ITEM_OFFSET;
    }

    public Vec3 getItemPos() {
        final BlockPos blockPos = getBlockPos();
        final Vec3 offset = getCentralItemOffset();
        return new Vec3(blockPos.getX() + offset.x, blockPos.getY() + offset.y, blockPos.getZ() + offset.z);
    }

    public Vec3 getSpiritItemOffset(int slot, float partialTicks) {
        float projectedSpiritSpin = spiritSpin + getSpinUp(Easing.SINE_IN_OUT) * 0.05f + speed * 0.5f;
        float lerpSpiritSpin = spiritSpin + partialTicks * (projectedSpiritSpin - spiritSpin);
        float distanceOscillation = Mth.sin((lerpSpiritSpin / 20f) % 6.28f) * 0.025f;
        float distance = 1 - getSpinUp(Easing.SINE_OUT) * 0.25f + distanceOscillation;
        float height = 0.75f + getSpinUp(Easing.QUARTIC_OUT) * getSpinUp(Easing.BACK_OUT) * 0.5f;
        return VecHelper.rotatingRadialOffset(new Vec3(0.5f, height, 0.5f), distance, slot, spiritAmount, lerpSpiritSpin, 360);
    }

    public float getSpinUp(Easing easing) {
        if (warmupTimer > WARMUP_DURATION) {
            return 1;
        }
        return easing.ease(warmupTimer / WARMUP_DURATION, 0, 1, 1);
    }
}