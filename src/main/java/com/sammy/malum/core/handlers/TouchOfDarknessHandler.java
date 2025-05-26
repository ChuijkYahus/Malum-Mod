package com.sammy.malum.core.handlers;

import com.sammy.malum.registry.common.*;
import net.minecraft.world.entity.*;
import net.neoforged.neoforge.event.tick.*;

public class TouchOfDarknessHandler {

    public static void handlePrimordialSoupContact(LivingEntity livingEntity) {
        livingEntity.getData(MalumAttachmentTypes.TOUCH_OF_DARKNESS.get()).setAfflictionLevel(100);
    }

    public static void entityTick(EntityTickEvent.Pre event) {
        if (event.getEntity() instanceof LivingEntity livingEntity) {
            livingEntity.getData(MalumAttachmentTypes.TOUCH_OF_DARKNESS).update(livingEntity);
        }
    }
}