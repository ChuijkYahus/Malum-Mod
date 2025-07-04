package com.sammy.malum.common.spiritrite.arcane;

import com.sammy.malum.common.spiritrite.*;
import com.sammy.malum.core.systems.rite.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.magic.*;
import net.minecraft.world.entity.*;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.*;

public class EarthenRiteType extends SpiritRiteType {
    public EarthenRiteType() {
        super("earthen_rite", ARCANE_SPIRIT, EARTHEN_SPIRIT, EARTHEN_SPIRIT);
    }

    @Override
    public OldTotemicRiteEffect getNaturalRiteEffect() {
        return new PotionRiteEffectOld(MalumSpiritTypes.AERIAL_SPIRIT, LivingEntity.class, MalumMobEffects.GAIAS_BULWARK);
    }

    @Override
    public OldTotemicRiteEffect getCorruptedEffect() {
        return new PotionRiteEffectOld(MalumSpiritTypes.AERIAL_SPIRIT, LivingEntity.class, MalumMobEffects.EARTHEN_MIGHT);
    }
}
