package com.sammy.malum.registry.common.sound;

import com.sammy.malum.common.sound.*;
import net.minecraft.sounds.*;
import net.neoforged.neoforge.registries.*;

public class MalumBlockSoundEvents {

    public static void init() {

    }

    //Soulstone
    public static final MalumBlockSoundType SOULSTONE_BUD = new MalumBlockSoundType("soulstone_bud");
    public static final MalumBlockSoundType ARCHAIC_SOULSTONE_BUD = new MalumBlockSoundType("archaic_soulstone_bud");
    public static final MalumBlockSoundType REALIZED_SOULSTONE_BUD = new MalumBlockSoundType("realized_soulstone_bud");
    public static final DeferredHolder<SoundEvent, SoundEvent> SOULSTONE_BUD_GROWS = MalumSoundEvents.registerVariable("soulstone_bud_grows");
    public static final DeferredHolder<SoundEvent, SoundEvent> SOULSTONE_BUD_FULLY_MATURES = MalumSoundEvents.registerVariable("soulstone_bud_fully_matures");

    public static final MalumBlockSoundType SOULSTONE_ORE = new MalumBlockSoundType("soulstone");
    public static final MalumBlockSoundType DEEPSLATE_SOULSTONE_ORE = new MalumBlockSoundType("deepslate_soulstone");
    public static final MalumBlockSoundType BLOCK_OF_SOULSTONE = new MalumBlockSoundType("block_of_soulstone");
    public static final MalumBlockSoundType BLOCK_OF_RAW_SOULSTONE = new MalumBlockSoundType("block_of_raw_soulstone");

    //Brilliance
    public static final MalumBlockSoundType BRILLIANCE_ORE = new MalumBlockSoundType("brilliance");
    public static final MalumBlockSoundType DEEPSLATE_BRILLIANCE_ORE = new MalumBlockSoundType("deepslate_brilliance");
    public static final MalumBlockSoundType BLOCK_OF_BRILLIANCE = new MalumBlockSoundType("block_of_brilliance");
    public static final MalumBlockSoundType BLOCK_OF_RAW_BRILLIANCE = new MalumBlockSoundType("block_of_raw_brilliance");

    //Blazing Quartz
    public static final MalumBlockSoundType BLAZING_QUARTZ_ORE = new MalumBlockSoundType("blazing_quartz_ore");
    public static final MalumBlockSoundType BLAZING_QUARTZ_CLUSTER = new QuartzClusterBlockSoundType("blazing_quartz_cluster");
    public static final MalumBlockSoundType BLOCK_OF_BLAZING_QUARTZ = new MalumBlockSoundType("block_of_blazing_quartz");

    //Cthonic Gold
    public static final CthonicGoldBlockSoundType CTHONIC_GOLD_ORE = new CthonicGoldBlockSoundType("cthonic_gold_ore");
    public static final CthonicGoldBlockSoundType CTHONIC_GOLD_CLUSTER = new CthonicGoldBlockSoundType("cthonic_gold_cluster");
    public static final CthonicGoldBlockSoundType BLOCK_OF_CTHONIC_GOLD = new CthonicGoldBlockSoundType("block_of_cthonic_gold");
    public static final DeferredHolder<SoundEvent, SoundEvent> CTHONIC_GOLD_ORE_BREAK_MOTIF = MalumSoundEvents.registerVariable("cthonic_gold_ore_break_motif");
    public static final DeferredHolder<SoundEvent, SoundEvent> CTHONIC_GOLD_ORE_PLACE_MOTIF = MalumSoundEvents.registerVariable("cthonic_gold_ore_place_motif");
    public static final DeferredHolder<SoundEvent, SoundEvent> CTHONIC_GOLD_ORE_HIT_MOTIF = MalumSoundEvents.registerVariable("cthonic_gold_ore_hit_motif");


    //Metals & Misc
    public static final MalumBlockSoundType BLOCK_OF_ARCANE_CHARCOAL = new MalumBlockSoundType("block_of_arcane_charcoal");
    public static final MalumBlockSoundType BLOCK_OF_EBONY = new MalumBlockSoundType("block_of_ebony");

    public static final MalumBlockSoundType BLOCK_OF_SOUL_STAINED_STEEL = new MalumBlockSoundType("block_of_soul_stained_steel");
    public static final MalumBlockSoundType BLOCK_OF_HALLOWED_GOLD = new MalumBlockSoundType("block_of_hallowed_gold");

    public static final MalumBlockSoundType BLOCK_OF_MALIGNANT_LEAD = new MalumBlockSoundType("block_of_malignant_lead");
    public static final MalumBlockSoundType BLOCK_OF_MALIGNANT_PEWTER = new MalumBlockSoundType("block_of_malignant_pewter");

    //Flora
    public static final MalumBlockSoundType EBONY_SAPLING = new MalumBlockSoundType("ebony_sapling");
    public static final MalumBlockSoundType EBONY = new MalumBlockSoundType("ebony");


    public static final MalumBlockSoundType WILD_WITCHHAZEL = new MalumBlockSoundType("wild_witchhazel");
    public static final MalumBlockSoundType WITCHHAZEL = new MalumBlockSoundType("witchhazel");

