package com.sammy.malum.common.effect.rite.aura;

import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.magic.MalumSpiritTypes;
import net.minecraft.world.effect.*;
import net.neoforged.neoforge.event.entity.living.*;
import team.lodestar.lodestone.helpers.ColorHelper;

public class StoneWardEffect extends MobEffect {
    public StoneWardEffect() {
        super(MobEffectCategory.BENEFICIAL, ColorHelper.getColor(MalumSpiritTypes.EARTHEN_COLORS().primaryColor()));
    }

    public static void reduceDamage(LivingDamageEvent.Pre event) {
        var entity = event.getEntity();
        var instance = entity.getEffect(MalumMobEffects.STONE_WARD);
        if (instance == null) {
            return;
        }
        int amplifier = instance.getAmplifier()+1;
        float armorCoverPercentage = entity.getArmorCoverPercentage();
        if (armorCoverPercentage == 0) {
            amplifier *= 2;
        }
        float reduction = Math.min(amplifier * 0.1f, 0.5f);
        event.setNewDamage(event.getNewDamage()*reduction);
    }
}