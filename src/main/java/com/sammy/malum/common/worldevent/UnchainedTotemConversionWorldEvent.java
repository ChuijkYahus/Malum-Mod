package com.sammy.malum.common.worldevent;

import com.sammy.malum.common.block.blight.*;
import com.sammy.malum.common.block.curiosities.totem.TotemPoleBlock;
import com.sammy.malum.common.block.curiosities.totem.TotemPoleBlockEntity;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.content.MalumContent;
import com.sammy.malum.registry.common.sound.*;
import net.minecraft.core.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.*;
import team.lodestar.lodestone.modules.core.easing.Easing;

public class UnchainedTotemConversionWorldEvent extends ActiveBlightWorldEvent {
    public int transformedTotemParts;

    public UnchainedTotemConversionWorldEvent() {
        super(MalumWorldEventTypes.UNCHAINED_TOTEM_CONVERSION.get());
    }

    @Override
    public void createBlight(ServerLevel level, int intensity) {
        super.createBlight(level, intensity);
        if (transformedTotemParts == 0) {
            placeBlock(level, position, MalumContent.Totemancy.SOULWOOD_TOTEM_BASE.get().defaultBlockState());
            transformedTotemParts++;
            return;
        }
        int offset = transformedTotemParts;
        BlockPos totemPos = position.above(offset);
        if (level.getBlockEntity(totemPos) instanceof TotemPoleBlockEntity) {
            BlockState totemPole = level.getBlockState(totemPos);
            replaceTotemPole(level, totemPos, totemPole);
        }
        transformedTotemParts++;
    }

    public void replaceTotemPole(ServerLevel level, BlockPos pos, BlockState state) {
        var direction = state.getValue(TotemPoleBlock.HORIZONTAL_FACING);
        var spirit = state.getValue(TotemPoleBlock.SPIRIT_TYPE);
        var newState = MalumContent.Totemancy.SOULWOOD_TOTEM_POLE.get().defaultBlockState()
                .setValue(TotemPoleBlock.HORIZONTAL_FACING, direction)
                .setValue(TotemPoleBlock.SPIRIT_TYPE, spirit);
        placeBlock(level, pos, newState);
        if (level.getBlockEntity(pos) instanceof TotemPoleBlockEntity blockEntity) {
            blockEntity.brieflyActivate(level);
        }
        maybePlaceBlightedGunk(level, pos, direction);
    }

    public void placeBlock(ServerLevel level, BlockPos pos, BlockState state) {
        level.setBlockAndUpdate(pos, state);
        level.levelEvent(null, 2001, pos, Block.getId(state));
        float pitch = Easing.SINE_IN_OUT.asWeighedRandom(level.getRandom(), 1.6f, 2f);
        level.playSound(null, pos, MalumBlockSoundEvents.MINOR_BLIGHT_MOTIF.get(), SoundSource.BLOCKS, 1f, pitch);
    }

    public void maybePlaceBlightedGunk(ServerLevel level, BlockPos pos, Direction totemDirection) {
        final RandomSource random = level.getRandom();
        if (random.nextFloat() < 0.4f) {
            var direction = Direction.from2DDataValue(random.nextInt(4));
            if (direction.equals(totemDirection)) {
                return;
            }
            var defaultState = MalumContent.BlockSets.CLINGING_BLIGHT.get().defaultBlockState();
            var state = defaultState
                    .setValue(BlockStateProperties.HORIZONTAL_FACING, direction.getOpposite())
                    .setValue(CreepingBlightBlock.BLIGHT_TYPE, CreepingBlightBlock.BlightType.SOULWOOD_SPIKE);
            placeBlock(level, pos.relative(direction), state);
        }
    }
}