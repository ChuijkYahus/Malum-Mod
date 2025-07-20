package com.sammy.malum.common.item.curiosities.curios.runes.madness;

import com.sammy.malum.core.helpers.*;
import com.sammy.malum.registry.common.magic.MalumSpiritTypes;
import com.sammy.malum.registry.common.item.MalumItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import team.lodestar.lodestone.helpers.CurioHelper;
import team.lodestar.lodestone.helpers.EntityHelper;

import java.util.function.Consumer;

public class RuneTwinnedDurationItem extends MadnessRuneCurioItem {

    public RuneTwinnedDurationItem(Properties builder) {
        super(builder, MalumSpiritTypes.AQUEOUS_SPIRIT);
    }

    @Override
    public void addExtraTooltipLines(Consumer<Component> consumer) {
        consumer.accept(ComponentHelper.positiveCurioEffect("extend_positive_effect"));
    }

    public static void scaleDuration(MobEffectEvent.Added event) {
        LivingEntity entity = event.getEntity();
        if (event.getOldEffectInstance() == null && CurioHelper.hasCurioEquipped(entity, MalumItems.RUNE_OF_TWINNED_DURATION.get())) {
            MobEffectInstance effect = event.getEffectInstance();
            var type = effect.getEffect().value();
            if (type.isBeneficial()) {
                EntityHelper.extendEffect(effect, entity, effect.getDuration());
            }
        }
    }
}
