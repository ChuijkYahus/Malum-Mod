package com.sammy.malum.common.block.curiosities.poppetry;

import com.sammy.malum.common.entity.PillowSeatEntity;
import com.sammy.malum.registry.common.util.PoppetRegistrySet;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.util.FakePlayer;
import net.neoforged.neoforge.items.ItemHandlerHelper;
import org.checkerframework.checker.nullness.qual.Nullable;
import team.lodestar.lodestone.modules.toolkit.block.WaterLoggedEntityBlock;

import java.util.List;

import static com.sammy.malum.common.block.curiosities.poppetry.PoppetPillowBlockEntity.*;

@SuppressWarnings("NullableProblems")
public class PoppetPillowBlock<T extends PoppetPillowBlockEntity> extends WaterLoggedEntityBlock<T> {

    public static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 10, 16);

    protected final PoppetRegistrySet set;
    protected final DyeColor color;

    public PoppetPillowBlock(Properties properties, PoppetRegistrySet set, DyeColor color) {
        super(properties);
        this.set = set;
        this.color = color;
    }

    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        super.fallOn(level, state, pos, entity, fallDistance * 0.25f);
    }

    @Override
    public void updateEntityAfterFallOn(BlockGetter reader, Entity entity) {
        BlockPos pos = entity.blockPosition();
        if (entity instanceof Player || isNonPassenger(entity) || isSeatOccupied(entity.level(), pos)) {
            if (entity.isSuppressingBounce()) {
                super.updateEntityAfterFallOn(reader, entity);
                return;
            }

            var movement = entity.getDeltaMovement();
            if (movement.y < 0.0D) {
                double factor = entity instanceof LivingEntity ? 1.0D : 0.8D;
                entity.setDeltaMovement(movement.x, -movement.y * (double) 0.8F * factor, movement.z);
            }

            return;
        }
        if (reader.getBlockState(pos).is(this)) {
            sitDown(entity.level(), pos, entity);
        }
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        var be = level.getBlockEntity(pos);
        if (be instanceof PoppetPillowBlockEntity pedestal) {
            return ItemHandlerHelper.calcRedstoneFromInventory(pedestal.inventory);
        }
        return 0;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public PathType getBlockPathType(BlockState state, BlockGetter world, BlockPos pos, @Nullable Mob entity) {
        return PathType.RAIL;
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
        return false;
    }
}