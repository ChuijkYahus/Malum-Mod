package com.sammy.malum.common.block.flora.wood.runewood;

import com.sammy.malum.common.block.flora.wood.MalumLeavesBlock;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class RunewoodLeavesBlock extends MalumLeavesBlock {

    public static IntegerProperty COLOR = IntegerProperty.create("color", 0, 6);

    public RunewoodLeavesBlock(Properties properties) {
        super(properties);
    }

    @Override
    public IntegerProperty getColorProperty() {
        return COLOR;
    }
}
