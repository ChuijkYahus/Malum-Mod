package com.sammy.malum.common.spiritrite.effect.aerial;

import com.sammy.malum.core.systems.rite.effect.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.world.entity.*;

import java.util.*;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.*;

public class AerialEmpowermentRiteEffect extends SpiritRiteEmpowermentEffect<LivingEntity> {

    public AerialEmpowermentRiteEffect() {
        super(List.of(MalumMobEffects.HOWLING_GALE, MalumMobEffects.SKY_TETHER), AERIAL_SPIRIT);
    }

    @Override
    public Class<LivingEntity> getTargetClass() {
        return LivingEntity.class;
    }
}
