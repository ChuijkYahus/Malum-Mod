package com.sammy.malum.common.item.curiosities.curios.runes;

import com.sammy.malum.core.helpers.*;
import com.sammy.malum.core.systems.registry.rite.*;
import com.sammy.malum.core.systems.registry.SpiritHolder;
import com.sammy.malum.core.systems.rite.effect.*;
import com.sammy.malum.core.systems.spirit.type.SpiritArcanaType;
import net.minecraft.network.chat.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.*;
import top.theillusivec4.curios.api.*;

import java.util.function.*;

public class TotemicRuneCurioItem extends AbstractRuneCurioItem {

    protected final RiteEffectHolder<? extends SpiritRitePotionEffect<?>> effect;

    public TotemicRuneCurioItem(Properties builder, RiteEffectHolder<? extends SpiritRitePotionEffect<?>> effect, SpiritHolder<SpiritArcanaType> spirit) {
        super(builder, spirit, MalumTrinketFamily.TOTEMIC_RUNE);
        this.effect = effect;
    }

    @Override
    public void addExtraTooltipLines(Consumer<Component> consumer) {
        Component effectName = effect.value().getEffect().value().getDisplayName();
        consumer.accept(ComponentHelper.positiveCurioEffect("totem_effect", effectName));
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        var target = slotContext.entity();
        if (target.level() instanceof ServerLevel level) {
            if (level.getGameTime() % 5L == 0) {
                effect.value().applyRuneEffect(level, target);
            }
        }
        super.curioTick(slotContext, stack);
    }
}