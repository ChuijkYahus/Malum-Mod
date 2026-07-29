package com.sammy.malum.core.helpers;

import com.sammy.malum.core.handlers.KeywordTooltipHandler;
import com.sammy.malum.core.systems.rite.*;
import net.minecraft.*;
import net.minecraft.network.chat.*;

public class TooltipComponentHelper {

    public enum Sentiment {
        POSITIVE("malum.effect.positive"), //+
        NEGATIVE("malum.effect.negative"), //-
        GENERIC("malum.effect.generic");   //┇

        public final String id;
        Sentiment(String id) {
            this.id = id;
        }
    }

    public static final String EFFECT = "malum.effect.";
    public static final String ITEM = EFFECT + "gear.";
    public static final String GEAS = EFFECT + "geas.";
    public static final String CURIO = EFFECT + "curio.";
    public static final String POUCH = EFFECT + "pouch.";

    private static Component effectLine(Sentiment sentiment, String key, Object... args) {
        var color = ChatFormatting.BLUE;
        if (sentiment.equals(Sentiment.NEGATIVE)) {
            color = ChatFormatting.RED;
        }
        return effectLine(sentiment, color, key, args);
    }

    private static Component effectLine(Sentiment sentiment, ChatFormatting color, String key, Object... args) {
        var text = Component.translatable(key, args).withStyle(color);
        return Component.translatable(sentiment.id, text).withStyle(ChatFormatting.DARK_GRAY);
    }

    public static Component positiveItemEffect(String key, Object... args) {
        return effectLine(Sentiment.POSITIVE, ChatFormatting.DARK_GREEN, ITEM + key, args);
    }

    public static Component negativeItemEffect(String key, Object... args) {
        return effectLine(Sentiment.NEGATIVE, ITEM + key, args);
    }

    public static Component positiveGeasEffect(String key, Object... args) {
        return effectLine(Sentiment.POSITIVE, GEAS + key, args);
    }

    public static Component negativeGeasEffect(String key, Object... args) {
        return effectLine(Sentiment.NEGATIVE, GEAS + key, args);
    }

    public static Component positiveCurioEffect(String key, Object... args) {
        return effectLine(Sentiment.POSITIVE, CURIO + key, args);
    }

    public static Component negativeCurioEffect(String key, Object... args) {
        return effectLine(Sentiment.NEGATIVE, CURIO + key, args);
    }

    public static Component pouchEffect(String key, Object... args) {
        return effectLine(Sentiment.GENERIC, POUCH + key, args);
    }

    //TODO: this needs to be like gutted outright
    public static Component riteEffect(String text, SpiritRiteType rite) {
        var identifyingSpirit = rite.getIdentifyingSpirit();
        var inner = Component.literal(text).withStyle(identifyingSpirit.getTextData().createStyle(0.6f));
        return Component.translatable(Sentiment.GENERIC.id, inner).withStyle(ChatFormatting.DARK_GRAY);
    }

    public static Component effectKeyword(KeywordTooltipHandler.TooltipKeyword keyword, Object... args) {
        return Component.translatable(keyword.getLangKey(), args).withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC);
    }
}