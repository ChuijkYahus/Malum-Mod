package com.sammy.malum.common.block.curiosities.totem.anchor;

import com.sammy.malum.common.item.spirit.*;
import com.sammy.malum.core.systems.spirit.*;
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
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;
import org.jetbrains.annotations.NotNull;
import team.lodestar.lodestone.helpers.block.*;
import team.lodestar.lodestone.systems.blockentity.*;

public class RiteAnchorBlockEntity extends LodestoneBlockEntity {

    private static final int EFFECT_STRENGTH = 20;
    private static final int EFFECT_REST = EFFECT_STRENGTH/2;

    protected SpiritArcanaType spirit;

    protected int visualEffectStrength = 0;

    public RiteAnchorBlockEntity(BlockEntityType<? extends RiteAnchorBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public RiteAnchorBlockEntity(BlockPos pos, BlockState state) {
        this(MalumBlockEntities.RITE_ANCHOR.get(), pos, state);
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag, HolderLookup.Provider registries) {
        if (spirit != null) {
            spirit.save(tag);
        }
        if (visualEffectStrength != 0) {
            tag.putInt("effectStrength", visualEffectStrength);
        }
        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider pRegistries) {
        spirit = SpiritArcanaType.load(tag).orElse(null);
        visualEffectStrength = tag.getInt("effectStrength");
        super.loadAdditional(tag, pRegistries);
    }

    @Override
    public void tick() {
        super.tick();
        if (spirit == null) {
            if (visualEffectStrength > 0) {
                visualEffectStrength--;
            }
        }
        else {
            if (visualEffectStrength > EFFECT_REST) {
                visualEffectStrength--;
            }
        }
    }

    @Override
    public ItemInteractionResult onUseWithItem(Player pPlayer, ItemStack pStack, InteractionHand pHand) {
        if (pStack.getItem() instanceof SpiritShardItem shard) {
            if (imbueAnchorBlock(level, worldPosition, shard)) {
                if (shard.matches(MalumSpiritTypes.UMBRAL_SPIRIT)) {
                    return ItemInteractionResult.FAIL;
                }
                if (spirit == null || shard.matches(spirit)) {
                    return ItemInteractionResult.FAIL;
                }
                if (level instanceof ServerLevel serverLevel) {
                    Direction facing = level.getBlockState(worldPosition).getValue(RiteAnchorBlock.HORIZONTAL_FACING);
                    var newState = SpiritTypeProperty.setSpiritType(MalumBlocks.RITE_ANCHOR.get().defaultBlockState(), spirit).setValue(RiteAnchorBlock.HORIZONTAL_FACING, facing);
                    level.setBlockAndUpdate(worldPosition, newState);
                    setSpirit(serverLevel, spirit.getSpirit());
                }
                return ItemInteractionResult.SUCCESS;
            }
        }
        return super.onUseWithItem(pPlayer, pStack, pHand);
    }

    public SpiritArcanaType getSpirit() {
        return spirit;
    }

    public float getEffectDelta() {
        return visualEffectStrength / (float) EFFECT_STRENGTH;
    }

    public void setSpirit(ServerLevel level, SpiritArcanaType spirit) {
        level.playSound(null, worldPosition, MalumSoundEvents.TOTEM_ENGRAVE.get(), SoundSource.BLOCKS, 1, Mth.nextFloat(level.random, 0.9f, 1.1f));
        level.playSound(null, worldPosition, SoundEvents.DEEPSLATE_BRICKS_PLACE, SoundSource.BLOCKS, 1, Mth.nextFloat(level.random, 0.9f, 1.1f));
        this.spirit = spirit;
        this.visualEffectStrength = EFFECT_STRENGTH;
        level.levelEvent(2001, worldPosition, Block.getId(level.getBlockState(worldPosition)));
        BlockStateHelper.updateState(level, worldPosition);
    }

    public static boolean imbueAnchorBlock(Level level, BlockPos pos, SpiritLike spirit) {
        if (spirit.matches(MalumSpiritTypes.UMBRAL_SPIRIT)) {
            return false;
        }
        if (level.getBlockEntity(pos) instanceof RiteAnchorBlockEntity blockEntity) {
            if (blockEntity.spirit == null || blockEntity.spirit.matches(spirit)) {
                return false;
            }
        }
        if (level instanceof ServerLevel serverLevel) {
            Direction facing = level.getBlockState(pos).getValue(RiteAnchorBlock.HORIZONTAL_FACING);
            var newState = SpiritTypeProperty.setSpiritType(MalumBlocks.RITE_ANCHOR.get().defaultBlockState(), spirit).setValue(RiteAnchorBlock.HORIZONTAL_FACING, facing);
            level.setBlockAndUpdate(pos, newState);
            if (level.getBlockEntity(pos) instanceof RiteAnchorBlockEntity blockEntity) {
                blockEntity.setSpirit(serverLevel, spirit.getSpirit());
            }
        }
        return true;
    }
}