package com.sammy.malum.common.spiritrite.effect.arcane;

import com.sammy.malum.common.entity.activator.rite.*;
import com.sammy.malum.core.systems.rite.effect.*;
import com.sammy.malum.registry.common.MalumTags;
import com.sammy.malum.registry.common.recipe.MalumRecipeTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.state.BlockState;
import team.lodestar.lodestone.helpers.block.BlockStateHelper;
import team.lodestar.lodestone.modules.toolkit.recipe.*;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.*;

public class UnchainedRiteEffect extends SpiritRiteBlockEffect {

    public UnchainedRiteEffect() {
        super(SpiritRiteEffectTag.GREATER_RITE);
    }

    @Override
    public void applyEffect(ServerLevel level, BlockRiteEffectActivator entity, BlockState state, BlockPos pos, float impact) {
        if (!state.is(MalumTags.Blocks.UNCHAINED_RITE_CATALYST)) {
            entity.discard();
            return;
        }
        var targetPos = pos.above();
        var targetState = level.getBlockState(targetPos);
        var targetAsItem = targetState.getBlock().asItem().getDefaultInstance();

        var input = new SingleRecipeInput(targetAsItem);
        var recipe = LodestoneRecipeSearch.search(level, MalumRecipeTypes.UNCHAINED_TRANSMUTATION::get).findRecipe(input);
        if (recipe == null) {
            return;
        }
        var optional = recipe.createOutput();
        if (optional.isEmpty()) {
            return;
        }
        var result = optional.get();
        var newState = BlockStateHelper.setBlockStateWithExistingProperties(level, targetPos, result.defaultBlockState(), 3);
        level.levelEvent(2001, targetPos, Block.getId(newState));
        createEffect(level, targetPos, ARCANE_SPIRIT, ELDRITCH_SPIRIT);
        if (result instanceof EntityBlock entityBlock) {
            var blockEntity = level.getBlockEntity(targetPos);
            if (blockEntity == null) {
                return;
            }
            var newEntity = entityBlock.newBlockEntity(pos, newState);
            if (newEntity == null) {
                return;
            }
            if (!newEntity.getClass().equals(blockEntity.getClass())) {
                return;
            }
            level.setBlockEntity(blockEntity);
        }
    }
}
