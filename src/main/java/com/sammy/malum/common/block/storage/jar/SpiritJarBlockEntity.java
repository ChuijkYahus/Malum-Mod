package com.sammy.malum.common.block.storage.jar;

import com.sammy.malum.common.data.component.*;
import com.sammy.malum.common.data.component.pouch.*;
import com.sammy.malum.common.item.spirit.SpiritShardItem;
import com.sammy.malum.registry.common.block.MalumBlockEntities;
import com.sammy.malum.registry.common.item.MalumDataComponents;
import com.sammy.malum.registry.common.sound.*;
import com.sammy.malum.visual_effects.SpiritLightSpecs;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.*;
import net.minecraft.nbt.*;
import net.minecraft.sounds.*;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.items.*;
import org.jetbrains.annotations.NotNull;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.helpers.block.*;
import team.lodestar.lodestone.modules.core.easing.Easing;
import team.lodestar.lodestone.modules.toolkit.blockentity.*;

import java.util.*;
import java.util.function.Supplier;

public class SpiritJarBlockEntity extends LodestoneBlockEntity implements IInventoryCapabilityProvider {

    public SpiritJarBlockEntity(LodestoneBlockEntityType<? extends SpiritJarBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public SpiritJarBlockEntity(BlockPos pos, BlockState state) {
        this(MalumBlockEntities.SPIRIT_JAR.get(), pos, state);
    }

    public SpiritJarContentsComponent contents;

    // Storage Drawers moment
    private long lastClickTime;
    private UUID lastClickUUID;

    private final Supplier<IItemHandler> inventory = () -> new IItemHandler() {
        @Override
        public int getSlots() {
            return 2;
        }

        @NotNull
        @Override
        public ItemStack getStackInSlot(int slot) {
            if (slot == 0 && contents != null) {
                return contents.createStack();
            }
            return ItemStack.EMPTY;
        }

        @NotNull
        @Override
        public ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
            if (!(stack.getItem() instanceof SpiritShardItem shardItem)) {
                return stack;
            }
            if (slot == 1) {
                if (!simulate) {
                    if (contents == null) {
                        contents = new SpiritJarContentsComponent(shardItem, stack.getCount());
                    } else if (contents.matches(shardItem)) {
                        contents = contents.add(stack.getCount());
                    }
                    if (!level.isClientSide) {
                        BlockStateHelper.updateAndNotifyState(level, worldPosition);
                    }
                }
                return ItemStack.EMPTY;
            }
            return stack;
        }

        @NotNull
        @Override
        public ItemStack extractItem(int slot, int amount, boolean simulate) {
            if (contents == null)
                return ItemStack.EMPTY;

            int amountToExtract = Math.min(contents.count(), amount);
            var result = contents.createStack(amountToExtract);
            if (!simulate) {
                contents = contents.remove(amountToExtract);
                if (!level.isClientSide) {
                    BlockStateHelper.updateAndNotifyState(level, worldPosition);
                }
            }
            return result;
        }

        @Override
        public int getSlotLimit(int slot) {
            if (slot == 0)
                return contents == null ? 64 : Math.min(64, contents.count());
            return 64;
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            if (stack.getItem() instanceof SpiritShardItem shardItem) {
                return contents == null || contents.matches(shardItem);
            }
            return false;
        }
    };


    @Override
    public ItemInteractionResult onUseWithItem(Player player, ItemStack pStack, InteractionHand pHand) {
        if (getLevel() == null) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }

        int inserted;
        if (getLevel().getGameTime() - lastClickTime < 10 && player.getUUID().equals(lastClickUUID)) {
            inserted = insertAllSpirits(player);
        } else {
            inserted = insertHeldItem(player);
        }
        if (inserted > 0) {
            SoundHelper.playSound(player, MalumSoundEvents.PEDESTAL_SPIRIT_INSERT.get(), SoundSource.BLOCKS, 0.7f, Easing.SINE_IN_OUT.asWeighedRandom(player.getRandom(), 0.8f, 1.2f));
        }

        lastClickTime = getLevel().getGameTime();
        lastClickUUID = player.getUUID();

