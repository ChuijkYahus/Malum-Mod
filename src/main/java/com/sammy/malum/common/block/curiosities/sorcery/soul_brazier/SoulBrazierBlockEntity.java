package com.sammy.malum.common.block.curiosities.sorcery.soul_brazier;

import com.sammy.malum.common.block.MalumBlockItemStackHandler;
import com.sammy.malum.common.recipe.SoulBindingRecipe;
import com.sammy.malum.core.handlers.GeasEffectHandler;
import com.sammy.malum.core.systems.recipe.SpiritBasedRecipeInput;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.MalumContent;
import com.sammy.malum.registry.common.block.*;
import com.sammy.malum.registry.common.recipe.MalumRecipeTypes;
import com.sammy.malum.registry.common.sound.*;
import com.sammy.malum.visual_effects.block.SoulBindingBrazierParticleEffects;
import com.sammy.malum.visual_effects.networked.brazier.*;
import com.sammy.malum.visual_effects.networked.MalumNetworkedParticleEffectColorData;
import net.minecraft.core.*;
import net.minecraft.nbt.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.*;
import net.minecraft.world.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.phys.*;
import net.neoforged.neoforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;
import team.lodestar.lodestone.helpers.DamageTypeHelper;
import team.lodestar.lodestone.helpers.VecHelper;
import team.lodestar.lodestone.helpers.block.*;
import team.lodestar.lodestone.modules.toolkit.blockentity.*;
import team.lodestar.lodestone.modules.core.easing.Easing;
import team.lodestar.lodestone.modules.toolkit.inventory.ItemStackMultiHandler;
import team.lodestar.lodestone.modules.toolkit.recipe.LodestoneRecipeSearch;

import javax.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SuppressWarnings({"deprecation", "NullableProblems"})
public class SoulBrazierBlockEntity extends LodestoneBlockEntity implements IInventoryCapabilityProvider {

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
    public MalumBlockItemStackHandler inventory;
    public MalumBlockItemStackHandler spiritInventory;
    public ItemStackMultiHandler inventoryHandler;

    public SoulBindingRecipe recipe;
    public BrazierState state = BrazierState.IDLE;

    public float warmupTimer;
    public int progress;
    public boolean isReady;

    public List<UUID> sacrificedTargets = new ArrayList<>();

    public float extrasAmount, extrasSpin;
    public float spiritAmount, spiritSpin;

    public SoulBrazierBlockEntity(LodestoneBlockEntityType<? extends SoulBrazierBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public SoulBrazierBlockEntity(BlockPos pos, BlockState state) {
        this(MalumBlockEntities.SOUL_BRAZIER.get(), pos, state);

        inventory = MalumBlockItemStackHandler.create(this, 1).noSpirits().onContentsChanged(this::updateRecipe).build();
        spiritInventory = MalumBlockItemStackHandler.create(this, 9).onlySpirits().onContentsChanged(this::updateRecipe).build();
        inventoryHandler = new ItemStackMultiHandler(inventory, spiritInventory);
    }

    @Override
    public IItemHandler getInventory(Direction direction) {
        return inventoryHandler;
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        inventory.save(registries, tag);
        spiritInventory.save(registries, tag, "spiritInventory");

        tag.putString("state", state.name);
        tag.putFloat("warmupTimer", warmupTimer);
        tag.putInt("progress", progress);
        tag.putBoolean("isReady", isReady);

        ListTag list = new ListTag();
        for (UUID target : sacrificedTargets) {
            list.add(NbtUtils.createUUID(target));
        }
        tag.put("sacrificedTargets", list);
    }

    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        inventory.load(registries, tag);
        spiritInventory.load(registries, tag, "spiritInventory");

        state = tag.contains("state") ? CODEC.byName(tag.getString("state")) : BrazierState.IDLE;
        warmupTimer = tag.getFloat("warmupTimer");
        progress = tag.getInt("progress");
        isReady = tag.getBoolean("isReady");

        sacrificedTargets.clear();
        for (Tag target : tag.getList("sacrificedTargets", 11)) {
            sacrificedTargets.add(NbtUtils.loadUUID(target));
        }

        if (level != null) {
            updateRecipe();
            if (level.isClientSide && isActive()) {
                BrazierSoundInstance.playSound(this);
            }
        }
        super.loadAdditional(tag, registries);
    }

    @Override
    public void onBreak(@Nullable Player player) {
        inventory.dumpItems(level, worldPosition);
        spiritInventory.dumpItems(level, worldPosition);
    }

    @Override
    public ItemInteractionResult onUse(Player player, InteractionHand hand) {
        if (!(level instanceof ServerLevel serverLevel)) {
            return ItemInteractionResult.SUCCESS;
        }
        if (attemptSoulbinding(serverLevel, player, player.getItemInHand(hand))) {
            return ItemInteractionResult.SUCCESS;
        }
        if (isActive()) {
            if (addSacrifice(serverLevel, player)) {
                return ItemInteractionResult.SUCCESS;
            }
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }
        if (inventoryHandler.interact(serverLevel, player, hand)) {
            return ItemInteractionResult.SUCCESS;
        }
        return super.onUse(player, hand);
    }

    @Override
    public void clientTick(Level level) {
        if (isActive()) {
            progress++;
        }
        spiritAmount = Math.max(1, Mth.lerp(0.1f, spiritAmount, spiritInventory.getFilledSlotCount()));
        extrasAmount = Math.max(1, Mth.lerp(0.1f, extrasAmount, inventory.getFilledSlotCount() - 1));
        float extraSpeed = Math.min(progress / 100f, 5) * getSpinUp(Easing.BOUNCE_IN_OUT);
        float speed = 1 + getSpinUp(Easing.SINE_IN_OUT) * 0.4f + extraSpeed;
        spiritSpin += speed;
        extrasSpin -= speed;
        SoulBindingBrazierParticleEffects.passiveBrazierParticles(this);
    }

