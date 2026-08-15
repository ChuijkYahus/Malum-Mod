package com.sammy.malum.common.item.soulTags;

import com.sammy.malum.common.entity.soulTag.SoulTagEntity;
import com.sammy.malum.registry.common.item.MalumDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.UUID;

public class SoulTagItem extends Item {

    public SoulTagItem(Properties properties) {
        super(properties);
    }

    public static void setTarget(ItemStack stack, UUID uuid) {
        stack.set(MalumDataComponents.SOUL_TAG_TARGET.get(), uuid);
    }

    public static UUID getTarget(ItemStack stack) {
        return stack.get(MalumDataComponents.SOUL_TAG_TARGET.get());
    }

    public static boolean hasTarget(ItemStack stack) {
        return stack.has(MalumDataComponents.SOUL_TAG_TARGET.get());
    }

    public static void setTargetName(ItemStack stack, Component name) {
        stack.set(MalumDataComponents.SOUL_TAG_TARGET_NAME.get(),name);
    }

    public static Component getTargetName(ItemStack stack) {
        return stack.get(MalumDataComponents.SOUL_TAG_TARGET_NAME.get());
    }

    public static boolean hasTargetName(ItemStack stack) {
        return stack.has(MalumDataComponents.SOUL_TAG_TARGET_NAME.get());
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack stack, Player player) {
        if (!player.level().isClientSide()) {

            SoulTagEntity entity = new SoulTagEntity(player.level());

            entity.setItem(stack);

            entity.setPos(
                    player.getX(),
                    player.getEyeY() - 0.1,
                    player.getZ()
            );

            Vec3 look = player.getLookAngle();

            entity.setDeltaMovement(look.scale(3.35D));

            player.level().addFreshEntity(entity);
        }

        return false;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);

        Component targetName = getTargetName(stack);

        if (targetName != null) {
            tooltip.add(
                    Component.translatable(
                            "item.malum.soul_tag.target",
                            targetName
                    ).withStyle(ChatFormatting.DARK_GREEN)
            );
        }
    }
}