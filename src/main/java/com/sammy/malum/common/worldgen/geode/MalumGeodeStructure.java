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
import org.spongepowered.include.com.google.common.collect.Multimap;
import org.spongepowered.include.com.google.common.collect.Multimaps;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;

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
        int y = placement.sample(random, worldgenContext);
        var center = chunkPos.getBlockAt(random.nextInt(16), y, random.nextInt(16));


        var blockSettings = geodeConfiguration.geodeBlockSettings();
        var anchorSettings = geodeConfiguration.geodeAnchorSettings();
        var crackSettings = geodeConfiguration.geodeCrackSettings();
        var layers = blockSettings.layers();
        double geodeSize = layers.stream().mapToDouble(GeodeLayer::size).sum();
        var normalNoise = NormalNoise.create(random, -4, 1.0);

        var anchorData = getAnchorData(anchorSettings.anchors(), () -> center, random);
        var anchorDistances = calculateDistances(anchorData, normalNoise, random, geodeSize, 1);


        var blocks = new HashMap<GeodeLayer, HashMap<BlockPos, GeodePlacementData>>();
        var crystals = new HashMap<GeodeLayer, HashMap<BlockPos, GeodePlacementData>>();
        var exposedBuddingGeodes = new HashMap<GeodeLayer, HashMap<BlockPos, GeodePlacementData>>();

        for (GeodeLayer layer : layers) {
            blocks.put(layer, new HashMap<>());
            if (layer.hasCrystals()) {
                crystals.put(layer, new HashMap<>());
            }
            if (layer.hasBuddingGeodes()) {
                exposedBuddingGeodes.put(layer, new HashMap<>());
            }
        }
        for (BlockPos pos : anchorDistances.keySet()) {
            var delta = anchorDistances.get(pos);
            if (delta > 1) {
                continue;
            }
            var layer = GeodeLayer.getLayer(blockSettings, delta);
            var map = blocks.get(layer);
            map.put(pos, new GeodePlacementData(layer, random, pos));
        }

        var airPresumably = layers.getFirst();
        var airMap = blocks.get(airPresumably);
        var airState = Blocks.AIR.defaultBlockState();
        var lastLayer = blocks.get(layers.getLast());
        var crackData = getAnchorData(crackSettings.cracks(), () -> getCrackPosition(lastLayer, random), random);
        var crackDistances = calculateDistances(crackData, normalNoise, random, geodeSize, crackSettings.generateCrackChance());
        for (BlockPos pos : crackDistances.keySet()) {
            double crackDelta = crackDistances.get(pos);
            if (crackDelta < 0.8f) {
                airMap.put(pos, new GeodePlacementData(airPresumably, pos, airState));
            }
        }


        for (GeodeLayer layer : layers) {
            var blockMap = blocks.get(layer);
            if (layer.hasBuddingGeodes()) {
                var exposedMap = exposedBuddingGeodes.get(layer);
                var buddingInfo = layer.getBuddingGeodes();

                for (BlockPos pos : blockMap.keySet()) {
                    boolean hasAir = false;
                    for (Direction direction : Direction.values()) {
                        if (airMap.containsKey(pos.relative(direction))) {
                            hasAir = true;
                            break;
                        }
                    }
                    if (hasAir) {
                        var buddingState = buddingInfo.state().getState(random, pos);
                        exposedMap.put(pos, new GeodePlacementData(layer, pos, buddingState));
                    }
                }
            }
            if (layer.hasCrystals()) {
                var clusterMap = crystals.get(layer);
                var crystalInfo = layer.getCrystals();
                for (BlockPos pos : blockMap.keySet()) {
                    if (airMap.containsKey(pos)) {
                        continue;
                    }
                    for (Direction direction : Direction.values()) {
                        var clusterPos = pos.relative(direction);
                        if (!airMap.containsKey(clusterPos)) {
                            continue;
                        }
                        if (clusterMap.containsKey(clusterPos)) {
                            if (random.nextBoolean()) {
                                continue;
                            }
                        }
                        var clusterState = crystalInfo.state().getState(random, clusterPos);
                        if (clusterState.hasProperty(BlockStateProperties.FACING)) {
                            clusterState = clusterState.setValue(BlockStateProperties.FACING, direction);
                        }
                        if (clusterState.hasProperty(GeodeCrystalClusterBlock.AGE)) {
                            clusterState = clusterState.setValue(GeodeCrystalClusterBlock.AGE, random.nextInt(3));
                        }
                        clusterMap.put(clusterPos, new GeodePlacementData(layer, clusterPos, clusterState));
                    }
                }
            }
        }

        HashMap<BlockPos, GeodePlacementData> baked = new HashMap<>();
        for (int i = layers.size() - 1; i >= 0; i--) {
            var layer = layers.get(i);
            var map = blocks.get(layer);
            for (BlockPos pos : map.keySet()) {
                var data = map.get(pos);
                baked.put(pos, data);
            }
        }
        for (GeodeLayer layer : layers) {
            if (layer.hasCrystals()) {
                var data = layer.getCrystals();
                pickAndAddRandom(crystals.get(layer), baked, random, data.amount(), true);
            }

            if (layer.hasBuddingGeodes()) {
                var data = layer.getBuddingGeodes();

                int roll = data.amount().sample(random);
                int count = roll / 2;
                int exposedCount = roll - count;
                pickAndAddRandom(blocks.get(layer), baked, random, count, false);
                pickAndAddRandom(exposedBuddingGeodes.get(layer), baked, random, exposedCount, false);
            }
        }

        return Optional.of(new GenerationStub(center, (b) -> createGeodePieces(b, context, baked, geodeSize)));
    }

    private void pickAndAddRandom(HashMap<BlockPos, GeodePlacementData> map, HashMap<BlockPos, GeodePlacementData> baked, RandomSource random, IntProvider amount, boolean needsAir) {
        int roll = amount.sample(random);
        pickAndAddRandom(map, baked, random, roll, needsAir);
    }

    private void pickAndAddRandom(HashMap<BlockPos, GeodePlacementData> map, HashMap<BlockPos, GeodePlacementData> baked, RandomSource random, int amount, boolean needsAir) {
        var set = map.keySet();
        amount = Math.min(amount, set.size());
        var shuffle = WorldgenHelper.shuffle(set, random);
        for (int i = 0; i < amount; i++) {
            var cluster = shuffle.get(i);
            var entry = map.get(cluster);

            var pos = entry.pos;
            var state = entry.state;
            if (baked.containsKey(pos)) {
                if (baked.get(pos).layer.isAir() != needsAir) {
                    continue;
                }
            }
            baked.put(pos, new GeodePlacementData(entry.layer, pos, state));
        }
    }

    private BlockPos getCrackPosition(HashMap<BlockPos, GeodePlacementData> outerLayer, RandomSource random) {
        var set = outerLayer.keySet();
        if (set.isEmpty()) {
            return BlockPos.ZERO;
        }
        var shuffled = WorldgenHelper.shuffle(set, random);
        return shuffled.getFirst();
    }

    private List<AnchorData> getAnchorData(List<GeodeAnchor> anchors, Supplier<BlockPos> anchorPos, RandomSource random) {
        List<AnchorData> anchorData = new ArrayList<>();
        for (GeodeAnchor anchor : anchors) {
            var offset = anchor.anchorOffset();
            int x = offset.sample(random);
            int y = offset.sample(random);
            int z = offset.sample(random);
            float scale = anchor.scale().sample(random);
            float noise = anchor.noiseIntensity().sample(random);
            var pos = anchorPos.get();
            anchorData.add(new AnchorData(pos.offset(x, y, z), scale, noise));
        }
        return anchorData;
    }

    private HashMap<BlockPos, Double> calculateDistances(List<AnchorData> anchorData, NormalNoise normalnoise, RandomSource randomSource, double geodeSize, double chance) {
        var data = new HashMap<BlockPos, Double>();
        var unbaked = HashMultimap.<BlockPos, Double>create();
        for (AnchorData anchor : anchorData) {
            if (randomSource.nextDouble() > chance) {
                continue;
            }
            int maxDist = Mth.floor(anchor.scale * geodeSize);
            int padding = (int) (maxDist * 1.25f);
            var anchorCenter = anchor.pos;
            var bottomLeft = anchorCenter.offset(-padding, -padding, -padding);
            var topRight = anchorCenter.offset(padding, padding, padding);
            for (BlockPos pos : BlockPos.betweenClosed(bottomLeft, topRight)) {
                double distance = Math.sqrt(pos.distSqr(anchorCenter)) / maxDist;
                double noise = normalnoise.getValue(pos.getX(), pos.getY(), pos.getZ()) * anchor.noise;
                double value = distance + distance * noise;
                unbaked.put(pos.immutable(), value);
            }
        }
        for (BlockPos pos : unbaked.keys()) {
            double min = unbaked.get(pos).stream()
                    .mapToDouble(d -> d).min().orElseThrow();
            data.put(pos, min);
        }
        return data;
    }

    private void createGeodePieces(StructurePiecesBuilder piecesBuilder, GenerationContext context, HashMap<BlockPos, GeodePlacementData> blockMap, double geodeSize) {
        int radius = SectionPos.blockToSectionCoord(geodeSize) + 3;

        HashMap<ChunkPos, HashMap<BlockPos, BlockState>> data = new HashMap<>();

        for (BlockPos pos : blockMap.keySet()) {
            var offsetChunkPos = new ChunkPos(pos);
            if (!data.containsKey(offsetChunkPos)) {
                data.put(offsetChunkPos, new HashMap<>());
            }
            var map = data.get(offsetChunkPos);
            var entry = blockMap.get(pos);
            map.put(pos, entry.state());
        }

        var chunkPos = context.chunkPos();
        for (int chunkX = -radius; chunkX <= radius; chunkX++) {
            for (int chunkZ = -radius; chunkZ <= radius; chunkZ++) {
                var offsetChunkPos = new ChunkPos(chunkPos.x + chunkX, chunkPos.z + chunkZ);
                if (!data.containsKey(offsetChunkPos)) {
                    continue;
                }
                var map = data.get(offsetChunkPos);
                if (map.isEmpty()) {
                    continue;
                }
                piecesBuilder.addPiece(new MalumGeodePiece(new MalumGeodePieceData(map)));
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