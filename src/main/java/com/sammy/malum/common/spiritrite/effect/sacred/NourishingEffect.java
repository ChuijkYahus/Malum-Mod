package com.sammy.malum.common.spiritrite.effect.sacred;

import com.sammy.malum.core.systems.rite.effect.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.server.level.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.player.*;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.*;

public class NourishingEffect extends SpiritRitePotionEffect<Player> {

    public NourishingEffect() {
        super(Player.class, MalumMobEffects.SACRED_NOURISHMENT, SACRED_SPIRIT);
    }
}
