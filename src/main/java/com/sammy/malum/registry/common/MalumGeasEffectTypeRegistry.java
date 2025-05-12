package com.sammy.malum.registry.common;

import com.sammy.malum.*;
import com.sammy.malum.common.geas.*;
import com.sammy.malum.common.geas.authority.*;
import com.sammy.malum.common.geas.oath.deliverance.*;
import com.sammy.malum.common.geas.oath.staff.*;
import com.sammy.malum.common.geas.pact.aerial.*;
import com.sammy.malum.common.geas.pact.aqueous.*;
import com.sammy.malum.common.geas.pact.arcane.*;
import com.sammy.malum.common.geas.pact.earthen.*;
import com.sammy.malum.common.geas.pact.eldritch.*;
import com.sammy.malum.common.geas.pact.infernal.*;
import com.sammy.malum.common.geas.pact.sacred.*;
import com.sammy.malum.common.geas.pact.wicked.*;
import com.sammy.malum.core.systems.geas.*;
import net.minecraft.core.*;
import net.minecraft.resources.*;
import net.neoforged.neoforge.registries.*;

import static com.sammy.malum.registry.common.SpiritTypeRegistry.*;

public class MalumGeasEffectTypeRegistry {

    public static ResourceKey<Registry<GeasEffectType>> GEAS_TYPES_KEY = ResourceKey.createRegistryKey(MalumMod.malumPath("geas_types"));
    public static final DeferredRegister<GeasEffectType> GEAS_TYPES = DeferredRegister.create(GEAS_TYPES_KEY, MalumMod.MALUM);
    public static final Registry<GeasEffectType> GEAS_TYPES_REGISTRY = GEAS_TYPES.makeRegistry(builder -> builder.sync(true));


    // Oath, Bond, Pact, Promise, Creed, Coda, Manifesto, Ideal


    public static final DeferredHolder<GeasEffectType, GeasEffectType> PACT_OF_DEFIANCE = GEAS_TYPES.register("pact_of_defiance", () -> new GeasEffectType(DefianceGeas::new, SACRED_SPIRIT, EARTHEN_SPIRIT));
    public static final DeferredHolder<GeasEffectType, GeasEffectType> PACT_OF_THE_PARASITE = GEAS_TYPES.register("pact_of_the_parasite", () -> new GeasEffectType(ParasiteGeas::new, SACRED_SPIRIT, INFERNAL_SPIRIT));
    public static final DeferredHolder<GeasEffectType, GeasEffectType> PACT_OF_THE_LIFEWEAVER = GEAS_TYPES.register("pact_of_the_lifeweaver", () -> new GeasEffectType(LifeweaverGeas::new, SACRED_SPIRIT, ELDRITCH_SPIRIT));

    public static final DeferredHolder<GeasEffectType, GeasEffectType> PACT_OF_THE_WARLOCK = GEAS_TYPES.register("pact_of_the_warlock", () -> new GeasEffectType(WarlockGeas::new, WICKED_SPIRIT, AQUEOUS_SPIRIT));
    public static final DeferredHolder<GeasEffectType, GeasEffectType> PACT_OF_THE_REAPER = GEAS_TYPES.register("pact_of_the_reaper", () -> new GeasEffectType(ReaperGeas::new, WICKED_SPIRIT, AERIAL_SPIRIT));
    public static final DeferredHolder<GeasEffectType, GeasEffectType> PACT_OF_THE_BERSERKER = GEAS_TYPES.register("pact_of_the_berserker", () -> new GeasEffectType(BerserkerGeas::new, WICKED_SPIRIT, ELDRITCH_SPIRIT));

    public static final DeferredHolder<GeasEffectType, GeasEffectType> PACT_OF_THE_FORTRESS = GEAS_TYPES.register("pact_of_the_fortress", () -> new GeasEffectType(FortressGeas::new, ARCANE_SPIRIT, AQUEOUS_SPIRIT));
    public static final DeferredHolder<GeasEffectType, GeasEffectType> PACT_OF_THE_SHIELD = GEAS_TYPES.register("pact_of_the_shield", () -> new GeasEffectType(ShieldGeas::new, ARCANE_SPIRIT, AERIAL_SPIRIT));
    public static final DeferredHolder<GeasEffectType, GeasEffectType> PACT_OF_RECIPROCATION = GEAS_TYPES.register("pact_of_reciprocation", () -> new GeasEffectType(ReciprocationGeas::new, ARCANE_SPIRIT, ELDRITCH_SPIRIT));

