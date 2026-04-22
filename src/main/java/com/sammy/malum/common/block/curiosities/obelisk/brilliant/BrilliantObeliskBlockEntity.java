package com.sammy.malum.common.block.curiosities.obelisk.brilliant;

import com.sammy.malum.common.block.curiosities.obelisk.*;
import com.sammy.malum.registry.common.MalumContent;
import com.sammy.malum.registry.common.block.MalumBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import team.lodestar.lodestone.modules.toolkit.multiblock.*;

import java.util.function.Supplier;

public class BrilliantObeliskBlockEntity extends ObeliskCoreBlockEntity {
    public static final Supplier<MultiBlockStructure> STRUCTURE = () -> (MultiBlockStructure.of(new MultiBlockStructure.StructurePiece(0, 1, 0, MalumContent.Sorcery.BRILLIANT_OBELISK_COMPONENT.get().defaultBlockState())));

    public BrilliantObeliskBlockEntity(BlockPos pos, BlockState state) {
        super(MalumBlockEntities.BRILLIANT_OBELISK.get(), STRUCTURE.get(), pos, state);
    }
}