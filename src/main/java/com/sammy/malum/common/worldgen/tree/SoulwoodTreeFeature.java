package com.sammy.malum.common.worldgen.tree;

import com.google.common.collect.*;
import com.mojang.datafixers.util.*;
import com.sammy.malum.common.block.blight.*;
import com.sammy.malum.common.block.blight.ClingingBlightBlock.*;
import com.sammy.malum.common.block.nature.*;
import com.sammy.malum.common.worldgen.blight.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.block.*;
import net.minecraft.core.*;
import net.minecraft.util.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.synth.*;
import net.minecraft.world.phys.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.helpers.block.*;
import team.lodestar.lodestone.systems.worldgen.*;
import team.lodestar.lodestone.systems.worldgen.LodestoneBlockFiller.*;

import java.util.*;

import static com.sammy.malum.common.block.blight.ClingingBlightBlock.BlightType.*;
import static com.sammy.malum.common.worldgen.WorldgenHelper.*;
import static com.sammy.malum.common.worldgen.tree.RunewoodTreeFeature.*;
import static net.minecraft.tags.BlockTags.*;
import static team.lodestar.lodestone.systems.worldgen.LodestoneBlockFiller.create;

public class SoulwoodTreeFeature extends Feature<NoneFeatureConfiguration> {

    public static final LodestoneLayerToken LOGS = new LodestoneLayerToken();
    public static final LodestoneLayerToken LEAVES = new LodestoneLayerToken();
    public static final LodestoneLayerToken HANGING_LEAVES = new LodestoneLayerToken();
    public static final LodestoneLayerToken BLIGHT = new LodestoneLayerToken();

    public SoulwoodTreeFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    private static BlockState makeClingingBlight(BlightType blightType, Direction direction) {
        return MalumBlocks.CLINGING_BLIGHT.get().defaultBlockState().setValue(ClingingBlightBlock.BLIGHT_TYPE, blightType).setValue(BlockStateProperties.HORIZONTAL_FACING, direction);
    }

    private int getSapBlockCount(RandomSource random) {
        return Mth.nextInt(random, 5, 7);
    }

    private int getTrunkHeight(RandomSource random) {
        return Mth.nextInt(random, 10, 15);
    }

    private int getTwistCooldown(RandomSource random) {
        return Mth.nextInt(random, 3, 5);
    }

    private int getTrunkTwistAmount(RandomSource random) {
        return Mth.nextInt(random, 2, 6);
    }

    private int getSideTrunkHeight(RandomSource random) {
        return Mth.nextInt(random, 1, 3);
    }

    private int getDownwardsBranchOffset(RandomSource random) {
        return Mth.nextInt(random, 2, 4);
    }

    private int getBranchLength(RandomSource random) {
        return Mth.nextInt(random, 3, 5);
    }

    private int getBranchTwistAmount(RandomSource random) {
        return Mth.nextInt(random, 0, 2);
    }

