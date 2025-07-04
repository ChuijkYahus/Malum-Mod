package com.sammy.malum.common.spiritrite.arcane;

import com.sammy.malum.common.spiritrite.*;
import com.sammy.malum.core.systems.rite.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.magic.*;
import net.minecraft.world.entity.*;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.*;

public class AerialRiteType extends SpiritRiteType {
    public AerialRiteType() {
        super("aerial_rite", ARCANE_SPIRIT, AERIAL_SPIRIT, AERIAL_SPIRIT);
    }

    @Override
    public OldTotemicRiteEffect getNaturalRiteEffect() {
        return new PotionRiteEffectOld(MalumSpiritTypes.AERIAL_SPIRIT, LivingEntity.class, MalumMobEffects.ZEPHYRS_COURAGE);
    }

    @Override
    public OldTotemicRiteEffect getCorruptedEffect() {
        return new PotionRiteEffectOld(MalumSpiritTypes.AERIAL_SPIRIT, LivingEntity.class, MalumMobEffects.AETHERS_CHARM);
    }
}