package com.sammy.malum.common.item.curiosities.curios.runes.madness;

import com.sammy.malum.common.item.curiosities.curios.runes.*;
import com.sammy.malum.core.systems.registry.*;
import com.sammy.malum.core.systems.spirit.SpiritArcanaType;

public class MadnessRuneCurioItem extends AbstractRuneCurioItem {
    public MadnessRuneCurioItem(Properties builder, SpiritHolder<SpiritArcanaType> spirit) {
        super(builder, spirit, MalumTrinketFamily.VOID_RUNE);
    }
}
