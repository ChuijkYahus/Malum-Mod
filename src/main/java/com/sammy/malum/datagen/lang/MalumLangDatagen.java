package com.sammy.malum.datagen.lang;

import com.sammy.malum.MalumMod;
import com.sammy.malum.common.data.component.*;
import com.sammy.malum.common.item.*;
import com.sammy.malum.compat.create.*;
import com.sammy.malum.core.systems.artifice.ArtificeAttributeType;
import com.sammy.malum.common.block.ether.EtherWallTorchBlock;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.core.systems.registry.*;
import com.sammy.malum.core.systems.rite.*;
import com.sammy.malum.core.systems.spirit.type.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.block.MalumBlocks;
import com.sammy.malum.registry.common.enchantment.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.*;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.WallTorchBlock;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredHolder;
import team.lodestar.lodestone.helpers.DataHelper;

import java.util.*;
import java.util.function.Supplier;

import static com.sammy.malum.registry.common.MalumAttributes.ATTRIBUTES;
import static com.sammy.malum.registry.common.MalumMobEffects.EFFECTS;
import static com.sammy.malum.registry.common.MalumSoundEvents.SOUNDS;
import static com.sammy.malum.registry.common.MalumSpiritTypes.SPIRIT_TYPES;
import static com.sammy.malum.registry.common.block.MalumBlocks.BLOCKS;
import static com.sammy.malum.registry.common.entity.MalumEntities.ENTITY_TYPES;
import static com.sammy.malum.registry.common.item.MalumItems.*;

public class MalumLangDatagen extends LanguageProvider {
    public static MalumLangDatagen lang;

    public MalumLangDatagen(PackOutput gen) {
        super(gen, MalumMod.MALUM, "en_us");
        lang = this;
    }

