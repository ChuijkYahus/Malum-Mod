package com.sammy.malum.datagen.lang.effect;

import com.sammy.malum.core.handlers.*;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.datagen.lang.*;

public class EffectKeywordLangDatagen {

    public static void addTranslations() {
        add(EffectComponentHelper.POSITIVE, "+%s");
        add(EffectComponentHelper.NEGATIVE, "-%s");
        add(EffectComponentHelper.GENERIC, "┇ %s");

        addEffectKeyword(KeywordTooltipHandler.AVARICE, "Avarice; Gradually Increases Fortune");
        addEffectKeyword(KeywordTooltipHandler.SOUL_WARD, "Soul Ward; Absorbs Damage, Recharges Over Time");
        addEffectKeyword(KeywordTooltipHandler.ARCANE_RESONANCE, "Arcane Resonance; Enhances Spirit-Collection Effects");

        addEffectKeyword(KeywordTooltipHandler.GLUTTONY, "Gluttony; Enhances Magic Damage, Kills Sprout Damaging Locusts");
        addEffectKeyword(KeywordTooltipHandler.TRIAL_OF_FAITH, "Trial of Faith; Enhances Healing, Recovering Health Grows Damaging Locusts");
        addEffectKeyword(KeywordTooltipHandler.DESPERATE_NEED, "Desperate Need; Enhances Scythe Damage, Scythe Damage Forms Damaging Locusts");

    }

    public static void addEffectKeyword(KeywordTooltipHandler.TooltipKeyword keyword, String name) {
        add(keyword.getLangKey(), name);
    }

    protected static void add(String key, String value) {
        MalumLangDatagen.lang.add(key, value);
    }
}