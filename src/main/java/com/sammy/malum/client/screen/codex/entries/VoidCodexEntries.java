//package com.sammy.malum.client.screen.codex.entries;
//
//import com.sammy.malum.client.screen.codex.*;
//import com.sammy.malum.client.screen.codex.objects.progression.*;
//import com.sammy.malum.client.screen.codex.pages.*;
//import com.sammy.malum.client.screen.codex.pages.recipe.*;
//import com.sammy.malum.client.screen.codex.pages.recipe.vanilla.*;
//import com.sammy.malum.client.screen.codex.pages.text.*;
//import com.sammy.malum.client.screen.codex.screens.progression.*;
//import com.sammy.malum.core.systems.geas.*;
//import com.sammy.malum.registry.common.magic.*;
//import net.minecraft.*;
//import net.minecraft.core.*;
//import net.minecraft.core.registries.*;
//import net.minecraft.world.item.*;
//
//import static com.sammy.malum.MalumMod.malumPath;
//import static com.sammy.malum.client.screen.codex.WidgetDesignType.*;
//import static com.sammy.malum.client.screen.codex.WidgetDesignType.FillingType.*;
//import static com.sammy.malum.client.screen.codex.WidgetDesignType.FillingType.PAPER;
//import static com.sammy.malum.client.screen.codex.WidgetDesignType.FrameType.SOULWOOD;
//import static com.sammy.malum.registry.common.content.item.MalumItems.*;
//import static net.minecraft.world.item.Items.ENCHANTED_BOOK;
//
//public class VoidCodexEntries {
//
//    public static void setupEntries(VoidProgressionScreen screen) {
//        Item EMPTY = ItemStack.EMPTY.getItem();
//        BookPage.isVoidThemed = true;
//
//        screen.addEntry("chronicles_of_the_soul", 0, 0, b -> b
//                .setWidgetSupplier((e, x, y) -> new ScreenOpenerObject(e, x, y, ArcanaProgressionScreen.SCREEN, malumPath("textures/gui/book/icons/arcana_button.png"), 20, 20))
//                .configureWidget(w -> w.setDesign(GRAND, SOULWOOD, DARK))
//                .withTitleStyle(s -> s.withColor(ChatFormatting.YELLOW))
//        );
//
//        screen.addEntry("void.the_weeping_well", 0, 1, b -> b
//                .configureWidget(w -> w.setIcon(VOID_DEPOT).setDesign(GILDED, SOULWOOD, PAPER).setOrigin())
//                .addPage(new HeadlineTextPage("void.the_weeping_well", "void.the_weeping_well.1"))
//                .addPage(new TextPage("void.the_weeping_well.2"))
//                .addPage(new TextPage("void.the_weeping_well.3"))
//                .addPage(new TextPage("void.the_weeping_well.4"))
//                .addPage(new TextPage("void.the_weeping_well.5"))
//                .addPage(new TextPage("void.the_weeping_well.6"))
//                .addPage(new TextPage("void.the_weeping_well.7"))
//        );
//
//        screen.addEntry("void.material_study_soulstone", 0, 2, b -> b
//                .configureWidget(w -> w.setIcon(RAW_SOULSTONE).setDesign(SMALL, SOULWOOD, PAPER))
//                .addPage(new WeepingWellTextPage("void.material_study_soulstone", "void.material_study_soulstone.1", RAW_SOULSTONE.get()))
//                .addPage(new TextPage("void.material_study_soulstone.2"))
//        );
//
//        screen.addEntry("void.material_study_null_slate", -2, 3, b -> b
//                .configureWidget(w -> w.setIcon(NULL_SLATE).setDesign(DEFAULT, SOULWOOD, PAPER))
//                .addPage(new WeepingWellTextPage("void.material_study_null_slate", "void.material_study_null_slate.1", NULL_SLATE.get()))
//                .addPage(new TextPage("void.material_study_null_slate.2"))
//                .addReference(new EntryReference(UMBRAL_SPIRIT,
//                        BookEntry.create("void.material_study_null_slate.reexamination")
//                                .addPage(new HeadlineTextPage("void.material_study_null_slate.reexamination", "void.material_study_null_slate.reexamination.1"))
//                                .addPage(new TextPage("void.material_study_null_slate.reexamination.2"))
//                                .afterUmbralCrystal()
//                ))
//        );
//
//        screen.addEntry("void.material_study_mnemonic_fragment", -3, 4, b -> b
//                .configureWidget(w -> w.setIcon(MNEMONIC_FRAGMENT).setDesign(DEFAULT, SOULWOOD, PAPER))
//                .addPage(new WeepingWellTextPage("void.material_study_mnemonic_fragment", "void.material_study_mnemonic_fragment.1", MNEMONIC_FRAGMENT.get()))
//                .addPage(new TextPage("void.material_study_mnemonic_fragment.2"))
//                .addReference(new EntryReference(UMBRAL_SPIRIT,
//                        BookEntry.create("void.material_study_mnemonic_fragment.reexamination")
//                                .addPage(new HeadlineTextPage("void.material_study_mnemonic_fragment.reexamination", "void.material_study_mnemonic_fragment.reexamination.1"))
//                                .afterUmbralCrystal()
//                ))
//        );
//
//        screen.addEntry("void.material_study_void_salts", 0, 3, b -> b
//                .configureWidget(w -> w.setIcon(VOID_SALTS).setDesign(DEFAULT, SOULWOOD, PAPER))
//                .addPage(new WeepingWellTextPage("void.material_study_void_salts", "void.material_study_void_salts.1", VOID_SALTS.get()))
//                .addPage(new TextPage("void.material_study_void_salts.2"))
//                .addReference(new EntryReference(UMBRAL_SPIRIT,
//                        BookEntry.create("void.material_study_void_salts.reexamination")
//                                .addPage(new HeadlineTextPage("void.material_study_void_salts.reexamination", "void.material_study_void_salts.reexamination.1"))
//                                .afterUmbralCrystal()
//                ))
//        );
//
//        screen.addEntry("void.material_study_malignant_lead", 2, 3, b -> b
//                .configureWidget(w -> w.setIcon(MALIGNANT_LEAD).setDesign(DEFAULT, SOULWOOD, PAPER))
//                .addPage(new WeepingWellTextPage("void.material_study_malignant_lead", "void.material_study_malignant_lead.1", MALIGNANT_LEAD.get()))
//                .addPage(new TextPage("void.material_study_malignant_lead.2"))
//                .addReference(new EntryReference(UMBRAL_SPIRIT,
//                        BookEntry.create("void.material_study_malignant_lead.reexamination")
//                                .addPage(new HeadlineTextPage("void.material_study_malignant_lead.reexamination", "void.material_study_malignant_lead.reexamination.1"))
//                                .addPage(new TextPage("void.material_study_malignant_lead.reexamination.2"))
//                                .afterUmbralCrystal()
//                ))
//        );
//
//        screen.addEntry("void.material_study_auric_embers", 3, 4, b -> b
//                .configureWidget(w -> w.setIcon(AURIC_EMBERS).setDesign(DEFAULT, SOULWOOD, PAPER))
//                .addPage(new WeepingWellTextPage("void.material_study_auric_embers", "void.material_study_auric_embers.1", AURIC_EMBERS.get()))
//                .addPage(new TextPage("void.material_study_auric_embers.2"))
//                .addReference(new EntryReference(UMBRAL_SPIRIT,
//                        BookEntry.create("void.material_study_auric_embers.reexamination")
//                                .addPage(new HeadlineTextPage("void.material_study_auric_embers.reexamination", "void.material_study_auric_embers.reexamination.1"))
//                                .afterUmbralCrystal()
//                ))
//        );
//
//        screen.addEntry("void.catalyst_lobber", 5, 5, b -> b
//                .configureWidget(w -> w.setIcon(CATALYST_LOBBER).setDesign(DEFAULT, SOULWOOD, PAPER))
//                .addPage(new WeepingWellTextPage("void.catalyst_lobber", "void.catalyst_lobber.1", CATALYST_LOBBER.get()))
//                .addPage(new TextPage("void.catalyst_lobber.2"))
//                .addPage(new TextPage("void.catalyst_lobber.3"))
//                .addPage(SpiritInfusionPage.fromOutput(CATALYST_LOBBER.get()))
//        );
//
//        screen.addEntry("void.black_crystal", 0, 5, b -> b
//                .withFragmentEntry(fragment -> fragment.addPage(new WeepingWellTextPage("fragment.void.black_crystal", "fragment.void.black_crystal.1", EMPTY)))
//                .configureWidget(w -> w.setIcon(UMBRAL_SPIRIT).setDesign(GILDED, SOULWOOD, PAPER))
//                .addPage(new WeepingWellTextPage("void.black_crystal", "void.black_crystal.1", UMBRAL_SPIRIT.get()))
//                .addPage(new TextPage("void.black_crystal.2"))
//                .addPage(new TextPage("void.black_crystal.3"))
//                .addPage(new TextPage("void.black_crystal.4"))
//                .afterUmbralCrystal()
//        );
//
//        screen.addEntry("void.umbral_arcana", -1, 6, b -> b
//                .withEmptyFragmentEntry(DEFAULT)
//                .configureWidget(w -> w.setDesign(DEFAULT, SOULWOOD, DARK))
//                .setWidgetSupplier((e, x, y) -> new IconObject(e, x, y, malumPath("textures/gui/book/icons/umbral_shard.png")))
//                .addPage(new HeadlineTextPage("void.umbral_arcana", "void.umbral_arcana.1"))
//                .addPage(new TextPage("void.umbral_arcana.2"))
//                .addPage(new TextPage("void.umbral_arcana.3"))
//                .addPage(new TextPage("void.umbral_arcana.4"))
//                .addPage(new TextPage("void.umbral_arcana.5"))
//                .afterUmbralCrystal()
//        );
//
//        screen.addEntry("void.inverse_and_hybrid_arcana", 0, 7, b -> b
//                .withEmptyFragmentEntry(SMALL)
//                .configureWidget(w -> w.setDesign(DEFAULT, SOULWOOD, DARK))
//                .setWidgetSupplier((e, x, y) -> new IconObject(e, x, y, malumPath("textures/gui/book/icons/umbral_shard.png")))
//                .addPage(new HeadlineTextPage("void.inverse_and_hybrid_arcana", "void.inverse_and_hybrid_arcana.1"))
//                .addPage(new TextPage("void.inverse_and_hybrid_arcana.2"))
//                .addPage(new TextPage("void.inverse_and_hybrid_arcana.3"))
//                .afterUmbralCrystal()
//        );
//
//        screen.addEntry("void.material_study_arcana", 1, 8, b -> b
//                .withTraceFragmentEntry()
//                .configureWidget(w -> w.setDesign(DEFAULT, SOULWOOD, DARK))
//                .setWidgetSupplier((e, x, y) -> new IconObject(e, x, y, malumPath("textures/gui/book/icons/umbral_shard.png")))
//                .addPage(new HeadlineTextPage("void.material_study_arcana", "void.material_study_arcana.1"))
//                .addPage(new TextPage("void.material_study_arcana.2"))
//                .addPage(new TextPage("void.material_study_arcana.3"))
//                .afterUmbralCrystal()
//        );
//
//        screen.addEntry("void.staves_as_foci", 0, 9, b -> b
//                .withTraceFragmentEntry()
//                .configureWidget(w -> w.setIcon(MNEMONIC_HEX_STAFF).setDesign(GILDED, SOULWOOD, PAPER))
//                .addPage(new HeadlineTextPage("void.staves_as_foci", "void.staves_as_foci.1"))
//                .addPage(new TextPage("void.staves_as_foci.2"))
//                .addPage(new TextPage("void.staves_as_foci.3"))
//                .addPage(new TextPage("void.staves_as_foci.4"))
//                .addPage(new TextPage("void.staves_as_foci.5"))
//                .addPage(SpiritInfusionPage.fromOutput(MNEMONIC_HEX_STAFF.get()))
//                .addReference(new EntryReference(ENCHANTED_BOOK,
//                        BookEntry.create("void.staves_as_foci.enchanting")
//                                .addPage(new HeadlineTextPage("void.staves_as_foci.enchanting.replenishing", "void.staves_as_foci.enchanting.replenishing.1"))
//                                .addPage(new HeadlineTextPage("void.staves_as_foci.enchanting.capacitor", "void.staves_as_foci.enchanting.capacitor.1"))
//                ))
//                .addReference(new EntryReference(RING_OF_THE_ENDLESS_WELL,
//                        BookEntry.create("void.staves_as_foci.ring_of_the_endless_well")
//                                .addPage(new HeadlineTextPage("void.staves_as_foci.ring_of_the_endless_well", "void.staves_as_foci.ring_of_the_endless_well.1"))
//                                .addPage(SpiritInfusionPage.fromOutput(RING_OF_THE_ENDLESS_WELL.get()))
//                ))
//                .afterUmbralCrystal()
//        );
//
//        // Geas entries
//        addGeasEntry(screen, MalumGeasEffectTypes.OATH_OF_THE_OVERKEEN_EYE, -7, 13);
//        addGeasEntry(screen, MalumGeasEffectTypes.OATH_OF_THE_OVERBURDENED_MIND, -8, 14);
//        addGeasEntry(screen, MalumGeasEffectTypes.OATH_OF_THE_OVEREAGER_FIST, -7, 15);
//        addGeasEntry(screen, MalumGeasEffectTypes.OATH_OF_UNMAKERS_DISDAIN, 7, 13);
//        addGeasEntry(screen, MalumGeasEffectTypes.OATH_OF_UNSIGHTED_RESISTANCE, 8, 14);
//        addGeasEntry(screen, MalumGeasEffectTypes.OATH_OF_THE_UNDISCERNED_MAW, 7, 15);
//
//        // Remaining ring, necklace, and artifact entries
//        screen.addEntry("void.ring_of_gruesome_concentration", -3, 9, b -> b
//                .withTraceFragmentEntry()
//                .configureWidget(w -> w.setIcon(RING_OF_GRUESOME_CONCENTRATION).setDesign(DEFAULT, SOULWOOD, PAPER))
//                .addPage(new HeadlineTextPage("void.ring_of_gruesome_concentration", "void.ring_of_gruesome_concentration.1"))
//                .addPage(SpiritInfusionPage.fromOutput(RING_OF_GRUESOME_CONCENTRATION.get()))
//                .afterUmbralCrystal()
//        );
//
//        screen.addEntry("void.ring_of_growing_flesh", -4, 10, b -> b
//                .configureWidget(w -> w.setIcon(RING_OF_GROWING_FLESH).setDesign(DEFAULT, SOULWOOD, PAPER))
//                .addPage(new HeadlineTextPage("void.ring_of_growing_flesh", "void.ring_of_growing_flesh.1"))
//                .addPage(SpiritInfusionPage.fromOutput(RING_OF_GROWING_FLESH.get()))
//                .afterUmbralCrystal()
//        );
//
//        screen.addEntry("void.ring_of_echoing_arcana", -5, 10, b -> b
//                .configureWidget(w -> w.setIcon(RING_OF_ECHOING_ARCANA).setDesign(DEFAULT, SOULWOOD, PAPER))
//                .addPage(new HeadlineTextPage("void.ring_of_echoing_arcana", "void.ring_of_echoing_arcana.1"))
//                .addPage(SpiritInfusionPage.fromOutput(RING_OF_ECHOING_ARCANA.get()))
//                .afterUmbralCrystal()
//        );
//
//        screen.addEntry("void.necklace_of_the_watcher", -3, 11, b -> b
//                .configureWidget(w -> w.setIcon(NECKLACE_OF_THE_WATCHER).setDesign(DEFAULT, SOULWOOD, PAPER))
//                .addPage(new HeadlineTextPage("void.necklace_of_the_watcher", "void.necklace_of_the_watcher.1"))
//                .addPage(SpiritInfusionPage.fromOutput(NECKLACE_OF_THE_WATCHER.get()))
//                .afterUmbralCrystal()
//        );
//
//        screen.addEntry("void.necklace_of_the_hidden_blade", -4, 12, b -> b
//                .configureWidget(w -> w.setIcon(NECKLACE_OF_THE_HIDDEN_BLADE).setDesign(DEFAULT, SOULWOOD, PAPER))
//                .addPage(new HeadlineTextPage("void.necklace_of_the_hidden_blade", "void.necklace_of_the_hidden_blade.1"))
//                .addPage(SpiritInfusionPage.fromOutput(NECKLACE_OF_THE_HIDDEN_BLADE.get()))
//                .addPage(new TextPage("void.necklace_of_the_hidden_blade.2"))
//                .addPage(new TextPage("void.necklace_of_the_hidden_blade.3"))
//                .afterUmbralCrystal()
//        );
//
//        screen.addEntry("void.malignant_pewter", 3, 9, b -> b
//                .withTraceFragmentEntry()
//                .configureWidget(w -> w.setIcon(MALIGNANT_PEWTER_INGOT).setDesign(DEFAULT, SOULWOOD, PAPER))
//                .addPage(new HeadlineTextPage("void.malignant_pewter", "void.malignant_pewter.1"))
//                .addPage(SpiritInfusionPage.fromOutput(MALIGNANT_PEWTER_INGOT.get()))
//                .addPage(new TextPage("void.malignant_pewter.2"))
//                .addPage(new TextPage("void.malignant_pewter.3"))
//                .afterUmbralCrystal()
//        );
//
//        screen.addEntry("void.weight_of_worlds", 4, 10, b -> b
//                .configureWidget(w -> w.setIcon(WEIGHT_OF_WORLDS).setDesign(DEFAULT, SOULWOOD, PAPER))
//                .addPage(new HeadlineTextPage("void.weight_of_worlds", "void.weight_of_worlds.1"))
//                .addPage(SpiritInfusionPage.fromOutput(WEIGHT_OF_WORLDS.get()))
//                .addPage(new TextPage("void.weight_of_worlds.2"))
//                .afterUmbralCrystal()
//        );
//
//        screen.addEntry("void.edge_of_deliverance", 5, 10, b -> b
//                .configureWidget(w -> w.setIcon(EDGE_OF_DELIVERANCE).setDesign(DEFAULT, SOULWOOD, PAPER))
//                .addPage(new HeadlineTextPage("void.edge_of_deliverance", "void.edge_of_deliverance.1"))
//                .addPage(SpiritInfusionPage.fromOutput(EDGE_OF_DELIVERANCE.get()))
//                .addPage(new TextPage("void.edge_of_deliverance.2"))
//                .afterUmbralCrystal()
//        );
//
//        screen.addEntry("void.erosion_scepter", 3, 11, b -> b
//                .configureWidget(w -> w.setIcon(EROSION_SCEPTER)
//                        .setDesign(DEFAULT, SOULWOOD, PAPER))
//                .addPage(new HeadlineTextPage("void.erosion_scepter", "void.erosion_scepter.1"))
//                .addPage(SpiritInfusionPage.fromOutput(EROSION_SCEPTER.get()))
//                .addPage(new TextPage("void.erosion_scepter.2"))
//                .addPage(new TextPage("void.erosion_scepter.3"))
//                .addPage(new TextPage("void.erosion_scepter.4"))
//                .afterUmbralCrystal()
//        );
//
//        screen.addEntry("void.malignant_stronghold_armor", 4, 12, b -> b
//                .configureWidget(w -> w.setIcon(MALIGNANT_STRONGHOLD_HELMET)
//                        .setDesign(DEFAULT, SOULWOOD, PAPER))
//                .addPage(new HeadlineTextItemPage("void.malignant_stronghold_armor", MALIGNANT_STRONGHOLD_CHESTPLATE.get()))
//                .addPage(new TextPage("void.malignant_stronghold_armor.2"))
//                .addPage(new TextPage("void.malignant_stronghold_armor.3"))
//                .addPage(new TextPage("void.malignant_stronghold_armor.4"))
//                .addPage(new TextPage("void.malignant_stronghold_armor.5"))
//                .addPage(new CyclingPage(
//                        SpiritInfusionPage.fromOutput(MALIGNANT_STRONGHOLD_HELMET.get()),
//                        SpiritInfusionPage.fromOutput(MALIGNANT_STRONGHOLD_CHESTPLATE.get()),
//                        SpiritInfusionPage.fromOutput(MALIGNANT_STRONGHOLD_LEGGINGS.get()),
//                        SpiritInfusionPage.fromOutput(MALIGNANT_STRONGHOLD_BOOTS.get())
//                ))
//                .afterUmbralCrystal()
//        );
//
//
//        var voidRunesSubspace = new SubspaceEntryObject.SubspaceWidgetSupplier().setSize(200);
//        addVoidRuneEntry(voidRunesSubspace, RUNE_OF_BOLSTERING, -2, 12);
//        addVoidRuneEntry(voidRunesSubspace, RUNE_OF_RADIAL_EMPOWERMENT, 2, 12);
//        addVoidRuneEntry(voidRunesSubspace, RUNE_OF_SPELL_MASTERY, 0, 10);
//        addVoidRuneEntry(voidRunesSubspace, RUNE_OF_HERESY, 0, 14);
//        addVoidRuneEntry(voidRunesSubspace, RUNE_OF_UNNATURAL_STAMINA, -1, 11);
//        addVoidRuneEntry(voidRunesSubspace, RUNE_OF_TWINNED_DURATION, 1, 13);
//        addVoidRuneEntry(voidRunesSubspace, RUNE_OF_INDOMITABILITY, 1, 11);
//        addVoidRuneEntry(voidRunesSubspace, RUNE_OF_IGNEOUS_SOLACE, -1, 13);
//
//        screen.addEntry("void.runes", 0, 12, b -> b
//                .configureWidget(w -> w.setDesign(SUBENTRY, SOULWOOD, DARK))
//                .setWidgetSupplier(voidRunesSubspace)
//                .setAssociatedSpirit(MalumSpiritTypes.ELDRITCH_SPIRIT)
//                .afterUmbralCrystal());
//
//
//// screen.addEntry("void.anomalous_design", 0, 7, b -> b
////         .setWidgetConfig(w -> w.setIcon(ANOMALOUS_DESIGN)
////                 .setStyle(WidgetDesignType.DEFAULT.createDesign(FrameType.SOULWOOD, FillingType.PAPER)))
////         .addPage(new HeadlineTextItemPage("void.anomalous_design", "void.anomalous_design.1", ANOMALOUS_DESIGN.get()))
////         .addPage(SpiritInfusionPage.fromOutput(COMPLETE_DESIGN.get()))
//// );
//
//        screen.addEntry("void.fused_consciousness", 0, 15, b -> b
//                .configureWidget(w -> w.setIcon(FUSED_CONSCIOUSNESS).setDesign(GILDED, SOULWOOD, PAPER))
//                .addPage(new WeepingWellTextPage("void.fused_consciousness", "void.fused_consciousness.1", FUSED_CONSCIOUSNESS.get()))
//                .addPage(SpiritInfusionPage.fromOutput(COMPLETE_DESIGN.get()))
//                .afterUmbralCrystal()
//        );
//
//        screen.addEntry("void.sundering_anchor", -2, 16, b -> b
//                .configureWidget(w -> w.setIcon(SUNDERING_ANCHOR)
//                        .setDesign(TOTEMIC, SOULWOOD, PAPER))
//                .addPage(new WeepingWellTextPage("void.sundering_anchor", "void.sundering_anchor.1", SUNDERING_ANCHOR.get()))
//                .addPage(new TextPage("void.sundering_anchor.2"))
//                .addPage(new TextPage("void.sundering_anchor.3"))
//                .addPage(SpiritInfusionPage.fromOutput(SUNDERING_ANCHOR.get()))
//                .afterUmbralCrystal()
//        );
//
//        screen.addEntry("void.unwinding_chaos", 2, 16, b -> b
//                .configureWidget(w -> w.setIcon(UNWINDING_CHAOS)
//                        .setDesign(TOTEMIC, SOULWOOD, PAPER))
//                .addPage(new WeepingWellTextPage("void.unwinding_chaos", "void.unwinding_chaos.1", UNWINDING_CHAOS.get()))
//                .addPage(new TextPage("void.unwinding_chaos.2"))
//                .addPage(new TextPage("void.unwinding_chaos.3"))
//                .addPage(SpiritInfusionPage.fromOutput(UNWINDING_CHAOS.get()))
//                .afterUmbralCrystal()
//        );
//
//        screen.addEntry("void.belt_of_the_limitless", -3, 17, b -> b
//                .configureWidget(w -> w.setIcon(BELT_OF_THE_LIMITLESS)
//                        .setDesign(TOTEMIC, SOULWOOD, PAPER))
//                .addPage(new WeepingWellTextPage("void.belt_of_the_limitless", "void.belt_of_the_limitless.1", BELT_OF_THE_LIMITLESS.get()))
//                .addPage(new TextPage("void.belt_of_the_limitless.2"))
//                .addPage(new TextPage("void.belt_of_the_limitless.3"))
//                .addPage(SpiritInfusionPage.fromOutput(BELT_OF_THE_LIMITLESS.get()))
//                .afterUmbralCrystal()
//        );
//        addGeasEntry(screen, MalumGeasEffectTypes.AUTHORITY_OF_THE_INVERTED_HEART, -2, 18);
//        addGeasEntry(screen, MalumGeasEffectTypes.AUTHORITY_OF_THE_GLEEFUL_TARGET, 2, 18);
//        BookPage.isVoidThemed = false;
//    }
//
//    public static void addGeasEntry(AbstractProgressionCodexScreen screen, Holder<GeasEffectType> geas, int x, int y) {
//        screen.addEntry(geas.value().getRegistryName().getPath(), x, y, b -> b
//                .configureWidget(w -> w.setIcon(geas).setDesign(DEFAULT, SOULWOOD, DARK))
//                .addPage(SoulBindingPage.fromGeas(geas))
//                .addPage(new GeasInfoPage(geas))
//                .afterUmbralCrystal()
//        );
//    }
//
//    public static void addVoidRuneEntry(PlacedEntryAcceptor acceptor, Holder<Item> rune, int x, int y) {
//        var translationKey = "void." + BuiltInRegistries.ITEM.getKey(rune.value()).getPath();
//        acceptor.addEntry(translationKey, x, y, b -> b
//                .configureWidget(w -> w.setIcon(rune.value()).setDesign(DEFAULT, SOULWOOD, PAPER))
//                .addPage(new HeadlineTextPage(translationKey))
//                .addPage(RuneworkingPage.fromOutput(rune.value()))
//        );
//    }
//}