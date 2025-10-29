package com.sammy.malum.registry.common;

import com.sammy.malum.MalumMod;
import com.sammy.malum.common.payloads.*;
import com.sammy.malum.common.payloads.diode.SpiritDiodeStateUpdatePayload;
import com.sammy.malum.common.payloads.diode.SpiritDiodeVisualUpdatePayload;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import team.lodestar.lodestone.registry.common.LodestoneNetworkPayloads;

@SuppressWarnings("unused")
@EventBusSubscriber(modid = MalumMod.MALUM)
public class MalumPayloadTypes {

    public static LodestoneNetworkPayloads.PayloadRegistryHelper MALUM_CHANNEL = new LodestoneNetworkPayloads.PayloadRegistryHelper(MalumMod.MALUM);

    @SubscribeEvent
    public static void registerNetworkStuff(RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1");

        MALUM_CHANNEL.playToClient(registrar, "void_rejection", VoidRejectionPayload.class, VoidRejectionPayload::new);

        MALUM_CHANNEL.playToClient(registrar, "spirit_diode_update", SpiritDiodeVisualUpdatePayload.class, SpiritDiodeVisualUpdatePayload::new);
        MALUM_CHANNEL.playToServer(registrar, "spirit_diode_toggle", SpiritDiodeStateUpdatePayload.class, SpiritDiodeStateUpdatePayload::new);

    }
}