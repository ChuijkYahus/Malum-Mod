package com.sammy.malum.common.effect.geas;

import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.magic.MalumSpiritTypes;
import com.sammy.malum.registry.common.sound.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.phys.Vec3;
import team.lodestar.lodestone.helpers.*;

import javax.annotation.Nullable;

public class AvariceEffect extends MobEffect {
    public AvariceEffect() {
        super(MobEffectCategory.BENEFICIAL, ColorHelper.getColor(255, 230, 93));
    }

    public static int addFortune(LivingEntity entity, @Nullable Vec3 position, int enchantmentLevel) {
        if (!(entity.level() instanceof ServerLevel level)) {
            return 0;
        }

        var effect = MalumMobEffects.AVARICE;
        var instance = entity.getEffect(effect);
        if (instance != null) {
            float chance = 0.1f * (instance.getAmplifier() + 1);
            int bonus = 0;
            var rand = entity.level().getRandom();
            while (chance > 0) {
                if (chance >= 1 || rand.nextFloat() < chance) {
                    bonus++;
                }
                chance--;
            }
            if (bonus > 0) {
                if (enchantmentLevel < 3) {
                    bonus += 3 - enchantmentLevel;
                }
                if (position != null) {
                    MalumParticleEffectTypes.AVARICE_FORTUNE_EFFECT.createEffect(position)
                            .color(MalumSpiritTypes.INFERNAL_SPIRIT)
                            .spawn(level);
                }
                SoundHelper.playSound(entity, MalumSoundEvents.AVARICE_FORTUNE.get(), 1.0f, 0.8f + entity.getRandom().nextFloat() * 0.4f);
            }
            return bonus;
        }
        return 0;
    }
}