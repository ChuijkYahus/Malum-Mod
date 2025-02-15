package com.sammy.malum.common.item.augment.core;

import com.sammy.malum.common.block.curiosities.spirit_crucible.*;
import com.sammy.malum.common.worldevent.*;
import com.sammy.malum.core.systems.artifice.ArtificeAttributeType;
import com.sammy.malum.core.systems.artifice.ArtificeModifier;
import com.sammy.malum.core.systems.spirit.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.visual_effects.networked.data.*;
import net.minecraft.core.*;
import net.minecraft.server.level.*;
import net.minecraft.sounds.*;
import net.minecraft.world.level.Level;
import team.lodestar.lodestone.handlers.*;
import team.lodestar.lodestone.helpers.*;

import java.util.List;
import java.util.logging.*;

public class SuspiciousDeviceItem extends CoreAugmentItem {
    public SuspiciousDeviceItem(Properties pProperties) {
        super(pProperties, List.of(SpiritTypeRegistry.ARCANE_SPIRIT, SpiritTypeRegistry.ELDRITCH_SPIRIT), true,
                new ArtificeModifier(ArtificeAttributeType.INSTABILITY, 1f));
    }

    public static void blowUp(ServerLevel level, BlockPos pos) {
        WorldEventHandler.addWorldEvent(level,
                new SuspiciousDeviceTriggerWorldEvent()
                        .setPosition(pos)
                        .setData(List.of(1, 2, 2, 2, 3, 4, 5, 6, 6, 6, 6, 7, 8), 2, 50));
//        level.playSound(null, pos, SoundRegistry.SUSPICIOUS_DEVICE_DETONATES.get(), SoundSource.BLOCKS, 2.5f, RandomHelper.randomBetween(level.random, 0.8f, 1.2f));
        level.playSound(null, pos, SoundRegistry.LEGALIZE_NUCLEAR_BOMBS.get(), SoundSource.BLOCKS, 0.5f, 1f);
        ParticleEffectTypeRegistry.SUSPICIOUS_DEVICE_PRIMER.createPositionedEffect(level,
                new PositionEffectData(pos.getCenter().add(SpiritCrucibleCoreBlockEntity.CRUCIBLE_CORE_AUGMENT_OFFSET)),
                new ColorEffectData(SpiritTypeRegistry.ARCANE_SPIRIT, SpiritTypeRegistry.ELDRITCH_SPIRIT));

    }
}
