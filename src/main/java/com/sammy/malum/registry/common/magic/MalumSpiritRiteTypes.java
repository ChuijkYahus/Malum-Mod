package com.sammy.malum.registry.common.magic;

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

    public static ResourceKey<Registry<SpiritRiteType>> SPIRIT_RITE_KEY = ResourceKey.createRegistryKey(MalumMod.malumPath("spirit_rite_types"));
    public static final DeferredRiteTypes SPIRIT_RITE_TYPES = DeferredRiteTypes.create(MalumMod.MALUM);
    public static final Registry<SpiritRiteType> SPIRIT_RITE_REGISTRY = SPIRIT_RITE_TYPES.makeRegistry(builder -> builder.sync(true)
            .defaultKey(MalumMod.malumPath("undirected_rite")));

    public static final RiteHolder<SpiritRiteType> UNDIRECTED_RITE = SPIRIT_RITE_TYPES.register("undirected_rite", () ->
            specialTotemRite(ARCANE_SPIRIT).effect(UndirectedRiteEffect::new).build());
    public static final RiteHolder<SpiritRiteType> UNCHAINED_RITE = SPIRIT_RITE_TYPES.register("unchained_rite", () ->
            specialTotemRite(ARCANE_SPIRIT).effect(UnchainedRiteEffect::new).corrupted().build());

    public static final RiteHolder<SpiritRiteType> RITE_OF_HEALING = SPIRIT_RITE_TYPES.register("rite_of_healing", () ->
            minorTotemRite(SACRED_SPIRIT).effect(HealEffect::new).build());
    public static final RiteHolder<SpiritRiteType> RITE_OF_NOURISHMENT = SPIRIT_RITE_TYPES.register("rite_of_nourishment", () ->
            minorTotemRite(SACRED_SPIRIT).effect(NourishingEffect::new).corrupted().build());
    public static final RiteHolder<SpiritRiteType> RITE_OF_NURTURING = SPIRIT_RITE_TYPES.register("rite_of_nurturing", () ->
            majorTotemRite(SACRED_SPIRIT).effect(NurturingEffect::new).build());
    public static final RiteHolder<SpiritRiteType> RITE_OF_LUST = SPIRIT_RITE_TYPES.register("rite_of_lust", () ->
            majorTotemRite(SACRED_SPIRIT).effect(AnimalLoveEffect::new).corrupted().build());

    public static final RiteHolder<SpiritRiteType> RITE_OF_HARMING = SPIRIT_RITE_TYPES.register("rite_of_harming", () ->
            minorTotemRite(WICKED_SPIRIT).effect(HurtEffect::new).build());
    public static final RiteHolder<SpiritRiteType> RITE_OF_EMPOWERMENT = SPIRIT_RITE_TYPES.register("rite_of_empowerment", () ->
            minorTotemRite(WICKED_SPIRIT).effect(EmpowermentEffect::new).corrupted().build());
    public static final RiteHolder<SpiritRiteType> RITE_OF_CULLING = SPIRIT_RITE_TYPES.register("rite_of_culling", () ->
            majorTotemRite(WICKED_SPIRIT).effect(AnimalCullingEffect::new).corrupted().build());

    public static final RiteHolder<SpiritRiteType> RITE_OF_THE_HOWLING_GALE = SPIRIT_RITE_TYPES.register("rite_of_the_howling_gale", () ->
            minorTotemRite(AERIAL_SPIRIT).effect(HowlingGaleRiteEffect::new).build());
    public static final RiteHolder<SpiritRiteType> RITE_OF_THE_SKY_TETHER = SPIRIT_RITE_TYPES.register("rite_of_the_sky_tether", () ->
            minorTotemRite(AERIAL_SPIRIT).effect(SkyTetherRiteEffect::new).corrupted().build());
    public static final RiteHolder<SpiritRiteType> RITE_OF_GRAVITY = SPIRIT_RITE_TYPES.register("rite_of_gravity", () ->
            majorTotemRite(AERIAL_SPIRIT).effect(BlockGravityRiteEffect::new).build());
    public static final RiteHolder<SpiritRiteType> RITE_OF_LIFTING = SPIRIT_RITE_TYPES.register("rite_of_lifting", () ->
            majorTotemRite(AERIAL_SPIRIT).effect(BlockGravityRiteEffect::new).corrupted().build());

    public static final RiteHolder<SpiritRiteType> RITE_OF_THE_FLOWING_GRASP = SPIRIT_RITE_TYPES.register("rite_of_the_flowing_grasp", () ->
            minorTotemRite(AQUEOUS_SPIRIT).effect(FlowingGraspRiteEffect::new).build());
    public static final RiteHolder<SpiritRiteType> RITE_OF_THE_GOOD_TIDES = SPIRIT_RITE_TYPES.register("rite_of_the_good_tides", () ->
            minorTotemRite(AQUEOUS_SPIRIT).effect(GoodTidesRiteEffect::new).corrupted().build());
    public static final RiteHolder<SpiritRiteType> RITE_OF_SOAKING = SPIRIT_RITE_TYPES.register("rite_of_soaking", () ->
            majorTotemRite(AQUEOUS_SPIRIT).effect(BlockGrowingRiteEffect::new).build());
    public static final RiteHolder<SpiritRiteType> RITE_OF_SAPPING = SPIRIT_RITE_TYPES.register("rite_of_sapping", () ->
            majorTotemRite(AQUEOUS_SPIRIT).effect(DripstoneFluidExtractionRiteEffect::new).corrupted().build());

    public static final RiteHolder<SpiritRiteType> RITE_OF_THE_STONE_WARD = SPIRIT_RITE_TYPES.register("rite_of_the_stone_ward", () ->
            minorTotemRite(EARTHEN_SPIRIT).effect(StoneWardRiteEffect::new).build());
    public static final RiteHolder<SpiritRiteType> RITE_OF_THE_OAKEN_MIGHT = SPIRIT_RITE_TYPES.register("rite_of_the_oaken_might", () ->
            minorTotemRite(EARTHEN_SPIRIT).effect(OakenMightRiteEffect::new).corrupted().build());
    public static final RiteHolder<SpiritRiteType> RITE_OF_CREATION = SPIRIT_RITE_TYPES.register("rite_of_creation", () ->
            majorTotemRite(EARTHEN_SPIRIT).effect(CreateCobblestoneRiteEffect::new).build());
    public static final RiteHolder<SpiritRiteType> RITE_OF_DESTRUCTION = SPIRIT_RITE_TYPES.register("rite_of_destruction", () ->
            majorTotemRite(EARTHEN_SPIRIT).effect(BlockBreakRiteEffect::new).corrupted().build());

    public static final RiteHolder<SpiritRiteType> RITE_OF_THE_BURNING_FERVOR = SPIRIT_RITE_TYPES.register("rite_of_the_burning_fervor", () ->
            minorTotemRite(INFERNAL_SPIRIT).effect(BurningFervorRiteEffect::new).build());
    public static final RiteHolder<SpiritRiteType> RITE_OF_THE_FIERY_EMBRACE = SPIRIT_RITE_TYPES.register("rite_of_the_fiery_embrace", () ->
            minorTotemRite(INFERNAL_SPIRIT).effect(FieryEmbraceRiteEffect::new).corrupted().build());
    public static final RiteHolder<SpiritRiteType> RITE_OF_SMELTING = SPIRIT_RITE_TYPES.register("rite_of_smelting", () ->
            majorTotemRite(INFERNAL_SPIRIT).effect(BlockSmeltingRiteEffect::new).build());
    public static final RiteHolder<SpiritRiteType> RITE_OF_QUICKENING = SPIRIT_RITE_TYPES.register("rite_of_quickening", () ->
            majorTotemRite(INFERNAL_SPIRIT).effect(FurnaceAccelerationRiteEffect::new).corrupted().build());

    public static SpiritRiteType getRite(ServerLevel level, TotemBaseBlockEntity totemBase) {
        List<? extends SpiritRiteType> rites = MalumSpiritRiteTypes.SPIRIT_RITE_TYPES.getEntries().stream().map(DeferredHolder::get).toList();
        for (SpiritRiteType rite : rites) {
            if (rite.matches(level, totemBase)) {
                return rite;
            }
        }
        return null;
    }
}
