package com.sammy.malum.common.item.curiosities.curios.runes.miracle;

import com.sammy.malum.common.item.curiosities.curios.runes.*;
import com.sammy.malum.core.systems.registry.*;
import com.sammy.malum.core.systems.spirit.type.*;

public class MiracleRuneCurioItem extends AbstractRuneCurioItem {
    public MiracleRuneCurioItem(Properties builder, SpiritHolder<MalumSpiritType> spirit) {
        super(builder, spirit, MalumTrinketType.RUNE);
    }
}
