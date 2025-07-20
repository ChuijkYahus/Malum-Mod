package com.sammy.malum.common.spiritrite.effect.infernal;

import com.sammy.malum.core.systems.rite.effect.SpiritRiteBlockEffect;
import com.sammy.malum.registry.common.MalumParticleEffectTypes;
import com.sammy.malum.registry.common.MalumTags;
import com.sammy.malum.registry.common.recipe.MalumRecipeTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import team.lodestar.lodestone.helpers.block.BlockStateHelper;
import team.lodestar.lodestone.systems.recipe.LodestoneRecipeType;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.ELDRITCH_SPIRIT;
import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.INFERNAL_SPIRIT;

public class FurnaceAccelerationRiteEffect extends SpiritRiteBlockEffect {

    public FurnaceAccelerationRiteEffect() {
        super();
    }

    @Override
    public void applyEffect(ServerLevel level, BlockState state, BlockPos pos) {
        if (level.getBlockEntity(pos) instanceof AbstractFurnaceBlockEntity furnace) {
            createEffect(level, pos, INFERNAL_SPIRIT, ELDRITCH_SPIRIT);
            quickenFurnace(level, furnace, 20, 20);
        }
    }

    public void quickenFurnace(ServerLevel level, AbstractFurnaceBlockEntity furnace, int removedLitTime, int addedCookingProgress) {
        int maxCookingTime = furnace.cookingTotalTime - 1;
        int excessLitTime = 0;
        int excessCookingProgress = 0;

        furnace.litTime -= removedLitTime;
        furnace.cookingProgress += addedCookingProgress;
        if (furnace.litTime < 0) {
            int excess = Math.abs(furnace.litTime);
            furnace.litTime = 0;
            excessLitTime = excess;
        }
        if (furnace.cookingProgress > maxCookingTime) {
            int excess = furnace.cookingProgress - maxCookingTime;
            furnace.cookingProgress = maxCookingTime;
            excessCookingProgress = excess;
        }
        if (excessLitTime != 0 || excessCookingProgress != 0) {
            int cachedLitTime = furnace.litTime;
            int cachedCookingProgress = furnace.cookingProgress;
            AbstractFurnaceBlockEntity.serverTick(level, furnace.getBlockPos(), furnace.getBlockState(), furnace);
            furnace.litTime = cachedLitTime;
            furnace.cookingProgress = cachedCookingProgress;
            quickenFurnace(level, furnace, excessLitTime, excessCookingProgress);
            //Recursion to update the furnace with the excess values after a successful smelt
        }
    }
}