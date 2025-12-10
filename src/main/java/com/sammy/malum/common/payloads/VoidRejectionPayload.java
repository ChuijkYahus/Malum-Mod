package com.sammy.malum.common.payloads;

import com.sammy.malum.core.handlers.*;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.*;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import team.lodestar.lodestone.systems.network.OneSidedPayloadData;

public class VoidRejectionPayload extends OneSidedPayloadData {
    private final int entityId;

    public VoidRejectionPayload(int entityId) {
        this.entityId = entityId;
    }

    public VoidRejectionPayload(RegistryFriendlyByteBuf byteBuf) {
        this.entityId = byteBuf.readInt();
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void handle(IPayloadContext iPayloadContext) {
        Entity entity = Minecraft.getInstance().level.getEntity(entityId);
        if (entity instanceof Player player) {
            WeepingWellRejectionHandler.launchPlayer(player);
        }
    }

    @Override
    public void serialize(RegistryFriendlyByteBuf buf) {
        buf.writeInt(entityId);
    }
}