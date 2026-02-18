package com.sammy.malum.common.block.nature.soulwood;

import com.sammy.malum.common.block.nature.MalumLogBlock;
import com.sammy.malum.core.systems.spirit.type.*;
import com.sammy.malum.registry.common.sound.MalumSoundEvents;
import net.minecraft.core.*;
import net.minecraft.server.level.*;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class SoulwoodLogBlock extends MalumLogBlock {
    public SoulwoodLogBlock(Properties properties, Supplier<Block> stripped) {
        super(properties, stripped);
    }

    @Nullable
    @Override
    public BlockState getToolModifiedState(BlockState state, UseOnContext context, ItemAbility itemAbility, boolean simulate) {
        if (itemAbility.equals(ItemAbilities.AXE_STRIP)) {
            if (!simulate) {
                context.getLevel().playSound(null, context.getClickedPos(), MalumSoundEvents.MAJOR_BLIGHT_MOTIF.get(), SoundSource.BLOCKS, 1, 1);
            }
            return stripped.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
        }
        return null;
    }

    @Override
    public boolean createTotemPole(ServerLevel level, BlockPos pos, Direction direction, SpiritLike spirit) {
        boolean success = super.createTotemPole(level, pos, direction, spirit);
        if (success) {
            level.playSound(null, pos, MalumSoundEvents.MAJOR_BLIGHT_MOTIF.get(), SoundSource.BLOCKS, 1, 1);
        }
        return success;
    }
}