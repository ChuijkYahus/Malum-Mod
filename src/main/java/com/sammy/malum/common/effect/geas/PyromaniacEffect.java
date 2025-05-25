package com.sammy.malum.common.effect.geas;

import com.sammy.malum.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.entity.player.*;
import net.neoforged.neoforge.event.entity.player.*;
import team.lodestar.lodestone.helpers.*;

public class PyromaniacEffect extends MobEffect {
    public PyromaniacEffect() {
        super(MobEffectCategory.BENEFICIAL, ColorHelper.getColor(255, 230, 93));
        var id = MalumMod.malumPath("pyromaniacs_fervor");
        addAttributeModifier(Attributes.MOVEMENT_SPEED, id, 0.05f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        addAttributeModifier(Attributes.BLOCK_BREAK_SPEED, id, 0.05f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    }
}