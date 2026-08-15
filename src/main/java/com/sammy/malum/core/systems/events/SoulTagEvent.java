package com.sammy.malum.core.systems.events;

import com.sammy.malum.common.entity.soulTag.SoulTagEntity;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import team.lodestar.wayward_attributes.WaywardTags;

import java.util.List;

@EventBusSubscriber
public class SoulTagEvent {

    private static final double RANGE = 8.0D;

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent.Post event) {

        LivingEntity damagedEntity = event.getEntity();

        if (damagedEntity.level().isClientSide()) {
            return;
        }
        if(!event.getSource().is(WaywardTags.DamageTypeTags.IS_MAGIC)) {
            return;
        }

        if (event.getNewDamage() <= 0.0F) {
            return;
        }

        double rangeSquared = RANGE * RANGE;

        List<SoulTagEntity> soulTags =
                damagedEntity.level().getEntitiesOfClass(
                        SoulTagEntity.class,
                        damagedEntity.getBoundingBox().inflate(RANGE),
                        SoulTagEntity::isAlive
                );

        for (SoulTagEntity soulTag : soulTags) {

            if (soulTag.distanceToSqr(damagedEntity) <= rangeSquared) {

                soulTag.createSoulTag(
                        damagedEntity
                );
                break;
            }
        }
    }
}