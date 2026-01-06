package com.sammy.malum.common.item.curiosities.curios.sets.prospector;

import com.sammy.malum.common.item.curiosities.curios.*;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.registry.common.item.*;
import net.minecraft.*;
import net.minecraft.network.chat.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.enchantment.*;
import team.lodestar.lodestone.helpers.*;

import java.util.function.*;

public class CurioHeartyAvariceRing extends MalumCurioItem {

    public CurioHeartyAvariceRing(Properties builder) {
        super(builder, MalumTrinketType.METALLIC);
    }

    @Override
    public void addExtraTooltipLines(Consumer<Component> consumer, TooltipContext context) {
        consumer.accept(ComponentHelper.positiveCurioEffect("avarice_healing"));
    }

    public static boolean hasHeartyRing(LivingEntity entity) {
        return CurioHelper.hasCurioEquipped(entity, MalumItems.RING_OF_HEARTY_AVARICE.get());
    }
}