    @Override
    protected void addTranslations() {
        CodexLangDatagen.generateEntries();

        var blocks = new HashSet<>(BLOCKS.getEntries());
        var items = new HashSet<>(ITEMS.getEntries());
        var sounds = new HashSet<>(SOUNDS.getEntries());
        var effects = new HashSet<>(EFFECTS.getEntries());
        var attributes = new HashSet<>(ATTRIBUTES.getEntries());
        var entities = new HashSet<>(ENTITY_TYPES.getEntries());
        var spirits = new HashSet<>(SPIRIT_TYPES.getEntries());

        add(DataHelper.take(blocks, MalumBlocks.PRIMORDIAL_SOUP).get(), "The Weeping Well");
        add(DataHelper.take(blocks, MalumBlocks.VOID_CONDUIT).get(), "The Weeping Well");

        add("item.malum.filled_spirit_jar", "Filled Spirit Jar");
        add("malum.spirit.description.stored_spirit", "Contains: ");
        add("malum.spirit.description.stored_soul", "Stores Soul With: ");

        if (CreateCompat.LOADED) { //If Create is loaded, the copper nugget won't exist.
            add("item.malum.copper_nugget", "Copper Nugget");
        }
        DataHelper.takeAll(blocks, i -> i.get() instanceof WallTorchBlock);
        DataHelper.takeAll(blocks, i -> i.get() instanceof EtherWallTorchBlock);
        DataHelper.takeAll(blocks, i -> i.get() instanceof WallSignBlock);
        blocks.forEach(b -> {
            String name = b.get().getDescriptionId().replaceFirst("block\\.malum\\.", "");
            name = makeProper(DataHelper.toTitleCase(correctItemName(name), "_"));
            add(b.get().getDescriptionId(), name);
        });
        DataHelper.takeAll(items, i -> i.get() instanceof BlockItem && !(i.get() instanceof ItemNameBlockItem));
        items.forEach(i -> {
            String name = i.get().getDescriptionId().replaceFirst("item\\.malum\\.", "");
            name = makeProper(DataHelper.toTitleCase(correctItemName(name), "_"));
            add(i.get().getDescriptionId(), name);
        });

        sounds.forEach(s -> {
            String name = correctSoundName(s.getId().getPath()).replaceAll("_", " ");
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            add("malum.subtitle." + s.getId().getPath(), name);
        });

        effects.forEach(e -> {
            String name = DataHelper.toTitleCase(makeProperEnglish(e.getId().getPath()), "_");
            add("effect.malum." + BuiltInRegistries.MOB_EFFECT.getKey(e.get()).getPath(), name);
        });

        attributes.forEach(a -> {
            String name = DataHelper.toTitleCase(a.getId().getPath(), "_");
            add("attribute.name.malum." + BuiltInRegistries.ATTRIBUTE.getKey(a.get()).getPath(), name);
        });

        entities.forEach(e -> {
            String name = DataHelper.toTitleCase(e.getId().getPath(), "_");
            add("entity.malum." + BuiltInRegistries.ENTITY_TYPE.getKey(e.get()).getPath(), name);
        });

        spirits.forEach(s -> {
            MalumSpiritType spirit = s.get();
            String name = DataHelper.toTitleCase(s.getId().getPath(), "_");
            add(spirit.getCountedKey(), "%1$s " + name);
        });
        for (SoulwovenBannerPatternDataComponent pattern : SoulwovenBannerPatternDataComponent.REGISTERED_PATTERNS) {
            add(pattern.translationKey(), DataHelper.toTitleCase(pattern.type().getPath(), "_"));
        }
        for (ArtificeAttributeType attribute : ArtificeAttributeType.CRUCIBLE_ATTRIBUTES) {
            add(attribute.getLangKey(), DataHelper.toTitleCase(attribute.id.getPath().toLowerCase(Locale.ROOT), "_"));
        }

        for (DeferredHolder<GeasEffectType, ? extends GeasEffectType> geas : MalumGeasEffectTypes.GEAS_TYPES.getEntries()) {
            var effectType = geas.get();
            add(effectType.getLangKey(), DataHelper.toTitleCase(geas.getId().getPath().toLowerCase(Locale.ROOT), "_"));
        }

        addSpiritFlavour(MalumSpiritTypes.SACRED_SPIRIT, "Innocent");
        addSpiritFlavour(MalumSpiritTypes.WICKED_SPIRIT, "Malicious");
        addSpiritFlavour(MalumSpiritTypes.ARCANE_SPIRIT, "Fundamental");
        addSpiritFlavour(MalumSpiritTypes.ELDRITCH_SPIRIT, "Esoteric");
        addSpiritFlavour(MalumSpiritTypes.AERIAL_SPIRIT, "Swift");
        addSpiritFlavour(MalumSpiritTypes.AQUEOUS_SPIRIT, "Malleable");
        addSpiritFlavour(MalumSpiritTypes.INFERNAL_SPIRIT, "Radiant");
        addSpiritFlavour(MalumSpiritTypes.EARTHEN_SPIRIT, "Steady");
        addSpiritFlavour(MalumSpiritTypes.UMBRAL_SPIRIT, "Antithesis");

        add("malum.gui.slot", "Slot: ");

        add("malum.gui.augment.installed", "When installed: ");
        add("malum.gui.augment.type.augment", "Augment");
        add("malum.gui.augment.type.core_augment", "Core Augment");

        add(TotemicRiteType.TYPE, "Type: ");
        add(TotemicRiteType.MEDIUM, "Medium: ");
        add(TotemicRiteType.RUNEWOOD, "Runewood");
        add(TotemicRiteType.SOULWOOD, "Soulwood");
        add(TotemicRiteType.COVERAGE, "Coverage: ");
        add(TotemicRiteType.EFFECT, "Effect: ");

        add(GeasItem.GEAS, "Geas");
        add(GeasItem.SWORN, "When Sworn: ");
        add(GeasItem.CREATIVE, "Creative Item for Debug Purposes.");
        add(GeasItem.CREATIVE_HELP, "Use To Swear/Forswear Geas Effect.");

        addGeasDescription(MalumGeasEffectTypes.PACT_OF_DEFIANCE, "Rage, rage, against the dying of your might");
        addGeasDescription(MalumGeasEffectTypes.PACT_OF_THE_PARASITE, "Why work for what others have");
        addGeasDescription(MalumGeasEffectTypes.PACT_OF_THE_LIFEWEAVER, "Weave your life into miracles");

        addGeasDescription(MalumGeasEffectTypes.PACT_OF_THE_WARLOCK, "Weave the arcane");
        addGeasDescription(MalumGeasEffectTypes.PACT_OF_THE_REAPER, "Swear loyalty to the edge");
        addGeasDescription(MalumGeasEffectTypes.PACT_OF_THE_BERSERKER, "Your pain, their pain");

        addGeasDescription(MalumGeasEffectTypes.PACT_OF_THE_FORTRESS, "Be strong when you are needed");
        addGeasDescription(MalumGeasEffectTypes.PACT_OF_THE_SHIELD, "Always be ready to stand and fight");
        addGeasDescription(MalumGeasEffectTypes.PACT_OF_RECIPROCATION, "Prove your strength by wielding it");

        addGeasDescription(MalumGeasEffectTypes.PACT_OF_THE_SHATTERING_ADDICT, "Claim what you want, and never stop");
        addGeasDescription(MalumGeasEffectTypes.PACT_OF_THE_ARCANAPHAGE, "Seek magic in any form");
        addGeasDescription(MalumGeasEffectTypes.PACT_OF_RUNE_EXPLOITATION, "Gather them all, exhaust every possibility");

        addGeasDescription(MalumGeasEffectTypes.PACT_OF_SELF_CARE, "Eat, lest your body consume itself");
        addGeasDescription(MalumGeasEffectTypes.PACT_OF_THE_HIGH_PRIEST, "Become what they believe, so long as they believe");
        addGeasDescription(MalumGeasEffectTypes.PACT_OF_TIDAL_AFFINITY, "Become the ocean's avatar");
        addGeasDescription(MalumGeasEffectTypes.PACT_OF_PATIENCE_REPAID, "A warped echo");

        addGeasDescription(MalumGeasEffectTypes.PACT_OF_THE_WINDSWEPT, "Run as the wind");
        addGeasDescription(MalumGeasEffectTypes.PACT_OF_THE_CONTINUING_SHOT, "Step. Form. Ready. Raise. Begin. Draw. Release.");
        addGeasDescription(MalumGeasEffectTypes.PACT_OF_THE_CLOUDSKIPPER, "Dance along the edge of danger");
        addGeasDescription(MalumGeasEffectTypes.PACT_OF_THE_SKYBREAKER, "Move and be moved");

        addGeasDescription(MalumGeasEffectTypes.PACT_OF_CONTENTEDNESS, "To be full is to be anchored");
        addGeasDescription(MalumGeasEffectTypes.PACT_OF_THE_LONE_DRUID, "Shed your second skin");
        addGeasDescription(MalumGeasEffectTypes.PACT_OF_THE_PROFANE_ASCETIC, "Forswear indulgence, and be healed by rot");
        addGeasDescription(MalumGeasEffectTypes.PACT_OF_THE_PROFANE_GLUTTON, "Consume.");

        addGeasDescription(MalumGeasEffectTypes.PACT_OF_THE_FLAMEKEEPER, "Care for the flame");
        addGeasDescription(MalumGeasEffectTypes.PACT_OF_COMBUSTION, "Set yourself ablaze, figuratively");
        addGeasDescription(MalumGeasEffectTypes.PACT_OF_THE_PYROMANIAC, "Draw power from recklessness");
        addGeasDescription(MalumGeasEffectTypes.PACT_OF_WYRD_RECONSTRUCTION, "Witness oblivion and forge yourself anew");


//        addGeasDescription(MalumGeasEffectTypeRegistry.BOND_OF_BELOVED_CHAINS, "Tie your fates as one");
//        addGeasDescription(MalumGeasEffectTypeRegistry.BOND_OF_DEATHS_SEEKERS, "Find your ends together");

        addGeasDescription(MalumGeasEffectTypes.OATH_OF_THE_OVERKEEN_EYE, "Measure twice, cut once");
        addGeasDescription(MalumGeasEffectTypes.OATH_OF_THE_OVERBURDENED_MIND, "Measure carefully, cut later");
        addGeasDescription(MalumGeasEffectTypes.OATH_OF_THE_OVEREAGER_FIST, "Cut twice, never measure");

        addGeasDescription(MalumGeasEffectTypes.OATH_OF_UNMAKERS_DISDAIN, "Acknowledge no one, and be acknowledged by none");
        addGeasDescription(MalumGeasEffectTypes.OATH_OF_UNSIGHTED_RESISTANCE, "See no evil, feel no evil");
        addGeasDescription(MalumGeasEffectTypes.OATH_OF_THE_UNDISCERNED_MAW, "Take the life of your enemies");

        addGeasDescription(MalumGeasEffectTypes.AUTHORITY_OF_THE_INVERTED_HEART, "Your heart is the world, and the world beats");
        addGeasDescription(MalumGeasEffectTypes.AUTHORITY_OF_THE_GLEEFUL_TARGET, "Take it all, let it never stop, more, and more, and more");

        addGeasDescription(MalumGeasEffectTypes.CREED_OF_THE_BLIGHT_EATER, "Mmmm... Blight... So Tasty..");


        add("malum.waveform_artifice.wavecharger", "Redstone Easing Duration");
        add("malum.waveform_artifice.wavebanker", "Redstone Pulse Duration");
        add("malum.waveform_artifice.wavemaker", "Redstone Pulse Interval");
        add("malum.waveform_artifice.wavebreaker", "Redstone Pulse Delay");

        add("malum.waveform_artifice.value_display", ": %1$s %2$s");

        add("malum.waveform_artifice.redstone_ticks", "Redstone Ticks");
        add("malum.waveform_artifice.seconds", "Seconds");
        add("malum.waveform_artifice.minutes", "Minutes");

        add("malum.waveform_artifice.guide.2", "Scroll To Fine Tune Value");
        add("malum.waveform_artifice.guide.1", "Use Left Button To Modify Unit Type");
        add("malum.waveform_artifice.guide.0", "Release Right Button To Confirm");


        addRiteEffectCategory(TotemicRiteEffect.MalumRiteEffectCategory.AURA);
        addRiteEffectCategory(TotemicRiteEffect.MalumRiteEffectCategory.LIVING_ENTITY_EFFECT);
        addRiteEffectCategory(TotemicRiteEffect.MalumRiteEffectCategory.DIRECTIONAL_BLOCK_EFFECT);
        addRiteEffectCategory(TotemicRiteEffect.MalumRiteEffectCategory.RADIAL_BLOCK_EFFECT);
        addRiteEffectCategory(TotemicRiteEffect.MalumRiteEffectCategory.ONE_TIME_EFFECT);

        addRite(MalumSpiritRiteTypes.SACRED_RITE, "Rite of Healing", "Rite of Nourishment");
        addRite(MalumSpiritRiteTypes.WICKED_RITE, "Rite of Decay", "Rite of Empowerment");
        addRite(MalumSpiritRiteTypes.EARTHEN_RITE, "Rite of Warding", "Rite of the Arena");
        addRite(MalumSpiritRiteTypes.INFERNAL_RITE, "Rite of Haste", "Rite of the Hells");
        addRite(MalumSpiritRiteTypes.AERIAL_RITE, "Rite of Motion", "Rite of the Aether");
        addRite(MalumSpiritRiteTypes.AQUEOUS_RITE, "Rite of Loyalty", "Rite of the Seas");

        addRite(MalumSpiritRiteTypes.ARCANE_RITE, "Undirected Rite", "Unchained Rite");

        addRite(MalumSpiritRiteTypes.ELDRITCH_SACRED_RITE, "Rite of Growth", "Rite of Lust");
        addRite(MalumSpiritRiteTypes.ELDRITCH_WICKED_RITE, "Rite of Exorcism", "Rite of Culling");
        addRite(MalumSpiritRiteTypes.ELDRITCH_EARTHEN_RITE, "Rite of Destruction", "Rite of Shaping");
        addRite(MalumSpiritRiteTypes.ELDRITCH_INFERNAL_RITE, "Rite of Smelting", "Rite of Quickening");
        addRite(MalumSpiritRiteTypes.ELDRITCH_AERIAL_RITE, "Rite of Gravity", "Rite of Unwinding");
        addRite(MalumSpiritRiteTypes.ELDRITCH_AQUEOUS_RITE, "Rite of Sapping", "Rite of Drowning");

        add("malum.gui.ritual.type", "Ritual Type: ");
        add("malum.gui.ritual.tier", "Ritual Tier: ");

        add("jukebox_song.malum.arcane_elegy", "Kultik - Arcane Elegy");
        add("jukebox_song.malum.aesthetica", "Kultik - Aesthetica");

        add("curios.identifier.brooch", "Brooch");
        add("curios.modifiers.brooch", "When worn:");

        add("curios.identifier.rune", "Rune");
        add("curios.modifiers.rune", "When equipped:");

        add("malum.effect.positive", "+%s");
        add("malum.effect.negative", "-%s");

        addCurioEffect("scythe_execution", "Scythes Exploit Wounds");
        addCurioEffect("crits", "Critical Strikes");
        addCurioEffect("low_health_speed", "Speed at Low Health");
        addCurioEffect("shorten_negative_effect", "Shortens Negative Effects");
        addCurioEffect("burning_damage", "Burning Damage");

        addCurioEffect("scythe_chain", "Deadlier Scythe Sweeping");
        addCurioEffect("silence", "Silences Attackers");
        addCurioEffect("always_sprint", "Sprinting Always Available");
        addCurioEffect("extend_positive_effect", "Extends Positive Effects");
        addCurioEffect("burning_resistance", "Damage Resistance When Ablaze");

        addCurioEffect("totem_effect", "Grants %s");

        addCurioEffect("spirits_heal", "Spirit Collection Replenishes Health");
        addCurioEffect("spirits_xp", "Spirit Collection Generates Experience Points");
        addCurioEffect("spirits_extend_effect", "Spirit Collection Aids Potion Durations");
        addCurioEffect("spirits_weave_mana", "Spirit Collection Recovers Soul Ward");
        addCurioEffect("spirits_weave_mana_irons_spellbooks", "Spirit Collection Recovers Spell Mana [Iron's Spellbooks]");
        addCurioEffect("spirits_add_health", "Spirit Collection Grants Extra Hearts");
        addCurioEffect("spirits_weave_resonance", "Spirit Collection Generates Arcane Resonance");
        addCurioEffect("spirits_weave_resonance_irons_spellbooks", "Spirit Collection Reduces Spell Cooldown [Iron's Spellbooks]");
        addCurioEffect("hunger_drain", "Actively Drains Hunger");
        addCurioEffect("eat_rotten", "Rotten Foods are Tastier");
        addCurioEffect("growing_gluttony", "Eating Rotten Foods Extends Gluttony");
        addCurioEffect("explosion_drops_collected", "Automatic Collection of Explosion Drops");
        addCurioEffect("bigger_explosions", "Improves Explosions");
        addCurioEffect("no_sweep", "Disables Scythe Sweeping");
        addCurioEffect("enhanced_maneuvers", "Augments Rebound and Ascension");
        addCurioEffect("ascension_launch", "Ascension Launches Targets Upwards");
        addCurioEffect("lower_ascension_damage", "Ascension Suffers a Damage Penalty");
        addCurioEffect("rebound_maelstrom", "Rebound Creates A Windborne Maelstrom");
        addCurioEffect("longer_rebound_cooldown", "Rebound Suffers a Longer Cooldown");
        addCurioEffect("friendly_enemies", "Reduces Enemy Aggression");
        addCurioEffect("soul_ward_magic_resilience", "Soul Ward Reroutes Magic");
        addCurioEffect("soul_ward_long_shatter_cooldown", "Lengthy Soul Ward Recharge upon Disintegration");
        addCurioEffect("rotten_gluttony", "Eating Rotten Food Generates Gluttony");
        addCurioEffect("scythe_counterattack", "Powerful Scythe Counterattack When Struck");
        addCurioEffect("pacifist_recharge", "Cooldown Extends if the Scythe is Used");
        addCurioEffect("full_health_fake_collection", "Striking Full Health Targets Triggers Spirit Collection Effects");
        addCurioEffect("soul_ward_complete_absorption", "Soul Ward Absorbs All Damage");
        addCurioEffect("soul_ward_escalating_integrity", "Soul Ward Gains Integrity When Nearing Disintegration");
        addCurioEffect("spirits_gluttony", "Spirit Collection Generates Gluttony");
        addCurioEffect("enchanted_explosions", "Explosions are Enchanted with %s");
        addCurioEffect("explosions_spare_valuables", "Protects Valuable Items from Explosions");

        addGeasEffect("faster_natural_healing", "Saturation Heals Faster");
        addGeasEffect("spirits_absorption", "Spirit Collection Grants Absorption");
        addGeasEffect("passive_healing", "Passive Health Recovery");
        addGeasEffect("healing_aura", "Healing is Shared with Nearby Creatures");
        addGeasEffect("healing_aura_no_filter", "Shared Healing Heals All, Ally or Not");
        addGeasEffect("first_hit_bonus", "Blasts Healthy Enemies With Wicked Energy");
        addGeasEffect("aggressive_enemies", "Increases Enemy Aggression");
        addGeasEffect("scythe_combo", "Scythe Cuts Create Combo-Attacks");
        addGeasEffect("only_scythe", "Regular Weapons Crumble In Your Hands");
        addGeasEffect("damage_buildup", "Taking Damage Charges Up Wrath");
        addGeasEffect("damage_release", "Wrath Is Released When Striking A Target");
        addGeasEffect("more_damage", "All Incoming Damage Is Doubled");
        addGeasEffect("chained_spirit_bonus", "Repeated Soul Shatters Yield Extra Arcana");
        addGeasEffect("hunger_as_withdrawal", "Addiction to Slaughter");
        addGeasEffect("spirits_magic_boost", "Spirit Collection Amplifies Magic");
        addGeasEffect("oops_all_magic", "All Incoming Damage Functions As Magic");
        addGeasEffect("more_runes", "2 Rune Slots");
        addGeasEffect("rune_vulnerability", "Each Equipped Rune Dampens Healing, Armor and Magic Resistance");
        addGeasEffect("more_saturation", "Meals Provide Extra Saturation");
        addGeasEffect("food_effect_cleanse", "Eating Cleanses Negative Effects");
        addGeasEffect("faster_starving", "Starvation Occurs Faster");
        addGeasEffect("high_hunger_more_armor", "Being Well Fed Provides Extra Armor");
        addGeasEffect("low_hunger_less_armor", "Starvation Halves Armor");
        addGeasEffect("no_armor_armor", "The Absence of Equipped Armour Becomes Armor");
        addGeasEffect("no_armor", "Equipped Armour Damages You");
        addGeasEffect("bonus_reach", "Avoiding Harm Increases Reach");
        addGeasEffect("fragile_reach", "Reach Effect Faces Cooldown When Struck");
        addGeasEffect("fragile_reach_damage", "The High Priest's Rule Grips You Tightly");
        addGeasEffect("water_agility", "You Move and Swing Faster In Water");
        addGeasEffect("water_damage_resistance", "You Take Reduced Damage in Water");
        addGeasEffect("conduit_bonus", "Conduit Power Heals You And Amplifies Damage Reduction");
        addGeasEffect("fish_healing", "You Cannot Heal Outside Of Water");
        addGeasEffect("buffered_damage", "Half Of Incoming Damage Is Buffered");
        addGeasEffect("buffered_damage_non_lethal", "Buffered Damage Is Applied Overtime And Cannot Kill You");
        addGeasEffect("buffered_damage_more_overall", "Buffered Damage Is Amplified");
        addGeasEffect("movement_acceleration", "Sprinting Builds Up Extra Momentum");
        addGeasEffect("knockback_also_accelerates", "High Momentum Amplifies Received Knockback");
        addGeasEffect("faster_draw_time", "Successful Ranged Damage Builds Up Extra Draw Speed");
        addGeasEffect("missing_halts_draw_time", "Missing Inverts The Benefit");
        addGeasEffect("soul_ward_on_hit", "Magic Damage Recovers Soul Ward");
        addGeasEffect("incoming_fall_damage_auto_attack", "Taking Fall Damage Attacks Nearby Targets");
        addGeasEffect("outgoing_fall_damage_auto_attack", "Outgoing Fall Damage Attacks Targets Regardless Of Distance");
        addGeasEffect("more_knockback", "Doubles Incoming Knockback");
        addGeasEffect("rocket_jumping", "Wind Charges Provide Greater Propulsion");
        addGeasEffect("wind_charge_exhaustion", "Continuous Activations Weigh You Down");
        addGeasEffect("weak_legs", "Fall Damage Is Amplified");
        addGeasEffect("mining_buffs", "Breaking Blocks Or Taking Damage Grants Flamekeeper's Fervor");
        addGeasEffect("flamekeeper", "Flamekeeper's Fervor grants Mining and Attack Speed");
        addGeasEffect("self_immolation", "High Fervor Briefly Ignites You When Struck");
        addGeasEffect("hotter_fire", "Fire Effects You Apply Are Accelerated");
        addGeasEffect("extinguish_hurt", "Being Extinguished Hurts You");
        addGeasEffect("explosion_lover", "Absorbed Explosions Grant Pyromaniac's Fervor");
        addGeasEffect("pyromaniac", "Pyromaniac's Fervor grants Mining and Movement Speed");
        addGeasEffect("explosion_resistance", "You Gain Powerful Explosion Resistance");
        addGeasEffect("explosion_fire", "High Fervor Ignites You Upon Blast Impact");
        addGeasEffect("scary_fire", "Fire Damage Is Amplified");
        addGeasEffect("trial_of_faith", "Gluttony Becomes Trial of Faith");
        addGeasEffect("trial_of_faith_healing", "Trial of Faith Increases Healing Received");
        addGeasEffect("rotten_healing", "Eating Rotten Foods Heals You");
        addGeasEffect("no_passive_healing", "Saturation And Hunger No Longer Restore Health");
        addGeasEffect("no_normal_foods", "You Must Only Eat Rot");
        addGeasEffect("desperate_need", "Gluttony Becomes Desperate Need");
        addGeasEffect("desperate_need_scythe_proficiency", "Desperate Need Substantially Increases Scythe Proficiency");
        addGeasEffect("desperate_need_betrayal", "The Rot Will Consume You");
        addGeasEffect("wyrd_reconstruction", "Death Triggers Resurrection");
        addGeasEffect("wyrd_reconstruction_spirits", "Resurrection Repeatedly Activates Spirit-Collection Effects");
        addGeasEffect("wyrd_reconstruction_cooldown", "Arcane Resonance is Dampened Until Recharged");
        addGeasEffect("spirits_hunger", "Spirit Collection Drains Hunger");
        addGeasEffect("malignant_crit_leech", "Malignant Deliverance Leeches Life Essence");
        addGeasEffect("malignant_crit_healing_overexertion", "Repeated Activations Gradually Diminish All Healing");
        addGeasEffect("malignant_crit_combo", "Malignant Deliverance Repeatedly Slashes Targets");
        addGeasEffect("malignant_crit_health_condition", "Prevents Malignant Deliverance When Wounded");
        addGeasEffect("malignant_crit_reinforcement", "Malignant Deliverance Reinforces Armor");
        addGeasEffect("malignant_crit_reduced_damage", "Reduces Malignant Deliverance Damage");
        addGeasEffect("staff_homing", "Staff Projectiles Home In on Targets");
        addGeasEffect("staff_autofire", "Staff Charges Automatically Fire");
        addGeasEffect("inverted_heart", "Injuries, Emotions, Senses are Shared with Witnesses");
        addGeasEffect("inverted_heart_arcane_resonance", "Arcane Resonance Favors Influence Radius");
        addGeasEffect("gleeful_target", "Ailments, Blessings, Curses are Paused When Struck");
        addGeasEffect("gleeful_target_arcane_resonance", "Arcane Resonance Aids Stasis Duration");
        addGeasEffect("presence_breaker", "Refusal, Detachment, Exile is Forced onto Targets");
        addGeasEffect("presence_breaker_arcane_resonance", "Arcane Resonance Favors Exile Duration");

        add("malum.effect.soul_based_damage", "Deals Soul-Rending Damage");
        add("malum.effect.weight_of_worlds_crit", "Sometimes Strikes With Critical Force");
        add("malum.effect.weight_of_worlds_kill", "Kills Guarantee a Critical Strike");
        add("malum.effect.edge_of_deliverance_crit", "Follow-up Strikes Hit Critically");
        add("malum.effect.edge_of_deliverance_unpowered_attack", "Non-Critical Strikes Deal Reduced Damage");
        add("malum.effect.hex_bolts", "Charges a Burst of Mnemonic Blades");
        add("malum.effect.erosive_spread", "Charges a Spread of Eroding Sub-munitions");
        add("malum.effect.erosive_silence", "Erosion Damage Silences Targets");
        add("malum.effect.unwinding_chaos_volley", "Charges a Volley of Composite Energy");
        add("malum.effect.unwinding_chaos_burn", "Burn Damage Recovers Charges");
        add("malum.effect.sundering_anchor_damage_split", "Damage is Split Between Several Cuts");
        add("malum.effect.sundering_anchor_hatred", "Each Cut Applies Hatred");

        add("malum.effect.wayne_june.0", "The Iron Crown. Enigmatic, and Ubiquitous");
        add("malum.effect.wayne_june.1", "A Semi-Circle, Radiating Five Points of Power. A Symbol Hidden Deep in the Iconography of Every Ancient Empire");
        add("malum.effect.wayne_june.2", "The Point of No Return Welcomes You, With Open Arms");
        add("malum.effect.wayne_june.3", "The Greatest Horror It Would Seem, Is Nothing At All");
        add("malum.effect.wayne_june.4", "An Ocean of Emptiness, Slowly Swallowing the World");
        add("malum.effect.wayne_june.5", "A Nebulous Nightmare, an Apocalypse that Only We Can Oppose");
        add("malum.effect.wayne_june.6", "Stagnant And Sprawling, This Hellish Abyss Extends Beyond Sanity Itself");
        add("malum.effect.wayne_june.7", "We Travel Through The Incalculable Dimensions Of Human Weakness");
        add("malum.effect.wayne_june.8", "Success, So Long Pursued, Is Rewarded Only With Creeping Revalation");
        add("malum.effect.wayne_june.9", "You Have Cowered In Your Cowering Denial Long Enough");
        add("malum.effect.wayne_june.10", "Let Us Drag Your Agglutinated Indignities Out Into The Light");

        add("malum.jei.spirit_infusion", "Spirit Infusion");
        add("malum.jei.spirit_focusing", "Spirit Focusing");
        add("malum.jei.spirit_repair", "Spirit Repair");
        add("malum.jei.spirit_rite", "Spirit Rites");
        add("malum.jei.runeworking", "Runeworking");
        add("malum.jei.weeping_well", "The Weeping Well");
        add("malum.jei.spirit_transmutation", "The Unchained Rite");

        add("itemGroup.malum_basis_of_magic", "Malum: Study of Miracles");
        add("itemGroup.malum_arcane_construct", "Malum: The Earth Imbued");
        add("itemGroup.malum_scars_of_arcana", "Malum: Scars of Arcana");
        add("itemGroup.malum_metallurgic_magics", "Malum: Imitation Alchemy");
        add("itemGroup.malum_geas", "Malum: Sworn Oaths");
        add("itemGroup.malum_ritual_shards", "Malum: Ritual Shards");
        add("itemGroup.malum_cosmetics", "Malum: One's True Self");

        addDeathMessage(MalumDamageTypes.VOODOO, "%1$s had their soul shattered by %2$s", "%1$s had their soul shattered by %2$s using %3$s");
        addPlayerlessDeathMessage(MalumDamageTypes.VOODOO_PLAYERLESS, "%1$s had their soul shattered", "%1$s had their soul shattered while trying to escape %2$s");

        addPlayerlessDeathMessage(MalumDamageTypes.VOID, "%1$s underwent reality erosion", "%1$s underwent reality erosion while trying to escape %2$s");
        addPlayerlessDeathMessage(MalumDamageTypes.KARMIC, "%1$s was forsworn", "%1$s was forsworn while trying to escape %2$s");
        addPlayerlessDeathMessage(MalumDamageTypes.ROT, "%1$s was consumed by rot", "%1$s was consumed by rot while trying to escape %2$s");

        addDeathMessage(MalumDamageTypes.NITRATE, "%1$s had their soul detonated by %2$s", "%1$s had their soul detonated by %2$s using %3$s");
        addPlayerlessDeathMessage(MalumDamageTypes.NITRATE_PLAYERLESS, "%1$s had their soul detonated", "%1$s had their soul detonated while trying to escape %2$s");

        addDeathMessage(MalumDamageTypes.SCYTHE_MELEE, "%1$s was sliced in half by %2$s", "%1$s was sliced in half by %2$s using %3$s");
        addDeathMessage(MalumDamageTypes.SCYTHE_SWEEP, "%1$s was sliced in half by %2$s", "%1$s was sliced in half by %2$s using %3$s");
        addDeathMessage(MalumDamageTypes.SCYTHE_REBOUND, "%1$s was boomeranged by %2$s", "%1$s was boomeranged by %2$s using %3$s");
        addDeathMessage(MalumDamageTypes.SCYTHE_ASCENSION, "%1$s was cleaved using ascension by %2$s", "%1$s was cleaved using ascension by %2$s using %3$s");
        addDeathMessage(MalumDamageTypes.SCYTHE_COMBO, "%1$s was sliced in half and then again by %2$s", "%1$s was sliced in half and then again by %2$s using %3$s");
        addDeathMessage(MalumDamageTypes.SCYTHE_MAELSTROM, "%1$s was sliced and diced by %2$s", "%1$s was sliced and diced by %2$s using %3$s");

        addDeathMessage(MalumDamageTypes.HIDDEN_BLADE_PHYSICAL_COUNTER, "%1$s was sliced into innumerable pieces by %2$s", "%1$s was sliced into innumerable pieces by %2$s using %3$s");
        addDeathMessage(MalumDamageTypes.HIDDEN_BLADE_MAGIC_COUNTER, "%1$s had their soul sliced into innumerable pieces by %2$s", "%1$s had their soul sliced into innumerable pieces by %2$s using %3$s");

        addDeathMessage(MalumDamageTypes.TYRVING, "%1$s had their soul scarred by %2$s", "%1$s had their soul scarred by %2$s using %3$s");

        addDeathMessage(MalumDamageTypes.SUNDERING_ANCHOR_PHYSICAL_COMBO, "%1$s was struck down through torment by %2$s", "%1$s was struck down through torment by %2$s using %3$s");
        addDeathMessage(MalumDamageTypes.SUNDERING_ANCHOR_MAGIC_COMBO, "%1$s had their soul struck down through torment by %2$s", "%1$s had their soul struck down through torment by %2$s using %3$s");

        addDeathMessage(MalumDamageTypes.WARLOCK_SPIRIT_IMPACT, "%1$s had their soul shattered by %2$s", "%1$s had their soul shattered by %2$s using %3$s");
        addDeathMessage(MalumDamageTypes.BERSERKER_SPIRIT_IMPACT, "%1$s had their soul shattered by %2$s", "%1$s had their soul shattered by %2$s using %3$s");

        addDeathMessage(MalumDamageTypes.INVERTED_HEART_PROPAGATION, "%1$s was caught in %2$s's karmic flow", "%1$s was caught in %2$s's karmic flow using %3$s");
        addDeathMessage(MalumDamageTypes.INVERTED_HEART_RETALIATION, "%1$s was caught in %2$s's karmic flow", "%1$s was caught in %2$s's karmic flow using %3$s");

        addJEEDEffectDescription(MalumMobEffects.GAIAS_BULWARK, "An earthen carapace surrounds your body, functioning as extra armor.");
        addJEEDEffectDescription(MalumMobEffects.EARTHEN_MIGHT, "Your fists and tools are reinforced with earth, increasing your strength.");
        addJEEDEffectDescription(MalumMobEffects.MINERS_RAGE, "Your tools are bolstered with radiance, increasing your mining and attack speed.");
        addJEEDEffectDescription(MalumMobEffects.IFRITS_EMBRACE, "The warm embrace of fire coats your soul, mending your seared scars.");
        addJEEDEffectDescription(MalumMobEffects.ZEPHYRS_COURAGE, "The zephyr propels you forward, increasing your movement speed.");
        addJEEDEffectDescription(MalumMobEffects.AETHERS_CHARM, "The heavens call for you, increasing jump height and decreasing gravity.");
        addJEEDEffectDescription(MalumMobEffects.POSEIDONS_GRASP, "You reach out for further power, increasing your reach and item pickup distance.");
        addJEEDEffectDescription(MalumMobEffects.ANGLERS_LURE, "Let any fish who meets my gaze learn the true meaning of fear; for I am the harbinger of death. The bane of creatures sub-aqueous, my rod is true and unwavering as I cast into the aquatic abyss. A man, scorned by this uncaring Earth, finds solace in the sea. My only friend, the worm upon my hook. Wriggling, writhing, struggling to surmount the mortal pointlessness that permeates this barren world. I am alone. I am empty. And yet, I fish.");

        addJEEDEffectDescription(MalumMobEffects.ASCENSION, "Eases your fall and reduces gravity after a successful Scythe Ascenison.");
        addJEEDEffectDescription(MalumMobEffects.GLUTTONY, "You feed on the vulnerable, increasing magic proficiency at the expense of hunger./");
        addJEEDEffectDescription(MalumMobEffects.CANCEROUS_GROWTH, "You are emboldened by uncontrolled growth, increasing maximum health.");
        addJEEDEffectDescription(MalumMobEffects.ECHOING_ARCANA, "You are made wiser by uncontrolled magnification, increasing arcane resonance.");
        addJEEDEffectDescription(MalumMobEffects.WICKED_INTENT, "You bring forth a powerful counter attack, your next scythe attack will unleash an impossible volley of cuts.");
        addJEEDEffectDescription(MalumMobEffects.SILENCED, "You are silenced, leaving your magical capabilities neutered.");
        addJEEDEffectDescription(MalumMobEffects.GRIM_CERTAINTY, "The Weight of Worlds oscillates, sealing the next strike as a critical blow.");
        addJEEDEffectDescription(MalumMobEffects.IMMINENT_DELIVERANCE, "The Edge of Deliverance oscillates, sealing it's next strike as a critical blow.");

        addTetraMaterial("malignant_pewter", "Malignant Pewter");
        addTetraMaterial("soul_stained_steel", "Soulstained Steel");
        addTetraMaterial("hallowed_gold", "Hallowed Gold");
        addTetraMaterial("runewood", "Runewood");
        addTetraMaterial("soulwood", "Soulwood");
        addTetraMaterial("tainted_rock", "Tainted Rock");
        addTetraMaterial("twisted_rock", "Twisted Rock");
        addTetraMaterial("soulwoven_silk", "Soulwoven Silk");

        addTetraImprovement("malum.soul_strike", "Soul Strike", "The item's material allows it to strike the soul.");

        addEnchantmentNameAndDescription(EnchantmentKeys.ANIMATED, "Improves attack speed.");
        addEnchantmentNameAndDescription(EnchantmentKeys.HAUNTED, "Improves the Weapon's Magic Damage");
        addEnchantmentNameAndDescription(EnchantmentKeys.SPIRIT_PLUNDER, "Reaps extra Spirits when shattering a soul.");

        addEnchantmentNameAndDescription(EnchantmentKeys.REBOUND, "Allows the Scythe to be thrown when used.");
        addEnchantmentNameAndDescription(EnchantmentKeys.ASCENSION, "Enables the Scythe to propel the player upwards, damaging nearby enemies when used.");

        addEnchantmentNameAndDescription(EnchantmentKeys.REPLENISHING, "Restores Spell Charges when dealing melee damage with the staff.");
        addEnchantmentNameAndDescription(EnchantmentKeys.CAPACITOR, "Adds additional Spell Charges for use with the staff");

        addAttributeLibAttributeDescription(MalumAttributes.SCYTHE_PROFICIENCY, "Damage multiplier for Scythes");
        addAttributeLibAttributeDescription(MalumAttributes.SPIRIT_SPOILS, "Flat increase to spirits looted from slain foes");
        addAttributeLibAttributeDescription(MalumAttributes.ARCANE_RESONANCE, "Bonus potency for spirit-collection effects");

        addAttributeLibAttributeDescription(MalumAttributes.HEALING_MULTIPLIER, "An increase in healing received");

        addAttributeLibAttributeDescription(MalumAttributes.SOUL_WARD_INTEGRITY, "A percentile increase in durability for Soul Ward");
        addAttributeLibAttributeDescription(MalumAttributes.SOUL_WARD_RECOVERY_RATE, "A percentile increase in recovery rate for Soul Ward");
        addAttributeLibAttributeDescription(MalumAttributes.SOUL_WARD_RECOVERY_MULTIPLIER, "A percentile increase the amount of Soul Ward recovered");
        addAttributeLibAttributeDescription(MalumAttributes.SOUL_WARD_CAPACITY, "The capacity for Soul Ward");

        addAttributeLibAttributeDescription(MalumAttributes.CHARGE_DURATION, "The duration for charging a staff");
        addAttributeLibAttributeDescription(MalumAttributes.CHARGE_CAPACITY, "The capacity for Spell Charges");
        addAttributeLibAttributeDescription(MalumAttributes.CHARGE_RECOVERY_RATE, "A percentile increase in recovery rate for Spell Charges");

        addAttributeLibAttributeDescription(MalumAttributes.GEAS_LIMIT, "The limit for active Geas Bindings that can be sworn at once");

        addAttributeLibAttributeDescription(MalumAttributes.MALIGNANT_CONVERSION, "A percentile conversion rate in which certain magical attributes are converted into armor, armor toughness and magic resistance");

    }

