package com.sammy.malum.common.item.curiosities.pouch;

import com.sammy.malum.common.data.component.pouch.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.item.*;
import net.minecraft.*;
import net.minecraft.core.*;
import net.minecraft.core.component.*;
import net.minecraft.network.chat.*;
import net.minecraft.server.level.*;
import net.minecraft.sounds.*;
import net.minecraft.stats.*;
import net.minecraft.util.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.item.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.inventory.*;
import net.minecraft.world.inventory.tooltip.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.neoforged.neoforge.event.entity.player.*;
import net.neoforged.neoforge.registries.*;
import org.apache.commons.lang3.math.*;
import team.lodestar.lodestone.helpers.*;

import javax.annotation.*;
import java.util.*;

public abstract class MalumPouchItem extends Item {
    protected static final int BAR_COLOR = Mth.color(0.4F, 0.4F, 1.0F);

    public MalumPouchItem(Properties properties) {
        super(properties);
    }
    public abstract MalumPouchContentsComponent getContents(ItemStack stack);

    public abstract MalumPouchContentsComponent emptyContents();

    public abstract void setContents(ItemStack stack, MalumPouchContentsComponent contents);

    @Override
    public boolean overrideStackedOnOther(ItemStack stack, Slot slot, ClickAction action, Player player) {
        if (stack.getCount() != 1 || action != ClickAction.SECONDARY) {
            return false;
        } else {
            var contents = getContents(stack);
            if (contents == null) {
                return false;
            } else {
                var itemstack = slot.getItem();
                var mutable = contents.mutable();
                if (itemstack.isEmpty()) {
                    this.playRemoveOneSound(player);
                    var tryRemove = mutable.removeOne();
                    if (tryRemove != null) {
                        var removed = slot.safeInsert(tryRemove);
                        mutable.tryInsert(removed);
                    }
                } else if (itemstack.getItem().canFitInsideContainerItems()) {
                    int i = mutable.tryTransfer(slot, player);
                    if (i > 0) {
                        this.playInsertSound(player);
                    }
                }
                setContents(stack, mutable.immutable());
                return true;
            }
        }
    }

    @Override
    public boolean overrideOtherStackedOnMe(ItemStack stack, ItemStack other, Slot slot, ClickAction action, Player player, SlotAccess access) {
        if (stack.getCount() != 1) {
            return false;
        }
        if (action == ClickAction.SECONDARY && slot.allowModification(player)) {
            var contents = getContents(stack);
            if (contents == null) {
                return false;
            } else {
                var mutable = contents.mutable();
                if (other.isEmpty()) {
                    var removed = mutable.removeOne();
                    if (removed != null) {
                        this.playRemoveOneSound(player);
                        access.set(removed);
                    }
                } else {
                    int i = mutable.tryInsert(other);
                    if (i > 0) {
                        this.playInsertSound(player);
                    }
                }

                setContents(stack, mutable.immutable());
                return true;
            }
        } else {
            return false;
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack itemstack = player.getItemInHand(usedHand);
        if (dropContents(itemstack, player)) {
            this.playDropContentsSound(player);
            player.awardStat(Stats.ITEM_USED.get(this));
            player.getCooldowns().addCooldown(itemstack.getItem(), 200);
            return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
        } else {
            return InteractionResultHolder.fail(itemstack);
        }
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        var contents = getContents(stack);
        if (contents == null) {
            return false;
        }
        return contents.weight().compareTo(Fraction.ZERO) > 0;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        var contents = getContents(stack);
        if (contents == null) {
            return 1;
        }
        return Math.min(1 + Mth.mulAndTruncate(contents.weight(), 12), 13);
    }

    @Override
    public int getBarColor(ItemStack stack) {
        return BAR_COLOR;
    }

    protected final boolean dropContents(ItemStack stack, Player player) {
        var contents = getContents(stack);
        if (contents == null) {
            return false;
        }
        return dropContents(stack, contents, player);
    }

    protected boolean dropContents(ItemStack stack, MalumPouchContentsComponent contents, Player player) {
        if (!contents.isEmpty()) {
            setContents(stack, emptyContents());
            if (player instanceof ServerPlayer) {
                contents.getItemsCopy().forEach(item -> player.drop(item, true));
            }

            return true;
        } else {
            return false;
        }
    }

    @Override
    public Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        return !stack.has(DataComponents.HIDE_TOOLTIP) && !stack.has(DataComponents.HIDE_ADDITIONAL_TOOLTIP)
                ? Optional.ofNullable(getContents(stack))
                : Optional.empty();
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        var contents = getContents(stack);
        if (contents != null) {
            int max = contents.getStorageSize();
            int i = Mth.mulAndTruncate(contents.weight(), max);
            tooltipComponents.add(Component.translatable("item.minecraft.bundle.fullness", i, max).withStyle(ChatFormatting.GRAY));
        }
    }

    @Override
    public void onDestroyed(ItemEntity itemEntity) {
        var contents = getContents(itemEntity.getItem());
        if (contents != null) {
            setContents(itemEntity.getItem(), emptyContents());
            ItemUtils.onContainerDestroyed(itemEntity, contents.getItemsCopy());
        }
    }

    public void playRemoveOneSound(Entity entity) {
        entity.playSound(SoundEvents.BUNDLE_REMOVE_ONE, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
    }

    public void playInsertSound(Entity entity) {
        //Side Agnostic Implementation
        if (!entity.level().isClientSide) {
            SoundHelper.playSound(entity, SoundEvents.BUNDLE_INSERT, 0.8f, 0.8f + entity.level().getRandom().nextFloat() * 0.4F);
        }
        entity.playSound(SoundEvents.BUNDLE_INSERT, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
    }

    public void playDropContentsSound(Entity entity) {
        entity.playSound(SoundEvents.BUNDLE_DROP_CONTENTS, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
    }
}