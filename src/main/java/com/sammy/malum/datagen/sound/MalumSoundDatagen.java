package com.sammy.malum.datagen.sound;

import com.sammy.malum.MalumMod;
import com.sammy.malum.registry.common.sound.*;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import team.lodestar.lodestone.modules.datagen.providers.sound.LodestoneBlockSoundEventSystem;

import java.lang.reflect.*;
import java.util.*;
import java.util.function.*;

import static com.sammy.malum.registry.common.sound.MalumSoundEvents.*;


public class MalumSoundDatagen extends LodestoneBlockSoundEventSystem {

    public List<MalumSoundDatagenWrapper> partials = List.of(
            new MalumBlockSoundDatagen(this), new MalumGearSoundDatagen(this), new MalumMobSoundDatagen(this)
    );

    public MalumSoundDatagen(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
        super(packOutput, MalumMod.MALUM, existingFileHelper);
    }

    @Override
    public void registerSounds() {
        for (MalumSoundDatagenWrapper partial : partials) {
            partial.registerSounds();
        }

        add(ARCANA_CODEX_OPEN, s -> s.with(allSounds("codex/book_open")));
        add(ARCANA_CODEX_CLOSE, s -> s.with(allSounds("codex/book_close")));
        add(ARCANA_ENTRY_OPEN, s -> s.with(allSounds("codex/book_entry_open")));
        add(ARCANA_ENTRY_CLOSE, s -> s.with(allSounds("codex/book_entry_close")));
        add(ARCANA_ENTRY_HOVER, s -> s.with(allSounds("codex/book_hover")));
        add(ARCANA_ENTRY_UNHOVER, s -> s.with(allSounds("codex/book_unhover")));
        add(ARCANA_PAGE_FLIP, s -> s.with(allSounds("codex/book_page_turn")));
        add(ARCANA_SUBENTRY_OPEN, s -> s.with(allSounds("codex/book_subentry_open")));
        add(ARCANA_SUBENTRY_CLOSE, s -> s.with(allSounds("codex/book_subentry_close")));
        add(ARCANA_SWEETENER_NORMAL, s -> s.with(allSounds("codex/book_swtnr_normal")));
        add(ARCANA_SWEETENER_EVIL, s -> s.with(allSounds("codex/book_swtnr_evil")));
        add(ARCANA_TRANSITION_NORMAL, s -> s.with(allSounds("codex/book_transition_normal")));
        add(ARCANA_TRANSITION_EVIL, s -> s.with(allSounds("codex/book_transition_evil")));

        add(ARCANE_WHISPERS, s -> s.with(allSounds("curiosities/soul/spirit_idle")));
        add(SPIRIT_PICKUP, s -> s.with(allSounds("curiosities/soul/spirit_pickup")));
        add(SOUL_SHATTER, s -> s.with(allSounds("curiosities/soul/soul_shatter")));

        add(PEDESTAL_ITEM_INSERT, s -> s.with(allSounds("block_interaction/pedestal_item_insert")));
        add(PEDESTAL_ITEM_PICKUP, s -> s.with(allSounds("block_interaction/pedestal_item_remove")));
        add(PEDESTAL_SPIRIT_INSERT, s -> s.with(allSounds("block_interaction/pedestal_spirit_insert")));
        add(PEDESTAL_SPIRIT_PICKUP, s -> s.with(allSounds("block_interaction/pedestal_spirit_remove")));

        add(CLOTH_TRINKET_EQUIP, s -> s.with(allSounds("equip_sounds/cloth/equip_cloth")));
        add(ORNATE_TRINKET_EQUIP, s -> s.with(allSounds("equip_sounds/ornate/equip_ornate")));
        add(GILDED_TRINKET_EQUIP, s -> s.with(allSounds("equip_sounds/gilded/equip_gilded")));
        add(ALCHEMICAL_TRINKET_EQUIP, s -> s.with(allSounds("equip_sounds/alchemical/equip_alchemical")));
        add(ROTTEN_TRINKET_EQUIP, s -> s.with(allSounds("equip_sounds/rotten/equip_rotten")));
        add(METALLIC_TRINKET_EQUIP, s -> s.with(allSounds("equip_sounds/metallic/equip_metallic")));
        add(VOID_TRINKET_EQUIP, s -> s.with(allSounds("equip_sounds/void/equip_void")));

        add(RUNE_EQUIP, s -> s.with(allSounds("equip_sounds/rune/equip_rune_stone")));
        add(TOTEMIC_RUNE_EQUIP, s -> s.with(allSounds("equip_sounds/rune/equip_rune_wood")));
        add(VOID_RUNE_EQUIP, s -> s.with(allSounds("equip_sounds/rune/equip_rune_void")));

        add(SPIRIT_MOTE_CREATED, s -> s.with(allSounds("curiosities/gear/lamplighters_tongs/created")));

        add(RAVENOUS_POUCH_INSERT, s -> s.with(allSounds("curiosities/gear/ravenous_pouch/insert")));

        add(TUNING_FORK_TINKER, s -> s.with(allSounds("curiosities/augments/tinker")));
        add(CRUCIBLE_AUGMENT_APPLY, s -> s.with(allSounds("curiosities/augments/augment_insert")));
        add(CRUCIBLE_AUGMENT_REMOVE, s -> s.with(allSounds("curiosities/augments/augment_remove")));
        add(WARPING_ENGINE_REVERBERATES, s -> s.with(allSounds("curiosities/augments/warping_engine_reverberates")));
        add(SHIELDING_APPARATUS_SHIELDS, s -> s.with(allSounds("curiosities/augments/shielding_apparatus_shields")));
        add(SUSPICIOUS_DEVICE_DETONATES, s -> s.with(allSounds("curiosities/augments/device_detonate")));
        add(SUSPICIOUS_DEVICE_DETONATES_AGAIN, s -> s.with(allSounds("curiosities/augments/device_secondary_detonate")));
        add(SUSPICIOUS_DEVICE_DETONATES_ODDLY, s -> s.with(sound("curiosities/augments/legalize_nuclear_bombs")));

        add(AVARICE_COLLECT, s -> s.with(allSounds("curiosities/effects/avarice/collect")));
        add(AVARICE_FORTUNE, s -> s.with(allSounds("curiosities/effects/avarice/fortune")));

        add(SOUL_WARD_HIT, s -> s.with(allSounds("curiosities/effects/soul_ward/block")));
        add(SOUL_WARD_GROW, s -> s.with(allSounds("curiosities/effects/soul_ward/grow")));
        add(SOUL_WARD_FULLY_CHARGED, s -> s.with(allSounds("curiosities/effects/soul_ward/full")));
        add(SOUL_WARD_DEPLETE, s -> s.with(allSounds("curiosities/effects/soul_ward/break")));

        add(MALIGNANT_AEGIS_HIT, s -> s.with(allSounds("curiosities/effects/malignant_aegis/block")));
        add(MALIGNANT_AEGIS_GROW, s -> s.with(allSounds("curiosities/effects/malignant_aegis/grow")));
        add(MALIGNANT_AEGIS_FULLY_CHARGED, s -> s.with(allSounds("curiosities/effects/malignant_aegis/full")));
        add(MALIGNANT_AEGIS_DEPLETE, s -> s.with(allSounds("curiosities/effects/malignant_aegis/break")));

        add(SPELL_CHARGE_GROW, s -> s.with(allSounds("curiosities/effects/spell_charge/charge")));
        add(SPELL_CHARGE_FULL, s -> s.with(allSounds("curiosities/effects/spell_charge/charge_full")));

        add(OAKEN_MIGHT_HIT, s -> s.with(allSounds("curiosities/effects/potion/oaken_might")));

        add(WARLOCK_BLAST, s -> s.with(allSounds("curiosities/geas/warlock_impact")));
        add(REAPER_CUT, s -> s.with(allSounds("curiosities/geas/reaper_impact")));
        add(BERSERKER_WRATH, s -> s.with(allSounds("curiosities/geas/berserker_impact")));
        add(PATIENT_DROWNING, s -> s.with(allSounds("minecraft:entity/player/hurt/drown")));
        add(DESPERATE_NEED_CUT, s -> s.with(allSounds("curiosities/geas/reaper_impact")));
        add(DESPERATE_NEED_WITHDRAWAL, s -> s.with(allSounds("curiosities/geas/reaper_impact")));
        add(PROSPECTOR_BURN, s -> s.with(sound("minecraft:mob/ghast/fireball4")));
        add(COMBUSTION_WHIPLASH, s -> s.with(sound("minecraft:random/fizz")));
        add(WYRD_RECONSTRUCTION, s -> s.with(allSounds("curiosities/geas/wyrd_reconstruction")));
        add(INVERTED_HEART_IMPACT, s -> s.with(allSounds("curiosities/geas/soulwashing_impact")));

        add(ALTAR_LOOP, s -> s.with(sound("altar/altar_loop")));
        add(ALTAR_CRAFT, s -> s.with(allSounds("altar/altar_craft")));
        add(ALTAR_CONSUME, s -> s.with(allSounds("altar/altar_consume")));
        add(ALTAR_SPEED_UP, s -> s.with(allSounds("altar/altar_speedup")));

        add(CRUCIBLE_LOOP, s -> s.with(sound("crucible/crucible_loop")));
        add(CRUCIBLE_CRAFT, s -> s.with(allSounds("crucible/crucible_craft")));
        add(IMPETUS_CRACK, s -> s.with(allSounds("crucible/impetus_crack")));

        add(REPAIR_PYLON_LOOP, s -> s.with(sound("repair_pylon/repair_pylon_loop")));
        add(REPAIR_PYLON_REPAIR_START, s -> s.with(allSounds("repair_pylon/repair_pylon_send")));
        add(REPAIR_PYLON_REPAIR_FINISH, s -> s.with(allSounds("repair_pylon/repair_pylon_fix")));

        add(BRAZIER_LOOP, s -> s.with(sound("brazier/brazier_loop")));
        add(BRAZIER_START, s -> s.with(allSounds("brazier/brazier_start")));
        add(BRAZIER_FINISH, s -> s.with(allSounds("brazier/brazier_end")));
        add(BRAZIER_SACRIFICE, s -> s.with(allSounds("brazier/brazier_sacrifice")));

        add(RUNIC_WORKBENCH_SHAPES_RUNE_STONE, s -> s.with(allSounds("runic_workbench/craft_rune_stone")));
        add(RUNIC_WORKBENCH_SHAPES_RUNE_WOODEN, s -> s.with(allSounds("runic_workbench/craft_rune_wood")));
        add(RUNIC_WORKBENCH_SHAPES_RUNE_VOID, s -> s.with(allSounds("runic_workbench/craft_rune_void")));
        add(RUNIC_WORKBENCH_SHAPES_RUNE_GENERIC, s -> s.with(allSounds("runic_workbench/craft_rune_generic")));

        add(WEAVERS_WORKBENCH_CRAFT, s -> s.with(allSounds("runic_workbench/craft_rune_generic")));

        add(TOTEM_LOOP, s -> s.with(sound("totem/totem_loop")));
        add(TOTEM_CHARGE, s -> s.with(allSounds("totem/totem_charge")));
        add(TOTEM_ACTIVATED, s -> s.with(sound("totem/totem_activate")));
        add(TOTEM_CANCELLED, s -> s.with(sound("totem/totem_cancel")));
        add(TOTEM_ENGRAVE, s -> s.with(allSounds("totem/totem_engrave")));

        add(SPARK_FORMED, s -> s.with(allSounds("totem/spark/spark_create")));
        add(SPARK_IMPACT, s -> s.with(allSounds("totem/spark/spark_hit")));
        add(SPARK_POTION_IMPACT, s -> s.with(allSounds("totem/spark/spark_potion_hit")));
        add(SPARK_UNWOVEN, s -> s.with(allSounds("totem/spark/spark_create", se -> se.pitch(0.5f))));
        add(SPARK_DIRECTED, s -> s.with(allSounds("totem/spark/spark_direct")));

        add(TOTEM_BLOCK_GRAVITY, s -> s.with(allSounds("minecraft:mob/phantom/flap")));
        add(TOTEM_BLOCK_GROW, s -> s.with(allSounds("minecraft:item/bonemeal/bonemeal")));
        add(TOTEM_BLOCK_SAP, s -> s.with(allSounds("minecraft:block/pointed_dripstone/drip_water_cauldron")));

        add(RITUAL_BEGINS, s -> s.with(sound("ritual/ritual_start")));
        add(RITUAL_ABSORBS_ITEM, s -> s.with(allSounds("ritual/ritual_absorb_item")));
        add(RITUAL_FORMS, s -> s.with(sound("ritual/ritual_stage2")));
        add(RITUAL_ABSORBS_SPIRIT, s -> s.with(allSounds("ritual/ritual_absorb_spirit")));
        add(RITUAL_EVOLVES, s -> s.with(allSounds("ritual/ritual_level_up")));
        add(RITUAL_COMPLETED, s -> s.with(sound("ritual/ritual_level_up_max")));
        add(RITUAL_BEGINNING_AMBIENCE, s -> s.with(sound("ritual/ritual_loop1")));
        add(RITUAL_EVOLUTION_AMBIENCE, s -> s.with(sound("ritual/ritual_loop2")));
        add(COMPLETED_RITUAL_AMBIENCE, s -> s.with(sound("ritual/ritual_loop3")));

        add(UNCANNY_VALLEY, s -> s.with(allSounds("weeping_well/uncanny_valley")));
        add(VOID_HEARTBEAT, s -> s.with(sound("weeping_well/void_heartbeat")));
        add(SONG_OF_THE_VOID, s -> s.with(allSounds("weeping_well/song_of_the_void")));
        add(VOID_REJECTION, s -> s.with(sound("weeping_well/void_rejection")));
        add(VOID_TRANSMUTATION, s -> s.with(allSounds("weeping_well/void_transmutation")));
        add(VOID_EATS_GUNK, s -> s.with(allSounds("curiosities/gear/trinkets/starved/nom")));

        add(THE_DEEP_BECKONS, s -> s.with(sound("suspicious_sound")));
        add(THE_HEAVENS_SIGN, s -> s.with(sound("heavenly_organs")));

        add(ARCANE_ELEGY, s -> s.with(sound("arcane_elegy")));
        add(AESTHETICA, s -> s.with(sound("aesthetica")));
    }
}