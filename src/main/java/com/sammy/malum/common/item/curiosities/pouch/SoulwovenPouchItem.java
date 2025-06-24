package com.sammy.malum.common.item.curiosities.pouch;

import com.sammy.malum.common.data.component.pouch.*;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.core.systems.registry.*;
import com.sammy.malum.core.systems.spirit.type.*;
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
import org.apache.commons.lang3.math.*;
import team.lodestar.lodestone.helpers.*;

import java.util.*;

public class SoulwovenPouchItem extends MalumPouchItem {

    public SoulwovenPouchItem(Item.Properties properties) {
        super(properties.component(MalumDataComponents.SOULWOVEN_POUCH_CONTENTS, SoulwovenPouchContentsComponent.EMPTY));
    }

    public static float getFullnessDisplay(ItemStack stack) {
        var contents = stack.getOrDefault(MalumDataComponents.SOULWOVEN_POUCH_CONTENTS, SoulwovenPouchContentsComponent.EMPTY);
        return contents.weight().floatValue();
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        tooltipComponents.add(ComponentHelper.positivePouchEffect("soulwoven_pouch_collection"));
        tooltipComponents.add(ComponentHelper.positivePouchEffect("soulwoven_pouch_storage"));
    }

    @Override
    public MalumPouchContentsComponent getContents(ItemStack stack) {
        return stack.getOrDefault(MalumDataComponents.SOULWOVEN_POUCH_CONTENTS, SoulwovenPouchContentsComponent.EMPTY);
    }

    @Override
    public MalumPouchContentsComponent emptyContents() {
        return SoulwovenPouchContentsComponent.EMPTY;
    }

    @Override
    public void setContents(ItemStack stack, MalumPouchContentsComponent contents) {
        stack.set(MalumDataComponents.SOULWOVEN_POUCH_CONTENTS, (SoulwovenPouchContentsComponent) contents);
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
        if (!pickedUp.is(MalumTags.ItemTags.SOULWOVEN_POUCH_AUTOCOLLECT)) {
            return;
        }
        for (NonNullList<ItemStack> playerInventory : player.getInventory().compartments) {
            for (ItemStack item : playerInventory) {
                if (item.has(MalumDataComponents.SOULWOVEN_POUCH_CONTENTS)) {
                    if (player.getCooldowns().isOnCooldown(item.getItem())) {
                        continue;
                    }
                    trySwallowItem(player, item, pickedUp);
                }
            }
        }
    }
    public static void trySwallowItem(Player player, ItemStack stack, ItemStack pickedUp) {
        var contents = stack.get(MalumDataComponents.SOULWOVEN_POUCH_CONTENTS);
        if (contents != null) {
            var mutable = new SoulwovenPouchContentsComponent.Mutable(contents);
            int i = mutable.tryInsert(pickedUp);
            if (i > 0) {
                if (stack.getItem() instanceof SoulwovenPouchItem pouch) {
                    pouch.playInsertSound(player);
                }
            }
            stack.set(MalumDataComponents.SOULWOVEN_POUCH_CONTENTS, mutable.immutable());
        }
    }
}