    @Override
    public String getName() {
        return "Malum Lang Entries";
    }

    public String makeProper(String s) {
        s = s
                .replaceAll("Of", "of")
                .replaceAll("The", "the")
                .replaceAll("Soul Stained", "Soulstained")
                .replaceAll("Soul Hunter", "Soulhunter");
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }

    public void addCurioEffect(String identifier, String name) {
        add("malum.effect.curio." + identifier, name);
    }

    public void addGeasEffect(String identifier, String name) {
        add("malum.effect.geas." + identifier, name);
    }
    public void addRite(TotemicRiteType riteType, String basicName, String corruptName) {
        add(riteType.getLangKey(false), basicName);
        add(riteType.getLangKey(true), corruptName);
    }

    public void addRiteEffectCategory(TotemicRiteEffect.MalumRiteEffectCategory category) {
        add(category.getTranslationKey(), DataHelper.toTitleCase(category.name().toLowerCase(), "_"));
    }

    public void addGeasDescription(Holder<GeasEffectType> effectType, String description) {
        add(effectType.value().getDescription(), description);
    }

    public void addSpiritFlavour(SpiritHolder<MalumSpiritType> spiritType, String flavour) {
        add(spiritType.value().getFlavourKey(), flavour);
    }

    public void addTetraMaterial(String identifier, String name) {
        add("tetra.material." + identifier, name);
        add("tetra.material." + identifier + ".prefix", name);
    }

