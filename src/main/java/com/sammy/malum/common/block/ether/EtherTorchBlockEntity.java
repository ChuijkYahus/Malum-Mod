package com.sammy.malum.common.block.ether;

import com.sammy.malum.registry.common.block.MalumBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.commons.lang3.mutable.MutableDouble;
import team.lodestar.lodestone.modules.toolkit.blockentity.LodestoneBlockEntityType;

public class EtherTorchBlockEntity extends EtherBlockEntity{

    public EtherTorchBlockEntity(LodestoneBlockEntityType<? extends EtherBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public EtherTorchBlockEntity(BlockPos pos, BlockState state) {
        this(MalumBlockEntities.ETHER_TORCH.get(), pos, state);
    }

    @Override
    public void modifyParticleCenter(MutableDouble x, MutableDouble y, MutableDouble z) {
        if (getBlockState().getBlock() instanceof EtherWallTorchBlock<?>) {
            float offset = 0.15f;
            var direction = getBlockState().getValue(WallTorchBlock.FACING);
            var normal = direction.getNormal();
            x.subtract(normal.getX() * offset);
            y.add(0.4f);
            z.subtract(normal.getZ() * offset);
        }
        else {
            y.add(0.3f);
        }
    }
}
