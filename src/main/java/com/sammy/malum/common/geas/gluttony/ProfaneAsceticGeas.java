package com.sammy.malum.common.geas.gluttony;

import com.sammy.malum.core.handlers.*;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.core.systems.events.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.network.chat.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.neoforged.neoforge.event.entity.living.*;
import team.lodestar.lodestone.helpers.*;

import java.util.function.*;

import static com.sammy.malum.registry.common.item.ItemTagRegistry.GROSS_FOODS;

public class ProfaneAsceticGeas extends GeasEffect {

    public ProfaneAsceticGeas() {
        super(MalumGeasEffectTypeRegistry.PACT_OF_THE_PROFANE_ASCETIC.get());
    }

    @Override
    public void modifyGluttonyPropertiesEvent(ModifyGluttonyPropertiesEvent event, LivingEntity collector) {
        event.replaceEffect(MobEffectRegistry.TRIAL_OF_FAITH);
    }

    @Override
    public void addTooltipComponents(LivingEntity entity, Consumer<Component> tooltipAcceptor, TooltipFlag tooltipFlag) {
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("trial_of_faith"));
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("trial_of_faith_healing"));
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("rotten_healing"));
        tooltipAcceptor.accept(ComponentHelper.negativeGeasEffect("no_passive_healing"));
        tooltipAcceptor.accept(ComponentHelper.negativeGeasEffect("no_normal_foods"));
        super.addTooltipComponents(entity, tooltipAcceptor, tooltipFlag);
    }

    public static boolean isProfaneAscetic(LivingEntity entity) {
        return GeasEffectHandler.hasGeasEffect(entity, MalumGeasEffectTypeRegistry.PACT_OF_THE_PROFANE_ASCETIC);
    }

    public static void slowDownEating(LivingEntityUseItemEvent.Start event) {
        if (!event.getItem().is(GROSS_FOODS)) {
            if (isProfaneAscetic(event.getEntity())) {
                event.setDuration((int) (event.getDuration() * 2f));
            }
        }
    }

    public static void onEat(Level level, LivingEntity livingEntity, ItemStack food) {
        if (isProfaneAscetic(livingEntity)) {
            if (food.is(GROSS_FOODS)) {
                livingEntity.heal(livingEntity.getMaxHealth() / 5);
            } else {
                livingEntity.hurt(DamageTypeHelper.create(level, DamageTypeRegistry.KARMIC), livingEntity.getMaxHealth() / 2);
            }
        }
    }
}