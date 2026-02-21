package com.sammy.malum.common.block.curiosities.totem.anchor;

import com.sammy.malum.common.block.curiosities.totem.*;
import com.sammy.malum.common.entity.activator.rite.*;
import com.sammy.malum.common.item.spirit.*;
import com.sammy.malum.core.systems.spirit.type.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.block.*;

import com.sammy.malum.registry.common.magic.*;
import com.sammy.malum.registry.common.sound.*;
import net.minecraft.core.*;
import net.minecraft.nbt.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.*;
import net.minecraft.util.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;
import team.lodestar.lodestone.helpers.block.*;
import team.lodestar.lodestone.systems.blockentity.*;

import java.util.*;
import java.util.function.*;

public class RiteAnchorBlockEntity extends LodestoneBlockEntity implements RiteSparkInteractable {

    private static final int WARMUP_DURATION = 20;

    public static final HashMap<Holder<SpiritArcanaType>, Consumer<BlockRiteEffectActivator>> EFFECTS = new HashMap<>();
    static {
        EFFECTS.put(MalumSpiritTypes.SACRED_SPIRIT, BlockRiteEffectActivator::recoverHealth);
        EFFECTS.put(MalumSpiritTypes.WICKED_SPIRIT, BlockRiteEffectActivator::leechHealth);
        EFFECTS.put(MalumSpiritTypes.ELDRITCH_SPIRIT, BlockRiteEffectActivator::duplicate);

        EFFECTS.put(MalumSpiritTypes.AERIAL_SPIRIT, e -> e.upgrade(RiteSparkAttributeDataStorage::getSpeed));
        EFFECTS.put(MalumSpiritTypes.AQUEOUS_SPIRIT, e -> e.upgrade(RiteSparkAttributeDataStorage::getPotency));
        EFFECTS.put(MalumSpiritTypes.EARTHEN_SPIRIT, e -> e.upgrade(RiteSparkAttributeDataStorage::getImpact));
        EFFECTS.put(MalumSpiritTypes.INFERNAL_SPIRIT, e -> e.upgrade(RiteSparkAttributeDataStorage::getDistance));
    }

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
    public void commonTick(Level level) {
        if (spirit != null) {
            if (getBlockState().getValue(RiteAnchorBlock.POWERED)) {
                if (visualEffectStrength > WARMUP_DURATION/4) {
                    visualEffectStrength--;
                }
                return;
            }
            if (visualEffectStrength < WARMUP_DURATION) {
                visualEffectStrength++;
            }
        }
    }

    @Override
    public void travel(ServerLevel level, BlockRiteEffectActivator spark) {
        if (getBlockState().getValue(RiteAnchorBlock.POWERED)) {
            return;
        }
        if (spirit != null) {
            var holder = spirit.getHolder();
            var effect = EFFECTS.get(holder);
            if (effect != null) {
                effect.accept(spark);
            }
            if (aimDirection.data2d != -1) {
                var direction = Direction.from2DDataValue(aimDirection.data2d);
                spark.updateDirection(direction);
                notifyObservers();
                playSound(MalumSoundEvents.SPARK_DIRECTED.get());
                MalumParticleEffectTypes.RITE_ANCHOR_EFFECT.createEffect(getBlockPos().above())
                        .color(spirit)
                        .spawn(level);
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
        var old = aimDirection;
        aimDirection = AimState.fromDirection(player.getDirection());
        return old != aimDirection;
    }
}