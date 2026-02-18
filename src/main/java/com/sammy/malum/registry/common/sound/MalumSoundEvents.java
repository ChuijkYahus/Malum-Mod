package com.sammy.malum.registry.common.sound;

import com.sammy.malum.MalumMod;
import com.sammy.malum.common.sound.BlightedSoundType;
import com.sammy.malum.common.sound.QuartzClusterSoundType;
import com.sammy.malum.common.sound.CthonicGoldSoundType;
import net.minecraft.core.registries.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.JukeboxSong;
import net.minecraft.world.level.block.SoundType;
import net.neoforged.neoforge.common.util.*;
import net.neoforged.neoforge.registries.*;

import static com.sammy.malum.MalumMod.MALUM;

public class MalumSoundEvents {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, MALUM);

    public static final DeferredHolder<SoundEvent, SoundEvent> ARCANA_CODEX_OPEN = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("arcana_codex_opened")));
    public static final DeferredHolder<SoundEvent, SoundEvent> ARCANA_CODEX_CLOSE = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("arcana_codex_closed")));
    public static final DeferredHolder<SoundEvent, SoundEvent> ARCANA_ENTRY_OPEN = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("arcana_entry_opened")));
    public static final DeferredHolder<SoundEvent, SoundEvent> ARCANA_ENTRY_CLOSE = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("arcana_entry_closed")));
    public static final DeferredHolder<SoundEvent, SoundEvent> ARCANA_ENTRY_HOVER = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("arcana_entry_hovered")));
    public static final DeferredHolder<SoundEvent, SoundEvent> ARCANA_ENTRY_UNHOVER = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("arcana_entry_unhovered")));
    public static final DeferredHolder<SoundEvent, SoundEvent> ARCANA_PAGE_FLIP = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("arcana_page_flipped")));
    public static final DeferredHolder<SoundEvent, SoundEvent> ARCANA_SUBENTRY_OPEN = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("arcana_subentry_opened")));
    public static final DeferredHolder<SoundEvent, SoundEvent> ARCANA_SUBENTRY_CLOSE = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("arcana_subentry_closed")));
    public static final DeferredHolder<SoundEvent, SoundEvent> ARCANA_SWEETENER_NORMAL = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("arcana_sweetener_normal")));
    public static final DeferredHolder<SoundEvent, SoundEvent> ARCANA_SWEETENER_EVIL = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("arcana_sweetener_evil")));
    public static final DeferredHolder<SoundEvent, SoundEvent> ARCANA_TRANSITION_NORMAL = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("arcana_transition_normal")));
    public static final DeferredHolder<SoundEvent, SoundEvent> ARCANA_TRANSITION_EVIL = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("arcana_transition_evil")));

    public static final DeferredHolder<SoundEvent, SoundEvent> PEDESTAL_ITEM_INSERT = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("pedestal_item_inserted")));
    public static final DeferredHolder<SoundEvent, SoundEvent> PEDESTAL_ITEM_PICKUP = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("pedestal_item_picked_up")));
    public static final DeferredHolder<SoundEvent, SoundEvent> PEDESTAL_SPIRIT_INSERT = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("pedestal_spirit_inserted")));
    public static final DeferredHolder<SoundEvent, SoundEvent> PEDESTAL_SPIRIT_PICKUP = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("pedestal_spirit_picked_up")));

    public static final DeferredHolder<SoundEvent, SoundEvent> CLOTH_TRINKET_EQUIP = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("cloth_trinket_equipped")));
    public static final DeferredHolder<SoundEvent, SoundEvent> ORNATE_TRINKET_EQUIP = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("ornate_trinket_equipped")));
    public static final DeferredHolder<SoundEvent, SoundEvent> GILDED_TRINKET_EQUIP = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("gilded_trinket_equipped")));
    public static final DeferredHolder<SoundEvent, SoundEvent> ALCHEMICAL_TRINKET_EQUIP = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("alchemical_trinket_equipped")));
    public static final DeferredHolder<SoundEvent, SoundEvent> ROTTEN_TRINKET_EQUIP = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("rotten_trinket_equipped")));
    public static final DeferredHolder<SoundEvent, SoundEvent> METALLIC_TRINKET_EQUIP = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("metallic_trinket_equipped")));
    public static final DeferredHolder<SoundEvent, SoundEvent> VOID_TRINKET_EQUIP = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("void_trinket_equipped")));

    public static final DeferredHolder<SoundEvent, SoundEvent> RUNE_EQUIP = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("rune_equipped")));
    public static final DeferredHolder<SoundEvent, SoundEvent> TOTEMIC_RUNE_EQUIP = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("totemic_rune_equipped")));
    public static final DeferredHolder<SoundEvent, SoundEvent> VOID_RUNE_EQUIP = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("void_rune_equipped")));

    public static final DeferredHolder<SoundEvent, SoundEvent> HUNGRY_BELT_FEEDS = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("hungry_belt_feeds")));
    public static final DeferredHolder<SoundEvent, SoundEvent> VORACIOUS_RING_FEEDS = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("voracious_ring_feeds")));
    public static final DeferredHolder<SoundEvent, SoundEvent> GRUESOME_RING_FEEDS = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("gruesome_ring_feeds")));
    public static final DeferredHolder<SoundEvent, SoundEvent> FLESH_RING_ABSORBS = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("flesh_ring_absorbs")));
    public static final DeferredHolder<SoundEvent, SoundEvent> ECHOING_RING_ABSORBS = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("echoing_ring_absorbs")));

    public static final DeferredHolder<SoundEvent, SoundEvent> CONCENTRATED_GLUTTONY_DRINK = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("concentrated_gluttony_drink")));
    public static final DeferredHolder<SoundEvent, SoundEvent> SPIRIT_MOTE_CREATED = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("spirit_mote_created")));

    public static final DeferredHolder<SoundEvent, SoundEvent> RAVENOUS_POUCH_INSERT = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("ravenous_pouch_eats")));

    public static final DeferredHolder<SoundEvent, SoundEvent> TUNING_FORK_TINKER = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("tuning_fork_tinkers")));
    public static final DeferredHolder<SoundEvent, SoundEvent> CRUCIBLE_AUGMENT_APPLY = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("crucible_augment_applied")));
    public static final DeferredHolder<SoundEvent, SoundEvent> CRUCIBLE_AUGMENT_REMOVE = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("crucible_augment_removed")));
    public static final DeferredHolder<SoundEvent, SoundEvent> WARPING_ENGINE_REVERBERATES = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("warping_engine_reverberates")));
    public static final DeferredHolder<SoundEvent, SoundEvent> SHIELDING_APPARATUS_SHIELDS = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("shielding_apparatus_shields")));
    public static final DeferredHolder<SoundEvent, SoundEvent> SUSPICIOUS_DEVICE_DETONATES = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("suspicious_device_detonates")));
    public static final DeferredHolder<SoundEvent, SoundEvent> SUSPICIOUS_DEVICE_DETONATES_AGAIN = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("suspicious_device_detonates_again")));
    public static final DeferredHolder<SoundEvent, SoundEvent> SWAG_MESSIAH = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("legalize_nuclear_bombs")));

    public static final DeferredHolder<SoundEvent, SoundEvent> ARCANE_WHISPERS = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("arcane_whispers")));
    public static final DeferredHolder<SoundEvent, SoundEvent> SPIRIT_PICKUP = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("spirit_picked_up")));
    public static final DeferredHolder<SoundEvent, SoundEvent> SOUL_SHATTER = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("a_soul_shatters")));

    public static final DeferredHolder<SoundEvent, SoundEvent> BLIGHT_PROPAGATION = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("blight_propagates")));
    public static final DeferredHolder<SoundEvent, SoundEvent> SCARSTONE_PROPAGATION = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("scarstone_monument_forms")));

    public static final DeferredHolder<SoundEvent, SoundEvent> AVARICE_COLLECT = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("avarice_collected")));
    public static final DeferredHolder<SoundEvent, SoundEvent> AVARICE_FORTUNE = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("avarice_grants_fortune")));


    public static final DeferredHolder<SoundEvent, SoundEvent> SOUL_WARD_HIT = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("soul_ward_absorbs_damage")));
    public static final DeferredHolder<SoundEvent, SoundEvent> SOUL_WARD_GROW = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("soul_ward_grows")));
    public static final DeferredHolder<SoundEvent, SoundEvent> SOUL_WARD_FULLY_CHARGED = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("soul_ward_charge_completed")));
    public static final DeferredHolder<SoundEvent, SoundEvent> SOUL_WARD_DEPLETE = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("soul_ward_disintegrates")));

    public static final DeferredHolder<SoundEvent, SoundEvent> MALIGNANT_AEGIS_HIT = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("malignant_aegis_nullifies_damage")));
    public static final DeferredHolder<SoundEvent, SoundEvent> MALIGNANT_AEGIS_GROW = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("malignant_aegis_grows")));
    public static final DeferredHolder<SoundEvent, SoundEvent> MALIGNANT_AEGIS_FULLY_CHARGED = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("malignant_aegis_charge_completed")));
    public static final DeferredHolder<SoundEvent, SoundEvent> MALIGNANT_AEGIS_DEPLETE = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("malignant_disintegrates")));

    public static final DeferredHolder<SoundEvent, SoundEvent> SPELL_CHARGE_GROW = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("spell_charge_grow")));
    public static final DeferredHolder<SoundEvent, SoundEvent> SPELL_CHARGE_FULL = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("spell_charge_full")));

    public static final DeferredHolder<SoundEvent, SoundEvent> SCYTHE_SWEEP = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("scythe_sweeps")));
    public static final DeferredHolder<SoundEvent, SoundEvent> SCYTHE_CUT = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("scythe_cuts")));
    public static final DeferredHolder<SoundEvent, SoundEvent> EDGE_OF_DELIVERANCE_SWEEP = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("edge_of_deliverance_sweeps")));
    public static final DeferredHolder<SoundEvent, SoundEvent> EDGE_OF_DELIVERANCE_CUT = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("edge_of_deliverance_cuts")));

    public static final DeferredHolder<SoundEvent, SoundEvent> SCYTHE_ASCENSION = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("scythe_user_ascends")));
    public static final DeferredHolder<SoundEvent, SoundEvent> SCYTHE_ASCENSION_LAUNCH = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("scythe_target_is_launched")));

    public static final DeferredHolder<SoundEvent, SoundEvent> SCYTHE_THROW = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("scythe_thrown")));
    public static final DeferredHolder<SoundEvent, SoundEvent> SCYTHE_SPINS = register(SoundEvent.createFixedRangeEvent(MalumMod.malumPath("scythe_spins_happily"), 32f));
    public static final DeferredHolder<SoundEvent, SoundEvent> SCYTHE_CATCH = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("scythe_caught")));

    public static final DeferredHolder<SoundEvent, SoundEvent> HIDDEN_BLADE_CHARGED = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("hidden_blade_charged")));
    public static final DeferredHolder<SoundEvent, SoundEvent> HIDDEN_BLADE_PRIMED = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("hidden_blade_primed")));
    public static final DeferredHolder<SoundEvent, SoundEvent> HIDDEN_BLADE_DISRUPTED = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("hidden_blade_disrupted")));
    public static final DeferredHolder<SoundEvent, SoundEvent> HIDDEN_BLADE_UNLEASHED = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("hidden_blade_unleashed")));

    public static final DeferredHolder<SoundEvent, SoundEvent> SPELLWEAVING_TOOL_PRIME = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("spellweaving_tool_primed")));
    public static final DeferredHolder<SoundEvent, SoundEvent> SPELLWEAVING_TOOL_DAMPEN = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("spellweaving_tool_dampened")));
    public static final DeferredHolder<SoundEvent, SoundEvent> SPELLWOVEN_SPRITE_SPAWN = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("spellwoven_sprite_spawns")));
    public static final DeferredHolder<SoundEvent, SoundEvent> SPELLWOVEN_SPRITE_HARVESTS = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("spellwoven_sprite_harvests")));
    public static final DeferredHolder<SoundEvent, SoundEvent> SPELLWOVEN_SPRITE_RETURNS = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("spellwoven_sprite_returns")));

    public static final DeferredHolder<SoundEvent, SoundEvent> RAVENOUS_SCYTHE_EATS = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("ravenous_scythe_eats")));
    public static final DeferredHolder<SoundEvent, SoundEvent> GLUTTONOUS_BLUDGEON_SPROUTS = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("gluttonous_bludgeon_sprouts")));

    public static final DeferredHolder<SoundEvent, SoundEvent> TYRVING_SLASH = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("tyrving_slashes_twice")));
    public static final DeferredHolder<SoundEvent, SoundEvent> WEIGHT_OF_WORLDS_CUT = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("weight_of_worlds_cuts")));

    public static final DeferredHolder<SoundEvent, SoundEvent> SUNDERING_ANCHOR_SWING = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("sundering_anchor_cuts")));
    public static final DeferredHolder<SoundEvent, SoundEvent> SUNDERING_ANCHOR_EXTRA_SWING = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("sundering_anchor_twists_gracefully")));
    public static final DeferredHolder<SoundEvent, SoundEvent> SUNDERING_ANCHOR_PROJECTILE_SWING = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("sundering_anchor_lashes_out_ecstatically")));
    public static final DeferredHolder<SoundEvent, SoundEvent> SUNDERING_ANCHOR_THROW = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("sundering_anchor_leaps_out_eagerly")));
    public static final DeferredHolder<SoundEvent, SoundEvent> SUNDERING_ANCHOR_CATCH = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("sundering_anchor_returns_with_euphoria")));

    public static final DeferredHolder<SoundEvent, SoundEvent> OAKEN_MIGHT_HIT = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("oaken_might_takes_effect")));

    public static final DeferredHolder<SoundEvent, SoundEvent> WARLOCK_BLAST = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("wicked_energy_bursts_forward")));
    public static final DeferredHolder<SoundEvent, SoundEvent> REAPER_CUT = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("reaper_scythe_slashes")));
    public static final DeferredHolder<SoundEvent, SoundEvent> BERSERKER_WRATH = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("wrathful_energy_surges")));
    public static final DeferredHolder<SoundEvent, SoundEvent> PATIENT_DROWNING = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("player_patiently_drowns")));
    public static final DeferredHolder<SoundEvent, SoundEvent> DESPERATE_NEED_CUT = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("the_rot_spreads")));
    public static final DeferredHolder<SoundEvent, SoundEvent> DESPERATE_NEED_WITHDRAWAL = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("the_rot_consumes")));
    public static final DeferredHolder<SoundEvent, SoundEvent> PROSPECTOR_BURN = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("player_burns_from_greed")));
    public static final DeferredHolder<SoundEvent, SoundEvent> COMBUSTION_WHIPLASH = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("player_experiences_combustion_whiplash")));
    public static final DeferredHolder<SoundEvent, SoundEvent> WYRD_RECONSTRUCTION = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("wyrd_reconstruction_reconstructs_body")));
    public static final DeferredHolder<SoundEvent, SoundEvent> INVERTED_HEART_IMPACT = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("the_inverted_heart_shrieks")));

    public static final DeferredHolder<SoundEvent, SoundEvent> CATALYST_LOBBER_UNLOCKED = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("catalyst_lobber_unlocked")));
    public static final DeferredHolder<SoundEvent, SoundEvent> CATALYST_LOBBER_LOCKED = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("catalyst_lobber_locked")));
    public static final DeferredHolder<SoundEvent, SoundEvent> CATALYST_LOBBER_PRIMED = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("catalyst_lobber_primed")));
    public static final DeferredHolder<SoundEvent, SoundEvent> CATALYST_LOBBER_FIRED = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("catalyst_lobber_fired")));

    public static final DeferredHolder<SoundEvent, SoundEvent> STAFF_FIRES = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("staff_fires")));
    public static final DeferredHolder<SoundEvent, SoundEvent> STAFF_POWERS_UP = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("staff_powers_up")));
    public static final DeferredHolder<SoundEvent, SoundEvent> STAFF_SIZZLES_OUT = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("staff_sizzles_out")));
    public static final DeferredHolder<SoundEvent, SoundEvent> STAFF_CHARGED = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("staff_charged")));
    public static final DeferredHolder<SoundEvent, SoundEvent> STAFF_STRIKES = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("staff_strikes")));

    public static final DeferredHolder<SoundEvent, SoundEvent> DRAINING_MOTIF = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("the_sound_of_silence")));
    public static final DeferredHolder<SoundEvent, SoundEvent> MALIGNANT_METAL_MOTIF = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("malignant_deliverance_triggers")));
    public static final DeferredHolder<SoundEvent, SoundEvent> MALIGNANT_METAL_COMBO = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("malignant_deliverance_strikes_again")));

    public static final DeferredHolder<SoundEvent, SoundEvent> WORLDSOUL_MOTIF_LIGHT_IMPACT = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("the_worldsoul_shifts_slightly")));
    public static final DeferredHolder<SoundEvent, SoundEvent> WORLDSOUL_MOTIF_HEAVY_IMPACT = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("the_worldsoul_shifts_erratically")));
    public static final DeferredHolder<SoundEvent, SoundEvent> WORLDSOUL_MOTIF_REVERB = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("the_worldsoul_reverberates")));

    public static final DeferredHolder<SoundEvent, SoundEvent> ALTAR_LOOP = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("spirit_altar_infuses")));
    public static final DeferredHolder<SoundEvent, SoundEvent> ALTAR_CRAFT = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("spirit_altar_completes_infusion")));
    public static final DeferredHolder<SoundEvent, SoundEvent> ALTAR_CONSUME = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("spirit_altar_absorbs_item")));
    public static final DeferredHolder<SoundEvent, SoundEvent> ALTAR_SPEED_UP = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("spirit_altar_speeds_up")));

    public static final DeferredHolder<SoundEvent, SoundEvent> CRUCIBLE_LOOP = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("spirit_crucible_focuses")));
    public static final DeferredHolder<SoundEvent, SoundEvent> CRUCIBLE_CRAFT = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("spirit_crucible_completes_focusing")));
    public static final DeferredHolder<SoundEvent, SoundEvent> IMPETUS_CRACK = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("impetus_takes_damage")));

    public static final DeferredHolder<SoundEvent, SoundEvent> REPAIR_PYLON_LOOP = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("repair_pylon_eagerly_hums")));
    public static final DeferredHolder<SoundEvent, SoundEvent> REPAIR_PYLON_REPAIR_START = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("repair_pylon_begins_repairing")));
    public static final DeferredHolder<SoundEvent, SoundEvent> REPAIR_PYLON_REPAIR_FINISH = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("repair_pylon_finishes_repairing")));

    public static final DeferredHolder<SoundEvent, SoundEvent> BRAZIER_LOOP = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("soulbinding_brazier_fuses")));
    public static final DeferredHolder<SoundEvent, SoundEvent> BRAZIER_START = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("soulbinding_sequence_initiated")));
    public static final DeferredHolder<SoundEvent, SoundEvent> BRAZIER_FINISH = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("soulbinding_sequence_completed")));
    public static final DeferredHolder<SoundEvent, SoundEvent> BRAZIER_SACRIFICE = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("soulbinding_brazier_accepts_offering")));

    public static final DeferredHolder<SoundEvent, SoundEvent> RUNIC_WORKBENCH_SHAPES_RUNE_STONE = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("runic_workbench_shapes_tainted_rune")));
    public static final DeferredHolder<SoundEvent, SoundEvent> RUNIC_WORKBENCH_SHAPES_RUNE_WOODEN = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("runic_workbench_shapes_wooden_rune")));
    public static final DeferredHolder<SoundEvent, SoundEvent> RUNIC_WORKBENCH_SHAPES_RUNE_VOID = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("runic_workbench_shapes_void_rune")));

    public static final DeferredHolder<SoundEvent, SoundEvent> RUNIC_WORKBENCH_SHAPES_RUNE_GENERIC = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("runic_workbench_shapes_something")));

    public static final DeferredHolder<SoundEvent, SoundEvent> WEAVERS_WORKBENCH_CRAFT = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("weavers_workbench_weaves")));

    public static final DeferredHolder<SoundEvent, SoundEvent> TOTEM_LOOP = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("totem_hums")));
    public static final DeferredHolder<SoundEvent, SoundEvent> TOTEM_CHARGE = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("totem_charges")));
    public static final DeferredHolder<SoundEvent, SoundEvent> TOTEM_ACTIVATED = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("totemic_rite_activated")));
    public static final DeferredHolder<SoundEvent, SoundEvent> TOTEM_CANCELLED = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("totemic_rite_cancelled")));
    public static final DeferredHolder<SoundEvent, SoundEvent> TOTEM_ENGRAVE = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("spirit_engraved")));

    public static final DeferredHolder<SoundEvent, SoundEvent> SPARK_FORMED = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("spark_formed")));
    public static final DeferredHolder<SoundEvent, SoundEvent> SPARK_IMPACT = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("spark_bestows_effect")));
    public static final DeferredHolder<SoundEvent, SoundEvent> SPARK_POTION_IMPACT = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("spark_bestows_potion_effect")));
    public static final DeferredHolder<SoundEvent, SoundEvent> SPARK_UNWOVEN = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("spark_unwoven")));
    public static final DeferredHolder<SoundEvent, SoundEvent> SPARK_DIRECTED = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("spark_directed")));

    public static final DeferredHolder<SoundEvent, SoundEvent> TOTEM_BLOCK_GRAVITY = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("totemic_weight")));
    public static final DeferredHolder<SoundEvent, SoundEvent> TOTEM_BLOCK_GROW = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("totemic_growth")));
    public static final DeferredHolder<SoundEvent, SoundEvent> TOTEM_BLOCK_SAP = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("totemic_sapping")));

    public static final DeferredHolder<SoundEvent, SoundEvent> RITUAL_BEGINS = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("ritual_begins")));
    public static final DeferredHolder<SoundEvent, SoundEvent> RITUAL_ABSORBS_ITEM = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("ritual_absorbs_item")));
    public static final DeferredHolder<SoundEvent, SoundEvent> RITUAL_FORMS = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("ritual_forms")));
    public static final DeferredHolder<SoundEvent, SoundEvent> RITUAL_ABSORBS_SPIRIT = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("ritual_absorbs_spirit")));
    public static final DeferredHolder<SoundEvent, SoundEvent> RITUAL_EVOLVES = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("ritual_evolves")));
    public static final DeferredHolder<SoundEvent, SoundEvent> RITUAL_COMPLETED = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("ritual_completed")));
    public static final DeferredHolder<SoundEvent, SoundEvent> RITUAL_BEGINNING_AMBIENCE = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("ritual_beginning_ambience")));
    public static final DeferredHolder<SoundEvent, SoundEvent> RITUAL_EVOLUTION_AMBIENCE = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("ritual_evolution_ambience")));
    public static final DeferredHolder<SoundEvent, SoundEvent> COMPLETED_RITUAL_AMBIENCE = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("completed_ritual_ambience")));

    public static final DeferredHolder<SoundEvent, SoundEvent> UNCANNY_VALLEY = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("the_unknown_weeps")));
    public static final DeferredHolder<SoundEvent, SoundEvent> VOID_HEARTBEAT = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("the_void_heart_beats")));
    public static final DeferredHolder<SoundEvent, SoundEvent> SONG_OF_THE_VOID = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("song_of_the_void")));
    public static final DeferredHolder<SoundEvent, SoundEvent> VOID_REJECTION = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("rejected_by_the_unknown")));
    public static final DeferredHolder<SoundEvent, SoundEvent> VOID_TRANSMUTATION = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("void_transmutation")));
    public static final DeferredHolder<SoundEvent, SoundEvent> VOID_EATS_GUNK = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("void_eats_gunk")));

    public static final MalumSoundType SOULSTONE_ORE = new MalumSoundType("soulstone");
    public static final MalumSoundType DEEPSLATE_SOULSTONE_ORE = new MalumSoundType("deepslate_soulstone");
    public static final MalumSoundType BLOCK_OF_SOULSTONE = new MalumSoundType("block_of_soulstone");
    public static final MalumSoundType BLOCK_OF_RAW_SOULSTONE = new MalumSoundType("block_of_raw_soulstone");

    public static final MalumSoundType BRILLIANCE_ORE = new MalumSoundType("brilliance");
    public static final MalumSoundType DEEPSLATE_BRILLIANCE_ORE = new MalumSoundType("deepslate_brilliance");
    public static final MalumSoundType BLOCK_OF_BRILLIANCE = new MalumSoundType("block_of_brilliance");
    public static final MalumSoundType BLOCK_OF_RAW_BRILLIANCE = new MalumSoundType("block_of_raw_brilliance");

    public static final MalumSoundType BLAZING_QUARTZ_ORE = new MalumSoundType("blazing_quartz_ore");
    public static final MalumSoundType BLAZING_QUARTZ_CLUSTER = new QuartzClusterSoundType("blazing_quartz_cluster");
    public static final MalumSoundType BLOCK_OF_BLAZING_QUARTZ = new MalumSoundType("block_of_blazing_quartz");

    public static final MalumSoundType NATURAL_QUARTZ_ORE = new MalumSoundType("natural_quartz_ore");
    public static final MalumSoundType NATURAL_DEEPSLATE_QUARTZ_ORE = new MalumSoundType("natural_deepslate_quartz_ore");
    public static final MalumSoundType NATURAL_QUARTZ_CLUSTER = new QuartzClusterSoundType("natural_quartz_cluster");
    public static final MalumSoundType BLOCK_OF_NATURAL_QUARTZ = new MalumSoundType("block_of_natural_quartz");

    public static final CthonicGoldSoundType CTHONIC_GOLD_ORE = new CthonicGoldSoundType("cthonic_gold_ore");
    public static final CthonicGoldSoundType CTHONIC_GOLD_CLUSTER = new CthonicGoldSoundType("cthonic_gold_cluster");
    public static final CthonicGoldSoundType BLOCK_OF_CTHONIC_GOLD = new CthonicGoldSoundType("block_of_cthonic_gold");
    public static final DeferredHolder<SoundEvent, SoundEvent> CTHONIC_GOLD_ORE_BREAK_MOTIF = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("cthonic_gold_ore_break_motif")));
    public static final DeferredHolder<SoundEvent, SoundEvent> CTHONIC_GOLD_ORE_PLACE_MOTIF = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("cthonic_gold_ore_place_motif")));
    public static final DeferredHolder<SoundEvent, SoundEvent> CTHONIC_GOLD_ORE_HIT_MOTIF = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("cthonic_gold_ore_hit_motif")));

    public static final MalumSoundType BLOCK_OF_ARCANE_CHARCOAL = new MalumSoundType("block_of_arcane_charcoal");
    public static final MalumSoundType BLOCK_OF_SOUL_STAINED_STEEL = new MalumSoundType("block_of_soul_stained_steel");
    public static final MalumSoundType BLOCK_OF_HALLOWED_GOLD = new MalumSoundType("block_of_hallowed_gold");

    public static final MalumSoundType BLOCK_OF_MALIGNANT_LEAD = new MalumSoundType("block_of_malignant_lead");
    public static final MalumSoundType BLOCK_OF_MALIGNANT_PEWTER = new MalumSoundType("block_of_malignant_pewter");

    public static final MalumSoundType RUNEWOOD = new MalumSoundType("runewood");
    public static final MalumSoundType RUNEWOOD_LEAVES = new MalumSoundType("runewood_leaves");
    public static final MalumSoundType RUNEWOOD_HANGING_SIGN = new MalumSoundType("runewood_hanging_sign");
    public static final DeferredHolder<SoundEvent, SoundEvent> RUNEWOOD_BUTTON_CLICK_OFF = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("runewood_button_click_off")));
    public static final DeferredHolder<SoundEvent, SoundEvent> RUNEWOOD_BUTTON_CLICK_ON = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("runewood_button_click_on")));
    public static final DeferredHolder<SoundEvent, SoundEvent> RUNEWOOD_PRESSURE_PLATE_CLICK_OFF = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("runewood_pressure_plate_click_off")));
    public static final DeferredHolder<SoundEvent, SoundEvent> RUNEWOOD_PRESSURE_PLATE_CLICK_ON = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("runewood_pressure_plate_click_on")));
    public static final DeferredHolder<SoundEvent, SoundEvent> RUNEWOOD_FENCE_GATE_CLOSE = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("runewood_fence_gate_close")));
    public static final DeferredHolder<SoundEvent, SoundEvent> RUNEWOOD_FENCE_GATE_OPEN = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("runewood_fence_gate_open")));
    public static final DeferredHolder<SoundEvent, SoundEvent> RUNEWOOD_DOOR_CLOSE = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("runewood_door_close")));
    public static final DeferredHolder<SoundEvent, SoundEvent> RUNEWOOD_DOOR_OPEN = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("runewood_door_open")));
    public static final DeferredHolder<SoundEvent, SoundEvent> RUNEWOOD_TRAPDOOR_CLOSE = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("runewood_trapdoor_close")));
    public static final DeferredHolder<SoundEvent, SoundEvent> RUNEWOOD_TRAPDOOR_OPEN = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("runewood_trapdoor_open")));

    public static final MalumSoundType SOULWOOD = new MalumSoundType("soulwood");
    public static final MalumSoundType SOULWOOD_LEAVES = new MalumSoundType("soulwood_leaves");
    public static final MalumSoundType SOULWOOD_HANGING_SIGN = new MalumSoundType("soulwood_hanging_sign");
    public static final DeferredHolder<SoundEvent, SoundEvent> SOULWOOD_BUTTON_CLICK_OFF = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("soulwood_button_click_off")));
    public static final DeferredHolder<SoundEvent, SoundEvent> SOULWOOD_BUTTON_CLICK_ON = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("soulwood_button_click_on")));
    public static final DeferredHolder<SoundEvent, SoundEvent> SOULWOOD_PRESSURE_PLATE_CLICK_OFF = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("soulwood_pressure_plate_click_off")));
    public static final DeferredHolder<SoundEvent, SoundEvent> SOULWOOD_PRESSURE_PLATE_CLICK_ON = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("soulwood_pressure_plate_click_on")));
    public static final DeferredHolder<SoundEvent, SoundEvent> SOULWOOD_FENCE_GATE_CLOSE = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("soulwood_fence_gate_close")));
    public static final DeferredHolder<SoundEvent, SoundEvent> SOULWOOD_FENCE_GATE_OPEN = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("soulwood_fence_gate_open")));
    public static final DeferredHolder<SoundEvent, SoundEvent> SOULWOOD_DOOR_CLOSE = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("soulwood_door_close")));
    public static final DeferredHolder<SoundEvent, SoundEvent> SOULWOOD_DOOR_OPEN = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("soulwood_door_open")));
    public static final DeferredHolder<SoundEvent, SoundEvent> SOULWOOD_TRAPDOOR_CLOSE = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("soulwood_trapdoor_close")));
    public static final DeferredHolder<SoundEvent, SoundEvent> SOULWOOD_TRAPDOOR_OPEN = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("soulwood_trapdoor_open")));

    public static final MalumSoundType TAINTED_ROCK = new MalumSoundType("tainted_rock");
    public static final MalumSoundType TAINTED_ROCK_BRICKS = new MalumSoundType("tainted_rock_bricks");
    public static final MalumSoundType CHISELED_TAINTED_ROCK = new MalumSoundType("chiseled_tainted_rock");
    
    public static final MalumSoundType TWISTED_ROCK = new MalumSoundType("twisted_rock");
    public static final MalumSoundType TWISTED_ROCK_BRICKS = new MalumSoundType("twisted_rock_bricks");
    public static final MalumSoundType CHISELED_TWISTED_ROCK = new MalumSoundType("chiseled_twisted_rock");

    public static final MalumSoundType DROSS_STONE = new MalumSoundType("dross_stone");
    public static final MalumSoundType DROSS_STONE_BRICKS = new MalumSoundType("dross_stone_bricks");
    public static final MalumSoundType CHISELED_DROSS_STONE = new MalumSoundType("chiseled_dross_stone");

    public static final MalumSoundType WEEPING_WELL_BRICKS = new MalumSoundType("weeping_well_bricks");
    public static final MalumSoundType ARCANE_ROCK_ARTIFICE = new MalumSoundType("arcane_rock_artifice");

    public static final MalumSoundType SPIRIT_DIODE = new MalumSoundType("spirit_diode");
    public static final DeferredHolder<SoundEvent, SoundEvent> SPIRIT_DIODE_OPEN = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("spirit_diode_open")));
    public static final DeferredHolder<SoundEvent, SoundEvent> SPIRIT_DIODE_CLOSE = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("spirit_diode_close")));
    public static final DeferredHolder<SoundEvent, SoundEvent> SPIRIT_DIODE_CONFIGURATION_DRAG = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("spirit_diode_ticks")));
    public static final DeferredHolder<SoundEvent, SoundEvent> SPIRIT_DIODE_CONFIGURATION_CLICK = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("spirit_diode_ticks_ferociously")));

    public static final DeferredHolder<SoundEvent, SoundEvent> WAVECHARGER_CHARGE = register(SoundEvent.createFixedRangeEvent(MalumMod.malumPath("wavecharger_charges"), 8f));
    public static final DeferredHolder<SoundEvent, SoundEvent> WAVECHARGER_RELEASE = register(SoundEvent.createFixedRangeEvent(MalumMod.malumPath("wavecharger_releases"), 8f));
    public static final DeferredHolder<SoundEvent, SoundEvent> WAVEBANKER_STORE = register(SoundEvent.createFixedRangeEvent(MalumMod.malumPath("wavebanker_stores"), 8f));
    public static final DeferredHolder<SoundEvent, SoundEvent> WAVEBANKER_RELEASE = register(SoundEvent.createFixedRangeEvent(MalumMod.malumPath("wavebanker_releases"), 8f));
    public static final DeferredHolder<SoundEvent, SoundEvent> WAVEBREAKER_STORE = register(SoundEvent.createFixedRangeEvent(MalumMod.malumPath("wavebreaker_stores"), 8f));
    public static final DeferredHolder<SoundEvent, SoundEvent> WAVEBREAKER_RELEASE = register(SoundEvent.createFixedRangeEvent(MalumMod.malumPath("wavebreaker_releases"), 8f));
    public static final DeferredHolder<SoundEvent, SoundEvent> WAVEMAKER_PULSE = register(SoundEvent.createFixedRangeEvent(MalumMod.malumPath("wavemaker_pulses"), 4f));

    public static final MalumSoundType VARNISHED_TERRACOTTA = new MalumSoundType("varnished_terracotta");
    public static final MalumSoundType ETHER = new MalumSoundType("ether");

    public static final MalumSoundType BLIGHTED_EARTH = new MalumSoundType("blighted_earth");
    public static final MalumSoundType BLIGHTED_FOLIAGE = new MalumSoundType("blighted_foliage");
    public static final MalumSoundType SCARSTONE = new MalumSoundType("scarstone");
    public static final MalumSoundType STRANGE_CRYSTAL = new MalumSoundType("strange_crystal");

    public static final DeferredHolder<SoundEvent, SoundEvent> MAJOR_BLIGHT_MOTIF = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("blight_reacts")));
    public static final DeferredHolder<SoundEvent, SoundEvent> MINOR_BLIGHT_MOTIF = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("blight_reacts_faintly")));

    public static final MalumSoundType WRITHING_FLESH = new MalumSoundType("writhing_flesh");
    public static final MalumSoundType FLESH = new MalumSoundType("flesh");

    public static final DeferredHolder<SoundEvent, SoundEvent> THE_DEEP_BECKONS = register(SoundEvent.createFixedRangeEvent(MalumMod.malumPath("the_deep_beckons"), 32f));
    public static final DeferredHolder<SoundEvent, SoundEvent> THE_HEAVENS_SIGN = register(SoundEvent.createFixedRangeEvent(MalumMod.malumPath("the_heavens_sing"), 32f));

    public static final DeferredHolder<SoundEvent, SoundEvent> ARCANE_ELEGY = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("arcane_elegy")));
    public static final DeferredHolder<SoundEvent, SoundEvent> AESTHETICA = register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath("aesthetica")));

    public static final ResourceKey<JukeboxSong> ARCANE_ELEGY_KEY =  register("arcane_elegy");
    public static final ResourceKey<JukeboxSong> AESTHETICA_KEY =  register("aesthetica");

    public static DeferredHolder<SoundEvent, SoundEvent> register(SoundEvent soundEvent) {
        return SOUNDS.register(soundEvent.getLocation().getPath(), () -> soundEvent);
    }

    public static ResourceKey<JukeboxSong> register(String name){
        return ResourceKey.create(Registries.JUKEBOX_SONG, MalumMod.malumPath(name));
    }
}
