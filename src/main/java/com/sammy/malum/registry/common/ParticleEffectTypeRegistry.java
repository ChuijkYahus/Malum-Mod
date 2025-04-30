package com.sammy.malum.registry.common;

import com.sammy.malum.visual_effects.networked.altar.*;
import com.sammy.malum.visual_effects.networked.attack.*;
import com.sammy.malum.visual_effects.networked.banner.SoulwovenBannerParticleEffect;
import com.sammy.malum.visual_effects.networked.blight.*;
import com.sammy.malum.visual_effects.networked.brazier.*;
import com.sammy.malum.visual_effects.networked.crucible.*;
import com.sammy.malum.visual_effects.networked.geas.*;
import com.sammy.malum.visual_effects.networked.gluttony.*;
import com.sammy.malum.visual_effects.networked.nitrate.*;
import com.sammy.malum.visual_effects.networked.pylon.*;
import com.sammy.malum.visual_effects.networked.sap.SapCollectionParticleEffect;
import com.sammy.malum.visual_effects.networked.spirit_diode.SpiritDiodeCloseParticleEffect;
import com.sammy.malum.visual_effects.networked.spirit_diode.SpiritDiodeOpenParticleEffect;
import com.sammy.malum.visual_effects.networked.spirit_mote.*;
import com.sammy.malum.visual_effects.networked.staff.*;
import com.sammy.malum.visual_effects.networked.totem.*;
import com.sammy.malum.visual_effects.networked.weeping_well.*;

public class ParticleEffectTypeRegistry {

    //Blight vfx
    public static final BlightingMistParticleEffect BLIGHTING_MIST = new BlightingMistParticleEffect("blighting_mist");

    //Spirit Altar
    public static final SpiritAltarCraftParticleEffect SPIRIT_ALTAR_CRAFTS = new SpiritAltarCraftParticleEffect("spirit_altar_crafts");
    public static final SpiritAltarEatItemParticleEffect SPIRIT_ALTAR_EATS_ITEM = new SpiritAltarEatItemParticleEffect("spirit_altar_eats_item");

    //Crucible
    public static final SpiritCrucibleCraftParticleEffect SPIRIT_CRUCIBLE_CRAFTS = new SpiritCrucibleCraftParticleEffect("spirit_crucible_crafts");
    public static final SuspiciousDevicePrimerParticleEffect SUSPICIOUS_DEVICE_PRIMER = new SuspiciousDevicePrimerParticleEffect("suspicious_device_primer");

    //Repair Pylon
    public static final PylonPrepareRepairParticleEffect REPAIR_PYLON_PREPARES = new PylonPrepareRepairParticleEffect("repair_pylon_prepare");
    public static final PylonRepairParticleEffect REPAIR_PYLON_REPAIRS = new PylonRepairParticleEffect("repair_pylon_repairs");

    //Totem Effects
    public static final TotemPoleActivatedParticleEffect TOTEM_POLE_ACTIVATED = new TotemPoleActivatedParticleEffect("totem_pole_activated");
    public static final EntityTotemParticleEffect ENTITY_RITE_EFFECT = new EntityTotemParticleEffect("entity_rite_effect");
    public static final BlockTotemParticleEffect BLOCK_RITE_EFFECT = new BlockTotemParticleEffect("block_rite_effect");
    public static final BlockFallTotemParticleEffect BLOCK_FALL_RITE_EFFECT = new BlockFallTotemParticleEffect("block_fall_rite_effect");

    //Soulbinding Brazier
    public static final SoulBrazierStartParticleEffect SOULBINDING_BRAZIER_BEGINS = new SoulBrazierStartParticleEffect("brazier_start");
    public static final SoulBrazierAcceptSacrificeParticleEffect SOULBINDING_BRAZIER_ACCEPTS_SACRIFICE = new SoulBrazierAcceptSacrificeParticleEffect("brazier_sacrifice");
    public static final SoulBrazierEndParticleEffect SOULBINDING_BRAZIER_ENDS = new SoulBrazierEndParticleEffect("brazier_end");

    //Ritual Plinth
//    public static final NetworkedParticleEffectType RITUAL_PLINTH_EATS_ITEM = new RitualPlinthAbsorbItemParticleEffect("ritual_plinth_eats_item");
//    public static final NetworkedParticleEffectType RITUAL_PLINTH_EATS_SPIRIT = new RitualPlinthAbsorbSpiritParticleEffect("ritual_plinth_eats_spirit");
//    public static final NetworkedParticleEffectType RITUAL_PLINTH_BEGINS_CHARGING = new RitualPlinthBeginChargingParticleEffect("ritual_plinth_begins_charging");
//    public static final NetworkedParticleEffectType RITUAL_PLINTH_CHANGES_TIER = new RitualPlinthChangeTierParticleEffect("ritual_plinth_changes_tier");
//    public static final NetworkedParticleEffectType RITUAL_PLINTH_FAILURE = new RitualPlinthFailureParticleEffect("ritual_plinth_failure");