    private int getBranchHeight(RandomSource random) {
        return Mth.nextInt(random, 5, 6);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        var pos = context.origin();
        if (level.isEmptyBlock(pos.below()) || !MalumBlocks.SOULWOOD_GROWTH.get().defaultBlockState().canSurvive(level, pos)) {
            return false;
        }
        var rand = context.random();
        List<Pair<Direction, BlockPos>> validSoulwoodSpikePositions = new ArrayList<>();

        var logState = MalumBlocks.SOULWOOD_LOG.get().defaultBlockState();
        var blightedLogState = MalumBlocks.BLIGHTED_SOULWOOD.get().defaultBlockState();

        var filler = new LodestoneBlockFiller().addLayers(LOGS, LEAVES, HANGING_LEAVES, BLIGHT);

        int sapBlockCount = getSapBlockCount(rand);
        int trunkHeight = getTrunkHeight(rand);
        int twistCooldown = getTwistCooldown(rand);
        int twistDirectionIndex = rand.nextInt(DIRECTIONS.length);
        int remainingTwists = getTrunkTwistAmount(rand);
        int twistCutoffPoint = trunkHeight - 5;
        var mutable = new BlockPos.MutableBlockPos().set(pos);
        for (int i = 0; i <= trunkHeight; i++) { //Main Trunk
            if (i < twistCutoffPoint) {
                for (int j = 0; j < 4; j++) {
                    Direction direction = Direction.from2DDataValue(j);
                    validSoulwoodSpikePositions.add(Pair.of(direction.getOpposite(), mutable.relative(direction)));
                }
                if (twistCooldown == 0 && remainingTwists != 0) {
                    final Direction twistDirection = Direction.from2DDataValue(twistDirectionIndex % 4);
                    if (rand.nextFloat() < 0.75f) {
                        twistDirectionIndex++;
                    }
                    if (!canPlace(level, mutable)) {
                        return false;
                    }
                    filler.getLayer(LOGS).put(mutable.immutable(), create(logState));
                    filler.getLayer(BLIGHT).put(mutable.above(), create(makeClingingBlight(BlightType.ROOTED_BLIGHT, twistDirection)));
                    mutable.move(twistDirection);
                    twistCooldown = getTwistCooldown(rand);
                    remainingTwists--;
                }
            }
            if (!canPlace(level, mutable)) {
                return false;
            }
            for (int j = 0; j < 4; j++) {
                Direction direction = Direction.from2DDataValue(j);
                validSoulwoodSpikePositions.add(Pair.of(direction.getOpposite(), mutable.relative(direction)));
            }
            filler.getLayer(LOGS).put(mutable.immutable(), create(i == 0 ? blightedLogState : logState));
            mutable.move(Direction.UP);
            twistCooldown--;
        }
        BlockPos trunkTop = mutable.immutable();
        for (int i = 0; i < 4; i++) { //Side Trunk Stumps
            Direction direction = Direction.from2DDataValue(i);
            int sideTrunkHeight = getSideTrunkHeight(rand);
            mutable.set(pos).move(direction);
            var trunkBottom = addDownwardsTrunkConnections(logState, level, filler, mutable);
            for (int j = 0; j < sideTrunkHeight; j++) {
                if (!canPlace(level, mutable)) {
                    return false;
                }
                filler.getLayer(LOGS).put(mutable.immutable(), create(logState));
                mutable.move(Direction.UP);
            }
            filler.getLayer(LOGS).put(trunkBottom, create(blightedLogState));
            filler.getLayer(BLIGHT).put(trunkBottom.relative(direction), create(makeClingingBlight(BlightType.ROOTED_BLIGHT, direction.getOpposite())));
        }

        for (int i = 0; i < 4; i++) { //Branches
            Direction direction = Direction.from2DDataValue(i);
            int downwardsBranchOffset = getDownwardsBranchOffset(rand);
            int branchLength = getBranchLength(rand);
            int branchHeight = getBranchHeight(rand);
            remainingTwists = getBranchTwistAmount(rand);
            twistCooldown = 1;
            mutable.set(trunkTop);
            mutable.move(Direction.DOWN, downwardsBranchOffset);
            for (int j = 1; j < branchLength; j++) {
                mutable.move(direction);
                if (!canPlace(level, mutable)) {
                    return false;
                }
                final Direction.Axis axis = direction.getAxis();
                if (twistCooldown <= 0) {
                    filler.getLayer(LOGS).put(mutable.immutable(), create(logState.setValue(RotatedPillarBlock.AXIS, axis)));
                    mutable.move(Direction.UP);
                    twistCooldown = getTwistCooldown(rand);
                    remainingTwists--;
                }
                final boolean start = j == 1;
                final Direction opposite = direction.getOpposite();
                filler.getLayer(LOGS).put(mutable.immutable(), create(logState.setValue(RotatedPillarBlock.AXIS, axis)));
                filler.getLayer(BLIGHT).put(mutable.below(), create(makeClingingBlight(start ? HANGING_BLIGHT : HANGING_ROOTS, opposite)));
                if (start) {
                    filler.getLayer(BLIGHT).put(mutable.below(2), create(makeClingingBlight(HANGING_BLIGHT_CONNECTION, opposite)));
                }
                if (remainingTwists > 0) {
                    twistCooldown--;
                }
            }
            for (int j = 0; j < branchHeight; j++) {
                if (!canPlace(level, mutable)) {
                    return false;
                }
                filler.getLayer(LOGS).put(mutable.immutable(), create(logState));
                mutable.move(Direction.UP);
            }
            makeLeafBlob(filler, rand, mutable.move(Direction.DOWN, branchHeight-1));
        }
        BlightFeature.generateBlight(level, pos, 8).fill(level);

        ArrayList<BlockPos> sapBlockPositions = new ArrayList<>(filler.getLayer(LOGS).keySet());
        Collections.shuffle(sapBlockPositions);
        for (BlockPos blockPos : sapBlockPositions.subList(0, sapBlockCount)) {
            var entry = filler.getLayer(LOGS).get(blockPos);
            if (entry.getState().getBlock().equals(MalumBlocks.BLIGHTED_SOULWOOD.get())) {
                continue;
            }
            filler.getLayer(LOGS).replace(blockPos, e -> create(BlockStateHelper.getBlockStateWithExistingProperties(e.getState(), MalumBlocks.EXPOSED_SOULWOOD_LOG.get().defaultBlockState())).build());
        }

        int spikeCount = 6;
        Collections.shuffle(validSoulwoodSpikePositions);
        for (Pair<Direction, BlockPos> entry : validSoulwoodSpikePositions) {
            final BlockPos entryPos = entry.getSecond();
            if (!filler.getLayer(BLIGHT).containsKey(entryPos)) {
                filler.getLayer(BLIGHT).put(entryPos, create(makeClingingBlight(SOULWOOD_SPIKE, entry.getFirst())));
                if (spikeCount == 0) {
                    break;
                }
                spikeCount--;
            }
        }
        filler.fill(level);
        updateLeaves(level, filler.getLayer(LOGS).keySet());
        return true;
    }

