package com.sammy.malum.common.block.curiosities.obelisk.rite_pylon;

import com.sammy.malum.common.block.curiosities.obelisk.*;
import com.sammy.malum.registry.common.block.*;
import net.minecraft.core.*;
import net.minecraft.world.level.block.state.*;
import team.lodestar.lodestone.systems.multiblock.*;

import java.util.function.*;

public class ArcanaPylonBlockEntity extends ObeliskCoreBlockEntity {
    public static final Supplier<MultiBlockStructure> STRUCTURE = () -> (MultiBlockStructure.of(new MultiBlockStructure.StructurePiece(0, 1, 0, MalumBlocks.ARCANA_PYLON_COMPONENT.get().defaultBlockState())));

    public ArcanaPylonBlockEntity(BlockPos pos, BlockState state) {
        super(MalumBlockEntities.ARCANA_PYLON.get(), STRUCTURE.get(), pos, state);
    }
}