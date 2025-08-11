package com.sammy.malum.common.item.curiosities.curios.runes.totemic;

import com.sammy.malum.common.item.curiosities.curios.runes.TotemicRuneCurioItem;
import com.sammy.malum.registry.common.magic.rite.*;
import com.sammy.malum.registry.common.magic.MalumSpiritTypes;

public class RuneGoodTides extends TotemicRuneCurioItem {
    public RuneGoodTides(Properties builder) {
        super(builder, MalumSpiritRiteEffectTypes.THE_GOOD_TIDES_EFFECT, MalumSpiritTypes.AQUEOUS_SPIRIT);
    }
}
