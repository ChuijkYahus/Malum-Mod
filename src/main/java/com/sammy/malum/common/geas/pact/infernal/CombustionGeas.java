package com.sammy.malum.common.geas.pact.infernal;

import com.sammy.malum.common.worldevent.*;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.tag.*;
import com.sammy.malum.visual_effects.networked.*;
import net.minecraft.network.chat.*;
import net.minecraft.tags.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.event.entity.living.*;
import team.lodestar.lodestone.handlers.*;
import team.lodestar.lodestone.registry.common.tag.*;

import java.util.function.*;

public class CombustionGeas extends GeasEffect {

    public CombustionGeas() {
        super(MalumGeasEffectTypeRegistry.PACT_OF_COMBUSTION.get());
    }

    @Override
    public void addTooltipComponents(LivingEntity entity, Consumer<Component> tooltipAcceptor, TooltipFlag tooltipFlag) {
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("hotter_fire"));
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("magic_fire"));
        tooltipAcceptor.accept(ComponentHelper.negativeGeasEffect("extinguish_hurt"));
    }

    @Override
    public void outgoingDamageEvent(LivingDamageEvent.Pre event, LivingEntity attacker, LivingEntity target, ItemStack stack) {
        if (event.getSource().is(DamageTypeTags.IS_FIRE)) {
            target.setRemainingFireTicks(Math.max(target.getRemainingFireTicks()-8, 0));
            target.invulnerableTime = 0;
        }
        else if (event.getSource().is(LodestoneDamageTypeTags.IS_MAGIC)) {
            target.igniteForSeconds(5);
        }
    }

    public static void extinguish(LivingEntity entity) {
        WorldEventHandler.addWorldEvent(entity.level(),
                new DelayedDamageWorldEvent(entity)
                        .setDamageData(2, 2, 2)
                        .setMagicDamageType(DamageTypeRegistry.KARMIC)
                        .setImpactParticleEffect(ParticleEffectTypeRegistry.SHAKEN_FAITH, new MalumNetworkedParticleEffectColorData(SpiritTypeRegistry.INFERNAL_SPIRIT))
                        .setSound(SoundRegistry.SCYTHE_SWEEP, 0.5f, 1.5f, 0.3f));
    }
}
