package com.sammy.malum.visual_effects.networked.arcana_pylon;

import com.sammy.malum.common.block.curiosities.obelisk.rite_pylon.*;
import com.sammy.malum.common.block.curiosities.runic_workbench.*;
import com.sammy.malum.visual_effects.*;
import com.sammy.malum.visual_effects.networked.*;
import net.minecraft.util.*;
import net.minecraft.world.level.*;
import net.neoforged.api.distmarker.*;
import team.lodestar.lodestone.systems.network.particle.*;

public class ArcanaPylonEatSpiritParticleEffect extends MalumNetworkedParticleEffectType<NetworkedParticleEffectExtraData> {

    public ArcanaPylonEatSpiritParticleEffect(String id) {
        super(id);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void act(Level level, RandomSource random, NetworkedParticleEffectPositionData positionData, MalumNetworkedParticleEffectColorData colorData, NetworkedParticleEffectExtraData extraData) {
        if (!(level.getBlockEntity(positionData.getAsBlockPos()) instanceof ArcanaPylonBlockEntity arcanaPylon)) {
            return;
        }
        ArcanaPylonParticleEffects.eatSpiritParticles(level, arcanaPylon, colorData);
    }
}