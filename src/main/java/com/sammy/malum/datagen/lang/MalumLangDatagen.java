package com.sammy.malum.datagen.lang;

import com.sammy.malum.MalumMod;
import com.sammy.malum.common.block.curiosities.artifice.crystallarium.*;
import com.sammy.malum.common.data.component.*;
import com.sammy.malum.common.item.*;
import com.sammy.malum.compat.create.*;
import com.sammy.malum.core.handlers.KeywordTooltipHandler;
import com.sammy.malum.core.systems.artifice.ArtificeAttributeType;
import com.sammy.malum.common.block.ether.EtherWallTorchBlock;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.core.systems.registry.*;
import com.sammy.malum.core.systems.rite.*;
import com.sammy.malum.core.systems.rite.effect.SpiritRiteEffectTag;
import com.sammy.malum.core.systems.spirit.type.*;
import com.sammy.malum.datagen.lang.effect.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.MalumContent;
import com.sammy.malum.registry.common.enchantment.*;
import com.sammy.malum.registry.common.magic.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.*;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.WallTorchBlock;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredHolder;
import team.lodestar.lodestone.helpers.DataHelper;
import team.lodestar.lodestone.modules.toolkit.creative_tab.CategorizedCreativeTab;

import java.util.*;
import java.util.function.Supplier;

import static com.sammy.malum.registry.common.MalumAttributes.ATTRIBUTES;
import static com.sammy.malum.registry.common.MalumMobEffects.MOB_EFFECTS;
import static com.sammy.malum.registry.common.sound.MalumSoundEvents.SOUND_EVENTS;
import static com.sammy.malum.registry.common.magic.MalumGeasEffectTypes.GEAS_TYPES;
import static com.sammy.malum.registry.common.magic.rite.MalumSpiritRiteTypes.RITE_TYPES;
import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.SPIRIT_TYPES;
import static com.sammy.malum.registry.common.MalumContent.BLOCKS;
import static com.sammy.malum.registry.common.entity.MalumEntityTypes.ENTITY_TYPES;

public class MalumLangDatagen extends LanguageProvider {
    public static MalumLangDatagen lang;

    public MalumLangDatagen(PackOutput gen) {
        super(gen, MalumMod.MALUM, "en_us");
        lang = this;
    }

