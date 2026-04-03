package com.sammy.malum.common.block.storage.jar;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.*;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.event.entity.player.*;
import team.lodestar.lodestone.modules.toolkit.block.WaterLoggedEntityBlock;

public class SpiritJarBlock<T extends SpiritJarBlockEntity> extends WaterLoggedEntityBlock<T> {
    public static final VoxelShape SHAPE = makeShape();

    public SpiritJarBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return SHAPE;
    }

    @Override
    public void attack(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer) {
        handleAttack(pLevel, pPos, pPlayer);
    }

    public static void cancelBreak(PlayerInteractEvent.LeftClickBlock event) {
        BlockPos pos = event.getPos();
        Level level = event.getLevel();
        BlockState state = level.getBlockState(pos);
        Block block = state.getBlock();
        if (block instanceof SpiritJarBlock jarBlock) {
            Player player = event.getEntity();
            BlockHitResult target = Item.getPlayerPOVHitResult(level, player, ClipContext.Fluid.NONE);
            if (target.getType() == HitResult.Type.BLOCK && target.getBlockPos().equals(pos) && target.getDirection().getAxis() == Direction.Axis.X) {
                if (player.isCreative()) {
                    event.setCanceled(jarBlock.handleAttack(level, pos, player));
                }
            }
        }
    }

    public boolean handleAttack(Level level, BlockPos pos, Player player) {
        if (level.getBlockEntity(pos) instanceof SpiritJarBlockEntity jar) {
            return jar.handleAttack(player);
        }
        return false;
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState pState) {
        return true;
    }

    @Override
    protected int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        if (level.getBlockEntity(pos) instanceof SpiritJarBlockEntity jar) {
            return jar.contents != null ? jar.contents.getSpirit().getAnalogSignal() : 0;
        }
        return 0;
    }

    public static VoxelShape makeShape() {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.15625, 0.03125, 0.15625, 0.84375, 0.84375, 0.84375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.21875, 0.90625, 0.21875, 0.78125, 1.03125, 0.78125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.28125, 0.84375, 0.28125, 0.71875, 0.90625, 0.71875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.34375, -0.03125, 0.34375, 0.65625, 0.09375, 0.65625), BooleanOp.OR);
        return shape;
    }
}