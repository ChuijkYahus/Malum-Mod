package com.sammy.malum.common.item.curiosities.pouch;

import com.sammy.malum.common.data.component.pouch.*;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.registry.common.item.*;

import com.sammy.malum.registry.common.sound.*;
import net.minecraft.core.*;
import net.minecraft.network.chat.*;
import net.minecraft.server.level.*;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.item.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.event.entity.player.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.modules.toolkit.sound.SoundPlayer;

import java.util.*;

public class RavenousPouchItem extends MalumPouchItem {

    public RavenousPouchItem(net.minecraft.world.item.Item.Properties properties) {
        super(properties.component(MalumDataComponents.RAVENOUS_POUCH_CONTENTS, RavenousPouchContentsComponent.EMPTY));
    }

    public static float getFullnessDisplay(ItemStack stack) {
        var contents = stack.getOrDefault(MalumDataComponents.RAVENOUS_POUCH_CONTENTS, RavenousPouchContentsComponent.EMPTY);
        return contents.weight().floatValue();
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        tooltipComponents.add(TooltipComponentHelper.pouchEffect("ravenous_pouch_collection"));
        tooltipComponents.add(TooltipComponentHelper.pouchEffect("ravenous_pouch_drop"));
    }

    @Override
    public MalumPouchContentsComponent getContents(ItemStack stack) {
        return stack.getOrDefault(MalumDataComponents.RAVENOUS_POUCH_CONTENTS, RavenousPouchContentsComponent.EMPTY);
    }

    @Override
    public MalumPouchContentsComponent emptyContents() {
        return RavenousPouchContentsComponent.EMPTY;
    }

    @Override
    public void setContents(ItemStack stack, MalumPouchContentsComponent contents) {
        stack.set(MalumDataComponents.RAVENOUS_POUCH_CONTENTS, (RavenousPouchContentsComponent) contents);
    }

    @Override
    protected SoundEvent getExtractSound() {
        return SoundEvents.BUNDLE_REMOVE_ONE;
    }

    @Override
    protected SoundEvent getInsertSound() {
        return MalumSoundEvents.RAVENOUS_POUCH_INSERT.get();
    }

    @Override
    protected SoundEvent getDropSound() {
        return SoundEvents.BUNDLE_DROP_CONTENTS;
    }

    @Override
    protected boolean dropContents(ItemStack stack, MalumPouchContentsComponent contents, Player player) {
        if (!contents.isEmpty()) {
            if (player instanceof ServerPlayer) {
                MalumPouchContentsComponent.Mutable mutable = contents.mutable();
                mutable.clearItems();
                contents.getItems().forEach(item -> {
                    ItemStack droppedItem = item.copy();
                    if (!mutable.containsItem(item.getItem()) && droppedItem.getCount() > 1) {
                        ItemStack split = droppedItem.split(1);
                        mutable.addItem(split);
                    }
                    player.drop(droppedItem, true);
                });
                setContents(stack, mutable.immutable());
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void playInsertSound(Entity entity) {
        SoundPlayer.create(MalumSoundEvents.RAVENOUS_POUCH_INSERT).volume(0.8f).pitch(0.8f, 1.2f).play(entity);
    }

    public static void trySwallowItem(ItemEntityPickupEvent.Pre event) {
        if (event.canPickup().isFalse()) {
            return;
        }
        ItemEntity itemEntity = event.getItemEntity();
        if (itemEntity.hasPickUpDelay()) {
            return;
        }
        Player player = event.getPlayer();
        ItemStack pickedUp = itemEntity.getItem();
        for (NonNullList<ItemStack> playerInventory : player.getInventory().compartments) {
            for (ItemStack item : playerInventory) {
                if (item.has(MalumDataComponents.RAVENOUS_POUCH_CONTENTS)) {
                    if (player.getCooldowns().isOnCooldown(item.getItem())) {
                        continue;
                    }
                    trySwallowItem(player, item, pickedUp);
                }
            }
        }
    }
    public static void trySwallowItem(Player player, ItemStack stack, ItemStack pickedUp) {
        var contents = stack.get(MalumDataComponents.RAVENOUS_POUCH_CONTENTS);
        if (contents != null) {
            if (contents.containsItem(pickedUp.getItem())) {
                var mutable = new RavenousPouchContentsComponent.Mutable(contents);
                int i = mutable.tryInsert(pickedUp);
                if (i > 0) {
                    if (stack.getItem() instanceof RavenousPouchItem pouch) {
                        pouch.playInsertSound(player);
                    }
                }
                stack.set(MalumDataComponents.RAVENOUS_POUCH_CONTENTS, mutable.immutable());
            }
        }
    }
}