package com.sammy.malum.common.effect.geas;

import com.sammy.malum.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.resources.*;
import net.minecraft.util.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import team.lodestar.lodestone.helpers.*;

public class ProspectorsGreedEffect extends MobEffect {
    public ProspectorsGreedEffect() {
        super(MobEffectCategory.BENEFICIAL, ColorHelper.getColor(255, 230, 93));
        final ResourceLocation id = MalumMod.malumPath("prospectors_streak");
        addAttributeModifier(Attributes.BLOCK_BREAK_SPEED, id, 0.1f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    }

    public static int addFortune(LivingEntity entity) {
        var effect = MalumMobEffects.PROSPECTORS_STREAK;
        var instance = entity.getEffect(effect);
        if (instance != null) {
            float chance = 0.1f * (instance.getAmplifier() + 1);
            int bonus = 0;
            final RandomSource rand = entity.level().getRandom();
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