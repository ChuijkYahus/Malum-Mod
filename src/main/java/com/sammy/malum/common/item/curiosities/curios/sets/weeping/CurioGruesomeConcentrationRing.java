package com.sammy.malum.common.item.curiosities.curios.sets.weeping;

import com.sammy.malum.common.effect.gluttony.*;
import com.sammy.malum.common.item.*;
import com.sammy.malum.common.item.curiosities.curios.*;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.item.*;
import net.minecraft.network.chat.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import team.lodestar.lodestone.helpers.*;

import java.util.function.*;

import static com.sammy.malum.registry.common.MalumTags.ItemTags.*;

public class CurioGruesomeConcentrationRing extends MalumCurioItem implements IVoidItem {
    public CurioGruesomeConcentrationRing(Properties builder) {
        super(builder, MalumTrinketType.VOID);
    }

    @Override
    public void addExtraTooltipLines(Consumer<Component> consumer) {
        consumer.accept(ComponentHelper.positiveCurioEffect("rotten_gluttony"));
        consumer.accept(ComponentHelper.effectKeyword("gluttony"));
    }

    public static void onEat(Level level, LivingEntity livingEntity, ItemStack food) {
        if (level.isClientSide) {
            return;
        }
        if (food.is(GROSS_FOODS)) {
            if (CurioHelper.hasCurioEquipped(livingEntity, MalumItems.RING_OF_GRUESOME_CONCENTRATION.get())) {
                GluttonyEffect.applyGluttony(livingEntity, b -> b
                        .setInitialDuration(600)
                        .setInitialAmplifier(1)
                        .setAmplifierGain(2)
                        .setAmplifierLimit(10));
                livingEntity.playSound(MalumSoundEvents.GRUESOME_RING_FEEDS.get(), 0.5f, RandomHelper.randomBetween(level.random, 0.8f, 1.2f));
            }
        }
    }
}