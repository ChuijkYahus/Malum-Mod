package com.sammy.malum.common.spiritrite.arcane;

import com.sammy.malum.common.spiritrite.*;
import com.sammy.malum.core.systems.rite.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.world.entity.*;

import static com.sammy.malum.registry.common.MalumSpiritTypes.*;

public class AqueousRiteType extends TotemicRiteType {
    public AqueousRiteType() {
        super("aqueous_rite", ARCANE_SPIRIT, AQUEOUS_SPIRIT, AQUEOUS_SPIRIT);
    }

    @Override
    public TotemicRiteEffect getNaturalRiteEffect() {
        return new PotionRiteEffect(MalumSpiritTypes.AERIAL_SPIRIT, LivingEntity.class, MalumMobEffects.POSEIDONS_GRASP);
    }

    @Override
    public TotemicRiteEffect getCorruptedEffect() {
        return new PotionRiteEffect(MalumSpiritTypes.AERIAL_SPIRIT, LivingEntity.class, MalumMobEffects.ANGLERS_LURE);
    }
}