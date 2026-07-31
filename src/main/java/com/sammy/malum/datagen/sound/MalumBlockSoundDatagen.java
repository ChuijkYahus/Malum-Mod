package com.sammy.malum.datagen.sound;

import com.sammy.malum.registry.common.MalumContent;
import com.sammy.malum.registry.common.util.GeodeCrystalRegistrySet;
import com.sammy.malum.registry.common.util.MetallicsItemRegistryBundle;
import com.sammy.malum.registry.common.sound.*;
import com.sammy.malum.registry.common.util.building.MinorBuildingSet;

import static team.lodestar.lodestone.modules.datagen.providers.sound.LodestoneSoundEventSystem.sound;

public class MalumBlockSoundDatagen extends MalumSoundDatagenWrapper {

    public MalumBlockSoundDatagen(MalumSoundDatagen soundDatagen) {
        super(soundDatagen);
    }

    @Override
    public void registerSounds() {

        //Derealized Ores
        addMetallicsSounds(MalumContent.AlchemyAndMetallics.IRON_METALLICS, 1);
        addMetallicsSounds(MalumContent.AlchemyAndMetallics.COPPER_METALLICS, 1.4f);
        addMetallicsSounds(MalumContent.AlchemyAndMetallics.GOLD_METALLICS, 0.8f);
        addMetallicsSounds(MalumContent.AlchemyAndMetallics.ZINC_METALLICS, 1.2f);

        addMetallicsSounds(MalumContent.AlchemyAndMetallics.LEAD_METALLICS, 0.7f);
        addMetallicsSounds(MalumContent.AlchemyAndMetallics.SILVER_METALLICS, 1.45f);
        addMetallicsSounds(MalumContent.AlchemyAndMetallics.ALUMINIUM_METALLICS, 1.6f);
        addMetallicsSounds(MalumContent.AlchemyAndMetallics.NICKEL_METALLICS, 1.25f);

        for (MinorBuildingSet malumSet : MinorBuildingSet.getMalumSets()) {
            malumSet.addSounds(this);
        }

        //Soulstone
        add(MalumBlockSoundEvents.SOULSTONE_BUD, "block/ore/soulstone/bud");
        add(MalumBlockSoundEvents.ARCHAIC_SOULSTONE_BUD, "block/ore/soulstone/bud", b -> b.modifySounds(se -> se.pitch(0.75f)));
        add(MalumBlockSoundEvents.REALIZED_SOULSTONE_BUD, "block/ore/soulstone/bud", b -> b.modifySounds(se -> se.pitch(1.25f)));

        add(MalumBlockSoundEvents.SOULSTONE_BUD_GROWS, s -> s.with(allSounds("block/ore/soulstone/bud/grow", se -> se.volume(0.75f))));
        add(MalumBlockSoundEvents.SOULSTONE_BUD_FULLY_MATURES, s -> s.with(allSounds("block/ore/soulstone/bud/siphon", se -> se.volume(0.75f))));

        add(MalumBlockSoundEvents.SOULSTONE_ORE, "block/ore/soulstone", b -> b
                .addStepHitFallSounds(allSounds("minecraft:block/basalt/step", se -> se.pitch(1.2f))));
        add(MalumBlockSoundEvents.DEEPSLATE_SOULSTONE_ORE, "block/ore/soulstone/deepslate", b -> b
                .setStepHitFallSoundPaths("block/ore/soulstone").addStepHitFallSounds(allSounds("minecraft:block/basalt/step")));
        add(MalumBlockSoundEvents.BLOCK_OF_SOULSTONE, "block/ore/soulstone", b -> b
                .modifySounds(se -> se.pitch(1.4f)));
        add(MalumBlockSoundEvents.BLOCK_OF_RAW_SOULSTONE, "block/ore/soulstone", b -> b
                .modifySounds(se -> se.pitch(0.85f)));

        //Brilliance
        add(MalumBlockSoundEvents.BRILLIANCE_ORE, "block/ore/natural_quartz", b -> b
                .addStepHitFallSounds(allSounds("minecraft:block/basalt/step", se -> se.pitch(1.2f)))
                .modifySounds(se -> se.pitch(0.8f)));
        add(MalumBlockSoundEvents.DEEPSLATE_BRILLIANCE_ORE, "block/ore/natural_quartz/deepslate", b -> b
                .addStepHitFallSounds(allSounds("minecraft:block/basalt/step", se -> se.pitch(1.4f)))
                .modifySounds(se -> se.pitch(0.8f)));
        add(MalumBlockSoundEvents.BLOCK_OF_BRILLIANCE, "block/ore/natural_quartz", b -> b
                .modifySounds(se -> se.pitch(1.4f)));
        add(MalumBlockSoundEvents.BLOCK_OF_RAW_BRILLIANCE, "block/ore/natural_quartz", b -> b
                .modifySounds(se -> se.pitch(1.2f)));

        //Blazing Quartz
        add(MalumBlockSoundEvents.BLAZING_QUARTZ_ORE, "block/ore/blazing_quartz", b -> b
                .setStepHitFallSoundPaths("minecraft:block/nether_ore")
                .modifySounds(se -> se.pitch(1.2f)));
        add(MalumBlockSoundEvents.BLAZING_QUARTZ_CLUSTER, "block/ore/blazing_quartz", b -> b
                .setStepHitFallSoundPaths("minecraft:block/nether_ore")
                .modifySounds(se -> se.pitch(1.4f)));
        add(MalumBlockSoundEvents.BLOCK_OF_BLAZING_QUARTZ, "block/ore/blazing_quartz", b -> b
                .setStepHitFallSoundPaths("minecraft:block/nether_ore")
                .modifySounds(se -> se.pitch(1.6f)));

        //Cthonic Gold
        add(MalumBlockSoundEvents.BLOCK_OF_CTHONIC_GOLD, "block/ore/soulstone/deepslate", b -> b
                .setStepHitFallSoundPaths("block/ore/soulstone")
                .modifySounds(se -> se.pitch(1.6f)));
        add(MalumBlockSoundEvents.CTHONIC_GOLD_ORE, "block/ore/soulstone/deepslate", b -> b
                .setStepHitFallSoundPaths("block/ore/soulstone")
                .modifySounds(se -> se.pitch(1.2f)));
        add(MalumBlockSoundEvents.CTHONIC_GOLD_CLUSTER, "block/ore/soulstone/deepslate", b -> b
                .setStepHitFallSoundPaths("block/ore/soulstone")
                .modifySounds(se -> se.pitch(1.4f)));
        add(MalumBlockSoundEvents.CTHONIC_GOLD_ORE_BREAK_MOTIF, s -> s.with(allSounds("block/ore/cthonic_gold/break")));
        add(MalumBlockSoundEvents.CTHONIC_GOLD_ORE_PLACE_MOTIF, s -> s.with(allSounds("block/ore/cthonic_gold/break")));
        add(MalumBlockSoundEvents.CTHONIC_GOLD_ORE_HIT_MOTIF, s -> s.with(allSounds("block/ore/cthonic_gold/hit")).with(allSounds("minecraft:block/nether_ore/step")));

        //Geode
        addCrystalSetSounds(MalumContent.Materials.MUNDANE_QUARTZ, 1.4f);

        addCrystalSetSounds(MalumContent.Materials.VIVID_AMETRINE, 1.6f);
        addCrystalSetSounds(MalumContent.Materials.MARINE_AGATE, 1.2f);
        addCrystalSetSounds(MalumContent.Materials.RUGGED_CITRINE, 0.8f);

        //Metals & Misc
        add(MalumBlockSoundEvents.BLOCK_OF_ARCANE_CHARCOAL, "block/gemstone");
        add(MalumBlockSoundEvents.BLOCK_OF_EBONY, "block/gemstone");

        add(MalumBlockSoundEvents.BLOCK_OF_SOUL_STAINED_STEEL, "block/soul_stained_steel");
        add(MalumBlockSoundEvents.BLOCK_OF_HALLOWED_GOLD, "block/hallowed_gold");

        add(MalumBlockSoundEvents.BLOCK_OF_MALIGNANT_LEAD, "block/ore/soulstone", b -> b.modifySounds(se -> se.pitch(0.6f)));
        add(MalumBlockSoundEvents.BLOCK_OF_MALIGNANT_PEWTER, "block/soul_stained_steel", b -> b.modifySounds(se -> se.pitch(1.6f)));

//        add(MalumBlockSoundEvents.BLOCK_OF_IGNEOUS_CHITIN, "block/dungeon/igneous_chitin");
//        add(MalumBlockSoundEvents.BLOCK_OF_TORN_BRIMSTONE, "block/dungeon/torn_brimstone");

        add(MalumBlockSoundEvents.EBONY_SAPLING, "block/flora/ebony", b -> b.setBreakPlaceSoundPaths("block/flora/ebony/sapling"));
        add(MalumBlockSoundEvents.EBONY, "block/flora/ebony");

        //Runewood
        add(MalumBlockSoundEvents.RUNEWOOD, "block/runewood");
        add(MalumBlockSoundEvents.RUNEWOOD_HANGING_SIGN, "block/runewood/hanging_sign");
        add(MalumBlockSoundEvents.RUNEWOOD_LEAVES, "minecraft:block/azalea_leaves");

        add(MalumBlockSoundEvents.RUNEWOOD_BUTTON_CLICK_OFF, s -> s.with(sound("block/runewood/button/runewood_button")));
        add(MalumBlockSoundEvents.RUNEWOOD_BUTTON_CLICK_ON, s -> s.with(sound("block/runewood/button/runewood_button")));
        add(MalumBlockSoundEvents.RUNEWOOD_PRESSURE_PLATE_CLICK_OFF, s -> s.with(sound("block/runewood/button/runewood_button")));
        add(MalumBlockSoundEvents.RUNEWOOD_PRESSURE_PLATE_CLICK_ON, s -> s.with(sound("block/runewood/button/runewood_button")));
        add(MalumBlockSoundEvents.RUNEWOOD_FENCE_GATE_CLOSE, s -> s.with(sounds("block/runewood/fence_gate/toggle", 3)));
        add(MalumBlockSoundEvents.RUNEWOOD_FENCE_GATE_OPEN, s -> s.with(sounds("block/runewood/fence_gate/toggle", 3)));
        add(MalumBlockSoundEvents.RUNEWOOD_DOOR_CLOSE, s -> s.with(sounds("block/runewood/door/toggle", 3)));
        add(MalumBlockSoundEvents.RUNEWOOD_DOOR_OPEN, s -> s.with(sounds("block/runewood/door/toggle", 3)));
        add(MalumBlockSoundEvents.RUNEWOOD_TRAPDOOR_CLOSE, s -> s.with(sounds("block/runewood/trapdoor/toggle", 3)));
        add(MalumBlockSoundEvents.RUNEWOOD_TRAPDOOR_OPEN, s -> s.with(sounds("block/runewood/trapdoor/toggle", 3)));

        //Soulwood
        add(MalumBlockSoundEvents.SOULWOOD, "block/runewood");
        add(MalumBlockSoundEvents.SOULWOOD_HANGING_SIGN, "block/runewood/hanging_sign");
        add(MalumBlockSoundEvents.SOULWOOD_LEAVES, "minecraft:block/azalea_leaves");

        add(MalumBlockSoundEvents.SOULWOOD_BUTTON_CLICK_OFF, s -> s.with(sound("block/runewood/button/runewood_button")));
        add(MalumBlockSoundEvents.SOULWOOD_BUTTON_CLICK_ON, s -> s.with(sound("block/runewood/button/runewood_button")));
        add(MalumBlockSoundEvents.SOULWOOD_PRESSURE_PLATE_CLICK_OFF, s -> s.with(sound("block/runewood/button/runewood_button")));
        add(MalumBlockSoundEvents.SOULWOOD_PRESSURE_PLATE_CLICK_ON, s -> s.with(sound("block/runewood/button/runewood_button")));
        add(MalumBlockSoundEvents.SOULWOOD_FENCE_GATE_CLOSE, s -> s.with(sounds("block/runewood/fence_gate/toggle", 3)));
        add(MalumBlockSoundEvents.SOULWOOD_FENCE_GATE_OPEN, s -> s.with(sounds("block/runewood/fence_gate/toggle", 3)));
        add(MalumBlockSoundEvents.SOULWOOD_DOOR_CLOSE, s -> s.with(sounds("block/runewood/door/toggle", 3)));
        add(MalumBlockSoundEvents.SOULWOOD_DOOR_OPEN, s -> s.with(sounds("block/runewood/door/toggle", 3)));
        add(MalumBlockSoundEvents.SOULWOOD_TRAPDOOR_CLOSE, s -> s.with(sounds("block/runewood/trapdoor/toggle", 3)));
        add(MalumBlockSoundEvents.SOULWOOD_TRAPDOOR_OPEN, s -> s.with(sounds("block/runewood/trapdoor/toggle", 3)));

        //Tainted Rock
        addArcaneRockSounds(MalumBlockSoundEvents.TAINTED_ROCK, "block/arcane_rock", 1.1f);
        addArcaneRockSounds(MalumBlockSoundEvents.TAINTED_ROCK_BRICKS, "block/arcane_rock/bricks", 1.1f);
        addArcaneRockSounds(MalumBlockSoundEvents.CHISELED_TAINTED_ROCK, "block/arcane_rock/chiseled", 1.1f);

        //Twisted Rock
        addArcaneRockSounds(MalumBlockSoundEvents.TWISTED_ROCK, "block/arcane_rock", 0.85f);
        addArcaneRockSounds(MalumBlockSoundEvents.TWISTED_ROCK_BRICKS, "block/arcane_rock/bricks", 0.85f);
        addArcaneRockSounds(MalumBlockSoundEvents.CHISELED_TWISTED_ROCK, "block/arcane_rock/chiseled", 0.85f);

        //Misc
        addArcaneRockSounds(MalumBlockSoundEvents.WEEPING_WELL_BRICKS, "block/arcane_rock/artifice","minecraft:block/heavy_core", 0.7f);
        addArcaneRockSounds(MalumBlockSoundEvents.ARCANE_ROCK_ARTIFICE, "block/arcane_rock/artifice", "minecraft:block/heavy_core", 1.0f);

        //Spirit Diodes
        add(MalumBlockSoundEvents.SPIRIT_DIODE, "block/spirit_diode", b -> b.setStepHitFallSoundPaths("minecraft:block/copper_bulb"));
        add(MalumBlockSoundEvents.SPIRIT_DIODE_OPEN, s -> s.with(sounds("block/spirit_diode/waveform_open", 4)));
        add(MalumBlockSoundEvents.SPIRIT_DIODE_CLOSE, s -> s.with(sounds("block/spirit_diode/waveform_close", 4)));
        add(MalumBlockSoundEvents.SPIRIT_DIODE_CONFIGURATION_DRAG, s -> s.with(sounds("block/spirit_diode/waveform_tick", 8)));
        add(MalumBlockSoundEvents.SPIRIT_DIODE_CONFIGURATION_CLICK, s -> s.with(sounds("block/spirit_diode/waveform_long_tick", 8)));

        add(MalumBlockSoundEvents.WAVECHARGER_CHARGE, s -> s.with(sound("block/spirit_diode/waveform_pulse").volume(0.3f).pitch(1.2f)));
        add(MalumBlockSoundEvents.WAVECHARGER_RELEASE, s -> s.with(sound("block/spirit_diode/waveform_pulse").volume(0.3f).pitch(0.8f)));
        add(MalumBlockSoundEvents.WAVEBANKER_STORE, s -> s.with(sound("block/spirit_diode/waveform_pulse").volume(0.3f).pitch(1.2)));
        add(MalumBlockSoundEvents.WAVEBANKER_RELEASE, s -> s.with(sound("block/spirit_diode/waveform_pulse").volume(0.3f).pitch(0.8)));
        add(MalumBlockSoundEvents.WAVEBREAKER_STORE, s -> s.with(sound("block/spirit_diode/waveform_pulse").volume(0.3f).pitch(1.2)));
        add(MalumBlockSoundEvents.WAVEBREAKER_RELEASE, s -> s.with(sound("block/spirit_diode/waveform_pulse").volume(0.3f).pitch(0.8)));
        add(MalumBlockSoundEvents.WAVEMAKER_PULSE, s -> s.with(sound("block/spirit_diode/waveform_pulse").volume(0.2f).pitch(1.4)));

        //Decoration
        add(MalumBlockSoundEvents.ETHER, "block/ether", b -> b.setStepHitFallSoundNames("cloth").setStepHitFallSoundPaths("minecraft:step"));
        add(MalumBlockSoundEvents.VARNISHED_TERRACOTTA, "block/terracotta");

        //Blight & Scarstone
        add(MalumBlockSoundEvents.BLIGHTED_EARTH, "minecraft:block/nylium");
        add(MalumBlockSoundEvents.BLIGHTED_FOLIAGE, "minecraft:block/netherwart", b -> b.setStepHitFallSoundPaths("minecraft:block/nether_sprouts"));
        add(MalumBlockSoundEvents.SCARSTONE, "block/scarstone");
        add(MalumBlockSoundEvents.STRANGE_CRYSTAL, "block/strange_crystal");

        add(MalumBlockSoundEvents.MAJOR_BLIGHT_MOTIF, s -> s.with(sounds("block/blight/blight_motif", 6)));
        add(MalumBlockSoundEvents.MINOR_BLIGHT_MOTIF, s -> s.with(sounds("block/blight/minor_blight_motif", 6)));

        add(MalumBlockSoundEvents.BLIGHT_PROPAGATION, s -> s.with(allSounds("block/blight/blight_propagation")));
        add(MalumBlockSoundEvents.SCARSTONE_PROPAGATION, s -> s.with(allSounds("block/scarstone/scarstone_propagation")));

        //Dungeon Architecture
        add(MalumBlockSoundEvents.ODD_SCRIPTURES, "block/terracotta", b -> b.modifySounds(se -> se.pitch(0.7f)));
        addDrossStoneSounds(MalumBlockSoundEvents.DROSS_STONE, "block/arcane_rock", 1.0f);
        addDrossStoneSounds(MalumBlockSoundEvents.DROSS_STONE_BRICKS, "block/arcane_rock/bricks", 1.0f);
        addDrossStoneSounds(MalumBlockSoundEvents.CHISELED_DROSS_STONE, "block/arcane_rock/chiseled", 1.0f);

        //Dungeon Flesh
        add(MalumBlockSoundEvents.WRITHING_FLESH, "minecraft:dig", b -> b
                .setBreakPlaceSoundNames("coral")
                .setStepHitFallSoundNames("coral")
                .setStepHitFallSoundPaths("minecraft:step")
                .modifySounds(se -> se.pitch(1.25f)));
        add(MalumBlockSoundEvents.FLESH, "minecraft:dig", b -> b
                .setBreakPlaceSoundNames("coral")
                .setStepHitFallSoundNames("coral")
                .setStepHitFallSoundPaths("minecraft:step"));
    }

