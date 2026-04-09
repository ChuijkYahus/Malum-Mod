package com.sammy.malum.core.enumextension;

import com.sammy.malum.*;
import com.sammy.malum.registry.common.content.item.MalumItemProperties;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Items;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;

public class MalumEnumParams {

    public static final EnumProxy<Boat.Type> RUNEWOOD_BOAT_TYPE = new EnumProxy<>(
            Boat.Type.class, MalumItemProperties.RUNEWOOD_PLANKS, MalumMod.MALUM + ":runewood", MalumItemProperties.RUNEWOOD_BOAT, MalumItemProperties.RUNEWOOD_CHEST_BOAT, Items.STICK, false
    );

    public static final EnumProxy<Boat.Type> SOULWOOD_BOAT_TYPE = new EnumProxy<>(
            Boat.Type.class, MalumItemProperties.SOULWOOD_PLANKS, MalumMod.MALUM + ":soulwood", MalumItemProperties.SOULWOOD_BOAT, MalumItemProperties.SOULWOOD_CHEST_BOAT, Items.STICK, false
    );
}