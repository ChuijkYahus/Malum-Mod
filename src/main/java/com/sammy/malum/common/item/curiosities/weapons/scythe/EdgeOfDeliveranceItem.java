package com.sammy.malum.common.item.curiosities.weapons.scythe;

import com.sammy.malum.common.item.*;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.core.systems.events.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.core.Holder;
import net.minecraft.server.level.*;
import net.minecraft.sounds.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.common.*;
import net.neoforged.neoforge.common.damagesource.*;
import net.neoforged.neoforge.event.entity.living.*;
import team.lodestar.lodestone.handlers.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.systems.item.*;

public class EdgeOfDeliveranceItem extends MalumScytheItem {

    public EdgeOfDeliveranceItem(Tier tier, float attackDamage, float attackSpeed, LodestoneItemProperties properties) {
        super(tier, attackDamage, attackSpeed, properties);
    }

    @Override
    public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
        if (enchantment.equals(Enchantments.BREACH)) {
            return true;
        }
        return super.supportsEnchantment(stack, enchantment);
    }

    @Override
    public void modifyAttributeTooltipEvent(AddAttributeTooltipsEvent event) {
        event.addTooltipLines(ComponentHelper.positiveEffect("edge_of_deliverance_crit"));
        event.addTooltipLines(ComponentHelper.negativeEffect("edge_of_deliverance_unpowered_attack"));
    }

    @Override
    public void outgoingDamageEvent(LivingDamageEvent.Pre event, LivingEntity attacker, LivingEntity target, ItemStack stack) {
        super.outgoingDamageEvent(event, attacker, target, stack);
        if (attacker.level() instanceof ServerLevel level) {
            var source = event.getSource();
            if (source.is(MalumTags.DamageTypeTags.IS_SCYTHE) || source.is(MalumDamageTypes.INVERTED_HEART_PROPAGATION) || source.is(MalumDamageTypes.UNMAKERS_DISDAIN_COMBO)) {
                var effect = MalumMobEffects.IMMINENT_DELIVERANCE;
                if (target.hasEffect(effect)) {
                    if (triggerMalignantCrit(event.getContainer(), attacker, target)) {
                        var particle = MalumParticleEffectTypes.EDGE_OF_DELIVERANCE_CRIT.createEffect()
                                .originatesFrom(attacker).targets(target).tiedToTarget().forwardOffset(-0.8f).upwardOffset(-0.4f);
                        if (!canSweep(attacker)) {
                            particle.verticalSlashRotation();
                        }
                        particle.spawn(level);
                        target.removeEffect(effect);
                    }
                } else {
                    event.setNewDamage(event.getNewDamage() * 0.5f);
                    if (source.is(MalumTags.DamageTypeTags.IS_HIDDEN_BLADE) && attacker.getRandom().nextFloat() <= 0.5f) {
                        return;
                    }
                    target.addEffect(new MobEffectInstance(MalumMobEffects.IMMINENT_DELIVERANCE, 60));
                }
            }
        }
    }

    @Override
    public Holder<SoundEvent> getScytheSound(boolean canSweep) {
        return canSweep ? MalumSoundEvents.EDGE_OF_DELIVERANCE_SWEEP : MalumSoundEvents.EDGE_OF_DELIVERANCE_CUT;
    }

    public static boolean triggerMalignantCrit(DamageContainer damageContainer, LivingEntity attacker, LivingEntity target) {
        var event = new MalignantCritEvent.Pre(target, damageContainer);
        var eventResponders = ItemEventHandler.getEventResponders(attacker);
        eventResponders.forEach(lookup -> lookup.run(IMalumEventResponder.class,
                (eventResponderItem, stack) -> eventResponderItem.malignantCritEvent(event, attacker)));
        NeoForge.EVENT_BUS.post(event);

        if (event.isCanceled()) {
            return false;
        }
        var postEvent = new MalignantCritEvent.Post(target, damageContainer);
        eventResponders.forEach(lookup -> lookup.run(IMalumEventResponder.class,
                (eventResponderItem, stack) -> eventResponderItem.finalizedMalignantCritEvent(postEvent, attacker)));
        NeoForge.EVENT_BUS.post(postEvent);

        damageContainer.setNewDamage(damageContainer.getNewDamage() * 2);
        SoundHelper.playSound(target, MalumSoundEvents.MALIGNANT_METAL_MOTIF.get(), SoundSource.PLAYERS, 2f, 0.75f);
        SoundHelper.playSound(target, MalumSoundEvents.MALIGNANT_METAL_MOTIF.get(), SoundSource.PLAYERS, 3f, 1.25f);
        SoundHelper.playSound(target, MalumSoundEvents.MALIGNANT_METAL_MOTIF.get(), SoundSource.PLAYERS, 3f, 1.75f);
        return true;
    }
}