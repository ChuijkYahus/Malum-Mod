package com.sammy.malum.common.block.curiosities.redstone;

import com.sammy.malum.common.payloads.spirit_diode.SpiritDiodeVisualUpdatePayload;
import com.sammy.malum.registry.common.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.network.PacketDistributor;
import team.lodestar.lodestone.helpers.RandomHelper;
import team.lodestar.lodestone.systems.blockentity.LodestoneBlockEntity;
import team.lodestar.lodestone.systems.particle.data.color.*;

import java.awt.*;
import java.util.*;

import static net.minecraft.network.chat.Component.translatable;

public class SpiritDiodeBlockEntity extends LodestoneBlockEntity {

    public enum TimeIntervalType {
        REDSTONE_TICK(0, 2),
        SECOND(1, 20),
        MINUTE(2, 1200);

        final int id;
        final int timeScale;

        TimeIntervalType(int id, int timeScale) {
            this.id = id;
            this.timeScale = timeScale;
        }

        public String getName() {
            return toString().toLowerCase(Locale.ROOT);
        }

        public Component getText(SpiritDiodeBlockEntity blockEntity) {
            return getText(blockEntity.frequency > 1);
        }
        public Component getText(boolean plural) {
            var key = plural ? getPluralLangKey() : getLangKey();
            return Component.translatable(key);
        }

        public String getLangKey() {
            return "malum.waveform_artifice." + getName();
        }
        public String getPluralLangKey() {
            return getLangKey() + "_plural";
        }
    }

    public TimeIntervalType type = TimeIntervalType.REDSTONE_TICK;
    public int frequency = 20;

    public int cachedInputSignal = -1;
    public int outputSignal;

    public int closeDelay;

    //TODO: remove all this
    public long visualStartTime;
    public int visualTransitionDuration;
    public int visualTransitionStart;
    public int visualTransitionEnd;

    public SpiritDiodeBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);
        type = TimeIntervalType.valueOf(pTag.getString("type"));
        frequency = pTag.getInt("frequency");

        cachedInputSignal = pTag.getInt("cachedInputSignal");
        outputSignal = pTag.getInt("outputSignal");
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putString("type", type.name());
        tag.putInt("frequency", frequency);

        tag.putInt("cachedInputSignal", cachedInputSignal);
        tag.putInt("outputSignal", outputSignal);
    }

    @Override
    public void serverTick(ServerLevel level) {
        if (closeDelay > 0) {
            closeDelay--;
            if (closeDelay == 0) {
                toggleState(false, type, frequency);
            }
        }
    }

    public void toggleState(boolean newValue, TimeIntervalType type, int frequency) {
        if (level instanceof ServerLevel serverLevel) {
            boolean value = getBlockState().getValue(SpiritDiodeBlock.OPEN);
            if (value != newValue) {
                level.setBlock(getBlockPos(), getBlockState().setValue(SpiritDiodeBlock.OPEN, !value), 3);
                level.playSound(null, getBlockPos(), value ? MalumSoundEvents.SPIRIT_DIODE_CLOSE.get() : MalumSoundEvents.SPIRIT_DIODE_OPEN.get(), SoundSource.BLOCKS, 0.8f, RandomHelper.randomBetween(level.getRandom(), 0.9f, 1.1f));
                var particleEffect = value ? MalumParticleEffectTypes.SPIRIT_DIODE_CLOSE : MalumParticleEffectTypes.SPIRIT_DIODE_OPEN;
                particleEffect.createEffect()
                        .at(worldPosition.getCenter().add(0, value ? 0 : 0.5f, 0))
                        .color(ColorParticleData.create(new Color(170, 15, 1), new Color(129, 12, 0)).build())
                        .spawn(serverLevel);
                this.type = type;
                this.frequency = frequency;
                setDirty();
            }
            closeDelay = newValue ? 100 : 0;
        }
    }

    public Component getTitleComponent() {
        var id = BuiltInRegistries.BLOCK.getKey(getBlockState().getBlock());
        return Component.translatable("malum.waveform_artifice." + id.getPath());
    }

    public int getAdjustedFrequency() {
        return frequency * type.timeScale;
    }

    public int getOutputSignal() {
        return Mth.clamp(outputSignal, 0, 15);
    }

    public void updateVisuals(int outputSignal, int inputSignal, boolean isPowering) {
        this.outputSignal = outputSignal;
        this.cachedInputSignal = inputSignal;
        this.visualStartTime = getLevel().getGameTime();
        this.visualTransitionDuration = getAdjustedFrequency();
        this.visualTransitionStart = isPowering ? 0 : 1;
        this.visualTransitionEnd = 1 - visualTransitionStart;
    }

    public void updateAnimation(ServerLevel serverLevel, BlockPos pos, int inputSignal) {
        int outputSignal = getOutputSignal();
        PacketDistributor.sendToPlayersTrackingChunk(serverLevel,
                new ChunkPos(pos), new SpiritDiodeVisualUpdatePayload(pos, outputSignal, inputSignal, outputSignal == 0));
    }
}