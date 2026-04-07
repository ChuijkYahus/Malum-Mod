package com.sammy.malum.registry.common.sound;

import com.sammy.malum.MalumMod;
import net.minecraft.core.registries.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.JukeboxSong;
import net.neoforged.bus.api.*;
import net.neoforged.neoforge.registries.*;

import static com.sammy.malum.MalumMod.MALUM;

public class MalumSoundEvents {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, MALUM);

    public static void register(IEventBus modEventBus) {
        SOUND_EVENTS.register(modEventBus);
        MalumBlockSoundEvents.init();
        MalumGearSoundEvents.init();
        MalumCultistSoundEvents.init();
    }

    public static final DeferredHolder<SoundEvent, SoundEvent> ARCANA_CODEX_OPEN = registerVariable("arcana_codex_opened");
    public static final DeferredHolder<SoundEvent, SoundEvent> ARCANA_CODEX_CLOSE = registerVariable("arcana_codex_closed");
    public static final DeferredHolder<SoundEvent, SoundEvent> ARCANA_ENTRY_OPEN = registerVariable("arcana_entry_opened");
    public static final DeferredHolder<SoundEvent, SoundEvent> ARCANA_ENTRY_CLOSE = registerVariable("arcana_entry_closed");
    public static final DeferredHolder<SoundEvent, SoundEvent> ARCANA_ENTRY_HOVER = registerVariable("arcana_entry_hovered");
    public static final DeferredHolder<SoundEvent, SoundEvent> ARCANA_ENTRY_UNHOVER = registerVariable("arcana_entry_unhovered");
    public static final DeferredHolder<SoundEvent, SoundEvent> ARCANA_PAGE_FLIP = registerVariable("arcana_page_flipped");
    public static final DeferredHolder<SoundEvent, SoundEvent> ARCANA_BUTTON_HOVER = registerVariable("arcana_button_hover");
    public static final DeferredHolder<SoundEvent, SoundEvent> ARCANA_BUTTON_UNHOVER = registerVariable("arcana_button_unhover");
    public static final DeferredHolder<SoundEvent, SoundEvent> ARCANA_BUTTON_CLICK = registerVariable("arcana_button_click");
    public static final DeferredHolder<SoundEvent, SoundEvent> ARCANA_BUTTON_UNCLICK = registerVariable("arcana_button_unclick");

    public static final DeferredHolder<SoundEvent, SoundEvent> ARCANA_SUBENTRY_OPEN = registerVariable("arcana_subentry_opened");
    public static final DeferredHolder<SoundEvent, SoundEvent> ARCANA_SUBENTRY_CLOSE = registerVariable("arcana_subentry_closed");
    public static final DeferredHolder<SoundEvent, SoundEvent> ARCANA_SWEETENER_NORMAL = registerVariable("arcana_sweetener_normal");
    public static final DeferredHolder<SoundEvent, SoundEvent> ARCANA_SWEETENER_EVIL = registerVariable("arcana_sweetener_evil");
    public static final DeferredHolder<SoundEvent, SoundEvent> ARCANA_TRANSITION_NORMAL = registerVariable("arcana_transition_normal");
    public static final DeferredHolder<SoundEvent, SoundEvent> ARCANA_TRANSITION_EVIL = registerVariable("arcana_transition_evil");

    public static final DeferredHolder<SoundEvent, SoundEvent> ARCANE_WHISPERS = registerVariable("arcane_whispers");
    public static final DeferredHolder<SoundEvent, SoundEvent> SPIRIT_PICKUP = registerVariable("spirit_picked_up");
    public static final DeferredHolder<SoundEvent, SoundEvent> SOUL_SHATTER = registerVariable("a_soul_shatters");

    public static final DeferredHolder<SoundEvent, SoundEvent> PEDESTAL_ITEM_INSERT = registerVariable("pedestal_item_inserted");
    public static final DeferredHolder<SoundEvent, SoundEvent> PEDESTAL_ITEM_PICKUP = registerVariable("pedestal_item_picked_up");
    public static final DeferredHolder<SoundEvent, SoundEvent> PEDESTAL_SPIRIT_INSERT = registerVariable("pedestal_spirit_inserted");
    public static final DeferredHolder<SoundEvent, SoundEvent> PEDESTAL_SPIRIT_PICKUP = registerVariable("pedestal_spirit_picked_up");

    public static final DeferredHolder<SoundEvent, SoundEvent> CLOTH_TRINKET_EQUIP = registerVariable("cloth_trinket_equipped");
    public static final DeferredHolder<SoundEvent, SoundEvent> ORNATE_TRINKET_EQUIP = registerVariable("ornate_trinket_equipped");
    public static final DeferredHolder<SoundEvent, SoundEvent> GILDED_TRINKET_EQUIP = registerVariable("gilded_trinket_equipped");
    public static final DeferredHolder<SoundEvent, SoundEvent> ALCHEMICAL_TRINKET_EQUIP = registerVariable("alchemical_trinket_equipped");
    public static final DeferredHolder<SoundEvent, SoundEvent> ROTTEN_TRINKET_EQUIP = registerVariable("rotten_trinket_equipped");
    public static final DeferredHolder<SoundEvent, SoundEvent> METALLIC_TRINKET_EQUIP = registerVariable("metallic_trinket_equipped");
    public static final DeferredHolder<SoundEvent, SoundEvent> VOID_TRINKET_EQUIP = registerVariable("void_trinket_equipped");

    public static final DeferredHolder<SoundEvent, SoundEvent> RUNE_EQUIP = registerVariable("rune_equipped");
    public static final DeferredHolder<SoundEvent, SoundEvent> TOTEMIC_RUNE_EQUIP = registerVariable("totemic_rune_equipped");
    public static final DeferredHolder<SoundEvent, SoundEvent> VOID_RUNE_EQUIP = registerVariable("void_rune_equipped");

    public static final DeferredHolder<SoundEvent, SoundEvent> SPIRIT_MOTE_CREATED = registerVariable("spirit_mote_created");
    public static final DeferredHolder<SoundEvent, SoundEvent> RAVENOUS_POUCH_INSERT = registerVariable("ravenous_pouch_eats");

    public static final DeferredHolder<SoundEvent, SoundEvent> TUNING_FORK_TINKER = registerVariable("tuning_fork_tinkers");
    public static final DeferredHolder<SoundEvent, SoundEvent> CRUCIBLE_AUGMENT_APPLY = registerVariable("crucible_augment_applied");
    public static final DeferredHolder<SoundEvent, SoundEvent> CRUCIBLE_AUGMENT_REMOVE = registerVariable("crucible_augment_removed");
    public static final DeferredHolder<SoundEvent, SoundEvent> WARPING_ENGINE_REVERBERATES = registerVariable("warping_engine_reverberates");
    public static final DeferredHolder<SoundEvent, SoundEvent> SHIELDING_APPARATUS_SHIELDS = registerVariable("shielding_apparatus_shields");
    public static final DeferredHolder<SoundEvent, SoundEvent> SUSPICIOUS_DEVICE_DETONATES = registerVariable("suspicious_device_detonates");
    public static final DeferredHolder<SoundEvent, SoundEvent> SUSPICIOUS_DEVICE_DETONATES_AGAIN = registerVariable("suspicious_device_detonates_again");
    public static final DeferredHolder<SoundEvent, SoundEvent> SUSPICIOUS_DEVICE_DETONATES_ODDLY = registerVariable("legalize_nuclear_bombs");

    public static final DeferredHolder<SoundEvent, SoundEvent> AVARICE_COLLECT = registerVariable("avarice_collected");
    public static final DeferredHolder<SoundEvent, SoundEvent> AVARICE_FORTUNE = registerVariable("avarice_grants_fortune");

    public static final DeferredHolder<SoundEvent, SoundEvent> SOUL_WARD_HIT = registerVariable("soul_ward_absorbs_damage");
    public static final DeferredHolder<SoundEvent, SoundEvent> SOUL_WARD_GROW = registerVariable("soul_ward_grows");
    public static final DeferredHolder<SoundEvent, SoundEvent> SOUL_WARD_FULLY_CHARGED = registerVariable("soul_ward_charge_completed");
    public static final DeferredHolder<SoundEvent, SoundEvent> SOUL_WARD_DEPLETE = registerVariable("soul_ward_disintegrates");

    public static final DeferredHolder<SoundEvent, SoundEvent> MALIGNANT_AEGIS_HIT = registerVariable("malignant_aegis_nullifies_damage");
    public static final DeferredHolder<SoundEvent, SoundEvent> MALIGNANT_AEGIS_GROW = registerVariable("malignant_aegis_grows");
    public static final DeferredHolder<SoundEvent, SoundEvent> MALIGNANT_AEGIS_FULLY_CHARGED = registerVariable("malignant_aegis_charge_completed");
    public static final DeferredHolder<SoundEvent, SoundEvent> MALIGNANT_AEGIS_DEPLETE = registerVariable("malignant_disintegrates");

    public static final DeferredHolder<SoundEvent, SoundEvent> SPELL_CHARGE_GROW = registerVariable("spell_charge_grow");
    public static final DeferredHolder<SoundEvent, SoundEvent> SPELL_CHARGE_FULL = registerVariable("spell_charge_full");

    public static final DeferredHolder<SoundEvent, SoundEvent> OAKEN_MIGHT_HIT = registerVariable("oaken_might_takes_effect");

    public static final DeferredHolder<SoundEvent, SoundEvent> WARLOCK_BLAST = registerVariable("wicked_energy_bursts_forward");
    public static final DeferredHolder<SoundEvent, SoundEvent> REAPER_CUT = registerVariable("reaper_scythe_slashes");
    public static final DeferredHolder<SoundEvent, SoundEvent> BERSERKER_WRATH = registerVariable("wrathful_energy_surges");
    public static final DeferredHolder<SoundEvent, SoundEvent> PATIENT_DROWNING = registerVariable("player_patiently_drowns");
    public static final DeferredHolder<SoundEvent, SoundEvent> DESPERATE_NEED_CUT = registerVariable("the_rot_spreads");
    public static final DeferredHolder<SoundEvent, SoundEvent> DESPERATE_NEED_WITHDRAWAL = registerVariable("the_rot_consumes");
    public static final DeferredHolder<SoundEvent, SoundEvent> PROSPECTOR_BURN = registerVariable("player_burns_from_greed");
    public static final DeferredHolder<SoundEvent, SoundEvent> COMBUSTION_WHIPLASH = registerVariable("player_experiences_combustion_whiplash");
    public static final DeferredHolder<SoundEvent, SoundEvent> WYRD_RECONSTRUCTION = registerVariable("wyrd_reconstruction_reconstructs_body");
    public static final DeferredHolder<SoundEvent, SoundEvent> INVERTED_HEART_IMPACT = registerVariable("the_inverted_heart_shrieks");

    public static final DeferredHolder<SoundEvent, SoundEvent> ALTAR_LOOP = registerVariable("spirit_altar_infuses");
    public static final DeferredHolder<SoundEvent, SoundEvent> ALTAR_CRAFT = registerVariable("spirit_altar_completes_infusion");
    public static final DeferredHolder<SoundEvent, SoundEvent> ALTAR_CONSUME = registerVariable("spirit_altar_absorbs_item");
    public static final DeferredHolder<SoundEvent, SoundEvent> ALTAR_SPEED_UP = registerVariable("spirit_altar_speeds_up");

    public static final DeferredHolder<SoundEvent, SoundEvent> CRUCIBLE_LOOP = registerVariable("spirit_crucible_focuses");
    public static final DeferredHolder<SoundEvent, SoundEvent> CRUCIBLE_CRAFT = registerVariable("spirit_crucible_completes_focusing");
    public static final DeferredHolder<SoundEvent, SoundEvent> IMPETUS_CRACK = registerVariable("impetus_takes_damage");

    public static final DeferredHolder<SoundEvent, SoundEvent> REPAIR_PYLON_LOOP = registerVariable("repair_pylon_eagerly_hums");
    public static final DeferredHolder<SoundEvent, SoundEvent> REPAIR_PYLON_REPAIR_START = registerVariable("repair_pylon_begins_repairing");
    public static final DeferredHolder<SoundEvent, SoundEvent> REPAIR_PYLON_REPAIR_FINISH = registerVariable("repair_pylon_finishes_repairing");

    public static final DeferredHolder<SoundEvent, SoundEvent> BRAZIER_LOOP = registerVariable("soulbinding_brazier_fuses");
    public static final DeferredHolder<SoundEvent, SoundEvent> BRAZIER_START = registerVariable("soulbinding_sequence_initiated");
    public static final DeferredHolder<SoundEvent, SoundEvent> BRAZIER_FINISH = registerVariable("soulbinding_sequence_completed");
    public static final DeferredHolder<SoundEvent, SoundEvent> BRAZIER_SACRIFICE = registerVariable("soulbinding_brazier_accepts_offering");

    public static final DeferredHolder<SoundEvent, SoundEvent> RUNIC_WORKBENCH_SHAPES_RUNE_STONE = registerVariable("runic_workbench_shapes_tainted_rune");
    public static final DeferredHolder<SoundEvent, SoundEvent> RUNIC_WORKBENCH_SHAPES_RUNE_WOODEN = registerVariable("runic_workbench_shapes_wooden_rune");
    public static final DeferredHolder<SoundEvent, SoundEvent> RUNIC_WORKBENCH_SHAPES_RUNE_VOID = registerVariable("runic_workbench_shapes_void_rune");
    public static final DeferredHolder<SoundEvent, SoundEvent> RUNIC_WORKBENCH_SHAPES_RUNE_GENERIC = registerVariable("runic_workbench_shapes_something");

    public static final DeferredHolder<SoundEvent, SoundEvent> WEAVERS_WORKBENCH_CRAFT = registerVariable("weavers_workbench_weaves");

    public static final DeferredHolder<SoundEvent, SoundEvent> TOTEM_LOOP = registerVariable("totem_hums");
    public static final DeferredHolder<SoundEvent, SoundEvent> TOTEM_CHARGE = registerVariable("totem_charges");
    public static final DeferredHolder<SoundEvent, SoundEvent> TOTEM_ACTIVATED = registerVariable("totemic_rite_activated");
    public static final DeferredHolder<SoundEvent, SoundEvent> TOTEM_CANCELLED = registerVariable("totemic_rite_cancelled");
    public static final DeferredHolder<SoundEvent, SoundEvent> TOTEM_ENGRAVE = registerVariable("spirit_engraved");

    public static final DeferredHolder<SoundEvent, SoundEvent> SPARK_FORMED = registerVariable("spark_formed");
    public static final DeferredHolder<SoundEvent, SoundEvent> SPARK_IMPACT = registerVariable("spark_bestows_effect");
    public static final DeferredHolder<SoundEvent, SoundEvent> SPARK_POTION_IMPACT = registerVariable("spark_bestows_potion_effect");
    public static final DeferredHolder<SoundEvent, SoundEvent> SPARK_UNWOVEN = registerVariable("spark_unwoven");
    public static final DeferredHolder<SoundEvent, SoundEvent> SPARK_DIRECTED = registerVariable("spark_directed");

    public static final DeferredHolder<SoundEvent, SoundEvent> TOTEM_BLOCK_GRAVITY = registerVariable("totemic_weight");
    public static final DeferredHolder<SoundEvent, SoundEvent> TOTEM_BLOCK_GROW = registerVariable("totemic_growth");
    public static final DeferredHolder<SoundEvent, SoundEvent> TOTEM_BLOCK_SAP = registerVariable("totemic_sapping");

    public static final DeferredHolder<SoundEvent, SoundEvent> RITUAL_BEGINS = registerVariable("ritual_begins");
    public static final DeferredHolder<SoundEvent, SoundEvent> RITUAL_ABSORBS_ITEM = registerVariable("ritual_absorbs_item");
    public static final DeferredHolder<SoundEvent, SoundEvent> RITUAL_FORMS = registerVariable("ritual_forms");
    public static final DeferredHolder<SoundEvent, SoundEvent> RITUAL_ABSORBS_SPIRIT = registerVariable("ritual_absorbs_spirit");
    public static final DeferredHolder<SoundEvent, SoundEvent> RITUAL_EVOLVES = registerVariable("ritual_evolves");
    public static final DeferredHolder<SoundEvent, SoundEvent> RITUAL_COMPLETED = registerVariable("ritual_completed");
    public static final DeferredHolder<SoundEvent, SoundEvent> RITUAL_BEGINNING_AMBIENCE = registerVariable("ritual_beginning_ambience");
    public static final DeferredHolder<SoundEvent, SoundEvent> RITUAL_EVOLUTION_AMBIENCE = registerVariable("ritual_evolution_ambience");
    public static final DeferredHolder<SoundEvent, SoundEvent> COMPLETED_RITUAL_AMBIENCE = registerVariable("completed_ritual_ambience");

    public static final DeferredHolder<SoundEvent, SoundEvent> UNCANNY_VALLEY = registerVariable("the_unknown_weeps");
    public static final DeferredHolder<SoundEvent, SoundEvent> VOID_HEARTBEAT = registerVariable("the_void_heart_beats");
    public static final DeferredHolder<SoundEvent, SoundEvent> SONG_OF_THE_VOID = registerVariable("song_of_the_void");
    public static final DeferredHolder<SoundEvent, SoundEvent> VOID_REJECTION = registerVariable("rejected_by_the_unknown");
    public static final DeferredHolder<SoundEvent, SoundEvent> VOID_TRANSMUTATION = registerVariable("void_transmutation");
    public static final DeferredHolder<SoundEvent, SoundEvent> VOID_EATS_GUNK = registerVariable("void_eats_gunk");

    public static final DeferredHolder<SoundEvent, SoundEvent> THE_DEEP_BECKONS = registerFixed("the_deep_beckons", 32f);
    public static final DeferredHolder<SoundEvent, SoundEvent> THE_HEAVENS_SIGN = registerFixed("the_heavens_sing", 32f);

    public static final DeferredHolder<SoundEvent, SoundEvent> ARCANE_ELEGY = registerVariable("arcane_elegy");
    public static final DeferredHolder<SoundEvent, SoundEvent> AESTHETICA = registerVariable("aesthetica");

    public static final ResourceKey<JukeboxSong> ARCANE_ELEGY_KEY = registerJukeboxKey("arcane_elegy");
    public static final ResourceKey<JukeboxSong> AESTHETICA_KEY = registerJukeboxKey("aesthetica");

    public static DeferredHolder<SoundEvent, SoundEvent> registerVariable(String name) {
        return register(SoundEvent.createVariableRangeEvent(MalumMod.malumPath(name)));
    }

    public static DeferredHolder<SoundEvent, SoundEvent> registerFixed(String name, float range) {
        return register(SoundEvent.createFixedRangeEvent(MalumMod.malumPath(name), range));
    }

    public static ResourceKey<JukeboxSong> registerJukeboxKey(String name) {
        return ResourceKey.create(Registries.JUKEBOX_SONG, MalumMod.malumPath(name));
    }

    public static DeferredHolder<SoundEvent, SoundEvent> register(SoundEvent soundEvent) {
        return SOUND_EVENTS.register(soundEvent.getLocation().getPath(), () -> soundEvent);
    }
}