package com.sammy.malum.registry.common;

import com.sammy.malum.*;
import com.sammy.malum.common.geas.*;
import com.sammy.malum.common.geas.bond.*;
import com.sammy.malum.common.geas.deliverance.*;
import com.sammy.malum.common.geas.explosion.*;
import com.sammy.malum.common.geas.gluttony.*;
import com.sammy.malum.common.geas.health.*;
import com.sammy.malum.common.geas.oath.*;
import com.sammy.malum.common.geas.scythe.*;
import com.sammy.malum.common.geas.soul_ward.*;
import com.sammy.malum.common.geas.staff.*;
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

    public static final DeferredHolder<GeasEffectType, GeasEffectType> PACT_OF_THE_NIGHTCHILD = GEAS_TYPES.register("pact_of_the_nightchild", () -> new GeasEffectType(NightChildGeas::new, WICKED_SPIRIT, ARCANE_SPIRIT));
    public static final DeferredHolder<GeasEffectType, GeasEffectType> PACT_OF_THE_DAYBLESSED = GEAS_TYPES.register("pact_of_the_dayblessed", () -> new GeasEffectType(DayBlessedGeas::new, INFERNAL_SPIRIT, SACRED_SPIRIT));
    public static final DeferredHolder<GeasEffectType, GeasEffectType> PACT_OF_THE_SHATTERING_ADDICT = GEAS_TYPES.register("pact_of_the_shattering_addict", () -> new GeasEffectType(ShatteringAddictGeas::new, ELDRITCH_SPIRIT));

    public static final DeferredHolder<GeasEffectType, GeasEffectType> PACT_OF_THE_FORTRESS = GEAS_TYPES.register("pact_of_the_fortress", () -> new GeasEffectType(FortressGeas::new, ARCANE_SPIRIT, AQUEOUS_SPIRIT));
    public static final DeferredHolder<GeasEffectType, GeasEffectType> PACT_OF_THE_SHIELD = GEAS_TYPES.register("pact_of_the_shield", () -> new GeasEffectType(ShieldGeas::new, ARCANE_SPIRIT, AERIAL_SPIRIT));

    public static final DeferredHolder<GeasEffectType, GeasEffectType> PACT_OF_THE_LIFELONG = GEAS_TYPES.register("pact_of_the_lifelong", () -> new GeasEffectType(LifelongGeas::new, SACRED_SPIRIT, AQUEOUS_SPIRIT));
    public static final DeferredHolder<GeasEffectType, GeasEffectType> PACT_OF_THE_ENDURING = GEAS_TYPES.register("pact_of_the_enduring", () -> new GeasEffectType(EnduringGeas::new, SACRED_SPIRIT, INFERNAL_SPIRIT));

    public static final DeferredHolder<GeasEffectType, GeasEffectType> PACT_OF_THE_REAPER = GEAS_TYPES.register("pact_of_the_reaper", () -> new GeasEffectType(ReaperGeas::new, WICKED_SPIRIT, ELDRITCH_SPIRIT));

    public static final DeferredHolder<GeasEffectType, GeasEffectType> PACT_OF_THE_SKYBREAKER = GEAS_TYPES.register("pact_of_the_skybreaker", () -> new GeasEffectType(SkyBreakerGeas::new, AERIAL_SPIRIT, EARTHEN_SPIRIT));
    public static final DeferredHolder<GeasEffectType, GeasEffectType> PACT_OF_THE_CLOUDSKIPPER = GEAS_TYPES.register("pact_of_the_cloudskipper", () -> new GeasEffectType(CloudSkipperGeas::new, AERIAL_SPIRIT, INFERNAL_SPIRIT));

    public static final DeferredHolder<GeasEffectType, GeasEffectType> PACT_OF_THE_PYROMANIAC = GEAS_TYPES.register("pact_of_the_pyromaniac", () -> new GeasEffectType(PyromaniacGeas::new, INFERNAL_SPIRIT, ELDRITCH_SPIRIT));

    public static final DeferredHolder<GeasEffectType, GeasEffectType> PACT_OF_RECIPROCATION = GEAS_TYPES.register("pact_of_reciprocation", () -> new GeasEffectType(ReciprocationGeas::new, ARCANE_SPIRIT, ELDRITCH_SPIRIT));

    public static final DeferredHolder<GeasEffectType, GeasEffectType> PACT_OF_THE_PROFANE_ASCETIC = GEAS_TYPES.register("pact_of_the_profane_ascetic", () -> new GeasEffectType(ProfaneAsceticGeas::new, EARTHEN_SPIRIT, WICKED_SPIRIT));

    public static final DeferredHolder<GeasEffectType, GeasEffectType> PACT_OF_THE_PROFANE_GLUTTON = GEAS_TYPES.register("pact_of_the_profane_glutton", () -> new GeasEffectType(ProfaneGluttonGeas::new, EARTHEN_SPIRIT, AQUEOUS_SPIRIT));
    
    public static final DeferredHolder<GeasEffectType, GeasEffectType> PACT_OF_WYRD_RECONSTRUCTION = GEAS_TYPES.register("pact_of_wyrd_reconstruction", () -> new GeasEffectType(WyrdReconstructionGeas::new, INFERNAL_SPIRIT, SACRED_SPIRIT));


    // Bond of Beloved Chains
    //Requires Several Players
    //All Bound Players can no longer hurt eachother
    //All Bound Players can see each other regardless of invisibility
    //All Bound Players receive Healing Received for each Bound Player
    //Healing is Distributed between all Bound Players within a certain radius
    public static final DeferredHolder<GeasEffectType, GeasEffectType> BOND_OF_BELOVED_CHAINS = GEAS_TYPES.register("bond_of_beloved_chains", () -> new GeasEffectType(BelovedChainsBond::new, SACRED_SPIRIT, EARTHEN_SPIRIT, ELDRITCH_SPIRIT));

    // Bond of Death's Seekers
    //Requires Several Players
    //All Bound Players receive extra Scythe Proficiency for each Bound Player
    //All Bound Players lose some armor for each Bound Player
    //Damage taken is Distributed between all Bound Players within a certain radius
    public static final DeferredHolder<GeasEffectType, GeasEffectType> BOND_OF_DEATHS_SEEKERS = GEAS_TYPES.register("bond_of_deaths_seekers", () -> new GeasEffectType(DeathsSeekersBond::new, WICKED_SPIRIT, INFERNAL_SPIRIT, ELDRITCH_SPIRIT));



    public static final DeferredHolder<GeasEffectType, GeasEffectType> OATH_OF_THE_OVERKEEN_EYE = GEAS_TYPES.register("oath_of_the_overkeen_eye", () -> new GeasEffectType(OverkeenEyeGeas::new, ARCANE_SPIRIT, AERIAL_SPIRIT));
    public static final DeferredHolder<GeasEffectType, GeasEffectType> OATH_OF_THE_OVEREAGER_FIST = GEAS_TYPES.register("oath_of_the_overeager_fist", () -> new GeasEffectType(OvereagerFistGeas::new, ARCANE_SPIRIT, INFERNAL_SPIRIT));

    public static final DeferredHolder<GeasEffectType, GeasEffectType> OATH_OF_THE_UNDISCERNED_MAW = GEAS_TYPES.register("oath_of_the_undiscerned_maw", () -> new GeasEffectType(UndiscernedMawGeas::new, SACRED_SPIRIT, WICKED_SPIRIT));
    // Oath of Unsighted Resistance
    //Malignant Deliverance generates Malignant Conversion
    //Need to think about this one more but the general theming of armor generation will persist
    //This is a void geas
    public static final DeferredHolder<GeasEffectType, GeasEffectType> OATH_OF_UNSIGHTED_RESISTANCE = GEAS_TYPES.register("oath_of_unsighted_resistance", () -> new GeasEffectType(UnsightedResistance::new, EARTHEN_SPIRIT, ARCANE_SPIRIT));

    public static final DeferredHolder<GeasEffectType, GeasEffectType> OATH_OF_THE_INVERTED_HEART = GEAS_TYPES.register("oath_of_the_inverted_heart", () -> new GeasEffectType(InvertedHeartOath::new, ELDRITCH_SPIRIT, SACRED_SPIRIT, ARCANE_SPIRIT));

    public static final DeferredHolder<GeasEffectType, GeasEffectType> OATH_OF_THE_GLEEFUL_TARGET = GEAS_TYPES.register("oath_of_the_gleeful_target", () -> new GeasEffectType(GleefulTargetOath::new, ELDRITCH_SPIRIT, AQUEOUS_SPIRIT, INFERNAL_SPIRIT));
//    // Oath of the Loosened Shackles
//    //Each time you die, it is instead stored for later
//    //At a later point in time, all of your deaths will come for you all at once
//    //As long as you keep dying, this timer is extended
//    //Reduces Armor significantly
//    //This is a void geas, might use fused consciousness even
//    public static final DeferredHolder<GeasEffectType, GeasEffectType> OATH_OF_THE_LAST_STAND = GEAS_TYPES.register("oath_of_the_last_stand", () -> new GeasEffectType(LastStandOath::new, AQUEOUS_SPIRIT, WICKED_SPIRIT, AERIAL_SPIRIT, ELDRITCH_SPIRIT));

    public static final DeferredHolder<GeasEffectType, GeasEffectType> CREED_OF_THE_BLIGHT_EATER = GEAS_TYPES.register("creed_of_the_blight_eater", () -> new GeasEffectType(BlightEaterCreed::new, ELDRITCH_SPIRIT));

//    public static final DeferredHolder<GeasEffectType, GeasEffectType> THE_LAST_CURSE = GEAS_TYPES.register("the_last_curse", () -> new GeasEffectType(TheLastCurse::new));

}
