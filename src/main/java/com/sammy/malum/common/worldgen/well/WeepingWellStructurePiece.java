package com.sammy.malum.common.worldgen.well;

import com.sammy.malum.common.block.curiosities.weeping_well.*;
import com.sammy.malum.common.block.curiosities.weeping_well.encasement.*;
import com.sammy.malum.registry.common.block.*;
import com.sammy.malum.registry.common.worldgen.*;
import net.minecraft.core.*;
import net.minecraft.nbt.*;
import net.minecraft.util.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.chunk.*;
import net.minecraft.world.level.levelgen.structure.*;
import net.minecraft.world.level.levelgen.structure.pieces.*;
import team.lodestar.lodestone.systems.worldgen.*;

import java.util.*;

import static team.lodestar.lodestone.systems.worldgen.LodestoneBlockFiller.create;

public class WeepingWellStructurePiece extends StructurePiece {
    public static final LodestoneBlockFiller.LodestoneLayerToken WELL = new LodestoneBlockFiller.LodestoneLayerToken();

    private final BlockPos startPos;

    protected WeepingWellStructurePiece(BlockPos startPos, BoundingBox boundingBox) {
        super(MalumStructurePieceTypes.WEEPING_WELL.get(), 0, boundingBox);
        this.startPos = startPos;
    }

    public WeepingWellStructurePiece(CompoundTag tag) {
        super(MalumStructurePieceTypes.WEEPING_WELL.get(), tag);
        this.startPos = NbtUtils.readBlockPos(tag, "startPos").orElseThrow();
    }

    @Override
    protected void addAdditionalSaveData(StructurePieceSerializationContext context, CompoundTag tag) {
        tag.put("startPos", NbtUtils.writeBlockPos(startPos));
    }

