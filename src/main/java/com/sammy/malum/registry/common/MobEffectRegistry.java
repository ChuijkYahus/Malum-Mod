package com.sammy.malum.registry.common;

import com.sammy.malum.MalumMod;
import com.sammy.malum.common.effect.*;
import com.sammy.malum.common.effect.aura.*;
import com.sammy.malum.common.effect.geas.*;
import com.sammy.malum.common.effect.gluttony.*;
import com.sammy.malum.common.effect.rune.*;
import com.sammy.malum.registry.common.item.ItemRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.GAME)
public class MobEffectRegistry {

    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, MalumMod.MALUM);

    //Aura
    public static final DeferredHolder<MobEffect, MobEffect> GAIAS_BULWARK = EFFECTS.register("gaias_bulwark", EarthenAura::new);
    public static final DeferredHolder<MobEffect, MobEffect> EARTHEN_MIGHT = EFFECTS.register("earthen_might", CorruptedEarthenAura::new);
    public static final DeferredHolder<MobEffect, MobEffect> MINERS_RAGE = EFFECTS.register("miners_rage", InfernalAura::new);
    public static final DeferredHolder<MobEffect, MobEffect> IFRITS_EMBRACE = EFFECTS.register("ifrits_embrace", CorruptedInfernalAura::new);
    public static final DeferredHolder<MobEffect, MobEffect> ZEPHYRS_COURAGE = EFFECTS.register("zephyrs_courage", AerialAura::new);
    public static final DeferredHolder<MobEffect, MobEffect> AETHERS_CHARM = EFFECTS.register("aethers_charm", CorruptedAerialAura::new);
    public static final DeferredHolder<MobEffect, MobEffect> POSEIDONS_GRASP = EFFECTS.register("poseidons_grasp", AqueousAura::new);
    public static final DeferredHolder<MobEffect, MobEffect> ANGLERS_LURE = EFFECTS.register("anglers_lure", CorruptedAqueousAura::new);

    //Rune
    public static final DeferredHolder<MobEffect, MobEffect> REACTIVE_SHIELDING = EFFECTS.register("reactive_shielding", ReactiveShieldingEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> SACRIFICIAL_EMPOWERMENT = EFFECTS.register("sacrificial_empowerment", SacrificialEmpowermentEffect::new);

    //Geas
    public static final DeferredHolder<MobEffect, MobEffect> WYRD_EXHAUSTION = EFFECTS.register("wyrd_exhaustion", WyrdExhaustionEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> PYROMANIACS_FERVOR = EFFECTS.register("pyromaniacs_fervor", PyromaniacEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> UNSIGHTED_RESISTANCE = EFFECTS.register("unsighted_resistance", UnsightedResistanceEffect::new);

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

    //Misc
    public static final DeferredHolder<MobEffect, MobEffect> ASCENSION = EFFECTS.register("ascension", AscensionEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> SILENCED = EFFECTS.register("silenced", SilencedEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> HATRED = EFFECTS.register("hatred", HatredEffect::new);

    public static final DeferredHolder<MobEffect, MobEffect> REJECTED = EFFECTS.register("rejected", RejectedEffect::new);

    @SubscribeEvent
    public static void registerBrewingRecipes(RegisterBrewingRecipesEvent event) {
        PotionBrewing.Builder builder = event.getBuilder();
        builder.addMix(Potions.WATER, ItemRegistry.ROTTING_ESSENCE.get(), Potions.MUNDANE);
        builder.addMix(Potions.AWKWARD, ItemRegistry.ROTTING_ESSENCE.get(), Potions.POISON);
        builder.addMix(Potions.WATER, ItemRegistry.ASTRAL_WEAVE.get(), Potions.MUNDANE);
        builder.addMix(Potions.AWKWARD, ItemRegistry.ASTRAL_WEAVE.get(), Potions.INVISIBILITY);
    }
}
