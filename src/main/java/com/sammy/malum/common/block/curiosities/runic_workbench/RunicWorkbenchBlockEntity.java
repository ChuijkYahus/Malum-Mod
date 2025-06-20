package com.sammy.malum.common.block.curiosities.runic_workbench;

import com.sammy.malum.common.block.storage.*;
import com.sammy.malum.common.item.spirit.*;
import com.sammy.malum.common.recipe.RunicWorkbenchRecipe.*;
import com.sammy.malum.core.systems.spirit.type.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.block.*;
import com.sammy.malum.registry.common.recipe.MalumRecipeTypes;
import com.sammy.malum.visual_effects.networked.runic_workbench.*;
import net.minecraft.core.*;
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
import net.neoforged.neoforge.common.crafting.*;
import team.lodestar.lodestone.helpers.block.*;
import team.lodestar.lodestone.systems.recipe.*;

import java.util.*;

public class RunicWorkbenchBlockEntity extends MalumItemHolderBlockEntity {

    public static final Vec3 RUNIC_WORKBENCH_ITEM_OFFSET = new Vec3(0.5f, 1.25f, 0.5f);

    public RunicWorkbenchBlockEntity(BlockEntityType<? extends RunicWorkbenchBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public RunicWorkbenchBlockEntity(BlockPos pos, BlockState state) {
        this(MalumBlockEntities.RUNIC_WORKBENCH.get(), pos, state);
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
        var input = new RunicWorkbenchRecipeInput(inventoryStack, heldStack);
        boolean success = tryCraft(level, input, !player.isCreative());
        if (success) {
            return ItemInteractionResult.SUCCESS;
        }
        return super.onUseWithItem(player, heldStack, hand);
    }

    //TODO: This should run during hopper interaction and shit
    public boolean tryCraft(Level level, RunicWorkbenchRecipeInput input, boolean consumeItems) {
        var recipe = LodestoneRecipeType.getRecipe(level, MalumRecipeTypes.RUNEWORKING.get(), input);
        if (recipe == null) {
            return false;
        }
        if (level instanceof ServerLevel serverLevel) {
            SpiritShardItem spirit = null;
            if (input.secondaryInput().getItem() instanceof SpiritShardItem shardItem) {
                spirit = shardItem;
            }
            else if (input.primaryInput().getItem() instanceof SpiritShardItem shardItem) {
                spirit = shardItem;
            }

            Vec3 itemPos = getItemPos();
            ItemEntity itemEntity = new ItemEntity(serverLevel, itemPos.x, itemPos.y, itemPos.z, recipe.output.copy());
            itemEntity.setPickUpDelay(15);
            serverLevel.addFreshEntity(itemEntity);
            serverLevel.playSound(null, worldPosition, MalumSoundEvents.RUNIC_WORKBENCH_CRAFT.get(), SoundSource.BLOCKS, 1, 0.9f + serverLevel.random.nextFloat() * 0.25f);
            var effectType = spirit != null ? MalumParticleEffectTypes.RUNIC_WORKBENCH_CRAFTS_RUNE : MalumParticleEffectTypes.RUNIC_WORKBENCH_CRAFTS_SPIRITLESS_ITEM;
            var particle = effectType.createEffect(worldPosition).customData(new RunicWorkbenchEffectData(input.primaryInput().copy(), input.secondaryInput().copy()));
            if (spirit != null) {
                particle.color(spirit);
            }
            particle.spawn(serverLevel);

            if (consumeItems) {
                input.primaryInput().shrink(recipe.primaryInput.count());
                input.secondaryInput().shrink(recipe.secondaryInput.count());
            }
            BlockStateHelper.updateAndNotifyState(serverLevel, worldPosition);
        }
        return true;
    }
}