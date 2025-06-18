package com.sammy.malum.core.systems.rite;

import com.sammy.malum.*;
import com.sammy.malum.common.block.curiosities.totem.*;
import com.sammy.malum.core.systems.spirit.type.*;
import net.minecraft.*;
import net.minecraft.network.chat.*;
import net.minecraft.resources.*;

import java.util.*;

public abstract class TotemicRiteType {

    public static final String RITE = "malum.gui.rite";
    public static final String CORRUPTED_RITE = "malum.gui.rite.corrupt";
    public static final String TYPE = "malum.gui.rite.type";
    public static final String MEDIUM = "malum.gui.rite.medium";
    public static final String RUNEWOOD = "malum.gui.rite.medium.runewood";
    public static final String SOULWOOD = "malum.gui.rite.medium.soulwood";
    public static final String COVERAGE = "malum.gui.rite.coverage";
    public static final String EFFECT = "malum.gui.rite.effect";
    public static final String CORRUPTED_EFFECT = "malum.gui.rite.effect.corrupt";

    public final List<MalumSpiritType> spirits;
    public final String identifier;
    public final TotemicRiteEffect effect;
    public final TotemicRiteEffect corruptedEffect;

    public TotemicRiteType(String identifier, SpiritWrapper... spirits) {
        this.identifier = identifier;
        this.spirits = Arrays.stream(spirits).map(SpiritWrapper::unwrapSpirit).toList();
        this.effect = getNaturalRiteEffect();
        this.corruptedEffect = getCorruptedEffect();
    }

    public List<Component> getDescription(boolean corrupted) {
        List<Component> tooltip = new ArrayList<>();
        var spiritStyleModifier = getIdentifyingSpirit().getItemRarity().getStyleModifier();
        var riteEffect = getRiteEffect(corrupted);
        var riteCategory = riteEffect.category;
        tooltip.add(Component.translatable(getLangKey(corrupted)).withStyle(spiritStyleModifier));
        tooltip.add(createDescriptionComponent(TYPE, riteCategory.getTranslationKey()));
        tooltip.add(createDescriptionComponent(MEDIUM, corrupted ? SOULWOOD : RUNEWOOD));
        if (!riteCategory.equals(TotemicRiteEffect.MalumRiteEffectCategory.ONE_TIME_EFFECT)) {
            tooltip.add(createDescriptionComponent(COVERAGE, riteEffect.getRiteCoverageDescriptor()));
        }
        tooltip.add(createDescriptionComponent(EFFECT, (corrupted ? CORRUPTED_EFFECT : EFFECT) + "." + identifier));
        return tooltip;
    }

    public final Component createDescriptionComponent(String title, String type) {
        return Component.translatable(title).withStyle(ChatFormatting.GOLD)
                .append(Component.translatable(type).withStyle(ChatFormatting.YELLOW));
    }

    public String getLangKey(boolean corrupt) {
        return (corrupt ? CORRUPTED_RITE : RITE) + "." + identifier;
    }

    public ResourceLocation getIcon() {
        return MalumMod.malumPath("textures/vfx/rite/" + identifier.replace("greater_", "").replace("_rite", "") + ".png");
    }

    public MalumSpiritType getIdentifyingSpirit() {
        return spirits.getLast();
    }

    protected abstract TotemicRiteEffect getNaturalRiteEffect();

    protected abstract TotemicRiteEffect getCorruptedEffect();

    public final TotemicRiteEffect getRiteEffect(boolean corrupted) {
        return corrupted ? corruptedEffect : effect;
    }

    public void executeRite(TotemBaseBlockEntity totemBase) {
        getRiteEffect(totemBase.isSoulwood).doRiteEffect(totemBase);
    }
}