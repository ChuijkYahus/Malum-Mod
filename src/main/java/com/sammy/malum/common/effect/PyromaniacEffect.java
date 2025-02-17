package com.sammy.malum.common.effect;

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
        addAttributeModifier(Attributes.MOVEMENT_SPEED, MalumMod.malumPath("pyromaniac"), 0.05f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    }

    public static void increaseDigSpeed(PlayerEvent.BreakSpeed event) {
        Player player = event.getEntity();
        if (player.hasEffect(MobEffectRegistry.PYROMANIACS_FERVOR)) {
            final int amplifier = player.getEffect(MobEffectRegistry.PYROMANIACS_FERVOR).getAmplifier()+1;
            event.setNewSpeed(event.getOriginalSpeed() * (1 + 0.05f * amplifier));
        }
    }
}