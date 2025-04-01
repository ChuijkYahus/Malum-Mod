package com.sammy.malum.registry.common.worldgen;

import com.sammy.malum.*;
import com.sammy.malum.common.worldgen.well.*;
import net.minecraft.core.registries.*;
import net.minecraft.world.level.levelgen.structure.*;
import net.neoforged.neoforge.registries.*;

public class MalumStructureTypes {

    public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPES = DeferredRegister.create(BuiltInRegistries.STRUCTURE_TYPE.key(), MalumMod.MALUM);

    public static final DeferredHolder<StructureType<?>, StructureType<WeepingWellStructure>> WEEPING_WELL = STRUCTURE_TYPES.register("weeping_well", ()-> ()-> WeepingWellStructure.CODEC);

}