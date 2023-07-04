package com.sammy.malum.registry.common;

import com.sammy.malum.*;
import com.sammy.malum.common.packets.*;
import com.sammy.malum.common.packets.particle.*;
import com.sammy.malum.common.packets.particle.curiosities.altar.*;
import com.sammy.malum.common.packets.particle.curiosities.blight.*;
import com.sammy.malum.common.packets.particle.curiosities.nitrate.*;
import com.sammy.malum.common.packets.particle.curiosities.rite.*;
import com.sammy.malum.common.packets.particle.curiosities.rite.generic.*;
import net.minecraftforge.eventbus.api.*;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.network.*;
import net.minecraftforge.network.simple.*;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = MalumMod.MALUM, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PacketRegistry {
    public static final String PROTOCOL_VERSION = "1";
    public static SimpleChannel MALUM_CHANNEL = NetworkRegistry.newSimpleChannel(MalumMod.malumPath("main"), () -> PacketRegistry.PROTOCOL_VERSION, PacketRegistry.PROTOCOL_VERSION::equals, PacketRegistry.PROTOCOL_VERSION::equals);

    @SuppressWarnings("UnusedAssignment")
    @SubscribeEvent
    public static void registerNetworkStuff(FMLCommonSetupEvent event) {
        int index = 0;

        //functionality
        VoidRejectionPacket.register(MALUM_CHANNEL, index++);

        ParticleEffectPacket.register(MALUM_CHANNEL, index++);

        //vfx
        AltarCraftParticlePacket.register(MALUM_CHANNEL, index++);
        AltarConsumeParticlePacket.register(MALUM_CHANNEL, index++);

        MajorEntityEffectParticlePacket.register(MALUM_CHANNEL, index++);
        EthericNitrateParticlePacket.register(MALUM_CHANNEL, index++);
        VividNitrateBounceParticlePacket.register(MALUM_CHANNEL, index++);
        SuccessfulSoulHarvestParticlePacket.register(MALUM_CHANNEL, index++);

        TotemPoleActivationEffectPacket.register(MALUM_CHANNEL, index++);
        SacredMistRiteEffectPacket.register(MALUM_CHANNEL, index++);
        BlockSparkleParticlePacket.register(MALUM_CHANNEL, index++);
        InfernalAccelerationRiteEffectPacket.register(MALUM_CHANNEL, index++);
        InfernalExtinguishRiteEffectPacket.register(MALUM_CHANNEL, index++);
        AerialBlockFallRiteEffectPacket.register(MALUM_CHANNEL, index++);
        SpiritRiteActivationEffectPacket.register(MALUM_CHANNEL, index++);

        SyncMalumPlayerCapabilityDataPacket.register(MALUM_CHANNEL, index++);
        SyncLivingCapabilityDataPacket.register(MALUM_CHANNEL, index++);

        BlightMistParticlePacket.register(MALUM_CHANNEL, index++);
        BlightTransformItemParticlePacket.register(MALUM_CHANNEL, index++);

    }
}