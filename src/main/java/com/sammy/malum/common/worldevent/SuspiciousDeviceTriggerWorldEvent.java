package com.sammy.malum.common.worldevent;

import com.sammy.malum.common.block.curiosities.spirit_crucible.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.visual_effects.networked.MalumNetworkedParticleEffectColorData;
import net.minecraft.server.level.*;
import net.minecraft.sounds.*;
import net.minecraft.util.*;
import net.minecraft.world.level.*;
import net.minecraft.world.phys.*;
import team.lodestar.lodestone.helpers.*;

import static com.sammy.malum.registry.common.block.BlockRegistry.*;

public class SuspiciousDeviceTriggerWorldEvent extends ActiveBlightWorldEvent {
    public SuspiciousDeviceTriggerWorldEvent() {
        super(WorldEventTypeRegistry.SUSPICIOUS_DEVICE_TRIGGER.get());
    }

    @Override
    public void createBlight(ServerLevel level, int intensity) {
        final RandomSource random = level.random;
        level.playSound(null, position, SoundRegistry.SUSPICIOUS_DEVICE_DETONATES_AGAIN.get(), SoundSource.BLOCKS, 0.5f, RandomHelper.randomBetween(random, 0.8f, 1.2f));
        final Vec3 center = position.getCenter().add(SpiritCrucibleCoreBlockEntity.CRUCIBLE_CORE_AUGMENT_OFFSET);
        ParticleEffectTypeRegistry.ETHERIC_NITRATE_IMPACT.createEffect(center)
                .color(SpiritTypeRegistry.ARCANE_SPIRIT, SpiritTypeRegistry.ELDRITCH_SPIRIT)
                .spawn(level);
        if (level.getBlockEntity(position) instanceof SpiritCrucibleCoreBlockEntity crucible) {
            crucible.destroyMultiblock(null, level, position);
            level.destroyBlock(position, false);
            level.updateNeighborsAt(position, SPIRIT_CRUCIBLE.get());
        }
        level.explode(null, null, null,
                center.x+RandomHelper.randomBetween(random, -2f, 2f),
                center.y+RandomHelper.randomBetween(random, -2f, 2f),
                center.z+RandomHelper.randomBetween(random, -2f, 2f),
                1.9F, true, Level.ExplosionInteraction.TNT
        );
        super.createBlight(level, intensity);
    }
}