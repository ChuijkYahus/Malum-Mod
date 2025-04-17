package com.sammy.malum.common.item.curiosities.curios.sets.rotten;

import com.sammy.malum.common.effect.gluttony.*;
import com.sammy.malum.common.item.curiosities.curios.*;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.item.*;
import net.minecraft.network.chat.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraft.world.level.Level;
import team.lodestar.lodestone.helpers.*;

import java.util.function.*;

import static com.sammy.malum.registry.common.tag.ItemTagRegistry.*;

public class CurioVoraciousRing extends MalumCurioItem {

    public CurioVoraciousRing(Properties builder) {
        super(builder, MalumTrinketType.ROTTEN);
    }

    @Override
    public void addExtraTooltipLines(Consumer<Component> consumer) {
        consumer.accept(ComponentHelper.positiveCurioEffect("eat_rotten"));
        consumer.accept(ComponentHelper.positiveCurioEffect("growing_gluttony"));
    }

    public static void modifyEating(LivingEntityUseItemEvent.Start event) {
        if (event.getItem().is(GROSS_FOODS)) {
            if (CurioHelper.hasCurioEquipped(event.getEntity(), ItemRegistry.RING_OF_DESPERATE_VORACITY.get())) {
                event.setDuration((int) (event.getDuration() * 0.5f));
            }
        }
    }

    public static void onEat(Level level, LivingEntity livingEntity, ItemStack food) {
        if (level.isClientSide) {
            return;
        }
        if (food.is(GROSS_FOODS)) {
            if (CurioHelper.hasCurioEquipped(livingEntity, ItemRegistry.RING_OF_DESPERATE_VORACITY.get())) {
                GluttonyEffect.applyGluttony(livingEntity, b -> b
                        .setStackingData(300, 0)
                        .setLimitData(3000, 0));
                if (livingEntity instanceof Player player) {
                    player.getFoodData().eat(1, 1f);
                }
                livingEntity.playSound(SoundRegistry.VORACIOUS_RING_FEEDS.get(), 0.5f, RandomHelper.randomBetween(level.random, 1.2f, 1.6f));
            }
        }
    }
}