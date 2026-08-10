package com.sammy.malum.common.worldgen.tree;

import com.sammy.malum.common.block.blight.*;
import com.sammy.malum.common.block.blight.CreepingBlightBlock.*;
import com.sammy.malum.common.block.flora.wood.StagedLeavesBlock;
import com.sammy.malum.common.worldgen.blight.*;
import com.sammy.malum.registry.common.MalumContent;
import net.minecraft.core.*;
import net.minecraft.util.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.phys.Vec2;
import team.lodestar.lodestone.modules.toolkit.worldgen.LodestoneWorldgenBuilder;
import team.lodestar.lodestone.modules.toolkit.worldgen.LodestoneWorldgenBuilderEntry;
import team.lodestar.lodestone.modules.toolkit.worldgen.LodestoneWorldgenBuilderLayer;
import team.lodestar.lodestone.modules.toolkit.worldgen.PlacementCondition;

import java.util.HashMap;
import java.util.Map;

import static com.sammy.malum.common.block.blight.CreepingBlightBlock.BlightType.*;
import static com.sammy.malum.common.worldgen.WorldgenHelper.*;
import static com.sammy.malum.common.worldgen.tree.RunewoodTreeFeature.*;
import static com.sammy.malum.registry.common.MalumContent.BlockSets.*;
import static com.sammy.malum.registry.common.MalumContent.BlockSets.SOULWOOD_SET;

public class SoulwoodTreeFeature extends Feature<NoneFeatureConfiguration> {

    public SoulwoodTreeFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    private static BlockState makeClingingBlight(BlightType blightType, Direction direction) {
        return MalumContent.Blight.CLINGING_BLIGHT.get().defaultBlockState().setValue(CreepingBlightBlock.BLIGHT_TYPE, blightType).setValue(BlockStateProperties.HORIZONTAL_FACING, direction);
    }

