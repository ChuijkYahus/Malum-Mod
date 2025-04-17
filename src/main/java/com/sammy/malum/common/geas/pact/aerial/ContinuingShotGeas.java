package com.sammy.malum.common.geas.pact.aerial;

import com.sammy.malum.common.entity.bolt.*;
import com.sammy.malum.core.handlers.*;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.network.chat.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.projectile.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.event.entity.*;
import net.neoforged.neoforge.event.entity.living.*;
import team.lodestar.lodestone.helpers.*;

import java.util.function.*;

public class ContinuingShotGeas extends GeasEffect {

    public static final int MAX_DRAW_SPEED_STACKS = 5;

    public ContinuingShotGeas() {
        super(MalumGeasEffectTypeRegistry.PACT_OF_THE_CONTINUING_SHOT.get());
    }

    @Override
    public void addTooltipComponents(LivingEntity entity, Consumer<Component> tooltipAcceptor, TooltipFlag tooltipFlag) {
        super.addTooltipComponents(entity, tooltipAcceptor, tooltipFlag);
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("faster_draw_time"));
        tooltipAcceptor.accept(ComponentHelper.negativeGeasEffect("missing_halts_draw_time"));
    }

    //TODO: make it work nicer with multishot
    public static void entityJoin(EntityJoinLevelEvent event) {
        var entity = event.getEntity();
        if (entity instanceof AbstractArrow || entity instanceof AbstractBoltProjectileEntity) {
            if (entity.level().isClientSide) {
                return;
            }
            Projectile projectile = (Projectile) entity;
            if (projectile.getOwner() instanceof LivingEntity owner) {
                GeasEffect geasEffect = GeasEffectHandler.getGeasEffect(owner, MalumGeasEffectTypeRegistry.PACT_OF_THE_CONTINUING_SHOT);
                if (geasEffect instanceof ContinuingShotGeas) {
                    projectile.getData(AttachmentTypeRegistry.PROJECTILE_SOUL_INFO).setAethersBlessingCallback(()-> resetStreak(owner));
                }
            }
        }
    }

    public static void handleItemUseStartEvent(LivingEntityUseItemEvent.Start event) {
        LivingEntity entity = event.getEntity();
        GeasEffect geasEffect = GeasEffectHandler.getGeasEffect(entity, MalumGeasEffectTypeRegistry.PACT_OF_THE_CONTINUING_SHOT);
        if (geasEffect instanceof ContinuingShotGeas) {
            int duration = event.getDuration();
            int drawSpeedStacks = 0;
            final MobEffectInstance blessing = entity.getEffect(MobEffectRegistry.TRUE_SHOT);
            final MobEffectInstance curse = entity.getEffect(MobEffectRegistry.FLAWED_AIM);
            if (blessing != null) {
                drawSpeedStacks += blessing.getAmplifier() + 1;
            }
            if (curse != null) {
                drawSpeedStacks -= curse.getAmplifier() + 1;
            }
            if (drawSpeedStacks < 0) {
                duration -= drawSpeedStacks * 5;
            } else {
                duration -= (int) (drawSpeedStacks * 1.5f);
            }
            event.setDuration(duration);
        }
    }

    public static void resetStreak(LivingEntity entity) {
        final MobEffectInstance curse = entity.getEffect(MobEffectRegistry.TRUE_SHOT);
        if (curse != null) {
            EntityHelper.shortenEffect(curse, entity, 400);
        }
        var effect = MobEffectRegistry.FLAWED_AIM;
        var instance = entity.getEffect(effect);
        if (instance == null) {
            entity.addEffect(new MobEffectInstance(effect, 200, 0, true, true, true));
        } else {
            EntityHelper.amplifyEffect(instance, entity, 1, MAX_DRAW_SPEED_STACKS-1);

            EntityHelper.extendEffect(instance, entity, 40, 400);
        }
    }

    @Override
    public void finalizedOutgoingDamageEvent(LivingDamageEvent.Post event, LivingEntity attacker, LivingEntity target, ItemStack stack) {
        final Entity projectile = event.getSource().getDirectEntity();
        if (projectile instanceof AbstractArrow || projectile instanceof AbstractBoltProjectileEntity) {
            final MobEffectInstance curse = attacker.getEffect(MobEffectRegistry.FLAWED_AIM);
            if (curse != null) {
                EntityHelper.shortenEffect(curse, attacker, 200);
                return;
            }
            var effect = MobEffectRegistry.TRUE_SHOT;
            var instance = attacker.getEffect(effect);
            if (instance == null) {
                attacker.addEffect(new MobEffectInstance(effect, 100, 0, true, true, true));
            } else {
                EntityHelper.amplifyEffect(instance, attacker, 1, MAX_DRAW_SPEED_STACKS-1);

                EntityHelper.extendEffect(instance, attacker, 40, 400);
            }
        }
    }
}