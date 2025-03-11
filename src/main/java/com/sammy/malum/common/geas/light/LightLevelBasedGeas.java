package com.sammy.malum.common.geas.light;

import com.google.common.collect.*;
import com.sammy.malum.core.systems.geas.*;
import net.minecraft.*;
import net.minecraft.core.*;
import net.minecraft.network.chat.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.neoforged.neoforge.event.tick.*;

import java.util.function.*;

public abstract class LightLevelBasedGeas extends GeasEffect {

    public static final String LIGHT = "malum.gui.geas.light_effect";
    public static final String DARKNESS = "malum.gui.geas.darkness_effect";

    public boolean isInLight;

    public LightLevelBasedGeas(GeasEffectType type) {
        super(type);
    }

    @Override
    public void addTooltipComponents(LivingEntity entity, Consumer<Component> tooltipAcceptor, TooltipFlag tooltipFlag) {
        tooltipAcceptor.accept(Component.translatable(LIGHT).withStyle(ChatFormatting.GOLD));
        addTooltipComponents(entity, tooltipAcceptor, tooltipFlag, true);
        tooltipAcceptor.accept(Component.empty());
        tooltipAcceptor.accept(Component.translatable(DARKNESS).withStyle(ChatFormatting.GOLD));
        addTooltipComponents(entity, tooltipAcceptor, tooltipFlag, false);
    }

    public void addTooltipComponents(LivingEntity entity, Consumer<Component> tooltipAcceptor, TooltipFlag tooltipFlag, boolean isInLight) {
        createAttributeModifiers(entity, isInLight).entries().forEach((entry) -> addTooltipComponent(entry, tooltipAcceptor, tooltipFlag));
    }

    @Override
    public void update(EntityTickEvent.Pre event, LivingEntity entity) {
        var level = entity.level();
        boolean wasInLight = isInLight;
        isInLight = false;
        var blockPos = entity.blockPosition();
        boolean day = level.isDay();
        if (level.canSeeSky(blockPos) && day) {
            isInLight = true;
        }
        if (level.getBrightness(LightLayer.BLOCK, blockPos) > 3) {
            isInLight = true;
        }
        if (level.getBrightness(LightLayer.SKY, blockPos) > 5 && day) {
            isInLight = true;
        }
        if (wasInLight != isInLight) {
            setDirty();
        }
    }

    public final Multimap<Holder<Attribute>, AttributeModifier> createAttributeModifiers(LivingEntity entity, Multimap<Holder<Attribute>, AttributeModifier> modifiers) {
        return createAttributeModifiers(entity, modifiers, isInLight);
    }

    public Multimap<Holder<Attribute>, AttributeModifier> createAttributeModifiers(LivingEntity entity, boolean isInLight) {
        return createAttributeModifiers(entity, LinkedHashMultimap.create(), isInLight);
    }

    public Multimap<Holder<Attribute>, AttributeModifier> createAttributeModifiers(LivingEntity entity, Multimap<Holder<Attribute>, AttributeModifier> modifiers, boolean isInLight) {
        return LinkedHashMultimap.create();
    }
}