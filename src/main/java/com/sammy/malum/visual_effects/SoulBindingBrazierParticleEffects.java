package com.sammy.malum.visual_effects;

import com.sammy.malum.common.block.curiosities.soul_brazier.SoulBrazierBlockEntity;
import com.sammy.malum.common.block.curiosities.spirit_altar.IAltarAccelerator;
import com.sammy.malum.common.block.curiosities.spirit_altar.SpiritAltarBlockEntity;
import com.sammy.malum.common.item.spirit.SpiritShardItem;
import com.sammy.malum.common.recipe.SpiritInfusionRecipe;
import com.sammy.malum.core.systems.spirit.MalumSpiritType;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import team.lodestar.lodestone.helpers.RandomHelper;
import team.lodestar.lodestone.systems.blockentity.LodestoneBlockEntityInventory;
import team.lodestar.lodestone.systems.particle.builder.AbstractParticleBuilder;

import static com.sammy.malum.visual_effects.SpiritLightSpecs.spiritLightSpecs;

public class SoulBindingBrazierParticleEffects {

    public static MalumSpiritType getCentralSpiritType(SoulBrazierBlockEntity brazier) {
        final LodestoneBlockEntityInventory spiritInventory = brazier.spiritInventory;
        int spiritCount = spiritInventory.getFilledSlotCount();
        Item currentItem = spiritInventory.getStackInSlot(0).getItem();
        if (spiritCount > 1) {
            float duration = 30f * spiritCount;
            float gameTime = (brazier.getLevel().getGameTime() % duration) / 30f;
            currentItem = spiritInventory.getStackInSlot(Mth.floor(gameTime)).getItem();
        }
        if (!(currentItem instanceof SpiritShardItem spiritItem)) {
            return null;
        }
        return spiritItem.type;
    }
    public static void passiveBrazierParticles(SoulBrazierBlockEntity brazier) {
        MalumSpiritType activeSpiritType = getCentralSpiritType(brazier);
        if (activeSpiritType == null) {
            return;
        }
        Level level = brazier.getLevel();
        var random = level.random;
        Vec3 itemPos = brazier.getItemPos();
        LodestoneBlockEntityInventory spiritInventory = brazier.spiritInventory;
        int spiritsRendered = 0;
        for (int i = 0; i < spiritInventory.slotCount; i++) {
            ItemStack item = spiritInventory.getStackInSlot(i);
            if (item.getItem() instanceof SpiritShardItem spiritSplinterItem) {
                Vec3 offset = brazier.getSpiritOffset(spiritsRendered++, 0);
                activeSpiritType = spiritSplinterItem.type;
                BlockPos blockPos = brazier.getBlockPos();
                Vec3 spiritPosition = new Vec3(blockPos.getX() + offset.x, blockPos.getY() + offset.y, blockPos.getZ() + offset.z);
                spiritLightSpecs(level, spiritPosition, activeSpiritType).spawnParticles();
            }
        }
    }
}
