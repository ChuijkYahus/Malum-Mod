package com.sammy.malum.common.worldgen.tree;

import com.sammy.malum.common.block.flora.wood.StagedLeavesBlock;
import net.minecraft.core.*;
import net.minecraft.tags.*;
import net.minecraft.util.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.levelgen.feature.*;
import org.joml.Vector2i;
import team.lodestar.lodestone.modules.toolkit.worldgen.LodestoneWorldgenBuilder;
import team.lodestar.lodestone.modules.toolkit.worldgen.LodestoneWorldgenBuilderEntry;
import team.lodestar.lodestone.modules.toolkit.worldgen.LodestoneWorldgenBuilderLayer;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

import static com.sammy.malum.common.worldgen.WorldgenHelper.*;
import static com.sammy.malum.registry.common.MalumContent.BlockSets.*;

public class RunewoodTreeFeature extends Feature<RunewoodTreeConfiguration> {

    public RunewoodTreeFeature() {
        super(RunewoodTreeConfiguration.CODEC);
    }

    private int getSapBlockCount(RandomSource random) {
        return Mth.nextInt(random, 2, 3);
    }

    private int getTrunkHeight(RandomSource random) {
        return Mth.nextInt(random, 7, 10);
    }

    private int getSideTrunkHeight(RandomSource random) {
        return Mth.nextInt(random, 0, 2);
    }

    private int getDownwardsBranchOffset(RandomSource random) {
        return Mth.nextInt(random, 2, 4);
    }

    private int getBranchLength(RandomSource random) {
        return Mth.nextInt(random, 2, 3);
    }

    private int getBranchHeight(RandomSource random) {
        return Mth.nextInt(random, 3, 5);
    }

    @Override
    public boolean place(FeaturePlaceContext<RunewoodTreeConfiguration> context) {
        var level = context.level();
        var pos = context.origin();
        var config = context.config();
        if (level.isEmptyBlock(pos.below()) || !config.sapling().defaultBlockState().canSurvive(level, pos)) {
            return false;
        }
        var rand = context.random();
        var log = config.log();
        var runewoodLog = log.defaultBlockState();
        var sappyLog = RUNEWOOD_SET.sappyLog.get();
        var leavesBlock = config.leaves();


        var builder = LodestoneWorldgenBuilder.create();
        var treeLayer = builder.createLayer();
        var leavesLayer = builder.createLayer();

        int trunkHeight = getTrunkHeight(rand);
        int sapBlockCount = getSapBlockCount(rand);

        var mutable = new BlockPos.MutableBlockPos().set(pos);

        for (int i = 0; i <= trunkHeight; i++) { //Main Trunk
            if (!canPlaceTree(level, mutable)) {
                return false;
            }
            treeLayer.add(mutable, runewoodLog);
            mutable.move(Direction.UP);
        }
        for (int i = 0; i < 4; i++) { //Side Trunk Stumps
            var direction = Direction.from2DDataValue(i);
            int sideTrunkHeight = getSideTrunkHeight(rand);
            for (int j = 0; j < sideTrunkHeight; j++) {
                if (!canPlaceTree(level, mutable)) {
                    return false;
                }
                treeLayer.add(mutable, runewoodLog);
                mutable.move(Direction.UP);
            }
            mutable.set(pos).move(direction);
            addDownwardsTrunkConnections(level, mutable, p -> treeLayer.add(p, runewoodLog));
        }

        addSap(treeLayer, sappyLog, rand, sapBlockCount);

        for (int i = 0; i < 4; i++) { //Branches
            Direction direction = Direction.from2DDataValue(i);
            int downwardsBranchOffset = getDownwardsBranchOffset(rand);
            int branchLength = getBranchLength(rand);
            int branchHeight = getBranchHeight(rand);

            mutable.set(pos);
            mutable.move(Direction.UP, trunkHeight-downwardsBranchOffset);

            for (int j = 0; j < branchLength; j++) {
                mutable.move(direction);
                if (!canPlaceTree(level, mutable)) {
                    return false;
                }
                treeLayer.add(mutable, runewoodLog.setValue(RotatedPillarBlock.AXIS, direction.getAxis()));
            }
            for (int j = 0; j < branchHeight; j++) {
                if (!canPlaceTree(level, mutable)) {
                    return false;
                }
                treeLayer.add(mutable, runewoodLog);
                mutable.move(Direction.UP);
            }

            makeLeafBlob(leavesLayer, leavesBlock, mutable.move(Direction.DOWN, branchHeight-1), new int[]{1, 2, 2, 2, 1});
        }
        makeLeafBlob(leavesLayer, leavesBlock, mutable.set(pos).move(Direction.UP, trunkHeight-1), new int[]{1, 2, 3, 2, 1});
        applyLeavesColor(leavesLayer, rand);

        makeHangingLeaves(leavesLayer, config.hangingLeaves(), level);
        builder.place(level);
        updateLeaves(level, treeLayer.getAffectedArea());
        return true;
    }