    //Spirit Diode
    public static final SpiritDiodeOpenParticleEffect SPIRIT_DIODE_OPEN = new SpiritDiodeOpenParticleEffect("spirit_diode_open");
    public static final SpiritDiodeCloseParticleEffect SPIRIT_DIODE_CLOSE = new SpiritDiodeCloseParticleEffect("spirit_diode_close");

    //Slash Effects
    public static final ScytheSlashParticleEffect SCYTHE_SLASH = new ScytheSlashParticleEffect("scythe_slash");
    public static final AscensionRadialSlashParticleEffect SCYTHE_ASCENSION_SPIN = new AscensionRadialSlashParticleEffect("scythe_ascension_spin");
    public static final AscensionUppercutParticleEffect SCYTHE_ASCENSION_UPPERCUT = new AscensionUppercutParticleEffect("scythe_ascension_uppercut");

    public static final TyrvingSlashParticleEffect TYRVING_SLASH = new TyrvingSlashParticleEffect("tyrving_slash");
    public static final SunderingAnchorSlashParticleEffect SUNDERING_ANCHOR_SLASH = new SunderingAnchorSlashParticleEffect("sundering_anchor_slash");
    public static final SunderingAnchorSweepParticleEffect SUNDERING_ANCHOR_SWEEP = new SunderingAnchorSweepParticleEffect("sundering_anchor_sweep");

    public static final WeightOfWorldsCritParticleEffect WEIGHT_OF_WORLDS_CRIT = new WeightOfWorldsCritParticleEffect("weight_of_worlds_crit");
    public static final EdgeOfDeliveranceCritParticleEffect EDGE_OF_DELIVERANCE_CRIT = new EdgeOfDeliveranceCritParticleEffect("weight_of_worlds_crit");

    public static final HiddenBladeCounterParticleEffect HIDDEN_BLADE_COUNTER_FLURRY = new HiddenBladeCounterParticleEffect("hidden_blade_counter_flurry");

    //Slam Effects
    public static final StaffSlamAttackParticleEffect STAFF_SLAM = new StaffSlamAttackParticleEffect("staff_slam");

    //Wand
    public static final BoltImpactParticleEffect HEX_BOLT_IMPACT = new HexBoltImpactParticleEffect("hex_bolt_impact");
    public static final BoltImpactParticleEffect DRAINING_BOLT_IMPACT = new DrainingBoltImpactParticleEffect("draining_bolt_impact");
    public static final BoltImpactParticleEffect ENTROPIC_BOLT_IMPACT = new EntropicBoltImpactParticleEffect("entropic_bolt_impact");
    public static final UnwindingChaosChargeParticleEffect UNWINDING_CHAOS_CHARGE = new UnwindingChaosChargeParticleEffect("unwinding_chaos_charge");

    //Geas
    public static final ShakenFaithParticleEffect SHAKEN_FAITH = new ShakenFaithParticleEffect("shaken_faith");
    public static final InvertedHeartGeasImpactParticleEffect INVERTED_HEART_IMPACT = new InvertedHeartGeasImpactParticleEffect("inverted_heart_impact");
    public static final WyrdReconstructionReviveParticleEffect WYRD_RECONSTRUCTION_REVIVE = new WyrdReconstructionReviveParticleEffect("wyrd_reconstruction_revive");

    //Gluttony
    public static final ThrownGluttonyParticleEffect THROWN_GLUTTONY_IMPACT = new ThrownGluttonyParticleEffect("splash_of_gluttony");
    public static final AbsorbGluttonyParticleEffect GLUTTONY_ABSORB = new AbsorbGluttonyParticleEffect("gluttony_absorbed");

    //Nitrate
    public static final EthericNitrateImpactParticleEffect ETHERIC_NITRATE_IMPACT = new EthericNitrateImpactParticleEffect("etheric_nitrate_impact");

    //Void vfx
    public static final WeepingWellReactionParticleEffect WEEPING_WELL_REACTS = new WeepingWellReactionParticleEffect("weeping_well_reacts");

    //Misc vfx
    public static final SpiritMotePlaceParticleEffect SPIRIT_MOTE_SPARKLES = new SpiritMotePlaceParticleEffect("spirit_mote_sparkles");
    public static final SoulwovenBannerParticleEffect SOULWOVEN_BANNER_ACTIVATED = new SoulwovenBannerParticleEffect("soulwoven_banner_activated");
    public static final SapCollectionParticleEffect SAP_COLLECTED = new SapCollectionParticleEffect("sap_collected");

}
