package com.sammy.malum.core.systems.events;

import com.sammy.malum.common.effect.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.core.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.neoforged.neoforge.event.entity.living.*;

public class ModifyGluttonyPropertiesEvent extends LivingEvent {

    private final GluttonyEffect.GluttonyEffectProperties properties;

    public ModifyGluttonyPropertiesEvent(LivingEntity entity, GluttonyEffect.GluttonyEffectProperties properties) {
        super(entity);
        this.properties = properties;
    }

    public GluttonyEffect.GluttonyEffectProperties getProperties() {
        return properties;
    }
}
