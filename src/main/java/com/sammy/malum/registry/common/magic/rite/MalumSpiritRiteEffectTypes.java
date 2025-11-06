package com.sammy.malum.registry.common.magic.rite;

import com.sammy.malum.*;
import com.sammy.malum.common.spiritrite.effect.aerial.*;
import com.sammy.malum.common.spiritrite.effect.aqueous.*;
import com.sammy.malum.common.spiritrite.effect.arcane.*;
import com.sammy.malum.common.spiritrite.effect.earthen.*;
import com.sammy.malum.common.spiritrite.effect.infernal.*;
import com.sammy.malum.common.spiritrite.effect.sacred.*;
import com.sammy.malum.common.spiritrite.effect.wicked.*;
import com.sammy.malum.core.systems.registry.rite.*;
import com.sammy.malum.core.systems.rite.effect.*;
import net.minecraft.core.*;
import net.minecraft.resources.*;

public class MalumSpiritRiteEffectTypes {

    public static ResourceKey<Registry<SpiritRiteEffect>> EFFECT_KEY = ResourceKey.createRegistryKey(MalumMod.malumPath("rite_effect_types"));
    public static final DeferredRiteEntityEffectTypes EFFECT_TYPES = DeferredRiteEntityEffectTypes.create(MalumMod.MALUM);
    public static final Registry<SpiritRiteEffect> EFFECT_TYPE_REGISTRY = EFFECT_TYPES.makeRegistry(builder -> builder
            .defaultKey(MalumMod.malumPath("heal"))
            .sync(true));

    public static final RiteEffectHolder<UndirectedRiteEffect> UNDIRECTED_RITE_EFFECT = EFFECT_TYPES.register("undirected_rite_effect", UndirectedRiteEffect::new);
    public static final RiteEffectHolder<UnchainedRiteEffect> UNCHAINED_RITE_EFFECT = EFFECT_TYPES.register("unchained_rite_effect", UnchainedRiteEffect::new);

    public static final RiteEffectHolder<HealEffect> HEALING_EFFECT = EFFECT_TYPES.register("healing_effect", HealEffect::new);
    public static final RiteEffectHolder<NourishingEffect> NOURISHMENT_EFFECT = EFFECT_TYPES.register("nourishment_effect", NourishingEffect::new);
    public static final RiteEffectHolder<NurturingEffect> NURTURING_EFFECT = EFFECT_TYPES.register("nurturing_effect", NurturingEffect::new);
    public static final RiteEffectHolder<AnimalLoveEffect> LUST_EFFECT = EFFECT_TYPES.register("lust_effect", AnimalLoveEffect::new);

    public static final RiteEffectHolder<HurtEffect> HARMING_EFFECT = EFFECT_TYPES.register("harming_effect", HurtEffect::new);
    public static final RiteEffectHolder<EmpowermentEffect> EMPOWERMENT_EFFECT = EFFECT_TYPES.register("empowerment_effect", EmpowermentEffect::new);
    public static final RiteEffectHolder<AnimalCullingEffect> CULLING_EFFECT = EFFECT_TYPES.register("culling_effect", AnimalCullingEffect::new);
    public static final RiteEffectHolder<MonsterRaisingEffect> RAISING_EFFECT = EFFECT_TYPES.register("monster_raising_effect", MonsterRaisingEffect::new);

    public static final RiteEffectHolder<HowlingGaleRiteEffect> APPLY_HOWLING_GALE_EFFECT = EFFECT_TYPES.register("apply_howling_gale_effect", HowlingGaleRiteEffect::new);
    public static final RiteEffectHolder<SkyTetherRiteEffect> APPLY_SKY_TETHER_EFFECT = EFFECT_TYPES.register("apply_sky_tether_effect", SkyTetherRiteEffect::new);
    public static final RiteEffectHolder<BlockGravityRiteEffect> BLOCK_GRAVITY_EFFECT = EFFECT_TYPES.register("block_gravity_effect", BlockGravityRiteEffect::new);
    public static final RiteEffectHolder<BlockAscensionRiteEffect> BLOCK_ASCENSION_EFFECT = EFFECT_TYPES.register("block_ascension_effect", BlockAscensionRiteEffect::new);