    //Runewood
    public static final MalumBlockSoundType RUNEWOOD = new MalumBlockSoundType("runewood");
    public static final MalumBlockSoundType RUNEWOOD_LEAVES = new MalumBlockSoundType("runewood_leaves");
    public static final MalumBlockSoundType RUNEWOOD_HANGING_SIGN = new MalumBlockSoundType("runewood_hanging_sign");
    public static final DeferredHolder<SoundEvent, SoundEvent> RUNEWOOD_BUTTON_CLICK_OFF = MalumSoundEvents.registerVariable("runewood_button_click_off");
    public static final DeferredHolder<SoundEvent, SoundEvent> RUNEWOOD_BUTTON_CLICK_ON = MalumSoundEvents.registerVariable("runewood_button_click_on");
    public static final DeferredHolder<SoundEvent, SoundEvent> RUNEWOOD_PRESSURE_PLATE_CLICK_OFF = MalumSoundEvents.registerVariable("runewood_pressure_plate_click_off");
    public static final DeferredHolder<SoundEvent, SoundEvent> RUNEWOOD_PRESSURE_PLATE_CLICK_ON = MalumSoundEvents.registerVariable("runewood_pressure_plate_click_on");
    public static final DeferredHolder<SoundEvent, SoundEvent> RUNEWOOD_FENCE_GATE_CLOSE = MalumSoundEvents.registerVariable("runewood_fence_gate_close");
    public static final DeferredHolder<SoundEvent, SoundEvent> RUNEWOOD_FENCE_GATE_OPEN = MalumSoundEvents.registerVariable("runewood_fence_gate_open");
    public static final DeferredHolder<SoundEvent, SoundEvent> RUNEWOOD_DOOR_CLOSE = MalumSoundEvents.registerVariable("runewood_door_close");
    public static final DeferredHolder<SoundEvent, SoundEvent> RUNEWOOD_DOOR_OPEN = MalumSoundEvents.registerVariable("runewood_door_open");
    public static final DeferredHolder<SoundEvent, SoundEvent> RUNEWOOD_TRAPDOOR_CLOSE = MalumSoundEvents.registerVariable("runewood_trapdoor_close");
    public static final DeferredHolder<SoundEvent, SoundEvent> RUNEWOOD_TRAPDOOR_OPEN = MalumSoundEvents.registerVariable("runewood_trapdoor_open");

    //Soulwood
    public static final MalumBlockSoundType SOULWOOD = new MalumBlockSoundType("soulwood");
    public static final MalumBlockSoundType SOULWOOD_LEAVES = new MalumBlockSoundType("soulwood_leaves");
    public static final MalumBlockSoundType SOULWOOD_HANGING_SIGN = new MalumBlockSoundType("soulwood_hanging_sign");
    public static final DeferredHolder<SoundEvent, SoundEvent> SOULWOOD_BUTTON_CLICK_OFF = MalumSoundEvents.registerVariable("soulwood_button_click_off");
    public static final DeferredHolder<SoundEvent, SoundEvent> SOULWOOD_BUTTON_CLICK_ON = MalumSoundEvents.registerVariable("soulwood_button_click_on");
    public static final DeferredHolder<SoundEvent, SoundEvent> SOULWOOD_PRESSURE_PLATE_CLICK_OFF = MalumSoundEvents.registerVariable("soulwood_pressure_plate_click_off");
    public static final DeferredHolder<SoundEvent, SoundEvent> SOULWOOD_PRESSURE_PLATE_CLICK_ON = MalumSoundEvents.registerVariable("soulwood_pressure_plate_click_on");
    public static final DeferredHolder<SoundEvent, SoundEvent> SOULWOOD_FENCE_GATE_CLOSE = MalumSoundEvents.registerVariable("soulwood_fence_gate_close");
    public static final DeferredHolder<SoundEvent, SoundEvent> SOULWOOD_FENCE_GATE_OPEN = MalumSoundEvents.registerVariable("soulwood_fence_gate_open");
    public static final DeferredHolder<SoundEvent, SoundEvent> SOULWOOD_DOOR_CLOSE = MalumSoundEvents.registerVariable("soulwood_door_close");
    public static final DeferredHolder<SoundEvent, SoundEvent> SOULWOOD_DOOR_OPEN = MalumSoundEvents.registerVariable("soulwood_door_open");
    public static final DeferredHolder<SoundEvent, SoundEvent> SOULWOOD_TRAPDOOR_CLOSE = MalumSoundEvents.registerVariable("soulwood_trapdoor_close");
    public static final DeferredHolder<SoundEvent, SoundEvent> SOULWOOD_TRAPDOOR_OPEN = MalumSoundEvents.registerVariable("soulwood_trapdoor_open");

    //Tainted Rock
    public static final MalumBlockSoundType TAINTED_ROCK = new MalumBlockSoundType("tainted_rock");
    public static final MalumBlockSoundType TAINTED_ROCK_BRICKS = new MalumBlockSoundType("tainted_rock_bricks");
    public static final MalumBlockSoundType CHISELED_TAINTED_ROCK = new MalumBlockSoundType("chiseled_tainted_rock");

