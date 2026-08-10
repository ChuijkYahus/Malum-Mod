package com.sammy.malum.common.effect.geas;

import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.magic.MalumSpiritTypes;
import com.sammy.malum.registry.common.sound.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.phys.Vec3;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.modules.toolkit.sound.SoundPlayer;

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

                SoundPlayer.create(MalumSoundEvents.AVARICE_FORTUNE).pitch(0.8f, 1.2f).play(entity);

            }
            return bonus;
        }
        return 0;
    }
}