package com.sammy.malum.common.worldgen.geode;

import com.google.common.collect.HashMultimap;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.sammy.malum.common.worldgen.WorldgenHelper;
import com.sammy.malum.common.worldgen.geode.config.GeodeAnchorSettings;
import com.sammy.malum.common.worldgen.geode.config.GeodeLayer;
import com.sammy.malum.common.worldgen.geode.config.MalumGeodeConfiguration;
import com.sammy.malum.registry.common.worldgen.MalumStructureTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.SectionPos;
import net.minecraft.util.Mth;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.include.com.google.common.collect.Multimap;

import java.util.*;

import static net.minecraft.core.SectionPos.sectionToBlockCoord;

public class MalumGeodeStructure extends Structure {

    public static final MapCodec<MalumGeodeStructure> CODEC = RecordCodecBuilder.mapCodec(
            builder -> builder.group(settingsCodec(builder),
                            MalumGeodeConfiguration.CODEC.fieldOf("geode_config").forGetter(MalumGeodeStructure::getGeodeConfiguration),
                            HeightProvider.CODEC.fieldOf("placement").forGetter(MalumGeodeStructure::getPlacement)
                    )
                    .apply(builder, MalumGeodeStructure::new)
    );

    private final MalumGeodeConfiguration geodeConfiguration;
    private final HeightProvider placement;

    public MalumGeodeStructure(StructureSettings settings, MalumGeodeConfiguration geodeConfiguration, HeightProvider placement) {
        super(settings);
        this.geodeConfiguration = geodeConfiguration;
        this.placement = placement;
    }

    public MalumGeodeConfiguration getGeodeConfiguration() {
        return geodeConfiguration;
    }

    public HeightProvider getPlacement() {
        return placement;
    }

    @Override
    protected @NotNull Optional<GenerationStub> findGenerationPoint(GenerationContext context) {

        var chunkPos = context.chunkPos();
        var random = context.random();
        var levelHeightAccessor = context.heightAccessor();
        var worldgenContext = new WorldGenerationContext(context.chunkGenerator(), levelHeightAccessor);

        var center = chunkPos.getBlockAt(random.nextInt(16), placement.sample(random, worldgenContext), random.nextInt(16));


        var blockSettings = geodeConfiguration.geodeBlockSettings();
        var anchorSettings = geodeConfiguration.geodeAnchorSettings();
        var layers = blockSettings.layers();
        double geodeSize = layers.stream().mapToDouble(GeodeLayer::size).sum();
        var normalnoise = NormalNoise.create(random, -4, 1.0);
        var crackPositions = new ArrayList<BlockPos>();

        record AnchorData(BlockPos pos, float scale, float noise) {
        }

        var anchorData = new ArrayList<AnchorData>();

        for (GeodeAnchorSettings.GeodeAnchor anchor : anchorSettings.anchors()) {
            var offset = anchor.anchorOffset();
            int x = offset.sample(random);
            int y = offset.sample(random);
            int z = offset.sample(random);
            float scale = anchor.scale().sample(random);
            float noise = anchor.noiseIntensity().sample(random);
            anchorData.add(new AnchorData(center.offset(x, y, z), scale, noise));
        }

        var geodeData = new MalumGeodeData();
        for (AnchorData anchor : anchorData) {
            int maxDist = Mth.floor(anchor.scale * geodeSize);
            var anchorCenter = anchor.pos;
            var bottomLeft = anchorCenter.offset(-maxDist, -maxDist, -maxDist);
            var topRight = anchorCenter.offset(maxDist, maxDist, maxDist);
            for (BlockPos pos : BlockPos.betweenClosed(bottomLeft, topRight)) {
                double distance = Math.sqrt(pos.distSqr(anchorCenter)) / maxDist;
                double noise = normalnoise.getValue(pos.getX(), pos.getY(), pos.getZ()) * anchor.noise;
                double value = distance + distance * noise;
                geodeData.push(pos.immutable(), value);
            }
        }
        var entries = geodeData.data();
        var blockMap = new HashMap<BlockPos, BlockState>();
        var possibleClusterPositions = HashMultimap.<GeodeLayer, BlockPos>create();
        for (BlockPos pos : entries.keySet()) {
            var delta = entries.get(pos);
            var layer = GeodeLayer.getLayer(blockSettings, delta);
            blockMap.put(pos, layer.block().getState(random, pos));
            if (!layer.hasCrystals()) {
                continue;
            }
            for (int i = 0; i < 6; i++) {
                var direction = Direction.from3DDataValue(i);

                possibleClusterPositions.put(layer, pos.relative(direction));
            }
        }

        for (GeodeLayer layer : layers) {
            var optional = layer.crystalClusters();
            if (optional.isEmpty()) {
                continue;
            }
            for (BlockPos pos : blockMap.keySet()) {
                possibleClusterPositions.remove(layer, pos);
            }
            var data = optional.get();
            var set = possibleClusterPositions.get(layer);
            var shuffle = WorldgenHelper.shuffle(set, random);
            var amount = data.getSecond();
            int roll = amount.sample(random);
            roll = Math.min(roll, shuffle.size());
            for (int i = 0; i < roll; i++) {
                var clusterPos = shuffle.get(i);
                var state = data.getFirst().getState(random, clusterPos);
                if (state.hasProperty(BlockStateProperties.FACING)) {
                    var possibleDirections = new ArrayList<Direction>();
                    for (int j = 0; j < 6; j++) {
                        var direction = Direction.from3DDataValue(j);
                        var supportingPos = clusterPos.relative(direction.getOpposite());
                        if (blockMap.containsKey(supportingPos)) {
                            possibleDirections.add(direction);
                        }
                    }
                    var direction = possibleDirections.get(random.nextInt(possibleDirections.size()-1));
                    state.setValue(BlockStateProperties.FACING, direction);
                    blockMap.put(clusterPos, state);
                }

            }
        }

        return Optional.of(new GenerationStub(center, (b) -> createGeodePieces(b, context, blockMap, geodeSize)));
    }

    private void createGeodePieces(StructurePiecesBuilder piecesBuilder, GenerationContext context, HashMap<BlockPos, BlockState> blockMap, double geodeSize) {
        int radius = SectionPos.blockToSectionCoord(geodeSize) + 1;

        var chunkPos = context.chunkPos();
        for (int chunkX = -radius; chunkX <= radius; chunkX++) {
            for (int chunkZ = -radius; chunkZ <= radius; chunkZ++) {
                var offsetChunkPos = new ChunkPos(chunkPos.x + chunkX, chunkPos.z + chunkZ);
                var pieceData = MalumGeodePieceData.filtered(blockMap, offsetChunkPos);
                piecesBuilder.addPiece(new MalumGeodePiece(pieceData));
            }
        }
    }

    @Override
    public @NotNull StructureType<?> type() {
        return MalumStructureTypes.StructureTypes.GEODE.get();
    }

}