    public static void addSap(LodestoneWorldgenBuilderLayer layer, Block sappyLog, RandomSource randomSource, int sapBlockCount) {
        var sapBlocks = layer.getRandomEntries(randomSource, sapBlockCount);
        for (LodestoneWorldgenBuilderEntry entry : sapBlocks) {
            var state = entry.blockState();
            if (!state.is(BlockTags.LOGS)) {
                continue;
            }
            var axis = state.getValue(RotatedPillarBlock.AXIS);
            entry.changeState(sappyLog.defaultBlockState().setValue(RotatedPillarBlock.AXIS, axis));
        }
    }

    public static void applyLeavesColor(LodestoneWorldgenBuilderLayer leaves, RandomSource randomSource) {
        HashMap<Vector2i, Integer> max = leaves.getEntries().stream()
                .collect(Collectors.toMap(
                        e -> new Vector2i(e.position().getX(), e.position().getZ()),
                        e -> e.position().getY(),
                        Integer::max,
                        HashMap::new
                ));
        var offset = randomSource.nextFloat();
        Collection<LodestoneWorldgenBuilderEntry> entries = leaves.getEntries();
        for (LodestoneWorldgenBuilderEntry entry : entries) {
            var state = entry.blockState();
            if (!(state.getBlock() instanceof StagedLeavesBlock stagedLeavesBlock)) {
                continue;
            }

            BlockPos position = entry.position();
            int x = position.getX();
            int y = position.getY();
            int z = position.getZ();
            int scale = stagedLeavesBlock.getColorProperty().getPossibleValues().size();

            Vector2i key = new Vector2i(x, z);
            int stage = Mth.abs(max.get(key) - y - Math.round(offset * scale)) % scale;

            IntegerProperty property = stagedLeavesBlock.getColorProperty();

            entry.changeState(state.setValue(property, stage));
        }
    }

    public static BlockPos addDownwardsTrunkConnections(WorldGenLevel level, BlockPos pos, Consumer<BlockPos> consumer) {
        var mutable = pos.mutable();
        while (true) {
            mutable.move(Direction.DOWN);
            if (!canPlaceTree(level, mutable)) {
                return mutable.above();
            }
            consumer.accept(mutable.immutable());
        }
    }

    public static void makeLeafBlob(LodestoneWorldgenBuilderLayer layer, Block leavesBlock, BlockPos pos, int[] leafSizes) {
        var mutable = pos.mutable();
        for (int size : leafSizes) {
            makeLeafSlice(layer, leavesBlock, mutable, size);
            mutable.move(Direction.UP);
        }
    }

    public static void makeLeafSlice(LodestoneWorldgenBuilderLayer leaves, Block leavesBlock, BlockPos pos, int leavesSize) {
        for (int x = -leavesSize; x <= leavesSize; x++) {
            for (int z = -leavesSize; z <= leavesSize; z++) {
                if (Math.abs(x) == leavesSize && Math.abs(z) == leavesSize) {
                    continue;
                }
                BlockPos leavesPos = pos.offset(x, 0, z);

                leaves.add(leavesPos, leavesBlock.defaultBlockState());
            }
        }
    }


    public static void makeHangingLeaves(LodestoneWorldgenBuilderLayer leaves, Block hangingLeaves, WorldGenLevel level) {

        if (!(hangingLeaves instanceof StagedLeavesBlock stagedHangingLeaves)) {
            return;
        }
        HashMap<BlockPos, BlockState> toAdd = new HashMap<>();
        for (LodestoneWorldgenBuilderEntry entry : leaves.getEntries()) {
            BlockPos position = entry.position();
            BlockPos below = position.below();
            if (level.getRandom().nextFloat() < 0.35f) {
                continue;
            }
            if (!canPlaceTree(level, below)) {
                continue;
            }
            BlockState state = entry.blockState();
            if (!(state.getBlock() instanceof StagedLeavesBlock leavesBlock)) {
                continue;
            }
            if (leaves.containsKey(below)) {
                continue;
            }
            if (!level.isEmptyBlock(below)) {
                continue;
            }

            var color = stagedHangingLeaves.getColorProperty();

            var leafColor = state.getValue(leavesBlock.getColorProperty());
            int appropriatedLeafColor = leafColor % color.getPossibleValues().size();
            toAdd.put(below, hangingLeaves.defaultBlockState().setValue(color, appropriatedLeafColor));
        }
        for (Map.Entry<BlockPos, BlockState> entry : toAdd.entrySet()) {
            leaves.add(entry.getKey(), entry.getValue());
        }
    }
}