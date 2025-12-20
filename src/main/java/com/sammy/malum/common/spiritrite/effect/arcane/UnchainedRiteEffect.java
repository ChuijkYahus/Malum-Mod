package com.sammy.malum.common.spiritrite.effect.arcane;

import com.sammy.malum.common.entity.activator.*;
import com.sammy.malum.core.systems.rite.effect.*;
import com.sammy.malum.registry.common.MalumTags;
import com.sammy.malum.registry.common.recipe.MalumRecipeTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import team.lodestar.lodestone.helpers.block.BlockStateHelper;
import team.lodestar.lodestone.systems.recipe.LodestoneRecipeType;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.*;

public class UnchainedRiteEffect extends SpiritRiteBlockEffect {

    public UnchainedRiteEffect() {
        super(SpiritRiteEffectTag.GREATER_RITE);
    }

    @Override
    public void applyEffect(ServerLevel level, BlockRiteEffectActivator entity, BlockState state, BlockPos pos, float impact) {
        if (!state.is(MalumTags.BlockTags.UNCHAINED_RITE_CATALYST)) {
            entity.discard();
            return;
        }
        var targetPos = pos.above();
        var targetState = level.getBlockState(targetPos);
        var targetAsItem = targetState.getBlock().asItem().getDefaultInstance();
        var recipe = LodestoneRecipeType.getRecipe(level, MalumRecipeTypes.UNCHAINED_TRANSMUTATION.get(), new SingleRecipeInput(targetAsItem));
        if (recipe != null) {
            if (recipe.output.getItem() instanceof BlockItem blockItem) {
                Block resultBlock = blockItem.getBlock();
                BlockState newState = BlockStateHelper.setBlockStateWithExistingProperties(level, targetPos, resultBlock.defaultBlockState(), 3);
                level.levelEvent(2001, targetPos, Block.getId(newState));
                createEffect(level, targetPos, ARCANE_SPIRIT, ELDRITCH_SPIRIT);
                if (resultBlock instanceof EntityBlock entityBlock) {
                    BlockEntity blockEntity = level.getBlockEntity(targetPos);
                    if (blockEntity != null) {
                        BlockEntity newEntity = entityBlock.newBlockEntity(pos, newState);
                        if (newEntity != null) {
                            if (newEntity.getClass().equals(blockEntity.getClass())) {
                                level.setBlockEntity(blockEntity);
                            }
                        }
                    }
                }
            }
        }
    }
}
