package com.sammy.malum.common.geas.pact.eldritch;

import com.google.common.collect.*;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.core.systems.events.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.core.*;
import net.minecraft.network.chat.*;
import net.minecraft.server.level.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.event.tick.*;

import java.util.function.*;

public class ShatteringAddictGeas extends GeasEffect {

    public ShatteringAddictGeas() {
        super(MalumGeasEffectTypeRegistry.PACT_OF_THE_SHATTERING_ADDICT.get());
    }

    @Override
    public void addTooltipComponents(LivingEntity entity, Consumer<Component> tooltipAcceptor, TooltipFlag tooltipFlag) {
        super.addTooltipComponents(entity, tooltipAcceptor, tooltipFlag);
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("chained_spirit_bonus"));
        tooltipAcceptor.accept(ComponentHelper.negativeGeasEffect("hunger_as_withdrawal"));
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> createAttributeModifiers(LivingEntity entity, Multimap<Holder<Attribute>, AttributeModifier> modifiers) {
        addAttributeModifier(modifiers, AttributeRegistry.SPIRIT_SPOILS, 1, AttributeModifier.Operation.ADD_VALUE);
        return modifiers;
    }

    @Override
    public void modifySpiritSpoilsEvent(ModifySpiritSpoilsEvent event, LivingEntity attacker) {
        if (attacker.level() instanceof ServerLevel serverLevel) {
            long timeSince = serverLevel.getGameTime() - attacker.getData(AttachmentTypeRegistry.LIVING_SOUL_INFO).getMostRecentShatter();
            if (timeSince < 2) {
                event.addSpirits(1);
            }
        }
    }

    @Override
    public void update(EntityTickEvent.Pre event, LivingEntity entity) {
        if (entity instanceof Player player) {
            if (player.level() instanceof ServerLevel serverLevel) {
                long timeSince = serverLevel.getGameTime() - player.getData(AttachmentTypeRegistry.LIVING_SOUL_INFO).getMostRecentShatter();
                if (timeSince > 32000) {
                    float drain = 0.005f;
                    long remainder = timeSince - 32000;
                    while (remainder > 6000) {
                        remainder -= 6000;
                        drain += 0.01f;
                    }
                    player.causeFoodExhaustion(drain);
                }
            }
        }
    }
}
