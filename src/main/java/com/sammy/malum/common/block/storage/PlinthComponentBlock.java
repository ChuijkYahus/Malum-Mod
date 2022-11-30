package com.sammy.malum.common.block.storage;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.wrapper.EmptyHandler;
import team.lodestar.lodestone.systems.multiblock.MultiBlockComponentEntity;
import team.lodestar.lodestone.systems.multiblock.MultiblockComponentBlock;

import java.util.function.Supplier;

public class PlinthComponentBlock extends MultiblockComponentBlock {
    public static final VoxelShape SHAPE = makeShape();
    private final Supplier<Item> cloneStack;
    public PlinthComponentBlock(Properties properties, Supplier<Item> cloneStack) {
        super(properties);
        this.cloneStack = cloneStack;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter world, BlockPos pos, Player player) {
        return cloneStack.get().getDefaultInstance();
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState pState) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState pState, Level pLevel, BlockPos pPos) {
        BlockEntity be = pLevel.getBlockEntity(pPos);
        if (be instanceof MultiBlockComponentEntity component) {
            return ItemHandlerHelper.calcRedstoneFromInventory(component.getCapability(ForgeCapabilities.ITEM_HANDLER).orElse(new EmptyHandler()));
        }
        return 0;
    }

    public static VoxelShape makeShape(){
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.25, 0, 0.25, 0.75, 0.25, 0.75), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.5625, 0, 0.5625, 0.875, 0.25, 0.875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.5625, 0, 0.125, 0.875, 0.25, 0.4375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.6875, 0.125, 0.6875, 1, 0.75, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.6875, 0.125, 0, 1, 0.75, 0.3125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.125, 0, 0.3125, 0.75, 0.3125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.125, 0, 0.5625, 0.4375, 0.25, 0.875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.125, 0.6875, 0.3125, 0.75, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.125, 0, 0.125, 0.4375, 0.25, 0.4375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.1875, 0.5625, 0.1875, 0.8125, 0.6875, 0.8125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.0625, 0.25, 0.0625, 0.9375, 0.5625, 0.9375), BooleanOp.OR);

        return shape;
    }
}
