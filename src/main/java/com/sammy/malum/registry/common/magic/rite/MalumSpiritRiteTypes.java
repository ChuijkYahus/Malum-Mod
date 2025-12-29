package com.sammy.malum.registry.common.magic.rite;

import com.sammy.malum.*;
import com.sammy.malum.common.block.curiosities.totem.*;
import com.sammy.malum.core.systems.registry.rite.*;
import com.sammy.malum.core.systems.rite.*;
import net.minecraft.core.*;
import net.minecraft.resources.*;
import net.minecraft.server.level.*;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.List;

import static com.sammy.malum.core.systems.rite.SpiritRiteTypeBuilder.*;
import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.*;

public class MalumSpiritRiteTypes {

    public static ResourceKey<Registry<SpiritRiteType>> RITE_KEY = ResourceKey.createRegistryKey(MalumMod.malumPath("spirit_rite_types"));
    public static final DeferredRiteTypes RITE_TYPES = DeferredRiteTypes.create(MalumMod.MALUM);
    public static final Registry<SpiritRiteType> RITE_REGISTRY = RITE_TYPES.makeRegistry(builder -> builder
            .defaultKey(MalumMod.malumPath("undirected_rite"))
            .sync(true));

    //TODO: Spirit Rites will be data driven, the only information a rite really is is just name, spirit pattern and effect. Might as well

    public static final RiteHolder<SpiritRiteType> UNDIRECTED_RITE = RITE_TYPES.register("undirected_rite", () ->
            specialTotemRite(ARCANE_SPIRIT).build(MalumSpiritRiteEffectTypes.UNDIRECTED_RITE_EFFECT));
    public static final RiteHolder<SpiritRiteType> UNCHAINED_RITE = RITE_TYPES.register("unchained_rite", () ->
            specialTotemRite(ARCANE_SPIRIT).setCorrupted().build(MalumSpiritRiteEffectTypes.UNCHAINED_RITE_EFFECT));

    public static final RiteHolder<SpiritRiteType> RITE_OF_HEALING = RITE_TYPES.register("rite_of_healing", () ->
            minorTotemRite(SACRED_SPIRIT).build(MalumSpiritRiteEffectTypes.HEALING_EFFECT));
    public static final RiteHolder<SpiritRiteType> RITE_OF_NOURISHMENT = RITE_TYPES.register("rite_of_nourishment", () ->
            minorTotemRite(SACRED_SPIRIT).setCorrupted().build(MalumSpiritRiteEffectTypes.NOURISHMENT_EFFECT));
    public static final RiteHolder<SpiritRiteType> RITE_OF_NURTURING = RITE_TYPES.register("rite_of_nurturing", () ->
            majorTotemRite(SACRED_SPIRIT).build(MalumSpiritRiteEffectTypes.NURTURING_EFFECT));
    public static final RiteHolder<SpiritRiteType> RITE_OF_LUST = RITE_TYPES.register("rite_of_lust", () ->
            majorTotemRite(SACRED_SPIRIT).setCorrupted().build(MalumSpiritRiteEffectTypes.LUST_EFFECT));

    public static final RiteHolder<SpiritRiteType> RITE_OF_HARMING = RITE_TYPES.register("rite_of_harming", () ->
            minorTotemRite(WICKED_SPIRIT).build(MalumSpiritRiteEffectTypes.HARMING_EFFECT));
    public static final RiteHolder<SpiritRiteType> RITE_OF_EMPOWERMENT = RITE_TYPES.register("rite_of_empowerment", () ->
            minorTotemRite(WICKED_SPIRIT).setCorrupted().build(MalumSpiritRiteEffectTypes.EMPOWERMENT_EFFECT));
    public static final RiteHolder<SpiritRiteType> RITE_OF_CULLING = RITE_TYPES.register("rite_of_culling", () ->
            majorTotemRite(WICKED_SPIRIT).build(MalumSpiritRiteEffectTypes.CULLING_EFFECT));
    public static final RiteHolder<SpiritRiteType> RITE_OF_RAISING = RITE_TYPES.register("rite_of_rending", () ->
            majorTotemRite(WICKED_SPIRIT).setCorrupted().build(MalumSpiritRiteEffectTypes.RAISING_EFFECT));

    public static final RiteHolder<SpiritRiteType> RITE_OF_THE_HOWLING_GALE = RITE_TYPES.register("rite_of_the_howling_gale", () ->
            minorTotemRite(AERIAL_SPIRIT).build(MalumSpiritRiteEffectTypes.APPLY_HOWLING_GALE_EFFECT));
    public static final RiteHolder<SpiritRiteType> RITE_OF_THE_SKY_TETHER = RITE_TYPES.register("rite_of_the_sky_tether", () ->
            minorTotemRite(AERIAL_SPIRIT).setCorrupted().build(MalumSpiritRiteEffectTypes.APPLY_SKY_TETHER_EFFECT));
    public static final RiteHolder<SpiritRiteType> RITE_OF_GRAVITY = RITE_TYPES.register("rite_of_gravity", () ->
            majorTotemRite(AERIAL_SPIRIT).build(MalumSpiritRiteEffectTypes.BLOCK_GRAVITY_EFFECT));
    public static final RiteHolder<SpiritRiteType> RITE_OF_ASCENSION = RITE_TYPES.register("rite_of_ascension", () ->
            majorTotemRite(AERIAL_SPIRIT).setCorrupted().build(MalumSpiritRiteEffectTypes.BLOCK_ASCENSION_EFFECT));

