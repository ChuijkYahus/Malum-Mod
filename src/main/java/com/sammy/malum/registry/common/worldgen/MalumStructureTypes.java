package com.sammy.malum.registry.common.worldgen;

import com.sammy.malum.*;
import com.sammy.malum.common.worldgen.geode.MalumGeodePiece;
import com.sammy.malum.common.worldgen.geode.MalumGeodeStructure;
import net.minecraft.core.registries.*;
import net.minecraft.resources.*;
import net.minecraft.world.level.levelgen.structure.*;
import net.minecraft.world.level.levelgen.structure.pieces.*;
import net.neoforged.neoforge.registries.*;

import java.util.function.Supplier;

public class MalumStructureTypes {
    public static class StructurePieceTypes {
        public static final DeferredRegister<StructurePieceType> STRUCTURE_PIECE_TYPES = DeferredRegister.create(BuiltInRegistries.STRUCTURE_PIECE.key(), MalumMod.MALUM);

        public static final DeferredHolder<StructurePieceType, StructurePieceType> GEODE = register("geode", MalumGeodePiece::new);



        private static DeferredHolder<StructurePieceType, StructurePieceType> register(String id, StructurePieceType.ContextlessType type) {
            return STRUCTURE_PIECE_TYPES.register(id, () -> type);
        }
    }

    public static class StructureTypes {
        public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPES = DeferredRegister.create(BuiltInRegistries.STRUCTURE_TYPE.key(), MalumMod.MALUM);

        public static final Supplier<StructureType<MalumGeodeStructure>> GEODE = STRUCTURE_TYPES.register("geode", ()-> ()-> MalumGeodeStructure.CODEC);


    }

    public static class StructureKeys {


        public static final StructureKey OVERWORLD_GEODES = new StructureKey("overworld_geodes");

        public static final ResourceKey<Structure> VIVID_AMETRINE_QUARTZ_GEODE = structure("vivid_ametrine_quartz_geode");
        public static final ResourceKey<Structure> MARINE_BERYL_QUARTZ_GEODE = structure("marine_beryl_quartz_geode");
        public static final ResourceKey<Structure> RUGGED_CITRINE_QUARTZ_GEODE = structure("rugged_citrine_quartz_geode");

        public static <T> ResourceKey<Structure> structure(String id) {
            return ResourceKey.create(Registries.STRUCTURE, MalumMod.malumPath(id));
        }

        public record StructureKey(ResourceKey<Structure> structure, ResourceKey<StructureSet> structureSet) {

            public StructureKey(String id) {
                this(ResourceKey.create(Registries.STRUCTURE, MalumMod.malumPath(id)), ResourceKey.create(Registries.STRUCTURE_SET, MalumMod.malumPath(id)));
            }
        }
    }
}