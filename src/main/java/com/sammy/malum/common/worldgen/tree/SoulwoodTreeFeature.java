package com.sammy.malum.common.worldgen.tree;

import com.sammy.malum.common.block.blight.*;
import com.sammy.malum.common.block.blight.CreepingBlightBlock.*;
import com.sammy.malum.common.block.flora.wood.MalumLeavesBlock;
import com.sammy.malum.common.worldgen.WorldgenHelper;
import com.sammy.malum.common.worldgen.blight.*;
import com.sammy.malum.registry.common.block.*;
import net.minecraft.core.*;
import net.minecraft.util.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import team.lodestar.lodestone.modules.core.easing.Easing;
import team.lodestar.lodestone.systems.worldgen.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.*;

import static com.sammy.malum.common.block.blight.CreepingBlightBlock.BlightType.*;
import static com.sammy.malum.common.worldgen.WorldgenHelper.*;
import static com.sammy.malum.common.worldgen.tree.RunewoodTreeFeature.*;

public class SoulwoodTreeFeature extends Feature<NoneFeatureConfiguration> {

    public SoulwoodTreeFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    private static BlockState makeClingingBlight(BlightType blightType, Direction direction) {
        return MalumBlocks.CLINGING_BLIGHT.get().defaultBlockState().setValue(CreepingBlightBlock.BLIGHT_TYPE, blightType).setValue(BlockStateProperties.HORIZONTAL_FACING, direction);
    }

    //TODO: all of this should be a FeatureConfiguration
    private int getSapBlockCount(RandomSource random) {
        return Mth.nextInt(random, 5, 7);
    }
    private int getSpikeCount(RandomSource random) {
        return Mth.nextInt(random, 4, 6);
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
        if (level.isEmptyBlock(pos.below()) || !MalumBlocks.SOULWOOD_SAPLING.get().defaultBlockState().canSurvive(level, pos)) {
            return false;
        }
        var rand = context.random();
        var mutable = pos.mutable();

        var soulwoodLog = MalumBlocks.SOULWOOD_LOG.get();
        var blightedSoulwoodLog = MalumBlocks.BLIGHTED_SOULWOOD.get();

        var builder = LodestoneWorldgenBuilder.create();
        var treeLayer = builder.createLayer();
        var blightLayer = builder.createLayer();
        var leavesLayer = builder.createLayer();
        var rootsBuilder = LodestoneWorldgenBuilder.create();
        var rootsLayer = rootsBuilder.createLayer();

        int trunkHeight = getTrunkHeight(rand);
        int twistCooldown = getTwistCooldown(rand);
        int remainingTwists = getTrunkTwistAmount(rand);
        int twistCutoffPoint = trunkHeight - 5;
        int twistDirectionIndex = rand.nextInt(4);
        for (int i = 0; i <= trunkHeight; i++) { //Main Trunk
            if (i < twistCutoffPoint) {
                if (twistCooldown == 0 && remainingTwists != 0) {
                    final Direction twistDirection = Direction.from2DDataValue(twistDirectionIndex % 4);
                    if (rand.nextFloat() < 0.75f) {
                        twistDirectionIndex++;
                    }
                    if (!canPlace(level, mutable)) {
                        return false;
                    }
                    treeLayer.add(mutable, soulwoodLog);
                    mutable.move(twistDirection);
                    twistCooldown = getTwistCooldown(rand);
                    remainingTwists--;
                }
            }
            if (!canPlace(level, mutable)) {
                return false;
            }
            treeLayer.add(mutable, i == 0 ? blightedSoulwoodLog : soulwoodLog);
            mutable.move(Direction.UP);
            twistCooldown--;
        }
        BlockPos trunkTop = mutable.immutable();
        for (int i = 0; i < 4; i++) { //Side Trunk Stumps
            Direction direction = Direction.from2DDataValue(i);
            BlockPos sidePos = pos.relative(direction);
            int sideTrunkHeight = getSideTrunkHeight(rand);
            mutable.set(sidePos);
            for (int j = 0; j < sideTrunkHeight; j++) {
                if (!canPlace(level, mutable)) {
                    return false;
                }
                treeLayer.add(mutable, soulwoodLog);
                mutable.move(Direction.UP);
            }
            var lowestLog = addDownwardsTrunkConnections(level, sidePos, p -> treeLayer.add(p, soulwoodLog));
            treeLayer.add(lowestLog, blightedSoulwoodLog);

            BlockPos clingingBlightPos = lowestLog.relative(direction);
            if (canPlace(level, clingingBlightPos)) {
                rootsLayer.add(clingingBlightPos, makeClingingBlight(BlightType.CLINGING_BLIGHT, direction.getOpposite())).addPlacementCondition(PlacementCondition.CAN_SURVIVE);
            }

            //Roots
            for (int j = 0; j < 4; j++) {
                int offset = rand.nextInt(2, 4);
                int sideOffset = rand.nextInt(-4, 4);
                BlockPos rootPos = lowestLog.relative(direction, offset).relative(direction.getClockWise(), sideOffset);
                Direction rootsDirection = rand.nextFloat() < 0.4f ? Direction.from2DDataValue(rand.nextInt(4)) : direction;
                BlockState roots = makeClingingBlight(SOULWOOD_ROOTS, rootsDirection);
                mutable.set(rootPos);
                for (int k = 0; k < 4; k++) {
                    if (!canPlace(level, mutable)) {
                        if (k == 2) {
                            mutable.set(rootPos);
                        }
                        mutable.move(k >= 2 ? Direction.UP : Direction.DOWN);
                        continue;
                    }
                    rootsLayer.add(mutable, roots).addPlacementCondition(PlacementCondition.CAN_SURVIVE);
                    break;
                }
            }
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
                Direction.Axis axis = direction.getAxis();
                if (twistCooldown <= 0) {
                    treeLayer.add(mutable, soulwoodLog.defaultBlockState().setValue(RotatedPillarBlock.AXIS, axis));
                    mutable.move(Direction.UP);
                    twistCooldown = getTwistCooldown(rand);
                    remainingTwists--;
                }
                Direction opposite = direction.getOpposite();
                if (j == 1) {
                    blightLayer.add(mutable.below(), makeClingingBlight(HANGING_BLIGHT, opposite));
                }
                treeLayer.add(mutable, soulwoodLog.defaultBlockState().setValue(RotatedPillarBlock.AXIS, axis));
                if (remainingTwists > 0) {
                    twistCooldown--;
                }
            }
            for (int j = 0; j < branchHeight; j++) {
                if (!canPlace(level, mutable)) {
                    return false;
                }
                treeLayer.add(mutable, soulwoodLog);
                mutable.move(Direction.UP);
            }
            makeLeafBlob(leavesLayer, rand, mutable.move(Direction.DOWN, branchHeight-1));
        }

