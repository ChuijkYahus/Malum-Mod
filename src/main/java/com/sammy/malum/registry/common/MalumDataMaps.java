package com.sammy.malum.registry.common;

import com.sammy.malum.MalumMod;
import com.sammy.malum.common.data.map.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.registries.datamaps.DataMapType;
import net.neoforged.neoforge.registries.datamaps.RegisterDataMapTypesEvent;

import java.util.List;

public class MalumDataMaps {


    public static final DataMapType<Block, SoulstoneOreConversionMap> SOULSTONE_ORE_CONVERSION = DataMapType.builder(
            MalumMod.malumPath("soulstone_ore_conversion"), Registries.BLOCK, SoulstoneOreConversionMap.CODEC).build();
    public static final DataMapType<Block, TotemPoleConversionMap> TOTEM_POLE_CONVERSION = DataMapType.builder(
            MalumMod.malumPath("totem_pole_conversion"), Registries.BLOCK, TotemPoleConversionMap.CODEC).build();

    public static final DataMapType<Block, BlockCarvingMap> BLOCK_CARVING = DataMapType.builder(
            MalumMod.malumPath("block_carving"), Registries.BLOCK, BlockCarvingMap.CODEC).build();
    public static final DataMapType<Block, FluidTappingMap> FLUID_TAPPING = DataMapType.builder(
            MalumMod.malumPath("fluid_tapping"), Registries.BLOCK, FluidTappingMap.CODEC).build();


    public static void registerDataMapTypes(RegisterDataMapTypesEvent event) {
        event.register(SOULSTONE_ORE_CONVERSION);
        event.register(TOTEM_POLE_CONVERSION);

        event.register(BLOCK_CARVING);
        event.register(FLUID_TAPPING);
    }
}
