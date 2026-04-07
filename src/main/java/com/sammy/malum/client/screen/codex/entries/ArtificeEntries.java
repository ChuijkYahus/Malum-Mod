//package com.sammy.malum.client.screen.codex.entries;
//
//import com.sammy.malum.client.screen.codex.*;
//import com.sammy.malum.client.screen.codex.pages.*;
//import com.sammy.malum.client.screen.codex.pages.recipe.*;
//import com.sammy.malum.client.screen.codex.pages.recipe.vanilla.*;
//import com.sammy.malum.client.screen.codex.pages.text.*;
//import com.sammy.malum.client.screen.codex.screens.progression.*;
//import com.sammy.malum.common.data.component.*;
//import net.minecraft.client.Minecraft;
//import net.minecraft.world.item.*;
//import net.minecraft.world.item.enchantment.Enchantments;
//
//import static com.sammy.malum.client.screen.codex.WidgetDesignType.FillingType.PAPER;
//import static com.sammy.malum.client.screen.codex.WidgetDesignType.FrameType.RUNEWOOD;
//import static com.sammy.malum.client.screen.codex.WidgetDesignType.GILDED;
//import static com.sammy.malum.registry.common.item.MalumItems.*;
//import static net.minecraft.world.item.Items.*;
//
//public class ArtificeEntries {
//
//    public static void setupEntries(ArcanaProgressionScreen screen) {
//        Item EMPTY = ItemStack.EMPTY.getItem();
//
//        var spiritStonesReexamination = BookEntry.create("spirit_stones.reexamination")
//                .addPage(new HeadlineTextPage("spirit_stones.reexamination", "spirit_stones.reexamination.1"))
//                .addPage(new TextPage("spirit_stones.reexamination.2"))
//                .addPage(new TextPage("spirit_stones.reexamination.3"))
//                .afterUmbralCrystal();
//
//        screen.addEntry("spirit_stones", 3, 7, b -> b
//                .configureWidget(w -> w.setIcon(TAINTED_ROCK))
//                .addPage(new HeadlineTextPage("spirit_stones.tainted_rock", "spirit_stones.tainted_rock.1"))
//                .addPage(SpiritInfusionPage.fromOutput(TAINTED_ROCK.get()))
//                .addPage(CraftingPage.itemPedestalPage(TAINTED_ROCK_ITEM_PEDESTAL.get(), TAINTED_ROCK.get(), TAINTED_ROCK_SLAB.get()))
//                .addPage(CraftingPage.itemStandPage(TAINTED_ROCK_ITEM_STAND.get(), TAINTED_ROCK.get(), TAINTED_ROCK_SLAB.get()))
//                .addPage(new HeadlineTextPage("spirit_stones.twisted_rock", "spirit_stones.twisted_rock.1"))
//                .addPage(SpiritInfusionPage.fromOutput(TWISTED_ROCK.get()))
//                .addPage(CraftingPage.itemPedestalPage(TWISTED_ROCK_ITEM_PEDESTAL.get(), TWISTED_ROCK.get(), TWISTED_ROCK_SLAB.get()))
//                .addPage(CraftingPage.itemStandPage(TWISTED_ROCK_ITEM_STAND.get(), TWISTED_ROCK.get(), TWISTED_ROCK_SLAB.get()))
//                .addReference(new EntryReference(UMBRAL_SPIRIT, spiritStonesReexamination))
//        );
//
//        screen.addEntry("ether", 4, 8, b -> b
//                .configureWidget(w -> w.setIcon(ETHER))
//                .addPage(new HeadlineTextPage("ether", "ether.1"))
//                .addPage(SpiritInfusionPage.fromOutput(ETHER.get()))
//                .addPage(new TextPage("ether.2"))
//                .addPage(new CyclingPage(
//                        new CraftingPage(ETHER_TORCH.get(), EMPTY, EMPTY, EMPTY, EMPTY, ETHER.get(), EMPTY, EMPTY, STICK, EMPTY),
//                        new CraftingPage(TAINTED_ETHER_BRAZIER.get(), EMPTY, EMPTY, EMPTY, TAINTED_ROCK.get(), ETHER.get(), TAINTED_ROCK.get(), STICK, TAINTED_ROCK.get(), STICK),
//                        new CraftingPage(TWISTED_ETHER_BRAZIER.get(), EMPTY, EMPTY, EMPTY, TWISTED_ROCK.get(), ETHER.get(), TWISTED_ROCK.get(), STICK, TWISTED_ROCK.get(), STICK))
//                )
//                .addPage(new HeadlineTextPage("ether.iridescent", "ether.iridescent.1"))
//                .addPage(new TextPage("ether.iridescent.2"))
//                .addPage(SpiritInfusionPage.fromOutput(IRIDESCENT_ETHER.get()))
//                .addPage(new CyclingPage(
//                        new CraftingPage(IRIDESCENT_ETHER_TORCH.get(), EMPTY, EMPTY, EMPTY, EMPTY, IRIDESCENT_ETHER.get(), EMPTY, EMPTY, STICK, EMPTY),
//                        new CraftingPage(TAINTED_IRIDESCENT_ETHER_BRAZIER.get(), EMPTY, EMPTY, EMPTY, TAINTED_ROCK.get(), IRIDESCENT_ETHER.get(), TAINTED_ROCK.get(), STICK, TAINTED_ROCK.get(), STICK),
//                        new CraftingPage(TWISTED_IRIDESCENT_ETHER_BRAZIER.get(), EMPTY, EMPTY, EMPTY, TWISTED_ROCK.get(), IRIDESCENT_ETHER.get(), TWISTED_ROCK.get(), STICK, TWISTED_ROCK.get(), STICK))
//                )
//        );
//
//        screen.addEntry("basic_artifice", 5, 8, b -> b
//                .configureWidget(w -> w.setIcon(WAVEMAKER))
//                .addPage(new HeadlineTextPage("basic_artifice", "basic_artifice.1"))
//                .addPage(new TextPage("basic_artifice.2"))
//                .addPage(new HeadlineTextPage("basic_artifice.wavecharger", "basic_artifice.wavecharger.1"))
//                .addPage(SpiritInfusionPage.fromOutput(WAVECHARGER.get()))
//                .addPage(new HeadlineTextPage("basic_artifice.wavebanker", "basic_artifice.wavebanker.1"))
//                .addPage(SpiritInfusionPage.fromOutput(WAVEBANKER.get()))
//                .addPage(new HeadlineTextPage("basic_artifice.wavemaker", "basic_artifice.wavemaker.1"))
//                .addPage(SpiritInfusionPage.fromOutput(WAVEMAKER.get()))
//                .addPage(new HeadlineTextPage("basic_artifice.wavebreaker", "basic_artifice.wavebreaker.1"))
//                .addPage(SpiritInfusionPage.fromOutput(WAVEBREAKER.get()))
//                .addReference(new EntryReference(ARTIFICERS_CLAW, BookEntry.create("basic_artifice.artificers_claw")
//                        .addPage(new HeadlineTextPage("basic_artifice.artificers_claw", "basic_artifice.artificers_claw.1"))
//                        .addPage(SpiritInfusionPage.fromOutput(ARTIFICERS_CLAW.get()))
//                ))
//        );
//
//        screen.addEntry("windweaving", 6, 9, b -> b
//                .configureWidget(w -> w.setIcon(GUST_IGNITER))
//                .addPage(new HeadlineTextPage("windweaving"))
//                .addPage(new TextPage("windweaving.2"))
//                .addPage(new HeadlineTextPage("windweaving.gust_igniter"))
//                .addPage(SpiritInfusionPage.fromOutput(GUST_IGNITER.get()))
//                .addPage(new HeadlineTextPage("windweaving.wind_tunnel"))
//                .addPage(SpiritInfusionPage.fromOutput(WIND_TUNNEL.get()))
//        );
//
//        screen.addEntry("soulwoven_silk", 4, 5, b -> b
//                .configureWidget(w -> w.setIcon(SOULWOVEN_SILK))
//                .addPage(new HeadlineTextPage("soulwoven_silk", "soulwoven_silk.1"))
//                .addPage(SpiritInfusionPage.fromOutput(SOULWOVEN_SILK.get()))
//                .addPage(new TextPage("soulwoven_silk.soulwoven_banner.1"))
//                .addPage(new CraftingPage(new ItemStack(SOULWOVEN_BANNER.get(), 2), EMPTY, RUNEWOOD_PLANKS.get(), EMPTY, EMPTY, SOULWOVEN_SILK.get(), EMPTY, EMPTY, SOULWOVEN_SILK.get()))
//                .addPage(new CyclingPage(
//                        CraftingPage.bannerPage(ROTTING_ESSENCE.get(), SoulwovenBannerPatternDataComponent.HUNGER),
//                        CraftingPage.bannerPage(GRIM_TALC.get(), SoulwovenBannerPatternDataComponent.HORNS),
//                        CraftingPage.bannerPage(EERIE_WEAVE.get(), SoulwovenBannerPatternDataComponent.HEFT),
//                        CraftingPage.bannerPage(WARP_FLUX.get(), SoulwovenBannerPatternDataComponent.HALLUCINATION)
//                ))
//                .addPage(new CyclingPage(
//                        new SpiritInfusionPage(SoulwovenBannerPatternDataComponent.SACRED),
//                        new SpiritInfusionPage(SoulwovenBannerPatternDataComponent.WICKED),
//                        new SpiritInfusionPage(SoulwovenBannerPatternDataComponent.ARCANE),
//                        new SpiritInfusionPage(SoulwovenBannerPatternDataComponent.ELDRITCH),
//                        new SpiritInfusionPage(SoulwovenBannerPatternDataComponent.AERIAL),
//                        new SpiritInfusionPage(SoulwovenBannerPatternDataComponent.AQUEOUS),
//                        new SpiritInfusionPage(SoulwovenBannerPatternDataComponent.EARTHEN),
//                        new SpiritInfusionPage(SoulwovenBannerPatternDataComponent.INFERNAL),
//                        new SpiritInfusionPage(SoulwovenBannerPatternDataComponent.COLORFUL_WORLD)
//                ))
//        );
//
//        screen.addEntry("soulwoven_pouch", 5, 6, b -> b
//                .configureWidget(w -> w.setIcon(SOULWOVEN_POUCH))
//                .addPage(new HeadlineTextPage("soulwoven_pouch", "soulwoven_pouch.1"))
//                .addPage(new CraftingPage(SOULWOVEN_POUCH.get(), EMPTY, STRING, EMPTY, EMPTY, SOULWOVEN_SILK.get()))
//                .addReference(new EntryReference(RAVENOUS_POUCH, BookEntry.create("soulwoven_pouch.ravenous_pouch")
//                        .addPage(new HeadlineTextPage("soulwoven_pouch.ravenous_pouch", "soulwoven_pouch.ravenous_pouch.1"))
//                        .addPage(SpiritInfusionPage.fromOutput(RAVENOUS_POUCH.get()))
//                ))
//        );
//
//        screen.addEntry("soulhunter_armor", 6, 6, b -> b
//                .configureWidget(w -> w.setIcon(SOUL_HUNTER_CLOAK))
//                .addPage(new HeadlineTextPage("soulhunter_armor", "soulhunter_armor.1"))
//                .addPage(new CyclingPage(
//                        SpiritInfusionPage.fromOutput(SOUL_HUNTER_CLOAK.get()),
//                        SpiritInfusionPage.fromOutput(SOUL_HUNTER_ROBE.get()),
//                        SpiritInfusionPage.fromOutput(SOUL_HUNTER_LEGGINGS.get()),
//                        SpiritInfusionPage.fromOutput(SOUL_HUNTER_BOOTS.get())
//                ))
//        );
//
////        screen.addEntry("something2", 7, 7, b -> {});
//
//
//        screen.addEntry("spirit_focusing", 10, 7, b -> b
//                .configureWidget(w -> w.setIcon(SPIRIT_CRUCIBLE).setDesign(GILDED, RUNEWOOD, PAPER))
//                .addPage(new HeadlineTextItemPage("spirit_focusing", "spirit_focusing.1", SPIRIT_CRUCIBLE.get()))
//                .addPage(new TextPage("spirit_focusing.2"))
//                .addPage(SpiritInfusionPage.fromOutput(SPIRIT_CRUCIBLE.get()))
//                .addPage(SpiritInfusionPage.fromOutput(ALCHEMICAL_IMPETUS.get()))
//        );
//
//        screen.addEntry("focus_ashes", 9, 6, b -> b
//                .configureWidget(w -> w.setIcon(GUNPOWDER))
//                .addPage(new HeadlineTextPage("focus_ashes", "focus_ashes.1"))
//                .addPage(SpiritFocusingPage.fromOutput(GUNPOWDER))
//                .addPage(SpiritFocusingPage.fromOutput(GLOWSTONE_DUST))
//                .addPage(SpiritFocusingPage.fromOutput(REDSTONE))
//        );
//
//        screen.addEntry("focus_metals", 11, 8, b -> b
//                .configureWidget(w -> w.setIcon(IRON_NODE))
//                .addPage(new HeadlineTextItemPage("focus_metals", "focus_metals.1", IRON_NODE.get()))
//                .addPage(new TextPage("focus_metals.2"))
//                .addPage(new CyclingPage(
//                        SpiritInfusionPage.fromOutput(IRON_IMPETUS.get()),
//                        SpiritInfusionPage.fromOutput(GOLD_IMPETUS.get()),
//                        SpiritInfusionPage.fromOutput(COPPER_IMPETUS.get()),
//                        SpiritInfusionPage.fromOutput(LEAD_IMPETUS.get()),
//                        SpiritInfusionPage.fromOutput(SILVER_IMPETUS.get()),
//                        SpiritInfusionPage.fromOutput(ALUMINUM_IMPETUS.get()),
//                        SpiritInfusionPage.fromOutput(NICKEL_IMPETUS.get()),
//                        SpiritInfusionPage.fromOutput(URANIUM_IMPETUS.get()),
//                        SpiritInfusionPage.fromOutput(OSMIUM_IMPETUS.get()),
//                        SpiritInfusionPage.fromOutput(ZINC_IMPETUS.get()),
//                        SpiritInfusionPage.fromOutput(TIN_IMPETUS.get()),
//                        SpiritInfusionPage.fromOutput(COBALT_IMPETUS.get())
//                ))
//                .addPage(new CyclingPage(
//                        SpiritFocusingPage.fromOutput(IRON_NODE.get()),
//                        SpiritFocusingPage.fromOutput(GOLD_NODE.get()),
//                        SpiritFocusingPage.fromOutput(COPPER_NODE.get()),
//                        SpiritFocusingPage.fromOutput(LEAD_NODE.get()),
//                        SpiritFocusingPage.fromOutput(SILVER_NODE.get()),
//                        SpiritFocusingPage.fromOutput(ALUMINUM_NODE.get()),
//                        SpiritFocusingPage.fromOutput(NICKEL_NODE.get()),
//                        SpiritFocusingPage.fromOutput(URANIUM_NODE.get()),
//                        SpiritFocusingPage.fromOutput(OSMIUM_NODE.get()),
//                        SpiritFocusingPage.fromOutput(ZINC_NODE.get()),
//                        SpiritFocusingPage.fromOutput(TIN_NODE.get()),
//                        SpiritFocusingPage.fromOutput(COBALT_NODE.get())
//                ))
//        );
//
//        screen.addEntry("crucible_acceleration", 10, 5, b -> b
//                .configureWidget(w -> w.setIcon(SPIRIT_CATALYZER))
//                .addPage(new HeadlineTextPage("crucible_acceleration", "crucible_acceleration.1"))
//                .addPage(new TextPage("crucible_acceleration.2"))
//                .addPage(new TextPage("crucible_acceleration.3"))
//                .addPage(SpiritInfusionPage.fromOutput(SPIRIT_CATALYZER.get()))
//        );
//
//        screen.addEntry("arcane_restoration", 10, 9, b -> b
//                .configureWidget(w -> w.setIcon(REPAIR_PYLON))
//                .addPage(new HeadlineTextPage("arcane_restoration", "arcane_restoration.1"))
//                .addPage(SpiritInfusionPage.fromOutput(REPAIR_PYLON.get()))
//                .addPage(new TextPage("arcane_restoration.2"))
//                .addPage(new SpiritRepairPage("alchemical_impetus_restoration"))
//                .addPage(new CyclingPage(
//                        SpiritRepairPage.fromOutput(FRACTURED_IRON_IMPETUS.get()),
//                        SpiritRepairPage.fromOutput(FRACTURED_GOLD_IMPETUS.get()),
//                        SpiritRepairPage.fromOutput(FRACTURED_COPPER_IMPETUS.get()),
//                        SpiritRepairPage.fromOutput(FRACTURED_LEAD_IMPETUS.get()),
//                        SpiritRepairPage.fromOutput(FRACTURED_SILVER_IMPETUS.get()),
//                        SpiritRepairPage.fromOutput(FRACTURED_ALUMINUM_IMPETUS.get()),
//                        SpiritRepairPage.fromOutput(FRACTURED_NICKEL_IMPETUS.get()),
//                        SpiritRepairPage.fromOutput(FRACTURED_URANIUM_IMPETUS.get()),
//                        SpiritRepairPage.fromOutput(FRACTURED_OSMIUM_IMPETUS.get()),
//                        SpiritRepairPage.fromOutput(FRACTURED_ZINC_IMPETUS.get()),
//                        SpiritRepairPage.fromOutput(FRACTURED_TIN_IMPETUS.get()),
//                        SpiritRepairPage.fromOutput(FRACTURED_COBALT_IMPETUS.get())
//                ))
//                .addReference(new EntryReference(IRON_PICKAXE, BookEntry.create("arcane_restoration.tool_repair")
//                        .addPage(new HeadlineTextPage("arcane_restoration.tool_repair", "arcane_restoration.tool_repair.1"))
//                        .addPage(new SpiritRepairPage("wooden_restoration"))
//                        .addPage(new SpiritRepairPage("stone_restoration"))
//                        .addPage(new SpiritRepairPage("iron_restoration"))
//                        .addPage(new SpiritRepairPage("gold_restoration"))
//                        .addPage(new SpiritRepairPage("diamond_restoration"))
//                        .addPage(new SpiritRepairPage("netherite_restoration"))
//                        .addPage(new SpiritRepairPage("trident_restoration"))
//                        .addPage(new SpiritRepairPage("mace_restoration"))
//                        .addPage(new TextPage("arcane_restoration.tool_repair.2"))
//                        .addPage(new SpiritRepairPage("soul_stained_steel_restoration"))
//                        .addPage(new SpiritRepairPage("soul_stained_steel_armor_restoration"))
//                        .addPage(new SpiritRepairPage("soul_hunter_armor_restoration"))
//                ))
//        );
//
//        screen.addEntry("focus_crystals", 12, 6, b -> b
//                .configureWidget(w -> w.setIcon(QUARTZ))
//                .addPage(new HeadlineTextPage("focus_crystals", "focus_crystals.1"))
//                .addPage(SpiritFocusingPage.fromOutput(QUARTZ))
//                .addPage(SpiritFocusingPage.fromOutput(AMETHYST_SHARD))
//                .addPage(SpiritFocusingPage.fromOutput(BLAZING_QUARTZ.get()))
//                .addPage(SpiritFocusingPage.fromOutput(PRISMARINE))
//        );
//
//        screen.addEntry("focus_elemental", 14, 5, b -> b
//                .configureWidget(w -> w.setIcon(ZEPHYR_IMPETUS))
//                .addPage(new HeadlineTextPage("focus_elemental"))
//                .addPage(new TextPage("focus_elemental.1"))
//                .addReference(new EntryReference(ZEPHYR_IMPETUS, BookEntry.create("focus_elemental.zephyr_impetus")
//                        .addPage(new HeadlineTextPage("focus_elemental.zephyr_impetus"))
//                        .addPage(new TextPage("focus_elemental.zephyr_impetus.2"))
//                        .addPage(new TextPage("focus_elemental.zephyr_impetus.3"))
//                        .addPage(SpiritInfusionPage.fromOutput(ZEPHYR_IMPETUS.get()))
//                        .addPage(SpiritFocusingPage.fromOutput(WIND_CHARGE))
//                        .addPage(SpiritFocusingPage.fromOutput(WIND_NUCLEUS.get()))
//                ))
//                .addReference(new EntryReference(IFRIT_IMPETUS, BookEntry.create("focus_elemental.ifrit_impetus")
//                        .addPage(new HeadlineTextPage("focus_elemental.ifrit_impetus"))
//                        .addPage(new TextPage("focus_elemental.ifrit_impetus.2"))
//                        .addPage(new TextPage("focus_elemental.ifrit_impetus.3"))
//                        .addPage(SpiritInfusionPage.fromOutput(IFRIT_IMPETUS.get()))
//                        .addPage(SpiritFocusingPage.fromOutput(BLAZE_POWDER))
//                        .addPage(SpiritFocusingPage.fromOutput(PYRE_NUCLEUS.get()))
//                ))
//        );
//
//        screen.addEntry("something", 15, 4, b -> b
//                .configureWidget(w -> w.setIcon(BARRIER))
//        );
//        screen.addEntry("something", 16, 4, b -> b
//                .configureWidget(w -> w.setIcon(BARRIER))
//        );
//
//
//        screen.addEntry("something", 11, 11, b -> b
//                .configureWidget(w -> w.setIcon(BARRIER))
//        );
//        screen.addEntry("something", 10, 12, b -> b
//                .configureWidget(w -> w.setIcon(BARRIER))
//        );
//        screen.addEntry("something", 10, 13, b -> b
//                .configureWidget(w -> w.setIcon(BARRIER))
//        );
//    }
//}
