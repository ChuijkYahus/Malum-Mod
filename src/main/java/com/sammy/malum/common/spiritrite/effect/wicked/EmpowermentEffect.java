package com.sammy.malum.common.spiritrite.effect.wicked;

import com.sammy.malum.core.systems.registry.*;
import com.sammy.malum.core.systems.rite.effect.*;
import com.sammy.malum.core.systems.spirit.type.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.core.*;
import net.minecraft.server.level.*;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.*;
import team.lodestar.lodestone.helpers.*;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.*;

public class EmpowermentEffect extends SpiritRitePotionEffect<Monster> {

    public EmpowermentEffect() {
        super(Monster.class, MalumMobEffects.WICKED_EMPOWERMENT, WICKED_SPIRIT);
    }
}
