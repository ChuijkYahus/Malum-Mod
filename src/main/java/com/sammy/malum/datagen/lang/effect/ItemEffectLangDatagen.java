package com.sammy.malum.datagen.lang.effect;

import com.sammy.malum.core.helpers.*;
import com.sammy.malum.datagen.lang.*;

public class ItemEffectLangDatagen {

    public static void addTranslations() {

        addPouchEffect("soulwoven_pouch_collection", "Collects Spirit Arcana");
        addPouchEffect("ravenous_pouch_collection", "Snatches Items Already In Storage");
        addPouchEffect("ravenous_pouch_drop", "Retains A Minimum When Emptied");

        addGearEffect("ravenous_scythe_gluttony", "Amasses Gluttony From Struck Targets");
        addGearEffect("gluttonous_bludgeon_locusts", "Sprouts Damaging Locusts When Hitting Targets");
        addGearEffect("soul_based_damage", "Deals Soul-Rending Damage");
        addGearEffect("weight_of_worlds_crit", "Sometimes Strikes With Critical Force");
        addGearEffect("weight_of_worlds_kill", "Kills Guarantee a Critical Strike");
        addGearEffect("edge_of_deliverance_crit", "Follow-up Strikes Hit Critically");
        addGearEffect("edge_of_deliverance_unpowered_attack", "Non-Critical Strikes Deal Reduced Damage");
        addGearEffect("hex_bolts", "Charges a Burst of Mnemonic Blades");
        addGearEffect("erosive_spread", "Charges a Spread of Eroding Sub-munitions");
        addGearEffect("erosive_silence", "Erosion Damage Silences Targets");
        addGearEffect("unwinding_chaos_volley", "Charges a Volley of Composite Energy");
        addGearEffect("unwinding_chaos_burn", "Burn Damage Recovers Charges");
        addGearEffect("sundering_anchor_damage_split", "Damage is Split Between Several Cuts");
        addGearEffect("sundering_anchor_hatred", "Each Cut Applies Hatred");

//        addMiscEffect("wayne_june.0", "The Iron Crown. Enigmatic, and Ubiquitous");
//        addMiscEffect("wayne_june.1", "A Semi-Circle, Radiating Five Points of Power. A Symbol Hidden Deep in the Iconography of Every Ancient Empire");
//        addMiscEffect("wayne_june.2", "The Point of No Return Welcomes You, With Open Arms");
//        addMiscEffect("wayne_june.3", "The Greatest Horror It Would Seem, Is Nothing At All");
//        addMiscEffect("wayne_june.4", "An Ocean of Emptiness, Slowly Swallowing the World");
//        addMiscEffect("wayne_june.5", "A Nebulous Nightmare, an Apocalypse that Only We Can Oppose");
//        addMiscEffect("wayne_june.6", "Stagnant And Sprawling, This Hellish Abyss Extends Beyond Sanity Itself");
//        addMiscEffect("wayne_june.7", "We Travel Through The Incalculable Dimensions Of Human Weakness");
//        addMiscEffect("wayne_june.8", "Success, So Long Pursued, Is Rewarded Only With Creeping Revalation");
//        addMiscEffect("wayne_june.9", "You Have Cowered In Your Cowering Denial Long Enough");
//        addMiscEffect("wayne_june.10", "Let Us Drag Your Agglutinated Indignities Out Into The Light");
    }

    public static void addGearEffect(String identifier, String name) {
        add(TooltipComponentHelper.ITEM + identifier, name);
    }

    public static void addPouchEffect(String identifier, String name) {
        add(TooltipComponentHelper.POUCH + identifier, name);
    }


    protected static void add(String key, String value) {
        MalumLangDatagen.lang.add(key, value);
    }
}