    @Override
    public void commonTick(Level level) {
        if (isReady) {
            warmupTimer++;
        } else {
            warmupTimer = Mth.clamp(warmupTimer - 1, 0, WARMUP_DURATION);
        }
    }

    @Override
    public void serverTick(ServerLevel level) {
        if ((!isReady && recipe != null) || (isReady && recipe == null)) {
            isReady = !isReady;
            BlockStateHelper.updateState(level, worldPosition);
            setDirty();
        }
        if (isActive()) {
            progress++;
            if (recipe == null) {
                updateRecipe();
                if (recipe == null) {
                    state = BrazierState.IDLE;
                    setDirty();
                    return;
                }
            }
            if (progress % 20 == 0) {
                AABB radius = new AABB(worldPosition).inflate(4);
                List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, radius);
                for (LivingEntity entity : entities) {
                    if (entity.hasEffect(MobEffects.WEAKNESS)) {
                        addSacrifice(level, entity);
                    }
                }
            }

            if (progress > SOULBINDING_DURATION) {
                List<LivingEntity> targets = new ArrayList<>();
                for (UUID uuid : sacrificedTargets) {
                    if (level.getEntity(uuid) instanceof LivingEntity entity) {
                        targets.add(entity);
                    }
                }
                completeSoulBinding(level, targets);
            }
        } else {
            sacrificedTargets.clear();
            progress = Math.max(progress - 10, 0);
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
        if (MalumContent.BlockSets.ETHER.is(item)) {
            beginSoulbinding(level, BrazierState.BINDING);
        } else if (item.equals(MalumContent.Materials.PARACAUSAL_FLAME.get())) {
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
        level.playSound(null, worldPosition, MalumSoundEvents.BRAZIER_START.get(), SoundSource.BLOCKS, 2f, 1.3f + level.random.nextFloat() * 0.3f);
        level.playSound(null, worldPosition, MalumSoundEvents.BRAZIER_START.get(), SoundSource.BLOCKS, 2f, 0.9f + level.random.nextFloat() * 0.3f);

        MalumParticleEffectTypes.SOULBINDING_BRAZIER_BEGINS.createEffect(worldPosition)
                .color(MalumNetworkedParticleEffectColorData.fromSpirits(recipe.spirits))
                .customData(new SoulBrazierStateEffectData(state))
                .spawn(level);
        level.setBlock(worldPosition, getBlockState().setValue(SoulBrazierBlock.LIT, true), 3);
        setDirty();
    }

    public boolean addSacrifice(ServerLevel level, LivingEntity entity) {
        UUID uuid = entity.getUUID();
        if (!sacrificedTargets.contains(uuid)) {
            sacrificedTargets.add(uuid);
            entity.hurt(DamageTypeHelper.create(level, MalumDamageTypes.KARMIC), entity.getMaxHealth()/4f);
            level.playSound(null, worldPosition, MalumSoundEvents.BRAZIER_SACRIFICE.get(), SoundSource.BLOCKS, 1, 0.9f + level.random.nextFloat() * 0.2f);

            MalumParticleEffectTypes.SOULBINDING_BRAZIER_ACCEPTS_SACRIFICE.createEffect(worldPosition)
                    .color(MalumNetworkedParticleEffectColorData.fromSpirits(recipe.spirits))
                    .customData(new SoulBrazierAcceptSacrificeParticleEffect.SoulBrazierEntityEffectData(entity.getId()))
                    .spawn(level);

            setDirty();
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
        inventory.getStackInSlot(0).shrink(recipe.input.count());
        spiritInventory.spendSpiritsOnRecipe(recipe.spirits);
        inventory.spendItemsOnRecipe(recipe.extraInputs);
        for (LivingEntity target : targets) {
            boolean success = false;

            if (state.equals(BrazierState.BINDING)) {
                success = GeasEffectHandler.addGeasEffect(target, recipe.result);
            }
            else if (state.equals(BrazierState.UNBINDING)) {
                success = GeasEffectHandler.removeGeasEffect(target, recipe.result);
            }
            if (!success) {
                target.hurt(DamageTypeHelper.create(level, MalumDamageTypes.KARMIC), target.getMaxHealth() / 2f);
            }
        }
        MalumParticleEffectTypes.SOULBINDING_BRAZIER_ENDS.createEffect(worldPosition)
                .color(MalumNetworkedParticleEffectColorData.fromSpirits(recipe.spirits))
                .customData(new SoulBrazierStateEffectData(state))
                .spawn(level);
        level.playSound(null, worldPosition, MalumSoundEvents.BRAZIER_FINISH.get(), SoundSource.BLOCKS, 1, 0.9f + level.random.nextFloat() * 0.2f);
        level.setBlock(worldPosition, getBlockState().setValue(SoulBrazierBlock.LIT, false), 3);
        state = BrazierState.IDLE;
        updateRecipe();
        setDirty();
    }

    public void updateRecipe() {
        inventory.updateCaches();
        spiritInventory.updateCaches();
        var input = new SpiritBasedRecipeInput(inventory.getNonEmptyStacks(), spiritInventory.getNonEmptyStacks());
        recipe = LodestoneRecipeSearch.search(level, MalumRecipeTypes.SOUL_BINDING::get).findRecipe(input);
    }

    public boolean isActive() {
        return !state.equals(BrazierState.IDLE) && recipe != null;
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
        return easing.ease(warmupTimer / (float)WARMUP_DURATION);
    }
}