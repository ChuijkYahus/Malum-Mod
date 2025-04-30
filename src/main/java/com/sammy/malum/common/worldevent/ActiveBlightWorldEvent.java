package com.sammy.malum.common.worldevent;

import com.google.common.collect.*;
import com.sammy.malum.common.block.blight.BlightedSoilBlock;
import com.sammy.malum.common.worldgen.tree.SoulwoodTreeFeature;
import com.sammy.malum.registry.common.ParticleEffectTypeRegistry;
import com.sammy.malum.registry.common.SoundRegistry;
import net.minecraft.core.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.synth.*;
import team.lodestar.lodestone.systems.easing.*;
import team.lodestar.lodestone.systems.worldevent.*;
import team.lodestar.lodestone.systems.worldgen.LodestoneBlockFiller;
import team.lodestar.lodestone.systems.worldgen.LodestoneBlockFiller.*;

import java.util.*;
import java.util.function.*;

import static com.sammy.malum.common.worldgen.tree.SoulwoodTreeFeature.BLIGHT;

public abstract class ActiveBlightWorldEvent extends WorldEventInstance {
    protected List<Integer> intensity = new ArrayList<>();
    protected int frequency;
    protected int delay;
    protected int timer;
    protected BlockPos position;
    public Map<Integer, Double> noiseValues;

    public ActiveBlightWorldEvent(WorldEventType type) {
        super(type);
    }

    public ActiveBlightWorldEvent setData(List<Integer> intensity, int frequency, int delay) {
        this.intensity.addAll(intensity);
        this.frequency = frequency;
        this.delay = delay;
        return this;
    }

    public ActiveBlightWorldEvent setPosition(BlockPos position) {
        this.position = position;
        return this;
    }

    @Override
    public void tick(Level level) {
        if (delay > 0) {
            delay--;
            return;
        }
        if (timer == 0) {
            timer = frequency;
            if (intensity.isEmpty()) {
                end(level);
                return;
            }
            createBlight((ServerLevel) level, intensity.removeFirst());
        }
        timer--;
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compoundTag) {

    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compoundTag) {

    }

    public void createBlight(ServerLevel level, int intensity) {
        LodestoneBlockFiller filler = new LodestoneBlockFiller(new LodestoneBlockFillerLayer(BLIGHT));
        if (noiseValues == null) {
            noiseValues = SoulwoodTreeFeature.generateBlight(level, filler, position, intensity);
        } else {
            SoulwoodTreeFeature.generateBlight(level, filler, noiseValues, position, intensity);
        }
        createBlightVFX(level, filler);
        level.playSound(null, position, SoundRegistry.MAJOR_BLIGHT_MOTIF.get(), SoundSource.BLOCKS, 1f, 1.8f);
    }

    public static void createBlightVFX(ServerLevel level, LodestoneBlockFiller filler) {
        filler.getLayer(BLIGHT).entrySet().stream().filter(e -> e.getValue().getState().getBlock() instanceof BlightedSoilBlock).map(Map.Entry::getKey)
                .forEach(p -> ParticleEffectTypeRegistry.BLIGHTING_MIST.createEffect(p).spawn(level));
    }
    private static final PerlinSimplexNoise COVERING_NOISE = new PerlinSimplexNoise(new WorldgenRandom(new LegacyRandomSource(1234L)), ImmutableList.of(0));

    public static Set<BlockPos> fetchCoveringPositions(ServerLevelAccessor level, BlockPos center, int radius) {
        return fetchCoveringPositions(level, center, radius,
                p -> {
                    BlockState state = level.getBlockState(p);
                    if (state.canBeReplaced() || !state.isFaceSturdy(level, p, Direction.UP)) {
                        return false;
                    }
                    return !level.getBlockState(p.below()).canBeReplaced() && level.getBlockState(p.above()).canBeReplaced();
                },
                false);
    }
    public static Set<BlockPos> fetchHangingBlockPositions(ServerLevelAccessor level, BlockPos center, int radius) {
        return fetchCoveringPositions(level, center, radius,
                p -> {
                    BlockState state = level.getBlockState(p);
                    if (state.canBeReplaced() || !state.isFaceSturdy(level, p, Direction.DOWN)) {
                        BlockState above = level.getBlockState(p.above());
                        return false;
                    }
                    return level.getBlockState(p.below()).canBeReplaced();
                },
                true);
    }
    public static Set<BlockPos> fetchCoveringPositions(ServerLevelAccessor level, BlockPos center, int radius, Predicate<BlockPos> statePredicate, boolean flipVerticalConditions) {
        Set<BlockPos> positions = new HashSet<>();
        int x = center.getX();
        int z = center.getZ();
        var mutable = new BlockPos.MutableBlockPos();

        int verticalRange = 6;
        float limit = Mth.sqrt(radius * radius + radius * radius);
        for (int i = -radius; i <= radius; i++) {
            for (int j = -radius; j <= radius; j++) {
                int offsetX = x + i;
                int offsetZ = z + j;
                float differenceX = x - offsetX;
                float differenceZ = z - offsetZ;
                float distance = Mth.sqrt(differenceX * differenceX + differenceZ * differenceZ);
                double theta = Math.toDegrees(Math.atan2(differenceX, differenceZ)) * 0.01f;
                double noise = (COVERING_NOISE.getValue(x * 10000 + theta, z * 10000 + theta, true)+1)/2;
                double threshold = Easing.SINE_IN_OUT.clamped(noise, 0.5f, 2) * radius * (limit-distance)/limit;
                if (distance <= threshold) {
                    mutable.set(offsetX, center.getY(), offsetZ);
                    for (int k = 0; !level.isStateAtPosition(mutable, BlockBehaviour.BlockStateBase::canBeReplaced) && k < verticalRange; ++k) {
                        mutable.move(flipVerticalConditions ? Direction.DOWN : Direction.UP);
                    }
                    for (int k = 0; level.isStateAtPosition(mutable, BlockBehaviour.BlockStateBase::canBeReplaced) && k < verticalRange; ++k) {
                        mutable.move(flipVerticalConditions ? Direction.UP : Direction.DOWN);
                    }
                    if (statePredicate.test(mutable)) {
                        positions.add(mutable.immutable());
                    }
                }
            }
        }
        return positions;
    }


}