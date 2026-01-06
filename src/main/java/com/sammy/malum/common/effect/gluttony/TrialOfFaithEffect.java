package com.sammy.malum.common.effect.gluttony;

import com.sammy.malum.*;
import com.sammy.malum.compat.irons_spellbooks.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.util.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.entity.player.*;
import net.neoforged.neoforge.event.entity.living.*;
import team.lodestar.lodestone.helpers.*;

import static com.sammy.malum.common.effect.gluttony.GluttonyEffect.spawnLocusts;

public class TrialOfFaithEffect extends MobEffect {
    public TrialOfFaithEffect() {
        super(MobEffectCategory.BENEFICIAL, ColorHelper.getColor(88, 86, 60));
        addAttributeModifier(MalumAttributes.HEALING_MULTIPLIER, MalumMod.malumPath("trial_of_faith"), 0.1f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyEffectTick(LivingEntity entityLivingBaseIn, int amplifier) {
        if (entityLivingBaseIn instanceof Player player) {
            player.causeFoodExhaustion(0.005f * (amplifier + 1));
        }
        return true;
    }

    public static void onHeal(LivingHealEvent event) {
        var entity = event.getEntity();
        var instance = entity.getEffect(MalumMobEffects.TRIAL_OF_FAITH);
        if (instance == null) {
            return;
        }
        int amount = 1 + Mth.floor(event.getAmount() / 2f);
        if (spawnLocusts(entity, entity, amount)) {
            instance.amplifier--;
            if (instance.amplifier < 0) {
                entity.removeEffect(MalumMobEffects.TRIAL_OF_FAITH);
            }
            else {
                EntityHelper.syncEffect(instance, entity);
            }
        }
    }
}