package com.sammy.malum.common.block.curiosities.sorcery.wand_tinkerer;

import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import team.lodestar.lodestone.modules.toolkit.block.LodestoneEntityBlock;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.HORIZONTAL_FACING;

public class WandTinkererBlock<T extends WandTinkererBlockEntity> extends LodestoneEntityBlock<T> {

    public WandTinkererBlock(Properties properties) {
        super(properties);
    }
}
