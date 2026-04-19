package com.sammy.malum.common.block.curiosities.artifice.crystallarium;

import com.sammy.malum.common.block.storage.MalumItemHolderBlockEntity;
import com.sammy.malum.common.item.spirit.SpiritShardItem;
import com.sammy.malum.common.recipe.RuneworkingRecipe.RunicWorkbenchRecipeInput;
import com.sammy.malum.registry.common.MalumParticleEffectTypes;
import com.sammy.malum.registry.common.block.MalumBlockEntities;
import com.sammy.malum.registry.common.recipe.MalumRecipeTypes;
import com.sammy.malum.visual_effects.block.ConjunctureCrystallariumParticleEffects;
import com.sammy.malum.visual_effects.networked.runic_workbench.RunicWorkbenchEffectData;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import team.lodestar.lodestone.modules.core.easing.Easing;
import team.lodestar.lodestone.modules.toolkit.blockentity.LodestoneBlockEntityType;
import team.lodestar.lodestone.modules.toolkit.recipe.LodestoneRecipeSearch;

public class ConjunctureCrystallariumBlockEntity extends MalumItemHolderBlockEntity {

    public ConjunctureCrystallariumBlockEntity(LodestoneBlockEntityType<? extends ConjunctureCrystallariumBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public ConjunctureCrystallariumBlockEntity(BlockPos pos, BlockState state) {
        this(MalumBlockEntities.CONJUNCTURE_CRYSTALLARIUM.get(), pos, state);
    }

    @Override
    public void clientTick(Level level) {
        ConjunctureCrystallariumParticleEffects.passiveCrystallariumParticles(this);
    }
}