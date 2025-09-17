package com.sammy.malum.core.systems.spirit;

import net.minecraft.core.*;
import net.minecraft.server.level.*;
import net.minecraft.world.phys.*;
import team.lodestar.lodestone.helpers.block.*;

import java.util.*;
import java.util.stream.*;

public interface SpiritEntityAcceptor {



    Vec3 getSpiritDestination();


//    static List<SpiritEntityAcceptor> findNearbyAcceptors(ServerLevel level, BlockPos spiritPos, int range) {
//        var nearbyAcceptors = BlockEntityHelper.getBlockEntitiesStream(SpiritEntityAcceptor.class, level, spiritPos, range);
//    }
//
//    record SearchResult(List<SpiritEntityAcceptor> nearbyAcceptors, BlockPos spiritPos, long captureTime) {
//
//    }
}