    @Override
    public void postProcess(WorldGenLevel level, StructureManager structureManager, ChunkGenerator generator, RandomSource random, BoundingBox box, ChunkPos chunkPos, BlockPos pos) {
        BlockPos wellPosition = startPos;

        LodestoneBlockFiller filler = new LodestoneBlockFiller().addLayers(WELL);
        Direction[] directions = new Direction[]{Direction.NORTH, Direction.WEST, Direction.SOUTH, Direction.EAST};

//        BlockPos.MutableBlockPos mutable = startPos.mutable();
//        int failedSolidChecks = 0;
//        int failedNonSolidChecks = 0;
//
//        for (int x = -2; x <= 2; x++) {
//            for (int y = -5; y <= 4; y++) {
//                for (int z = -2; z <= 2; z++) {
//                    mutable.set(wellPosition.getX() + x, wellPosition.getY() + y, wellPosition.getZ() + z);
//                    if (y <= 0) {
//                        if (level.isEmptyBlock(mutable) || !level.isFluidAtPosition(mutable, FluidState::isEmpty)) {
//                            failedSolidChecks++;
//                        }
//                    } else {
//                        if (!canPlace(level, mutable)) {
//                            failedNonSolidChecks++;
//                        }
//                    }
//                }
//            }
//        }
//        if (failedSolidChecks >= 25) {
//            return;
//        }
//        if (failedNonSolidChecks >= 50) {
//            return;
//        }
        var layer = filler.getLayer(WELL);
        var caveAir = create(Blocks.CAVE_AIR.defaultBlockState()).setForcePlace();

        var bricks = create(BlockRegistry.WEEPING_WELL_BRICKS.get().defaultBlockState()).setForcePlace();

        var primordialSoupTop = create(BlockRegistry.PRIMORDIAL_SOUP.get().defaultBlockState()).setForcePlace();
        var primordialSoup = create(BlockRegistry.PRIMORDIAL_SOUP.get().defaultBlockState().setValue(PrimordialSoupBlock.TOP, false)).setForcePlace();
        var voidConduit = create(BlockRegistry.VOID_CONDUIT.get().defaultBlockState()).setForcePlace();

        int wellDepth = random.nextInt(6, 9);
        var mutable = wellPosition.mutable();
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                for (int y = 0; y <= wellDepth; y++) {
                    mutable.set(wellPosition).move(x, -y, z);
                    var state = caveAir;
                    if (y == wellDepth) {
                        state = bricks;
                    }
                    else {
                        if (y >= 2) {
                            final boolean isTop = y == 2;
                            state = x == 0 && isTop && z == 0 ? voidConduit : (isTop ? primordialSoupTop : primordialSoup);
                        }
                    }
                    layer.put(mutable.immutable(), state);
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            Direction direction = Direction.from2DDataValue(i);
            for (int j = 0; j <= wellDepth; j++) {
                int state = Math.min(j, 3);
                if (j == wellDepth) {
                    state++;
                }
                var centerState = create(BlockRegistry.WEEPING_WELL_CENTER.get().defaultBlockState()
                        .setValue(BlockStateProperties.HORIZONTAL_FACING, direction)
                        .setValue(WeepingWellLayeredBlock.LAYER, state))
                        .setForcePlace();
                var sideState = create(BlockRegistry.WEEPING_WELL_SIDE.get().defaultBlockState()
                        .setValue(BlockStateProperties.HORIZONTAL_FACING, direction)
                        .setValue(WeepingWellLayeredBlock.LAYER, state))
                        .setForcePlace();
                var sideMirroredState = create(BlockRegistry.WEEPING_WELL_SIDE_MIRROR.get().defaultBlockState()
                        .setValue(BlockStateProperties.HORIZONTAL_FACING, direction)
                        .setValue(WeepingWellLayeredBlock.LAYER, state))
                        .setForcePlace();
                var cornerState = create(BlockRegistry.WEEPING_WELL_CORNER.get().defaultBlockState()
                        .setValue(BlockStateProperties.HORIZONTAL_FACING, direction)
                        .setValue(WeepingWellLayeredBlock.LAYER, state))
                        .setForcePlace();
                mutable.set(wellPosition).move(direction, 2).setY(wellPosition.getY()-j);
                layer.put(mutable.immutable(), centerState);
                layer.put(mutable.relative(direction.getClockWise()), sideState);
                layer.put(mutable.relative(direction.getCounterClockWise()), sideMirroredState);
                layer.put(mutable.relative(direction.getClockWise(), 2), cornerState);
            }
        }




//        int wellDepth = 4;
//        int airPocketHeight = 2;
//        var layer = filler.getLayer(WELL);
//        var caveAir = create(Blocks.CAVE_AIR.defaultBlockState()).setForcePlace();
//        for (int i = 0; i < 9; i++) {
//            int xOffset = (i / 3) - 1;
//            int zOffset = i % 3 - 1;
//            for (int j = 0; j < wellDepth; j++) {
//                BlockPos primordialGoopPos = wellPosition.offset(xOffset, -j, zOffset);
//                boolean top = j == 0;
//                BlockState state = top && i == 5
//                        ? BlockRegistry.VOID_CONDUIT.get().defaultBlockState()
//                        : BlockRegistry.PRIMORDIAL_SOUP.get().defaultBlockState().setValue(PrimordialSoupBlock.TOP, top);
//
//                layer.put(primordialGoopPos, create(state).setForcePlace());
//            }
//            for (int j = 1; j <= airPocketHeight; j++) {
//                BlockPos airPocketPos = wellPosition.offset(xOffset, j, zOffset);
//                layer.put(airPocketPos, caveAir.setForcePlace());
//            }
//        }
//
//        BlockPos above = wellPosition.above();
//        for (Direction direction : directions) {
//            BlockPos.MutableBlockPos start = above.mutable().move(direction, 2).move(direction.getCounterClockWise(), 2);
//            for (int i = 0; i < 4; i++) {
//                Block block = BlockRegistry.WEEPING_WELL_SIDE.get();
//                BlockPos.MutableBlockPos segmentPosition = start.move(direction.getClockWise());
//                if (i == 1) {
//                    block = BlockRegistry.WEEPING_WELL_CORE.get();
//                }
//                if (i == 3) {
//                    block = BlockRegistry.WEEPING_WELL_CORNER.get();
//                }
//                BlockState state = block.defaultBlockState().setValue(WeepingWellBlock.FACING, direction);
//                BlockPos immutable = segmentPosition.immutable();
//                layer.put(immutable, create(state).setForcePlace());
//                layer.put(immutable.below(), create(Blocks.DEEPSLATE.defaultBlockState()).setForcePlace());
//                layer.put(immutable.above(), caveAir);
//                layer.put(immutable.above(2), caveAir);
//            }
//        }
//
//        int startingIndex = random.nextInt(2);
//        Direction cachedChosenDirection = null;
//        for (int i = 0; i < 2; i++) {
//            Direction columnDirection = directions[startingIndex + i * 2];
//            BlockPos.MutableBlockPos columnPosition = wellPosition.mutable().move(0, 2, 0).move(columnDirection, 3);
//            if (random.nextBoolean()) {
//                Direction chosenDirection;
//                if (cachedChosenDirection == null) {
//                    chosenDirection = random.nextBoolean() ? columnDirection.getCounterClockWise() : columnDirection.getClockWise();
//                } else {
//                    chosenDirection = random.nextBoolean() ? cachedChosenDirection.getOpposite() : null;
//                }
//                if (chosenDirection != null) {
//                    columnPosition.move(chosenDirection);
//                }
//                cachedChosenDirection = chosenDirection;
//            }
//            int columnHeight = 3 + random.nextInt(4);
//            int wallHeightDifference = 1 + random.nextInt(3);
//            int wallHeight = columnHeight - wallHeightDifference;
//            for (int j = 0; j < columnHeight; j++) {
//                BlockState state;
//                if (j == 0 || j == columnHeight - 1) {
//                    state = BlockRegistry.TAINTED_ROCK_COLUMN_CAP.get().defaultBlockState().setValue(BlockStateProperties.FACING, j == 0 ? Direction.DOWN : Direction.UP);
//                } else {
//                    state = BlockRegistry.TAINTED_ROCK_COLUMN.get().defaultBlockState().setValue(BlockStateProperties.AXIS, Direction.Axis.Y);
//                }
//                layer.put(columnPosition.immutable(), create(state).setForcePlace());
//                if (j < wallHeight) {
//                    BlockState wallState = BlockRegistry.TAINTED_ROCK_BRICKS_WALL.get().defaultBlockState();
//                    final WallSide wallSide = j == wallHeight - 1 ? WallSide.LOW : WallSide.TALL;
//                    switch (columnDirection) {
//                        case SOUTH -> wallState = wallState.setValue(WallBlock.SOUTH_WALL, wallSide);
//                        case NORTH -> wallState = wallState.setValue(WallBlock.NORTH_WALL, wallSide);
//                        case WEST -> wallState = wallState.setValue(WallBlock.WEST_WALL, wallSide);
//                        case EAST -> wallState = wallState.setValue(WallBlock.EAST_WALL, wallSide);
//                    }
//                    layer.put(columnPosition.move(columnDirection.getOpposite()).immutable(), create(wallState).setForcePlace());
//                    columnPosition.move(columnDirection);
//                }
//                columnPosition.move(0, 1, 0);
//            }
//        }

        filler.fill(level);
    }

    public static boolean canPlace(WorldGenLevel level, BlockPos pos) {
        if (level.isOutsideBuildHeight(pos)) {
            return false;
        }
        BlockState state = level.getBlockState(pos);
        return level.isEmptyBlock(pos) || state.canBeReplaced();
    }

    public boolean isSufficientlyFlat(WorldGenLevel level, BlockPos origin, int check) {
        List<BlockPos> blockPosList = new ArrayList<>();
        for (int x = -check; x < check; x++) {
            for (int z = -check; z < check; z++) {
                blockPosList.add(origin.offset(x, 0, z));
            }
        }
        int count = 0;
        for (BlockPos pos : blockPosList) {
            if (level.getBlockState(pos).isAir() && !level.getBlockState(pos.below()).isAir()) {
                count++;
            }
        }
        return count >= check * check * 2;
    }
}