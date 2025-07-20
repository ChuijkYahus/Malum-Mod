package com.sammy.malum.common.spiritrite.effect.wicked;

import com.sammy.malum.core.systems.rite.effect.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.world.entity.monster.*;

import java.util.Collections;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.*;

public class EmpowermentEffect extends SpiritRitePotionEffect<Monster> {

    public EmpowermentEffect() {
        super(Collections.emptyList(), MalumMobEffects.WICKED_EMPOWERMENT, WICKED_SPIRIT);
    }

    @Override
    public Class<Monster> getTargetClass() {
        return Monster.class;
    }
}
