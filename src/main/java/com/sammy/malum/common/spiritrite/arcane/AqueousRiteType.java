package com.sammy.malum.common.spiritrite.arcane;

import com.sammy.malum.common.spiritrite.*;
import com.sammy.malum.core.systems.rite.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.magic.*;
import net.minecraft.world.entity.*;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.*;

public class AqueousRiteType extends SpiritRiteType {
    public AqueousRiteType() {
        super("aqueous_rite", ARCANE_SPIRIT, AQUEOUS_SPIRIT, AQUEOUS_SPIRIT);
    }

    @Override
    public OldTotemicRiteEffect getNaturalRiteEffect() {
        return new PotionRiteEffectOld(MalumSpiritTypes.AERIAL_SPIRIT, LivingEntity.class, MalumMobEffects.POSEIDONS_GRASP);
    }

    @Override
    public OldTotemicRiteEffect getCorruptedEffect() {
        return new PotionRiteEffectOld(MalumSpiritTypes.AERIAL_SPIRIT, LivingEntity.class, MalumMobEffects.ANGLERS_LURE);
    }
}