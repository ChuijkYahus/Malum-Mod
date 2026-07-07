package com.sammy.malum.common.item.curiosities.curios.runes.miracle;

import com.sammy.malum.common.item.curiosities.curios.runes.*;
import com.sammy.malum.core.systems.registry.*;
import com.sammy.malum.core.systems.spirit.SpiritArcanaType;

public class MiracleRuneCurioItem extends AbstractRuneCurioItem {
    public MiracleRuneCurioItem(Properties builder, SpiritHolder<SpiritArcanaType> spirit) {
        super(builder, spirit, MalumTrinketFamily.RUNE);
    }
}
