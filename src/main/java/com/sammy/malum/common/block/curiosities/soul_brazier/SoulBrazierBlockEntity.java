package com.sammy.malum.common.block.curiosities.soul_brazier;

import com.sammy.malum.common.block.MalumBlockEntityInventory;
import com.sammy.malum.common.block.MalumSpiritBlockEntityInventory;
import com.sammy.malum.common.recipe.SoulBindingRecipe;
import com.sammy.malum.core.handlers.GeasEffectHandler;
import com.sammy.malum.core.systems.recipe.SpiritBasedRecipeInput;
import com.sammy.malum.core.systems.recipe.SpiritIngredient;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.block.*;
import com.sammy.malum.registry.common.item.ItemRegistry;
import com.sammy.malum.registry.common.recipe.RecipeTypeRegistry;
import com.sammy.malum.visual_effects.SoulBindingBrazierParticleEffects;
import com.sammy.malum.visual_effects.networked.brazier.SoulBrazierEndParticleEffect;
import com.sammy.malum.visual_effects.networked.brazier.SoulBrazierStartParticleEffect;
import com.sammy.malum.visual_effects.networked.data.ColorEffectData;
import com.sammy.malum.visual_effects.networked.data.NBTEffectData;
import com.sammy.malum.visual_effects.networked.data.PositionEffectData;
import net.minecraft.core.*;
import net.minecraft.nbt.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.crafting.SizedIngredient;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.wrapper.CombinedInvWrapper;
import org.jetbrains.annotations.NotNull;
import team.lodestar.lodestone.helpers.DamageTypeHelper;
import team.lodestar.lodestone.helpers.VecHelper;
import team.lodestar.lodestone.helpers.block.*;
import team.lodestar.lodestone.systems.blockentity.*;
import team.lodestar.lodestone.systems.easing.Easing;
import team.lodestar.lodestone.systems.recipe.LodestoneRecipeType;

import javax.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

public class SoulBrazierBlockEntity extends LodestoneBlockEntity implements IItemHandlerSupplier {

    public static final StringRepresentable.EnumCodec<BrazierState> CODEC = StringRepresentable.fromEnum(BrazierState::values);

    public enum BrazierState implements StringRepresentable {
        IDLE("idle"),
        BINDING("binding"),
        UNBINDING("unbinding");
        public final String name;

        BrazierState(String name) {
            this.name = name;
        }

        @Override
        public @NotNull String getSerializedName() {
            return name;
        }
    }

    public static final Vec3 BRAZIER_ITEM_OFFSET = new Vec3(0.5f, 1.125f, 0.5f);
    public static final Vec3 BRAZIER_GEAS_ICON_OFFSET = new Vec3(0.5f, 4f, 0.5f);
    private static final int WARMUP_DURATION = 40;
    private static final int SOULBINDING_DURATION = 600;
    public LodestoneBlockEntityInventory inventory;
    public LodestoneBlockEntityInventory spiritInventory;

    public Supplier<IItemHandler> exposedInventory = () -> new CombinedInvWrapper(inventory, spiritInventory);

    public SoulBindingRecipe recipe;
    public BrazierState state = BrazierState.IDLE;

    public float warmupTimer;
    public int progress;
    public boolean isReady;

    public List<UUID> sacrificedTargets = new ArrayList<>();

    public float extrasAmount, extrasSpin;
    public float spiritAmount, spiritSpin;

