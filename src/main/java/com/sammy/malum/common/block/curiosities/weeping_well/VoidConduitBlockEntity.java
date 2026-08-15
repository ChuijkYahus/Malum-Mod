package com.sammy.malum.common.block.curiosities.weeping_well;

import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.MalumContent;
import com.sammy.malum.registry.common.block.*;
import com.sammy.malum.registry.common.recipe.MalumRecipeTypes;
import com.sammy.malum.registry.common.sound.*;
import com.sammy.malum.visual_effects.*;
import net.minecraft.core.*;
import net.minecraft.nbt.*;
import net.minecraft.server.level.*;
import net.minecraft.sounds.*;
import net.minecraft.util.*;
import net.minecraft.world.entity.item.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.phys.*;
import team.lodestar.lodestone.helpers.block.*;
import team.lodestar.lodestone.modules.core.easing.Easing;
import team.lodestar.lodestone.modules.toolkit.blockentity.*;
import team.lodestar.lodestone.modules.toolkit.recipe.LodestoneRecipeSearch;

import java.util.*;

public class VoidConduitBlockEntity extends LodestoneBlockEntity {


    private static final int PROCESSING_TIME = 80;

    public final List<ItemStack> eatenItems = new ArrayList<>();
    public int progress;

    public VoidConduitBlockEntity(BlockPos pos, BlockState state) {
        super(MalumBlockEntities.VOID_CONDUIT.get(), pos, state);
    }

    @Override
    protected void saveAdditional(CompoundTag compound, HolderLookup.Provider registries) {
        if (!eatenItems.isEmpty()) {
            compound.putInt("itemCount", eatenItems.size());
            for (int i = 0; i < eatenItems.size(); i++) {
                ItemStack stack = eatenItems.get(i);
                CompoundTag itemTag = new CompoundTag();
                stack.save(registries, itemTag);
                compound.put("item_" + i, itemTag);
            }
        }
        compound.putInt("progress", progress);
        super.saveAdditional(compound, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag compound, HolderLookup.Provider pRegistries) {
        eatenItems.clear();
        for (int i = 0; i < compound.getInt("itemCount"); i++) {
            CompoundTag itemTag = compound.getCompound("item_" + i);
            ItemStack.parse(pRegistries, itemTag).ifPresent(eatenItems::add);
        }
        progress = compound.getInt("progress");
    }

    @Override
    public void serverTick(ServerLevel level) {
        long gameTime = level.getGameTime();
        if (gameTime % 100L == 0) {
            level.playSound(null, worldPosition, MalumSoundEvents.UNCANNY_VALLEY.get(), SoundSource.HOSTILE, 1f, Mth.nextFloat(level.getRandom(), 0.55f, 1.75f));
        }
        if (gameTime % 20L == 0) {
            level.playSound(null, worldPosition, MalumSoundEvents.VOID_HEARTBEAT.get(), SoundSource.HOSTILE, 1.5f, Mth.nextFloat(level.getRandom(), 0.95f, 1.15f));
        }
        if (gameTime % 5L == 0) {
            acceptItems(level);
        }
        if (!eatenItems.isEmpty()) {
            progress++;
            if (progress >= PROCESSING_TIME) {
                processItem(level);
            }
            if (eatenItems.isEmpty()) {
                progress = 0;
            }
        }
    }

    @Override
    public void clientTick(Level level) {
        WeepingWellParticleEffects.passiveWeepingWellParticles(this);
    }

    public void acceptItems(ServerLevel serverLevel) {
        AABB aabb = new AABB(worldPosition).inflate(1, 3, 1).move(0, -3, 0);
        List<ItemEntity> items = serverLevel.getEntitiesOfClass(ItemEntity.class, aabb).stream().sorted(Comparator.comparingInt(ItemEntity::getAge)).toList();
        for (ItemEntity entity : items) {
            if (entity.getInBlockState().getBlock() instanceof PrimordialSoupBlock) {
                eatenItems.add(entity.getItem());
                entity.discard();
            }
        }
        BlockStateHelper.updateAndNotifyState(level, worldPosition);
    }

    public void processItem(ServerLevel serverLevel) {
        var particleEffectType = MalumParticleEffectTypes.WEEPING_WELL_REACTS;
        var stack = eatenItems.getLast();

        spitOutItem(stack);

        progress = PROCESSING_TIME-20;
        eatenItems.removeLast();

        particleEffectType.createEffect(worldPosition.getCenter()).spawn(serverLevel);
        setDirty();
    }

    public void spitOutItem(ItemStack stack) {
        var input = new SingleRecipeInput(stack);
        var recipe = LodestoneRecipeSearch.search(level, MalumRecipeTypes.VOID_FAVOR::get).findRecipe(input);
        float pitch = Easing.SINE_IN_OUT.asWeighedRandom(level.getRandom(), 0.8f, 1.3f);
        var outputPosition = worldPosition.getCenter();
        var sound = MalumSoundEvents.VOID_REJECTION.get();
        var outputStack = stack.copy();
        if (recipe != null) {
            outputStack = recipe.result.copyWithCount(stack.getCount());
            sound = MalumSoundEvents.VOID_TRANSMUTATION.get();
        }
        ItemEntity entity = new ItemEntity(level, outputPosition.x, outputPosition.y, outputPosition.z, outputStack);
        entity.setDeltaMovement(0, 0.65f, 0.15f);
        level.addFreshEntity(entity);
        level.playSound(null, worldPosition, sound, SoundSource.HOSTILE, 2f, pitch);
    }
}