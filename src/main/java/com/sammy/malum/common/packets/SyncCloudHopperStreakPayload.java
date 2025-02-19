package com.sammy.malum.common.packets;

import com.sammy.malum.common.geas.explosion.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.network.*;
import net.minecraft.world.entity.*;
import net.neoforged.neoforge.network.handling.*;
import team.lodestar.lodestone.systems.network.*;

public class SyncCloudHopperStreakPayload extends OneSidedPayloadData {
    private final int entityId;
    private final int streak;

    public SyncCloudHopperStreakPayload(int entityId, int streak) {
        this.entityId = entityId;
        this.streak = streak;
    }
    public SyncCloudHopperStreakPayload(FriendlyByteBuf buf) {
        this.entityId = buf.readInt();
        this.streak = buf.readInt();
    }

    public void handle(IPayloadContext context) {
        Entity entity = context.player().level().getEntity(entityId);
        if (entity instanceof LivingEntity livingEntity) {
            var data = livingEntity.getData(AttachmentTypeRegistry.LIVING_SOUL_INFO);
            var cloudskipper = MalumGeasEffectTypeRegistry.PACT_OF_THE_CLOUDSKIPPER;
            if (!data.hasGeasEffect(livingEntity, cloudskipper)) {
                data.addGeasEffect(cloudskipper.get().createDefaultStack());
            }
            var geas = data.getGeasEffect(livingEntity, cloudskipper).getValue();
            if (geas instanceof CloudSkipperGeas cloudSkipperGeas) {
                cloudSkipperGeas.streak = streak;
            }
            livingEntity.setData(AttachmentTypeRegistry.LIVING_SOUL_INFO, data);
        }
    }

    @Override
    public void serialize(FriendlyByteBuf buf) {
        buf.writeInt(entityId);
        buf.writeInt(streak);
    }
}
