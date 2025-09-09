package com.sammy.malum.common.block.curiosities.runic_workbench;

import com.sammy.malum.common.block.storage.*;
import com.sammy.malum.common.item.spirit.*;
import com.sammy.malum.common.recipe.RuneworkingRecipe.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.block.*;
import com.sammy.malum.registry.common.recipe.MalumRecipeTypes;
import com.sammy.malum.visual_effects.networked.runic_workbench.*;
import net.minecraft.core.*;
import net.minecraft.nbt.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.item.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.phys.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.helpers.block.*;
import team.lodestar.lodestone.systems.recipe.*;

@SuppressWarnings("DataFlowIssue")
public class RunicWorkbenchBlockEntity extends MalumItemHolderBlockEntity {

    public static final Vec3 RUNIC_WORKBENCH_ITEM_OFFSET = new Vec3(0.5f, 1.25f, 0.5f);

    protected int progress = 0;
    protected RunicWorkbenchRecipeInput input;

    public RunicWorkbenchBlockEntity(BlockEntityType<? extends RunicWorkbenchBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public RunicWorkbenchBlockEntity(BlockPos pos, BlockState state) {
        this(MalumBlockEntities.RUNIC_WORKBENCH.get(), pos, state);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.saveAdditional(pTag, pRegistries);
        pTag.putInt("progress", progress);
        if (input != null) {
            var access = level.registryAccess();
            pTag.put("primaryInput", input.primaryInput().save(access));
            pTag.put("secondaryInput", input.secondaryInput().save(access));
        }
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);
        progress = pTag.getInt("progress");
        if (pTag.contains("primaryInput") && pTag.contains("secondaryInput")) {
            var primaryInput = ItemStack.parseOptional(level.registryAccess(), pTag.getCompound("primaryInput"));
            var secondaryInput = ItemStack.parseOptional(level.registryAccess(), pTag.getCompound("secondaryInput"));
            input = new RunicWorkbenchRecipeInput(
                    primaryInput,
                    secondaryInput
            );
        }
    }

    @Override
    public Vec3 getItemOffset(float partialTicks) {
        if (inventory.getStackInSlot(0).getItem() instanceof SpiritShardItem) {
            float gameTime = level.getGameTime() + partialTicks;
            return RUNIC_WORKBENCH_ITEM_OFFSET.add(0, (float) Math.sin((gameTime % 360) / 20f) * 0.05f, 0);
        }
        return RUNIC_WORKBENCH_ITEM_OFFSET;
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
    public void tick() {
        if (level instanceof ServerLevel serverLevel) {
            if (input != null) {
                progress++;
                if (progress == 20) {
                    craft(serverLevel);
                    progress = 0;
                    input = null;
                }
            }
        }
        super.tick();
    }

    public boolean tryCraft(Level level, ItemStack primaryInput, ItemStack secondaryInput, boolean consumeItems) {
        var recipe = LodestoneRecipeType.getRecipe(level, MalumRecipeTypes.RUNEWORKING.get(), new RunicWorkbenchRecipeInput(primaryInput, secondaryInput));
        if (recipe == null) {
            return false;
        }
        if (level instanceof ServerLevel serverLevel) {
            int primaryCount = recipe.input.count();
            int secondaryCount = recipe.secondaryInput.count();
            input = new RunicWorkbenchRecipeInput(
                    primaryInput.copyWithCount(primaryCount),
                    secondaryInput.copyWithCount(secondaryCount)
            );

            if (consumeItems) {
                primaryInput.shrink(primaryCount);
                secondaryInput.shrink(secondaryCount);
            }

            SpiritShardItem spirit = null;
            if (input.secondaryInput().getItem() instanceof SpiritShardItem shardItem) {
                spirit = shardItem;
            } else if (input.primaryInput().getItem() instanceof SpiritShardItem shardItem) {
                spirit = shardItem;
            }
            serverLevel.playSound(null, worldPosition, recipe.soundType, SoundSource.BLOCKS, 1, RandomHelper.randomBetween(serverLevel.random, 0.9f, 1.2f));
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
        var recipe = LodestoneRecipeType.getRecipe(level, MalumRecipeTypes.RUNEWORKING.get(), input);
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