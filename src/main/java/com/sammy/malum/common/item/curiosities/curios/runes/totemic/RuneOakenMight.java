package com.sammy.malum.common.item.curiosities.curios.runes.totemic;

import com.sammy.malum.common.item.curiosities.curios.runes.TotemicRuneCurioItem;
import com.sammy.malum.registry.common.magic.rite.*;
import com.sammy.malum.registry.common.magic.MalumSpiritTypes;

public class RuneOakenMight extends TotemicRuneCurioItem {
    public RuneOakenMight(Properties builder) {
        super(builder, MalumSpiritRiteEffectTypes.APPLY_OAKEN_MIGHT_EFFECT, MalumSpiritTypes.EARTHEN_SPIRIT);
    }
}
