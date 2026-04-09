package com.sammy.malum.registry.common.content.item;

import com.sammy.malum.*;
import com.sammy.malum.client.renderer.curio.*;
import com.sammy.malum.common.block.flora.wood.IGradientedLeavesBlock;
import com.sammy.malum.common.data.component.*;
import com.sammy.malum.common.item.banner.*;
import com.sammy.malum.common.item.cosmetic.curios.*;
import com.sammy.malum.common.item.curiosities.*;
import com.sammy.malum.common.item.curiosities.pouch.*;
import com.sammy.malum.common.item.curiosities.tools.*;
import com.sammy.malum.common.item.curiosities.tools.spellweaver.*;
import com.sammy.malum.common.item.ether.*;
import com.sammy.malum.registry.common.content.MalumContent;
import com.sammy.malum.common.item.spirit.*;
import com.sammy.malum.core.enumextension.*;
import com.sammy.malum.registry.client.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.client.renderer.item.*;
import net.minecraft.world.item.*;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.registries.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.modules.toolkit.item.*;

import top.theillusivec4.curios.api.client.*;

import java.util.function.*;

import static com.sammy.malum.MalumMod.*;
import static com.sammy.malum.registry.common.content.block.MalumBlocks.*;

@SuppressWarnings("unused")
public class MalumItemProperties {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MALUM);

    public static LodestoneItemProperties DEFAULT_PROPERTIES() {
        return new LodestoneItemProperties(MalumCreativeTabs.CONTENT);
    }

    public static LodestoneItemProperties GEAR_PROPERTIES() {
        return DEFAULT_PROPERTIES().stacksTo(1);
    }

    public static LodestoneItemProperties IMPETUS_PROPERTIES() {
        return DEFAULT_PROPERTIES().stacksTo(1);
    }

    public static LodestoneItemProperties COSMETIC_PROPERTIES() {
        return new LodestoneItemProperties(MalumCreativeTabs.COSMETIC);
    }

    public static LodestoneItemProperties HIDDEN_PROPERTIES() {
        return new LodestoneItemProperties().stacksTo(1);
    }


    public static <T extends Item> DeferredItem<T> register(String name, Supplier<LodestoneItemProperties> propertySupplier, Function<LodestoneItemProperties, T> function) {
        return ITEMS.register(name, () -> {
            var properties = propertySupplier.get();
            LodestoneItemProperties.addToTabSorting(MalumMod.malumPath(name), properties);
            return function.apply(properties);
        });
    }

    //endregion

    public static final DeferredItem<Item> RUNEWOOD_BOAT = register("runewood_boat", () -> DEFAULT_PROPERTIES().stacksTo(1), (p) -> new BoatItem(false, MalumEnumParams.RUNEWOOD_BOAT_TYPE.getValue(), p));
    public static final DeferredItem<Item> RUNEWOOD_CHEST_BOAT = register("runewood_chest_boat", () -> DEFAULT_PROPERTIES().stacksTo(1), (p) -> new BoatItem(true, MalumEnumParams.RUNEWOOD_BOAT_TYPE.getValue(), p));

    public static final DeferredItem<Item> SOULWOOD_BOAT = register("soulwood_boat", () -> DEFAULT_PROPERTIES().stacksTo(1), (p) -> new BoatItem(false, MalumEnumParams.SOULWOOD_BOAT_TYPE.getValue(), p));
    public static final DeferredItem<Item> SOULWOOD_CHEST_BOAT = register("soulwood_chest_boat", () -> DEFAULT_PROPERTIES().stacksTo(1), (p) -> new BoatItem(true, MalumEnumParams.SOULWOOD_BOAT_TYPE.getValue(), p));
    //endregion

    //endregion

    //region cosmetics
    public static final DeferredItem<Item> ESOTERIC_SPOOL = register("esoteric_spool", MalumItemProperties::COSMETIC_PROPERTIES, Item::new);
    public static final DeferredItem<Item> ANCIENT_WEAVE = register("ancient_weave", MalumItemProperties::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.ANCIENT_CLOTH));
    public static final DeferredItem<Item> CORNERED_WEAVE = register("cornered_weave", MalumItemProperties::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.COMMANDO));
    public static final DeferredItem<Item> MECHANICAL_WEAVE_V1 = register("mechanical_weave_v1", MalumItemProperties::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.BLUE_MACHINE));
    public static final DeferredItem<Item> MECHANICAL_WEAVE_V2 = register("mechanical_weave_v2", MalumItemProperties::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.RED_MACHINE));

    public static final DeferredItem<Item> ACE_PRIDEWEAVE = register("ace_prideweave", MalumItemProperties::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.ACE));
    public static final DeferredItem<Item> AGENDER_PRIDEWEAVE = register("agender_prideweave", MalumItemProperties::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.AGENDER));
    public static final DeferredItem<Item> ARO_PRIDEWEAVE = register("aro_prideweave", MalumItemProperties::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.ARO));
    public static final DeferredItem<Item> AROACE_PRIDEWEAVE = register("aroace_prideweave", MalumItemProperties::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.AROACE));
    public static final DeferredItem<Item> BI_PRIDEWEAVE = register("bi_prideweave", MalumItemProperties::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.BI));
    public static final DeferredItem<Item> DEMIBOY_PRIDEWEAVE = register("demiboy_prideweave", MalumItemProperties::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.DEMIBOY));
    public static final DeferredItem<Item> DEMIGIRL_PRIDEWEAVE = register("demigirl_prideweave", MalumItemProperties::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.DEMIGIRL));
    public static final DeferredItem<Item> ENBY_PRIDEWEAVE = register("enby_prideweave", MalumItemProperties::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.ENBY));
    public static final DeferredItem<Item> GAY_PRIDEWEAVE = register("gay_prideweave", MalumItemProperties::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.GAY));
    public static final DeferredItem<Item> GENDERFLUID_PRIDEWEAVE = register("genderfluid_prideweave", MalumItemProperties::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.GENDERFLUID));
    public static final DeferredItem<Item> GENDERQUEER_PRIDEWEAVE = register("genderqueer_prideweave", MalumItemProperties::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.GENDERQUEER));
    public static final DeferredItem<Item> INTERSEX_PRIDEWEAVE = register("intersex_prideweave", MalumItemProperties::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.INTERSEX));
    public static final DeferredItem<Item> LESBIAN_PRIDEWEAVE = register("lesbian_prideweave", MalumItemProperties::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.LESBIAN));
    public static final DeferredItem<Item> PAN_PRIDEWEAVE = register("pan_prideweave", MalumItemProperties::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.PAN));
    public static final DeferredItem<Item> PLURAL_PRIDEWEAVE = register("plural_prideweave", MalumItemProperties::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.PLURAL));
    public static final DeferredItem<Item> POLY_PRIDEWEAVE = register("poly_prideweave", MalumItemProperties::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.POLY));
    public static final DeferredItem<Item> PRIDE_PRIDEWEAVE = register("pride_prideweave", MalumItemProperties::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.PRIDE));
    public static final DeferredItem<Item> TRANS_PRIDEWEAVE = register("trans_prideweave", MalumItemProperties::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.TRANS));

    public static final DeferredItem<Item> TOPHAT = register("tophat", () -> COSMETIC_PROPERTIES().stacksTo(1), CurioTopHat::new);
    //endregion

    //region hidden items
    public static final DeferredItem<Item> SOUL_OF_A_SCYTHE = register("soul_of_a_scythe", MalumItemProperties::HIDDEN_PROPERTIES, TemporarilyDisabledItem::new);
    public static final DeferredItem<Item> SOUL_OF_THE_ANCHOR = register("soul_of_the_anchor", MalumItemProperties::HIDDEN_PROPERTIES, TemporarilyDisabledItem::new);
    public static final DeferredItem<Item> TOKEN_OF_GRATITUDE = register("token_of_gratitude", MalumItemProperties::HIDDEN_PROPERTIES, CurioTokenOfGratitude::new);
    //endregion


    public static Item skinHoldingItem(Item.Properties properties, ItemSkinComponent skin) {
        return new Item(properties.component(MalumDataComponents.ITEM_SKIN, skin));
    }

    @EventBusSubscriber(modid = MalumMod.MALUM, value = Dist.CLIENT)
    public static class ClientOnly {

        @SubscribeEvent
        public static void registerExtras(FMLClientSetupEvent event) {
            CuriosRendererRegistry.register(MalumItemProperties.TOKEN_OF_GRATITUDE.get(), TokenOfGratitudeRenderer::new);
            CuriosRendererRegistry.register(MalumItemProperties.TOPHAT.get(), TopHatCurioRenderer::new);

            MalumHiddenTags.registerHiddenTags();
        }

        @SubscribeEvent(priority = EventPriority.LOWEST)
        public static void addItemProperties(FMLClientSetupEvent event) {
            event.enqueueWork(() -> {
                ITEMS.getEntries().stream().filter(r -> r.get() instanceof LodestoneArmorItem).forEach(armor ->
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

            event.register((stack, tintIndex) -> ColorHelper.getColor(((IGradientedLeavesBlock) ((BlockItem) stack.getItem()).getBlock()).getMaxColor()),
                    RUNEWOOD_LEAVES, HANGING_RUNEWOOD_LEAVES, AZURE_RUNEWOOD_LEAVES, HANGING_AZURE_RUNEWOOD_LEAVES);
            event.register((stack, tintIndex) -> ColorHelper.getColor(((IGradientedLeavesBlock) ((BlockItem) stack.getItem()).getBlock()).getMinColor()),
                    SOULWOOD_LEAVES, HANGING_SOULWOOD_LEAVES);

            event.register((s, c) -> switch (c) {
                        case 2 -> EtherItem.getSecondaryColor(s);
                        case 1 -> EtherItem.getPrimaryColor(s);
                        default -> -1;
                    },
                    MalumContent.BlockSets.ETHER_TORCH, MalumContent.BlockSets.IRIDESCENT_ETHER_TORCH,
                    MalumContent.BlockSets.ETHER_CANDLE, MalumContent.BlockSets.IRIDESCENT_ETHER_CANDLE,
                    MalumContent.BlockSets.TAINTED_ETHER_BRAZIER, MalumContent.BlockSets.TWISTED_ETHER_BRAZIER, MalumContent.BlockSets.DROSS_ETHER_BRAZIER,
                    MalumContent.BlockSets.TAINTED_IRIDESCENT_ETHER_BRAZIER, MalumContent.BlockSets.TWISTED_IRIDESCENT_ETHER_BRAZIER, MalumContent.BlockSets.DROSS_IRIDESCENT_ETHER_BRAZIER,
                    MalumContent.BlockSets.TAINTED_ETHER_CRESSET, MalumContent.BlockSets.TWISTED_ETHER_CRESSET, MalumContent.BlockSets.DROSS_ETHER_CRESSET,
                    MalumContent.BlockSets.TAINTED_IRIDESCENT_ETHER_CRESSET, MalumContent.BlockSets.TWISTED_IRIDESCENT_ETHER_CRESSET, MalumContent.BlockSets.DROSS_IRIDESCENT_ETHER_CRESSET);

            event.register((s, c) -> c == 0 ? EtherItem.getPrimaryColor(s) : EtherItem.getSecondaryColor(s),
                    MalumContent.BlockSets.ETHER, MalumContent.BlockSets.IRIDESCENT_ETHER);
        }
    }
}