        for (LodestoneWorldgenBuilderEntry entry : getRandomEntries(treeLayer.getOrderedEntries(), getSapBlockCount(rand), rand)) {
            entry.changeState(s -> {
                if (s.getBlock().equals(MalumBlocks.SOULWOOD_LOG.get())) {
                    return MalumBlocks.SAPPY_SOULWOOD_LOG.get().defaultBlockState().setValue(RotatedPillarBlock.AXIS, s.getValue(RotatedPillarBlock.AXIS));
                }
                return s;
            });
        }
        for (LodestoneWorldgenBuilderEntry entry : getRandomEntries(treeLayer.getOrderedEntries(), getSpikeCount(rand), rand)) {
            if (entry.position().getY() > pos.getY()+3) {
                entry.addAdditionalPlacement(((l, e) -> {
                    Direction direction = Direction.from2DDataValue(l.getRandom().nextInt(4));
                    BlockPos offsetPos = e.position().relative(direction);
                    e.place(l, offsetPos, makeClingingBlight(SOULWOOD_SPIKE, direction.getOpposite()));
                }));
            }
        }
        var blight = BlightFeature.generateBlight(level, pos, true, 10);
        builder.merge(blight);
        builder.place(level);
        rootsBuilder.place(level);
        updateLeaves(level, treeLayer.getAffectedArea());
        return true;
    }

	private static <T> ArrayList<T> getRandomEntries(Collection<T> collection, int amount, RandomSource rand) {
		return new ArrayList<>(
			WorldgenHelper.shuffle(collection, rand).subList(0, Math.min(amount, collection.size()))
		);
	}

    public BlockPos addDownwardsTrunkConnections(WorldGenLevel level, BlockPos pos, Consumer<BlockPos> consumer) {
        var mutable = pos.mutable();
        while (true) {
            mutable.move(Direction.DOWN);
            if (!canPlace(level, mutable)) {
                return mutable.above();
            }
            consumer.accept(mutable.immutable());
        }
    }

    public static void makeLeafBlob(LodestoneWorldgenBuilderLayer layer, RandomSource rand, BlockPos pos) {
        var mutable = pos.mutable();
        int[] leafSizes = new int[]{1, 2, 3, 3, 3, 2, 1};
        int[] leafColors = new int[]{4, 3, 2, 1, 2, 3, 4};
        for (int i = 0; i < 7; i++) {
            int size = leafSizes[i];
            int color = leafColors[i];
            makeLeafSlice(layer, rand, mutable, size, color);
            mutable.move(Direction.UP);
        }
        mutable = pos.mutable();
        for (int i = 0; i < 3; i++) {
            int size = leafSizes[i];
            int color = leafColors[i];
            makeHangingLeaves(layer, rand, mutable, size, color);
            mutable.move(Direction.UP);
        }
    }

    public static void makeLeafSlice(LodestoneWorldgenBuilderLayer leaves, RandomSource rand, BlockPos pos, int leavesSize, int leavesColor) {
        int offsetColor = leavesColor;
        for (int x = -leavesSize; x <= leavesSize; x++) {
            for (int z = -leavesSize; z <= leavesSize; z++) {
                if (Math.abs(x) == leavesSize && Math.abs(z) == leavesSize) {
                    continue;
                }
                if (rand.nextFloat() < 0.05f) {
                    offsetColor = (offsetColor + 1) % 4;
                }
                BlockPos leavesPos = pos.offset(x, 0, z);

                leaves.add(leavesPos, MalumBlocks.SOULWOOD_LEAVES.get().defaultBlockState().setValue(MalumLeavesBlock.COLOR, offsetColor));
            }
        }
    }

    public static void makeHangingLeaves(LodestoneWorldgenBuilderLayer leaves, RandomSource rand, BlockPos pos, int leavesSize, int leavesColor) {
        int offsetColor = leavesColor;
        for (int x = -leavesSize; x <= leavesSize; x++) {
            for (int z = -leavesSize; z <= leavesSize; z++) {
                float colorRate = Easing.SINE_IN_OUT.asWeighedRandom(rand, 0.1f, 0.3f) + leavesSize * 0.1f;
                if (Math.abs(x) == leavesSize && Math.abs(z) == leavesSize) {
                    continue;
                }
                if (rand.nextFloat() < 0.05f) {
                    offsetColor = (offsetColor + 1) % 4;
                }
                BlockPos leavesPos = pos.offset(x, 0, z);
                if (!(x == 0 && z == 0)) {
                    int startOffset = Math.max(Easing.SINE_IN_OUT.asWeighedRandom(rand, 0, leavesSize - 2), 0);
                    int size = 2 + Easing.SINE_IN_OUT.asWeighedRandom(rand, 0, leavesSize) - startOffset;
                    var mutable = leavesPos.mutable().move(Direction.UP, startOffset);
                    for (int i = 0; i <= size; i++) {
                        mutable.move(Direction.DOWN);
                        int color = offsetColor + Mth.floor(i * colorRate);
                        boolean hanging = i == size;
                        if (hanging && leaves.containsKey(mutable)) {
                            continue;
                        }
                        leaves.add(mutable, createLeaves(hanging, color))
                                .addPlacementCondition((l, e) -> l.getBlockState(e.position().above()).is(MalumBlocks.SOULWOOD_LEAVES.get()));
                    }
                }
            }
        }
    }

    public static BlockState createLeaves(boolean hanging, int color) {
        var leaves = hanging ? MalumBlocks.HANGING_SOULWOOD_LEAVES.get() : MalumBlocks.SOULWOOD_LEAVES.get();
        return leaves.defaultBlockState().setValue(MalumLeavesBlock.COLOR, Mth.clamp(color, 0, 4));
    }
}
