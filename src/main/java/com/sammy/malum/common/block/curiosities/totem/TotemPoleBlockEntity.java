package com.sammy.malum.common.block.curiosities.totem;

import com.sammy.malum.core.systems.spirit.SpiritTypeProperty;
import com.sammy.malum.core.systems.spirit.type.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.block.*;

import com.sammy.malum.visual_effects.*;
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
import net.neoforged.neoforge.common.ItemAbilities;
import org.jetbrains.annotations.NotNull;
import team.lodestar.lodestone.helpers.block.*;
import team.lodestar.lodestone.systems.blockentity.*;

import static com.sammy.malum.common.block.curiosities.totem.TotemPoleBlockEntity.TotemPoleState.*;

public class TotemPoleBlockEntity extends LodestoneBlockEntity {

    public enum TotemPoleState {
        INACTIVE,
        VISUAL_ONLY,
        CHARGING,
        ACTIVE
    }

    protected final SpiritArcanaType spirit;

    protected TotemPoleState state = TotemPoleState.INACTIVE;
    protected BlockPos basePos;
    protected int glow;

    public TotemPoleBlockEntity(BlockEntityType<? extends TotemPoleBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        spirit = SpiritTypeProperty.getSpiritType(state).value();
    }

    public TotemPoleBlockEntity(BlockPos pos, BlockState state) {
        this(MalumBlockEntities.TOTEM_POLE.get(), pos, state);
    }

    public Block getLogBlock() {
        return asBlock().getLogBlock();
    }

    public boolean isSoulwood() {
        return asBlock().isSoulwood();
    }

    public TotemPoleBlock<?> asBlock() {
        return (TotemPoleBlock<?>) getBlockState().getBlock();
    }

    public SpiritArcanaType getSpirit() {
        return spirit;
    }

    public TotemPoleState getState() {
        return state;
    }

    public void setState(TotemPoleState state) {
        this.state = state;
    }

    @Override
    public ItemInteractionResult onUseWithItem(Player player, ItemStack held, InteractionHand hand) {
        if (held.canPerformAction(ItemAbilities.AXE_STRIP)) {
            if (level instanceof ServerLevel serverLevel) {
                strip(serverLevel);
            }
            return ItemInteractionResult.SUCCESS;
        }

        if (held.is(MalumTags.ItemTags.IS_TOTEMIC_TOOL)) {
            if (level instanceof ServerLevel serverLevel) {
                if (state.equals(CHARGING) || state.equals(ACTIVE)) {
                    return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
                }
                toggleVisuals(serverLevel);
            }
            return ItemInteractionResult.SUCCESS;
        }
        return super.onUseWithItem(player, held, hand);
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag, HolderLookup.Provider registries) {
        tag.putInt("state", state.ordinal());
        tag.putInt("glow", glow);
        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider pRegistries) {
        state = TotemPoleState.values()[tag.getInt("state")];
        glow = tag.getInt("glow");
        super.loadAdditional(tag, pRegistries);
    }

    @Override
    public void tick() {
        super.tick();
        if (state.equals(INACTIVE)) {
            if (glow > 0) {
                glow--;
            }
        } else {
            int cap = state.equals(CHARGING) ? 10 : 20;
            if (glow < cap) {
                glow++;
            }
        }
        if (level.isClientSide) {
            if (spirit != null && state.equals(ACTIVE)) {
                TotemParticleEffects.activeTotemPoleParticles(this);
            }
        }
    }

    public float getGlowDelta() {
        return glow / 20f;
    }

    public void brieflyActivate(ServerLevel level) {
        float pitch = Mth.nextFloat(level.random, 0.9f, 1.1f);
        level.playSound(null, worldPosition, MalumSoundEvents.TOTEM_ENGRAVE.get(), SoundSource.BLOCKS, 1, pitch);
        level.playSound(null, worldPosition, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1, pitch);
        this.glow = 10;
        MalumParticleEffectTypes.TOTEM_POLE_ACTIVATED.createEffect()
                .at(worldPosition).color(spirit)
                .spawn(level);
        BlockStateHelper.updateState(level, worldPosition);
    }

    public void beginCharging(ServerLevel level, TotemBaseBlockEntity totemBase, int index) {
        float pitch = 0.8f + 0.2f * index;
        this.state = TotemPoleState.CHARGING;
        this.basePos = totemBase.getBlockPos();
        level.playSound(null, worldPosition, MalumSoundEvents.TOTEM_CHARGE.get(), SoundSource.BLOCKS, 1, pitch);
        MalumParticleEffectTypes.TOTEM_POLE_ACTIVATED.createEffect()
                .at(worldPosition).color(spirit)
                .spawn(level);
        BlockStateHelper.updateState(level, worldPosition);
    }

    public void strip(ServerLevel level) {
        level.setBlockAndUpdate(worldPosition, getLogBlock().defaultBlockState());
        level.playSound(null, worldPosition, MalumSoundEvents.TOTEM_ENGRAVE.get(), SoundSource.BLOCKS, 1, 0.7f);
        MalumParticleEffectTypes.TOTEM_POLE_ACTIVATED.createEffect()
                .at(worldPosition)
                .color(spirit)
                .spawn(level);
    }

    public void toggleVisuals(ServerLevel level) {
        if (state.equals(VISUAL_ONLY)) {
            state = INACTIVE;
        } else if (state.equals(INACTIVE)) {
            state = VISUAL_ONLY;
        }
        float pitch = state.equals(INACTIVE) ? 1.2f : 0.7f;
        level.playSound(null, worldPosition, MalumSoundEvents.TOTEM_ENGRAVE.get(), SoundSource.BLOCKS, 1, pitch);
        BlockStateHelper.updateState(level, worldPosition);
        MalumParticleEffectTypes.TOTEM_POLE_ACTIVATED.createEffect()
                .at(worldPosition)
                .color(spirit)
                .spawn(level);
    }
}