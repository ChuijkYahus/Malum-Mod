package com.sammy.malum.common.item.curiosities.curios.runes.totemic;

import com.sammy.malum.common.item.curiosities.curios.runes.TotemicRuneCurioItem;
import com.sammy.malum.registry.common.magic.rite.*;
import com.sammy.malum.registry.common.magic.MalumSpiritTypes;

public class RuneStoneWard extends TotemicRuneCurioItem {
    public RuneStoneWard(Properties builder) {
        super(builder, MalumSpiritRiteEffectTypes.APPLY_STONE_WARD_EFFECT, MalumSpiritTypes.EARTHEN_SPIRIT);
    }
}
