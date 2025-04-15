package com.sammy.malum.common.geas.pact.wicked;

import com.google.common.collect.*;
import com.sammy.malum.common.worldevent.*;
import com.sammy.malum.core.handlers.*;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.visual_effects.networked.attack.slash.*;
import com.sammy.malum.visual_effects.networked.data.*;
import net.minecraft.core.*;
import net.minecraft.network.chat.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.event.entity.living.*;
import team.lodestar.lodestone.handlers.*;
import team.lodestar.lodestone.helpers.*;

import java.util.function.*;

public class ChallengerGeas extends GeasEffect {

    public ChallengerGeas() {
        super(MalumGeasEffectTypeRegistry.PACT_OF_THE_CHALLENGER.get());
    }

    public static void increaseDetection(LivingEvent.LivingVisibilityEvent event) {
        if (GeasEffectHandler.hasGeasEffect(event.getEntity(), MalumGeasEffectTypeRegistry.PACT_OF_THE_CHALLENGER)) {
            event.modifyVisibility(2f);
        }
    }

    @Override
    public void outgoingDamageEvent(LivingDamageEvent.Pre event, LivingEntity attacker, LivingEntity target, ItemStack stack) {
        if (target.getHealth() > target.getMaxHealth() * 0.95f) {
            var random = target.getRandom();
            var slashDirection = attacker.getLookAngle();
            var slashProperties = SlashAttackParticleEffect.createData(slashDirection, random.nextBoolean(), RandomHelper.randomBetween(random, -0.5f, 0.5f));
            WorldEventHandler.addWorldEvent(target.level(),
                    new DelayedDamageWorldEvent(target)
                            .setDamageData(0, 4, 2)
                            .setMagicDamageType(DamageTypeRegistry.VOODOO)
                            .setImpactParticleEffect(ParticleEffectTypeRegistry.SHAKEN_FAITH, new ColorEffectData(SpiritTypeRegistry.WICKED_SPIRIT))
                            .setParticleEffectNBT(slashProperties)
                            .setSound(SoundRegistry.SCYTHE_SWEEP, 0.5f, 1.5f, 0.3f));
        }
    }

    @Override
    public void addTooltipComponents(LivingEntity entity, Consumer<Component> tooltipAcceptor, TooltipFlag tooltipFlag) {
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("first_hit_bonus"));
        tooltipAcceptor.accept(ComponentHelper.negativeGeasEffect("aggressive_enemies"));
        super.addTooltipComponents(entity, tooltipAcceptor, tooltipFlag);
    }
}
