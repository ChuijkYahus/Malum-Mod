package com.sammy.malum.common.item.curiosities.curios.runes.totemic;

import com.sammy.malum.common.item.curiosities.curios.runes.TotemicRuneCurioItem;
import com.sammy.malum.registry.common.magic.rite.*;
import com.sammy.malum.registry.common.magic.MalumSpiritTypes;

public class RuneSkyTether extends TotemicRuneCurioItem {
    public RuneSkyTether(Properties builder) {
        super(builder, MalumSpiritRiteEffectTypes.APPLY_SKY_TETHER_EFFECT, MalumSpiritTypes.AERIAL_SPIRIT);
    }
}
