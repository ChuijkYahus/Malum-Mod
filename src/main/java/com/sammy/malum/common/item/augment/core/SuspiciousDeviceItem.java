package com.sammy.malum.common.item.augment.core;

import com.sammy.malum.common.block.curiosities.spirit_crucible.*;
import com.sammy.malum.common.block.the_device.*;
import com.sammy.malum.common.worldevent.*;
import com.sammy.malum.core.systems.artifice.ArtificeAttributeType;
import com.sammy.malum.core.systems.artifice.ArtificeModifier;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.magic.*;
import com.sammy.malum.registry.common.sound.*;
import net.minecraft.core.*;
import net.minecraft.server.level.*;
import net.minecraft.sounds.*;
import team.lodestar.lodestone.handlers.*;
import team.lodestar.lodestone.helpers.*;

import java.util.List;

public class SuspiciousDeviceItem extends CoreAugmentItem {
    public SuspiciousDeviceItem(Properties pProperties) {
        super(pProperties, List.of(MalumSpiritTypes.ARCANE_SPIRIT, MalumSpiritTypes.ELDRITCH_SPIRIT), true,
                new ArtificeModifier(ArtificeAttributeType.INSTABILITY, 1f));
    }

    public static void blowUp(ServerLevel level, BlockPos pos) {
        int delay = 20;
        float pitch = RandomHelper.randomBetween(level.random, 0.8f, 1.2f);
        var sound = MalumSoundEvents.SUSPICIOUS_DEVICE_DETONATES.get();
        if (level.getBlockState(pos.below()).getBlock() instanceof TheDevice) {
            delay = 50;
            pitch = 1;
            sound = MalumSoundEvents.SWAG_MESSIAH.get();
        }
        WorldEventHandler.addWorldEvent(level,
                new SuspiciousDeviceTriggerWorldEvent()
                        .setPosition(pos)
                        .setData(List.of(1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8), 1, delay));
        level.playSound(null, pos, sound, SoundSource.BLOCKS, 2.5f, pitch);
        MalumParticleEffectTypes.SUSPICIOUS_DEVICE_PRIMER.createEffect()
                .at(pos.getCenter().add(SpiritCrucibleCoreBlockEntity.CRUCIBLE_CORE_AUGMENT_OFFSET))
                .color(MalumSpiritTypes.ARCANE_SPIRIT, MalumSpiritTypes.ELDRITCH_SPIRIT)
                .spawn(level);
    }
}