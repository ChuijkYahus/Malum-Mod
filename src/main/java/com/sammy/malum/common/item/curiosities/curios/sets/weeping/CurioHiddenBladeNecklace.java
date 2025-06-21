package com.sammy.malum.common.item.curiosities.curios.sets.weeping;

import com.sammy.malum.common.entity.hidden_blade.*;
import com.sammy.malum.common.item.*;
import com.sammy.malum.common.item.curiosities.curios.*;
import com.sammy.malum.common.payloads.*;
import com.sammy.malum.core.handlers.*;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.item.*;
import net.minecraft.network.chat.*;
import net.minecraft.server.level.*;
import net.minecraft.util.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.event.entity.living.*;
import net.neoforged.neoforge.event.tick.*;
import net.neoforged.neoforge.network.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.registry.common.*;

import java.util.function.*;

public class CurioHiddenBladeNecklace extends MalumCurioItem implements IMalumEventResponder, IVoidItem {

    public static final int COOLDOWN_DURATION = 200;

    public CurioHiddenBladeNecklace(Properties builder) {
        super(builder, MalumTrinketType.VOID);
    }

    @Override
    public void addExtraTooltipLines(Consumer<Component> consumer) {
        consumer.accept(ComponentHelper.positiveCurioEffect("scythe_counterattack"));
        consumer.accept(ComponentHelper.negativeCurioEffect("pacifist_recharge"));
    }

    @Override
    public void incomingDamageEvent(LivingDamageEvent.Pre event, LivingEntity attacker, LivingEntity attacked, ItemStack stack) {
        if (attacked.level().isClientSide()) {
            return;
        }
        if (attacked.getData(MalumAttachmentTypes.CURIO_DATA).hiddenBladeNecklaceCooldown == 0) {
            float damage = event.getOriginalDamage();
            int amplifier = Math.min(Mth.floor(damage / 4), 9);
            attacked.addEffect(new MobEffectInstance(MalumMobEffects.WICKED_INTENT, 80, amplifier));
            SoundHelper.playSound(attacked, MalumSoundEvents.HIDDEN_BLADE_PRIMED.get(), 1f, RandomHelper.randomBetween(attacked.level().getRandom(), 1.4f, 1.6f));
        }
    }

    @Override
    public void outgoingDamageEvent(LivingDamageEvent.Pre event, LivingEntity attacker, LivingEntity target, ItemStack stack) {
        if (attacker.level() instanceof ServerLevel level) {
            var source = event.getSource();
            if (!source.is(MalumDamageTypes.SCYTHE_MELEE)) {
                return;
            }
            if (CurioHelper.hasCurioEquipped(attacker, MalumItems.NECKLACE_OF_THE_HIDDEN_BLADE.get())) {
                var data = attacker.getData(MalumAttachmentTypes.CURIO_DATA);
                var random = level.getRandom();
                if (data.hiddenBladeNecklaceCooldown != 0) {
                    if (data.hiddenBladeNecklaceCooldown <= COOLDOWN_DURATION) {
                        SoundHelper.playSound(attacker, MalumSoundEvents.HIDDEN_BLADE_DISRUPTED.get(), 1f, RandomHelper.randomBetween(random, 0.7f, 0.8f));
                    }
                    data.hiddenBladeNecklaceCooldown = (int) (COOLDOWN_DURATION * 1.5);
                    PacketDistributor.sendToPlayersTrackingEntityAndSelf(attacker, new SyncCurioDataPayload(attacker.getId(), data));
                    return;
                }
                var effect = attacker.getEffect(MalumMobEffects.WICKED_INTENT);
                if (effect == null) {
                    return;
                }
                var scytheWeapon = SoulDataHandler.getScytheWeapon(source, attacker);
                var direction = attacker.getLookAngle();
                var damageCenter = attacker.position().add(direction);
                var attributes = attacker.getAttributes();
                float multiplier = 1 + (effect.amplifier + 1) * 0.2f;
                int duration = 25;

                float physicalDamage = (float) (event.getNewDamage() + attributes.getValue(Attributes.ATTACK_DAMAGE)) / duration * multiplier;
                float magicDamage = (float) (attributes.getValue(LodestoneAttributes.MAGIC_DAMAGE) / duration) * multiplier;

                var entity = new HiddenBladeDelayedImpactEntity(level, damageCenter.x, damageCenter.y - 3f + attacker.getBbHeight() / 2f, damageCenter.z);
                entity.setData(attacker, physicalDamage, magicDamage, duration);
                entity.setItem(scytheWeapon);
                level.addFreshEntity(entity);
                PacketDistributor.sendToPlayersTrackingEntityAndSelf(attacker, new SyncCurioDataPayload(attacker.getId(), data));
                if (!effect.isInfiniteDuration()) {
                    data.hiddenBladeNecklaceCooldown = COOLDOWN_DURATION;
                    attacker.removeEffect(effect.getEffect());
                }
                for (int i = 0; i < 3; i++) {
                    SoundHelper.playSound(attacker, MalumSoundEvents.HIDDEN_BLADE_UNLEASHED.get(), 3f, RandomHelper.randomBetween(random, 0.75f, 1.25f));
                }
                MalumParticleEffectTypes.HIDDEN_BLADE_COUNTER_FLURRY.createEffect()
                        .originatesFrom(attacker)
                        .aimedAt(direction)
                        .color(scytheWeapon.getItem())
                        .mirroredRandomly(random)
                        .randomSlashRotation(random)
                        .spawn(level);
                event.setNewDamage(0);
            }
        }
    }
    public static void entityTick(EntityTickEvent.Pre event) {
        if (event.getEntity() instanceof LivingEntity entity) {
            var level = entity.level();
            var data = entity.getData(MalumAttachmentTypes.CURIO_DATA);
            if (data.hiddenBladeNecklaceCooldown > 0) {
                data.hiddenBladeNecklaceCooldown--;
                if (!level.isClientSide()) {
                    if (data.hiddenBladeNecklaceCooldown == 0) {
                        SoundHelper.playSound(entity, MalumSoundEvents.HIDDEN_BLADE_CHARGED.get(), 1f, RandomHelper.randomBetween(level.getRandom(), 1.0f, 1.2f));
                    }
                }
            }
        }
    }
}
