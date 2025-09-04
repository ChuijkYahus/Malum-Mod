package com.sammy.malum.common.spiritrite.effect.earthen;

import com.sammy.malum.core.systems.rite.effect.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.world.entity.*;

import java.util.*;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.*;

public class EarthenEmpowermentRiteEffect extends SpiritRiteEmpowermentEffect<LivingEntity> {

    public EarthenEmpowermentRiteEffect() {
        super(List.of(MalumMobEffects.STONE_WARD, MalumMobEffects.OAKEN_MIGHT), EARTHEN_SPIRIT);
    }

    @Override
    public Class<LivingEntity> getTargetClass() {
        return LivingEntity.class;
    }
}
