package com.sammy.malum.registry.common;

import com.sammy.malum.MalumMod;
import com.sammy.malum.common.payloads.*;
import com.sammy.malum.common.payloads.spirit_diode.SpiritDiodeStateUpdatePayload;
import com.sammy.malum.common.payloads.spirit_diode.SpiritDiodeVisualUpdatePayload;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import team.lodestar.lodestone.registry.common.LodestoneNetworkPayloads;

@SuppressWarnings("unused")
@EventBusSubscriber(modid = MalumMod.MALUM, bus = EventBusSubscriber.Bus.MOD)
public class MalumPayloadTypes {

    public static LodestoneNetworkPayloads.PayloadRegistryHelper MALUM_CHANNEL = new LodestoneNetworkPayloads.PayloadRegistryHelper(MalumMod.MALUM);

    @SubscribeEvent
    public static void registerNetworkStuff(RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1");

        MALUM_CHANNEL.playToClient(registrar, "void_rejection", VoidRejectionPayload.class, VoidRejectionPayload::new);

        MALUM_CHANNEL.playToClient(registrar, "spirit_diode_update", SpiritDiodeVisualUpdatePayload.class, SpiritDiodeVisualUpdatePayload::new);
        MALUM_CHANNEL.playToServer(registrar, "spirit_diode_toggle", SpiritDiodeStateUpdatePayload.class, SpiritDiodeStateUpdatePayload::new);

        MALUM_CHANNEL.playToClient(registrar, "sync_soul_ward_data", SyncSoulWardDataPayload.class, SyncSoulWardDataPayload::new);
        MALUM_CHANNEL.playToClient(registrar, "sync_curio_data", SyncCurioDataPayload.class, SyncCurioDataPayload::new);
        MALUM_CHANNEL.playToClient(registrar, "sync_geas_data", SyncGeasDataPayload.class, SyncGeasDataPayload::new);
        MALUM_CHANNEL.playToClient(registrar, "sync_staff_ability_data", SyncStaffAbilityDataPayload.class, SyncStaffAbilityDataPayload::new);
    }
}