package com.sammy.malum.common.block.curiosities.obelisk.runewood;

import com.sammy.malum.common.block.curiosities.obelisk.*;
import com.sammy.malum.common.block.curiosities.crafting.spirit_altar.*;
import com.sammy.malum.core.systems.spirit.type.*;
import com.sammy.malum.registry.common.MalumContent;
import com.sammy.malum.registry.common.block.*;
import com.sammy.malum.visual_effects.block.SpiritAltarParticleEffects;
import net.minecraft.core.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.phys.*;
import team.lodestar.lodestone.modules.toolkit.multiblock.*;

import java.util.function.*;

public class RunewoodObeliskBlockEntity extends ObeliskCoreBlockEntity implements IAltarAccelerator {
    private static final Vec3 OBELISK_PARTICLE_OFFSET = new Vec3(0.5f, 2f, 0.5f);

    private static final AltarAcceleratorType OBELISK = new AltarAcceleratorType(4, "obelisk");
    public static final Supplier<MultiBlockStructure> STRUCTURE = () -> (MultiBlockStructure.of(new MultiBlockStructure.StructurePiece(0, 1, 0, MalumContent.Progression.RUNEWOOD_OBELISK_COMPONENT.get().defaultBlockState())));

    public RunewoodObeliskBlockEntity(BlockPos pos, BlockState state) {
        super(MalumBlockEntities.RUNEWOOD_OBELISK.get(), STRUCTURE.get(), pos, state);
    }

    @Override
    public AltarAcceleratorType getAcceleratorType() {
        return OBELISK;
    }

    @Override
    public float getAcceleration() {
        return 0.25f;
    }

    @Override
    public void addParticles(SpiritAltarBlockEntity altar, SpiritArcanaType activeSpiritType) {
        SpiritAltarParticleEffects.runewoodObeliskParticles(this, altar, activeSpiritType);
    }

    public Vec3 getParticleOffset() {
        return OBELISK_PARTICLE_OFFSET;
    }
}