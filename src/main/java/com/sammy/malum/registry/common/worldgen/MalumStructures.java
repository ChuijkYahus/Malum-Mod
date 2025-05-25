package com.sammy.malum.registry.common.worldgen;

import com.sammy.malum.*;
import com.sammy.malum.common.worldgen.well.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.resources.*;
import net.minecraft.world.level.levelgen.structure.*;
import net.minecraft.world.level.levelgen.structure.pieces.*;
import net.neoforged.neoforge.registries.*;

public class MalumStructures {
    public static class StructurePieceTypes {
        public static final DeferredRegister<StructurePieceType> STRUCTURE_PIECE_TYPES = DeferredRegister.create(BuiltInRegistries.STRUCTURE_PIECE.key(), MalumMod.MALUM);

        public static final DeferredHolder<StructurePieceType, StructurePieceType> WEEPING_WELL = register("weeping_well", WeepingWellStructurePiece::new);

        private static DeferredHolder<StructurePieceType, StructurePieceType> register(String id, StructurePieceType.ContextlessType type) {
            return STRUCTURE_PIECE_TYPES.register(id, () -> type);
        }
    }

    public static class StructureTypes {
        public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPES = DeferredRegister.create(BuiltInRegistries.STRUCTURE_TYPE.key(), MalumMod.MALUM);

        public static final DeferredHolder<StructureType<?>, StructureType<WeepingWellStructure>> WEEPING_WELL = STRUCTURE_TYPES.register("weeping_well", () -> () -> WeepingWellStructure.CODEC);
    }

    public static class StructureKeys {
        public static final ResourceKey<Structure> WEEPING_WELL_STRUCTURE_KEY = register(Registries.STRUCTURE, "weeping_well");
        public static final ResourceKey<StructureSet> WEEPING_WELL_STRUCTURE_SET_KEY = register(Registries.STRUCTURE_SET, "weeping_well");


        public static <T> ResourceKey<T> register(ResourceKey<Registry<T>> key, String id) {
            return ResourceKey.create(key, MalumMod.malumPath(id));
        }
    }
}