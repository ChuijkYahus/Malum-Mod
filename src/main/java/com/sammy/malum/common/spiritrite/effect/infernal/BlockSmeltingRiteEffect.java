package com.sammy.malum.common.spiritrite.effect.infernal;

import com.sammy.malum.core.systems.rite.effect.SpiritRiteBlockEffect;
import com.sammy.malum.registry.common.MalumParticleEffectTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.ELDRITCH_SPIRIT;
import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.INFERNAL_SPIRIT;

public class BlockSmeltingRiteEffect extends SpiritRiteBlockEffect {

    public BlockSmeltingRiteEffect() {
        super();
    }

    @Override
    public void applyEffect(ServerLevel level, BlockState state, BlockPos pos) {
        var recipeOptional = level.getRecipeManager().getRecipeFor(RecipeType.SMELTING, new SingleRecipeInput(new ItemStack(state.getBlock().asItem(), 1)), level);
        if (recipeOptional.isPresent()) {
            var recipe = recipeOptional.get().value();
            var output = recipe.getResultItem(level.registryAccess());
            if (output.getItem() instanceof BlockItem blockItem) {
                var block = blockItem.getBlock();
                var newState = block.defaultBlockState();
                level.setBlockAndUpdate(pos, newState);
                level.levelEvent(2001, pos, Block.getId(newState));
                createEffect(level, pos, INFERNAL_SPIRIT, ELDRITCH_SPIRIT);
            }
        }
    }
}
