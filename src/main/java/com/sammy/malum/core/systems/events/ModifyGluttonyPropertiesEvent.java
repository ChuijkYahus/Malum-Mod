package com.sammy.malum.core.systems.events;

import com.sammy.malum.registry.common.*;
import net.minecraft.core.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.neoforged.neoforge.event.entity.living.*;

public class ModifyGluttonyPropertiesEvent extends LivingEvent {

    public Holder<MobEffect> effect = MobEffectRegistry.GLUTTONY;

    public ModifyGluttonyPropertiesEvent(LivingEntity entity) {
        super(entity);
    }

    public void replaceEffect(Holder<MobEffect> effect) {
        this.effect = effect;
    }
}
