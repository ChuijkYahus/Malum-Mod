package com.sammy.malum.common.block.curiosities.poppetry;

import com.sammy.malum.common.block.storage.MalumItemHolderBlockEntity;
import com.sammy.malum.common.entity.PillowSeatEntity;
import com.sammy.malum.registry.common.MalumTags;
import com.sammy.malum.registry.common.block.MalumBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.common.util.FakePlayer;
import team.lodestar.lodestone.modules.toolkit.blockentity.LodestoneBlockEntityType;

import java.util.List;
import java.util.Optional;

public class PoppetPillowBlockEntity extends MalumItemHolderBlockEntity {

    public PoppetPillowBlockEntity(LodestoneBlockEntityType<? extends PoppetPillowBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public PoppetPillowBlockEntity(BlockPos pos, BlockState state) {
        this(MalumBlockEntities.POPPET_PILLOW.get(), pos, state);
        inventory.attachDisplayData(PoppetPillowDisplayData::new);
    }

    @Override
    public ItemInteractionResult onUse(Player player, InteractionHand hand) {
        if (player.isShiftKeyDown() || player instanceof FakePlayer) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }
        var pos = getBlockPos();
        var stack = player.getItemInHand(hand);

        var block = (PoppetPillowBlock<?>) getBlockState().getBlock();
        var color = DyeColor.getColor(stack);
        if (color != null && color != block.color) {
            if (level.isClientSide) {
                return ItemInteractionResult.SUCCESS;
            }
            var newState = block.set.getPillow().getVariant(color).getDefaultState();
            level.setBlockAndUpdate(pos, newState);
            return ItemInteractionResult.SUCCESS;
        }

        var seats = level.getEntitiesOfClass(PillowSeatEntity.class, new AABB(pos));
        if (!seats.isEmpty()) {
            var seatEntity = seats.getFirst();
            var passengers = seatEntity.getPassengers();
            if (!passengers.isEmpty() && passengers.getFirst() instanceof Player) {
                return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
            if (!level.isClientSide) {
                seatEntity.ejectPassengers();
                player.startRiding(seatEntity);
            }
            return ItemInteractionResult.SUCCESS;
        }

        if (!level.isClientSide) {
            Entity leashed = getLeashed(player).orElse(player);
            sitDown(level, pos, leashed);
        }
        return ItemInteractionResult.SUCCESS;
    }

    public static boolean isNonPassenger(Entity passenger) {
        if (passenger instanceof Shulker)
            return true;
        if (passenger instanceof Player)
            return true;
        if (passenger.getType().is(MalumTags.Entities.IGNORE_SEAT))
            return true;
        return !(passenger instanceof LivingEntity);
    }

    public static boolean isSeatOccupied(Level world, BlockPos pos) {
        return !world.getEntitiesOfClass(PillowSeatEntity.class, new AABB(pos)).isEmpty();
    }

    public static void sitDown(Level level, BlockPos pos, Entity entity) {
        if (level.isClientSide)
            return;
        PillowSeatEntity seat = new PillowSeatEntity(level);
        seat.setPos(pos.getX() + 0.5f, pos.getY(), pos.getZ() + 0.5f);
        level.addFreshEntity(seat);
        entity.startRiding(seat, true);
        if (entity instanceof TamableAnimal animal) {
            animal.setInSittingPose(true);
        }
    }

    public static Optional<Entity> getLeashed(Player player) {
        var nearby = player.level().getEntities((Entity) null, player.getBoundingBox().inflate(10), e -> true);
        for (Entity entity : nearby) {
            if (!(entity instanceof Mob mob)) {
                continue;
            }
            if (!player.equals(mob.getLeashHolder())) {
                continue;
            }
            if (isNonPassenger(mob)) {
                continue;
            }
            return Optional.of(mob);
        }
        return Optional.empty();
    }
}