    public static final RiteHolder<SpiritRiteType> RITE_OF_THE_FLOWING_GRASP = RITE_TYPES.register("rite_of_the_flowing_grasp", () ->
            minorTotemRite(AQUEOUS_SPIRIT).build(MalumSpiritRiteEffectTypes.APPLY_FLOWING_GRASP_EFFECT));
    public static final RiteHolder<SpiritRiteType> RITE_OF_THE_GOOD_TIDES = RITE_TYPES.register("rite_of_the_good_tides", () ->
            minorTotemRite(AQUEOUS_SPIRIT).setCorrupted().build(MalumSpiritRiteEffectTypes.THE_GOOD_TIDES_EFFECT));
    public static final RiteHolder<SpiritRiteType> RITE_OF_SOAKING = RITE_TYPES.register("rite_of_soaking", () ->
            majorTotemRite(AQUEOUS_SPIRIT).build(MalumSpiritRiteEffectTypes.SOAKING_EFFECT));
    public static final RiteHolder<SpiritRiteType> RITE_OF_SAPPING = RITE_TYPES.register("rite_of_sapping", () ->
            majorTotemRite(AQUEOUS_SPIRIT).setCorrupted().build(MalumSpiritRiteEffectTypes.SAPPING_EFFECT));

    public static final RiteHolder<SpiritRiteType> RITE_OF_THE_STONE_WARD = RITE_TYPES.register("rite_of_the_stone_ward", () ->
            minorTotemRite(EARTHEN_SPIRIT).build(MalumSpiritRiteEffectTypes.APPLY_STONE_WARD_EFFECT));
    public static final RiteHolder<SpiritRiteType> RITE_OF_THE_OAKEN_MIGHT = RITE_TYPES.register("rite_of_the_oaken_might", () ->
            minorTotemRite(EARTHEN_SPIRIT).setCorrupted().build(MalumSpiritRiteEffectTypes.APPLY_OAKEN_MIGHT_EFFECT));
    public static final RiteHolder<SpiritRiteType> RITE_OF_CREATION = RITE_TYPES.register("rite_of_creation", () ->
            majorTotemRite(EARTHEN_SPIRIT).build(MalumSpiritRiteEffectTypes.CREATION_EFFECT));
    public static final RiteHolder<SpiritRiteType> RITE_OF_DESTRUCTION = RITE_TYPES.register("rite_of_destruction", () ->
            majorTotemRite(EARTHEN_SPIRIT).setCorrupted().build(MalumSpiritRiteEffectTypes.DESTRUCTION_EFFECT));

    public static final RiteHolder<SpiritRiteType> RITE_OF_THE_BURNING_FERVOR = RITE_TYPES.register("rite_of_the_burning_fervor", () ->
            minorTotemRite(INFERNAL_SPIRIT).build(MalumSpiritRiteEffectTypes.APPLY_BURNING_FERVOR_EFFECT));
    public static final RiteHolder<SpiritRiteType> RITE_OF_THE_FIERY_EMBRACE = RITE_TYPES.register("rite_of_the_fiery_embrace", () ->
            minorTotemRite(INFERNAL_SPIRIT).setCorrupted().build(MalumSpiritRiteEffectTypes.APPLY_FIERY_EMBRACE_EFFECT));
    public static final RiteHolder<SpiritRiteType> RITE_OF_SMELTING = RITE_TYPES.register("rite_of_smelting", () ->
            majorTotemRite(INFERNAL_SPIRIT).build(MalumSpiritRiteEffectTypes.SMELTING_EFFECT));
    public static final RiteHolder<SpiritRiteType> RITE_OF_QUICKENING = RITE_TYPES.register("rite_of_quickening", () ->
            majorTotemRite(INFERNAL_SPIRIT).setCorrupted().build(MalumSpiritRiteEffectTypes.QUICKENING_EFFECT));


    public static SpiritRiteType getRite(ServerLevel level, TotemBaseBlockEntity totemBase) {
        boolean corrupted = totemBase.corrupted;
        var totemPoles = totemBase.getTotemPoles(level);
        for (TotemPoleBlockEntity totemPole : totemPoles) {
            if (totemPole.isSoulwood() != corrupted) {
                return null;
            }
        }
        var rites = MalumSpiritRiteTypes.RITE_TYPES.getEntries().stream().map(DeferredHolder::get).toList();
        for (SpiritRiteType rite : rites) {
            if (rite.matches(level, totemBase)) {
                return rite;
            }
        }
        return null;
    }
}
