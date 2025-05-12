package com.sammy.malum.common.geas.pact.aqueous;

import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.event.entity.living.*;
import net.neoforged.neoforge.event.tick.*;

public class TidalAffinityGeas extends GeasEffect {

    private boolean isInWater;
    private boolean hasConduitEffect;

    public TidalAffinityGeas() {
        super(MalumGeasEffectTypeRegistry.PACT_OF_TIDAL_AFFINITY.get());
    }

    @Override
    public void incomingDamageEvent(LivingDamageEvent.Pre event, LivingEntity attacker, LivingEntity target, ItemStack stack) {
        float damageScalar = 1f - (isInWater ? 0.25f : 0f) - (hasConduitEffect ? 0.25f : 0f);
        event.setNewDamage(event.getNewDamage() * damageScalar);
    }

    @Override
    public void update(EntityTickEvent.Pre event, LivingEntity entity) {
        if (entity.level().getGameTime() % 10L == 0) {
            isInWater = entity.isInWater();
            hasConduitEffect = entity.hasEffect(MobEffects.CONDUIT_POWER);
            if (hasConduitEffect) {
                entity.heal(1);
            }
        }
    }
}