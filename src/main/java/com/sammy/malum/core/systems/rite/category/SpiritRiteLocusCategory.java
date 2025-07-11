package com.sammy.malum.core.systems.rite.category;

import com.sammy.malum.core.systems.rite.SpiritRiteType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class SpiritRiteLocusCategory extends SpiritRiteEffectCategory{

    public static final SpiritRiteEffectCategory LOCUS = new SpiritRiteLocusCategory();
    public SpiritRiteLocusCategory(){
        super("locus", 160, 4);
    }


    @Override
    public MutableComponent getCoverage() {
        return Component.translatable(SpiritRiteType.ANCHOR);
    }
}
