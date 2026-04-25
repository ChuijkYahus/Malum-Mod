package com.sammy.malum.registry.client;

import com.sammy.malum.MalumMod;
import com.sammy.malum.common.data.component.ItemSkinComponent;
import com.sammy.malum.common.block.curiosities.decor.banner.SoulwovenBannerBlockItem;
import com.sammy.malum.common.item.curiosities.pouch.RavenousPouchItem;
import com.sammy.malum.common.item.curiosities.pouch.SoulwovenPouchItem;
import com.sammy.malum.common.item.curiosities.tools.CatalystLobberItem;
import com.sammy.malum.common.item.curiosities.tools.spellweaver.SpellweavingPickaxeItem;
import com.sammy.malum.common.item.ether.EtherItem;
import com.sammy.malum.common.item.spirit.SpiritShardItem;
import com.sammy.malum.registry.common.MalumContent;
import net.minecraft.client.renderer.item.ItemProperties;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import team.lodestar.lodestone.helpers.ColorHelper;
import team.lodestar.lodestone.modules.toolkit.item.LodestoneArmorItem;

public class MalumItemColors {
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void addItemProperties(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MalumContent.ITEMS.getEntries().stream().filter(r -> r.get() instanceof LodestoneArmorItem).forEach(armor ->
                    ItemProperties.register(
                            armor.get(),
                            MalumMod.malumPath("item_skin"),
                            (stack, level, holder, holderID) -> ItemSkinComponent.getAppliedSkinId(stack)));

            ItemProperties.register(
                    MalumContent.Gear.SOULWOVEN_POUCH.get(),
                    MalumMod.malumPath("filled"),
                    (stack, level, holder, holderID) -> SoulwovenPouchItem.getFullnessDisplay(stack));
            ItemProperties.register(
                    MalumContent.Gear.RAVENOUS_POUCH.get(),
                    MalumMod.malumPath("filled"),
                    (stack, level, holder, holderID) -> RavenousPouchItem.getFullnessDisplay(stack));
            ItemProperties.register(
                    MalumContent.BlockSets.SOULWOVEN_BANNER.getItem(),
                    MalumMod.malumPath("pattern"),
                    (stack, level, holder, holderID) -> SoulwovenBannerBlockItem.getBannerPattern(stack));
            ItemProperties.register(
                    MalumContent.Gear.CATALYST_LOBBER.get(),
                    MalumMod.malumPath("state"),
                    (stack, level, holder, holderID) -> CatalystLobberItem.getStateDisplay(stack));
            ItemProperties.register(
                    MalumContent.Gear.SPELLWEAVING_PICKAXE.get(),
                    MalumMod.malumPath("primed"),
                    (stack, level, holder, holderID) -> SpellweavingPickaxeItem.getStateDisplay(stack));
            ItemProperties.register(
                    MalumContent.Gear.SPELLWEAVING_AXE.get(),
                    MalumMod.malumPath("primed"),
                    (stack, level, holder, holderID) -> SpellweavingPickaxeItem.getStateDisplay(stack));
        });
    }

    @SubscribeEvent
    public static void setItemColors(RegisterColorHandlersEvent.Item event) {

        event.register((stack, tintIndex) -> ColorHelper.getColor(((SpiritShardItem) stack.getItem()).getSpiritHolder().getItemColor()),
                MalumContent.Spirits.SACRED_SPIRIT.get(), MalumContent.Spirits.WICKED_SPIRIT.get(), MalumContent.Spirits.ARCANE_SPIRIT.get(), MalumContent.Spirits.ELDRITCH_SPIRIT.get(),
                MalumContent.Spirits.AQUEOUS_SPIRIT.get(), MalumContent.Spirits.AERIAL_SPIRIT.get(), MalumContent.Spirits.EARTHEN_SPIRIT.get(), MalumContent.Spirits.INFERNAL_SPIRIT.get());

        event.register((s, c) -> switch (c) {
                    case 2 -> EtherItem.getSecondaryColor(s);
                    case 1 -> EtherItem.getPrimaryColor(s);
                    default -> -1;
                },
                MalumContent.BlockSets.ETHER_TORCH, MalumContent.BlockSets.IRIDESCENT_ETHER_TORCH,
                MalumContent.BlockSets.ETHER_CANDLE, MalumContent.BlockSets.IRIDESCENT_ETHER_CANDLE,
                MalumContent.BlockSets.ETHER_BRAZIER, MalumContent.BlockSets.IRIDESCENT_ETHER_BRAZIER,
                MalumContent.BlockSets.ETHER_CRESSET, MalumContent.BlockSets.IRIDESCENT_ETHER_CRESSET);

        event.register((s, c) -> c == 0 ? EtherItem.getPrimaryColor(s) : EtherItem.getSecondaryColor(s),
                MalumContent.BlockSets.ETHER, MalumContent.BlockSets.IRIDESCENT_ETHER);
    }
}
