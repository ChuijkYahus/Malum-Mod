package com.sammy.malum.datagen.lang;

import com.sammy.malum.client.screen.codex.pages.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.core.systems.registry.rite.RiteHolder;
import com.sammy.malum.core.systems.rite.*;
import com.sammy.malum.registry.common.magic.*;
import com.sammy.malum.registry.common.magic.rite.*;
import net.minecraft.core.*;
import team.lodestar.lodestone.helpers.DataHelper;

public class CodexLangDatagen {

    private static String obfuscate(String s) {
        return "$k" + s + "/$";
    }

    //Only supported by geas info pages
    private static String scaled(float s) {
        return "$m" + s + "/$";
    }

    private static String italic(String s) {
        return "$i" + s + "/$";
    }

    private static String bold(String s) {
        return "$b" + s + "/$";
    }

    private static String strike(String s) {
        return "$s" + s + "/$";
    }

    private static String underline(String s) {
        return "$u" + s + "/$";
    }

    private static void addSimpleRiteEntry(RiteHolder<SpiritRiteType> holder, String entryDescription, String riteDescription, String effect) {
        SpiritRiteType rite = holder.value();
        String name = rite.getName();
        addEntryHeader(name, DataHelper.toTitleCase(name, "_"), entryDescription);
        addRiteDetails(holder, riteDescription, effect);
    }
    private static void addRiteDetails(RiteHolder<SpiritRiteType> holder, String description, String effect) {
        SpiritRiteType rite = holder.value();
        add(rite.getCodexEntryLangKey(), description);
        add(rite.getEffectLangKey(), effect);
    }

    private static void addGeasDetails(Holder<GeasEffectType> geasEffectType, String geasPositives, String geasNegatives) {
        final GeasEffectType geas = geasEffectType.value();
        add(geas.getDetailedPros(), geasPositives);
        add(geas.getDetailedCons(), geasNegatives);
    }

    private static void addEntryHeader(String identifier, String name, String description) {
        add("malum.gui.book.entry." + identifier, name);
        addDescription(identifier, description);
    }

    private static void addSimpleEntryHeader(String identifier, String name, String description) {
        addHeadline(identifier, name);
        addEntryHeader(identifier, name, description);
    }

    private static void addPage(String identifier, String page) {
        add(BookPage.TEXT + "." + identifier, page);
    }

    private static void addPages(String identifier, String... pages) {
        int i = 1;
        for (String s : pages) {
            addPage(identifier + "." + i++, s);
        }
    }

    private static void addDescription(String identifier, String tooltip) {
        add("malum.gui.book.entry." + identifier + ".description", tooltip);
    }

    private static void addHeadline(String identifier, String tooltip) {
        add(BookPage.HEADLINE + "." + identifier, tooltip);
    }

    private static void addRecipeDescriptor(String identifier, String tooltip) {
        add("malum.gui.book.entry.page.info." + identifier, tooltip);
    }

    private static void add(String key, String value) {
        MalumLangDatagen.lang.add(key, value);
    }

