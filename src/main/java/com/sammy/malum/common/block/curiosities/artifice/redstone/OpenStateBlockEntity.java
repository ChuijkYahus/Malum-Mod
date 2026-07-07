package com.sammy.malum.common.block.curiosities.artifice.redstone;

import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.sound.*;
import net.minecraft.core.*;
import net.minecraft.server.level.*;
import net.minecraft.sounds.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;
import team.lodestar.lodestone.modules.core.easing.Easing;
import team.lodestar.lodestone.modules.toolkit.blockentity.*;
import team.lodestar.lodestone.systems.particle.data.color.*;

import java.awt.*;

public abstract class OpenStateBlockEntity extends LodestoneBlockEntity {

    public int closeDelay;

    public OpenStateBlockEntity(LodestoneBlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public void serverTick(ServerLevel level) {
        if (closeDelay > 0) {
            closeDelay--;
            if (closeDelay == 0) {
                handleTinkeredStateChange(level, false, resetState());
            }
        }
    }

    public abstract NetworkedTinkeringInfo<? extends OpenStateBlockEntity> resetState();

    public void handleTinkeredStateChange(ServerLevel level, boolean newValue, NetworkedTinkeringInfo<?> networkedTinkeringInfo) {
        if (!canTinker()) {
            return;
        }
        var affected = redirectTinkerFocus();
        if (affected != this) {
            affected.handleTinkeredStateChange(level, newValue, networkedTinkeringInfo);
            return;
        }
        var state = getBlockState();
        if (state.getValue(BlockStateProperties.OPEN) != newValue) {
            level.setBlock(getBlockPos(), state.cycle(BlockStateProperties.OPEN), 3);
            var particleEffect = newValue ? MalumParticleEffectTypes.SPIRIT_DIODE_OPEN : MalumParticleEffectTypes.SPIRIT_DIODE_CLOSE;
            var sound = newValue ? MalumBlockSoundEvents.SPIRIT_DIODE_OPEN.get() : MalumBlockSoundEvents.SPIRIT_DIODE_CLOSE.get();
            float pitch = Easing.SINE_IN_OUT.asWeighedRandom(level.getRandom(), 0.9f, 1.1f);
            level.playSound(null, getBlockPos(), sound, SoundSource.BLOCKS, 0.8f, pitch);
            particleEffect.createEffect()
                    .at(getBlockPos().getCenter().add(0, newValue ? 0.5f : 0, 0))
                    .color(ColorParticleData.create(new Color(170, 15, 1), new Color(129, 12, 0)).build())
                    .spawn(level);
            setDirty();
            networkedTinkeringInfo.sync(affected);
        }
        closeDelay = newValue ? 100 : 0;
    }

    public boolean canTinker() {
        return true;
    }

    public OpenStateBlockEntity redirectTinkerFocus() {
        return this;
    }

    public interface NetworkedTinkeringInfo<T extends LodestoneBlockEntity> {

        default void sync(OpenStateBlockEntity entity) {
            sync((T) entity);
        }

        void sync(T entity);
    }
}