package com.sammy.malum.registry.common;

import com.sammy.malum.MalumMod;
import com.sammy.malum.registry.common.magic.rite.MalumSpiritRiteTypes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AddReloadListenerEvent;

@EventBusSubscriber(modid = MalumMod.MALUM)
public class MalumSpiritRiteReloadListeners {

    @SubscribeEvent
    public static void registerReloadListeners(AddReloadListenerEvent event) {
        event.addListener(
                MalumSpiritRiteTypes.RITE_RECIPES
        );
    }
}