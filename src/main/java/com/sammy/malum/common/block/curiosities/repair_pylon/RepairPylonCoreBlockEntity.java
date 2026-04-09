package com.sammy.malum.common.block.curiosities.repair_pylon;

import com.sammy.malum.common.block.*;
import com.sammy.malum.common.block.storage.*;
import com.sammy.malum.common.recipe.spirit_repair.*;
import com.sammy.malum.core.systems.recipe.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.content.MalumContent;
import com.sammy.malum.registry.common.content.block.*;
import com.sammy.malum.registry.common.recipe.MalumRecipeTypes;
import com.sammy.malum.registry.common.sound.*;
import com.sammy.malum.visual_effects.*;
import com.sammy.malum.visual_effects.networked.MalumNetworkedParticleEffectColorData;
import com.sammy.malum.visual_effects.networked.repair_pylon.*;
import net.minecraft.core.*;
import net.minecraft.nbt.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.*;
import net.minecraft.util.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.phys.*;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.wrapper.CombinedInvWrapper;
import org.jetbrains.annotations.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.helpers.block.*;
import team.lodestar.lodestone.modules.toolkit.blockentity.*;
import team.lodestar.lodestone.modules.toolkit.inventory.ItemStackMultiHandler;
import team.lodestar.lodestone.modules.toolkit.multiblock.*;
import team.lodestar.lodestone.modules.toolkit.recipe.LodestoneRecipeSearch;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.function.*;

@SuppressWarnings("deprecation")
public class RepairPylonCoreBlockEntity extends MultiBlockCoreEntity implements IInventoryCapabilityProvider {

    private static final Vec3 PYLON_ITEM_OFFSET = new Vec3(0.5f, 2.5f, 0.5f);
    private static final int HORIZONTAL_RANGE = 6;
    private static final int VERTICAL_RANGE = 4;

    public static final Supplier<MultiBlockStructure> STRUCTURE = () -> (MultiBlockStructure.of(
            new MultiBlockStructure.StructurePiece(0, 1, 0, MalumContent.Progression.REPAIR_PYLON_COMPONENT.get().defaultBlockState()),
            new MultiBlockStructure.StructurePiece(0, 2, 0, MalumContent.Progression.REPAIR_PYLON_COMPONENT.get().defaultBlockState().setValue(RepairPylonComponentBlock.TOP, true))));

    public static final StringRepresentable.EnumCodec<RepairPylonState> CODEC = StringRepresentable.fromEnum(RepairPylonState::values);

    public enum RepairPylonState implements StringRepresentable {
        IDLE("idle"),
        SEARCHING("searching"),
        CHARGING("active"),
        REPAIRING("repairing");
        final String name;

        RepairPylonState(String name) {
            this.name = name;
        }

        @Override
        public @NotNull String getSerializedName() {
            return name;
        }
    }

    public MalumBlockItemStackHandler inventory;
    public MalumBlockItemStackHandler spiritInventory;
    public ItemStackMultiHandler inventoryHandler;

    public SpiritRepairRecipe recipe;
    public RepairPylonState state = RepairPylonState.IDLE;
    public BlockPos repairTargetPosition;
    public int timer;

    public float spiritAmount;
    public float spiritSpin;

    private final Supplier<IItemHandler> exposedInventory = () -> new CombinedInvWrapper(inventory, spiritInventory);

    public RepairPylonCoreBlockEntity(LodestoneBlockEntityType<? extends RepairPylonCoreBlockEntity> type, MultiBlockStructure structure, BlockPos pos, BlockState state) {
        super(type, structure, pos, state);
        inventory = MalumBlockItemStackHandler.create(this, 1).noSpirits().onContentsChanged(this::updateRecipe).build();
        spiritInventory = MalumBlockItemStackHandler.create(this, 6).onlySpirits().onContentsChanged(this::updateRecipe).build();
        inventoryHandler = new ItemStackMultiHandler(inventory, spiritInventory);
    }