    //TODO: all of this should be a FeatureConfiguration
    private int getSapBlockCount(RandomSource random) {
        return Mth.nextInt(random, 5, 7);
    }
    private int getSpikeCount(RandomSource random) {
        return Mth.nextInt(random, 12, 16);
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
        if (level.isEmptyBlock(pos.below()) || !SOULWOOD_SAPLING.get().defaultBlockState().canSurvive(level, pos)) {
            return false;
        }
        var rand = context.random();
        var mutable = pos.mutable();

        var soulwoodLog = SOULWOOD_SET.log.get();
        var blightedSoulwoodLog = BLIGHTED_SOULWOOD.get();
        var sappyLog = SOULWOOD_SET.sappyLog.get();
        var leaves = SOULWOOD_LEAVES.get();

        var builder = LodestoneWorldgenBuilder.create();
        var treeLayer = builder.createLayer();
        var leavesLayer = builder.createLayer();
        var blightLayer = builder.createLayer();
        var rootsBuilder = LodestoneWorldgenBuilder.create();
        var rootsLayer = rootsBuilder.createLayer();

        int trunkHeight = getTrunkHeight(rand);
        int sapBlockCount = getSapBlockCount(rand);
        int spikeCount = getSpikeCount(rand);
        int twistCooldown = getTwistCooldown(rand);
        int remainingTwists = getTrunkTwistAmount(rand);
        int twistCutoffPoint = trunkHeight - 5;
        int twistDirectionIndex = rand.nextInt(4);
        for (int i = 0; i <= trunkHeight; i++) { //Main Trunk
            if (i < twistCutoffPoint) {
                if (twistCooldown == 0 && remainingTwists != 0) {
                    var twistDirection = Direction.from2DDataValue(twistDirectionIndex % 4);
                    if (rand.nextFloat() < 0.75f) {
                        twistDirectionIndex++;
                    }
                    if (!canPlaceTree(level, mutable)) {
                        return false;
                    }
                    treeLayer.add(mutable, soulwoodLog);
                    mutable.move(twistDirection);
                    twistCooldown = getTwistCooldown(rand);
                    remainingTwists--;
                }
            }
            if (!canPlaceTree(level, mutable)) {
                return false;
            }
            treeLayer.add(mutable, i == 0 ? blightedSoulwoodLog : soulwoodLog);
            mutable.move(Direction.UP);
            twistCooldown--;
        }
        var spikeEntries = treeLayer.getRandomEntries(rand, spikeCount);

        BlockPos trunkTop = mutable.immutable();
        for (int i = 0; i < 4; i++) { //Side Trunk Stumps
            var direction = Direction.from2DDataValue(i);
            var sidePos = pos.relative(direction);
            int sideTrunkHeight = getSideTrunkHeight(rand);
            mutable.set(sidePos);
            for (int j = 0; j < sideTrunkHeight; j++) {
                if (!canPlaceTree(level, mutable)) {
                    return false;
                }
                treeLayer.add(mutable, soulwoodLog);
                mutable.move(Direction.UP);
            }
            var lowestLog = addDownwardsTrunkConnections(level, sidePos, p -> treeLayer.add(p, soulwoodLog));
            treeLayer.add(lowestLog, blightedSoulwoodLog);

            var clingingBlightPos = lowestLog.relative(direction);
            if (canPlaceTree(level, clingingBlightPos)) {
                rootsLayer.add(clingingBlightPos, makeClingingBlight(BlightType.CLINGING_BLIGHT, direction.getOpposite())).addPlacementCondition(PlacementCondition.CAN_SURVIVE);
            }

            //Roots
            for (int j = 0; j < 4; j++) {
                int offset = rand.nextInt(2, 4);
                int sideOffset = rand.nextInt(-4, 4);
                var rootPos = lowestLog.relative(direction, offset).relative(direction.getClockWise(), sideOffset);
                var rootsDirection = rand.nextFloat() < 0.4f ? Direction.from2DDataValue(rand.nextInt(4)) : direction;
                var roots = makeClingingBlight(SOULWOOD_ROOTS, rootsDirection);
                mutable.set(rootPos);
                for (int k = 0; k < 4; k++) {
                    if (!canPlaceTree(level, mutable)) {
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

        addSap(treeLayer, sappyLog, rand, sapBlockCount);

        for (int i = 0; i < 4; i++) { //Branchewws
            var direction = Direction.from2DDataValue(i);
            int downwardsBranchOffset = getDownwardsBranchOffset(rand);
            int branchLength = getBranchLength(rand);
            int branchHeight = getBranchHeight(rand);
            remainingTwists = getBranchTwistAmount(rand);
            twistCooldown = 1;
            mutable.set(trunkTop);
            mutable.move(Direction.DOWN, downwardsBranchOffset);
            for (int j = 1; j < branchLength; j++) {
                mutable.move(direction);
                if (!canPlaceTree(level, mutable)) {
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
                if (!canPlaceTree(level, mutable)) {
                    return false;
                }
                treeLayer.add(mutable, soulwoodLog);
                mutable.move(Direction.UP);
            }
            makeLeafBlob(leavesLayer, leaves, mutable.move(Direction.DOWN, branchHeight-1), new int[]{1, 2, 3, 3, 3, 2, 1});
        }
        applyLeavesColor(leavesLayer, rand);


        var blight = BlightFeature.generateBlight(level, pos, true, 10);
        for (LodestoneWorldgenBuilderEntry entry : spikeEntries) {
            if (entry.position().getY() > pos.getY()+3) {
                var direction = Direction.from2DDataValue(level.getRandom().nextInt(4));
                var offsetPos = entry.position().relative(direction);
                blightLayer.add(offsetPos, makeClingingBlight(SOULWOOD_SPIKE, direction.getOpposite()));
            }
        }

        makeOverhang(leavesLayer, level, new Vec2(trunkTop.getX(), trunkTop.getZ()));
        makeHangingLeaves(leavesLayer, HANGING_SOULWOOD_LEAVES.get(), level);

        builder.merge(blight);
        builder.place(level);
        rootsBuilder.place(level);
        updateLeaves(level, treeLayer.getAffectedArea());
        return true;
    }

    public static void makeOverhang(LodestoneWorldgenBuilderLayer leaves, WorldGenLevel level, Vec2 center) {
        RandomSource random = level.getRandom();
        HashMap<BlockPos, BlockState> toAdd = new HashMap<>();
        for (LodestoneWorldgenBuilderEntry entry : leaves.getEntries()) {
            BlockPos position = entry.position();
            BlockPos below = position.below();
            if (!canPlaceTree(level, below) || leaves.containsKey(below)) {
                continue;
            }
            BlockState state = entry.blockState();
            if (!(state.getBlock() instanceof StagedLeavesBlock leavesBlock)) {
                continue;
            }
            var color = leavesBlock.getColorProperty();
            var mutable = below.mutable();
            float xDifference = center.x - position.getX();
            float zDifference = center.y - position.getZ();
            float rate = 1f + random.nextFloat() * 0.5f;
            int hang = Math.round(Mth.sqrt(xDifference * xDifference + zDifference * zDifference) * rate) - 4;
            for (int i = 0; i <= hang; i++) {
                if (!level.isEmptyBlock(mutable)) {
                    continue;
                }
                toAdd.put(mutable.immutable(), state = state.cycle(color));
                mutable.move(Direction.DOWN);
            }
        }
        for (Map.Entry<BlockPos, BlockState> entry : toAdd.entrySet()) {
            leaves.add(entry.getKey(), entry.getValue());
        }
    }
}
