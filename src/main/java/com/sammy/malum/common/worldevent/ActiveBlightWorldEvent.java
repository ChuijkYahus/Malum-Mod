package com.sammy.malum.common.worldevent;

import com.sammy.malum.common.worldgen.blight.*;
import com.sammy.malum.core.systems.spirit.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.visual_effects.networked.*;
import com.sammy.malum.visual_effects.networked.blight.*;
import net.minecraft.core.*;
import net.minecraft.nbt.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.*;
import team.lodestar.lodestone.systems.particle.data.color.*;
import team.lodestar.lodestone.systems.worldevent.*;
import team.lodestar.lodestone.systems.worldgen.LodestoneBlockFiller;

import java.util.*;

public abstract class ActiveBlightWorldEvent extends WorldEventInstance {
    protected List<Integer> intensity = new ArrayList<>();
    protected int frequency;
    protected int delay;
    protected int timer;
    protected BlockPos position;

    public ActiveBlightWorldEvent(WorldEventType type) {
        super(type);
    }

    public ActiveBlightWorldEvent setData(List<Integer> intensity, int frequency, int delay) {
        this.intensity.addAll(intensity);
        this.frequency = frequency;
        this.delay = delay;
        return this;
    }

    public ActiveBlightWorldEvent setPosition(BlockPos position) {
        this.position = position;
        return this;
    }

    @Override
    public void tick(Level level) {
        if (delay > 0) {
            delay--;
            return;
        }
        if (timer == 0) {
            timer = frequency;
            if (intensity.isEmpty()) {
                end(level);
                return;
            }
            createBlight((ServerLevel) level, intensity.removeFirst());
        }
        timer--;
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compoundTag) {
        compoundTag.putInt("timer", timer);
        compoundTag.putInt("frequency", frequency);
        compoundTag.putInt("delay", delay);
        compoundTag.put("position", NbtUtils.writeBlockPos(position));
        ListTag intensityList = new ListTag();
        for (Integer i : intensity) {
            intensityList.add(IntTag.valueOf(i));
        }
        compoundTag.put("intensity", intensityList);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compoundTag) {
        timer = compoundTag.getInt("timer");
        frequency = compoundTag.getInt("frequency");
        delay = compoundTag.getInt("delay");
        position = NbtUtils.readBlockPos(compoundTag, "position").orElseThrow();
        intensity.clear();
        ListTag intensityList = compoundTag.getList("intensity", Tag.TAG_INT);
        for (int i = 0; i < intensityList.size(); i++) {
            intensity.add(intensityList.getInt(i));
        }
    }

    public void createBlight(ServerLevel level, int intensity) {
        LodestoneBlockFiller filler = BlightFeature.generateBlight(level, position, intensity);
        filler.fill(level);
        createBlightVFX(level, position, filler, MalumSpiritTypes.ARCANE_SPIRIT);
        level.playSound(null, position, MalumSoundEvents.MAJOR_BLIGHT_MOTIF.get(), SoundSource.BLOCKS, 1f, 1.8f);
    }

    public static void createBlightVFX(ServerLevel level, BlockPos sourcePos, LodestoneBlockFiller filler, MalumSpiritType spiritType) {
        for (Map.Entry<BlockPos, LodestoneBlockFiller.BlockStateEntry> entry : filler.getLayer(BlightFeature.BLIGHT).entrySet()) {
            if (entry.getValue().getState().is(MalumTags.BlockTags.BLIGHTED_BLOCKS)) {
                MalumParticleEffectTypes.BLIGHTING_MIST.createEffect(entry.getKey())
                        .customData(new BlightPropagationParticleEffect.BlightPropagationEffectData(sourcePos))
                        .color(spiritType)
                        .spawn(level);
            }
        }
    }
}