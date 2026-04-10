package com.sammy.malum.common.item.curiosities.curios.runes.miracle;

import com.sammy.malum.core.helpers.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.magic.*;
import net.minecraft.network.chat.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.event.entity.living.*;
import team.lodestar.lodestone.handlers.*;

import java.util.function.*;

public class RuneCullingItem extends MiracleRuneCurioItem implements ItemEventHandler.IEventResponder {

    public RuneCullingItem(Properties builder) {
        super(builder, MalumSpiritTypes.WICKED_SPIRIT);
    }

    @Override
    public void addExtraTooltipLines(Consumer<Component> consumer) {
        consumer.accept(ComponentHelper.positiveCurioEffect("scythe_execution"));
    }

    @Override
    public void outgoingDamageEvent(LivingDamageEvent.Pre event, LivingEntity attacker, LivingEntity target, ItemStack stack) {
        if (event.getSource().is(MalumTags.DamageTypes.IS_SCYTHE)) {
            if (target.getHealth() < target.getMaxHealth() * 0.5f) {
                event.setNewDamage(event.getNewDamage() * 1.4f);
            }
        }
    }
}