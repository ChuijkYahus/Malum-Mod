package com.sammy.malum.datagen.lang;

import com.sammy.malum.common.data.component.soulstone.*;

public class SoulstoneMetallicsLangDatagen {

    public static void addTranslations() {
        add(StoredInSoulstoneMetal.METAL_COMPOSITION, "┇ Composition: <%s>");
        add(StoredInSoulstoneMetal.METAL_PURITY, "┇ Purity: <%s>");
        addMetalComposition("empty", "##");

        addMetalComposition("copper", "Cu");
        addMetalComposition("iron", "Fe");
        addMetalComposition("gold", "Au");
        addMetalComposition("zinc", "Zn");
        addMetalComposition("lead", "Pb");
        addMetalComposition("silver", "Ag");
        addMetalComposition("aluminium", "Al");
        addMetalComposition("nickel", "Ni");
    }

    private static void addMetalComposition(String key, String value) {
        add(StoredInSoulstoneMetal.METAL_ENTRY + key, value);
    }

    protected static void add(String key, String value) {
        MalumLangDatagen.lang.add(key, value);
    }
}