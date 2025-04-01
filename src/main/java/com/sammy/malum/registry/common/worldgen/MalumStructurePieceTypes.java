package com.sammy.malum.registry.common.worldgen;

import com.sammy.malum.*;
import com.sammy.malum.common.worldgen.well.*;
import net.minecraft.core.registries.*;
import net.minecraft.world.level.levelgen.structure.pieces.*;
import net.neoforged.neoforge.registries.*;

import java.util.function.*;

public class MalumStructurePieceTypes {

    public static final DeferredRegister<StructurePieceType> STRUCTURE_PIECE_TYPES = DeferredRegister.create(BuiltInRegistries.STRUCTURE_PIECE.key(), MalumMod.MALUM);

    public static final DeferredHolder<StructurePieceType, StructurePieceType> WEEPING_WELL = STRUCTURE_PIECE_TYPES.register("weeping_well", setPieceId(WeepingWellStructurePiece::new));

    private static Supplier<StructurePieceType> setPieceId(StructurePieceType.ContextlessType type) {
        return () -> type;
    }
}