    //Twisted Rock
    public static final MalumBlockSoundType TWISTED_ROCK = new MalumBlockSoundType("twisted_rock");
    public static final MalumBlockSoundType TWISTED_ROCK_BRICKS = new MalumBlockSoundType("twisted_rock_bricks");
    public static final MalumBlockSoundType CHISELED_TWISTED_ROCK = new MalumBlockSoundType("chiseled_twisted_rock");

    //Misc
    public static final MalumBlockSoundType WEEPING_WELL_BRICKS = new MalumBlockSoundType("weeping_well_bricks");
    public static final MalumBlockSoundType ARCANE_ROCK_ARTIFICE = new MalumBlockSoundType("arcane_rock_artifice");

    //Spirit Diodes
    public static final MalumBlockSoundType SPIRIT_DIODE = new MalumBlockSoundType("spirit_diode");
    public static final DeferredHolder<SoundEvent, SoundEvent> SPIRIT_DIODE_OPEN = MalumSoundEvents.registerVariable("spirit_diode_open");
    public static final DeferredHolder<SoundEvent, SoundEvent> SPIRIT_DIODE_CLOSE = MalumSoundEvents.registerVariable("spirit_diode_close");
    public static final DeferredHolder<SoundEvent, SoundEvent> SPIRIT_DIODE_CONFIGURATION_DRAG = MalumSoundEvents.registerVariable("spirit_diode_ticks");
    public static final DeferredHolder<SoundEvent, SoundEvent> SPIRIT_DIODE_CONFIGURATION_CLICK = MalumSoundEvents.registerVariable("spirit_diode_ticks_ferociously");

    public static final DeferredHolder<SoundEvent, SoundEvent> WAVECHARGER_CHARGE = MalumSoundEvents.registerFixed("wavecharger_charges", 8f);
    public static final DeferredHolder<SoundEvent, SoundEvent> WAVECHARGER_RELEASE = MalumSoundEvents.registerFixed("wavecharger_releases", 8f);
    public static final DeferredHolder<SoundEvent, SoundEvent> WAVEBANKER_STORE = MalumSoundEvents.registerFixed("wavebanker_stores", 8f);
    public static final DeferredHolder<SoundEvent, SoundEvent> WAVEBANKER_RELEASE = MalumSoundEvents.registerFixed("wavebanker_releases", 8f);
    public static final DeferredHolder<SoundEvent, SoundEvent> WAVEBREAKER_STORE = MalumSoundEvents.registerFixed("wavebreaker_stores", 8f);
    public static final DeferredHolder<SoundEvent, SoundEvent> WAVEBREAKER_RELEASE = MalumSoundEvents.registerFixed("wavebreaker_releases", 8f);
    public static final DeferredHolder<SoundEvent, SoundEvent> WAVEMAKER_PULSE = MalumSoundEvents.registerFixed("wavemaker_pulses", 4f);

    //Decoration
    public static final MalumBlockSoundType VARNISHED_TERRACOTTA = new MalumBlockSoundType("varnished_terracotta");
    public static final MalumBlockSoundType ETHER = new MalumBlockSoundType("ether");

    //Blight & Scarstone
    public static final MalumBlockSoundType BLIGHTED_EARTH = new MalumBlockSoundType("blighted_earth");
    public static final MalumBlockSoundType BLIGHTED_FOLIAGE = new MalumBlockSoundType("blighted_foliage");
    public static final MalumBlockSoundType SCARSTONE = new MalumBlockSoundType("scarstone");
    public static final MalumBlockSoundType STRANGE_CRYSTAL = new MalumBlockSoundType("strange_crystal");

    public static final DeferredHolder<SoundEvent, SoundEvent> MAJOR_BLIGHT_MOTIF = MalumSoundEvents.registerVariable("blight_reacts");
    public static final DeferredHolder<SoundEvent, SoundEvent> MINOR_BLIGHT_MOTIF = MalumSoundEvents.registerVariable("blight_reacts_faintly");
    public static final DeferredHolder<SoundEvent, SoundEvent> BLIGHT_PROPAGATION = MalumSoundEvents.registerVariable("blight_propagates");
    public static final DeferredHolder<SoundEvent, SoundEvent> SCARSTONE_PROPAGATION = MalumSoundEvents.registerVariable("scarstone_monument_forms");

    //Dungeon Architecture
    public static final MalumBlockSoundType ODD_SCRIPTURES = new MalumBlockSoundType("odd_scriptures");

    public static final MalumBlockSoundType DROSS_STONE = new MalumBlockSoundType("dross_stone");
    public static final MalumBlockSoundType DROSS_STONE_BRICKS = new MalumBlockSoundType("dross_stone_bricks");
    public static final MalumBlockSoundType CHISELED_DROSS_STONE = new MalumBlockSoundType("chiseled_dross_stone");

    //Dungeon Flesh
    public static final MalumBlockSoundType WRITHING_FLESH = new MalumBlockSoundType("writhing_flesh");
    public static final MalumBlockSoundType FLESH = new MalumBlockSoundType("flesh");


}
