package com.sammy.malum.common.block.curiosities.artifice.redstone;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import com.sammy.malum.common.payloads.waveform.SpiritDiodeVisualUpdatePayload;
import io.netty.buffer.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.*;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.network.PacketDistributor;
import team.lodestar.lodestone.modules.toolkit.blockentity.LodestoneBlockEntityType;

import java.util.*;

import static net.minecraft.network.chat.Component.translatable;

public class SpiritDiodeBlockEntity extends OpenStateBlockEntity {

    public record SpiritDiodeInfo(TimeIntervalType type, int frequency) implements InboundInfo<SpiritDiodeBlockEntity> {
        public static final Codec<SpiritDiodeInfo> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                TimeIntervalType.CODEC.fieldOf("type").forGetter(SpiritDiodeInfo::type),
                Codec.INT.fieldOf("frequency").forGetter(SpiritDiodeInfo::frequency)
        ).apply(instance, SpiritDiodeInfo::new));

        public static StreamCodec<ByteBuf, SpiritDiodeInfo> STREAM_CODEC = ByteBufCodecs.fromCodec(SpiritDiodeInfo.CODEC);

        @Override
        public void sync(SpiritDiodeBlockEntity entity) {
            entity.type = type;
            entity.frequency = frequency;
        }
    }

    public enum TimeIntervalType implements StringRepresentable {
        REDSTONE_TICK("redstone_tick", 0, 2),
        SECOND("second", 1, 20),
        MINUTE("minute", 2, 1200);

        public static final StringRepresentable.EnumCodec<TimeIntervalType> CODEC = StringRepresentable.fromEnum(TimeIntervalType::values);

        final String name;
        final int id;
        final int timeScale;

        TimeIntervalType(String name, int id, int timeScale) {
            this.name = name;
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

        @Override
        public String getSerializedName() {
            return name;
        }
    }

    public TimeIntervalType type = TimeIntervalType.REDSTONE_TICK;
    public int frequency = 20;

    public int cachedInputSignal = -1;
    public int outputSignal;

    //TODO: remove all this
    public long visualStartTime;
    public int visualTransitionDuration;
    public int visualTransitionStart;
    public int visualTransitionEnd;

    public SpiritDiodeBlockEntity(LodestoneBlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public SpiritDiodeInfo resetState() {
        return new SpiritDiodeInfo(type, frequency);
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