package com.sammy.malum.common.worldgen.geode;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.sammy.malum.common.worldgen.geode.config.GeodeLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.GeodeLayerSettings;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import team.lodestar.lodestone.modules.toolkit.worldgen.MutableBoundingBox;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.sammy.malum.MalumMod.LOGGER;

public record MalumGeodeData(HashMap<BlockPos, Double> data) {

    public MalumGeodeData() {
        this(new HashMap<>());
    }

    public void push(BlockPos pos, double value) {
        if (value > 1) {
            return;
        }
        if (data.containsKey(pos)) {
            var existingValue = data.get(pos);
            if (existingValue > value) {
                return;
            }
        }
        data.put(pos, value);
    }

    public HashMap<ChunkPos, MalumGeodeData> bake() {
        var result = new HashMap<ChunkPos, MalumGeodeData>();
        for (Map.Entry<BlockPos, Double> entry : data.entrySet()) {
            var pos = entry.getKey();
            var chunkPos = new ChunkPos(pos);
            if (!result.containsKey(chunkPos)) {
                result.put(chunkPos, new MalumGeodeData());
            }
            var sorted = result.get(chunkPos);
            var value = entry.getValue();
            sorted.push(pos, value);
        }
        return result;
    }
}
