package com.sammy.malum.registry.common;

import com.sammy.malum.MalumMod;
import com.sammy.malum.common.effect.*;
import com.sammy.malum.common.effect.rite.aura.*;
import com.sammy.malum.common.effect.rite.aura.soulwood.*;
import com.sammy.malum.common.effect.geas.*;
import com.sammy.malum.common.effect.gluttony.*;
import com.sammy.malum.common.effect.rite.*;
import com.sammy.malum.registry.common.item.MalumItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber()
public class MalumMobEffects {

    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, MalumMod.MALUM);

    //Rite
    public static final DeferredHolder<MobEffect, MobEffect> SACRED_NOURISHMENT = EFFECTS.register("sacred_nourishment", SacredNourishment::new);
    public static final DeferredHolder<MobEffect, MobEffect> WICKED_EMPOWERMENT = EFFECTS.register("wicked_empowerment", WickedEmpowerment::new);

    public static final DeferredHolder<MobEffect, MobEffect> HOWLING_GALE = EFFECTS.register("howling_gale", HowlingGaleEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> SKY_TETHER = EFFECTS.register("sky_tether", SkyTetherEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> FLOWING_GRASP = EFFECTS.register("flowing_grasp", FlowingGraspEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> GOOD_TIDES = EFFECTS.register("good_tides", GoodTidesEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> STONE_WARD = EFFECTS.register("stone_ward", StoneWardEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> OAKEN_MIGHT = EFFECTS.register("oaken_might", OakenMightEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> BURNING_FERVOR = EFFECTS.register("burning_fervor", BurningFervorEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> FIERY_EMBRACE = EFFECTS.register("fiery_embrace", FieryEmbraceEffect::new);

    //Geas
    public static final DeferredHolder<MobEffect, MobEffect> ARCANAPHAGE = EFFECTS.register("arcanaphage", ArcanaphageEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> SHAKEN_FAITH = EFFECTS.register("shaken_faith", ShakenFaithEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> TRUE_SHOT = EFFECTS.register("true_shot", TrueShotEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> FLAWED_AIM = EFFECTS.register("flawed_aim", FlawedAimEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> PROSPECTORS_STREAK = EFFECTS.register("prospectors_streak", ProspectorsGreedEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> PYROMANIACS_FERVOR = EFFECTS.register("pyromaniacs_fervor", PyromaniacsFervorEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> WYRD_EXHAUSTION = EFFECTS.register("wyrd_exhaustion", WyrdExhaustionEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> GLEEFUL_TARGET = EFFECTS.register("gleeful_target", GleefulTargetEffect::new);

    //Trinket
    public static final DeferredHolder<MobEffect, MobEffect> CANCEROUS_GROWTH = EFFECTS.register("cancerous_growth", GrowingFleshEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> ECHOING_ARCANA = EFFECTS.register("echoing_arcana", EchoingArcanaEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> WICKED_INTENT = EFFECTS.register("wicked_intent", WickedIntentEffect::new);

    //Malignant
    public static final DeferredHolder<MobEffect, MobEffect> GRIM_CERTAINTY = EFFECTS.register("grim_certainty", GrimCertaintyEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> IMMINENT_DELIVERANCE = EFFECTS.register("imminent_deliverance", ImminentDeliveranceEffect::new);

    //Gluttony
    public static final DeferredHolder<MobEffect, MobEffect> GLUTTONY = EFFECTS.register("gluttony", GluttonyEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> TRIAL_OF_FAITH = EFFECTS.register("trial_of_faith", TrialOfFaithEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> DESPERATE_NEED = EFFECTS.register("desperate_need", DesperateNeedEffect::new);

    //Silence
    public static final DeferredHolder<MobEffect, MobEffect> SILENCED = EFFECTS.register("silenced", SilencedEffect::new);

    //Misc
    public static final DeferredHolder<MobEffect, MobEffect> ASCENSION = EFFECTS.register("ascension", AscensionEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> HATRED = EFFECTS.register("hatred", HatredEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> REJECTED = EFFECTS.register("rejected", RejectedEffect::new);

    @SubscribeEvent
    public static void registerBrewingRecipes(RegisterBrewingRecipesEvent event) {
        PotionBrewing.Builder builder = event.getBuilder();
        builder.addMix(Potions.WATER, MalumItems.ROTTING_ESSENCE.get(), Potions.MUNDANE);
        builder.addMix(Potions.AWKWARD, MalumItems.ROTTING_ESSENCE.get(), Potions.POISON);
        builder.addMix(Potions.WATER, MalumItems.ASTRAL_WEAVE.get(), Potions.MUNDANE);
        builder.addMix(Potions.AWKWARD, MalumItems.ASTRAL_WEAVE.get(), Potions.INVISIBILITY);
    }
}
