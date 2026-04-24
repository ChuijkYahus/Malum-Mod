package com.sammy.malum.core.helpers;

import com.sammy.malum.core.handlers.KeywordTooltipHandler;
import com.sammy.malum.core.systems.rite.*;
import net.minecraft.*;
import net.minecraft.network.chat.*;

public class EffectComponentHelper {

    public static final String POSITIVE = "malum.effect.positive"; //+
    public static final String NEGATIVE = "malum.effect.negative"; //-
    public static final String GENERIC = "malum.effect.generic"; //┇

    public static final String EFFECT = "malum.effect.";
    public static final String GEAR = EFFECT + "gear.";
    public static final String GEAS = EFFECT + "geas.";
    public static final String CURIO = EFFECT + "curio.";
    public static final String POUCH = EFFECT + "pouch.";

    private static Component effectLine(String wrapperKey, ChatFormatting color, String key, Object... args) {
        return Component.translatable(
                wrapperKey, Component.translatable(key, args).withStyle(color)
        ).withStyle(ChatFormatting.DARK_GRAY);
    }

    public static Component positiveEffectGreen(String key, Object... args) {
        return effectLine(POSITIVE, ChatFormatting.DARK_GREEN, key, args);
    }

    public static Component positiveEffect(String key, Object... args) {
        return effectLine(POSITIVE, ChatFormatting.BLUE, key, args);
    }

    public static Component negativeEffect(String key, Object... args) {
        return effectLine(NEGATIVE, ChatFormatting.RED, key, args);
    }

    public static Component positiveGenericEffect(String key, Object... args) {
        return effectLine(GENERIC, ChatFormatting.BLUE, key, args);
    }

    public static Component negativeGenericEffect(String key, Object... args) {
        return effectLine(GENERIC, ChatFormatting.RED, key, args);
    }

    public static Component positiveItemEffect(String key, Object... args) {
        return positiveEffectGreen(GEAR + key, args);
    }

    public static Component negativeItemEffect(String key, Object... args) {
        return negativeEffect(GEAR + key, args);
    }

    public static Component positiveGeasEffect(String key, Object... args) {
        return positiveEffect(GEAS + key, args);
    }

    public static Component negativeGeasEffect(String key, Object... args) {
        return negativeEffect(GEAS + key, args);
    }

    public static Component positiveCurioEffect(String key, Object... args) {
        return positiveEffect(CURIO + key, args);
    }

    public static Component negativeCurioEffect(String key, Object... args) {
        return negativeEffect(CURIO + key, args);
    }

    public static Component pouchEffect(String key, Object... args) {
        return positiveGenericEffect(POUCH + key, args);
    }

    //TODO: this needs to be standardized
    public static Component riteEffect(String text, SpiritRiteType rite) {
        var inner = Component.literal(text).withStyle(rite.getIdentifyingSpirit().getStyle(0.6f));
        return Component.translatable(GENERIC, inner).withStyle(ChatFormatting.DARK_GRAY);
    }

    public static Component effectKeyword(KeywordTooltipHandler.TooltipKeyword keyword, Object... args) {
        return Component.translatable(keyword.getLangKey(), args).withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC);
    }
}