    public void addCrystalSetSounds(GeodeCrystalRegistrySet type, float pitch) {
//        add(type.getClusterSound(), "block/ore/geode/cluster",
//                b -> b.modifySounds(se -> se.pitch(pitch)));
//
//        add(type.getGeodeSound(), "block/ore/geode",
//                b -> b.modifySounds(se -> se.pitch(pitch)));
    }

    public void addMetallicsSounds(MetallicsItemRegistryBundle metallics, float pitch) {
        add(metallics.getOreSound(), "block/ore/derealized", b -> b.modifySounds(se -> se.pitch(pitch - 0.1f)));
        add(metallics.getDeepslateOreSound(), "block/ore/derealized", b -> b.modifySounds(se -> se.pitch(pitch - 0.2f)));
        add(metallics.getDerealizedBlockSound(), "block/ore/derealized", b -> b.modifySounds(se -> se.pitch(pitch + 0.1f)));
        add(metallics.getHarmonizedBlockSound(), "block/ore/derealized", b -> b.modifySounds(se -> se.pitch(pitch + 0.2f)));
    }

    public void addArcaneRockSounds(MalumBlockSoundType soundType, String path, float pitch) {
        addArcaneRockSounds(soundType, path, "minecraft:block/basalt", pitch);
    }

    public void addDrossStoneSounds(MalumBlockSoundType soundType, String path, float pitch) {
        addArcaneRockSounds(soundType, path, "minecraft:block/dripstone", pitch);
    }

    public void addArcaneRockSounds(MalumBlockSoundType soundType, String path, String fillerStepPath, float pitch) {
        add(soundType, path, b -> b
                .modifyStepHitFallSounds(se -> se.weight(3))
                .modifySounds(se -> se.pitch(pitch))
                .addStepHitFallSounds(allSounds(fillerStepPath, "step", se -> se.weight(2).pitch(pitch))
                ));
    }
}