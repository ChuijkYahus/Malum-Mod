package com.sammy.malum.events;

import com.sammy.malum.MalumMod;
import com.sammy.malum.client.screen.tooltip.ClientMalumPouchTooltip;
import com.sammy.malum.common.data.component.pouch.*;
import com.sammy.malum.core.handlers.client.*;
import com.sammy.malum.registry.client.*;
import com.sammy.malum.registry.common.MalumParticles;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;

@EventBusSubscriber(value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class ClientSetupEvents {

    @SubscribeEvent
    public static void setBlockColors(RegisterColorHandlersEvent.Block event) {
        MalumBlockColors.setBlockColors(event);
    }

    @SubscribeEvent
    public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        MalumClientExtensions.registerClientExtensions(event);
    }

    @SubscribeEvent
    public static void registerTooltipComponentManagers(RegisterClientTooltipComponentFactoriesEvent event) {
        event.register(SoulwovenPouchContentsComponent.class, ClientMalumPouchTooltip::new);
        event.register(RavenousPouchContentsComponent.class, ClientMalumPouchTooltip::new);
    }

    @SubscribeEvent
    public static void registerOverlays(RegisterGuiLayersEvent event) {
        event.registerAbove(VanillaGuiLayers.EXPERIENCE_LEVEL, MalumMod.malumPath("soul_ward"),
                SoulWardRenderHandler::renderSoulWard);
        event.registerAbove(VanillaGuiLayers.EXPERIENCE_LEVEL, MalumMod.malumPath("staff_charges"),
                StaffAbilityRenderHandler::renderStaffCharges);

        event.registerAbove(VanillaGuiLayers.EXPERIENCE_LEVEL, MalumMod.malumPath("hidden_blade_cooldown"),
                HiddenBladeRenderHandler::renderHiddenBladeCooldown);

        event.registerAboveAll(MalumMod.malumPath("touch_of_darkness"),
                TouchOfDarknessRenderHandler::renderDarknessVignette);
    }

    @SubscribeEvent
    public static void registerParticleFactory(RegisterParticleProvidersEvent event) {
        MalumParticles.registerParticleFactory(event);
        MalumScreenParticles.registerParticleFactory(event);
    }
}
