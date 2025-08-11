package com.sammy.malum.common.item.curiosities.curios.runes.totemic;

import com.sammy.malum.common.item.curiosities.curios.runes.TotemicRuneCurioItem;
import com.sammy.malum.registry.common.magic.rite.*;
import com.sammy.malum.registry.common.magic.MalumSpiritTypes;

public class RuneFlowingGrasp extends TotemicRuneCurioItem {
    public RuneFlowingGrasp(Properties builder) {
        super(builder, MalumSpiritRiteEffectTypes.APPLY_FLOWING_GRASP_EFFECT, MalumSpiritTypes.AQUEOUS_SPIRIT);
    }
}
