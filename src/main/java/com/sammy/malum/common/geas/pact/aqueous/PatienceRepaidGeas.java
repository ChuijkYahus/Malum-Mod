package com.sammy.malum.common.geas.pact.aqueous;

import com.sammy.malum.core.helpers.*;
import com.sammy.malum.core.systems.geas.GeasEffect;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.visual_effects.networked.*;
import net.minecraft.network.chat.*;
import net.minecraft.network.protocol.game.*;
import net.minecraft.server.level.*;
import net.minecraft.util.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.event.entity.living.*;
import net.neoforged.neoforge.event.tick.*;
import team.lodestar.lodestone.helpers.*;

import java.util.function.*;

public class PatienceRepaidGeas extends GeasEffect {

    private float bufferedDamage;
    private int damageTimer;

    public PatienceRepaidGeas() {
        super(MalumGeasEffectTypes.PACT_OF_PATIENCE_REPAID.get());
    }

    @Override
    public void addTooltipComponents(LivingEntity entity, Consumer<Component> tooltipAcceptor, TooltipFlag tooltipFlag) {
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("buffered_damage"));
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("buffered_damage_non_lethal"));
        tooltipAcceptor.accept(ComponentHelper.negativeGeasEffect("buffered_damage_more_overall"));
    }

    @Override
    public void incomingDamageEvent(LivingIncomingDamageEvent event, LivingEntity attacker, LivingEntity target, ItemStack stack) {
        if (event.isCanceled() || event.getSource().is(MalumDamageTypes.KARMIC)) {
            return;
        }
        float half = event.getAmount() * 0.5f;
        event.setAmount(half);
        bufferedDamage += half * 1.4f;
    }

    @Override
    public void incomingDeathEvent(LivingDeathEvent event, LivingEntity attacker, LivingEntity target, ItemStack stack) {
        if (event.getSource().is(MalumDamageTypes.KARMIC)) {
            event.setCanceled(true);
            target.setHealth(1);
        }
    }

    @Override
    public void update(EntityTickEvent.Pre event, LivingEntity entity) {
        if (entity.level() instanceof ServerLevel level) {
            if (bufferedDamage > 0) {
                damageTimer++;
                float delta = Math.min(bufferedDamage / (entity.getMaxHealth() * 0.5f), 1);
                int time = 100 - Mth.ceil(delta * 80);
                if (damageTimer > time) {
                    float percentage = 1f - delta * 0.8f;
                    float damage = bufferedDamage * percentage;
                    var velocity = entity.getDeltaMovement();
                    entity.hurt(DamageTypeHelper.create(entity.level(), MalumDamageTypes.KARMIC), damage);
                    entity.setDeltaMovement(velocity);
                    if (entity instanceof ServerPlayer serverplayer) {
                        serverplayer.connection.send(new ClientboundSetEntityMotionPacket(serverplayer));
                    }

                    MalumParticleEffectTypes.PATIENCE_REPAID.createEffect(entity)
                            .color(new MalumNetworkedParticleEffectColorData(MalumSpiritTypes.AQUEOUS_SPIRIT, MalumSpiritTypes.ELDRITCH_SPIRIT))
                            .spawn(level);
                    SoundHelper.playSound(entity, MalumSoundEvents.PATIENT_DROWNING.get(), entity.getSoundSource(), 1.0f, RandomHelper.randomBetween(level.random, 0.9f, 1.1f));

                    bufferedDamage -= damage;
                    damageTimer = 0;
                }
            }
        }
    }
}
