package com.sammy.malum.common.packets;

import com.sammy.malum.common.data.attachment.soul_data.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.network.*;
import net.minecraft.world.entity.*;
import net.neoforged.neoforge.network.handling.*;
import team.lodestar.lodestone.systems.network.*;

public class SyncGeasDataPayload extends OneSidedPayloadData {
    private final int entityId;
    private final GeasSoulData data;

    public SyncGeasDataPayload(int entityId, GeasSoulData data) {
        this.entityId = entityId;
        this.data = data;
    }

    public SyncGeasDataPayload(FriendlyByteBuf buf) {
        this.entityId = buf.readInt();
        this.data = GeasSoulData.STREAM_CODEC.decode(buf);
    }

    public void handle(IPayloadContext context) {
        Entity entity = context.player().level().getEntity(entityId);
        if (entity instanceof LivingEntity livingEntity) {
            livingEntity.setData(MalumAttachmentTypes.GEAS_SOUL_INFO, data);
        }
    }

    @Override
    public void serialize(FriendlyByteBuf buf) {
        buf.writeInt(entityId);
        GeasSoulData.STREAM_CODEC.encode(buf, data);
    }
}