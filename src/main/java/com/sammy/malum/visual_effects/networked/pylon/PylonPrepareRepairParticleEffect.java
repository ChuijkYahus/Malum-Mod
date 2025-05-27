package com.sammy.malum.visual_effects.networked.pylon;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import com.sammy.malum.common.block.curiosities.repair_pylon.*;
import com.sammy.malum.common.block.storage.*;
import com.sammy.malum.visual_effects.*;
import com.sammy.malum.visual_effects.networked.*;
import com.sammy.malum.visual_effects.networked.altar.*;
import io.netty.buffer.*;
import net.minecraft.network.codec.*;
import net.minecraft.util.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import team.lodestar.lodestone.systems.network.*;
import net.minecraft.core.*;
import net.minecraft.nbt.*;
import net.neoforged.api.distmarker.*;
import team.lodestar.lodestone.systems.network.particle.*;

import java.util.*;
import java.util.function.*;

public class PylonPrepareRepairParticleEffect extends MalumNetworkedParticleEffectType<PylonEffectData> {

    public PylonPrepareRepairParticleEffect(String id) {
        super(id);
    }

    @Override
    public Optional<StreamCodec<ByteBuf, ? extends NetworkedParticleEffectExtraData>> getExtraCodec() {
        return Optional.of(PylonEffectData.STREAM_CODEC);
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
        RepairPylonParticleEffects.prepareRepairParticles(pylon, holder, colorData);
    }
}