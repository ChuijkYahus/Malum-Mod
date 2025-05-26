package com.sammy.malum.common.worldgen.well;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.sammy.malum.registry.common.worldgen.*;
import net.minecraft.core.*;
import net.minecraft.util.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.chunk.*;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.structure.*;
import net.minecraft.world.level.levelgen.structure.pieces.*;

import java.util.Optional;

public class WeepingWellStructure extends Structure {

    public static final MapCodec<WeepingWellStructure> CODEC = RecordCodecBuilder.mapCodec(builder ->
            builder.group(settingsCodec(builder))
                    .apply(builder, WeepingWellStructure::new)
    );

    public WeepingWellStructure(StructureSettings settings) {
        super(settings);
    }

    @Override
    protected Optional<GenerationStub> findGenerationPoint(GenerationContext context) {
        ChunkPos chunkPos = context.chunkPos();
        WorldgenRandom random = context.random();
        RandomState randomState = context.randomState();
        ChunkGenerator chunkGenerator = context.chunkGenerator();
        var levelHeightAccessor = context.heightAccessor();

        if (random.nextFloat() < 0.4f) {
            return Optional.empty();
        }

        int blockX = chunkPos.getBlockX(random.nextInt(16));
        int blockZ = chunkPos.getBlockZ(random.nextInt(16));
        int baseHeight = chunkGenerator.getBaseHeight(blockX, blockZ, Heightmap.Types.WORLD_SURFACE_WG, levelHeightAccessor, randomState);
        int blockY = Mth.clamp(baseHeight - random.nextIntBetweenInclusive(64, 128), 0, 32);
        var position = new BlockPos.MutableBlockPos(blockX, blockY, blockZ);
        int y = extraChecks(context, position);
        if (y == -1) {
            return Optional.empty();
        }
        position.setY(y);
        return Optional.of(new Structure.GenerationStub(position, (b) -> place(context, b, levelHeightAccessor, position.immutable())));
    }

    public void place(GenerationContext context, StructurePiecesBuilder piecesBuilder, LevelHeightAccessor levelHeightAccessor, BlockPos pos) {
        var chunkPos = context.chunkPos();
        int x = SectionPos.sectionToBlockCoord(chunkPos.x);
        int z = SectionPos.sectionToBlockCoord(chunkPos.z);
        var startPos = new BlockPos(x, pos.getY(), z);

        BoundingBox boundingBox = new BoundingBox(
                startPos.getX(), levelHeightAccessor.getMinBuildHeight(), startPos.getZ(),
                startPos.getX() + 15, levelHeightAccessor.getMaxBuildHeight(), startPos.getZ() + 15
        );
        piecesBuilder.addPiece(new WeepingWellStructurePiece(pos, boundingBox));
    }

    private int extraChecks(GenerationContext context, BlockPos pos) {
        NoiseColumn baseColumn = context.chunkGenerator().getBaseColumn(pos.getX(), pos.getZ(), context.heightAccessor(), context.randomState());
        int surfaceLevel = pos.getY();
        while (true) {
            if (context.heightAccessor().isOutsideBuildHeight(surfaceLevel)) {
                return -1;
            }
            BlockState blockState = baseColumn.getBlock(surfaceLevel);
            BlockState floor = baseColumn.getBlock(surfaceLevel - 1);
            boolean isEmptyBlock = blockState.isAir() && blockState.getFluidState().isEmpty();
            boolean isSolidFloor = !floor.isAir() && !floor.canBeReplaced();
            if ((isEmptyBlock && isSolidFloor)) {
                break;
            }
            surfaceLevel--;
        }

        int emptySpace = 0;
        for (int i = 0; i < 20; i++) {
            BlockState blockState = baseColumn.getBlock(surfaceLevel + i);
            if (blockState.isAir() && blockState.getFluidState().isEmpty()) {
                emptySpace++;
            } else {
                break;
            }

        }
        if (emptySpace < 8) {
            return -1;
        }
        return surfaceLevel;
    }

    @Override
    public StructureType<?> type() {
        return MalumStructures.StructureTypes.WEEPING_WELL.get();
    }
}
