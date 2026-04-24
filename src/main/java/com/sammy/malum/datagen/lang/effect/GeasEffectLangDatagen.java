package com.sammy.malum.datagen.lang.effect;

import com.sammy.malum.core.handlers.*;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.datagen.lang.*;

public class GeasEffectLangDatagen {

    public static void addTranslations() {
        //Sacred 1
        addGeasEffect("faster_natural_healing", "Saturation Heals Faster");
        //Sacred 2
        addGeasEffect("spirits_absorption", "Spirit Collection Grants Absorption");
        //Sacred 3
        addGeasEffect("passive_healing", "Passively Recover Health");
        addGeasEffect("healing_aura", "Healing is Shared with Nearby Creatures");
        addGeasEffect("healing_aura_no_filter", "Shared Healing Heals All, Ally or Not");

        //Wicked 1
        addGeasEffect("first_hit_bonus", "Blasts Healthy Enemies With Wicked Energy");
        addGeasEffect("aggressive_enemies", "Increases Enemy Aggression");
        //Wicked 2
        addGeasEffect("scythe_combo", "Scythe Cuts Create Combo-Attacks");
        addGeasEffect("only_scythe", "Regular Weapons Crumble In Your Hands");
        //Wicked 3
        addGeasEffect("damage_buildup", "Taking Damage Charges Up Wrath");
        addGeasEffect("damage_release", "Wrath Is Released When Striking A Target");
        addGeasEffect("more_damage", "All Incoming Damage Is Doubled");

        //Arcane 3
        addGeasEffect("soul_ward_on_hit", "Magic Damage Recovers Soul Ward");


        //Eldritch 1
        addGeasEffect("chained_spirit_bonus", "Repeated Soul Shatters Yield Extra Arcana");
        addGeasEffect("hunger_as_withdrawal", "Addiction to Slaughter");
        //Eldritch 2
        addGeasEffect("spirits_magic_boost", "Spirit Collection Amplifies Magic");
        addGeasEffect("oops_all_magic", "All Incoming Damage Functions As Magic");
        //Eldritch 3
        addGeasEffect("more_runes", "2 Rune Slots");
        addGeasEffect("rune_vulnerability", "Each Equipped Rune Dampens Healing, Armor and Magic Resistance");

        //Aqueous 1
        addGeasEffect("more_saturation", "Meals Provide Extra Saturation");
        addGeasEffect("food_effect_cleanse", "Eating Cleanses Negative Effects");
        addGeasEffect("faster_starving", "Starvation Occurs Faster");
        //Aqueous 2
        addGeasEffect("bonus_reach", "Avoiding Harm Increases Reach");
        addGeasEffect("fragile_reach", "Reach Effect Faces Cooldown When Struck");
        addGeasEffect("fragile_reach_slowdown", "Cooldown State Reduces Movement Speed");
        //Aqueous 3
        addGeasEffect("buffered_damage", "Half Of Incoming Damage Is Buffered");
        addGeasEffect("buffered_damage_non_lethal", "Buffered Damage Is Applied Overtime And Cannot Kill You");
        addGeasEffect("buffered_damage_more_overall", "Buffered Damage Is Amplified");

        //Aerial 1
        addGeasEffect("movement_acceleration", "Sprinting Builds Up Extra Momentum");
        addGeasEffect("knockback_also_accelerates", "High Momentum Amplifies Received Knockback");
        //Aerial 2
        addGeasEffect("faster_draw_time", "Successful Ranged Damage Builds Up Extra Draw Speed");
        addGeasEffect("missing_halts_draw_time", "Missing Inverts The Benefit");
        //Aerial 3
        addGeasEffect("incoming_fall_damage_auto_attack", "Taking Fall Damage Attacks Nearby Targets");
        addGeasEffect("outgoing_fall_damage_auto_attack", "Outgoing Fall Damage Attacks Targets Regardless Of Distance");
        addGeasEffect("more_knockback", "Doubles Incoming Knockback");

        //Earthen 1
        addGeasEffect("high_hunger_more_armor", "Being Well Fed Provides Extra Armor");
        addGeasEffect("low_hunger_less_armor", "Starvation Halves Armor");
        //Earthen 2
        addGeasEffect("no_armor_druid_armor", "The Absence of Equipped Armour Grants Armor And Enhances Healing");
        addGeasEffect("no_armor", "Equipped Armour Shackles You When Vulnerable");
        //Earthen 3
        addGeasEffect("trial_of_faith", "Gluttony Becomes Trial of Faith");
        addGeasEffect("rotten_healing", "Eating Rotten Foods Heals You");
        addGeasEffect("no_passive_healing", "Saturation And Hunger No Longer Restore Health");
        addGeasEffect("no_normal_foods", "You Must Only Eat Rot");

        //Infernal 1
        addGeasEffect("hotter_fire", "Fire Effects You Apply Are Accelerated");
        addGeasEffect("extinguish_hurt", "Being Extinguished Hurts You");

        //Infernal 2

        //Infernal 3
        addGeasEffect("wyrd_reconstruction", "Death Triggers Resurrection");
        addGeasEffect("wyrd_reconstruction_spirits", "Resurrection Repeatedly Activates Spirit-Collection Effects");
        addGeasEffect("wyrd_reconstruction_cooldown", "Arcane Resonance is Dampened Until Recharged");
        addGeasEffect("spirits_hunger", "Spirit Collection Drains Hunger");

//        addGeasEffect("desperate_need", "Gluttony Becomes Desperate Need");
//        addGeasEffect("desperate_need_vulnerability", "Desperate Need Amplifies Damage Taken");
//        addGeasEffect("desperate_need_poison", "Starvation Brings Poison");

        addGeasEffect("malignant_crit_leech", "Malignant Deliverance Leeches Life Essence");
        addGeasEffect("malignant_crit_healing_overexertion", "Repeated Activations Gradually Diminish All Healing");
        addGeasEffect("malignant_crit_combo", "Malignant Deliverance Repeatedly Slashes Targets");
        addGeasEffect("malignant_crit_combo_reinforcement", "Slashes Grow Stronger Through Malignant Conversion");
        addGeasEffect("malignant_crit_aegis_rerouting", "Malignant Deliverance Recharges Malignant Aegis");
        addGeasEffect("malignant_crit_reduced_damage", "Recharge Reduces Malignant Deliverance Damage");
        addGeasEffect("staff_homing", "Staff Projectiles Home In on Targets");
        addGeasEffect("staff_autofire", "Staff Charges Automatically Fire");
        addGeasEffect("inverted_heart", "Injuries, Emotions, Senses are Shared with Witnesses");
        addGeasEffect("inverted_heart_arcane_resonance", "Arcane Resonance Favors Influence Radius");
        addGeasEffect("gleeful_target", "Ailments, Blessings, Curses are Paused When Struck");
        addGeasEffect("gleeful_target_arcane_resonance", "Arcane Resonance Aids Stasis Duration");
        addGeasEffect("presence_breaker", "Refusal, Detachment, Exile is Forced onto Targets");
        addGeasEffect("presence_breaker_arcane_resonance", "Arcane Resonance Favors Exile Duration");
    }

    public static void addGeasEffect(String identifier, String name) {
        add(EffectComponentHelper.GEAS + identifier, name);
    }

    protected static void add(String key, String value) {
        MalumLangDatagen.lang.add(key, value);
    }
}