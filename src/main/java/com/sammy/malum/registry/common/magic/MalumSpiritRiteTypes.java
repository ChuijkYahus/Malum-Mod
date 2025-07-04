package com.sammy.malum.registry.common.magic;

import com.sammy.malum.*;
import com.sammy.malum.common.spiritrite.arcane.*;
import com.sammy.malum.common.spiritrite.effect.sacred.*;
import com.sammy.malum.common.spiritrite.effect.wicked.*;
import com.sammy.malum.common.spiritrite.eldritch.*;
import com.sammy.malum.core.systems.registry.*;
import com.sammy.malum.core.systems.rite.*;
import com.sammy.malum.core.systems.spirit.type.MalumSpiritType;
import net.minecraft.core.*;
import net.minecraft.resources.*;

import java.util.List;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.*;

public class MalumSpiritRiteTypes {

    public static ResourceKey<Registry<SpiritRiteType>> SPIRIT_RITE_KEY = ResourceKey.createRegistryKey(MalumMod.malumPath("spirit_rite_types"));
    public static final DeferredRiteTypes SPIRIT_RITE_TYPES = DeferredRiteTypes.create(MalumMod.MALUM);
    public static final Registry<SpiritRiteType> SPIRIT_RITE_REGISTRY = SPIRIT_RITE_TYPES.makeRegistry(builder -> builder.sync(true));

    public static final RiteHolder<SpiritRiteType> RITE_OF_HEALING = SPIRIT_RITE_TYPES.register("rite_of_healing", () ->
            SpiritRiteTypeBuilder.createArcane(SACRED_SPIRIT).effect(HealEffect::new).build());
    public static final RiteHolder<SpiritRiteType> RITE_OF_NOURISHMENT = SPIRIT_RITE_TYPES.register("rite_of_nourishment", () ->
            SpiritRiteTypeBuilder.createArcane(SACRED_SPIRIT).effect(NourishingEffect::new).corrupted().build());
    public static final RiteHolder<SpiritRiteType> RITE_OF_NURTURING = SPIRIT_RITE_TYPES.register("rite_of_nurturing", () ->
            SpiritRiteTypeBuilder.createEldritch(SACRED_SPIRIT).effect(NurturingEffect::new).build());
    public static final RiteHolder<SpiritRiteType> RITE_OF_LUST = SPIRIT_RITE_TYPES.register("rite_of_lust", () ->
            SpiritRiteTypeBuilder.createEldritch(SACRED_SPIRIT).effect(LustEffect::new).corrupted().build());

    public static final RiteHolder<SpiritRiteType> RITE_OF_HARMING = SPIRIT_RITE_TYPES.register("rite_of_harming", () ->
            SpiritRiteTypeBuilder.createArcane(WICKED_SPIRIT).effect(HurtEffect::new).build());
    public static final RiteHolder<SpiritRiteType> RITE_OF_EMPOWERMENT = SPIRIT_RITE_TYPES.register("rite_of_empowerment", () ->
            SpiritRiteTypeBuilder.createArcane(WICKED_SPIRIT).effect(EmpowermentEffect::new).corrupted().build());


    public static SpiritRiteType SACRED_RITE = create(new SacredRiteType());
    public static SpiritRiteType ELDRITCH_SACRED_RITE = create(new EldritchSacredRiteType());
    public static SpiritRiteType WICKED_RITE = create(new WickedRiteType());
    public static SpiritRiteType ELDRITCH_WICKED_RITE = create(new EldritchWickedRiteType());

    public static SpiritRiteType EARTHEN_RITE = create(new EarthenRiteType());
    public static SpiritRiteType ELDRITCH_EARTHEN_RITE = create(new EldritchEarthenRiteType());
    public static SpiritRiteType INFERNAL_RITE = create(new InfernalRiteType());
    public static SpiritRiteType ELDRITCH_INFERNAL_RITE = create(new EldritchInfernalRiteType());
    public static SpiritRiteType AERIAL_RITE = create(new AerialRiteType());
    public static SpiritRiteType ELDRITCH_AERIAL_RITE = create(new EldritchAerialRiteType());
    public static SpiritRiteType AQUEOUS_RITE = create(new AqueousRiteType());
    public static SpiritRiteType ELDRITCH_AQUEOUS_RITE = create(new EldritchAqueousRiteType());

    public static SpiritRiteType ARCANE_RITE = create(new ArcaneRiteType());

    public static SpiritRiteType create(SpiritRiteType type) {
        RITES.add(type);
        return type;
    }

    public static SpiritRiteType getRite(String identifier) {
        for (SpiritRiteType rite : RITES) {
            if (rite.identifier.equals(identifier)) {
                return rite;
            }
        }
        return null;
    }

    public static SpiritRiteType getRite(List<MalumSpiritType> spirits) {
        for (SpiritRiteType rite : RITES) {
            if (rite.matches(spirits)) {
                return rite;
            }
        }
        return null;
    }
}
