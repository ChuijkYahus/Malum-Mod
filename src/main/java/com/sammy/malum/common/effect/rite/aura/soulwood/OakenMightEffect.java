package com.sammy.malum.common.effect.rite.aura.soulwood;

import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.magic.MalumSpiritTypes;
import com.sammy.malum.registry.common.sound.*;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.*;
import net.neoforged.neoforge.event.entity.living.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.modules.toolkit.sound.SoundPlayer;
import team.lodestar.lodestone.registry.common.tag.*;
import team.lodestar.wayward_attributes.WaywardTags;

public class OakenMightEffect extends MobEffect {
    public OakenMightEffect() {
        super(MobEffectCategory.BENEFICIAL, ColorHelper.getColor(MalumSpiritTypes.EARTHEN_COLORS().primaryColor()));
    }

    public static void increaseDamage(LivingDamageEvent.Pre event) {
        final DamageSource source = event.getSource();
        if (source.getDirectEntity() instanceof LivingEntity entity) {
            if (!source.is(WaywardTags.DamageTypeTags.CAN_TRIGGER_MAGIC_DAMAGE)) {
                return;
            }
            var instance = entity.getEffect(MalumMobEffects.OAKEN_MIGHT);
            if (instance == null) {
                return;
            }
            if (entity instanceof Player player && player.getAttackStrengthScale(0) < 0.9f) {
                return;
            }
            int amplifier = instance.getAmplifier() + 1;
            var weapon = entity.getWeaponItem();
            if (weapon.isEmpty() || weapon.is(MalumTags.Items.COUNTS_AS_EMPTY_HAND)) {
                amplifier *= 2;
                SoundPlayer.create(MalumSoundEvents.OAKEN_MIGHT_HIT).pitch(0.8f, 1.2f).play(entity);
            }
            event.setNewDamage(event.getNewDamage() + amplifier);
        }
    }
}