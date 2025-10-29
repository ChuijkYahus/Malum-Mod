package com.sammy.malum.core.handlers;

import com.sammy.malum.common.data.attachment.*;
import com.sammy.malum.common.payloads.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.world.entity.player.*;
import net.neoforged.neoforge.event.tick.*;
import net.neoforged.neoforge.network.*;

public class StaffAbilityHandler {

    public static void recoverStaffCharges(PlayerTickEvent.Pre event) {
        Player player = event.getEntity();
        if (!player.level().isClientSide) {
            if (player.hasData(MalumAttachmentTypes.STAFF_ABILITIES)) {
                var data = player.getData(MalumAttachmentTypes.STAFF_ABILITIES);
                data.tickData(player);
                if (data.isDirty()) {
                    player.syncData(MalumAttachmentTypes.STAFF_ABILITIES);
                    data.setDirty(false);
                }
            }
        }
    }
}
