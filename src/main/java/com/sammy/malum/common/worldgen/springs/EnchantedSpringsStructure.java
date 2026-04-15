package com.sammy.malum.common.worldgen.springs;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import com.sammy.malum.registry.common.worldgen.MalumStructureTypes;
import net.minecraft.core.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.structure.*;
import net.minecraft.world.level.levelgen.structure.pieces.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class EnchantedSpringsStructure extends Structure {

    public static final MapCodec<EnchantedSpringsStructure> CODEC = RecordCodecBuilder.mapCodec(builder ->
            builder.group(settingsCodec(builder))
                    .apply(builder, EnchantedSpringsStructure::new));

    public EnchantedSpringsStructure(StructureSettings settings) {
        super(settings);
    }


    @Override
    protected @NotNull Optional<GenerationStub> findGenerationPoint(GenerationContext context) {
        var chunkPos = context.chunkPos();
        var random = context.random();
        var randomState = context.randomState();
        var chunkGenerator = context.chunkGenerator();
        var levelHeightAccessor = context.heightAccessor();

        int blockX = chunkPos.getBlockX(random.nextInt(16));
        int blockZ = chunkPos.getBlockZ(random.nextInt(16));

        int baseHeight = chunkGenerator.getBaseHeight(blockX, blockZ, Heightmap.Types.WORLD_SURFACE_WG, levelHeightAccessor, randomState);
        var center = new BlockPos(blockX, baseHeight, blockZ);

        int radius = random.nextIntBetweenInclusive(48, 64);
        int springRadius = random.nextIntBetweenInclusive(16, 32);

        var data = new EnchantedSpringsData(center, radius, springRadius);
        return Optional.of(new Structure.GenerationStub(center, (b) -> createGrovePieces(b, context, data)));
    }

    private void createGrovePieces(StructurePiecesBuilder piecesBuilder, GenerationContext context, EnchantedSpringsData config) {
        var levelHeightAccessor = context.heightAccessor();
        var chunkPos = context.chunkPos();
        int radius = SectionPos.blockToSectionCoord(config.radius) + 1;

        for (int chunkX = -radius; chunkX <= radius; chunkX++) {
            for (int chunkZ = -radius; chunkZ <= radius; chunkZ++) {
                ChunkPos offsetChunkPos = new ChunkPos(chunkPos.x + chunkX, chunkPos.z + chunkZ);
                int x = SectionPos.sectionToBlockCoord(offsetChunkPos.x);
                int z = SectionPos.sectionToBlockCoord(offsetChunkPos.z);
                var boundingBox = getChunkBoundingBox(levelHeightAccessor, x, z);
                piecesBuilder.addPiece(new RunicSanctuaryPiece(config, boundingBox));
            }
        }
    }

    @Override
    public @NotNull StructureType<?> type() {
        return MalumStructureTypes.StructureTypes.RUNIC_SANCTUARY.get();
    }

    public BoundingBox getChunkBoundingBox(LevelHeightAccessor level, int x, int z) {
        var chunkStartPos = new BlockPos(x, 0, z);
        int minHeight = level.getMinBuildHeight();
        int maxHeight = level.getMaxBuildHeight();
        return new BoundingBox(
                chunkStartPos.getX(), minHeight, chunkStartPos.getZ(),
                chunkStartPos.getX() + 15, maxHeight, chunkStartPos.getZ() + 15
        );
    }
}