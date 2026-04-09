package com.sammy.malum.common.item.curiosities.curios.sets.prospector;

import com.sammy.malum.common.item.curiosities.curios.MalumCurioItem;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.registry.common.content.MalumContent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import team.lodestar.lodestone.helpers.CurioHelper;

import java.util.function.Consumer;

public class CurioDischargeRing extends MalumCurioItem {

    public CurioDischargeRing(Properties builder) {
        super(builder, MalumTrinketType.METALLIC);
    }

    @Override
    public void addExtraTooltipLines(Consumer<Component> consumer) {
        consumer.accept(ComponentHelper.positiveCurioEffect("bigger_explosions"));
    }

    public static float increaseExplosionRadius(LivingEntity source, float original) {
        if (source != null && CurioHelper.hasCurioEquipped(source, MalumContent.Gear.RING_OF_HEAVY_DISCHARGE.get())) {
            return original + 1;
        }
        return original;
    }
}
