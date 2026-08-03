package com.sammy.malum.registry.common;

import com.sammy.malum.MalumMod;
import com.sammy.malum.common.data.map.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.registries.datamaps.DataMapType;
import net.neoforged.neoforge.registries.datamaps.RegisterDataMapTypesEvent;

public class MalumDataMaps {

    public static final DataMapType<Block, TotemWoodGroupMap> TOTEM_WOOD_GROUP = DataMapType.builder(
            MalumMod.malumPath("totem_wood_group"), Registries.BLOCK, TotemWoodGroupMap.CODEC).build();
    public static final DataMapType<Block, TotemPoleConversionMap> TOTEM_POLE_CONVERSION = DataMapType.builder(
            MalumMod.malumPath("totem_pole_conversion"), Registries.BLOCK, TotemPoleConversionMap.CODEC).build();

    public static final DataMapType<Block, SoulstoneOreConversionMap> SOULSTONE_ORE_CONVERSION = DataMapType.builder(
            MalumMod.malumPath("soulstone_ore_conversion"), Registries.BLOCK, SoulstoneOreConversionMap.CODEC).build();

    public static final DataMapType<Block, FluidTappingMap> FLUID_TAPPING = DataMapType.builder(
            MalumMod.malumPath("fluid_tapping"), Registries.BLOCK, FluidTappingMap.CODEC).build();

    public static final DataMapType<Item, ConjunctureCrystallariumFuel> CONJUNCTURE_CRYSTALLARIUM_FUEL = DataMapType.builder(
            MalumMod.malumPath("conjuncture_crystallarium_fuel"), Registries.ITEM, ConjunctureCrystallariumFuel.CODEC)
            .synced(ConjunctureCrystallariumFuel.BURN_TIME_CODEC, false).build();


    public static void registerDataMapTypes(RegisterDataMapTypesEvent event) {
        event.register(TOTEM_WOOD_GROUP);
        event.register(TOTEM_POLE_CONVERSION);

        event.register(SOULSTONE_ORE_CONVERSION);
        event.register(FLUID_TAPPING);

        event.register(CONJUNCTURE_CRYSTALLARIUM_FUEL);
    }
}
