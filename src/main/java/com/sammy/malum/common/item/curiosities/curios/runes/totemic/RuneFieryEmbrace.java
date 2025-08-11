package com.sammy.malum.common.item.curiosities.curios.runes.totemic;

import com.sammy.malum.common.item.curiosities.curios.runes.TotemicRuneCurioItem;
import com.sammy.malum.registry.common.magic.rite.*;
import com.sammy.malum.registry.common.magic.MalumSpiritTypes;

public class RuneFieryEmbrace extends TotemicRuneCurioItem {
    public RuneFieryEmbrace(Properties builder) {
        super(builder, MalumSpiritRiteEffectTypes.APPLY_FIERY_EMBRACE_EFFECT, MalumSpiritTypes.INFERNAL_SPIRIT);
    }
}
