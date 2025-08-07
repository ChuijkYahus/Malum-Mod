package com.sammy.malum.common.block.curiosities.totem;

import com.sammy.malum.core.systems.rite.*;
import com.sammy.malum.core.systems.spirit.type.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.block.*;
import com.sammy.malum.registry.common.magic.rite.MalumSpiritRiteTypes;
import net.minecraft.core.*;
import net.minecraft.nbt.*;
import net.minecraft.server.level.*;
import net.minecraft.sounds.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;
import team.lodestar.lodestone.helpers.block.*;
import team.lodestar.lodestone.systems.blockentity.*;

import javax.annotation.*;
import java.util.*;

public class TotemBaseBlockEntity extends LodestoneBlockEntity {

    private static final int INTERVAL = 20;

    public enum TotemBaseState {
        INACTIVE,
        ASSEMBLING,
        ACTIVE;
    }

    public final boolean corrupted;

    protected TotemBaseState state = TotemBaseState.INACTIVE;
    protected SpiritRiteType rite;
    protected int totemHeight;
    protected int timer;

    public TotemBaseBlockEntity(BlockEntityType<? extends TotemBaseBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.corrupted = ((TotemBaseBlock<?>) state.getBlock()).corrupted;
    }

    public TotemBaseBlockEntity(BlockPos pos, BlockState state) {
          this(MalumBlockEntities.TOTEM_BASE.get(), pos, state);
    }

    @Override
    protected void saveAdditional(CompoundTag compound, HolderLookup.Provider registries) {
        compound.putInt("state", state.ordinal());
        if (rite != null) {
            rite.save(compound);
        }
        compound.putInt("totemHeight", totemHeight);
        compound.putInt("timer", timer);
        super.saveAdditional(compound, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag compound, HolderLookup.Provider pRegistries) {
        state = TotemBaseState.values()[compound.getInt("state")];
        rite = SpiritRiteType.load(compound).orElse(null);
        totemHeight = compound.getInt("totemHeight");
        timer = compound.getInt("timer");
        super.loadAdditional(compound, pRegistries);
    }

    @Override
    public void tick() {
        super.tick();
        if (level instanceof ServerLevel serverLevel) {
            switch (state) {
                case ACTIVE -> {
                    timer--;
                    if (timer <= 0) {
                        timer = 100;
                        rite.triggerRiteEffect(serverLevel, this);
                        BlockStateHelper.updateAndNotifyState(serverLevel, worldPosition);
                    }
                }
                case ASSEMBLING -> {
                    timer--;
                    if (timer <= 0) {
                        var polePos = worldPosition.above(totemHeight +1);
                        if (serverLevel.getBlockEntity(polePos) instanceof TotemPoleBlockEntity pole) {
                            timer = INTERVAL;
                            addTotemPole(serverLevel, pole);
                        } else {
                            var rite = MalumSpiritRiteTypes.getRite(serverLevel, this);
                            if (rite == null) {
                                setState(serverLevel, TotemBaseState.INACTIVE);
                                return;
                            }
                            this.rite = rite;
                            setTotemPoleState(serverLevel, TotemPoleBlockEntity.TotemPoleState.ACTIVE);
                            setState(serverLevel, TotemBaseState.ACTIVE);
                        }
                    }
                }
            }
        }
    }

    @Override
    public ItemInteractionResult onUseWithItem(Player player, ItemStack pStack, InteractionHand pHand) {
        if (state.equals(TotemBaseState.ASSEMBLING)) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }
        if (getFirstTotemPole().isPresent()) {
            if (level instanceof ServerLevel serverLevel) {
                if (state.equals(TotemBaseState.ACTIVE)) {
                    setState(serverLevel, TotemBaseState.INACTIVE);
                } else {
                    setState(serverLevel, TotemBaseState.ASSEMBLING);
                }
                BlockStateHelper.updateState(level, worldPosition);
            }
            return ItemInteractionResult.SUCCESS;

        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    public void onBreak(@Nullable Player player) {
        if (level instanceof ServerLevel serverLevel) {
            setState(serverLevel, TotemBaseState.INACTIVE);
        }
    }

    public TotemBaseState getState() {
        return state;
    }

    public SpiritRiteType getRite() {
        return rite;
    }

    public int getTotemHeight() {
        return totemHeight;
    }

    public void addTotemPole(ServerLevel level, TotemPoleBlockEntity pole) {
        totemHeight++;
        pole.beginCharging(level,this, totemHeight);
        BlockStateHelper.updateState(level, worldPosition);
    }

    public void setState(ServerLevel level, TotemBaseState newState) {
        if (newState.equals(TotemBaseState.INACTIVE)) {
            level.playSound(null, worldPosition, MalumSoundEvents.TOTEM_CANCELLED.get(), SoundSource.BLOCKS, 1, 1f);
            setTotemPoleState(level, TotemPoleBlockEntity.TotemPoleState.INACTIVE);
            totemHeight = 0;
            rite = null;
        }
        if (newState.equals(TotemBaseState.ACTIVE)) {
            level.playSound(null, worldPosition, MalumSoundEvents.TOTEM_ACTIVATED.get(), SoundSource.BLOCKS, 1, 1f);
        }
        this.state = newState;
        this.timer = 0;
        BlockStateHelper.updateAndNotifyState(level, worldPosition);
    }

    public void setTotemPoleState(ServerLevel level, TotemPoleBlockEntity.TotemPoleState state) {
        for (TotemPoleBlockEntity totemPole : getTotemPoles(level)) {
            totemPole.setState(state);
            BlockStateHelper.updateState(level, totemPole.getBlockPos());
        }
    }

    public List<SpiritArcanaType> getSpirits(ServerLevel level) {
        return getTotemPoles(level).stream().map(TotemPoleBlockEntity::getSpirit).toList();
    }

    public List<TotemPoleBlockEntity> getTotemPoles(ServerLevel level) {
        List<TotemPoleBlockEntity> totemPoles = new ArrayList<>();
        BlockPos.MutableBlockPos mutable = getBlockPos().mutable();
        for (int i = 0; i < totemHeight; i++) {
            mutable.move(Direction.UP);
            if (level.getBlockEntity(mutable) instanceof TotemPoleBlockEntity totemPole) {
                totemPoles.add(totemPole);
            }
        }
        return totemPoles;
    }

    public boolean isActiveOrAssembling() {
        return !state.equals(TotemBaseState.INACTIVE);
    }

    public Optional<TotemPoleBlockEntity> getFirstTotemPole() {
        if (level.getBlockEntity(worldPosition.above()) instanceof TotemPoleBlockEntity totemPole) {
            return Optional.of(totemPole);
        }
        return Optional.empty();
    }
}