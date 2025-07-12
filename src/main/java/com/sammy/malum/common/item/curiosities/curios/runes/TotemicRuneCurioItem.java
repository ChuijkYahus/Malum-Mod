package com.sammy.malum.common.item.curiosities.curios.runes;

import com.sammy.malum.core.helpers.*;
import com.sammy.malum.core.systems.registry.RiteHolder;
import com.sammy.malum.core.systems.registry.SpiritHolder;
import com.sammy.malum.core.systems.rite.*;
import com.sammy.malum.core.systems.rite.effect.SpiritRiteAuraEffect;
import com.sammy.malum.core.systems.spirit.type.SpiritArcanaType;
import net.minecraft.network.chat.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.*;
import top.theillusivec4.curios.api.*;

import java.util.function.*;

public class TotemicRuneCurioItem extends AbstractRuneCurioItem {

    protected final RiteHolder<SpiritRiteType> riteType;

    public TotemicRuneCurioItem(Properties builder, RiteHolder<SpiritRiteType> riteType, SpiritHolder<SpiritArcanaType> spirit) {
        super(builder, spirit, MalumTrinketType.TOTEMIC_RUNE);
        this.riteType = riteType;
    }

    @Override
    public void addExtraTooltipLines(Consumer<Component> consumer) {
        SpiritRiteType spiritRite = riteType.get();
        if (spiritRite.getEffect() instanceof SpiritRiteAuraEffect<?> potionEffect) {
            Component effectName = potionEffect.getEffect().value().getDisplayName();
            consumer.accept(ComponentHelper.positiveCurioEffect("totem_effect", effectName));
        }
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        var target = slotContext.entity();
        if (target.level() instanceof ServerLevel level) {
            if (level.getGameTime() % 5L == 0) {
                SpiritRiteType spiritRite = riteType.get();
                if (spiritRite.getEffect() instanceof SpiritRiteAuraEffect<?> potionEffect) {
                    potionEffect.applyRuneEffect(level, target);
                }
            }
        }
        super.curioTick(slotContext, stack);
    }
}