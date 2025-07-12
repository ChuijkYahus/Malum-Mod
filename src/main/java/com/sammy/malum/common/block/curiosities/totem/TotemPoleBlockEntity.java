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

    protected SpiritArcanaType spirit;
    protected int glow;

    protected TotemPoleState state = INACTIVE;
    protected BlockPos basePos;

    public TotemPoleBlockEntity(BlockEntityType<? extends TotemPoleBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public TotemPoleBlockEntity(BlockPos pos, BlockState state) {
        this(MalumBlockEntities.TOTEM_POLE.get(), pos, state);
        spirit = SpiritTypeProperty.getSpiritType(state).value();
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
                level.setBlockAndUpdate(worldPosition, getLogBlock().defaultBlockState());
                level.playSound(null, worldPosition, MalumSoundEvents.TOTEM_ENGRAVE.get(), SoundSource.BLOCKS, 1, 0.7f);
                MalumParticleEffectTypes.TOTEM_POLE_ACTIVATED.createEffect()
                        .at(worldPosition).color(spirit)
                        .spawn(serverLevel);
            }
            return ItemInteractionResult.SUCCESS;
        }

        if (held.is(MalumTags.ItemTags.IS_TOTEMIC_TOOL)) {
            if (level instanceof ServerLevel serverLevel) {
                boolean inactive = state.equals(INACTIVE);
                if (inactive || state.equals(VISUAL_ONLY)) {
                    state = inactive ? VISUAL_ONLY : INACTIVE;
                    float pitch = inactive ? 1.2f : 0.7f;
                    level.playSound(null, worldPosition, MalumSoundEvents.TOTEM_ENGRAVE.get(), SoundSource.BLOCKS, 1, pitch);
                    BlockStateHelper.updateState(level, worldPosition);
                    MalumParticleEffectTypes.TOTEM_POLE_ACTIVATED.createEffect()
                            .at(worldPosition).color(spirit)
                            .spawn(serverLevel);
                }
            }
            return ItemInteractionResult.SUCCESS;
        }
        return super.onUseWithItem(player, held, hand);
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag, HolderLookup.Provider registries) {
        if (spirit != null) {
            spirit.save(tag);
        }
        tag.putInt("state", state.ordinal());
        tag.putInt("glow", glow);
        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider pRegistries) {
        spirit = SpiritArcanaType.load(tag).orElse(null);
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

    public void setSpirit(ServerLevel level, SpiritLike spirit) {
        level.playSound(null, worldPosition, MalumSoundEvents.TOTEM_ENGRAVE.get(), SoundSource.BLOCKS, 1, Mth.nextFloat(level.random, 0.9f, 1.1f));
        level.playSound(null, worldPosition, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1, Mth.nextFloat(level.random, 0.9f, 1.1f));
        this.spirit = spirit.getSpirit();
        this.glow = 10;
        MalumParticleEffectTypes.TOTEM_POLE_ACTIVATED.createEffect()
                .at(worldPosition).color(spirit)
                .spawn(level);
        BlockStateHelper.updateState(level, worldPosition);
    }

    public void beginCharging(ServerLevel level, TotemBaseBlockEntity totemBase, int index) {
        float pitch = 0.8f + 0.2f * index;
        this.state = CHARGING;
        this.basePos = totemBase.getBlockPos();
        level.playSound(null, worldPosition, MalumSoundEvents.TOTEM_CHARGE.get(), SoundSource.BLOCKS, 1, 0.9f + 0.2f * pitch);
        MalumParticleEffectTypes.TOTEM_POLE_ACTIVATED.createEffect()
                .at(worldPosition).color(spirit)
                .spawn(level);
        BlockStateHelper.updateState(level, worldPosition);
    }
}