    public void addTetraImprovement(String identifier, String name, String description) {
        add("tetra.improvement." + identifier + ".name", name);
        add("tetra.improvement." + identifier + ".description", description);
    }

    public void addPlayerlessDeathMessage(ResourceKey<DamageType> damageType, String base, String player) {
        final String key = "death.attack." + damageType.location().getPath();
        add(key, base);
        add(key + ".player", player);
    }

    public void addDeathMessage(ResourceKey<DamageType> damageType, String base, String item) {
        final String key = "death.attack." + damageType.location().getPath();
        add(key, base);
        add(key + ".item", item);
    }

    public void addEnchantmentNameAndDescription(ResourceKey<Enchantment> enchantment, String desc) {
        var name = enchantment.location().getPath();
        var key = "enchantment.malum." + name;
        add(key, DataHelper.toTitleCase(name, "_"));
        add(key + ".desc", desc);
    }

    public void addAttributeLibAttributeDescription(DeferredHolder<Attribute, Attribute> attribute, String desc) {
        add("attribute.name.malum." + attribute.getId().getPath() + ".desc", desc);
    }

    public void addJEEDEffectDescription(Supplier<MobEffect> mobEffectSupplier, String description) {
        add(mobEffectSupplier.get().getDescriptionId() + ".description", description);
    }