    @Override
    protected void addTranslations() {
        CodexLangDatagen.generateEntries();
        IntroductionChapterLangDatagen.generateEntries();

        CreativeTabLangDatagen.addTranslations();
        WaveformInterfaceLangDatagen.addTranslations();
        SoulstoneMetallicsLangDatagen.addTranslations();

        EffectKeywordLangDatagen.addTranslations();
        CurioEffectLangDatagen.addTranslations();
        GeasEffectLangDatagen.addTranslations();
        ItemEffectLangDatagen.addTranslations();

        add("container.malum.wand_tinkerer", "Wand Tinkerer");
        add("container.malum.conjuncture_crystallarium", "Conjuncture Crystallarium");

        var blocks = new HashSet<>(BLOCKS.getEntries());
        var items = new HashSet<>(MalumContent.ITEMS.getEntries());
        var sounds = new HashSet<>(SOUND_EVENTS.getEntries());
        var effects = new HashSet<>(MOB_EFFECTS.getEntries());
        var attributes = new HashSet<>(ATTRIBUTES.getEntries());
        var entities = new HashSet<>(ENTITY_TYPES.getEntries());
        var spirits = new HashSet<>(SPIRIT_TYPES.getEntries());
        var rites = new HashSet<>(RITE_TYPES.getEntries());
        var geasa = new HashSet<>(GEAS_TYPES.getEntries());
        var soulwovenBanners = SoulwovenBannerPatternDataComponent.REGISTERED_PATTERNS;
        var crucibleAttributes = ArtificeAttributeType.CRUCIBLE_ATTRIBUTES;

        add(DataHelper.take(blocks, MalumContent.DungeonBlockSets.ODD_SCRIPTURES_I.block()).get(), "Odd Scriptures I");


        add(DataHelper.take(blocks, MalumContent.WeepingWell.PRIMORDIAL_SOUP.block()).get(), "The Weeping Well");
        add(DataHelper.take(blocks, MalumContent.WeepingWell.VOID_CONDUIT.block()).get(), "The Weeping Well");

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
            String id = s.getId().getPath();
            String name = correctSoundName(id).replaceAll("_", " ");
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            add("malum.subtitle." + id, name);
        });

        effects.forEach(e -> {
            String id = e.getId().getPath();
            String name = DataHelper.toTitleCase(makeProperEnglish(id), "_");
            add("effect.malum." + id, name);
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
            SpiritArcanaType spirit = s.get();
            String name = DataHelper.toTitleCase(spirit.getName(), "_");
            add(spirit.getCountedKey(), "%1$s " + name);
            add(spirit.getLangKey(), name);
        });
        rites.forEach(r -> {
            SpiritRiteType rite = r.get();
            String name = DataHelper.toTitleCase(rite.getName(), "_");
            add(rite.getLangKey(), name);
        });
        geasa.forEach(g -> {
            GeasEffectType effect = g.get();
            String name = DataHelper.toTitleCase(g.getId().getPath(), "_");
            add(effect.getLangKey(), name);
        });
        soulwovenBanners.forEach(p -> {
            String name = DataHelper.toTitleCase(p.type().getPath(), "_");
            add(p.translationKey(), name);
        });
        crucibleAttributes.forEach(a -> {
            String name = DataHelper.toTitleCase(a.getId().getPath(), "_");
            add(a.getLangKey(), name);
        });


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

        addRiteTag(SpiritRiteEffectTag.RUNEWOOD, "Runewood");
        addRiteTag(SpiritRiteEffectTag.SOULWOOD, "Soulwood");
        addRiteTag(SpiritRiteEffectTag.AURA, "Aura");

        addRiteTag(SpiritRiteEffectTag.LESSER_RITE, "Lesser Rite");
        addRiteTag(SpiritRiteEffectTag.GREATER_RITE, "Greater Rite");

        addRiteTag(SpiritRiteEffectTag.RADIAL_EFFECT, "Area of Effect");
        addRiteTag(SpiritRiteEffectTag.LOCUS_EFFECT, "Bound Locus Effect");
        addRiteTag(SpiritRiteEffectTag.STRANGE_EFFECT, "Strange Effect");

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
        addGeasDescription(MalumGeasEffectTypes.PACT_OF_PATIENCE_REPAID, "A warped echo");

        addGeasDescription(MalumGeasEffectTypes.PACT_OF_THE_WINDSWEPT, "Run as the wind");
        addGeasDescription(MalumGeasEffectTypes.PACT_OF_THE_CONTINUING_SHOT, "Step. Form. Ready. Raise. Begin. Draw. Release.");
        addGeasDescription(MalumGeasEffectTypes.PACT_OF_THE_SKYBREAKER, "Move and be moved");

        addGeasDescription(MalumGeasEffectTypes.PACT_OF_CONTENTEDNESS, "To be full is to be anchored");
        addGeasDescription(MalumGeasEffectTypes.PACT_OF_THE_LONE_DRUID, "Shed your second skin");
        addGeasDescription(MalumGeasEffectTypes.PACT_OF_THE_PROFANE_ASCETIC, "Forswear indulgence, and be healed by rot");

        addGeasDescription(MalumGeasEffectTypes.PACT_OF_COMBUSTION, "To ignite and watch");
        addGeasDescription(MalumGeasEffectTypes.PACT_OF_THE_PROSPECTOR, "Engulf yourself with greed");
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
//        addGeasDescription(MalumGeasEffectTypes.AUTHORITY_OF_CRUSHING_MELANCHOLY, "Dissociate from the weight of your existence");
        addGeasDescription(MalumGeasEffectTypes.AUTHORITY_OF_THE_GLEEFUL_TARGET, "Take it all, let it never stop, more, and more, and more");

        addGeasDescription(MalumGeasEffectTypes.CREED_OF_THE_BLIGHT_EATER, "Mmmm... Blight... So Tasty..");

        add("jukebox_song.malum.arcane_elegy", "Kultik - Arcane Elegy");
        add("jukebox_song.malum.aesthetica", "Kultik - Aesthetica");

        add("curios.identifier.brooch", "Brooch");
        add("curios.modifiers.brooch", "When worn:");

        add("curios.identifier.rune", "Rune");
        add("curios.modifiers.rune", "When equipped:");

        add("malum.jei.spirit_infusion", "Spirit Infusion");
        add("malum.jei.spirit_focusing", "Spirit Focusing");
        add("malum.jei.spirit_repair", "Spirit Repair");
        add("malum.jei.spirit_rite", "Spirit Rites");
        add("malum.jei.runeworking", "Runeworking");
        add("malum.jei.weeping_well", "The Weeping Well");
        add("malum.jei.spirit_transmutation", "The Unchained Rite");


        addPlayerlessDeathMessage(MalumDamageTypes.CULTIST_MAGIC, "%1$s met their end", "%1$s met their end while trying to escape %2$s");

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
        addDeathMessage(MalumDamageTypes.HIDDEN_BLADE_MAGIC_COUNTER, "%1$s had their soul shattered into innumerable pieces by %2$s", "%1$s had their soul shattered into innumerable pieces by %2$s using %3$s");

        addDeathMessage(MalumDamageTypes.TYRVING, "%1$s had their soul scarred by %2$s", "%1$s had their soul scarred by %2$s using %3$s");

        addDeathMessage(MalumDamageTypes.SUNDERING_ANCHOR_PHYSICAL_COMBO, "%1$s was struck down through torment by %2$s", "%1$s was struck down through torment by %2$s using %3$s");
        addDeathMessage(MalumDamageTypes.SUNDERING_ANCHOR_MAGIC_COMBO, "%1$s had their soul struck down through torment by %2$s", "%1$s had their soul struck down through torment by %2$s using %3$s");

        addDeathMessage(MalumDamageTypes.WARLOCK_SPIRIT_IMPACT, "%1$s had their soul shattered by %2$s", "%1$s had their soul shattered by %2$s using %3$s");
        addDeathMessage(MalumDamageTypes.BERSERKER_SPIRIT_IMPACT, "%1$s had their soul shattered by %2$s", "%1$s had their soul shattered by %2$s using %3$s");

        addDeathMessage(MalumDamageTypes.INVERTED_HEART_PROPAGATION, "%1$s was caught in %2$s's karmic flow", "%1$s was caught in %2$s's karmic flow using %3$s");
        addDeathMessage(MalumDamageTypes.INVERTED_HEART_RETALIATION, "%1$s was caught in %2$s's karmic flow", "%1$s was caught in %2$s's karmic flow using %3$s");

        addJEEDEffectDescription(MalumMobEffects.STONE_WARD, "An earthen carapace surrounds your body, functioning as extra armor.");
        addJEEDEffectDescription(MalumMobEffects.OAKEN_MIGHT, "Your fists and tools are reinforced with earth, increasing your strength.");
        addJEEDEffectDescription(MalumMobEffects.BURNING_FERVOR, "Your tools are bolstered with radiance, increasing your mining and attack speed.");
        addJEEDEffectDescription(MalumMobEffects.FIERY_EMBRACE, "The warm embrace of fire coats your soul, mending your seared scars.");
        addJEEDEffectDescription(MalumMobEffects.HOWLING_GALE, "The zephyr propels you forward, increasing your movement speed.");
        addJEEDEffectDescription(MalumMobEffects.SKY_TETHER, "The heavens call for you, increasing jump height and decreasing gravity.");
        addJEEDEffectDescription(MalumMobEffects.FLOWING_GRASP, "You reach out for further power, increasing your reach and item pickup distance.");
        addJEEDEffectDescription(MalumMobEffects.GOOD_TIDES, "Let any fish who meets my gaze learn the true meaning of fear; for I am the harbinger of death. The bane of creatures sub-aqueous, my rod is true and unwavering as I cast into the aquatic abyss. A man, scorned by this uncaring Earth, finds solace in the sea. My only friend, the worm upon my hook. Wriggling, writhing, struggling to surmount the mortal pointlessness that permeates this barren world. I am alone. I am empty. And yet, I fish.");

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

        addEnchantmentNameAndDescription(EnchantmentKeys.WEAVERS_PROPAGATION, "Weaver's Propagation", "Summons additional spell loci when breaking a block with the tool.");
        addEnchantmentNameAndDescription(EnchantmentKeys.WEAVERS_HASTE, "Weaver's Haste", "Accelerates any spawned spell loci by the tool.");

        addEnchantmentNameAndDescription(EnchantmentKeys.REPLENISHING, "Restores Spell Charges when dealing melee damage with the staff.");
        addEnchantmentNameAndDescription(EnchantmentKeys.CAPACITOR, "Adds additional Spell Charges for use with the staff");

        addAttributeLibAttributeDescription(MalumAttributes.SCYTHE_PROFICIENCY, "Damage multiplier for Scythes");
        addAttributeLibAttributeDescription(MalumAttributes.SPIRIT_SPOILS, "Flat increase to spirits looted from slain foes");
        addAttributeLibAttributeDescription(MalumAttributes.ARCANE_RESONANCE, "Bonus potency for spirit-collection effects");

        addAttributeLibAttributeDescription(MalumAttributes.HEALING_MULTIPLIER, "An increase in healing received");

        addAttributeLibAttributeDescription(MalumAttributes.SOUL_WARD_INTEGRITY, "A percentile increase in durability for Soul Ward");
        addAttributeLibAttributeDescription(MalumAttributes.SOUL_WARD_RECOVERY_RATE, "A percentile increase in recovery rate for Soul Ward");
        addAttributeLibAttributeDescription(MalumAttributes.SOUL_WARD_RECOVERY_GAIN, "A percentile increase the amount of Soul Ward recovered");
        addAttributeLibAttributeDescription(MalumAttributes.SOUL_WARD_CAPACITY, "The capacity for Soul Ward");

        addAttributeLibAttributeDescription(MalumAttributes.CHARGE_DURATION, "The duration for charging a staff");
        addAttributeLibAttributeDescription(MalumAttributes.CHARGE_CAPACITY, "The capacity for Spell Charges");
        addAttributeLibAttributeDescription(MalumAttributes.CHARGE_RECOVERY_RATE, "A percentile increase in recovery rate for Spell Charges");

        addAttributeLibAttributeDescription(MalumAttributes.GEAS_LIMIT, "The limit for active Geas Bindings that can be sworn at once");

        addAttributeLibAttributeDescription(MalumAttributes.MALIGNANT_CONVERSION, "A percentile conversion rate in which certain magical attributes are converted into Malignant Aegis");
        addAttributeLibAttributeDescription(MalumAttributes.MALIGNANT_AEGIS_CAPACITY, "A powerful multipurpose damage resistance attribute gained through Malignant Aegis");

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

    public void addRiteEffect(String identifier, String name) {
        add("malum.effect.rite." + identifier, name);
    }

    public void addRiteTag(SpiritRiteEffectTag tag, String name) {
        add(tag.getLangKey(), name);
    }

    public void addGeasDescription(Holder<GeasEffectType> effectType, String description) {
        add(effectType.value().getDescription(), description);
    }

    public void addSpiritFlavour(SpiritHolder<SpiritArcanaType> spiritType, String flavour) {
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
        var id = enchantment.location().getPath();
        addEnchantmentNameAndDescription(enchantment, DataHelper.toTitleCase(id, "_"), desc);
    }
    public void addEnchantmentNameAndDescription(ResourceKey<Enchantment> enchantment, String name, String desc) {
        var id = enchantment.location().getPath();
        var key = "enchantment.malum." + id;
        add(key, name);
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
