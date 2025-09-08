package com.sammy.malum.common.block.curiosities.totem.anchor;

import com.sammy.malum.common.block.curiosities.totem.*;
import com.sammy.malum.common.entity.activator.*;
import com.sammy.malum.common.item.spirit.*;
import com.sammy.malum.core.systems.spirit.type.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.block.*;

import com.sammy.malum.registry.common.magic.*;
import net.minecraft.core.*;
import net.minecraft.nbt.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.*;
import net.minecraft.util.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;
import team.lodestar.lodestone.helpers.block.*;
import team.lodestar.lodestone.systems.blockentity.*;

public class RiteAnchorBlockEntity extends LodestoneBlockEntity implements RiteSparkInteractable {

    private static final int WARMUP_DURATION = 20;

    public static final StringRepresentable.EnumCodec<AimState> CODEC = StringRepresentable.fromEnum(AimState::values);

    public enum AimState implements StringRepresentable {
        NORTH("north", 2),
        SOUTH("south", 0),
        WEST("west", 1),
        EAST("east", 3),
        PUSH("push", -1),
        PULL("pull", -1);

        public final String name;
        public final int data2d;
        AimState(String name, int data2d) {
            this.name = name;
            this.data2d = data2d;
        }

        public String getName() {
            return name;
        }

        public int getData2d() {
            return data2d;
        }

        @Override
        public String getSerializedName() {
            return name;
        }

        public static AimState fromDirection(Direction direction) {
            return switch (direction) {
                case NORTH -> NORTH;
                case SOUTH -> SOUTH;
                case WEST -> WEST;
                case EAST -> EAST;
                case UP -> PUSH;
                case DOWN -> PULL;
            };
        }
    }

    protected SpiritArcanaType spirit;
    protected int visualEffectStrength;
    protected AimState aimDirection;

    public RiteAnchorBlockEntity(BlockEntityType<? extends RiteAnchorBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public RiteAnchorBlockEntity(BlockPos pos, BlockState state) {
        this(MalumBlockEntities.RITE_ANCHOR.get(), pos, state);
    }

    @Override
    protected void saveAdditional(CompoundTag compound, HolderLookup.Provider registryLookup) {
        if (spirit != null) {
            spirit.save(compound);
        }
        if (aimDirection != null) {
            compound.putString("aimDirection", aimDirection.name);
        }
        compound.putInt("visualEffectStrength", visualEffectStrength);
        super.saveAdditional(compound, registryLookup);
    }

    @Override
    public void loadAdditional(CompoundTag compound, HolderLookup.Provider registries) {
        spirit = SpiritArcanaType.load(compound).orElse(null);
        aimDirection = compound.contains("aimDirection") ? CODEC.byName(compound.getString("aimDirection")) : null;
        visualEffectStrength = compound.getInt("visualEffectStrength");
        super.loadAdditional(compound, registries);
    }

    @Override
    public void tick() {
        if (level instanceof ServerLevel serverLevel) {
        }
        if (spirit != null) {
            if (visualEffectStrength < WARMUP_DURATION) {
                visualEffectStrength++;
            }
        }
    }

    @Override
    public void travel(BlockRiteEffectActivatorEntity entity) {
        if (spirit != null) {
            var level = entity.level();
            if (aimDirection.data2d != -1) {
                Direction direction = Direction.from2DDataValue(aimDirection.data2d);
                entity.updateDirection(direction);
            }
            if (spirit.matches(MalumSpiritTypes.SACRED_SPIRIT)) {
                //Recovers Remaining Distance Overtime
                entity.recoverHealth();
            }
            if (spirit.matches(MalumSpiritTypes.WICKED_SPIRIT)) {
                //Sacrifices One Stat to Instantly Recover Remaining Distance
                entity.leechHealth();
            }
            if (spirit.matches(MalumSpiritTypes.ARCANE_SPIRIT)) {
                //Free Turn
            }
            if (spirit.matches(MalumSpiritTypes.ELDRITCH_SPIRIT)) {
                //Splits Spark
            }
            if (spirit.matches(MalumSpiritTypes.AERIAL_SPIRIT)) {
                //Increases Speed
                if (entity.tryUpgrade(level)) {
                    entity.speed.increase();
                }
            }
            if (spirit.matches(MalumSpiritTypes.AQUEOUS_SPIRIT)) {
                //Increases Potency
                if (entity.tryUpgrade(level)) {
                    entity.potency.increase();
                }
            }
            if (spirit.matches(MalumSpiritTypes.EARTHEN_SPIRIT)) {
                //Increases Distance
                if (entity.tryUpgrade(level)) {
                    entity.distance.increase();
                }
            }
            if (spirit.matches(MalumSpiritTypes.INFERNAL_SPIRIT)) {
                //Increases Impact
                if (entity.tryUpgrade(level)) {
                    entity.impact.increase();
                }
            }
        }
    }

    @Override
    public ItemInteractionResult onUseWithItem(Player pPlayer, ItemStack pStack, InteractionHand pHand) {
        if (pStack.is(MalumTags.ItemTags.IS_TOTEMIC_TOOL)) {
            if (level instanceof ServerLevel serverLevel) {
                if (updateAimDirection(serverLevel, pPlayer)) {
                    BlockStateHelper.updateState(level, worldPosition);
                    return ItemInteractionResult.SUCCESS;
                }
            }
        }
        if (pStack.getItem() instanceof SpiritShardItem shard) {
            if (spirit != null && shard.matches(spirit)) {
                return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
            if (level instanceof ServerLevel serverLevel) {
                final SpiritArcanaType spirit = shard.getSpirit();
                setSpirit(serverLevel, pPlayer, spirit);
                if (!pPlayer.isCreative() && !spirit.matches(MalumSpiritTypes.UMBRAL_SPIRIT)) {
                    pStack.shrink(1);
                }
                BlockStateHelper.updateState(level, worldPosition);
            }
            return ItemInteractionResult.SUCCESS;
        }
        return super.onUseWithItem(pPlayer, pStack, pHand);
    }

    public SpiritArcanaType getSpirit() {
        return spirit;
    }

    public AimState getAimDirection() {
        return aimDirection;
    }

    public float getGlowDelta() {
        return visualEffectStrength / (float) WARMUP_DURATION;
    }

    public void setSpirit(ServerLevel level, Player player, SpiritArcanaType spirit) {
        updateAimDirection(level, player);
        level.playSound(null, worldPosition, MalumSoundEvents.TOTEM_ENGRAVE.get(), SoundSource.BLOCKS, 1, Mth.nextFloat(level.random, 0.9f, 1.1f));
        level.playSound(null, worldPosition, SoundEvents.DEEPSLATE_BRICKS_PLACE, SoundSource.BLOCKS, 1, Mth.nextFloat(level.random, 0.9f, 1.1f));
        this.visualEffectStrength = 0;
        this.spirit = spirit;
        level.levelEvent(2001, worldPosition, Block.getId(level.getBlockState(worldPosition)));
    }

    public boolean updateAimDirection(ServerLevel level, Player player) {
        BlockState state = getBlockState();
        var facing = state.getValue(RiteAnchorBlock.FACING);
        if (facing.getAxis().isVertical()) {
            var old = aimDirection;
            aimDirection = AimState.fromDirection(player.getDirection());
            return old != aimDirection;
        }
        else {
            if (aimDirection == AimState.PUSH) {
                aimDirection = AimState.PULL;
            }
            else {
                aimDirection = AimState.PUSH;
            }
            return true;
        }
    }


}