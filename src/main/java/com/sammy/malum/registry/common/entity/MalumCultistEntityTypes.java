package com.sammy.malum.registry.common.entity;

import com.sammy.malum.common.entity.mob.cultist.altar.*;
import com.sammy.malum.common.entity.mob.cultist.altar.projectile.*;
import com.sammy.malum.common.entity.mob.cultist.believer.*;
import com.sammy.malum.common.entity.mob.cultist.cardinal.*;
import com.sammy.malum.common.entity.mob.cultist.cardinal.projectile.*;
import com.sammy.malum.common.entity.mob.cultist.cherub.*;
import com.sammy.malum.common.entity.mob.cultist.evangelist.*;
import net.minecraft.world.entity.*;
import net.neoforged.neoforge.event.entity.*;
import net.neoforged.neoforge.registries.*;

import static com.sammy.malum.registry.common.entity.MalumEntityTypes.register;

public class MalumCultistEntityTypes {

    public static void init() {

    }

    public static final DeferredHolder<EntityType<?>, EntityType<AltarCultist>> ALTAR = register(
            "altar", AltarCultist::new, MobCategory.MONSTER, b -> b
                    .sized(0.9F, 1.25F)
                    .eyeHeight(1F)
                    .passengerAttachments(1.1F)
                    .ridingOffset(-0.2F)
                    .clientTrackingRange(8)
    );

    public static final DeferredHolder<EntityType<?>, EntityType<BelieverCultist>> BELIEVER = register(
            "believer", BelieverCultist::new, MobCategory.MONSTER, b -> b
                    .sized(0.6F, 1.9F)
                    .eyeHeight(1.75F)
                    .passengerAttachments(1.8f)
                    .clientTrackingRange(8)
    );

    public static final DeferredHolder<EntityType<?>, EntityType<CherubCultist>> CHERUB = register(
            "cherub", CherubCultist::new, MobCategory.MONSTER, b -> b
                    .sized(0.8F, 0.8F)
                    .eyeHeight(0.5f)
                    .clientTrackingRange(8)
    );

    public static final DeferredHolder<EntityType<?>, EntityType<CardinalCultist>> CARDINAL = register(
            "cardinal", CardinalCultist::new, MobCategory.MONSTER, b -> b
                    .sized(1.5F, 2.6F)
                    .eyeHeight(2.7F)
                    .passengerAttachments(2.8f)
                    .clientTrackingRange(8)
    );

    public static final DeferredHolder<EntityType<?>, EntityType<EvangelistCultist>> EVANGELIST = register(
            "evangelist", EvangelistCultist::new, MobCategory.MONSTER, b -> b
                    .sized(0.9F, 2.9F)
                    .eyeHeight(2.75F)
                    .passengerAttachments(2.8f)
                    .clientTrackingRange(8)
    );

    public static final DeferredHolder<EntityType<?>, EntityType<CursedBoltProjectile>> CURSED_BOLT =
            register("cursed_bolt", CursedBoltProjectile::new, 1F, 1F, 10);

    public static final DeferredHolder<EntityType<?>, EntityType<EntropyChargeProjectile>> ENTROPY_CHARGE =
            register("entropy_charge", EntropyChargeProjectile::new, 0.5F, 0.5F, 10);

    public static final DeferredHolder<EntityType<?>, EntityType<CultistBlessingProjectile>> CULTIST_BLESSING =
            register("cultist_blessing", CultistBlessingProjectile::new, 1F, 1F, 10);

    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(ALTAR.get(), AltarCultist.createAttributes().build());
        event.put(CHERUB.get(), CherubCultist.createAttributes().build());
        event.put(BELIEVER.get(), BelieverCultist.createAttributes().build());
        event.put(CARDINAL.get(), CardinalCultist.createAttributes().build());
        event.put(EVANGELIST.get(), EvangelistCultist.createAttributes().build());
    }
}
