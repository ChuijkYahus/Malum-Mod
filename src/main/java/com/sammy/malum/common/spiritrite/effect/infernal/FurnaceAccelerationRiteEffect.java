package com.sammy.malum.common.spiritrite.effect.infernal;

import com.sammy.malum.common.entity.activator.rite.*;
import com.sammy.malum.core.systems.rite.effect.*;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.*;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.ELDRITCH_SPIRIT;
import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.INFERNAL_SPIRIT;

public class FurnaceAccelerationRiteEffect extends SpiritRiteBlockEffect {

    public FurnaceAccelerationRiteEffect() {
        super(SpiritRiteEffectTag.GREATER_RITE);
    }

    @Override
    public void applyEffect(ServerLevel level, BlockRiteEffectActivator entity, BlockState state, BlockPos pos, float impact) {
        if (level.getBlockEntity(pos) instanceof AbstractFurnaceBlockEntity furnace) {
            createEffect(level, pos, INFERNAL_SPIRIT, ELDRITCH_SPIRIT);
            int progress = Mth.floor(20*impact);
            quickenFurnace(level, furnace, progress, progress);
        }
    }

    public void quickenFurnace(ServerLevel level, AbstractFurnaceBlockEntity furnace, int removedLitTime, int addedCookingProgress) {
        if (!furnace.isLit()) {
            return;
        }
        int maxCookingTime = furnace.cookingTotalTime - 1;
        int excessLitTime = 0;
        int excessCookingProgress = 0;

        furnace.litTime -= removedLitTime;
        furnace.cookingProgress += addedCookingProgress;
        if (furnace.litTime < 1) {
            int excess = Math.abs(furnace.litTime);
            furnace.litTime = 1;
            excessLitTime = excess;
        }
        if (furnace.cookingProgress > maxCookingTime) {
            int excess = furnace.cookingProgress - maxCookingTime;
            furnace.cookingProgress = maxCookingTime;
            excessCookingProgress = excess;
        }
        if (excessLitTime > 0 || excessCookingProgress > 0) {
            AbstractFurnaceBlockEntity.serverTick(level, furnace.getBlockPos(), furnace.getBlockState(), furnace);
            quickenFurnace(level, furnace, excessLitTime, excessCookingProgress);
            //Recursion to update the furnace with the excess values after a successful smelt
        }
    }
}