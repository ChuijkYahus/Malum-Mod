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
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.network.PacketDistributor;
import team.lodestar.lodestone.helpers.RandomHelper;
import team.lodestar.lodestone.helpers.block.*;
import team.lodestar.lodestone.systems.blockentity.LodestoneBlockEntity;
import team.lodestar.lodestone.systems.particle.data.color.*;

import java.awt.*;
import java.util.*;

import static net.minecraft.network.chat.Component.translatable;

public class SpiritDiodeBlockEntity extends LodestoneBlockEntity {

    public enum TimeIntervalType {
        REDSTONE_TICKS(0, 2),
        SECONDS(1, 20),
        MINUTES(2, 1200);

        final int id;
        final int timeScale;

        TimeIntervalType(int id, int timeScale) {
            this.id = id;
            this.timeScale = timeScale;
        }

        public String getName() {
            return toString().toLowerCase(Locale.ROOT);
        }

        public Component getText() {
            return Component.translatable(getLangKey());
        }

        public String getLangKey() {
            return "malum.waveform_artifice." + getName();
        }
    }

    public TimeIntervalType type = TimeIntervalType.REDSTONE_TICKS;
    public int frequency = 20;

    public int outputSignal;
    public int inputSignal;

    public int closeDelay;

    public long visualStartTime;
    public int visualTransitionDuration;
    public int visualTransitionStart;
    public int visualTransitionEnd;

    public SpiritDiodeBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public ItemInteractionResult onUseWithItem(Player pPlayer, ItemStack pStack, InteractionHand pHand) {
        if (pPlayer.isCrouching()) {
            if (pStack.is(MalumTags.ItemTags.IS_REDSTONE_TOOL)) {
                level.setBlock(getBlockPos(), getBlockState().rotate(level, getBlockPos(), Rotation.CLOCKWISE_90), 3);
                level.playSound(null, getBlockPos(), MalumSoundEvents.SPIRIT_DIODE_TICK.get(), SoundSource.BLOCKS, 0.8f, RandomHelper.randomBetween(level.getRandom(), 0.9f, 1.1f));
                return ItemInteractionResult.SUCCESS;
            }
        }
        return super.onUseWithItem(pPlayer, pStack, pHand);
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);
        frequency = pTag.getInt("frequency");
        type = TimeIntervalType.valueOf(pTag.getString("type"));
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putInt("frequency", frequency);
        tag.putString("type", type.name());
    }

    @Override
    public void tick() {
        if (level instanceof ServerLevel serverLevel) {
            if (closeDelay > 0) {
                closeDelay--;
                if (closeDelay == 0) {
                    toggleState(false, type, frequency);
                }
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
        this.inputSignal = inputSignal;
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