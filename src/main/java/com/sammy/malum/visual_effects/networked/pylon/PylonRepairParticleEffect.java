package com.sammy.malum.visual_effects.networked.pylon;

import com.sammy.malum.common.block.curiosities.repair_pylon.*;
import com.sammy.malum.common.block.storage.*;
import com.sammy.malum.visual_effects.*;
import com.sammy.malum.visual_effects.networked.*;
import net.minecraft.util.*;
import net.minecraft.world.level.*;
import team.lodestar.lodestone.systems.network.*;
import net.minecraft.core.*;
import net.minecraft.nbt.*;
import net.neoforged.api.distmarker.*;
import team.lodestar.lodestone.systems.network.particle.*;

import java.util.function.*;

public class PylonRepairParticleEffect extends MalumNetworkedParticleEffectType<PylonEffectData> {

    public PylonRepairParticleEffect(String id) {
        super(id);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void act(Level level, RandomSource random, NetworkedParticleEffectPositionData positionData, MalumNetworkedParticleEffectColorData colorData, PylonEffectData extraData) {
        if (!(level.getBlockEntity(positionData.getAsBlockPos()) instanceof RepairPylonCoreBlockEntity pylon)) {
            return;
        }
        if (!(level.getBlockEntity(extraData.holderPos()) instanceof IMalumSpecialItemAccessPoint holder)) {
            return;
        }
        RepairPylonParticleEffects.repairItemParticles(pylon, holder, colorData);
    }
}