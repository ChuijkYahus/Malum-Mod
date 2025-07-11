package com.sammy.malum.common.item.curiosities.curios.runes.totemic;

import com.sammy.malum.common.item.curiosities.curios.runes.TotemicRuneCurioItem;
import com.sammy.malum.core.systems.registry.RiteHolder;
import com.sammy.malum.core.systems.registry.SpiritHolder;
import com.sammy.malum.core.systems.rite.SpiritRiteType;
import com.sammy.malum.core.systems.spirit.type.SpiritArcanaType;
import com.sammy.malum.registry.common.magic.MalumSpiritRiteTypes;
import com.sammy.malum.registry.common.magic.MalumSpiritTypes;

public class RuneHowlingGale extends TotemicRuneCurioItem {
    public RuneHowlingGale(Properties builder) {
        super(builder, MalumSpiritRiteTypes.RITE_OF_THE_HOWLING_GALE, MalumSpiritTypes.AERIAL_SPIRIT);
    }
}
