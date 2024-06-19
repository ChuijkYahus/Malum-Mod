package com.sammy.malum.registry.common;

import com.sammy.malum.visual_effects.networked.*;
import com.sammy.malum.visual_effects.networked.altar.*;
import com.sammy.malum.visual_effects.networked.blight.*;
import com.sammy.malum.visual_effects.networked.crucible.*;
import com.sammy.malum.visual_effects.networked.generic.*;
import com.sammy.malum.visual_effects.networked.nitrate.*;
import com.sammy.malum.visual_effects.networked.pylon.*;
import com.sammy.malum.visual_effects.networked.ritual.*;
import com.sammy.malum.visual_effects.networked.staff.*;
import com.sammy.malum.visual_effects.networked.totem.*;
import com.sammy.malum.visual_effects.networked.weeping_well.*;

import java.util.*;

public class ParticleEffectTypeRegistry {

    public static final Map<String, ParticleEffectType> EFFECT_TYPES = new LinkedHashMap<>();

    //Generic, all-purpose effect application vfx
    public static final ParticleEffectType HEXING_SMOKE = new HexingSmokeParticleEffect("minor_hexing_smoke", 1);
    public static final ParticleEffectType MAJOR_HEXING_SMOKE = new HexingSmokeParticleEffect("major_hexing_smoke", 1.75f);
    public static final ParticleEffectType DRIPPING_SMOKE = new DrippingSmokeParticleEffect("dripping_smoke", 1.25f);

    public static final ParticleEffectType CONCENTRATED_RISING_SPARKLES = new RisingSparklesParticleEffect("concentrated_rising_sparkles");

    //Blight vfx
    public static final ParticleEffectType BLIGHTING_MIST = new BlightingMistParticleEffect("blighting_mist");

    //Functional vfx
    public static final ParticleEffectType SPIRIT_ALTAR_CRAFTS = new SpiritAltarCraftParticleEffect("spirit_altar_crafts");
    public static final ParticleEffectType SPIRIT_ALTAR_EATS_ITEM = new SpiritAltarEatItemParticleEffect("spirit_altar_eats_item");

    public static final ParticleEffectType SPIRIT_CRUCIBLE_CRAFTS = new SpiritCrucibleCraftParticleEffect("spirit_crucible_crafts");

    public static final ParticleEffectType REPAIR_PYLON_PREPARES = new PylonPrepareRepairParticleEffect("repair_pylon_prepare");
    public static final ParticleEffectType REPAIR_PYLON_REPAIRS = new PylonRepairParticleEffect("repair_pylon_repairs");

    public static final ParticleEffectType TOTEM_POLE_ACTIVATED = new TotemPoleActivatedParticleEffect("totem_pole_activated");

    public static final ParticleEffectType RITUAL_PLINTH_EATS_ITEM = new RitualPlinthAbsorbItemParticleEffect("ritual_plinth_eats_item");
    public static final ParticleEffectType RITUAL_PLINTH_EATS_SPIRIT = new RitualPlinthAbsorbSpiritParticleEffect("ritual_plinth_eats_spirit");
    public static final ParticleEffectType RITUAL_PLINTH_BEGINS_CHARGING = new RitualPlinthBeginChargingParticleEffect("ritual_plinth_begins_charging");
    public static final ParticleEffectType RITUAL_PLINTH_CHANGES_TIER = new RitualPlinthChangeTierParticleEffect("ritual_plinth_changes_tier");
    public static final ParticleEffectType RITUAL_PLINTH_FAILURE = new RitualPlinthFailureParticleEffect("ritual_plinth_failure");

    //Wand
    public static final ParticleEffectType HEX_BOLT_IMPACT = new HexBoltImpactParticleEffect("hex_bolt_impact");
    public static final ParticleEffectType DRAINING_BOLT_IMPACT = new DrainingBoltImpactParticleEffect("draining_bolt_impact");
    public static final ParticleEffectType AURIC_BOLT_IMPACT = new AuricBoltImpactParticleEffect("auric_bolt_impact");

    //Nitrate
    public static final ParticleEffectType ETHERIC_NITRATE_IMPACT = new EthericNitrateImpactParticleEffect("etheric_nitrate_impact");

    //Void vfx
    public static final ParticleEffectType WEEPING_WELL_REACTS = new WeepingWellReactionParticleEffect("weeping_well_reacts");
    public static final ParticleEffectType WEEPING_WELL_EMITS_RADIANCE = new WeepingWellRadianceParticleEffect("weeping_well_emits_radiance");

    //Misc vfx
    public static final ParticleEffectType SPIRIT_MOTE_SPARKLES = new SpiritMoteCreationParticleEffect("spirit_mote_sparkles");
    public static final ParticleEffectType SAP_COLLECTED = new SapCollectionParticleEffect("sap_collected");

}
