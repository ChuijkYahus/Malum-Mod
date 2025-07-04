package com.sammy.malum.common.item.curiosities.curios.runes.miracle;

import com.sammy.malum.core.helpers.*;
import com.sammy.malum.registry.common.magic.MalumSpiritTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import team.lodestar.lodestone.handlers.ItemEventHandler;

import java.util.function.Consumer;

public class RuneScorchingItem extends MiracleRuneCurioItem implements ItemEventHandler.IEventResponder {

    public RuneScorchingItem(Properties builder) {
        super(builder, MalumSpiritTypes.INFERNAL_SPIRIT);
    }

    @Override
    public void addExtraTooltipLines(Consumer<Component> consumer) {
        consumer.accept(ComponentHelper.positiveCurioEffect("burning_damage"));
    }

    @Override
    public void outgoingDamageEvent(LivingDamageEvent.Pre event, LivingEntity attacker, LivingEntity target, ItemStack stack) {
        if (event.getSource().is(DamageTypeTags.IS_FIRE)) {
            event.setNewDamage(event.getNewDamage() * 2);
        }
    }
}
