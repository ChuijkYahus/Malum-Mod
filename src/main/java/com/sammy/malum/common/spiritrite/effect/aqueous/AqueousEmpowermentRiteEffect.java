package com.sammy.malum.common.spiritrite.effect.aqueous;

import com.sammy.malum.core.systems.rite.effect.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.world.entity.*;

import java.util.*;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.*;

public class AqueousEmpowermentRiteEffect extends SpiritRiteEmpowermentEffect<LivingEntity> {

    public AqueousEmpowermentRiteEffect() {
        super(List.of(MalumMobEffects.FLOWING_GRASP, MalumMobEffects.GOOD_TIDES), AQUEOUS_SPIRIT);
    }

    @Override
    public Class<LivingEntity> getTargetClass() {
        return LivingEntity.class;
    }
}
