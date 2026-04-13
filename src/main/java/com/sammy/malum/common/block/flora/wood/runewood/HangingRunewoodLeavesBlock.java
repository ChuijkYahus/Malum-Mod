package com.sammy.malum.common.block.flora.wood.runewood;

import com.sammy.malum.common.block.flora.wood.MalumHangingLeavesBlock;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class HangingRunewoodLeavesBlock extends MalumHangingLeavesBlock {

    public static IntegerProperty COLOR = IntegerProperty.create("color", 0, 6);

    public HangingRunewoodLeavesBlock(Properties properties) {
        super(properties);
    }

    @Override
    public IntegerProperty getColorProperty() {
        return COLOR;
    }
}