    public static final DeferredHolder<GeasEffectType, GeasEffectType> PACT_OF_THE_SHATTERING_ADDICT = GEAS_TYPES.register("pact_of_the_shattering_addict", () -> new GeasEffectType(ShatteringAddictGeas::new, ELDRITCH_SPIRIT));
    public static final DeferredHolder<GeasEffectType, GeasEffectType> PACT_OF_THE_ARCANAPHAGE = GEAS_TYPES.register("pact_of_the_arcanaphage", () -> new GeasEffectType(ArcanaphageGeas::new, ELDRITCH_SPIRIT));
    public static final DeferredHolder<GeasEffectType, GeasEffectType> PACT_OF_THE_RUNE_EXPLOITATION = GEAS_TYPES.register("pact_of_rune_exploitation", () -> new GeasEffectType(RuneExploitationGeas::new, ELDRITCH_SPIRIT));

    public static final DeferredHolder<GeasEffectType, GeasEffectType> PACT_OF_SELF_CARE = GEAS_TYPES.register("pact_of_self_care", () -> new GeasEffectType(SelfCareGeas::new, AQUEOUS_SPIRIT, SACRED_SPIRIT));
    public static final DeferredHolder<GeasEffectType, GeasEffectType> PACT_OF_THE_HIGH_PRIEST = GEAS_TYPES.register("pact_of_the_high_priest", () -> new GeasEffectType(HighPriestGeas::new, AQUEOUS_SPIRIT, AERIAL_SPIRIT));
    public static final DeferredHolder<GeasEffectType, GeasEffectType> PACT_OF_TIDAL_AFFINITY = GEAS_TYPES.register("pact_of_tidal_affinity", () -> new GeasEffectType(TidalAffinityGeas::new, AQUEOUS_SPIRIT));
    public static final DeferredHolder<GeasEffectType, GeasEffectType> AQUEOUS_4 = GEAS_TYPES.register("aqueous_4", () -> new GeasEffectType(Aqueous4Geas::new, AQUEOUS_SPIRIT));

    public static final DeferredHolder<GeasEffectType, GeasEffectType> PACT_OF_THE_WINDSWEPT = GEAS_TYPES.register("pact_of_the_windswept", () -> new GeasEffectType(WindsweptGeas::new, AERIAL_SPIRIT, ARCANE_SPIRIT));
    public static final DeferredHolder<GeasEffectType, GeasEffectType> PACT_OF_THE_CONTINUING_SHOT = GEAS_TYPES.register("pact_of_the_continuing_shot", () -> new GeasEffectType(ContinuingShotGeas::new, AERIAL_SPIRIT, INFERNAL_SPIRIT));
    public static final DeferredHolder<GeasEffectType, GeasEffectType> PACT_OF_THE_CLOUDSKIPPER = GEAS_TYPES.register("pact_of_the_cloudskipper", () -> new GeasEffectType(CloudSkipperGeas::new, AERIAL_SPIRIT, SACRED_SPIRIT));
    public static final DeferredHolder<GeasEffectType, GeasEffectType> PACT_OF_THE_SKYBREAKER = GEAS_TYPES.register("pact_of_the_skybreaker", () -> new GeasEffectType(SkyBreakerGeas::new, AERIAL_SPIRIT, EARTHEN_SPIRIT));

    public static final DeferredHolder<GeasEffectType, GeasEffectType> PACT_OF_CONTENTEDNESS = GEAS_TYPES.register("pact_of_contentedness", () -> new GeasEffectType(ContentednessGeas::new, EARTHEN_SPIRIT, SACRED_SPIRIT));
    public static final DeferredHolder<GeasEffectType, GeasEffectType> PACT_OF_THE_LONE_DRUID = GEAS_TYPES.register("pact_of_the_lone_druid", () -> new GeasEffectType(LoneDruidGeas::new, EARTHEN_SPIRIT, ARCANE_SPIRIT));
    public static final DeferredHolder<GeasEffectType, GeasEffectType> PACT_OF_THE_PROFANE_ASCETIC = GEAS_TYPES.register("pact_of_the_profane_ascetic", () -> new GeasEffectType(ProfaneAsceticGeas::new, EARTHEN_SPIRIT, SACRED_SPIRIT));
    public static final DeferredHolder<GeasEffectType, GeasEffectType> PACT_OF_THE_PROFANE_GLUTTON = GEAS_TYPES.register("pact_of_the_profane_glutton", () -> new GeasEffectType(ProfaneGluttonGeas::new, EARTHEN_SPIRIT, WICKED_SPIRIT));

    public static final DeferredHolder<GeasEffectType, GeasEffectType> INFERNAL_1 = GEAS_TYPES.register("infernal_1", () -> new GeasEffectType(Infernal1Geas::new, INFERNAL_SPIRIT));
    public static final DeferredHolder<GeasEffectType, GeasEffectType> INFERNAL_2 = GEAS_TYPES.register("infernal_2", () -> new GeasEffectType(Infernal2Geas::new, INFERNAL_SPIRIT));
    public static final DeferredHolder<GeasEffectType, GeasEffectType> PACT_OF_THE_PYROMANIAC = GEAS_TYPES.register("pact_of_the_pyromaniac", () -> new GeasEffectType(PyromaniacGeas::new, INFERNAL_SPIRIT, ELDRITCH_SPIRIT));
    public static final DeferredHolder<GeasEffectType, GeasEffectType> PACT_OF_WYRD_RECONSTRUCTION = GEAS_TYPES.register("pact_of_wyrd_reconstruction", () -> new GeasEffectType(WyrdReconstructionGeas::new, INFERNAL_SPIRIT, SACRED_SPIRIT));




