package com.sammy.malum.common.effect.geas;

import com.sammy.malum.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.resources.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import team.lodestar.lodestone.helpers.*;

public class AvariceEffect extends MobEffect {
    public AvariceEffect() {
        super(MobEffectCategory.BENEFICIAL, ColorHelper.getColor(255, 230, 93));
        final ResourceLocation id = MalumMod.malumPath("prospectors_greed");
    }

    public static int addFortune(LivingEntity entity) {
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
            return bonus;
        }
        return 0;
    }
}