package com.sammy.malum.registry.common;

import com.sammy.malum.MalumMod;

import com.sammy.malum.common.creativetab.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.magic.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import team.lodestar.lodestone.modules.toolkit.creative_tab.CategorizedCreativeTab;

public class MalumCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MalumMod.MALUM);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ANCIENT_SORCERY = CREATIVE_MODE_TABS.register("malum_ancient_sorcery",
            () -> CategorizedCreativeTab.builder(MalumAncientSorceryTab::new)
                    .withVisualInfo(MalumVisualInfo.VISUAL_INFO)
                    .title(Component.translatable(MalumMod.MALUM + ".itemGroup.malum_ancient_sorcery"))
                    .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
                    .icon(MalumContent.Sorcery.SPIRIT_ALTAR::getDefaultInstance).build()
    );
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> LESSER_SOULSTUFF = CREATIVE_MODE_TABS.register("malum_lesser_soulstuff",
            () -> CategorizedCreativeTab.builder(MalumLesserSoulstuffTab::new)
                    .withVisualInfo(MalumVisualInfo.VISUAL_INFO)
                    .title(Component.translatable(MalumMod.MALUM + ".itemGroup.malum_lesser_soulstuff"))
                    .withTabsBefore(ANCIENT_SORCERY)
                    .icon(MalumContent.BuildingBlocks.ETHER::getDefaultInstance).build()
    );

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ALCHEMY_AND_METALLICS = CREATIVE_MODE_TABS.register("malum_alchemy_and_metallics",
            () -> CategorizedCreativeTab.builder(MalumAlchemyAndMetallicsTab::new)
                    .withVisualInfo(MalumVisualInfo.VISUAL_INFO)
                    .title(Component.translatable(MalumMod.MALUM + ".itemGroup.malum_alchemy_and_metallics"))
                    .withTabsBefore(LESSER_SOULSTUFF.getId())
                    .icon(MalumContent.AlchemyAndMetallics.ALCHEMICAL_IMPETUS::toStack).build()
    );

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> GEAS = CREATIVE_MODE_TABS.register("malum_geas",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable(MalumMod.MALUM + ".itemGroup.geas"))
                    .withTabsBefore(ALCHEMY_AND_METALLICS.getId())
                    .displayItems((p, o) -> {
                        for (DeferredHolder<GeasEffectType, ? extends GeasEffectType> geasType : MalumGeasEffectTypes.GEAS_TYPES.getEntries()) {
                            o.accept(geasType.get().getDummyCreativeStack());
                        }
                    })
                    .icon(() -> MalumGeasEffectTypes.PACT_OF_THE_ARCANAPHAGE.get().createDefaultStack()).build()
    );
    

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> COSMETIC = CREATIVE_MODE_TABS.register("malum_cosmetic",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable(MalumMod.MALUM + ".itemGroup.cosmetics"))
                    .withTabsBefore(GEAS.getId())
                    .icon(() -> MalumContent.Sorcery.WEAVERS_WORKBENCH.getItem().getDefaultInstance()).build()
    );
}
