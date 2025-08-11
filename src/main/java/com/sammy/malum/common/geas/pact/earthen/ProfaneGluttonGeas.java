package com.sammy.malum.common.geas.pact.earthen;

import com.sammy.malum.common.entity.scythe.*;
import com.sammy.malum.common.item.curiosities.weapons.scythe.*;
import com.sammy.malum.common.worldevent.*;
import com.sammy.malum.core.handlers.*;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.core.systems.events.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.magic.*;
import com.sammy.malum.visual_effects.networked.MalumNetworkedParticleEffectColorData;
import net.minecraft.network.chat.*;
import net.minecraft.server.level.*;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.event.entity.living.*;
import team.lodestar.lodestone.handlers.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.registry.common.*;

import java.util.function.*;

import static net.minecraft.world.entity.EquipmentSlot.MAINHAND;

public class ProfaneGluttonGeas extends GeasEffect {

    public ProfaneGluttonGeas() {
        super(MalumGeasEffectTypes.PACT_OF_THE_PROFANE_GLUTTON.get());
    }

    @Override
    public void modifyGluttonyPropertiesEvent(ModifyGluttonyPropertiesEvent event, LivingEntity collector) {
        event.getProperties()
                .scaleInitialAmplifier(4)
                .scaleAmplifierGain(2)
                .scaleAmplifierLimit(2)
                .replaceEffectType(MalumMobEffects.DESPERATE_NEED);
    }

    @Override
    public void addTooltipComponents(LivingEntity entity, Consumer<Component> tooltipAcceptor, TooltipFlag tooltipFlag) {
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("desperate_need"));
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("desperate_need_scythe_proficiency"));
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("poison_slash"));
        tooltipAcceptor.accept(ComponentHelper.negativeGeasEffect("poison_slash_consumes_desperate_need"));
        tooltipAcceptor.accept(ComponentHelper.negativeGeasEffect("desperate_need_betrayal"));
        super.addTooltipComponents(entity, tooltipAcceptor, tooltipFlag);
    }

    @Override
    public void finalizedIncomingDamageEvent(LivingDamageEvent.Post event, LivingEntity attacker, LivingEntity target, ItemStack stack) {
        if (attacker == null) {
            if (event.getSource().is(DamageTypes.STARVE)) {
                target.addEffect(new MobEffectInstance(MobEffects.POISON, 40, 1));
            }
            return;
        }
        MobEffectInstance effect = attacker.getEffect(MobEffects.POISON);
        if (effect == null) {
            return;
        }
        MobEffectInstance copy = new MobEffectInstance(effect);
        copy.duration = Math.min(copy.duration, 30);
        target.addEffect(copy);
    }

    //TODO: This thing is rlly needlessly complicated
    @Override
    public void outgoingDamageEvent(LivingDamageEvent.Pre event, LivingEntity attacker, LivingEntity target, ItemStack stack) {
        if (attacker.level() instanceof ServerLevel level) {
            var effect = attacker.getEffect(MalumMobEffects.DESPERATE_NEED);
            if (effect == null) {
                return;
            }
            var source = event.getSource();
            var random = attacker.getRandom();
            if (source.is(MalumDamageTypes.DESPERATE_NEED_CUT)) {
                int amplifier = effect.getAmplifier();
                int consumedStacks = 1 + amplifier / 6;
                if (consumedStacks >= amplifier) {
                    attacker.removeEffect(MalumMobEffects.DESPERATE_NEED);
                } else {
                    effect.amplifier -= consumedStacks;
                    EntityHelper.syncEffect(effect, attacker);
                }
                int poisonDuration = 40;
                int poisonStrength = 1 + amplifier;
                target.addEffect(new MobEffectInstance(MobEffects.POISON, poisonDuration, poisonStrength, false, true, true));

                var particle = MalumParticleEffectTypes.SCYTHE_SLASH.createEffect()
                        .originatesFrom(attacker)
                        .targets(target)
                        .tiedToTarget()
                        .forwardOffset(-2f)
                        .upwardOffset(-0.5f)
                        .color(MalumSpiritTypes.EARTHEN_SPIRIT)
                        .mirroredRandomly(random);
                if (MalumScytheItem.canSweep(attacker)) {
                    MalumScytheItem.trySweep(attacker, target, event.getNewDamage());
                }
                else {
                    particle.verticalSlashRotation();
                }
                particle.slashRotation(particle.getSlashRotation() + RandomHelper.randomBetween(random, -0.8f, 0.8f));
                particle.spawn(level);
                return;
            }
            if (source.is(MalumTags.DamageTypeTags.IS_SCYTHE)) {
                MalumScytheItem.ScytheDamage damage = MalumScytheItem.getScytheDamage(source, attacker);
                float physicalDamage = damage.physicalDamage();
                float magicDamage = damage.magicDamage();
                float damageScalar = 0.5f;
                int delay = 2;

                float average = (physicalDamage + magicDamage) / 2;
                physicalDamage *= physicalDamage / average * damageScalar;
                magicDamage *= magicDamage / average * damageScalar;
                WorldEventHandler.addWorldEvent(level,
                        new DelayedDamageWorldEvent(target)
                                .setAttacker(attacker, source.getDirectEntity())
                                .setDamageData(physicalDamage, magicDamage, delay)
                                .setPhysicalDamageType(MalumDamageTypes.DESPERATE_NEED_CUT)
                                .setSound(MalumSoundEvents.DESPERATE_NEED_CUT, 0.9f, 1.1f, 1));
            }
        }
    }
}