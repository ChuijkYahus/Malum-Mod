package com.sammy.malum.visual_effects.networked.brazier;

import com.sammy.malum.common.block.curiosities.soul_brazier.SoulBrazierBlockEntity;
import com.sammy.malum.common.block.curiosities.spirit_altar.SpiritAltarBlockEntity;
import com.sammy.malum.visual_effects.SoulBindingBrazierParticleEffects;
import com.sammy.malum.visual_effects.SpiritAltarParticleEffects;
import com.sammy.malum.visual_effects.networked.ParticleEffectType;
import com.sammy.malum.visual_effects.networked.data.NBTEffectData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.function.Supplier;

public class SoulBrazierStartParticleEffect extends ParticleEffectType {

    public SoulBrazierStartParticleEffect(String id) {
        super(id);
    }

    public static NBTEffectData createData(SoulBrazierBlockEntity brazier) {
        CompoundTag tag = new CompoundTag();
        tag.putString("state", brazier.state.name);
        return new NBTEffectData(tag);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public Supplier<ParticleEffectActor> get() {
        return () -> (level, random, positionData, colorData, nbtData) -> {
            if (!(level.getBlockEntity(positionData.getAsBlockPos()) instanceof SoulBrazierBlockEntity brazier)) {
                return;
            }
            if (!nbtData.compoundTag.contains("state")) {
                return;
            }
            brazier.state = SoulBrazierBlockEntity.CODEC.byName(nbtData.compoundTag.getString("state"));
            SoulBindingBrazierParticleEffects.beginSoulBindingParticles(brazier, colorData);
        };
    }
}