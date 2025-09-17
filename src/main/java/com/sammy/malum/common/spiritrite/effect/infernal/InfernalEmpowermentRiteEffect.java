package com.sammy.malum.common.spiritrite.effect.infernal;

import com.sammy.malum.core.systems.rite.effect.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.world.entity.*;

import java.util.*;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.*;

public class InfernalEmpowermentRiteEffect extends SpiritRiteEmpowermentEffect<LivingEntity> {

    public InfernalEmpowermentRiteEffect() {
        super(List.of(MalumMobEffects.BURNING_FERVOR, MalumMobEffects.FIERY_EMBRACE), INFERNAL_SPIRIT);
    }

    @Override
    public Class<LivingEntity> getTargetClass() {
        return LivingEntity.class;
    }
}
