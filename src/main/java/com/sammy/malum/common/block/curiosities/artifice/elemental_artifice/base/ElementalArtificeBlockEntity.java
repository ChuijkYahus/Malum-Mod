package com.sammy.malum.common.block.curiosities.artifice.elemental_artifice.base;

import com.sammy.malum.common.block.curiosities.artifice.TinkererArtificeBlockEntity;
import com.sammy.malum.common.block.curiosities.artifice.elemental_artifice.ElementalArtificeTinkeringInfo;
import net.minecraft.core.*;
import net.minecraft.world.level.block.state.*;
import team.lodestar.lodestone.modules.toolkit.blockentity.LodestoneBlockEntityType;

public abstract class ElementalArtificeBlockEntity extends TinkererArtificeBlockEntity {

    public ElementalArtificeBlockEntity(LodestoneBlockEntityType<? extends ElementalArtificeBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }
}