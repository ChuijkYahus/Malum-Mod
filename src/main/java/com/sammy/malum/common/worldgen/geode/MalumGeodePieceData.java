package com.sammy.malum.common.worldgen.geode;

import com.mojang.serialization.Codec;
import com.sammy.malum.common.worldgen.sanctuary.SanctuaryGenerationData;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import team.lodestar.lodestone.modules.toolkit.worldgen.MutableBoundingBox;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.sammy.malum.MalumMod.LOGGER;

public record MalumGeodePieceData(Map<BlockPos, BlockState> toPlace) {

    public static final Codec<MalumGeodePieceData> CODEC =
            Codec.unboundedMap(BlockPos.CODEC, BlockState.CODEC)
                    .xmap(
                            MalumGeodePieceData::new,
                            MalumGeodePieceData::toPlace
                    );


    public void save(CompoundTag tag) {
        MalumGeodePieceData.CODEC
                .encodeStart(NbtOps.INSTANCE, this)
                .resultOrPartial(LOGGER::error)
                .ifPresent(p -> tag.put("data", p));
    }

    public static MalumGeodePieceData load(CompoundTag tag) {
        return MalumGeodePieceData.CODEC.parse(NbtOps.INSTANCE, tag.get("data")).resultOrPartial(LOGGER::error).orElse(null);
    }

    public static MalumGeodePieceData filtered(Map<BlockPos, BlockState> blockMap, ChunkPos chunkPos) {
        var copy = new HashMap<>(blockMap);
        for (BlockPos pos : blockMap.keySet()) {
            var filtered = new ChunkPos(pos);
            if (filtered.equals(chunkPos)) {
                continue;
            }
            copy.remove(pos);
        }
        return new MalumGeodePieceData(copy);
    }

    public BoundingBox boundingBox() {
        Collection<BlockPos> blockPos = toPlace.keySet();
        MutableBoundingBox unsafe = new MutableBoundingBox().encapsulate(blockPos);
        for (BlockPos pos : toPlace.keySet()) {
            unsafe.encapsulate(pos);
        }
        return unsafe.toBoundingBox();
    }
}
