package com.sammy.malum.common.worldgen.geode;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import team.lodestar.lodestone.modules.toolkit.codec.LodestoneCodecs;
import team.lodestar.lodestone.modules.toolkit.worldgen.MutableBoundingBox;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.sammy.malum.MalumMod.LOGGER;

public record MalumGeodePieceData(Map<BlockPos, BlockState> toPlace) {

    public static final Codec<MalumGeodePieceData> CODEC =
            Codec.unboundedMap(LodestoneCodecs.BLOCK_POS, BlockState.CODEC)
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

    public BoundingBox boundingBox() {
        Collection<BlockPos> blockPos = toPlace.keySet();
        MutableBoundingBox unsafe = new MutableBoundingBox().encapsulate(blockPos);
        for (BlockPos pos : toPlace.keySet()) {
            unsafe.encapsulate(pos);
        }
        return unsafe.toBoundingBox();
    }
}
