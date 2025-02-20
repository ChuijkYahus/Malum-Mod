package com.sammy.malum.core.handlers;

import com.sammy.malum.common.capabilities.*;
import com.sammy.malum.common.packets.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.util.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.*;
import net.neoforged.neoforge.event.tick.*;
import net.neoforged.neoforge.network.*;
import team.lodestar.lodestone.helpers.*;

public class StaffAbilityHandler {

    public static void recoverStaffCharges(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        if (!player.level().isClientSide) {
            final StaffAbilityData data = player.getData(AttachmentTypeRegistry.STAFF_ABILITIES);
            data.tickData(player);
            if (data.isDirty()) {
                PacketDistributor.sendToPlayersTrackingEntityAndSelf(player, new SyncStaffAbilityDataPayload(player.getId(), data));
                data.setDirty(false);
            }
        }
    }
}
