package com.sammy.malum.common.worldgen.springs;

import com.sammy.malum.common.worldgen.WorldgenHelper;
import com.sammy.malum.registry.common.worldgen.MalumStructureTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.XoroshiroRandomSource;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.synth.ImprovedNoise;
import org.jetbrains.annotations.NotNull;
import team.lodestar.lodestone.modules.core.easing.Easing;

import java.util.ArrayList;
import java.util.List;

public class RunicSanctuaryPiece extends StructurePiece {
    protected EnchantedSpringsData springsData;

    protected RunicSanctuaryPiece(EnchantedSpringsData springsData, BoundingBox boundingBox) {
        super(MalumStructureTypes.StructurePieceTypes.RUNIC_SANCTUARY.get(), 0, boundingBox);
        this.springsData = springsData;
    }

    public RunicSanctuaryPiece(CompoundTag tag) {
        super(MalumStructureTypes.StructurePieceTypes.RUNIC_SANCTUARY.get(), tag);
        this.springsData = EnchantedSpringsData.load(tag);
    }

    @Override
    protected void addAdditionalSaveData(@NotNull StructurePieceSerializationContext pContext, @NotNull CompoundTag pTag) {
        springsData.save(pTag);
    }

    @Override
    public void postProcess(WorldGenLevel level, @NotNull StructureManager structureManager, @NotNull ChunkGenerator generator, @NotNull RandomSource random, @NotNull BoundingBox box, @NotNull ChunkPos chunkPos, @NotNull BlockPos pos) {
//        var unsafeBoundingBox = new UnsafeBoundingBox();
        var mutable = new BlockPos.MutableBlockPos();

        var center = springsData.getCenter();
        int centerHeight = level.getHeight(Heightmap.Types.WORLD_SURFACE_WG, center.getX(), center.getZ()) + 5;

        var noiseSampler = new ImprovedNoise(new XoroshiroRandomSource(level.getSeed()));

        record FeatureData(float heightDelta, BlockPos pos) {

        }
        List<FeatureData> featureData = new ArrayList<>();
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int blockX = chunkPos.getBlockX(x);
                int blockZ = chunkPos.getBlockZ(z);
                float xOffset = center.getX() - blockX;
                float zOffset = center.getZ() - blockZ;
                float dist = Mth.sqrt(xOffset * xOffset + zOffset * zOffset);
                float delta = 1 - (dist / springsData.radius);

                int height = level.getHeight(Heightmap.Types.WORLD_SURFACE, blockX, blockZ);
                mutable.set(blockX, height, blockZ);

                float noise = (float) WorldgenHelper.getNoise(noiseSampler, blockX, blockZ, 0.075f);
                float displaced = delta * noise;
                if (displaced < 0f) {
                    continue;
                }
                if (level.getBlockState(mutable).canBeReplaced()) {
                    level.setBlock(mutable, Blocks.AIR.defaultBlockState(), 2);
                }

                displaced = Mth.clamp(displaced, 0, 1);
                int springHeight = 14;
                int raisedAmount = Mth.floor(Easing.QUINTIC_OUT.lerp(displaced, 0, springHeight));
                float raisedDelta = raisedAmount / (float) springHeight;
                int desiredAdjustment = centerHeight + raisedAmount - height;

                int adjustment = Math.round(displaced * desiredAdjustment);
                mutable.set(blockX, height-1, blockZ);
                shift(level, mutable, adjustment);

                BlockPos posForFeature = mutable.relative(Direction.UP, adjustment);
                featureData.add(new FeatureData(raisedDelta, posForFeature));
            }
        }

//        var registryAccess = level.registryAccess().registryOrThrow(Registries.CONFIGURED_FEATURE);
//        var holder = registryAccess.getHolder(MalumFeatures.ConfiguredFeatures.RUNEWOOD_TREE).orElseThrow().value();
//        int features = Easing.SINE_IN_OUT.asValueDistribution(random.nextFloat(), 4, 12);
//        features = Math.min(features, featureData.size());
//        if (features > 0) {
//            featureData = WorldgenHelper.shuffle(featureData, random);
//            for (int i = 0; i < features; i++) {
//                BlockPos position = featureData.get(i);
//                level.setBlock(position, Blocks.RED_WOOL.defaultBlockState(), 2);
//                holder.place(level, generator, random, position);
//            }
//        }
    }

    public static void shift(WorldGenLevel level, BlockPos startPos, int offset) {
        int depth = Mth.abs(offset*3);
        BlockState[] states = new BlockState[depth];

        var mutable = startPos.mutable();
        for (int i = 0; i < depth; i++) {
            states[i] = level.getBlockState(mutable);
            if (offset < 0) {
                level.setBlock(mutable, Blocks.AIR.defaultBlockState(), 2);
            }
            mutable.move(Direction.DOWN);
        }

        mutable.setY(startPos.getY() + offset);
        for (int i = 0; i < depth; i++) {
            level.setBlock(mutable, states[i], 2);
            mutable.move(Direction.DOWN);
        }
    }
}