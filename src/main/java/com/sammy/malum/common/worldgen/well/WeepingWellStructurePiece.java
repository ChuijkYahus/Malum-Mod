package com.sammy.malum.common.worldgen.well;

import com.sammy.malum.common.block.curiosities.weeping_well.*;
import com.sammy.malum.common.block.curiosities.weeping_well.encasement.*;
import com.sammy.malum.registry.common.MalumContent;
import com.sammy.malum.registry.common.worldgen.*;
import net.minecraft.core.*;
import net.minecraft.nbt.*;
import net.minecraft.util.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.chunk.*;
import net.minecraft.world.level.levelgen.structure.*;
import net.minecraft.world.level.levelgen.structure.pieces.*;
import team.lodestar.lodestone.systems.worldgen.*;

import static team.lodestar.lodestone.systems.worldgen.LodestoneBlockFiller.create;

public class WeepingWellStructurePiece extends StructurePiece {
    public static final LodestoneBlockFiller.LodestoneLayerToken WELL = new LodestoneBlockFiller.LodestoneLayerToken();

    private final BlockPos startPos;

    protected WeepingWellStructurePiece(BlockPos startPos, BoundingBox boundingBox) {
        super(MalumStructureTypes.StructurePieceTypes.WEEPING_WELL.get(), 0, boundingBox);
        this.startPos = startPos;
    }

