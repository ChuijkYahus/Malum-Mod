package com.sammy.malum.common.spiritrite.arcane;

import com.sammy.malum.common.spiritrite.*;
import com.sammy.malum.core.systems.rite.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.world.entity.*;

import static com.sammy.malum.registry.common.MalumSpiritTypes.*;

public class EarthenRiteType extends TotemicRiteType {
    public EarthenRiteType() {
        super("earthen_rite", ARCANE_SPIRIT, EARTHEN_SPIRIT, EARTHEN_SPIRIT);
    }

    @Override
    public TotemicRiteEffect getNaturalRiteEffect() {
        return new PotionRiteEffect(MalumSpiritTypes.AERIAL_SPIRIT, LivingEntity.class, MalumMobEffects.GAIAS_BULWARK);
    }

    @Override
    public TotemicRiteEffect getCorruptedEffect() {
        return new PotionRiteEffect(MalumSpiritTypes.AERIAL_SPIRIT, LivingEntity.class, MalumMobEffects.EARTHEN_MIGHT);
    }
}
