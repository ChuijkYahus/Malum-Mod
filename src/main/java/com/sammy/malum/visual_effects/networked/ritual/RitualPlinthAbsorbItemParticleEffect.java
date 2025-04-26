package com.sammy.malum.visual_effects.networked.ritual;

import com.sammy.malum.common.block.curiosities.ritual_plinth.*;
import com.sammy.malum.visual_effects.*;
import com.sammy.malum.visual_effects.networked.*;
import team.lodestar.lodestone.systems.network.*;
import net.minecraft.nbt.*;
import net.minecraft.world.item.*;
import net.minecraft.world.phys.*;
import net.neoforged.api.distmarker.*;
import team.lodestar.lodestone.systems.network.particle.NetworkedParticleEffectExtraData;
import team.lodestar.lodestone.systems.network.particle.NetworkedParticleEffectType;

import java.util.function.*;

public class RitualPlinthAbsorbItemParticleEffect extends MalumNetworkedParticleEffectType {

    public RitualPlinthAbsorbItemParticleEffect(String id) {
        super(id);
    }

    public static NetworkedParticleEffectExtraData createData(Vec3 targetPos, ItemStack stack) {
        NetworkedParticleEffectExtraData effectData = new NetworkedParticleEffectExtraData(stack);
        final CompoundTag compoundTag = effectData.compoundTag;
        CompoundTag position = new CompoundTag();
        position.putDouble("x", targetPos.x);
        position.putDouble("y", targetPos.y);
        position.putDouble("z", targetPos.z);
        compoundTag.put("targetPosition", position);
        return effectData;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public Supplier<ParticleEffectActor> get() {
        return () -> (level, random, positionData, colorData, nbtData) -> {
            if (!(level.getBlockEntity(positionData.getAsBlockPos()) instanceof RitualPlinthBlockEntity ritualPlinth)) {
                return;
            }
            final CompoundTag compoundTag = nbtData.compoundTag.getCompound("targetPosition");
            RitualPlinthParticleEffects.eatItemParticles(ritualPlinth, new Vec3(compoundTag.getDouble("x"), compoundTag.getDouble("y"), compoundTag.getDouble("z")), colorData, nbtData.getStack());
        };
    }
}