        if (!level.isClientSide) {
            BlockStateHelper.updateAndNotifyState(level, worldPosition);
        }
        return ItemInteractionResult.sidedSuccess(level.isClientSide);
    }

    public boolean handleAttack(Player pPlayer) {
        ItemStack item = getInventory(Direction.DOWN).extractItem(0, pPlayer.isShiftKeyDown() ? 64 : 1, false);
        if (!item.isEmpty()) {
            ItemHandlerHelper.giveItemToPlayer(pPlayer, item, pPlayer.getInventory().selected);
            if (!level.isClientSide()) {
                BlockStateHelper.updateAndNotifyState(level, worldPosition);
                SoundHelper.playSound(pPlayer, MalumSoundEvents.PEDESTAL_SPIRIT_PICKUP.get(), SoundSource.BLOCKS, 0.7f, Easing.SINE_IN_OUT.asWeighedRandom(pPlayer.getRandom(), 0.8f, 1.2f));
            }

            return true;
        }

        return false;
    }

    public int insertHeldItem(Player player) {
        int count = 0;
        ItemStack playerStack = player.getInventory().getSelected();
        if (!playerStack.isEmpty())
            count = insertFromStack(playerStack, player);

        return count;
    }

    public int insertAllSpirits(Player player) {
        if (contents == null)
            return 0;

        int count = 0;
        for (int i = 0, n = player.getInventory().getContainerSize(); i < n; i++) {
            ItemStack subStack = player.getInventory().getItem(i);
            if (!subStack.isEmpty()) {
                int subCount = insertFromStack(subStack, player);
                if (subCount > 0 && subStack.getCount() == 0)
                    player.getInventory().setItem(i, ItemStack.EMPTY);

                count += subCount;
            }
        }

        return count;
    }

    public int insertFromStack(ItemStack stack, Player player) {
        int inserted = 0;

        MalumPouchContentsComponent pouchContents = stack.get(MalumDataComponents.SOULWOVEN_POUCH_CONTENTS);
        if (pouchContents != null && !pouchContents.isEmpty()) {
            ArrayList<ItemStack> remainingSpirits = new ArrayList<>();
            for (ItemStack item : pouchContents.getItems()) {
                inserted += insertFromStack(item, player);
                if (!item.isEmpty()) {
                    remainingSpirits.add(item);
                }
            }
            SoundHelper.playSound(player, SoundEvents.BUNDLE_DROP_CONTENTS, SoundSource.BLOCKS, 1.2f, Easing.SINE_IN_OUT.asWeighedRandom(player.getRandom(), 0.8f, 1.2f));
            stack.set(MalumDataComponents.SOULWOVEN_POUCH_CONTENTS, new SoulwovenPouchContentsComponent(remainingSpirits));

            return inserted;
        }
        pouchContents = stack.get(MalumDataComponents.RAVENOUS_POUCH_CONTENTS);
        if (pouchContents != null && !pouchContents.isEmpty()) {
            ArrayList<ItemStack> remainingSpirits = new ArrayList<>();
            for (ItemStack item : pouchContents.getItems()) {
                inserted += insertFromStack(item, player);
                if (!item.isEmpty()) {
                    remainingSpirits.add(item);
                }
            }
            SoundHelper.playSound(player, SoundEvents.BUNDLE_DROP_CONTENTS, SoundSource.BLOCKS, 1.2f, Easing.SINE_IN_OUT.asWeighedRandom(player.getRandom(), 0.8f, 1.2f));
            stack.set(MalumDataComponents.SOULWOVEN_POUCH_CONTENTS, new SoulwovenPouchContentsComponent(remainingSpirits));

            return inserted;
        }
        if (stack.getItem() instanceof SpiritShardItem shardItem) {
            if (contents == null || contents.matches(shardItem)) {
                if (contents == null) {
                    contents = new SpiritJarContentsComponent(shardItem, stack.getCount());
                } else if (contents.matches(shardItem)) {
                    contents = contents.add(stack.getCount());
                }
                inserted += stack.getCount();
                stack.shrink(stack.getCount());
            }
        }
        return inserted;
    }

    @Override
    protected void collectImplicitComponents(DataComponentMap.Builder components) {
        super.collectImplicitComponents(components);
        components.set(MalumDataComponents.SPIRIT_JAR_CONTENTS, contents);
    }

    @Override
    protected void applyImplicitComponents(BlockEntity.DataComponentInput componentInput) {
        super.applyImplicitComponents(componentInput);
        contents = componentInput.get(MalumDataComponents.SPIRIT_JAR_CONTENTS);
    }

    @Override
    public void removeComponentsFromTag(CompoundTag tag) {
        tag.remove("contents");
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        if (contents != null) {
                tag.put("contents", SpiritJarContentsComponent.CODEC.encodeStart(NbtOps.INSTANCE, contents).getOrThrow());
        }
    }

    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        contents = SpiritJarContentsComponent.CODEC.parse(NbtOps.INSTANCE, tag.get("contents")).result().orElse(null);
        super.loadAdditional(tag, registries);
    }

    @Override
    public void clientTick(Level level) {
        if (contents != null) {
            SpiritLightSpecs.rotatingLightSpecs(level, getItemPos(), contents.spirit(), 0.4f, 3);
        }
    }

    public Vec3 getItemPos() {
        double time = (level.getGameTime() * 0.05f) % 6.28f;
        double x = getBlockPos().getX() + 0.5f;
        double y = getBlockPos().getY() + 0.5f + (float) Math.sin(time) * 0.2f;
        double z = getBlockPos().getZ() + 0.5f;
        return new Vec3(x, y, z);
    }

    @Override
    public IItemHandler getInventory(Direction direction) {
        return inventory.get();
    }
}