    public String correctSoundName(String name) {
        if ((name.endsWith("_step"))) {
            return "footsteps";
        }
        if ((name.endsWith("_place"))) {
            return "block_placed";
        }
        if ((name.endsWith("_break"))) {
            return "block_broken";
        }
        if ((name.endsWith("_hit"))) {
            return "block_breaking";
        }
        return name;
    }

    public String correctItemName(String name) {
        if (name.contains("music_disc")) {
            return "music_disc";
        }
        if ((!name.endsWith("_bricks"))) {
            if (name.contains("bricks")) {
                name = name.replaceFirst("bricks", "brick");
            }
        }
        if ((!name.endsWith("_boards"))) {
            if (name.contains("boards")) {
                name = name.replaceFirst("boards", "board");
            }
        }
        if (name.contains("_fence") || name.contains("_button")) {
            if (name.contains("planks")) {
                name = name.replaceFirst("_planks", "");
            }
        }
        if (name.startsWith("trans_")) {
            //TODO: replace this with just...
            // replace(ItemRegistry.WEAVERS_WORKBENCH.get(), this::makeProperEnglish);
            // no need to run the damn code on every single item, while filtering prideweaves
            return name;
        }
        return makeProperEnglish(name);
    }

    public String makeProperEnglish(String name) {
        String[] replacements = new String[]{"ns_", "rs_", "ts_"};
        String properName = name;
        for (String replacement : replacements) {
            int index = properName.indexOf(replacement);
            if (index != -1) {
                properName = properName.replaceFirst("s_", "'s_");
                break;
            }
        }
        return properName;
    }
}
