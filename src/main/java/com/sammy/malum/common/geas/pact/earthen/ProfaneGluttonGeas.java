package com.sammy.malum.common.geas.pact.earthen;

import com.sammy.malum.common.worldevent.*;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.core.systems.events.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.magic.*;
import com.sammy.malum.visual_effects.networked.MalumNetworkedParticleEffectColorData;
import net.minecraft.network.chat.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.event.entity.living.*;
import team.lodestar.lodestone.handlers.*;
import team.lodestar.lodestone.helpers.*;

import java.util.function.*;

public class ProfaneGluttonGeas extends GeasEffect {

    public ProfaneGluttonGeas() {
        super(MalumGeasEffectTypes.PACT_OF_THE_PROFANE_GLUTTON.get());
    }

    @Override
    public void modifyGluttonyPropertiesEvent(ModifyGluttonyPropertiesEvent event, LivingEntity collector) {
        event.getProperties()
                .scaleInitialAmplifier(2)
                .scaleAmplifierGain(2)
                .scaleAmplifierLimit(4)
                .replaceEffectType(MalumMobEffects.DESPERATE_NEED);
    }

    @Override
    public void addTooltipComponents(LivingEntity entity, Consumer<Component> tooltipAcceptor, TooltipFlag tooltipFlag) {
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("desperate_need"));
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("desperate_need_scythe_proficiency"));
        tooltipAcceptor.accept(ComponentHelper.negativeGeasEffect("desperate_need_betrayal"));
        super.addTooltipComponents(entity, tooltipAcceptor, tooltipFlag);
    }

    @Override
    public void incomingDamageEvent(LivingDamageEvent.Pre event, LivingEntity attacker, LivingEntity target, ItemStack stack) {
        var effect = target.getEffect(MalumMobEffects.DESPERATE_NEED);
        if (effect != null) {
            EntityHelper.shortenEffect(effect, target, effect.getDuration() / 4);
            if (event.getSource().is(MalumDamageTypes.ROT)) {
                target.addEffect(new MobEffectInstance(MobEffects.POISON, 40, 0, true, true));
                return;
            }
            var random = target.getRandom();
            float chance = effect.getAmplifier() * 0.02f;
            while (chance > 0) {
                if (random.nextFloat() < chance) {
                    WorldEventHandler.addWorldEvent(target.level(),
                            new DelayedDamageWorldEvent(target)
                                    .setDamageData(1, 1, 2)
                                    .setPhysicalDamageType(MalumDamageTypes.ROT)
                                    .setMagicDamageType(MalumDamageTypes.ROT)
                                    .setImpactParticleEffect(MalumParticleEffectTypes.HIGH_PRIEST_PENANCE, new MalumNetworkedParticleEffectColorData(MalumSpiritTypes.EARTHEN_SPIRIT))
                                    .setSound(MalumSoundEvents.DESPERATE_NEED_WITHDRAWAL, 0.5f, 1.5f, 0.3f));
                }
                chance--;
            }
        }
    }
}