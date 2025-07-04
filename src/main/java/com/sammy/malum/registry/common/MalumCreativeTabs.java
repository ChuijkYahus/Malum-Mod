package com.sammy.malum.registry.common;

import com.sammy.malum.MalumMod;

import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.item.*;
import com.sammy.malum.registry.common.magic.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MalumCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MalumMod.MALUM);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CONTENT = CREATIVE_MODE_TABS.register("malum_content",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup." + MalumMod.MALUM + "_basis_of_magic"))
                    .withTabsAfter(MalumMod.malumPath("malum_nature"))
                    .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
                    .icon(() -> MalumItems.SPIRIT_ALTAR.get().getDefaultInstance()).build()
    );

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> NATURE = CREATIVE_MODE_TABS.register("malum_nature",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup." + MalumMod.MALUM + "_scars_of_arcana"))
                    .withTabsBefore(CONTENT.getId())
                    .withTabsAfter(MalumMod.malumPath("malum_building"))
                    .icon(() -> MalumItems.RUNEWOOD_SAPLING.get().getDefaultInstance()).build()
    );

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BUILDING = CREATIVE_MODE_TABS.register("malum_building",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup." + MalumMod.MALUM + "_arcane_construct"))
                    .withTabsBefore(NATURE.getId())
                    .withTabsAfter(MalumMod.malumPath("malum_metallurgy"))
                    .icon(() -> MalumItems.TAINTED_ROCK.get().getDefaultInstance()).build()
    );

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> METALLURGY = CREATIVE_MODE_TABS.register("malum_metallurgy",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup." + MalumMod.MALUM + "_metallurgic_magics"))
                    .withTabsBefore(BUILDING.getId())
                    .withTabsAfter(MalumMod.malumPath("ritual_shards"))
                    .icon(() -> MalumItems.ALCHEMICAL_IMPETUS.get().getDefaultInstance()).build()
    );

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> GEAS = CREATIVE_MODE_TABS.register("malum_geas",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup." + MalumMod.MALUM + "_geas"))
                    .withTabsBefore(METALLURGY.getId())
                    .withTabsAfter(MalumMod.malumPath("malum_cosmetics"))
                    .displayItems((p, o) -> {
                        int i = 0;
                        for (DeferredHolder<GeasEffectType, ? extends GeasEffectType> etchingType : MalumGeasEffectTypes.GEAS_TYPES.getEntries()) {
                            final GeasEffectType geasEffectType = etchingType.get();
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
                    .title(Component.translatable("itemGroup." + MalumMod.MALUM + "_cosmetics"))
                    .withTabsBefore(GEAS.getId())
                    .icon(() -> MalumItems.WEAVERS_WORKBENCH.get().getDefaultInstance()).build()
    );
}
