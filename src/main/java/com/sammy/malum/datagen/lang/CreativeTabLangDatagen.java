package com.sammy.malum.datagen.lang;

import com.sammy.malum.registry.common.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.registries.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.modules.toolkit.creative_tab.*;

public class CreativeTabLangDatagen {

    public static void addTranslations() {
        addCreativeTab(MalumCreativeTabs.CONTENT, "Malum: Study of Ancient Sorcery");
        addCreativeTab(MalumCreativeTabs.ALCHEMY_AND_METALLICS, "Malum: Alchemy & Metallics");
        add("malum.itemGroup.geas", "Malum: Sworn Oaths");
        add("malum.itemGroup.cosmetics", "Malum: One's True Self");
    }

    @SuppressWarnings("DataFlowIssue")
    public static void addCreativeTab(DeferredHolder<CreativeModeTab, CreativeModeTab> tab, String name) {
        var categories = ((CategorizedCreativeTab) tab.get()).getCategories().values();
        add("malum.itemGroup." + tab.getKey().location().getPath(), name);
        categories.forEach(a -> {
            String categoryName = DataHelper.toTitleCase(a.id(), "_");
            categoryName = categoryName.replaceAll("And", "&");
            add(a.getHeaderLangKey(), categoryName);
        });
    }


    protected static void add(String key, String value) {
        MalumLangDatagen.lang.add(key, value);
    }
}