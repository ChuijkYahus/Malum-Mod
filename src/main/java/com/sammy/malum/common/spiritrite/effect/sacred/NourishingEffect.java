package com.sammy.malum.common.spiritrite.effect.sacred;

import com.sammy.malum.core.systems.rite.effect.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.world.entity.player.*;

import java.util.Collection;
import java.util.Collections;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.*;

public class NourishingEffect extends SpiritRitePotionEffect<Player> {

    public NourishingEffect() {
        super(Collections.emptyList(), MalumMobEffects.SACRED_NOURISHMENT, SACRED_SPIRIT);
    }

    @Override
    public Class<Player> getTargetClass() {
        return Player.class;
    }
}
