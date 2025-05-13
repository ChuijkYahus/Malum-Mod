package com.sammy.malum.visual_effects.networked.geas;

import com.sammy.malum.visual_effects.*;
import com.sammy.malum.visual_effects.networked.*;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.*;
import team.lodestar.lodestone.systems.network.WeaponParticleEffectType.*;
import team.lodestar.lodestone.systems.network.particle.NetworkedParticleEffectPositionData;

public class ShakenFaithParticleEffect extends MalumNetworkedWeaponParticleEffectType<WeaponParticleEffectData> {

    public ShakenFaithParticleEffect(String id) {
        super(id);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void act(Level level, RandomSource random, NetworkedParticleEffectPositionData positionData, MalumNetworkedParticleEffectColorData colorData, WeaponParticleEffectData extraData) {
        GeasParticleEffects.warlockBlast(level, random, positionData, colorData, extraData);
    }
}