    public BlockPos addDownwardsTrunkConnections(BlockState logState, WorldGenLevel level, LodestoneBlockFiller filler, BlockPos pos) {
        var mutable = pos.mutable();
        while (true) {
            mutable.move(Direction.DOWN);
            if (!canPlace(level, mutable)) {
                return mutable.above();
            }
            filler.getLayer(LOGS).put(mutable.immutable(), create(logState));
        }
    }

    public static void makeLeafBlob(LodestoneBlockFiller filler, RandomSource rand, BlockPos pos) {
        final BlockPos.MutableBlockPos mutable = pos.mutable();
        int[] leafSizes = new int[]{1, 2, 3, 3, 3, 2, 1};
        int[] leafColors = new int[]{4, 3, 2, 1, 2, 3, 4};
        for (int i = 0; i < 7; i++) {
            int size = leafSizes[i];
            int color = leafColors[i];
            makeLeafSlice(filler, rand, mutable, size, color, false);
            mutable.move(Direction.UP);
        }
        mutable.move(Direction.DOWN, 7);
        for (int i = 0; i < 3; i++) {
            int size = leafSizes[i];
            int color = leafColors[i];
            makeLeafSlice(filler, rand, mutable, size, color, true);
            mutable.move(Direction.UP);
        }
    }

    public static void makeLeafSlice(LodestoneBlockFiller filler, RandomSource rand, BlockPos.MutableBlockPos pos, int leavesSize, int leavesColor, boolean makeHangingLeaves) {
        int offsetColor = leavesColor;
        for (int x = -leavesSize; x <= leavesSize; x++) {
            for (int z = -leavesSize; z <= leavesSize; z++) {
                float scalar = RandomHelper.randomBetween(rand, 0.1f, 0.3f) + leavesSize * 0.1f;
                if (Math.abs(x) == leavesSize && Math.abs(z) == leavesSize) {
                    continue;
                }
                if (rand.nextFloat() < 0.05f) {
                    offsetColor = (offsetColor+1)%4;
                }
                BlockPos leavesPos = pos.offset(x, 0, z);
                if (makeHangingLeaves && !(x == 0 && z == 0)) {
                    int offset = Math.max(RandomHelper.randomBetween(rand, 0, leavesSize-2), 0);
                    int length = 2+RandomHelper.randomBetween(rand, 0, leavesSize)-offset;
                    BlockPos.MutableBlockPos hangingLeavesPos = leavesPos.mutable().move(Direction.UP, offset);
                    for (int i = 0; i <= length; i++) {
                        final int colorValue = Mth.clamp(offsetColor + Mth.floor(i*scalar), 0, 4);
                        var vinePos = hangingLeavesPos.move(Direction.DOWN).immutable();
                        boolean hanging = i == length;
                        var block = (hanging ? MalumBlocks.HANGING_SOULWOOD_LEAVES : MalumBlocks.SOULWOOD_LEAVES).get();
                        var entry = create(block.defaultBlockState().setValue(MalumLeavesBlock.COLOR, colorValue));
                        if (hanging) {
                            entry.setDiscardPredicate((l, p, s) -> !filler.getLayer(LEAVES).containsKey(p.above()) || filler.getLayer(LOGS).containsKey(p.above()));
                        }
                        var layer = filler.getLayer(hanging ? HANGING_LEAVES : LEAVES);
                        layer.put(vinePos, entry.build());
                    }
                } else {
                    filler.getLayer(LEAVES).put(leavesPos, create(MalumBlocks.SOULWOOD_LEAVES.get().defaultBlockState().setValue(MalumLeavesBlock.COLOR, offsetColor)));
                }
            }
        }
    }
}