    public static void generateEntries() {
        addSimpleEntryHeader("chronicles_of_the_void", "Chronicles of the Void", "A magecraft of madness");
        addSimpleEntryHeader("chronicles_of_the_soul", "Chronicles of the Soul", "A magecraft of miracles");

        addRecipeDescriptor("spirit_infusion", "Spirit Infusion. \nRequires a Prime Item and Spirit Arcana to be held within The Altar.");
        addRecipeDescriptor("spirit_infusion.spirit", "Spirit Arcana is to be stored directly within the Spirit Altar.");
        addRecipeDescriptor("spirit_infusion.item", "Additional Items must be held on nearby Item Pedestals or Stands.");

        addRecipeDescriptor("runeworking", "Runeworking. \nRequires a slate for the inscription process. Spirit Arcana must be inserted afterwards.");

        addRecipeDescriptor("soulbinding", "Soul Binding. \nPhysical and Arcane ingredients are to be inserted into the Brazier. \nAn Etheric Blood Offering initiates the process");

        addRecipeDescriptor("unchained_transmutation", "Unchained Transmutation. \nTransforms blocks into other blocks via Unchained Rite. Requires Blight as a conduit.");
        addRecipeDescriptor("unchained_transmutation_tree", "Unchained Transmutation. \nA series of block transformations via Unchained Rite. Requires Blight as a conduit.");

        addRecipeDescriptor("spirit_focusing", "Spirit Focusing. \nRequires a Catalyst and Spirit Arcana to be held within The Crucible. The Catalyst is not consumed, but does get damaged.");
        addRecipeDescriptor("spirit_focusing.spirit", "Spirit Arcana is to be stored directly within the Spirit Crucible.");

        addRecipeDescriptor("spirit_repair", "Spirit Repair. \nRequires a Repair Material and Spirit Arcana to be held within The Repair Pylon.");
        addRecipeDescriptor("spirit_repair.spirit", "Spirit Arcana is to be stored directly within the Repair Pylon.");
        addRecipeDescriptor("spirit_repair.damaged", "The Damaged Item is to be provided within a nearby Item Pedestal, Stand, or a Spirit Crucible.");

        addSimpleEntryHeader("void.the_weeping_well", "The Weeping Well", "Gate to the unknown");
        addPages("void.the_weeping_well",
                "I have discovered a... structure. One with implications beyond nearly anything I've found before, because the existence of this Weeping Well implies I am not the first to touch the arcana.",
                "The Well, which I name for its constant mournful tone, appears to be a small pool of... something. I am not entirely sure what, as though it doesn't appear to be liquid, it certainly doesn't physically interact like a solid does.",
                "The implication comes from the fact that the structure around it appears to be constructed of Tainted Rock, as if preventing the substance inside from spreading further. That said, the core of the structure itself is sturdier than the rock I have created, almost approaching Bedrock in toughness.",
                "In what was perhaps an ill-advised course of action, my first instinct was to throw a rock at it. It seemed stable enough, if it had been here for so long... and to my relief, nothing dangerous occurred. The rock shot out seconds later, with a belching sound and a spray of what appears to be a harmless concentration of the stuff in the Well.",
                "Certain items I had on me from the mining trip, such as a bit of Brilliance and a nodule of Cthonic Gold, tugged slightly against my pockets while I'm near the well. I wondered what that meant, and, if the stone was ejected out, surmised it made sense to try throwing them in too.",
                "When I did so, the materials had been transformed once ejected - interestingly, both the rock and these were ejected due south. Might there be other materials, other creations I could derive from this? And the fact that both materials that tugged were related to the arcana is fascinating.",
                "I am not a historian, so I will not investigate the cultural implications, beyond the fact that Tainted Rock, so far as I know, can only be created through Spirit Infusion. This implies a civilization once touched the arcana and... tried to contain it? Feared it? Used it? Is this civilization related to the other structures buried within the earth? I do not know, but I hope to find out.");

        addEntryHeader("void.material_study_soulstone", "Material Study: Soulstone", "An old friend");
        addHeadline("void.material_study_soulstone", "Study: Soulstone");
        addPages("void.material_study_soulstone",
                "Soulstone is, of course, nothing new. It is the basis of my craft. But, when most raw metals are passed through the Well, they become raw Soulstone instead.",
                "Does this imply that Soulstone itself is metallic? Perhaps. It would certainly explain the ease with which it attunes iron. This also implies that Soulstone itself is, somehow, connected to whatever the Well is. Perhaps it is an ensouled area of reality, or a means of bestowing souls? Both are doubtful, but I cannot discard even doubtful theories just yet.");

        addEntryHeader("void.material_study_mnemonic_fragment", "Material Study: Condensed Brilliance", "Not experience, but memory");
        addHeadline("void.material_study_mnemonic_fragment", "Study: Mnemosyne");
        addPages("void.material_study_mnemonic_fragment",
                "When passed through the Well, Brilliance becomes a substance I call Mnemosyne. These Mnemonic Fragments appear to be Brilliance in physical property, but with the contained power more condensed, more nuanced.",
                "Rather than containing simple experience, the sense I get from holding it is as though I am holding an entire memory, context and all.\nDoes this imply the Weeping Well is alive, and that this is its memory? Or is it collecting the memory of the soul which created the Brilliance in the first place, binding it into this more dense form? And why can I feel impressions while simply holding the stone?");

        addSimpleEntryHeader("void.material_study_mnemonic_fragment.reexamination", "Reexamination: Mnemnosyne", "Patterns holding true");
        addPages("void.material_study_mnemonic_fragment.reexamination",
                "Mnemnosyne appears to have the same internal patterning as Brilliance, but on a much smaller and more detailed scale. Presumably this is what gives it its properties.");

        addEntryHeader("void.material_study_null_slate", "Material Study: Refined Soulstone", "A blank slate, perhaps a precursor");
        addHeadline("void.material_study_null_slate", "Study: Null Slate");
        addPages("void.material_study_null_slate",
                "When passed through the Well, Soulstone becomes a substance I call Null Slate. While physically it is similar to Soulstone, it appears to be utterly devoid of a soul... and yet it interacts with the arcane much as Soulstone does.",
                "Might this be what Soulstone is, before it gains a soul's energy? I cannot determine how it interacts with souls, nor have I been able to transfer that property, as I have done to make Soulstained Steel. My only theory is that somehow the complete absence of arcana, beyond even the trace amounts threading existence, has an arcane power of its own. But what power would that be?");

        addSimpleEntryHeader("void.material_study_null_slate.reexamination", "Reexamination: Null Slate", "Attuned to Umbral");
        addPages("void.material_study_null_slate.reexamination",
                "A complete absence of arcana creating an effect. It should be obvious what Null Slate truly is - Soulstone, but attuned to the Void instead of the arcane.",
                "Perhaps still a precursor, or maybe they are related in other ways... The physical makeup is the same as Soulstone's. It follows that the differing properties are purely from the medium the stone interacts with.");

        addEntryHeader("void.material_study_void_salts", "Material Study: Purified Ash", "A clue to the nature of souls");
        addHeadline("void.material_study_void_salts", "Study: Void Salts");
        addPages("void.material_study_void_salts",
                "When passed through the Well, Hex Ash is reduced to a substance I call Void Salt. This dark powder appears to be chemically similar to Hex Ash, but without the carbonization that gives the Ash its name.",
                "It appears to be comprised of an unknown and unstable metal, bonded to something caustic I cannot identify. It is baffling beyond the physical, though. Carbon is the basis of life, and yet removing carbon makes this substance... almost seem to move? I haven't been able to verify that experimentally, but I could swear that the material is alive and shifting.");

        addSimpleEntryHeader("void.material_study_void_salts.reexamination", "Reexamination: Void Salts", "Concerning");
        addPages("void.material_study_void_salts.reexamination",
                "The fact that " + italic("absence") + " is creating " + italic("presence") + " implies Umbral. Perhaps it " + italic("is") + " alive... but with life defined by deeper nothingness instead of the presence of matter. Might this indicate there is more complex life adapted to the Void?");

        addEntryHeader("void.material_study_auric_embers", "Material Study: Blazing Exaltation", "The essence of progress");
        addHeadline("void.material_study_auric_embers", "Study: Auric Ember");
        addPages("void.material_study_auric_embers",
                "When passed through the Well, Blaze Powder becomes a substance I call Auric Ember. An ethereal flame of gold, yet a physical object at the same time. It burns like charcoal, yet its flame has no combustion.",
                "While such a brilliant substance may stand out among the other materials I have obtained from the Well, it fits the pattern cleanly. The Well has stripped something away from each material I pass through it, be that impurities, a portion of matter, or something more esoteric. Here, it appears to strip away anything besides the purity of fire, producing transformation incarnate.");

        addSimpleEntryHeader("void.material_study_auric_embers.reexamination", "Reexamination: Auric Ember", "Not stripping away, but inverting");
        addPages("void.material_study_auric_embers.reexamination",
                "The Well does not, as I previously thought, strip things away. It inverts them, replacing them with voidish counterparts. Auric Ember is perhaps the purest example of this - anything that is not the essence of progress is cast in void shadow, creating a material that is " + italic("more") + " than perfectly attuned to a purpose.");

        addEntryHeader("void.material_study_malignant_lead", "Material Study: Putrefacted Gold", "Perfection cast to base");
        addHeadline("void.material_study_malignant_lead", "Study: Malignant Lead");
        addPages("void.material_study_malignant_lead",
                "When passed through the Well, Cthonic Gold is warped into Malignant Lead. Much of the Arcana bonded to the gold resolves itself into Wicked, dragging the metal from the alchemist's apex to the lowest of materials.",
                "There appears to be roughly half as much arcana bound to the metal as before. Was it stripped away by the Well, or transmuted into something I have not yet isolated and identified? Either could be true. This might be related to the arcane interactivity displayed by Null Slate...");

        addEntryHeader("void.material_study_malignant_lead.reexamination", "Reexamination: Malignant Lead", "Wicked and Umbral");
        addHeadline("void.material_study_malignant_lead.reexamination", "Reexamining Malignant Lead");
        addPages("void.material_study_malignant_lead.reexamination",
                "Malignant Lead is at the moment my only means of assessing the physical form of an Umbral Crystal - for what else could have caused it to lose precisely half of its arcane weight?",
                "I have not had success in isolating the microcrystals like I have with Cthonic Gold or the Wicked crystals in the lead, but until I can obtain more, this is my best avenue to research Umbral's structure.");

        addEntryHeader("spirit_metals.reexamination", "Reexamination: Spirit Metals", "A different principle");
        addHeadline("spirit_metals.reexamination", "Reexamining Spirit Metals");
        addPages("spirit_metals.reexamination",
                "The inherent structures of the arcana simply don't exist in spirit metals, and yet they interact with the arcane all the same. There might be something analogous, but if so, I hardly have instruments precise enough to measure it. Why is this is different from the raw stones I have investigated, with even Soulstained Steel differing in structure vastly from Soulstone?",
                "This might explain why Cthonic Gold has arcana fused into its structure, unlike the spirit metals - they might be different phenomena, one a metal 'tuned' to the arcana, another physically alloyed with it. I wonder if I can tune other metals similarly...");

        addEntryHeader("spirit_stones.reexamination", "Reexamination: Spirit Stones", "Tessellation of crystal structures");
        addHeadline("spirit_stones.reexamination", "Reexamining Spirit Stones");
        addPages("spirit_stones.reexamination",
                "It appears that the crystal structures of at least Wicked and Sacred crystals can be tessellated infinitely, as that is exactly what my deconstruction and analysis of samples of Twisted and Tainted Rocks has found them to be. Is it possible that other spirit stones - or rather, tessellations - might exist? Very likely.",
                "But what would their properties be? Sacred and Wicked create stone which accept or reject magic... so might Aerial and Earthen create stone that either moves or locks magic in place? Redundant for both, with Hallowed Gold... Infernal might be used to accelerate while Aqueous mutates, though, which might be useful.",
                "I don't think I have the proper context to identify what Arcane, Eldritch, and Umbral might create. I lack the full understanding of what they represent, as destructive testing isn't an option for Umbral as of yet. But, with the proximity of bedrock to the Void... might bedrock's indestructibility be a sign of the Umbral stone?");

        addEntryHeader("cthonic_gold.reexamination", "Reexamination: Cthonic Gold", "A new principle in old material");
        addHeadline("cthonic_gold.reexamination", "Reexamining Cthonic Gold");
        addPages("cthonic_gold.reexamination",
                "I now know why I could not create Cthonic Gold - I did not understand the principles it was built on. Fusing crystal into matter is not something unique to this material. It may be possible to force crystals to form inside of objects to make similar 'natural' arcane alloys.",
                "Either I need to find a way to phase the crystal through solid matter, or I need to find a way to cause a similar effect to the Spirit Jar's crystal formation in a medium other than air. Either one might allow me to make Cthonic Gold, and potentially much, much more.");

        addEntryHeader("spirit_minerals.reexamination", "Reexamination: Soulstone and Brilliance", "More than just arcana");
        addHeadline("spirit_minerals.reexamination", "Reexamining Spirit Minerals");
        addPages("spirit_minerals.reexamination",
                "I now know the arcana have physical properties with meaning. I can use these properties to create a staff, and likely for many more things. But Soulstone and Brilliance... as far as I can tell, for every other base material relevant to the arcana save Runewood I work with, there are either spirit crystals fused within or patterned in the structure.",
                "The fact that Null Slate is physically identical to Soulstone implies a greater principle. I posit that Soulstone and Brilliance have similar fundamental resonances to the arcana, structures of inherent power at a precision far beyond what I can measure. This implies interaction with the arcane is simply... a quirk of an object's topology? I don't know how I would replicate it myself, but if I can...");

        addEntryHeader("strange_crystal.reexamination", "Reexamination: Strange Crystal", "Striking resemblance");
        addHeadline("strange_crystal.reexamination", "Reexamining Strange Crystals");
        addPages("strange_crystal.reexamination",
                "Mnemosyne. It bears a striking resemblance to the Strange Crystal, though they appear unrelated. I am unsure what that means.");

        addEntryHeader("strange_crystal.revelation", "Revelation: Strange Crystal", "A strange kind of life");
        addHeadline("strange_crystal.revelation", "Revealing Strange Crystals");
        addPages("strange_crystal.revelation",
                "Aha. This crystal... it betrays deeper meaning, but is worthless of itself. It bears the marks of othering found in the Fused Consciousness. It is not a mind, but it is a strange, impossible kind of life. Perhaps the striations are the physical encoding of its life...");

        addEntryHeader("void.catalyst_lobber", "Catalyst Lobber", "Progress overtakes");
        addHeadline("void.catalyst_lobber", "Catalyst Lobber");
        addPages("void.catalyst_lobber",
                "The flame of progress is a potent one, which bulldozes everything in its search for advancement. There had to be a destructive way to harness it, and so there was.",
                "I've created a device out of a pair of lamplighter's tongs I call the Catalyst Lobber. It \"unlocks\" Auric Embers by retuning them, turning their flame from a gentle one into an explosive blaze, containing the result until it's ready to fire.",
                "I implemented a safety, because... well, I don't want to rebuild my lab again. The flames are violently explosive. Standard explosive precautions work just as well, such as obsidian, of course.");

        addSimpleEntryHeader("fragment.void.black_crystal", "Scribbled notes", "Incomprehensible");
        addPages("fragment.void.black_crystal",
                italic("You attempt to read the entry, but the text seems to slide off the eyes, escaping from your mind every time you grasp it. What little fragments stick with you form an impression of something besides these materials being cast into the Well..."));

        addSimpleEntryHeader("void.black_crystal", "A Black Crystal", "A mistake, or a boon?");
        addPages("void.black_crystal",
                "Well, I now know what happens when a living being, or at least, a sapient one, enters the Well.",
                "I had grown too comfortable in my experiments, and tripped over one of the flasks of reagent I had left around... directly into the Well's black maw. As I'm writing this, clearly I survived... Though I'd rather not test that again.",
                "I was spat out by what I now know is liquid, much like the items I have thrown in. Further tests with monsters and cattle showed they do " + italic("not") + " get rejected, simply seeming to vanish into the pool, and I see no reason to waste resources to test that exhaustively. Especially with my attention set on what came back out with me.",
                "As if I had shattered a soul - and considering mine was the only one present, that is a concerning possibility, though all readings of myself I have taken are within tolerances - a black spirit crystal emerged from the Well alongside me, which I collected. Does this herald a ninth arcana? If so, what impulse does it represent? This will need more study.");

        addSimpleEntryHeader("fragment.void.umbral_arcana", "Strange equations", "Assuming an absence of existence...");
        addSimpleEntryHeader("void.umbral_arcana", "Umbral Arcana", "Utter impossibility");
        addPages("void.umbral_arcana",
                "I do not understand this arcana. What is it? It isn't any of the eight I know, and barely seems like one at all... yet a spirit crystal it remains. It can be contained in jars like the others, shares many of the same properties... But there is one deep and fundamental difference.",
                "This crystal " + italic("does not exist.") + " That is not to say that it cannot be obtained, or touched, or even seen. There is no matter there. This... " + italic("Umbral") + " arcana is a void. Would that make it the opposite of both Arcane and Eldritch? Both lack direction of impulse, and both are power. Ergo, Umbral arcana is the absence of power, where impulse is irrelevant.",
                "It is possible there are two types of this arcana, I suppose. One to pair with the null impulse of the Arcane, and one to pair with the complete impulse of the Eldritch. If those types exist, it is functionally impossible to distinguish them. They " + italic("must") + " act the same. Without power, the impulse is meaningless.",
                "And yet, despite being a void, it is power. Or perhaps the lack of power creates a pressure differential? I am not certain. Either way, it can be used. Infused, in theory, even, although that is hard to wrap my head around. It acts like matter, but is not. It is power, and the absence of it.",
                "My research into this arcana must continue. It has to. If anything holds the secrets of achieving the pinnacle of thaumaturgy, it is this paradoxical void. And I will grasp it with both hands.");

        addEntryHeader("fragment.void.inverse_and_hybrid_arcana", "A failed experiment", "An attempt to create something new?");
        addEntryHeader("void.inverse_and_hybrid_arcana", "Inverse and Hybrid Arcana?", "Failed theories");
        addHeadline("void.inverse_and_hybrid_arcana", "Theoretical Arcana");
        addPages("void.inverse_and_hybrid_arcana",
                "If Arcane and Eldritch had inverses, it stood to reason that there might be inverses for the other spirit crystals - an absolute absence of fire creating the inverse of Infernal, for example. Pulling the power of a spirit from a jar is one way to form a crystal, and in theory, recreating similar conditions could allow for a different type of crystal to form.",
                "Through a combination of Soulwood rites and careful placement of crystals, I was able to create the theoretical environment for such a crystal for the six base Arcana... But nothing happened at all. It would be far more useful if I could determine how to create the same environment for Umbral, but as of yet I have not determined how to remove " + italic("all") + " arcana from an area.",
                "Similarly, no amount of tuning an environment's contents with combinations of arcana was able to cause a crystal to form other than one of the base six. This implies that hybrid arcana don't exist, at least, not in the same way - that there are a finite number of states the arcana can be stable in. But the arcana can combine in other ways...");

        addEntryHeader("void.material_study_arcana", "Material Study: the Arcana", "New depths to old wells");
        addHeadline("void.material_study_arcana", "Study: Spirit Crystal");
        addPages("void.material_study_arcana",
                "I had not investigated deeper into the physical properties of the spirit crystals before now. This can be excused, as I was focused on their magical implications rather than their physical... but it isn't only the Umbral spirit that portrays strange properties. They all do.",
                "First and foremost, the spirit crystals - assumedly also the Umbral spirit, though I only have one - are physically identical to others of their type to any degree I am able to discern. Not simply similar, but " + italic("precisely") + " the same, down to at least millionths of a block's scale. This raised the question, of course, of attempting to carve or break part of one.",
                "Through testing - not quite " + italic("exhaustively,") + " as I have not tested Umbral - it appears that shape is the only one a crystal can exist in. Any removal or damage of even the smallest amount of the crystal is impossible, and the physical shape remains inviolable until enough force is applied to break it.",
                "Even more interestingly, the spirit crystals, while physically not particularly strong, appear to be utterly chemically inert, and I tried a very extensive set of reactants. Do they even have matter, in the traditional sense? And if not, what does that mean for the Umbral crystal?",
                "I am not sure of the precise implications of these physical properties, but given the strangeness of the Umbral crystal, there is clearly more to the more mundane aspect of the arcana than I knew.");

        addSimpleEntryHeader("void.staves_as_foci", "Staves as Foci", "Imitating the arcana");
        addPages("void.staves_as_foci",
                "Consideration of the properties I observed in spirit crystals led me to wonder if the structure itself was important somehow. To test this, I constructed a Mnemnosyne replica - the condensed soul memory being the closest substance I could think of - of the Wicked Arcana, precise to a scale of hundreds of thousandths of a block.",
                "I did not expect it to explode in my face.\n\nBut explosions are useful, if harnessed. So I did it again, but this time, I gave the false crystal a structure to operate off of - a housing of Soulwood and Soulstained Steel to direct it outwards. The resulting staff acts akin to a rite in miniature when I focus on it, though the resulting effects are different.",
                "When used in melee, staves are... adequate. I would tend to prefer a scythe, but I suppose this works well enough. They tend to focus more on magic damage than on physical damage, which admittedly can be helpful at times. But what makes them special is what happens if I " + italic("use") + " it.",
                "This Mnemonic Hex Staff launches balls of liquefied Wicked, like a witch's hex. It takes a moment to activate, and seems to be limited in how much it can be used at once, but each use deals potent damage to anything they hit. The staff recharges itself from my soul, and because of this, charge will be distributed over any staves I have.",
                "I have not found success in creating any other false Arcana this way, though that might be a matter of material. Mnemnosyne may simply resonate with Wicked through the death required to create Brilliance.");

        addEntryHeader("void.staves_as_foci.enchanting", "Staff Enchanting", "Imitated efficacy");
        addHeadline("void.staves_as_foci.enchanting.replenishing", "Replenishing");
        addPages("void.staves_as_foci.enchanting.replenishing",
                "A staff is a conduit of physical magic. Obviously, it can flow out, and flow in, but it is possible to do that less passively as well. When a Replenishing staff is used in melee, it will occasionally restore the staff's energy pool by about one third of a shot's worth. Modifying the staff this way makes it unable to accept Capacitor.");
        addHeadline("void.staves_as_foci.enchanting.capacitor", "Capacitor");
        addPages("void.staves_as_foci.enchanting.capacitor",
                "Spirit crystals can only hold so much power, as I have seen... but that is only true of the strange not-physical matter of the arcana. Capacitor allows the staff to accumulate more energy for the same physical space, allowing it to store about one shot more per level. Modifying the staff this way makes it unable to accept Replenishing.");

        addSimpleEntryHeader("void.staves_as_foci.ring_of_the_endless_well", "Ring of the Endless Well", "Storing staff charges");
        addPages("void.staves_as_foci.ring_of_the_endless_well",
                "I have recreated the Arcane spirit in false form. A core of Mnemnosyne in Null Slate housing appears to neutralize the Wicked attunement of the Mnemnosyne, leaving raw arcana as the pattern it mimics. The false Arcane spirit appears to hold a charge, one very similar to the Wicked crystal in my staff.",
                "I have fashioned a ring - the Endless Well - with these false crystals in housing. The ring appears to concentrate my focus, allowing me to hold staff charges in reserve. The ring charges slower, as it is not a symbolic " + italic("focus") + " like my staff, but it can hold three attacks in reserve for faster use.");

        addSimpleEntryHeader("void.malignant_pewter", "Malignant Pewter", "Progress cast false");
        addPages("void.malignant_pewter",
                "Now that I have identified the composition of Malignant Lead, it is easier to plan usages for it. As one might expect from Umbral's strangeness, alloying it creates a metal with fascinating properties. As Cthonic Gold was brought low to make the Lead, this Pewter seeks to do to others. It seeks to erode, to unmake, to unwind man's advances.",
                "While this property has its mundane uses - damaging armor more when used as a weapon, along with shattering souls - it has esoteric ones as well. Malignant Pewter isn't magically inert, nor an absorber of magic. Instead, it's as if magic cannot " + italic("form") + " within or around it; symbolically laying the pursuit of knowledge low.",
                "The metal itself is quite tough and takes a keen edge, but wearing an antimagic material would have its issues... and its uses. I have plans for a set of armor fashioned of it, which should prove quite potent, to harness these.");

        addEntryHeader("void.malignant_stronghold_armor", "Malignant Stronghold Armor", "The defense of the inevitable");
        addHeadline("void.malignant_stronghold_armor", "The Malignant Stronghold");
        addPages("void.malignant_stronghold_armor",
                "Malignant Pewter rejects magic, and as such, wearing it might seem an odd choice for a mage. And yet, it is " + italic("because") + " I am a mage that the Stronghold Armor I have made is so potent. It stands as an inverse to the Soulstained Steel armor beneath the Pewter plating - my soul is unwarded, but I am certainly not.",
                "In unraveling magic, energy still remains. While worn, the armor will react to that energy, growing stronger alongside it. Any strictly defensive or offensive magical attribute to be inscribed upon my soul is instead absorbed by the metal, granting armor and magic resistance bonuses. The only unique case I've found is magic proficiency, which the metal absorbs half as much.");

        addSimpleEntryHeader("void.weight_of_worlds", "The Weight of Worlds", "Existential burdens externalized");
        addPages("void.weight_of_worlds",
                "Harnessing the reality-corroding properties of Malignant Pewter is easy. What fits the revocation of progress better than a crude weapon? An axe, which I named the Weight of Worlds in a fit of pique. It is slow, it is clumsy, it is physical... and it is " + italic("powerful") + " beyond measure.",
                "Rarely, the Weight seems to lend its approval to my slaughter, doubling the force I strike with, with no seeming source. I believe slaughter is the key because of the resonance I feel when I take a life with it - that resonance seems to expend itself when I swing next, guaranteeing that doubling of force.");

        addSimpleEntryHeader("void.edge_of_deliverance", "The Edge of Deliverance", "A mad screamer's melody");
        addPages("void.edge_of_deliverance",
                "Progress can be revoked, and crude can overcome grand. But progress can be undone by progress, can it not? A scythe, familiar in ways the axe was not, which I name the Edge of Deliverance. It is the true opposite - where the Weight of Worlds is sharp and heavy, the Edge is thin and winding.",
                "This focuses the inconsistent force of the Weight of Worlds, seeking slaughter to wet the blade. Instead, a single attack's taste of blood empowers the scythe, creating an alternating pattern of killing strength.");

        addSimpleEntryHeader("void.erosion_scepter", "Erosion Scepter", "May mages fear my might");
        addPages("void.erosion_scepter",
                "A new false arcana has been created, using Malignant Pewter as a focus. In its corrosive reversal, the metal serves entropy - and so Eldritch answers its call. Like other uses of the metal, the resulting crystal in its Void Salt suspension seeks to unwind what humanity has wrought.",
                "The Erosion Scepter, made using this false crystal, is a staff which fires bolts of a crawling, corruptive substance - almost like Blight or the Void Salt used in its creation. Each charge fires two volleys, each of four bolts. These bolts chew away at the souls of their victims, which, delightfully, appears to inhibit the use of magic for a time.",
                "Each bolt applies another layer of this effect, reducing the effective magical might and soul force of the target by a tenth. Naturally, this can stack up to a complete seal on the target, and every application reinforces and lengthens the durations of the others.",
                "Striking a foe with the staff will inflict the same decay twice on the victim's soul, making it useful as more than just a weapon of desperation in close quarters. Not all magics require a stable soul, but the pain of the degradation of self should help distract them nonetheless.");

        addSimpleEntryHeader("void.ring_of_growing_flesh", "Ring of Growing Flesh", "Creeping and crawling");
        addPages("void.ring_of_growing_flesh",
                "I have overclocked the Ring of Curative Talent, creating a ring that does not simply heal, but overheal, granting additional health instead of regeneration whenever I collect a spirit crystal.");

        addSimpleEntryHeader("void.ring_of_echoing_arcana", "Ring of Echoing Arcana", "I can see see the future " + italic("future future"));
        addPages("void.ring_of_echoing_arcana",
                "Overclocking the Ring of Curative Talent created a useful ring, so I have applied that principle to another ring, Manaweaving, to not only bind, but create resonance in magic. The Ring of Echoing Arcana grants Arcane Resonance whenever I collect a spirit crystal, empowering all my other spirit-collection effects.");

        addEntryHeader("void.ring_of_gruesome_concentration", "Ring of Gruesome Concentration", "Consume and incorporate");
        addHeadline("void.ring_of_gruesome_concentration", "Gruesome Concentration");
        addPages("void.ring_of_gruesome_concentration",
                "Why would Gluttony be restricted to the collection of spirits? By eating foul food, I can make myself hungrier, and so cultivate and concentrate what remains, increasing my magical might even as I starve.");

        addSimpleEntryHeader("void.necklace_of_the_watcher", "Necklace of the Watcher", "It looks back");
        addPages("void.necklace_of_the_watcher",
                "Souls emit energy when damaged, not merely when shattered. This necklace allows me to harness that energy, causing effects that normally only occur when I collect a spirit to also happen when I strike an enemy at full health.");

        addEntryHeader("void.necklace_of_the_hidden_blade", "Necklace of the Hidden Blade", "A knife at their backs");
        addHeadline("void.necklace_of_the_hidden_blade", "The Hidden Blade");
        addPages("void.necklace_of_the_hidden_blade",
                "The Narrow Edge concentrates my scythe's edge, but the Hidden Blade goes further, inverting the edge and making it hunger. I lose the sweeping attack, yes, but as I am harmed, the blade drinks of my pain- feeding upon it to enable a seemingly impossible flurry of cuts and slashes.",
                "Upon activation, the necklace remains inert for a total of ten seconds, requiring rest and concentration in order to recover it's effect. Attacking at any point in this state will prolong this absence of function.",
                "The counterattack's nature is a strange one, one I have not fully identified. It is as though the blade asserts its existence in multiple places at once, demanding reality make it so.");

        //TODO: Fused Consciousness (and All/One overlay?)
        addSimpleEntryHeader("void.fused_consciousness", "Fused Consciousness", "The blueprint of everything that ever was and will be");
        addPages("void.fused_consciousness",
                "The Future Holds Many Secrets... But you may look at some of them early.\n Create the anomalous design by throwing an iron block into the well. Throw the complete design into the well for the fused consciousness.");

        addSimpleEntryHeader("void.sundering_anchor", "The Sundering Anchor", "What I hate, it hates in twain");
        addPages("void.sundering_anchor",
                "A knife which thinks, which feels, which hates. The Edge and the Weight are simple things, mere tools to use. The Anchor is more. It knows its enemy, and hates them for the crime of continuing to exist.",
                "It slashes with me, adding magic damage which scars the soul, forcing its Hatred upon the enemy for a time, applying the revocation of progress Malignant armor does by force.",
                "The blade also can be thrown, akin to Rebound. It will fly like an arrow, pursuing nearby targets with unwavering eagerness, even swerving in the air to strike them, inflicting Hatred and dealing extra damage with each stack of the effect. It will strike entire groups of Hated enemies, one by one, if many are nearby.");

        addSimpleEntryHeader("void.unwinding_chaos", "Unwinding Chaos", "The dying truth crawls");
        addPages("void.unwinding_chaos",
                "Fused Consciousness can add thought to action, a mind to impose its own will on the world. And this staff, this destruction, oh, how the Unwinding Chaos wills it be.",
                "It blasts enemies with some manner of esoteric effect, and can be charged for a volley. The nature of the attack is not Infernal but Eldritch, yet it burns all the same, dragging all closer to the promised end of Eldritch truth in utter entropy.",
                "The staff drinks in the pain of the flames and the death of the burning with glee, recharging its internal pool all the faster, advancing the cycle as it so dearly desires.");

        addSimpleEntryHeader("void.belt_of_the_limitless", "Belt of the Limitless", "Unfathomable love");
        addPages("void.belt_of_the_limitless",
                "Love. It is a curious thing, but the Belt of the Limitless loves without barrier, without condition. It sees you for what and who you are, and loves you despite. I cannot claim to be comfortable with that, though nor can I deny the utility.",
                "The Belt adds its own Soul Ward to the bearer's, doubling their capacity. The overlaid effects cause the Soul Ward to absorb " + italic("all") + " damage, not merely most. Integrity increases as the Ward grows weaker, giving me a defense when I need it most.",
                "Unfortunately, it is not wholly benefit. The harmonization of the barriers of two minds is taxing, and Soul Ward suffers in its recovery from this exhaustion. I consider the trade worthwhile.");

        addSimpleEntryHeader("introduction", "Introduction", "On the nature of souls");
        addPages("introduction",
                "\"Within our world, every living being has a soul. That soul is consciousness, what animates the body, and the meeting point between matter and magic. These represent our existence; as the body is presented to the physical world, so the soul is to the arcane.\"",
                "I seem to have stumbled upon something peculiar: a form of magic so far undocumented. I could hardly call myself a magus if I refused the opportunity to study it. In this codex, the Encyclopedia Arcana, I write my research into this power, hoping to document everything about it.",
                "The energies this thaumaturgical discipline manipulates seem to be rooted in the soul. More accurately, they are the energies of the soul, the inclinations and impulses that make up each one of us.",
                "So far, what I have described is basic. But I have found a way to separate, and then condense, the impulse of a soul into a physical form I call a spirit crystal. This forms the basis of my research.",
                "The natures of the soul I condense influence the crystal's properties. Each soul is slightly different, and that can result in changes to the crystals formed. I believe these spirit crystals to be just the breakthrough I need.");

        addSimpleEntryHeader("spirit_crystals", "Spirit Crystals", "Matter and magic");
        addPages("spirit_crystals",
                "The soul is a notoriously fickle thing. Even confirming its existence is difficult, requiring the highest thaumaturgies to get a reading. That is what sets spirit arcana apart from other magic. We don't need grand assemblies and esoteric artifice to see a soul. Simply destroying it is proof enough.",
                "A material I have named Soulstone is the means by which we do so. It appears mundane until refined, but once it is rid of impurities, it seems... out of phase with the world. By creating a blade using it as a core, I should be able to strike not only the physical form, but also the soul, shattering it to energy before it can disperse.",
                "These energies, as previously noted, have different 'frequencies' of sorts. A being burning with light would have a soul that reflects that radiance, and a being prone to adaptation would have a soul as malleable as itself. Occasionally, the energy has no flavor to it at all, leaving only the raw impulse of creation behind. That type of crystal bears further study.");

        addSimpleEntryHeader("soulstone", "Soulstone", "Out of phase");
        addPages("soulstone",
                "Sometimes, it appears that matter can be charged with the energies of a soul, despite not having a soul of its own.",
                "This serves as the basis for spirit arcana - the ensouling of the soulless. Soulstone is an ore that exists more in the arcane than the physical, and, refined, presents many uses for my magic. It strongly radiates magic.");

        addSimpleEntryHeader("natural_quartz", "Natural Quartz", "Deep in the earth");
        addPages("natural_quartz",
                "Natural Quartz is, as the name implies, a natural equivalent of the nether resource. It's used for most of the same things. It's rare, and found deep underground, sometimes in geodes.");

        addSimpleEntryHeader("cthonic_gold", "Cthonic Gold", "Fused with the arcane");
        addPages("cthonic_gold",
                "Cthonic Gold is a strange yet useful metal. Its physical makeup is that of gold, yet its properties are entirely distinct. The ore is found deep underground in the deepslate layer of the world, rooted deeply into existing veins of gold.",
                "Physically, Cthonic Gold resembles pyrite, albeit with the density of true gold. The altered nature of the metal appears to derive from a mix of earthen and infernal arcana somehow bonded to its physical structure, creating a strange alloy. Arcana does not normally interact with metal in this way, at least in my experiments.",
                "The alloy of physical and metaphysical causes this material to serve as a bridging point, a gate between realms, so to speak. Or perhaps a guardian of those gates? It exists as purely physical, yet the arcane acknowledges its passage.",
                "I'm not sure what use this metal will have quite yet, but I doubt I will be short for applications for a material with such atypical properties.");

        addSimpleEntryHeader("runewood", "Runewood", "Arcane oak");
        addPages("runewood",
                "Runewood is a strange mix of magic and nature, and a fairly common one at that. While pretty, I am more interested in practicality. Runewood is soaked in magic, and as such, can serve as the basis for the arcane.",
                "The tree is predominantly found within large open plains, however it can also be found in forests. The tree can be best identified by it's leaves, proudly displaying an orange-yellow palette.");
        addHeadline("runewood.arcane_charcoal", "Arcane Charcoal");
        addPages("runewood.arcane_charcoal",
                "Runewood's charcoal, as magic-infused as it is, burns with an arcane fervor for longer than regular charcoal. This makes it rather useful for fueling any smelting I need to do.");
        addHeadline("runewood.runic_sap", "Runic Sap");
        addPages("runewood.runic_sap",
                "Runewood trees tend to have a buildup of sticky sap on the sides of their logs. When this happens, if you strip off the bark, you'll be able to bottle the sap, which makes for a rejuvenating drink.",
                "In terms of taste, it is a bit like honey, sweet but with a more earthly flavor. The aftertaste in particular is quite special, it is rejuvenating in the purest of forms as it mends your wounds.",
                "The sap can also be used to create sapballs, able to be used interchangeably with slimeballs for most recipes.");

        addSimpleEntryHeader("blazing_quartz", "Blazing Quartz", "Ignition");
        addPages("blazing_quartz",
                "It stands to reason that a place like the nether would have a substance that was flammable, and Blazing Quartz certainly fits the bill. It acts much like coal, even being able to form torches. A useful substance, even if fairly mundane.");

        addSimpleEntryHeader("brilliance", "Brilliance", "The stuff of experience");
        addPages("brilliance",
                "Brilliance is a term I have heard bandied about for what others call experience. It is a part of the soul, though improperly attached, and can be collected and used for enchanting and repairs.",
                "What many don't know is that it can condense into a physical form. I have heard rumors of solid Brilliance coming from crushing ore, but the most reliable source is small clusters of ore where a soul faded away, leaving its experiences engraved on the stone.");

        addSimpleEntryHeader("scythes", "Scythes", "Harvest");
        addPages("scythes",
                "After several inert attempts, I have socketed Soulstone into a weapon that can reliably harvest these spirit crystals. The long blade allows time for the body to die before I strike the soul, while also providing a wide sweep attack. It isn't as sharp as a sword, but for my purposes, it will do nicely.",
                "What I had managed to do before with careful, painstaking experiments, the scythe did in a matter of seconds. The souls of the monsters I slew shattered, streaming bits of deeply hued matter towards me: the spirit crystals. Finally, my research can begin in earnest.");
        addSimpleEntryHeader("scythes.enchanting", "Enchanting a Scythe", "Two ways to skin a soul");
        addPages("scythes.enchanting",
                "At its core, the scythe enchants like other weapons or tools I've used. It has its own set of enchantments, of course, due to its differing nature, but can take Unbreaking and the like as well as a sword can.");
        addHeadline("scythes.enchanting.spirit_plunder", "Spirit Plunder");
        addPages("scythes.enchanting.spirit_plunder",
                "This is not a perfect method. Some of the soul is unavoidably lost in the moment between blade and stone. But by enchanting the blade, that loss can be mitigated, and more of the soul condensed. This unfortunately strains the stone, and can result in my scythe's durability decreasing.");
        addHeadline("scythes.enchanting.haunted", "Haunted");
        addPages("scythes.enchanting.haunted",
                "Soulstone interacts with souls, obviously. The crudest and simplest use is blunt force, shattering a soul like a pane of glass. If I condense the arcane power of the stone, it would therefore hit and shatter harder, perhaps even damaging the body with the backlash.");
        addHeadline("scythes.enchanting.animated", "Animated");
        addPages("scythes.enchanting.animated",
                "If power can be concentrated, it can be diffused. Interestingly, by diffusing the power that would be drawn together for Haunted into the rest of the scythe, the entire weapon gains a conceptual \"lightness\", making its swings lighter and faster.");
        addHeadline("scythes.enchanting.rebound", "Rebound");
        addPages("scythes.enchanting.rebound",
                "A scythe reaps. That is its purpose, and that can be harnessed. Should a reaper need my hand? By harnessing the purpose of the blade as a weapon, I can let it act on its own to some extent, as if it were a boomerang. The stronger the enchantment, the sooner it’s ready to throw again.");
        addHeadline("scythes.enchanting.ascension", "Ascension");
        addPages("scythes.enchanting.ascension",
                "A fascinating bit of symbolism is the role of the tool in raising man up. Civilization was built by the food a scythe harvests. By harnessing the purpose of the blade as a tool, I can… well, raise myself, the winds I rise on sharp as the blade itself to my foes. Rebound is incompatible; literally, at cross purposes with this enchantment.");

        addSimpleEntryHeader("esoteric_reaping", "Esoteric Reaping", "Leaked magic");
        addPages("esoteric_reaping",
                "When a being dies, its soul disperses. This is basic theory, and well proven by this point. It's been proposed that sometimes, that power leaks into the body of the creature as it dies, to explain the existence of reagents they drop. That hadn't been proven yet.",
                "But now, with my scythe, I have proved it beyond doubt. When a soul is shattered, even if only for a brief moment, the energy collides with what's left of it's vessel, it's physical remains. That collision creates brand new reagents that cannot normally be obtained by destroying just the body. This phenomena appears to create a strong reaction, a change of sorts.",
                "I have discovered five reagents born through this process, which I will detail further in this entry. In summary, the flesh of zombies can curdle to Rotting Essence; the bones of skeletons can crystallize to Grim Talc; the wings of phantoms can spin to Astral Weave; the essence of the breeze can persist as the Wind Nucleus; and the magic of endermen can coalesce into Warp Flux.");
        addHeadline("esoteric_reaping.rotting_essence", "Rotting Essence");
        addPages("esoteric_reaping.rotting_essence",
                "When exposed to this magic, the flesh of the undead can curdle into Rotting Essence, a toxic and foul substance that smells like death itself.");
        addHeadline("esoteric_reaping.grim_talc", "Grim Talc");
        addPages("esoteric_reaping.grim_talc",
                "Bones exposed to this magic can crystallize into Grim Talc, a useful mineral that can also be broken down into bonemeal.");
        addHeadline("esoteric_reaping.astral_weave", "Astral Weave");
        addPages("esoteric_reaping.astral_weave",
                "The membrane of a phantom will spin into Astral Weave with this magic, a mystic cloth with strange arcane properties.");
        addHeadline("esoteric_reaping.wind_nucleus", "Wind Nucleus");
        addPages("esoteric_reaping.wind_nucleus",
                "The strange construction of the Breeze echoes the Trials around them, and the Wind Nucleus appears to be a part of a Breeze's core bindings. In a pinch, those bindings can be released to push surrounding creatures away.");
        addHeadline("esoteric_reaping.warp_flux", "Warp Flux");
        addPages("esoteric_reaping.warp_flux",
                "The magic that envelops the endermen coalesces into Warp Flux, a strange essence that seems to be inimical to natural law.");

        addSimpleEntryHeader("spirit_infusion", "Spirit Infusion", "Creation of wonders");
        addPages("spirit_infusion",
                "By using Runewood's natural magic as a base, I have designed the altar that will serve as the basis for my magecraft - the Spirit Altar. It is the other piece of the equation, the use for the arcana. By infusing them into items, and using the energies to effect other fusions, I can begin to explore this.",
                "To use the altar, I must lay the item I wish to infuse on top of it, along with an appropriate set of arcana. If I wish to fuse other items in the process, I must place them on some form of Runewood item holder. They must be within four blocks of the altar to work.",
                "Once all the arcana are present, the power within the crystals will begin to flow into the central item. If other items are fused in, they are pulled in during this process. When all of that is done, the product of the infusion will appear. It " + italic("is") + " rather slow, though...");
        addSimpleEntryHeader("spirit_infusion.hex_ash", "Hex Ash", "Obligatory magic powder");
        addPages("spirit_infusion.hex_ash",
                "My first product with this process is a powder I call Hex Ash, after its color. It is a simple and useful grit, with the niter and sulfur mostly transmuted by the raw arcana, leaving a mixture of reagent and carbon.");
        addSimpleEntryHeader("spirit_infusion.living_flesh", "Living Flesh", "Please don't try to eat it");
        addPages("spirit_infusion.living_flesh",
                "Next, for the sake of understanding how spirit arcana interacts with living substance, I have created... " + italic("something") + " which is now known as Living Flesh. It is a disgusting meaty chunk completely unfit for human consumption. Who knows if I'll end up finding a proper use for it.");
        addSimpleEntryHeader("spirit_infusion.alchemical_calx", "Alchemical Calx", "Clay 2");
        addPages("spirit_infusion.alchemical_calx",
                "Lastly, I have created an experimental substrate I named Alchemical Calx. It's initially strong and tallow-like, but when met with a lesser amount of force it turns extremely malleable. It's bound to prove an useful ingredient.");

        addEntryHeader("primary_arcana", "Primary Arcana", "The components of magic");
        addHeadline("primary_arcana.sacred", "Sacred Spirit");
        addPages("primary_arcana.sacred",
                "Sacred arcana is essential to any magic that enhances life. It can be defined as holy, the energy of particularly vibrant life, or even the simplicity of youth. It is pure and untainted, making it a useful component.",
                "It is the impulse of purity, the desire for optimism. It is found in those who are passive, innocent, or holy in origin.");
        addHeadline("primary_arcana.wicked", "Wicked Spirit");
        addPages("primary_arcana.wicked",
                "Wicked arcana is inimical to life. It seeks death and despair, and warps the living into something else. Even touching the crystal makes my soul shudder in pain.",
                "It is the impulse of corruption, the desire to cause suffering. It is found in those whose souls lack life, or those twisted by malice.");
        addHeadline("primary_arcana.arcane", "Arcane Spirit");
        addPages("primary_arcana.arcane",
                "While other arcana are impulses of the soul, it would be more accurate to say that the arcane is the impulse of the arcana themselves. This " + bold("raw arcana") + " lacks any particular quality, simply being undirected spiritual power.",
                "It is the impulse of creation, the first principle of all things. It is found within those who have opened their soul to power, or whose origins lie in that power.",
                "I suspect that this arcana, unlike others, can join a soul over time. Most things about the soul are defined early on. The impulses that define you are woven into your very self, after all. But lacking an impulse, perhaps this arcana is different. A witch was not born a mage, after all.");

        addSimpleEntryHeader("elemental_arcana", "Elemental Arcana", "Focused magic");
        addHeadline("elemental_arcana.aerial", "Aerial Spirit");
        addPages("elemental_arcana.aerial",
                "Aerial arcana is the simplest of the elemental arcana. That very simplicity that gives it its utility. I have heard tales of magi soaring on the winds, ruling the skies. If any arcana is to make those tales achievable, it is this.",
                "It is the impulse of speed given form, the desire to run and to soar. It is found in anything particularly swift or mobile.");
        addHeadline("elemental_arcana.earthen", "Earthen Spirit");
        addPages("elemental_arcana.earthen",
                "Earthen arcana is relatively simple as well. It lends itself easily to strength, communion with nature, and the force of vitality. If I wish to enhance myself, or reshape the world, this arcana will be the key.",
                "It is the impulse of stability, the desire to stand and endure. It is found in anything that is unconcerned with the world around it changing.");
        addHeadline("elemental_arcana.infernal", "Infernal Spirit");
        addPages("elemental_arcana.infernal",
                "Infernal arcana is more complex, but not nearly as malicious as it might seem. Fire is dangerous, yes, but it is also the source of light and heat. It can burn something down as easily as it can fuse two things together.",
                "It is the impulse of light, the desire to burn. It is found in anything that shines brightly, as well as most denizens of the nether.");
        addHeadline("elemental_arcana.aqueous", "Aqueous Spirit");
        addPages("elemental_arcana.aqueous",
                "And finally, Aqueous arcana. It is strange, to say the least. It is malleable, yet doesn't do much by itself. It grants an affinity for the sea, but beyond that, its effects are rather esoteric.",
                "It is the impulse of change, the desire to adapt. It is found in anything that embodies that adaptation, as well as anything which lives in the flowing waters.");

        addEntryHeader("eldritch_arcana", "Eldritch Arcana", "For every push there is a pull");
        addHeadline("eldritch_arcana", "Eldritch Spirit");
        addPages("eldritch_arcana",
                "Eldritch arcana is a mystery to me. It has no impulse, none that I can understand, at least. And yet, it doesn't act like raw arcana. It changes, emboldens, enlightens... Raw arcana merely amplifies. This... this alters.",
                "I am not sure I understand what impulse creates this arcana. I find it in very few beings, and those I find it in are those who already defy explanation. But if it must be the pair to raw arcana, then that would imply that it's the impulse of endings, the " + italic("last") + " principle of all things.\n\nI do not like that thought.");

        addEntryHeader("spirit_stones", "Spirit Stones", "To suffuse with arcana");
        addHeadline("spirit_stones.tainted_rock", "Tainted Rock");
        addPages("spirit_stones.tainted_rock",
                "Stone is reluctant to change, but nothing can endure the power of an unchained soul forever. By using raw arcana to force that change, I have created stones with useful magical properties. With Sacred arcana as the catalyst, it forms Tainted Rock, a stone that dissipates magic nearby.");
        addHeadline("spirit_stones.twisted_rock", "Twisted Rock");
        addPages("spirit_stones.twisted_rock",
                "With Wicked arcana's nature as the opposite of Sacred, it follows that the stone produced with it would act opposite. Twisted Rock has most of the same properties as Tainted Rock, but pushes magic away from it instead of dissipating it. Both can be fashioned into item holders, as Runewood can.");

        addSimpleEntryHeader("ether", "Ether", "All the colors of the wind");
        addPages("ether",
                "A common task for an apprentice magus is to create a flame that burns without heat or fuel. It serves as a test of magical control, as well as the ability to circumvent natural phenomena. Spirit arcana, of course, can produce this wonder as well.",
                "A peculiarity of Ether's flame is that it resonates with colors. As if it was leather to be dyed, I can tint its appearance. It is an emitter of light, so dyeing it darker colors will lower the intensity rather than change the color of the flame itself.");
        addHeadline("ether.iridescent", "Iridescent Ether");
        addPages("ether.iridescent",
                "As if this was not enough, I have found a way to imbue a second color into my Ether, creating Iridescent Ether. When created, this form of Ether locks in its original color, leaving a new, " + italic("second") + " color open to dyeing. The light will shift from the original color into the new color towards the peak of the flames.",
                "Getting the right coloring for this can be tricky, though. As stated, once Ether is made Iridescent, its original color can no longer be changed. This is hardly an issue, but should be kept in mind when tinting your flames.");

        addSimpleEntryHeader("basic_artifice", "Waveform Artifice", "Signal modulation");
        addPages("basic_artifice",
                "Redstone is always useful, no matter your profession or goal. One needs not be an engineer to build a piston door. I now add my own contribution: spirit diodes. By imprinting the impulses of elemental spirits upon a matrix of ether-treated copper, I have created four interesting redstone devices.",
                "These diodes act by translating redstone impulse into arcane intent, allowing the nature of the spirit to shape the resulting effect, and then translating it back. Ether serves as the medium, with copper acting as a conceptual anchor to machinery and logic.");
        addHeadline("basic_artifice.wavecharger", "Wavecharger");
        addPages("basic_artifice.wavecharger",
                "Aerial seeks to soar, to rise. Given a signal, this transfers it but needs to 'warm up', causing the output's strength to slowly rise to meet the input signal. When unpowered or given a lower signal, it will similarly coast down until it turns off again.");
        addHeadline("basic_artifice.wavebanker", "Wavebanker");
        addPages("basic_artifice.wavebanker",
                "Aqueous seeks to change, to adapt. Given a signal, this transfers it but is 'sticky', continuing to emit the signal for a while after power is cut.");
        addHeadline("basic_artifice.wavemaker", "Wavemaker");
        addPages("basic_artifice.wavemaker",
                "Earthen seeks to stand, to stay. Given a signal or lack thereof, this acts like a clock, emitting the inverse of the input every so often on a steady rhythm, and emitting the input the rest of the time.");
        addHeadline("basic_artifice.wavebreaker", "Wavebreaker");
        addPages("basic_artifice.wavebreaker",
                "Infernal seeks to burn, to shine. Given a signal, this transfers it but is 'slow', the signal being held - 'admired' - by the diode for a time before being emitted out the other side.");

        addSimpleEntryHeader("basic_artifice.artificers_claw", "Artificer's Claw", "Despite the name, not sharp");
        addPages("basic_artifice.artificers_claw",
                "I have also developed a tool to tweak the timing of these diodes. By using it to move parts of the copper matrix, you can lengthen or shorten the delays involved in a diode, with a single redstone tick being the lower limit.");

        addSimpleEntryHeader("soulwoven_silk", "Soulwoven Silk", "To weave the ephemeral");
        addPages("soulwoven_silk", "Soulwoven Silk is a light, yet sturdy material imbued with soulstuff. Like a body, it naturally insulates against arcane energies. While other materials do the same, cloth is far easier to work with in designing than stone.");
        addPages("soulwoven_silk.soulwoven_banner", "Soulstuff can glow, as a shattered soul proves. Why not activate that glow within the cloth? I have made banners to test the effect, and used differing reagents and spirits to give a variety of patterns. When imbued, they will glow dimly, or fiercely if given an additional spirit.");

        addSimpleEntryHeader("soulwoven_pouch", "Soulwoven Pouch", "A hop, a skip, and a reach outside");
        addPages("soulwoven_pouch", "I often find Spirit Arcana cluttering my inventory. Why not solve a problem of magic with magic? This pouch acts like a bundle, but also snaps up Arcana when I collect any. It's physical capacity is no more impressive than that of a bundle with the added benefit of magical items being rotated slightly out of reality, taking up less space.");

        addSimpleEntryHeader("soulwoven_pouch.ravenous_pouch", "Ravenous Pouch", "Now make it hungry.");
        addPages("soulwoven_pouch.ravenous_pouch", "Although Spirit Arcana makes up much of the clutter I carry, it is not all that burdens my pockets. By fusing the pouch with rot, it sprouts teeth and transforms into the Ravenous Pouch. This hungry sack embodies the Gourmet aspect of Gluttony, aggressively snatching up any collected item that matches it's stored appetite.");

        addSimpleEntryHeader("soulhunter_armor", "Soulhunter Armor", "Shaped like glass");
        addPages("soulhunter_armor",
                "Spirit Fabric is an insulator, but that doesn't mean it has to dampen magic. This set of armor is designed to focus that magic, effectively amplifying the user's arcane abilities. Unfortunately, it's not exactly the strongest of materials, and it protects me just about as much as leather clothing.");

        addSimpleEntryHeader("spirit_focusing", "Spirit Focusing", "Mystic replication");
        addPages("spirit_focusing",
                "Using the opposing polarities of Twisted and Tainted Rock, I have created a device that draws in and focuses arcane energy. If given a compatible substrate, I can use this process to create things.",
                "The basic substrate here is the Alchemical Impetus, an artifact similar to those I've seen in the past. By focusing arcana into it, I can cause bits of the calx to transmute into something new, though this damages the Impetus in the process.");

        addSimpleEntryHeader("focus_ashes", "Arising of Ashes", "Creating powdered reagents");
        addPages("focus_ashes",
                "By applying differing qualities of arcana to an Alchemical Impetus, I can cause powders of various forms to be created. It is a simple yet very useful arcane recipe.");

        addSimpleEntryHeader("focus_metals", "Magecraft of Metals", "Forming banded crystals");
        addPages("focus_metals",
                "By altering the composition of the Alchemical Impetus with niter, sulfur, and cthonic gold, it is possible to alter the artifact in such a way that allows for forming nodes of most pure metals.",
                "It isn't particularly efficient or fast, but it is certainly better than having to mine for every ingot I need. Each metallic node can be processed at a furnace of any kind into two thirds of an ingot worth of metal nuggets.");

        addSimpleEntryHeader("focus_crystals", "Creation of Crystals", "Forming irregular crystals");
        addPages("focus_crystals", "By applying differing qualities of arcana to an Alchemical Impetus, I can cause more mundane crystals to be formed.");

        addSimpleEntryHeader("focus_elemental", "Recreation of Relics", "Creating construct cores");
        addHeadline("focus_elemental.zephyr_impetus", "Zephyr Impetus");
        addPages("focus_elemental.zephyr_impetus",
                "Wind Charges are objects with fascinating implications. They are clearly created, but historians are not clear on who created them, or why. Regardless, what was created can be made again, given the right Impetus. The Heavy Core and Wind Nucleus create the Zephyr Impetus - though it is extremely slow to operate, due to the complexity.",
                "The Wind Nucleus is a remnant of the Breeze, and, while it is analogous to a soul, it is not one. Given the fact that I needed a Wind Nucleus to recreate it, this cannot have been how the originals were created, and I do not understand the nature of their artificial existences enough to delve further.");

        addSimpleEntryHeader("crucible_acceleration", "Crucible Acceleration", "Heating up");
        addPages("crucible_acceleration",
                "The Spirit Crucible is, unfortunately, a rather slow device. It takes time for it to coalesce the power of the arcana into the central item. This isn't without reason. Most matter simply can't take a faster stream, and you risk damaging the catalyst by overloading it.",
                "However, by heating the catalyst through mystic means, you can lessen this rejection and speed up the coalescence at once. That is what the Spirit Catalyzer is for. Unfortunately, this is not perfect, and instability often causes the catalyst to be damaged more than strictly necessary.",
                "Each fueled Catalyzer nearby to a Crucible will amplify the speed of the focusing process, up to a maximum of eight. Unfortunately, the risk of instability proportionally rises with each one, resulting in your impetus potentially receiving more damage than necessary.");

        addSimpleEntryHeader("arcane_restoration", "Arcane Restoration", "Mystic repair");
        addPages("arcane_restoration",
                "The Spirit Crucible has an annoying habit of breaking the tools to work with it. While a cost is to be expected, I'd rather pay it in installments. I have designed a device I call the Repair Pylon, intended to shore items up as they break down.",
                "The Repair Pylon requires a toll of Spirit Arcana as well as a properly suited repair material for the job. When fitted with items, the Pylon will search for a nearby damaged item in any Item Pedestal, Item Stand, or Crucible. It should be theoretically possible to repair an item with " + italic("just") + " Arcana.. but that's only theory-crafting at the moment.");

        addSimpleEntryHeader("arcane_restoration.tool_repair", "Repairing Tools", "Mending for Steelwear");
        addPages("arcane_restoration.tool_repair",
                "The Repair Pylon can be used for more than just an Impetus. More mundane - or even magical - tools can be repaired without costing experience, by using spirits to fuel the restoration instead of Brilliance. This still requires material to perform the repairs, of course, and restores about half of an item's durability.",
                "Gear made of material that resonates with spirits - created from or by them - seems to be even more effectively repaired by this. Most, if not all, of the tools and armor written of in this book will be restored by three quarters of durability, rather than half.");
        addSimpleEntryHeader("crucible_augmentation", "Crucible Augmentation", "Tuning the attuner");
        addPages("crucible_augmentation",
                "The Spirit Crucible is a machine of great, but largely unrealized, potential. Through a process I call Augmentation, revolving around foci of Alchemical Calx, this potential can be extracted.",
                "Each augment provides a unique effect that can be activated by inserting it in the spirit catalyzer, or placing up to four in the spirit crucible itself. Using more than one instance of the same augment type will compound their effects.",
                "To assist in controlling this process, I have modified a Tuning Fork for the purpose. While held, I can see all the unique resonances of the crucible and it's augments. On top of that, by using this Tuning Fork on the crucible, I may choose an attribute to improve, at the cost of other attributes lessening in potency.");

        addSimpleEntryHeader("sympathy_drive", "Sympathy Drive", "Harnessing hatreds");
        addPages("sympathy_drive",
                "Wicked and Sacred are opposed forces, so why not harness them together? When a Sympathy Drive-affected Crucible's Impetus takes damage, the Wicked charge will remember. When it is healed by the Mending Diffuser, the Sacred charge will 'forgive' the damage, repairing further and improving stats generally for three cycles.");
        addSimpleEntryHeader("resonance_tuner", "Resonance Tuner", "Dampening dissonance");
        addPages("resonance_tuner",
                "Aerial and Earthen do not interact, generally, but non-interaction is useful in its own way. The Resonance Tuner dampens the interaction of forces in the Crucible, heavily decreasing or even eliminating instability by causing the process to take longer.");
        addSimpleEntryHeader("caustic_catalyst", "Caustic Catalyst", "Inflaming instability");
        addPages("caustic_catalyst",
                "Infernal and Aqueous mix energetically, often in transformative and destructive ways. The Caustic Catalyst increases Tuning Potency the further the instability of the system rises, harnessing that bubbling backlash for greater power.");
        addSimpleEntryHeader("suspicious_device", "Suspicious Device", "A cautionary tale");
        addPages("suspicious_device",
                "Being opposed, you " + italic("can") + " use Arcane and Eldritch to create an opposition augment. However, this just causes violent entropy as Arcane flows eagerly into the end state of Eldritch, and the result is... a corruptive explosion generating Blight. This entry serves as a warning, not a recommendation.");

        addSimpleEntryHeader("mending_diffuser", "Mending Diffuser", "Unliving scar tissue");
        addPages("mending_diffuser",
                "By using Living Flesh to sympathize with natural healing, the Mending Diffuser will, upon the Crucible completing a focusing cycle, potentially repair any impetus by a small amount. It cannot mend an already fractured impetus.");

        addSimpleEntryHeader("impurity_stabilizer", "Impurity Stabilizer", "Potency from weakness");
        addPages("impurity_stabilizer",
                "The wicked spirit is drawn to the weak, seeking to cull. The Impurity Stabilizer subverts that property, providing a powerful percentage tuning bonus to the weakest crucible attribute besides fuel usage rate and instability. This secondary influence does create a bit of strain on the standard tuning process however.");

        addSimpleEntryHeader("shielding_apparatus", "Shielding Apparatus", "A bulwark against the storm");
        addPages("shielding_apparatus",
                "By utilizing the multiphasic property of Soulstained Steel, the Shielding Apparatus provides a chance for the damage imposed upon the impetus to be " + italic("completely") + " absorbed, while also slightly stabilizing the focusing process. It does, however, reduce focusing speed.");

        addSimpleEntryHeader("warping_engine", "Warping Engine", "Suspension of linear time");
        addPages("warping_engine",
                "Warp Flux rejects natural law, and its application here is no less concerning. The Warping Engine rejects the sequence of cause and effect, allowing an additional cycle to sometimes be completed almost before it is begun whenever a cycle is completed normally.",
                "Furthermore, chained activations of the Warping Engine provide a stacking benefit to " + italic("all") + " other attributes. Perhaps predictably, this behavior creates a significant increase in fuel requirements of the focusing process. Lastly, the influence of the Warping Engine creates a minor amount of strain on the standard tuning process.");

        addSimpleEntryHeader("prismatic_focus_lens", "Prismatic Focus Lens", "Stability");
        addPages("prismatic_focus_lens",
                "Sometimes, simplicity is the best goal. The Prismatic Focus Lens bends not light, but the flow of arcana, reducing instability of the spirit focusing process. It is important to note, stability can only prevent the impetus from suffering " + italic("additional") + " damage.");

        addSimpleEntryHeader("accelerating_inlay", "Accelerating Inlay", "Doubling down");
        addPages("accelerating_inlay",
                "Through the use of a superior conductor in Astral Weave, Accelerating Inlay simply provides a substantial bonus to the focusing speed of a Crucible without any drawbacks.");

        addSimpleEntryHeader("blazing_diode", "Blazing Diode", "The strongest force in the world");
        addPages("blazing_diode",
                "The Blazing Diode extracts the full force of a soul on fire, lessening the fuel requirement of any catalyzer powering the crucible while also slightly hastening the entire process.");

        addSimpleEntryHeader("intricate_assembly", "Intricate Assembly", "Fudging the numbers");
        addPages("intricate_assembly",
                "The Intricate Assembly, as its name suggests, draws its power from the unbounded nature of its fractal complexity. At the cost of an increased dependency on fuel, as well as a reduced focusing speed, it enables the crucible to potentially produce double the usual amount of items during each focusing cycle.");

        addEntryHeader("spirit_metals.soul_stained_steel", "Soulstained Steel", "To alloy realities");
        addHeadline("spirit_metals.soul_stained_steel", "Soulstained Steel");
        addPages("spirit_metals.soul_stained_steel",
                "Iron is mundane, in a word. By attuning the metal with Soulstone, I can create a steel that is " + italic("simultaneously") + " in and out of phase with the world.",
                "Anything made from Soulstained Steel is capable of striking the soul, without the need for specifics of engineering like with my crude scythe. Wearing the metal in its base form as armor is dangerous, as it will touch your own soul as well, so I must engineer a countermeasure.");

        addEntryHeader("spirit_metals.hallowed_gold", "Hallowed Gold", "To bring in tune");
        addHeadline("spirit_metals.hallowed_gold", "Hallowed Gold");
        addPages("spirit_metals.hallowed_gold",
                "Gold is often used as a thaumaturgical base, its natural conductivity of magic making it quite useful. Spirit arcana are no exception. In fact, using Sacred arcana, we can enhance those conductive properties.",
                "Hallowed Gold, as a metal, acts much like its mundane counterpart. The inherent innocence of the arcana infused into the alloy makes other arcana glide through it smoothly, creating the perfect conductor for my purposes. It also makes for some quite fashionable item peripherals.");

        addEntryHeader("spirit_jar", "Spirit Storage", "Arcane Archives");
        addHeadline("spirit_jar", "Spirit Jar");
        addPages("spirit_jar", "A simple application of hallowed gold is the Spirit Jar. As spirits in their raw form don't have mass, by trapping them under Hallowed Gold you can store far more than you could physically. The capacity of these jars is near-infinite, though each only stores one type of spirit.");

        addSimpleEntryHeader("soulstained_scythe", "Soulstained Scythe", "Reap");
        addPages("soulstained_scythe", "The scythe I created to harvest spirits was useful, but ultimately has outlived that usefulness. I have grown fond of the utility it provides, though, and so instead of discarding it I sought to improve it. With Soulstained Steel, I was able to create a more effective weapon and maintain the scythe's advantages.");

        addSimpleEntryHeader("soulstained_armor", "Soulstained Armor", "Spiritual protection");
        addPages("soulstained_armor",
                "Much like the Soulstained Scythe, I have improved upon my mundane iron armor to create the Soulstained Armor. To avoid the metal touching me directly, and so jostling and rubbing against my very soul, I used thin plates of Twisted Rock beneath the metal of the armor.",
                "As it exists in both the arcane and physical realms, Soulstained Steel exhibits fascinating defensive properties. It can intercept attacks from both, creating an effect I call Soul Ward. It takes time to restore if the effect is disrupted, but it acts as additional armor which nearly absorbs magic damage completely, and dampens physical damage.",
                "This effect seems similar in nature to others I have studied, such as engraving runes into armor or invoking a black sun upon oneself. Though unlike those, it doesn't " + italic("appear") + " to have a cost. Where is the energy for Soul Ward coming from?");

        addSimpleEntryHeader("spirit_trinkets", "Spirit Trinkets", "Accessorizing");
        addPages("spirit_trinkets",
                "Many disciplines of magic, and even more mundane practices, allow the creation of useful trinkets. These are also referred to as baubles or curios by some. The metals I have alloyed have properties useful in their own rights, and can be used as the basis for even grander designs.",
                "In their most basic form, Hallowed Gold trinkets protect the user as if they were wearing weak armor, and Soulstained Steel trinkets increase the toughness of the armor being worn.");

        addEntryHeader("reactive_trinkets", "Reactive Trinkets", "Harnessing the harvest");
        addHeadline("reactive_trinkets.ring_of_curative_talent", "Ring of Curative Talent");
        addPages("reactive_trinkets.ring_of_curative_talent",
                "The trinkets documented within cause effects whenever a spirit crystal is collected, feeding off the excess energy. As an example, this restorative trinket will replenish a small division of my health any time I collect arcana.");
        addHeadline("reactive_trinkets.ring_of_alchemical_mastery", "Ring of Alchemical Mastery");
        addPages("reactive_trinkets.ring_of_alchemical_mastery",
                "This ring, through alchemical trickery, is able to manipulate the potions running through my blood. Whenever I collect arcana, the ring will partially filter out negative effects, while at the same time prolonging positive ones.");
        addHeadline("reactive_trinkets.ring_of_manaweaving", "Ring of Manaweaving");
        addPages("reactive_trinkets.ring_of_manaweaving",
                "Soul Ward is a powerful barrier, but in it's current state it leaves much to be desired. One of it's glaring issues is the burdensome recovery time. To combat this, I've created a ring that in reaction to spirit arcana accelerates the recovery process of Soul Ward.");
        addHeadline("reactive_trinkets.ring_of_prowess", "Ring of Prowess");
        addPages("reactive_trinkets.ring_of_prowess",
                "Brilliance is attached to the soul, but isn't an impulse like the arcana. It is accumulated knowledge, and so is not inherently tied to the soul that learned it. Even strikes which pass through the soul harmlessly are capable of dislodging it.",
                "By using condensed Brilliance, I have created a ring that filters out that Brilliance out of arcana I collect, giving me a burst of Brilliant knowledge whenever I collect arcana.");

        addSimpleEntryHeader("ring_of_esoteric_spoils", "Ring of Esoteric Spoils", "Be fruitful and multiply");
        addPages("ring_of_esoteric_spoils",
                "It can be tiring, harvesting the sheer quantities of arcana I need for my research. This ring can increase the efficiency of the harvest, allowing me to reap an additional spirit from every slain soul. At a certain point, though, \"efficiency\" ceases to explain it. How am I obtaining more power than the soul itself has?");

        addSimpleEntryHeader("belt_of_the_starved", "Belt of the Starved", "Channeling voracity");
        addPages("belt_of_the_starved",
                "The arcana I collect occasionally have scraps of wishes and desires woven in. Often, given the base nature of what I reap, this comes in the form of hunger, lust, or petty grudges. All of these impurities can be catalyzed into Gluttony, an stacking amplifier towards magic damage.",
                "Harnessing magical power this way carries the perhaps predictable effect that my own hunger amplifies, draining quicker in the process. The magic proficiency this grants is not to be ignored, but... I must say, the means are rather distasteful.");
        addSimpleEntryHeader("belt_of_the_starved.ring_of_desperate_voracity", "Ring of Desperate Voracity", "Widening the channel");
        addPages("belt_of_the_starved.ring_of_desperate_voracity",
                "This ring makes rotten foods just a little bit more bearable, allowing me to amass more hunger and saturation from such an unusual diet. Normally, such a diet would be ill-advised, however, a secondary function of the ring allows it to extend the duration of the Gluttony status effect that the Belt of the Starved grants.");
        addSimpleEntryHeader("belt_of_the_starved.concentrated_gluttony", "Concentrated Gluttony", "Bypass");
        addPages("belt_of_the_starved.concentrated_gluttony",
                "I have grown somewhat annoyed with the rotten foods I rely on - they are hardly pleasant to eat. By concentrating them, I can minimize the time I spend tasting rot, granting Gluttony which is amplified by each rotten trinket I wear. It takes seconds to digest, but that is preferable to the taste.",
                "It is like a potion, and so, like potions, I have derived a Splash variant of it. Upon impact, the essence of rot stored inside is released in a small area, applying its usual benefits to every creature caught inside.");

        addSimpleEntryHeader("belt_of_the_prospector", "Belt of the Prospector", "Treasures of the earth");
        addPages("belt_of_the_prospector",
                "To fuel my various magics and other goals I more often than not find myself needing various earthen treasures. This belt prevents explosions " + italic("directly") + " caused by me from harming valuable items on the ground, and causes those explosions to break blocks as though I were using a Fortune III tool.");
        addSimpleEntryHeader("belt_of_the_prospector.ring_of_the_hoarder", "Ring of the Hoarder", "Directly into my veins");
        addPages("belt_of_the_prospector.ring_of_the_hoarder",
                "Explosions are chaotic, and messy, inherently. This is hardly a problem, when I want to cause such rampant destruction to collect resources, but collecting the items is a burden. This ring entangles the explosion with my soul, causing the debris and loot to appear at my location.");
        addSimpleEntryHeader("belt_of_the_prospector.ring_of_the_demolitionist", "Ring of the Demolitionist", bold("More dakka"));
        addPages("belt_of_the_prospector.ring_of_the_demolitionist",
                "If raw explosive power is not sufficient, you simply aren't using enough of it. This ring amplifies explosions, mitigating that issue.");

        addEntryHeader("necklace_of_blissful_harmony", "Necklace of Blissful Harmony", "No sign of morning coming");
        addHeadline("necklace_of_blissful_harmony", "Necklace of Blissful Harmony");
        addPages("necklace_of_blissful_harmony",
                "To focus on my magics I more often than not need peace and clarity. As such, I have devised a tool to redirect attention around me. While worn, this accessory will hide my presence from nearby adversaries, decreasing their likelihood of taking interest in me.",
                "Upon further studies, it would appear that the effects of my newly forged trinket are " + italic("especially") + " potent when exerting their influence over any soul bearing a Wicked spirit.");

        addEntryHeader("necklace_of_the_mystic_mirror", "Necklace of the Mystic Mirror", "As without, so within");
        addHeadline("necklace_of_the_mystic_mirror", "Necklace of the Mystic Mirror");
        addPages("necklace_of_the_mystic_mirror",
                "I have devised another way to capture some of the lost energy from loose spirits. The Resonant Lens I socketed in is able to focus magic, collecting a little bit of excess energy as I pick up arcana. This energy is then redistributed to the rest of my trinkets, increasing the effect of any that act upon collecting spirits.");

        addEntryHeader("necklace_of_the_narrow_edge", "Necklace of the Narrow Edge", "Focused and sharpened");
        addHeadline("necklace_of_the_narrow_edge", "Necklace of the Narrow Edge");
        addPages("necklace_of_the_narrow_edge",
                "The sweep of the scythe is its main draw. The ability to cut my targets like so much wheat is invaluable. But that comes at the cost of damage to a single target. This necklace mystically focuses the edge of my attack, directing all of the power into one target for a strong damage boost.");

        addEntryHeader("necklace_of_the_narrow_edge.ring_of_the_rising_edge", "Ring of the Rising Edge", "An A press is an A press");
        addHeadline("necklace_of_the_narrow_edge.ring_of_the_rising_edge", "The Rising Edge");
        addPages("necklace_of_the_narrow_edge.ring_of_the_rising_edge",
                "Rebound and Ascension are interesting enchantments, from a categorical perspective. Neither association has any wind implication, and yet Aerial is their arcana all the same. In the course of investigating this, I discovered how to alter Ascension with an effect I name the Rising Edge.",
                "A Wind Charge is presumably something constructed by tools, though I know not by who or how. Utilizing that connection, at the cost of a decrease to damage, those cut by Ascension's blades of wind under the Rising Edge’s effect will also find themselves launched upwards. Useful for crowd control and whatnot, but like the scythe itself, the Narrow Edge can change this purpose.",
                "By wearing both the Necklace of the Narrow Edge and the Ring of the Rising Edge, the concentrated sweep of wind launches with vastly increased potency, allowing me to juggle my enemies above me.");

        addEntryHeader("necklace_of_the_narrow_edge.ring_of_the_howling_maelstrom", "Ring of the Howling Maelstrom", "30% chance to flinch");
        addHeadline("necklace_of_the_narrow_edge.ring_of_the_howling_maelstrom", "The Howling Maelstrom");
        addPages("necklace_of_the_narrow_edge.ring_of_the_howling_maelstrom",
                "Rebound and Ascension are interesting enchantments, from a categorical perspective. Neither association has any wind implication, and yet Aerial is their arcana all the same. In the course of investigating this, I discovered how to alter Rebound with an effect I name the Howling Maelstrom.",
                "Rebound is reaping, and a Wind Charge has been reaped. By utilizing that connection, this ring causes cutting winds to spin about the scythe when thrown, damaging things around, at the cost of a longer cooldown afterwards.",
                "By wearing both the Necklace of the Narrow Edge and the Ring of the Howling Maelstrom, I concentrate these winds to the moment the scythe strikes a foe, creating a small storm there for around two seconds.");

        addEntryHeader("runeworking", "Runeworking", "The central pin");
        addHeadline("runeworking", "Runeworking");
        addPages("runeworking",
                "Every trinket I've made thus far has proven to have its place in my work, but it has become somewhat frustrating to have to spend time choosing out my jewelery before every task. After all, some effects may be able to be divorced from their genesis.",
                "The process of Runeworking allows me to do so via Brooches and Runes. The brooch is simply the focus I have chosen, its placement over the heart symbolizing the price of suffering inherent to runic power.",
                "To begin, I will need to create a Runic Workbench to inscribe these runes. The simplest beginning will be the Runic Brooch, which will allow me to equip what I have created.");

        addEntryHeader("runic_brooch", "Runic Brooch", "Power in the palms");
        addHeadline("runic_brooch", "Runic Brooch");
        addPages("runic_brooch",
                "The simplest of brooches is the Runic Brooch. It represents the quest for power at price, and the bloodied palms with which one grasps a razored enlightenment. When worn, by symbolically removing one of my hands' capacity to channel magic from a ring, it will grant me the opportunity to inscribe a rune in each palm.");

        addEntryHeader("glass_brooch", "Glass Brooch", "Fragile power");
        addHeadline("glass_brooch", "Glass Brooch");
        addPages("glass_brooch",
                "The Runic Brooch works well, but my hands are not the only symbolic home of power. I can instead imbue them into my blood with the Glass Brooch, stripping a portion of my physical health away to inscribe two runes within my chest.");

        addEntryHeader("elaborate_brooch", "Elaborate Brooch", "A change in purpose");
        addHeadline("elaborate_brooch", "Elaborate Brooch");
        addPages("elaborate_brooch",
                "Brooches symbolize a sacrifice, but that sacrifice can seem abstract, even comical, from the outside. A necklace is not so different from a belt in form, and by stripping away notions of " + italic("fashion") + " and propriety, I can wear a belt's power around my neck.");

        addEntryHeader("gluttonous_brooch", "Gluttonous Brooch", "Endlessly unsated");
        addHeadline("gluttonous_brooch", "Gluttonous Brooch");
        addPages("gluttonous_brooch",
                "Some sacrifices are mental, rather than physical. The Gluttonous Brooch strips away satiation, causing my body to crave food even when it should be full. In exchange for this, my loosened belly has the space to accommodate an additional belt.");

        addSimpleEntryHeader("rune_of_vitality", "Rune of Vitality", "The impulse to mend");
        addPages("rune_of_vitality",
                "The Rune of Vitality implores a body to restore itself, amplifying all healing received by roughly one fifth.");
        addSimpleEntryHeader("rune_of_culling", "Rune of Culling", "The impulse to break");
        addPages("rune_of_culling",
                "The Rune of Culling implores a mind to seek ruin, granting the user a bonus to Scythe Proficiency against wounded targets. The effect appears to activate with targets at or below half health, and increases the damage dealt by the scythe by about two fifths.");
        addSimpleEntryHeader("rune_of_ailment_cleansing", "Rune of Ailment Cleansing", "The impulse to process");
        addPages("rune_of_ailment_cleansing",
                "The Rune of Ailment Cleansing implores a body to catalyze and change substance, allowing it to burn through and process negative effects quicker than normal.");
        addSimpleEntryHeader("rune_of_scorching", "Rune of Scorching", "The impulse to burn");
        addPages("rune_of_scorching",
                "The Rune of Scorching implores a mind to share its light with others regardless of what that will cause, doubling the strength of fire damage originating from the user.");
        addSimpleEntryHeader("rune_of_protection", "Rune of Protection", "The impulse to withstand");
        addPages("rune_of_protection",
                "The Rune of Protection implores a body to stand fast, granting the user a bonus to Armor equal to about one fifth.");
        addSimpleEntryHeader("rune_of_dexterity", "Rune of Dexterity", "The impulse to flee");
        addPages("rune_of_dexterity",
                "The Rune of Dexterity implores a mind to move when cornered, boosting movement speed which can up to double as the user's health pool diminishes.");
        addSimpleEntryHeader("rune_of_reinforcement", "Rune of Reinforcement", "The impulse to make");
        addPages("rune_of_reinforcement",
                "The Rune of Reinforcement, rather than imploring the body, simply provides pressure to the Arcane quality of its existence, granting their Soul Ward capacity and integrity.");
        addSimpleEntryHeader("rune_of_volatile_distortion", "Rune of Volatile Distortion", "The impulse to putrefy");
        addPages("rune_of_volatile_distortion",
                "The Rune of Volatile Distortion, rather than imploring the mind, corrupts its actions with random chance, making the user's attacks erratic in damage - sometimes aligning with weaknesses by chance, doubling the strength of the attack.");

        addSimpleEntryHeader("void.runes", "Voidish Runecraft", "An altered alphabet");
        addPages("void.runes",
                "By inscribing the runes on tablets of Null Slate, their effects run wild and warped, seeking Void instead of creation. Each seeks to tear itself apart, creating paradoxical and fascinating effects; I have my notes on their functions in the attached entries.");

        addSimpleEntryHeader("void.rune_of_bolstering", "Rune of Bolstering", "To heal what is whole");
        addPages("void.rune_of_bolstering",
                "The Rune of Bolstering does not heal like its counterpart. Instead, it forces the body to heal past its limits, granting a small amount of extra health.");
        addEntryHeader("void.rune_of_sacrificial_empowerment", "Rune of Sacrificial Empowerment", "To break what is broken");
        addHeadline("void.rune_of_sacrificial_empowerment", "Sacrificial Empowerment");
        addPages("void.rune_of_sacrificial_empowerment",
                "The Rune of Sacrificial Empowerment grants strength in exchange for the lives taken by a scythe, causing your weapon to grow more potent with each kill for a time.");
        addSimpleEntryHeader("void.rune_of_twinned_duration", "Rune of Twinned Duration", "To suspend what must process");
        addPages("void.rune_of_twinned_duration",
                "The Rune of Twinned Duration inhibits the body in breaking down substances, causing the body to hold on to positive effects for longer.");
        addSimpleEntryHeader("void.rune_of_igneous_solace", "Rune of Igneous Solace", "To fuel what must burn");
        addPages("void.rune_of_igneous_solace",
                "The Rune of Igneous Solace toughens the user's skin when burning, giving them a partial resistance to any inbound damage. It does not, however, do anything about the flames.");
        addSimpleEntryHeader("void.rune_of_indomitability", "Rune of Indomitability", "To bear what must break");
        addPages("void.rune_of_indomitability",
                "The Rune of Indomitability reinforces the user's stance, negating any knockback taken.");
        addSimpleEntryHeader("void.rune_of_unnatural_stamina", "Rune of Unnatural Stamina", "To flee what must pursue");
        addPages("void.rune_of_unnatural_stamina",
                "The Rune of Unnatural Stamina gives its user the speed of hysteria, constantly able to move faster, and even being able to sprint if your hunger would normally prevent you from doing so.");
        addSimpleEntryHeader("void.rune_of_spell_mastery", "Rune of Spell Mastery", "To make what destroys");
        addPages("void.rune_of_spell_mastery",
                "The Rune of Spell Mastery mirrors the false arcana, allowing me to charge my staves twice as fast.");
        addSimpleEntryHeader("void.rune_of_heresy", "Rune of Heresy", "To destroy what makes");
        addPages("void.rune_of_heresy",
                "The Rune of Heresy decries magic, Silencing foes much like the Erosion Scepter can. This weakens their spirit magic, and the effect can stack up to complete suppression.");

        addSimpleEntryHeader("rune_of_motion", "Rune of Motion", "Uplifting your impulses");
        addPages("rune_of_motion",
                "The Rune of Motion conveys the Rite of Motion, granting Zephyr's Courage to its wearer at a reduced potency, speeding them up.");
        addSimpleEntryHeader("rune_of_loyalty", "Rune of Loyalty", "Molding your impulses");
        addPages("rune_of_loyalty",
                "The Rune of Loyalty conveys the Rite of Loyalty, granting Poseidon's Grasp to its wearer at a reduced potency, extending their reach.");
        addSimpleEntryHeader("rune_of_warding", "Rune of Warding", "Grounding your impulses");
        addPages("rune_of_warding",
                "The Rune of Warding conveys the Rite of Warding, granting Gaia's Bulwark to its wearer at a reduced potency, effectively granting armor.");
        addSimpleEntryHeader("rune_of_haste", "Rune of Haste", "Igniting your impulses");
        addPages("rune_of_haste",
                "The Rune of Haste conveys the Rite of Haste, granting Miner's Rage to its wearer at a reduced potency, speeding up their swings of weapons and tools.");
        addSimpleEntryHeader("rune_of_the_aether", "Rune of the Aether", "Scattering your impulses");
        addPages("rune_of_the_aether",
                "The Rune of the Aether conveys the Rite of the Aether, granting Aether's Charm to its wearer at a reduced potency, lowering the influence of gravity on them.");
        addSimpleEntryHeader("rune_of_the_seas", "Rune of the Seas", "Deforming your impulses");
        addPages("rune_of_the_seas",
                "The Rune of the Seas conveys the Rite of the Seas, granting Angler's Lure to its wearer at a reduced potency, increasing their skill with fishing.");
        addSimpleEntryHeader("rune_of_the_arena", "Rune of the Arena", "Honing your impulses");
        addPages("rune_of_the_arena",
                "The Rune of the Arena conveys the Rite of the Arena, granting Earthen Might to its wearer at a reduced potency, causing their attacks to do more damage.");
        addSimpleEntryHeader("rune_of_the_hells", "Rune of the Hells", "Extinguishing your impulses");
        addPages("rune_of_the_hells",
                "The Rune of the Hells conveys the Rite of the Hells, granting Ifrit's Embrace to its wearer at a reduced potency when they are on fire, extinguishing and healing them.");

        addSimpleEntryHeader("spirited_glass", "Spirited Glass", "Not suitable for Oculators");
        addPages("spirited_glass",
                "I have designed a simple but aesthetically pleasing glass which is tinted by the arcana, framed in iron. The particles of the glass are 'aligned' by the power placed within - which means Raw and Eldritch, having no direction, are somewhat chaotic. They do still look interesting, though.");

        addSimpleEntryHeader("mote_making", "Mote Making", "Worship the cube");
        addPages("mote_making",
                "Arcana crystals emit their own strange glow. Why not tune that to be stronger? The tool I use to do this is the Lamplighter's Tongs; simply hold them in one hand and the crystal in another to create a 'mote'.",
                "These Motes are concentrations of pure arcane energy, with a thin shell of warding magic to keep it from spilling. This has little magical implication, but the resulting lights are pretty.");

        addSimpleEntryHeader("mirror_magic", "Mirror Magic", "Magic Funnels");
        addPages("mirror_magic", "Mirror Magic in Malum will be a system centered around transporting items and spirits. It'll be split into two categories, one centered around managing spirit arcana and the other providing you with modular item funnels. You'll be able to connect your spirit jars to your altar or crucible and have it automatically resupply.",
                "For general item transport, mirrors will create a portal-esque gravity tunnel, a leyline. This leyline will be capable of picking up and transporting items forward. Depending on what lens you create for your mirror, the leyline will acquaint different properties. In the next release, either Voodoo Magic or Mirror Magic will be added to malum.");

        addSimpleEntryHeader("voodoo_magic", "Voodoo Magic", "Poppet Creation");
        addPages("voodoo_magic", "Voodoo Magic in Malum will be a system that allows you to program and command little fellas known as poppets. It'll be split into two categories, combat and utility. You can expect to be able to command a small army of combat poppets while you sit back and watch.",
                "For utility purposes, you'll also be able to employ poppets with simple jobs such as collecting items, putting items in chests, shearing sheep, harvesting crops, that sort of thing. In the next release, either Voodoo Magic or Mirror Magic will be added to malum.");

        addSimpleEntryHeader("ritual_magic", "Ritual magic", "Grand Magics");
        addPages("ritual_magic", "The future holds many secrets..");

        addEntryHeader("altar_acceleration", "Altar Acceleration", "Obelisks");
        addHeadline("altar_acceleration.runewood_obelisk", "Runewood Obelisk");
        addPages("altar_acceleration.runewood_obelisk",
                "Spirit Infusion, as essential as it is, has grown to be tedious. Even producing a stack of simple Hex Ash takes several minutes. Using Hallowed Gold, I have found a way to accelerate it. By placing up to four hallowed obelisks nearby the altar I may increase the processing speed by one fourth with each obelisk.");
        addHeadline("altar_acceleration.brilliant_obelisk", "Brilliant Obelisk");
        addPages("altar_acceleration.brilliant_obelisk",
                "While not useful for Infusion, per-se, the design of the obelisk can be used in another way as well. By socketing Brilliance instead of Hallowed Gold, the obelisk will harmonize with the Brilliance of enchanting, causing it to provide as much force of enchanting as five bookshelves do.");

        addSimpleEntryHeader("geas_magic", "Geasa, and Other Oaths", "Power for price");
        addPages("geas_magic",
                "Oaths and sacrifices hold power. Brooches, for example, sacrifice one thing for another. There are legends of those who willingly forswear something, gaining power from that action. I have devised a way to bind to a soul semi-permanently such a pact, or " + bold("geas") + ", through the Soulbinding Brazier.",
                "The Brazier is less a device and more a conduit, utilizing Hallowed Gold inlay and Cthonic Gold insets to conduct souls. Ether is used to ignite it, once the materials are arranged.",
                "Each oath requires material sacrifices, in the form of a central item, additional ingredients, and arcana to fuel the bond, inserted into the Brazier directly. Once lit, you must offer blood to seal the pact. Do so by interacting with the brazier. Any number of willing participants may offer blood to this end at once.",
                "Geasa can be applied even to simpler souls, though they might not know how to take advantage of them. A creature can be tempted to join a geas by afflicting it with Weakness before igniting the Brazier.",
                "Each soul only seems to have capacity for two separate geas at once, with additional ones failing. These are, again, tied to the soul itself, and so cannot be removed without significant effort.");

        addSimpleEntryHeader("undoing_geas_bindings", "Forswearing Geasa", "For prices too great");
        addPages("undoing_geas_bindings",
                "Ah, a pact that binds forever is a heavy thing. Geasa are potent, yes, and can achieve powerful effects, but their downsides are constant companions. These pacts can be forsworn on the Brazier, allowing you to decide what price you are willing to pay.",
                "To forswear a geas, assemble it on the Brazier as you did initially, but use the Paracausal Flame to kindle the Brazier instead. This will burn the contract " + italic("away") + " instead of inscribe it. The rest of the process works the same, including Weakness.");

        addSimpleEntryHeader("pact_of_defiance", "Pact of Defiance", "To accept no limit");
        addGeasDetails(MalumGeasEffectTypes.PACT_OF_DEFIANCE,
                scaled(0.8f) +"""
                        An unnatural regeneration imbued onto the body, a desire to grow forever.
                         -Saturation heals you Twice as fast, Thrice as fast when near death
                         -Increases Healing Received by Four Tenths""",
                "The magics shift your perception of death, each point of damage more significant.\n -Reduces Max Health by One Fifth");

        addSimpleEntryHeader("pact_of_the_parasite", "Pact of The Parasite", "To hide by stolen shields");
        addGeasDetails(MalumGeasEffectTypes.PACT_OF_THE_PARASITE,
                """
                        A surplus of confidence imbued onto the mind, your pain is only secondary.
                         -Spirit Collection generates Absorption""",
                "Your fragility is still very much real.\n -Reduces Healing Received by Four Tenths");

        addSimpleEntryHeader("pact_of_the_lifeweaver", "Pact of The Lifeweaver", "To share with any and all");
        addGeasDetails(MalumGeasEffectTypes.PACT_OF_THE_LIFEWEAVER,
                """
                        A rare generosity, everything that you are you will share with others.
                         -Passively Recovers your Health
                         -Health Recovery is Shared With Nearby Creatures""",
                "All creatures are deserving of care.\n -Healing Aura targets Everything, Ally and Enemy alike");


        addSimpleEntryHeader("pact_of_the_warlock", "Pact of The Warlock", "To fight forever");
        addGeasDetails(MalumGeasEffectTypes.PACT_OF_THE_WARLOCK,
                """
                        A pact designed to weave anger into pain.
                         -The Initial Hit against Enemies Blasts them with Wicked Arcana""",
                "Feel anger, cause anger\n -Enemies detect you from Twice as Far");

        addSimpleEntryHeader("pact_of_the_reaper", "Pact of The Reaper", "To master the harvest blade");
        addGeasDetails(MalumGeasEffectTypes.PACT_OF_THE_REAPER,
                """
                        A pact forged between your Soul and your Scythe, the Chains of Arcana bind you together.
                         -Scythe Attacks occasionally trigger follow up cuts""",
                """
                        To break the bond will be your undoing
                         -Reduces Damage Dealt using Forsworn Weapons by Nine Tenths
                         -Forsworn Weapons Deteriorate When Used""");

        addSimpleEntryHeader("pact_of_the_berserker", "Pact of The Berserker", "To return pain in kind");
        addGeasDetails(MalumGeasEffectTypes.PACT_OF_THE_BERSERKER,
                scaled(0.7f) + """
                        An endless wrath, a constant anger, your pain brings rage, and rage is power.
                         -Taking damage converts it to Wrath
                         -Dealing damage utilizes Wrath to Repeatedly Blast Enemies with Wicked Arcana""",
                """
                        Hurt yourself, hurt others
                         -Wrath Decreases Overtime
                         -All Incoming Damage is Doubled""");


        addSimpleEntryHeader("pact_of_the_fortress", "Pact of The Fortress", "To stand and defend");
        addGeasDetails(MalumGeasEffectTypes.PACT_OF_THE_FORTRESS,
                """
                        A compression of Soul Ward, made greater and refined.
                         -Increases Soul Ward Capacity
                         -Increases Soul Ward Integrity by Half""",
                "The added grandeur brings with itself a lethargic nature\n -Halves Soul Ward Recovery Rate");

        addSimpleEntryHeader("pact_of_the_shield", "Pact of The Shield", "To always walk with vigilance");
        addGeasDetails(MalumGeasEffectTypes.PACT_OF_THE_SHIELD,
                """
                        An acceleration of Soul Ward, made to be primed in the nick of time.
                         -Increases Soul Ward Capacity
                         -Doubles Soul Ward Recovery Rate""",
                "The added acceleration brings with itself a brittle nature\n -Halves Soul Ward Integrity");

        addSimpleEntryHeader("pact_of_reciprocation", "Pact of Reciprocation", "Two eyes for an eye");
        addGeasDetails(MalumGeasEffectTypes.PACT_OF_RECIPROCATION,
                """
                        A change, a new overruling mechanism.
                         -Dealing Magic Damage generates Soul Ward
                         -Increases Soul Ward Capacity Significantly
                         -Doubles Soul Ward Gain""",
                "War is all you are.\n -Disables Natural Soul Ward Regeneration");


        addSimpleEntryHeader("pact_of_the_shattering_addict", "Pact of The Shattering Addict", "To shatter and shatter");
        addGeasDetails(MalumGeasEffectTypes.PACT_OF_THE_SHATTERING_ADDICT,
                scaled(0.8f) + """
                        An Incantation forced onto the mind. An Insatiable Thirst for the Arcana
                         -Soul Shatter yields increased Arcana
                         -Even more Arcana with Chained Soul Shatters""",
                "The Incantation takes quite a toll on you.\n -Each Day without Reaping Spirits builds up withdrawal, draining more and more hunger until your Thirst is Satiated.");

        addSimpleEntryHeader("pact_of_the_arcanaphage", "Pact of The Arcanaphage", "To extend beyond oneself");
        addGeasDetails(MalumGeasEffectTypes.PACT_OF_THE_ARCANAPHAGE,
                scaled(0.75f) +"""
                        A part of your Soul is drawn out from your body, used as an Attractor for Arcana.
                         -Soul Shatter yields Extra Arcana
                         -Spirit Collection Aids most Magical Attributes""",
                """
                        The Soul remains on the inside for a reason.
                         -All Incoming Damage is converted into Magic Damage
                         -Soul Ward Integrity is halved""");

        addSimpleEntryHeader("pact_of_rune_exploitation", "Pact of The Rune Exploitation", "To follow the ancient pacts");
        addGeasDetails(MalumGeasEffectTypes.PACT_OF_RUNE_EXPLOITATION,
                """
                        A part of your Soul becomes a Housing for Runes.
                         -Grants you Two Rune Slots""",
                scaled(0.8f) + "Your Soul can carry only so much\n -Each Equipped Rune reduces your Healing Received, Armor, Armor Toughness and Magic Resistance by One Twentieth");


        addSimpleEntryHeader("pact_of_self_care", "Pact of Self-Care", "To care for oneself");
        addGeasDetails(MalumGeasEffectTypes.PACT_OF_SELF_CARE,
                """
                        Revel in Delight and Cleanse your Soul.
                         -Food grants Increased Saturation
                         -Eating Food Cleanses Negative Effects""",
                "To Starve is to lose yourself completely.\n -Low Hunger Drains Health Much Faster");

        addSimpleEntryHeader("pact_of_the_high_priest", "Pact of The High Priest", "To spread oneself thin");
        addGeasDetails(MalumGeasEffectTypes.PACT_OF_THE_HIGH_PRIEST,
                """
                        The Whole World is yours.
                         -Increases Block Reach by One Fifth and Entity Reach by One Tenth""",
                scaled(0.8f) + """
                        Your over-encroaching grip is a harmful one.
                         -Reach Effect enters Cooldown State when Struck by a Target
                         -When Reach Effect is Lost, you are Struck with Aqueous Arcana""");

        addSimpleEntryHeader("pact_of_tidal_affinity", "Pact of Tidal Affinity", "To be one with the sea");
        addGeasDetails(MalumGeasEffectTypes.PACT_OF_TIDAL_AFFINITY,
                scaled(0.7f) + """
                        Confine yourself to the Sea, Memorize the Conduit's Choral Pull.
                         -Increases Agility, Attack Speed, Mining Speed and Damage Resistance when Underwater
                         -Conduit Power Amplifies Listed Effects and Idly Recovers Health""",
                """
                        The chains of the ocean grip your heart tightly.
                         -You Cannot Heal Outside of Water""");

        addSimpleEntryHeader("pact_of_patience_repaid", "Pact of Patience Repaid", "To dilute one's pain");
        addGeasDetails(MalumGeasEffectTypes.PACT_OF_PATIENCE_REPAID,
                scaled(0.8f) + """
                        Split the Impact, Soften the Blow.
                         -Half of All Incoming Damage is Buffered; Converted to Damage Over Time
                         -Buffered Damage Cannot Kill You""",
                """
                        Prolonged Pain is Greater Pain.
                         -Buffered Damage becomes Magic Damage and is overall One Fifth More Potent""");
        
        addSimpleEntryHeader("pact_of_the_windswept", "Pact of The Windswept", "To be one with the wind");
        addGeasDetails(MalumGeasEffectTypes.PACT_OF_THE_WINDSWEPT,
                """
                        Move with the Wind.
                         -Continuously Sprinting builds up Movement Speed""",
                "Be Moved by the Wind\n -Ceasing Sprinting rapidly decreases bonus\n -High Momentum amplifies received knockback");

        addSimpleEntryHeader("pact_of_the_continuing_shot", "Pact of The Continuing Shot", "To be the arrow");
        addGeasDetails(MalumGeasEffectTypes.PACT_OF_THE_CONTINUING_SHOT,
                """
                        Precision is Key.
                         -Dealing Ranged Damage accelerates Draw Speed for most Ranged Weapons""",
                "You cannot feign perfection.\n -Missing a Shot will remove any Draw Speed bonuses and instead dampen it");

        addSimpleEntryHeader("pact_of_the_skybreaker", "Pact of The Skybreaker", "To bring it all down");
        addGeasDetails(MalumGeasEffectTypes.PACT_OF_THE_SKYBREAKER,
                scaled(0.8f) + """
                        Gravity; The greatest force to impose onto others.
                         -Taking Fall Damage Automatically Strikes Nearby Creatures
                         -Delivering Fall Damage Strikes The Target Regardless of Distance""",
                """
                        That which you impose on others shall come back twice over.
                         -Incoming Knockback is Doubled""");

        addSimpleEntryHeader("pact_of_the_cloudskipper", "Pact of The Cloudskipper", "To rise above all");
        addGeasDetails(MalumGeasEffectTypes.PACT_OF_THE_CLOUDSKIPPER,
                """
                        Gravity; The greatest force to leverage against.
                         -Wind Gusts Provide Greater Propulsion and Grant Reduced Gravity""",
                """
                        That which you rely on shall eventually crush you.
                         -Fall Damage Taken is Doubled""");


        addSimpleEntryHeader("pact_of_contentedness", "Pact of Contentedness", "To seek satisfaction");
        addGeasDetails(MalumGeasEffectTypes.PACT_OF_CONTENTEDNESS,
                """
                        To be complete is to be satisfied.
                         -Armor and Armor Toughness is Increased by One Fifth when near Satiation""",
                "To be satisfied can be a shackling necessity\n -Armor and Armor Toughness is Decreased by Half when near Starvation");

        addSimpleEntryHeader("pact_of_the_lone_druid", "Pact of The Lone Druid", "To shun the shaped");
        addGeasDetails(MalumGeasEffectTypes.PACT_OF_THE_LONE_DRUID,
                """
                        To be complete is to be unburdened.
                         -Each Empty Armor Slot grants Four Armor and Two Armor Toughness""",
                "To be unburdened can be a shackling standard\n -Wearing any Armour Hurts You");

        addSimpleEntryHeader("pact_of_the_profane_ascetic", "Pact of The Profane Ascetic", "To seek beauty in dross");
        addGeasDetails(MalumGeasEffectTypes.PACT_OF_THE_PROFANE_ASCETIC,
                scaled(0.65f) + """
                        Rot shall become your greatest friend.
                         -Gluttony becomes Trial of Faith, Increasing Healing Received
                         -Trial of Faith lasts Significantly Longer and has an Increased Limit of Power
                         -Eating Rotten Foods Heals You and Omits the Demand for Appetite""",
                """
                        Your loyalty to the Rot takes quite a toll on your body.
                         -Saturation and Hunger no longer restore health
                         -Eating Forsworn Foods brings Ruin""");

        addSimpleEntryHeader("pact_of_the_profane_glutton", "Pact of The Profane Glutton", "To consume");
        addGeasDetails(MalumGeasEffectTypes.PACT_OF_THE_PROFANE_GLUTTON,
                scaled(0.6f) + """
                        Rot shall become your greatest weapon.
                         -Gluttony becomes Desperate Need, Increasing Scythe Proficiency Substantially
                         -Desperate Need Gains Potency Faster with an Increased Limit of Power
                         -Scythe Strikes While Blessed by Desperate Need create an additional Poisonous Slash""",
                scaled(0.8f) + """
                        The Rot will Consume You.
                         -Desperate Need reduces Armor and Magic Resistance
                         -Starvation Damage Poisons You
                         -Poisoned Foes Spread Poison to You""");

        addSimpleEntryHeader("pact_of_combustion", "Pact of Combustion", "To fuel the flame");
        addGeasDetails(MalumGeasEffectTypes.PACT_OF_COMBUSTION,
                """
                        No force more powerful.
                         -Fire Effects you Inflict onto Enemies are Amplified""",
                "You cannot deny the flame it's nature\n -Being Forcibly Extinguished Blasts you with Infernal Arcana");

        addSimpleEntryHeader("pact_of_the_prospector", "Pact of the Prospector", "To grow one's fortune");
        addGeasDetails(MalumGeasEffectTypes.PACT_OF_THE_PROSPECTOR,
                """
                        A favor born from flame.
                         -Collecting Precious Minerals grants Prospector's Streak, Increasing Mining and providing Fortune Chance""",
                "Burn with Greed\n -Being Struck while Imbued with Prospector's Streak Ignites You and Consumes the Effect");

        addSimpleEntryHeader("pact_of_the_pyromaniac", "Pact of The Pyromaniac", "To spread the fire");
        addGeasDetails(MalumGeasEffectTypes.PACT_OF_THE_PYROMANIAC,
                scaled(0.8f) +"""
                        Recklessness and Haste, fueled by Kinetic Impact.
                         -Blowing Yourself Up Generates Pyromaniac's Fervor, Increasing Movement and Mining Speed
                         -Grants Damage Resistance Against Explosions""",
                """
                        You can only handle so much pressure.
                         -High Fervor Ignites You When Struck By Blast Impact
                         -Incoming Fire Damage is Doubled""");

        addSimpleEntryHeader("pact_of_wyrd_reconstruction", "Pact of Wyrd Reconstruction", "To survive one's fate");
        addGeasDetails(MalumGeasEffectTypes.PACT_OF_WYRD_RECONSTRUCTION,
                scaled(0.8f) +"""
                        Unchecked Power derived from Death Itself.
                         -Death Is Avoided Through Reconstruction
                         -Reconstruction is paired with brief Invulnerability and Rapid Activation of Spirit-Collection Effects""",
                scaled(0.8f) +"""
                        The Burning Stars Exhaust You.
                         -Reconstruction Effect bears a Heavy Cooldown
                         -Cooldown State Halves Arcane Resonance
                         -Spirit-Collection Drains Hunger""");

        addSimpleEntryHeader("oath_of_the_overkeen_eye", "Oath of The Overkeen Eye", "To hit one's target");
        addGeasDetails(MalumGeasEffectTypes.OATH_OF_THE_OVERKEEN_EYE,
                """
                        A Keen Eye for the Arcane Imbued onto the Soul
                        -Staff Projectiles Gain Homing Capabilities""",
                """
                        The Imbued Awareness demands Two-Fold Preparation
                         -Staff Charge Recovery Rate is Halved""");

        addSimpleEntryHeader("oath_of_the_overburdened_mind", "Oath of The Overburdened Mind", "To strike overwhemlingly");
        addGeasDetails(MalumGeasEffectTypes.OATH_OF_THE_OVERBURDENED_MIND,
                """
                        A Capacity for Arcane Knowledge Imbued onto the Mind
                        -Doubles Staff Charge Capacity""",
                """
                        The Imbued Insight demands Careful Consideration
                         -Staff Charge Duration is Increased by One Fourth""");

        addSimpleEntryHeader("oath_of_the_overeager_fist", "Oath of The Overeager Fist", "To never hesitate");
        addGeasDetails(MalumGeasEffectTypes.OATH_OF_THE_OVEREAGER_FIST,
                """
                        An Eagerness Imbued onto the Body and Soul
                        -Staff Charge Duration Is Halved""",
                """
                        The Imbued Eagerness demands Immediate Action
                         -Staff Charges Fire Immediately once Fully Charged""");

        addSimpleEntryHeader("oath_of_the_undiscerned_maw", "Oath of The Undiscerned Maw", "To devour all");
        addGeasDetails(MalumGeasEffectTypes.OATH_OF_THE_UNDISCERNED_MAW,
                scaled(0.8f) + """
                        An Etching imbued onto Malignant Deliverance
                        -Malignant Critical Strikes Devour the Life Essence of Afflicted Creatures
                        -Devoured Essence Heals You based on the Damage Dealt""",
                """
                        Born from the Malignant Metal, Devoured Essence seeks to Undo
                        -Overuse of the Healing Effect gradually diminishes All Healing""");

        addSimpleEntryHeader("oath_of_unmakers_disdain", "Oath of Unmakers Disdain", "To lord above");
        addGeasDetails(MalumGeasEffectTypes.OATH_OF_UNMAKERS_DISDAIN,
                """
                        An Etching imbued onto Malignant Deliverance
                        -Malignant Critical Strikes Trigger Several Consecutive Slashes""",
                """
                        Born from the Malignant Metal, Deliverance develops quite the Demand
                        -Malignant Critical Strikes require a Health Threshold to be met""");

        addSimpleEntryHeader("oath_of_unsighted_resistance", "Oath of Unsighted Resistance", "To wade into slaughter");
        addGeasDetails(MalumGeasEffectTypes.OATH_OF_UNSIGHTED_RESISTANCE,
                """
                        An Etching imbued onto Malignant Deliverance
                        -Malignant Critical Strikes Amplify Existing Armor and Armor Toughness""",
                scaled(0.8f) +"""
                        Born from the Malignant Metal, This layer of defense is only possible through Equivalent Exchange
                        -Reduces Malignant Critical Strike Damage by Two Fifths""");

        addSimpleEntryHeader("authority_of_the_inverted_heart", "Authority of the Inverted Heart", "To tie all hearts as one");
        addGeasDetails(MalumGeasEffectTypes.AUTHORITY_OF_THE_INVERTED_HEART,
                scaled(0.7f) + """
                        An Authority, a pact between your Soul and the World itself.
                        -Your Soul Exposes Itself to the World Around You. When Witnessed by another Soul it will Bind You Together
                        -Bound Targets will Receive Any Damage you Take or Deal""",
                scaled(0.8f) +"""
                        Your Soul is the World's Soul. Your Heart is the World's Heart. It is Open, Open for all to See, Vulnerable for all to Grasp.
                        -Quadruples Incoming Magic Damage""");

        addSimpleEntryHeader("authority_of_the_gleeful_target", "Authority of the Gleeful Target", "To be the most satisfied existence");
        addGeasDetails(MalumGeasEffectTypes.AUTHORITY_OF_THE_GLEEFUL_TARGET,
                scaled(0.55f) + """
                        An Authority, a pact between your Soul and the World itself.
                        -Your Body is Wrapped in a Sleeping Stasis. Whenever Struck, the Stasis is Activated
                        -While in Stasis, all Potion Effects are Paused and Cannot Ever Expire.
                        -Effects such as Regeneration or Poison That Demand The Forward Movement of Time Cease Function, However Any Benefits Unbothered By The Stasis Will Remain""",
                scaled(0.8f) +"""
                        Your Body is Frozen in Time, an Anomaly upon this World. It does not Age, nor does it not Feel.
                        -Quarters All Incoming Healing""");

        addSimpleEntryHeader("totem_magic", "Totem Magic", "Arcana unleashed");
        addPages("totem_magic",
                "Up until now, when performing spirit arcana, I have limited my research to personal enhancement and material production. Now, I affect the world.",
                "To begin with Totem Magic, I may engrave spirit arcana into Runewood Logs, forming a rune representing the magic. If unwanted, engraved spirits can be stripped off with an axe, but I have uses in mind. With a Runewood Totem Base, and then a specific set of runes in a totem pole placed above my totem base, I can perform a Spirit Rite.",
                "While each rite does offer a unique function, they follow patterns and categorize easily. Spirit Rites will either affect the souls of nearby creatures, or the arcane structure of the physical world around the totem. Both means of manifesting spirit arcana utilize what's known as a Rite Locus, a spark of directed energy that the Totem effortlessly weaves into existence."
        );

        addSimpleEntryHeader("managing_totems", "Totem Resonance", "Insight into the rites");
        addPages("managing_totems",
                "Totem rites are both complicated and simple, in their own ways. A simple, bounded effect, but dependent on the flow of arcana - and it can be difficult to discern their range.",
                "I have created a staff to act as a tuning fork of sorts for the energies of rites. Simply holding it resonates with the flow of arcana through the world, allowing me to visualize the area each totem can affect.",
                "Interestingly, the staff also allows me to 'tune' a rune into an active state by interacting with it, even if it's not on a totem. This is as far as I can tell purely visual, but if nothing else, it will make good decoration.");

        addSimpleEntryHeader("undirected_rite", "A Rite Undirected", "Creation Without Direction");
        addPages("undirected_rite",
                "Raw arcana provides the basis for all rites. Without power, nothing would be accomplished. This naturally makes one wonder what effect raw arcana would have as the focus of a rite. The answer is a complex and dangerous one.",
                "In order to form any meaningful effect, the spirit rite in question requires far more to form than other rites, taking the entire five runes to activate. It's as though I am pushing on some threshold, and need to break through. And in breaking through... momentum is conserved.");
        addRiteDetails(MalumSpiritRiteTypes.UNDIRECTED_RITE,
                "The rite - if you could call something so chaotic that - corrupts and burns through the totem, altering its very base nature, and transmuting the world around it into some indeterminate blighted substance.",
                "Note: Requires Containment");

        addSimpleEntryHeader("unchained_rite", "A Rite Unchained", "Creation Without Control");
        addPages("unchained_rite",
                "As arcane energy within the totem amassed, lacking direction, it dispersed. Chaotic, but expected given the rune composition. The Undirected Rite brought about erratic change to the totem; what I dub Soulwood bears scars from the violent method of its creation. Those scars warp magic, altering its fundamental nature.",
                "Any spirit rite performed with a Soulwood totem will produce a vastly different effect. Following this logic, the behavior of the Undirected Rite itself can also be altered by the newly formed soulwood structure...");
         addHeadline("unchained_rite.soulwood", "Soulwood Transmutation");
        addPage("unchained_rite.soulwood",
                "One of the immediate applications of the Unchained Transmutation I have found thus far is the ability to transform any Runewood block into it's Soulwood equivalent. It works with any wooden block or even the Runewood Sapling itself");
        addRiteDetails(MalumSpiritRiteTypes.UNCHAINED_RITE,
                "Now already scarred, the power bleeds from the soulwood totem in the form of a Blight-Bound Rite Locus. When this Locus travels through blight, it will transmute the block above.",
                "Creates a Grounded Rite Locus\nLocus Travels Through Blight and Affects The Block Above\nAffected Blocks Undergo Unchained Transmutation\nLocus Requires Blight To Survive");

        addSimpleRiteEntry(MalumSpiritRiteTypes.RITE_OF_HEALING, "",
                "A simple rite, while active it will slowly mend the wounds of nearby beings.",
                "Affected Creatures Are Healed For Two Hearts Of Damage");
        addSimpleRiteEntry(MalumSpiritRiteTypes.RITE_OF_NOURISHMENT, "",
                "A simple rite, while active it will idly recover lost stamina of nearby players, preserving hunger for longer.",
                "Affected Players Receive Sacred Nourishment\n Sacred Nourishment Slowly Absorbs Exhaustion");
        addSimpleRiteEntry(MalumSpiritRiteTypes.RITE_OF_NURTURING, "",
                "An advanced rite, while active it apply a spiritually nourishing effect to nearby animals, accelerating growth and certain biological processes.",
                """
                        Affected Animals Are Aged Twenty Five Seconds Worth
                        Different Species React Differently:
                         Sheep Will Feed On Grass More Frequently
                         Chickens Lay Eggs More Frequently
                         Bees Pollinate Faster And More Frequently
                         Allays Duplicate Faster""");
        addSimpleRiteEntry(MalumSpiritRiteTypes.RITE_OF_LUST, "",
                "An advanced rite, while active... nearby animals are made... " + italic("vigorous") + ", as if I had fed them myself.",
                "Affected Animals Are Made Vigorous\nEffect Ceases When Given Species Faces Overpopulation");

        addSimpleRiteEntry(MalumSpiritRiteTypes.RITE_OF_HARMING, "",
                "A simple rite, while active it will slowly harm souls of nearby beings.",
                "Affected Creatures Are Struck For Two Hearts Of Damage");
        addSimpleRiteEntry(MalumSpiritRiteTypes.RITE_OF_EMPOWERMENT, "",
                "A simple rite, while active it will empower the souls of nearby hostiles.",
                "Affected Hostiles Receive Wicked Empowerment\nWicked Empowerment Strengthens Damage And Health");
        addSimpleRiteEntry(MalumSpiritRiteTypes.RITE_OF_CULLING, "",
                "An advanced rite, while active it will cull herds of nearby overcrowded animals.",
                "Affected Animals Are Obliterated\nEffect Demands Overpopulation Within Given Species");
        addSimpleRiteEntry(MalumSpiritRiteTypes.RITE_OF_RAISING, "",
                "An advanced rite, while active it will strike vulnerable monsters, dealing a fatal blow to their soul and body.\nThe affected target is then reanimated as a soulless husk, a mindless being that lacks a soul.",
                "Affected Monsters Are Struck a Fatal Blow\nUpon Death, They Are Then Reanimated As A Soulless Husk\nSoulless Husks, Lacking A Soul, Do Not Drop Spirits");


        addSimpleRiteEntry(MalumSpiritRiteTypes.RITE_OF_THE_HOWLING_GALE, "",
                "A simple rite, while active it will bless nearby creatures with Howling Gale, increasing movement speed and attack speed.",
                "Affected Creatures Are Blessed With Howling Gale\nHowling Gale increases movement speed and attack speed by two fifths.");
        addSimpleRiteEntry(MalumSpiritRiteTypes.RITE_OF_THE_SKY_TETHER, "",
                "A simple rite, while active it will bless nearby creatures with Sky Tether, increasing jump height and reducing gravity with minimal fall damage reduction.",
                "Affected Creatures Are Blessed With Sky Tether\nSky Tether reduces gravity and increases jump height by two fifths each.\nFall damage is also moderately dampened");
        addSimpleRiteEntry(MalumSpiritRiteTypes.RITE_OF_GRAVITY, "",
                "An advanced rite, while active it will weave a Grounded Rite Locus. When this Locus travels over blocks it will apply a gravity effect to them, causing them to fall.",
                "Creates a Grounded Rite Locus\nLocus Travels Over Blocks, Applying Gravity Effect similar to Sand or Gravel\nCan Only affect Blocks that can be Preserved Using Silktouch");
        addSimpleRiteEntry(MalumSpiritRiteTypes.RITE_OF_ASCENSION, "",
                "An advanced rite, while active it will weave a Grounded Rite Locus. When this Locus travels over blocks it will... something... I don't know yet",
                "Creates a Grounded Rite Locus\nLocus Travels Over Blocks, Applying... Some Sort Of Effect... I don't know yet");

        addSimpleRiteEntry(MalumSpiritRiteTypes.RITE_OF_THE_FLOWING_GRASP, "",
                "A simple rite, while active it will bless nearby creatures with Flowing Grasp, increasing block interaction range and item pickup distance.",
                "Affected Creatures Are Blessed With Flowing Grasp\nFlowing Grasp increases block interaction range and item pickup distance by two fifths");
        addSimpleRiteEntry(MalumSpiritRiteTypes.RITE_OF_THE_GOOD_TIDES, "",
                "A simple rite, while active it will bless nearby creatures with Good Tides, providing one level worth of benefits granted by Lure and Luck of The Sea.",
                "Affected Creatures Are Blessed With Good Tides\nGood Tides increases grants effects equivalent to one level of Lure and Luck of The Sea");
        addSimpleRiteEntry(MalumSpiritRiteTypes.RITE_OF_SOAKING, "",
                "An advanced rite, while active it will weave a Grounded Rite Locus. When this Locus travels over blocks it will apply a growth-inducing effect similar to bonemeal or simply an accelerated passage of time.",
                "Creates a Grounded Lite Locus\nLocus Travels Over Blocks, Applying A Growth-Inducing Effect\nMost Bonemeal Applicable Blocks Are Affected as if with actual Bonemeal\nWhen Affecting Tilted Soil, the crop above will be affected instead with lesser effect");
        addSimpleRiteEntry(MalumSpiritRiteTypes.RITE_OF_SAPPING, "",
                "An advanced rite, while active it will weave a Grounded Rite Locus. When this Locus travels over cauldrons it will greatly accelerate the process of fluid accumulation from any pointed dripstone found above.",
                "Creates a Grounded Rite Locus\nLocus Travels Over Cauldrons, Accelerating Fluid Accumulation with a Generous Upwards Radius");

        addSimpleRiteEntry(MalumSpiritRiteTypes.RITE_OF_THE_STONE_WARD, "",
                "A simple rite, while active it will bless nearby creatures with Stone Ward, providing a damage reduction that grows in power when unarmored.",
                "Affected Creatures Are Blessed With Stone Ward\nStone Ward reduces damage taken by one fifth\nStone Ward Doubles in power when Unarmored");
        addSimpleRiteEntry(MalumSpiritRiteTypes.RITE_OF_THE_OAKEN_MIGHT, "",
                "A simple rite, while active it will .",
                "");
        addSimpleRiteEntry(MalumSpiritRiteTypes.RITE_OF_CREATION, "",
                "An advanced rite, while active it will weave a Grounded Rite Locus. When this Locus travels over blocks it will",
                "");
        addSimpleRiteEntry(MalumSpiritRiteTypes.RITE_OF_DESTRUCTION, "",
                "An advanced rite, while active it will weave a Grounded Rite Locus. When this Locus travels over blocks it will",
                "");

        addSimpleRiteEntry(MalumSpiritRiteTypes.RITE_OF_THE_BURNING_FERVOR, "",
                "A simple rite, while active it will .",
                "");
        addSimpleRiteEntry(MalumSpiritRiteTypes.RITE_OF_THE_FIERY_EMBRACE, "",
                "A simple rite, while active it will .",
                "");
        addSimpleRiteEntry(MalumSpiritRiteTypes.RITE_OF_SMELTING, "",
                "An advanced rite, while active it will weave a Grounded Rite Locus. When this Locus travels over blocks it will",
                "");
        addSimpleRiteEntry(MalumSpiritRiteTypes.RITE_OF_QUICKENING, "",
                "An advanced rite, while active it will weave a Grounded Rite Locus. When this Locus travels over blocks it will",
                "");

//        addRiteDetails(MalumSpiritRiteTypes.ELDRITCH_SACRED_RITE,
//                "An advanced rite, while active nearby crops planted on soil are filled with vigor and will grow more quickly.",
//                "Periodically ages nearby crops. Coverage matches water coverage.");
//
//        addEntryHeader("corrupt_sacred_rite", "Corrupting the Sacred Rites", "Stimulating the soul");
//        addRiteDetails(MalumSpiritRiteTypes.SACRED_RITE,
//                "A simple rite, while active it will apply a spiritually nourishing effect to nearby animals, accelerating growth and certain biological processes.",
//                """
//                        Affected animals instantly gain 25 seconds worth of age
//                         - Sheep will feed on grass more frequently
//                         - Bees pollinate faster and more frequently
//                         - Chickens lay eggs more frequently""");
//        addRiteDetails(MalumSpiritRiteTypes.ELDRITCH_SACRED_RITE,
//                "An advanced rite, while active... nearby animals are made... " + italic("vigorous") + ", as if I had fed them myself.",
//                "Affected animals are fed until there are more than twenty.\n - This limit applies separately for each type of animal within the range of the rite.");
//
//        addEntryHeader("wicked_rite", "Wicked Rites", "Maligning the soul");
//        addRiteDetails(MalumSpiritRiteTypes.WICKED_RITE,
//                "A simple rite, while active it will slowly bring nearby hostile beings to within an inch of death.",
//                "Deals one heart of non-lethal damage every two seconds.");
//        addRiteDetails(MalumSpiritRiteTypes.ELDRITCH_WICKED_RITE,
//                "An advanced rite, while active nearby beings on the brink of death are dealt a fatal blow to the body and soul.",
//                "Affected entities are dealt a fatal blow, dropping items and spirits on death.\n - Avoids entities with more than two and a half hearts remaining.");
//
//        addEntryHeader("corrupt_wicked_rite", "Corrupting the Wicked Rites", "Endangering the soul");
//        addRiteDetails(MalumSpiritRiteTypes.WICKED_RITE,
//                "Rather than harm, this rite enhances nearby beings, granting protection, force, and speed. Players are unfortunately omitted from this effect. Might have niche applications.",
//                "Grants all nearby non-Player entities resistance, strength, and speed.");
//        addRiteDetails(MalumSpiritRiteTypes.ELDRITCH_WICKED_RITE,
//                "An advanced rite, while active it will cull herds of nearby overcrowded animals.",
//                "While there are more than twenty animals within the range of the rite, the excess is removed.\n - This limit applies separately for each type of animal within the range of the rite.");
//
//        addEntryHeader("aerial_rite", "Aerial Rites", "Uplifting the soul");
//        addRiteDetails(MalumSpiritRiteTypes.AERIAL_RITE, "A simple aura rite, while active nearby friendly beings will find their movements sped up.",
//                "Applies Zephyr's Courage, increasing movement speed by two fifths.");
//        addRiteDetails(MalumSpiritRiteTypes.ELDRITCH_AERIAL_RITE,
//                "An advanced rite, by twisting the power of the air, blocks before the totem will be made to fall as though they were sand. Nothing Silk Touch cannot grab will be affected, though.",
//                "Causes targeted blocks to fall downwards if there is nothing underneath them.");
//
//        addEntryHeader("corrupt_aerial_rite", "Corrupting the Aerial Rites", "Scattering the soul");
//        addRiteDetails(MalumSpiritRiteTypes.AERIAL_RITE,
//                "A simple aura rite, while active nearby friendly beings will have their connection to the earth disrupted, lowering their gravity and increasing jump height.",
//                "Applies Aether's Charm, decreasing gravity by three fifths while also providing a substantial benefit to jump height.");
//        addRiteDetails(MalumSpiritRiteTypes.ELDRITCH_AERIAL_RITE,
//                "An advanced rite, while active it will slowly ease the stress of time on the mind, offsetting the effects of insomnia for those around it over time.",
//                "Passively reduces the insomnia value of nearby players.\n - Assuming phantoms are just starting to appear, it will take a single totem executing the rite two and two fifths of a minute to fully cleanse insomnia.\n - Naturally, the totem will take longer to fully cleanse insomnia if the player has already been suffering from it for some time.");
//
//        addEntryHeader("earthen_rite", "Earthen Rites", "Grounding the soul");
//        addRiteDetails(MalumSpiritRiteTypes.EARTHEN_RITE,
//                "A simple aura rite, while active nearby friendly beings will find their bodies are tougher and more resistant to damage.",
//                "Applies Gaia's Bulwark, increasing armor by four and armor toughness by two.");
//        addRiteDetails(MalumSpiritRiteTypes.ELDRITCH_EARTHEN_RITE,
//                "An advanced rite, while active it will cause blocks before the totem base to be broken.",
//                "Breaks targeted blocks. Unbreakable blocks behave as to be expected.");
//
//        addEntryHeader("corrupt_earthen_rite", "Corrupting the Earthen Rites", "Honing the soul");
//        addRiteDetails(MalumSpiritRiteTypes.EARTHEN_RITE,
//                "A simple aura rite, while active nearby friendly beings will find their attacks deal more damage.",
//                "Applies Earthen Might, increasing damage dealt by two hearts.");
//        addRiteDetails(MalumSpiritRiteTypes.ELDRITCH_EARTHEN_RITE,
//                "An advanced rite, while active the earth coalesces, and like lava meeting water, cobblestone is created before the totem base.",
//                "Creates cobblestone in place of empty space.");
//
//        addEntryHeader("infernal_rite", "Infernal Rites", "Igniting the soul");
//        addRiteDetails(MalumSpiritRiteTypes.INFERNAL_RITE,
//                "A simple aura rite, while active nearby friendly beings will find that their motions are infused with fiery vigor, letting them swing weapons and tools faster.",
//                "Applies Miner's Rage, increasing attack rate and dig speed by two fifths.");
//        addRiteDetails(MalumSpiritRiteTypes.ELDRITCH_INFERNAL_RITE,
//                "An advanced rite, while active it will cause blocks before the totem base to be smelted.",
//                "Smelts targeted blocks that can be smelted into other blocks.");
//
//        addEntryHeader("corrupt_infernal_rite", "Corrupting the Infernal Rites", "Extinguishing the soul");
//        addRiteDetails(MalumSpiritRiteTypes.INFERNAL_RITE,
//                "A simple aura rite, while active nearby friendly beings and close fires will have the heat sucked out of them, extinguishing them and healing those who were burned, giving them the survivability of denizens of the nether.",
//                "Extinguishes nearby flames, be it affecting the world or an entity.\n - Extinguished entities receive Ifrit's Embrace, recovering two hearts while being extinguished.");
//        addRiteDetails(MalumSpiritRiteTypes.ELDRITCH_INFERNAL_RITE,
//                "An advanced rite, instead of generating heat, this rite compresses it, causing nearby furnaces to operate more quickly.",
//                "Speeds up nearby furnaces by one fourth.\n - Fuel consumption rate is unaffected, meaning the rite also improves fuel efficiency.");
//
//        addEntryHeader("aqueous_rite", "Aqueous Rites", "Molding the soul");
//        addRiteDetails(MalumSpiritRiteTypes.AQUEOUS_RITE,
//                "A simple aura rite, while active nearby friendly beings will find that their reach is extended, letting them more easily interact with the world.",
//                "Applies Poseidon's Grasp, increasing block reach by two units of space and increasing item pickup range significantly.");
//        addRiteDetails(MalumSpiritRiteTypes.ELDRITCH_AQUEOUS_RITE,
//                "An advanced rite, while active, it will vastly increasing the drip speed of dripstone, causing more fluid to be produced.",
//                "Speeds up dripstone fluid production, works on both lava and water.\n - Only the tip of hanging dripstone needs to be within range for the effect to trigger.");
//
//        addEntryHeader("corrupt_aqueous_rite", "Corrupting the Aqueous Rites", "Deforming the soul");
//        addRiteDetails(MalumSpiritRiteTypes.AQUEOUS_RITE,
//                "A simple aura rite, while active nearby friendly beings will find themselves better at fishing.",
//                "Applies Angler's Lure, providing benefits to fishing skills equal to Lure I and Luck of the Sea I.\n - The effects stack with any enchantment already present on a fishing rod.");
//        addRiteDetails(MalumSpiritRiteTypes.ELDRITCH_AQUEOUS_RITE,
//                "An advanced rite, while active zombies near this rite will find themselves choking on their own breath, drowning even on land.",
//                "Converts nearby zombies to drowned.");

        addEntryHeader("blight", "A Study on Blight", "What, why, and how");
        addHeadline("blight.intro", "A Study on Blight");
        addPages("blight.intro",
                "Blight. " + italic("Something which spoils or damages.") + " What the Undirected Rite has created has many strange properties, and I intend to categorize them.",
                "The naïve explanation is that it is simply another form of power that taints the world, but that isn't right. Blight isn't harmful, not inof itself. It's just... " + italic("gunk."));
        addHeadline("blight.composition", "Blight Study: Substance");
        addPages("blight.composition",
                "The Undirected Rite, as the name suggests, is random. It transmutes, but it has no pattern to transmute things to. So, instead, you get something random, bits of disparate matter all jumbled together into a foul-smelling powder. I wouldn't recommend eating it, or growing things on it, but it's otherwise harmless.");
        addHeadline("blight.flora", "Blight Study: Flora");
        addPages("blight.flora",
                "While experimenting with the interaction between Blight and Bonemeal, I have observed two plants that frequently emerge; the Blightroot and the Blightpearl. Neither of these are useful in any way whatsoever, but they appear to thrive within Blight alone without the need for water or light. Interesting.");
        addHeadline("blight.spread", "Blight Study: Spread");
        addPages("blight.spread",
                "Blight does not spread on its own. It's just random matter, after all. But it has a spiritual memory, a pattern which to replicate. When given arcana, or a valid fertilizer, blight will haphazardly echo this pattern on the nearby area.");
        addHeadline("blight.arcane_rite", "Blight Study: Resonance");
        addPages("blight.arcane_rite",
                "That echo is why this substance is important for the Unchained Rite. The Rite remembers the violence of its creation, and resonates with the memory within the blight, applying its power to things laying on top of it.");

        addEntryHeader("soulwood", "A Study on Soulwood", "Twisted trees");
        addHeadline("soulwood", "A Study on Soulwood");
        addPages("soulwood",
                "After further study, I have discovered that the Soulwood produced by the Unchained Rite has actually become an entirely different species from the Runewood it is made from. It grows differently, it acts differently...",
                "It appears the spiritual scars that created it go deeper than just its color and magic. The most obvious differences with the tree itself are shape and leaf color. Soulwood is more spindly than Runewood, and its leaves are a sickly purple-red hue instead of a rich orange-yellow. It can still be used for many of the same things, though.");
        addHeadline("soulwood.blight", "Soulwood Study: Blight");
        addPages("soulwood.blight",
                "Another obvious difference is in its effect on the surroundings - namely, the fact that it echoes the Undirected Rite with the energies of its growth, transmuting the world around it into blight. I ought to create a safely contained area if I wish to grow these trees.");
        addHeadline("soulwood.bonemeal", "Soulwood Study: Growth");
        addPages("soulwood.bonemeal",
                "Much like blight, the sapling accepts both spirit arcana and common fertilizers such as bonemeal. The end result is roughly the same across both options.");
        addHeadline("soulwood.cursed_sap", "Soulwood Study: Sap");
        addPages("soulwood.cursed_sap",
                "The sticky lifeblood of the tree also seems to well up much more often in Soulwood than in Runewood. The sap's effects are corrupted as well; rather than restoring my vigor, Cursed Sap enhances it, increasing my attack strength and providing damage resistance.");

        addEntryHeader("scarstone", "A Study on Scarstone", "Warped Rock");
        addHeadline("scarstone", "A Study on Scarstone");
        addPages("scarstone",
                "While observing the spread of Blight (in controlled conditions, of course), I have found that rarely the metaphysical scarring is potent enough to affect even stone - hence, I have named it Scarstone.");
        addHeadline("scarstone.material", "Scarstone Study: Material");
        addPages("scarstone.material",
                "Scarstone is a strange kind of rock, similar to Blight, in a way. I have identified granite, obsidian, diorite, and andesite flecks, as well as more mundane stone, with no rhyme or reason to the pattern. It doesn't resonate for the purposes of the Unchained Rite, though.");
        addHeadline("scarstone.creation", "Scarstone Study: Creation");
        addPages("scarstone.creation",
                "It appears to only crop up randomly, and I have not isolated the conditions required. Roughly every tenth time Blight spreads, the trauma will carve out a Scarstone deposit.");
        addHeadline("scarstone.strange_crystal", "Scarstone Study: Strange Crystal");
        addPages("scarstone.strange_crystal",
                "While it doesn't resonate with the Unchained Rite, the stone has a strange... growth? It acts like a plant, despite being a crystal. I do not know what to make of that. Perhaps if I was a biomancer, this might be the discovery of a lifetime.");
        addHeadline("strange_crystal.material", "Strange Crystal Study: Material");
        addPages("strange_crystal.material",
                "These Strange Crystals are tough, but oddly flexible. The striation is fascinating, and seems to indicate... something, though I know not what.");
        addHeadline("strange_crystal.purpose", "Strange Crystal Study: Purpose");
        addPages("strange_crystal.purpose",
                "The material appears to be unreactive to the arcane, making it odder still. I haven't found a use for it, though that might be due to me not being a biologist. The material, as strange as it appears, as strange as Blight can be, is just... ordinary.",
                "It is quite pleasant to look at, I suppose, and a curiosity. A... plant? That grows with Blight, all on its own, with no basis like Soulwood's origin as Runewood. Fascinating, but ultimately, I have not found a use for it.");

        addSimpleEntryHeader("unchained_transmutation", "Unchained Transmutation", "Volatile reactions");
        addPages("unchained_transmutation.intro",
                "The Unchained Rite's echoes can scar more than simply Runewood. The patterns seem somewhat random, but then, blight is a substance of randomness. Trial and error has identified three categories; transmutation trees, of sorts.",
                "The effect of applying these scars depends on what material we start with. The pulsing of the Rite will shift any given block forward in the tree, with it degrading into blight given enough time.");
        addHeadline("unchained_transmutation.dirt", "Seedless Growth");
        addPages("unchained_transmutation.dirt",
                "In dirt, the rite encourages growth without any source. The dirt first grows wooden roots through its structure, which then somehow become grass. The mechanism is conceptual, and the next stage makes that even more clear - moss covers the dirt, consuming the entire volume of it. After that point, the growth has no direction to go, and it becomes randomized, creating Blight.");
        addHeadline("unchained_transmutation.stone", "Forceless Pulverization");
        addPages("unchained_transmutation.stone",
                "In raw stone, the rite powders the stone, bit by bit. The process is exponential - Raw stone becomes cobblestone, which becomes gravel, which becomes sand. Afterwards, the particles fail to divide into a finer substance, and the backlash creates Blight.");
        addHeadline("unchained_transmutation.basalt", "Heatless Incineration");
        addPages("unchained_transmutation.basalt",
                "In Basalt, the rite appears to mimic natural stone's pulverization, but through Nether materials instead. Basalt returns to magma, which crumbles apart and cools into fleshy Netherrack, and rots into Soul Sand. The souls react poorly with the rite, and Blight is the result.");
        addHeadline("unchained_transmutation.mud", "Fluidless Infusion");
        addPages("unchained_transmutation.mud",
                "In mud, the rite infuses the material with the concept of water. Mud becomes clay, as it does naturally, but then the clay crystallizes into mineral prismarine, which finally gives in to the water, becoming ice. It cannot become more water than frozen water already is, and so becomes Blight.");
        addHeadline("unchained_transmutation.packed_mud", "Orange Stones");
        addPages("unchained_transmutation.packed_mud",
                "One of a trio of seemingly color-based sequences, Packed Mud seems to fulfill the conditions to become Dripstone, oddly enough. That Dripstone will then degrade into Granite, which will then rejoin other types of stone in their sequence.");
        addHeadline("unchained_transmutation.snow", "White Stones");
        addPages("unchained_transmutation.snow",
                "One of a trio of seemingly color-based sequences, Snow bizarrely will become Calcite. That Calcite will then degrade into Diorite, which will then rejoin other types of stone in their sequence.");
        addHeadline("unchained_transmutation.deepslate", "Gray Stones");
        addPages("unchained_transmutation.deepslate",
                "One of a trio of seemingly color-based sequences, Deepslate will - a bit more logically - become Tuff. That Tuff will then degrade into Andesite, which will then rejoin other types of stone in their sequence.");

        addSimpleEntryHeader("tyrving", "Tyrving", "Ancient relic");
        addPages("tyrving",
                "My first scythes damaged the body and soul in sequence to shatter the soul. While I have moved beyond needing to do that, the Tyrving is a sword built to weaponize the effect.",
                "When Striking a Soul, the Paracausal Flame channeled through Twisted Rock creates a distortion, which inflicts magic damage proportional to the complexity and potency of a soul. The distortion also 'snaps back', after a moment, causing further damage after the initial strike as the soul violently reasserts itself.",
                "A Unique limitation however is that the sword cannot sweep and by extension cannot accept Sweeping Edge as an enchantment.",
                "The weapon has a strange affinity with sharp volcanic glasses, allowing me to repair it with Obsidian in a Spirit Crucible instead of any more expensive material.");

        addSimpleEntryHeader("belt_of_the_magebane", "Belt of the Magebane", "Newfound ruin");
        addPages("belt_of_the_magebane", "The Belt of the Magebane is a simple innovation, but a dangerously effective one. Normally, after being struck by any attack, soul ward will not recover until a long moment after. That moment of downtime has proven itself detrimental far too frequently. But that ends now.",
                "While worn, the belt provides a substantial bonus to soul ward recovery rate, while also improving capacity slightly. Furthermore, the belt will absorb the arcane essence of any instance of magical damage that strikes its bearer, converting that repurposed energy into immediate recovery of soul ward.");

        addSimpleEntryHeader("the_device", "The Device.", "microwave to recharge");
        addPage("the_device", "even works while bended");

        addSimpleEntryHeader("a_personal_note", "A Personal Note", "A page from another book");
        addPages("a_personal_note",
                "Within the 'Arcana, a page sticks out just barely. As you examine it, the writing seems different than usual- out of this world. The texts inside appear to be a show of gratitude of sorts. The names ring a bell, but not to you.");

        addSimpleEntryHeader("a_personal_note.commendations", "Malum; Commendations", "A Thank You Letter");
        addHeadline("a_personal_note.commendations.wiresegal", "Wiresegal");
        addPages("a_personal_note.commendations.wiresegal",
                "I'm not quite sure how we met, can't remember really. At some point, they offered to help out with Malum- most notably with the Encyclopedia Arcana, but also with a decent few bug fixes and refactors of dated systems. A large majority of the Entries are written by them :3");
        addHeadline("a_personal_note.commendations.alphalilly", "Alphalilly");
        addPages("a_personal_note.commendations.alphalilly",
                "I reached out to them after seeing their work for the Arcana project, the Malum' book background art was made by them per commission. They've done an incredible job with them and I'm very happy to have their work in game :3");
        addHeadline("a_personal_note.commendations.pessi_mysterio", "Pessi Mysterio");
        addPages("a_personal_note.commendations.pessi_mysterio",
                "I remember going on quite a witch hunt to find them, scouring through the internet and asking a bunch of people. They worked on Sound Design for a few mods and so I reached out to them to commission some for Malum. The Spirit Altar, Crucible and Totems are some of their works for this project.");
        addHeadline("a_personal_note.commendations.kultik", "Kultik");
        addPages("a_personal_note.commendations.kultik",
                "Once again, no idea how we got in touch, but they showed quite an interest in Malum and offered to have one of their tracks added to the mod as a music disc. They're responsible for the Aesthetica and Arcane Elegy Music Discs :3");
        addHeadline("a_personal_note.commendations.coalition_of_magic", "Coalition of Magic");
        addPages("a_personal_note.commendations.coalition_of_magic",
                "The Coalition of Magic is a Supporter exclusive server organized by me and Joefoxe, without everyone's voices and support of the mod, it'd be difficult to find motivation to work as much as I can now. Thank you!");
        addHeadline("a_personal_note.commendations.translation", "Translation");
        addPages("a_personal_note.commendations.translation",
                "There's this fella on github who very routinely pushes translations to the mod for the Chinese language, figured they deserve some thanks. They go by ChuijkYahus, so if you're reading this in Chinese it's thanks to them! If you plan to do a complete translation of Malum, let me know- I hope in the future I can replace this page with a list of credits.");

        addSimpleEntryHeader("a_personal_note.inspirations", "Malum; Inspirations", "What brought me here");
        addHeadline("a_personal_note.inspirations.thaumcraft", "Thaumcraft");
        addPages("a_personal_note.inspirations.thaumcraft",
                "By far the biggest inspiration for Malum, I enjoyed the mod immensely when I was first getting into modded minecraft. I only ever tried the 1.12 version though. The way the magic system is designed and the general sense of progression is something I love.");
        addHeadline("a_personal_note.inspirations.astral_sorcery", "Astral Sorcery");
        addPages("a_personal_note.inspirations.astral_sorcery",
                "I love the mod's visual identity and mechanics, anytime it was in a pack I'd make sure to make the most of it. The Path of Exiles is a huge inspiration for some upcoming stuff I wanna add to Malum.");
        addHeadline("a_personal_note.inspirations.eidolon", "Eidolon");
        addPages("a_personal_note.inspirations.eidolon",
                "Eidolon has such a charming atmosphere, when it released I got a little scared about how similar the mod was to my concept of what I want Malum to be, and it really pushed me to innovate. Additionally, the mod's author and I exchange a myriad of cat pictures, it is a charm.");
        addHeadline("a_personal_note.inspirations.mystic_modding", "Mystic Modding' Mods");
        addPages("a_personal_note.inspirations.mystic_modding",
                "Embers and Roots were some of my favourite magic mods I've come across, I found their very approachable nature a really appealing thing for a mod to incorporate.");

    }
}