    public SoulBrazierBlockEntity(BlockEntityType<? extends SoulBrazierBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public SoulBrazierBlockEntity(BlockPos pos, BlockState state) {
        this(BlockEntityRegistry.SOUL_BRAZIER.get(), pos, state);
        inventory = MalumBlockEntityInventory.stacksNotSpirits(this, 9).onContentsChanged(this::updateRecipe);
        spiritInventory = MalumSpiritBlockEntityInventory.spiritStacks(this, SpiritTypeRegistry.SPIRITS.size()).onContentsChanged(this::updateRecipe);
    }

    @Override
    public IItemHandler getInventory(Direction direction) {
        return exposedInventory.get();
    }

    @Override
    protected void saveAdditional(CompoundTag compound, HolderLookup.Provider pRegistries) {
        inventory.save(pRegistries, compound);
        spiritInventory.save(pRegistries, compound, "spiritInventory");

        compound.putString("state", state.name);
        compound.putFloat("warmupTimer", warmupTimer);
        compound.putInt("progress", progress);

        ListTag list = new ListTag();
        for (UUID uuid : sacrificedTargets) {
            list.add(NbtUtils.createUUID(uuid));
        }
        compound.put("sacrificedTargets", list);
    }

    @Override
    public void loadAdditional(CompoundTag compound, HolderLookup.Provider pRegistries) {
        inventory.load(pRegistries, compound);
        spiritInventory.load(pRegistries, compound, "spiritInventory");

        state = compound.contains("state") ? CODEC.byName(compound.getString("state")) : BrazierState.IDLE;
        warmupTimer = compound.getFloat("warmupTimer");
        progress = compound.getInt("progress");

        sacrificedTargets.clear();
        for (Tag tag : compound.getList("sacrificedTargets", 11)) {
            sacrificedTargets.add(NbtUtils.loadUUID(tag));
        }

        if (level != null) {
            updateRecipe();
            if (level.isClientSide && isActive()) {
                BrazierSoundInstance.playSound(this);
            }
        }
        super.loadAdditional(compound, pRegistries);
    }

    @Override
    public void onBreak(@Nullable Player player) {
        inventory.dumpItems(level, worldPosition);
        spiritInventory.dumpItems(level, worldPosition);
    }

    @Override
    public ItemInteractionResult onUse(Player pPlayer, InteractionHand pHand) {
        if (!(level instanceof ServerLevel serverLevel)) {
            return ItemInteractionResult.SUCCESS;
        }
        if (attemptSoulbinding(serverLevel, pPlayer, pPlayer.getItemInHand(pHand))) {
            return ItemInteractionResult.SUCCESS;
        }
        if (isActive()) {
            if (addSacrifice(pPlayer)) {
                return ItemInteractionResult.SUCCESS;
            }
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }
        var spiritResult = spiritInventory.interact(serverLevel, pPlayer, pHand);
        if (!spiritResult.isEmpty()) {
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
        extrasAmount = Math.max(1, Mth.lerp(0.1f, extrasAmount, inventory.getFilledSlotCount() - 1));

        if (isActive()) {
            progress++;
            if (level instanceof ServerLevel serverLevel) {
                if (recipe == null) {
                    updateRecipe();
                    if (recipe == null) {
                        state = BrazierState.IDLE;
                        BlockStateHelper.updateAndNotifyState(level, worldPosition);
                        return;
                    }
                }
                if (progress > SOULBINDING_DURATION) {
                    List<LivingEntity> targets = new ArrayList<>();
                    for (UUID uuid : sacrificedTargets) {
                        if (serverLevel.getEntity(uuid) instanceof LivingEntity entity) {
                            targets.add(entity);
                        }
                    }
                    completeSoulBinding(serverLevel, targets);
                }
            }
        } else {
            sacrificedTargets.clear();
            progress = Math.max(progress-10, 0);
        }

        if (!isReady && recipe != null) {
            isReady = true;
            BlockStateHelper.updateAndNotifyState(level, worldPosition);
        } else if (isReady && recipe == null) {
            isReady = false;
            BlockStateHelper.updateAndNotifyState(level, worldPosition);
        }

        if (isReady) {
            warmupTimer++;
        } else {
            warmupTimer = Mth.clamp(warmupTimer - 1, 0, WARMUP_DURATION);
        }

        if (level.isClientSide) {
            float extraSpeed = Math.min(progress / 100f, 5) * getSpinUp(Easing.BOUNCE_IN_OUT);
            float speed = 1 + getSpinUp(Easing.SINE_IN_OUT) * 0.4f + extraSpeed;
            spiritSpin += speed;
            extrasSpin -= speed;
            SoulBindingBrazierParticleEffects.passiveBrazierParticles(this);
        }
    }

    public boolean attemptSoulbinding(ServerLevel level, @Nullable Player player, ItemStack stack) {
        if (recipe == null) {
            return false;
        }
        if (isActive()) {
            return false;
        }
        var item = stack.getItem();
        if (item.equals(ItemRegistry.ETHER.get())) {
            beginSoulbinding(level, BrazierState.BINDING);
        } else if (item.equals(ItemRegistry.PARACAUSAL_FLAME.get())) {
            beginSoulbinding(level, BrazierState.UNBINDING);
        }
        if (isIdle()) {
            return false;
        }
        if (player != null && !player.isCreative()) {
            stack.shrink(1);
        }
        return true;
    }

    public void beginSoulbinding(ServerLevel level, BrazierState newState) {
        state = newState;
        sacrificedTargets.clear();
        level.playSound(null, worldPosition, SoundRegistry.BRAZIER_START.get(), SoundSource.BLOCKS, 2f, 1.3f + level.random.nextFloat() * 0.3f);
        level.playSound(null, worldPosition, SoundRegistry.BRAZIER_START.get(), SoundSource.BLOCKS, 2f, 0.9f + level.random.nextFloat() * 0.3f);

        ParticleEffectTypeRegistry.SOULBINDING_BRAZIER_BEGINS.createEffect(worldPosition)
                .color(ColorEffectData.fromSpiritIngredients(recipe.spirits))
                .customData(SoulBrazierEndParticleEffect.createData(this))
                .spawn(level);
        level.setBlock(worldPosition, getBlockState().setValue(SoulBrazierBlock.LIT, true), 3);
        BlockStateHelper.updateAndNotifyState(level, worldPosition);
    }

    public boolean addSacrifice(LivingEntity entity) {
        UUID uuid = entity.getUUID();
        if (!sacrificedTargets.contains(uuid)) {
            sacrificedTargets.add(uuid);
            entity.hurt(DamageTypeHelper.create(level, DamageTypeRegistry.KARMIC), entity.getMaxHealth()/4f);
            level.playSound(null, worldPosition, SoundRegistry.BRAZIER_SACRIFICE.get(), SoundSource.BLOCKS, 1, 0.9f + level.random.nextFloat() * 0.2f);
            BlockStateHelper.updateAndNotifyState(level, worldPosition);
            return true;
        }
        return false;
    }

    public void completeSoulBinding(ServerLevel level, List<LivingEntity> targets) {
        sacrificedTargets.clear();
        if (recipe == null) {
            updateRecipe();
            if (recipe == null) {
                return;
            }
        }
        for (SpiritIngredient spirit : recipe.spirits) {
            for (int i = 0; i < spiritInventory.slotCount; i++) {
                ItemStack spiritStack = spiritInventory.getStackInSlot(i);
                if (spirit.test(spiritStack)) {
                    spiritStack.shrink(spirit.getCount());
                    break;
                }
            }
        }
        List<SizedIngredient> extraIngredients = new ArrayList<>(recipe.extraIngredients);
        inventory.getStackInSlot(0).shrink(recipe.ingredient.count());
        for (SizedIngredient ingredient : extraIngredients) {
            for (int i = 0; i < inventory.slotCount; i++) {
                ItemStack stack = inventory.getStackInSlot(i);
                if (ingredient.test(stack)) {
                    stack.shrink(ingredient.count());
                    break;
                }
            }
        }
        for (LivingEntity target : targets) {
            boolean success = false;

            if (state.equals(BrazierState.BINDING)) {
                success = GeasEffectHandler.addGeasEffect(target, recipe.geas);
            }
            else if (state.equals(BrazierState.UNBINDING)) {
                success = GeasEffectHandler.removeGeasEffect(target, recipe.geas);
            }
            if (!success) {
                target.hurt(DamageTypeHelper.create(level, DamageTypeRegistry.KARMIC), target.getMaxHealth() / 2f);
            }
        }
        ParticleEffectTypeRegistry.SOULBINDING_BRAZIER_ENDS.createEffect(worldPosition)
                .color(ColorEffectData.fromSpiritIngredients(recipe.spirits))
                .customData(SoulBrazierEndParticleEffect.createData(this))
                .spawn(level);
        level.playSound(null, worldPosition, SoundRegistry.BRAZIER_FINISH.get(), SoundSource.BLOCKS, 1, 0.9f + level.random.nextFloat() * 0.2f);
        state = BrazierState.IDLE;
        level.setBlock(worldPosition, getBlockState().setValue(SoulBrazierBlock.LIT, false), 3);
        updateRecipe();
        BlockStateHelper.updateAndNotifyState(level, worldPosition);
    }

    public void updateRecipe() {
        inventory.updateInventoryCaches();
        spiritInventory.updateInventoryCaches();
        recipe = LodestoneRecipeType.getRecipe(level, RecipeTypeRegistry.SOUL_BINDING.get(),
                new SpiritBasedRecipeInput(inventory.nonEmptyItemStacks, spiritInventory.nonEmptyItemStacks));
    }

    public boolean isActive() {
        return !isIdle() && recipe != null;
    }

    public boolean isIdle() {
        return state.equals(BrazierState.IDLE);
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
        if (warmupTimer >= WARMUP_DURATION) {
            return 1;
        }
        return easing.ease(warmupTimer / WARMUP_DURATION, 0, 1, 1);
    }
}