    public RepairPylonCoreBlockEntity(BlockPos pos, BlockState state) {
        this(MalumBlockEntities.REPAIR_PYLON.get(), STRUCTURE.get(), pos, state);
    }

    @Override
    public IItemHandler getInventory(Direction direction) {
        return exposedInventory.get();
    }

    @Override
    protected void saveAdditional(CompoundTag compound, @NotNull HolderLookup.Provider pRegistries) {
        compound.putString("state", state.name);
        if (spiritAmount != 0) {
            compound.putFloat("spiritAmount", spiritAmount);
        }
        if (repairTargetPosition != null) {
            compound.put("targetedBlock", NBTHelper.saveBlockPos(repairTargetPosition));
        }
        if (timer != 0) {
            compound.putInt("timer", timer);
        }
        inventory.save(pRegistries, compound);
        spiritInventory.save(pRegistries, compound, "spiritInventory");
    }

    @Override
    public void loadAdditional(CompoundTag compound, HolderLookup.Provider pRegistries) {
        state = compound.contains("state") ? CODEC.byName(compound.getString("state")) : RepairPylonState.IDLE;
        spiritAmount = compound.getFloat("spiritAmount");
        if (compound.contains("targetedBlock")) {
            repairTargetPosition = NBTHelper.readBlockPos(compound.getCompound("targetedBlock"));
        }
        timer = compound.getInt("timer");
        inventory.load(pRegistries, compound);
        spiritInventory.load(pRegistries, compound, "spiritInventory");
        super.loadAdditional(compound, pRegistries);
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
    public void onBreak(@Nullable Player player) {
        inventory.dumpItems(level, worldPosition);
        spiritInventory.dumpItems(level, worldPosition);
        super.onBreak(player);
    }

    @Override
    public void commonTick(Level level) {
        spiritAmount = Math.max(1, Mth.lerp(0.1f, spiritAmount, spiritInventory.getFilledSlotCount()));
    }

    @Override
    public void clientTick(Level level) {
        spiritSpin++;
        IMalumSpecialItemAccessPoint target = null;
        if (repairTargetPosition != null && level.getBlockEntity(repairTargetPosition) instanceof IMalumSpecialItemAccessPoint accessPoint) {
            target = accessPoint;
        }
        RepairPylonParticleEffects.passiveRepairPylonParticles(this, target);
    }

    @Override
    public void serverTick(ServerLevel level) {
        if (!state.equals(RepairPylonState.IDLE)) {
            if (recipe == null) {
                setState(RepairPylonState.IDLE);
                return;
            }
        }
        switch (state) {
            case IDLE -> {
                if (recipe != null) {
                    setState(RepairPylonState.SEARCHING);
                }
            }
            case SEARCHING -> {
                timer++;
                if (timer >= 40) {
                    boolean success = searchForRepairTarget();
                    if (success) {
                        setState(RepairPylonState.CHARGING);
                    } else {
                        timer = 0;
                    }
                }
            }
            case CHARGING -> {
                timer++;
                if (timer >= 600) {
                    getRepairAccessPoint(level).ifPresent(p -> beginRepair(level, p));
                }
            }
            case REPAIRING -> {
                timer++;
                if (timer >= 40) {
                    getRepairAccessPoint(level).ifPresent(p -> completeRepair(level, p));
                }
            }
        }
    }

    public boolean searchForRepairTarget() {
        var pylonProviders = BlockEntityHelper.getBlockEntities(IMalumSpecialItemAccessPoint.class, level, worldPosition, HORIZONTAL_RANGE, VERTICAL_RANGE, HORIZONTAL_RANGE);
        for (IMalumSpecialItemAccessPoint provider : pylonProviders) {
            boolean success = isRepairTargetValid(provider);
            if (success) {
                repairTargetPosition = provider.getAccessPointBlockPos();
                return true;
            }
        }
        return false;
    }

    public boolean isRepairTargetValid(IMalumSpecialItemAccessPoint provider) {
        var suppliedInventory = provider.getSuppliedInventory();
        var repairTarget = suppliedInventory.getStackInSlot(0);
        if (repairTarget.isRepairable() && !repairTarget.isDamaged()) {
            return false;
        }
        var input = getRecipeInput();
        return recipe.canApply(input, repairTarget);
    }

    public Optional<IMalumSpecialItemAccessPoint> getRepairAccessPoint(ServerLevel level) {
        if (repairTargetPosition == null) {
            setState(RepairPylonState.IDLE);
            return Optional.empty();
        }
        if (!(level.getBlockEntity(repairTargetPosition) instanceof IMalumSpecialItemAccessPoint provider) || !isRepairTargetValid(provider)) {
            setState(RepairPylonState.IDLE);
            return Optional.empty();
        }
        return Optional.of(provider);
    }

    public void beginRepair(ServerLevel level, IMalumSpecialItemAccessPoint provider) {
        MalumParticleEffectTypes.REPAIR_PYLON_PREPARES
                .createEffect(worldPosition)
                .color(MalumNetworkedParticleEffectColorData.fromSpirits(recipe.spirits))
                .customData(new PylonEffectData(provider.getAccessPointBlockPos()))
                .spawn(level);
        level.playSound(null, worldPosition, MalumSoundEvents.REPAIR_PYLON_REPAIR_START.get(), SoundSource.BLOCKS, 1.0f, 0.8f);
        setState(RepairPylonState.REPAIRING);
    }

    public void completeRepair(ServerLevel level, IMalumSpecialItemAccessPoint provider) {
        var suppliedInventory = provider.getSuppliedInventory();
        var repairTarget = suppliedInventory.getStackInSlot(0);
        var repairMaterial = inventory.getStackInSlot(0);
        repairMaterial.shrink(recipe.repairMaterial.count());
        spiritInventory.spendSpiritsOnRecipe(recipe.spirits);
        var result = recipe.getResultItem(repairTarget);
        suppliedInventory.setStackInSlot(0, result);
        MalumParticleEffectTypes.REPAIR_PYLON_REPAIRS
                .createEffect(worldPosition)
                .color(MalumNetworkedParticleEffectColorData.fromSpirits(recipe.spirits))
                .customData(new PylonEffectData(provider.getAccessPointBlockPos()))
                .spawn(level);
        level.playSound(null, worldPosition, MalumSoundEvents.REPAIR_PYLON_REPAIR_FINISH.get(), SoundSource.BLOCKS, 1.0f, 0.8f);
        setState(RepairPylonState.IDLE);
    }

    public void setState(RepairPylonState state) {
        this.state = state;
        this.timer = state.equals(RepairPylonState.SEARCHING) ? 100 : 0;
        setDirty();
    }

    public void updateRecipe() {
        var input = new SpiritBasedRecipeInput(inventory.getStackInSlot(0), spiritInventory.getNonEmptyStacks());
        recipe = LodestoneRecipeSearch.search(level, MalumRecipeTypes.SPIRIT_REPAIR::get).findRecipe(input);
    }

    public SpiritBasedRecipeInput getRecipeInput() {
        return new SpiritBasedRecipeInput(inventory.getStackInSlot(0), spiritInventory.getNonEmptyStacks());
    }

    public Vec3 getItemPos() {
        final BlockPos blockPos = getBlockPos();
        final Vec3 offset = getCentralItemOffset();
        return new Vec3(blockPos.getX()+offset.x, blockPos.getY()+offset.y, blockPos.getZ()+offset.z);
    }

    public Vec3 getCentralItemOffset() {
        return PYLON_ITEM_OFFSET;
    }

    public Vec3 getSpiritItemOffset(int slot, float partialTicks) {
        float distance = 0.75f + (float) Math.sin(((spiritSpin + partialTicks) % 6.28f) / 20f) * 0.025f;
        float height = 2.75f;
        return VecHelper.rotatingRadialOffset(new Vec3(0.5f, height, 0.5f), distance, slot, spiritAmount, spiritSpin + partialTicks, 360);
    }
}