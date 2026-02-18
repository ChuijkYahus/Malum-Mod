package com.sammy.malum.datagen;

import com.sammy.malum.MalumMod;
import com.sammy.malum.registry.common.sound.*;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import team.lodestar.lodestone.systems.datagen.providers.sound.*;

import static com.sammy.malum.registry.common.sound.MalumSoundEvents.*;


public class MalumSoundDatagen extends LodestoneBlockSoundEventProvider {

    public MalumSoundDatagen(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
        super(packOutput, MalumMod.MALUM, existingFileHelper);
    }

    @Override
    public void registerSounds() {
        add(ARCANA_CODEX_OPEN, s -> s.with(allSounds("codex", "book_open")));
        add(ARCANA_CODEX_CLOSE, s -> s.with(allSounds("codex", "book_close")));
        add(ARCANA_ENTRY_OPEN, s -> s.with(allSounds("codex", "book_entry_open")));
        add(ARCANA_ENTRY_CLOSE, s -> s.with(allSounds("codex", "book_entry_close")));
        add(ARCANA_ENTRY_HOVER, s -> s.with(allSounds("codex", "book_hover")));
        add(ARCANA_ENTRY_UNHOVER, s -> s.with(allSounds("codex", "book_unhover")));
        add(ARCANA_PAGE_FLIP, s -> s.with(allSounds("codex", "book_page_turn")));
        add(ARCANA_SUBENTRY_OPEN, s -> s.with(allSounds("codex", "book_subentry_open")));
        add(ARCANA_SUBENTRY_CLOSE, s -> s.with(allSounds("codex", "book_subentry_close")));
        add(ARCANA_SWEETENER_NORMAL, s -> s.with(allSounds("codex", "book_swtnr_normal")));
        add(ARCANA_SWEETENER_EVIL, s -> s.with(allSounds("codex", "book_swtnr_evil")));
        add(ARCANA_TRANSITION_NORMAL, s -> s.with(allSounds("codex", "book_transition_normal")));
        add(ARCANA_TRANSITION_EVIL, s -> s.with(allSounds("codex", "book_transition_evil")));

        add(PEDESTAL_ITEM_INSERT, s -> s.with(allSounds("block_interaction", "pedestal_item_insert")));
        add(PEDESTAL_ITEM_PICKUP, s -> s.with(allSounds("block_interaction", "pedestal_item_remove")));
        add(PEDESTAL_SPIRIT_INSERT, s -> s.with(allSounds("block_interaction", "pedestal_spirit_insert")));
        add(PEDESTAL_SPIRIT_PICKUP, s -> s.with(allSounds("block_interaction", "pedestal_spirit_remove")));

        add(CLOTH_TRINKET_EQUIP, s -> s.with(allSounds("equip_sounds/cloth", "equip_cloth")));
        add(ORNATE_TRINKET_EQUIP, s -> s.with(allSounds("equip_sounds/ornate", "equip_ornate")));
        add(GILDED_TRINKET_EQUIP, s -> s.with(allSounds("equip_sounds/gilded", "equip_gilded")));
        add(ALCHEMICAL_TRINKET_EQUIP, s -> s.with(allSounds("equip_sounds/alchemical", "equip_alchemical")));
        add(ROTTEN_TRINKET_EQUIP, s -> s.with(allSounds("equip_sounds/rotten", "equip_rotten")));
        add(METALLIC_TRINKET_EQUIP, s -> s.with(allSounds("equip_sounds/metallic", "equip_metallic")));
        add(VOID_TRINKET_EQUIP, s -> s.with(allSounds("equip_sounds/void", "equip_void")));

        add(RUNE_EQUIP, s -> s.with(allSounds("equip_sounds/rune", "equip_rune_stone")));
        add(TOTEMIC_RUNE_EQUIP, s -> s.with(allSounds("equip_sounds/rune", "equip_rune_wood")));
        add(VOID_RUNE_EQUIP, s -> s.with(allSounds("equip_sounds/rune", "equip_rune_void")));

        add(HUNGRY_BELT_FEEDS, s -> s.with(allSounds("curiosities/gear/trinkets/starved", "nom")));
        add(VORACIOUS_RING_FEEDS, s -> s.with(allSounds("curiosities/gear/trinkets/starved", "nom")));
        add(GRUESOME_RING_FEEDS, s -> s.with(allSounds("curiosities/gear/trinkets/starved", "nom")));
        add(FLESH_RING_ABSORBS, s -> s.with(allSounds("curiosities/gear/trinkets/cancer_ring", "grow")));
        add(ECHOING_RING_ABSORBS, s -> s.with(allSounds("curiosities/gear/trinkets/cancer_ring", "grow"))); // TODO: unique sound


        add(CONCENTRATED_GLUTTONY_DRINK, s -> s.with(allSounds("curiosities/gear/concentrated_gluttony", "drink")));
        add(SPIRIT_MOTE_CREATED, s -> s.with(allSounds("curiosities/gear/lamplighters_tongs", "created")));

        add(RAVENOUS_POUCH_INSERT, s -> s.with(allSounds("curiosities/gear/ravenous_pouch", "insert")));

        add(TUNING_FORK_TINKER, s -> s.with(allSounds("curiosities/augments", "tinker")));
        add(CRUCIBLE_AUGMENT_APPLY, s -> s.with(allSounds("curiosities/augments", "augment_insert")));
        add(CRUCIBLE_AUGMENT_REMOVE, s -> s.with(allSounds("curiosities/augments", "augment_remove")));
        add(WARPING_ENGINE_REVERBERATES, s -> s.with(allSounds("curiosities/augments", "warping_engine_reverberates")));
        add(SHIELDING_APPARATUS_SHIELDS, s -> s.with(allSounds("curiosities/augments", "shielding_apparatus_shields")));
        add(SUSPICIOUS_DEVICE_DETONATES, s -> s.with(allSounds("curiosities/augments", "device_detonate")));
        add(SUSPICIOUS_DEVICE_DETONATES_AGAIN, s -> s.with(allSounds("curiosities/augments", "device_secondary_detonate")));
        add(SWAG_MESSIAH, s -> s.with(sound("curiosities/augments/legalize_nuclear_bombs")));

        add(ARCANE_WHISPERS, s -> s.with(allSounds("curiosities/soul", "spirit_idle")));
        add(SPIRIT_PICKUP, s -> s.with(allSounds("curiosities/soul", "spirit_pickup")));
        add(SOUL_SHATTER, s -> s.with(allSounds("curiosities/soul", "soul_shatter")));

        add(BLIGHT_PROPAGATION, s -> s.with(allSounds("block/blight", "blight_propagation")));
        add(SCARSTONE_PROPAGATION, s -> s.with(allSounds("block/scarstone", "scarstone_propagation")));

        add(AVARICE_COLLECT, s -> s.with(allSounds("curiosities/effects/avarice", "collect")));
        add(AVARICE_FORTUNE, s -> s.with(allSounds("curiosities/effects/avarice", "fortune")));

        add(SOUL_WARD_HIT, s -> s.with(allSounds("curiosities/effects/soul_ward", "block")));
        add(SOUL_WARD_GROW, s -> s.with(allSounds("curiosities/effects/soul_ward", "grow")));
        add(SOUL_WARD_FULLY_CHARGED, s -> s.with(allSounds("curiosities/effects/soul_ward", "full")));
        add(SOUL_WARD_DEPLETE, s -> s.with(allSounds("curiosities/effects/soul_ward", "break")));

        add(MALIGNANT_AEGIS_HIT, s -> s.with(allSounds("curiosities/effects/malignant_aegis", "block")));
        add(MALIGNANT_AEGIS_GROW, s -> s.with(allSounds("curiosities/effects/malignant_aegis", "grow")));
        add(MALIGNANT_AEGIS_FULLY_CHARGED, s -> s.with(allSounds("curiosities/effects/malignant_aegis", "full")));
        add(MALIGNANT_AEGIS_DEPLETE, s -> s.with(allSounds("curiosities/effects/malignant_aegis", "break")));

        add(SPELL_CHARGE_GROW, s -> s.with(allSounds("curiosities/effects/spell_charge", "charge")));
        add(SPELL_CHARGE_FULL, s -> s.with(allSounds("curiosities/effects/spell_charge", "charge_full")));

        add(SCYTHE_SWEEP, s -> s.with(allSounds("curiosities/gear/weapons/scythe", "sweep")));
        add(SCYTHE_CUT, s -> s.with(allSounds("curiosities/gear/weapons/scythe", "sweep")));
        add(EDGE_OF_DELIVERANCE_SWEEP, s -> s.with(allSounds("curiosities/gear/weapons/scythe", "sweep")));
        add(EDGE_OF_DELIVERANCE_CUT, s -> s.with(allSounds("curiosities/gear/weapons/scythe", "sweep")));

        add(SCYTHE_ASCENSION, s -> s.with(allSounds("curiosities/gear/weapons/scythe", "throw")));
        add(SCYTHE_ASCENSION_LAUNCH, s -> s.with(allSounds("minecraft:entity/wind_charge", "wind_burst")));

        add(SCYTHE_THROW, s -> s.with(allSounds("curiosities/gear/weapons/scythe", "throw")));
        add(SCYTHE_SPINS, s -> s.with(sound("curiosities/gear/weapons/scythe/spin")));
        add(SCYTHE_CATCH, s -> s.with(allSounds("curiosities/gear/weapons/scythe", "catch")));

        add(HIDDEN_BLADE_CHARGED, s -> s.with(sound("curiosities/gear/trinkets/hidden_blade/charge")));
        add(HIDDEN_BLADE_PRIMED, s -> s.with(sound("curiosities/gear/trinkets/hidden_blade/charge")));
        add(HIDDEN_BLADE_DISRUPTED, s -> s.with(sound("curiosities/gear/trinkets/hidden_blade/charge")));
        add(HIDDEN_BLADE_UNLEASHED, s -> s.with(allSounds("curiosities/gear/trinkets/hidden_blade", "flurry")));

        add(SPELLWEAVING_TOOL_PRIME, s -> s.with(allSounds("curiosities/spellweaving", "prime_tool")));
        add(SPELLWEAVING_TOOL_DAMPEN, s -> s.with(allSounds("curiosities/spellweaving", "dampen_tool")));
        add(SPELLWOVEN_SPRITE_SPAWN, s -> s.with(allSounds("curiosities/spellweaving", "spell_spawn")));
        add(SPELLWOVEN_SPRITE_HARVESTS, s -> s.with(allSounds("curiosities/spellweaving", "spell_mine")));
        add(SPELLWOVEN_SPRITE_RETURNS, s -> s.with(allSounds("curiosities/spellweaving", "spell_vanish")));

        add(RAVENOUS_SCYTHE_EATS, s -> s.with(allSounds("curiosities/gear/weapons/ravenous", "scythe_hit")));
        add(GLUTTONOUS_BLUDGEON_SPROUTS, s -> s.with(allSounds("curiosities/gear/weapons/ravenous", "bludgeon_hit")));

        add(TYRVING_SLASH, s -> s.with(allSounds("curiosities/gear/weapons/tyrving", "hit")));
        add(WEIGHT_OF_WORLDS_CUT, s -> s.with(allSounds("curiosities/gear/weapons/scythe", "sweep")));

        add(SUNDERING_ANCHOR_SWING, s -> s.with(allSounds("curiosities/gear/weapons/sundering_anchor", "swing")));
        add(SUNDERING_ANCHOR_EXTRA_SWING, s -> s.with(allSounds("curiosities/gear/weapons/sundering_anchor", "extra_swing")));
        add(SUNDERING_ANCHOR_PROJECTILE_SWING, s -> s.with(allSounds("curiosities/gear/weapons/sundering_anchor", "extra_swing")));
        add(SUNDERING_ANCHOR_THROW, s -> s.with(allSounds("curiosities/gear/weapons/scythe", "throw")));
        add(SUNDERING_ANCHOR_CATCH, s -> s.with(allSounds("curiosities/gear/weapons/scythe", "catch")));

        add(OAKEN_MIGHT_HIT, s -> s.with(allSounds("curiosities/effects/potion", "oaken_might")));

        add(WARLOCK_BLAST, s -> s.with(allSounds("curiosities/geas", "warlock_impact")));
        add(REAPER_CUT, s -> s.with(allSounds("curiosities/geas", "reaper_impact")));
        add(BERSERKER_WRATH, s -> s.with(allSounds("curiosities/geas", "berserker_impact")));
        add(PATIENT_DROWNING, s -> s.with(allSounds("minecraft:entity/player/hurt", "drown")));
        add(DESPERATE_NEED_CUT, s -> s.with(allSounds("curiosities/geas", "reaper_impact")));
        add(DESPERATE_NEED_WITHDRAWAL, s -> s.with(allSounds("curiosities/geas", "reaper_impact")));
        add(PROSPECTOR_BURN, s -> s.with(sound("minecraft:mob/ghast/fireball4")));
        add(COMBUSTION_WHIPLASH, s -> s.with(sound("minecraft:random/fizz")));
        add(WYRD_RECONSTRUCTION, s -> s.with(allSounds("curiosities/geas", "wyrd_reconstruction")));
        add(INVERTED_HEART_IMPACT, s -> s.with(allSounds("curiosities/geas", "soulwashing_impact")));

        add(CATALYST_LOBBER_UNLOCKED, s -> s.with(allSounds("curiosities/gear/catalyst_lobber", "open")));
        add(CATALYST_LOBBER_LOCKED, s -> s.with(allSounds("curiosities/gear/catalyst_lobber", "open")));
        add(CATALYST_LOBBER_PRIMED, s -> s.with(allSounds("curiosities/gear/catalyst_lobber", "load")));
        add(CATALYST_LOBBER_FIRED, s -> s.with(allSounds("curiosities/gear/catalyst_lobber", "fire")));

        add(STAFF_FIRES, s -> s.with(allSounds("curiosities/gear/weapons/staff", "fire")));
        add(STAFF_POWERS_UP, s -> s.with(allSounds("curiosities/gear/weapons/staff", "power_up")));
        add(STAFF_SIZZLES_OUT, s -> s.with(allSounds("curiosities/gear/weapons/staff", "sizzle")));
        add(STAFF_CHARGED, s -> s.with(allSounds("curiosities/gear/weapons/staff", "charge")));
        add(STAFF_STRIKES, s -> s.with(allSounds("curiosities/gear/weapons/staff", "hit")));

        add(DRAINING_MOTIF, s -> s.with(allSounds("curiosities/motifs", "draining_bubbling")));
        add(MALIGNANT_METAL_MOTIF, s -> s.with(allSounds("curiosities/motifs", "malignant_crit")));
        add(MALIGNANT_METAL_COMBO, s -> s.with(allSounds("curiosities/motifs", "malignant_crit")));

        add(WORLDSOUL_MOTIF_LIGHT_IMPACT, s -> s.with(allSounds("curiosities/motifs", "worldsoul_minor_impact")));
        add(WORLDSOUL_MOTIF_HEAVY_IMPACT, s -> s.with(allSounds("curiosities/motifs", "worldsoul_impact")));
        add(WORLDSOUL_MOTIF_REVERB, s -> s.with(allSounds("curiosities/motifs", "worldsoul_reverb")));

        add(ALTAR_LOOP, s -> s.with(sound("altar/altar_loop")));
        add(ALTAR_CRAFT, s -> s.with(allSounds("altar", "altar_craft")));
        add(ALTAR_CONSUME, s -> s.with(allSounds("altar", "altar_consume")));
        add(ALTAR_SPEED_UP, s -> s.with(allSounds("altar", "altar_speedup")));

        add(CRUCIBLE_LOOP, s -> s.with(sound("crucible/crucible_loop")));
        add(CRUCIBLE_CRAFT, s -> s.with(allSounds("crucible", "crucible_craft")));
        add(IMPETUS_CRACK, s -> s.with(allSounds("crucible", "impetus_crack")));

        add(REPAIR_PYLON_LOOP, s -> s.with(sound("repair_pylon/repair_pylon_loop")));
        add(REPAIR_PYLON_REPAIR_START, s -> s.with(allSounds("repair_pylon", "repair_pylon_send")));
        add(REPAIR_PYLON_REPAIR_FINISH, s -> s.with(allSounds("repair_pylon", "repair_pylon_fix")));

        add(BRAZIER_LOOP, s -> s.with(sound("brazier/brazier_loop")));
        add(BRAZIER_START, s -> s.with(allSounds("brazier", "brazier_start")));
        add(BRAZIER_FINISH, s -> s.with(allSounds("brazier", "brazier_end")));
        add(BRAZIER_SACRIFICE, s -> s.with(allSounds("brazier", "brazier_sacrifice")));

        add(RUNIC_WORKBENCH_SHAPES_RUNE_STONE, s -> s.with(allSounds("runic_workbench", "craft_rune_stone")));
        add(RUNIC_WORKBENCH_SHAPES_RUNE_WOODEN, s -> s.with(allSounds("runic_workbench", "craft_rune_wood")));
        add(RUNIC_WORKBENCH_SHAPES_RUNE_VOID, s -> s.with(allSounds("runic_workbench", "craft_rune_void")));
        add(RUNIC_WORKBENCH_SHAPES_RUNE_GENERIC, s -> s.with(allSounds("runic_workbench", "craft_rune_generic")));

        add(WEAVERS_WORKBENCH_CRAFT, s -> s.with(allSounds("runic_workbench", "craft_rune_generic")));

        add(TOTEM_LOOP, s -> s.with(sound("totem/totem_loop")));
        add(TOTEM_CHARGE, s -> s.with(allSounds("totem", "totem_charge")));
        add(TOTEM_ACTIVATED, s -> s.with(sound("totem/totem_activate")));
        add(TOTEM_CANCELLED, s -> s.with(sound("totem/totem_cancel")));
        add(TOTEM_ENGRAVE, s -> s.with(allSounds("totem", "totem_engrave")));

        add(SPARK_FORMED, s -> s.with(allSounds("totem/spark", "spark_create")));
        add(SPARK_IMPACT, s -> s.with(allSounds("totem/spark", "spark_hit")));
        add(SPARK_POTION_IMPACT, s -> s.with(allSounds("totem/spark", "spark_potion_hit")));
        add(SPARK_UNWOVEN, s -> s.with(allSounds("totem/spark", "spark_create", se -> se.pitch(0.5f))));
        add(SPARK_DIRECTED, s -> s.with(allSounds("totem/spark", "spark_direct")));

        add(TOTEM_BLOCK_GRAVITY, s -> s.with(allSounds("minecraft:mob/phantom", "flap")));
        add(TOTEM_BLOCK_GROW, s -> s.with(allSounds("minecraft:item/bonemeal", "bonemeal")));
        add(TOTEM_BLOCK_SAP, s -> s.with(allSounds("minecraft:block/pointed_dripstone", "drip_water_cauldron")));

        add(RITUAL_BEGINS, s -> s.with(sound("ritual/ritual_start")));
        add(RITUAL_ABSORBS_ITEM, s -> s.with(allSounds("ritual", "ritual_absorb_item")));
        add(RITUAL_FORMS, s -> s.with(sound("ritual/ritual_stage2")));
        add(RITUAL_ABSORBS_SPIRIT, s -> s.with(allSounds("ritual", "ritual_absorb_spirit")));
        add(RITUAL_EVOLVES, s -> s.with(allSounds("ritual", "ritual_level_up")));
        add(RITUAL_COMPLETED, s -> s.with(sound("ritual/ritual_level_up_max")));
        add(RITUAL_BEGINNING_AMBIENCE, s -> s.with(sound("ritual/ritual_loop1")));
        add(RITUAL_EVOLUTION_AMBIENCE, s -> s.with(sound("ritual/ritual_loop2")));
        add(COMPLETED_RITUAL_AMBIENCE, s -> s.with(sound("ritual/ritual_loop3")));

        add(UNCANNY_VALLEY, s -> s.with(allSounds("weeping_well", "uncanny_valley")));
        add(VOID_HEARTBEAT, s -> s.with(sound("weeping_well/void_heartbeat")));
        add(SONG_OF_THE_VOID, s -> s.with(allSounds("weeping_well", "song_of_the_void")));
        add(VOID_REJECTION, s -> s.with(sound("weeping_well/void_rejection")));
        add(VOID_TRANSMUTATION, s -> s.with(allSounds("weeping_well", "void_transmutation")));
        add(VOID_EATS_GUNK, s -> s.with(allSounds("curiosities/gear/trinkets/starved", "nom")));

        add(THE_DEEP_BECKONS, s -> s.with(sound("suspicious_sound")));
        add(THE_HEAVENS_SIGN, s -> s.with(sound("heavenly_organs")));

        add(SOULSTONE_ORE, "block/ore/soulstone", b -> b
                .addStepHitFallSounds(allSounds("minecraft:block/basalt", "step", se -> se.pitch(1.2f))));
        add(DEEPSLATE_SOULSTONE_ORE, "block/ore/soulstone/deepslate", b -> b
                .setStepHitFallSoundPaths("block/ore/soulstone").addStepHitFallSounds(allSounds("minecraft:block/basalt", "step")));
        add(BLOCK_OF_SOULSTONE, "block/ore/soulstone", b -> b
                .modifySounds(se -> se.pitch(1.4f)));
        add(BLOCK_OF_RAW_SOULSTONE, "block/ore/soulstone", b -> b
                .modifySounds(se -> se.pitch(0.85f)));

        add(BRILLIANCE_ORE, "block/ore/natural_quartz", b -> b
                .addStepHitFallSounds(allSounds("minecraft:block/basalt", "step", se -> se.pitch(1.2f)))
                .modifySounds(se -> se.pitch(0.8f)));
        add(DEEPSLATE_BRILLIANCE_ORE, "block/ore/natural_quartz/deepslate", b -> b
                .addStepHitFallSounds(allSounds("minecraft:block/basalt", "step", se -> se.pitch(1.4f)))
                .modifySounds(se -> se.pitch(0.8f)));
        add(BLOCK_OF_BRILLIANCE, "block/ore/natural_quartz", b -> b
                .modifySounds(se -> se.pitch(1.4f)));
        add(BLOCK_OF_RAW_BRILLIANCE, "block/ore/natural_quartz", b -> b
                .modifySounds(se -> se.pitch(1.2f)));

        add(BLAZING_QUARTZ_ORE, "block/ore/blazing_quartz", b -> b
                .setStepHitFallSoundPaths("minecraft:block/nether_ore")
                .modifySounds(se -> se.pitch(1.2f)));
        add(BLAZING_QUARTZ_CLUSTER, "block/ore/blazing_quartz", b -> b
                .setStepHitFallSoundPaths("minecraft:block/nether_ore")
                .modifySounds(se -> se.pitch(1.4f)));
        add(BLOCK_OF_BLAZING_QUARTZ, "block/ore/blazing_quartz", b -> b
                .setStepHitFallSoundPaths("minecraft:block/nether_ore")
                .modifySounds(se -> se.pitch(1.6f)));

        add(NATURAL_QUARTZ_ORE, "block/ore/natural_quartz", b -> b
                .addStepHitFallSounds(allSounds("minecraft:block/calcite", "step", se -> se.pitch(1.2f))));
        add(NATURAL_DEEPSLATE_QUARTZ_ORE, "block/ore/natural_quartz/deepslate", b -> b
                .addStepHitFallSounds(allSounds("minecraft:block/calcite", "step", se -> se.pitch(1.4f))));
        add(NATURAL_QUARTZ_CLUSTER, "block/ore/natural_quartz", b -> b
                .modifySounds(se -> se.pitch(1.4f)));
        add(BLOCK_OF_NATURAL_QUARTZ, "block/ore/natural_quartz", b -> b
                .modifySounds(se -> se.pitch(1.6f)));

        add(BLOCK_OF_CTHONIC_GOLD, "block/ore/soulstone/deepslate", b -> b
                .setStepHitFallSoundPaths("block/ore/soulstone")
                .modifySounds(se -> se.pitch(1.6f)));
        add(CTHONIC_GOLD_ORE, "block/ore/soulstone/deepslate", b -> b
                .setStepHitFallSoundPaths("block/ore/soulstone")
                .modifySounds(se -> se.pitch(1.2f)));
        add(CTHONIC_GOLD_CLUSTER, "block/ore/soulstone/deepslate", b -> b
                .setStepHitFallSoundPaths("block/ore/soulstone")
                .modifySounds(se -> se.pitch(1.4f)));
        add(CTHONIC_GOLD_ORE_BREAK_MOTIF, s -> s.with(allSounds("block/ore/cthonic_gold", "break")));
        add(CTHONIC_GOLD_ORE_PLACE_MOTIF, s -> s.with(allSounds("block/ore/cthonic_gold", "break")));
        add(CTHONIC_GOLD_ORE_HIT_MOTIF, s -> s.with(allSounds("block/ore/cthonic_gold", "hit")).with(allSounds("minecraft:block/nether_ore", "step")));

        add(BLOCK_OF_ARCANE_CHARCOAL, "block/gemstone");
        add(BLOCK_OF_SOUL_STAINED_STEEL, "block/soul_stained_steel");
        add(BLOCK_OF_HALLOWED_GOLD, "block/hallowed_gold");

        add(BLOCK_OF_MALIGNANT_LEAD, "block/ore/soulstone", b -> b.modifySounds(se -> se.pitch(0.6f)));
        add(BLOCK_OF_MALIGNANT_PEWTER, "block/soul_stained_steel", b -> b.modifySounds(se -> se.pitch(1.6f)));

        add(RUNEWOOD, "block/runewood");
        add(RUNEWOOD_HANGING_SIGN, "block/runewood/hanging_sign");
        add(RUNEWOOD_LEAVES, "minecraft:block/azalea_leaves");

        add(RUNEWOOD_BUTTON_CLICK_OFF, s -> s.with(sound("block/runewood/button/runewood_button")));
        add(RUNEWOOD_BUTTON_CLICK_ON, s -> s.with(sound("block/runewood/button/runewood_button")));
        add(RUNEWOOD_PRESSURE_PLATE_CLICK_OFF, s -> s.with(sound("block/runewood/button/runewood_button")));
        add(RUNEWOOD_PRESSURE_PLATE_CLICK_ON, s -> s.with(sound("block/runewood/button/runewood_button")));
        add(RUNEWOOD_FENCE_GATE_CLOSE, s -> s.with(sounds("block/runewood/fence_gate/toggle", 3)));
        add(RUNEWOOD_FENCE_GATE_OPEN, s -> s.with(sounds("block/runewood/fence_gate/toggle", 3)));
        add(RUNEWOOD_DOOR_CLOSE, s -> s.with(sounds("block/runewood/door/toggle", 3)));
        add(RUNEWOOD_DOOR_OPEN, s -> s.with(sounds("block/runewood/door/toggle", 3)));
        add(RUNEWOOD_TRAPDOOR_CLOSE, s -> s.with(sounds("block/runewood/trapdoor/toggle", 3)));
        add(RUNEWOOD_TRAPDOOR_OPEN, s -> s.with(sounds("block/runewood/trapdoor/toggle", 3)));

        add(SOULWOOD, "block/runewood");
        add(SOULWOOD_HANGING_SIGN, "block/runewood/hanging_sign");
        add(SOULWOOD_LEAVES, "minecraft:block/azalea_leaves");

        add(SOULWOOD_BUTTON_CLICK_OFF, s -> s.with(sound("block/runewood/button/runewood_button")));
        add(SOULWOOD_BUTTON_CLICK_ON, s -> s.with(sound("block/runewood/button/runewood_button")));
        add(SOULWOOD_PRESSURE_PLATE_CLICK_OFF, s -> s.with(sound("block/runewood/button/runewood_button")));
        add(SOULWOOD_PRESSURE_PLATE_CLICK_ON, s -> s.with(sound("block/runewood/button/runewood_button")));
        add(SOULWOOD_FENCE_GATE_CLOSE, s -> s.with(sounds("block/runewood/fence_gate/toggle", 3)));
        add(SOULWOOD_FENCE_GATE_OPEN, s -> s.with(sounds("block/runewood/fence_gate/toggle", 3)));
        add(SOULWOOD_DOOR_CLOSE, s -> s.with(sounds("block/runewood/door/toggle", 3)));
        add(SOULWOOD_DOOR_OPEN, s -> s.with(sounds("block/runewood/door/toggle", 3)));
        add(SOULWOOD_TRAPDOOR_CLOSE, s -> s.with(sounds("block/runewood/trapdoor/toggle", 3)));
        add(SOULWOOD_TRAPDOOR_OPEN, s -> s.with(sounds("block/runewood/trapdoor/toggle", 3)));

        addArcaneRockSounds(TAINTED_ROCK, "block/arcane_rock", 1.1f);
        addArcaneRockSounds(TAINTED_ROCK_BRICKS, "block/arcane_rock/bricks", 1.1f);
        addArcaneRockSounds(CHISELED_TAINTED_ROCK, "block/arcane_rock/chiseled", 1.1f);

        addArcaneRockSounds(TWISTED_ROCK, "block/arcane_rock", 0.85f);
        addArcaneRockSounds(TWISTED_ROCK_BRICKS, "block/arcane_rock/bricks", 0.85f);
        addArcaneRockSounds(CHISELED_TWISTED_ROCK, "block/arcane_rock/chiseled", 0.85f);

        addDrossStoneSounds(DROSS_STONE, "block/arcane_rock", 1.0f);
        addDrossStoneSounds(DROSS_STONE_BRICKS, "block/arcane_rock/bricks", 1.0f);
        addDrossStoneSounds(CHISELED_DROSS_STONE, "block/arcane_rock/chiseled", 1.0f);

        addArcaneRockSounds(WEEPING_WELL_BRICKS, "block/arcane_rock/artifice", "minecraft:block/heavy_core", 0.7f);
        addArcaneRockSounds(ARCANE_ROCK_ARTIFICE, "block/arcane_rock/artifice", "minecraft:block/heavy_core", 1.0f);

        add(SPIRIT_DIODE, "block/spirit_diode", b -> b.setStepHitFallSoundPaths("minecraft:block/copper_bulb"));
        add(SPIRIT_DIODE_OPEN, s -> s.with(sounds("block/spirit_diode/waveform_open", 4)));
        add(SPIRIT_DIODE_CLOSE, s -> s.with(sounds("block/spirit_diode/waveform_close", 4)));
        add(SPIRIT_DIODE_CONFIGURATION_DRAG, s -> s.with(sounds("block/spirit_diode/waveform_tick", 8)));
        add(SPIRIT_DIODE_CONFIGURATION_CLICK, s -> s.with(sounds("block/spirit_diode/waveform_long_tick", 8)));

        add(WAVECHARGER_CHARGE, s -> s.with(sound("block/spirit_diode/waveform_pulse").volume(0.3f).pitch(1.2f)));
        add(WAVECHARGER_RELEASE, s -> s.with(sound("block/spirit_diode/waveform_pulse").volume(0.3f).pitch(0.8f)));
        add(WAVEBANKER_STORE, s -> s.with(sound("block/spirit_diode/waveform_pulse").volume(0.3f).pitch(1.2)));
        add(WAVEBANKER_RELEASE, s -> s.with(sound("block/spirit_diode/waveform_pulse").volume(0.3f).pitch(0.8)));
        add(WAVEBREAKER_STORE, s -> s.with(sound("block/spirit_diode/waveform_pulse").volume(0.3f).pitch(1.2)));
        add(WAVEBREAKER_RELEASE, s -> s.with(sound("block/spirit_diode/waveform_pulse").volume(0.3f).pitch(0.8)));
        add(WAVEMAKER_PULSE, s -> s.with(sound("block/spirit_diode/waveform_pulse").volume(0.2f).pitch(1.4)));

        add(ETHER, "block/ether", b -> b.setStepHitFallSoundNames("cloth").setStepHitFallSoundPaths("minecraft:step"));
        add(VARNISHED_TERRACOTTA, "block/terracotta");

        add(BLIGHTED_EARTH, "minecraft:block/nylium");
        add(BLIGHTED_FOLIAGE, "minecraft:block/netherwart", b -> b.setStepHitFallSoundPaths("minecraft:block/nether_sprouts"));
        add(SCARSTONE, "block/scarstone");
        add(STRANGE_CRYSTAL, "block/strange_crystal");

        add(MAJOR_BLIGHT_MOTIF, s -> s.with(sounds("block/blight/blight_motif", 6)));
        add(MINOR_BLIGHT_MOTIF, s -> s.with(sounds("block/blight/minor_blight_motif", 6)));

        add(WRITHING_FLESH, "minecraft:dig", b -> b
                .setBreakPlaceSoundNames("coral")
                .setStepHitFallSoundNames("coral")
                .setStepHitFallSoundPaths("minecraft:step")
                .modifySounds(se -> se.pitch(1.25f)));
        add(FLESH, "minecraft:dig", b -> b
                .setBreakPlaceSoundNames("coral")
                .setStepHitFallSoundNames("coral")
                .setStepHitFallSoundPaths("minecraft:step"));

        add(ARCANE_ELEGY, s -> s.with(sound("arcane_elegy")));
        add(AESTHETICA, s -> s.with(sound("aesthetica")));
    }

    public void addArcaneRockSounds(MalumSoundType soundType, String path, float pitch) {
        addArcaneRockSounds(soundType, path, "minecraft:block/basalt", pitch);
    }

    public void addDrossStoneSounds(MalumSoundType soundType, String path, float pitch) {
        addArcaneRockSounds(soundType, path, "minecraft:block/dripstone", pitch);
    }

    public void addArcaneRockSounds(MalumSoundType soundType, String path, String fillerStepPath, float pitch) {
        blueprint(path, b -> b
                .modifyStepHitFallSounds(se -> se.weight(3))
                .modifySounds(se -> se.pitch(pitch))
                .addStepHitFallSounds(allSounds(fillerStepPath, "step", se -> se.weight(2).pitch(pitch)))
        ).add(soundType);
    }
}