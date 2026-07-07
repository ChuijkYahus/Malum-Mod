package com.sammy.malum.common.item.curiosities.curios.sets.rotten;

import com.sammy.malum.common.item.curiosities.curios.*;
import com.sammy.malum.core.helpers.*;
import net.minecraft.network.chat.*;

import java.util.function.*;

public class CurioSwarmingRing extends MalumCurioItem {

    public CurioSwarmingRing(Properties builder) {
        super(builder, MalumTrinketFamily.ROTTEN);
    }

    @Override
    public void addExtraTooltipLines(Consumer<Component> consumer) {
        consumer.accept(TooltipComponentHelper.positiveCurioEffect("rot_multiplicity"));
    }
}