    // Bond of Beloved Chains
    //Requires Several Players
    //All Bound Players can no longer hurt eachother
    //All Bound Players can see each other regardless of invisibility
    //All Bound Players receive Healing Received for each Bound Player
    //Healing is Distributed between all Bound Players within a certain radius
//    public static final DeferredHolder<GeasEffectType, GeasEffectType> BOND_OF_BELOVED_CHAINS = GEAS_TYPES.register("bond_of_beloved_chains", () -> new GeasEffectType(BelovedChainsBond::new, SACRED_SPIRIT, EARTHEN_SPIRIT, ELDRITCH_SPIRIT));

    // Bond of Death's Seekers
    //Requires Several Players
    //All Bound Players receive extra Scythe Proficiency for each Bound Player
    //All Bound Players lose some armor for each Bound Player
    //Damage taken is Distributed between all Bound Players within a certain radius
//    public static final DeferredHolder<GeasEffectType, GeasEffectType> BOND_OF_DEATHS_SEEKERS = GEAS_TYPES.register("bond_of_deaths_seekers", () -> new GeasEffectType(DeathsSeekersBond::new, WICKED_SPIRIT, INFERNAL_SPIRIT, ELDRITCH_SPIRIT));



    public static final DeferredHolder<GeasEffectType, GeasEffectType> OATH_OF_THE_OVERKEEN_EYE = GEAS_TYPES.register("oath_of_the_overkeen_eye", () -> new GeasEffectType(OverkeenEyeGeas::new, ARCANE_SPIRIT, AERIAL_SPIRIT, ELDRITCH_SPIRIT));
    public static final DeferredHolder<GeasEffectType, GeasEffectType> OATH_OF_THE_OVERBURDENED_MIND = GEAS_TYPES.register("oath_of_the_overburdened_mind", () -> new GeasEffectType(OverburdenedMindGeas::new, ARCANE_SPIRIT, AQUEOUS_SPIRIT, ELDRITCH_SPIRIT));
    public static final DeferredHolder<GeasEffectType, GeasEffectType> OATH_OF_THE_OVEREAGER_FIST = GEAS_TYPES.register("oath_of_the_overeager_fist", () -> new GeasEffectType(OvereagerFistGeas::new, ARCANE_SPIRIT, INFERNAL_SPIRIT, ELDRITCH_SPIRIT));

    public static final DeferredHolder<GeasEffectType, GeasEffectType> OATH_OF_UNMAKERS_DISDAIN = GEAS_TYPES.register("oath_of_unmakers_disdain", () -> new GeasEffectType(UnmakersDisdainGeas::new, ARCANE_SPIRIT, WICKED_SPIRIT, ELDRITCH_SPIRIT));
    public static final DeferredHolder<GeasEffectType, GeasEffectType> OATH_OF_UNSIGHTED_RESISTANCE = GEAS_TYPES.register("oath_of_unsighted_resistance", () -> new GeasEffectType(UnsightedResistanceGeas::new, ARCANE_SPIRIT, EARTHEN_SPIRIT, ELDRITCH_SPIRIT));
    public static final DeferredHolder<GeasEffectType, GeasEffectType> OATH_OF_THE_UNDISCERNED_MAW = GEAS_TYPES.register("oath_of_the_undiscerned_maw", () -> new GeasEffectType(UndiscernedMawGeas::new, ARCANE_SPIRIT, SACRED_SPIRIT, ELDRITCH_SPIRIT));

    public static final DeferredHolder<GeasEffectType, GeasEffectType> AUTHORITY_OF_THE_INVERTED_HEART = GEAS_TYPES.register("authority_of_the_inverted_heart", () -> new GeasEffectType(InvertedHeartAuthority::new, ARCANE_SPIRIT, SACRED_SPIRIT, ELDRITCH_SPIRIT));

    public static final DeferredHolder<GeasEffectType, GeasEffectType> AUTHORITY_OF_THE_GLEEFUL_TARGET = GEAS_TYPES.register("authority_of_the_gleeful_target", () -> new GeasEffectType(GleefulTargetAuthority::new, ARCANE_SPIRIT, AQUEOUS_SPIRIT, ELDRITCH_SPIRIT));

    public static final DeferredHolder<GeasEffectType, GeasEffectType> CREED_OF_THE_BLIGHT_EATER = GEAS_TYPES.register("creed_of_the_blight_eater", () -> new GeasEffectType(BlightEaterCreed::new, ELDRITCH_SPIRIT));
}
