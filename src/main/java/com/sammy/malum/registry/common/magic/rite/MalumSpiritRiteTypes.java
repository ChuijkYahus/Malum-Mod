package com.sammy.malum.registry.common.magic.rite;

import com.sammy.malum.*;
import com.sammy.malum.common.block.curiosities.totem.TotemBaseBlockEntity;
import com.sammy.malum.common.spiritrite.effect.aerial.BlockGravityRiteEffect;
import com.sammy.malum.common.spiritrite.effect.aerial.HowlingGaleRiteEffect;
import com.sammy.malum.common.spiritrite.effect.aerial.SkyTetherRiteEffect;
import com.sammy.malum.common.spiritrite.effect.aqueous.BlockGrowingRiteEffect;
import com.sammy.malum.common.spiritrite.effect.aqueous.DripstoneFluidExtractionRiteEffect;
import com.sammy.malum.common.spiritrite.effect.aqueous.FlowingGraspRiteEffect;
import com.sammy.malum.common.spiritrite.effect.aqueous.GoodTidesRiteEffect;
import com.sammy.malum.common.spiritrite.effect.arcane.UnchainedRiteEffect;
import com.sammy.malum.common.spiritrite.effect.arcane.UndirectedRiteEffect;
import com.sammy.malum.common.spiritrite.effect.earthen.BlockBreakRiteEffect;
import com.sammy.malum.common.spiritrite.effect.earthen.CreateCobblestoneRiteEffect;
import com.sammy.malum.common.spiritrite.effect.earthen.OakenMightRiteEffect;
import com.sammy.malum.common.spiritrite.effect.earthen.StoneWardRiteEffect;
import com.sammy.malum.common.spiritrite.effect.infernal.BlockSmeltingRiteEffect;
import com.sammy.malum.common.spiritrite.effect.infernal.BurningFervorRiteEffect;
import com.sammy.malum.common.spiritrite.effect.infernal.FieryEmbraceRiteEffect;
import com.sammy.malum.common.spiritrite.effect.infernal.FurnaceAccelerationRiteEffect;
import com.sammy.malum.common.spiritrite.effect.sacred.*;
import com.sammy.malum.common.spiritrite.effect.wicked.*;
import com.sammy.malum.core.systems.registry.*;
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

    public static final RiteHolder<SpiritRiteType> UNDIRECTED_RITE = RITE_TYPES.register("undirected_rite", () ->
            specialTotemRite(ARCANE_SPIRIT).build(UndirectedRiteEffect::new));
    public static final RiteHolder<SpiritRiteType> UNCHAINED_RITE = RITE_TYPES.register("unchained_rite", () ->
            specialTotemRite(ARCANE_SPIRIT).setCorrupted().build(UnchainedRiteEffect::new));

    public static final RiteHolder<SpiritRiteType> RITE_OF_HEALING = RITE_TYPES.register("rite_of_healing", () ->
            minorTotemRite(SACRED_SPIRIT).build(HealEffect::new));
    public static final RiteHolder<SpiritRiteType> RITE_OF_NOURISHMENT = RITE_TYPES.register("rite_of_nourishment", () ->
            minorTotemRite(SACRED_SPIRIT).setCorrupted().build(NourishingEffect::new));
    public static final RiteHolder<SpiritRiteType> RITE_OF_NURTURING = RITE_TYPES.register("rite_of_nurturing", () ->
            majorTotemRite(SACRED_SPIRIT).build(NurturingEffect::new));
    public static final RiteHolder<SpiritRiteType> RITE_OF_LUST = RITE_TYPES.register("rite_of_lust", () ->
            majorTotemRite(SACRED_SPIRIT).setCorrupted().build(AnimalLoveEffect::new));

    public static final RiteHolder<SpiritRiteType> RITE_OF_HARMING = RITE_TYPES.register("rite_of_harming", () ->
            minorTotemRite(WICKED_SPIRIT).build(HurtEffect::new));
    public static final RiteHolder<SpiritRiteType> RITE_OF_EMPOWERMENT = RITE_TYPES.register("rite_of_empowerment", () ->
            minorTotemRite(WICKED_SPIRIT).setCorrupted().build(EmpowermentEffect::new));
    public static final RiteHolder<SpiritRiteType> RITE_OF_CULLING = RITE_TYPES.register("rite_of_culling", () ->
            majorTotemRite(WICKED_SPIRIT).setCorrupted().build(AnimalCullingEffect::new));

    public static final RiteHolder<SpiritRiteType> RITE_OF_THE_HOWLING_GALE = RITE_TYPES.register("rite_of_the_howling_gale", () ->
            minorTotemRite(AERIAL_SPIRIT).build(HowlingGaleRiteEffect::new));
    public static final RiteHolder<SpiritRiteType> RITE_OF_THE_SKY_TETHER = RITE_TYPES.register("rite_of_the_sky_tether", () ->
            minorTotemRite(AERIAL_SPIRIT).setCorrupted().build(SkyTetherRiteEffect::new));
    public static final RiteHolder<SpiritRiteType> RITE_OF_GRAVITY = RITE_TYPES.register("rite_of_gravity", () ->
            majorTotemRite(AERIAL_SPIRIT).build(BlockGravityRiteEffect::new));
    public static final RiteHolder<SpiritRiteType> RITE_OF_LIFTING = RITE_TYPES.register("rite_of_lifting", () ->
            majorTotemRite(AERIAL_SPIRIT).setCorrupted().build(BlockGravityRiteEffect::new));

    public static final RiteHolder<SpiritRiteType> RITE_OF_THE_FLOWING_GRASP = RITE_TYPES.register("rite_of_the_flowing_grasp", () ->
            minorTotemRite(AQUEOUS_SPIRIT).build(FlowingGraspRiteEffect::new));
    public static final RiteHolder<SpiritRiteType> RITE_OF_THE_GOOD_TIDES = RITE_TYPES.register("rite_of_the_good_tides", () ->
            minorTotemRite(AQUEOUS_SPIRIT).setCorrupted().build(GoodTidesRiteEffect::new));
    public static final RiteHolder<SpiritRiteType> RITE_OF_SOAKING = RITE_TYPES.register("rite_of_soaking", () ->
            majorTotemRite(AQUEOUS_SPIRIT).build(BlockGrowingRiteEffect::new));
    public static final RiteHolder<SpiritRiteType> RITE_OF_SAPPING = RITE_TYPES.register("rite_of_sapping", () ->
            majorTotemRite(AQUEOUS_SPIRIT).setCorrupted().build(DripstoneFluidExtractionRiteEffect::new));

    public static final RiteHolder<SpiritRiteType> RITE_OF_THE_STONE_WARD = RITE_TYPES.register("rite_of_the_stone_ward", () ->
            minorTotemRite(EARTHEN_SPIRIT).build(StoneWardRiteEffect::new));
    public static final RiteHolder<SpiritRiteType> RITE_OF_THE_OAKEN_MIGHT = RITE_TYPES.register("rite_of_the_oaken_might", () ->
            minorTotemRite(EARTHEN_SPIRIT).setCorrupted().build(OakenMightRiteEffect::new));
    public static final RiteHolder<SpiritRiteType> RITE_OF_CREATION = RITE_TYPES.register("rite_of_creation", () ->
            majorTotemRite(EARTHEN_SPIRIT).build(CreateCobblestoneRiteEffect::new));
    public static final RiteHolder<SpiritRiteType> RITE_OF_DESTRUCTION = RITE_TYPES.register("rite_of_destruction", () ->
            majorTotemRite(EARTHEN_SPIRIT).setCorrupted().build(BlockBreakRiteEffect::new));

    public static final RiteHolder<SpiritRiteType> RITE_OF_THE_BURNING_FERVOR = RITE_TYPES.register("rite_of_the_burning_fervor", () ->
            minorTotemRite(INFERNAL_SPIRIT).build(BurningFervorRiteEffect::new));
    public static final RiteHolder<SpiritRiteType> RITE_OF_THE_FIERY_EMBRACE = RITE_TYPES.register("rite_of_the_fiery_embrace", () ->
            minorTotemRite(INFERNAL_SPIRIT).setCorrupted().build(FieryEmbraceRiteEffect::new));
    public static final RiteHolder<SpiritRiteType> RITE_OF_SMELTING = RITE_TYPES.register("rite_of_smelting", () ->
            majorTotemRite(INFERNAL_SPIRIT).build(BlockSmeltingRiteEffect::new));
    public static final RiteHolder<SpiritRiteType> RITE_OF_QUICKENING = RITE_TYPES.register("rite_of_quickening", () ->
            majorTotemRite(INFERNAL_SPIRIT).setCorrupted().build(FurnaceAccelerationRiteEffect::new));

    public static SpiritRiteType getRite(ServerLevel level, TotemBaseBlockEntity totemBase) {
        List<? extends SpiritRiteType> rites = MalumSpiritRiteTypes.RITE_TYPES.getEntries().stream().map(DeferredHolder::get).toList();
        for (SpiritRiteType rite : rites) {
            if (rite.matches(level, totemBase)) {
                return rite;
            }
        }
        return null;
    }
}
