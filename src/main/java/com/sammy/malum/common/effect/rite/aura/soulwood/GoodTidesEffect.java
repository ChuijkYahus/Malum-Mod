package com.sammy.malum.common.effect.rite.aura.soulwood;

import com.mojang.datafixers.util.Pair;
import com.sammy.malum.registry.common.MalumMobEffects;
import com.sammy.malum.registry.common.magic.MalumSpiritTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import team.lodestar.lodestone.helpers.ColorHelper;

public class GoodTidesEffect extends MobEffect {
    public GoodTidesEffect() {
        super(MobEffectCategory.BENEFICIAL, ColorHelper.getColor(MalumSpiritTypes.AQUEOUS_COLORS().primaryColor()));
    }

    public static Pair<Integer, Integer> increaseFishingStats(Player player) {
        MobEffectInstance instance = player.getEffect(MalumMobEffects.GOOD_TIDES);
        if (instance != null) {
            int bonus = instance.getAmplifier() + 1;
            int luckBonus = bonus / 2;
            int lureSpeedBonus = (bonus+1) / 2;
            return Pair.of(luckBonus, lureSpeedBonus);
        }
        return Pair.of(0, 0);
    }
}