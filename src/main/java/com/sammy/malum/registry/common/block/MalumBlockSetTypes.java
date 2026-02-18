package com.sammy.malum.registry.common.block;

import com.sammy.malum.registry.common.sound.*;
import net.minecraft.world.level.block.state.properties.*;

public class MalumBlockSetTypes {
    public static final BlockSetType RUNEWOOD = BlockSetType.register(
            new BlockSetType(
                    "runewood",
                    true,
                    true,
                    true,
                    BlockSetType.PressurePlateSensitivity.EVERYTHING,
                    MalumSoundEvents.RUNEWOOD,
                    MalumSoundEvents.RUNEWOOD_DOOR_CLOSE.get(), MalumSoundEvents.RUNEWOOD_DOOR_OPEN.get(),
                    MalumSoundEvents.RUNEWOOD_TRAPDOOR_CLOSE.get(), MalumSoundEvents.RUNEWOOD_TRAPDOOR_OPEN.get(),
                    MalumSoundEvents.RUNEWOOD_PRESSURE_PLATE_CLICK_OFF.get(), MalumSoundEvents.RUNEWOOD_PRESSURE_PLATE_CLICK_ON.get(),
                    MalumSoundEvents.RUNEWOOD_BUTTON_CLICK_OFF.get(), MalumSoundEvents.RUNEWOOD_BUTTON_CLICK_ON.get()));

    public static final BlockSetType SOULWOOD = BlockSetType.register(
            new BlockSetType(
                    "soulwood",
                    true,
                    true,
                    true,
                    BlockSetType.PressurePlateSensitivity.EVERYTHING,
                    MalumSoundEvents.SOULWOOD,
                    MalumSoundEvents.SOULWOOD_DOOR_CLOSE.get(),
                    MalumSoundEvents.SOULWOOD_DOOR_OPEN.get(),
                    MalumSoundEvents.SOULWOOD_TRAPDOOR_CLOSE.get(),
                    MalumSoundEvents.SOULWOOD_TRAPDOOR_OPEN.get(),
                    MalumSoundEvents.SOULWOOD_PRESSURE_PLATE_CLICK_OFF.get(),
                    MalumSoundEvents.SOULWOOD_PRESSURE_PLATE_CLICK_ON.get(),
                    MalumSoundEvents.SOULWOOD_BUTTON_CLICK_OFF.get(),
                    MalumSoundEvents.SOULWOOD_BUTTON_CLICK_ON.get()
            )
    );
}
