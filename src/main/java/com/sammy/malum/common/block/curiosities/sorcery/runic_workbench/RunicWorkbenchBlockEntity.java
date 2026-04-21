package com.sammy.malum.common.block.curiosities.sorcery.runic_workbench;

import com.sammy.malum.common.block.storage.*;
import com.sammy.malum.common.block.storage.pedestal.*;
import com.sammy.malum.common.item.spirit.*;
import com.sammy.malum.common.recipe.RuneworkingRecipe.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.block.*;
import com.sammy.malum.registry.common.recipe.MalumRecipeTypes;
import com.sammy.malum.visual_effects.networked.runic_workbench.*;
import net.minecraft.core.*;
import net.minecraft.nbt.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.*;
import net.minecraft.world.entity.item.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.phys.*;
import team.lodestar.lodestone.modules.core.easing.Easing;
import team.lodestar.lodestone.modules.toolkit.blockentity.LodestoneBlockEntityType;
import team.lodestar.lodestone.modules.toolkit.recipe.LodestoneRecipeSearch;

@SuppressWarnings("DataFlowIssue")
public class RunicWorkbenchBlockEntity extends MalumItemHolderBlockEntity {

    protected int progress = 0;
    protected RunicWorkbenchRecipeInput input;

    public RunicWorkbenchBlockEntity(LodestoneBlockEntityType<? extends RunicWorkbenchBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public RunicWorkbenchBlockEntity(BlockPos pos, BlockState state) {
        this(MalumBlockEntities.RUNIC_WORKBENCH.get(), pos, state);
        inventory.attachDisplayData(RunicWorkbenchItemDisplayData::new);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putInt("progress", progress);
        if (input != null) {
            var access = level.registryAccess();
            tag.put("primaryInput", input.primaryInput().save(access));
            tag.put("secondaryInput", input.secondaryInput().save(access));
        }
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        progress = tag.getInt("progress");
        if (tag.contains("primaryInput") && tag.contains("secondaryInput")) {
            var primaryInput = ItemStack.parseOptional(level.registryAccess(), tag.getCompound("primaryInput"));
            var secondaryInput = ItemStack.parseOptional(level.registryAccess(), tag.getCompound("secondaryInput"));
            input = new RunicWorkbenchRecipeInput(
                    primaryInput,
                    secondaryInput
            );
        }
    }

    @Override
    public ItemInteractionResult onUseWithItem(Player player, ItemStack heldStack, InteractionHand hand) {
        var inventoryStack = inventory.getStackInSlot(0);
        if (inventoryStack.isEmpty()) {
            return super.onUseWithItem(player, heldStack, hand);
        }
        boolean success = tryCraft(level, inventoryStack, heldStack, !player.isCreative());
        if (success) {
            return ItemInteractionResult.SUCCESS;
        }
        return super.onUseWithItem(player, heldStack, hand);
    }

    @Override
    public void serverTick(ServerLevel level) {
        if (input != null) {
            progress++;
            if (progress == 20) {
                craft(level);
                progress = 0;
                input = null;
            }
        }
    }

    public boolean tryCraft(Level level, ItemStack primaryInput, ItemStack secondaryInput, boolean consumeItems) {
        var storedInput = new RunicWorkbenchRecipeInput(primaryInput, secondaryInput);
        var recipe = LodestoneRecipeSearch.search(level, MalumRecipeTypes.RUNEWORKING::get).findRecipe(storedInput);
        if (recipe == null) {
            return false;
        }
        if (level instanceof ServerLevel serverLevel) {
            int primaryCount = recipe.input.count();
            int secondaryCount = recipe.secondaryInput.count();

            if (consumeItems) {
                primaryInput.shrink(primaryCount);
                secondaryInput.shrink(secondaryCount);
            }
            input = storedInput;

            SpiritShardItem spirit = null;
            if (input.secondaryInput().getItem() instanceof SpiritShardItem shardItem) {
                spirit = shardItem;
            } else if (input.primaryInput().getItem() instanceof SpiritShardItem shardItem) {
                spirit = shardItem;
            }
            float pitch = Easing.SINE_IN_OUT.asWeighedRandom(serverLevel.random, 0.9f, 1.2f);
            playSound(recipe.soundType, 1, pitch);
            var effectType = spirit != null ? MalumParticleEffectTypes.RUNIC_WORKBENCH_CRAFTS_RUNE : MalumParticleEffectTypes.RUNIC_WORKBENCH_CRAFTS_SPIRITLESS_ITEM;
            var particle = effectType.createEffect(worldPosition).customData(new RunicWorkbenchEffectData(input.primaryInput().copy(), input.secondaryInput().copy()));
            if (spirit != null) {
                particle.color(spirit);
            }
            particle.spawn(serverLevel);
        }

        return true;
    }

    public void craft(ServerLevel level) {
        var recipe = LodestoneRecipeSearch.search(level, MalumRecipeTypes.RUNEWORKING::get).findRecipe(input);
        if (recipe == null) {
            return;
        }
        Vec3 itemPos = getItemPos();
        ItemEntity itemEntity = new ItemEntity(level, itemPos.x, itemPos.y, itemPos.z, recipe.output.copy());
        itemEntity.setPickUpDelay(15);
        itemEntity.setDeltaMovement(0, 0.25f, 0);
        level.addFreshEntity(itemEntity);
        setDirty();
    }
}