package com.sammy.malum.common.spiritrite.effect.aerial;

import com.sammy.malum.common.entity.activator.*;
import com.sammy.malum.core.systems.rite.effect.*;
import com.sammy.malum.registry.common.MalumParticleEffectTypes;
import com.sammy.malum.registry.common.MalumSoundEvents;
import com.sammy.malum.registry.common.MalumTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;
import team.lodestar.lodestone.helpers.*;

import java.util.List;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.*;

public class BlockGravityRiteEffect extends SpiritRiteBlockEffect {

    public BlockGravityRiteEffect() {
        super(SpiritRiteEffectTag.GREATER_RITE);
    }

    @Override
    public void applyEffect(ServerLevel level, BlockRiteEffectActivatorEntity entity, BlockState state, BlockPos pos, float impact) {
        var stateBelow = level.getBlockState(pos.below());
        if (FallingBlock.isFree(stateBelow) || !stateBelow.canOcclude() || stateBelow.is(net.minecraft.tags.BlockTags.SLABS)) {
            if (!state.isAir() && level.getBlockEntity(pos) == null && canSilkTouch(level, pos, state)) {
                FallingBlockEntity.fall(level, pos, state);

                createEffect(level, MalumParticleEffectTypes.BLOCK_FALL_RITE_EFFECT, pos, AERIAL_SPIRIT);
                level.playSound(null, pos, MalumSoundEvents.TOTEM_BLOCK_GRAVITY.get(), SoundSource.BLOCKS, 0.5f, RandomHelper.randomBetween(level.random, 1.75f, 2f));
            }
        }
    }

    private static final List<Item> TOOLS = List.of(Items.NETHERITE_PICKAXE, Items.NETHERITE_AXE, Items.NETHERITE_SHOVEL, Items.NETHERITE_HOE);
    // From Botania, modified slightly

    private static boolean canSilkTouch(ServerLevel level, BlockPos pos, BlockState state) {
        if (state.is(MalumTags.BlockTags.GREATER_AERIAL_WHITELIST)) {
            return true;
        }
        ItemStack harvestToolStack = getToolForState(state);
        if (harvestToolStack.isEmpty()) {
            return false;
        }
        harvestToolStack.enchant(level.registryAccess().holderOrThrow(Enchantments.SILK_TOUCH), 1);
        List<ItemStack> drops = Block.getDrops(state, level, pos, null, null, harvestToolStack);
        Item blockItem = state.getBlock().asItem();
        return drops.stream().anyMatch(s -> s.getItem() == blockItem);
    }

    private static ItemStack getToolForState(BlockState state) {
        if (!state.requiresCorrectToolForDrops()) {
            return new ItemStack(Items.NETHERITE_PICKAXE);
        } else {
            for (Item item : TOOLS) {
                ItemStack stack = new ItemStack(item);
                if (stack.isCorrectToolForDrops(state)) {
                    return stack;
                }
            }

            return ItemStack.EMPTY;
        }
    }
}
