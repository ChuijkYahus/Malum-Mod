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
                    MalumBlockSoundEvents.RUNEWOOD,
                    MalumBlockSoundEvents.RUNEWOOD_DOOR_CLOSE.get(), MalumBlockSoundEvents.RUNEWOOD_DOOR_OPEN.get(),
                    MalumBlockSoundEvents.RUNEWOOD_TRAPDOOR_CLOSE.get(), MalumBlockSoundEvents.RUNEWOOD_TRAPDOOR_OPEN.get(),
                    MalumBlockSoundEvents.RUNEWOOD_PRESSURE_PLATE_CLICK_OFF.get(), MalumBlockSoundEvents.RUNEWOOD_PRESSURE_PLATE_CLICK_ON.get(),
                    MalumBlockSoundEvents.RUNEWOOD_BUTTON_CLICK_OFF.get(), MalumBlockSoundEvents.RUNEWOOD_BUTTON_CLICK_ON.get()));

    public static final BlockSetType SOULWOOD = BlockSetType.register(
            new BlockSetType(
                    "soulwood",
                    true,
                    true,
                    true,
                    BlockSetType.PressurePlateSensitivity.EVERYTHING,
                    MalumBlockSoundEvents.SOULWOOD,
                    MalumBlockSoundEvents.SOULWOOD_DOOR_CLOSE.get(),
                    MalumBlockSoundEvents.SOULWOOD_DOOR_OPEN.get(),
                    MalumBlockSoundEvents.SOULWOOD_TRAPDOOR_CLOSE.get(),
                    MalumBlockSoundEvents.SOULWOOD_TRAPDOOR_OPEN.get(),
                    MalumBlockSoundEvents.SOULWOOD_PRESSURE_PLATE_CLICK_OFF.get(),
                    MalumBlockSoundEvents.SOULWOOD_PRESSURE_PLATE_CLICK_ON.get(),
                    MalumBlockSoundEvents.SOULWOOD_BUTTON_CLICK_OFF.get(),
                    MalumBlockSoundEvents.SOULWOOD_BUTTON_CLICK_ON.get()
            )
    );
}
