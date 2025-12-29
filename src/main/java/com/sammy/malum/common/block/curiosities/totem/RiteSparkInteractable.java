package com.sammy.malum.common.block.curiosities.totem;

import com.sammy.malum.common.entity.activator.rite.*;
import net.minecraft.server.level.*;

public interface RiteSparkInteractable {
    void travel(ServerLevel level, BlockRiteEffectActivator spark);

    default int getTravelCost(ServerLevel level, BlockRiteEffectActivator spark) {
        return 1;
    }
}