    public WeepingWellStructurePiece(CompoundTag tag) {
        super(MalumStructureTypes.StructurePieceTypes.WEEPING_WELL.get(), tag);
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
        var layer = filler.getLayer(WELL);
        var caveAir = create(Blocks.CAVE_AIR.defaultBlockState()).setForcePlace();


        var primordialSoupTop = create(MalumContent.WeepingWell.PRIMORDIAL_SOUP.get().defaultBlockState()).setForcePlace();
        var primordialSoup = create(MalumContent.WeepingWell.PRIMORDIAL_SOUP.get().defaultBlockState().setValue(PrimordialSoupBlock.TOP, false)).setForcePlace();
        var voidConduit = create(MalumContent.WeepingWell.VOID_CONDUIT.get().defaultBlockState()).setForcePlace();

        int airLayer = 3;
        int wellDepth = random.nextInt(8, 12);
        var mutable = wellPosition.mutable();
        for (int x = -2; x <= 2; x++) {
            for (int z = -2; z <= 2; z++) {
                for (int y = 0; y <= airLayer; y++) {
                    mutable.set(wellPosition).move(x, y, z);
                    layer.put(mutable.immutable(), caveAir);
                }
            }
        }
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                for (int y = 0; y <= wellDepth; y++) {
                    mutable.set(wellPosition).move(x, -y, z);
                    var state = caveAir;
                    if (y == 2) {
                        state = x == 0 && z == 0 ? voidConduit : primordialSoupTop;
                    }
                    else if (y > 2) {
                        state = primordialSoup;
                    }
                    layer.put(mutable.immutable(), state);
                }
            }
        }
        var flagstoneState = create(MalumContent.WeepingWell.WEEPING_WELL_FLAGSTONE.get().defaultBlockState())
                .setForcePlace();
        for (int i = 0; i < 4; i++) {
            Direction direction = Direction.from2DDataValue(i);
            if (direction.getAxis().equals(Direction.Axis.X)) {
                var columnBase = create(MalumContent.WeepingWell.WEEPING_WELL_COLUMN_BASE.get().defaultBlockState())
                        .setForcePlace();
                var column = create(MalumContent.WeepingWell.WEEPING_WELL_COLUMN.get().defaultBlockState())
                        .setForcePlace();
                var columnCap = create(MalumContent.WeepingWell.WEEPING_WELL_COLUMN_CAP.get().defaultBlockState())
                        .setForcePlace();


                mutable.set(wellPosition).move(direction, 5);
                int columnHeight = random.nextInt(5, 7);
                for (int j = 0; j <= columnHeight; j++) {
                    var state = column;
                    if (j == 0) {
                        state = flagstoneState;
                    }
                    else if (j == 1) {
                        state = columnBase;
                    }
                    else if (j == columnHeight) {
                        state = columnCap;
                    }
                    layer.put(mutable.above(j), state);
                }

                for (int j = 0; j < 4; j++) {
                    Direction columnDirection = Direction.from2DDataValue(j);
                    var centerState = create(MalumContent.WeepingWell.WEEPING_WELL_CENTER.get().defaultBlockState()
                            .setValue(BlockStateProperties.HORIZONTAL_FACING, columnDirection))
                            .setForcePlace();
                    var cornerState = create(MalumContent.WeepingWell.WEEPING_WELL_CORNER.get().defaultBlockState()
                            .setValue(BlockStateProperties.HORIZONTAL_FACING, columnDirection))
                            .setForcePlace();
                    var centerPos = mutable.relative(columnDirection);
                    layer.put(centerPos, centerState);
                    var cornerPos = centerPos.relative(columnDirection.getClockWise());
                    layer.put(cornerPos, cornerState);
                    for (int k = 0; k < 4; k++) {
                        int state = Math.min(k+1, 4);
                        centerState = create(MalumContent.WeepingWell.WEEPING_WELL_CENTER.get().defaultBlockState()
                                .setValue(BlockStateProperties.HORIZONTAL_FACING, columnDirection)
                                .setValue(WeepingWellLayeredBlock.LAYER, state));
                        cornerState = create(MalumContent.WeepingWell.WEEPING_WELL_CORNER.get().defaultBlockState()
                                .setValue(BlockStateProperties.HORIZONTAL_FACING, columnDirection)
                                .setValue(WeepingWellLayeredBlock.LAYER, state));
                        layer.put(centerPos.below(state), centerState);
                        layer.put(cornerPos.below(state), cornerState);
                    }
                }
            }
            for (int j = 0; j <= wellDepth; j++) {
                int state = Math.min(j, 3);
                if (j == wellDepth) {
                    state++;
                }
                var centerState = create(MalumContent.WeepingWell.WEEPING_WELL_CENTER.get().defaultBlockState()
                        .setValue(BlockStateProperties.HORIZONTAL_FACING, direction)
                        .setValue(WeepingWellLayeredBlock.LAYER, state))
                        .setForcePlace();
                var sideState = create(MalumContent.WeepingWell.WEEPING_WELL_SIDE.get().defaultBlockState()
                        .setValue(BlockStateProperties.HORIZONTAL_FACING, direction)
                        .setValue(WeepingWellLayeredBlock.LAYER, state))
                        .setForcePlace();
                var sideMirroredState = create(MalumContent.WeepingWell.WEEPING_WELL_SIDE_MIRROR.get().defaultBlockState()
                        .setValue(BlockStateProperties.HORIZONTAL_FACING, direction)
                        .setValue(WeepingWellLayeredBlock.LAYER, state))
                        .setForcePlace();
                var cornerState = create(MalumContent.WeepingWell.WEEPING_WELL_CORNER.get().defaultBlockState()
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
        for (int i = 0; i < 4; i++) {
            Direction columnDirection = Direction.from2DDataValue(i);
            var centerState = create(MalumContent.WeepingWell.WEEPING_WELL_CENTER.get().defaultBlockState()
                    .setValue(BlockStateProperties.HORIZONTAL_FACING, columnDirection))
                    .setForcePlace();
            var cornerState = create(MalumContent.WeepingWell.WEEPING_WELL_CORNER.get().defaultBlockState()
                    .setValue(BlockStateProperties.HORIZONTAL_FACING, columnDirection))
                    .setForcePlace();
            var flagstonePos = mutable.set(wellPosition).move(Direction.DOWN, wellDepth+1);
            layer.put(flagstonePos.immutable(), flagstoneState);
            var sidePos = flagstonePos.relative(columnDirection);
            layer.put(sidePos, centerState);
            var cornerPos = sidePos.relative(columnDirection.getClockWise());
            layer.put(cornerPos, cornerState);
        }

        filler.fill(level);
    }
}