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
        var layer = filler.getLayer(WELL);
        var caveAir = create(Blocks.CAVE_AIR.defaultBlockState()).setForcePlace();


        var primordialSoupTop = create(BlockRegistry.PRIMORDIAL_SOUP.get().defaultBlockState()).setForcePlace();
        var primordialSoup = create(BlockRegistry.PRIMORDIAL_SOUP.get().defaultBlockState().setValue(PrimordialSoupBlock.TOP, false)).setForcePlace();
        var voidConduit = create(BlockRegistry.VOID_CONDUIT.get().defaultBlockState()).setForcePlace();

        int airLayer = 3;
        int wellDepth = random.nextInt(6, 9);
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