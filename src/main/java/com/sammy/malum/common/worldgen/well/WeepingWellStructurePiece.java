package com.sammy.malum.common.worldgen.well;

import com.sammy.malum.common.block.curiosities.weeping_well.*;
import com.sammy.malum.common.block.curiosities.weeping_well.encasement.*;
import com.sammy.malum.registry.common.MalumContent;
import com.sammy.malum.registry.common.MalumContent.WeepingWell;
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
import team.lodestar.lodestone.modules.toolkit.worldgen.LodestoneWorldgenBuilder;

import static com.sammy.malum.common.block.curiosities.weeping_well.encasement.WeepingWellLayeredBlock.*;
import static com.sammy.malum.registry.common.MalumContent.WeepingWell.*;
import static net.minecraft.world.level.block.state.properties.BlockStateProperties.*;


public class WeepingWellStructurePiece extends StructurePiece {

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

        LodestoneWorldgenBuilder builder = LodestoneWorldgenBuilder.create();

        var layer = builder.createLayer();
        var caveAir = Blocks.CAVE_AIR.defaultBlockState();


        var primordialSoupTop = PRIMORDIAL_SOUP.getDefaultState();
        var primordialSoup = PRIMORDIAL_SOUP.getDefaultState().setValue(PrimordialSoupBlock.TOP, false);
        var voidConduit = VOID_CONDUIT.getDefaultState();

        int airLayer = 3;
        int wellDepth = random.nextInt(8, 12);
        var mutable = wellPosition.mutable();
        for (int x = -2; x <= 2; x++) {
            for (int z = -2; z <= 2; z++) {
                for (int y = 0; y <= airLayer; y++) {
                    mutable.set(wellPosition).move(x, y, z);
                    layer.add(mutable, caveAir);
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
                    layer.add(mutable, state);
                }
            }
        }
        var flagstoneState = WEEPING_WELL_FLAGSTONE.getDefaultState();
        for (int i = 0; i < 4; i++) {
            Direction direction = Direction.from2DDataValue(i);
            if (direction.getAxis().equals(Direction.Axis.X)) {
                var columnBase = WEEPING_WELL_COLUMN_BASE.getDefaultState();
                var column = WEEPING_WELL_COLUMN.getDefaultState();
                var columnCap = WEEPING_WELL_COLUMN_CAP.getDefaultState();


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
                    layer.add(mutable.above(j), state);
                }

                for (int j = 0; j < 4; j++) {
                    Direction columnDirection = Direction.from2DDataValue(j);
                    var centerState = WEEPING_WELL_CENTER.getDefaultState().setValue(HORIZONTAL_FACING, columnDirection);
                    var cornerState = WEEPING_WELL_CORNER.getDefaultState().setValue(HORIZONTAL_FACING, columnDirection);
                    var centerPos = mutable.relative(columnDirection);
                    layer.add(centerPos, centerState);
                    var cornerPos = centerPos.relative(columnDirection.getClockWise());
                    layer.add(cornerPos, cornerState);
                    for (int k = 0; k < 4; k++) {
                        int state = Math.min(k+1, 4);
                        centerState = WEEPING_WELL_CENTER.getDefaultState().setValue(HORIZONTAL_FACING, columnDirection).setValue(LAYER, state);
                        cornerState = WEEPING_WELL_CORNER.getDefaultState().setValue(HORIZONTAL_FACING, columnDirection).setValue(LAYER, state);
                        layer.add(centerPos.below(state), centerState);
                        layer.add(cornerPos.below(state), cornerState);
                    }
                }
            }
            for (int j = 0; j <= wellDepth; j++) {
                int state = Math.min(j, 3);
                if (j == wellDepth) {
                    state++;
                }
                var centerState = WEEPING_WELL_CENTER.getDefaultState().setValue(HORIZONTAL_FACING, direction).setValue(LAYER, state);
                var sideState = WEEPING_WELL_SIDE.getDefaultState().setValue(HORIZONTAL_FACING, direction).setValue(LAYER, state);
                var sideMirroredState = WEEPING_WELL_SIDE_MIRROR.getDefaultState().setValue(HORIZONTAL_FACING, direction).setValue(LAYER, state);
                var cornerState = WEEPING_WELL_CORNER.getDefaultState().setValue(HORIZONTAL_FACING, direction).setValue(LAYER, state);
                mutable.set(wellPosition).move(direction, 2).setY(wellPosition.getY()-j);
                layer.add(mutable, centerState);
                layer.add(mutable.relative(direction.getClockWise()), sideState);
                layer.add(mutable.relative(direction.getCounterClockWise()), sideMirroredState);
                layer.add(mutable.relative(direction.getClockWise(), 2), cornerState);
            }
        }
        for (int i = 0; i < 4; i++) {
            Direction columnDirection = Direction.from2DDataValue(i);
            var centerState = WEEPING_WELL_CENTER.getDefaultState()
                    .setValue(HORIZONTAL_FACING, columnDirection);
            var cornerState = WEEPING_WELL_CORNER.getDefaultState().setValue(HORIZONTAL_FACING, columnDirection);
            var flagstonePos = mutable.set(wellPosition).move(Direction.DOWN, wellDepth+1);
            layer.add(flagstonePos.immutable(), flagstoneState);
            var sidePos = flagstonePos.relative(columnDirection);
            layer.add(sidePos, centerState);
            var cornerPos = sidePos.relative(columnDirection.getClockWise());
            layer.add(cornerPos, cornerState);
        }

        builder.place(level);
    }
}