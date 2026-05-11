package com.sammy.malum.registry.common.worldgen;

import com.sammy.malum.*;
import com.sammy.malum.common.worldgen.geode.MalumGeodePiece;
import com.sammy.malum.common.worldgen.geode.MalumGeodeStructure;
import com.sammy.malum.common.worldgen.sanctuary.RunicSanctuaryPiece;
import com.sammy.malum.common.worldgen.sanctuary.RunicSanctuaryStructure;
import com.sammy.malum.common.worldgen.well.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.resources.*;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.structure.*;
import net.minecraft.world.level.levelgen.structure.pieces.*;
import net.neoforged.neoforge.registries.*;

import java.util.function.Supplier;

public class MalumStructureTypes {
    public static class StructurePieceTypes {
        public static final DeferredRegister<StructurePieceType> STRUCTURE_PIECE_TYPES = DeferredRegister.create(BuiltInRegistries.STRUCTURE_PIECE.key(), MalumMod.MALUM);

        public static final DeferredHolder<StructurePieceType, StructurePieceType> RUNIC_SANCTUARY = register("runic_sanctuary", RunicSanctuaryPiece::new);
        public static final DeferredHolder<StructurePieceType, StructurePieceType> GEODE = register("geode", MalumGeodePiece::new);

        public static final DeferredHolder<StructurePieceType, StructurePieceType> WEEPING_WELL = register("weeping_well", WeepingWellStructurePiece::new);


        private static DeferredHolder<StructurePieceType, StructurePieceType> register(String id, StructurePieceType.ContextlessType type) {
            return STRUCTURE_PIECE_TYPES.register(id, () -> type);
        }
    }

    public static class StructureTypes {
        public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPES = DeferredRegister.create(BuiltInRegistries.STRUCTURE_TYPE.key(), MalumMod.MALUM);

        public static final Supplier<StructureType<RunicSanctuaryStructure>> RUNIC_SANCTUARY = STRUCTURE_TYPES.register("runic_sanctuary", ()-> ()-> RunicSanctuaryStructure.CODEC);
        public static final Supplier<StructureType<MalumGeodeStructure>> GEODE = STRUCTURE_TYPES.register("geode", ()-> ()-> MalumGeodeStructure.CODEC);

        public static final DeferredHolder<StructureType<?>, StructureType<WeepingWellStructure>> WEEPING_WELL = STRUCTURE_TYPES.register("weeping_well", () -> () -> WeepingWellStructure.CODEC);

    }

    public static class StructureKeys {

        public static final StructureKey WEEPING_WELL = new StructureKey("weeping_well");

        public static final StructureKey QUARTZ_GEODE = new StructureKey("quartz_geode");
        public static final StructureKey VIVID_QUARTZ_GEODE = new StructureKey("vivid_quartz_geode");
        public static final StructureKey MARINE_QUARTZ_GEODE = new StructureKey("marine_quartz_geode");
        public static final StructureKey RUGGED_QUARTZ_GEODE = new StructureKey("rugged_quartz_geode");

        public static final StructureKey NETHER_QUARTZ_GEODE = new StructureKey("nether_quartz_geode");
        public static final StructureKey JAGGED_QUARTZ_GEODE = new StructureKey("jagged_quartz_geode");
        public static final StructureKey PERFECT_QUARTZ_GEODE = new StructureKey("perfect_quartz_geode");
        public static final StructureKey BLAZING_QUARTZ_GEODE = new StructureKey("blazing_quartz_geode");


        public static final StructureKey RUNIC_SANCTUARY = new StructureKey("runic_sanctuary");
        public static final StructureKey AZURE_SANCTUARY = new StructureKey("azure_sanctuary");


        public static <T> ResourceKey<T> register(ResourceKey<Registry<T>> key, String id) {
            return ResourceKey.create(key, MalumMod.malumPath(id));
        }

        public record StructureKey(ResourceKey<Structure> structure, ResourceKey<StructureSet> structureSet) {

            public StructureKey(String id) {
                this(ResourceKey.create(Registries.STRUCTURE, MalumMod.malumPath(id)), ResourceKey.create(Registries.STRUCTURE_SET, MalumMod.malumPath(id)));
            }
        }
    }
}