package com.sammy.malum.common.block.curiosities.weeping_well;

import com.sammy.malum.common.packets.CodecUtil;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.block.*;
import com.sammy.malum.registry.common.item.*;
import com.sammy.malum.registry.common.recipe.RecipeTypeRegistry;
import com.sammy.malum.visual_effects.*;
import net.minecraft.core.*;
import net.minecraft.nbt.*;
import net.minecraft.server.level.*;
import net.minecraft.sounds.*;
import net.minecraft.util.*;
import net.minecraft.world.entity.item.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.phys.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.helpers.block.*;
import team.lodestar.lodestone.systems.blockentity.*;
import team.lodestar.lodestone.systems.recipe.*;

import java.util.*;

public class VoidConduitBlockEntity extends LodestoneBlockEntity {


    private static final int PROCESSING_TIME = 80;

    public final List<ItemStack> eatenItems = new ArrayList<>();
    public int progress;
    public int streak;
    public boolean reachedStreakGoal;
    public int lingeringRadiance;

    public VoidConduitBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.VOID_CONDUIT.get(), pos, state);
    }

    @Override
    protected void saveAdditional(CompoundTag compound, HolderLookup.Provider registries) {
        if (!eatenItems.isEmpty()) {
            compound.putInt("itemCount", eatenItems.size());
            for (int i = 0; i < eatenItems.size(); i++) {
                ItemStack stack = eatenItems.get(i);
                CompoundTag itemTag = (CompoundTag) CodecUtil.encodeNBT(ItemStack.CODEC, stack);
                compound.put("item_" + i, itemTag);
            }
        }
        compound.putInt("progress", progress);
        compound.putInt("streak", streak);
        compound.putBoolean("reachedStreakGoal", reachedStreakGoal);
        compound.putInt("lingeringRadiance", lingeringRadiance);
        super.saveAdditional(compound, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag compound, HolderLookup.Provider pRegistries) {
        eatenItems.clear();
        for (int i = 0; i < compound.getInt("itemCount"); i++) {
            CompoundTag itemTag = compound.getCompound("item_" + i);
            eatenItems.add(CodecUtil.decodeNBT(ItemStack.CODEC, itemTag));
        }
        progress = compound.getInt("progress");
        streak = compound.getInt("streak");
        reachedStreakGoal = compound.getBoolean("reachedStreakGoal");
        lingeringRadiance = compound.getInt("lingeringRadiance");
    }

    @Override
    public void tick() {
        super.tick();
        if (lingeringRadiance > 0) {
            lingeringRadiance--;
        }
        if (level instanceof ServerLevel serverLevel) {
            final long gameTime = serverLevel.getGameTime();
            if (gameTime % 100L == 0) {
                level.playSound(null, worldPosition, SoundRegistry.UNCANNY_VALLEY.get(), SoundSource.HOSTILE, 1f, Mth.nextFloat(level.getRandom(), 0.55f, 1.75f));
            }
            if (gameTime % 20L == 0) {
                level.playSound(null, worldPosition, SoundRegistry.VOID_HEARTBEAT.get(), SoundSource.HOSTILE, 1.5f, Mth.nextFloat(level.getRandom(), 0.95f, 1.15f));
                acceptItems(serverLevel);
            }
            if (!eatenItems.isEmpty()) {
                progress++;
                if (progress >= PROCESSING_TIME) {
                    processItem(serverLevel);
                }
                if (eatenItems.isEmpty()) {
                    progress = 0;
                }
            } else if (streak != 0) {
                streak = 0;
            }
        } else {
            if (lingeringRadiance <= 100) {
                WeepingWellParticleEffects.passiveWeepingWellParticles(this);
            } else {
                RadiantParticleEffects.radiantWeepingWellParticles(this);
            }
        }
    }

    public void acceptItems(ServerLevel serverLevel) {
        AABB aabb = new AABB(worldPosition).inflate(1, 2, 1).move(0, -2, 0);
        List<ItemEntity> items = serverLevel.getEntitiesOfClass(ItemEntity.class, aabb).stream().sorted(Comparator.comparingInt(ItemEntity::getAge)).toList();
        for (ItemEntity entity : items) {
            eatenItems.add(entity.getItem());
            entity.discard();
        }
        BlockStateHelper.updateAndNotifyState(level, worldPosition);
    }

    public void processItem(ServerLevel serverLevel) {
        var particleEffectType = ParticleEffectTypeRegistry.WEEPING_WELL_REACTS;
        var stack = eatenItems.getLast();
        if (stack.getItem().equals(ItemRegistry.BLIGHTED_GUNK.get())) {
            eatGunk(stack);
        } else {
            Item result = spitOutItem(stack);
            if (result.equals(ItemRegistry.FUSED_CONSCIOUSNESS.get())) {
                lingeringRadiance = 400;
                particleEffectType = ParticleEffectTypeRegistry.WEEPING_WELL_EMITS_RADIANCE;
            }
        }
        progress = PROCESSING_TIME-20;
        eatenItems.removeLast();

        particleEffectType.createEffect(worldPosition.getCenter()).spawn(serverLevel);
        BlockStateHelper.updateAndNotifyState(level, worldPosition);
    }

    public void eatGunk(ItemStack stack) {
        streak += stack.getCount();
        if (streak >= 4096) {
            reachedStreakGoal = true;
        }
        level.playSound(null, worldPosition, SoundRegistry.VOID_EATS_GUNK.get(), SoundSource.PLAYERS, 0.7f, RandomHelper.randomBetween(level.getRandom(), 0.5f, 2f));
        level.playSound(null, worldPosition, SoundEvents.GENERIC_EAT, SoundSource.PLAYERS, 0.7f, RandomHelper.randomBetween(level.getRandom(), 0.5f, 2f));
    }

    public Item spitOutItem(ItemStack stack) {
        var recipe = LodestoneRecipeType.getRecipe(level, RecipeTypeRegistry.VOID_FAVOR.get(), new SingleRecipeInput(stack));
        float pitch = RandomHelper.randomBetween(level.getRandom(), 0.8f, 1.3f);
        var outputPosition = worldPosition.getCenter();
        var sound = SoundRegistry.VOID_REJECTION.get();
        var outputStack = stack.copy();
        if (recipe != null) {
            outputStack = recipe.output.copyWithCount(stack.getCount());
            sound = SoundRegistry.VOID_TRANSMUTATION.get();
        }
        ItemEntity entity = new ItemEntity(level, outputPosition.x, outputPosition.y, outputPosition.z, outputStack);
        entity.setDeltaMovement(0, 0.65f, 0.15f);
        level.addFreshEntity(entity);
        level.playSound(null, worldPosition, sound, SoundSource.HOSTILE, 2f, pitch);
        return outputStack.getItem();
    }
}