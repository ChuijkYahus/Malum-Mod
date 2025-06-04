package com.sammy.malum.common.worldgen.blight;

import com.google.common.collect.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.block.*;
import com.sammy.malum.visual_effects.networked.blight.*;
import net.minecraft.core.*;
import net.minecraft.server.level.*;
import net.minecraft.sounds.*;
import net.minecraft.util.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.synth.*;
import net.minecraft.world.level.material.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.helpers.block.*;
import team.lodestar.lodestone.systems.easing.*;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class BlightFeature extends Feature<NoneFeatureConfiguration> {

    private static final PerlinSimplexNoise COVERING_NOISE = new PerlinSimplexNoise(new WorldgenRandom(new LegacyRandomSource(1234L)), ImmutableList.of(0));

    public BlightFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        var pos = context.origin();
        generateBlight(level, pos,true, 8).place(level);
        return true;
    }

    public static LodestoneWorldgenBuilder generateBlightWithVisuals(WorldGenLevel level, BlockPos pos, boolean allowScarstone, int radius) {
        boolean generateScarstone = allowScarstone && level.getRandom().nextFloat() < 0.08f;
        var blight = generateBlight(level, pos, generateScarstone, radius);
        if (level instanceof ServerLevel realLevel) {
            createBlightVFX(realLevel, pos, blight);
            level.playSound(null, pos, MalumSoundEvents.BLIGHT_PROPAGATION.get(), SoundSource.BLOCKS, 1f, 1f);
            if (generateScarstone) {
                var scarstonePos = blight.getLayer(1).getRandomEntries(1).getFirst().position();
                level.playSound(null, scarstonePos, MalumSoundEvents.SCARSTONE_PROPAGATION.get(), SoundSource.BLOCKS, 2f, 1f);
            }
        }
        return blight;
    }

    public static void createBlightVFX(ServerLevel level, BlockPos sourcePos, BlightFeature.LodestoneWorldgenBuilder blight) {
        MalumParticleEffectTypes.BLIGHT_PROPAGATION.createEffect(sourcePos)
                .customData(new BlightParticleEffect.BlightEffectData(blight.getAffectedArea(0)))
                .spawn(level);
        MalumParticleEffectTypes.BLIGHT_PLANT_GROWTH.createEffect(sourcePos)
                .customData(new BlightParticleEffect.BlightEffectData(blight.getAffectedArea(1)))
                .spawn(level);
    }

    public record BlightGenerationData(LodestoneWorldgenBuilder builder, BlockPos blightCenter, Optional<BlockPos> scarstoneCenter) {};

    public static LodestoneWorldgenBuilder generateBlight(WorldGenLevel level, BlockPos pos, boolean generateScarstone, int radius) {
        var random = level.getRandom();

        var builder = LodestoneWorldgenBuilder.create().addAdditionalPlacement(BlightFeature::cleanupFoliage);
        if (generateScarstone) {
            int offset = (int) (radius*0.8f);
            int xOffset = RandomHelper.randomBetween(random, Easing.CIRC_OUT, offset/2, offset*2) * (random.nextBoolean() ? 1 : -1);
            int zOffset = RandomHelper.randomBetween(random, Easing.CIRC_OUT, offset/2, offset*2) * (random.nextBoolean() ? 1 : -1);
            var scarstonePos = pos.offset(xOffset, 0, zOffset);
            var extraBlight = generateBlight(level, scarstonePos, false, radius);
            var scarstone = ScarstoneFeature.generateScarstone(level, scarstonePos, (int) (radius*0.7f));
            builder.merge(extraBlight).merge(scarstone);
        }
        var blightLayer = builder.createLayer();
        var floraLayer = builder.createLayer();
        var coveringLayer = builder.createLayer();


        List<BlockPos> blightedArea = fetchCoveringPositions(level, pos, radius);
        for (BlockPos blockPos : blightedArea) {
            BlockState state = level.getBlockState(blockPos);
            if (state.is(MalumTags.BlockTags.BLIGHT_REPLACEABLE)) {
                blightLayer.add(blockPos, MalumBlocks.BLIGHTED_EARTH.get());
            }
        }

        ArrayList<BlockPos> floraPositions = new ArrayList<>(blightedArea);
        if (!floraPositions.isEmpty()) {
            Collections.shuffle(floraPositions);
            int floraCount = Math.min(random.nextInt(1, radius * 4 + 1), floraPositions.size() - 1);
            boolean hasSoulwood = false;
            for (BlockPos blockPos : floraPositions) {
                BlockPos above = blockPos.above();
                BlockState state = level.getBlockState(above);
                if (!state.getFluidState().isEmpty()) {
                    continue;
                }
                if (!state.canBeReplaced()) {
                    continue;
                }
                if (state.is(MalumTags.BlockTags.BLIGHTED_PLANTS)) {
                    continue;
                }
                Block block;
                if (radius > 3 && !hasSoulwood && random.nextFloat() < 0.1f) {
                    block = MalumBlocks.SOULWOOD_SAPLING.get();
                    hasSoulwood = true;
                } else if (random.nextFloat() < 0.4f) {
                    block = random.nextFloat() < 0.2f ? MalumBlocks.BLIGHTPEARL.get() : MalumBlocks.BLIGHTROOT.get();
                } else {
                    block = MalumBlocks.BLIGHTED_GROWTH.get();
                }
                floraLayer.add(above, block);
                floraCount--;
                if (floraCount == 0) {
                    break;
                }
            }
        }
        List<BlockPos> coveringArea = fetchCoveringPositions(level, pos, radius + 3);
        if (!coveringArea.isEmpty()) {
            Collections.shuffle(coveringArea);
            int coveringCount = Math.min(random.nextInt(1, 8 + radius * 8 + 1), coveringArea.size() - 1);
            for (BlockPos blockPos : coveringArea) {
                BlockState state = level.getBlockState(blockPos);
                if (!state.is(MalumTags.BlockTags.BLIGHT_REPLACEABLE)) {
                    continue;
                }
                if (blightLayer.containsKey(blockPos)) {
                    continue;
                }
                var above = blockPos.above();
                boolean isWaterLogged = level.getBlockState(above).getFluidState().is(Fluids.WATER);
                var covering = MalumBlocks.BLIGHT.get().defaultBlockState()
                        .setValue(MultifaceBlock.getFaceProperty(Direction.DOWN), true)
                        .setValue(BlockStateProperties.WATERLOGGED, isWaterLogged);
                coveringLayer.add(above, covering);
                coveringCount--;
                if (coveringCount == 0) {
                    break;
                }
            }
        }

        return builder;
    }

    public static void cleanupFoliage(WorldGenLevel level, LodestoneWorldgenBuilderEntry entry) {
        BlockPos.MutableBlockPos mutable = entry.position().mutable();
        for (int i = 0; i < 3; i++) {
            mutable.move(Direction.UP);
            BlockState aboveState = level.getBlockState(mutable);
            if (aboveState.getFluidState().isEmpty() && aboveState.is(MalumTags.BlockTags.BLIGHT_REMOVABLE)) {
                level.setBlock(mutable, Blocks.AIR.defaultBlockState(), 19);
                if (level instanceof Level realLevel) {
                    BlockStateHelper.updateState(realLevel, mutable);
                }
            }
        }
    }

    public static List<BlockPos> fetchCoveringPositions(ServerLevelAccessor level, BlockPos center, int radius) {
        return fetchCoveringPositions(level, center, radius, BlightFeature::canBeRemoved).stream().filter(
                        p -> {
                            BlockState above = level.getBlockState(p.above());
                            return above.canBeReplaced() || above.is(MalumTags.BlockTags.BLIGHT_REMOVABLE);
                        })
                .collect(Collectors.toList());
    }

    public static List<BlockPos> fetchCoveringPositions(ServerLevelAccessor level, BlockPos center, int radius, Predicate<BlockState> statePredicate) {
        List<BlockPos> positions = new ArrayList<>();
        int x = center.getX();
        int z = center.getZ();
        var mutable = new BlockPos.MutableBlockPos();

        int verticalRange = 6;
        float limit = Mth.sqrt(radius * radius + radius * radius);
        for (int i = -radius; i <= radius; i++) {
            for (int j = -radius; j <= radius; j++) {
                int offsetX = x + i;
                int offsetZ = z + j;
                float distance = Mth.sqrt(i * i + j * j);
                double theta = Math.toDegrees(Math.atan2(i, j)) * 0.01f;
                double noise = (COVERING_NOISE.getValue(x * 10000 + theta, z * 10000 + theta, true) + 1) / 2;
                double threshold = Easing.SINE_IN_OUT.clamped(noise, 0.5f, 2) * radius * (limit - distance) / limit;
                if (distance <= threshold) {
                    mutable.set(offsetX, center.getY(), offsetZ);
                    for (int k = 0; k < verticalRange; k++) {
                        if (!level.isStateAtPosition(mutable, statePredicate)) {
                            mutable.move(Direction.UP);
                        }
                    }
                    for (int k = 0; k <= verticalRange * 2; k++) {
                        if (level.isStateAtPosition(mutable, statePredicate)) {
                            mutable.move(Direction.DOWN);
                        }
                    }
                    var state = level.getBlockState(mutable);
                    if (state.isFaceSturdy(level, mutable, Direction.UP)) {
                        positions.add(mutable.immutable());
                    }
                }
            }
        }
        return positions;
    }

    public static boolean canBeRemoved(BlockState state) {
        return state.canBeReplaced() || state.is(MalumTags.BlockTags.BLIGHT_REMOVABLE);
    }

    public static class LodestoneWorldgenBuilder {

        public static LodestoneWorldgenBuilder create() {
            return new LodestoneWorldgenBuilder();
        }

        protected final ArrayList<LodestoneWorldgenBuilderLayer> layers = new ArrayList<>();

        protected PlacementCondition defaultPlacementCondition = (level, entry) -> true;
        protected AdditionalPlacement defaultAdditionalPlacement = (level, entry) -> {
        };

        public LodestoneWorldgenBuilder() {
        }

        public LodestoneWorldgenBuilder addAdditionalPlacement(AdditionalPlacement defaultAdditionalPlacement) {
            this.defaultAdditionalPlacement = defaultAdditionalPlacement;
            return this;
        }

        public LodestoneWorldgenBuilder addPlacementCondition(PlacementCondition defaultPlacementCondition) {
            this.defaultPlacementCondition = defaultPlacementCondition;
            return this;
        }

        public LodestoneWorldgenBuilderLayer createLayer() {
            LodestoneWorldgenBuilderLayer layer = new LodestoneWorldgenBuilderLayer();
            layer.addAdditionalPlacement(defaultAdditionalPlacement);
            layer.addPlacementCondition(defaultPlacementCondition);
            layers.add(layer);
            return layer;
        }

        public ArrayList<LodestoneWorldgenBuilderLayer> getLayers() {
            return layers;
        }

        public LodestoneWorldgenBuilderLayer getLayer(int index) {
            return layers.get(index);
        }


        public ArrayList<BlockPos> getAffectedArea(int layerIndex) {
            return new ArrayList<>(getLayer(layerIndex).getAffectedArea());
        }

        public ArrayList<BlockPos> getAffectedArea() {
            ArrayList<BlockPos> affectedArea = new ArrayList<>();
            for (LodestoneWorldgenBuilderLayer layer : getLayers()) {
                affectedArea.addAll(layer.getAffectedArea());
            }
            return affectedArea;
        }

        public Collection<LodestoneWorldgenBuilderEntry> getEntries(int layerIndex) {
            return getLayer(layerIndex).getEntries();
        }

        public ArrayList<LodestoneWorldgenBuilderEntry> getAllEntries() {
            ArrayList<LodestoneWorldgenBuilderEntry> entries = new ArrayList<>();
            for (LodestoneWorldgenBuilderLayer layer : getLayers()) {
                entries.addAll(layer.getEntries());
            }
            return entries;
        }

        public Collection<LodestoneWorldgenBuilderEntry> getOrderedEntries(int layerIndex) {
            return getLayer(layerIndex).getOrderedEntries();
        }

        public ArrayList<LodestoneWorldgenBuilderEntry> getOrderedEntries() {
            ArrayList<LodestoneWorldgenBuilderEntry> entries = new ArrayList<>();
            for (LodestoneWorldgenBuilderLayer layer : getLayers()) {
                entries.addAll(layer.getOrderedEntries());
            }
            return entries;
        }

        public LodestoneWorldgenBuilder merge(LodestoneWorldgenBuilder other) {
            getLayers().addAll(other.getLayers());
            return this;
        }

        public void place(WorldGenLevel level) {
            Set<BlockPos> skippedPositions = new HashSet<>();
            for (LodestoneWorldgenBuilderLayer layer : getLayers()) {
                for (LodestoneWorldgenBuilderEntry entry : layer.getOrderedEntries()) {
                    if (!entry.isImportant() && skippedPositions.contains(entry.position())) {
                        continue;
                    }
                    if (entry.tryPlace(level)) {
                        skippedPositions.add(entry.position());
                    }
                }
            }
        }
    }

    public static class LodestoneWorldgenBuilderLayer {

        protected final HashMap<BlockPos, LodestoneWorldgenBuilderEntry> entries = new HashMap<>();
        protected final ArrayList<BlockPos> entryOrder = new ArrayList<>();
        protected PlacementCondition defaultPlacementCondition;
        protected AdditionalPlacement defaultAdditionalPlacement;

        public LodestoneWorldgenBuilderLayer() {
        }

        public ArrayList<BlockPos> getAffectedArea() {
            return new ArrayList<>(entries.keySet());
        }

        public LodestoneWorldgenBuilderLayer addAdditionalPlacement(AdditionalPlacement defaultAdditionalPlacement) {
            this.defaultAdditionalPlacement = defaultAdditionalPlacement;
            return this;
        }

        public LodestoneWorldgenBuilderLayer addPlacementCondition(PlacementCondition defaultPlacementCondition) {
            this.defaultPlacementCondition = defaultPlacementCondition;
            return this;
        }

        public LodestoneWorldgenBuilderLayer merge(LodestoneWorldgenBuilderLayer other) {
            this.entries.putAll(other.entries);
            return this;
        }

        public LodestoneWorldgenBuilderEntry add(BlockPos blockPos, Block block) {
            return add(blockPos, block.defaultBlockState());
        }

        public LodestoneWorldgenBuilderEntry add(BlockPos pos, BlockState state) {
            if (pos instanceof BlockPos.MutableBlockPos mutable) {
                pos = mutable.immutable();
            }
            LodestoneWorldgenBuilderEntry entry = new LodestoneWorldgenBuilderEntry(pos, state);
            if (!entry.hasPlacementCondition()) {
                entry.addPlacementCondition(defaultPlacementCondition);
            }
            if (!entry.hasAdditionalPlacement()) {
                entry.addAdditionalPlacement(defaultAdditionalPlacement);
            }
            add(pos, entry);
            return entry;
        }

        public LodestoneWorldgenBuilderLayer add(BlockPos pos, LodestoneWorldgenBuilderEntry entry) {
            entries.put(pos, entry);
            entryOrder.add(pos);
            return this;
        }

        public LodestoneWorldgenBuilderLayer remove(BlockPos pos) {
            entries.remove(pos);
            entryOrder.remove(pos);
            return this;
        }

        public LodestoneWorldgenBuilderEntry get(BlockPos pos) {
            return entries.get(pos);
        }

        public boolean containsKey(BlockPos pos) {
            return entries.containsKey(pos);
        }

        public Collection<LodestoneWorldgenBuilderEntry> getEntries() {
            return entries.values();
        }

        public ArrayList<LodestoneWorldgenBuilderEntry> getOrderedEntries() {
            ArrayList<LodestoneWorldgenBuilderEntry> orderedEntries = new ArrayList<>();
            for (BlockPos pos : entryOrder) {
                orderedEntries.add(entries.get(pos));
            }
            return orderedEntries;
        }

        public ArrayList<LodestoneWorldgenBuilderEntry> getRandomEntries(int amount) {
            ArrayList<LodestoneWorldgenBuilderEntry> randomEntries = new ArrayList<>();
            List<BlockPos> keys = new ArrayList<>(entries.keySet());
            Collections.shuffle(keys);
            for (int i = 0; i < Math.min(amount, keys.size()); i++) {
                randomEntries.add(entries.get(keys.get(i)));
            }
            return randomEntries;
        }
    }

    public static class LodestoneWorldgenBuilderEntry {
        protected BlockPos pos;
        protected BlockState state;
        protected PlacementCondition placementCondition;
        protected AdditionalPlacement additionalPlacement;

        protected boolean important;

        public LodestoneWorldgenBuilderEntry(BlockPos pos, BlockState state) {
            this.pos = pos;
            this.state = state;
        }

        public BlockPos position() {
            return pos;
        }

        public BlockState blockState() {
            return state;
        }

        /**
         * Changes the position of this entry using a function.
         * @param function The function to apply to the current position.
         */
        public LodestoneWorldgenBuilderEntry changePos(Function<BlockPos, BlockPos> function) {
            return changePos(function.apply(pos));
        }

        /**
         * Changes the position of this entry using a new position.
         * @param pos The new position to set for this entry.
         */
        public LodestoneWorldgenBuilderEntry changePos(BlockPos pos) {
            this.pos = pos;
            return this;
        }

        /**
         * Changes the block state of this entry using a function.
         * @param function The function to apply to the current block state.
         */
        public LodestoneWorldgenBuilderEntry changeState(Function<BlockState, BlockState> function) {
            return changeState(function.apply(state));
        }

        /**
         * Changes the block state of this entry.
         * @param state The new block state to set for this entry.
         */
        public LodestoneWorldgenBuilderEntry changeState(BlockState state) {
            this.state = state;
            return this;
        }

        /**
         * Adds a condition to the placement of this entry, which will be checked before placing the block.
         * @param placementCondition The placement condition to add to this entry.
         */
        public LodestoneWorldgenBuilderEntry addPlacementCondition(PlacementCondition placementCondition) {
            this.placementCondition = placementCondition;
            return this;
        }

        /**
         * Adds additional placement behavior to this entry which will be executed after the block is placed.
         * @param additionalPlacement The additional placement to add to this entry.
         */
        public LodestoneWorldgenBuilderEntry addAdditionalPlacement(AdditionalPlacement additionalPlacement) {
            this.additionalPlacement = additionalPlacement;
            return this;
        }

        /**\
         * Marks this entry as important, meaning it will be placed even if a previous layer already placed a block at the same position.
         */
        public LodestoneWorldgenBuilderEntry setImportant() {
            this.important = true;
            return this;
        }

        public boolean isImportant() {
            return important;
        }

        public boolean hasPlacementCondition() {
            return placementCondition != null;
        }

        public boolean hasAdditionalPlacement() {
            return additionalPlacement != null;
        }

        public boolean canPlace(WorldGenLevel level) {
            return placementCondition.canPlace(level, this);
        }

        public boolean tryPlace(WorldGenLevel level) {
            if (canPlace(level)) {
                place(level);
                additionalPlacement.place(level, this);
                return true;
            }
            return false;
        }

        public void place(WorldGenLevel level) {
            place(level, position(), blockState());
        }

        /**
         * Places a block at the specified position. For use with the additional placement.
         * @param level The world to place the block in.
         * @param pos The position to place the block at.
         * @param state The block state to place at the position.
         */
        public void place(WorldGenLevel level, BlockPos pos, BlockState state) {
            level.setBlock(pos, state, 19);
            if (level instanceof Level realLevel) {
                BlockStateHelper.updateState(realLevel, pos);
            }
        }
    }

    @FunctionalInterface
    public interface AdditionalPlacement {
        void place(WorldGenLevel level, LodestoneWorldgenBuilderEntry entry);
    }

    @FunctionalInterface
    public interface PlacementCondition {
        PlacementCondition CAN_SURVIVE = (level, entry) -> entry.blockState().canSurvive(level, entry.position());
        boolean canPlace(WorldGenLevel level, LodestoneWorldgenBuilderEntry entry);
    }
}