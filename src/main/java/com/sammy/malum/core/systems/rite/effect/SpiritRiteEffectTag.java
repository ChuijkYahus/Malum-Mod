package com.sammy.malum.core.systems.rite.effect;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class SpiritRiteEffectTag {

    public static final String TAG = "malum.gui.rite.tag";

    public static final SpiritRiteEffectTag RUNEWOOD = malumTag("runewood");
    public static final SpiritRiteEffectTag SOULWOOD = malumTag("soulwood");
    public static final SpiritRiteEffectTag AURA = malumTag("aura");
    public static final SpiritRiteEffectTag TARGET_FRIENDLY = malumTag("target_friendly");
    public static final SpiritRiteEffectTag TARGET_ANIMAL = malumTag("target_animal");
    public static final SpiritRiteEffectTag TARGET_ANY = malumTag("target_any");

    public static final SpiritRiteEffectTag RADIAL_EFFECT = malumTag("radial_effect");
    public static final SpiritRiteEffectTag LOCUS_EFFECT = malumTag("locus_effect");
    public static final SpiritRiteEffectTag STRANGE_EFFECT = malumTag("strange_effect");

    private final String langKey;

    public static SpiritRiteEffectTag malumTag(String key) {
        return new SpiritRiteEffectTag(TAG + "." + key);
    }

    public SpiritRiteEffectTag(String langKey) {
        this.langKey = langKey;
    }

    public String getLangKey() {
        return langKey;
    }
}
