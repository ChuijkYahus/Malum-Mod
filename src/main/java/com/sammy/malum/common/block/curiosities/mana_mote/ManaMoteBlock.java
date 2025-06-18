package com.sammy.malum.common.block.curiosities.mana_mote;

import com.sammy.malum.common.block.curiosities.totem.*;
import com.sammy.malum.core.systems.spirit.SpiritTypeProperty;
import com.sammy.malum.core.systems.spirit.type.*;
import com.sammy.malum.registry.common.MalumSpiritTypes;
import com.sammy.malum.registry.common.block.*;
import net.minecraft.core.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import team.lodestar.lodestone.systems.block.LodestoneEntityBlock;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.HORIZONTAL_FACING;

public class ManaMoteBlock extends LodestoneEntityBlock<ManaMoteBlockEntity> {

    public static final SpiritTypeProperty SPIRIT_TYPE = SpiritTypeProperty.SPIRIT_TYPE;

    public ManaMoteBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(SPIRIT_TYPE, "sacred"));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(SPIRIT_TYPE);
    }

    public static BlockState createManaMoteState(SpiritWrapper spiritType) {
        return MalumBlocks.SPIRIT_MOTE.get().defaultBlockState().setValue(SPIRIT_TYPE, spiritType.getRegistryName().getPath());
    }
}