    public static final RiteEffectHolder<FlowingGraspRiteEffect> APPLY_FLOWING_GRASP_EFFECT = EFFECT_TYPES.register("apply_flowing_grasp_effect", FlowingGraspRiteEffect::new);
    public static final RiteEffectHolder<GoodTidesRiteEffect> THE_GOOD_TIDES_EFFECT = EFFECT_TYPES.register("apply_good_tides_effect", GoodTidesRiteEffect::new);
    public static final RiteEffectHolder<BlockGrowingRiteEffect> SOAKING_EFFECT = EFFECT_TYPES.register("soaking_effect", BlockGrowingRiteEffect::new);
    public static final RiteEffectHolder<DripstoneFluidExtractionRiteEffect> SAPPING_EFFECT = EFFECT_TYPES.register("sapping_effect", DripstoneFluidExtractionRiteEffect::new);

    public static final RiteEffectHolder<StoneWardRiteEffect> APPLY_STONE_WARD_EFFECT = EFFECT_TYPES.register("apply_stone_ward_effect", StoneWardRiteEffect::new);
    public static final RiteEffectHolder<OakenMightRiteEffect> APPLY_OAKEN_MIGHT_EFFECT = EFFECT_TYPES.register("apply_oaken_might_effect", OakenMightRiteEffect::new);
    public static final RiteEffectHolder<CreateCobblestoneRiteEffect> CREATION_EFFECT = EFFECT_TYPES.register("creation_effect", CreateCobblestoneRiteEffect::new);
    public static final RiteEffectHolder<BlockBreakRiteEffect> DESTRUCTION_EFFECT = EFFECT_TYPES.register("destruction_effect", BlockBreakRiteEffect::new);

    public static final RiteEffectHolder<BurningFervorRiteEffect> APPLY_BURNING_FERVOR_EFFECT = EFFECT_TYPES.register("apply_burning_fervor_effect", BurningFervorRiteEffect::new);
    public static final RiteEffectHolder<FieryEmbraceRiteEffect> APPLY_FIERY_EMBRACE_EFFECT = EFFECT_TYPES.register("apply_fiery_embrace_effect", FieryEmbraceRiteEffect::new);
    public static final RiteEffectHolder<BlockSmeltingRiteEffect> SMELTING_EFFECT = EFFECT_TYPES.register("smelting_effect", BlockSmeltingRiteEffect::new);
    public static final RiteEffectHolder<FurnaceAccelerationRiteEffect> QUICKENING_EFFECT = EFFECT_TYPES.register("quickening_effect", FurnaceAccelerationRiteEffect::new);

    public static final RiteEffectHolder<AerialEmpowermentRiteEffect> EMPOWER_AERIAL_EFFECTS = EFFECT_TYPES.register("empower_aerial_effects_effect", AerialEmpowermentRiteEffect::new);
    public static final RiteEffectHolder<AqueousEmpowermentRiteEffect> EMPOWER_AQUEOUS_EFFECTS = EFFECT_TYPES.register("empower_aqueous_effects_effect", AqueousEmpowermentRiteEffect::new);
    public static final RiteEffectHolder<EarthenEmpowermentRiteEffect> EMPOWER_EARTHEN_EFFECTS = EFFECT_TYPES.register("empower_earthen_effects_effect", EarthenEmpowermentRiteEffect::new);
    public static final RiteEffectHolder<InfernalEmpowermentRiteEffect> EMPOWER_INFERNAL_EFFECTS = EFFECT_TYPES.register("empower_infernal_effects_effect", InfernalEmpowermentRiteEffect::new);

}
