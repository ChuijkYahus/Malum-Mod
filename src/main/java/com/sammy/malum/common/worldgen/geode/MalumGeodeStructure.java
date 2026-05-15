package com.sammy.malum.common.worldgen.geode;

import com.google.common.collect.HashMultimap;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.sammy.malum.common.block.geode.GeodeCrystalClusterBlock;
import com.sammy.malum.common.worldgen.WorldgenHelper;
import com.sammy.malum.common.worldgen.geode.config.GeodeAnchor;
import com.sammy.malum.common.worldgen.geode.config.GeodeLayer;
import com.sammy.malum.common.worldgen.geode.config.MalumGeodeConfiguration;
import com.sammy.malum.registry.common.worldgen.MalumStructureTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.SectionPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import org.jetbrains.annotations.NotNull;

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
        var crackSettings = geodeConfiguration.geodeCrackSettings();
        var layers = blockSettings.layers();
        double geodeSize = layers.stream().mapToDouble(GeodeLayer::size).sum();
        var normalNoise = NormalNoise.create(random, -4, 1.0);

        var anchorData = getAnchorData(anchorSettings.anchors(), center, random);
        var crackData = getAnchorData(crackSettings.cracks(), center, random);

        var anchorDistances = calculateDistances(anchorData, normalNoise, geodeSize);
        var crackDistances = calculateDistances(crackData, normalNoise, geodeSize);
        var anchorEntries = anchorDistances.data();
        var crackEntries = crackDistances.data();

        var blocksByLayer = new HashMap<GeodeLayer, HashMap<BlockPos, GeodePlacementData>>();
        var clustersByLayer = new HashMap<GeodeLayer, HashMap<BlockPos, GeodePlacementData>>();

        for (GeodeLayer layer : layers) {
            blocksByLayer.put(layer, new HashMap<>());
            if (layer.hasCrystals()) {
                clustersByLayer.put(layer, new HashMap<>());
            }
        }
        var airPresumably = layers.getFirst();
        for (BlockPos pos : anchorEntries.keySet()) {
            var delta = anchorEntries.get(pos);
            var layer = GeodeLayer.getLayer(blockSettings, delta);
            var map = blocksByLayer.get(layer);
            if (crackEntries.containsKey(pos)) {
                double crackDelta = crackEntries.get(pos);
                if (crackDelta < 0.5f) {
                    map.put(pos, new GeodePlacementData(airPresumably, pos, Blocks.AIR.defaultBlockState()));
                    continue;
                }
            }
            map.put(pos, new GeodePlacementData(layer, random, pos));
        }
        for (GeodeLayer layer : layers) {
            var buddingOptional = layer.buddingGeodes();
            var map = blocksByLayer.get(layer);
            if (buddingOptional.isPresent()) {
                for (BlockPos pos : map.keySet()) {
                    var data = map.get(pos);
                }
            }
            if (layer.hasCrystals()) {
                var clusterMap = clustersByLayer.get(layer);
                var info = layer.getCrystals();
                for (BlockPos pos : map.keySet()) {
                    if (map.get(pos).state.isAir()) {
                        continue;
                    }
                    for (Direction direction : Direction.values()) {
                        var clusterPos = pos.relative(direction);

                        if (!anchorEntries.containsKey(clusterPos)) {
                            continue;
                        }
                        if (clusterMap.containsKey(pos)) {
                            if (random.nextBoolean()) {
                                continue;
                            }
                        }
                        var clusterDelta = anchorEntries.get(clusterPos);
                        var clusterLayer = GeodeLayer.getLayer(blockSettings, clusterDelta);
                        if (!clusterLayer.equals(airPresumably)) {
                            continue;
                        }
                        var clusterState = info.state().getState(random, clusterPos);
                        if (clusterState.hasProperty(BlockStateProperties.FACING)) {
                            clusterState = clusterState.setValue(BlockStateProperties.FACING, direction);
                        }
                        if (clusterState.hasProperty(GeodeCrystalClusterBlock.AGE)) {
                            clusterState = clusterState.setValue(GeodeCrystalClusterBlock.AGE, random.nextInt(3));
                        }
                        clusterMap.put(pos, new GeodePlacementData(layer, clusterPos, clusterState));
                    }
                }
            }
        }

        HashMap<BlockPos, GeodePlacementData> baked = new HashMap<>();
        for (int i = layers.size() - 1; i >= 0; i--) {
            var layer = layers.get(i);
            var map = blocksByLayer.get(layer);
            for (BlockPos pos : map.keySet()) {
                var data = map.get(pos);
                baked.put(pos, data);
            }
        }
        for (GeodeLayer layer : layers) {
            if (layer.hasCrystals()) {
                var data = layer.getCrystals();
                var crystals = clustersByLayer.get(layer);
                var set = crystals.keySet();
                var shuffle = WorldgenHelper.shuffle(set, random);
                var amount = data.amount();
                int roll = amount.sample(random);
                roll = Math.min(roll, shuffle.size());
                for (int i = 0; i < roll; i++) {
                    var cluster = shuffle.get(i);
                    var clusterData = crystals.get(cluster);
                    var pos = clusterData.pos;
                    var state = clusterData.state;
                    if (baked.containsKey(pos)) {
                        if (!baked.get(pos).layer.equals(airPresumably)) {
                            continue;
                        }
                    }
                    baked.put(pos, new GeodePlacementData(layer, pos, state));
                }
            }
        }

        return Optional.of(new GenerationStub(center, (b) -> createGeodePieces(b, context, baked, geodeSize)));
    }


    private List<AnchorData> getAnchorData(List<GeodeAnchor> anchors, BlockPos center, RandomSource random) {
        List<AnchorData> anchorData = new ArrayList<>();
        for (GeodeAnchor anchor : anchors) {
            var offset = anchor.anchorOffset();
            int x = offset.sample(random);
            int y = offset.sample(random);
            int z = offset.sample(random);
            float scale = anchor.scale().sample(random);
            float noise = anchor.noiseIntensity().sample(random);
            anchorData.add(new AnchorData(center.offset(x, y, z), scale, noise));
        }
        return anchorData;
    }

    private MalumGeodeData calculateDistances(List<AnchorData> anchorData, NormalNoise normalnoise, double geodeSize) {
        var data = new MalumGeodeData();
        for (AnchorData anchor : anchorData) {
            int maxDist = Mth.floor(anchor.scale * geodeSize);
            int padding = (int) (maxDist * 1.25f);
            var anchorCenter = anchor.pos;
            var bottomLeft = anchorCenter.offset(-padding, -padding, -padding);
            var topRight = anchorCenter.offset(padding, padding, padding);
            for (BlockPos pos : BlockPos.betweenClosed(bottomLeft, topRight)) {
                double distance = Math.sqrt(pos.distSqr(anchorCenter)) / maxDist;
                double noise = normalnoise.getValue(pos.getX(), pos.getY(), pos.getZ()) * anchor.noise;
                double value = distance + distance * noise;
                data.push(pos.immutable(), value);
            }
        }
        return data;
    }

    private void createGeodePieces(StructurePiecesBuilder piecesBuilder, GenerationContext context, HashMap<BlockPos, GeodePlacementData> blockMap, double geodeSize) {
        int radius = SectionPos.blockToSectionCoord(geodeSize) + 3;

        var chunkPos = context.chunkPos();
        for (int chunkX = -radius; chunkX <= radius; chunkX++) {
            for (int chunkZ = -radius; chunkZ <= radius; chunkZ++) {
                var offsetChunkPos = new ChunkPos(chunkPos.x + chunkX, chunkPos.z + chunkZ);
                var pieceData = MalumGeodePieceData.filtered(blockMap, offsetChunkPos);
                if (pieceData.toPlace().isEmpty()) {
                    continue;
                }
                piecesBuilder.addPiece(new MalumGeodePiece(pieceData));
            }
        }
    }

    @Override
    public @NotNull StructureType<?> type() {
        return MalumStructureTypes.StructureTypes.GEODE.get();
    }

    private record AnchorData(BlockPos pos, float scale, float noise) {
    }

    public record GeodePlacementData(GeodeLayer layer, BlockPos pos, BlockState state) {

        public GeodePlacementData(GeodeLayer layer, RandomSource randomSource, BlockPos pos) {
            this(layer, pos, layer.block().getState(randomSource, pos));
        }
    }
}