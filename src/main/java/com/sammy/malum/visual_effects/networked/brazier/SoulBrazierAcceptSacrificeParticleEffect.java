package com.sammy.malum.visual_effects.networked.brazier;

import com.sammy.malum.common.block.curiosities.soul_brazier.*;
import com.sammy.malum.visual_effects.*;
import com.sammy.malum.visual_effects.networked.*;
import com.sammy.malum.visual_effects.networked.data.*;
import net.minecraft.nbt.*;
import net.minecraft.world.entity.*;
import net.neoforged.api.distmarker.*;

import java.util.function.*;

public class SoulBrazierAcceptSacrificeParticleEffect extends ParticleEffectType {

    public SoulBrazierAcceptSacrificeParticleEffect(String id) {
        super(id);
    }

    public static NBTEffectData createData(LivingEntity entity) {
        CompoundTag tag = new CompoundTag();
        tag.putInt("entity", entity.getId());
        return new NBTEffectData(tag);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public Supplier<ParticleEffectActor> get() {
        return () -> (level, random, positionData, colorData, nbtData) -> {
            if (!(level.getBlockEntity(positionData.getAsBlockPos()) instanceof SoulBrazierBlockEntity brazier)) {
                return;
            }
            if (!nbtData.compoundTag.contains("entity")) {
                return;
            }
            if (level.getEntity(nbtData.compoundTag.getInt("entity")) instanceof LivingEntity entity) {
                SoulBindingBrazierParticleEffects.acceptSacrificeParticles(brazier, entity, colorData);
            }
        };
    }
}