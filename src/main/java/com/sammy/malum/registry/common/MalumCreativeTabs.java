package com.sammy.malum.registry.common;

import com.sammy.malum.MalumMod;

import com.sammy.malum.common.category.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.item.*;
import com.sammy.malum.registry.common.magic.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import team.lodestar.lodestone.modules.toolkit.creative_tab.CategorizedCreativeTab;

public class MalumCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MalumMod.MALUM);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CONTENT = CREATIVE_MODE_TABS.register("malum_content",
            () -> CategorizedCreativeTab.builder(MalumCreativeTab::new)
                    .title(Component.translatable(MalumMod.MALUM + ".itemGroup.spirit_arcana"))
                    .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
                    .withTabsAfter(MalumMod.malumPath("malum_nature"))
                    .icon(() -> MalumItems.SPIRIT_ALTAR.get().getDefaultInstance()).build()
    );

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> GEAS = CREATIVE_MODE_TABS.register("malum_geas",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable(MalumMod.MALUM + ".itemGroup.geas"))
                    .withTabsBefore(CONTENT.getId())
                    .withTabsAfter(MalumMod.malumPath("malum_cosmetics"))
                    .displayItems((p, o) -> {
                        for (DeferredHolder<GeasEffectType, ? extends GeasEffectType> geasType : MalumGeasEffectTypes.GEAS_TYPES.getEntries()) {
                            final GeasEffectType geasEffectType = geasType.get();
                            if (geasEffectType.equals(MalumGeasEffectTypes.CREED_OF_THE_BLIGHT_EATER.get())) {
                                continue;
                            }

                            o.accept(geasEffectType.getDummyCreativeStack());
                        }
                    })
                    .icon(() -> MalumGeasEffectTypes.PACT_OF_THE_ARCANAPHAGE.get().createDefaultStack()).build()
    );
    

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> COSMETIC = CREATIVE_MODE_TABS.register("malum_cosmetic",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable(MalumMod.MALUM + ".itemGroup.cosmetics"))
                    .withTabsBefore(GEAS.getId())
                    .icon(() -> MalumItems.WEAVERS_WORKBENCH.get().getDefaultInstance()).build()
    );
}
