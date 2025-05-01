package com.sammy.malum.common.geas.pact.wicked;

import com.sammy.malum.common.worldevent.*;
import com.sammy.malum.core.handlers.*;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.visual_effects.networked.MalumNetworkedParticleEffectColorData;
import net.minecraft.network.chat.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.event.entity.living.*;
import team.lodestar.lodestone.handlers.*;

import java.util.function.*;

public class WarlockGeas extends GeasEffect {

    public WarlockGeas() {
        super(MalumGeasEffectTypeRegistry.PACT_OF_THE_WARLOCK.get());
    }

    public static void increaseDetection(LivingEvent.LivingVisibilityEvent event) {
        if (GeasEffectHandler.hasGeasEffect(event.getEntity(), MalumGeasEffectTypeRegistry.PACT_OF_THE_WARLOCK)) {
            event.modifyVisibility(2f);
        }
    }

    @Override
    public void outgoingDamageEvent(LivingDamageEvent.Pre event, LivingEntity attacker, LivingEntity target, ItemStack stack) {
        if (target.level().isClientSide) {
            return;
        }
        if (event.getSource().is(DamageTypeRegistry.WARLOCK_GEAS)) {
            return;
        }
        if (target.getHealth() > target.getMaxHealth() * 0.95f) {
            WorldEventHandler.addWorldEvent(target.level(),
                    new DelayedDamageWorldEvent(target)
                            .setAttacker(attacker)
                            .setDamageData(0, 4, 3)
                            .setMagicDamageType(DamageTypeRegistry.WARLOCK_GEAS)
                            .setImpactParticleEffect(ParticleEffectTypeRegistry.SPIRIT_IMPACT, new MalumNetworkedParticleEffectColorData(SpiritTypeRegistry.WICKED_SPIRIT))
                            .setSound(SoundRegistry.WARLOCK_BLAST, 1.25f, 1.75f, 0.3f));
        }
    }

    @Override
    public void addTooltipComponents(LivingEntity entity, Consumer<Component> tooltipAcceptor, TooltipFlag tooltipFlag) {
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("first_hit_bonus"));
        tooltipAcceptor.accept(ComponentHelper.negativeGeasEffect("aggressive_enemies"));
        super.addTooltipComponents(entity, tooltipAcceptor, tooltipFlag);
    }
}
