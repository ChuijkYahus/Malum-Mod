package com.sammy.malum.common.geas.oath.deliverance;

import com.sammy.malum.common.worldevent.*;
import com.sammy.malum.core.handlers.*;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.core.systems.events.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.magic.*;
import net.minecraft.network.chat.*;
import net.minecraft.server.level.*;
import net.minecraft.util.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.event.entity.living.*;
import team.lodestar.lodestone.handlers.*;

import java.util.function.*;

import static com.sammy.malum.common.item.curiosities.weapons.scythe.MalumScytheItem.canSweep;

public class UnmakersDisdainGeas extends GeasEffect {

    public UnmakersDisdainGeas() {
        super(MalumGeasEffectTypes.OATH_OF_UNMAKERS_DISDAIN.get());
    }

    @Override
    public void addTooltipComponents(LivingEntity entity, Consumer<Component> tooltipAcceptor, TooltipFlag tooltipFlag) {
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("malignant_crit_combo"));
        tooltipAcceptor.accept(ComponentHelper.negativeGeasEffect("malignant_crit_health_condition"));
        super.addTooltipComponents(entity, tooltipAcceptor, tooltipFlag);
    }

    @Override
    public void malignantCritEvent(MalignantCritEvent.Pre event, LivingEntity attacker) {
        float health = attacker.getHealth() / attacker.getMaxHealth();
        if (health <= 0.8f) {
            event.setCanceled(true);
        }
    }

    @Override
    public void finalizedOutgoingDamageEvent(LivingDamageEvent.Post event, LivingEntity attacker, LivingEntity target, ItemStack stack) {
        if (attacker.level() instanceof ServerLevel level) {
            var source = event.getSource();
            if (source.is(MalumDamageTypes.UNMAKERS_DISDAIN_COMBO)) {
                var random = attacker.getRandom();
                var particle = MalumParticleEffectTypes.SCYTHE_SLASH.createEffect()
                        .originatesFrom(attacker)
                        .targets(target)
                        .tiedToTarget()
                        .forwardOffset(-2f)
                        .upwardOffset(-0.5f)
                        .randomSlashRotation(random)
                        .mirroredRandomly(random);
                if (SoulDataHandler.getScytheWeapon(source, attacker).isEmpty() || !canSweep(attacker)) {
                    particle.horizontalOffset(0.75f).verticalSlashRotation();
                }
                particle.spawn(level);
            }
        }
    }

    @Override
    public void finalizedMalignantCritEvent(MalignantCritEvent.Post event, LivingEntity attacker) {
        var target = event.getLivingEntity();
        if (!target.level().isClientSide) {
            var source = event.getSource();
            var random = target.getRandom();
            int extraHits = random.nextInt(4, 6);
            float damage = (float) (attacker.getAttribute(Attributes.ATTACK_DAMAGE).getValue()) / extraHits * 0.75f;
            for (int i = 0; i < extraHits; i++) {
                int delay = 4 + i;
                WorldEventHandler.addWorldEvent(target.level(),
                        new DelayedDamageWorldEvent(target)
                                .setAttacker(attacker, source.getDirectEntity())
                                .setDamageData(damage, 0, delay)
                                .setPhysicalDamageType(MalumDamageTypes.UNMAKERS_DISDAIN_COMBO)
                                .setSound(MalumSoundEvents.MALIGNANT_METAL_COMBO, 0.5f, 1.5f, 0.3f));

            }
        }
    }
}