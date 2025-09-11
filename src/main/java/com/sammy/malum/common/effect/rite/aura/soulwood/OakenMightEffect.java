package com.sammy.malum.common.effect.rite.aura.soulwood;

import com.sammy.malum.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.magic.MalumSpiritTypes;
import net.minecraft.world.*;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.neoforge.event.entity.living.*;
import team.lodestar.lodestone.helpers.ColorHelper;

public class OakenMightEffect extends MobEffect {
    public OakenMightEffect() {
        super(MobEffectCategory.BENEFICIAL, ColorHelper.getColor(MalumSpiritTypes.EARTHEN_COLORS().primaryColor()));
    }

    public static void increaseDamage(LivingDamageEvent.Pre event) {
        if (event.getSource().getDirectEntity() instanceof LivingEntity entity) {
            var instance = entity.getEffect(MalumMobEffects.OAKEN_MIGHT);
            if (instance == null) {
                return;
            }
            int amplifier = instance.getAmplifier() + 1;
            if (entity.getWeaponItem().isEmpty()) {
                amplifier *= 4;
            }
            float reduction = Math.min(amplifier * 0.1f, 0.5f);
            event.setNewDamage(event.getNewDamage() * reduction);
        }
    }
}