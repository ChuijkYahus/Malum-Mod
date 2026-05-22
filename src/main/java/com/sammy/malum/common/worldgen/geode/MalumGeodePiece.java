package com.sammy.malum.common.worldgen.geode;

import com.mojang.serialization.Codec;
import com.sammy.malum.registry.common.worldgen.MalumStructureTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

import static com.sammy.malum.MalumMod.LOGGER;

public class MalumGeodePiece extends StructurePiece {

    protected final MalumGeodePieceData data;

    protected MalumGeodePiece(MalumGeodePieceData data) {
        super(MalumStructureTypes.StructurePieceTypes.GEODE.get(), 0, data.boundingBox());
        this.data = data;
    }

    public MalumGeodePiece(CompoundTag tag) {
        super(MalumStructureTypes.StructurePieceTypes.GEODE.get(), tag);
        this.data = MalumGeodePieceData.load(tag);
    }

    @Override
    protected void addAdditionalSaveData(StructurePieceSerializationContext context, CompoundTag tag) {
        if (data == null) {
            return;
        }
        data.save(tag);
    }

    @Override
    public void postProcess(WorldGenLevel level, StructureManager structureManager, ChunkGenerator generator, RandomSource random, BoundingBox box, ChunkPos chunkPos, BlockPos pos) {
        if (data == null) {
            return;
        }
        Map<BlockPos, BlockState> toPlace = data.toPlace();
        for (BlockPos geodePos : toPlace.keySet()) {
            level.setBlock(geodePos, toPlace.get(geodePos), 2);
        }
    }
}