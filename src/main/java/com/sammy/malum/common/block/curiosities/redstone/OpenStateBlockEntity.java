package com.sammy.malum.common.block.curiosities.redstone;

import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.sound.*;
import net.minecraft.core.*;
import net.minecraft.server.level.*;
import net.minecraft.sounds.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.systems.blockentity.*;
import team.lodestar.lodestone.systems.particle.data.color.*;

import java.awt.*;

public abstract class OpenStateBlockEntity extends LodestoneBlockEntity {

    public int closeDelay;

    public OpenStateBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public void serverTick(ServerLevel level) {
        if (closeDelay > 0) {
            closeDelay--;
            if (closeDelay == 0) {
                toggleState(level, false, resetState());
            }
        }
    }

    public abstract InboundInfo<? extends OpenStateBlockEntity> resetState();

    public void toggleState(ServerLevel level, boolean newValue, InboundInfo<?> inboundInfo) {
        if (!canTinker()) {
            return;
        }
        var affected = getTinkeredBlock();
        if (affected != this) {
            affected.toggleState(level, newValue, inboundInfo);
            return;
        }
        boolean value = getBlockState().getValue(BlockStateProperties.OPEN);
        if (value != newValue) {
            level.setBlock(getBlockPos(), getBlockState().setValue(BlockStateProperties.OPEN, !value), 3);
            level.playSound(null, getBlockPos(), value ? MalumBlockSoundEvents.SPIRIT_DIODE_CLOSE.get() : MalumBlockSoundEvents.SPIRIT_DIODE_OPEN.get(), SoundSource.BLOCKS, 0.8f, RandomHelper.randomBetween(level.getRandom(), 0.9f, 1.1f));
            var particleEffect = value ? MalumParticleEffectTypes.SPIRIT_DIODE_CLOSE : MalumParticleEffectTypes.SPIRIT_DIODE_OPEN;
            particleEffect.createEffect()
                    .at(getBlockPos().getCenter().add(0, value ? 0 : 0.5f, 0))
                    .color(ColorParticleData.create(new Color(170, 15, 1), new Color(129, 12, 0)).build())
                    .spawn(level);
            setDirty();
            inboundInfo.sync(affected);
        }
        closeDelay = newValue ? 100 : 0;
    }

    public boolean canTinker() {
        return true;
    }

    public OpenStateBlockEntity getTinkeredBlock() {
        return this;
    }

    public interface InboundInfo<T extends LodestoneBlockEntity> {

        default void sync(OpenStateBlockEntity entity) {
            sync((T) entity);
        }

        void sync(T entity);
    }
}