package com.sammy.malum.common.geas.pact.wicked;

import com.sammy.malum.common.worldevent.*;
import com.sammy.malum.core.handlers.*;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.visual_effects.networked.attack.slash.*;
import com.sammy.malum.visual_effects.networked.data.*;
import net.minecraft.network.chat.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.event.entity.living.*;
import team.lodestar.lodestone.handlers.*;
import team.lodestar.lodestone.helpers.*;

import java.util.function.*;

public class Wicked1Geas extends GeasEffect {

    public Wicked1Geas() {
        super(MalumGeasEffectTypeRegistry.WICKED_1.get());
    }
}
