package com.sammy.malum.common.item.curiosities.curios.runes.totemic;

import com.sammy.malum.common.item.curiosities.curios.runes.TotemicRuneCurioItem;
import com.sammy.malum.registry.common.magic.rite.*;
import com.sammy.malum.registry.common.magic.MalumSpiritTypes;

public class RuneHowlingGale extends TotemicRuneCurioItem {
    public RuneHowlingGale(Properties builder) {
        super(builder, MalumSpiritRiteEffectTypes.APPLY_HOWLING_GALE_EFFECT, MalumSpiritTypes.AERIAL_SPIRIT);
    }
}
