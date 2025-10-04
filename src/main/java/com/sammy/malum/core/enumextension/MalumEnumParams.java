package com.sammy.malum.core.enumextension;

import com.sammy.malum.*;
import com.sammy.malum.registry.common.item.*;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Items;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;

public class MalumEnumParams {
    public static final EnumProxy<Boat.Type> RUNEWOOD_BOAT_TYPE = new EnumProxy<>(
            Boat.Type.class, MalumItems.RUNEWOOD_PLANKS, MalumMod.MALUM + ":runewood", MalumItems.RUNEWOOD_BOAT, MalumItems.RUNEWOOD_CHEST_BOAT, Items.STICK, false
    );

    public static final EnumProxy<Boat.Type> SOULWOOD_BOAT_TYPE = new EnumProxy<>(
            Boat.Type.class, MalumItems.SOULWOOD_PLANKS, MalumMod.MALUM + ":soulwood", MalumItems.SOULWOOD_BOAT, MalumItems.SOULWOOD_CHEST_BOAT, Items.STICK, false
    );
}