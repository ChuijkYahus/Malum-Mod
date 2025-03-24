package com.sammy.malum.common.block.curiosities.soul_brazier;

import com.sammy.malum.common.block.MalumBlockEntityInventory;
import com.sammy.malum.common.block.MalumSpiritBlockEntityInventory;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.block.*;
import net.minecraft.core.*;
import net.minecraft.nbt.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.wrapper.CombinedInvWrapper;
import team.lodestar.lodestone.helpers.VecHelper;
import team.lodestar.lodestone.helpers.block.*;
import team.lodestar.lodestone.systems.blockentity.*;
import team.lodestar.lodestone.systems.easing.Easing;

import javax.annotation.*;
import java.util.function.Supplier;

public class SoulBrazierBlockEntity extends LodestoneBlockEntity implements IItemHandlerSupplier {

    public static final Vec3 BRAZIER_ITEM_OFFSET = new Vec3(0.5f, 1.125f, 0.5f);
    private static final int WARMUP_DURATION = 40;
    public LodestoneBlockEntityInventory inventory;
    public LodestoneBlockEntityInventory extrasInventory;
    public LodestoneBlockEntityInventory spiritInventory;

    public Supplier<IItemHandler> exposedInventory = () -> new CombinedInvWrapper(inventory, extrasInventory, spiritInventory);


    public float extrasAmount;
    public float extrasSpin;

    public float spiritAmount;
    public float spiritSpin;

    public boolean isCrafting;
    public float timeActive;

    public SoulBrazierBlockEntity(BlockEntityType<? extends SoulBrazierBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public SoulBrazierBlockEntity(BlockPos pos, BlockState state) {
        this(BlockEntityRegistry.SOUL_BRAZIER.get(), pos, state);
        inventory = MalumBlockEntityInventory.singleStackNotSpirit(this);//.onContentsChanged(this::recalculateRecipes);
        extrasInventory = MalumBlockEntityInventory.stacksNotSpirits(this, 8);
        spiritInventory = MalumSpiritBlockEntityInventory.spiritStacks(this, SpiritTypeRegistry.SPIRITS.size());//.onContentsChanged(this::recalculateRecipes);
    }

    @Override
    public IItemHandler getInventory(Direction direction) {
        return exposedInventory.get();
    }

    @Override
    protected void saveAdditional(CompoundTag compound, HolderLookup.Provider pRegistries) {
        inventory.save(pRegistries, compound);
        spiritInventory.save(pRegistries, compound, "spiritInventory");
        extrasInventory.save(pRegistries, compound, "extrasInventory");
        compound.putFloat("timeActive", timeActive);
    }

    @Override
    public void loadAdditional(CompoundTag compound, HolderLookup.Provider pRegistries) {
        inventory.load(pRegistries, compound);
        spiritInventory.load(pRegistries, compound, "spiritInventory");
        extrasInventory.load(pRegistries, compound, "extrasInventory");
        timeActive = compound.getFloat("timeActive");
        super.loadAdditional(compound, pRegistries);
    }

    @Override
    public void onBreak(@Nullable Player player) {
        inventory.dumpItems(level, worldPosition);
        spiritInventory.dumpItems(level, worldPosition);
        extrasInventory.dumpItems(level, worldPosition);
    }

    @Override
    public ItemInteractionResult onUse(Player pPlayer, InteractionHand pHand) {
        if (!(level instanceof ServerLevel serverLevel)) {
            return ItemInteractionResult.CONSUME;
        }
        var spiritResult = spiritInventory.interact(serverLevel, pPlayer, pHand);
        if (!spiritResult.isEmpty()) {
            return ItemInteractionResult.SUCCESS;
        }
        var extraResult = extrasInventory.interact(serverLevel, pPlayer, pHand);
        if (!extraResult.isEmpty()) {
            return ItemInteractionResult.SUCCESS;
        }
        var result = inventory.interact(serverLevel, pPlayer, pHand);
        if (!result.isEmpty()) {
            return ItemInteractionResult.SUCCESS;
        }
        return super.onUse(pPlayer, pHand);
    }

    @Override
    public void tick() {
        super.tick();
        spiritAmount = Math.max(1, Mth.lerp(0.1f, spiritAmount, spiritInventory.getFilledSlotCount()));
        extrasAmount = Math.max(1, Mth.lerp(0.1f, extrasAmount, extrasInventory.getFilledSlotCount()));

        if (isCrafting) {
            timeActive++;
        }
        else {
            timeActive = Mth.clamp(timeActive-1, 0, WARMUP_DURATION);
        }
        if (level.isClientSide) {
            float speed = 1 + getSpinUp(Easing.SINE_IN_OUT) * 0.05f;
            spiritSpin += speed;
            extrasSpin -= speed;
        }

        if (level.getGameTime() % 100L == 0) {
            isCrafting = !isCrafting;
        }
    }

    public Vec3 getItemPos() {
        return BlockPosHelper.fromBlockPos(getBlockPos()).add(BRAZIER_ITEM_OFFSET);
    }

    public Vec3 getSpiritOffset(int slot, float partialTicks) {
        float projectedSpin = spiritSpin + 1 + getSpinUp(Easing.SINE_IN_OUT) * 0.05f;
        float spinLerp = spiritSpin + partialTicks * (projectedSpin - spiritSpin);
        float distance = 1.25f - getSpinUp(Easing.SINE_OUT) * 0.25f + Mth.sin((spinLerp / 20f) % 6.28f) * 0.025f;
        float height = 1f + getSpinUp(Easing.QUARTIC_OUT) * getSpinUp(Easing.BACK_OUT) * 0.9f;
        return VecHelper.rotatingRadialOffset(new Vec3(0.5f, height, 0.5f), distance, slot, spiritAmount, spinLerp, 360);
    }

    public Vec3 getExtrasOffset(int slot, float partialTicks) {
        float projectedSpin = extrasSpin - 1 - getSpinUp(Easing.SINE_IN_OUT) * 0.05f;
        float spinLerp = extrasSpin + partialTicks * (projectedSpin - extrasSpin);
        float distance = 1.1f - getSpinUp(Easing.SINE_OUT) * 0.25f + Mth.sin((spinLerp / 20f) % 6.28f) * 0.025f;
        float height = 0.8f + getSpinUp(Easing.QUARTIC_OUT) * getSpinUp(Easing.BACK_OUT) * 0.7f;
        return VecHelper.rotatingRadialOffset(new Vec3(0.5f, height, 0.5f), distance, slot, extrasAmount, spinLerp, 360);
    }

    public float getSpinUp(Easing easing) {
        if (timeActive >= WARMUP_DURATION) {
            return 1;
        }
        return easing.ease(timeActive / WARMUP_DURATION, 0, 1, 1);
    }
}