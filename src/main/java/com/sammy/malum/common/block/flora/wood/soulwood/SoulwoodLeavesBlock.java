package com.sammy.malum.common.block.flora.wood.soulwood;

import com.sammy.malum.common.block.flora.wood.MalumLeavesBlock;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class SoulwoodLeavesBlock extends MalumLeavesBlock {

    public static IntegerProperty COLOR = IntegerProperty.create("color", 0, 8);

    public SoulwoodLeavesBlock(Properties properties) {
        super(properties);
    }

    @Override
    public IntegerProperty getColorProperty() {
        return COLOR;
    }
}
