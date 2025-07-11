package com.sammy.malum.common.effect.rite.aura.soulwood;

import com.sammy.malum.*;
import com.sammy.malum.registry.common.MalumMobEffects;
import com.sammy.malum.registry.common.magic.MalumSpiritTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.*;
import net.neoforged.neoforge.event.entity.living.LivingEvent;
import net.neoforged.neoforge.event.entity.living.LivingFallEvent;
import team.lodestar.lodestone.helpers.ColorHelper;

public class SkyTetherEffect extends MobEffect {
    public SkyTetherEffect() {
        super(MobEffectCategory.BENEFICIAL, ColorHelper.getColor(MalumSpiritTypes.AERIAL_COLORS().primaryColor()));
        var id = MalumMod.malumPath("sky_tether");
        addAttributeModifier(Attributes.GRAVITY, id, -0.2f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        addAttributeModifier(Attributes.JUMP_STRENGTH, id, 0.2f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    }

    public static void onEntityFall(LivingFallEvent event) {
        LivingEntity entity = event.getEntity();
        MobEffectInstance effectInstance = entity.getEffect(MalumMobEffects.SKY_TETHER);
        if (effectInstance != null) {
            event.setDistance(event.getDistance() / (6 + effectInstance.getAmplifier()));
        }
    }
}