package com.sammy.malum.common.block.dungeon.curiosities;

import com.sammy.malum.common.block.curiosities.obelisk.*;
import com.sammy.malum.common.block.curiosities.spirit_altar.*;
import com.sammy.malum.core.systems.spirit.type.*;
import com.sammy.malum.registry.common.content.MalumContent;
import com.sammy.malum.registry.common.content.block.*;
import net.minecraft.core.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.phys.*;
import team.lodestar.lodestone.modules.toolkit.multiblock.*;

import java.util.function.*;

public class OminousObeliskBlockEntity extends ObeliskCoreBlockEntity implements IAltarAccelerator {
    private static final Vec3 OBELISK_PARTICLE_OFFSET = new Vec3(0.5f, 2f, 0.5f);

    private static final AltarAcceleratorType OBELISK = new AltarAcceleratorType(4, "ominous_obelisk");
    public static final Supplier<MultiBlockStructure> STRUCTURE = () -> (MultiBlockStructure.of(new MultiBlockStructure.StructurePiece(0, 1, 0, MalumContent.DungeonBlockSets.OMINOUS_OBELISK_COMPONENT.get().defaultBlockState())));

    public OminousObeliskBlockEntity(BlockPos pos, BlockState state) {
        super(MalumBlockEntities.OMINOUS_OBELISK.get(), STRUCTURE.get(), pos, state);
    }

    @Override
    public AltarAcceleratorType getAcceleratorType() {
        return OBELISK;
    }

    @Override
    public float getAcceleration() {
        return 0.15f;
    }

    @Override
    public boolean canAccelerate(SpiritAltarBlockEntity altar) {
        return altar instanceof OminousAltarBlockEntity;
    }

    @Override
    public void addParticles(SpiritAltarBlockEntity altar, SpiritArcanaType activeSpiritType) {
    }

    public Vec3 getParticleOffset() {
        return OBELISK_PARTICLE_OFFSET;
    }
}